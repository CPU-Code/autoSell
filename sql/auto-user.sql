
CREATE DATABASE IF NOT EXISTS `auto-user`
DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci
DEFAULT ENCRYPTION='N'

USE `auto-user`;


-- 无外键

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- 合作商表
-- ----------------------------
DROP TABLE IF EXISTS `tb_partner`;
CREATE TABLE `tb_partner` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(50) DEFAULT NULL COMMENT '合作商名',
  `password` varchar(200) DEFAULT NULL COMMENT '密码',
  `account` varchar(32) DEFAULT NULL COMMENT '账号',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮件',
  `province` varchar(10) DEFAULT NULL COMMENT '所在省',
  `city` varchar(20) DEFAULT NULL COMMENT '所在市',
  `county` varchar(20) DEFAULT NULL COMMENT '区县',
  `addr` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `contact` varchar(10) DEFAULT NULL COMMENT '联系人',
  `ratio` int DEFAULT '100' COMMENT '分成比例',
  `status` tinyint DEFAULT '1' COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tb_partner_mobile_uindex` (`mobile`),
  UNIQUE KEY `tb_partner_name_uindex` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='合作商表';


-- ----------------------------
-- 用户角色表
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `role_id` int NOT NULL AUTO_INCREMENT COMMENT '自增角色主键',
  `role_code` varchar(10) DEFAULT NULL COMMENT '角色编号',
  `role_name` varchar(10) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户角色表';



-- ----------------------------
-- 用户信息表
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `role_id` int DEFAULT NULL COMMENT '角色Id',
  `user_name` varchar(50) NOT NULL COMMENT '用户名称',
  `login_name` varchar(50) DEFAULT NULL COMMENT '登录名',
  `password` varchar(60) DEFAULT NULL COMMENT '密码',
  `secret` varchar(32) DEFAULT NULL COMMENT '私钥',
  `role_code` varchar(10) DEFAULT NULL COMMENT '角色编号',
  `mobile` varchar(15) DEFAULT NULL COMMENT '手机号',
  `region_id` bigint DEFAULT NULL COMMENT '所属区域Id',
  `region_name` varchar(50) DEFAULT NULL COMMENT '区域名称',
  `status` tinyint DEFAULT '1' COMMENT '是否启用',
  `image` varchar(500) DEFAULT NULL COMMENT '照片url',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tb_user_Id_uindex` (`id`),
  UNIQUE KEY `tb_user_mobile_uindex` (`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户信息表';



-- ----------------------------
-- 合作商表
-- ----------------------------
INSERT INTO `tb_partner` VALUES ('1', '金燕龙合作商', '$2a$10$xykah91CBBmrNTljPMZakOVjoiKltyzxUbsJHU1QuDPptma4NCcG6', '13800000000', '13800000000', null, null, null, null, null, null, '张三', '15', '1', '2020-09-24 15:41:25', '2020-09-24 15:59:11');
INSERT INTO `tb_partner` VALUES ('4', '天华物业', '$2a$10$rORK8GPPgkT7tpF0cOea/ulddc0xDHvt7AxF4un585kyRSS7PMGAS', '18940843614', '18940843614', null, null, null, null, null, null, '刘俊杰', '5', '1', '2020-12-18 09:59:29', '2020-12-18 10:12:51');
INSERT INTO `tb_partner` VALUES ('6', '金燕龙物业', '$2a$10$p/TmfhPhPs8uzUzzpOIq/OvpQBNTP7rid1YDlyKJ0in4S/fdOZGda', '18500000000', '18500000000', null, null, null, null, null, null, '金燕龙合作商', '20', '1', '2020-12-23 15:21:08', '2020-12-23 15:21:08');
INSERT INTO `tb_partner` VALUES ('10', '合作商开发-勿删', '$2a$10$rRrU8QaiMCRkO7YDffGvcOhTjBPa2gdI5nMwzJO3zrpgjKsJd58jK', '13436868832', '13436868832', '13436868832', null, null, null, null, null, '黄旭', '100', '1', '2020-12-28 15:31:37', '2020-12-28 15:31:37');
INSERT INTO `tb_partner` VALUES ('11', '北京合作商', '$2a$10$XDs7VYpf/y1yz9klFQjSIejSXGDFGhR0oiU56jpzS0EsRA5oa5g2K', '13261698937', '13261698937', null, null, null, null, null, null, '北京合作商', '99', '1', '2021-01-11 09:16:59', '2021-01-11 09:16:59');
INSERT INTO `tb_partner` VALUES ('12', '1', '$2a$10$1O6margNLhbqYMBHBUqI2eW2hVoUahfyAlurFSVd0Vz4.dwhR66fa', '1', '1', null, null, null, null, null, null, '1', '1', '1', '2021-01-11 10:06:46', '2021-01-11 10:06:46');
INSERT INTO `tb_partner` VALUES ('16', '2', '$2a$10$V7r5mgUvSOA5/WuhvQaCDOc2VXrh9t1e0BJ21.FZF4rX9B3gJ/YmW', '2', '2', null, null, null, null, null, null, '2', '2', '1', '2021-01-11 10:07:15', '2021-01-11 10:07:15');
INSERT INTO `tb_partner` VALUES ('17', '3', '$2a$10$xYMlbtRnFRpMtl1P9a.6Eek12zeQWAFiTjXaXZ6uHZHz5fohXW4c2', '33', '3', null, null, null, null, null, null, '3', '3', '1', '2021-01-11 10:08:12', '2021-01-11 10:08:12');
INSERT INTO `tb_partner` VALUES ('19', '佳佳', '$2a$10$8KD9MagBQ8JV.LB7RIJ0.uEjTcxJOdvlkJLGZgTa32FfR7KjOtz92', '5', '18500000002', null, null, null, null, null, null, '佳佳集团', '90', '1', '2021-01-11 10:08:29', '2021-01-11 10:08:29');
INSERT INTO `tb_partner` VALUES ('20', '6', '$2a$10$.nt1K8gtYz9h/1ZflAf/e.uv71w9D/rw8hjQ4Ea6zovAWpCbTw21m', '6', '6', null, null, null, null, null, null, '6', '6', '1', '2021-01-11 10:08:40', '2021-01-11 10:08:40');
INSERT INTO `tb_partner` VALUES ('21', '7', '$2a$10$MxeKfDCXZNH2K94lNu1TVu/MSMrIlra8QB9InMCkG1oNM3.R8.ase', '7', '7', null, null, null, null, null, null, '7', '7', '1', '2021-01-11 10:08:54', '2021-01-11 10:08:54');
INSERT INTO `tb_partner` VALUES ('24', 'test', '$2a$10$jLLyofMGrU8Q/aMOvvSSrulkQGtqjwJyIVoNx6H59ZNTdazOcef3m', '13436868833', '13436868833', null, null, null, null, null, null, 'test', '10', '1', '2021-01-11 16:00:31', '2021-01-11 16:00:31');
INSERT INTO `tb_partner` VALUES ('25', 'likede', '$2a$10$ckdzsIiEheP/Cty87kiqN.YWFWCS3RPbjaPxZiwJnxbPm88UKchIi', '18940843666', '18940843666', null, null, null, null, null, null, 'likede', '1', '1', '2021-01-11 16:03:35', '2021-01-11 16:03:35');


-- ----------------------------
-- 用户角色表
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', '1001', '管理员');
INSERT INTO `tb_role` VALUES ('2', '1002', '运营员');
INSERT INTO `tb_role` VALUES ('3', '1003', '维修员');


-- ----------------------------
-- 用户信息表
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', '1', 'admin', 'admin', '$2a$10$qQ1NU2aiSMPudkl9m.kvCumm0Qlyk3byrqeK44uyfPpPlDONmCmT.', '600162879276', '1001', '13800000000', '1', '第一个区域', '1', null);
INSERT INTO `tb_user` VALUES ('4', '2', '运营魏帅鸣', null, null, '1600162879276lkd', '1002', '13651077781', '1348506559038099457', '金燕龙办公楼', '1', 'http://lkd2-java.itheima.net/image/avatar.png');
INSERT INTO `tb_user` VALUES ('6', '3', '运维刘俊杰', null, null, '1601027222449lkd', '1003', '18940843614', '1', '霍营街道', '1', 'http://lkd2-java.itheima.net/image/avatar.png');
INSERT INTO `tb_user` VALUES ('7', '3', '运维金瀚漠', null, null, '1601027222449lkd', '1003', '13600000003', '1', '霍营街道', '1', 'http://lkd2-java.itheima.net/image/avatar.png');
INSERT INTO `tb_user` VALUES ('8', '2', '运营邱元明', null, null, '1601027222449lkd', '1002', '18940843615', '1', '霍营街道', '1', 'http://lkd2-java.itheima.net/image/avatar.png');
INSERT INTO `tb_user` VALUES ('10', '2', '运营石凯安', null, null, '1608271138375lkd', '1002', '13700000005', '1', '霍营街道', '1', 'http://lkd2-java.itheima.net/image/avatar.png');
INSERT INTO `tb_user` VALUES ('11', '3', '运维汪阳羽', null, null, '1608271182102lkd', '1003', '13700000006', '1', '霍营街道', '1', 'http://lkd2-java.itheima.net/image/avatar.png');
INSERT INTO `tb_user` VALUES ('12', '2', '运营余俊迈', null, null, '1608348027839lkd', '1002', '13700000007', '1', '霍营街道', '0', 'http://lkd2-java.itheima.net/image/avatar.png');
INSERT INTO `tb_user` VALUES ('13', '2', '运营沈琪睿', null, null, '1608348311409lkd', '1002', '13700000008', '1305439798119075841', '城北街道', '1', 'http://lkd2-java.itheima.net/image/avatar.png');
INSERT INTO `tb_user` VALUES ('14', '2', '运营田星光', null, null, '1608348340283lkd', '1002', '13700000009', '1305439798119075841', '城北街道', '1', 'http://lkd2-java.itheima.net/image/avatar.png');
INSERT INTO `tb_user` VALUES ('15', '2', '运营白建茗', null, null, '1608348361625lkd', '1002', '13700000010', '1305439798119075841', '城北街道', '1', 'http://lkd2-java.itheima.net/image/avatar.png');
INSERT INTO `tb_user` VALUES ('16', '2', '运营韩乐人', null, null, '1608348378043lkd', '1002', '13700000011', '1305439798119075841', '城北街道', '1', 'http://lkd2-java.itheima.net/image/avatar.png');
INSERT INTO `tb_user` VALUES ('17', '3', '运维谭俊贤', null, null, '1608348405049lkd', '1003', '19110314968', '1305439798119075841', '城北街道', '1', 'http://lkd2-java.itheima.net/image/avatar.png');
INSERT INTO `tb_user` VALUES ('18', '3', '运维夏俊达', null, null, '1608348420299lkd', '1003', '13700000013', '1305439798119075841', '城北街道', '1', 'http://lkd2-java.itheima.net/image/avatar.png');
INSERT INTO `tb_user` VALUES ('19', '3', '运维漕宏爽', null, null, '1608348438147lkd', '1003', '13700000014', '1305439798119075841', '城北街道', '1', 'http://lkd2-java.itheima.net/image/avatar.png');
INSERT INTO `tb_user` VALUES ('20', '2', '运营蔡弘壮', null, null, '1608348463538lkd', '1002', '13700000015', '1339752425761804289', '西直门街道', '1', 'http://lkd2-java.itheima.net/image/avatar.png');
INSERT INTO `tb_user` VALUES ('21', '2', '运营黎俊德', null, null, '1608348484524lkd', '1002', '13700000016', '1339752425761804289', '西直门街道', '1', 'http://lkd2-java.itheima.net/image/avatar.png');
INSERT INTO `tb_user` VALUES ('22', '2', '运营朱翔飞', null, null, '1608348503003lkd', '1002', '13700000017', '1339752425761804289', '西直门街道', '1', 'http://lkd2-java.itheima.net/image/avatar.png');
INSERT INTO `tb_user` VALUES ('23', '2', '运营董浩浩', null, null, '1608348519438lkd', '1002', '13700000018', '1339752425761804289', '西直门街道', '1', 'http://lkd2-java.itheima.net/image/avatar.png');
INSERT INTO `tb_user` VALUES ('24', '3', '运维秦勇毅', null, null, '1608348535426lkd', '1003', '13700000019', '1339752425761804289', '西直门街道', '1', 'http://lkd2-java.itheima.net/image/avatar.png');
INSERT INTO `tb_user` VALUES ('25', '3', '运维郝翰翮', null, null, '1608348549670lkd', '1003', '13700000020', '1339752425761804289', '西直门街道', '1', 'http://lkd2-java.itheima.net/image/avatar.png');
INSERT INTO `tb_user` VALUES ('26', '3', '运维史俊楠', null, null, '1608348568899lkd', '1003', '13700000021', '1348506559038099457', '金燕龙办公楼', '1', 'http://lkd2-java.itheima.net/image/avatar.png');
INSERT INTO `tb_user` VALUES ('36', '3', '运维郝燕', null, null, '1610352632695lkd', '1003', '13261698937', '1348506559038099457', '金燕龙办公楼', '1', 'http://lkd2-java.itheima.net/image/头像.jpg');
INSERT INTO `tb_user` VALUES ('40', '2', '皇叔', null, null, '1610939169242lkd', '1002', '17701265258', '1', '霍营街道', '1', 'http://lkd2-java.itheima.net/image/admin.png');
INSERT INTO `tb_user` VALUES ('43', '3', '运维朵朵', null, null, '1611221871919lkd', '1003', '13700000000', '1352155034036473857', '金燕龙写字楼', '1', 'http://lkd2-java.itheima.net/image/头像.jpg');
INSERT INTO `tb_user` VALUES ('45', '2', '运营刘俊杰', null, null, '1611716233380lkd', '1002', '19110314967', '1', '霍营街道', '1', 'http://lkd2-java.itheima.net/image/avatar.png');
INSERT INTO `tb_user` VALUES ('49', '2', '运营多利', null, null, '1612423298220lkd', '1002', '18500000009', '1357163894690287618', '北京8街道', '0', 'http://lkd2-java.itheima.net/image/头像.jpg');
