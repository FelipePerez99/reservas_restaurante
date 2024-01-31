package com.app.reservasR.application.service;

import com.app.reservasR.application.Exception.EntityNotFoundException;
import com.app.reservasR.application.lasting.EMessage;
import com.app.reservasR.domain.dto.DiningTableDto;
import com.app.reservasR.domain.dto.UserDto;
import com.app.reservasR.domain.entity.DiningTable;
import com.app.reservasR.domain.entity.User;
import com.app.reservasR.domain.repository.DiningTableRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public record DiningTableService(DiningTableRepository diningTableRepository) {

    public void createDiningTable(DiningTableDto diningTableDto){
        DiningTable diningTable = DiningTable.builder()
                .capacity(diningTableDto.capacity())
                .build();

        diningTableRepository.save(diningTable);
    }

    public DiningTableDto findDiningTableById(Integer id) throws EntityNotFoundException {
        DiningTable diningTable = diningTableRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(EMessage.DATA_NOT_FOUND));

        return new DiningTableDto(
                diningTable.getId(),
                diningTable.getCapacity()
        );
    }

    public List<DiningTableDto> allDiningTable(){
        List<DiningTable> diningTables = diningTableRepository.findAll();
        return diningTables.stream().map(diningTable -> new DiningTableDto(diningTable.getId(),
                diningTable.getCapacity())).collect(Collectors.toList());
    }

    public DiningTableDto updateDiningTable(Integer id, DiningTableDto diningTableDto) throws EntityNotFoundException{
        DiningTable diningTable = diningTableRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(EMessage.DATA_NOT_FOUND));

        diningTable.setCapacity(diningTableDto.capacity());

        DiningTable updateDiningTable = diningTableRepository.save(diningTable);
        return new DiningTableDto(updateDiningTable.getId(), updateDiningTable.getCapacity());
    }

    public void deleteById(Integer id)throws EntityNotFoundException {
        Optional<DiningTable> diningTable = diningTableRepository.findById(id);
        if (diningTable.isPresent()){
            diningTableRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException(EMessage.DATA_NOT_FOUND);
        }
    }
}
