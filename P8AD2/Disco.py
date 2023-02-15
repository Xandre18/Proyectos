class Disco:

    #Constructor de la clase disco
    def __init__(self,nombre, artista, genero, anho):
        self.nombre = nombre
        self.artista = artista
        self.genero = genero
        self.anho = anho

    #Getters 
    def getNombre(self):
        return self.nombre

    def getArtistas(self):
        return self.artista
    
    def getGenero(self):
        return self.genero

    def getanho(self):
        return self.anho

    #Setters
    def setNombre(self, newNombre):
        self.nombre = newNombre 

    def setArtista(self, newArtista):
        self.artista = newArtista
    
    def setGenero(self, newGenero):
        self.genero = newGenero
    
    def setAnho(self, newAnho):
        self.anho = newAnho

    
