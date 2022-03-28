package com.endava.internship.collections;



import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class testStudentList {
    public static void main(String[] args) {

        StudentList<Student> list = new StudentList<>();
        StudentList<Student> newList = new StudentList<>();
//        StudentList<Student> list1 = new StudentList<>();
        List<Student> arrayList = new ArrayList<>();
        Object[] arrList = new Object[10];

        Student st1 = new Student("CINCI", LocalDate.of(2001, 01, 01), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 01, 01), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 01, 01), "Student TREE");
        Student st4 = new Student("Patru", LocalDate.of(2008, 01, 01), "Student FOUR");
        Student st10 = new Student("ZECE", LocalDate.of(2010, 01, 01), "Student TEN");
        Student st110 = new Student("110", LocalDate.of(2000, 01, 01), "Student 110");
        Student st5 = new Student("CINCI", LocalDate.of(2000, 01, 01), "Student FIVE");


        list.add(st1);
        list.add(st2);
        list.add(st3);
        list.add(st4);
//        list.add(null);
        list.add(st5);
//        list.add(null);

//        arrayList.add(st1);
//        arrayList.add(st2);
//        arrayList.add(st3);
//        arrayList.add(st4);
//        arrayList.add(st5);


//        list.add(st1);
//        list.add(st2);
//        list.add(st3);
//        list.add(st4);
//        list.add(st5);

//        newList.add(st1);
//        newList.add(st2);
//        newList.add(st3);
//        newList.add(st4);
//        newList.add(st5);

//        arrList[0] = st1;
//        arrList[1] = st2;
//        arrList[2] = st3;
//        arrList[3] = st4;
//        arrList[4] = st5;


//        System.out.println(list.get(0));

//        list.set(2, st3);
//
//        System.out.println(list.get(2));
//        System.out.println("The size of the list is " + list.size());
//
//        System.out.println("=========================================");
//        System.out.println("Is the our list empty? " + list.isEmpty());
//        System.out.println("Is the our list1 empty " + list1.isEmpty());
//
//        System.out.println("=========================================");
//        System.out.println("This list contains the given object? " + list.contains(st3));
//        System.out.println("This list contains the given object? " + list.contains(st10));
//        System.out.println("This list contains the given object? " + list.contains(null));
//
//        System.out.println("=========================================");
//        System.out.println("The object is in the location with the index " + list.indexOf(st10));//
//        System.out.println("The object is in the location with the index " + list.indexOf(null));//
//

//        System.out.println("=========================================");
//        System.out.println("The object is in the location with the index " + list.lastIndexOf(null));
//        System.out.println("The object is in the location with the index " + list.lastIndexOf(st4));


//        System.out.println(list.get(4));
//        System.out.println("The size of our list is: " + list.size());
//        System.out.println(list.get(2));


        // SUBLIST

//       List newSubList = list.subList(1, 4);
//        System.out.println("The size of the new list " + newSubList.size());
//        System.out.println(newSubList.get(0));
//        System.out.println(newSubList.get(1));
//        System.out.println(newSubList.get(2));
//
//        System.out.println();
//        System.out.println("The size of our list is: " + list.size());
//        System.out.println(list.get(4));

        // REMOVE return Object
//        System.out.println("================================================================================");
//        list.remove(4);
//        System.out.println("The size of our list is: " + list.size());
//        System.out.println(list.get(4));


//        for (Student st: list) {
//
//            System.out.println(list);
//        }

//        System.out.println("ARRAY LIST");
//
//        listList.add(st1);
//        listList.add(st3);
//        listList.add(st2);
//        listList.add(st4);
//        listList.add(st10);
//
//        System.out.println(listList.get(5));

        // REMOVE return BOOLEAN
//        System.out.println("================================================================================");
//        list.remove(st10);
////        System.out.println(list.remove(st10));
//        System.out.println("The size of our list is: " + list.size());
//        System.out.println(list.get(4));

//        CLEAR
//        System.out.println("================================================================================");
//        System.out.println(list.get(0));
//        System.out.println(list.get(1));
//        System.out.println(list.get(2));
//        System.out.println(list.get(3));
//        System.out.println(list.get(4));
//        System.out.println("================================================================================");
//        list.clear();
//        System.out.println(list.get(0));
//        System.out.println(list.get(1));
//        System.out.println(list.get(2));
//        System.out.println(list.get(3));
//        System.out.println(list.get(4));
//        System.out.println("================================================================================");

//        Object[] toArray()
//        System.out.println("================================================================================");
//        Object[] str = new Object[list.size()];
//        str = list.toArray();
//        for (int i = 0; i < str.length; i++) {
//            System.out.println(str[i]);
//        }
//        System.out.println("================================================================================");


//        Object[] toArray() WITH PARAMETER
//        System.out.println("================================================================================");
//        Object[] str = new Object[4];
//        Object[] strRes = list.toArray(str);
////        Object[] strRes = arrayList.toArray(str);
//        for (int i = 0; i < strRes.length; i++) {
//            System.out.println(strRes[i]);
//        }
//        System.out.println("================================================================================");
//
        //   ADD
