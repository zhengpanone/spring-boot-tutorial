package com.zp.controller;

import com.zp.pojo.City;
import com.zp.service.CityService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

/**
 * Author : zhengpanone
 * Date : 2025/3/18 12:53
 * Version : v1.0.0
 * Description:
 */
@RestController
public class FileDownloadController {

    //@Value("file:///d:/software/OllamaSetup.exe")
    @Value("file:/Users/mac/Downloads/K8S配套资料.zip")
    private Resource file;

    @jakarta.annotation.Resource
    private CityService cityService;

    /**
     * 直接从服务器流式传输大文件到客户端，避免内存溢出。
     * spring:
     * mvc:
     * async:
     * request-timeout: -1
     *
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping("/download")
    public ResponseEntity<StreamingResponseBody> downloadFile() throws UnsupportedEncodingException {
        String fileName = file.getFilename();
        StreamingResponseBody responseBody = outputStream -> {
            try (InputStream inputStream = file.getInputStream()) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                    // 确保数据及时发送
                    outputStream.flush();
                }
            } catch (IOException ex) {
                throw new RuntimeException("文件下载失败", ex);
            }
        };

        return ResponseEntity.ok()
                .header("Content-Type", "application/octet-stream")
                .header("Content-Disposition", String.format("attachment; filename=%s", URLEncoder.encode(fileName, "UTF-8")))
                .body(responseBody);
    }

    /**
     * 推送实时生成的动态数据（如日志、股票价格、传感器数据）
     * @return
     */
    @GetMapping("/stream/logs")
    public ResponseEntity<StreamingResponseBody> streamLogs(){
        StreamingResponseBody responseBody = outputStream -> {
            for(int i = 0; i < 20; i++){
                String log = "日志数据"+i+"-"+ LocalDateTime.now()+"\n";
                outputStream.write(log.getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
                // 模拟延迟
                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        return ResponseEntity.ok()
                .header("Content-Type", "text/plain; charset=utf-8")
                //.header("Content-Disposition", "attachment; filename=logs.txt")
                .body(responseBody);
    }

    @GetMapping("/export/users")
    public ResponseEntity<StreamingResponseBody> exportCsv(){
        StreamingResponseBody responseBody = outputStream -> {
            // 写入CSV头部
            outputStream.write("Id,Name,Email\n".getBytes());
            for (int i = 1; i <= 10; i++){
                //List<City> allCity = cityService.findAllCity();
                outputStream.write(String.format("%d,City%d,city%d@example.com\n", i, i, i).getBytes(StandardCharsets.UTF_8));
            }
            // 分页写入后立即刷新
            outputStream.flush();
        };
        return ResponseEntity.ok()
                .header("Content-Type", "text/csv; charset=utf-8")
                .header("Content-Disposition", "attachment; filename=data.csv")
                .body(responseBody);
    }
}
