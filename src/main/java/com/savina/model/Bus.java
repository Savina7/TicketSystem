package com.savina.model;

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


    public Bus addBus(){
        return null;
    }

    public boolean updateStatus(){
        return true;
    }
}
