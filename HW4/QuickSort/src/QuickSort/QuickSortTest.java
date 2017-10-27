package QuickSort;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuickSortTest {

	@Test
	public void test1() {
		int[] var = {5,4,3,2,1}; 
		SortingContext ans = new SortingContext(new midPivotStrategy()); 
		ans.nonDecSort(var);
		
		for(int i : var){
			System.out.print(i);
		}
		System.out.println();
	}
		
	@Test
	public void test2() {
		int[] var = {1,2,3,4,5}; 
		SortingContext ans = new SortingContext(new midPivotStrategy()); 
		ans.nonIncSort(var);
		
		for(int i : var){
			System.out.print(i);
		}
		System.out.println();
	}
	
	@Test
	public void test3() {
		int[] var = {5,4,3,2,1}; 
		SortingContext ans = new SortingContext(new firstPivotStrategy()); 
		ans.nonDecSort(var);
		
		for(int i : var){
			System.out.print(i);
		}
		System.out.println();
	}
	
	@Test
	public void test4() {
		int[] var = {1,2,3,4,5}; 
		SortingContext ans = new SortingContext(new firstPivotStrategy()); 
		ans.nonIncSort(var);
		
		for(int i : var){
			System.out.print(i);
		}
		System.out.println();
	}
	
}
