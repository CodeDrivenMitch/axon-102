package com.insidion.axon102.personaldata2

import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "PERSONAL_DATA")
data class PersonalDataEntity(
    @Column(name = "DT_STORED")
    val storedDate: Instant,
    @Column(name = "DATA_VALUE")
    val value: String,
) {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
