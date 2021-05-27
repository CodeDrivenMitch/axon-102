package com.insidion.axon102

import com.insidion.axon102.domain.commands.ApplicationCommandMessage
import com.insidion.axon102.domain.commands.CreateProfileCommand
import org.axonframework.commandhandling.GenericCommandMessage
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.MetaData
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service
import kotlin.system.exitProcess

@Service
class MetadataDemoRunner(
    private val commandGateway: CommandGateway,
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        var exception: Exception? = null

        // Dispatch a message with missing name and company
        // This should throw an error caused by the ParameterResolver
        // Which is a bit late, since the event is already applied.
        try {
            val metaData = MetaData.from(mapOf("company" to "codecentric Netherlands B.V."))
            val command = CreateProfileCommand("morlack", "Mitchell Herrijgers")
            commandGateway.sendAndWait<Any>(GenericCommandMessage(command, metaData))
        } catch (e: Exception) {
            exception = e
        }

        require(exception?.message == "Error handling event of type [class com.insidion.axon102.domain.events.ProfileCreatedEvent] in aggregate")


        // We can also validate it on the command-dispatch level. We want all commands to have a "company" metadata, with the company the user works for.
        // When it is missing, the MetadataCommandInterceptor detects this and throws an error. Now no events have been thrown
        try {
            val metaData = MetaData.from(mapOf("username" to "Morlack"))
            commandGateway.sendAndWait<Any>(GenericCommandMessage(CreateProfileCommand("morlack", "Mitchell Herrijgers"), metaData))
        } catch (e: Exception) {
            exception = e
        }

        require(exception?.message == "Every command should have company defined in its metadata!")


        // Better, write your own command wrapper with all needed metadata
        commandGateway.sendAndWait<Any>(ApplicationCommandMessage(CreateProfileCommand("morlack", "Mitchell Herrijgers"), "morlack", "codecentric Nederland B.V."))

        exitProcess(0)
    }
}
