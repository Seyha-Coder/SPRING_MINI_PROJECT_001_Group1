package com.example.SPRING_MINI_PROJECT_001_Group1.controller;


import com.example.SPRING_MINI_PROJECT_001_Group1.model.response.ApiResponse;
import com.example.SPRING_MINI_PROJECT_001_Group1.model.response.FileResponse;
import com.example.SPRING_MINI_PROJECT_001_Group1.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/files")
@AllArgsConstructor

public class FileController {
    private final FileService fileService;
    @PostMapping(value = "/uploadMany",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam List<MultipartFile> files) throws IOException {
        String fileName = null;
        List<FileResponse> fileResponses = new ArrayList<>();
        for (MultipartFile file : files) {
            fileName = fileService.saveFile(file);
            String fileUrl = "http://localhost:8080/" + fileName;
            FileResponse fileResponse = new FileResponse(fileName, fileUrl, file.getContentType(), file.getSize());
            fileResponses.add(fileResponse);
        }


        ApiResponse<List<FileResponse>> response = ApiResponse.<List<FileResponse>>builder()
                .message("successfully uploaded file")
                .status(HttpStatus.OK).code(200)
                .payload( fileResponses).build();
        return ResponseEntity.ok(response);
    }
    @PostMapping(value = "/uploadOne",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadOneFile(@RequestParam MultipartFile file) throws IOException {
        String fileName = null;
        fileName = fileService.saveFile(file);
        String fileUrl = "http://localhost:8080/" + fileName;
        FileResponse fileResponse = new FileResponse(fileName, fileUrl, file.getContentType(), file.getSize());




        ApiResponse<FileResponse> response = ApiResponse.<FileResponse>builder()
                .message("successfully uploaded file")
                .status(HttpStatus.OK).code(200)
                .payload( fileResponse).build();
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<?> getFile(@RequestParam String fileName) throws IOException {
        Resource resource = fileService.getFileByFileName(fileName);
        MediaType mediaType;
        if (fileName.endsWith(".pdf")){mediaType = MediaType.APPLICATION_PDF;}
        else if(fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png") || fileName.endsWith(".gif")){
            mediaType = MediaType.IMAGE_PNG;}
        else {mediaType = MediaType.APPLICATION_OCTET_STREAM;}
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                .contentType(mediaType).body(resource);
    }}


