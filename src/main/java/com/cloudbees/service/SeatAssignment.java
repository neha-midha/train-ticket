package com.cloudbees.service;

import com.cloudbees.entity.Seat;
import com.cloudbees.entity.Train;
import com.cloudbees.enums.Section;

public interface SeatAssignment {

    Seat assignSeat(Train train, Section section);
}
