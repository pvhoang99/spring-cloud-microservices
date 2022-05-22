package com.example.catalog.dao.api.v1;

import com.example.catalog.dao.entity.FileEntity;
import com.example.catalog.dao.repository.FileRepository;
import com.example.catalog.exception.FileStorageException;
import com.example.catalog.file.FileStorageProperties;
import com.example.catalog.utils.DateUtils;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class FileStorageService {

  private final Path fileStorageLocation;
  private final FileRepository fileRepository;

  @Autowired
  public FileStorageService(FileStorageProperties fileStorageProperties,
      FileRepository fileRepository) {
    this.fileRepository = fileRepository;
    this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
        .toAbsolutePath().normalize();

    try {
      if (!Files.exists(fileStorageLocation)) {
        Files.createDirectories(this.fileStorageLocation);
      }
    } catch (Exception ex) {
      throw new FileStorageException(
          "Could not create the directory where the uploaded files will be stored.", ex);
    }
  }

  public String storeFile(MultipartFile file) {
    String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
    try {
      if (fileName.contains("..")) {
        throw new FileStorageException(
            "Sorry! Filename contains invalid path sequence " + fileName);
      }
      String fileId = UUID.randomUUID().toString();
      Path targetLocation = this.fileStorageLocation
          .resolve(DateUtils.formatDate(DateUtils.getToday()))
          .resolve(fileId)
          .resolve(fileName);
      Files.createDirectories(targetLocation.getParent());
      Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

      String path = fileStorageLocation.relativize(targetLocation).toString();
      FileEntity upload = fileRepository.save(new FileEntity(fileId, path));
      return upload.getFileId();
    } catch (IOException ex) {
      throw new FileStorageException("Could not store file " + fileName + ". Please try again!",
          ex);
    }
  }

  public Resource loadFileAsResource(String fileId) {
    FileEntity fileEntity = this.findById(fileId);
    String fileName = fileEntity.getUrl();

    try {
      File f = fileStorageLocation.toFile();
      if (!f.exists()) {
        throw new RuntimeException();
      }

      Path filePath = this.fileStorageLocation.resolve(fileName);
      Resource resource = new UrlResource(filePath.toUri());
      if (resource.exists()) {
        return resource;
      } else {
        throw new RuntimeException("File not found " + fileName);
      }
    } catch (MalformedURLException ex) {
      throw new RuntimeException("File not found " + fileName);
    }
  }

  public FileEntity findById(String fileId) {
    return fileRepository.findByFileId(fileId)
        .orElseThrow(() -> new RuntimeException("Cannot find file with id: " + fileId));
  }
}
