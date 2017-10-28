package QuickSort;
import java.util.concurrent.ThreadLocalRandom;

interface Order{
	public boolean compare(int a, int b);
}

class nonDec implements Order{
	@Override
	public boolean compare(int a, int b){
		return (a < b)? true: false;
	};
}

class nonInc implements Order{
	@Override
	public boolean compare(int a, int b){
		return (b < a)? true: false;
	};
}

public abstract class SortingTemplate{
	
	Order comp;
	
	SortingTemplate(Order o){
		comp = o;
	}
	
	public void sort(int[] array) {
		Sort(array, 0, array.length - 1);
    	}
	
	protected abstract int pivotSelection(int a, int b);
	
	private void Sort(int[] arr, int a, int b){
		int i = a, j = b;
		int pivot = arr[pivotSelection(i, j)];
		while(i <= j){
			while(comp.compare(arr[i], pivot)) i++;
			while(comp.compare(pivot, arr[j])) j--;
			if(i <= j){
				exchange(arr, i, j);
				i++;
				j--;
			}
		};
		if(a < j) Sort(arr, a, j);
		if(i < b) Sort(arr, i, b);
	};
	
	private void exchange(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

class midPivotStrategy extends SortingTemplate{
	
	public midPivotStrategy(Order o) {
        super(o);
    }
	
	@Override
	public int pivotSelection(int a, int b){
		int pivot = a + (b - a) / 2;
		return pivot;
	};
	
}

class randomPivotStrategy extends SortingTemplate{
	
	public randomPivotStrategy(Order o) {
        super(o);
    }
	
	@Override
	public int pivotSelection(int a, int b){
		int pivot = ThreadLocalRandom.current().nextInt(a, b + 1);
		return pivot;
	};
	
}
