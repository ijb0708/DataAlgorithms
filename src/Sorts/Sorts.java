package Sorts;

import java.util.Stack;

/************************************************************************
						정렬 모음집 
		버블, 선택, 퀵, 도수, 삽입, 셸 등으로 구성  추후 추가 
************************************************************************/

public class Sorts {
	static private int[] sortsArr;
	
	//버블정렬
	/**********************
	 * 
	 * 아주 기본적인 정렬임 매우 쉬움 대신 속도는 보장 못함
	 * 거품이 뜨는 것처럼 가벼운게 뜬는 구조로 정렬하는형태
	 * 
	 * @param arr
	 * @return
	 */
	public static int[] bubbleSort(int[] arr) {
		
		int arrSize =arr.length-1;
		
		boolean isPass =true;
		int i, temp;
		
		int count =0;
		
		do {		
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
		}while(isPass);
		
		System.out.println("repeat : " + count);
		
		return arr;
	}
	
	//선택정렬
	/*********
	 * 
	 * 앞에서부터 직관적으로 비교하여 사용하는 정렬 매우단순 무식
	 * 설명 불필요! 
	 * 
	 * @param arr
	 * @return
	 */
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
	
	//도수정렬
	/*****
	 * 
	 * 계산을 이용하여  정렬하는 방식
	 * 
	 * @param arr
	 * @return
	 */
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
	
	//삽입정렬
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
	
	//셸정렬(삽입정렬 + 공간)
	public static int[] shellSort(int[] arr) {
		
		int arrSize =arr.length;
		int i, j, temp, step;
		
		//step이 배수가 되지않도록해야함!
		// 1, 4, 13, ...
		for(step=1; step<arrSize/9; step=step*3+1);
		
		while(step>0) {
			for(i=step; i<arrSize; i++) {
				temp =arr[i];
				j=i-step;
				while(j>=0 && temp<arr[j]) {
					arr[j+step] =arr[j];
					j-=step;
				}
				arr[j+step] =temp;
			}
			step/=3;
		}
		return arr;
	}
	
	//퀵정렬 스택 버젼
	public static int[] quickSort(int[] arr) {
		
		int arrSize =arr.length;
		int pivot, left, right, temp;
		
		Stack<point> pStack = new Stack<point>();
		pStack.push(new point(0, arrSize-1));
		
		while(!pStack.isEmpty()) {
			point pt =pStack.pop();
			
			left =pt.getLeft();
			right =pt.getRight();
			pivot =(left+right)/2;
			
			do{
				while(arr[pivot] > arr[left]) left++;
				while(arr[pivot] < arr[right]) right--;
				
				if(left<=right) {
					temp =arr[left];
					arr[left] =arr[right];
					arr[right] =temp;
					left++;
					right--;
				}
				
			}while(left<=right);
			
			if(pt.getLeft() < right) pStack.push(new point(pt.getLeft(), right));
			if(left < pt.getRight()) pStack.push(new point(left, pt.getRight()));
		}
		
		return arr;
	}
	
	//병합정렬 재귀버젼 
	public static int[] mergeSort(int[] arr) {
		
		sortsArr=arr;
		reqMerge(0, arr.length-1);
		
        return arr;

	}
	
	private static void reqMerge(int start, int end) {
		
		int arrSize =sortsArr.length;
		int middle;
		
		if(start >= end) {
			return;
		}
		
		System.out.println("start : " + start +  " end : " + end);
		middle =(start+end)/2;
		reqMerge(start, middle);
		reqMerge(middle+1, end);
		
		mergeArray(start, middle, middle+1, end);
	}
	
	private static void mergeArray(int startPoint1, int endPoint1, int startPoint2, int endPoint2) {
		
		int arrSize =sortsArr.length;
		int firstPointIndex =startPoint1, secondPointIndex =startPoint2, tempIndex=0;
		int index;
		int[] tempArr =new int[arrSize];
		
		while(firstPointIndex<=endPoint1 && secondPointIndex<=endPoint2) {
			if(sortsArr[firstPointIndex] < sortsArr[secondPointIndex]) {
				tempArr[tempIndex] =sortsArr[firstPointIndex];
				firstPointIndex++;
			}else {
				tempArr[tempIndex] =sortsArr[secondPointIndex];
				secondPointIndex++;
			}
			tempIndex++;
		}
		
		for(index =firstPointIndex; index<=endPoint1; index++) {
			tempArr[tempIndex] =sortsArr[index];
			tempIndex++;
		}
		
		for(index =secondPointIndex; index<=endPoint2; index++) {
			tempArr[tempIndex] =sortsArr[index];
			tempIndex++;
		}
		
		for(index=startPoint1; index<=endPoint2; index++) {
			sortsArr[index] =tempArr[index];
		}
		
		return;
		
	}
	
	//병합정렬 스택버젼 (제작중)
	public static int[] MergeSortOnStack(int[] arr) {
		
		int arrSize = arr.length;
		int left, right, mid;
		
		Stack<point> splitStack =new Stack<point>();
		Stack<point> mergeStack =new Stack<point>();
		
		splitStack.push(new point(0, arrSize));
		
		while(!splitStack.isEmpty()){
			
			point pt =splitStack.pop();
			
			mid =(pt.getLeft() + pt.getRight())/2;
			if( (pt.getRight() - pt.getLeft()) > 1) {
				System.out.println("Left" + pt.getLeft() + "Right" + pt.getRight());
				splitStack.push(new point(pt.getLeft(), mid));
				splitStack.push(new point(mid+1, pt.getRight()));
				
			}
			
		}
		return arr;
	}
	
}

//스택사용시 포인트 클래스
class point {
	private int left;
	private int right;
	public point(int left,  int right) {
		this.right =right;
		this.left =left;
	}
	public int getLeft() {
		return left;
	}
	public int getRight() {
		return right;
	}
}

