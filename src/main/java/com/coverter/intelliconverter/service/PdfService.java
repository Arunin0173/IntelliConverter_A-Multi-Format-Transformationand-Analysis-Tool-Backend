package com.coverter.intelliconverter.service;
import org.springframework.core.io.InputStreamResource;

public interface PdfService {
	InputStreamResource generatePdf(String text) throws Exception;
}
