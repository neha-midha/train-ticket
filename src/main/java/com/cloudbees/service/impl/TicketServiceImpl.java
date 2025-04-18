package com.cloudbees.service.impl;

import com.cloudbees.entity.*;
import com.cloudbees.exception.InvalidInputException;
import com.cloudbees.exception.NotFoundException;
import com.cloudbees.model.TicketPurchaseRequest;
import com.cloudbees.model.TicketPurchaseResponse;
import com.cloudbees.model.UpdateSeatRequest;
import com.cloudbees.repository.DataStore;
import com.cloudbees.service.SeatAssignment;
import com.cloudbees.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service("ticketServiceImpl")
public class TicketServiceImpl implements TicketService {

    @Autowired
    private DataStore dataStore;

    private Map<String,SeatAssignment> seatAssignmentServices;

    public TicketServiceImpl(
            @Autowired @Qualifier("preferredSectionSeatService") SeatAssignment preferredSectionSeatService,
            @Autowired @Qualifier("randomSectionSeatService") SeatAssignment randomSectionSeatService) {
        seatAssignmentServices = new HashMap<>();
        seatAssignmentServices.put("RANDOM", randomSectionSeatService);
        seatAssignmentServices.put("PREFERRED", preferredSectionSeatService);
    }

    @Override
    public TicketPurchaseResponse purchaseTicket(TicketPurchaseRequest request) {
        Train train = dataStore.getTrain(request.getTrainName());
        if(Objects.isNull(train)) {
            throw new NotFoundException("Train not found");
        }
        Seat seat;
        if(Objects.isNull(request.getPreferredSection())) {
            seat = seatAssignmentServices.get("RANDOM").assignSeat(train, null);
        } else {
            seat = seatAssignmentServices.get("PREFERRED").assignSeat(train, request.getPreferredSection());
        }
        User user = new User(request.getFirstName(), request.getLastName(), request.getEmail());
        Optional<Route> route = train.getRoutes().stream().filter(r -> r.getFrom().equals(request.getFrom()) && r.getTo().equals(request.getTo())).findFirst();
        if(route.isEmpty()) {
            throw new InvalidInputException("Invalid Route for the train.");
        }
        seat.setOccupied(true);
        Ticket ticket = new Ticket(train.getName(), user, seat, route.get());
        dataStore.addTicket(ticket);
        TicketPurchaseResponse response = new TicketPurchaseResponse();
        response.setTicketId(ticket.getTicketId());
        response.setFrom(request.getFrom());
        response.setTo(request.getTo());
        response.setUser(user);
        response.setPricePaid(route.get().getPrice());
        return response;
    }

    @Override
    public TicketPurchaseResponse getReceipt(String ticketId) {
        Ticket ticket = dataStore.getTicketById(ticketId);
        if(Objects.isNull(ticket)) {
            throw new NotFoundException("Ticket not found");
        }
        TicketPurchaseResponse response = new TicketPurchaseResponse();
        response.setFrom(ticket.getRoute().getFrom());
        response.setTo(ticket.getRoute().getTo());
        response.setUser(ticket.getUser());
        response.setPricePaid(ticket.getRoute().getPrice());
        return response;
    }

    @Override
    public void removeUser(String ticketId) {
        Ticket ticket = dataStore.getTicketById(ticketId);
        if(Objects.isNull(ticket)) {
            throw new NotFoundException("Ticket not found");
        }
        dataStore.removeTicket(ticket);
        ticket.getSeat().setOccupied(false);
    }

    @Override
    public void updateUserSeat(String ticketId, UpdateSeatRequest request) {
        Ticket ticket = dataStore.getTicketById(ticketId);
        if(Objects.isNull(ticket)) {
            throw new NotFoundException("Ticket not found");
        }
        Train train = dataStore.getTrain(ticket.getTrainName());
        if(Objects.isNull(train)) {
            throw new NotFoundException("Train not found");
        }
        Seat oldSeat = ticket.getSeat();
        Optional<Seat> newSeatOption;
        if(Objects.isNull(request.getSection())) {
            newSeatOption = train.getAllSeats().stream()
                    .filter(s -> s.getSeatNumber().equals(request.getSeatNumber()) && s.getSection().equals(request.getSection()))
                    .findFirst();
        } else {
            newSeatOption = train.getAllSeatsFromSection(request.getSection()).stream()
                    .filter(s -> s.getSeatNumber().equals(request.getSeatNumber()))
                    .findFirst();
        }
        Seat newSeat = newSeatOption.get();
        synchronized (newSeat) {
            if(newSeat.isOccupied()) {
                throw new InvalidInputException("Seat not available");
            }
            newSeat.setOccupied(true);
            oldSeat.setOccupied(false);
            ticket.setSeat(newSeat);
        }
    }

}
