package com.savina.ticketsystem.service;

import com.savina.ticketsystem.model.Device;
import com.savina.ticketsystem.model.Bus;
import com.savina.ticketsystem.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    // Shto device të ri
    public Device addDevice(String deviceType, Float serialNumber, Bus bus) {
        Device device = new Device(deviceType, serialNumber, bus);
        device.setStatus("A"); // default ACTIVE
        return deviceRepository.save(device);
    }

    // Përditëso të dhënat e device
    public Device updateDevice(Long deviceId, String deviceType, Float serialNumber, Bus bus, String status) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Device nuk u gjet me ID: " + deviceId));

        if (deviceType != null) device.setDeviceType(deviceType);
        if (serialNumber != null) device.setSerialNumber(serialNumber);
        if (bus != null) device.setBus(bus);
        if (status != null) device.setStatus(status);

        return deviceRepository.save(device);
    }

}
