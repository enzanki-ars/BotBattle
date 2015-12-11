from io.github.enzankiars.botbattle.bot import Bot

class TestBot(Bot):
    def run(self):
        self.addBodyRotation(1)
        self.addGunRotation(-2)