package com.coverter.intelliconverter.controller;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coverter.intelliconverter.service.PdfService;
import com.coverter.intelliconverter.service.TextToImageService;

@RestController
@CrossOrigin
public class TextToController {	
	
	//text to image 
	
	    @Autowired 
	    private TextToImageService textToImageService;

	    @GetMapping(value = "/textToImage", produces = MediaType.IMAGE_PNG_VALUE)
	    public byte[] generateTextImage(@RequestParam("Text") String text) throws IOException {
	        return textToImageService.generateImage(text);
	    }
	

		

		//text to pdf
		
	  @Autowired
	    private PdfService pdfService;
	 @PostMapping("/generate")
	    public ResponseEntity<?> generatePdf(@RequestBody String text) {
	        try {
	            InputStreamResource resource = pdfService.generatePdf(text);

	            HttpHeaders headers = new HttpHeaders();
	            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=generated.pdf");

	            return ResponseEntity.ok()
	                    .headers(headers)
	                    .contentType(MediaType.APPLICATION_PDF)
	                    .body(resource);
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.badRequest().body(e.getMessage());
	        } catch (Exception e) {
	            return ResponseEntity.internalServerError().body("An error occurred while generating the PDF.");
	        }
	    }
		
		

}




