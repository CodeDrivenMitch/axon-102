package com.insidion.axon102.domain.commands

import org.axonframework.commandhandling.GenericCommandMessage

class ApplicationCommandMessage<T>(
    command: T,
    username: String,
    company: String
) : GenericCommandMessage<T>(
    command, mutableMapOf(
        "username" to username,
        "company" to company,
    )
)
