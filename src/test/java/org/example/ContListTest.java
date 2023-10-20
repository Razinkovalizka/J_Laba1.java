package org.example;
import org.example.ContList;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContListTest {
    //Проверка на добавление элемента в пустой список
    @Test
    void testAddInEmptyList() {
        ContList<Integer> list = new ContList<>();
        list.add(78);
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertEquals(78, list.receive(0));
    }

    //Проверка на добавление элемента в заполненный список и проверка работы конструктора,
    // который принимает на вход массив
    @Test
    void testAddInFilledList() {

        Integer[] i = {1,2,6};
        ContList<Integer> list = new ContList<>(i);

        list.add(10);

        assertFalse(list.isEmpty());
        assertEquals(4, list.size());
        assertEquals(6, list.receive(2));
    }

    //Проверка на получение элемента из заполненного списка
    @Test
    void testReceiveFromFilledList() {
        ContList<Integer> list = new ContList<>();

        list.add(1);
        list.add(567);
        list.add(666);
        list.add(78);

        assertEquals(78, list.receive(3));
    }

    //Проверка на выпадение ошибки о нарушении границ списка при получении данных из списка
    @Test
    void testAtOutOfBoundsException() {
        ContList<Integer> list = new ContList<>();

        list.add(1);
        list.add(567);
        list.add(666);
        list.add(78);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.receive(4);
        });
    }

    //Проверка на удаление первого элемента списка
    @Test
    void testRemoveFirstElementFromFilledList() {
        ContList<Integer> list = new ContList<>();

        list.add(1);
        list.add(567);
        list.add(666);
        list.add(78);

        int size = list.size();

        list.remove(0);

        assertEquals(567, list.receive(0));
        assertEquals(size - 1, list.size());
    }

    //Проверка на удаление внутреннего элемента списка
    @Test
    void testRemoveInnerElementFromFilledList() {
        ContList<Integer> list = new ContList<>();

        list.add(1);
        list.add(567);
        list.add(666);
        list.add(78);
        int size = list.size();

        list.remove(1);

        assertEquals(666, list.receive(1));
        assertEquals(size - 1, list.size());
    }

    //Проверка на удаление последнего элемента списка
    @Test
    void testRemoveLastElementFromFilledList() {
        ContList<Integer> list = new ContList<>();

        list.add(1);
        list.add(567);
        list.add(666);
        list.add(78);

        int size = list.size();

        list.remove(3);

        assertEquals(666, list.receive(list.size() - 2));
        assertEquals(size - 1, list.size());
    }

    //Проверка пустого списка на пустоту
    @Test
    void testIsEmptyWithEmptyList() {
        ContList<Integer> list = new ContList<>();

        assertTrue(list.isEmpty());
    }

    //Проверка заполненного списка на пустоту
    @Test
    void testIsEmptyWithFilledList() {
        ContList<Integer> list = new ContList<>();

        list.add(1);
        list.add(567);
        list.add(666);
        list.add(78);

        assertFalse(list.isEmpty());
    }

    //Проверка на получение корректного размера пустого списка
    @Test
    void testSizeFromEmptyList() {
        ContList<Integer> list = new ContList<>();

        assertEquals(0, list.size());
    }

    //Проверка на получение корректного размера списка после добавления элемента
    @Test
    void testSizeAfterPushBack() {
        ContList<Integer> list = new ContList<>();

        list.add(1);
        list.add(567);
        list.add(666);
        list.add(78);

        int size = list.size();

        list.add(637);

        assertEquals(size + 1, list.size());
    }

    //Проверка на получение корректного размера списка после удаления элемента
    @Test
    void testSizeAfterRemove() {
        ContList<Integer> list = new ContList<>();

        list.add(1);
        list.add(567);
        list.add(666);
        list.add(78);

        int size = list.size();

        list.remove(1);

        assertEquals(size - 1, list.size());
    }

    //Проверка метода clear на пустом списке
    @Test
    void testClearWithEmptyList() {
        ContList<Integer> list = new ContList<>();

        int size = list.size();

        list.clear();

        assertEquals(size, list.size());
    }

    //Проверка метода clear на непустом списке
    @Test
    void testClearWithFilledList() {
        ContList<Integer> list = new ContList<>();

        list.add(1);
        list.add(567);
        list.add(666);
        list.add(78);

        int size = list.size();

        list.clear();

        assertEquals(size - 4, list.size());
    }

    //Проверка метода IsIt, если элемент есть в списке
    @Test
    void testIsItWith() {
        ContList<Integer> list = new ContList<>();

        list.add(1);
        list.add(567);
        list.add(666);
        list.add(78);

        assertEquals(true, list.IsIt(1));
    }

    //Проверка метода IsIt, если элемента нет в списке
    @Test
    void testIsItWithout() {
        ContList<Integer> list = new ContList<>();

        list.add(1);
        list.add(567);
        list.add(666);
        list.add(78);

        assertEquals(false, list.IsIt(9));
    }

    //Проверка метода swap
    @Test
    void testSwap() {
        ContList<Integer> list = new ContList<>();

        list.add(1);
        list.add(567);
        list.add(666);
        list.add(78);
        list.swap(2, 8);

        assertEquals(8, list.receive(2));
    }

    //Проверка метода deleteLike и проверка работы конструктора с одним параметром
    @Test
    void testDeleteLike() {
        ContList<Integer> list = new ContList<>(1);

        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);

        list.deleteLike(1);

        assertEquals(0, list.size());
    }
}