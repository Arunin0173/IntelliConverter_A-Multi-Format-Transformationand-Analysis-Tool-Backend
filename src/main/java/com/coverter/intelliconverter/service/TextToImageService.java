package com.coverter.intelliconverter.service;

import java.io.IOException;

public interface TextToImageService {
	 byte[] generateImage(String text) throws IOException;
}
