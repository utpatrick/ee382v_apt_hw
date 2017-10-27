package QuickSort;
import java.util.concurrent.ThreadLocalRandom;

public interface SortingStrategy{
	
	public abstract int pivotSelection(int a, int b);
	
	public default void nonDecSort(int[] arr, int a, int b){
		int i = a, j = b;
		int pivot = arr[pivotSelection(i, j)];
		while(i <= j){
			while(arr[i] < pivot) i++;
			while(arr[j] > pivot) j--;
			if(i <= j){
				exchange(arr, i, j);
				i++;
				j--;
			}
		};
		if(a < j) nonDecSort(arr, a, j);
		if(i < b) nonDecSort(arr, i, b);
	};
	
	public default void nonIncSort(int[] arr, int a, int b){
		int i = a, j = b;
		int pivot = arr[pivotSelection(i, j)];
		while(i <= j){
			while(arr[i] > pivot) i++;
			while(arr[j] < pivot) j--;
			if(i <= j){
				exchange(arr, i, j);
				i++;
				j--;
			}
		};
		if(a < j) nonIncSort(arr, a, j);
		if(i < b) nonIncSort(arr, i, b);
	};
	
	default void exchange(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

class midPivotStrategy implements SortingStrategy{
	
	@Override
	public int pivotSelection(int a, int b){
		int pivot = a + (b - a) / 2;
		return pivot;
	};
	
}

class randomPivotStrategy implements SortingStrategy{
	
	@Override
	public int pivotSelection(int a, int b){
		int pivot = ThreadLocalRandom.current().nextInt(a, b + 1);
		return pivot;
	};
	
}
