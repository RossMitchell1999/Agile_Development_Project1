package com.company;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class BubbleSortTest {

    @Test
    public void verifySortReturnsSortedListWhenGivenPresortedArray() {
        BubbleSort sorter = new BubbleSort();
        List<Integer> twoElementList = new ArrayList<Integer>();
        twoElementList.add(1);
        twoElementList.add(2);
        assertEquals("Did not return presorted list", twoElementList, sorter.sort(twoElementList));
    }

    @Test
    public void verifySortReturnsSortedListWhenGivenArrayOfTwoUnsortedElements() {
        BubbleSort sorter = new BubbleSort();
        List<Integer> twoElementList = new ArrayList<Integer>();
        twoElementList.add(2);
        twoElementList.add(1);

        List<Integer> sortedTwoElementList = new ArrayList<Integer>();
        sortedTwoElementList.add(1);
        sortedTwoElementList.add(2);

        assertEquals("Did not return correctly sorted two-element list", sortedTwoElementList, sorter.sort(twoElementList));
    }

    @Test
    public void verifySortReturnsSortedListWhenGivenArrayOfThreeUnsortedElements() {
        BubbleSort sorter = new BubbleSort();

        List<Integer> threeElementList = new ArrayList<Integer>();
        threeElementList.add(3);
        threeElementList.add(2);
        threeElementList.add(1);

        List<Integer> sortedThreeElementList = new ArrayList<Integer>();
        sortedThreeElementList.add(1);
        sortedThreeElementList.add(2);
        sortedThreeElementList.add(3);

        assertEquals("Did not return correctly sorted three-element list", sortedThreeElementList, sorter.sort(threeElementList));
    }

    @Test
    public void verifySortReturnsSortedListWhenGivenArrayOfFourUnsortedElements() {
        BubbleSort sorter = new BubbleSort();

        List<Integer> fourElementList = new ArrayList<Integer>();
        fourElementList.add(4);
        fourElementList.add(2);
        fourElementList.add(3);
        fourElementList.add(1);

        List<Integer> sortedFourElementList = new ArrayList<Integer>();
        sortedFourElementList.add(1);
        sortedFourElementList.add(2);
        sortedFourElementList.add(3);
        sortedFourElementList.add(4);

        assertEquals("Did not return correctly sorted four-element list", sortedFourElementList, sorter.sort(fourElementList));
    }

    @Test
    public void verifySortReturnsSortedListWhenGivenArrayOfFourUnsortedElementsWithDuplicateNumbers() {
        BubbleSort sorter = new BubbleSort();

        List<Integer> fourElementList = new ArrayList<Integer>();
        fourElementList.add(4);
        fourElementList.add(2);
        fourElementList.add(4);
        fourElementList.add(2);

        List<Integer> sortedFourElementList = new ArrayList<Integer>();
        sortedFourElementList.add(2);
        sortedFourElementList.add(2);
        sortedFourElementList.add(4);
        sortedFourElementList.add(4);

        assertEquals("Did not return correctly sorted four-element list", sortedFourElementList, sorter.sort(fourElementList));
    }

    @Test
    public void verifySortReturnsSortedListWhenGivenArrayOfTenUnsortedElements() {
        BubbleSort sorter = new BubbleSort();

        List<Integer> tenElementList = new ArrayList<Integer>();
        tenElementList.add(10);
        tenElementList.add(1);
        tenElementList.add(9);
        tenElementList.add(8);
        tenElementList.add(2);
        tenElementList.add(7);
        tenElementList.add(6);
        tenElementList.add(3);
        tenElementList.add(5);
        tenElementList.add(4);

        List<Integer> sortedTenElementList = new ArrayList<Integer>();
        sortedTenElementList.add(1);
        sortedTenElementList.add(2);
        sortedTenElementList.add(3);
        sortedTenElementList.add(4);
        sortedTenElementList.add(5);
        sortedTenElementList.add(6);
        sortedTenElementList.add(7);
        sortedTenElementList.add(8);
        sortedTenElementList.add(9);
        sortedTenElementList.add(10);

        assertEquals("Did not return correctly sorted ten-element list", sortedTenElementList, sorter.sort(tenElementList));
    }
}