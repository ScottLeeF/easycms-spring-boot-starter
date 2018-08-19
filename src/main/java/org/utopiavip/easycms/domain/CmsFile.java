package org.utopiavip.easycms.domain;

import org.springframework.data.annotation.Id;

public class CmsFile extends BaseDomain {

    @Id
    private String id;
    private String repositoryId;
    private String folderId;
    private String fileName;
    private String fileType;
    private String contentType;
    private Boolean isStoreGridFS;

    /**
     * 文件状态,0 回收站,1 正常, 2 上传中
     */
    private Integer status;

    /**
     * 文件名后缀
     */
    private String suffix;

    /**
     * 长度(字节)
     */
    private Long size;

    /**
     * 可读size(KB/M/G)
     */
    private String friendlySize;

    /**
     * 文件存储ID
     */
    private String fileStoreId;

    /**
     * 图像宽度
     */
    private Integer imageWidth;

    /**
     * 图像高度
     */
    private Integer imageHeight;

    /**
     * GPS经度
     */
    private String gpsLongitude;

    /**
     * 纬度
     */
    private String gpsLatitude;

    public CmsFile() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Boolean getStoreGridFS() {
        return isStoreGridFS;
    }

    public void setStoreGridFS(Boolean storeGridFS) {
        isStoreGridFS = storeGridFS;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getFriendlySize() {
        return friendlySize;
    }

    public void setFriendlySize(String friendlySize) {
        this.friendlySize = friendlySize;
    }

    public String getFileStoreId() {
        return fileStoreId;
    }

    public void setFileStoreId(String fileStoreId) {
        this.fileStoreId = fileStoreId;
    }

    public Integer getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(Integer imageWidth) {
        this.imageWidth = imageWidth;
    }

    public Integer getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(Integer imageHeight) {
        this.imageHeight = imageHeight;
    }

    public String getGpsLongitude() {
        return gpsLongitude;
    }

    public void setGpsLongitude(String gpsLongitude) {
        this.gpsLongitude = gpsLongitude;
    }

    public String getGpsLatitude() {
        return gpsLatitude;
    }

    public void setGpsLatitude(String gpsLatitude) {
        this.gpsLatitude = gpsLatitude;
    }
}
