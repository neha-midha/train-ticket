package com.cloudbees.model;

import com.cloudbees.entity.User;
import com.cloudbees.enums.Station;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TicketPurchaseResponse {

    @JsonProperty("ticketId")
    private String ticketId;

    @JsonProperty("from")
    private Station from;

    @JsonProperty("to")
    private Station to;

    @JsonProperty("user")
    private User user;

    @JsonProperty("price")
    private double pricePaid;
}
