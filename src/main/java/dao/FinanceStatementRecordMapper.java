package dao;
import model.FinanceStatementRecord;

import java.util.Date;
import java.util.List;

public interface FinanceStatementRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(FinanceStatementRecord record);

    int insertSelective(FinanceStatementRecord record);

    FinanceStatementRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FinanceStatementRecord record);

    int updateByPrimaryKey(FinanceStatementRecord record);

    /**
     *
     * @param start
     * @param end
     * @return
     */
    List<FinanceStatementRecord> selectByParams(Date start, Date end);
}