//        System.out.println("================================================================================");
//        list.add(st110);
//        System.out.println(list.get(list.size()-1));
//        System.out.println("================================================================================");

        // REMOVE
//        System.out.println("================================================================================");
//        System.out.println(list.remove(st2));
//        System.out.println(list.get(1));
//        System.out.println("================================================================================");
//
//         CLEAR
//        System.out.println("================================================================================");
//        list.clear();
//        System.out.println(list.size());
//        System.out.println(list.get(0));
//        System.out.println("================================================================================");

        //   GET
//        System.out.println("================================================================================");
////        System.out.println(list.get(-1));
//        System.out.println(list.get(0));
//        System.out.println(list.get(2));
//        System.out.println(list.get(4));
////        System.out.println(list.get(5));
//        System.out.println("===========s=====================================================================");

//        //   SET
//        System.out.println("================================================================================");
//        System.out.println(list.set(0, st5));
//        System.out.println(list.set(4, null));
//        System.out.println("================================================================================");


        //   ADD(int i, T t)
//        System.out.println("================================================================================");
////        System.out.println(list.get(4));
//            list.get(4);
//        list.add(4, st1);
//        for (int i = 0; i <= list.size(); i++) {
//        System.out.println(list.get(i));
//        }
////        System.out.println(list.set(4, null));
//        System.out.println("================================================================================");
//

//      REMOVE(int i)
//        System.out.println("================================================================================");
//        list.remove(2);
//        for (int i = 0; i < list.size(); i++) {
//        System.out.println(list.get(i));
//        }
//        System.out.println("================================================================================");

        //      REMOVE(Object o)
//        System.out.println("================================================================================");
//        list.remove(st3);
//        for (int i = 0; i < list.size(); i++) {
//        System.out.println(list.get(i));
//        }
//        System.out.println("================================================================================");

//        INDEXOF
//        System.out.println("================================================================================");
//        System.out.println(list.indexOf(st1));
//        System.out.println(list.indexOf(st2));
//        System.out.println(list.indexOf(st3));
//        System.out.println(list.indexOf(st4));
//        System.out.println(list.indexOf(st5));
//        System.out.println(list.indexOf(null));
//        System.out.println("================================================================================");

        //        LastIndexOf
//        System.out.println("================================================================================");
//        System.out.println(list.lastIndexOf(st1));
//        System.out.println(list.lastIndexOf(st2));
//        System.out.println("================================================================================");

//        //        LastIndexOf
//        System.out.println("================================================================================");
//        System.out.println(list.lastIndexOf(st1));
//        System.out.println(list.lastIndexOf(st2));
//        System.out.println("================================================================================");


//        ADD
//        System.out.println("================================================================================");
//        System.out.println(list.size());
//        System.out.println(list.add(st5));
//        list.add(st5);
//        System.out.println(list.size());
//        System.out.println("================================================================================");


        // SUBLIST
//        System.out.println("================================================================================");
////        System.out.println(list.subList(0, 3));
//        List<Student> nn = list.subList(0, 4);
//        System.out.println(Arrays.toString(nn.toArray()));
//        System.out.println("================================================================================");



        // addAll DOES NOT WORK BECAUSE I HAVE TO IMPLEMENT ITERATOR
//        System.out.println("================================================================================");
//        System.out.println("list " + list.size());
//        list.addAll(newList);
//        System.out.println("newList " + newList.size());
//        System.out.println("================================================================================");

        // Iterator<T> iterator()
//        System.out.println("================================================================================");
//        Iterator<Student> iterator = list.iterator();
//
//            while (iterator.hasNext()){
//                    Student element = iterator.next();
//                    System.out.print(element + " \n");
//            }
//        System.out.println("================================================================================");
//
//
//        LISTITERATOR(INT i)
//        System.out.println("================================================================================");
//        ListIterator<Student> listIteratorN = list.listIterator(2);
//        System.out.println("All the next items");
//            while (listIteratorN.hasNext()){
//                Student element = listIteratorN.next();
//                System.out.print(element + " \n");
//            }
//        System.out.println("================================================================================");
//        System.out.println("All the previous items");
//        ListIterator<Student> listIteratorP = list.listIterator(2);
//        while (listIteratorP.hasPrevious()){
//            Student element = listIteratorP.previous();
//            System.out.print(element + " \n");
//        }
//        System.out.println("================================================================================");
//
//
//        COMPARE TO
//        System.out.println("================================================================================");
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
//
//        System.out.println("================================================================================");
//        System.out.println("================================================================================");
//        System.out.println("================================================================================");
//
//        Comparator<Student> comparator = (st11, st22) -> st11.compareTo(st22);
//
//        list.sort(comparator);
//
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
//
//        System.out.println("================================================================================");
        list.resizeArray();

//                                      DONE !!!!!
    }
}


//for (int i = 0; i < size; i++) {
//        if (Objects.isNull(elements[i]) || (t != null || t.equals(elements[i]))) {
//        return true;
//        }
//        }