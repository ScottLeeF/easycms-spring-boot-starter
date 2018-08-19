package org.utopiavip.easycms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.utopiavip.easycommon.response.ResponseUtil;
import org.utopiavip.easycms.domain.CmsFolder;
import org.utopiavip.easycms.service.FolderService;

import java.util.Map;

@RestController
@RequestMapping("/cms/folders")
public class FolderController {

    @Autowired
    private FolderService folderService;

    @PostMapping
    public Map add(@RequestBody CmsFolder folder) {
        return ResponseUtil.success(folderService.add(folder));
    }
}
