package com.jks.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.jks.demo.utils.JenkinsConnect;
import com.jks.demo.utils.MyJenkinsHttpClient;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liyang(leonasli)
 * @className DemoTest
 * @description TODO
 * @create 2022/5/11 16:17
 **/
@RunWith(SpringRunner.class)
public class DemoTest {

//    String jsonPath = "E:\\workspace\\demotest\\src\\main\\java\\com\\jks\\demo\\files\\pipeline.json";

    String jenkinsIp = "http://10.143.128.9:49000";


    @Test
    public void convertTest() throws IOException {

        String content = new String(Files.readAllBytes(Paths.get("E:\\workspace\\demotest\\src\\main\\java\\com\\jks\\demo\\files\\pipeline.json")));

//        FileInputStream fis = new FileInputStream("E:\\workspace\\demotest\\src\\main\\java\\com\\jks\\demo\\files\\pipeline.json");
//        byte[] buffer = new byte[10];
//        StringBuilder sb = new StringBuilder();
//        while (fis.read(buffer) != -1) {
//            sb.append(new String(buffer));
//            buffer = new byte[10];
//        }
//        fis.close();
//        String content1 = sb.toString();

        System.out.println("1111111="+content);

        List<NameValuePair> data = new ArrayList<NameValuePair>();
        NameValuePair nameValuePair = new BasicNameValuePair("json", content);
        data.add(nameValuePair);
        MyJenkinsHttpClient myJenkinsHttpClient = null;
        myJenkinsHttpClient = JenkinsConnect.getClient(jenkinsIp);
        HttpResponse rs = myJenkinsHttpClient.post_form_with_result("pipeline-model-converter/toJenkinsfile", data, false);//将json转换成jenkinsfile

        JSONObject result = JSONObject.parseObject(IOUtils.toString(rs.getEntity().getContent()));
        JSONObject dataInfo = result.getJSONObject("data");
        String jenkinsfile = dataInfo.getString("jenkinsfile");
        System.out.println("--------1111111111" + jenkinsfile);
    }

    @Test
    public void convertPipelineToJson() throws IOException {
        String pipelineString = new String(Files.readAllBytes(Paths.get("E:\\workspace\\demotest\\src\\main\\java\\com\\jks\\demo\\files\\pipeline.txt")));

        List<NameValuePair> data = new ArrayList<NameValuePair>();
        NameValuePair nameValuePair = new BasicNameValuePair("json", pipelineString);
        data.add(nameValuePair);

        MyJenkinsHttpClient myJenkinsHttpClient = null;
        myJenkinsHttpClient = JenkinsConnect.getClient(jenkinsIp);

        HttpResponse rs = myJenkinsHttpClient.post_form_with_result("pipeline-model-converter/toJson", data, false);//将jenkinsfile转换成json

        JSONObject result = JSONObject.parseObject(IOUtils.toString(rs.getEntity().getContent()));
        JSONObject dataInfo = result.getJSONObject("data");

        System.out.println("--------1111111111" + dataInfo);
    }


}
