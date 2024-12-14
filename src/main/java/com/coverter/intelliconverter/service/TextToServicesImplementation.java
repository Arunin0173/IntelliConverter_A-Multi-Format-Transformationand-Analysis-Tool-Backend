package com.coverter.intelliconverter.service;


import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

@Service

public class TextToServicesImplementation implements TextToServices {

	

	    @Override
	    public byte[] generateImage(String text) throws IOException {
	        // Define image dimensions
	        int width = 400;
	        int height = 200;

	        // Create a BufferedImage
	        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

	        // Get the graphics context of the image
	        Graphics2D graphics = bufferedImage.createGraphics();

	        // Fill the background with white color
	        graphics.setColor(Color.WHITE);
	        graphics.fillRect(0, 0, width, height);

	        // Set font and text color
	        graphics.setFont(new Font("Arial", Font.BOLD, 24));
	        graphics.setColor(Color.BLACK);

	        // Draw the text on the image
	        FontMetrics fontMetrics = graphics.getFontMetrics();
	        int textWidth = fontMetrics.stringWidth(text);
	        int textHeight = fontMetrics.getHeight();
	        int x = (width - textWidth) / 2; // Center horizontally
	        int y = (height - textHeight) / 2 + fontMetrics.getAscent(); // Center vertically
	        graphics.drawString(text, x, y);

	        // Dispose of the graphics context
	        graphics.dispose();

	        // Convert the BufferedImage to a byte array
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        ImageIO.write(bufferedImage, "png", outputStream);
	        return outputStream.toByteArray();
	    }
	}



