package com.company;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class MergeSortTest {

    @Test
    public void verifySortReturnsSortedListWhenGivenPresortedArray() {
        MergeSort sorter = new MergeSort();
        List<Integer> twoElementList = new ArrayList<Integer>();
        twoElementList.add(1);
        twoElementList.add(2);
        assertEquals("Did not return presorted list", twoElementList, sorter.sort(twoElementList));
    }

    @Test
    public void verifySortReturnsSortedListWhenGivenArrayOfTwoUnsortedElements() {
        MergeSort sorter = new MergeSort();
        List<Integer> twoElementList = new ArrayList<Integer>();
        twoElementList.add(2);
        twoElementList.add(1);

        List<Integer> sortedTwoElementList = new ArrayList<Integer>();
        sortedTwoElementList.add(1);
        sortedTwoElementList.add(2);

        assertEquals("Did not return correctly sorted two-element list", sortedTwoElementList, sorter.sort(twoElementList));
    }

    @Test
    public void verfiyMergeCorrectlyCombinesTwoListsWithOneElementEach() {
        MergeSort sorter = new MergeSort();
        List<Integer> leftList = new ArrayList<Integer>();
        List<Integer> rightList = new ArrayList<Integer>();
        List<Integer> expectedResult = new ArrayList<Integer>();
        Integer number1 = new Integer(1);
        Integer number2 = new Integer(2);

        leftList.add(number2);
        rightList.add(number1);
        expectedResult.add(number1);
        expectedResult.add(number2);

        assertEquals("Did not merge two single-element lists into one result", expectedResult, sorter.merge(leftList, rightList));
    }

    @Test
    public void verfiyMergeCorrectlyCombinesTwoListsWithTwoElementsEach() {
        MergeSort sorter = new MergeSort();
        List<Integer> leftList = new ArrayList<Integer>();
        List<Integer> rightList = new ArrayList<Integer>();
        List<Integer> expectedResult = new ArrayList<Integer>();
        Integer number1 = new Integer(1);
        Integer number2 = new Integer(2);
        Integer number3 = new Integer(3);
        Integer number4 = new Integer(4);

        leftList.add(number3);
        leftList.add(number4);
        rightList.add(number1);
        rightList.add(number2);

        expectedResult.add(number1);
        expectedResult.add(number2);
        expectedResult.add(number3);
        expectedResult.add(number4);

        assertEquals("Did not merge two two-element lists into one result", expectedResult, sorter.merge(leftList, rightList));
    }

    @Test
    public void verfiyMergeCorrectlyCombinesTwoListsOfDifferentSize() {
        MergeSort sorter = new MergeSort();
        List<Integer> leftList = new ArrayList<Integer>();
        List<Integer> rightList = new ArrayList<Integer>();
        List<Integer> expectedResult = new ArrayList<Integer>();
        Integer number1 = new Integer(1);
        Integer number2 = new Integer(2);
        Integer number3 = new Integer(3);
        Integer number4 = new Integer(4);
        Integer number5 = new Integer(5);

        leftList.add(number2);
        leftList.add(number3);
        leftList.add(number5);
        rightList.add(number1);
        rightList.add(number4);

        expectedResult.add(number1);
        expectedResult.add(number2);
        expectedResult.add(number3);
        expectedResult.add(number4);
        expectedResult.add(number5);

        assertEquals("Did not merge lists of different sizes into one result", expectedResult, sorter.merge(leftList, rightList));
    }

    @Test
    public void compareFirstElementOfEachListCorrectlyReturnsListWithSmallestNumber() {
        MergeSort sorter = new MergeSort();
        List<Integer> leftList = new ArrayList<Integer>();
        List<Integer> rightList = new ArrayList<Integer>();
        Integer number1 = new Integer(1);
        Integer number2 = new Integer(2);

        leftList.add(number2);
        rightList.add(number1);

        assertEquals("Did not return correctly add smallest number of the two lists", rightList,
                sorter.compareFirstElementOfEachListAndReturnListWithSmallestFirstElement(leftList, rightList));
    }


    @Test
    public void verifySortReturnsSortedListWhenGivenArrayOfThreeUnsortedElements() {
        MergeSort sorter = new MergeSort();

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
        MergeSort sorter = new MergeSort();

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
        MergeSort sorter = new MergeSort();

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
        MergeSort sorter = new MergeSort();

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
	