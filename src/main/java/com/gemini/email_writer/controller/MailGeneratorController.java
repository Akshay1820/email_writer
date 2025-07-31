package com.gemini.email_writer.controller;

import com.gemini.email_writer.dto.EmailRequest;
import com.gemini.email_writer.service.MailGeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mail")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class MailGeneratorController {

    private final MailGeneratorService mailGeneratorService;

    @PostMapping("/generate")
    public ResponseEntity<?> generateResponse(@RequestBody EmailRequest emailRequest){
       String response=  mailGeneratorService.generateEmailResponse(emailRequest);
        return ResponseEntity.ok(response);
    }
}
