# 스트리밍 서비스 정산 시스템
<img src="https://raw.githubusercontent.com/wooni423/Biling-System/main/assets/statistic.jpg" alt="Statistics" width="700" height="300" />

## 📌 목차
1. [프로젝트 개요]()
2. [설치 및 실행]()
3. [데이터 베이스 설계]()
4.  [기술 스택]()
5. [주요 기능]()

##  프로젝트 개요
본 프로젝트는 스트리밍 서비스에서 판매자를 위한 정산 페이지를 제공하기 위한 목적으로 개발되었습니다. 동영상 및 광고 관련 통계 및 정산과 관련된 비즈니스 로직을 처리하고 다중 서버 환경에서 성능을 보장하는 것을 목표로 하였습니다.

### 프로젝트 진행기간

2024.06 ~ 2024.07 (5주)

### 프로젝트 배포링크
https://billing-system.example/


## ⚙️ 설치 및 실행

### 요구 사항
- Docker
- Docker Compose
- JDK 17 
- Gradle

###  설치 방법

1.  **레포지토리 복제**
	```bash
	$ git clone https://github.com/wooni423/Biling-System.git
	$ cd Billing-System
	```
2. **Docker 환경 설정**
	```bash
	$ docker-compose up -d
	```
3.  **프로젝트 빌드 및 실행**
	```bash
	$./gradlew build
	$./gradlew bootrun
	```
## 🛠 기술스택

<img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=Java&logoColor=white"> <img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white"> <img src="https://img.shields.io/badge/Spring Data JPA-6DB33F?style=for-the-badge&logo=Spring&logoColor=white"> <img src="https://img.shields.io/badge/Spring MVC-6DB33F?style=for-the-badge&logo=Spring&logoColor=white"> <img src="https://img.shields.io/badge/Spring%20Batch-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
  <img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=Gradle&logoColor=white"> <img src="https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=JSON Web Tokens&logoColor=white">

<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white">

<img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white">
<img src="https://img.shields.io/badge/GitHub Actions-2088FF?style=for-the-badge&logo=GitHub Actions&logoColor=white">

<img src="https://img.shields.io/badge/Git-181717?style=for-the-badge&logo=guthub&logoColor=white"> <img src="https://img.shields.io/badge/AWS%20EC2-569A31?style=for-the-badge&logo=guthub&logoColor=white">

## 📌 주요기능
### 회원 서비스
- **회원 가입**
	- 소설 로그인을 통해 회원 가입 기능을 제공합니다. 
- **로그인 및 로그아웃**
	- 소설 로그인을 통해 회원 가입 기능을 제공합니다. 
- **권한 관리**
	- 사용자 계정과 판매자 계정의  권한을 구분하여 관리합니다.

### 스트리밍 서비스
- **동영상 재생**
	- 등록된 동영상을 재생하며, 조회수와 재생 시간을 기록합니다. 
- **광고 시청**
	- 동영상에 등록된 광고를 시청하며, 광고 시청 횟수를 기록합니다. 

###  동영상 관리
- **동영상 등록**
	- 동영상을 등록하고 관리할 수 있습니다. 
- **광고 등록**
	- 동영상에 등록된 광고를 시청하며, 5분마다 광고가 자동으로 추가됩니다. 

###  통계 및 정산
- **조회수 통계**
	- 1일, 1주일, 1달 동안 조회수가 높은 동영상 TOP5를 제공합니다.
- **재생 시간 통계**
	- 1일, 1주일, 1달 동안 조회수가 높은 동영상 TOP5를 제공합니다.
- **정산 데이터 생성**
	- 동영상 및 광고 조회수를 기반으로 정산 금액을 계산하고 저장합니다.
- **정산 데이터 조회**
	- 1일, 1주일 ,1달 동안 총 정산금액, 영상별 정산금액, 광고별 정산금액을 조회할 수 있습니다.

###  어뷰징 방지

- **조회수 조작 방지**
	- 동영상 게시자가 자신의 영상을 시청하는 경우 조회수와 광고 시청 횟수를 카운트하지 않습니다.
- **중복 시청 방지**
	- 30초 이내에 동일한 IP 또는 인증키로부터의 접속을 어뷰징으로 간주하여 조회수와 시청 횟수를 카운트하지 않습니다.


## 🔥 성능 최적화
- [Spring Batch 멀티 쓰레드 적용으로 배치 속도 개선]()   
  - 성능 개선 수치 및 관련 이미지 추후 추가

- [이미지 최적화로 이미지 크기 75% 축소](https://comprogramming.tistory.com/122)
- [SEO 점수 22% 개선](https://comprogramming.tistory.com/119)
<br/>  

## 🔫 트러블 슈팅
- [Grid 아이템 이미지 로딩 후 리플로우 이슈 해결]()
  
- [상세 상품 prefetch로 인한 불필요한 네트워크 요청 해결]()

## 💭 기술적 의사결정

- [에러 바운더리를 사용한 선언적 에러 핸들링](https://comprogramming.tistory.com/121)   

- [컴파운트 컴포넌트를 활용한 공통 컴포넌트 재사용성과 가독성 향상](https://comprogramming.tistory.com/117)


## 🏗 아키텍쳐



## 🗂 프로젝트 구조
```
┣ 📁__mocks__
┣ 📁__test__
┣ 📁public
┣ 📁src
  ┣ 📁main
      ┣ 📁com
	      ┣ 📁jiwoong
		      ┣ 📁billingsystem
			      ┣ 📁adjustment
			      ┣ 📁global
			      ┣ 📁streaming
      ┣ 📁payment
      ┣ 📁productDetail
      ┣ 📁products
      ┣ 📁ui
  ┣ 📁consts
  ┣ 📁hooks
      ┣ 📁auth
      ┣ 📁cart
      ┣ 📁form
      ┣ 📁home
      ┣ 📁myPage
      ┣ 📁payment
      ┣ 📁productDetail
      ┣ 📁products
      ┣ 📁seller
  ┣ 📁layout
  ┣ 📁lib
  ┣ 📁pages
      ┣ 📁error
  ┣ 📁router
  ┣ 📁store
  ┣ 📁styles
  ┣ 📁types
  ┣ 📁utils
```
- `__mocks__` : 테스트 환경에서 사용되는 mock data 저장
- `__test__` : 단위 테스트 파일 저장
- public : 정적 파일 저장 (svg 파일, SEO 관련 파일 포함)
- src : 소스코드 파일 저장

