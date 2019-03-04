package com.cdnsample.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	@Autowired
	private Environment env;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getIndexAsByteArray(HttpServletResponse response) throws IOException {
		URL oracle = new URL("http://" + env.getProperty("CDN_CNAME") + "/index.html");
		BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

		String inputLine;
		StringBuffer sb = new StringBuffer();
		while ((inputLine = in.readLine()) != null)
			sb.append(inputLine);
		in.close();
		return sb.toString();
	}

	@RequestMapping(value = "/us", method = RequestMethod.GET)
	public void getImageUSAsByteArray(HttpServletResponse response) throws IOException {
		BufferedImage image = null;
		URL url = new URL("http://" + env.getProperty("CDN_CNAME_US") + "/Us-flag-picture.png");
		image = ImageIO.read(url);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(image, "png", os);
		InputStream fis = new ByteArrayInputStream(os.toByteArray());
		response.setContentType(MediaType.IMAGE_PNG_VALUE);
		IOUtils.copy(fis, response.getOutputStream());
	}
	@RequestMapping(value = "/india", method = RequestMethod.GET)
	public void getImageINDAsByteArray(HttpServletResponse response) throws IOException {
		BufferedImage image = null;
		URL url = new URL("http://" + env.getProperty("CDN_CNAME_IND") + "/Indian-flag-picture");
		system.out.println("**********"+url);
		image = ImageIO.read(url);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(image, "png", os);
		InputStream fis = new ByteArrayInputStream(os.toByteArray());
		response.setContentType(MediaType.IMAGE_PNG_VALUE);
		IOUtils.copy(fis, response.getOutputStream());
	}
}
