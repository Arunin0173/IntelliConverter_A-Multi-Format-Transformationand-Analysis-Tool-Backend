package com.coverter.intelliconverter.service;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfToServicesImplementation implements PdfToServices {

    public List<File> renderPDFToImages(File pdfFile, String outputDir) throws IOException {
        List<File> imageFiles = new ArrayList<>();

        try (PDDocument document = PDDocument.load(pdfFile)) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            int pageCount = document.getNumberOfPages();

            for (int page = 0; page < pageCount; page++) {
                BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(page, 300); // Adjust DPI as needed
                File imageFile = new File(outputDir, "page-" + (page + 1) + ".png");
                ImageIO.write(bufferedImage, "PNG", imageFile);
                imageFiles.add(imageFile);
            }
        }
        return imageFiles;
    }
    
    
    
    public String extractText(File pdfFile) throws IOException {
	      try (PDDocument document = PDDocument.load(pdfFile)) {
	            PDFTextStripper textStripper = new PDFTextStripper();
	            return textStripper.getText(document);
	        }
	    }

    
    
}