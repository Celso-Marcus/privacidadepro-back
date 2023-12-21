package com.pro.privacidade.infra.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
@ConfigurationProperties(prefix = "file")
public class FileStorageConfig {

    @Value("${file.upload-dir-evidences}")
    private String uploadDirEvidences;

    @Value("${file.upload-dir-interviews}")
    private String uploadDirInterviews;

    public String getUploadDirEvidences() {
        return uploadDirEvidences;
    }

    public void setUploadDirEvidences(String uploadDir) {
        this.uploadDirEvidences = uploadDir;
    }

    public String getUploadDirInterviews() {
        return uploadDirInterviews;
    }

    public void setUploadDirInterviews(String uploadDir) {
        this.uploadDirInterviews = uploadDir;
    }
}
