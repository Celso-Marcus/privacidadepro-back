package com.pro.privacidade.core.services;

import com.pro.privacidade.infra.config.FileStorageConfig;
import com.pro.privacidade.core.exceptions.FileNotFoundException;
import com.pro.privacidade.core.exceptions.FileStorageException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
public class FileStorageService {

    private final Path interviewsFileStorageLocation;
    private final Path evidencesFileStorageLocation;

    public FileStorageService(FileStorageConfig fileStorageConfig) {
        this.interviewsFileStorageLocation = Paths.get(fileStorageConfig.getUploadDirInterviews())
                .toAbsolutePath().normalize();
        this.evidencesFileStorageLocation = Paths.get(fileStorageConfig.getUploadDirEvidences())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.interviewsFileStorageLocation);
            Files.createDirectories(this.evidencesFileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException
                    ("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file, String prefix, boolean isEvidence) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String newFileName = isEvidence ?
                prefix.concat(fileName) : prefix.concat(fileName.substring(fileName.lastIndexOf(".")));
        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Path targetLocation = this.evidencesFileStorageLocation.resolve(newFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return newFileName;
        } catch (Exception ex) {
            throw new FileStorageException("Could not store file:" + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName, boolean isEvidence) {
        try {
            Path filePath = this.searchFile(fileName, isEvidence);
            Resource resource = new UrlResource(filePath.toUri());
            if (!resource.exists()) {
                throw new FileNotFoundException("File not found " + fileName);
            }
            return resource;
        } catch (Exception ex) {
            throw new FileNotFoundException("Could not load file:" + fileName + ". Please try again!", ex);
        }
    }

    public void deleteFile(String fileName, boolean isEvidence) {
        try {
            Path filePath = this.searchFile(fileName, isEvidence);
            Files.deleteIfExists(filePath);
        } catch (Exception ex) {
            throw new FileNotFoundException("Could not delete file:" + fileName + ". Please try again!", ex);
        }
    }

    private Path searchFile(String fileName, boolean isEvidence) {
        if (isEvidence) {
            return this.evidencesFileStorageLocation.resolve(fileName).normalize();
        }
        return this.interviewsFileStorageLocation.resolve(fileName).normalize();
    }
}
