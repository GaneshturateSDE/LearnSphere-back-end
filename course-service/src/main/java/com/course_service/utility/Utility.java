package com.course_service.utility;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utility {
    public static String getImageUrl(String dir_name, MultipartFile mf) throws IOException {
        String absPath = Paths.get(dir_name).toAbsolutePath().toString();
        String fileName = System.currentTimeMillis() + "_" + mf.getOriginalFilename();


// Create folders if they don't exist
        Path imagesDir = Paths.get(absPath, "images");
        Path videosDir = Paths.get(absPath, "videos");

        Files.createDirectories(imagesDir);
        Files.createDirectories(videosDir);
        Path target=null;
        String fileUrl="";

        if (mf.getContentType().startsWith("image")) {
            target = imagesDir.resolve(fileName);
            mf.transferTo(target.toFile());
            fileUrl = "http://localhost:8085/api/courses/files/images/" + fileName; // public URL
        } else if (mf.getContentType().startsWith("video")) {
            target = videosDir.resolve(fileName);
            mf.transferTo(target.toFile());
            fileUrl = "http://localhost:8085/api/courses/files/videos/" + fileName; // public URL
        } else {
            throw new RuntimeException("Unsupported file type");
        }

        System.out.println("Saved file at: " + fileUrl);
//        System.out.println("file name"+mf.getOriginalFilename());
        return fileUrl;
    }
}
