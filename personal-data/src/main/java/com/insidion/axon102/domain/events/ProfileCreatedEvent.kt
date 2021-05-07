package com.insidion.axon102.domain.events

import com.insidion.axon102.domain.PersonalData

data class ProfileCreatedEvent(
    val username: String,
    val realName: PersonalData,
)
