package com.gemini.email_writer.controller;

import com.gemini.email_writer.dto.EmailRequest;
import com.gemini.email_writer.service.MailGeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mail")
@AllArgsConstructor
public class MailGeneratorController {

    private final MailGeneratorService mailGeneratorService;

    @PostMapping("/reply")
    public ResponseEntity<?> generateResponse(@RequestBody EmailRequest emailRequest){
       String response=  mailGeneratorService.generateEmailResponse(emailRequest);
        return ResponseEntity.ok(response);
    }
}
