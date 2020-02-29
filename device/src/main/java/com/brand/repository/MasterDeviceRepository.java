package com.brand.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.brand.entity.MasterDevice;

@Repository
public interface MasterDeviceRepository extends CrudRepository<MasterDevice, Long> {

    /**
     * @param serialNumber
     * @return
     */

    MasterDevice findBySerialNumber(String serialNumber);

    /**
     * @return
     */
    @Query("SELECT m FROM MasterDevice m")
    List<MasterDevice> findAllDevices();

}
