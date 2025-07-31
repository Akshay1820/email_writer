package com.gemini.email_writer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gemini.email_writer.dto.EmailRequest;
import com.gemini.email_writer.gemini_api.GeminiAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class MailGeneratorService {


    @Value("${google.gemini.api_key}")
    private String geminiKey;

    @Autowired
    private GeminiAPI geminiAPI;

    public String generateEmailResponse(EmailRequest emailRequest){

        String prompt=buildPrompt(emailRequest);    //build a prompt

        Map<String, Object> request = Map.of(
                "contents", new Object[]{
                        Map.of(
                                "parts", new Object[]{
                                        Map.of(
                                                "text", prompt
                                        )
                                }
                        )
                }
        );



       String response= geminiAPI.getGeminiResponse(geminiKey,request); //Do request and Get Response

       return extractResponse(response);    //Extract proper response from raw format
    }

    private String buildPrompt(EmailRequest emailRequest){
        StringBuilder prompt=new StringBuilder();
        prompt.append("""
                Generate a professional Email reply for the following Email Content. 
                Please don't generate the subject
                """);

        //Check if tone is present
        if(Objects.nonNull(emailRequest.getTone()) && !emailRequest.getTone().isEmpty()){
            prompt.append("Use a ").append(emailRequest.getTone()).append(" tone.");
        }
        prompt.append("\nOriginal Email : ").append(emailRequest.getEmailContent());

        return prompt.toString();
    }

    private String extractResponse(String response) {
        ObjectMapper objectMapper=new ObjectMapper();
        try{
            JsonNode jsonNode=objectMapper.readTree(response);
            return jsonNode.path("candidates").get(0).path("content").path("parts").get(0).get("text").asText();
        }
        catch (JsonProcessingException ex){
            return "Failed to parse the result";
        }
    }
}
