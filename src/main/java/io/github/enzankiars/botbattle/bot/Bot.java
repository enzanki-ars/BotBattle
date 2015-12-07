package io.github.enzankiars.botbattle.bot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import io.github.enzankiars.botbattle.util.TintImage;

public class Bot {

	private Color bodyColor;
	private Color gunColor;
	private BufferedImage bodyImage;
	private BufferedImage gunImage;
	int rotation = 180;
	

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public Bot(Color body, Color gun) {
		bodyColor = body;
		gunColor = gun;
		try {
			bodyImage = ImageIO.read(getClass().getClassLoader().getResource("body.png"));
			BufferedImage maskBody = TintImage.generateMask(bodyImage, body, 0.5f);
			bodyImage = TintImage.tint(bodyImage, maskBody);
			
			gunImage = ImageIO.read(getClass().getClassLoader().getResource("gun.png"));
			BufferedImage maskGun = TintImage.generateMask(gunImage, gun, 0.5f);
			gunImage = TintImage.tint(gunImage, maskGun);
        } catch (IOException exp) {
            exp.printStackTrace();
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
	
	public void drawFullImage(Graphics g, int x, int y) {
		drawFullImage(g, x, y, rotation);
	}
	
	public void drawFullImage(Graphics g, int x, int y, double rotate) {
		double rotationRequired = Math.toRadians(rotate);
		double locationX = bodyImage.getWidth() / 2;
		double locationY = bodyImage.getHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		// Drawing the rotated image at the required drawing locations
		g.drawImage(op.filter(bodyImage, null), x, y, null);
	}
}
