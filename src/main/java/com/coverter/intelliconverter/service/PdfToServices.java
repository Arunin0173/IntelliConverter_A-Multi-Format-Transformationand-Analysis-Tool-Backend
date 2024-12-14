package com.coverter.intelliconverter.service;


	import java.io.File;
	import java.io.IOException;
	import java.util.List;

	public interface PdfToServices {

		List<File> renderPDFToImages(File tempFile, String outputDir) throws IOException;
		public String extractText(File pdfFile) throws IOException ;
		
	}