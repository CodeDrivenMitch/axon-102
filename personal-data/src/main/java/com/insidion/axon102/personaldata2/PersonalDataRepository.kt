package com.insidion.axon102.personaldata2

import org.springframework.data.repository.CrudRepository

interface PersonalDataRepository : CrudRepository<PersonalDataEntity, Long> {
    fun findFirstByValue(value: String): PersonalDataEntity?
}
