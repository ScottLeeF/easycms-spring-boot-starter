package org.utopiavip.easycms.domain;

import java.util.Date;

public abstract class BaseDomain {

    protected Date creationDate;
    protected Date lastUpdateDate;

    public BaseDomain() {
        this.creationDate = new Date();
        this.lastUpdateDate = new Date();
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
