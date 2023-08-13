package com.importsource.springcloud;


import com.importsource.springcloud.common.Page;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * @author hezhuofan groupId:artifactId:version:classifier:port
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringCloudContractConsumerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureStubRunner( workOffline = true,
        ids = {"com.importsource.springcloud:spring-cloud-contract-provider:+:stubs:41565"})
public class SpringCloudContractConsumerApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testGetCustomers() {
        ParameterizedTypeReference<Page> ptf =
                new ParameterizedTypeReference<Page>() {
                };
        ResponseEntity<Page> responseEntity =
                restTemplate.exchange("http://localhost:41565/api/customers", HttpMethod.GET, null, ptf);
        System.out.println(responseEntity.getBody().getData());
        Assert.assertEquals("size error!", 2, responseEntity.getBody().getData().size());
    }
}
