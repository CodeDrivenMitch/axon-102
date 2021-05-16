package com.insidion.axon102.domain.commands

data class CreateProfileCommand(
    val username: String,
    val realName: String,
)
