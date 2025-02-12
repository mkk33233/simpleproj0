package com.example.agriculturesystem.repository;

import com.example.agriculturesystem.model.Device;
import com.example.agriculturesystem.model.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    @Query("SELECT d FROM Device d LEFT JOIN FETCH d.crop WHERE d.id = :id")
    Optional<Device> findByIdWithCrop(@Param("id") int id);
    List<Device> findByCrop(Crop crop);
    List<Device> findByStatus(String status);

    List<Device>  findByType(String Tp);
    Device  findById(int id);
    List<Device> findAllById(Iterable<Long> ids);

    List<Device> findByIdIn(List<Integer> deviceIds);


    List<Device> findByCropId(Long cropid);
}
