package service;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Lenovo on 2018/7/5.
 */
@Service
@Slf4j
public class PayService {
    @Value("${URL.payservice}")
    private String remoteUrl;
    public JSONObject getPayDetail(String instNo, String today) {
        String result = "";
        BufferedReader reader = null;
        URL url = null;
        HttpURLConnection conn = null;
        JSONObject resultObj = null;
        try {
            log.info("remoteUrl : " + remoteUrl);
            url = new URL(remoteUrl+"?busiInstNo="+instNo+"&reqDate="+today);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setUseCaches(false);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            // 设置文件类型:
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            if (conn.getResponseCode() == 200) {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                result = reader.readLine();
                resultObj = JSONObject.parseObject(result);
                if("0".equals(resultObj.get("code").toString())) {
                    log.info("XXL-JOB, 执行成功.");
                }else {
                    log.info("XXL-JOB" + resultObj.get("msg").toString());
                }
            } else {
                log.error("XXL-JOB, 服务器返回错误" + conn.getResponseCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            conn.disconnect();
        }
        return resultObj;
    }
}
