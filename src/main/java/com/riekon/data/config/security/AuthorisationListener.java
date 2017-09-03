package com.riekon.data.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.access.event.AuthorizationFailureEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthorisationListener implements ApplicationListener<AuthorizationFailureEvent> {
    public static final Logger logger = LoggerFactory.getLogger(AuthorisationListener.class);

    @Override
    public void onApplicationEvent(AuthorizationFailureEvent event) {
        logger.error("Failed authorization attempt for user {}", event.getAuthentication().getName());
        logger.error("  principle: {}", event.getAuthentication().getPrincipal());
    }
}
