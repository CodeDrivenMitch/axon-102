package com.insidion.axon102.personaldata2

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.jsontype.TypeSerializer
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.insidion.axon102.domain.PersonalData
import org.springframework.stereotype.Component

@Component
class PersonalDataJacksonSerializer(private val store: PersonalDataStore) : StdSerializer<PersonalData>(PersonalData::class.java) {
    override fun serializeWithType(value: PersonalData?, gen: JsonGenerator, serializers: SerializerProvider, typeSer: TypeSerializer) {
        this.serialize(value, gen, serializers)
    }

    override fun serialize(personalData: PersonalData?, gen: JsonGenerator, provider: SerializerProvider) {
        if (personalData == null || personalData.value.isBlank()) {
            gen.writeNull()
            return
        }

        personalData.storedId = store.retrieveDataId(personalData.value)
        if (personalData.storedId == null) {
            gen.writeNull()
        } else {
            gen.writeObject(SerializedPersonalData(personalData.storedId!!))
        }
    }
}

data class SerializedPersonalData(
    val id: Long,
)
