package org.matthenry87.nativetesting.api;

import lombok.extern.slf4j.Slf4j;
import org.matthenry87.nativetesting.api.db1.Db1Service;
import org.matthenry87.nativetesting.api.db2.Db2Service;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(ApiApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(Db1Service db1Service, Db2Service db2Service) {

        return args -> {

            log.info("Injected bean: " + db1Service.toString());
            log.info("Injected bean: " + db2Service.toString());
        };
    }

}
