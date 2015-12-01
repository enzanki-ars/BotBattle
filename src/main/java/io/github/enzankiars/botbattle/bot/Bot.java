package io.github.enzankiars.botbattle.bot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bot {

	private Color bodyColor;
	private Color gunColor;
	private BufferedImage bodyImage;
	private BufferedImage gunImage;

	public Bot() {
		bodyColor = Color.BLUE;
		gunColor = Color.BLUE;
		try {
			bodyImage = ImageIO.read(getClass().getClassLoader().getResource("body.png"));
			gunImage = ImageIO.read(getClass().getClassLoader().getResource("gun.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Color getBodyColor() {
		return bodyColor;
	}

	public void setBodyColor(Color color) {
		this.bodyColor = color;
	}

	public Color getGunColor() {
		return gunColor;
	}

	public void setGunColor(Color gunColor) {
		this.gunColor = gunColor;
	}

	public BufferedImage getBodyImage() {
		return bodyImage;
	}

	public void setBodyImage(BufferedImage bodyImage) {
		this.bodyImage = bodyImage;
	}

	public BufferedImage getGunImage() {
		return gunImage;
	}

	public void setGunImage(BufferedImage gunImage) {
		this.gunImage = gunImage;
	}
	
	public BufferedImage getFullImage() {
		// create the new image, canvas size is the max. of both image sizes
		int w = Math.max(bodyImage.getWidth(), gunImage.getWidth());
		int h = Math.max(bodyImage.getHeight(), gunImage.getHeight());
		BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		// paint both images, preserving the alpha channels
		Graphics g = combined.getGraphics();
		g.drawImage(bodyImage, 0, 0, null);
		g.drawImage(gunImage, 0, 0, null);

		// Save as new image
		ImageIO.write(combined, "PNG", new File(path, "combined.png"));
	}
}
