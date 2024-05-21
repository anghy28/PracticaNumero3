from modelo import HistorialComandos
class Usuario:
    def __init__(self, nombre):
        self.__nombre = nombre
        self.__historial = HistorialComandos()

    @property
    def nombre(self):
        return self.__nombre

    @property
    def historial(self):
        return self.__historial

    def ejecutar_comando(self, comando):
        self.__historial.agregar_comando(comando)

    def obtener_historial_comandos(self):
        return self.__historial.obtener_historial()
