package com.brand.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.brand.entity.Device;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {

    /**
     * @param identicalNumberDevice
     */
    @Modifying
    @Query("DELETE FROM Device WHERE identicalNumber = :identicalNumberDevice")
    void deleteByIdenticalNumber(Long identicalNumberDevice);

}
