/**
 *
 */
package com.brand.test;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.assertj.core.api.Assertions;
import org.junit.gen5.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.brand.dto.DeviceDto;
import com.brand.dto.MasterDeviceDto;
import com.brand.dto.StatusDevice;
import com.brand.entity.Device;
import com.brand.entity.MasterDevice;
import com.brand.repository.MasterDeviceRepository;
import com.brand.service.DeviceService;

/**
 * @author Bozhidar
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DeviseServiceTest {

    @Inject
    private DeviceService deviceService;

    @Inject
    private MasterDeviceRepository masterDeviceRepository;


    /**
     * test junit 5 example
     * @throws Exception
     */
    @Test
    void testSaveMasterDevice() throws Exception {
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setIdenticalNumber(124124214L);
        deviceDto.setVendor("vendor");
        deviceDto.setStatus(StatusDevice.online);
        List<DeviceDto> deviceDtos = new ArrayList<>();
        deviceDtos.add(deviceDto);

        MasterDeviceDto dto = new MasterDeviceDto();
        dto.setSerialNumber("1234");
        dto.setName("name");
        dto.setAddressIPv4("137.235.181.12");
        dto.setDevices(deviceDtos);
        Response response = deviceService.storeDevice(dto);

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(masterDeviceRepository.findBySerialNumber(dto.getSerialNumber())).isNotNull();

    }

}
