package com.insidion.axon102.personaldata2

import com.insidion.axon102.domain.PersonalData
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class PersonalDataStore(
    private val personalDataRepository: PersonalDataRepository
) {
    fun retrieveDataValue(id: Long?): PersonalData? {
        return retrieveDataEntityById(id)?.let { PersonalData(it.value, it.id) }
    }

    fun retrieveDataId(value: String?): Long? {
        if (value == null) {
            return null
        }
        return personalDataRepository.findFirstByValue(value)?.id
            ?: createNewDatabaseValue(value)
    }

    private fun createNewDatabaseValue(value: String): Long? {
        val newEntity = PersonalDataEntity(
            storedDate = Instant.now(),
            value = value
        )
        return personalDataRepository.save(newEntity).id
    }

    fun retrieveDataEntityById(id: Long?): PersonalDataEntity? {
        if (id == null) {
            return null
        }
        return personalDataRepository.findById(id).orElse(null)
    }
}
