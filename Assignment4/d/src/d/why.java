package d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
 
public class why {
	public static void main(String[] args) {
		int[] x = { 9, 2, 4, 7, 3, 7, 10 };
		ArrayList<Integer> prices = new ArrayList<Integer>();
		ArrayList<Integer> newList = new ArrayList<Integer>();
		prices.add(4000);
		prices.add(6000);
		prices.add(3212);
		prices.add(3322);
		prices.add(7654);
		prices.add(4444);
		prices.add(1000);
		System.out.println(prices);
 
		int low = 0;
		int high = prices.size() -1;
 
		quickSort(prices, low, high);
		searchBetweenValues(prices, newList, 3500, 6555);
		System.out.println(prices);
		System.out.println(newList);
	}
	
	public static void searchBetweenValues(List<Integer> prices, List<Integer> newList, int min, int max) {
		for(int i =0; i< prices.size()-1; i++) {
			if (prices.get(i) > min && prices.get(i) < max) {
				newList.add(prices.get(i));
			}
		}
	}
 
	public static void quickSort(List<Integer> prices, int low, int high) {
		if (prices == null || prices.size() == 0)
			return;
 
		if (low >= high)
			return;
 
		// pick the pivot
		int middle = low + (high - low) / 2;
		int pivot = prices.get(middle);
 
		// make left < pivot and right > pivot
		int i = low, j = high;
		while (i <= j) {
			while (prices.get(i) < pivot) {
				i++;
			}
 
			while (prices.get(j) > pivot) {
				j--;
			}
 
			if (i <= j) {
				int temp = prices.get(i);
				prices.set(i, prices.get(j));
				prices.set(j, temp);
				i++;
				j--;
			}
		}
 
		// recursively sort two sub parts
		if (low < j)
			quickSort(prices, low, j);
 
		if (high > i)
			quickSort(prices, i, high);
	}
}