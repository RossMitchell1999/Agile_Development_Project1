import java.util.List;


public class QuickSort {

    public List<Integer> sort(List<Integer> list, int left, int right) {
        if (list.size() <= 1) return list;
        int index = partition(list, left, right);
        if (left < index - 1) {
             sort(list, left, index - 1);
        }
        if (index < right) {
            sort(list, index, right);
        }
        return list;
    }

    public int partition(List<Integer> list, int left, int right) {
        int i = left, j = right;
        int pivot = getMiddleForPivotIndex(list, left, right);
        while (i <= j) {
            while (list.get(i) < pivot) i++;
            while (list.get(j) > pivot) j--;
            if (i <= j) {
                int storeIndex = list.get(i);
                list.set(i, list.get(j));
                list.set(j, storeIndex);
                i++;
                j--;
            }
        }
        return i;
    }

    protected int getMiddleForPivotIndex(List<Integer> list, int left, int right) {
        return list.get((left + right) / 2);
    }
}