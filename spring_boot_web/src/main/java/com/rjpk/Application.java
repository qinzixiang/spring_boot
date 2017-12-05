package com.rjpk;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName Application
 * @Description
 * @Author xiangnan.xu
 * @DATE 2017/12/5 10:39
 */
//@Controller
//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication
@EnableAsync
@MapperScan(basePackages = {
        "com.rjpk.core.*.dao.generated",
        "com.rjpk.core.*.dao.customized"
})
@EnableTransactionManagement
@Slf4j
public class Application {

    @Controller
    public class MainController {

        @RequestMapping("/rjpk/index")
        @ResponseBody
        String home() {
            return "欢迎访问人间萍客!";// http://127.0.0.1:8011/rjpk/index
        }
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
