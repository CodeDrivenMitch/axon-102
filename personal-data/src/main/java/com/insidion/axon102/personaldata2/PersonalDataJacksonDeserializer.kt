package com.insidion.axon102.personaldata2

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer
import com.insidion.axon102.domain.PersonalData
import org.springframework.stereotype.Component

@Component
class PersonalDataJacksonDeserializer(private val store: PersonalDataStore) : StdDeserializer<PersonalData>(PersonalData::class.java) {
    override fun deserializeWithType(p: JsonParser, ctxt: DeserializationContext, typeDeserializer: TypeDeserializer, intoValue: PersonalData) = this.deserialize(p, ctxt)

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext) = try {
        p.readValueAs(SerializedPersonalData::class.java)?.let {
            // Retrieve the actual value with the id provided in the serialized (json) value id
            store.retrieveDataValue(it.id)
        }
    } catch (e: Exception) {
        // Something happened. The value does not exist or we have an error. Return masked value
        PersonalData("***", -1)
    }
}
