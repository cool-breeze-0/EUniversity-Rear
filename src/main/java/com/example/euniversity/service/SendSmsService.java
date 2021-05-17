package com.example.euniversity.service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.stereotype.Service;

@Service
public class SendSmsService {
    public boolean send(String PhoneNumbers, String code) {
        /*连接阿里云*/
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FyZJ9L5gR5VCR67cn5u", "UIkAI7iIuBS5UccC6s6Ll1zUmJbPHA");
        IAcsClient client = new DefaultAcsClient(profile);
        /*创建请求*/
        CommonRequest request = new CommonRequest();
        /*系统规定，切勿修改*/
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");

        request.setSysAction("SendSms");

        //手机号，签名，模板名称
        request.putQueryParameter("PhoneNumbers", PhoneNumbers);
        request.putQueryParameter("SignName", "遇见");
        request.putQueryParameter("TemplateCode", "SMS_200703179");


        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}

