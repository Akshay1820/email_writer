package com.gemini.email_writer.gemini_api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "gemini-service", url = "https://generativelanguage.googleapis.com/v1beta/models")

public interface GeminiAPI {


    @PostMapping(value = "/gemini-2.0-flash:generateContent", consumes = "application/json")
    String getGeminiResponse(
            @RequestHeader("X-goog-api-key") String apiKey,
            @RequestBody Map<String,Object> request
    );

}
