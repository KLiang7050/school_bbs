/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.215.201
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : 192.168.215.201:3306
 Source Schema         : bbs_like

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 21/05/2024 16:04:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for favorites_user
-- ----------------------------
DROP TABLE IF EXISTS `favorites_user`;
CREATE TABLE `favorites_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NULL DEFAULT NULL,
  `post_id` bigint NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of favorites_user
-- ----------------------------
INSERT INTO `favorites_user` VALUES (8, 9, 54, '2024-04-23 23:23:32');
INSERT INTO `favorites_user` VALUES (10, 9, 53, '2024-04-24 16:39:42');
INSERT INTO `favorites_user` VALUES (11, 10, 60, '2024-04-24 21:57:51');
INSERT INTO `favorites_user` VALUES (12, 9, 60, '2024-04-24 22:20:07');
INSERT INTO `favorites_user` VALUES (15, 10, 65, '2024-05-07 16:28:29');
INSERT INTO `favorites_user` VALUES (16, 14, 70, '2024-05-07 17:37:57');
INSERT INTO `favorites_user` VALUES (17, 13, 90, '2024-05-13 17:14:25');
INSERT INTO `favorites_user` VALUES (18, 13, 70, '2024-05-13 17:15:46');
INSERT INTO `favorites_user` VALUES (19, 16, 70, '2024-05-13 23:17:13');
INSERT INTO `favorites_user` VALUES (20, 9, 65, '2024-05-14 11:29:41');

-- ----------------------------
-- Table structure for like_user
-- ----------------------------
DROP TABLE IF EXISTS `like_user`;
CREATE TABLE `like_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NULL DEFAULT NULL,
  `post_id` bigint NULL DEFAULT NULL,
  `main_id` bigint NULL DEFAULT NULL COMMENT '主贴id',
  `type` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '0：main；1：reply',
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of like_user
-- ----------------------------
INSERT INTO `like_user` VALUES (5, 8, 51, 51, '0', '2024-04-23 20:44:14');
INSERT INTO `like_user` VALUES (6, 9, 53, 53, '0', '2024-04-23 23:23:07');
INSERT INTO `like_user` VALUES (7, 9, 54, 54, '0', '2024-04-23 23:23:31');
INSERT INTO `like_user` VALUES (8, 9, 55, 55, '0', '2024-04-24 11:17:42');
INSERT INTO `like_user` VALUES (9, 9, 30, 54, '1', '2024-04-24 11:43:41');
INSERT INTO `like_user` VALUES (19, 9, 39, 56, '1', '2024-04-24 16:37:06');
INSERT INTO `like_user` VALUES (20, 9, 38, 56, '1', '2024-04-24 16:38:01');
INSERT INTO `like_user` VALUES (21, 10, 60, 60, '0', '2024-04-24 21:57:50');
INSERT INTO `like_user` VALUES (22, 10, 57, 60, '1', '2024-04-24 21:57:52');
INSERT INTO `like_user` VALUES (23, 10, 58, 58, '0', '2024-04-24 21:57:57');
INSERT INTO `like_user` VALUES (24, 9, 89, 59, '1', '2024-04-28 20:59:48');
INSERT INTO `like_user` VALUES (25, 9, 59, 59, '0', '2024-05-01 23:27:36');
INSERT INTO `like_user` VALUES (26, 9, 59, 59, '0', '2024-05-01 23:27:36');
INSERT INTO `like_user` VALUES (27, 9, 59, 59, '0', '2024-05-01 23:27:36');
INSERT INTO `like_user` VALUES (45, 9, 64, 64, '0', '2024-05-06 17:50:18');
INSERT INTO `like_user` VALUES (46, 10, 65, 65, '0', '2024-05-07 16:28:28');
INSERT INTO `like_user` VALUES (47, 11, 67, 67, '0', '2024-05-07 17:03:45');
INSERT INTO `like_user` VALUES (48, 11, 95, 67, '1', '2024-05-07 17:03:47');
INSERT INTO `like_user` VALUES (49, 12, 68, 68, '0', '2024-05-07 17:18:10');
INSERT INTO `like_user` VALUES (51, 14, 108, 70, '1', '2024-05-07 17:37:52');
INSERT INTO `like_user` VALUES (52, 14, 107, 70, '1', '2024-05-07 17:37:55');
INSERT INTO `like_user` VALUES (53, 14, 70, 70, '0', '2024-05-07 17:37:58');
INSERT INTO `like_user` VALUES (54, 14, 113, 70, '1', '2024-05-07 17:38:01');
INSERT INTO `like_user` VALUES (55, 9, 106, 70, '1', '2024-05-08 09:25:22');
INSERT INTO `like_user` VALUES (56, 9, 105, 70, '1', '2024-05-08 09:57:44');
INSERT INTO `like_user` VALUES (57, 13, 90, 90, '0', '2024-05-13 17:14:15');
INSERT INTO `like_user` VALUES (58, 13, 118, 70, '1', '2024-05-13 17:15:33');
INSERT INTO `like_user` VALUES (59, 13, 137, 70, '1', '2024-05-13 17:15:34');
INSERT INTO `like_user` VALUES (60, 13, 148, 70, '1', '2024-05-13 17:15:34');
INSERT INTO `like_user` VALUES (61, 13, 119, 70, '1', '2024-05-13 17:15:35');
INSERT INTO `like_user` VALUES (63, 16, 70, 70, '0', '2024-05-13 23:17:10');
INSERT INTO `like_user` VALUES (64, 16, 139, 70, '1', '2024-05-13 23:17:18');
INSERT INTO `like_user` VALUES (65, 9, 65, 65, '0', '2024-05-14 11:29:41');
INSERT INTO `like_user` VALUES (66, 16, 60, 60, '0', '2024-05-14 12:42:13');
INSERT INTO `like_user` VALUES (68, 9, 70, 70, '0', '2024-05-14 14:42:51');

SET FOREIGN_KEY_CHECKS = 1;
