package com.hotelservice.hotelApi.controller;

import com.hotelservice.hotelApi.DTO.ExcursionDTO;
import com.hotelservice.hotelApi.service.ExcursionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "hotels/{hotel_name}/excursion")
public class ExcursionController {
    ExcursionService excursionService;
    @GetMapping
    public List<ExcursionDTO> getExcursions(@PathVariable(name = "hotel_name") String hotelName){
            return excursionService.getAllExcursions(hotelName);
    }
    @PostMapping
    public HttpStatus addExcursion(@PathVariable(name = "hotel_name") String hotelName,
                                   @RequestBody ExcursionDTO excursionDto){
        return excursionService.addExcursion(hotelName,excursionDto);
    }

    @PutMapping
    public HttpStatus updateExcursion(@PathVariable(name = "hotel_name") String hotelName,
                                      @RequestBody ExcursionDTO excursionDTO){
        try{
            return excursionService.update(hotelName, excursionDTO);
        }catch (Exception e){
            return HttpStatus.CONFLICT;
        }
    }

    @PutMapping(path = "/{excursion_name}")
    public HttpStatus updateExcursion(@PathVariable(name = "hotel_name") String hotelName,
                                      @PathVariable(name = "excursion_name") String excursionName)
    {
        try{
            return excursionService.setEnabled(hotelName, excursionName);
        }catch (Exception e){
            return HttpStatus.NOT_FOUND;
        }
    }
}
