package com.coverter.intelliconverter.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
	
	@GetMapping(value = "/textToImage",produces = MediaType.IMAGE_PNG_VALUE)
	 public byte[] textToImage(@RequestParam("Text") String text) throws IOException {
		BufferedImage image = new BufferedImage(700, 280, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = image.createGraphics();
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, 700, 280);
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("Arial",Font.PLAIN, 50));
		g2d.drawString(text, 120, 150);
		g2d.dispose();
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, "png", baos);
		
		return baos.toByteArray();
	}

}
