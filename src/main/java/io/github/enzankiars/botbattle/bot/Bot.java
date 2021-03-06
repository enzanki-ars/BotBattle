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

public abstract class Bot {

	private Color bodyColor;
	private Color gunColor;
	private BufferedImage bodyImage;
	private BufferedImage gunImage;
	private int rotationBody = 180;
	private int rotationGun = 180;
	private int x;
	private int y;
	private String name;
	private BotStatus botStatusWindow;

	public abstract void run();

	public int getBodyRotation() {
		return rotationBody;
	}

	public void addBodyRotation(int rotation) {
		rotationBody += rotation;
	}
	
	public int getGunRotation() {
		return rotationGun;
	}

	public void addGunRotation(int rotation) {
		rotationGun += rotation;
	}

	public Bot(Color body, Color gun, String name) {
		x = (int) (Math.random() * (MainWindow.getCanvasWidth() - 40)) + 20;
		y = (int) (Math.random() * (MainWindow.getCanvasHeight() - 40)) + 20;

		bodyColor = body;
		gunColor = gun;
		this.name = name;
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
		
		botStatusWindow = new BotStatus(this);
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
		double locationBodyX = bodyImage.getWidth() / 2;
		double locationBodyY = bodyImage.getHeight() / 2;
		AffineTransform txBody = AffineTransform.getRotateInstance(Math.toRadians(rotationBody), locationBodyX,
				locationBodyY);
		AffineTransformOp opBody = new AffineTransformOp(txBody, AffineTransformOp.TYPE_BILINEAR);

		double locationGunX = gunImage.getWidth() / 2;
		double locationGunY = gunImage.getHeight() / 2;
		AffineTransform txGun = AffineTransform.getRotateInstance(Math.toRadians(rotationGun), locationGunX,
				locationGunY);
		AffineTransformOp opGun = new AffineTransformOp(txGun, AffineTransformOp.TYPE_BILINEAR);

		// Drawing the rotated image at the required drawing locations
		g.drawImage(opBody.filter(bodyImage, null), x, y, null);
		g.drawImage(opGun.filter(gunImage, null), x, y, null);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BotStatus getBotStatusWindow() {
		return botStatusWindow;
	}

	public void updateBotStatusWindow() {
		botStatusWindow.update();
	}
	
	public void resetBotStatusWindow() {
		botStatusWindow = new BotStatus(this);
	}
	
	public void forward() {
		x += Math.round(Math.sin(Math.toRadians(rotationBody))*2);
		y += Math.round(Math.cos(Math.toRadians(rotationBody))*2);
	}
	public void backward() {
		x -= Math.round(Math.sin(Math.toRadians(rotationBody))*2);
		y -= Math.round(Math.cos(Math.toRadians(rotationBody))*2);
	}
}
