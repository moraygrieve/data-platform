package com.riekon.data.controller;

import com.riekon.data.model.MetaModel;
import com.riekon.data.service.MetaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/meta")
public class MetaController {
    public static final Logger logger = LoggerFactory.getLogger(MetaController.class);

    @Autowired
    MetaService metaService;

    @PreAuthorize("hasAuthority('user')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> add(@RequestBody MetaModel entry) {
        logger.info("Received new meta-data with details {}", entry.toString());

        UUID uuid = UUID.randomUUID();
        entry.setUuid(uuid.toString());
        metaService.addEntry(entry);

        logger.info("Meta-data entry has been assigned uuid {}", uuid.toString());
        return new ResponseEntity<String>(uuid.toString(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('super_user')")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<String> delete(@RequestParam(value="uuid") String uuid) {
        logger.info("Received request to delete meta-data with uuid {}", uuid);

        metaService.deleteEntry(new MetaModel(uuid));
        logger.info("Meta-data successfully deleted for uuid {}", uuid.toString());
        return new ResponseEntity<String>("Meta-data deleted successfully", HttpStatus.OK);
    }
}