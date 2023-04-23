# D의 의지 - SulSul
* JDK17
* SpringBoot 3.0.6
* CDK for TF

# 멀티모듈
* sulsul-api
  * 클라이언트로부터 인바운드 요청을 받는 모듈
* sulsul-domain
  * 비즈니스 로직을 포함하는 모듈

# 아키텍처
* CI/CD
  * Github Action
* Compute
  * EC2
* Storage
  * MongoDB
  * MariaDB, PostgreSQL
  * S3
* Log
  * Elastic Beanstalk  
    * CloudWatch
* API Docs
  * Swagger

# 리뷰 문화
* 버그가 있는 코드, 성능 상 문제가 있는 코드인지를 중점적으로 리뷰한다.
* 상대방의 구현 방식을 존중하고, 의견이 있다면 approve와 함께 첨언한다. 구현의 어떠함은 approve를 막을 수 없다.

# Conventions
pr convention: PR 요청 후 본인의 작업 내용에 풍부한 설명을 코멘트로 남겨둔다.  
commit convention: https://gist.github.com/stephenparish/9941e89d80e2bc58a153
