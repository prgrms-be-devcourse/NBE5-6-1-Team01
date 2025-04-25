insert into items(`ITEM_ID`, `ITEM_TYPE`, `ITEM_NAME`, `ITEM_PRICE`) values
(1, '커피콩', 'Columbia Nariñó', 5000);
insert into items(`ITEM_ID`, `ITEM_TYPE`, `ITEM_NAME`, `ITEM_PRICE`) values
(2, '커피콩', 'Brazil Serra Do Caparaó', 6000);
insert into items(`ITEM_ID`, `ITEM_TYPE`, `ITEM_NAME`, `ITEM_PRICE`) values
(3, '커피콩', 'Columbia Nariñó', 7000);
insert into items(`ITEM_ID`, `ITEM_TYPE`, `ITEM_NAME`, `ITEM_PRICE`) values
(4, '커피콩', 'Ethiopia Yirgacheffe', 8000);

insert into ORDERS(`ORDER_ID`, `EMAIL`, `ADDRESS`, `POSTCODE`, `TOTAL_PRICE`, `ORDER_STATUS`) values
    (1, 'test@test.com', '서울특별시 은평구', '03456', 120000, 'ORDER');
insert into ORDERS(`ORDER_ID`, `EMAIL`, `ADDRESS`, `POSTCODE`, `TOTAL_PRICE`, `ORDER_STATUS`) values
    (2, 'test@test.com', '서울특별시 은평구', '03456', 10000, 'CANCEL');
insert into ORDERS(`ORDER_ID`, `EMAIL`, `ADDRESS`, `POSTCODE`, `TOTAL_PRICE`, `ORDER_STATUS`) values
    (3, 'test@test.com', '서울특별시 서대문구', '03421', 50000, 'DELIVER');
insert into ORDERS(`ORDER_ID`, `EMAIL`, `ADDRESS`, `POSTCODE`, `TOTAL_PRICE`, `ORDER_STATUS`) values
    (4, 'test1@test.com', '서울특별시 중구', '03475', 70000, 'ORDER');
insert into ORDERS(`ORDER_ID`, `EMAIL`, `ADDRESS`, `POSTCODE`, `TOTAL_PRICE`, `ORDER_STATUS`) values
    (5, 'test2@test.com', '서울특별시 종로구', '03496', 21000, 'ORDER');
insert into ORDERS(`ORDER_ID`, `EMAIL`, `ADDRESS`, `POSTCODE`, `TOTAL_PRICE`, `ORDER_STATUS`) values
    (6, 'test3@test.com', '서울특별시 금천구', '03418', 670000, 'ORDER');