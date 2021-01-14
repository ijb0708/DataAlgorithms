package Sorts;

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
		}while(isPass);
		
		System.out.println("repeat : " + count);
		
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
		
		System.out.println("repeat : " + count);
		
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
		
		for (i=1; i<arrSize; i++) {
			temp =arr[i];
			j=i-1;
			while(temp<arr[j] && j>=0) {
				arr[j+1] =arr[j];
				j--;
				count+=1;
			}
			//�ݺ��� �������� j-- ������ 1 ���Ѵ�.
			arr[j+1]=temp;
		}
		
		System.out.println("count : " + count);
		
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
	
	//�������� ���ù��� (������)
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

//���û��� ����Ʈ Ŭ����
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

