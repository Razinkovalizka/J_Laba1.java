package org.example;

import java.util.Iterator;
/**
 * Класс ContList реализует односвязный список и его основные методы
 *
 * @param <T> тип данных, использующийся при работе с классом
 *
 * @author Razinkova_Elizaveta */
public class ContList<T> implements Iterable<T> {

    /**
     * Node - класс узлов, из которых состоит список.
     */
    private class Node <T>{
        private T value;
        private Node next;
        public Node(T thing){
            value = thing;
            next = null;
        }
    }
    private Node head;
    private int size;
    /**
     * Конструктор по умолчанию.
     *
     * @example <p>Пример использования конструктора:
     *      <pre>{@code
     *           ContList list = new ContList();
     *      }</pre>
     * */
    public ContList(){
        head = null;
        size = 0;
    }
    /**
     * Конструктор с одним параметром.
     *
     * @param other принимает любой тип данных
     *
     * @example <p>Пример использования конструктора:
     *      <pre>{@code
     *           ContList list = new ContList(1);
     *      }</pre>
     * */
    public ContList(T other){
        try {
            head = new Node<>(other);
            size = 1;
        }catch (NullPointerException e){
            System.err.println("Нельзя вставлять ноль!");
            head = null;
            size = 0;
        }
    }
    /**
     * Конструктор, который принимает на вход массив.
     *
     * @param array массив
     *
     * @example <p>Пример использования конструктора:
     *            <pre>{@code
     *                  Integer[] i = {31,62,37,666};
     *                  ContList<Integer> list = new ContList<>(i);
     *            }</pre>
     * */
    public ContList(T[] array){
        try{
            for(T i: array){
                add(i);
            }
        }catch(NullPointerException e){
            head = null;
            size = 0;
        }
    }
    @Override
    public Iterator<T> iterator() {
        return new ContListIterator();
    }
    private class ContListIterator implements Iterator<T> {
        private Node current = head;
        @Override
        public boolean hasNext() {
            return current != null;
        }
        @Override
        public T next() {
            T value = (T) current.value;
            current = current.next;
            return value;
        }
    }
    /**
     * Метод add для добавления значения в список.
     *
     * @param thing элемент, который требуется добавить
     * */
    public void add(T thing){
        if(head!= null){
            Node h1 = head;
            while(h1.next != null){
                h1 = h1.next;
            }
            h1.next = new Node<>(thing);
        }
        else{
            head = new Node<>(thing);
        }
        size += 1;
    }
    /**
     * Метод IsIt проверяет, содержит ли список необходимый элемент.
     *
     * @param thing искомый элемент
     *
     * @return True, если искомый элемент содержится в списке. False, если такого элемента нет
     **/
    public boolean IsIt(T thing){
        Node h1 = head;
        do{
            if(h1.value == thing){
                return true;
            }
            h1 = h1.next;
        } while (h1.next != null);
        return false;
    }
    /**
     * Метод receive для получения хранящегося в списке значения по индексу.
     * @param index индекс, по которому находится значение
     * @return Значение найденного элемента
     */
    public T receive(int index) {
        if (index < 0 || index > size - 1) {
            System.out.println("OutOfIndexExceptionDemo!");
        }

        Node<T> curNode = head;
        int i = 0;

        while (i != index) {
            curNode = curNode.next;
            ++i;
        }

        return curNode.value;
    }

    /**
     * Метод swap для замены элемента по указанному индексу новым элементом.
     *
     * @param index индекс элемента, который необходимо заменить.
     *
     * @param var значение, которое необходимо вставить вместо прежнего.
     * */
    public void swap(int index, T var){
        if(index < size){
            int i = 0;
            Node h1 = head;
            while(i <= index){
                h1 = h1.next;
                i++;
            }
            h1.value = var;
        }else {
            System.out.println("Невозможно выполнить это действие! ");
        }
    }

    /**
     * Метод remove для удаления элемента в списке по индексу.
     *
     * @param index индекс элемента, который требуется удалить
     * */
    public void remove (int index) {
        if (index <= size) {
            if (index == 0) {
                head = head.next;
            } else {
                Node current = head;
                for (int i = 0; i < index - 1; i++) {
                    current = current.next;
                }
                current.next = current.next.next;
            }
        } else {
            System.out.println("OutOfIndexExceptionDemo!");
        }
    }

    /**
     * Метод deleteLike для удаления всех переменных в списке,
     * значение которых совпадает со значением, указанном в аргументе метода.
     *
     * @param thing значение, которое будет искать метод*/
    public void deleteLike (T thing){
        int count = 0;
        int countOfDeleted = 0;
        if(head != null){
            Node current = head;
            Node prev = null;
            do{
                if(current.value != thing){
                    prev = current;
                    current = current.next;
                }
                else {
                    if(prev != null) {
                        prev.next = current.next;
                        current = current.next;
                    }
                    else{
                        head = head.next;
                    }
                    countOfDeleted += 1;
                }
            }while(current != null);
            if(countOfDeleted != 0){
                System.out.println("Удалено " + countOfDeleted + " переменных!");
            }else{
                System.out.println("В списке нет таких значений!");
            }
        }
        else{
            System.err.println("Список пуст!");
        }
    }

    /**
     * Метод printToString для вывода всех элементов списка в виде строки.
     * */
    public void printToString () {

        try {
            Node h1 = head;
            do{
                System.out.print(h1.value + " ");
                h1 = h1.next;
            } while (h1 != null);
            System.out.println();
        } catch (NullPointerException e) {
            System.out.println("Невозможно вывести список. Список пуст!");
        }
    }

    /**Метод size показывает текущий размер списка*/
    public int size () {
        return size;
    }

    /**Метод isEmpty для проверки списка на пустоту*/
    public boolean isEmpty () {
        return head == null;
    }

    /**Метод clear для обнуления переменных*/
    public void clear () {
        head = null;
        size = 0;
    }

}