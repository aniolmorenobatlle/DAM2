class Cotxe:
    def __init__(self, marca, model, color):
        self.marca = marca
        self.model = model
        self.color = color

    def accelerar(self):
        print(f"El cotxe {self.marca} {self.model} està accelerant.")

    def frenar(self):
        print(f"El cotxe {self.marca} {self.model} està frenant.")

cotxe1 = Cotxe("Totyota", "Corolla", "Blau")

cotxe1.accelerar()
cotxe1.frenar()