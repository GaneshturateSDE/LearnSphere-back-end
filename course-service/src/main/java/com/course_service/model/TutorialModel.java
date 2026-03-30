package com.course_service.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TutorialModel {

    // Mongo doesn't need ID for embedded docs (optional)
    private String id;   // you may keep it if you want

    private String title;

    private String content;   // text, video link, markdown, etc.

    private String resourceUrl;

    private Integer orderIndex;   // sequence inside course
}
