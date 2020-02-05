
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class QuickSortTest {

    private List<Integer> unsorted;
    private QuickSort quickSort;
    private int left;
    private int right;

    @Before
    public void setUp() {
        unsorted = Arrays.asList(5,7,3,9,2,10,6,8,1,4);
        quickSort = new QuickSort();
        left = 0;
        right = unsorted.size() - 1;
    }

    @Test
    public void shouldReturnSameForCollectionOfZeroElements() {
        List<Integer> unsorted = Arrays.asList();

        right = unsorted.size() - 1;
        List<Integer> sorted = quickSort.sort(unsorted, left, right);

        assertThat(sorted, is(unsorted));
    }

    @Test
    public void shouldReturnSameForCollectionOfOneElement() {
        List<Integer> unsorted = Arrays.asList(2);

        List<Integer> sorted = quickSort.sort(unsorted, left, right);

        assertThat(sorted, is(unsorted));
    }

    @Test
    public void shouldReturnPivotIndexOfValue2() {
        int pivotIndex = quickSort.getMiddleForPivotIndex(unsorted, left, right);

        assertThat(pivotIndex, is(2));
    }

    @Test
    public void shouldGetFinalPositionOfPivotFromFullUnsortedList() {
        int finalPosition = quickSort.partition(unsorted, left, right);

        assertThat(finalPosition, is(2));
    }

    @Test
    public void shouldReturnCollectionInAscendingNumericOrder() {
        System.out.println("Original unsorted list: " + unsorted);
        List<Integer> sorted = quickSort.sort(unsorted, left, right);

        List<Integer> expected = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        assertThat(sorted, is(expected));
        System.out.println("Sorted list: " + sorted);
    }

}