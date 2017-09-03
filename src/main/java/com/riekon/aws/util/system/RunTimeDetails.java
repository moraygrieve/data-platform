package com.riekon.aws.util.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class RunTimeDetails {
    public static final Logger logger = LoggerFactory.getLogger(RunTimeDetails.class);

    public static void logDetails() {
        logger.info("System runtime details:");
        logger.info("  Available processors (cores): {} " , Runtime.getRuntime().availableProcessors());
        logger.info("  Free memory (bytes): {}", Runtime.getRuntime().freeMemory());

        long maxMemory = Runtime.getRuntime().maxMemory();
        logger.info("  Maximum memory (bytes): {}", (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));
        logger.info("  Total memory available to JVM (bytes): {}", Runtime.getRuntime().totalMemory());

        File[] roots = File.listRoots();
        for (File root : roots) {
            logger.info("  File health root: {}", root.getAbsolutePath());
            logger.info("    Total space (bytes): {}", root.getTotalSpace());
            logger.info("    Free space (bytes): {}", root.getFreeSpace());
            logger.info("    Usable space (bytes): {}", root.getUsableSpace());
        }
    }
}
