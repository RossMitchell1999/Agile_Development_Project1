package com.company;

import java.util.ArrayList;
import java.util.List;

public class InsertionSort {
    public List sort(Integer[] unsortedArray) {
        List<Integer> sortedList = new ArrayList<Integer>();

        for (Integer unsortedIndex = 0; unsortedIndex < unsortedArray.length; unsortedIndex++) {
            Integer unsortedElement = unsortedArray[unsortedIndex];
            if(sortedList.isEmpty()) {
                addElementToEmptySortedList(sortedList, unsortedElement);
            } else {
                addElementToListIfSmallerThanHighestSortedInteger(sortedList, unsortedElement);
                addElementToListIfHigherThanBiggestSortedInteger(sortedList, unsortedElement);
            }
        }
    return sortedList;
    }

    private List addElementToEmptySortedList(List<Integer> sortedList, Integer unsortedElement) {
        sortedList.add(unsortedElement);
        return sortedList;
    }

    private List addElementToListIfSmallerThanHighestSortedInteger(List<Integer> sortedList, Integer unsortedElement) {
        Integer sortedListSize = sortedList.size();
        for (Integer sortedIndex = 0; sortedIndex < sortedListSize; sortedIndex++) {
            if (unsortedElement <= sortedList.get(sortedIndex)) {
                sortedList.add(sortedIndex, unsortedElement);
                sortedIndex = sortedListSize;
            }
        }
        return sortedList;
    }

    private List addElementToListIfHigherThanBiggestSortedInteger(List<Integer> sortedList, Integer unsortedElement) {
        Integer sortedListSize = sortedList.size();
        if (unsortedElement > sortedList.get(sortedListSize - 1)) {
            sortedList.add(unsortedElement);
        }
        return sortedList;
    }

}
