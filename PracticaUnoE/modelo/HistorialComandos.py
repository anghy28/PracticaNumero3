from tda import Linked_List

class HistorialComandos:
    def __init__(self, limite=20):
        self.__historial = Linked_List()
        self.__limite = limite

    @property
    def historial(self):
        return self.__historial

    @property
    def limite(self):
        return self.__limite

    def agregar_comando(self, comando):
        if self.__historial._Linked_List__length >= self.__limite:
            self.__historial.delete(0)
        self.__historial.__addLast__(comando)

    def obtener_historial(self):
        return self.__historial.toArray()
