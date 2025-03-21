package com.cit.backend.domain.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;


public class UploadFilesService {

    private final Path rootLocation;

    public UploadFilesService(String dir) {
        this.rootLocation = Paths.get("src/main/resources/static/" + dir);
        init();
    }

    public void store(MultipartFile file) {
        store(file, file.getOriginalFilename());
    }

    public void store(MultipartFile file, String name) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file.");
            }
            Files.copy(file.getInputStream(), this.rootLocation.resolve(name), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file.", e);
        }
    }

    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    public List<Path> loadAll(String pattern) {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + rootLocation.toString() + pattern);
        try (Stream<Path> files = Files.walk(rootLocation, 1)) {
            return files.filter(pathMatcher::matches).toList();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read stored files", e);
        }
    }

    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }
}
