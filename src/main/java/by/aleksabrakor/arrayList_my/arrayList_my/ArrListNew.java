package by.aleksabrakor.arrayList_my.arrayList_my;

import java.util.Collection;
import java.util.Comparator;

/**
 * Пользовательская реализация списка на основе динамического массива, который может изменять свой размер
 * Этот класс предоставляет методы для добавления, удаления и доступа к элементам, а также для сортировки списка  QuickSort - бысторая  сортировка элементов
 * <р>
 * Для хранения элементов в списке используется массив.
 * Размер массива может увеличиться на <i>MULTIPLIER</i>, если достигнут предел его емкости.
 * <р>
 *
 * @param <E> тип элементов в  списке
 * @автор Бракор Александра
 */
public class ArrListNew<E> {
    private E[] elements;
    private final static int A_CAPACITY = 10; //вместимость листа по умолчанию
    private int capacity; //вместимость листа
    private int size; //количество элементов, размер листа
    private static final int MULTIPLIER = 2;

    /**
     * Конструктор создает пустой список с начальной вместимостью  по умолчанию
     */
    public ArrListNew() {
        this.elements = (E[]) new Object[A_CAPACITY];
        this.capacity = A_CAPACITY;
        this.size = 0;
    }

    /**
     * Конструктор создает пустой список с указанной начальной емкостью.
     *
     * @param capacity начальная емкость списка
     * @throws IllegalArgumentException, если указанная емкость отрицательна
     */
    public ArrListNew(int capacity) {
        if (capacity >= 0) {
            this.elements = (E[]) new Object[capacity];
            this.capacity = capacity;
            this.size = 0;
        } else {
            System.out.println("Вместимость не может быть меньше 0");
            throw new IllegalArgumentException("Capacity is wrong: " + capacity);
        }
    }

    /**
     * Конструктор создает новый список с начальной емкостью и добавляет в него множество элементов.
     * Если количество элементов превышает начальную емкость, емкость увеличивается в соответствии с множителем.
     * В противном случае используется начальная емкость.
     * После проверки и/или изменения  емкости элементы добавляются в список.
     *
     * @param arr массив (множество) элементов, которые будут добавлены в список при создании.
     *            Если массив пуст, создается пустой список с начальной емкостью.
     */
    public ArrListNew(E... arr) {
        if (arr.length > A_CAPACITY) {
            this.capacity = A_CAPACITY * MULTIPLIER;
            this.elements = (E[]) new Object[capacity];
        } else {
            this.capacity = A_CAPACITY;
            this.elements = (E[]) new Object[capacity];
        }
        this.addAll(arr);
        this.size = arr.length;
    }

    /**
     * Добавляет один элемент в конец списка.
     * Если массив заполнен, его емкость увеличивается.
     *
     * @param elem элемент, который нужно добавить в список
     */

    public void add(E elem) {
        if (capacity <= size) {
            increaseCapacity();
        }
        this.elements[size] = elem;
        size++;
    }

    /**
     * Добавляет множество элементов в конец списка.
     * Если массив заполнен, его емкость увеличивается.
     *
     * @param arr
     */
    // Добавляет множество элементов
    public void addAll(E... arr) {
        for (int i = 0; i < arr.length; i++) {
            this.add(arr[i]);
        }
    }

    /**
     * Добавляет элементы из пользовательского листа (такой же).
     * Если массив заполнен, его емкость увеличивается.
     *
     * @param arrListNew - пользовательский лист (такой же собственной реализации)
     */
    public void addAll(ArrListNew<E> arrListNew) {
        for (int i = 0; i < arrListNew.size; i++) {
            add(arrListNew.getElements()[i]);
        }
    }

    /**
     * Добавляет элементы из любой Java коллекции.
     * Если массив заполнен, его емкость увеличивается.
     * Если переданная коллекция пуста или равна null, метод не выполняет никаких действий.
     *
     * @param c Коллекция элементов, которые должны быть добавлены в список.
     *  Элементы могут быть любого типа.
     */
     public void addAll(Collection<? extends E> c) {
        if (c == null || c.isEmpty()) {
            System.out.println("пустая коллекция - ничего не добавлено");
            return;
        }
        for (E e : c) {
            add(e);
        }
    }


