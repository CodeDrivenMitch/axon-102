package com.insidion.axon102.adapter.rest

import com.insidion.axon102.domain.commands.CreateProfileCommand
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/account")
class ProfileEndpoint(
    private val commandGateway: CommandGateway
) {
    @PostMapping
    fun createAccount(@RequestBody body: CreateAccountRequest) {
        commandGateway.sendAndWait<Any>(CreateProfileCommand(body.username, body.realName))
    }

    data class CreateAccountRequest(
        val username: String,
        val realName: String,
    )
}
