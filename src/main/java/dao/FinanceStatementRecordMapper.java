package dao;

import lyw.model.FinanceStatementRecord;

public interface FinanceStatementRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(FinanceStatementRecord record);

    int insertSelective(FinanceStatementRecord record);

    FinanceStatementRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FinanceStatementRecord record);

    int updateByPrimaryKey(FinanceStatementRecord record);
}