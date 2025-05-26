DROP TABLE IF EXISTS `db_sec`.`tb_veh_sk`;
CREATE TABLE `db_sec`.`tb_veh_sk`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `vin`         VARCHAR(20)  NOT NULL COMMENT '车架号',
    `type`        VARCHAR(100) NOT NULL COMMENT '密钥类型',
    `value`       VARCHAR(255) NOT NULL COMMENT '密钥值',
    `description` VARCHAR(255)          DEFAULT NULL COMMENT '备注',
    `create_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   VARCHAR(64)           DEFAULT NULL COMMENT '创建者',
    `modify_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `modify_by`   VARCHAR(64)           DEFAULT NULL COMMENT '修改者',
    `row_version` INT                   DEFAULT 1 COMMENT '记录版本',
    `row_valid`   TINYINT               DEFAULT 1 COMMENT '记录是否有效',
    PRIMARY KEY (`id`),
    INDEX `idx_order_num` (`vin`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='车辆密钥表';

DROP TABLE IF EXISTS `db_sec`.`tb_cert_operation_log`;
CREATE TABLE `db_sec`.`tb_cert_operation_log`
(
    `id`               BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `device_type`      VARCHAR(50)  NOT NULL COMMENT '设备类型',
    `device_sn`        VARCHAR(100) NOT NULL COMMENT '设备序列号',
    `device_key`       VARCHAR(100)          DEFAULT NULL COMMENT '设备KEY',
    `device_operation` SMALLINT     NOT NULL COMMENT '设备请求类型：1-证书申请并下载',
    `sn`               VARCHAR(100)          DEFAULT NULL COMMENT '证书序列号',
    `template`         VARCHAR(100) NOT NULL COMMENT '证书模板',
    `subject`          VARCHAR(255) NOT NULL COMMENT '证书主题',
    `csr`              TEXT                  DEFAULT NULL COMMENT '证书请求',
    `description`      VARCHAR(255)          DEFAULT NULL COMMENT '备注',
    `create_time`      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`        VARCHAR(64)           DEFAULT NULL COMMENT '创建者',
    `modify_time`      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `modify_by`        VARCHAR(64)           DEFAULT NULL COMMENT '修改者',
    `row_version`      INT                   DEFAULT 1 COMMENT '记录版本',
    `row_valid`        TINYINT               DEFAULT 1 COMMENT '记录是否有效',
    PRIMARY KEY (`id`),
    INDEX `idx_device_sn` (`device_sn`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='证书操作日志表';