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
   * 下面代码可以给android端使用，具体的前后端分离使用ajax的方式获取tgt和st，方便浏览器保存有全局的tgt和sso服务认证中心端保持
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
    	
    	LOGGER.info("###########################################start#####################################################");
    	
    	LOGGER.info("【=======--------->>登录的用户名和密码：username = {},password = {}】",username,password);
        String url = "http://localhost:8080/cas/v1/tickets";  
        
        try {  
            HttpURLConnection hsu = (HttpURLConnection) openConn(url);  
            String s = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");  
            s += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");  
  
            LOGGER.info("【=======--------->>rest登录获取TGT地址的服务前缀！={}】",url); 
            LOGGER.info("【=======--------->>rest登录获取TGT地址的用户名和密码编码encode_username_password={} 】",s);
            OutputStreamWriter out = new OutputStreamWriter(hsu.getOutputStream());  
            BufferedWriter bwr = new BufferedWriter(out);  
            bwr.write(s);  
            bwr.flush();  
            bwr.close();  
            out.close();  
  
            String tgt = hsu.getHeaderField("location");  
            if (tgt != null && hsu.getResponseCode() == 201) {  
            	LOGGER.info("【=======--------->>得到Tgt is = {} 】",tgt.substring(tgt.lastIndexOf("/") + 1));
                tgt = tgt.substring(tgt.lastIndexOf("/") + 1);  
                bwr.close();  
                closeConn(hsu);  
  
                String serviceURL = "http://localhost:8989/sso-client/restlogin";  
                String encodedServiceURL = URLEncoder.encode("service", "utf-8") + "="  
                        + URLEncoder.encode(serviceURL, "utf-8");  
                
                
                String myURL = url + "/" + tgt;  
                LOGGER.info("【=======--------->>根据tgt获取 st 地址的前缀  = {} 】",myURL);
                LOGGER.info("【=======--------->>根据tgt获取 st 地址的编码服务Service url is  = {} 】",encodedServiceURL);
                
                hsu = (HttpURLConnection) openConn(myURL);  
                out = new OutputStreamWriter(hsu.getOutputStream());  
                bwr = new BufferedWriter(out);  
                bwr.write(encodedServiceURL);  
                bwr.flush();  
                bwr.close();  
                out.close();  
  
  
                BufferedReader isr = new BufferedReader(new InputStreamReader(hsu.getInputStream()));  
                String line;  
                while ((line = isr.readLine()) != null) {  
                    LOGGER.info("【=======--------->>获取st打印响应line  = {} 】",line);
                }  
                LOGGER.info("###########################################end#####################################################");
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
