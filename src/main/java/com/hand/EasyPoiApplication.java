package com.hand;

import io.choerodon.resource.annoation.EnableChoerodonResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAsync
public class EasyPoiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyPoiApplication.class, args);
    }

    @GetMapping("/get")
    public String inds(){
        return "de";
    }

}
