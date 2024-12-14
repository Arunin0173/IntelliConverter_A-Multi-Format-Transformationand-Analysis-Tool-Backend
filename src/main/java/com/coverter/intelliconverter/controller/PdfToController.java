package com.coverter.intelliconverter.controller;


	import java.io.File;
	import java.io.IOException;
	import java.util.List;
	import java.util.stream.Collectors;

	import org.springframework.beans.factory.annotation.Autowired;

	import org.springframework.core.io.InputStreamResource;
	import org.springframework.http.HttpHeaders;
	import org.springframework.http.MediaType;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.*;
	import org.springframework.web.multipart.MultipartFile;

import com.coverter.intelliconverter.service.PdfToServices;

import java.io.*;
	import java.nio.file.Files;
	import java.util.List;
	import java.util.zip.ZipEntry;
	import java.util.zip.ZipOutputStream;

	@RestController
	public class PdfToController{

	    @Autowired
	    private PdfToServices pdfToService;

	    @PostMapping("/convert-and-download")
	    public ResponseEntity<?> convertAndDownloadPDFToImages(@RequestParam("file") MultipartFile file) {
	        try {
	            // Save uploaded file to a temporary location
	            File pdfFile = Files.createTempFile("uploaded-", ".pdf").toFile();
	            file.transferTo(pdfFile);

	            // Generate images
	            File outputDir = Files.createTempDirectory("pdf-images").toFile();
	            List<File> imageFiles = pdfToService.renderPDFToImages(pdfFile, outputDir.getAbsolutePath());

	            // Create ZIP file
	            File zipFile = new File(outputDir, "images.zip");
	            try (FileOutputStream fos = new FileOutputStream(zipFile);
	                 ZipOutputStream zipOut = new ZipOutputStream(fos)) {
	                for (File imageFile : imageFiles) {
	                    try (FileInputStream fis = new FileInputStream(imageFile)) {
	                        ZipEntry zipEntry = new ZipEntry(imageFile.getName());
	                        zipOut.putNextEntry(zipEntry);

	                        byte[] buffer = new byte[1024];
	                        int length;
	                        while ((length = fis.read(buffer)) >= 0) {
	                            zipOut.write(buffer, 0, length);
	                        }
	                    }
	                }//zipOut.write(buffer, length);
	            }

	            // Serve ZIP file for download
	            InputStreamResource resource = new InputStreamResource(new FileInputStream(zipFile));
	            return ResponseEntity.ok()
	                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=images.zip")
	                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
	                    .body(resource);

	        } catch (IOException e) {
	            return ResponseEntity.status(500).body("Error processing PDF: " + e.getMessage());
	        }
	    }
	    
	    
	    
	    
	    
	    @PostMapping("/convert-to-text")
	    public ResponseEntity<String> convertToText(@RequestParam("file") MultipartFile file) {
	        try {
	            File tempFile = File.createTempFile("pdf", ".pdf");
	            file.transferTo(tempFile);
	            String text = pdfToService.extractText(tempFile);
	            return ResponseEntity.ok(text);
	        } catch (IOException e) {
	            return ResponseEntity.internalServerError().body("Error processing PDF: " + e.getMessage());
	        }
	    }
	}