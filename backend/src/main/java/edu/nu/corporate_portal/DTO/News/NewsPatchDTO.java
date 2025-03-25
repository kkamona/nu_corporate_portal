package edu.nu.corporate_portal.DTO.News;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NewsPatchDTO {
    private String title;
    private String content;
    private LocalDate publishedDate;
}
