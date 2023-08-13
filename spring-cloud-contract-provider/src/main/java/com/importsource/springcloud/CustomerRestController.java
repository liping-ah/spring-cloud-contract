package com.importsource.springcloud;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hezhuofan
 */
@RestController
public class CustomerRestController {

   /* @Autowired
    private CustomerRepository customerRepository;*/

    @RequestMapping(path = "/api/customers")
    public String getCustomers() {
       /* Page page = new Page();

        page.setData(customerRepository.findAll());*/
        String page="xxx";
        return page;
    }
}