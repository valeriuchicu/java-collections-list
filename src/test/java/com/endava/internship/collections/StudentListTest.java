package com.endava.internship.collections;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentListTest {

    @BeforeEach
    public void setUp() {
    }

    @Test
    void constructorShouldThrowAnExceptionIfTheCapacityIsLessThenZero() {
        assertThrows(IllegalArgumentException.class, () -> new StudentList<Student>(-1));
    }

    @Test
    void shouldTheSizeBeRight() {
        //given
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        studentList.add(st1);
        studentList.add(st2);
        studentList.add(st3);

        //when
        int listSize = studentList.size();

        //then
        assertEquals(listSize, 3);
    }

    @Test
    void isArrayEmpty() {

        //given
        List<Student> studentList = new StudentList<>();
        //when
        //then
        assertTrue(studentList.isEmpty());
    }

    @Test
    void arrayShouldBeNotEmpty() {

        //given
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        studentList.add(st1);
        //when
        //then
        assertFalse(studentList.isEmpty());
    }

    @Test
    void arrayShouldContainsElement() {
        //given
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        studentList.add(st1);
        studentList.add(st2);

        //when
        boolean existSt1 = studentList.contains(st1);
        boolean existSt2 = studentList.contains(st2);

        //then
        assertTrue(existSt1);
        assertTrue(existSt2);
    }

    @Test
    void arrayShouldNotContainsElement() {
        //given
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        studentList.add(st1);
        studentList.add(st2);

        //when
        boolean existSt3 = studentList.contains(st3);

        //then
        assertFalse(existSt3);
    }

    @Test
    void ifTheArgumentOfContainMethodIsEqualToNull() {
        //given
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        studentList.add(st1);
        studentList.add(st2);

        //when
        boolean existSt = studentList.contains(null);

        //then
        assertFalse(existSt);
    }

    @Test
    void iteratorShouldHasNext() {
        //given
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        studentList.add(st1);
        studentList.add(st2);
        Iterator<Student> iterator = studentList.iterator();
        //when
        //then
        assertTrue(iterator.hasNext());
    }

    @Test
    void iteratorShouldThrowExceptionOntheNextMethod() {
        //given
        List<Student> studentList = new StudentList<>();
        Iterator<Student> iterator = studentList.iterator();
        //when
        //then
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    @Test
    void shouldRemoveTheNextElement() {
        //given
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        studentList.add(st1);
        studentList.add(st2);
        Iterator<Student> iterator = studentList.iterator();
        //when
        iterator.remove();
        //then
        assertThat(studentList).hasSize(1)
                .contains(st2)
                .doesNotContain(st1);
    }

    @Test
    void shouldRemoveTheNextElementAfterNext() {
        //given
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        studentList.add(st1);
        studentList.add(st2);
        Iterator<Student> iterator = studentList.iterator();
        //when
        iterator.next();
        iterator.remove();
        //then
        assertThat(studentList).hasSize(1)
                .contains(st2)
                .doesNotContain(st1);
    }

    @Test
    void shouldConvertTheListToArray() {
        //given
        List<Student> studentList = new StudentList<>();
        List<Student> list = new ArrayList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        studentList.add(st1);
        studentList.add(st2);
        studentList.add(st3);

        list.add(st1);
        list.add(st2);
        list.add(st3);

        //when
        Object[] actualArrayOfStudents = studentList.toArray();
        Object[] expectedArrayOfStudents = list.toArray();

        //then
        assertArrayEquals(actualArrayOfStudents, expectedArrayOfStudents);
    }

    @Test
    void removeShouldReturnDeletedObject() {
        //given
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        studentList.add(st1);
        studentList.add(st2);
        studentList.add(st3);

        //when
        Student removedStudent = studentList.remove(1);

        //then
        assertEquals(st2, removedStudent);
    }

    @Test
    void afterRemoveShouldNotContainRemovedObject() {
        //given
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        studentList.add(st1);
        studentList.add(st2);
        studentList.add(st3);

        //when
        Student removedStudent = studentList.remove(1);
        boolean isContainTheStudent = studentList.contains(removedStudent);

        //then
        assertFalse(isContainTheStudent);
    }

    @Test
    void ifInsertedIndexInMethodREMOVEIsOutOfBound() {
        //given
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        studentList.add(st1);
        studentList.add(st2);
        studentList.add(st3);

        //when
        //then
        assertThrows(IndexOutOfBoundsException.class, () -> studentList.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> studentList.remove(3));
    }

    @Test
    void shouldRemoveObjectFromList() {
        //given
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        studentList.add(st1);
        studentList.add(st2);
        studentList.add(st3);

        //when
        //then
        assertTrue(studentList.remove(st2));
    }

    @Test
    void shouldNotRemoveObjectFromListBecauseItDoesNotExistThere() {
        //given
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        studentList.add(st1);
        studentList.add(st2);

        //when
        //then
        assertFalse(studentList.remove(st3));
    }

    @Test
    void clear() {
        //given
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        studentList.add(st1);
        studentList.add(st2);
        //when
        studentList.clear();
        //then
        assertEquals(studentList.size(), 0);
    }

    @Test
    void ifGetObjectIsRight() {
        //given
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        studentList.add(st1);
        studentList.add(st2);
        //when
        boolean isTheRightObject = st2.equals(studentList.get(1));
        //then
        assertTrue(isTheRightObject);
    }

    @Test
    void ifInsertedIndexInMethodGetIsOutOfBound() {
        //given
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        studentList.add(st1);
        studentList.add(st2);
        //when
        //then
        assertThrows(IndexOutOfBoundsException.class, () -> studentList.get(3));
        assertThrows(IndexOutOfBoundsException.class, () -> studentList.get(-1));
    }

    @Test
    void shouldSetTheObjectAtTheGivenIndex() {
        //given
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        studentList.add(st1);
        studentList.add(st2);
        //when
        Student setStudent = studentList.set(1, st3);
        //then
        assertEquals(st3, setStudent);
    }

    @Test
    void setShouldThrowIndexOutOfBoundsException() {
        //given
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        studentList.add(st1);
        studentList.add(st2);
        //when
        //then
        assertThrows(IndexOutOfBoundsException.class, () -> studentList.set(3, st3));
        assertThrows(IndexOutOfBoundsException.class, () -> studentList.set(-1, st3));
    }

    @Test
    void shouldAddTheObjectAtTheIndicatedIndex() {
        //given
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        studentList.add(st1);
        studentList.add(st2);
        //when
        studentList.add(1, st3);
        //then
        assertEquals(st3, studentList.get(1));
    }

    @Test
    void addShouldThrowIndexOutOfBoundsException() {
        //given
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        studentList.add(st1);
        studentList.add(st2);
        //when
        //then
        assertThrows(IndexOutOfBoundsException.class, () -> studentList.add(-1, st3));
        assertThrows(IndexOutOfBoundsException.class, () -> studentList.add(3, st3));
    }

    @Test
    void ShouldIndexOfCorrect() {
        //given
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        studentList.add(st1);
        studentList.add(st2);
        //when
        //then
        assertThat(studentList.indexOf(st2)).isEqualTo(1);
        assertThat(studentList.indexOf(st2)).isNotEqualTo(2);
    }

    @Test
    void indexOfOfTheObjectWhichDoesNotExistInTheList() {
        //given
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        studentList.add(st1);
        studentList.add(st2);
        //when
        //then
        assertThat(studentList.indexOf(st3)).isEqualTo(-1);
    }

    @Test
    void shouldReturnTheLastIndexOfTheObject() {
        //given
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        studentList.add(st1);
        studentList.add(st2);
        studentList.add(st1);
        studentList.add(st3);
        //when
        //then
        assertEquals(2, studentList.lastIndexOf(st1));
    }

    @Test
    void lastIndexOfShouldReturnMinusOneIfTheListDoesNotContainsTheObject() {
        //given
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        studentList.add(st1);
        studentList.add(st2);
        studentList.add(st1);
        //when
        //then
        assertEquals(-1, studentList.lastIndexOf(st3));
    }

    @Test
    void listIteratorShouldStartFromTheIndexZero() {
        //given
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        studentList.add(st1);
        studentList.add(st2);
        studentList.add(st3);
        //when
        boolean indexZero = st1.equals(studentList.listIterator().next());
        //then
        assertTrue(indexZero);
    }

    @Test
    void listIteratorShouldStartFromTheGivenIndex() {
        //given
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        studentList.add(st1);
        studentList.add(st2);
        studentList.add(st3);
        //when
        boolean theListIteratorStartFromIndex = st2.equals(studentList.listIterator(1).next());
        //then
        assertTrue(theListIteratorStartFromIndex);
    }

    @Nested
    class testListIterator {

        @Test
        void listIteratorShouldStartFromTheGivenIndex() {
            //given
            List<Student> studentList = new StudentList<>();
            Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
            Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
            Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
            studentList.add(st1);
            studentList.add(st2);
            studentList.add(st3);
            //when
            boolean indexOne = st2.equals(studentList.listIterator(1).next());
            //then
            assertTrue(indexOne);
        }

        @Test
        void hasPreviousShouldReturnTrue() {
            //given
            List<Student> studentList = new StudentList<>();
            Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
            Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
            Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
            studentList.add(st1);
            studentList.add(st2);
            studentList.add(st3);
            ListIterator<Student> listIterator = studentList.listIterator();
            //when
            listIterator.next();
            //then
            assertTrue(listIterator.hasPrevious());
        }

        @Test
        void hasPreviousShouldReturnFalseIfWeDoNotCallNext() {
            //given
            List<Student> studentList = new StudentList<>();
            Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
            Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
            Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
            studentList.add(st1);
            studentList.add(st2);
            studentList.add(st3);
            ListIterator<Student> listIterator = studentList.listIterator();
            //when
//            listIterator.next();
            //then
            assertFalse(listIterator.hasPrevious());
        }

        @Test
        void previousShouldReturnPreviousElement() {
            //given
            List<Student> studentList = new StudentList<>();
            Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
            Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
            Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
            studentList.add(st1);
            studentList.add(st2);
            studentList.add(st3);
            ListIterator<Student> listIterator = studentList.listIterator();
            //when
            listIterator.next();
            //then
            assertEquals(st1, listIterator.previous());
        }

        @Test
        void previousShouldReturnNoSuchElementExceptionIfPreviousObjectDoesNotExist() {
            //given
            List<Student> studentList = new StudentList<>();
            Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
            Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
            Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
            studentList.add(st1);
            studentList.add(st2);
            studentList.add(st3);
            ListIterator<Student> listIterator = studentList.listIterator();
            //when
            //then
            assertThrows(NoSuchElementException.class, () -> listIterator.previous());
        }

        @Test
        void nextIndexShouldReturnNextIndex() {
            //given
            List<Student> studentList = new StudentList<>();
            Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
            Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
            Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
            studentList.add(st1);
            studentList.add(st2);
            studentList.add(st3);
            ListIterator<Student> listIterator = studentList.listIterator();
            //when
            listIterator.next();
            //then
            assertEquals(1, listIterator.nextIndex());
        }

        @Test
        void previousIndexShouldReturnPreviousIndex() {
            //given
            List<Student> studentList = new StudentList<>();
            Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
            Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
            Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
            studentList.add(st1);
            studentList.add(st2);
            studentList.add(st3);
            ListIterator<Student> listIterator = studentList.listIterator();
            //when
            listIterator.next();
            //then
            assertEquals(0, listIterator.previousIndex());
        }

        @Test
        void previousIndexShouldThrowsAnException() {
            //given
            List<Student> studentList = new StudentList<>();
            ListIterator<Student> listIterator = studentList.listIterator();
            //when
            //then
            assertThrows(IndexOutOfBoundsException.class, () -> listIterator.previousIndex());
        }

        @Test
        void setShouldReplaceTheLastElementReturnedByNext() {
            //given
            List<Student> studentList = new StudentList<>();
            Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
            Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
            Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
            studentList.add(st1);
            studentList.add(st2);
            studentList.add(st3);
            ListIterator<Student> listIterator = studentList.listIterator();
            //when
            listIterator.next();
            listIterator.set(st3);
            listIterator.next();
            listIterator.set(null);
            //then
            assertEquals(st3, studentList.get(0));
            assertNull(studentList.get(1));
        }

        @Test
        void setShouldThrowsNoSuchElementException() {
            //given
            List<Student> studentList = new StudentList<>();
            Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
            ListIterator<Student> listIterator = studentList.listIterator();
            //when
            //then
            assertThrows(NoSuchElementException.class, () -> listIterator.set(st1));
        }

        @Test
        void addShouldAddTheElementAtTheIndexZero() {
            //given
            List<Student> studentList = new StudentList<>();
            Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
            Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
            Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
            Student st4 = new Student("Patru", LocalDate.of(2008, 1, 1), "Student FOUR");
            studentList.add(st1);
            studentList.add(st2);
            studentList.add(st3);
            ListIterator<Student> listIterator = studentList.listIterator();
            //when
            listIterator.add(st4);
            //then
            assertEquals(st4, studentList.get(0));
            assertEquals(st1, studentList.get(1));
            assertEquals(st2, studentList.get(2));
            assertEquals(st3, studentList.get(3));
        }

        @Test
        void addShouldAddTheElementAtTheIndexTree() {
            //given
            List<Student> studentList = new StudentList<>();
//            List<Student> studentList = new ArrayList<>();
            Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
            Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
            Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
            Student st4 = new Student("Patru", LocalDate.of(2008, 1, 1), "Student FOUR");
            studentList.add(st1);
            studentList.add(st2);
            studentList.add(st3);
            ListIterator<Student> listIterator = studentList.listIterator();
            //when
            listIterator.next();
            listIterator.next();
            listIterator.next();
            listIterator.add(st4);
            //then
            assertEquals(st4, studentList.get(3));
        }
    }

    @Test
    void shouldGiveTheRightSubList() {
        //given
        List<Student> studentList = new StudentList<>();
        List<Student> studentList1 = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        Student st4 = new Student("Patru", LocalDate.of(2008, 1, 1), "Student FOUR");
        Student st5 = new Student("CINCI", LocalDate.of(2000, 1, 1), "Student FIVE");
        studentList.add(st1);
        studentList.add(st2);
        studentList.add(st3);
        studentList.add(st4);
        studentList.add(st5);

        studentList1.add(st2);
        studentList1.add(st3);
        studentList1.add(st4);
        //when
        List<Student> actual = studentList.subList(1, 4);
        //then
        assertThat(actual).hasSameElementsAs(studentList1);
    }

    @Test
    void subListShouldThrowAndException() {
        //given
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        Student st4 = new Student("Patru", LocalDate.of(2008, 1, 1), "Student FOUR");
        Student st5 = new Student("CINCI", LocalDate.of(2000, 1, 1), "Student FIVE");
        studentList.add(st1);
        studentList.add(st2);
        studentList.add(st3);
        studentList.add(st4);
        studentList.add(st5);

        //when
        //then
        assertThrows(IndexOutOfBoundsException.class, () -> studentList.subList(-1, 4));
        assertThrows(IndexOutOfBoundsException.class, () -> studentList.subList(1, 6));
    }

    @Test
    void addAllShouldAddACollectionToTheActualList() {
        //given
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

        //when
        boolean isAdded = studentListA.addAll(studentList);
        //then
        assertArrayEquals(studentListE.toArray(), studentListA.toArray());
        assertTrue(isAdded);
    }

    @Test
    void shouldThrowNPEIfThePassedArgumentIsNulltoArray() {
        //given
        List<Student> studentList = new StudentList<>();
        //when
        Object[] test = null;
        //then
        assertThrows(NullPointerException.class, () -> studentList.toArray(test));
    }

    @Test
    void toArrayShouldReturnAnArrayOfThisList() {
        //given
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        studentList.add(st1);
        studentList.add(st2);
        studentList.add(st3);
        //when
        Student[] expectedArray = {st1, st2, st3};
        Student[] serviceArray1 = {st1, st2, st3};
        Student[] serviceArray2 = new Student[3];
        //then
        assertArrayEquals(expectedArray, studentList.toArray(serviceArray1));
        assertArrayEquals(expectedArray, studentList.toArray(serviceArray2));
    }

    @Test
    void toArrayShoulPutNullIfTheArrayIs0rGreaterThanSizeOfTheList() {
        //given
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        studentList.add(st1);
        studentList.add(st2);
        //when
        Student[] expectedArray = {st1, st2, null};
        Student[] serviceArray2 = {st1, st2, st3};
        //then
        assertArrayEquals(expectedArray, studentList.toArray(serviceArray2));
    }

    @Test
    void containsAllShouldThrowUnsupportedOperationException() {
        //given
        List<Student> studentList = new StudentList<>();
        //when
        //then
        assertThrows(UnsupportedOperationException.class, () -> studentList.containsAll(new StudentList<Student>()));
    }

    @Test
    void addAllShouldThrowUnsupportedOperationException() {
        //given
        List<Student> studentList = new StudentList<>();
        //when
        //then
        assertThrows(UnsupportedOperationException.class, () -> studentList.addAll(1, new StudentList<>()));
    }

    @Test
    void removeAllShouldThrowUnsupportedOperationException() {
        //given
        List<Student> studentList = new StudentList<>();
        //when
        //then
        assertThrows(UnsupportedOperationException.class, () -> studentList.removeAll(new StudentList<Student>()));
    }

    @Test
    void retainAllShouldThrowUnsupportedOperationException() {
        //given
        List<Student> studentList = new StudentList<>();
        //when
        //then
        assertThrows(UnsupportedOperationException.class, () -> studentList.retainAll(new StudentList<Student>()));
    }

    @Test
    void shouldResizeArray() {
        //given
        List<Student> studentList = new StudentList<>();
        Student st1 = new Student("Unu", LocalDate.of(2001, 1, 1), "Student ONE");
        Student st2 = new Student("Doi", LocalDate.of(2004, 1, 1), "Student TWO");
        Student st3 = new Student("Trei", LocalDate.of(2006, 1, 1), "Student TREE");
        Student st4 = new Student("Patru", LocalDate.of(2008, 1, 1), "Student FOUR");
        Student st5 = new Student("CINCI", LocalDate.of(2000, 1, 1), "Student FIVE");
        studentList.add(st1);
        studentList.add(st2);
        studentList.add(st3);
        studentList.add(st4);
        studentList.add(st5);
        studentList.add(st1);
        studentList.add(st2);
        studentList.add(st3);
        studentList.add(st4);
        studentList.add(st5);

        //when
        studentList.add(st5);
        //then
        assertEquals(11, studentList.size());
    }
}