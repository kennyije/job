package service;

import common.util.DateUtil;
import dao.FinanceStatementRecordMapper;
import model.FinanceStatementRecord;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by Lenovo on 2018/7/9.
 */
public class FinanceStatementRecordService {
    public List<FinanceStatementRecord> getFinanceStatementRecordByParam(Date start, Date end) {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("config/mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory =new SqlSessionFactoryBuilder().build(inputStream);
        // 是否自动提交
        SqlSession session =sqlSessionFactory.openSession(true);
        FinanceStatementRecordMapper fsr = session.getMapper(FinanceStatementRecordMapper.class);
        List<FinanceStatementRecord> list = fsr.selectByParams(DateUtil.getStartTime(), DateUtil.getEndTime());
        session.close();
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
