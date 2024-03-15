CREATE TABLE `seisekihyou` (
    `id` SERIAL NOT NULL,
    `japanese` VARCHAR(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '国語',
    `math` VARCHAR(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '数学',
    `science` VARCHAR(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '理科',
    `society` VARCHAR(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '社会',
    `english` VARCHAR(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '英語',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;
