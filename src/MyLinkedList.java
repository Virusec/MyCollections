/**
 * Класс MyLinkedList представляет собой собственную реализацию связанного списка.
 * В нём реализованы методы для добавления, удаления, получения, замены элементов,
 * создания подсписка и определения текущего размера списка.
 *
 * @param <T> Тип элементов, хранимых в списке.
 * @author Anatoliy Shikin
 */
public class MyLinkedList<T> {
    /**
     * Внутренний класс Node представляет собой узел списка,
     * содержащий данные и ссылку на следующий элемент.
     *
     * @param <T> Тип данных, хранимых в узле.
     */
    private static class Node<T> {
        /**
         * Данные, хранимые в узле.
         */
        T data;
        /**
         * Ссылка на следующий узел списка.
         */
        Node<T> next;

        /**
         * Конструктор узла.
         *
         * @param data данные для хранения в узле.
         */
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Голова списка (первый узел).
     */
    private Node<T> head;
    /**
     * Хвост списка (последний узел).
     */
    private Node<T> tail;
    /**
     * Текущее количество элементов в списке.
     */
    private int size;

    /**
     * Конструктор по умолчанию.
     * Инициализирует пустой список.
     */
    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Если список пуст, новый узел становится головой и хвостом.
     * В противном случае добавляет указанный элемент в конец списка и обновляем хвост.
     *
     * @param element элемент, который необходимо добавить.
     * @return true после успешного добавления элемента.
     */
    public boolean add(T element) {
        Node<T> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
        return true;
    }

    /**
     * Возвращает элемент, расположенный по указанному индексу.
     *
     * @param index индекс элемента, который требуется получить.
     * @return элемент, находящийся по заданному индексу.
     * @throws IndexOutOfBoundsException если индекс выходит за пределы диапазона(index < 0 || index >= size()).
     */
    public T get(int index) {
        checkIndex(index);
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    /**
     * Заменяет элемент по указанному индексу новым значением и возвращает старое значение.
     *
     * @param index   индекс элемента, который необходимо заменить.
     * @param element новый элемент, который будет установлен по данному индексу.
     * @return старый элемент, ранее находившийся по заданному индексу.
     * @throws IndexOutOfBoundsException если индекс выходит за пределы диапазона(index < 0 || index >= size()).
     */
    public T set(int index, T element) {
        checkIndex(index);
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        T oldData = current.data;
        current.data = element;
        return oldData;
    }

    /**
     * Удаляет элемент, расположенный по указанному индексу, и возвращает его.
     * При удалении элемента производится сдвиг ссылок, чтобы список оставался связанным.
     *
     * @param index индекс элемента, который требуется удалить.
     * @return удалённый элемент.
     * @throws IndexOutOfBoundsException если индекс меньше 0 или больше/равен размеру списка.
     */
    public T remove(int index) {
        checkIndex(index);
        T removedData;
        if (index == 0) {
            removedData = head.data;
            head = head.next;
            if (head == null) {
                tail = null;
            }
        } else {
            Node<T> prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            Node<T> toRemove = prev.next;
            removedData = toRemove.data;
            prev.next = toRemove.next;
            if (toRemove == tail) {
                tail = prev;
            }
        }
        size--;
        return removedData;
    }

    /**
     * Возвращает текущее количество элементов в списке.
     *
     * @return размер списка.
     */
    public int size() {
        return size;
    }

    /**
     * Пропускает первые fromIndex узлов.
     * Добавляет в новый список узлы от fromIndex до toIndex (не включительно).
     * Возвращает новый список элементов, находящихся в диапазоне от fromIndex (включительно)
     * до toIndex (не включительно).
     *
     * @param fromIndex индекс начала подсписка (включительно).
     * @param toIndex   индекс конца подсписка (не включительно).
     * @return новый экземпляр MyLinkedList, содержащий элементы из указанного диапазона.
     * @throws IndexOutOfBoundsException если fromIndex или toIndex выходят за пределы списка,
     *                                   или если fromIndex больше toIndex.
     */
    public MyLinkedList<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("fromIndex: " + fromIndex + ", toIndex: " + toIndex);
        }
        MyLinkedList<T> subList = new MyLinkedList<>();
        Node<T> current = head;
        for (int i = 0; i < fromIndex; i++) {
            current = current.next;
        }
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(current.data);
            current = current.next;
        }
        return subList;
    }

    /**
     * Возвращает строковое представление списка в формате [element1, element2, ..., elementN].
     *
     * @return строковое представление списка.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Проверяет, что индекс находится в допустимом диапазоне (от 0 до size-1).
     *
     * @param index индекс для проверки.
     * @throws IndexOutOfBoundsException если индекс выходит за пределы диапазона списка(index < 0 || index >= size()).
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
