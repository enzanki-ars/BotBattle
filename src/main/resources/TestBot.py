from io.github.enzankiars.botbattle.bot import Bot

class TestBot(Bot):
    def run(self):
        setRotation(getRotation()+1)