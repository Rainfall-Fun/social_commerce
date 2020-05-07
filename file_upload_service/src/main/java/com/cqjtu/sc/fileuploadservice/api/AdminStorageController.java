package com.cqjtu.sc.fileuploadservice.api;


import com.cqjtu.sc.fileuploadservice.util.ResponseUtil;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class AdminStorageController {
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object create(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID()+file.getOriginalFilename();
        file.transferTo(new File("F:\\1111\\"+fileName));
        Map<String,String> data=new HashMap<>();
        data.put("url",""+fileName);//后面需要改成图片地址
        data.put("fileName",fileName);
        return ResponseUtil.ok(data);
    }
}
