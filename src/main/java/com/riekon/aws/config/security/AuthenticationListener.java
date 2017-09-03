package com.riekon.aws.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationListener implements ApplicationListener<AbstractAuthenticationFailureEvent> {
    public static final Logger logger = LoggerFactory.getLogger(AuthenticationListener.class);

    @Override
    public void onApplicationEvent(AbstractAuthenticationFailureEvent event) {
        logger.error("Failed authentication attempt for user {}", event.getAuthentication().getName());
        logger.error("  reason   : password is incorrect");
    }
}