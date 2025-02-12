package com.example.agriculturesystem.service;

import com.example.agriculturesystem.exception.ResourceNotFoundException;
import com.example.agriculturesystem.model.Crop;
import com.example.agriculturesystem.model.Device;
import com.example.agriculturesystem.model.UserDevice;
import com.example.agriculturesystem.model.usercrop;
import com.example.agriculturesystem.repository.CropRepository;
import com.example.agriculturesystem.repository.UserCropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CropService {

    @Autowired
    private CropRepository cropRepository;
    @Autowired
    private UserCropRepository usercroprepository;
    @Autowired
    private CropRepository croprepository;
    // 获取作物信息
    public Crop getCropById(Long cropId) {
        return cropRepository.findById(cropId)
                .orElseThrow(() -> new ResourceNotFoundException("Crop not found"));
    }

    // 获取所有作物信息
    public List<Crop> getAllCrops() {
        return cropRepository.findAll();
    }

    // 添加新作物
    public Crop addCrop(Crop crop) {
        return cropRepository.save(crop);
    }

    // 更新作物
    public Crop updateCrop(Long cropId, Crop crop) {
        Crop existingCrop = cropRepository.findById(cropId)
                .orElseThrow(() -> new ResourceNotFoundException("Crop not found"));
        existingCrop.setName(crop.getName());
        existingCrop.setGrowthCycle(crop.getGrowthCycle());
        return cropRepository.save(existingCrop);
    }

    // 删除作物
    public void deleteCrop(Long cropId) {
        Crop existingCrop = cropRepository.findById(cropId)
                .orElseThrow(() -> new ResourceNotFoundException("Crop not found"));
        cropRepository.delete(existingCrop);
    }



    public List<Crop> getCropByUsername(String username) {

        List<usercrop> usercrops =  usercroprepository.findByUsername(username);

        // 如果没有找到设备，直接返回空列表
        if (usercrops.isEmpty()) {System.out.println("没找到、");
            return new ArrayList<>();
        }

        // 提取设备ID并转换为 List<Long>
        List<Long> cropids = usercrops.stream()
                .map(userCrop -> userCrop.getCrop().getId())  // 确保返回 Long 类型
                .collect(Collectors.toList());
         System.out.println(cropids+"assssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
          return croprepository.findAllById(cropids);

    }

    public usercrop save(usercrop usercrop) {
        return usercroprepository.save(usercrop);
    }
}
