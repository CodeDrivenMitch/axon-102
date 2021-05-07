package com.insidion.axon102.personaldata2

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.Version
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.module.SimpleModule
import com.insidion.axon102.domain.PersonalData
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ObjectMapperConfiguration {
    @Bean
    fun objectMapper(personalDataJacksonSerializer: PersonalDataJacksonSerializer, personalDataJacksonDeserializer: PersonalDataJacksonDeserializer): ObjectMapper {
        val module = SimpleModule("GDPR module", Version.unknownVersion())
        module.addSerializer(PersonalData::class.java, personalDataJacksonSerializer)
        module.addDeserializer(PersonalData::class.java, personalDataJacksonDeserializer)

        return ObjectMapper()
            .findAndRegisterModules()
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .registerModule(module)
    }
}
