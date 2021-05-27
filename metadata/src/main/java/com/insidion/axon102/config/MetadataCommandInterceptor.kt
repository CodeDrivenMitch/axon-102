package com.insidion.axon102.config

import org.axonframework.commandhandling.CommandBus
import org.axonframework.commandhandling.CommandMessage
import org.axonframework.messaging.Message
import org.axonframework.messaging.MessageDispatchInterceptor
import org.springframework.stereotype.Component
import java.util.function.BiFunction
import javax.annotation.PostConstruct

@Component
class MetadataCommandInterceptor<T : Message<*>>(
    private val commandBus: CommandBus
) : MessageDispatchInterceptor<T> {
    @PostConstruct
    fun register() {
        commandBus.registerDispatchInterceptor(this as MessageDispatchInterceptor<in CommandMessage<*>>)
    }

    override fun handle(messages: MutableList<out T>?): BiFunction<Int, T, T> {
        return BiFunction { index, message ->
            require(message.metaData.containsKey("company")) { "Every command should have company defined in its metadata!" }

            message
        }
    }
}
