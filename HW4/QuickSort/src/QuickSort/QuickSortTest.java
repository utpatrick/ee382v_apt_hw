package QuickSort;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class QuickSortTest {

	@Test
	public void test1() {
		int[] var = {5,4,3,2,1};
		int[] expArr = {1,2,3,4,5};
		SortingContext ans = new SortingContext(new midPivotStrategy()); 
		ans.nonDecSort(var);
		assertTrue(Arrays.equals(var, expArr));
	}
		
	@Test
	public void test2() {
		int[] var = {1,2,3,4,5}; 
		int[] expArr = {5,4,3,2,1};
		SortingContext ans = new SortingContext(new midPivotStrategy()); 
		ans.nonIncSort(var);
		assertTrue(Arrays.equals(var, expArr));
	}
	
	@Test
	public void test3() {
		int[] var = {5,4,3,2,1};
		int[] expArr = {1,2,3,4,5};
		SortingContext ans = new SortingContext(new randomPivotStrategy()); 
		ans.nonDecSort(var);
		assertTrue(Arrays.equals(var, expArr));
	}
	
	@Test
	public void test4() {
		int[] var = {1,1,5,5,5};
		int[] expArr = {5,5,5,1,1};
		SortingContext ans = new SortingContext(new randomPivotStrategy()); 
		ans.nonIncSort(var);
		assertTrue(Arrays.equals(var, expArr));
	}
	@Test
	public void test5() {
		int[] var = {5,5,5,1,1};
		int[] expArr = {1,1,5,5,5};
		SortingContext ans = new SortingContext(new midPivotStrategy()); 
		ans.nonDecSort(var);
		assertTrue(Arrays.equals(var, expArr));
	}
		
	@Test
	public void test6() {
		int[] var = {1,1,5,5,5}; 
		int[] expArr = {5,5,5,1,1};
		SortingContext ans = new SortingContext(new midPivotStrategy()); 
		ans.nonIncSort(var);
		assertTrue(Arrays.equals(var, expArr));
	}
	
	@Test
	public void test7() {
		int[] var = {5,5,5,1,1};
		int[] expArr = {1,1,5,5,5};
		SortingContext ans = new SortingContext(new randomPivotStrategy()); 
		ans.nonDecSort(var);
		assertTrue(Arrays.equals(var, expArr));
	}
	
	@Test
	public void test8() {
		int[] var = {1,2,3,4,5};
		int[] expArr = {5,4,3,2,1};
		SortingContext ans = new SortingContext(new randomPivotStrategy()); 
		ans.nonIncSort(var);
		assertTrue(Arrays.equals(var, expArr));
	}
}
