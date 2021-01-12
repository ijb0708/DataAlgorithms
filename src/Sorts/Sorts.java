package Sorts;

public class Sorts {
	
	public static int[] bubbleSort(int[] arr) {
		
		int arrSize =arr.length-1;
		
		boolean isPass =true;
		int i, temp;
		
		int count =0;
		
		while(true) {
			
			isPass =true;
			for (i=0; i<arrSize; i++) {
				if(arr[i]>arr[i+1]) {
					temp =arr[i];
					arr[i] =arr[i+1];
					arr[i+1] = temp;
					isPass =false;
					count +=1;
				}
			}
			
			if(isPass) {
				break;
			}
		}
		
		System.out.println("repeat : " + count);
		
		return arr;
	}
	
	public static int[] selectionSort(int[] arr) {
		
		int arrSize =arr.length;
		int i, j, min, temp;
		int count =0;
		
		for (i=0; i<arrSize; i++) {
			min =i;
			//가장작은 수의 인덱스 찾기
			for (j=i+1; j<arrSize; j++) {
				if(arr[min] > arr[j]) {
					min = j;
				}
			}
			//자리교체
			if(min != i) {
				temp =arr[min];
				arr[min] =arr[i];
				arr[i] =temp;
				
				count +=1;
			}
		}
		
		System.out.println("repeat : " + count);
		
		return arr;
	}
	
	public static int[] countingSort(int[] arr) {
			
		int arrSize =arr.length;
		int max=0;
		int i, count=0;
		
		for (i=0; i<arrSize; i++) {
			if(max<arr[i]) {
				max =arr[i];
			}
		}
		
		int[] countings =new int[max +1];
		int[] purposes =new int[arrSize];
		
		//arr에서 값추출
		for (i=0; i<arrSize; i++) {
			countings[arr[i]] +=1;
		}
		//counting 에서 천천히 더함
		for (i=0; i<max; i++) {
			countings[i+1] = countings[i] + countings[i+1];
		}
		//정렬
		for (i=0; i<arrSize; i++) {
			countings[arr[i]] -=1;
			purposes[countings[arr[i]]] = arr[i];
		}
		
		return purposes;
	}
	
	public static int[] insertionSort(int[] arr) {
		
		int arrSize =arr.length;
		int i, j, temp, count=0;
		
		for (i=1; i<arrSize; i++) {
			temp =arr[i];
			j=i-1;
			while(temp<arr[j] && j>=0) {
				arr[j+1] =arr[j];
				j--;
				count+=1;
			}
			//반복문 마지막의 j-- 때문에 1 더한다.
			arr[j+1]=temp;
		}
		
		System.out.println("count : " + count);
		
		return arr;
	}
	
	public static int[] shellSort1(int[] arr) {
		
		int arrSize =arr.length;
		// gap 은 간격을 의미
		int i, j, temp, gap =arrSize/2;
		
		while(gap>0) {
			
			gap /=2;
		}
		
		return null;
	}
	
	public static int[] shellSort2(int[] arr) {
		return null;
	}
	
}
