package com.coverter.intelliconverter.service;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfServiceImpl implements PdfService {

    @Override
    public InputStreamResource generatePdf(String text) throws IOException {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Input text cannot be null or empty.");
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.beginText();
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(50, 700);

              
                String[] lines = text.split("\n");
                for (String line : lines) {
                   
                    while (line.length() > 80) { 
                        contentStream.showText(line.substring(0, 80));
                        contentStream.newLine();
                        line = line.substring(80);
                    }
                    contentStream.showText(line);
                    contentStream.newLine();
                }
                contentStream.endText();
            }

            document.save(byteArrayOutputStream);
        }

        ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return new InputStreamResource(inputStream);
    }
}

