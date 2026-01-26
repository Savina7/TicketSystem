package com.savina;

import com.savina.ticketsystem.model.Bus;
import com.savina.ticketsystem.model.Device;
import com.savina.ticketsystem.repository.DeviceRepository;
import com.savina.ticketsystem.service.DeviceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeviceServiceTest {

    @InjectMocks
    private DeviceService deviceService;

    @Mock
    private DeviceRepository deviceRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testAddDevice() {
        Bus bus = new Bus();
        Device device = new Device("GPS", 12345f, bus);
        device.setStatus("A");

        when(deviceRepository.save(any(Device.class))).thenReturn(device);

        Device result = deviceService.addDevice("GPS", 12345f, bus);

        assertNotNull(result);
        assertEquals("GPS", result.getDeviceType());
        assertEquals(12345f, result.getSerialNumber());
        assertEquals(bus, result.getBus());
        assertEquals("A", result.getStatus());

        verify(deviceRepository, times(1)).save(any(Device.class));
    }


    @Test
    void testUpdateDevice_Success() {
        Long deviceId = 1L;
        Bus bus = new Bus();
        Device existingDevice = new Device("OldType", 11111f, bus);
        existingDevice.setStatus("A");

        when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(existingDevice));
        when(deviceRepository.save(existingDevice)).thenReturn(existingDevice);

        Device result = deviceService.updateDevice(deviceId, "NewType", 22222f, bus, "I");

        assertNotNull(result);
        assertEquals("NewType", result.getDeviceType());
        assertEquals(22222f, result.getSerialNumber());
        assertEquals(bus, result.getBus());
        assertEquals("I", result.getStatus());

        verify(deviceRepository, times(1)).findById(deviceId);
        verify(deviceRepository, times(1)).save(existingDevice);
    }


    @Test
    void testUpdateDevice_NotFound() {
        Long deviceId = 99L;

        when(deviceRepository.findById(deviceId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                deviceService.updateDevice(deviceId, "Type", 123f, new Bus(), "A"));

        assertEquals("Device nuk u gjet me ID: " + deviceId, exception.getMessage());
        verify(deviceRepository, times(1)).findById(deviceId);
        verify(deviceRepository, never()).save(any(Device.class));
    }
}