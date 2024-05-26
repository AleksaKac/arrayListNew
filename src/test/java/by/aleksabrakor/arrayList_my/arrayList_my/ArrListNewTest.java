package by.aleksabrakor.arrayList_my.arrayList_my;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ArrListNewTest <E>{
    private ArrListNew<Integer> arrListIntegerTest;
    private ArrListNew<String> arrListStringTest;


    @BeforeEach
    void setUp() {
       arrListIntegerTest = new ArrListNew<>();
        arrListStringTest = new ArrListNew<>();
        System.out.println("Начало теста");

    }

    @AfterEach
    void tearDown() {
      arrListIntegerTest = null;
        System.out.println("Конец теста");
        System.out.println("-------------------");
    }

    @DisplayName("Тест  конструктора  с заданной начальной емкостью ")
    @Test
    void testConstructorWithCapacity() {
        ArrListNew arrListIntegerTest1 = new ArrListNew<>(100);

        Assertions.assertEquals(0, arrListIntegerTest1.size());
        Assertions.assertEquals(100, arrListIntegerTest1.capacity());

    }

    @DisplayName("Тест конструктора с добавлением  множество элементов ")
    @Test
    void testConstructorWithElements() {
        ArrListNew arrListIntegerTest1 = new ArrListNew<>(1,78,54,784,4,5,7,88);

        Assertions.assertEquals(8, arrListIntegerTest1.size());
        Assertions.assertEquals(10, arrListIntegerTest1.capacity());

    }

    @DisplayName("Тест конструктора с добавлением  множество элементов ")
    @Test
    void testConstructorWithCollection() {
        List<String> arr = List.of("a", "b");
        ArrListNew arrListStringTest1 = new ArrListNew<>();
        arrListStringTest1.addAll(arr);
        arrListStringTest1.showAllElements();

        Assertions.assertEquals(2, arrListStringTest1.size());
        Assertions.assertEquals(10,arrListStringTest1.capacity());
        Assertions.assertEquals("a",arrListStringTest1.findElemForIndex(0));

    }

    @DisplayName("Тест на добавление элементов в лист с помощью цикла")
    @ParameterizedTest
    @CsvSource(value = {
            "50",
            "1000000"
    })
    void add(int expectedCount) {
        for (int i =0; i <expectedCount; i++) {
            arrListIntegerTest.add(i);
        }
        Assertions.assertEquals(expectedCount, arrListIntegerTest.size());
    }

    @DisplayName("Тест  на добавление массива элементов в лист ")
    @Test
    void testAddAll1() {
        Integer[] array = {1,2,3,4,5};
        arrListIntegerTest.addAll(array);
        arrListIntegerTest.addAll(array);
        arrListIntegerTest.showAllElements();
        Assertions.assertEquals(10, arrListIntegerTest.size());
        Assertions.assertEquals(10, arrListIntegerTest.capacity());
    }

    @DisplayName("Тест  на добавление множества элементов в лист ")
    @Test
    void testAddAll2() {
        arrListIntegerTest.addAll(1,5,7,9,48,78);
        arrListIntegerTest.showAllElements();
        Assertions.assertEquals(6, arrListIntegerTest.size());
        Assertions.assertEquals(10, arrListIntegerTest.capacity());
    }

    @DisplayName("Тест  на добавление java коллекции элементов в лист ")
    @Test
    void testAddAllCollection() {
        List<Integer> list = List.of(1,5,457,45,78,45,45,78,98,78,5877,25,45,78,78,258,1,5,457,45,78,45,45,78,98,78,5877,25,45,78,78,258);
        arrListIntegerTest.addAll(list);
        arrListIntegerTest.showAllElements();
        Assertions.assertEquals(32, arrListIntegerTest.size());
        Assertions.assertEquals(40, arrListIntegerTest.capacity());
    }

    @DisplayName("Тест вставки элемента  по индексу (не заменяет)")
    @Test
    void testAddElementOnIndex() {
        arrListIntegerTest.addAll(1,21,45,7,8,78,9);
        arrListIntegerTest.showAllElements();
        arrListIntegerTest.addElementOnIndex(0,158);
        arrListIntegerTest.addElementOnIndex(5,158);
        arrListIntegerTest.showAllElements();
        Assertions.assertEquals(9, arrListIntegerTest.size());
        Assertions.assertEquals(10, arrListIntegerTest.capacity());
        Assertions.assertEquals(9, arrListIntegerTest.findElemForIndex(arrListIntegerTest.getSize()-1));
        if(arrListIntegerTest.findElemForIndex(0)!=158){
           Assertions.fail();
        }
        if(arrListIntegerTest.findElemForIndex(5)!=158){
            Assertions.fail();
        }
        if(arrListIntegerTest.findElemForIndex(6)!=8){
            Assertions.fail();
        }
    }

    @DisplayName("Тест замену элемента в списке по указанному индексу")
    @Test
    public void testChangElement(){
        arrListIntegerTest.addAll(1,21,45,7,8,78,9);
        arrListIntegerTest.changElement(0,789);
        // замена последнего элемента
        arrListIntegerTest.changElement(arrListIntegerTest.size()-1,222);
        arrListIntegerTest.showAllElements();
        Assertions.assertEquals(7, arrListIntegerTest.size());
        Assertions.assertEquals(10, arrListIntegerTest.capacity());
        Assertions.assertEquals(789,arrListIntegerTest.findElemForIndex(0));
        Assertions.assertEquals(222,arrListIntegerTest.findElemForIndex(arrListIntegerTest.size()-1));
    };

    @DisplayName("Тест поиска элемента по указанному индексу")
    @Test
   void testFindElemForIndex(){
        arrListIntegerTest.addAll(1,21,45,7,8,78,9);

        Assertions.assertEquals(9,arrListIntegerTest.findElemForIndex(arrListIntegerTest.size()-1));
        Assertions.assertEquals(1,arrListIntegerTest.findElemForIndex(0));
        Assertions.assertEquals(7,arrListIntegerTest.findElemForIndex(3));

    }

    @DisplayName("Тест поиска индексa элемента первого совпадения по значению")
    @Test
    void findIndex(){
        arrListIntegerTest.addAll(1,21,45,7,21,78,9);
       arrListIntegerTest.findIndex(21);
        arrListStringTest.addAll("aa","aa","cc","aa","bb","hi","ab");
        arrListStringTest.findIndex("hi");
        Assertions.assertEquals(true,arrListIntegerTest.findIndex(21));
        Assertions.assertEquals(false,arrListIntegerTest.findIndex(2221));
        Assertions.assertEquals(true,arrListStringTest.findIndex("hi"));
        Assertions.assertEquals(false,arrListStringTest.findIndex("gjkdsld"));

    }


    @DisplayName("Тест удаления элемента  первого и последнего  элемента")
    @Test
    void testRemoveByIndexFirstLastIndex() {
        arrListIntegerTest.addAll(1,21,45,7,8,78,9);
        arrListIntegerTest.removeByIndex(0);
        arrListIntegerTest.removeByIndex(arrListIntegerTest.getSize()-1);
        arrListIntegerTest.showAllElements();
        Assertions.assertEquals(10, arrListIntegerTest.capacity());
        //проверка, что последний элемент соответствует значению в листе, и при удалении пустой элемент удалился
        Assertions.assertEquals(78, arrListIntegerTest.findElemForIndex(arrListIntegerTest.getSize()-1));
        if(arrListIntegerTest.findElemForIndex(0)!=21){
            Assertions.fail();
        }
    }

    @DisplayName("Тест удаления элемента  по индексу последнего элемента")
    @Test
    void testRemoveByIndex() {
        arrListIntegerTest.addAll(1,21,45,7,8,78,9);
        arrListIntegerTest.removeByIndex(arrListIntegerTest.getSize()-1);
        arrListIntegerTest.showAllElements();
        Assertions.assertEquals(6, arrListIntegerTest.size());
        Assertions.assertEquals(10, arrListIntegerTest.capacity());
        Assertions.assertEquals(1, arrListIntegerTest.findElemForIndex(0));
        if(arrListIntegerTest.findElemForIndex(arrListIntegerTest.getSize()-1)!=78){
            Assertions.fail();
        }
    }

    @DisplayName("Тест добавление удаления элемента  по индексу в цикле ")
    @ParameterizedTest
    @CsvSource(value = {
            "10",
            "100"
    })
    void testRemoveByIndexMany(int expectedCount) {
        arrListIntegerTest.add(589);
        // добавили циклом указанное в параметрах кол элементов
        for (int i = 1; i <=expectedCount; i++) {
            arrListIntegerTest.add(i);
        }
        arrListIntegerTest.add(589); // добавили в конец списка
        arrListIntegerTest.showAllElements();
        Assertions.assertEquals(expectedCount+2, arrListIntegerTest.size());
     //удаляем циклом все элементы в списке
        for (int i = expectedCount+1; i >= 0 ; i--) {
            arrListIntegerTest.removeByIndex(i);
        }
        arrListIntegerTest.showAllElements();
        Assertions.assertEquals(0, arrListIntegerTest.size());

    }

    @DisplayName("Тест удаления элемента  по значению для Integer")
    @Test
    void testRemoveElementInt() {
        arrListIntegerTest.addAll(1,21,45,7,8,78,9);
        arrListIntegerTest.removeElement(45);
        arrListIntegerTest.showAllElements();
        Assertions.assertEquals(6, arrListIntegerTest.size());
        Assertions.assertEquals(10, arrListIntegerTest.capacity());
        Assertions.assertEquals(9, arrListIntegerTest.findElemForIndex(arrListIntegerTest.getSize()-1));
        if(arrListIntegerTest.findElemForIndex(2)!=7){
            Assertions.fail();
        }
    }

    @DisplayName("Тест удаления элемента  по значению для String")
    @Test
    void testRemoveElementStr() {
        arrListStringTest.addAll("aa","aa","cc","aa","bb","hi","ab");
        arrListStringTest.showAllElements();
        arrListStringTest.removeElement("hi");
        arrListStringTest.showAllElements();
        Assertions.assertEquals(6, arrListStringTest.size());
        Assertions.assertEquals(10, arrListStringTest.capacity());
        Assertions.assertEquals("ab", arrListStringTest.findElemForIndex(arrListStringTest.getSize()-1));

    }


    @DisplayName("Тест быстрой сортировки для Integer")
    @Test
    void testSortInt() {
        arrListIntegerTest.addAll(1,21,45,7,189,78,9);
         arrListIntegerTest.showAllElements();
       arrListIntegerTest.sort1(Comparator.naturalOrder());
        arrListIntegerTest.showAllElements();
        Assertions.assertEquals(1, arrListIntegerTest.findElemForIndex(0));
        Assertions.assertEquals(189, arrListIntegerTest.findElemForIndex(arrListIntegerTest.getSize()-1));

    }
    @DisplayName("Тест быстрой сортировки для String")
    @Test
    void testSortStr() {
        arrListStringTest.addAll("aa","aa","cc","aa","bb","hi","ab");
        arrListStringTest.showAllElements();
        arrListStringTest.sort1(Comparator.naturalOrder());
        arrListStringTest.showAllElements();
        Assertions.assertEquals("aa", arrListStringTest.findElemForIndex(0));
        Assertions.assertEquals("hi", arrListStringTest.findElemForIndex(arrListStringTest.getSize()-1));

    }

    @DisplayName("Тест  очистки списка")
    @Test
    void testClear() {
        arrListIntegerTest.addAll(1,21,45,7,189,78,9);
        arrListIntegerTest.showAllElements();
        arrListIntegerTest.clear();
        arrListIntegerTest.showAllElements();
        Assertions.assertEquals(0, arrListIntegerTest.size());
        Assertions.assertEquals(10, arrListIntegerTest.capacity());

    }

    @DisplayName("Тест  увеличения вместимости списка")
    @Test
    void testIncreaseCapacity() {
        for (int i =0; i <15; i++) {
            arrListIntegerTest.add(i);
        }
        arrListIntegerTest.showAllElements();
        Assertions.assertEquals(20, arrListIntegerTest.getCapacity());
    }

    @DisplayName("Тест что конструктор с отрицательной вместимостью выбрасывает IllegalArgumentException")
    @Test
    void testAddWithNegativeCapacity() {
       Assertions.assertThrows(IllegalArgumentException.class, () -> new ArrListNew<>(-1));
    }

    @DisplayName("Тест что вставка элемента по несуществующему индексу выбрасывает IndexOutOfBoundsException")
    @Test
    void testAddElementOnIndexWithInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrListIntegerTest.addElementOnIndex(1, 1));
    }

    @DisplayName("Тест  что удаление элемента по несуществующему индексу выбрасывает IndexOutOfBoundsException")
    @Test
    void testRemoveByIndexWithInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrListIntegerTest.removeByIndex(0));
    }

    @DisplayName("Тест  что замена элемента по несуществующему индексу выбрасывает IndexOutOfBoundsException")
    @Test
    void testChangElementWithInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrListIntegerTest.changElement(1, 1));
    }

    @DisplayName("Тест что получение элемента по несуществующему индексу выбрасывает IndexOutOfBoundsException")
    @Test
    void testFindElemForIndexWithInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrListIntegerTest.findElemForIndex(0));
    }

    @DisplayName("Тест  что удаление null из списка корректно обрабатывается")
    @Test
    void testRemoveElementWithNull() {
        arrListIntegerTest.addAll(1,2,3);
        assertFalse(arrListIntegerTest.removeElement(null));
        assertFalse( arrListIntegerTest.size()==5);
        assertEquals(3, arrListIntegerTest.size());
    }

    @DisplayName("Тест что попытка удалить несуществующий элемент не изменяет размер списка")
    @Test
    void testRemoveElementWithNonExistentElement() {
        arrListIntegerTest.addAll(1, 2, 3);
        assertFalse(arrListIntegerTest.removeElement(4));
        assertEquals(3, arrListIntegerTest.size());
    }

    @DisplayName("Тест что добавление null коллекции не изменяет список")
    @Test
    void testAddAllWithNullCollection() {
        arrListIntegerTest.addAll();
        assertEquals(0, arrListIntegerTest.size());
    }

    @DisplayName("Тест что добавление пустой коллекции не изменяет список.")
    @Test
    void testAddAllWithEmptyCollection() {
        arrListIntegerTest.addAll(Arrays.asList());
        assertEquals(0, arrListIntegerTest.size());
        assertEquals(10, arrListIntegerTest.capacity());
    }



}