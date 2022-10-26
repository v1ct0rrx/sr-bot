package com.vvelazquez.telegram.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;

@Component
public class Utilidades {
	
	public byte[] obtenerImagen(String urlImg) throws IOException {
		try {
			URL url = new URL(urlImg);
			BufferedImage bImage = ImageIO.read(url);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(bImage, "jpg", bos);
			byte[] data = bos.toByteArray();
			return data;
		}catch(IOException ex) {
			throw ex;
		}
	}

	public String obtenerFechaConFormato(Date fecha, String dateFormat01) {
		String fechaParse = null;
		DateFormat dateFormat = new SimpleDateFormat(dateFormat01);
		try {
			fechaParse = dateFormat.format(fecha);
		}catch(Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		
		return fechaParse;
	}
}
