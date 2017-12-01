package cn.wcj.sso.controller;

import java.io.BufferedReader;  
import java.io.BufferedWriter;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.OutputStreamWriter;  
import java.net.HttpURLConnection;  
import java.net.MalformedURLException;  
import java.net.URL;  
import java.net.URLConnection;  
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;  
  /**
   * 前后端分离的情况之下rest风格登录获取TGT ST
   * blog:http://equalxx.iteye.com/blog/2336030
   * @Description:TODO
   * @author:hsj qq:2356899074
   * @time:2017年12月1日 下午2:50:17
   */
public class RestFulLogin {  
	private final static Logger LOGGER = LoggerFactory.getLogger(RestFulLogin.class);
  
    public static void main(String... args) throws Exception {  
        String username = "hsjhsj";  
        String password = "hsjhsj";  
        validateFromCAS(username, password);  
    }  
  
    public static boolean validateFromCAS(String username, String password) throws Exception {  
    	LOGGER.info("username = {},password = {}",username,password);
        String url = "http://localhost:8080/cas/v1/tickets";  
        LOGGER.info("cas/v1/tickets={} ",url);
        try {  
            HttpURLConnection hsu = (HttpURLConnection) openConn(url);  
            String s = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");  
            s += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");  
  
            LOGGER.info("rest 登录获取TGT！={}",hsu); 
            OutputStreamWriter out = new OutputStreamWriter(hsu.getOutputStream());  
            BufferedWriter bwr = new BufferedWriter(out);  
            bwr.write(s);  
            bwr.flush();  
            bwr.close();  
            out.close();  
  
            String tgt = hsu.getHeaderField("location");  
            System.out.println(hsu.getResponseCode());  
            if (tgt != null && hsu.getResponseCode() == 201) {  
                System.out.println(tgt);  
  
                System.out.println("Tgt is : " + tgt.substring(tgt.lastIndexOf("/") + 1));  
                tgt = tgt.substring(tgt.lastIndexOf("/") + 1);  
                bwr.close();  
                closeConn(hsu);  
  
                String serviceURL = "http://localhost:8989/sso-client/restlogin";  
                String encodedServiceURL = URLEncoder.encode("service", "utf-8") + "="  
                        + URLEncoder.encode(serviceURL, "utf-8");  
                System.out.println("Service url is : " + encodedServiceURL);  
  
                String myURL = url + "/" + tgt;  
                System.out.println(myURL);  
                hsu = (HttpURLConnection) openConn(myURL);  
                out = new OutputStreamWriter(hsu.getOutputStream());  
                bwr = new BufferedWriter(out);  
                bwr.write(encodedServiceURL);  
                bwr.flush();  
                bwr.close();  
                out.close();  
  
                System.out.println("Response code is:  " + hsu.getResponseCode());  
  
                BufferedReader isr = new BufferedReader(new InputStreamReader(hsu.getInputStream()));  
                String line;  
                System.out.println(hsu.getResponseCode());  
                while ((line = isr.readLine()) != null) {  
                    System.out.println(line);  
                }  
                isr.close();  
                hsu.disconnect();  
                return true;  
  
            } else {  
                return false;  
            }  
  
        } catch (MalformedURLException mue) {  
            mue.printStackTrace();  
            throw mue;  
  
        } catch (IOException ioe) {  
            ioe.printStackTrace();  
            throw ioe;  
        }  
  
    }  
  
    static URLConnection openConn(String urlk) throws MalformedURLException, IOException {  
  
        URL url = new URL(urlk);  
        HttpURLConnection hsu = (HttpURLConnection) url.openConnection();  
        hsu.setDoInput(true);  
        hsu.setDoOutput(true);  
        hsu.setRequestMethod("POST");  
        return hsu;  
  
    }  
  
    static void closeConn(HttpURLConnection c) {  
        c.disconnect();  
    }  
  
}  
