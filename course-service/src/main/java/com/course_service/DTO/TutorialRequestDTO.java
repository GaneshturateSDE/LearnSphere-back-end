package com.course_service.DTO;

import lombok.Data;

@Data
public class TutorialRequestDTO {

    private String title;

    private String content;   // text, video link, markdown, etc.

    private String resourceUrl;

    private Integer orderIndex;
}
