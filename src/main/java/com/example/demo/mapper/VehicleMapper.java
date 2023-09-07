package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.dto.VehicleDTO;
import com.example.demo.entity.Vehicle;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    VehicleDTO toDTO(Vehicle vehicle);
    Vehicle toEntity(VehicleDTO vehicleDTO);
}


