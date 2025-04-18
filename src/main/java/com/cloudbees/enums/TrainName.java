package com.cloudbees.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

@Schema(description = "Train names")
public enum TrainName {
    LONDON_FRANCE_EXPRESS("London France Express");

    private final String name;

    TrainName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

