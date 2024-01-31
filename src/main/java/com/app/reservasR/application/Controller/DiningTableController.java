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
    public ResponseEntity<?> allDiningTable(){
        List<DiningTableDto> diningTables = diningTableService.allDiningTable();
        return new ResponseEntity<>(diningTables, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDiningTable(@PathVariable Integer id, @RequestBody DiningTableDto diningTableDto) throws EntityNotFoundException{
        DiningTableDto updateDiningTable = diningTableService.updateDiningTable(id, diningTableDto);
        return new ResponseEntity<>(updateDiningTable, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDiningTable(@PathVariable Integer id) throws EntityNotFoundException{
        diningTableService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
