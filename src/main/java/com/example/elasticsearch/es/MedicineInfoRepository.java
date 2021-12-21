package com.example.elasticsearch.es;

import com.example.elasticsearch.entity.MedicineInfo;
import com.example.elasticsearch.entity.OilGas;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *
 * </p>
 *
 * @author ChenYu
 * @since 2021/12/17 17:15
 */
@Repository
public interface MedicineInfoRepository extends ElasticsearchRepository<MedicineInfo, Integer> {

}
