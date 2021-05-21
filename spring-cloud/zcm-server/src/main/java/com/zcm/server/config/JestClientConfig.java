package com.zcm.server.config;
import io.searchbox.client.*;
import io.searchbox.client.config.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;

/**
 * @ClassName: JestClientConfig
 * @Author: FJW
 * @Date: 2021/4/19 15:57
 * @Version: 1.0.0
 * @Description: ElasticSearch  JestClient
 */
@Configuration
public class JestClientConfig {

    // ES 数据库地址
    @Value("${elasticsearch.jestClient.uris}")
    private String serverUri;

    @Bean
    public JestClient getJestCline() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(serverUri)
                .multiThreaded(true)
                .connTimeout(1200)
                .readTimeout(1200)
                .build());
        return factory.getObject();
    }
}
