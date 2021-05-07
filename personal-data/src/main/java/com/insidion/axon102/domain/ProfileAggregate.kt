package com.insidion.axon102.domain

import com.insidion.axon102.domain.commands.CreateProfileCommand
import com.insidion.axon102.domain.events.ProfileCreatedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate


@Aggregate
class ProfileAggregate {
    @AggregateIdentifier
    private lateinit var username: String
    private lateinit var realName: PersonalData

    @CommandHandler
    constructor(command: CreateProfileCommand) {
        AggregateLifecycle.apply(ProfileCreatedEvent(command.username, PersonalData(command.realName)))
    }

    @EventSourcingHandler
    fun onEvent(event: ProfileCreatedEvent) {
        this.username = event.username
        this.realName = event.realName
    }

    constructor() {
        // Here for axon
    }
}
