package algorithms;

import java.util.Stack;

/************************************************************************
						���� ������ 
		����, ����, ��, ����, ����, �� ������ ����  ���� �߰� 
************************************************************************/

public class Sorts {
	static private int[] sortsArr;
	
	//��������
	/**********************
	 * 
	 * ���� �⺻���� ������ �ſ� ���� ��� �ӵ��� ���� ����
	 * ��ǰ�� �ߴ� ��ó�� ������� ��� ������ �����ϴ�����
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
	
	//��������
	/*********
	 * 
	 * �տ������� ���������� ���Ͽ� ����ϴ� ���� �ſ�ܼ� ����
	 * ���� ���ʿ�! 
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
			//�������� ���� �ε��� ã��
			for (j=i+1; j<arrSize; j++) {
				if(arr[min] > arr[j]) {
					min = j;
				}
			}
			//�ڸ���ü
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
	
	//��������
	/*****
	 * 
	 * ����� �̿��Ͽ�  �����ϴ� ���
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
		
		//arr���� ������
		for (i=0; i<arrSize; i++) {
			countings[arr[i]] +=1;
		}
		//counting ���� õõ�� ����
		for (i=0; i<max; i++) {
			countings[i+1] = countings[i] + countings[i+1];
		}
		//����
		for (i=0; i<arrSize; i++) {
			countings[arr[i]] -=1;
			purposes[countings[arr[i]]] = arr[i];
		}
		
		return purposes;
	}
	
	//��������
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
			//�ݺ��� �������� j-- ������ 1 ���Ѵ�.
			arr[j+1]=temp;

//			for (index=0; index<arrSize; index++) {
//				System.out.print(arr[index] + ", ");
//			}
//			System.out.println();
		}
		
//		System.out.println("count : " + count);
		
		return arr;
	}
	
	//������(�������� + ����)
	public static int[] shellSort(int[] arr) {
		
		int arrSize =arr.length;
		int i, j, temp, step;
		
		//step�� ����� �����ʵ����ؾ���!
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
	
	//������ ���� ����
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
	
	//�������� ��͹��� 
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
		
		// ��������Ʈ�� ������ �ѱ��̿��� -1�� �Ǵ��ϹǷ� �������� ó���Ѵ�.
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
	
	//�������� ���ù���
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
	
	// ������  �Լ��� �����  (���� ��ť�� �����°� �߰�)
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
	
	//������� �߰�����
	
}

//���û��� ����Ʈ Ŭ����
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


