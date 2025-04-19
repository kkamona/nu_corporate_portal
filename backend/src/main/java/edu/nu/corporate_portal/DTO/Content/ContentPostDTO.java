package edu.nu.corporate_portal.DTO.Content;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class ContentPostDTO {
    private String title;
    private String text;
}
