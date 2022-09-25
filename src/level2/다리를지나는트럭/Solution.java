package level2.다리를지나는트럭;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
	int answer = 1;
	// 트럭 대기열 큐
	LinkedList<Truck> waitingQ;
	// 다리에서 운행중인 트럭 큐
	LinkedList<Truck> bridgeQ;
	Integer sum; // 다리 위 트럭들 총 중량

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.solution(2, 10, new int[] {7,4,5,6})); 
		System.out.println(s.solution(100, 100, new int[] {10}));
		System.out.println(s.solution(100, 100, new int[] { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 }));

	}
	public int solution(int bridge_length, int weight, int[] truck_weights) {
		// 초기화
		waitingQ = IntStream.range(0, truck_weights.length)
                            .mapToObj( i-> new Truck(truck_weights[i],0) )
                            .collect(Collectors.toCollection(LinkedList::new));
		bridgeQ = new LinkedList<>();
		
		// 루프 탈출은 오롯이 bridgeQ가 비었는지에 의존한다.
		while (true) {
			//목표, 대기큐에 지움과 동시에 대기큐 자리에 바로 채워서 빈 순간이 없게 만들기
			//따라서, 가장 먼저 bridgeQ에서 제거해야 한다.
			
			// 다리 맨 앞 트럭의 달린 거리를 측정해 기준 거리 만큼 달렸으면 지운다.
			if(!bridgeQ.isEmpty() && bridge_length<=bridgeQ.peek().getRunningDistance() ) {
				bridgeQ.remove();
			}
			//지우고 난 뒤(다리를 빠져나옴) 다리위 트럭의 총 무게를 측정한다.
			sum = bridgeQ.stream().map(truck->Integer.valueOf(truck.getWeight()))
                                  .reduce(0, Integer::sum);
			
			//트럭이 나간 만큼 빈자리를 다른 트럭으로 채운다.
			if(!waitingQ.isEmpty()) {
				//총 무게 + 다음에 추가될 트럭 무게가 제한 중량보다 작으면 추가가 가능하다.
				if(sum+waitingQ.peekFirst().getWeight() <= weight ) {
					bridgeQ.add(waitingQ.poll());
				}
			}
			//다리 위 모든 트럭에 달린 거리 1을 추가한다.
			bridgeQ.forEach(Truck::addDistance);
			//총 걸린 시간 계산
			if(!bridgeQ.isEmpty()) answer++;
			//bridgeQ가 비어있다 == 모든 트럭이 지나갔다.
			if(bridgeQ.isEmpty()) break;
			
			print();
		}//while 끝
		//동시에 3개 돌릴 시 결과 바꿔치기
		int tmp = answer;
		answer= 1;
		return tmp;
	}
	
	void print() {
		sum = bridgeQ.stream().map(truck->Integer.valueOf(truck.getWeight())).reduce(0, Integer::sum);
		System.out.println("현재 하중 : "+sum);
		System.out.println("waitingQ"+waitingQ);
		System.out.println("bridgeQ"+bridgeQ);
		System.out.println();
	}
	
	class Truck {
		private int weight;
		private int runningDistance;

		public Truck(int weight, int runningDistance) {
			super();
			this.weight = weight;
			this.runningDistance = runningDistance;
		}

		@Override
		public String toString() {
			return "[weight=" + weight + ", length=" + runningDistance + "]";
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		public int getRunningDistance() {
			return runningDistance;
		}

		public void setRunningDistance(int runningDistance) {
			this.runningDistance = runningDistance;
		}

		public void addDistance() {
			this.runningDistance += 1;
		}
	}
}