# gps_App_Demo

## 2021-09-02 진행상황
### 환경구축 상황
- 포트포워딩 서버 환경구축이 어려울 것 같아, aws를 이용하여 서버 환경 구축 완료
- Ubunt 18.04, Nginx, spring boot, Mysql(8version)
- Aws의 ec2, rds 서비스 이용예정

## 2021-09-03 진행상황
### 환경구축 상황
- 포트포워딩 서버 환경구축이 어려울 것 같아, aws를 이용하여 서버 환경 구축 완료
- Ubunt 18.04, Nginx, spring boot, Mysql(8version)
- Aws의 ec2, rds 서비스 이용예정
### 1. 기획서의 변동사항
- 일단은 모바일 어플리케이션에서는, 신호만 보내주는 역할로 개발하기로 하였다.
- 지도 및 실시간 어플리케이션의 사용자들의 위치에 대해서는, 웹에서 확인할 수 있도록 개발하기로 하였다.
### 2. ERD 진행상황
- 기본적으로 User, Location, Restrict 3가지의 테이블로 구성하였으며,
추후에 필요에 따라 확장해 나갈 예정.
### 3. api 리스트업 진행상황
- User와 관련한 부분은 리스트업을 마친상태
### 4. 현재 구현중인 api
- 각 유저에 해당하는 위치와, 제한 구역의 정보를 다룰 수 있는 api 구현중
### 5. 클라이언트 개발자와의 회의내용
- 일단 모바일 어플리케이션은 신호만 보내는 역할을 하도록 하였고, 웹환경에서 모든 정보를 확인하는 방향으로 결정하였다.
### 6. 개발 도중에 발생한 이슈
- .


## 2021-09-08 진행상황
### 1. 기획서의 변동사항
- .
### 2. ERD 진행상황
- https://aquerytool.com/aquerymain/index/?rurl=1fb103d3-947b-42ed-8589-a724fb4d42c1
- passwd : 0v23p4
### 3. api 리스트업 진행상황
- 웹페이지와 관련된 제한구역(Restricted 테이블)관련된 api 리스트업 완료
### 4. 현재 구현중인 api
- 제한구역안에 있는지 아닌지 여부를 boolean 값으로 반환하는 api 구현 중에 있음.
### 5. 클라이언트 개발자와의 회의내용
- .
### 6. 개발 도중에 발생한 이슈
- .

## 2021-09-09 진행상황
### 1. 기획서의 변동사항
- .
### 2. ERD 진행상황
- https://aquerytool.com/aquerymain/index/?rurl=1fb103d3-947b-42ed-8589-a724fb4d42c1
- passwd : 0v23p4
### 3. api 리스트업 진행상황
- 클라이언트 개발자가 구현하면서, 추가적으로 필요한 api가 있을시 바로바로 리스트업 중에 있음.
### 4. 현재 구현중인 api
- jwt 토큰과 관련되어 유저를 식별하는 api 개발중에 있음.
### 5. 클라이언트 개발자와의 회의내용
- jwt 토근 사용 여부에 대한 협의를 했고, 사용하기로 결정하였다.
### 6. 개발 도중에 발생한 이슈
- database test connection이 갑자기 정상적으로 이루어지지 않아서, 해당 AWS rds 서비스의 패스워드를 변경하고 재연결하였다.
- 아직 어떤 이슈에 의해서인지 파악하지 못했고, 급한대로 패스워드 변경으로 해결하였다.

## 2021-09-10 진행상황
- 오프라인 지도를 통한 서비스 구현을 위해 공부중에 있음.
- leaflet이라는 지도 서비스를 이용할 예정임.
### 1. 기획서의 변동사항
- 온라인 서비스의 수준이 데모 수준으로 완성되었기에, 차차 오프라인 지도를 활용하여 데모 앱을 만들기 위해 공부중에 있음.
### 2. ERD 진행상황
- https://aquerytool.com/aquerymain/index/?rurl=1fb103d3-947b-42ed-8589-a724fb4d42c1
- passwd : 0v23p4
### 3. api 리스트업 진행상황
- .
### 4. 현재 구현중인 api
- 특정 유저의 위치 기록을 모두 삭제하는 api구현 중.
### 5. 클라이언트 개발자와의 회의내용
- 온라인 지도를 이용하는 유저 인터페이스의 완성도를 높이는 동안, 내가 오프라인 지도 서비스에 대해서 어떤 것이 사용 가능할지 알아보기로 하였음.
### 6. 개발 도중에 발생한 이슈
- .