    /**
     * Добавляет элемент в список на указанный индекс (не заменяет0
     * Если индекс равен размеру списка, элемент добавляется в конец.
     * Проверяет индекс на валидность
     * Если массив заполнен, его емкость увеличивается.
     * Сдвигает все элементы после указанного индекса на одну позицию вправо,
     * чтобы освободить место для нового элемента.
     *
     * @param index   индекс, на который нужно добавить элемент.
     * @param element элемент, который нужно добавить в список
     * @throws IndexOutOfBoundsException если индекс выходит за пределы диапазона.
     */
    public void addElementOnIndex(int index, E element) {
        if (index == size) {
            add(element);
            return;
        }
        checkIndex(index);
        if (size + 1 >= capacity) {
            increaseCapacity();
        }
        for (int i = size - 1; i > index; i--) {
            E temp = elements[i];
            elements[i + 1] = temp;
            elements[i] = elements[i - 1];

        }
        //!!!!можно заменить предыдущий цикл на это
//        System.arraycopy(elements, index,
//                elements, index + 1,
//                size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Заменит элемент в списке по указанному индексу
     *
     * @param index   индекс элемента, на который нужно добавить элемент.
     * @param element элемент, который нужно добавить в список
     */
    public void changElement(int index, E element) {
        checkIndex(index);
        elements[index] = element;
    }

    /**
     * Возвращает элемент по указанному индексу.
     * Перед возвращением элемента проверяет индекс на валидность.
     * Выводит в консоль информацию об элементе и его индексе.
     *
     * @param index индекс элемента, который нужно найти в списке.
     * @return элемент, находящийся на указанном индексе
     * @throws IndexOutOfBoundsException если индекс выходит за пределы диапазона [0, size - 1].
     */
    public E findElemForIndex(int index) {
        checkIndex(index);
        System.out.println("Элемент с индексом " + index + " = " + elements[index]);
        return elements[index];
    }

    /**
     * Находит индекс элемента первого совпадения по значению
     * Если элемент найден, выводит его индекс в консоль
     * В случае отсутствия элемента в списке, ничего не выводится.
     * @return  возвращает true - если находит индекс элемента, false - если не находит
     * @param value значение элемента для поиска в списке
     */
    public boolean  findIndex(E value) {
        System.out.println("Поиск элемента по значению " + value);
        for (int i = 0; i < size; i++) {
            if (value.equals(elements[i])) {
                System.out.println(value + "- найден элемент с индексом: " + i);
                return true; // Добавлено для прекращения поиска после нахождения первого совпадения
            }
        }
        return false;
    }

    /**
     * Удаляет элемент из списка по указанному индексу.
     * Все последующие элементы сдвигаются на одну позицию влево, а размер списка уменьшается на единицу.
     * Если индекс выходит за пределы допустимого диапазона, генерируется исключение.
     *
     * @param index индекс удаляемого элемента.
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка.
     */
    public void removeByIndex(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
            elements[size] = null;
        }
        size--;
    }


