package level2.프린터;
import java.util.*;
import java.util.stream.Collectors;

class Solution {
	PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
	
	public static void main(String[] args) {
		Solution s = new Solution();
		s.solution(new int[] { 1, 1, 9, 1, 1, 1 },3);
	}

	public int solution(int[] priorities, int location) {
		// 우선순위는 높은 것이 가장 빨리 출력된다. 즉, 내림차순
		pq.addAll(Arrays.stream(priorities).boxed().collect(Collectors.toList()));
		
		System.out.println("입력 받은 인자 : "+Arrays.toString(priorities));
		System.out.println("정렬한    인자 : "+pq);
		
		int order = 0;

		// 우선순위로 정렬한 priorities 큐
		while (!pq.isEmpty()) {
			// priorities 배열 효소를 순회하는 반복문
			for (int i = 0; i < priorities.length; i++) {
				// 우선순위, 즉 큐를 기준으로 priorities배열 요소를 순회한다.
				System.out.println("pq.peek() : "+pq.peek()	+ "   priorities["+i+"] : "+ priorities[i]);
				if (pq.peek() == priorities[i]) {
					if (i == location) {
						order++;
						System.out.println("location : "+location+" 위치 프린트물은 " +order+"번 째로 출력됩니다.");
						return order;
					}
					// 아니면, 순서 증가 및 제일 높은 우선순위를 제거한다.
					order++;
					pq.poll();
				} // if 끝
			} // for 끝
		} // while 끝
		
		return order == 0 ? -1 : order;
	}
	
}