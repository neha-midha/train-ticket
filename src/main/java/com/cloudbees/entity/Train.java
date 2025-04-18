package com.cloudbees.entity;

import com.cloudbees.enums.Section;
import com.cloudbees.enums.Station;
import com.cloudbees.enums.TrainName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Train {

    private static final AtomicLong ID_GENERATOR = new AtomicLong(1);

    private Long id;
    private TrainName name;
    private Station origin;
    private Station destination;
    private Set<Route> routes;
    private Map<Section, List<Seat>> sectionSeats;

    public Train(TrainName name, Station origin, Station destination, Set<Route> routes, Map<Section, List<Seat>> sectionSeats) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.name = name;
        this.origin = origin;
        this.destination = destination;
        this.routes = routes;
        this.sectionSeats = sectionSeats;
    }


    public List<Seat> getAllSeatsFromSection(Section section) {
        return sectionSeats.get(section).stream().toList();
    }

    public List<Seat> getAllSeats() {
        return sectionSeats.values().stream().flatMap(List::stream).toList();
    }
}
