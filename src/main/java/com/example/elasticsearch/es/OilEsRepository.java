package com.example.elasticsearch.es;

import com.example.elasticsearch.entity.OilGas;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
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
public interface OilEsRepository extends ElasticsearchRepository<OilGas, Integer> {
}
