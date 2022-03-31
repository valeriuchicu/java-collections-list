package com.endava.internship.collections;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class StudentListTest {
    List<Student> studentList;

    @BeforeEach
    void setUp() {
        studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        studentList.add(st1);
        studentList.add(st2);
        studentList.add(st3);
    }

    @Test
    void constructorShouldThrowAnExceptionIfTheCapacityIsLessThenZero() {
        assertThrows(IllegalArgumentException.class, () -> new StudentList<Student>(-1));
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 8, 10})
    void shouldPassTestIfCapacityIsEqualsToInsertedNumberWhenWeInstantiateAnObject(int index) throws NoSuchFieldException, IllegalAccessException {
        List<Student> studentList = new StudentList<>(index);

        Field capacityField = StudentList.class.getDeclaredField("capacity");
        capacityField.setAccessible(true);

        Integer cf = (Integer) capacityField.get(studentList);
        assertEquals(10, cf);
    }

    @Test
    void shouldReturnTrueIfInsertedValueInTheConstructorIsEqualsToCapacityWhenWeInstantiateAnObject() throws NoSuchFieldException, IllegalAccessException {
        List<Student> studentList = new StudentList<>(15);

        Field capacityField = StudentList.class.getDeclaredField("capacity");
        capacityField.setAccessible(true);

        Integer cf = (Integer) capacityField.get(studentList);
        boolean isTheSameValue = cf == 15;
        assertTrue(isTheSameValue);
    }

    @Test
    void shouldReturnTheSizeEqualsToTheNumberOfObjectsInArrayWhenWeCallTheSizeMethod() {
        int listSize = studentList.size();

        assertEquals(listSize, 3);
    }

    @Test
    void shouldReturnTrueWhenTheListHasNoElements() {
        List<Student> studentList = new StudentList<>();
        assertTrue(studentList.isEmpty());
    }

    @Test
    void shouldReturnFalseWhenTheListHasElements() {
        assertFalse(studentList.isEmpty());
    }

    @Test
    void shouldReturnTrueWhenWeCallMethodContainsThatHasAsArgumentAnObjectThatExistInArray() {
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        studentList.add(st1);
        studentList.add(st2);
        boolean existSt1 = studentList.contains(st1);
        boolean existSt2 = studentList.contains(st2);

        assertTrue(existSt1);
        assertTrue(existSt2);
    }

    @Test
    void shouldReturnFalseWhenWeCallMethodContainsThatHasAsArgumentAnObjectThatExistInArray() {
        Student st4 = new Student("Patru", LocalDate.of(2006, 1, 1), "Student PATRU");
        boolean existSt3 = studentList.contains(st4);

        assertFalse(existSt3);
    }

    @Test
    void shouldReturnFalseIfTheMethodContainsHasAsArgumentNull() {
        boolean existSt = studentList.contains(null);

        assertFalse(existSt);
    }

    @Test
    void shouldReturnTrueIfTheMethodContainsHasAsArgumentAnObject() {
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        boolean existSt = studentList.contains(st1);

        assertTrue(existSt);
    }

    @Test
    void shouldReturnTrueIfTheIteratorHasNext() {
        Iterator<Student> iterator = studentList.iterator();

        assertTrue(iterator.hasNext());
    }

    @Test
    void shouldThrowNoSuchElementExceptionWhenNextDoesNotExist() {
        List<Student> studentList = new StudentList<>();
        Iterator<Student> iterator = studentList.iterator();

        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    @Test
    void shouldConfirmIfTheObjectStillExistsInTheListWhenWeCallRemoveMethod() {
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Iterator<Student> iterator = studentList.iterator();
        iterator.remove();

        assertThat(studentList).hasSize(2)
                .contains(st2)
                .doesNotContain(st1);
    }

    @Test
    void shouldRemoveTheNextElementAfterNextWhenWeCallRemoveAfterNext() {
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Iterator<Student> iterator = studentList.iterator();
        iterator.next();
        iterator.remove();

        assertThat(studentList).hasSize(2)
                .contains(st2)
                .doesNotContain(st1);
    }

    @Test
    void shouldConvertTheListToArrayWhenWeCallMethodToArray() {
        List<Student> list = new ArrayList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        list.add(st1);
        list.add(st2);
        list.add(st3);

        Object[] actualArrayOfStudents = studentList.toArray();
        Object[] expectedArrayOfStudents = list.toArray();

        assertArrayEquals(actualArrayOfStudents, expectedArrayOfStudents);
    }

    @Test
    void shouldReturnRemovedObjectWhenWeCallRemoveMethodWithArgument() {
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student removedStudent = studentList.remove(1);

        assertEquals(st2, removedStudent);
    }

    @Test
    void shouldNotContainRemovedObjectWhenWeCallMethodRemoveWithArgument() {
        Student removedStudent = studentList.remove(1);
        boolean isContainTheStudent = studentList.contains(removedStudent);

        assertFalse(isContainTheStudent);
    }

    @Test
    void shouldThrowIndexOutOfBoundsExceptionWhenTheIndexDoesNotExist() {
        assertThrows(IndexOutOfBoundsException.class, () -> studentList.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> studentList.remove(3));
    }

    @Test
    void shouldRemoveObjectFromListWhenWeCallMethodRemove() {
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");

        assertTrue(studentList.remove(st2));
    }

    @Test
    void shouldNotRemoveObjectFromListWhenWeCallRemoveMethodBecauseItDoesNotExistThere() {
        Student st4 = new Student("Patru", LocalDate.of(2006, 1, 1), "Student FOUR");

        assertFalse(studentList.remove(st4));
    }

    @Test
    void shouldClearTheListWhenWeCallTheMethodClear() {
        studentList.clear();

        assertEquals(studentList.size(), 0);
    }

    @Test
    void shouldReturnTrueWhenWeCallGetMethodWithTheIndexCorrespondingArgumentOfTheObject() {
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        boolean isTheRightObject = st2.equals(studentList.get(1));

        assertTrue(isTheRightObject);
    }

    @Test
    void shouldThrowAnIndexOutOfBoundsExceptionWhenWeCallMethodGetWithNonExistingIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> studentList.get(3));
        assertThrows(IndexOutOfBoundsException.class, () -> studentList.get(-1));
    }

    @Test
    void shouldSetTheObjectAtTheGivenIndexWhenWeCallMethodSet() {
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        Student setStudent = studentList.set(1, st3);

        assertEquals(studentList.get(1), setStudent);
    }

    @Test
    void shouldThrowIndexOutOfBoundsExceptionWhenWeCallMethodSetWithNonExistingIndex() {
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");

        assertThrows(IndexOutOfBoundsException.class, () -> studentList.set(3, st3));
        assertThrows(IndexOutOfBoundsException.class, () -> studentList.set(-1, st3));
    }

    @Test
    void shouldAddTheObjectAtTheIndicatedIndexWhenWeCallTheMethodAdd() {
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        studentList.add(1, st3);

        assertEquals(st3, studentList.get(1));
    }

    @Test
    void shouldResizeTheArrayIfTheIndexIsEqualsToSizeWhenWeCallTheMethodAdd() {
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        studentList.add(st3);
        studentList.add(st3);
        studentList.add(st3);
        studentList.add(st3);
        studentList.add(st3);
        studentList.add(st3);
        studentList.add(st3);
        studentList.add(10, st3);

        assertEquals(st3, studentList.get(10));
        assertEquals(11, studentList.size());
    }

    @Test
    void shouldResizeTheArrayIfTheIndexIsEqualsToSizeWhenWeCallTheMethodAddAddAll() {
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        studentList.add(st3);
        studentList.add(st3);
        studentList.add(st3);
        studentList.add(st3);
        studentList.add(st3);
        studentList.add(st3);
        studentList.addAll(Arrays.asList(st3,st3));
        boolean isTheSizeMoreThanCapacityAndEqualsToTheNumberOfElementsInArray = 11 == studentList.size();

        assertTrue(isTheSizeMoreThanCapacityAndEqualsToTheNumberOfElementsInArray);
    }

    @Test
    void shouldThrowIndexOutOfBoundsExceptionWhenWeTryToAddAnObjectAtNonExistingIndex() {
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");

        assertThrows(IndexOutOfBoundsException.class, () -> studentList.add(-1, st3));
        assertThrows(IndexOutOfBoundsException.class, () -> studentList.add(4, st3));
    }

    @Test
    void shouldPassTheTestIdGivenObjectIsAtTheCorrectIndexWhenWeCallIndexOf() {
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");

        assertThat(studentList.indexOf(st2)).isEqualTo(1);
        assertThat(studentList.indexOf(st2)).isNotEqualTo(2);
    }

    @Test
    void shouldNotPassTheTestWhenWeCallIndexOfWithAnObjectWhichDoesNotExistInTheList() {
        Student st4 = new Student("Patru", LocalDate.of(2006, 1, 1), "Student FOUR");

        assertThat(studentList.indexOf(st4)).isEqualTo(-1);
    }

    @Test
    void shouldReturnTheLastIndexOfTheObjectWhenWeCallLastIndexOf() {
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        studentList.add(st1);
        studentList.add(st2);
        studentList.add(st1);
        studentList.add(st3);

        assertEquals(2, studentList.lastIndexOf(st1));
    }

    @Test
    void shouldReturnMinusOneIfTheListDoesNotContainsTheObjectWhenWeCallMethodLastIndexOf() {
        List<Student> studentList = new StudentList<>();
        Student st4 = new Student("Patru", LocalDate.of(2006, 1, 1), "Student FOUR");

        assertEquals(-1, studentList.lastIndexOf(st4));
    }

    @Test
    void shouldStartFromTheIndexZeroWhenWeCallListIterator() {
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        boolean indexZero = st1.equals(studentList.listIterator().next());

        assertTrue(indexZero);
    }

    @Test
    void shouldStartFromTheGivenIndexWhenWeCallListIterator() {
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        boolean theListIteratorStartFromIndex = st2.equals(studentList.listIterator(1).next());

        assertTrue(theListIteratorStartFromIndex);
    }

    @Nested
    class testAllMethodsOfTheInnerClassListIterator {

        @Test
        void shouldStartFromTheGivenIndexWhenWeCallingIterator() {
            Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
            boolean indexOne = st2.equals(studentList.listIterator(1).next());

            assertTrue(indexOne);
        }

        @Test
        void shouldReturnTrueWhenWeCallHasPreviousAndPreviousObjectExists() {
            ListIterator<Student> listIterator = studentList.listIterator();
            listIterator.next();

            assertTrue(listIterator.hasPrevious());
        }

        @Test
        void shouldReturnFalseWhenWeCallasPreviousAndWeDoNotCallNextMethod() {
            ListIterator<Student> listIterator = studentList.listIterator();

            assertFalse(listIterator.hasPrevious());
        }

        @Test
        void shouldReturnPreviousElementWhenWeCallMethodPrevious() {
            Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
            ListIterator<Student> listIterator = studentList.listIterator();
            listIterator.next();

            assertEquals(st1, listIterator.previous());
        }

        @Test
        void shouldReturnNoSuchElementExceptionWhenWeCallPreviousMethodAndPreviousObjectDoesNotExist() {
            ListIterator<Student> listIterator = studentList.listIterator();

            assertThrows(NoSuchElementException.class, () -> listIterator.previous());
        }

        @Test
        void shouldReturnNextIndexWhenWeCallNextIndex() {
            ListIterator<Student> listIterator = studentList.listIterator();
            listIterator.next();

            assertEquals(1, listIterator.nextIndex());
        }

        @Test
        void shouldReturnPreviousIndexWhenWeCallPreviousIndexMethod() {
            ListIterator<Student> listIterator = studentList.listIterator();
            listIterator.next();

            assertEquals(0, listIterator.previousIndex());
        }

        @Test
        void shouldThrowsIndexOutOfBoundsExceptionWhenWeCallPreviousIndexWhenThereIsNoIndex() {
            ListIterator<Student> listIterator = studentList.listIterator();

            assertThrows(IndexOutOfBoundsException.class, () -> listIterator.previousIndex());
        }

        @Test
        void shouldReplaceTheLastElementReturnedByNextWhenWeCallMethodSet() {
            Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
            ListIterator<Student> listIterator = studentList.listIterator();

            listIterator.next();
            listIterator.set(st3);
            listIterator.next();
            listIterator.set(null);

            assertEquals(st3, studentList.get(0));
            assertNull(studentList.get(1));
        }

        @Test
        void shouldThrowsNoSuchElementExceptionWhenWeCallSetOnTheEmptyList() {
            Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
            ListIterator<Student> listIterator = studentList.listIterator();

            assertThrows(NoSuchElementException.class, () -> listIterator.set(st1));
        }

        @Test
        void shouldAddTheElementAtTheIndexZeroWhenWeCallAddMethodWithoutCallingNextMethod() {
            List<Student> studentList = new StudentList<>();
            Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
            Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
            Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
            Student st4 = new Student("Patru", LocalDate.of(2008, 1, 1), "Student FOUR");
            studentList.add(st1);
            studentList.add(st2);
            studentList.add(st3);
            ListIterator<Student> listIterator = studentList.listIterator();

            listIterator.add(st4);

            assertEquals(st4, studentList.get(0));
            assertEquals(st1, studentList.get(1));
            assertEquals(st2, studentList.get(2));
            assertEquals(st3, studentList.get(3));
        }

        @Test
        void shouldCheckIfAddedObjectIsAddedAtTheRigntIndexWhenWeCallMethodAdd() {
            Student st4 = new Student("Patru", LocalDate.of(2008, 1, 1), "Student FOUR");
            ListIterator<Student> listIterator = studentList.listIterator();
            listIterator.next();
            listIterator.next();
            listIterator.next();
            listIterator.add(st4);

            assertEquals(st4, studentList.get(3));
        }
    }

    @Test
    void shouldCheckIfTheListIsRightWhenWeCallMethodSubList() {
        List<Student> studentList1 = new StudentList<>();
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        Student st4 = new Student("Patru", LocalDate.of(2008, 1, 1), "Student FOUR");
        Student st5 = new Student("CINCI", LocalDate.of(2000, 1, 1), "Student FIVE");
        studentList.add(st4);
        studentList.add(st5);

        studentList1.add(st2);
        studentList1.add(st3);
        studentList1.add(st4);

        List<Student> actual = studentList.subList(1, 4);
        assertThat(actual).hasSameElementsAs(studentList1);
    }

    @Test
    void shouldThrowIndexOutOfBoundsExceptionWhenWeCallSubListWithNonExistingIndexes() {
        Student st4 = new Student("Patru", LocalDate.of(2008, 1, 1), "Student FOUR");
        Student st5 = new Student("CINCI", LocalDate.of(2000, 1, 1), "Student FIVE");
        studentList.add(st4);
        studentList.add(st5);

        assertThrows(IndexOutOfBoundsException.class, () -> studentList.subList(-1, 4));
        assertThrows(IndexOutOfBoundsException.class, () -> studentList.subList(1, 6));
    }

    @Test
    void shouldAddACollectionToTheActualListWhenWeCallAddAllMethod() {
        List<Student> studentList = new StudentList<>();
        List<Student> studentListA = new StudentList<>();
        List<Student> studentListE = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        studentList.add(st1);
        studentList.add(st2);
        studentList.add(st3);

        studentListA.add(st1);
        studentListA.add(st2);
        studentListA.add(st3);

        studentListE.add(st1);
        studentListE.add(st2);
        studentListE.add(st3);
        studentListE.add(st1);
        studentListE.add(st2);
        studentListE.add(st3);

        boolean isAdded = studentListA.addAll(studentList);
        assertArrayEquals(studentListE.toArray(), studentListA.toArray());
        assertTrue(isAdded);
    }

    @Test
    void shouldThrowNullPointerExceptionWhenWeCallMethodToArrayWithANullArgument() {
        Object[] test = null;
        assertThrows(NullPointerException.class, () -> studentList.toArray(test));
    }

    @Test
    void shouldCheckIfReturnedArrayIsEqualsToTheListWhenWeCallToArrayMethod() {
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        Student[] expectedArray = {st1, st2, st3};
        Student[] serviceArray1 = {st1, st2, st3};
        Student[] serviceArray2 = new Student[3];
        assertArrayEquals(expectedArray, studentList.toArray(serviceArray1));
        assertArrayEquals(expectedArray, studentList.toArray(serviceArray2));
    }

    @Test
    void shouldCheckIdTheToArrayMethodPutNullIfTheArrayIsGreaterThanSizeOfTheListWhenWeCallToArrayMeghod() {
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        Student st4 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        Student[] expectedArray = {st1, st2, st3, null};
        Student[] serviceArray2 = {st1, st2, st3, st4};
        assertArrayEquals(expectedArray, studentList.toArray(serviceArray2));
    }

    @Test
    void shouldReturnTrueIfCopyAndResizeTheArrayIfTheSizeOfListIsHigherWhenLengthOfArrayWhenWeCallToArrayMethod() {
       Object[] resultedArray = studentList.toArray(new Student[2]);
        assertTrue(3 == resultedArray.length);
    }

    @Test
    void shouldThrowUnsupportedOperationExceptionWhenWeCallContainsAllMethod() {
        assertThrows(UnsupportedOperationException.class, () -> studentList.containsAll(new StudentList<Student>()));
    }

    @Test
    void shouldThrowUnsupportedOperationExceptionWhenWeCallAddAllMethod() {
        assertThrows(UnsupportedOperationException.class, () -> studentList.addAll(1, new StudentList<>()));
    }

    @Test
    void shouldThrowUnsupportedOperationExceptionWhenWeCallRemoveAllMethod() {
        assertThrows(UnsupportedOperationException.class, () -> studentList.removeAll(new StudentList<Student>()));
    }

    @Test
    void ShouldThrowUnsupportedOperationExceptionWhenWeCallRetainAllMethod() {
        assertThrows(UnsupportedOperationException.class, () -> studentList.retainAll(new StudentList<Student>()));
    }

    @Test
    void shouldPassTheTestIfTheArrayIsResizedWhenWeCallResizeArrayMethod() {
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        Student st4 = new Student("Patru", LocalDate.of(2008, 1, 1), "Student FOUR");
        Student st5 = new Student("CINCI", LocalDate.of(2000, 1, 1), "Student FIVE");
        studentList.add(st4);
        studentList.add(st5);
        studentList.add(st1);
        studentList.add(st2);
        studentList.add(st3);
        studentList.add(st4);
        studentList.add(st5);
        studentList.add(st5);
        assertEquals(11, studentList.size());
    }
}