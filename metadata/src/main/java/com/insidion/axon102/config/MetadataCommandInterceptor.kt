package com.insidion.axon102.config

import org.axonframework.messaging.InterceptorChain
import org.axonframework.messaging.Message
import org.axonframework.messaging.MessageDispatchInterceptor
import org.axonframework.messaging.MessageHandlerInterceptor
import org.axonframework.messaging.unitofwork.UnitOfWork
import org.springframework.stereotype.Component
import java.util.function.BiFunction

@Component
class MetadataCommandInterceptor<T : Message<*>>: MessageDispatchInterceptor<T>, MessageHandlerInterceptor<T> {
    override fun handle(messages: MutableList<out T>?): BiFunction<Int, T, T> {
        return BiFunction { index, message ->
            require(message.metaData.containsKey("company")) { "Every command should have company defined in its metadata!" }

            message
        }
    }

    override fun handle(unitOfWork: UnitOfWork<out T>, interceptorChain: InterceptorChain): Any? {
        handle(unitOfWork.message)
        return interceptorChain.proceed()
    }
}
