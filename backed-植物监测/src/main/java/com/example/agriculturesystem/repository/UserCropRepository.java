package com.example.agriculturesystem.repository;

import com.example.agriculturesystem.model.Crop;
import com.example.agriculturesystem.model.usercrop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserCropRepository extends JpaRepository<usercrop, Long> {
    // 可以根据需要自定义查询方法，例如：


    List<usercrop> findByUsername(String username);
    @Query("SELECT uc.crop.id FROM usercrop uc WHERE uc.username = :username")
    List<Long> findIds(String username);
}
