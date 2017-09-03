package com.riekon.data.service;

import com.riekon.data.model.MetaModel;
import com.riekon.data.repository.MetaRepository;
import com.riekon.data.model.ObjectFileEntry;
import com.riekon.data.model.ObjectModel;
import com.riekon.data.repository.ObjectRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ObjectService {
    public static final Logger logger = LoggerFactory.getLogger(ObjectService.class);

    @Autowired
    MetaRepository metaRepository;

    @Autowired
    ObjectRepository objectRepository;

    @Value("${amazon.s3.bucket}")
    private String amazonS3Bucket;

    public void addObject(ObjectModel model) throws JsonProcessingException {
        String folder = getTimeStamp();
        MetaModel metaModel = metaRepository.find(model.getUuid());

        //@TODO transactional roll back
        if ( metaModel != null ){
            //the primary
            addPrimaryAndUpdateMeta(folder, model.getPrimary(), metaModel);

            //the companions
            for (ObjectFileEntry companion : model.getCompanions())
                addCompanionAndUpdateMeta(folder, companion, metaModel);

            //the meta-data
            addAndUpdateMeta(folder, model, metaModel);

            //update the meta-data
            metaRepository.updateEntry(metaModel);
        }
    }

    public void deleteObject(String key) {
        objectRepository.deleteObject(key);
    }

    private void addPrimaryAndUpdateMeta(String folder, ObjectFileEntry entry, MetaModel metaModel) {
        String key = folder + "/" + entry.getUuid() + "." + entry.getExtension();
        logger.info("Adding primary file ({}) to S3 key {}", entry.getExtension(), key);
        metaModel.getMetaLocationEntry().setPrimary(getS3Path(key));
        objectRepository.addObject(key, entry);
    }

    private void addCompanionAndUpdateMeta(String folder, ObjectFileEntry entry, MetaModel metaModel) {
        String key = folder + "/" + entry.getUuid() + "." + entry.getExtension();
        logger.info("Adding companion file ({}) to S3 key {}", entry.getExtension(), key);
        metaModel.getMetaLocationEntry().addCompanion(getS3Path(key));
        objectRepository.addObject(key, entry);
    }

    private void addAndUpdateMeta(String folder, ObjectModel model, MetaModel metaModel) throws JsonProcessingException {
        String key = folder + "/" + model.getUuid() + ".json";
        logger.info("Adding meta-data companion file to S3 key {}", key);
        byte[] bytesToWrite = (new ObjectMapper()).writeValueAsBytes(metaModel);
        metaModel.getMetaLocationEntry().addCompanion(getS3Path(key));
        objectRepository.addObject(key, new ObjectFileEntry(model.getUuid(), "json", bytesToWrite));
    }

    private String getS3Path(String key) {
        return amazonS3Bucket + "/" + key;
    }

    private String getTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        return sdfDate.format(new Date());
    }
}
