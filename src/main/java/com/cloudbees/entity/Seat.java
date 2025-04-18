package com.cloudbees.entity;

import com.cloudbees.enums.Section;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Seat {

    private Integer seatNumber;
    private Section section;
    private boolean isOccupied;

}

