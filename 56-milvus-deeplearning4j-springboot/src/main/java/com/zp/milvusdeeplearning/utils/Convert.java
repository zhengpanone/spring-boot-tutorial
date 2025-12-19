package com.zp.milvusdeeplearning.utils;

import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2025/3/21 13:30
 * Version : v1.0.0
 * Description:
 */
public class Convert {
    /**
     * 将 INDArray 转换为 List<Float>，以便存储到 Milvus
     *
     * @param array
     * @return
     */
    public static List<Float> convertToFloatArray(INDArray array) {
        List<Float> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            list.add(array.getFloat(i));
        }
        return list;
    }
}
