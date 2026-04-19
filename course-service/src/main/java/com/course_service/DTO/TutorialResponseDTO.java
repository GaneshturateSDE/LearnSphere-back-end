package com.course_service.DTO;

import lombok.Data;

@Data
public class TutorialResponseDTO {
    private  String id;

    private String title;

    private String content;   // text, video link, markdown, etc.

    private String resourceUrl;

    private Integer orderIndex;

}
