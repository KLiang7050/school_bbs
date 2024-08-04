/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.215.201
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : 192.168.215.201:3306
 Source Schema         : bbs_user

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 21/05/2024 16:04:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for joinus
-- ----------------------------
DROP TABLE IF EXISTS `joinus`;
CREATE TABLE `joinus`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NULL DEFAULT NULL,
  `school` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `file_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` date NULL DEFAULT NULL,
  `auditor` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description_res` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '(0：申请，1：审核，2：详谈，3：归档)',
  `back_flag` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '(1：正常，2：回退)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of joinus
-- ----------------------------

-- ----------------------------
-- Table structure for requisition
-- ----------------------------
DROP TABLE IF EXISTS `requisition`;
CREATE TABLE `requisition`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `auditor` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `auditor_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of requisition
-- ----------------------------
INSERT INTO `requisition` VALUES (4, NULL, '新增私信功能', NULL, '0', '2024-04-25 00:05:17', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `real_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `nick_name` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `username` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `tel` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1' COMMENT '性别（1：男；2：女）',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `introduce` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简介',
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '生日',
  `student_id` bigint NULL DEFAULT NULL COMMENT '学号',
  `school` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学校',
  `college` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学院',
  `major` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专业',
  `post_count` int NOT NULL DEFAULT 0 COMMENT '贴子数量',
  `favorites_count` int NOT NULL DEFAULT 0 COMMENT '获收藏数',
  `like_count` int NOT NULL DEFAULT 0 COMMENT '获赞数',
  `is_enable` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '账号状态（1：已禁用；0：未禁用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '逻辑删除标识（1：已删除；0：未删除）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `enrollment_time` datetime(0) NULL DEFAULT NULL COMMENT '入学时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `status_index`(`is_enable`) USING BTREE,
  INDEX `del_flag_index`(`del_flag`) USING BTREE,
  INDEX `username_index`(`username`) USING BTREE,
  INDEX `student_id_index`(`student_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (9, '', '小明', 'liuming', 'e10adc3949ba59abbe56e057f20f883e', '15878757050', '1', 'https://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-04-23/1b7cd405-0217-4fe6-a983-5f78fa4472c7', '热衷于软件开发，精通Python', '2001-07-06 00:00:00', NULL, '', '', '', 50, 4, 9, '0', '0', '2022-01-01 00:00:00', '2024-05-14 01:41:20', '2020-09-01 00:00:00');
INSERT INTO `user` VALUES (10, '', '小王', 'wangfang', 'e10adc3949ba59abbe56e057f20f883e', '13900000001', '2', 'https://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-04-24/45eb8449-7f84-4698-b8c5-608346d17de4', '对文学充满热情，专注于古典文学研究', '2002-05-05 00:00:00', NULL, '', '', '', 10, 0, 1, '0', '0', '2022-05-05 00:00:00', '2022-05-05 00:00:00', '2020-09-01 00:00:00');
INSERT INTO `user` VALUES (11, '', '小赵', 'zhaogang', 'e10adc3949ba59abbe56e057f20f883e', '13900000002', '1', 'https://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-05-07/e4619a2f-31a1-4411-a0ac-20df3865fc8c', '致力于生物医学研究，专业方向为分子生物学', '2003-06-06 00:00:00', NULL, '', '', '', 4, 0, 1, '0', '0', '2022-06-06 00:00:00', '2022-06-06 00:00:00', '2020-09-01 00:00:00');
INSERT INTO `user` VALUES (12, '', 'wyjj', '8063', 'e10adc3949ba59abbe56e057f20f883e', '13900000002', '1', 'https://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-05-07/ddeabb76-0b5c-47a5-ba32-91431eaeff96', '我是杰瑞米瓦德', '2003-06-06 00:00:00', NULL, '', '', '', 35, 3, 3, '0', '0', '2022-06-06 00:00:00', '2022-06-06 00:00:00', '2020-09-01 00:00:00');
INSERT INTO `user` VALUES (13, '', '卓卓', '4680', 'e10adc3949ba59abbe56e057f20f883e', '13900000002', '1', 'blob:http://www.schoolbbs-gxmzsfxy.com/0aaff8cd-98e9-4074-ad44-24f75d60ee1c', '开开心心', '2003-06-06 00:00:00', NULL, '', '', '', 12, 0, 0, '0', '0', '2022-06-06 00:00:00', '2024-05-14 01:04:32', '2020-09-01 00:00:00');
INSERT INTO `user` VALUES (14, '', '还以为故事没说完', 'zouxian', 'e10adc3949ba59abbe56e057f20f883e', '13900000002', '1', 'https://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-05-07/e4619a2f-31a1-4411-a0ac-20df3865fc8c', '', '2003-06-06 00:00:00', NULL, '', '', '', 18, 1, 1, '0', '0', '2022-06-06 00:00:00', '2022-06-06 00:00:00', '2020-09-01 00:00:00');
INSERT INTO `user` VALUES (15, '', '邓冰冰', 'denghongbin', 'e10adc3949ba59abbe56e057f20f883e', '13900000002', '1', 'https://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-05-07/e4619a2f-31a1-4411-a0ac-20df3865fc8c', '', '2003-06-06 00:00:00', NULL, '', '', '', 13, 0, 0, '0', '0', '2022-06-06 00:00:00', '2022-06-06 00:00:00', '2020-09-01 00:00:00');
INSERT INTO `user` VALUES (16, '陈科良', 'wangfang1', 'wangfang1', 'e10adc3949ba59abbe56e057f20f883e', '15878757050', '1', 'https://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-05-13/7bc393dd-99ea-4c69-af6e-fe7bebc55d0b', NULL, '2002-03-01 00:00:00', 203013020101, '广西民族师范学院', '301', '计算机科学与技术计科技术(中)201班', 5, 0, 0, '0', '0', '2024-05-13 22:34:56', '2024-05-13 22:46:41', '2020-10-10 00:00:00');

SET FOREIGN_KEY_CHECKS = 1;
