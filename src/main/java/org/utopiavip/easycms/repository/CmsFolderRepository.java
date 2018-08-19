package org.utopiavip.easycms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.utopiavip.easycms.domain.CmsFolder;

import java.util.List;

public interface CmsFolderRepository extends MongoRepository<CmsFolder, String> {

    List<CmsFolder> findByParentFolderId(String parentFolderId);

}