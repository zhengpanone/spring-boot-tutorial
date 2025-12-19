package com.zp.milvusdeeplearning.service;

import org.datavec.image.loader.NativeImageLoader;
import org.deeplearning4j.nn.graph.ComputationGraph;
import org.deeplearning4j.zoo.ZooModel;
import org.deeplearning4j.zoo.model.ResNet50;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.api.preprocessor.ImagePreProcessingScaler;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * Author : zhengpanone
 * Date : 2025/3/19 17:48
 * Version : v1.0.0
 * Description: 特征抽取
 */
@Service
public class FeatureExtractor {

    private final ComputationGraph computationGraph;

    public FeatureExtractor() throws IOException {
        ZooModel<ComputationGraph> zooModel = ResNet50.builder().build();
        try {
            computationGraph = (ComputationGraph) zooModel.initPretrained();
        } catch (Exception e) {
            throw new IOException("Failed to initialize the pre-trained model: " + e.getMessage(), e);
        }
    }

    public INDArray extractFeatures(File imageFile) throws IOException {
        // 1. 加载图像: 使用 NativeImageLoader 将图像加载为一个 INDArray，并将图像的大小调整为 224x224 像素，通道数为 3（即 RGB 图像）。
        NativeImageLoader loader = new NativeImageLoader(224, 224, 3);
        INDArray image = loader.asMatrix(imageFile);
        // 2. 预处理图像: 使用 ImagePreProcessingScaler 将图像数据缩放到 [0, 1] 的范围，以便模型可以更好地处理。
        ImagePreProcessingScaler scaler = new ImagePreProcessingScaler(0, 1);
        scaler.transform(image);
        // 3. 提取特征: 使用计算图计算图像的输出，并返回一个包含特征向量的 INDArray。
        return computationGraph.outputSingle(image);

    }
}
