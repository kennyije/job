
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import service.FinancebalanceofaccountRecordService;

/**
 * Created by Lenovo on 2018/7/6.
 */
public class JobMain {
    private static Logger log = Logger.getLogger(FinancebalanceofaccountRecordService.class);
    public static void main(String args[]) {
        PropertyConfigurator.configure("log4j.properties");
        FinancebalanceofaccountRecordService financebalanceofaccountRecordService = new FinancebalanceofaccountRecordService();
        if(args != null && args.length > 0) {
            log.info("jar包入参为: " + args[0]);
            financebalanceofaccountRecordService.balanceOfAccount(args[0]);
        }
    }
}
