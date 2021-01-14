package StatMain;

import Sorts.Sorts;

public class StartMain {

	public static void main(String[] args) {
		
		int[] arr1 = {2, 4, 3, 30, 32, 10, 12, 23};
		int[] arr2 = {3, 5, 10, 33, 23, 50};
		
//		Sorts.mergeArr(arr1, 0, 1, 4);
		int[] result = Sorts.mergeSort(arr1);
		
		System.out.print("t: ");
		for (int i=0; i<result.length; i++) {
			System.out.print(i + ":" + result[i] + ", ");
		}
	}

}
