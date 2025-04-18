package com.cloudbees.service;

import com.cloudbees.enums.Section;
import com.cloudbees.enums.TrainName;
import com.cloudbees.model.SeatsBySectionResponse;

import java.util.List;

public interface TrainService {

    List<SeatsBySectionResponse> getOccupiedSeatsBySection(TrainName trainName, Section section);

}
