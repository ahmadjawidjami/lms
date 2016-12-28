package com.tu.ziik.lms.storage;

import com.tu.ziik.lms.model.lecturer.Post;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    void store(MultipartFile file, String filePath);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

    String createFilePath(MultipartFile file, Post post);

}
