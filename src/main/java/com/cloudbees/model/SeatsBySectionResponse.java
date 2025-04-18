package com.cloudbees.model;

import com.cloudbees.entity.Seat;
import com.cloudbees.entity.User;
import com.cloudbees.enums.Section;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SeatsBySectionResponse {

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("seatNumber")
    private Integer seatNumber;

    @JsonProperty("section")
    private Section section;
}
