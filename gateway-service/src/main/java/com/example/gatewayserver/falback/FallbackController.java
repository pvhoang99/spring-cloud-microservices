package com.example.gatewayserver.falback;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @RequestMapping("/message")
    public ResponseEntity<?> fallback() {
        return new ResponseEntity<>(FallbackResponse.withDefaults(), HttpStatus.SERVICE_UNAVAILABLE);

    }

    public static class FallbackResponse {

        private String message;
        private Long code;

        private FallbackResponse(String message, Long code) {
            this.message = message;
            this.code = code;
        }

        public static FallbackResponse withDefaults() {

            return new FallbackResponse("ERROR!. AN ERROR OCCURRED. PLEASE TRY AGAIN LATER", 503L);
        }

        public String getMessage() {
            return message;
        }

        public Long getCode() {
            return code;
        }
    }
}
