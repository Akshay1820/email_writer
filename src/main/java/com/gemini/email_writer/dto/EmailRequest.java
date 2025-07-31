package com.gemini.email_writer.dto;

import lombok.Data;

@Data
public class EmailRequest {
    private String emailBody;
    private String tone;
}
