# Chatting Program

## 🎥 (1) 시연 연상
![시연 영상](https://user-images.githubusercontent.com/48561660/152469640-aa1c5da3-52e7-4bde-a0d3-1efdc234cd0c.gif)

## 👨‍🏫 (2) 기능 설명

### (1) 유저
- 회원 가입
- 로그인 / 로그아웃
    - 후행 중복 로그인 시도 시, 선행 로그인 사용자 세션 만료
    - 비로그인 사용자만 채팅기능 사용 불가

### (2) 채팅
- 채팅방 리스트 조회
- 채팅 기능
    - 다중 사용자 접속 시, 채팅 기능 가능
    - 중도 사용자 접속 시, 이전 채팅 내용 조회 가능

<br>

##  🪛 (3) 구현 기술

- Java 8
- Spring
  - SpringBoot
  - Spring Data JPA
  - Spring Integration MQTT
  - Spring Security
  - Thymeleaf
- Lombok

<br>

## 🏗 (4) Architecture
`Publish/Subscribe Model(발행 구독 모델)`
1. 사용자는 MessageBroker를 통해 MQTT 메세지를 발행합니다.
2. MessageBroker는 특정 topic을 구독하는 사용자에게 발행자의 MQTT 메세지를 전달합니다.
3. 구독하는 사용자는 발행한 메세지를 전달받아 채팅창에 메세지를 출력합니다.

![image](https://user-images.githubusercontent.com/48561660/152463391-c30f1f2c-1fca-4cb8-b5da-e7bb60ce6cd6.png)

<br>

## (5) Mosquitto in Docker
```shell
# 1. mosquitto image pull
docker pull cooper0205/mosquitto

# 2. create container and run
docker run --name cooper-mosquitto -p 1883:1883 -p 9001:9001 cooper0205/mosquitto
```


