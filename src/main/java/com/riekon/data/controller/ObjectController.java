package com.riekon.data.controller;

import com.riekon.data.model.ObjectFileEntry;
import com.riekon.data.model.ObjectModel;
import com.riekon.data.service.ObjectService;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/object")
public class ObjectController {
    public static final Logger logger = LoggerFactory.getLogger(ObjectController.class);

    @Autowired
    ObjectService objectService;

    @PreAuthorize("hasAuthority('user')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
         public ResponseEntity<String> add(@RequestParam("uuid") String uuid,
                                           @RequestParam("file") MultipartFile[] files) {
        logger.info("Received object upload for uuid {}, primary object {}", uuid, files[0].getOriginalFilename());

        try {
            String ext = FilenameUtils.getExtension(files[0].getOriginalFilename());
            ObjectModel model = new ObjectModel(uuid, new ObjectFileEntry(uuid, ext, files[0].getBytes()));

            int size = files.length;
            for (int i = 1; i < size; i++) {
                logger.info("Adding companion {} file to the primary", files[i].getOriginalFilename());
                ext = FilenameUtils.getExtension(files[i].getOriginalFilename());
                model.addCompanion(new ObjectFileEntry(uuid, ext, files[i].getBytes()));
            }

            objectService.addObject(model);
            return new ResponseEntity<String>("Object uploaded successfully", HttpStatus.OK);
        } catch (IOException e) {
            logger.error("Error saving object to object store", e);
            return new ResponseEntity<String>("Error uploading object", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('super_user')")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<String> delete(@RequestParam(value="key") String key) {
        logger.info("Received request to delete object with uuid {}", key);

        objectService.deleteObject(key);
        logger.info("Object successfully deleted for uuid {}", key);
        return new ResponseEntity<String>("Object deleted successfully", HttpStatus.OK);
    }
}
