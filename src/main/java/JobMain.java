import dao.FinanceBalanceofaccountRecordMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Lenovo on 2018/7/6.
 */
public class JobMain {
    public static void main(String args[]) {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("config/mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory =new SqlSessionFactoryBuilder().build(inputStream);
        // 是否自动提交
        SqlSession session =sqlSessionFactory.openSession(true);
        FinanceBalanceofaccountRecordMapper fm = session.getMapper(FinanceBalanceofaccountRecordMapper.class);
    }
}
