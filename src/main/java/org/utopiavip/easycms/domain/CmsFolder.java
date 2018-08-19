package org.utopiavip.easycms.domain;

import org.springframework.data.annotation.Id;
import org.utopiavip.easycommon.args.annotation.NotNull;

public class CmsFolder extends BaseDomain {

    @Id
    private String id;

    @NotNull
    private String folderName;

    @NotNull
    private String repositoryId;
    private String parentFolderId;

    public CmsFolder() {
    }

    public CmsFolder(String folderName, String parentFolderId) {
        this.folderName = folderName;
        this.parentFolderId = parentFolderId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getParentFolderId() {
        return parentFolderId;
    }

    public void setParentFolderId(String parentFolderId) {
        this.parentFolderId = parentFolderId;
    }
}
