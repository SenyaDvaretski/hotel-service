package com.hotelservice.hotelApi.controller;

import com.hotelservice.hotelApi.DTO.ExcursionDTO;
import com.hotelservice.hotelApi.exception.CommonException;
import com.hotelservice.hotelApi.service.ExcursionService;
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

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/{hotel_name}/excursions")
public class ExcursionController extends BaseController{
    ExcursionService excursionService;

    @Operation(summary = "Get all excursions by hotel name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found more than zero excursions",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExcursionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Excursion not found",
                    content = @Content) })
    @GetMapping
    public ResponseEntity<List<ExcursionDTO>> getAllExcursions(@PathVariable(name = "hotel_name") String hotelName) throws CommonException {
            return new ResponseEntity<>(excursionService.getAllExcursions(hotelName), HttpStatus.OK);
    }

    @Operation(summary = "Get an excursion by its name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the excursion",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExcursionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Excursion not found",
                    content = @Content) })
    @GetMapping("/{excursion_name}")
    public ResponseEntity<ExcursionDTO> getExcursion(@PathVariable(name = "hotel_name") String hotelName,
                                                      @PathVariable(name = "excursion_name") String excursionName) throws CommonException {
        return new ResponseEntity<>(excursionService.getExcursion(hotelName, excursionName), HttpStatus.OK);
    }

    @Operation(summary = "Find all excursions with this tags in hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found excursions",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExcursionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "No excursions with this tags found",
                    content = @Content) })
    @GetMapping(path = "/tags", params = "tags")
    public ResponseEntity<List<ExcursionDTO>> getAllRoomsByTags(@PathVariable("hotel_name") String hotelName,
                                                           @RequestParam("tags") Set<String> tags) throws CommonException {
        return new ResponseEntity<>(excursionService.getAllExcursionsByHotelNameAndTags(hotelName, tags), HttpStatus.OK);
    }

    @Operation(summary = "Add an excursion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added the excursion",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExcursionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Cannot add excursion, hotel not found",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<ExcursionDTO> addExcursion(@PathVariable(name = "hotel_name") String hotelName,
                                   @RequestBody ExcursionDTO excursionDto) throws CommonException {
        return new ResponseEntity<>(excursionService.addExcursion(hotelName,excursionDto), HttpStatus.OK);
    }

    @Operation(summary = "Update an excursion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the excursion",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExcursionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Cannot update excursion, hotel not found",
                    content = @Content) })
    @PutMapping
    public ResponseEntity<ExcursionDTO> updateExcursion(@PathVariable(name = "hotel_name") String hotelName,
                                                                @RequestBody ExcursionDTO excursionDTO) throws CommonException {
        return new ResponseEntity<>(excursionService.updateExcursion(hotelName, excursionDTO), HttpStatus.OK);
    }

    @Operation(summary = "Enable of disable an excursion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully enabled/disabled the excursion",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExcursionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Cannot enable/disable excursion, hotel not found",
                    content = @Content) })
    @PatchMapping(path = "/{excursion_name}")
    public ResponseEntity<ExcursionDTO> setEnabledExcursion(@PathVariable(name = "hotel_name") String hotelName,
                                      @PathVariable(name = "excursion_name") String excursionName,
                                      @RequestBody Boolean isEnabled) throws CommonException {
        return new ResponseEntity<>(excursionService.setEnabled(hotelName, excursionName, isEnabled), HttpStatus.OK);
    }

    @Operation(summary = "Delete an excursion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted the excursion",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExcursionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Cannot delete excursion, hotel not found",
                    content = @Content) })
    @DeleteMapping(path = "/{excursion_name}")
    public ResponseEntity<ExcursionDTO> deleteExcursion(@PathVariable("hotel_name") String hotelName,
                                              @PathVariable("excursion_name") String excursionName) throws CommonException {
        return new ResponseEntity<>(excursionService.deleteExcursion(hotelName, excursionName), HttpStatus.OK);
    }

    @Operation(summary = "Add tag to excursion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added tag to excursion",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExcursionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Cannot add tag, hotel not found",
                    content = @Content) })
    @PatchMapping(path = "/{excursion_name}", params = "tag")
    public ResponseEntity<ExcursionDTO> addTagToExcursion(@PathVariable("hotel_name") String hotelName,
                                                 @PathVariable("excursion_name") String excursionName,
                                                 @RequestParam("tag") String tag) throws CommonException {
        return new ResponseEntity<>(excursionService.addTagToExcursion(hotelName, excursionName, tag), HttpStatus.OK);
    }
}
