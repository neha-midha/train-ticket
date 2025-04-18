package com.cloudbees.model;

import com.cloudbees.enums.Section;
import com.cloudbees.enums.Station;
import com.cloudbees.enums.TrainName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TicketPurchaseRequest {

    @JsonProperty(value = "from", required = true)
    private Station from;

    @JsonProperty(value = "to", required = true)
    private Station to;

    @JsonProperty(value = "firstName", required = true)
    private String firstName;

    @JsonProperty(value = "lastName", required = true)
    private String lastName;

    @JsonProperty(value = "email", required = true)
    private String email;

    @JsonProperty(value = "train", required = true)
    private TrainName trainName;

    @JsonProperty(value = "preferredSection")
    private Section preferredSection;
}
