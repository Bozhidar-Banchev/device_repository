package com.brand.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.brand.dto.CustomResponse;
import com.brand.dto.DeviceDto;
import com.brand.dto.MasterDeviceDto;
import com.brand.entity.Device;
import com.brand.entity.MasterDevice;
import com.brand.mapper.DeviceMapper;
import com.brand.mapper.MasterDeviceMapper;
import com.brand.repository.DeviceRepository;
import com.brand.repository.MasterDeviceRepository;

/**
 * @author Bozhidar
 *
 */
@Service
@Transactional
public class DeviceService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private MasterDeviceRepository masterRepository;

    private MasterDeviceMapper masterDeviceMapper;

    private DeviceRepository deviceRepository;

    private DeviceMapper deviceMapper;

    /**
     * default constructor
     */
    public DeviceService() {

    }

    @Inject
    public DeviceService(MasterDeviceRepository masterRepository, MasterDeviceMapper masterDeviceMapper,
            DeviceRepository deviceRepository, DeviceMapper deviceMapper) {
        this.masterRepository = masterRepository;
        this.masterDeviceMapper = masterDeviceMapper;
        this.deviceRepository = deviceRepository;
        this.deviceMapper = deviceMapper;
    }

    /**
     * Method that persist master device and devices in database
     *
     * @param masterDeviceDto
     */
    public Response storeDevice(MasterDeviceDto masterDeviceDto) {
        checkForDuplicateSerialNumber(masterDeviceDto.getSerialNumber());
        MasterDevice masterDevice = masterDeviceMapper.dtoToEntity(masterDeviceDto);
        try {
            masterDevice = masterRepository.save(masterDevice);
            if (!masterDevice.getDevices().isEmpty()) {
                for (Device device : masterDevice.getDevices()) {
                    device.setParent(masterDevice);
                    deviceRepository.save(device);
                }
            }
            return Response.ok()
                    .entity(new CustomResponse(HttpStatus.OK, "Devices are saved successfully.")).build();
        } catch (Exception e) {
            logger.error("__ERROR__ while save devices: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    /**
     * Mehtod that check for duplicate serial number
     *
     * @param serialNumber
     */
    private void checkForDuplicateSerialNumber(String serialNumber) {
        MasterDevice masterDevice = masterRepository.findBySerialNumber(serialNumber);
        if (masterDevice != null) {
            throw new DuplicateKeyException("Duplicate serial number!!!");
        }
    }

    /**
     * Find all devices
     *
     * @return
     */
    public Response findAll() {
        List<MasterDevice> allDevices = masterRepository.findAllDevices();
        List<MasterDeviceDto> masterDeviceDtos = masterDeviceMapper.entitiesToDtos(allDevices);
        return Response.ok().entity(masterDeviceDtos).build();
    }

    /**
     * Find device by id
     *
     * @param id
     * @return
     */
    public Response findDeviceBySerialNumber(String serialNumber) {
        MasterDevice masterDevice = masterRepository.findBySerialNumber(serialNumber);
        if (masterDevice == null) {
            throw new NotFoundException("Master device with this id not exist");
        }
        return Response.ok(masterDeviceMapper.entityToDto(masterDevice)).build();
    }

    /**
     * Add new device to masterDevice
     *
     * @param serialNumber
     * @param deviceDto
     * @return
     */
    public Response addDevice(String serialNumber, DeviceDto deviceDto) {
        MasterDevice masterDevice = masterRepository.findBySerialNumber(serialNumber);
        if (masterDevice == null) {
            throw new NotFoundException("Master device with this id not exist");
        } else {
        	if (masterDevice.getDevices().size() <10) {
        		Device device = deviceMapper.dtoToEntite(deviceDto);
        		device.setParent(masterDevice);
        		deviceRepository.save(device);
			}else {
				return Response.status(Response.Status.BAD_REQUEST).entity("Can't_add_device._Deveces_can't_be_more_than_10!").build();
			}
        }
        return Response.ok().build();
    }

    /**
     * Delete master device by id
     *
     * @param id
     * @return
     */
    public Response deleteMasterDevice(String serialNumber) {
        MasterDevice masterDevice = masterRepository.findBySerialNumber(serialNumber);
        if (masterDevice == null) {
            throw new NotFoundException("Master device with this id not exist");
        }
        masterRepository.deleteById(masterDevice.getId());
        return Response.ok().build();
    }

    /**
     * Delete device by identical number
     *
     * @param masterDeviceId
     * @param identicalNumberDevice
     * @return
     */
    public Response deleteDevice(String serialNumber, Long identicalNumberDevice) {
        MasterDevice masterDevice = masterRepository.findBySerialNumber(serialNumber);
        if (masterDevice == null) {
            throw new NotFoundException("Master device with this id not exist");
        }
        List<Device> devices = masterDevice.getDevices();
        if (!devices.isEmpty()
                && devices.stream().anyMatch(d -> d.getIdenticalNumber().equals(identicalNumberDevice))) {
            deviceRepository.deleteByIdenticalNumber(identicalNumberDevice);
        }
        return Response.ok().build();
    }

}
