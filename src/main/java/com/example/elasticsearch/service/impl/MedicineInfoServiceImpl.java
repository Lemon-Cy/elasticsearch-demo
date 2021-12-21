package com.example.elasticsearch.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.elasticsearch.entity.MedicineInfo;
import com.example.elasticsearch.mapper.MedicineInfoMapper;
import com.example.elasticsearch.service.MedicineInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ChenYu
 * @since 2021-12-20
 */
@Service
public class MedicineInfoServiceImpl extends ServiceImpl<MedicineInfoMapper, MedicineInfo> implements MedicineInfoService {
}
