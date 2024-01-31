package com.app.reservasR.application.Controller;

import com.app.reservasR.application.Exception.EntityNotFoundException;
import com.app.reservasR.application.service.ScheduleService;
import com.app.reservasR.domain.dto.DiningTableDto;
import com.app.reservasR.domain.dto.ScheduleDto;
import com.app.reservasR.domain.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public record ScheduleController(ScheduleService scheduleService) {
    @PostMapping
    public ResponseEntity<?> registerSchedule(@RequestBody ScheduleDto scheduleDto){
        scheduleService.createSchedule(scheduleDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findScheduleById(@PathVariable Integer id) throws EntityNotFoundException {
        ScheduleDto scheduleDto = scheduleService.findScheduleById(id);
        return new ResponseEntity<>(scheduleDto, HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<?> allSchedule(){
        List<ScheduleDto> scheduleDtoList = scheduleService.allSchedules();
        return new ResponseEntity<>(scheduleDtoList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSchedule(@PathVariable Integer id, @RequestBody ScheduleDto scheduleDto) throws EntityNotFoundException{
        ScheduleDto updateSchedule = scheduleService.updateSchedule(id, scheduleDto);
        return new ResponseEntity<>(updateSchedule, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Integer id) throws EntityNotFoundException{
        scheduleService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
