package com.example.jhyangnewthings.constructor.estest;//package com.fiberhome.uqbing.constructor.estest;
//
///**
// * @Author jhYang
// * @Date 2020/4/29 0029 17:55
// * @Discription todo
// */
//
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.IOException;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
//@Configuration
//public class ESClientSpringFactory {
//
//    private Logger logger  = LoggerFactory.getLogger(this.getClass());
//
////    @Value("${spring.data.elasticsearch.cluster-nodes}")
////    private String ipPort;
////    @Value("${spring.data.elasticsearch.cluster-name}")
////    private String clusterName;
//
//    @Bean
//    public  RestHighLevelClient getrestHighLevelClient() throws IOException {
//        RestHighLevelClient client = null;
//        logger.info("ElasticSearch初始化开始。。");
//        try {
//
//            client = new RestHighLevelClient(RestClient.builder(makeHttpHost("localhost:9200")));
//
//            logger.info("ElasticSearch初始化完成。。");
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("ElasticSearch初始化失败：" +  e.getMessage(),e);
//            client.close();
//        }
//
//        return client;
//    }
//
//        private HttpHost makeHttpHost(String s) {
//        String[] address = s.split(":");
//        String ip = address[0];
//        int port = Integer.parseInt(address[1]);
//
//        return new HttpHost(ip, port, "http");
//    }
//
//}