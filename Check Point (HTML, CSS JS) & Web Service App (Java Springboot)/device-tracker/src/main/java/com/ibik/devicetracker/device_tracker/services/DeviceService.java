package com.ibik.devicetracker.device_tracker.services;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ibik.devicetracker.device_tracker.models.entities.Device;
import com.ibik.devicetracker.device_tracker.models.repos.DeviceRepo;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepo deviceRepo;

    public Device save(Device device) {
        device.setLastAccess(LocalDateTime.now().toString());
        return deviceRepo.save(device);
    }

    public Device findByDeviceCode(String deviceCode) {
        List<Device> devices = deviceRepo.findByDeviceCode(deviceCode);
        if (!devices.isEmpty()) {
            return devices.get(0);
        }
        return null;
    }
    
}