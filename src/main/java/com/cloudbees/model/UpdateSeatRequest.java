package com.cloudbees.model;

import com.cloudbees.enums.Section;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateSeatRequest {

    @JsonProperty(value = "seatNumber", required = true)
    private Integer seatNumber;

    @JsonProperty("section")
    private Section section;
}
