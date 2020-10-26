package com.evan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@SpringBootApplication
@EnableFeignClients
@RestController
public class NpeDemo {
    @Autowired
    HostnameClient hostnameClient;

    @RequestMapping("/hostnames")
    public String hostnames() {
        return hostnameClient.hello();
    }

    @FeignClient(value = "hostnames")
    interface HostnameClient {
        @RequestMapping(value = "/", method = GET)
        String hello();
    }

    public static void main(String[] args) {
        SpringApplication.run(NpeDemo.class, args);
    }
}
