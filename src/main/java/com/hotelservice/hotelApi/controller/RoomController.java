package com.hotelservice.hotelApi.controller;

import com.hotelservice.hotelApi.DTO.BaseDTO;
import com.hotelservice.hotelApi.DTO.RoomDTO;
import com.hotelservice.hotelApi.exception.CommonException;
import com.hotelservice.hotelApi.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/{hotel_name}/rooms")
@AllArgsConstructor
public class RoomController extends BaseController{
    private final RoomService roomService;

    @Operation(summary = "Get all rooms by hotel name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found more than zero rooms",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoomDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Room not found",
                    content = @Content) })
    @GetMapping
    public ResponseEntity<Iterable<RoomDTO>> getAllRooms(@PathVariable("hotel_name") String hotelName) throws CommonException {
        return new ResponseEntity<>(roomService.getAllRooms(hotelName), HttpStatus.OK);
    }

    @Operation(summary = "Get all available rooms by hotel name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found all available/occupied rooms",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoomDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Rooms not found",
                    content = @Content) })
    @GetMapping(params = "available")
    public ResponseEntity<Iterable<RoomDTO>> getAllAvailableRooms(@PathVariable("hotel_name") String hotelName,
                                                                    @RequestParam("available") Boolean isAvailable) throws CommonException {
        return new ResponseEntity<>(roomService.getAllAvailableRooms(hotelName, isAvailable), HttpStatus.OK);
    }

    @Operation(summary = "Get room by hotel name and room number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found room",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoomDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Room is not found",
                    content = @Content) })
    @GetMapping("/{room_number}")
    public ResponseEntity<BaseDTO> getRoom(@PathVariable("hotel_name") String hotelName,
                                            @PathVariable("room_number") Integer roomNumber) throws CommonException {
        return new ResponseEntity<>(roomService.getRoom(hotelName, roomNumber), HttpStatus.OK);
    }

    @Operation(summary = "Add room by hotel name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added room",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoomDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Cannot add room, hotel is not found",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<BaseDTO> addRoom(@PathVariable("hotel_name") String hotelName,
                                           @RequestBody RoomDTO roomDTO) throws CommonException {
        return new ResponseEntity<>(roomService.addRoom(hotelName, roomDTO), HttpStatus.OK);
    }

    @Operation(summary = "Delete room by hotel name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted room",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoomDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Cannot add room, room is not found",
                    content = @Content) })
    @DeleteMapping(path = "/{room_number}")
    public ResponseEntity<BaseDTO> deleteRoom(@PathVariable("hotel_name") String hotelName,
                                 @PathVariable("room_number") Integer roomNumber) throws CommonException {
        return new ResponseEntity<>(roomService.deleteRoom(hotelName, roomNumber), HttpStatus.OK);
    }

    @Operation(summary = "Update room by hotel name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated room",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoomDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Cannot update room, room is not found",
                    content = @Content) })
    @PutMapping
    public ResponseEntity<BaseDTO> updateRoom(@PathVariable("hotel_name") String hotelName,
                                 @RequestBody RoomDTO RoomDTO) throws CommonException {
        return new ResponseEntity<>(roomService.updateRoom(hotelName, RoomDTO), HttpStatus.OK);
    }

    @Operation(summary = "Set room available/unavailable")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully set room available/unavailable",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoomDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Cannot set room available/unavailable, room is not found",
                    content = @Content) })
    @PatchMapping(path = "/{room_number}")
    public ResponseEntity<BaseDTO> setAvailableRoom(@PathVariable("hotel_name") String hotelName,
                                                    @PathVariable("room_number") Integer roomNumber,
                                                    @RequestBody Boolean availability) throws CommonException {
        return new ResponseEntity<>(roomService.setRoomAvailable(hotelName, roomNumber, availability), HttpStatus.OK);
    }
}
