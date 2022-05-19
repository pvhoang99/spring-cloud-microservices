package com.example.catalog.file;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadFileResponse {

  private String fileId;
  private String fileDownloadUri;
  private String fileType;
  private long size;
}
