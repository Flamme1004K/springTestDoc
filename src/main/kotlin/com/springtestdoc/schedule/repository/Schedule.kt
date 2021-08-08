package com.springtestdoc.schedule.repository
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
class Schedule(
    @Id @Column(name = "id") val id: String,
    @Column(name = "interval") val interval: Int?,
)