package QuickSort;

public class SortingContext {
	private final SortingStrategy strategy;
	
	public SortingContext(SortingStrategy strategy){
		this.strategy = strategy;
	}
	
	public void nonDecSort(int[] input) {
	    strategy.nonDecSort(input, 0, input.length - 1);
	}
	
	public void nonIncSort(int[] input) {
	    strategy.nonIncSort(input, 0, input.length - 1);
	}
}
