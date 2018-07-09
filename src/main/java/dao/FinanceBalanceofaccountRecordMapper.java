package dao;


import model.FinanceBalanceofaccountRecord;

import java.util.Date;
import java.util.List;

public interface FinanceBalanceofaccountRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(FinanceBalanceofaccountRecord record);

    int insertSelective(FinanceBalanceofaccountRecord record);

    FinanceBalanceofaccountRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FinanceBalanceofaccountRecord record);

    int updateByPrimaryKey(FinanceBalanceofaccountRecord record);

}