DROP TABLE IF EXISTS `USERS`;
CREATE TABLE USERS
(
    USER_ID    INT      AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '사용자 ID',
    EMAIL      VARCHAR(30)                         NOT NULL COMMENT '이메일',
    PASSWORD   VARCHAR(255)                        NOT NULL COMMENT '비밀번호',
    ROLE       VARCHAR(10)                         NULL     COMMENT '사용자/관리자',
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL     COMMENT '생성일자',
    DELETED_AT TIMESTAMP                           NULL     COMMENT '탈퇴일자'
);

DROP TABLE IF EXISTS `ITEM_IMG`;
CREATE TABLE `ITEM_IMG`
(
    `ITEM_IMG_ID`       int         NOT NULL auto_increment PRIMARY KEY  COMMENT '파일번호',
    `ITEM_ID`           int         NOT NULL                             COMMENT '파일번호',
    `ORIGIN_FILE_NAME` VARCHAR(255)  NOT NULL                            COMMENT '원본파일명',
    `RENAME_FILE_NAME` VARCHAR(255)  NOT NULL                            COMMENT '저장파일명',
    `SAVE_PATH`        VARCHAR(255) NOT NULL                             COMMENT '저장경로',
    `CREATED_AT`       timestamp    NULL       DEFAULT now()             COMMENT '파일등록일자'
);


DROP TABLE IF EXISTS `ITEMS`;
CREATE TABLE `ITEMS`
(
    `ITEM_ID`    int          AUTO_INCREMENT PRIMARY KEY   COMMENT '아이템번호',
    `ITEM_TYPE`  VARCHAR(20)                               COMMENT '아이템타입',
    `ITEM_NAME`  VARCHAR(50)  NOT NULL                     COMMENT '아이템이름',
    `ITEM_PRICE` int          NOT NULL                     COMMENT '아이템가격',
    `STOCK`      int          DEFAULT 100                  COMMENT '재고'
);


DROP TABLE IF EXISTS `ORDERS`;
CREATE TABLE `ORDERS`
(
    `ORDER_ID`     int          AUTO_INCREMENT PRIMARY KEY        COMMENT '주문번호',
    `EMAIL`        VARCHAR(30)  NOT NULL                          COMMENT '이메일',
    `ADDRESS`      VARCHAR(50)  NOT NULL                          COMMENT '주소',
    `POSTCODE`     int          NOT NULL                          COMMENT '우편번호',
    `TOTAL_PRICE`  int                                            COMMENT '총가격',
    `CREATED_AT`   timestamp    DEFAULT now()                     COMMENT '생성일자',
    `ORDER_STATUS` VARCHAR(10)                                    COMMENT 'ORDER/CANCEL/DELIVERY'
);

DROP TABLE IF EXISTS `ORDERITEMS`;
CREATE TABLE `ORDERITEMS`
(
    `ORDERITEM_ID`    int AUTO_INCREMENT PRIMARY KEY   COMMENT '주문아이템 ID',
    `ORDER_ID`        int                              COMMENT '주문번호',
    `ITEM_ID`         int                              COMMENT '주문아이템',
    `ITEM_NAME`       varchar(100)                     COMMENT '주문아이템이름',
    `ITEM_PRICE`      int                              COMMENT '주문아이템가격',
    `ITEM_COUNT`      int                              COMMENT '주문아이템갯수'
);
