package com.cloudbees.service.impl;

import com.cloudbees.entity.Ticket;
import com.cloudbees.enums.Section;
import com.cloudbees.enums.TrainName;
import com.cloudbees.model.SeatsBySectionResponse;
import com.cloudbees.repository.DataStore;
import com.cloudbees.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("trainServiceImpl")
public class TrainServiceImpl implements TrainService {

    @Autowired
    private DataStore dataStore;

    @Override
    public List<SeatsBySectionResponse> getOccupiedSeatsBySection(TrainName trainName, Section section) {
        List<Ticket> tickets = dataStore.getTicketsByTrainNameAndSection(trainName, section);
        List<SeatsBySectionResponse> seatsBySectionResponseList = new ArrayList<>();
        tickets.forEach(ticket -> {
            SeatsBySectionResponse response = new SeatsBySectionResponse();
            response.setFirstName(ticket.getUser().getFirstName());
            response.setLastName(ticket.getUser().getLastName());
            response.setEmail(ticket.getUser().getEmail());
            response.setSeatNumber(ticket.getSeat().getSeatNumber());
            response.setSection(ticket.getSeat().getSection());
            seatsBySectionResponseList.add(response);
        });
        return seatsBySectionResponseList;
    }
}
