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
		}
		
		return 0;
	}
}
