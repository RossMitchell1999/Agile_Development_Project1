package com.company;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class InsertionSortTest {

    @Test
    public void verifySortReturnsSortedListWhenGivenPresortedArray(){
        InsertionSort sorter = new InsertionSort();
        Integer[] twoElementArray = new Integer[]{1,2};
        List sortedTwoElementList = Arrays.asList(twoElementArray);

        assertEquals("Did not return presorted list", sortedTwoElementList, sorter.sort(twoElementArray));
    }

    @Test
    public void verifySortReturnsSortedListWhenGivenArrayOfTwoUnsortedElements(){
        InsertionSort sorter = new InsertionSort();
        Integer[] twoElementArray = new Integer[]{2,1};
        Integer[] sortedTwoElementArray = new Integer[]{1,2};
        List sortedTwoElementList = Arrays.asList(sortedTwoElementArray);

        assertEquals("Did not return correctly sorted two-element list", sortedTwoElementList, sorter.sort(twoElementArray));
    }

   @Test
    public void verifySortReturnsSortedListWhenGivenArrayOfThreeUnsortedElements(){
        InsertionSort sorter = new InsertionSort();
        Integer[] threeElementArray = new Integer[]{2,3,1};
        Integer[] sortedThreeElementArray = new Integer[]{1,2,3};
        List sortedThreeElementList = Arrays.asList(sortedThreeElementArray);

        assertEquals("Did not return correctly sorted three-element list", sortedThreeElementList, sorter.sort(threeElementArray));
    }

    @Test
    public void verifySortReturnsSortedListWhenGivenArrayOfFourUnsortedElements(){
        InsertionSort sorter = new InsertionSort();
        Integer[] fourElementArray = new Integer[]{4,3,2,1};
        Integer[] sortedFourElementArray = new Integer[]{1,2,3,4};
        List sortedFourElementList = Arrays.asList(sortedFourElementArray);

        assertEquals("Did not return correctly sorted three-element list", sortedFourElementList, sorter.sort(fourElementArray));
    }

    @Test
    public void verifySortReturnsSortedListWhenGivenArrayOfFourUnsortedElementsWithDuplicateNumbers(){
        InsertionSort sorter = new InsertionSort();
        Integer[] fourElementArray = new Integer[]{4,4,2,2};
        Integer[] sortedFourElementArray = new Integer[]{2,2,4,4};
        List sortedFourElementList = Arrays.asList(sortedFourElementArray);

        assertEquals("Did not return correctly sorted three-element list", sortedFourElementList, sorter.sort(fourElementArray));
    }

    @Test
    public void verifySortReturnsSortedListWhenGivenArrayOfTwentyUnsortedElementsWithDuplicateNumbers(){
        InsertionSort sorter = new InsertionSort();
        Integer[] twentyElementArray = new Integer[]{20,19,18,10,9,8,7,17,16,15,14,6,5,4,3,2,1,13,12,11};
        Integer[] sortedTwentyElementArray = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        List sortedTwentyElementList = Arrays.asList(sortedTwentyElementArray);

        assertEquals("Did not return correctly sorted three-element list", sortedTwentyElementList, sorter.sort(twentyElementArray));
    }
}