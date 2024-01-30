package com.app.reservasR.application.Controller;

import com.app.reservasR.application.Exception.EntityNotFoundException;
import com.app.reservasR.application.service.DiningTableService;
import com.app.reservasR.domain.dto.DiningTableDto;
import com.app.reservasR.domain.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diningTable")
public record DiningTableController(DiningTableService diningTableService) {

    @PostMapping
    public ResponseEntity<?> registerDiningTable(@RequestBody DiningTableDto diningTableDto){
        diningTableService.createDiningTable(diningTableDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findDiningTableById(@PathVariable Integer id) throws EntityNotFoundException {
        DiningTableDto diningTableDto = diningTableService.findDiningTableById(id);
        return new ResponseEntity<>(diningTableDto, HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<?> allUsers() throws EntityNotFoundException{
        List<DiningTableDto> diningTables = diningTableService.allDiningTable();
        return new ResponseEntity<>(diningTables, HttpStatus.OK);
    }
}
