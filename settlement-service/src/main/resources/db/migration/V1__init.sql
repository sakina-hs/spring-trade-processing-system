CREATE TABLE `settlement_trade`
(
    `id`       int(20) NOT NULL AUTO_INCREMENT,
    `symbol`  varchar(255) DEFAULT NULL,
    `quantity` int(11)      DEFAULT NULL,
    `price`    double       DEFAULT NULL,
    PRIMARY KEY (`id`)
);