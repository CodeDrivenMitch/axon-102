package com.insidion.axon102.domain

import com.insidion.axon102.domain.commands.CreateProfileCommand
import com.insidion.axon102.domain.events.ProfileCreatedEvent
import com.insidion.axon102.metadata.Username
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.messaging.MetaData
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate


@Aggregate
class ProfileAggregate {
    @AggregateIdentifier
    private lateinit var username: String
    private lateinit var realName: String

    @CommandHandler
    constructor(command: CreateProfileCommand, metadata: MetaData) {
        AggregateLifecycle.apply(ProfileCreatedEvent(command.username, command.realName), metadata)
    }

    @EventSourcingHandler
    fun onEvent(event: ProfileCreatedEvent, @Username username: String) {
        this.username = event.username
        this.realName = event.realName
        // We can so something with the username metadata here
    }

    constructor() {
        // Here for axon
    }
}
