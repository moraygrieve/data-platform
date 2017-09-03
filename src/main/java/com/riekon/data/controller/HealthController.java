package com.riekon.data.controller;

import com.riekon.data.util.system.ApplicationDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
public class HealthController {
    public static final Logger logger = LoggerFactory.getLogger(HealthController.class);

    @Autowired
    ApplicationDetails applicationDetails;

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/getVersion", method = RequestMethod.GET)
    public ResponseEntity<String> getVersion() {
        logger.info("Received request for version");
        return new ResponseEntity<String>(applicationDetails.getAppVersion(), HttpStatus.OK);
    }
}
