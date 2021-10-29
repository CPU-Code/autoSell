CREATE DATABASE IF NOT EXISTS `auto-status`
DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci
DEFAULT ENCRYPTION='N'

USE `auto-status`;

-- 无外键
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- 状态信息
-- ----------------------------
DROP TABLE IF EXISTS `tb_status_type`;
CREATE TABLE `tb_status_type` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `status_code` varchar(10) DEFAULT NULL COMMENT '状态代码',
  `descr` varchar(20) DEFAULT NULL COMMENT '状态描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `StatusType_StatusCode_uindex` (`status_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '状态信息';


-- ----------------------------
--售货机状态信息
-- ----------------------------
DROP TABLE IF EXISTS `tb_vm_status_info`;
CREATE TABLE `tb_vm_status_info` (
  `id` bigint NOT NULL AUTO_INCREMENT  COMMENT '自增id',
  `inner_code` varchar(15) DEFAULT NULL COMMENT '售货机编号',
  `status_code` varchar(10) DEFAULT NULL COMMENT '售货机状态码',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态',
  `utime` datetime DEFAULT NULL COMMENT '发生时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '售货机状态信息';



-- ----------------------------
-- 状态信息
-- ----------------------------
INSERT INTO `tb_status_type` VALUES ('1', '10001', '断网');
INSERT INTO `tb_status_type` VALUES ('2', '10002', '设备故障');
INSERT INTO `tb_status_type` VALUES ('3', '10003', '缺货');

