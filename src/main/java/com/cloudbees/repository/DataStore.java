package com.cloudbees.repository;

import com.cloudbees.entity.Route;
import com.cloudbees.entity.Seat;
import com.cloudbees.entity.Ticket;
import com.cloudbees.entity.Train;
import com.cloudbees.enums.Section;
import com.cloudbees.enums.Station;
import com.cloudbees.enums.TrainName;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class DataStore {

    private Map<TrainName, Train> trains = new ConcurrentHashMap<>();
    private Map<String,Ticket> tickets = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        Route route1 = new Route(Station.LONDON, Station.BRUSSELS, 10.0);
        Route route2 = new Route(Station.BRUSSELS, Station.PARIS, 10.0);
        Route route3 = new Route(Station.LONDON, Station.PARIS, 20.0);
        Set<Route> routes = new HashSet<>();
        routes.add(route1);
        routes.add(route2);
        routes.add(route3);
        Seat seat1A = new Seat(1,Section.A,false);
        Seat seat2A = new Seat(2,Section.A,false);
        Seat seat3A = new Seat(3,Section.A,false);
        Seat seat4A = new Seat(4,Section.A,false);
        Seat seat5A = new Seat(5,Section.A,false);
        Seat seat6A = new Seat(6,Section.A,false);
        Seat seat7A = new Seat(7,Section.A,false);
        Seat seat8A = new Seat(8,Section.A,false);
        Seat seat9A = new Seat(9,Section.A,false);
        Seat seat10A = new Seat(10,Section.A,false);
        Seat seat1B = new Seat(1,Section.B,false);
        Seat seat2B = new Seat(2,Section.B,false);
        Seat seat3B = new Seat(3,Section.B,false);
        Seat seat4B = new Seat(4,Section.B,false);
        Seat seat5B = new Seat(5,Section.B,false);
        Seat seat6B = new Seat(6,Section.B,false);
        Seat seat7B = new Seat(7,Section.B,false);
        Seat seat8B = new Seat(8,Section.B,false);
        Seat seat9B = new Seat(9,Section.B,false);
        Seat seat10B = new Seat(10,Section.B,false);
        Map<Section,List<Seat>> sectionSeats = new ConcurrentHashMap<>();
        sectionSeats.put(Section.A, Arrays.asList(seat1A, seat2A, seat3A, seat4A, seat5A, seat6A, seat7A, seat8A, seat9A, seat10A));
        sectionSeats.put(Section.B, Arrays.asList(seat1B, seat2B, seat3B, seat4B, seat5B, seat6B, seat7B, seat8B, seat9B, seat10B));
        Train train = new Train(TrainName.LONDON_FRANCE_EXPRESS, Station.LONDON, Station.PARIS,routes, sectionSeats);

        trains.put(TrainName.LONDON_FRANCE_EXPRESS, train);

    }

    public void addTicket(Ticket ticket) {
        tickets.put(ticket.getTicketId(), ticket);
    }

    public Train getTrain(TrainName name) {
        return trains.get(name);
    }

    public Ticket getTicketById(String ticketId) {
        return tickets.get(ticketId);
    }

    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket.getTicketId());
    }

    public List<Ticket> getTicketsByTrainNameAndSection(TrainName trainName, Section section) {
        return tickets.entrySet().stream()
                .filter(e -> e.getKey().startsWith(trainName.toString()) && e.getValue().getSeat().getSection().equals(section))
                .map(Map.Entry::getValue).collect(Collectors.toList());
    }
}
