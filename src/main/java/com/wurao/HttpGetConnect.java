package com.wurao;

/**
 * @author ：c_peizhi
 * @version ：1.0.0
 * @user ：c_PC
 * @date ：Created in 2019/6/14 9:46
 * @description：
 * @modified By：
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @说明：httpclent接口
 * @author: 勿扰
 * @CreateTime:2019年1月10日
 * @ModifyTime:2019年1月10日
 */
public class HttpGetConnect {

    /**
     *  获取html内容
     * @param url
     * @param charsetName  UTF-8、GB2312
     * @return
     * @throws IOException
     */
    public static String connect(String url,String charsetName) throws IOException{
        BasicHttpClientConnectionManager connManager = new BasicHttpClientConnectionManager();

        CloseableHttpClient httpclient = HttpClients.custom()
                .setConnectionManager(connManager)
                .build();
        String content = "";

        try{
            HttpGet httpget = new HttpGet(url);

            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(5000)
                    .setConnectTimeout(50000)
                    .setConnectionRequestTimeout(50000)
                    .build();
            httpget.setConfig(requestConfig);
            httpget.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            httpget.setHeader("Accept-Encoding", "gzip,deflate,sdch");
            httpget.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
            httpget.setHeader("Connection", "keep-alive");
            httpget.setHeader("Upgrade-Insecure-Requests", "1");
            httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
            //httpget.setHeader("Hosts", "www.oschina.net");
            httpget.setHeader("cache-control", "max-age=0");
            httpget.setHeader("cookie", "kg_mid=8e8be2ca5cb0821d9db66d7e5b451a47; kg_dfid=0zylrz0yXS7j0FpQ4H43cLXB; Hm_lvt_aedee6983d4cfc62f509129360d6bb3d=1560476545; kg_dfid_collect=d41d8cd98f00b204e9800998ecf8427e; Hm_lpvt_aedee6983d4cfc62f509129360d6bb3d=1560582838");
            CloseableHttpResponse response = httpclient.execute(httpget);

            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {

                HttpEntity entity = response.getEntity();
                InputStream instream = entity.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(instream,charsetName));
                StringBuffer sbf = new StringBuffer();
                String line = null;
                while ((line = br.readLine()) != null){
                    sbf.append(line + "\n");
                }

                br.close();
                content = sbf.toString();
            } else {
                content = "";
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            httpclient.close();
        }
        //log.info("content is " + content);
        return content;
    }
    private static Log log = LogFactory.getLog(HttpGetConnect.class);
}
