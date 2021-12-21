package com.example.elasticsearch.utils;

import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * <p>
 * ES相关工具类
 * </p>
 *
 * @author ChenYu
 * @since 2021/12/21 13:35
 */
public class EsUtils {

    /**
     * 结果集高亮显示
     * @param searchHits 命中结果集
     */
    public static <T> void highlightResponse(SearchHits<T> searchHits) throws IllegalAccessException {
        for (SearchHit hit : searchHits) {
            //获取当前命中的对象的所有高亮字段
            Map<String, List<String>> highlightFields = hit.getHighlightFields();
            Field[] fields = hit.getContent().getClass().getDeclaredFields();
            for (Field field : fields) {
                //如果对象中的属性存在高亮结果，则替换掉
                if (highlightFields.containsKey(field.getName())) {
                    //将私有属性设置为可访问状态
                    field.setAccessible(true);
                    StringJoiner joiner = new StringJoiner("");
                    highlightFields.get(field.getName()).forEach(joiner::add);
                    //相当于set方法
                    field.set(hit.getContent(), joiner.toString());
                }
            }
        }
    }

    /**
     * 获取基础的高亮配置Builder
     * @return 基础的高亮配置Builder
     */
    public static HighlightBuilder getHighlightBuilder(){
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<font color='red'>"); //高亮设置
        highlightBuilder.postTags("</font>");
        //下面这两项,如果你要高亮如文字内容等有很多字的字段,必须配置,不然会导致高亮不全,文章内容缺失等
        highlightBuilder.fragmentSize(10000); //最大高亮分片数
        highlightBuilder.numOfFragments(0); //从第一个分片获取高亮片段
        return highlightBuilder;
    }
}
