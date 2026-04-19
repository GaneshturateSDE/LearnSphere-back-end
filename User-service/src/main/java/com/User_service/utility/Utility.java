package com.User_service.utility;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class Utility {
    public static String getImageUrl(String dir_name, MultipartFile mf) throws IOException {
        String absPath = Paths.get(dir_name).toAbsolutePath().toString();
        String fileName = System.currentTimeMillis() + "_" + mf.getOriginalFilename();


// Create folders if they don't exist
        Path imagesDir = Paths.get(absPath, "images");


        Files.createDirectories(imagesDir);

        Path target=null;
        String fileUrl="";

       try{
            target = imagesDir.resolve(fileName);
            mf.transferTo(target.toFile());
            fileUrl = "http://localhost:8085/api/users/files/images/" + fileName; // public URL
        } catch (Exception e) {
           throw new RuntimeException(e);
       }

        System.out.println("Saved file at: " + fileUrl);
//        System.out.println("file name"+mf.getOriginalFilename());
        return fileUrl;
    }

}
