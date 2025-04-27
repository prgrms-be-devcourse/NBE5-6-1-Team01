package com.grepp.nbe561team01.app.controller.web.admin.item;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileController {

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/upload/{folder}/{year}/{month}/{day}/{filename:.+}")
    public Resource downloadFile(@PathVariable String folder,
        @PathVariable String year,
        @PathVariable String month,
        @PathVariable String day,
        @PathVariable String filename) throws Exception {

        Path fileStorageLocation = Paths.get(uploadPath).toAbsolutePath().normalize();
        Path filePath = fileStorageLocation.resolve(folder)
            .resolve(year)
            .resolve(month)
            .resolve(day)
            .resolve(filename)
            .normalize();
        Resource resource = new UrlResource(filePath.toUri());
        if (resource.exists()) {
            return resource;
        } else {
            throw new Exception("파일을 찾을 수 없습니다.");
        }
    }
}

