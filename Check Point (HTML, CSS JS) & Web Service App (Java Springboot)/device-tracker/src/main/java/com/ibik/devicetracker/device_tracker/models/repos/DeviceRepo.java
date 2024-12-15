package com.ibik.devicetracker.device_tracker.models.repos;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.ibik.devicetracker.device_tracker.models.entities.Device;

public interface DeviceRepo extends CrudRepository<Device, Long> {

    List<Device> findByDeviceCode(String deviceCode);

}
