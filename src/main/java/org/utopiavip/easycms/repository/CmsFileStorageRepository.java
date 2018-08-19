package org.utopiavip.easycms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.utopiavip.easycms.domain.CmsFileStorage;

public interface CmsFileStorageRepository extends MongoRepository<CmsFileStorage, String> {

}