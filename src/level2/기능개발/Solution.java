package level2.기능개발;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
    	int before= -1;
    	int count = 0;
    	List<Integer> deployList = new ArrayList<Integer>(); // 배포 기능 수를 담을 리스트
    	
    	int[] remainderArr = IntStream.range(0, speeds.length) // 인덱스 번호를 위한 스트림
    	         .map( i-> requiredDate(progresses[i], speeds[i]))
    	         .toArray();
    	
    	for(int i=0;i<remainderArr.length;i++) {
    		if(i==0)before = remainderArr[i];
    		
    		if(before<remainderArr[i] ) { // 만약 이전 작업보다 내 남은 작업량이 많다면, 이전 기능은 배포될 것이다.
    			deployList.add(count);
                before = remainderArr[i]; // 이제 이 작업이 기준이된다.
    			count=0;				// 배포한 이후 카운터는 초기화
    		}
    		count++;
    		if(remainderArr.length-1 == i) deployList.add(count);
    	}
        return deployList.stream().mapToInt(Integer::intValue).toArray();
    }
    
    private int requiredDate(int progresse, int  speed) {
    	int remainder = 100 - progresse;
    	//나머지가 1이라도 존재하면, 배포도 미뤄진다.
    	return remainder % speed>0? remainder/speed +1 : remainder/speed ; 
    }
}