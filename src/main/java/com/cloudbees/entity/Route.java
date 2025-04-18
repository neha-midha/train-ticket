package com.cloudbees.entity;

import com.cloudbees.enums.Station;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Route {

    private Station from;
    private Station to;
    private double price;

}
