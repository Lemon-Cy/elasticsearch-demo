package com.example.elasticsearch.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.elasticsearch.entity.EsResponsePage;
import com.example.elasticsearch.entity.OilGas;
import com.example.elasticsearch.entity.RequestPage;

import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ChenYu
 * @since 2021-12-17
 */
public interface OilGasService extends IService<OilGas> {

    EsResponsePage<OilGas> getByLatAndLonInterval(Double latitude, Double longitude, Double distance, RequestPage page) throws IOException, IllegalAccessException;
}
