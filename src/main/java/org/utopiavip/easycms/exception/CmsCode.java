package org.utopiavip.easycms.exception;

import org.utopiavip.easycommon.exception.Code;

public enum CmsCode implements Code {

    CMS_REPO_EXISTS("CMS1001", "Repository:%s already exists"),
    CMS_FILE_NULL("CMS1002", "file not null when upload resource"),
    ;

    private String code;
    private String message;

    CmsCode(String code) {
        this.code = code;
    }

    CmsCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
