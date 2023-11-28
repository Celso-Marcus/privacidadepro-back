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

    private final Path fileStorageLocation;

    public FileStorageService(FileStorageConfig fileStorageConfig) {
        this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeEvidenceFile(MultipartFile file, String category) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            if (fileName.contains("..")) {
                //Essa verificação garante que o nome do arquivo não contém caminhos de pasta inválidos
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            // Define o formato desejado
            String dateNow = LocalDateTime.now().atZone(ZoneId.of("America/Sao_Paulo"))
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
                    .replace(" ", "_").replace("/","_")
                    .replace(":","_");

            String newFileName =  dateNow + "_" + category + "_" + fileName;
//            String newFileName =  new Date() + "_" + category + "_" + fileName;

            //Para salvar na nuvem ou no banco de dados é necessário mudar as duas linhas abaixo
            Path targetLocation = this.fileStorageLocation.resolve(newFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return newFileName;
        }
        catch (Exception ex) {
            throw new FileStorageException("Could not store file:" + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try{
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (!resource.exists()) {
                throw new FileNotFoundException("File not found " + fileName);
            }
            return resource;
        }
        catch (Exception ex) {
            throw new FileNotFoundException("Could not load file:" + fileName + ". Please try again!", ex);
        }
    }

    public void deleteFile(String fileName) {
        try{
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Files.deleteIfExists(filePath);
        }
        catch (Exception ex) {
            throw new FileNotFoundException("Could not delete file:" + fileName + ". Please try again!", ex);
        }
    }
}
