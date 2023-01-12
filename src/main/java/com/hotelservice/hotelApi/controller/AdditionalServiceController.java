package com.hotelservice.hotelApi.controller;

import com.hotelservice.hotelApi.DTO.AdditionalServiceDTO;
import com.hotelservice.hotelApi.service.AdditionalServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "hotels/{hotel_name}/additional-services")
//todo extend base controller
//todo https://www.baeldung.com/spring-rest-openapi-documentation
public class AdditionalServiceController {
    AdditionalServiceService additionalService;
    @GetMapping
    public List<AdditionalServiceDTO> getAdditionalService(@PathVariable(name = "hotel_name") String hotelName) throws Exception {
        return additionalService.getAllAdditionalServices(hotelName);
    }
    @PostMapping
    //todo response entity with dto and status
    public HttpStatus createAdditionalService(@PathVariable(name = "hotel_name") String hotelName,
                                   @RequestBody AdditionalServiceDTO additionalServiceDTO){
        return additionalService.addAdditionalService(hotelName,additionalServiceDTO);
    }
    @PutMapping
    public HttpStatus updateExcursion(@PathVariable(name = "hotel_name") String hotelName,
                                      @RequestBody AdditionalServiceDTO additionalServiceDTO){
        //todo CA
        try{
            return additionalService.update(hotelName, additionalServiceDTO);
        }catch (Exception e){
            return HttpStatus.CONFLICT;
        }
    }
    @PatchMapping(path = "/{additional_serviceName_name}")
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
