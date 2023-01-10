package com.hotelservice.hotelApi.controllers;

import com.hotelservice.hotelApi.DTO.AdditionalServiceDTO;
import com.hotelservice.hotelApi.services.AdditionalServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "hotels/{hotel_name}/additional-services")
public class AdditionalServiceController {
    AdditionalServiceService additionalService;
    @GetMapping
    public List<AdditionalServiceDTO> getAdditionalService(@PathVariable(name = "hotel_name") String hotelName) throws Exception {
        return additionalService.getAllAdditionalServices(hotelName);
    }
    @PostMapping
    public HttpStatus addAdditionalService(@PathVariable(name = "hotel_name") String hotelName,
                                   @RequestBody AdditionalServiceDTO additionalServiceDTO){
        return additionalService.addAdditionalService(hotelName,additionalServiceDTO);
    }
    @PutMapping
    public HttpStatus updateExcursion(@PathVariable(name = "hotel_name") String hotelName,
                                      @RequestBody AdditionalServiceDTO additionalServiceDTO){
        try{
            return additionalService.update(hotelName, additionalServiceDTO);
        }catch (Exception e){
            return HttpStatus.CONFLICT;
        }
    }
    @PutMapping(path = "/{additional_serviceName_name}")
    public HttpStatus updateExcursion(@PathVariable(name = "hotel_name") String hotelName,
                                      @PathVariable(name = "additional_serviceName_name")
                                            String additionalServiceName)
    {
        try{
            return additionalService.setEnabled(hotelName, additionalServiceName);
        }catch (Exception e){
            return HttpStatus.NOT_FOUND;
        }
    }

}
