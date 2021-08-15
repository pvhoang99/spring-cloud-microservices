package com.amran.clinic.management.controller;

import com.amran.clinic.management.dto.LoginDTO;
import com.amran.clinic.management.dto.LoginResponseDTO;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import org.springframework.web.client.RestTemplate;

/**
 * @Author : Amran Hosssain on 9/1/2020
 */
@RestController
public class ClinicManagementController implements Serializable {

  @Autowired
  @Qualifier("restTemplate")
  private RestTemplate restTemplate;

  @Autowired
  private HttpServletRequest request;

  @Autowired
  @Qualifier("loadBalancedOauth2RestTemplate")
  private OAuth2RestTemplate oAuth2RestTemplate3;
//  @Autowired
//  @Qualifier("customOauth2RestTemplate")  private OAuth2RestTemplate oAuth2RestTemplate;

//  @Autowired
//  @Qualifier("oauth2RestTemplate1")
//  public OAuth2RestTemplate oAuth2RestTemplate2;

//  @Autowired
//  @Qualifier("messagingClientPasswordRestTemplate")
//  private OAuth2RestTemplate messagingClientPasswordRestTemplate;
//
//  @Autowired
//  @Qualifier("messagingClientClientCredsRestTemplate")
//  private OAuth2RestTemplate messagingClientClientCredsRestTemplate;
//
//  @Autowired
//  @Qualifier("messagingClientAuthCodeRestTemplate")
//  private OAuth2RestTemplate messagingClientAuthCodeRestTemplate;

  public ClinicManagementController() {
  }

  @Value("${server.url:Unable to connect to config server}")
  private String url;

  @RefreshScope
  @GetMapping(path = "/clinic-test", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> testMethod() {
    return new ResponseEntity<>("hello", HttpStatus.OK);
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDTO> response(@RequestBody LoginDTO loginDTO) {

    LoginResponseDTO loginResponseDTO = restTemplate.postForObject(
        "http://ehealth-api-gateway/oauth/token?grant_type=password&username=hoangpv1&password=12345678&client_id=kidclient&client_secret=admin",
        null,
        LoginResponseDTO.class);
    return ResponseEntity.ok(loginResponseDTO);

  }

  @PostMapping("/call-services")
  public ResponseEntity<String> responseEntity() {
    String authorization = request.getHeader("Authorization");
    String url = "http://patient-management-service/hello";
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.add("Authorization", authorization);
    HttpEntity<?> entity = new HttpEntity<>(headers);
    String s = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
    return ResponseEntity.ok(s);
  }


  @PostMapping("/call")
  public ResponseEntity<String> call() {
    String s = oAuth2RestTemplate3.getForObject(
        "http://patient-management-service/hello",
        String.class);

    return ResponseEntity.ok(s);
  }


}
