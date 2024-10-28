class Cotxe:
    def __init__(self, marca, model, color):
        self.marca = marca
        self.model = model
        self.color = color
        self.km = 0


    def conduir(self, km):
        if km > 0:
            self.km += km
            print(f"Has conduit {km}km. Total: {self.km}.")

        else:
            print("La distància ha de ser superior a 0.")

    
    def info(self):
        return f"{self.marca} {self.model}, {self.color} - {self.km} km"



if __name__ == "__main__":
    
    cotxe1 = Cotxe("Toyota", "Corolla", "Blau")

    print("Informació del cotxe")
    print(cotxe1.info())


    cotxe1.conduir(200)

    print("Informació del cotxe despres de conduir:")
    print(cotxe1.info())