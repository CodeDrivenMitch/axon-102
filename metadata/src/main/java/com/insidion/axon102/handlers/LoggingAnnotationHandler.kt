package com.insidion.axon102.handlers

import com.fasterxml.jackson.databind.ObjectMapper
import com.insidion.axon102.domain.events.ProfileCreatedEvent
import com.insidion.axon102.metadata.Username
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
@ProcessingGroup("logging-metadata-annotation")
class LoggingAnnotationHandler(
    private val objectMapper: ObjectMapper
) {
    private val logger = LoggerFactory.getLogger(LoggingAnnotationHandler::class.java)

    @EventHandler
    fun handle(event: ProfileCreatedEvent, @Username username: String) {
        logger.info("Got event by {}: {}", username, objectMapper.writeValueAsString(event))
    }
}
