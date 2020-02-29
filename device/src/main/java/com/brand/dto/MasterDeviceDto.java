package com.brand.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MasterDeviceDto implements Serializable {

    private static final long serialVersionUID = 2786005554221980735L;

    private static final String IPv4_REGEX = "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."
            + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."
            + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    @NotNull(message = "Serial_number_is_requared!")
    private String serialNumber;

    private String name;

    @Pattern(regexp = MasterDeviceDto.IPv4_REGEX, message = "IP_address_is_incorrect!")
    private String addressIPv4;

    @Size(max = 10, message = "Maximum_10_devices")
    private List<DeviceDto> devices;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressIPv4() {
        return addressIPv4;
    }

    public void setAddressIPv4(String addressIPv4) {
        this.addressIPv4 = addressIPv4;
    }

    public List<DeviceDto> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceDto> devices) {
        this.devices = devices;
    }

}
