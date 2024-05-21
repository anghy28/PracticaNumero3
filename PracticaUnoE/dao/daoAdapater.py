from typing import TypeVar, Generic, Type
import logging as log
from tda import LinkedList  
import os.path
import json
import os 

T = TypeVar("T")

class DaoAdapter(Generic[T]):
    atype: Type[T]
    
    def __init__(self, atype: Type[T]):
        self.atype = atype
        self.lista = LinkedList()
        self.file = atype.__name__.lower() + ".json"
        self.URL = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__)))) + "/data/"

    def _list(self) -> LinkedList:
        try:
            if os.path.isfile(self.URL + self.file):
                with open(self.URL + self.file, "r") as f:
                    datos = json.load(f)
                    self.lista = LinkedList()  # Resetea la lista antes de agregar nuevos elementos
                    for data in datos:
                        a = self.atype.deserializar(data)
                        self.lista.add(a, self.lista._length)
            return self.lista
        except Exception as e:
            print(f"Error en list es: {e}")
    
    def _save(self, data: T) -> None:
        try:
            self._list()
            self.lista.add(data, self.lista._length)
            with open(self.URL + self.file, "w") as a:
                a.write(self.__tranform__())
        except Exception as e:
            print(f"Error en save es: {e}")
   
    def _merge(self, data: T, pos: int) -> None:
        try:
            self._list()
            self.lista.editar(data, pos)
            with open(self.URL + self.file, "w") as a:
                a.write(self.__tranform__())
        except Exception as e:
            log.debug(f"Error en merge es: {e}")

    def to_dict(self):
        aux = []
        self._list()
        for i in range(0, self.lista._length):
            aux.append(self.lista.getNode(i).data.serializable)
        return aux

    def __tranform__(self) -> str:
        try:
            aux = "["
            for i in range(0, self.lista._length):
                if i < self.lista._length - 1:
                    aux += str(json.dumps(self.lista.getNode(i).data.serializable)) + ","
                else:
                    aux += str(json.dumps(self.lista.getNode(i).data.serializable))
            aux += "]"
            return aux
        except Exception as e:
            print(f"Error en transform es: {e}")
