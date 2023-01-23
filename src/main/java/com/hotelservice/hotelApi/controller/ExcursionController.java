package com.hotelservice.hotelApi.controller;

import com.hotelservice.hotelApi.DTO.ExcursionDTO;
import com.hotelservice.hotelApi.exception.CommonException;
import com.hotelservice.hotelApi.service.ExcursionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/{hotel_name}/excursion")
public class ExcursionController extends BaseController{
    ExcursionService excursionService;
    @GetMapping
    public ResponseEntity<List<ExcursionDTO>> getAllExcursions(@PathVariable(name = "hotel_name") String hotelName) throws CommonException {
            return new ResponseEntity<>(excursionService.getAllExcursions(hotelName), HttpStatus.OK);
    }

    @GetMapping("/{excursion_name}")
    public ResponseEntity<ExcursionDTO> getExcursion(@PathVariable(name = "hotel_name") String hotelName,
                                                      @PathVariable(name = "excursion_name") String excursionName) throws CommonException {
        return new ResponseEntity<>(excursionService.getExcursion(hotelName, excursionName), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ExcursionDTO> addExcursion(@PathVariable(name = "hotel_name") String hotelName,
                                   @RequestBody ExcursionDTO excursionDto) throws CommonException {
        return new ResponseEntity<>(excursionService.addExcursion(hotelName,excursionDto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ExcursionDTO> updateExcursion(@PathVariable(name = "hotel_name") String hotelName,
                                                                @RequestBody ExcursionDTO excursionDTO) throws CommonException {
        return new ResponseEntity<>(excursionService.updateExcursion(hotelName, excursionDTO), HttpStatus.OK);
    }

    @PatchMapping(path = "/{excursion_name}")
    public ResponseEntity<ExcursionDTO> setEnabledExcursion(@PathVariable(name = "hotel_name") String hotelName,
                                      @PathVariable(name = "excursion_name") String excursionName,
                                      @RequestBody Boolean isEnabled) throws CommonException {
        return new ResponseEntity<>(excursionService.setEnabled(hotelName, excursionName, isEnabled), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{excursion_name}")
    public ResponseEntity<ExcursionDTO> deleteExcursion(@PathVariable("hotel_name") String hotelName,
                                              @PathVariable("excursion_name") String excursionName) throws CommonException {
        return new ResponseEntity<>(excursionService.deleteExcursion(hotelName, excursionName), HttpStatus.OK);
    }
}
