package org.utopiavip.easycms.domain;

import org.springframework.data.annotation.Id;
import org.utopiavip.easycommon.args.annotation.NotNull;

public class CmsRepository extends BaseDomain {

    @Id
    private String id;

    @NotNull
    private String repoCode;

    @NotNull
    private String repoName;

    public CmsRepository() {
    }

    public CmsRepository(String repoCode, String repoName) {
        this.repoCode = repoCode;
        this.repoName = repoName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRepoCode() {
        return repoCode;
    }

    public void setRepoCode(String repoCode) {
        this.repoCode = repoCode;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }
}
