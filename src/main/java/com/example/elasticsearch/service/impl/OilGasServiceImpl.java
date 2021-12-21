package com.example.elasticsearch.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.elasticsearch.entity.EsResponsePage;
import com.example.elasticsearch.entity.OilGas;
import com.example.elasticsearch.entity.RequestPage;
import com.example.elasticsearch.es.OilEsRepository;
import com.example.elasticsearch.mapper.OilGasMapper;
import com.example.elasticsearch.service.OilGasService;
import com.example.elasticsearch.utils.EsUtils;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ChenYu
 * @since 2021-12-17
 */
@Service
public class OilGasServiceImpl extends ServiceImpl<OilGasMapper, OilGas> implements OilGasService {

    @Resource
    private ElasticsearchRestTemplate elasticRestTemplate;

    /**
     * 地理搜索
     *
     * @param latitude  纬度
     * @param longitude 经度
     * @param distance  距离
     */
    @Override
    public EsResponsePage<OilGas> getByLatAndLonInterval(Double latitude, Double longitude, Double distance, RequestPage requestPage) throws IOException, IllegalAccessException {
        PageRequest pageRequest = PageRequest.of(requestPage.getCurrent() - 1, requestPage.getSize());
        Query query = new NativeSearchQueryBuilder()
                .withPageable(pageRequest)
                //指定范围内
                .withQuery(new GeoDistanceQueryBuilder("location")
                        .point(latitude, longitude)
                        .distance(distance, DistanceUnit.KILOMETERS)
                        .geoDistance(GeoDistance.ARC))
                //按照距离排序
                .withSort(SortBuilders.geoDistanceSort("location", latitude, longitude)
                        .unit(DistanceUnit.KILOMETERS)
                        //.geoDistance(GeoDistance.ARC)  // 距离计算方式
                        .order(SortOrder.ASC))
                .build();
        SearchHits<OilGas> searchHits = elasticRestTemplate.search(query, OilGas.class);
        //封装page对象
        List<OilGas> content = searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
        Page<OilGas> resultPage = new PageImpl<>(content, pageRequest, searchHits.getTotalHits());
        // 计算两点距离
        resultPage.getContent().forEach(item -> {
            double calculate = GeoDistance.ARC.calculate(latitude, longitude, item.getGasAddressLatitude(), item.getGasAddressLongitude(), DistanceUnit.KILOMETERS);
            System.out.println(calculate);
        });
        return new EsResponsePage<>(requestPage, resultPage);
    }
}
