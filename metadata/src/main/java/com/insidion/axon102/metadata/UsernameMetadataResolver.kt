package com.insidion.axon102.metadata

import org.axonframework.messaging.Message
import org.axonframework.messaging.annotation.ParameterResolver

class UsernameMetadataResolver : ParameterResolver<String> {
    override fun resolveParameterValue(message: Message<*>): String? {
        return message.metaData["username"] as String?
    }

    override fun matches(message: Message<*>) = true
}
