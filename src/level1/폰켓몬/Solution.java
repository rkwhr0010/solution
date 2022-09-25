package level1.폰켓몬;

import java.util.stream.IntStream;

class Solution {
	public int solution(int[] nums) {
		int length = IntStream.of(nums)
				 .distinct()
				 .toArray()
				 .length; 
		int choice = nums.length/2;
		return length < choice? length : choice ;
	}
}