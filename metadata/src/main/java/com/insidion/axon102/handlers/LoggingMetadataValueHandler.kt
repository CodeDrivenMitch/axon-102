package com.insidion.axon102.handlers

import com.fasterxml.jackson.databind.ObjectMapper
import com.insidion.axon102.domain.events.ProfileCreatedEvent
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.axonframework.messaging.annotation.MetaDataValue
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
@ProcessingGroup("logging-metadata-value")
class LoggingMetadataValueHandler(
    private val objectMapper: ObjectMapper
) {
    private val logger = LoggerFactory.getLogger(LoggingMetadataValueHandler::class.java)


    @EventHandler
    fun handle(event: ProfileCreatedEvent, @MetaDataValue("username") username: String) {
        logger.info("Got event by {}: {}", username, objectMapper.writeValueAsString(event))
    }
}
