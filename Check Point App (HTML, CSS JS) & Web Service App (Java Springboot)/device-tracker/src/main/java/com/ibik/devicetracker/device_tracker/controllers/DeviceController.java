package com.ibik.devicetracker.device_tracker.controllers;

import com.ibik.devicetracker.device_tracker.models.entities.Device;
import com.ibik.devicetracker.device_tracker.services.DeviceService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/devicetracker/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Device device) {
        Device deviceByCode = deviceService.findByDeviceCode(device.getDeviceCode());

        if (deviceByCode == null || ! device.getDeviceCode().equals(deviceByCode.getDeviceCode())) {
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("deviceCode", "NOT FOUND IN DATABASE");
            responseBody.put("latitude", "");
            responseBody.put("longitude", "");
            responseBody.put("lastAccess", "");
            responseBody.put("zoneId", "");

            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        }

        deviceByCode.setLongitude(device.getLongitude());
        deviceByCode.setLatitude(device.getLatitude());

        deviceService.save(deviceByCode);

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("deviceCode", deviceByCode.getDeviceCode());
        responseBody.put("latitude", deviceByCode.getLatitude());
        responseBody.put("longitude", deviceByCode.getLongitude());
        responseBody.put("lastAccess", deviceByCode.getLastAccess());
        responseBody.put("zoneId", deviceByCode.getZoneId());

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

}