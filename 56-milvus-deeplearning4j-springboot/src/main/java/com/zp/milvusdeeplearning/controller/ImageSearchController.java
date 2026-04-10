package com.zp.milvusdeeplearning.controller;

import com.google.protobuf.MessageOrBuilder;
import com.zp.milvusdeeplearning.service.FeatureExtractor;
import com.zp.milvusdeeplearning.service.ImageSearcherService;
import com.zp.milvusdeeplearning.service.MilvusService;
import com.zp.milvusdeeplearning.utils.Convert;
import io.milvus.grpc.FieldData;
import io.milvus.grpc.IDs;
import jakarta.annotation.Resource;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Author : zhengpanone
 * Date : 2025/3/21 13:25
 * Version : v1.0.0
 * Description:
 */
@RestController
@RequestMapping("/api/image")
public class ImageSearchController {

    @Resource
    private FeatureExtractor featureExtractor;

    @Resource
    private MilvusService milvusService;
    @Resource
    private ImageSearcherService imageSearcherService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile imageFile) {
        INDArray indArray = null;
        try {
            File file = new File(System.getProperty("java.io.tmpdir") + "/" + imageFile.getOriginalFilename());
            imageFile.transferTo(file);
            indArray = featureExtractor.extractFeatures(file);
            List<Float> featureList = Convert.convertToFloatArray(indArray);
//            List<List<Float>> vectors = Arrays.asList(featureList);
//            List<Long> objects = imageSearcherService.searchImage(featureList, "image_collection", "embedding");
//            if(CollectionUtils.isEmpty(objects)){
//
//            }
            // 将图像特征向量存储到 Milvus
            milvusService.insertData(featureList, "image_collection", "embedding");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("特征提取失败：" + e.getMessage());
        }
        return ResponseEntity.ok("Image uploaded and features stored");
    }

    @PostMapping("/search")
    public ResponseEntity<List<Long>> searchImage(@RequestParam("file") MultipartFile imageFile) throws IOException {
        INDArray indArray = null;
        File file = new File(System.getProperty("java.io.tmpdir") + "/" + imageFile.getOriginalFilename());
        imageFile.transferTo(file);
        indArray = featureExtractor.extractFeatures(file);
        float[] floatVector = indArray.toFloatVector();

        List<Float> featureList = Convert.convertToFloatArray(indArray);
        List<List<Float>> vectors = Arrays.asList(featureList);
        // 在 Milvus 中进行图像查询
        List<Long> similarImageIds = imageSearcherService.searchImage(featureList, "image_collection", "embedding");
        if (Objects.isNull(similarImageIds)) {
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
        }
        return ResponseEntity.ok(similarImageIds);
    }
}
