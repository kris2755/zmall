package cn.itsource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class PlatApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(PlatApplication.class,args);
    }
}
