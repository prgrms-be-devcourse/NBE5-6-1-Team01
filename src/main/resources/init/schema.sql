DROP TABLE IF EXISTS `USERS`;
CREATE TABLE `USERS`
(
    `EMAIL`      VARCHAR(30)  PRIMARY KEY        COMMENT '이메일',
    `PASSWORD`   VARCHAR(20)  NOT NULL           COMMENT '비밀번호',
    `ROLE`       VARCHAR(10)                     COMMENT '사용자/관리자',
    `CREATED_AT` timestamp    NULL DEFAULT now() COMMENT '생성일자',
    `DELETED_AT` timestamp    DEFAULT NULL       COMMENT '탈퇴일자'
);


DROP TABLE IF EXISTS `ITEMS`;
CREATE TABLE `ITEMS`
(
    `ITEM_ID`    int          PRIMARY KEY   COMMENT '아이템번호',
    `ITEM_TYPE`  VARCHAR(20)                COMMENT '아이템타입',
    `ITEM_NAME`  VARCHAR(50)  NOT NULL      COMMENT '아이템이름',
    `ITEM_PRICE` int          NOT NULL      COMMENT '아이템가격',
    `STOCK`      int          DEFAULT 100   COMMENT '재고',
    `IMG`        VARCHAR(255)               COMMENT '아이템사진'
);


DROP TABLE IF EXISTS `ORDERS`;
CREATE TABLE `ORDERS`
(
    `ORDER_ID`     int          PRIMARY KEY        COMMENT '주문번호',
    `EMAIL`        VARCHAR(30)  NOT NULL           COMMENT '이메일',
    `ADDRESS`      VARCHAR(50)  NOT NULL           COMMENT '주소',
    `POSTCODE`     int          NOT NULL           COMMENT '우편번호',
    `ORDER_LIST`   VARCHAR(255)                    COMMENT '주문리스트',
    `TOTAL_PRICE`  int                             COMMENT '총가격',
    `CREATED_AT`   timestamp    NULL DEFAULT now() COMMENT '생성일자',
    `ORDER_STATUS` VARCHAR(10)                     COMMENT '주문상태'

);

DROP TABLE IF EXISTS `ORDERITEMS`;
CREATE TABLE `ORDERITEMS`
(
    `ORDERITEM_ID`    int PRIMARY KEY   COMMENT '주문아이템ID',
    `ORDER_ID`        int               COMMENT '주문번호',
    `ITEM_ID`         int               COMMENT '주문아이템',
    `ITEM_NAME`       varchar(100)      COMMENT '주문아이템이름',
    `ITEM_PRICE`      int               COMMENT '주문아이템가격',
    `ITEM_COUNT`      int               COMMENT '주문아이템갯수'
);
