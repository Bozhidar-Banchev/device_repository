package com.brand.controller;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.brand.dto.DeviceDto;
import com.brand.dto.MasterDeviceDto;
import com.brand.service.DeviceService;

/**
 * @author Bozhidar
 *
 */
@Component
@Path("/device")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DeviceController {

    private DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @POST
    @Path("/create")
    public Response createDevices(@Valid MasterDeviceDto masterDeviceDto) {
        return deviceService.storeDevice(masterDeviceDto);
    }

    @GET
    @Path("/all")
    public Response getAllDivices() {
        return deviceService.findAll();
    }

    @GET
    @Path("/{serialNumber}")
    public Response findMasterDeviceBySerialNumber(@PathParam("serialNumber") String serialNumber) {
        return deviceService.findDeviceBySerialNumber(serialNumber);
    }

    @POST
    @Path("/add/{serialNumber}")
    public Response addDeviceToMasterDevice(@PathParam("serialNumber") String serialNumber, DeviceDto deviceDto) {
        return deviceService.addDevice(serialNumber, deviceDto);
    }

    @DELETE
    @Path("/delete/{serialNumber}/{identicalNumberDevice}")
    public Response deleteDeviceById(@PathParam("serialNumber") String serialNumber,
            @PathParam("identicalNumberDevice") Long identicalNumberDevice) {
        return deviceService.deleteDevice(serialNumber, identicalNumberDevice);
    }

    @DELETE
    @Path("/{serialNumber}")
    public Response deleteMasterDeviceBySerialNumber(@PathParam("serialNumber") String serialNumber) {
        return deviceService.deleteMasterDevice(serialNumber);
    }
}
