package org.utopiavip.easycms.service;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.utopiavip.easycms.domain.CmsFile;
import org.utopiavip.easycms.domain.CmsFileStorage;
import org.utopiavip.easycms.domain.CmsFolder;
import org.utopiavip.easycms.exception.CmsCode;
import org.utopiavip.easycms.repository.CmsFileRepository;
import org.utopiavip.easycms.repository.CmsFileStorageRepository;
import org.utopiavip.easycms.util.FilePayload;
import org.utopiavip.easycms.util.FileStatus;
import org.utopiavip.easycommon.exception.ArgumentException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Optional;

@Service
public class FileService {

    private static final int KB_SIZE = 1024;
    private static final int MB_SIZE = KB_SIZE * 1024;
    private static final int GB_SIZE = MB_SIZE * 1024;
    private static final int _16M = MB_SIZE * 16;
    private static final DecimalFormat df = new DecimalFormat("0.00");//格式化小数

    @Autowired
    private GridFsTemplate gridFsTemplate;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private FolderService folderService;
    @Autowired
    private CmsFileRepository fileRepository;
    @Autowired
    private CmsFileStorageRepository fileStorageRepository;

    public CmsFile upload(FilePayload payload) throws IOException {
        CmsFolder folder = folderService.getFolder(payload.getFolderId());
        String folderId = null;
        String repositoryId;
        if (folder != null) {
            repositoryId = folder.getRepositoryId();
            folderId = folder.getId();
        } else {
            repositoryId = repositoryService.getDefaultRepoId();
        }

        MultipartFile multipartFile = payload.getFile();
        long size = multipartFile.getSize();
        String fileStorageId = null;
        if (size < _16M) {
            CmsFileStorage fileStorage = new CmsFileStorage(multipartFile.getBytes());
            fileStorage = fileStorageRepository.save(fileStorage);
            fileStorageId = fileStorage.getId();
        } else {
            ObjectId objectId = gridFsTemplate.store(multipartFile.getInputStream(), multipartFile.getOriginalFilename());
            fileStorageId = objectId.toString();
        }


        CmsFile file = new CmsFile();
        file.setRepositoryId(repositoryId);
        file.setFolderId(folderId);
        file.setFileName(multipartFile.getOriginalFilename());
        file.setFileStoreId(fileStorageId);
        file.setStatus(FileStatus.NORMAL);
        file.setSize(multipartFile.getSize());
        file.setContentType(multipartFile.getContentType());
        file.setFriendlySize(getFriendlySize(file.getSize()));
        fileRepository.save(file);
        return file;
    }

    public void download(String fileId, HttpServletResponse response) throws IOException {
        Optional<CmsFile> optional = fileRepository.findById(fileId);
        if (optional.isPresent()) {
            CmsFile file = optional.get();

            response.setHeader("Content-type", file.getContentType());
            response.setHeader("Content-Disposition", "inline; filename=\"" + file.getFileName() + "\"");
            ServletOutputStream outputStream = response.getOutputStream();
            if (file.getSize() < _16M) {
                CmsFileStorage fileStorage = fileStorageRepository.findById(file.getFileStoreId()).get();
                outputStream.write(fileStorage.getData());
            } else {
                GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(file.getFileStoreId())));
                GridFsResource resource = new GridFsResource(gridFSFile);
                IOUtils.copy(resource.getInputStream(), response.getOutputStream());
            }
        }
    }

    private static String getFriendlySize(long size) {
        String friendlySize;
        if (size < KB_SIZE) {
            friendlySize = size + "B";
        } else if (size < MB_SIZE) {
            friendlySize = df.format(size / (float) MB_SIZE) + "MB";
        } else if (size < GB_SIZE) {
            friendlySize = df.format(size / (float) MB_SIZE) + "MB";
        } else {
            friendlySize = df.format(size / (float) GB_SIZE) + "GB";
        }
        return friendlySize;
    }
}
