package com.cdnsample.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void getImageAsByteArray(HttpServletResponse response) throws IOException {
		BufferedImage image = null;
		URL url = new URL("http://d5kty0mtm7s3p.cloudfront.net/a-picture.png");
		image = ImageIO.read(url);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(image, "png", os);
		InputStream fis = new ByteArrayInputStream(os.toByteArray());
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		IOUtils.copy(fis, response.getOutputStream());
	}

}
