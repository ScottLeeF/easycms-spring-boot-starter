package org.utopiavip.easycms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utopiavip.easycms.domain.CmsRepository;
import org.utopiavip.easycms.exception.CmsCode;
import org.utopiavip.easycms.repository.CmsRepositoryRepository;
import org.utopiavip.easycommon.args.BeanParser;
import org.utopiavip.easycommon.exception.ArgumentException;
import org.utopiavip.easycommon.text.StringUtil;

@Service
public class RepositoryService {

    private static final String DEFAULT_REPO = "DEFAULT";
    private String DEFAULT_REPO_ID = null;

    @Autowired
    private CmsRepositoryRepository repositoryRepository;

    public synchronized CmsRepository add(CmsRepository repository) {
        BeanParser.validate(repository);
        CmsRepository dummy = repositoryRepository.findByRepoCode(repository.getRepoCode());
        if (dummy != null) {
            throw new ArgumentException(CmsCode.CMS_REPO_EXISTS);
        }
        return repositoryRepository.save(repository);
    }

    public String getDefaultRepoId() {
        if (DEFAULT_REPO_ID == null) {
            CmsRepository repository = repositoryRepository.findByRepoCode(DEFAULT_REPO);
            if (repository == null) {
                repository = add(new CmsRepository(DEFAULT_REPO, DEFAULT_REPO));
            }
            DEFAULT_REPO_ID = repository.getId();
        }

        return DEFAULT_REPO_ID;
    }

    public CmsRepository getRepository(String repositoryId) {
        if (!StringUtil.isBlank(repositoryId)) {
            if (!repositoryRepository.findById(repositoryId).isPresent()) {
                throw new IllegalArgumentException("Invalid repository id:" + repositoryId);
            }
        }
        return null;
    }
}
