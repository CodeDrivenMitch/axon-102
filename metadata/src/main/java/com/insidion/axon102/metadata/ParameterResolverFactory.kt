package com.insidion.axon102.metadata

import org.axonframework.messaging.annotation.ParameterResolverFactory
import java.lang.reflect.Executable
import java.lang.reflect.Parameter

@Suppress("unused") // Used by axon via META-INF file
class HamisParameterResolverFactory : ParameterResolverFactory {
    override fun createInstance(executable: Executable?, parameters: Array<out Parameter>, parameterIndex: Int) = when {
        parameters[parameterIndex].isAnnotationPresent(Username::class.java) -> UsernameMetadataResolver()
        else -> null
    }
}
