# Chatting Program

## ๐ฅ (1) ์์ฐ ์ฐ์
![แแตแแงแซ แแงแผแแกแผ](https://user-images.githubusercontent.com/48561660/152469640-aa1c5da3-52e7-4bde-a0d3-1efdc234cd0c.gif)

## ๐จโ๐ซ (2) ๊ธฐ๋ฅ ์ค๋ช

### (1) ์ ์ 
- ํ์ ๊ฐ์
- ๋ก๊ทธ์ธ / ๋ก๊ทธ์์
    - ํํ ์ค๋ณต ๋ก๊ทธ์ธ ์๋ ์, ์ ํ ๋ก๊ทธ์ธ ์ฌ์ฉ์ ์ธ์ ๋ง๋ฃ
    - ๋น๋ก๊ทธ์ธ ์ฌ์ฉ์๋ง ์ฑํ๊ธฐ๋ฅ ์ฌ์ฉ ๋ถ๊ฐ

### (2) ์ฑํ
- ์ฑํ๋ฐฉ ๋ฆฌ์คํธ ์กฐํ
- ์ฑํ ๊ธฐ๋ฅ
    - ๋ค์ค ์ฌ์ฉ์ ์ ์ ์, ์ฑํ ๊ธฐ๋ฅ ๊ฐ๋ฅ
    - ์ค๋ ์ฌ์ฉ์ ์ ์ ์, ์ด์  ์ฑํ ๋ด์ฉ ์กฐํ ๊ฐ๋ฅ

<br>

##  ๐ช (3) ๊ตฌํ ๊ธฐ์ 

- Java 8
- Spring
  - SpringBoot
  - Spring Data JPA
  - Spring Integration MQTT
  - Spring Security
  - Thymeleaf
- Lombok

<br>

## ๐ (4) Architecture
`Publish/Subscribe Model(๋ฐํ ๊ตฌ๋ ๋ชจ๋ธ)`
1. ์ฌ์ฉ์๋ MessageBroker๋ฅผ ํตํด MQTT ๋ฉ์ธ์ง๋ฅผ ๋ฐํํฉ๋๋ค.
2. MessageBroker๋ ํน์  topic์ ๊ตฌ๋ํ๋ ์ฌ์ฉ์์๊ฒ ๋ฐํ์์ MQTT ๋ฉ์ธ์ง๋ฅผ ์ ๋ฌํฉ๋๋ค.
3. ๊ตฌ๋ํ๋ ์ฌ์ฉ์๋ ๋ฐํํ ๋ฉ์ธ์ง๋ฅผ ์ ๋ฌ๋ฐ์ ์ฑํ์ฐฝ์ ๋ฉ์ธ์ง๋ฅผ ์ถ๋ ฅํฉ๋๋ค.

![image](https://user-images.githubusercontent.com/48561660/152463391-c30f1f2c-1fca-4cb8-b5da-e7bb60ce6cd6.png)

<br>

## (5) Mosquitto in Docker
```shell
# 1. mosquitto image pull
docker pull cooper0205/mosquitto

# 2. create container and run
docker run --name cooper-mosquitto -p 1883:1883 -p 9001:9001 cooper0205/mosquitto
```


