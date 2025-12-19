package com.zp.milvusdeeplearning.service;

import io.milvus.client.MilvusClient;
import io.milvus.grpc.DataType;
import io.milvus.grpc.IDs;
import io.milvus.grpc.MutationResult;
import io.milvus.param.IndexType;
import io.milvus.param.MetricType;
import io.milvus.param.R;
import io.milvus.param.RpcStatus;
import io.milvus.param.collection.CollectionSchemaParam;
import io.milvus.param.collection.CreateCollectionParam;
import io.milvus.param.collection.FieldType;
import io.milvus.param.collection.FlushParam;
import io.milvus.param.dml.InsertParam;
import io.milvus.param.index.CreateIndexParam;
import io.milvus.v2.service.collection.request.CreateCollectionReq;
import lombok.extern.slf4j.Slf4j;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2025/3/19 17:51
 * Version : v1.0.0
 * Description: Milvus数据库操作
 */
@Slf4j
@Service
public class MilvusService {

    private final MilvusClient milvusClient;

    public MilvusService(MilvusClient milvusClient) {
        this.milvusClient = milvusClient;
    }

    public void createCollection(String collectionName, String fieldName, String description) {
        // 定义 ID 字段（Int64 类型，作为主键）
        FieldType idField = FieldType.newBuilder()
                .withName("id")
                .withDataType(DataType.Int64)
                .withPrimaryKey(true)
                // 自动生成 ID
                .withAutoID(true)
                .build();
        // 定义向量字段（FloatVector 类型，需指定维度）
        FieldType vectorField = FieldType.newBuilder()
                // 字段名
                .withName(fieldName)
                // 向量类型
                .withDataType(DataType.FloatVector)
                // 设定向量维度 (例如 128)
                .withDimension(1000)
                .build();

        // 创建 Collection 参数
        CollectionSchemaParam schemaParam = CollectionSchemaParam.newBuilder().withFieldTypes(List.of(idField, vectorField)).build();


        CreateCollectionParam param = CreateCollectionParam.newBuilder()
                .withCollectionName(collectionName)
                .withDescription(description)
                .withShardsNum(2)
                .withSchema(schemaParam)
                .build();
        R<RpcStatus> result = milvusClient.createCollection(param);
        log.info(result.toString());
    }

    public void insertData(long id, INDArray feature, String collectionName) {
        List<Long> ids = Collections.singletonList(id);
        float[] floatVector = feature.toFloatVector();

        List<Float> floatList = new ArrayList<>();
        for (float f : floatVector) {
            floatList.add(f);
        }

        List<List<Float>> vectors = Collections.singletonList(floatList);
        List<InsertParam.Field> fields = new ArrayList<>();
        fields.add(new InsertParam.Field("id", ids));
        fields.add(new InsertParam.Field("embedding", vectors));
        InsertParam insertParam = InsertParam.newBuilder()
                .withCollectionName(collectionName)
                .withFields(fields)
                .build();
        milvusClient.insert(insertParam);
    }

    public void insertData(List<Float> featureList, String collectionName, String fieldName) {
        List<List<Float>> vectors = Arrays.asList(featureList);
        InsertParam insertParam = InsertParam.newBuilder().withCollectionName(collectionName)
                .withFields(Collections.singletonList(new InsertParam.Field(fieldName, vectors))).build();
        R<MutationResult> insert = milvusClient.insert(insertParam);
        IDs iDs = insert.getData().getIDs();

        flush(collectionName);
        buildIndex(collectionName);
    }

    public void flush(String collectionName) {
        milvusClient.flush(FlushParam.newBuilder()
                .withCollectionNames(Collections.singletonList(collectionName))
                .withSyncFlush(true)
                .withSyncFlushWaitingInterval(50L)
                .withSyncFlushWaitingTimeout(30L)
                .build());
    }

    public void buildIndex(String collectionName) {
        log.info("buildIndex");
        StopWatch stopWatch = new StopWatch("buildIndex");
        stopWatch.start();
        milvusClient.createIndex(CreateIndexParam.newBuilder()
                .withCollectionName(collectionName)
                .withFieldName("embedding")
                .withIndexType(IndexType.AUTOINDEX)
                .withMetricType(MetricType.L2)
                .withSyncMode(Boolean.TRUE)
                .withSyncWaitingInterval(500L)
                .withSyncWaitingTimeout(30L)
                .build());
        stopWatch.stop();
        log.info("Succeed in {}", stopWatch.prettyPrint());
    }

}
