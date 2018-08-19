package org.utopiavip.easycms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.utopiavip.easycms.domain.CmsFile;

import java.util.List;

public interface CmsFileRepository extends MongoRepository<CmsFile, String> {

    List<CmsFile> findByFolderId(String folderId);

}