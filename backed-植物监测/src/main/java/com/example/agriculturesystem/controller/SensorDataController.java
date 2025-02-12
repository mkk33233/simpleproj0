package com.example.agriculturesystem.controller;

import com.example.agriculturesystem.model.SensorData;
import com.example.agriculturesystem.dto.SensorDataDTO;
import com.example.agriculturesystem.dto.DeviceDTO;
import com.example.agriculturesystem.service.CropRadarService;
import com.example.agriculturesystem.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sensordata")
@EnableScheduling  // 启用定时任务
public class SensorDataController {


    @Autowired
    private CropRadarService cropRadarService;

    /**
     * 获取植物的雷达状态
     * @param cropId 植物ID
     * @return {"cropId": 1, "isRedPoint": true}
     */


//    @Autowired
//    private SensorDataService sensorDataService;
//     //手动触发传感器数据检测
//    @PostMapping("/manual-detect")
//    public ResponseEntity<String> manualDetectSensorData() {
//        sensorDataService.detectSensorData(); // 调用检测服务
//        return ResponseEntity.status(HttpStatus.OK).body("传感器数据检测已执行");
//    }
//
//
//    // 获取单个传感器数据
//    @GetMapping("/{sensorId}")
//    public ResponseEntity<SensorDataDTO> getSensorData(@PathVariable Long sensorId) {
//        SensorData sensorData = sensorDataService.getSensorDataById(sensorId);
//        // 将实体类转换为 DTO
//        SensorDataDTO sensorDataDTO = mapToDTO(sensorData);
//        return ResponseEntity.ok(sensorDataDTO);
//    }
//
//
//
//    // 获取某设备下的所有传感器数据
//    @GetMapping("/device/{deviceId}")
//    public ResponseEntity<List<SensorDataDTO>> getSensorDataByDevice(@PathVariable Long deviceId) {
//        List<SensorData> sensorDataList = sensorDataService.getSensorDataByDeviceId(deviceId);
//        // 将实体类转换为 DTO
//        List<SensorDataDTO> sensorDataDTOList = mapToDTOList(sensorDataList);
//        return ResponseEntity.ok(sensorDataDTOList);
//    }
//
//    // 获取所有传感器数据
//    @GetMapping
//    public ResponseEntity<List<SensorDataDTO>> getAllSensorData() {
//        List<SensorData> sensorDataList = sensorDataService.getAllSensorData();
//        List<SensorDataDTO> sensorDataDTOList = mapToDTOList(sensorDataList);
//        return ResponseEntity.ok(sensorDataDTOList);
//    }
//
//    // 根据时间范围获取传感器数据
//    @GetMapping("/time-range")
//    public ResponseEntity<List<SensorDataDTO>> getSensorDataByTimeRange(
//            @RequestParam LocalDateTime startTime, @RequestParam LocalDateTime endTime) {
//        List<SensorData> sensorDataList = sensorDataService.getSensorDataByTimeRange(startTime, endTime);
//        List<SensorDataDTO> sensorDataDTOList = mapToDTOList(sensorDataList);
//        return ResponseEntity.ok(sensorDataDTOList);
//    }
//
//    // 添加传感器数据
//    @PostMapping
//    public ResponseEntity<SensorDataDTO> addSensorData(@RequestBody SensorDataDTO sensorDataDTO) {
//        // 将 DTO 转换为实体类
//        SensorData sensorData = mapToEntity(sensorDataDTO);
//        SensorData savedSensorData = sensorDataService.addSensorData(sensorData);
//        // 将实体类转换为 DTO
//        SensorDataDTO savedSensorDataDTO = mapToDTO(savedSensorData);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedSensorDataDTO);
//    }
//
//    // 更新传感器数据
//    @PutMapping("/{sensorId}")
//    public ResponseEntity<SensorDataDTO> updateSensorData(@PathVariable Long sensorId, @RequestBody SensorDataDTO sensorDataDTO) {
//        // 将 DTO 转换为实体类
//        SensorData sensorData = mapToEntity(sensorDataDTO);
//        sensorData.setId(sensorId);
//        SensorData updatedSensorData = sensorDataService.updateSensorData(sensorData);
//        // 将实体类转换为 DTO
//        SensorDataDTO updatedSensorDataDTO = mapToDTO(updatedSensorData);
//        return ResponseEntity.ok(updatedSensorDataDTO);
//    }
//
//    // 删除传感器数据
//    @DeleteMapping("/{sensorId}")
//    public ResponseEntity<Void> deleteSensorData(@PathVariable Long sensorId) {
//        sensorDataService.deleteSensorData(sensorId);
//        return ResponseEntity.noContent().build();
//    }
//
//
//    // 将 SensorData 转换为 SensorDataDTO
//    private SensorDataDTO mapToDTO(SensorData sensorData) {
//        SensorDataDTO dto = new SensorDataDTO();
//        dto.setId(sensorData.getId());
//        dto.setData_Type(sensorData.getDataType());
//        dto.setValue(sensorData.getValue());
//        dto.setTimestamp(sensorData.getTimestamp());
//
//        // 映射设备信息
//        DeviceDTO deviceDTO = new DeviceDTO();
//        deviceDTO.setId(sensorData.getDevice().getId());
//        deviceDTO.setName(sensorData.getDevice().getName());
//        deviceDTO.setType(sensorData.getDevice().getType());
//        deviceDTO.setStatus(sensorData.getDevice().getStatus());
//        dto.setDevice(deviceDTO);
//
//        return dto;
//    }
//
//    // 将 SensorDataDTO 转换为 SensorData
//    private SensorData mapToEntity(SensorDataDTO sensorDataDTO) {
//        SensorData sensorData = new SensorData();
//        sensorData.setDataType(sensorDataDTO.getData_Type());
//        sensorData.setValue(sensorDataDTO.getValue());
//        sensorData.setTimestamp(sensorDataDTO.getTimestamp());
//
//        // 映射设备信息
//        // 假设设备数据已经通过设备ID关联好
//        // 在实际应用中，可能需要查询数据库来获取设备对象
//        // deviceRepository.findById(sensorDataDTO.getDevice().getId()) 来获取设备信息
//
//        return sensorData;
//    }
//
//    // 将 SensorData 列表转换为 SensorDataDTO 列表
//    private List<SensorDataDTO> mapToDTOList(List<SensorData> sensorDataList) {
//        return sensorDataList.stream()
//                .map(this::mapToDTO)
//                .toList();
//    }
}
