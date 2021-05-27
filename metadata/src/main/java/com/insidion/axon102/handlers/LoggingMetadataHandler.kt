package com.insidion.axon102.handlers

import com.fasterxml.jackson.databind.ObjectMapper
import com.insidion.axon102.domain.events.ProfileCreatedEvent
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.axonframework.messaging.MetaData
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
@ProcessingGroup("logging-metadata")
class LoggingMetadataHandler(
    private val objectMapper: ObjectMapper
) {
    private val logger = LoggerFactory.getLogger(LoggingMetadataHandler::class.java)


    @EventHandler
    fun handle(event: ProfileCreatedEvent, metadata: MetaData) {
        logger.info("Got event by {}: {}", metadata["username"], objectMapper.writeValueAsString(event))
    }
}
