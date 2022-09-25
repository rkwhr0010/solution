package level1.완주하지못한선수;

import static java.util.stream.Collectors.*;

import java.util.*;
import java.util.stream.Stream;

class Solution {
    public String solution(String[] participant, String[] completion) {
    	// 이름 , 동명인 수
    	Map<String, Long> collect = Stream.<String>of(participant)
    			.collect(groupingBy( u->u , counting()));
    	// 완주자 제거
    	for(String str : completion) {
    		if(collect.containsKey(str)) {
    			collect.put(str, collect.get(str)-1 );
    		}
    	}
        return collect.entrySet().stream()
				.filter( entry -> entry.getValue()>0 )
				.map( entry -> entry.getKey() )
				.toArray( String[]::new )[0];
    }
}