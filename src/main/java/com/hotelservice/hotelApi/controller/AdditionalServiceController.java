package com.hotelservice.hotelApi.controller;

import com.hotelservice.hotelApi.DTO.AdditionalServiceDTO;
import com.hotelservice.hotelApi.exception.CommonException;
import com.hotelservice.hotelApi.repository.HotelRepository;
import com.hotelservice.hotelApi.service.AdditionalServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/{hotel_name}/additional-services")
//todo https://www.baeldung.com/spring-rest-openapi-documentation
public class AdditionalServiceController extends BaseController{
    AdditionalServiceService additionalService;
    private final HotelRepository hotelRepository;

    public AdditionalServiceController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }
  /* @Operation(summary = "Get an additional service by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content) })*/
    @GetMapping
    public ResponseEntity<List<AdditionalServiceDTO>> getAdditionalService(@PathVariable(name = "hotel_name") String hotelName) throws Exception {
        return new ResponseEntity<>(additionalService.getAllAdditionalServices(hotelName), HttpStatus.OK);
    }

    @GetMapping("/{additional_serviceName_name}")
    public ResponseEntity<AdditionalServiceDTO> getAdditionalService(@PathVariable(name = "hotel_name") String hotelName,
                                                                     @PathVariable(name = "additional_serviceName_name") String additionalServiceName) throws Exception {
        return new ResponseEntity<>(additionalService.getAdditionalService(hotelName, additionalServiceName), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AdditionalServiceDTO> createAdditionalService(@PathVariable(name = "hotel_name") String hotelName,
                                   @RequestBody AdditionalServiceDTO additionalServiceDTO) throws CommonException {
        return new ResponseEntity<>(additionalService.addAdditionalService(hotelName, additionalServiceDTO), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<AdditionalServiceDTO> updateExcursion(@PathVariable(name = "hotel_name") String hotelName,
                                      @RequestBody AdditionalServiceDTO additionalServiceDTO) throws CommonException {
        return new ResponseEntity<>(additionalService.updateAdditionalService(hotelName, additionalServiceDTO), HttpStatus.OK);
    }
    @PatchMapping(path = "/{additional_serviceName_name}")
    public ResponseEntity<AdditionalServiceDTO> setExcursionEnabled(@PathVariable(name = "hotel_name") String hotelName,
                                      @PathVariable(name = "additional_serviceName_name") String additionalServiceName,
                                      @RequestBody Boolean isEnabled) throws CommonException {
        return new ResponseEntity<>(additionalService.setEnabled(hotelName, additionalServiceName, isEnabled), HttpStatus.OK);
    }
    @DeleteMapping(path = "/{additional_serviceName_name}")
    public ResponseEntity<AdditionalServiceDTO> deleteExcursion(@PathVariable(name = "hotel_name") String hotelName,
                                                   @PathVariable(name = "additional_serviceName_name") String additionalServiceName)
                                                    throws CommonException
    {
        return new ResponseEntity<>(additionalService.deleteAdditionalService(hotelName, additionalServiceName), HttpStatus.OK);
    }

}
