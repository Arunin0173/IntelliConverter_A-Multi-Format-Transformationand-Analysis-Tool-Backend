package com.coverter.intelliconverter.controller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coverter.intelliconverter.service.TextToService;

@RestController
@CrossOrigin
public class TextToController {	
	
	//text to image 
	
	@RestController
	@RequestMapping("/api")
	public class TextToImageController {

	    private final TextToService textToImageService;

	    public TextToImageController(TextToService textToImageService) {
	        this.textToImageService = textToImageService;
	    }

	    @GetMapping(value = "/textToImage", produces = MediaType.IMAGE_PNG_VALUE)
	    public byte[] generateTextImage(@RequestParam("Text") String text) throws IOException {
	        return textToImageService.generateImage(text);
	    }
	}

		

		//text to pdf
		
		    @PostMapping("/generate")
		    public ResponseEntity<InputStreamResource> generatePdf(@RequestBody String text) {
		    	ByteArrayOutputStream byos=new ByteArrayOutputStream();
		        try (PDDocument document = new PDDocument()){
		        	//create a blank page
		            PDPage page= new PDPage();
		            document.addPage(page);
		            
		            //add a content 
		            try(PDPageContentStream  contentStream= new PDPageContentStream(document, page)){
		            	contentStream.setFont(PDType1Font.HELVETICA,12);
		            	contentStream.beginText();
		            	contentStream.setLeading(14.5f);
		            	contentStream.newLineAtOffset(50, 700);
		            	
		            	//split text into lines and write a pdf
		            	
		            	String[] lines= text.split("\n");
		            	for(String line: lines) {
		            		contentStream.showText(line);
		            		contentStream.newLine();
		            	}
		            	contentStream.endText();
		            }
		            
		            //save the document to a byte array
		            document.save(byos);
		        } catch (IOException e) {
		        	e.printStackTrace();
		        	return ResponseEntity.internalServerError().build();
		        }
		            //prepare the inputstream from the byte array
		        
		        ByteArrayInputStream input= new ByteArrayInputStream(byos.toByteArray());
		        InputStreamResource resource= new InputStreamResource(input);
		        
		            HttpHeaders headers = new HttpHeaders();
		            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=generated.pdf");

		            return ResponseEntity.ok()
		            		.headers(headers)
		            		.contentType(MediaType.APPLICATION_PDF)
		            		.body(resource);
		       
		    }
		
		

	}



