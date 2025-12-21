package com.savina.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    private String deviceID;
    private String deviceType;
    private Float serialNumber;
    private Bus bus;



}
