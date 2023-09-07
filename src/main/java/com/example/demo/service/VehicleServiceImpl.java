package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.VehicleDTO;
import com.example.demo.entity.Vehicle;
import com.example.demo.exception.RegistrationNumberAlreadyExistsException;
import com.example.demo.exception.VehicleNotFoundException;
import com.example.demo.mapper.VehicleMapper;
import com.example.demo.repository.VehicleRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicles.stream()
                .map(vehicleMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VehicleDTO getVehicleById(Long id) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if (optionalVehicle.isPresent()) {
            return vehicleMapper.toDTO(optionalVehicle.get());
        } else {
            throw new VehicleNotFoundException("Vehicle with ID " + id + " not found");
        }
    }

    @Override
    public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleMapper.toEntity(vehicleDTO);

        // Check if the registration number is already taken
        if (vehicleRepository.findByRegistrationNumber(vehicle.getRegistrationNumber()) != null) {
            throw new RegistrationNumberAlreadyExistsException("Registration number " + vehicle.getRegistrationNumber() + " already exists");
        }

        // Set creation details
        vehicle.setCreatedBy("YourCreatedUser");
        vehicle.setCreationTime(new Date());

        vehicle = vehicleRepository.save(vehicle);
        return vehicleMapper.toDTO(vehicle);
    }

    @Override
    public VehicleDTO updateVehicle(Long id, VehicleDTO vehicleDTO) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if (optionalVehicle.isPresent()) {
            Vehicle existingVehicle = optionalVehicle.get();

            // Check if the updated registration number is already taken
            String updatedRegistrationNumber = vehicleDTO.getRegistrationNumber();
            if (!existingVehicle.getRegistrationNumber().equals(updatedRegistrationNumber) &&
                vehicleRepository.findByRegistrationNumber(updatedRegistrationNumber) != null) {
                throw new RegistrationNumberAlreadyExistsException("Registration number " + updatedRegistrationNumber + " already exists");
            }

            // Update the existing vehicle
            existingVehicle.setRegistrationNumber(vehicleDTO.getRegistrationNumber());
            existingVehicle.setOwnerName(vehicleDTO.getOwnerName());
            existingVehicle.setBrand(vehicleDTO.getBrand());
            existingVehicle.setRegistrationExpires(vehicleDTO.getRegistrationExpires());
            existingVehicle.setModifiedBy("YourModifiedUser");
            existingVehicle.setModifiedTime(new Date());

            vehicleRepository.save(existingVehicle);
            return vehicleMapper.toDTO(existingVehicle);
        } else {
            throw new VehicleNotFoundException("Vehicle with ID " + id + " not found");
        }
    }

    @Override
    public void deleteVehicle(Long id) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if (optionalVehicle.isPresent()) {
            vehicleRepository.deleteById(id);
        } else {
            throw new VehicleNotFoundException("Vehicle with ID " + id + " not found");
        }
    }
}
