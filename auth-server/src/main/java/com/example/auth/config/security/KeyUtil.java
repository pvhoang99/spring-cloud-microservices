package com.example.auth.config.security;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.annotation.PostConstruct;
import lombok.Getter;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

@Component
@Getter
public class KeyUtil {

  private PrivateKey privateKey;
  private PublicKey publicKey;

  @PostConstruct
  private void initKey() {
    if (privateKey == null || publicKey == null) {
      try {

        String publicKeyString = FileUtils.getFileContent("publicKey");
        String privateKeyString = FileUtils.getFileContent("privateKey");

        byte[] publicKeyByte = java.util.Base64.getDecoder().decode(publicKeyString.getBytes());
        byte[] privateKeyByte = java.util.Base64.getDecoder().decode(privateKeyString.getBytes());

        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyByte);
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyByte);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        publicKey = kf.generatePublic(publicKeySpec);
        privateKey = kf.generatePrivate(privateKeySpec);
      } catch (Exception e) {
        e.printStackTrace();
      }

    }
  }

  public static class FileUtils {

    public static String getFileContent(String fileName) {
      String result = "";
      ClassLoader classLoader = FileUtils.class.getClassLoader();
      try {
        result = IOUtils.toString(classLoader.getResourceAsStream(fileName), "UTF-8");
      } catch (Exception e) {
        e.printStackTrace();
      }
      return result;
    }
  }

}
