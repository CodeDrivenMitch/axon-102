package com.insidion.axon102.domain.events

data class ProfileCreatedEvent(
    val username: String,
    val realName: String,
)
