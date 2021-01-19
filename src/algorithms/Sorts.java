package algorithms;

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
		}while(!isPass);
		
		System.out.println("\n repeat : " + count);
		
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
		
//		System.out.println("repeat : " + count);
		
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
		int index;
		
//		System.out.println(arrSize);
//		for (index=0; index<arrSize; index++) {
//			System.out.print(arr[index] + ", ");
//		}
//		System.out.println();
		for (i=1; i<arrSize; i++) {
			temp =arr[i];
			j=i-1;
			while(j>=0 && temp<arr[j]) {
				arr[j+1] =arr[j];
				j--;
				count+=1;
			}
			//반복문 마지막의 j-- 때문에 1 더한다.
			arr[j+1]=temp;

//			for (index=0; index<arrSize; index++) {
//				System.out.print(arr[index] + ", ");
//			}
//			System.out.println();
		}
		
//		System.out.println("count : " + count);
		
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
			
			left =pt.getStart();
			right =pt.getEnd();
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
			
			if(pt.getStart() < right) pStack.push(new point(pt.getStart(), right));
			if(left < pt.getEnd()) pStack.push(new point(left, pt.getEnd()));
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
		
//		System.out.println("start : " + start +  " end : " + end);
		middle =(start+end)/2;
		reqMerge(start, middle);
		reqMerge(middle+1, end);
		
		mergeArray(start, middle, middle+1, end);
	}
	
	private static void mergeArray(int startPoint1, int endPoint1, int startPoint2, int endPoint2) {
		
		int arrSize =sortsArr.length;
		int firstPointIndex =startPoint1, secondPointIndex =startPoint2, tempIndex=startPoint1, index=0;
		int[] tempArr =new int[arrSize];
		
		// 엔드포인트는 위에서 총길이에서 -1로 판단하므로 같음으로 처리한다.
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

//		System.out.print("tempArr : ");
//		for(int i=0; i<tempArr.length; i++) {
//			System.out.print(tempArr[i] + ", " );
//		}
//		System.out.println();
		
		for(index =startPoint1; index<=endPoint2; index++) {
			sortsArr[index] =tempArr[index];
		}		

//		System.out.print("sortsArr : ");
//		for(int i=0; i<sortsArr.length; i++) {
//			System.out.print(sortsArr[i] + ", " );
//		}
//		System.out.println();
		
		return;
		
	}
	
	//병합정렬 스택버젼
	public static int[] mergeSortOnStack(int[] arr) {
		
		int arrSize = arr.length;
		int start, end, middle;
		point stackPoint;
		
		Stack<point> splitStack =new Stack<point>();
		Stack<point> mergeStack =new Stack<point>();
		
		splitStack.push(new point(0, arrSize-1));
		
		while(!splitStack.isEmpty()){
			
			stackPoint =splitStack.pop();
			start =stackPoint.getStart();
			end =stackPoint.getEnd();
			
			if(start >= end) {
				continue;
			}
			
//			System.out.println("start : " + start + " Right : " + end);
			
			middle =(start + end)/2;
			splitStack.push(new point(start, middle));
			splitStack.push(new point(middle+1, end));
			
			mergeStack.push(new point(start, end));
		}
		
		int[] tempArr =new int[arrSize];
		int firstStartPoint, firstEndPoint, firstIndex, secondStartPoint, secondEndPoint, secondIndex, tempIndex, index;
		
		while(!mergeStack.isEmpty()) {
			
			stackPoint =mergeStack.pop();
			
			firstStartPoint =stackPoint.getStart();
			firstEndPoint =(stackPoint.getStart()+stackPoint.getEnd())/2;
			secondStartPoint =((stackPoint.getStart()+stackPoint.getEnd())/2)+1;
			secondEndPoint =stackPoint.getEnd();
			
			firstIndex =firstStartPoint;
			secondIndex =secondStartPoint;
			tempIndex =firstStartPoint;
			
			while(firstIndex<=firstEndPoint && secondIndex<=secondEndPoint) {
				if(arr[firstIndex] < arr[secondIndex]) {
					tempArr[tempIndex] =arr[firstIndex];
					firstIndex++;
				}else {
					tempArr[tempIndex] =arr[secondIndex];
					secondIndex++;
				}
				tempIndex++;
			}
			
			for(index =firstIndex; index<=firstEndPoint; index++) {
				tempArr[tempIndex] =arr[index];
				tempIndex++;
			}
			
			for(index =secondIndex; index<=secondEndPoint; index++) {
				tempArr[tempIndex] =arr[index];
				tempIndex++;
			}
			
			for(index=firstStartPoint; index<=secondEndPoint; index++) {
				arr[index] =tempArr[index];
			}
			
		}
		
		return arr;
	}
	
	// 힙정렬  함수로 나누어서  (추후 원큐로 돌리는거 추가)
	public static int[] heapSort(int[] arr) {
		
		sortsArr =arr;
		int arrSize =sortsArr.length;
		
		int parentsIndex, leftChildIndex, rightChildIndex, rootIndex;
		int endPointIndex, checkIndex, biggestIndex, temp, index;

//		for(index =0; index<arrSize; index++) {
//			System.out.print(sortsArr[index] + ", ");
//		}
//		System.out.println();
		
		rootIndex =0;
		for(endPointIndex =arrSize; endPointIndex>0; endPointIndex--) {
//			System.out.println("endPointIndex : " + endPointIndex);
			for(checkIndex =(endPointIndex/2)-1; checkIndex>=0; checkIndex--) {
				parentsIndex =checkIndex;
				biggestIndex =checkIndex;
				while(true) {
					leftChildIndex =parentsIndex * 2 +1; 
					rightChildIndex =parentsIndex * 2 +2;
					
					temp =sortsArr[parentsIndex];
//					System.out.println("parents : " + parentsIndex + " / leftChildIndex : " + leftChildIndex + " / rightChildIndex : " + rightChildIndex);
					
					if(leftChildIndex <endPointIndex && sortsArr[leftChildIndex] > sortsArr[biggestIndex]) {
						biggestIndex =leftChildIndex;
					}
					if(rightChildIndex <endPointIndex && sortsArr[rightChildIndex] > sortsArr[biggestIndex]) {
						biggestIndex =rightChildIndex;
					}
					if(parentsIndex == biggestIndex){
						break;
					}	
					
//					System.out.println("change >> " + "parents : " + parentsIndex + " / biggestIndex : " + biggestIndex + " / leftChildIndex : " + leftChildIndex + " / rightChildIndex : " + rightChildIndex);
					
					
					sortsArr[parentsIndex] =sortsArr[biggestIndex];
					sortsArr[biggestIndex] =temp;
					
//					for(index =0; index<arrSize; index++) {
//						System.out.print(sortsArr[index] + ", ");
//					}
//					System.out.println();
					
					parentsIndex =biggestIndex;
				}
			}
			
			temp =sortsArr[endPointIndex-1];
			sortsArr[endPointIndex-1] =sortsArr[rootIndex];
			sortsArr[rootIndex] =temp;
			
		}
		
		return sortsArr;
	}
	
	//기수정렬 추가예정
	
}

//스택사용시 포인트 클래스
class point {
	private int start;
	private int end;
	public point(int start,  int end) {
		this.start =start;
		this.end =end;
	}
	public int getEnd() {
		return end;
	}
	public int getStart() {
		return start;
	}
}


