package service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import common.constants.BalanceOfAccountConstants;
import common.util.DateUtil;
import common.util.SnowflakeIdWorker;
import dao.FinanceBalanceofaccountRecordMapper;
import model.FinanceBalanceofaccountRecord;
import model.FinanceStatementRecord;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Lenovo on 2018/7/9.
 */
public class FinancebalanceofaccountRecordService {

    private static Logger log = Logger.getLogger(FinancebalanceofaccountRecordService.class);

    public Boolean balanceOfAccount(String instNo) {

        FinanceStatementRecordService financeStatementRecordService = new FinanceStatementRecordService();
        //查询财务系统资金流水记录表当日资金流水
        Date todayStart = DateUtil.getStartTime();
        Date todayEnd = DateUtil.getEndTime();
        List<FinanceStatementRecord> list = financeStatementRecordService.getFinanceStatementRecordByParam(todayStart, todayEnd);

        //调接口获取支付系统当日资金流水
        Date today = Calendar.getInstance().getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");

        log.info("调用支付系统查询资金流水接口入参为:" + today + ", " + instNo);

        PayService payService = new PayService();

        JSONObject obj = payService.getPayDetail(instNo, sdf.format(today));
        if(obj == null) {
            log.error("调用支付系统查询资金流水接口返回失败" + obj);
            return  false;
        }

        log.info("调用支付系统查询资金流水结果为;" + JSONObject.toJSONString(obj));

        if("1".equals(obj.getString("code"))) {
            log.error("调用支付系统查询资金流水接口返回失败:" + JSONObject.toJSONString(obj));
            return false;
        }

        JSONArray array = obj.getJSONArray("txDetails");

        if(array.size() == 0) {
            log.info("支付系统返回资金流水记录条数为0无需进行对账");
            return true;
        }

        if(list == null) {
            log.error("支付系统资金流水条数不为0，财务系统资金流水记录条数为0无法进行对账，请查看各个系统是否推送资金流水到财务系统");
            return false;
        }

        //将List转成Map
        Map<String, FinanceStatementRecord> map = new HashMap<String, FinanceStatementRecord>();

        for(FinanceStatementRecord instance : list) {
            //代付，代扣 用 busiNo作为 key。如果是批量还款就用 busiNo + batNo作为key
            if("2".equals(instance.getTxCode()) || "3".equals(instance.getTxCode())) {
                map.put(instance.getBusiReqNo(), instance);
            }
            if("5".equals(instance.getTxCode())){
                map.put(instance.getBusiReqNo() + instance.getBatSeqNo(), instance);
            }
        }

        //对账
        List<FinanceBalanceofaccountRecord> ReadyToInsert = new ArrayList<FinanceBalanceofaccountRecord>();
        SnowflakeIdWorker sw = new SnowflakeIdWorker(0,0);
        for(int i = 0; i < array.size(); i ++) {
            //支付系统对象
            JSONObject each = (JSONObject) array.get(i);
            String txAmount = each.getString("txAmount");
            String txCode = each.getString("txCode");
            String busiReqNo = each.getString("busiReqNo");
            String batSeqNo = each.getString("batSeqNo");
            //财务系统对象
            //代付，代扣 用 busiNo作为 key。如果是批量还款就用 busiNo + batNo作为key
            FinanceStatementRecord fi = null;
            if("2".equals(txCode) || "3".equals(txCode)) {
                fi = map.get(busiReqNo);
            } else {
                fi = map.get(busiReqNo+batSeqNo);
            }

            FinanceBalanceofaccountRecord ffr = new FinanceBalanceofaccountRecord();
            if(fi == null) {
                ffr.setBalanceResult(BalanceOfAccountConstants.ERROR_ACCOUNT);
            } else if(txAmount.equals(fi.getTxAmount())) {
                ffr.setBalanceResult(BalanceOfAccountConstants.BALANCE);
            } else {
                ffr.setBalanceResult(BalanceOfAccountConstants.NOT_BALANCE);
            }
            if(fi != null) {
                ffr.setBalanceTime(today); //对账时间
                ffr.setId(String.valueOf(sw.nextId())); //主键
                ffr.setBusiInstNoPay(each.getString("busiInstNo")); //机构号
                ffr.setBusiAppNoPay(each.getString("busiAppNo")); //申请号
                ffr.setCardNoPay(each.getString("cardNo")); //卡号
                ffr.setChannelCodePay(each.getString("channelCode")); //渠道码
                ffr.setChannelNamePay(each.getString("channelName")); //渠道名称
                ffr.setTxCodePay(each.getString("txCode")); //付款方向
                ffr.setTxAmountPay(each.getString("txAmount")); //交易金额

                try {
                    ffr.setReqTimePay(sdf1.parse(each.getString("reqTime")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                ffr.setBusiReqNoPay(each.getString("busiReqNo"));//业务号
                ffr.setBatSeqNoPay(each.getString("batSeqNo")); //业务号（批量时使用）

                ffr.setBusiInstNoFinance(fi.getBusiInstNo()); //机构号
                ffr.setBusiAppNoFinance(fi.getBusiAppNo()); //申请号
                ffr.setCardNoFinance(fi.getCardNo()); //卡号
                ffr.setChannelCodeFinance(fi.getChannelCode()); //渠道码
                ffr.setChannelNameFinance(fi.getChannelName()); //渠道名称
                ffr.setTxCodeFinance(fi.getTxCode()); //付款方向
                ffr.setTxAmountFinance(fi.getTxAmount()); //交易金额
                ffr.setReqTimeFinance(fi.getReqTime());
                ffr.setBusiReqNoFinance(fi.getBusiReqNo()); //业务号
                ffr.setBatSeqNoFinance(fi.getBatSeqNo()); //业务号（批量时使用）
            } else {
                ffr.setBalanceTime(today); //对账时间
                ffr.setId(String.valueOf(sw.nextId())); //主键
                ffr.setBusiInstNoPay(each.getString("busiInstNo")); //机构号
                ffr.setBusiAppNoPay(each.getString("busiAppNo")); //申请号
                ffr.setCardNoPay(each.getString("cardNo")); //卡号
                ffr.setChannelCodePay(each.getString("channelCode")); //渠道码
                ffr.setChannelNamePay(each.getString("channelName")); //渠道名称
                ffr.setTxCodePay(each.getString("txCode")); //付款方向
                ffr.setTxAmountPay(each.getString("txAmount")); //交易金额
                try {
                    ffr.setReqTimePay(sdf1.parse(each.getString("reqTime")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                ffr.setBusiReqNoPay(each.getString("busiReqNo"));//业务号
                ffr.setBatSeqNoPay(each.getString("batSeqNo")); //业务号（批量时使用）
            }
            ReadyToInsert.add(ffr);
        }

        //将对账结果插入对账记录表
        this.insertBatch(ReadyToInsert);
        return true;
    }

    public void insertBatch(List<FinanceBalanceofaccountRecord> list) {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("config/mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory =new SqlSessionFactoryBuilder().build(inputStream);
        // 是否自动提交
        SqlSession session =sqlSessionFactory.openSession(false);
        FinanceBalanceofaccountRecordMapper fsr = session.getMapper(FinanceBalanceofaccountRecordMapper.class);
        int count = 1;
        for(FinanceBalanceofaccountRecord record : list) {
            fsr.insertSelective(record);
            count ++;
            if(count % 50 == 0) {
                session.commit();
                session.clearCache();
            }
        }
        session.commit();
        session.close();
    }
}
