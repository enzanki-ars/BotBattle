package io.github.enzankiars.botbattle.bot.demo;

import java.awt.Color;
import javax.swing.JPanel;

import io.github.enzankiars.botbattle.bot.Bot;

public class DemoBot extends Bot {

	public DemoBot(Color body, Color gun, String name) {
		super(body, gun, name);
		JPanel customPanel = new DemoCustomPanel(this);
		getBotStatusWindow().addCustomPanel(customPanel);
	}

	@Override
	public void run() {
		
	}
}
