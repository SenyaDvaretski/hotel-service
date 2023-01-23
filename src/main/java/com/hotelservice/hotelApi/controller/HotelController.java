package com.hotelservice.hotelApi.controller;

import com.hotelservice.hotelApi.DTO.ExcursionDTO;
import com.hotelservice.hotelApi.DTO.HotelDTO;
import com.hotelservice.hotelApi.exception.CommonException;
import com.hotelservice.hotelApi.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level= AccessLevel.PRIVATE)
public class HotelController extends BaseController{
    HotelService hotelService;

    @Operation(summary = "Get all hotels")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found more than zero hotels",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExcursionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "No hotels found",
                    content = @Content) })
    @GetMapping
    public ResponseEntity<List<HotelDTO>> getHotels() throws CommonException {
        return hotelService.getAllHotels();
    }

    @Operation(summary = "Get hotel by hotel name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found hotel",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExcursionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Hotel not found",
                    content = @Content) })
    @GetMapping(path = "/{hotel_name}")
    public ResponseEntity<HotelDTO> getHotel(@PathVariable("hotel_name") String hotelName) throws CommonException {
        return hotelService.getHotel(hotelName);
    }

    @Operation(summary = "Get hotels by hotel location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found more than zero hotels",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExcursionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "No hotels found",
                    content = @Content) })
    @GetMapping(params = "address")
    public ResponseEntity<List<HotelDTO>> getHotelsByLocation(@RequestParam("address") String location) throws CommonException {
        return hotelService.getAllHotelsByAddress(location);
    }

    @Operation(summary = "Add hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added hotel",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExcursionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Cannot add hotel",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<HotelDTO> addHotel(@RequestBody HotelDTO hotelDTO){
        return hotelService.addHotel(hotelDTO);
    }

    @Operation(summary = "Delete hotel by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted hotel",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExcursionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Cannot delete hotel, hotel not found",
                    content = @Content) })
    @DeleteMapping(path = "/{hotel_name}")
    public ResponseEntity<HotelDTO> deleteHotel(@PathVariable("hotel_name") String hotelName) throws CommonException {
        return hotelService.delete(hotelName);
    }

    @Operation(summary = "Update hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated hotel",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExcursionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Cannot update hotel, hotel not found",
                    content = @Content) })
    @PatchMapping
    public ResponseEntity<HotelDTO> updateHotel(@RequestBody HotelDTO hotelDTO) throws CommonException {
            return hotelService.update(hotelDTO);
    }
}