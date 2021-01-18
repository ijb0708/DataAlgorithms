package StatMain;

import Sorts.Sorts;

public class StartMain {

	public static void main(String[] args) {
		
		int[] arr1 = {2, 4, 3, 30, 32, 10, 12};
		int[] arr2 = {3, 5, 10, 33, 23, 50};
		int[] arr3 = {4, 5, 3, 10, 20, 30, 40, 70, 80, 100, 90, 39, 92, 42, 34, 43};
		
		int[] RandomArr =new int[100000];
		int index;
		for (index =0; index<RandomArr.length; index++) {
			RandomArr[index] =(int) (Math.random() * 100);
		}
		
		long startTime, endTime;
		
		startTime =System.nanoTime();
//		Sorts.mergeArr(arr1, 0, 1, 4);
		int[] result = Sorts.bubbleSort(arr1);
		endTime =System.nanoTime();
		
		System.out.print("t: ");
		for (int i=0; i<result.length; i++) {
			System.out.print(i + ":" + result[i] + ", ");
		}
		
		System.out.println();
		System.out.println("time is " + (endTime - startTime));
	}

}
