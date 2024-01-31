package com.app.reservasR.application.service;

import com.app.reservasR.application.Exception.EntityNotFoundException;
import com.app.reservasR.application.lasting.EMessage;
import com.app.reservasR.domain.dto.ScheduleDto;
import com.app.reservasR.domain.entity.Schedule;
import com.app.reservasR.domain.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public record ScheduleService(ScheduleRepository scheduleRepository) {
    public void createSchedule(ScheduleDto scheduleDto){
        Schedule schedule = Schedule.builder().startTime(scheduleDto.startTime())
                .endTime(scheduleDto.endTime())
                .build();

        scheduleRepository.save(schedule);
    }

    public ScheduleDto findScheduleById(Integer id) throws EntityNotFoundException {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(EMessage.DATA_NOT_FOUND));
        return new ScheduleDto(
                schedule.getId(),
                schedule.getStartTime(),
                schedule.getEndTime()
        );
    }

    public ScheduleDto updateSchedule(Integer id, ScheduleDto scheduleDto) throws EntityNotFoundException{
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(EMessage.DATA_NOT_FOUND));

        schedule.setStartTime(scheduleDto.startTime());
        schedule.setEndTime(scheduleDto.endTime());

        Schedule updateSchedule =  scheduleRepository.save(schedule);

        return new ScheduleDto(
                updateSchedule.getId(),
                updateSchedule.getStartTime(),
                updateSchedule.getEndTime()
        );
    }

    public List<ScheduleDto> allSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream()
                .map(schedule -> new ScheduleDto(
                        schedule.getId(),
                        schedule.getStartTime(),
                        schedule.getEndTime()
                ))
                .collect(Collectors.toList());
    }

    public void deleteById(Integer id)throws EntityNotFoundException {
        Optional<Schedule> user = scheduleRepository.findById(id);
        if (user.isPresent()){
            scheduleRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException(EMessage.DATA_NOT_FOUND);
        }
    }
}
