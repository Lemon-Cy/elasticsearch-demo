package com.example.elasticsearch.controller;

import com.example.elasticsearch.entity.EsResponsePage;
import com.example.elasticsearch.entity.MedicineInfo;
import com.example.elasticsearch.entity.RequestPage;
import com.example.elasticsearch.es.MedicineInfoRepository;
import com.example.elasticsearch.service.MedicineInfoService;
import com.example.elasticsearch.utils.EsUtils;
import com.example.elasticsearch.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 药物信息控制器
 * </p>
 *
 * @author ChenYu
 * @since 2021/12/17 11:04
 */
@RestController
@RequestMapping("es/medicine")
@Api(tags = "药物信息控制器")
@Slf4j
public class MedicineController {

    @Resource
    private MedicineInfoService medicineInfoService;
    @Resource
    private ElasticsearchRestTemplate elasticRestTemplate;
    @Resource
    private MedicineInfoRepository medicineInfoRepository;

    @PostMapping("init")
    @ApiOperation("初始化数据")
    public Result<Object> init() {
        long startTime = System.currentTimeMillis();
        List<MedicineInfo> medicineInfos = medicineInfoService.list();
        int count = 0;
        List<IndexQuery> queryList = new ArrayList<>();
        for (MedicineInfo medicineInfo : medicineInfos) {
            IndexQuery indexQuery = new IndexQuery();
            indexQuery.setId(medicineInfo.getId().toString());
            indexQuery.setObject(medicineInfo);
            queryList.add(indexQuery);
            if (count % 1000 == 0) {
                elasticRestTemplate.bulkIndex(queryList, MedicineInfo.class);
                queryList.clear();
            }
            count++;
        }
        if (medicineInfos.size() > 0) {
            elasticRestTemplate.bulkIndex(queryList, MedicineInfo.class);
        }
        log.info(" use time: " + (System.currentTimeMillis() - startTime) / 1000 + "s");
        return Result.success();
    }

    @GetMapping("getByKeyword")
    @ApiOperation("根据关键字查询")
    @ApiImplicitParam(name = "keyword", value = "关键字", example = "板蓝根")
    public Result<EsResponsePage<MedicineInfo>> getByKeyWord(@NonNull String keyword, RequestPage requestPage) throws IllegalAccessException {
        HighlightBuilder highlightBuilder = EsUtils.getHighlightBuilder();
        //配置标题高亮显示
        highlightBuilder.field("gname");
        highlightBuilder.field("medicineComponent");
        PageRequest pageRequest = PageRequest.of(requestPage.getCurrent() - 1, requestPage.getSize());
        Query query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.queryStringQuery(keyword)
                        .field("gname", 10)
                        .field("medicineComponent", 8))
                .withPageable(pageRequest)
                .withSort(SortBuilders.scoreSort())
                .withMinScore(5)
                .withSort(SortBuilders.fieldSort("id").order(SortOrder.ASC))
                .withHighlightBuilder(highlightBuilder)
                .build();
        SearchHits<MedicineInfo> searchHits = elasticRestTemplate.search(query, MedicineInfo.class);
        //封装page对象
        List<MedicineInfo> content = searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
        Page<MedicineInfo> resultPage = new PageImpl<>(content, pageRequest, searchHits.getTotalHits());
        //结果集高亮
        EsUtils.highlightResponse(searchHits);
        return Result.success(new EsResponsePage<>(requestPage, resultPage));
    }

    @GetMapping("getById")
    @ApiOperation("根据ID查询数据")
    public Result<MedicineInfo> getById(@NonNull Integer id) {
        return Result.success(medicineInfoRepository.findById(id).orElse(null));
    }
}
