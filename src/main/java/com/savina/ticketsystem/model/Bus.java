package com.savina.ticketsystem.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bus {
    private String busId;
    private String busNumber;
    private Company company;


}
