package dao;

import lyw.model.FinanceBalanceofaccountRecord;

public interface FinanceBalanceofaccountRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(FinanceBalanceofaccountRecord record);

    int insertSelective(FinanceBalanceofaccountRecord record);

    FinanceBalanceofaccountRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FinanceBalanceofaccountRecord record);

    int updateByPrimaryKey(FinanceBalanceofaccountRecord record);
}