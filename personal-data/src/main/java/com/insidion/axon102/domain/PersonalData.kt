package com.insidion.axon102.domain

data class PersonalData(
    val value: String,
    var storedId: Long? = null, // Only filled after being stored in the database
)
