package com.springtestdoc.schedule.service

import com.springtestdoc.schedule.repository.Schedule
import com.springtestdoc.schedule.repository.ScheduleRepository
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension


@ExtendWith(MockitoExtension::class)
class ScheduleServiceTest {


    @Mock
    private lateinit var scheduleRepository : ScheduleRepository

    @InjectMocks
    private lateinit var scheduleService: ScheduleService

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun serviceTest() {
        //given
        val scheduleList = mutableListOf<Schedule>(
            Schedule("test1", 12),
            Schedule("test2", 13),
            Schedule("test3", 14),
            Schedule("test4", 15)
        )
        given(scheduleRepository.findAll()).willReturn(scheduleList)

        //when
        val schedules = scheduleService.getSchedules()

        //then
        assertEquals(schedules.size, scheduleList.size)
    }
}