package com.springtestdoc.schedule.service

import com.springtestdoc.schedule.repository.Schedule
import com.springtestdoc.schedule.repository.ScheduleRepository
import org.springframework.stereotype.Service

@Service
class ScheduleService(private val scheduleRepository: ScheduleRepository) {

    fun getSchedules(): MutableList<Schedule> =
        scheduleRepository.findAll()

}