package level1.숫자_문자열과_영단어;
import java.util.stream.Stream;

class Solution4 {
	//변환처리를 위한 배열, 순서 중요
	final static String[] INDEX_TO_WORD = 
		{ "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
	final static String NUM_STR = "0123456789";
	final static int WORD_LENGTH = 10;

	public static void main(String[] args) {
		Solution4 s = new Solution4();
		System.out.println(
		s.solution("2three45sixseven")
		);
	}
	
	public int solution(String s) {
		return Stream.of(s).mapToInt( arg ->{
			for(int i = 0 ; i < WORD_LENGTH ; i++) {
				arg = arg.replace(INDEX_TO_WORD[i] , String.valueOf(NUM_STR.charAt(i)) );
			}
			return Integer.valueOf(arg).intValue();
		}).sum();
	}
}