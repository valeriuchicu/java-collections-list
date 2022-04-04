package com.endava.internship.collections;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
    void shouldThrowIllegalArgumentExceptionWithInvalidParameter() {
        Throwable exceptionThatWasThrown = assertThrows(IllegalArgumentException.class, () -> new StudentList<Student>(-1));
        assertEquals(exceptionThatWasThrown.getMessage(), "Illegal Capacity: " + "-1");
    }

    @Test
    void shouldReturnTheSizeEqualsToTree() {
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

    @ParameterizedTest
    @MethodSource("provideStudentForContains")
    void contains_ShouldReturnExpectedBooleans(Student student, boolean expected) {
        assertEquals(expected, studentList.contains(student));
    }

    private static Stream<Arguments> provideStudentForContains() {
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st4 = new Student("Patru", LocalDate.of(2006, 1, 1), "Student PATRU");
        return Stream.of(
                Arguments.of(st1, true),
                Arguments.of(st4, false),
                Arguments.of(null, false));
    }

    @Nested
    class TestAllMethodsOfTheInnerClassIterator {
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        @Test
        void shouldReturnTrueIfTheIteratorHasNext() {
            Iterator<Student> iterator = studentList.iterator();

            assertTrue(iterator.hasNext());
        }

        @Test
        void shouldThrowNoSuchElementExceptionWhenNextDoesNotExist() {
            List<Student> studentList = new StudentList<>(13);
            Iterator<Student> iterator = studentList.iterator();

            assertThrows(NoSuchElementException.class, () -> iterator.next());
        }

        @Test
        void shouldConfirmIfTheObjectExistsInTheList() {
            Iterator<Student> iterator = studentList.iterator();
            iterator.remove();

            assertThat(studentList).hasSize(2)
                    .contains(st2)
                    .doesNotContain(st1);
        }

        @Test
        void shouldRemoveTheNextElement() {
            Iterator<Student> iterator = studentList.iterator();
            iterator.next();
            iterator.remove();

            assertThat(studentList).hasSize(2)
                    .contains(st2)
                    .doesNotContain(st1);
        }
    }

    @Test
    void shouldConvertTheListToArray() {
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");

        Object[] actualArrayOfStudents = studentList.toArray();
        Object[] expectedArrayOfStudents = new Student[]{st1, st2, st3};

        assertArrayEquals(actualArrayOfStudents, expectedArrayOfStudents);
    }

    @Test
    void shouldReturnRemovedObject() {
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student removedStudent = studentList.remove(1);

        assertEquals(st2, removedStudent);
        assertFalse(studentList.contains(removedStudent));
    }

    @Test
    void shouldThrowIndexOutOfBoundsExceptionWhenTheIndexDoesNotExist() {
        assertThrows(IndexOutOfBoundsException.class, () -> studentList.remove(-1));
        Throwable exceptionThatWasThrown = assertThrows(IndexOutOfBoundsException.class, () -> studentList.remove(3));

        assertEquals(exceptionThatWasThrown.getMessage(), "Insertion index was out of range. Must be non-negative and less than size");
    }

    @ParameterizedTest
    @MethodSource("provideStudentForRemove")
    void remove_ShouldReturnExpectedBooleans(Student student, boolean expected) {
        assertEquals(expected, studentList.remove(student));
    }

    private static Stream<Arguments> provideStudentForRemove() {
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st4 = new Student("Patru", LocalDate.of(2006, 1, 1), "Student PATRU");
        return Stream.of(
                Arguments.of(st2, true),
                Arguments.of(st4, false));
    }

    @Test
    void shouldClearTheList() {
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
    void shouldThrowIndexOutOfBoundsExceptionWhenGetIndexIsInvalid() {
        assertThrows(IndexOutOfBoundsException.class, () -> studentList.get(3));
        Throwable exceptionThatWasThrown = assertThrows(IndexOutOfBoundsException.class, () -> studentList.get(-1));

        assertEquals(exceptionThatWasThrown.getMessage(), "Index should be greater then or equal to 0 and less then " + "3");
    }

    @Test
    void shouldSetTheObjectAtTheGivenIndex() {
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        Student setStudent = studentList.set(1, st3);

        assertEquals(studentList.get(1), setStudent);
    }

    @Test
    void shouldThrowIndexOutOfBoundsExceptionWhenSetIndexIsInvalid() {
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");

        assertThrows(IndexOutOfBoundsException.class, () -> studentList.set(3, st3));
        Throwable exceptionThatWasThrown = assertThrows(IndexOutOfBoundsException.class, () -> studentList.set(-1, st3));
        assertEquals(exceptionThatWasThrown.getMessage(), "Insertion index was out of range. Must be non-negative and less than size");
    }

    @Test
    void shouldAddTheObjectAtTheIndicatedIndex() {
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        studentList.add(1, st3);

        assertEquals(st3, studentList.get(1));
    }

    @Test
    void shouldAddObjectAtTheGivenIndex() {
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
    void shouldAddAllObjects() {
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        studentList.add(st3);
        studentList.add(st3);
        studentList.add(st3);
        studentList.add(st3);
        studentList.add(st3);
        studentList.add(st3);
        studentList.addAll(Arrays.asList(st3, st3));
        boolean isTheSizeMoreThanCapacityAndEqualsToTheNumberOfElementsInArray = 11 == studentList.size();

        assertTrue(isTheSizeMoreThanCapacityAndEqualsToTheNumberOfElementsInArray);
    }

    @Test
    void shouldThrowIndexOutOfBoundsExceptionWhenAddIndexIsInvalid() {
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");

        assertThrows(IndexOutOfBoundsException.class, () -> studentList.add(-1, st3));
        Throwable exceptionThatWasThrown = assertThrows(IndexOutOfBoundsException.class, () -> studentList.add(4, st3));
        assertEquals(exceptionThatWasThrown.getMessage(), "Insertion index was out of range. Must be non-negative and less than or equal to size");
    }

    @Test
    void shouldGiveTheCorrectIndexOfTheObject() {
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");

        assertThat(studentList.indexOf(st2)).isEqualTo(1);
        assertThat(studentList.indexOf(st2)).isNotEqualTo(2);
    }

    @Test
    void shouldReturnsMinusOneIfTheObjectIsNotOnTheListWhenIndexOf() {
        Student st4 = new Student("Patru", LocalDate.of(2006, 1, 1), "Student FOUR");

        assertThat(studentList.indexOf(st4)).isEqualTo(-1);
    }

    @Test
    void shouldReturnTheLastIndexOfTheObject() {
        List<Student> studentList = new StudentList<>(8);
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
    void shouldReturnMinusOneIfTheListDoesNotContainsTheObjectWhenLastIndexOf() {
        Student st4 = new Student("Patru", LocalDate.of(2006, 1, 1), "Student FOUR");

        assertThat(studentList.lastIndexOf(st4)).isEqualTo(-1);
    }

    @Test
    void shouldStartFromTheIndexZero() {
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        boolean indexZero = st1.equals(studentList.listIterator().next());

        assertTrue(indexZero);
    }

    @Test
    void shouldStartFromTheGivenIndex() {
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        boolean theListIteratorStartFromIndex = st2.equals(studentList.listIterator(1).next());

        assertTrue(theListIteratorStartFromIndex);
    }

    @Nested
    class TestAllMethodsOfTheInnerClassListIterator {

        @Test
        void shouldStartFromTheGivenIndex() {
            Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
            boolean indexOne = st2.equals(studentList.listIterator(1).next());

            assertTrue(indexOne);
        }

        @Test
        void shouldReturnTrueIfThePreviousObjectExists() {
            ListIterator<Student> listIterator = studentList.listIterator();
            listIterator.next();

            assertTrue(listIterator.hasPrevious());
        }

        @Test
        void shouldReturnFalseIfPreviousObjectDoesNotExists() {
            ListIterator<Student> listIterator = studentList.listIterator();

            assertFalse(listIterator.hasPrevious());
        }

        @Test
        void shouldReturnPreviousElement() {
            Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
            ListIterator<Student> listIterator = studentList.listIterator();
            listIterator.next();

            assertEquals(st1, listIterator.previous());
        }

        @Test
        void shouldReturnNoSuchElementExceptionWhenPreviousObjectDoesNotExist() {
            ListIterator<Student> listIterator = studentList.listIterator();

            assertThrows(NoSuchElementException.class, () -> listIterator.previous());
        }

        @Test
        void shouldReturnNextIndex() {
            ListIterator<Student> listIterator = studentList.listIterator();
            listIterator.next();

            assertEquals(1, listIterator.nextIndex());
        }

        @Test
        void shouldReturnPreviousIndex() {
            ListIterator<Student> listIterator = studentList.listIterator();
            listIterator.next();

            assertEquals(0, listIterator.previousIndex());
        }

        @Test
        void shouldThrowsIndexOutOfBoundsExceptionWhenThereIsNoIndex() {
            ListIterator<Student> listIterator = studentList.listIterator();

            assertThrows(IndexOutOfBoundsException.class, () -> listIterator.previousIndex());
        }

        @Test
        void shouldReplaceTheLastElementReturnedByNext() {
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
        void shouldAddTheElementAtTheIndexZero() {
            Student st4 = new Student("Patru", LocalDate.of(2008, 1, 1), "Student FOUR");
            ListIterator<Student> listIterator = studentList.listIterator();
            listIterator.add(st4);

            assertEquals(st4, studentList.get(0));
        }

        @Test
        void shouldCheckIfAddedObjectIsAddedAtTheRignthIndex() {
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
    void shouldCheckIfReturnedSubListIsCorrect() {
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
    void shouldThrowIndexOutOfBoundsExceptionWhenIndexDoesNotExist() {
        Student st4 = new Student("Patru", LocalDate.of(2008, 1, 1), "Student FOUR");
        Student st5 = new Student("CINCI", LocalDate.of(2000, 1, 1), "Student FIVE");
        studentList.add(st4);
        studentList.add(st5);

        assertThrows(IndexOutOfBoundsException.class, () -> studentList.subList(-1, 4));
        assertThrows(IndexOutOfBoundsException.class, () -> studentList.subList(1, 6));
    }

    @Test
    void shouldAddACollectionToTheActualList() {
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

        assertTrue(studentListA.addAll(studentList));
        assertArrayEquals(studentListE.toArray(), studentListA.toArray());
    }

    @Test
    void shouldThrowNullPointerExceptionWhenArgumentIsNull() {
        Object[] test = null;
        assertThrows(NullPointerException.class, () -> studentList.toArray(test));
    }

    @Test
    void shouldCheckIfReturnedArrayIsEqualsToTheList() {
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
    void shouldCheckIfTheLastObjectIsNull() {
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        Student st4 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        Student[] expectedArray = {st1, st2, st3, null};
        Student[] serviceArray2 = {st1, st2, st3, st4};
        assertArrayEquals(expectedArray, studentList.toArray(serviceArray2));
    }

    @Test
    void shouldReturnTrueIfResultedArrayIsEqualsToTheSizeOfTheList() {
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
    void shouldReturnTheListWithTheSizeEleven() {
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