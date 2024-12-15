package com.coverter.intelliconverter.service;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class TextToServiceImplementation implements TextToService {

    @Override
    public byte[] generateImage(String text) throws IOException {
        int width = 700;
        int height = 280;

       
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

       
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.PLAIN, 50));
        g2d.drawString(text, 120, 150);
        g2d.dispose();

       
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        return baos.toByteArray();
    }
}
