package com.brand.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.brand.dto.MasterDeviceDto;
import com.brand.entity.MasterDevice;

@Mapper(componentModel = "spring", uses = DeviceMapper.class)
public interface MasterDeviceMapper {

    /**
     * @param masterDeviceDto
     * @return
     */
    MasterDevice dtoToEntity(MasterDeviceDto masterDeviceDto);

    /**
     * @param masterDevice
     * @param masterDeviceDto
     */
    void update(@MappingTarget MasterDevice masterDevice, MasterDeviceDto masterDeviceDto);

    MasterDeviceDto entityToDto(MasterDevice findAll);

    /**
     * @param findAll
     * @return
     */
    List<MasterDeviceDto> entitiesToDtos(List<MasterDevice> findAll);

}
