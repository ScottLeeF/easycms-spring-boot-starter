package org.utopiavip.easycms.domain;

import org.springframework.data.annotation.Id;

/**
 * 16M以下对象存储
 */
public class CmsFileStorage extends BaseDomain{

    @Id
    private String id;
    private byte[] data;

    public CmsFileStorage() {}

    public CmsFileStorage(byte[] data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
