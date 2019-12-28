package com.hand;

import io.choerodon.resource.annoation.EnableChoerodonResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class EasyPoiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyPoiApplication.class, args);
    }

}
