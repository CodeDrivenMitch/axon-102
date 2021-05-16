package com.insidion.axon102.metadata

import org.axonframework.messaging.Message
import org.axonframework.messaging.annotation.ParameterResolver

class UsernameMetadataResolver : ParameterResolver<String> {
    override fun resolveParameterValue(message: Message<*>): String? {
        val possibleInstant = message.metaData["username"]
        if (possibleInstant == null || possibleInstant !is String) {
            throw IllegalStateException("Username should be present in all events! Something went wrong")
        }
        return possibleInstant
    }

    override fun matches(message: Message<*>) = true
}
