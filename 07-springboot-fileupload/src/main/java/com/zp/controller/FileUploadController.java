package com.zp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController// @Controller + @ResponseBody
public class FileUploadController {
    @RequestMapping("/fileUploadController")
    public Map<String, Object> fileUpload(MultipartFile filename) throws IOException {
        System.out.println(filename.getOriginalFilename());
        filename.transferTo(new File("./" + filename.getOriginalFilename()));
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "ok");
        return map;
    }

}
