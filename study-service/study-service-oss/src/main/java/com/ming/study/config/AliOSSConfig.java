package com.ming.study.config;

import lombok.Data;

import java.io.IOException;
import java.util.Properties;

/**
 * @author: ziming
 * @Time: 2022/11/9 15:39
 */
@Data
public class AliOSSConfig {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String bucketEndpoint;


    private  AliOSSConfig(){
        Properties properties = new Properties();
        try {
            properties.load(AliOSSConfig.class.getResourceAsStream("/oss/alioss.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        endpoint = properties.getProperty("endpoint");
        accessKeyId= properties.getProperty("accessKeyId");
        accessKeySecret= properties.getProperty("accessKeySecret");
        bucketName= properties.getProperty("bucketName");
        bucketEndpoint= properties.getProperty("bucketEndpoint");
    }

    private static AliOSSConfig instance = new AliOSSConfig();

    public static AliOSSConfig getInstance() {
        return instance;
    }
}
