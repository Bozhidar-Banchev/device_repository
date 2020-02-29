package com.brand.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.brand.dto.DeviceDto;
import com.brand.entity.Device;

@Mapper(componentModel = "spring")
public interface DeviceMapper {

    Device dtoToEntite(DeviceDto deviceDtos);

    List<Device> dtosToEntities(List<DeviceDto> deviceDtos);

    DeviceDto entityToDto(Device device);

    List<DeviceDto> entitiesToDtos(List<Device> devices);

}
