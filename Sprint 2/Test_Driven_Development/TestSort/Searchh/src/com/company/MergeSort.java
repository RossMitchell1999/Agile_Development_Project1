package com.company;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {
    public List<Integer> sort(List<Integer> unsortedList) {
        Integer listSize = unsortedList.size();
        if (listSize <= 1) {
            return unsortedList;
        }

        Integer listHalfWayIndex = listSize / 2;
        List<Integer> leftList = new ArrayList<Integer>();
        List<Integer> rightList = new ArrayList<Integer>();

        for (Integer unsortedIndex = 0; unsortedIndex < listHalfWayIndex; unsortedIndex++) {
            Integer numberToAssign = unsortedList.get(unsortedIndex);
            leftList.add(numberToAssign);
        }

        for (Integer unsortedIndex = listHalfWayIndex; unsortedIndex < listSize; unsortedIndex++) {
            Integer numberToAssign = unsortedList.get(unsortedIndex);
            rightList.add(numberToAssign);
        }

        leftList = sort(leftList);
        rightList = sort(rightList);

        return merge(leftList, rightList);
    }

    public List<Integer> merge(List leftList, List rightList) {
        List<Integer> result = new ArrayList<Integer>();


        while (bothLeftAndRightContainElements(leftList, rightList)) {
            List<Integer> smallestList = compareFirstElementOfEachListAndReturnListWithSmallestFirstElement(leftList, rightList);
            Integer smallestInteger = smallestList.get(0);
            smallestList.remove(smallestInteger);
            result.add(smallestInteger);
        }

        if(bothLeftAndRightAreEmpty(leftList, rightList)){
            return result;
        } else {
            return assignLastRemainingItemsFromList(leftList, rightList, result);
        }
    }

    public List<Integer> compareFirstElementOfEachListAndReturnListWithSmallestFirstElement(List<Integer> leftList, List<Integer> rightList) {
        Integer firstLeftElement = leftList.get(0);
        Integer firstRightElement = rightList.get(0);

        if (firstLeftElement < firstRightElement) {
            return leftList;
        } else {
            return rightList;
        }
    }

    private boolean bothLeftAndRightContainElements(List leftList, List rightList) {
        return !leftList.isEmpty() && !rightList.isEmpty();
    }

    private boolean bothLeftAndRightAreEmpty(List leftList, List rightList) {
        return leftList.isEmpty() && rightList.isEmpty();
    }

    private List<Integer> assignLastRemainingItemsFromList(List<Integer> leftList, List<Integer> rightList, List<Integer> result) {
        if (leftList.isEmpty()) {
            emptyListIntoResult(rightList, result);
            rightList.clear();
        } else {
            emptyListIntoResult(leftList, result);
            leftList.clear();
        }
        return result;
    }

    private List<Integer> emptyListIntoResult(List<Integer> genericList, List<Integer> result) {
        while (!genericList.isEmpty()) {
            Integer smallestInteger = genericList.get(0);
            genericList.remove(smallestInteger);
            result.add(smallestInteger);
        }
        return result;
    }
}