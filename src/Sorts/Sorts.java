package Sorts;

import java.util.Stack;

/************************************************************************
						���� ������ 
		����, ����, ��, ����, ����, �� ������ ����  ���� �߰� 
************************************************************************/

public class Sorts {
	
	//��������
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
	
	//��������
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
		// gap �� ������ �ǹ�
		for(int h=arrSize/2; h>0; h/=2) {
			for(int i=h; i<arrSize; i++) {
				int j;
				int tmp =arr[i];
				for (j=i-h; j>=0 && arr[j] >tmp; j-=h) {
					arr[j+h] =arr[j];
				}
				arr[j+h] =tmp;
			}
		}
		
		return arr;
	}
	
	//������
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
	
	//��������(������) �����̸�....
	public static int[] mergeSort(int[] arr) {
		
		int arrSize =arr.length;
		int left =0, right =arrSize-1;
		Stack<point> pStack =new Stack<point>();
		
		while(true) {
			
			if(true) {
				break;
			}
		}
		
		return null;	
	}
	
	//���ľƴ�!! �������Ľ� ����������!
	private static int[] mergeArray(int[] arr1, int[] arr2) {

		int[] res1 =quickSort(arr1);
		int[] res2 =quickSort(arr2);
		
		int resSize1 =res1.length;
		int resSize2 =res2.length;
		
		int resPoint1 =0;
		int resPoint2 =0;
		int resPoint3 =0;
		
		int[] res3 =new int[resSize1 + resSize2];
		
		while(resPoint1<resSize1 && resPoint2<resSize2) {
			if(res1[resPoint1]>res2[resPoint2]) {
				res3[resPoint3] =res2[resPoint2];
				resPoint2++;
			}else {
				res3[resPoint3] =res2[resPoint1];
				resPoint1++;
			}
			resPoint3++;
		}
		
		while(resPoint1<resSize1) {
			res3[resPoint3] =res1[resPoint1];
			resPoint1++;
			resPoint3++;
		}

		while(resPoint2<resSize2) {
			res3[resPoint3] =res2[resPoint2];
			resPoint2++;
			resPoint3++;
		}
		
		return res3;	
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

