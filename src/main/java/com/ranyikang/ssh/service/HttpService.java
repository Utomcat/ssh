package com.ranyikang.ssh.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.net.URI;

/**
 * CLASS_NAME: HttpService.java <br/>
 *
 * @author ranyk           <br/>
 * @version V1.0           <br/>
 * @description: Http 请求业务测试  <br/>
 * @date: 2024-02-01 <br/>
 */
@Slf4j
@Service
public class HttpService {


    public String xmlTest(String xml){
        String url = "http://127.0.0.1:8080/test/post_1";
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            URI uri = new URIBuilder(url).build();
            HttpPost post = new HttpPost(uri);
            xml = "<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
                    "<root>\n" +
                    "    <Head>\n" +
                    "        <OpName>C001</OpName>\n" +
                    "        <Outsys_Code>奥赛法赛</Outsys_Code>\n" +
                    "        <merch_id>动啊发</merch_id>\n" +
                    "        <OpDate>发动昂</OpDate>\n" +
                    "    </Head>\n" +
                    "    <Param>\n" +
                    "        <Pay_Account>AAAA</Pay_Account>\n" +
                    "        <Pay_Name>测试账号</Pay_Name>\n" +
                    "        <Sub_Pay_Account>AAA</Sub_Pay_Account>\n" +
                    "        <Sub_Pay_Name>测试账号</Sub_Pay_Name>\n" +
                    "        <Rec_Name>啊啊</Rec_Name>\n" +
                    "        <Rec_Account>4444</Rec_Account>\n" +
                    "        <Rec_Bank>ad发赛</Rec_Bank>\n" +
                    "        <Rec_Bank_Id>ad发赛</Rec_Bank_Id>\n" +
                    "        <Amount>扥</Amount>\n" +
                    "        <Currency>打发</Currency>\n" +
                    "        <EnterpriseID>安达芬</EnterpriseID>\n" +
                    "        <VoucherID>大幅度</VoucherID>\n" +
                    "        <Area_Code>东奥扥爱上</Area_Code>\n" +
                    "        <Usage>答复东奥</Usage>\n" +
                    "        <Abs_Code>奥赛法赛</Abs_Code>\n" +
                    "        <Remark>大扥奥迪</Remark>\n" +
                    "        <Same_Bank>东奥房东</Same_Bank>\n" +
                    "        <Same_City>东奥房东</Same_City>\n" +
                    "        <Hurry_Flag>1</Hurry_Flag>\n" +
                    "        <Xchg_Code>大森达</Xchg_Code>\n" +
                    "        <Person_Flag>0</Person_Flag>\n" +
                    "        <Reserved1>1</Reserved1>\n" +
                    "        <Reserved2>2</Reserved2>\n" +
                    "        <Reserved3>3</Reserved3>\n" +
                    "        <Reserved4>4</Reserved4>\n" +
                    "    </Param>\n" +
                    "</root>";
            log.info("原参数值 ==> {}", xml);
            String param = new String(xml.getBytes("GBK"), "GBK");
            log.info("请求参数为 ==> {}", param);
            StringEntity entity = new StringEntity(param);
            entity.setContentEncoding(new BasicHeader("contentType","text/xml"));
            post.setEntity(entity);
            post.setHeader("Content-Type","text/xml");
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(35000).build();
            post.setConfig(requestConfig);
            CloseableHttpResponse response = httpClient.execute(post);
            HttpEntity responseEntity = response.getEntity();
            String result = null;
            if (responseEntity != null){
                result = EntityUtils.toString(responseEntity);
            }
            response.close();
            httpClient.close();
            log.info("响应结果为 ==> {}", result);
            return result;
        }
        catch (Exception e){
            log.error("请求异常, 异常为 ==> {}", e.getMessage());
            return "";
        }
    }

}
