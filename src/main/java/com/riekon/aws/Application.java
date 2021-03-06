package com.riekon.aws;

import com.riekon.aws.util.system.ApplicationDetails;
import com.riekon.aws.util.system.RunTimeDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = {"com.riekon.aws"})
public class Application {
    public static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        ApplicationDetails applicationDetails = ctx.getBean(ApplicationDetails.class);
        logger.info("Started MetaAudioEntry Analytic BLADE data platform, version {}", applicationDetails.getAppVersion());
        RunTimeDetails.logDetails();
    }
}