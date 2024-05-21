class Linked_List:
    class Node: 
        def __init__(self, data=None, next=None):
            self.data = data
            self.next = next

    def __init__(self):
        self.__head = None
        self.__last = None
        self.__length = 0

    def isEmpty(self):
        return self.__head is None or self.__length == 0

    def __addFirst__(self, data):
        node = self.Node(data, self.__head)
        self.__head = node
        if self.__last is None:
            self.__last = node
        self.__length += 1

    def __addLast__(self, data):
        if self.isEmpty():
            self.__addFirst__(data)
        else:
            node = self.Node(data)
            self.__last.next = node
            self.__last = node
            self.__length += 1

    def __str__(self):
        out = ""
        node = self.__head
        if self.isEmpty():
            out = 'List is Empty'
        else:
            while node is not None:
                out += str(node.data) + " -> "
                node = node.next
        return out.strip(" -> ")

    def add(self, data, pos=0):
        if pos < 0 or pos > self.__length:
            raise IndexError("Posición fuera de rango")
        if pos == 0:
            self.__addFirst__(data)
        elif pos == self.__length:
            self.__addLast__(data)
        else:
            prev_node = self.getNode(pos - 1)
            new_node = self.Node(data, prev_node.next)
            prev_node.next = new_node
            self.__length += 1

    def editar(self, data, pos):
        if pos < 0 or pos >= self.__length:
            raise IndexError("Posición fuera de rango")
        node = self.getNode(pos)
        node.data = data

    def toArray(self):
        array = []
        current = self.__head
        while current is not None:
            array.append(current.data)
            current = current.next
        return array

    def delete(self, pos):
        if pos < 0 or pos >= self.__length:
            raise IndexError("Posición fuera de rango")
        if pos == 0:
            self.__head = self.__head.next
            if self.__head is None:
                self.__last = None
        else:
            prev_node = self.getNode(pos - 1)
            node_to_delete = prev_node.next
            prev_node.next = node_to_delete.next
            if prev_node.next is None:
                self.__last = prev_node
        self.__length -= 1

    def getNode(self, pos):
        if pos < 0 or pos >= self.__length:
            raise IndexError("Posición fuera de rango")
        current = self.__head
        for _ in range(pos):
            current = current.next
        return current
