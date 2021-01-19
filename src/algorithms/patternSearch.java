package algorithms;

public class patternSearch {
	public static int kmpSearch(String txt, String pattern) {
		
		int patternSize =pattern.length();
		int[] skipTable =new int[patternSize];
		int patternIndex, skipIndex, readIndex;
		
		skipIndex =0;
		for(patternIndex =1; patternIndex<patternSize; patternIndex++) {
			if(pattern.charAt(skipIndex) == pattern.charAt(patternIndex)) {
				skipIndex++;
				skipTable[patternIndex] =skipIndex;
			}else {
				skipIndex =skipTable[skipIndex];
			}
		}

		skipTable[0] =-1;
		
		for (int i =0; i<skipTable.length; i++) {
			System.out.print(skipTable[i] + ", ");
		}
		System.out.println();
		
		readIndex =skipIndex =0;
		int txtSize =txt.length();
		while(readIndex < txtSize) { 
			System.out.println("a: " + pattern.charAt(skipIndex) + " / b: " + txt.charAt(readIndex));
			System.out.println("1: " + skipIndex + " / 2: " + readIndex);
			System.out.println();
			if(pattern.charAt(skipIndex) == txt.charAt(readIndex)) {
				skipIndex++;
				readIndex++;
			}else if(skipIndex>0 && pattern.charAt(skipIndex) != txt.charAt(readIndex)) {
				skipIndex =skipTable[skipIndex];
			}else {
				readIndex++;
			}
			
			if(skipIndex == patternSize-1) {
				System.out.println("find this " + (readIndex - patternSize + 1));
				skipIndex =skipTable[skipIndex];
			}
		}
		
		return 0;
	}
}
