## Lettuce
  - 구현이 간단
  - spring data redis를 이용하면 lettuce가 기본이므로 별도의 라이브러리를 사용하지 않아도 된다.
  - spin lock 방식이므로 동시에 많은 스레드가 lock 획득 대기 상태일 때 redis에 부하가 갈 수 있다.

## Redisson
  - 락 획득 재시도를 기본으로 제공
  - pub-sub 방식으로 구현이 되어있기 때문에 lettuce 와 비교했을 때 redis 에 부하가 덜 간다.
  - 별도의 라이브러리를 사용
  - lock 을 라이브러리 차원에서 제공해주기 떄문에 사용법을 공부해야 한다.


## 실무에서는 ?
  - 재시도가 필요하지 않은 lock 은 lettuce 활용
  - 재시도가 필요한 경우에는 redisson 를 활용
