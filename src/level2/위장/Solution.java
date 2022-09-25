package level2.위장;
import static java.util.stream.Collectors.*;

import java.util.Map;
import java.util.stream.Stream;

class Solution {
	public int solution(String[][] clothes) {
		Map<String, Integer> collect = Stream.<String[]>of(clothes)
			  .collect(groupingBy( strArr->strArr[1], //[1]은 종류
					  collectingAndThen(reducing(0L, e -> 1L, Long::sum )
                      					, Long::intValue)));
		
		return collect.values().stream().reduce(1, (a,b) -> a*(b+1) )-1;
        // 각 부위별 +1 은 옷을 안입는 경우의 수이다. 
        // 마지막 -1은 반드시 옷은 1개 이상은 입는 다는 조건이 있기 떄문이다.
	}
}