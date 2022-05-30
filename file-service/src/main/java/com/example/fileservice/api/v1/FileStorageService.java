package com.example.fileservice.api.v1;

import com.example.common.utils.DateUtils;
import com.example.fileservice.config.file.FileStorageProperties;
import com.example.fileservice.dao.entity.File;
import com.example.fileservice.dao.repository.FileRepository;
import com.example.fileservice.exception.FileStorageException;
import com.example.fileservice.exception.MyFileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
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
    // Normalize file name
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
      File upload = fileRepository.save(new File(fileId, path));
      return upload.getId();
    } catch (IOException ex) {
      throw new FileStorageException("Could not store file " + fileName + ". Please try again!",
          ex);
    }
  }

  public Resource loadFileAsResource(String fileId) {
    File file = fileRepository.findById(fileId)
        .orElseThrow(() -> new MyFileNotFoundException("file not exist"));
    String fileName = file.getUrl();

    try {
      java.io.File f = fileStorageLocation.toFile();
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
}
