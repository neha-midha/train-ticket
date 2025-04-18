package com.cloudbees.controller;

import com.cloudbees.enums.Section;
import com.cloudbees.enums.TrainName;
import com.cloudbees.model.SeatsBySectionResponse;
import com.cloudbees.service.TrainService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/train")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @GetMapping("{trainName}/user-seats/{section}")
    public ResponseEntity<List<SeatsBySectionResponse>> getUserSeats(
            @Parameter(description = "Train name", required = true, schema = @Schema(implementation = TrainName.class))
            @PathVariable("trainName") TrainName trainName,
            @Parameter(description = "Section of the train", required = true, schema = @Schema(implementation = Section.class))
            @PathVariable("section") Section section) {
        List<SeatsBySectionResponse> seatsList = trainService.getOccupiedSeatsBySection(trainName, section);
        return new ResponseEntity<>(seatsList, HttpStatus.OK);
    }
}
