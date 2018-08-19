package org.utopiavip.easycms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.utopiavip.easycommon.response.ResponseUtil;
import org.utopiavip.easycms.domain.CmsRepository;
import org.utopiavip.easycms.service.RepositoryService;

import java.util.Map;

@RestController
@RequestMapping("/cms/repositories")
public class RepositoryController {

    @Autowired
    private RepositoryService repositoryService;

    @PostMapping
    public Map add(@RequestBody CmsRepository repository) {
        return ResponseUtil.success(repositoryService.add(repository));
    }

}
