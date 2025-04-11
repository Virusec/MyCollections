/**
 * Класс MyArrayList представляет собой собственную реализацию динамического массива.
 * В нём реализованы методы для добавления, удаления, получения, замены элементов,
 * получения подсписка и вывода строкового представления списка.
 *
 * @param <T> Тип элементов, хранимых в списке.
 * @author Anatoliy Shikin
 */

public class MyArrayList<T> {
    /**
     * Начальный размер внутреннего массива.
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * Внутренний массив для хранения элементов.
     */
    private Object[] elements;
    /**
     * Текущее количество элементов в списке.
     */
    private int size;

    /**
     * Конструктор по умолчанию.
     * Инициализирует внутренний массив с размером по умолчанию.
     */
    public MyArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param element элемент для добавления.
     * @return true после успешного добавления.
     */
    public boolean add(T element) {
        ensureCapacity();
        elements[size++] = element;
        return true;
    }

    /**
     * Проверяет, достаточно ли места во внутреннем массиве.
     * Если массив заполнен, увеличивает его размер вдвое.
     */
    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = elements.length * 2;
            Object[] newElements = new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
    }

    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param index индекс нужного элемента.
     * @return элемент, находящийся по индексу.
     * @throws IndexOutOfBoundsException если индекс выходит за пределы диапазона(index < 0 || index >= size()).
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    /**
     * Заменяет элемент по указанному индексу новым и возвращает старый элемент.
     *
     * @param index   индекс изменяемого элемента.
     * @param element новый элемент.
     * @return элемент, ранее находившийся по заданному индексу.
     * @throws IndexOutOfBoundsException если индекс выходит за пределы диапазона(index < 0 || index >= size()).
     */
    @SuppressWarnings("unchecked")
    public T set(int index, T element) {
        checkIndex(index);
        T oldValue = (T) elements[index];
        elements[index] = element;
        return oldValue;
    }

    /**
     * Удаляет элемент по указанному индексу и возвращает его.
     * Все элементы после удаляемого сдвигаются влево.
     *
     * @param index индекс удаляемого элемента.
     * @return удалённый элемент.
     * @throws IndexOutOfBoundsException если индекс выходит за пределы диапазона(index < 0 || index >= size()).
     */
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);
        T removedElement = (T) elements[index];
        // Сдвигаем все элементы, начиная со следующего, на одну позицию влево
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        // Освобождаем последнюю позицию
        elements[size - 1] = null;
        size--;
        return removedElement;
    }

    /**
     * Возвращает количество элементов в списке.
     *
     * @return текущее количество элементов.
     */
    public int size() {
        return size;
    }

    /**
     * Возвращает новую коллекцию содержащую элементы в диапазоне [fromIndex, toIndex).
     *
     * @param fromIndex индекс начала диапазона (включительно).
     * @param toIndex   индекс конца диапазона (не включительно).
     * @return новый MyArrayList с элементами из указанного диапазона.
     * @throws IndexOutOfBoundsException если fromIndex или toIndex выходят за пределы массива или fromIndex > toIndex
     *                                   (fromIndex < 0 || toIndex > size || fromIndex > toIndex).
     */
    public MyArrayList<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("fromIndex: " + fromIndex + ", toIndex: " + toIndex);
        }
        MyArrayList<T> subList = new MyArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(get(i));
        }
        return subList;
    }

    /**
     * Проверяет, что указанный индекс находится в границах массива от 0 до size
     *
     * @param index индекс проверяемого элемента.
     * @throws IndexOutOfBoundsException если индекс выходит за пределы диапазона(index < 0 || index >= size())..
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * Возвращает строковое представление списка в формате [element1, element2, ..., elementN].
     *
     * @return строковое представление списка.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
