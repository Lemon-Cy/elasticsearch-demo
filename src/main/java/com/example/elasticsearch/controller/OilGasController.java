package com.example.elasticsearch.controller;

import com.example.elasticsearch.entity.EsResponsePage;
import com.example.elasticsearch.entity.OilGas;
import com.example.elasticsearch.entity.RequestPage;
import com.example.elasticsearch.es.OilEsRepository;
import com.example.elasticsearch.service.OilGasService;
import com.example.elasticsearch.utils.EsUtils;
import com.example.elasticsearch.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 油站控制器
 * </p>
 *
 * @author ChenYu
 * @since 2021/12/20 11:41
 */
@RestController
@RequestMapping("es/oil")
@Api(tags = "油站控制器")
@Slf4j
public class OilGasController {

    @Resource
    private OilGasService oilGasService;
    @Resource
    private ElasticsearchRestTemplate elasticRestTemplate;
    @Resource
    private OilEsRepository oilEsRepository;

    @PostMapping("init")
    @ApiOperation("初始化数据")
    public Result<Object> init() {
        long startTime = System.currentTimeMillis();
        List<OilGas> oilGases = oilGasService.list();
        for (OilGas oilGas : oilGases) {
            oilGas.setLocation(oilGas.getGasAddressLatitude() + "," + oilGas.getGasAddressLongitude());
            elasticRestTemplate.save(oilGas);
        }
        log.info(" use time: " + (System.currentTimeMillis() - startTime) / 1000 + "s");
        return Result.success();
    }

    @GetMapping("getByLatAndLonInterval")
    @ApiOperation("根据经纬度区间查询油站")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "latitude", value = "维度", required = true, example = "30"),
            @ApiImplicitParam(name = "longitude", value = "经度", required = true, example = "106"),
            @ApiImplicitParam(name = "distance", value = "距离（km）", required = true, example = "50")
    })
    public Result getByLatAndLonInterval(Double latitude, Double longitude, Double distance, RequestPage requestPage) throws IOException, IllegalAccessException {
        return Result.success(oilGasService.getByLatAndLonInterval(latitude, longitude, distance, requestPage));
    }

    @GetMapping("getByKeyword")
    @ApiOperation("根据关键字查询")
    public Result<EsResponsePage<OilGas>> getByKeyWord(@NonNull String keyword, RequestPage requestPage) throws IllegalAccessException {
        HighlightBuilder highlightBuilder = EsUtils.getHighlightBuilder();
        highlightBuilder.field("gasName");
        highlightBuilder.field("gasAddress");
        highlightBuilder.field("provinceName");
        PageRequest pageRequest = PageRequest.of(requestPage.getCurrent() - 1, requestPage.getSize());
        Query query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery(keyword, "gasName", "gasAddress", "provinceName"))
                .withPageable(pageRequest)
                .withSort(SortBuilders.scoreSort())
                .withMinScore(8)
                .withSort(SortBuilders.fieldSort("addTime").order(SortOrder.DESC))
                .withHighlightBuilder(highlightBuilder)
                .build();
        SearchHits<OilGas> searchHits = elasticRestTemplate.search(query, OilGas.class);
        //封装page对象
        List<OilGas> content = searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
        Page<OilGas> resultPage = new PageImpl<>(content, pageRequest, searchHits.getTotalHits());
        EsUtils.highlightResponse(searchHits);
        return Result.success(new EsResponsePage<>(requestPage, resultPage));
    }

    @GetMapping("getById")
    @ApiOperation("根据id查询")
    public Result<OilGas> findById(@NonNull Integer id) {
        return Result.success(oilEsRepository.findById(id).orElse(null));
    }
}
