package com.savina.ticketsystem.model;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // ID automatik
    @Column(name = "device_id", updatable = false)
    private Long deviceID;

    @Column(name = "device_type", length = 50)
    private String deviceType;

    @Column(name = "serial_number")
    private Float serialNumber;


    @Column(name = "STATUS", length = 1)
    private String status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id") // lidhja me tabelën Bus
    private Bus bus;

    // Konstruktor pa ID (ID gjenerohet automatikisht)
    public Device(String deviceType, Float serialNumber, Bus bus) {
        this.deviceType = deviceType;
        this.serialNumber = serialNumber;
        this.bus = bus;
    }
}
