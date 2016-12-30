package com.tu.ziik.lms.storage;

import com.tu.ziik.lms.model.lecturer.CourseContentPost;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    void store(MultipartFile file, String filePath);

    //Stream<Path> loadAll(Long courseId);

    Path load(String filename);

    Resource loadAsResource(String filename);

   void deleteAll();

    String createFilePath(MultipartFile file, Long courseContentPost);

}
