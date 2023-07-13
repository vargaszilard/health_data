package com.econsult.health.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GrowingTendencyResponse {
    private long patientId;
    private Map<String, List<DateResult>> tendencies;
}
