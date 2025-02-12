package com.example.agriculturesystem.repository;

import com.example.agriculturesystem.model.Crop;
import com.example.agriculturesystem.model.SensorData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CropRepository extends JpaRepository<Crop, Long> {
    // 可以根据需要自定义查询方法，例如：
    List<Crop> findByName(String name); // 根据作物名称查找作物

    @Query("SELECT sd FROM SensorData sd WHERE sd.device.crop.id = :cropId")
    List<SensorData> findByCropId(@Param("cropId") Long cropId);

    @Query("SELECT DISTINCT sd.device.crop.id " +
            "FROM SensorData sd")
    List<Long> findAllCropIds(); // 获取所有植物的ID



    @Query("SELECT DISTINCT sd.device.crop.id " +
            "FROM SensorData sd  ")
    List<Long> find();



}
