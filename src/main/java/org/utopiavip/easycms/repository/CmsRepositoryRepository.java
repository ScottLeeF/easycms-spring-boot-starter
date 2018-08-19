package org.utopiavip.easycms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.utopiavip.easycms.domain.CmsRepository;

public interface CmsRepositoryRepository extends MongoRepository<CmsRepository, String> {

    CmsRepository findByRepoCode(String repoCode);

}