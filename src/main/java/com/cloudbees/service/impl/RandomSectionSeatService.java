package com.cloudbees.service.impl;

import com.cloudbees.entity.Seat;
import com.cloudbees.entity.Train;
import com.cloudbees.enums.Section;
import com.cloudbees.exception.InvalidInputException;
import com.cloudbees.repository.DataStore;
import com.cloudbees.service.SeatAssignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("randomSectionSeatService")
public class RandomSectionSeatService implements SeatAssignment {

    @Autowired
    private DataStore dataStore;

    @Override
    public Seat assignSeat(Train train, Section section) {
        List<Seat> allSeats = train.getAllSeats();
        synchronized (allSeats) {
            Optional<Seat> availableSeatOption = allSeats.parallelStream().filter(s -> !s.isOccupied()).findAny();
            if(availableSeatOption.isEmpty()) {
                throw new InvalidInputException("No seat available");
            }
            return availableSeatOption.get();
        }
    }
}
