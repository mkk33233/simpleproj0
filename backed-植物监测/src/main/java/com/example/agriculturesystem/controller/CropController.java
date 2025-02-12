package com.example.agriculturesystem.controller;

import com.example.agriculturesystem.model.Crop;
import com.example.agriculturesystem.model.Device;
import com.example.agriculturesystem.model.usercrop;
import com.example.agriculturesystem.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/crops")
public class CropController {

    @Autowired
    private CropService cropService;

    // 获取单个作物信息
    @GetMapping("/{cropId}")
    public ResponseEntity<Crop> getCrop(@PathVariable Long cropId) {
        Crop crop = cropService.getCropById(cropId);
        return ResponseEntity.ok(crop);
    }

    // 获取所有作物信息
    @GetMapping
    public ResponseEntity<List<Crop>> getAllCrops() {
        List<Crop> crops = cropService.getAllCrops();
        return ResponseEntity.ok(crops);
    }

    // 添加新作物
    @PostMapping
    public ResponseEntity<Crop> addCrop(@RequestBody Crop crop) {
        Crop savedCrop = cropService.addCrop(crop);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCrop);
    }

    // 更新作物信息
    @PutMapping("/{cropId}")
    public ResponseEntity<Crop> updateCrop(@PathVariable Long cropId, @RequestBody Crop crop) {
        Crop updatedCrop = cropService.updateCrop(cropId, crop);
        return ResponseEntity.ok(updatedCrop);
    }






    @PostMapping("/uc")
    public ResponseEntity<usercrop> getDevicesByUsername(@RequestBody usercrop usercrop) {
        try {
            // 调用 Service 层获取设备信息S
            System.out.println("============================封装crops错了吗");
            usercrop usercrop2 = cropService.save(usercrop);


            return ResponseEntity.ok(usercrop2); // 返回设备信息
        } catch (Exception e) {
            return null; // 返回 500 错误
        }
    }












    @GetMapping("/username/{username}")
    public ResponseEntity<List<Crop>> getDevicesByUsername(@PathVariable String username) {
        try {
            // 调用 Service 层获取设备信息S
            System.out.println("============================封装crops错了吗");
            List<Crop> crops = cropService.getCropByUsername(username);
            System.out.println(crops+"adadacrops111111111111111111111111111111111111111111111111111111111");
            if (crops.isEmpty()) {
                return null; // 如果没有找到设备，返回 404
            }

            return ResponseEntity.ok(crops); // 返回设备信息
        } catch (Exception e) {
            return null; // 返回 500 错误
        }
    }




    // 删除作物
    @DeleteMapping("/{cropId}")
    public ResponseEntity<?> deleteCrop(@PathVariable Long cropId) {
        cropService.deleteCrop(cropId);
        return ResponseEntity.noContent().build();
    }
}
