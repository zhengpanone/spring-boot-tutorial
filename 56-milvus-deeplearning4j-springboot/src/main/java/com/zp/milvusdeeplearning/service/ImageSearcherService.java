package com.zp.milvusdeeplearning.service;

import io.milvus.client.MilvusClient;
import io.milvus.grpc.FieldData;
import io.milvus.grpc.IDs;
import io.milvus.grpc.SearchResultData;
import io.milvus.grpc.SearchResults;
import io.milvus.param.MetricType;
import io.milvus.param.R;
import io.milvus.param.collection.LoadCollectionParam;
import io.milvus.param.dml.SearchParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2025/3/19 17:53
 * Version : v1.0.0
 * Description: 图片搜索功能
 */
@Service
@Slf4j
public class ImageSearcherService {

    private final MilvusClient milvusClient;

    private final MilvusService milvusService;

    public ImageSearcherService(MilvusClient milvusClient, MilvusService milvusService) {
        this.milvusClient = milvusClient;
        this.milvusService = milvusService;
    }

    public List<Long> searchImage(List<Float> featureList, String collectionName, String fieldName) {
        // 1. 特征转换: 将 INDArray 转换为 float[] 数组，然后将其转换为 List<Float>。这是因为 Milvus 需要特定格式的向量输入。
        if (featureList == null || featureList.isEmpty()) {
            log.warn("Feature list is empty, returning empty result.");
            return Collections.emptyList();
        }
        List<List<Float>> vectors = Collections.singletonList(featureList);
        // 2.构建搜索参数: 创建一个 SearchParam 对象，指定要搜索的集合名称、度量类型（例如 L2 距离）、返回的最相似的前 K 个结果、向量字段名称以及搜索的向量数据。
        SearchParam searchParam = SearchParam.newBuilder()
                .withCollectionName(collectionName)
                .withMetricType(MetricType.L2)
                .withTopK(5)
                .withVectorFieldName(fieldName)
                .withFloatVectors(vectors)
                .build();
        milvusService.load(collectionName);
        // 3. 执行搜索: 使用 milvusClient 的 search 方法执行搜索，并将结果存储在 searchResults 中。
        R<SearchResults> search = milvusClient.search(searchParam);
        List<Long> arrayList = new ArrayList<>();
        if (search.getStatus().equals(0)) {
            SearchResults searchResults = search.getData();
            log.info("{}", searchResults);
            SearchResultData results = searchResults.getResults();
            List<Float> scoresList = results.getScoresList();
            for (int i = 0; i < scoresList.size(); i++) {
                if (scoresList.get(i) < 0.01) {
                    long data = results.getIds().getIntId().getData(i);
                    arrayList.add(data);
                }
            }
        }
        return arrayList;
    }
}
