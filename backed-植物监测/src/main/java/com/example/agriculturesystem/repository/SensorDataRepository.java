package com.example.agriculturesystem.repository;

import com.example.agriculturesystem.model.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SensorDataRepository extends JpaRepository<SensorData, Long> {

    // 根据设备ID查找所有传感器数据
    List<SensorData> findByDeviceId(Long deviceId);

    // 根据时间范围查找传感器数据
    List<SensorData> findByTimestampBetween(LocalDateTime startTime, LocalDateTime endTime);
    // 根据设备ID查询历史数据


    // 根据设备ID和时间范围查询数据
    List<SensorData> findByDeviceIdAndTimestampBetween(Long deviceId, LocalDateTime start, LocalDateTime end);

    SensorData findTopByOrderByTimestampDesc();
    @Query("SELECT COUNT(sd) " +
            "FROM SensorData sd " +
            "WHERE sd.device.crop.id = :cropId AND sd.status = '警告' " +
            "AND sd.timestamp IN (" +
            "    SELECT sd.timestamp " +
            "    FROM SensorData sd2 " +
            "    WHERE sd2.device.crop.id = :cropId " +
            "    ORDER BY sd2.timestamp DESC" +
            "    LIMIT 5)" )
    int countRecentWarningsByCropId(@Param("cropId") Long cropId);



    @Query("SELECT DISTINCT sd.device.crop.id " +
            "FROM SensorData sd")
    List<Long> findAllCropIds(); // 获取所有植物的ID

}
