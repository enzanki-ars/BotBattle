package io.github.enzankiars.botbattle.bot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import io.github.enzankiars.botbattle.MainWindow;
import io.github.enzankiars.botbattle.util.TintImage;

public class Bot {

	private Color bodyColor;
	private Color gunColor;
	private BufferedImage bodyImage;
	private BufferedImage gunImage;
	int rotation = 180;
	int x;
	int y;
	

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public Bot(Color body, Color gun) {
		x = (int) (Math.random() * MainWindow.getCanvasWidth());
		y = (int) (Math.random() * MainWindow.getCanvasHeight());
		
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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void drawFullImage(Graphics g) {
		double rotationRequired = Math.toRadians(rotation);
		double locationBodyX = bodyImage.getWidth() / 2;
		double locationBodyY = bodyImage.getHeight() / 2;
		AffineTransform txBody = AffineTransform.getRotateInstance(rotationRequired, locationBodyX, locationBodyY);
		AffineTransformOp opBody = new AffineTransformOp(txBody, AffineTransformOp.TYPE_BILINEAR);
		
		double locationGunX = gunImage.getWidth() / 2;
		double locationGunY = gunImage.getHeight() / 2;
		AffineTransform txGun = AffineTransform.getRotateInstance(rotationRequired, locationGunX, locationGunY);
		AffineTransformOp opGun = new AffineTransformOp(txGun, AffineTransformOp.TYPE_BILINEAR);

		// Drawing the rotated image at the required drawing locations
		g.drawImage(opBody.filter(bodyImage, null), x, y, null);
		g.drawImage(opGun.filter(gunImage, null), x, y, null);
	}
}
