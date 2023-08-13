package com.importsource.springcloud;


import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.cloud.contract.wiremock.restdocs.SpringCloudContractRestDocs;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;

import static org.springframework.cloud.contract.wiremock.restdocs.WireMockRestDocs.verify;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringCloudContractApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@AutoConfigureRestDocs(outputDir = "target/snippets")
@AutoConfigureMockMvc
public class SpringCloudContractApplicationTests {
    @Resource
    private MockMvc mockMvc;
    @Autowired
    private CustomerRestController customerRestController;
/*	@MockBean
	private CustomerRepository customerRepository;*/


    @Before
    public void before() {
		/*Mockito.when(customerRepository.findAll()).thenReturn(
				Arrays.asList(new Customer(1L, "sam"), new Customer(2L, "andy")));*/

        RestAssuredMockMvc.standaloneSetup(this.customerRestController);
    }
    @Test
    public void case1() throws Exception {
        System.out.println("xxxxxxxxxxcase1");
     mockMvc.perform(MockMvcRequestBuilders.get("/api/customers"))
                .andExpect(status().isOk())
                .andDo(verify().stub("case199"))
                .andDo(document("case1", SpringCloudContractRestDocs.dslContract()));

    }


   /* @Test
    public void case12() throws Exception {
        System.out.println(customerRestController.getCustomers());
    }*/

}
