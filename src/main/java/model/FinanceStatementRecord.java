package model;

import java.util.Date;

public class FinanceStatementRecord {
    private String id;

    private String busiInstNo;

    private String busiAppNo;

    private String cardNo;

    private String channelCode;

    private String channelName;

    private String txCode;

    private String txAmount;

    private Date reqTime;

    private String busiReqNo;

    private String batSeqNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBusiInstNo() {
        return busiInstNo;
    }

    public void setBusiInstNo(String busiInstNo) {
        this.busiInstNo = busiInstNo == null ? null : busiInstNo.trim();
    }

    public String getBusiAppNo() {
        return busiAppNo;
    }

    public void setBusiAppNo(String busiAppNo) {
        this.busiAppNo = busiAppNo == null ? null : busiAppNo.trim();
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public String getTxCode() {
        return txCode;
    }

    public void setTxCode(String txCode) {
        this.txCode = txCode == null ? null : txCode.trim();
    }

    public String getTxAmount() {
        return txAmount;
    }

    public void setTxAmount(String txAmount) {
        this.txAmount = txAmount == null ? null : txAmount.trim();
    }

    public Date getReqTime() {
        return reqTime;
    }

    public void setReqTime(Date reqTime) {
        this.reqTime = reqTime;
    }

    public String getBusiReqNo() {
        return busiReqNo;
    }

    public void setBusiReqNo(String busiReqNo) {
        this.busiReqNo = busiReqNo == null ? null : busiReqNo.trim();
    }

    public String getBatSeqNo() {
        return batSeqNo;
    }

    public void setBatSeqNo(String batSeqNo) {
        this.batSeqNo = batSeqNo == null ? null : batSeqNo.trim();
    }
}