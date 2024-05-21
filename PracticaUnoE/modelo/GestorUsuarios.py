from modelo import Usuario

class GestorUsuarios:
    def __init__(self, limite_historial=20):
        self.__usuarios = {}
        self.__limite_historial = limite_historial

    @property
    def usuarios(self):
        return self.__usuarios

    @property
    def limite_historial(self):
        return self.__limite_historial

    def agregar_usuario(self, nombre_usuario):
        if nombre_usuario not in self.__usuarios:
            # Aquí se debe instanciar la clase Usuario correctamente
            self.__usuarios[nombre_usuario] = Usuario(nombre_usuario, self.__limite_historial)
        else:
            print(f"El usuario {nombre_usuario} ya existe.")

    def ejecutar_comando(self, nombre_usuario, comando):
        if nombre_usuario in self.__usuarios:
            self.__usuarios[nombre_usuario].ejecutar_comando(comando)
        else:
            print(f"Usuario {nombre_usuario} no encontrado.")

    def obtener_historial_usuario(self, nombre_usuario):
        if nombre_usuario in self.__usuarios:
            return self.__usuarios[nombre_usuario].obtener_historial_comandos()
        else:
            print(f"Usuario {nombre_usuario} no encontrado.")
            return []
