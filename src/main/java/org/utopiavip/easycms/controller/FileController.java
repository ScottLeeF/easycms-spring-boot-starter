package org.utopiavip.easycms.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.utopiavip.easycms.config.CmsProperties;
import org.utopiavip.easycms.domain.CmsFile;
import org.utopiavip.easycommon.response.ResponseUtil;
import org.utopiavip.easycms.service.FileService;
import org.utopiavip.easycms.util.FilePayload;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/cms/resources")
public class FileController {

    @Autowired
    private FileService fileService;
    @Autowired
    private CmsProperties cmsProperties;

    @PostMapping
    public Map upload(FilePayload payload) throws IOException {
        CmsFile file = fileService.upload(payload);
        JSONObject data = new JSONObject();
        data.put("url", cmsProperties.getResourceDomain() + "cms/resources/" + file.getId());
        data.put("id", file.getId());
        data.put("fileName", file.getFileName());
        data.put("size", file.getSize());
        data.put("contentType", file.getContentType());
        return ResponseUtil.success(data);
    }

    @GetMapping("{id}")
    public void download(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        fileService.download(id, response);
    }

}
