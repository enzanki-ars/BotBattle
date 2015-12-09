from io.github.enzankiars.botbattle.bot import Bot

class TestBot(Bot):
    def run(self):
        Bot.setRotation(self, Bot.getRotation(self)+1)
