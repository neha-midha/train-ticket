package com.cloudbees.entity;

import com.cloudbees.enums.TrainName;
import lombok.*;

import java.util.concurrent.atomic.AtomicLong;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ticket {

    private static final AtomicLong ID_GENERATOR = new AtomicLong(1);

    private String ticketId;
    private TrainName trainName;
    private User user;
    private Seat seat;
    private Route route;

    public Ticket(TrainName trainName, User user, Seat seat, Route route) {
        this.ticketId = trainName + "_" + ID_GENERATOR.getAndIncrement();
        this.trainName = trainName;
        this.user = user;
        this.seat = seat;
        this.route = route;
    }
}
