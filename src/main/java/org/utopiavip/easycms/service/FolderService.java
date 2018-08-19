package org.utopiavip.easycms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utopiavip.easycms.domain.CmsFolder;
import org.utopiavip.easycms.repository.CmsFolderRepository;
import org.utopiavip.easycommon.args.BeanParser;
import org.utopiavip.easycommon.text.StringUtil;

import java.util.Optional;

@Service
public class FolderService {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private CmsFolderRepository folderRepository;

    public CmsFolder add(CmsFolder folder) {
        BeanParser.validate(folder);
        String repositoryId = folder.getRepositoryId();
        if (!StringUtil.isBlank(folder.getParentFolderId())) {
            CmsFolder cmsFolder = getFolder(folder.getParentFolderId());
            repositoryId = cmsFolder.getRepositoryId();

        } else if (!StringUtil.isBlank(repositoryId)) {
            repositoryService.getRepository(repositoryId);
        }

        if (repositoryId == null) {
            repositoryId = repositoryService.getDefaultRepoId();
        }
        folder.setRepositoryId(repositoryId);
        return folderRepository.save(folder);
    }

    public CmsFolder getFolder(String folderId) {
        if (!StringUtil.isBlank(folderId)) {
            Optional<CmsFolder> optional = folderRepository.findById(folderId);
            if (!optional.isPresent()) {
                throw new IllegalArgumentException("Invalid parent folder id:" + folderId);
            }
            return optional.get();
        }
        return null;
    }

}