    /**
     * Удаляет первое вхождение указанного элемента по значению  из списка.
     * Если элемент успешно найден и удален, возвращает true, иначе - false.
     *
     * @param removedEl элемент, который необходимо удалить из списка.
     * @return true, если элемент был удален, иначе false.
     */
    public boolean removeElement(E removedEl) {
        boolean result = false;
        if (removedEl == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    removeByIndex(i);
                    result = true;
                    break;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (removedEl.equals(elements[i])) {
                    removeByIndex(i);
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Очищает список, удаляя все элементы.
     * инициализируется новый массив объектов с вместимосью по умолчанию
     * устанавливает размер списка в ноль
     */
    public void clear() {
        elements = (E[]) new Object[capacity];

        size = 0;
    }


    /**
     * Сортирует список с использованием переданного компаратора.
     * Этот метод использует алгоритм быстрой сортировки для упорядочивания элементов.
     *
     * @param comparator Компаратор, определяющий порядок элементов.
     */
    public void sort1(Comparator<? super E> comparator) {
        quickSort(0, size - 1, comparator);
    }

    /**
     * Вспомогательный метод для выполнения быстрой сортировки  списка.
     * Разделяет список на две части вокруг опорного элемента и рекурсивно сортирует каждую часть.
     * Алгоритм:
     *      * выбирает опорный элемент в середине списка.
     *      *  Затем он разделяет элементы на две группы: меньше опорного и больше опорного.
     *      *  Элементы, меньшие опорного, перемещаются в левую часть, а большие - в правую.
     *      *  Процесс повторяется рекурсивно для каждой из двух созданных подгрупп до тех пор,
     *      *  пока весь диапазон не будет отсортирован.
     *
     * @param low        Нижний индекс диапазона для сортировки.
     * @param high       Верхний индекс диапазона для сортировки.
     * @param comparator Компаратор для сравнения элементов.
     */
    private void quickSort(int low, int high, Comparator comparator) {
        // Проверка условия выхода из рекурсии
        if (low >= high) {
            return;
        }
        // Выбор опорного элемента в середина списка
        int middle = low + (high - low) / 2;
        E support = elements[middle];

        // Инициализация границ для разделения списка
        int leftBound = low;
        int rightBound = high;

        // Перемещение элементов вокруг опорного элемента
        while (leftBound <= rightBound) {
            // Поиск элементов для обмена
            while (comparator.compare(elements[leftBound], support) < 0) {
                leftBound++;
            }
            while (comparator.compare(elements[rightBound], support) > 0) {
                rightBound--;
            }
            // Обмен элементов и сдвиг границ
            if (leftBound <= rightBound) {
                E temp = elements[leftBound];
                elements[leftBound] = elements[rightBound];
                elements[rightBound] = temp;
                leftBound++;
                rightBound--;
            }
        }
        // Рекурсивный вызов для сортировки поддиапазонов
        if (low < rightBound) {
            quickSort(low, rightBound, comparator);
        }
        if (high > leftBound) {
            quickSort(leftBound, high, comparator);
        }
    }

    /**
     * Выводит в консоль список всех элементов листа
     * Проверяет, если размер листа 0, то выводит в консоль запись об этом
     */
    public void showAllElements() {
        if (size == 0) System.out.println("Список пуст");
        else {
            System.out.print("[");
            for (int i = 0; i < size; i++) {
                if (i == size - 1) {
                    System.out.print(elements[i] + "]");

                } else {
                    System.out.print(elements[i] + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Возвращает размер емкости  массива элементов списка и выводит в консоль
     *
     * @return размер емкости массива элементов списка
     */
    public int capacity() {
        System.out.println(capacity);
        return capacity;
    }

    /**
     * Возвращает размер списка и выводит в консоль
     *
     * @return размер списка
     */
    public int size() {
        System.out.println(size);
        return size;
    }

    /**
     * Проверяет, находится ли индекс в допустимом диапазоне списка.
     *
     * @param index Индекс элемента для проверки.
     * @throws IndexOutOfBoundsException если индекс меньше нуля или больше или равен размеру списка.
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            System.out.println("индекса не существует");
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Увеличивает емкость массива элементов списка .
     * Метод вызывается, когда необходимо добавить больше элементов, чем позволяет текущая емкость.
     * Создается новый массив с увеличенной емкостью в заданное (MULTIPLIER) количество раз
     * В него  последовательно копируются все элементы из старого.
     * Индексы на старые элементы обнуляются
     */
    private void increaseCapacity() {
        capacity = (capacity * MULTIPLIER);
        E[] newElements = (E[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
            elements[i] = null;
        }
        elements = newElements;
    }

    public E[] getElements() {
        return elements;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

}
