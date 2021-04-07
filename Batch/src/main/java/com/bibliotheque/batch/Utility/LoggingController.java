package com.bibliotheque.batch.Utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoggingController {

    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    public String index() {

        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");

        return "Howdy! Check out the Logs to see the output...";

    }
}
