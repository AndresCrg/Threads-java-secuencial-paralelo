package models;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ChangeImage {

	public void change(String image) {
		// Carga de la img en memoria

		BufferedImage originalImage = null;
		try {
			// leemos la img
			originalImage = ImageIO.read(new File(image));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// recorremos pixel a pixel la imagen

		for (int x = 0; x < originalImage.getWidth(); x++) {
			for (int y = 0; y < originalImage.getHeight(); y++) {

				// obtenemos el color del pixel actual
				Color pixelColor = new Color(originalImage.getRGB(x, y));

				// descomponemos el color en sus RGB (rojo, verde y azul)
				int red = pixelColor.getRed();
				int green = pixelColor.getGreen();
				int blue = pixelColor.getBlue();

				// media para obtener su color en gris
				int mediaRGB = (red + green + blue) / 3;
				// media pasada a IntegerRGB
				int mediaRGBInteger = (mediaRGB << 16) | (mediaRGB << 8) | mediaRGB;
				// cambiamos el color del pixel por el color en escala de grises
				originalImage.setRGB(x, y, mediaRGBInteger);
			}
		}
		try {
			ImageIO.write(originalImage, "jpg",new File("./dataBaseB&M/"+ image.substring(10, image.length())));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}