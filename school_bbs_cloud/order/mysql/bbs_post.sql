/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.215.201
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : 192.168.215.201:3306
 Source Schema         : bbs_post

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 21/05/2024 16:04:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for detection_log
-- ----------------------------
DROP TABLE IF EXISTS `detection_log`;
CREATE TABLE `detection_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content_id` bigint NULL DEFAULT NULL,
  `words` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '敏感词，逗号分隔',
  `is_handle` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '1：合规，2：不合规，3：疑似，4：审核失败，5：已审核不合规',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核信息',
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `reply_flag` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '1：回复；0：主贴',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 109 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of detection_log
-- ----------------------------
INSERT INTO `detection_log` VALUES (1, 178, '', '1', '合规', '2024-05-06 16:39:10', '0');
INSERT INTO `detection_log` VALUES (2, 179, '', '1', '合规', '2024-05-06 16:39:10', '1');
INSERT INTO `detection_log` VALUES (3, 180, '', '1', '合规', '2024-05-07 16:20:02', '0');
INSERT INTO `detection_log` VALUES (4, 181, '', '1', '合规', '2024-05-07 16:29:00', '1');
INSERT INTO `detection_log` VALUES (6, 183, '', '1', '合规', '2024-05-07 16:58:01', '0');
INSERT INTO `detection_log` VALUES (7, 184, '', '1', '合规', '2024-05-07 16:58:01', '0');
INSERT INTO `detection_log` VALUES (8, 185, '', '1', '合规', '2024-05-07 16:58:01', '1');
INSERT INTO `detection_log` VALUES (9, 186, '', '1', '合规', '2024-05-07 17:04:00', '1');
INSERT INTO `detection_log` VALUES (10, 187, '', '1', '合规', '2024-05-07 17:05:00', '1');
INSERT INTO `detection_log` VALUES (11, 188, '', '1', '合规', '2024-05-07 17:06:01', '0');
INSERT INTO `detection_log` VALUES (12, 189, '', '1', '合规', '2024-05-07 17:07:00', '1');
INSERT INTO `detection_log` VALUES (13, 190, '', '1', '合规', '2024-05-07 17:08:02', '1');
INSERT INTO `detection_log` VALUES (14, 191, '', '1', '合规', '2024-05-07 17:08:02', '1');
INSERT INTO `detection_log` VALUES (15, 192, '', '1', '合规', '2024-05-07 17:08:02', '1');
INSERT INTO `detection_log` VALUES (16, 193, '', '1', '合规', '2024-05-07 17:08:02', '1');
INSERT INTO `detection_log` VALUES (17, 194, '', '1', '合规', '2024-05-07 17:19:00', '1');
INSERT INTO `detection_log` VALUES (18, 195, '', '1', '合规', '2024-05-07 17:21:01', '0');
INSERT INTO `detection_log` VALUES (19, 196, '', '1', '合规', '2024-05-07 17:21:01', '1');
INSERT INTO `detection_log` VALUES (20, 197, '', '1', '合规', '2024-05-07 17:21:01', '1');
INSERT INTO `detection_log` VALUES (21, 198, '', '1', '合规', '2024-05-07 17:27:01', '0');
INSERT INTO `detection_log` VALUES (22, 198, '', '1', '合规', '2024-05-07 17:27:01', '0');
INSERT INTO `detection_log` VALUES (23, 199, '', '1', '合规', '2024-05-07 17:27:01', '1');
INSERT INTO `detection_log` VALUES (24, 200, '', '1', '合规', '2024-05-07 17:28:04', '1');
INSERT INTO `detection_log` VALUES (25, 201, '', '1', '合规', '2024-05-07 17:28:04', '1');
INSERT INTO `detection_log` VALUES (26, 202, '', '1', '合规', '2024-05-07 17:28:04', '1');
INSERT INTO `detection_log` VALUES (27, 203, '', '1', '合规', '2024-05-07 17:28:04', '1');
INSERT INTO `detection_log` VALUES (28, 204, '', '1', '合规', '2024-05-07 17:28:04', '1');
INSERT INTO `detection_log` VALUES (29, 205, '', '1', '合规', '2024-05-07 17:28:04', '1');
INSERT INTO `detection_log` VALUES (30, 206, '', '1', '合规', '2024-05-07 17:28:04', '1');
INSERT INTO `detection_log` VALUES (31, 207, '', '1', '合规', '2024-05-07 17:29:04', '1');
INSERT INTO `detection_log` VALUES (32, 208, '', '1', '合规', '2024-05-07 17:29:04', '1');
INSERT INTO `detection_log` VALUES (33, 209, '', '1', '合规', '2024-05-07 17:29:04', '1');
INSERT INTO `detection_log` VALUES (34, 210, '', '1', '合规', '2024-05-07 17:29:04', '1');
INSERT INTO `detection_log` VALUES (35, 211, '', '1', '合规', '2024-05-07 17:29:04', '1');
INSERT INTO `detection_log` VALUES (36, 212, '', '1', '合规', '2024-05-07 17:29:04', '1');
INSERT INTO `detection_log` VALUES (37, 213, '', '1', '合规', '2024-05-07 17:29:04', '1');
INSERT INTO `detection_log` VALUES (38, 214, '', '1', '合规', '2024-05-07 17:30:02', '1');
INSERT INTO `detection_log` VALUES (39, 215, '全文', '1', '不合规', '2024-05-14 01:02:34', '1');
INSERT INTO `detection_log` VALUES (40, 216, '', '1', '合规', '2024-05-07 17:30:02', '1');
INSERT INTO `detection_log` VALUES (41, 217, '', '1', '合规', '2024-05-07 17:30:02', '1');
INSERT INTO `detection_log` VALUES (42, 218, '', '1', '合规', '2024-05-07 17:31:02', '1');
INSERT INTO `detection_log` VALUES (43, 219, '', '1', '合规', '2024-05-07 17:31:02', '1');
INSERT INTO `detection_log` VALUES (44, 220, '', '1', '合规', '2024-05-07 17:32:02', '1');
INSERT INTO `detection_log` VALUES (45, 221, '', '1', '合规', '2024-05-07 17:32:02', '1');
INSERT INTO `detection_log` VALUES (46, 222, '', '1', '合规', '2024-05-07 17:32:02', '1');
INSERT INTO `detection_log` VALUES (47, 223, '', '1', '合规', '2024-05-07 17:33:01', '1');
INSERT INTO `detection_log` VALUES (48, 224, '', '1', '合规', '2024-05-07 17:33:01', '1');
INSERT INTO `detection_log` VALUES (49, 225, '', '1', '合规', '2024-05-07 17:36:01', '1');
INSERT INTO `detection_log` VALUES (50, 226, '', '1', '合规', '2024-05-07 17:36:01', '1');
INSERT INTO `detection_log` VALUES (51, 227, '', '1', '合规', '2024-05-07 17:36:01', '1');
INSERT INTO `detection_log` VALUES (52, 228, '', '1', '合规', '2024-05-07 17:37:01', '1');
INSERT INTO `detection_log` VALUES (53, 229, '', '1', '合规', '2024-05-07 17:37:01', '1');
INSERT INTO `detection_log` VALUES (54, 230, '', '1', '合规', '2024-05-07 17:37:01', '1');
INSERT INTO `detection_log` VALUES (55, 231, '', '1', '合规', '2024-05-07 17:38:01', '1');
INSERT INTO `detection_log` VALUES (56, 232, '', '1', '合规', '2024-05-07 17:38:01', '1');
INSERT INTO `detection_log` VALUES (57, 233, '', '1', '合规', '2024-05-08 09:26:02', '1');
INSERT INTO `detection_log` VALUES (58, 234, '', '1', '合规', '2024-05-08 09:32:00', '1');
INSERT INTO `detection_log` VALUES (59, 235, '', '1', '合规', '2024-05-08 09:33:00', '1');
INSERT INTO `detection_log` VALUES (60, 236, '', '1', '合规', '2024-05-08 09:35:00', '1');
INSERT INTO `detection_log` VALUES (61, 237, '', '1', '合规', '2024-05-08 09:52:00', '1');
INSERT INTO `detection_log` VALUES (62, 238, '', '1', '合规', '2024-05-08 09:53:00', '1');
INSERT INTO `detection_log` VALUES (63, 239, '', '1', '合规', '2024-05-08 09:59:00', '1');
INSERT INTO `detection_log` VALUES (64, 240, '', '1', '合规', '2024-05-08 10:04:16', '0');
INSERT INTO `detection_log` VALUES (65, 241, '', '1', '合规', '2024-05-08 10:05:01', '0');
INSERT INTO `detection_log` VALUES (66, 242, '', '1', '合规', '2024-05-08 10:05:01', '0');
INSERT INTO `detection_log` VALUES (67, 243, '', '1', '合规', '2024-05-08 10:06:01', '0');
INSERT INTO `detection_log` VALUES (68, 244, '', '1', '合规', '2024-05-08 10:06:01', '0');
INSERT INTO `detection_log` VALUES (69, 245, '', '1', '合规', '2024-05-08 10:07:01', '0');
INSERT INTO `detection_log` VALUES (70, 246, '', '1', '合规', '2024-05-08 10:07:01', '0');
INSERT INTO `detection_log` VALUES (71, 247, '', '1', '合规', '2024-05-08 10:08:17', '0');
INSERT INTO `detection_log` VALUES (72, 248, '', '1', '合规', '2024-05-08 10:11:01', '1');
INSERT INTO `detection_log` VALUES (73, 249, '', '1', '合规', '2024-05-08 21:15:01', '0');
INSERT INTO `detection_log` VALUES (74, 250, '', '1', '合规', '2024-05-08 21:15:01', '0');
INSERT INTO `detection_log` VALUES (75, 251, '', '1', '合规', '2024-05-08 21:16:01', '0');
INSERT INTO `detection_log` VALUES (76, 252, '', '1', '合规', '2024-05-08 21:16:01', '0');
INSERT INTO `detection_log` VALUES (77, 253, '', '1', '合规', '2024-05-08 21:19:00', '0');
INSERT INTO `detection_log` VALUES (78, 254, '', '1', '合规', '2024-05-08 21:20:01', '0');
INSERT INTO `detection_log` VALUES (79, 255, '', '1', '合规', '2024-05-08 21:20:01', '0');
INSERT INTO `detection_log` VALUES (80, 256, '', '1', '合规', '2024-05-08 21:22:00', '0');
INSERT INTO `detection_log` VALUES (81, 257, '', '1', '合规', '2024-05-08 21:43:00', '0');
INSERT INTO `detection_log` VALUES (82, 258, '', '1', '合规', '2024-05-08 21:44:00', '0');
INSERT INTO `detection_log` VALUES (83, 259, '', '1', '合规', '2024-05-08 21:45:01', '0');
INSERT INTO `detection_log` VALUES (84, 260, '', '1', '合规', '2024-05-08 21:45:01', '0');
INSERT INTO `detection_log` VALUES (85, 261, '', '1', '合规', '2024-05-13 17:15:01', '1');
INSERT INTO `detection_log` VALUES (86, 262, '', '1', '合规', '2024-05-13 17:16:00', '1');
INSERT INTO `detection_log` VALUES (87, 263, '', '1', '合规', '2024-05-13 23:04:00', '1');
INSERT INTO `detection_log` VALUES (88, 264, '', '1', '合规', '2024-05-13 23:13:02', '1');
INSERT INTO `detection_log` VALUES (89, 265, '', '1', '合规', '2024-05-13 23:13:02', '1');
INSERT INTO `detection_log` VALUES (90, 266, '', '1', '合规', '2024-05-13 23:13:02', '1');
INSERT INTO `detection_log` VALUES (91, 267, '', '1', '合规', '2024-05-13 23:13:02', '1');
INSERT INTO `detection_log` VALUES (92, 268, '', '1', '合规', '2024-05-13 23:19:00', '0');
INSERT INTO `detection_log` VALUES (93, 268, '', '1', '合规', '2024-05-13 23:49:01', '0');
INSERT INTO `detection_log` VALUES (94, 269, '', '1', '合规', '2024-05-13 23:49:01', '0');
INSERT INTO `detection_log` VALUES (95, 270, '', '1', '合规', '2024-05-14 00:37:00', '0');
INSERT INTO `detection_log` VALUES (96, 271, '', '1', '合规', '2024-05-14 00:38:00', '0');
INSERT INTO `detection_log` VALUES (97, 271, '', '1', '合规', '2024-05-14 00:49:00', '0');
INSERT INTO `detection_log` VALUES (98, 272, '', '1', '合规', '2024-05-14 01:04:00', '1');
INSERT INTO `detection_log` VALUES (99, 180, '', '1', '合规', '2024-05-14 11:42:00', '0');
INSERT INTO `detection_log` VALUES (100, 180, '', '1', '合规', '2024-05-14 11:47:00', '0');
INSERT INTO `detection_log` VALUES (101, 273, '', '1', '合规', '2024-05-14 13:49:00', '0');
INSERT INTO `detection_log` VALUES (102, 274, '', '1', '合规', '2024-05-14 13:50:00', '1');
INSERT INTO `detection_log` VALUES (103, 275, '', '1', '合规', '2024-05-14 14:02:00', '0');
INSERT INTO `detection_log` VALUES (104, 276, '', '1', '合规', '2024-05-14 14:03:00', '0');
INSERT INTO `detection_log` VALUES (105, 277, '', '1', '合规', '2024-05-14 14:43:00', '1');
INSERT INTO `detection_log` VALUES (106, 278, '', '1', '合规', '2024-05-14 14:44:01', '1');
INSERT INTO `detection_log` VALUES (107, 279, '', '1', '合规', '2024-05-14 14:45:00', '0');
INSERT INTO `detection_log` VALUES (108, 280, NULL, '1', NULL, '2024-05-14 14:47:29', '0');

-- ----------------------------
-- Table structure for dict_post_type
-- ----------------------------
DROP TABLE IF EXISTS `dict_post_type`;
CREATE TABLE `dict_post_type`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of dict_post_type
-- ----------------------------
INSERT INTO `dict_post_type` VALUES (1, '最近实事');
INSERT INTO `dict_post_type` VALUES (2, '分享');
INSERT INTO `dict_post_type` VALUES (3, '吐槽');
INSERT INTO `dict_post_type` VALUES (4, '趣事');
INSERT INTO `dict_post_type` VALUES (5, '表白');
INSERT INTO `dict_post_type` VALUES (6, '二手交易');
INSERT INTO `dict_post_type` VALUES (7, '失物招领');
INSERT INTO `dict_post_type` VALUES (8, '寻物启事');
INSERT INTO `dict_post_type` VALUES (9, '求助');
INSERT INTO `dict_post_type` VALUES (99, '其他');

-- ----------------------------
-- Table structure for post_content
-- ----------------------------
DROP TABLE IF EXISTS `post_content`;
CREATE TABLE `post_content`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  `media_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '媒体url集合，用【;;】分割url',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 281 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of post_content
-- ----------------------------
INSERT INTO `post_content` VALUES (135, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">有没有23年中职升本的通信工程的学长学姐吗，专业考试到底怎么考，有没有习题，真的很需要</span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon9.png\" alt=\"\" data-href=\"\" style=\"\"/><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon9.png\" alt=\"\" data-href=\"\" style=\"\"/></p>', '');
INSERT INTO `post_content` VALUES (136, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">小孩一年级需要辅导功课</span></p>', '');
INSERT INTO `post_content` VALUES (137, '<h1 style=\"text-align: left;\"><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">想问问大家体育课如果挂科了，补考也不过，重修可以选择别的体育课吗，还是只能重修原来这门课？<br>（健美操太难了真的学不会）</span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon9.png\" alt=\"\" data-href=\"\" style=\"\"><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon9.png\" alt=\"\" data-href=\"\" style=\"\"></h1>', '');
INSERT INTO `post_content` VALUES (138, '<p style=\"text-align: center;\"><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">诚邀插画师参与</span></p><p style=\"text-align: center;\"><img src=\"http://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-04-24/e45d8d53-a307-416a-9de8-d5444717af8bNaN\" alt=\"2024-04-24/e45d8d53-a307-416a-9de8-d5444717af8bNaN\" data-href=\"\" style=\"\"></p><p style=\"text-align: center;\"><img src=\"http://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-04-24/7d329f45-2c5d-41d5-a067-17f1538dec65NaN\" alt=\"2024-04-24/7d329f45-2c5d-41d5-a067-17f1538dec65NaN\" data-href=\"\" style=\"\"></p>', 'http://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-04-24/e45d8d53-a307-416a-9de8-d5444717af8bNaN;;http://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-04-24/7d329f45-2c5d-41d5-a067-17f1538dec65NaN');
INSERT INTO `post_content` VALUES (139, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">有无报名渠道</span></p>', '');
INSERT INTO `post_content` VALUES (140, '<p><img src=\"http://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-04-24/aec65e2f-5fcb-40cf-87c7-3a41d8987a36NaN\" alt=\"2024-04-24/aec65e2f-5fcb-40cf-87c7-3a41d8987a36NaN\" data-href=\"\" style=\"\"/>测试</p>', 'http://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-04-24/aec65e2f-5fcb-40cf-87c7-3a41d8987a36NaN');
INSERT INTO `post_content` VALUES (141, '<p>我我我</p>', '');
INSERT INTO `post_content` VALUES (142, '<p>不知道</p>', '');
INSERT INTO `post_content` VALUES (143, '<p>我觉得有点傻逼</p>', '');
INSERT INTO `post_content` VALUES (144, '有的', NULL);
INSERT INTO `post_content` VALUES (145, '联系方式111111', NULL);
INSERT INTO `post_content` VALUES (146, '<p>我我我</p>', '');
INSERT INTO `post_content` VALUES (147, '我我我', NULL);
INSERT INTO `post_content` VALUES (148, '<p>可以</p>', '');
INSERT INTO `post_content` VALUES (149, '确定吗', NULL);
INSERT INTO `post_content` VALUES (150, '<p>呃呃呃</p>', '');
INSERT INTO `post_content` VALUES (151, '啥', NULL);
INSERT INTO `post_content` VALUES (152, '啊？', NULL);
INSERT INTO `post_content` VALUES (153, '(⊙﹏⊙)', NULL);
INSERT INTO `post_content` VALUES (154, '嗯？', NULL);
INSERT INTO `post_content` VALUES (155, '444', NULL);
INSERT INTO `post_content` VALUES (156, '555', NULL);
INSERT INTO `post_content` VALUES (157, '<p>我建议不学</p>', '');
INSERT INTO `post_content` VALUES (158, '我建议不建议', NULL);
INSERT INTO `post_content` VALUES (159, '别建议', NULL);
INSERT INTO `post_content` VALUES (160, '无语', NULL);
INSERT INTO `post_content` VALUES (161, '虾头男', NULL);
INSERT INTO `post_content` VALUES (162, '<p>不知道啊</p>', '');
INSERT INTO `post_content` VALUES (163, '<p>测试</p>', '');
INSERT INTO `post_content` VALUES (164, '<p>我建议不学</p>', '');
INSERT INTO `post_content` VALUES (165, '我不同意', NULL);
INSERT INTO `post_content` VALUES (166, '我建议不建议', NULL);
INSERT INTO `post_content` VALUES (167, '呃呃呃', NULL);
INSERT INTO `post_content` VALUES (168, '二二二', NULL);
INSERT INTO `post_content` VALUES (169, '服了', NULL);
INSERT INTO `post_content` VALUES (170, '无语', NULL);
INSERT INTO `post_content` VALUES (171, '虾头男', NULL);
INSERT INTO `post_content` VALUES (172, '<p>呃呃呃</p>', '');
INSERT INTO `post_content` VALUES (173, '<p>加油</p>', '');
INSERT INTO `post_content` VALUES (174, '素质素质\n', NULL);
INSERT INTO `post_content` VALUES (177, '<p>测试</p>', '');
INSERT INTO `post_content` VALUES (178, '<p>cs</p>', '');
INSERT INTO `post_content` VALUES (179, '<p>测试</p>', '');
INSERT INTO `post_content` VALUES (180, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\"><strong>太强了我的🦁🦁</strong></span><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\"><br></span><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\"><strong>UU们你们有这样👍的学校吗？</strong></span></p><p><img src=\"http://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-05-07/a440557a-0d14-4813-8817-e84571d79bf3NaN\" alt=\"2024-05-07/a440557a-0d14-4813-8817-e84571d79bf3NaN\" data-href=\"\" style=\"\"/></p>', '');
INSERT INTO `post_content` VALUES (181, '<p>😄</p>', '');
INSERT INTO `post_content` VALUES (183, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">到这个学校读要毕业的话要求过英语四级吗？</span></p>', '');
INSERT INTO `post_content` VALUES (184, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">请问我们学校数电的考研教室在哪里？🤕🤕🤕</span></p>', '');
INSERT INTO `post_content` VALUES (185, '<p>🤙</p>', '');
INSERT INTO `post_content` VALUES (186, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">崇理楼吧，不清楚，我在家备考的</span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon3.png\" alt=\"\" data-href=\"\" style=\"\"/><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">，你直接问辅导员吧，后面好像会统计</span></p>', '');
INSERT INTO `post_content` VALUES (187, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">不用</span></p>', '');
INSERT INTO `post_content` VALUES (188, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">寒假去了一次可是没人，想五一进去看看，给进去吗</span></p>', '');
INSERT INTO `post_content` VALUES (189, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">你专升本那个专业了？我物流管理</span></p>', '');
INSERT INTO `post_content` VALUES (190, '<p>可以啊</p>', '');
INSERT INTO `post_content` VALUES (191, '谢谢', NULL);
INSERT INTO `post_content` VALUES (192, '行政管理', NULL);
INSERT INTO `post_content` VALUES (193, '好的，谢谢', NULL);
INSERT INTO `post_content` VALUES (194, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">没人拦了现在</span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon91.png\" alt=\"\" data-href=\"\" style=\"\"/><br></p>', '');
INSERT INTO `post_content` VALUES (195, '<p>今天是2024年4月2号，坐标7栋，我外卖被偷了</p>', '');
INSERT INTO `post_content` VALUES (196, '<p>今天是2024年4月25号，坐标7栋，我外卖被偷了</p>', '');
INSERT INTO `post_content` VALUES (197, '<p>今天是2024年5月7日，坐标7栋，我外卖被偷了</p>', '');
INSERT INTO `post_content` VALUES (198, '<p style=\"text-align: left;\"><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">民🦁美食打分贴，邀请各位</span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon136.png\" alt=\"\" data-href=\"\" style=\"\"><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon136.png\" alt=\"\" data-href=\"\" style=\"\"><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">打分<br>0分:一拖四，味道不如答辩</span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon17.png\" alt=\"\" data-href=\"\" style=\"\"><br><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">1分:勉强能下肚，但是会吐出来</span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon9.png\" alt=\"\" data-href=\"\" style=\"\"><br><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">2分：味同嚼蜡，饿的不行了会考虑吃</span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon66.png\" alt=\"\" data-href=\"\" style=\"\"><br><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">3分:稍微有点味道，但不多</span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon1.png\" alt=\"\" data-href=\"\" style=\"\"><br><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">4分：还8错，值得安利</span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon3.png\" alt=\"\" data-href=\"\" style=\"\"><br><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">5分:非常棒，青春的回忆</span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon20.png\" alt=\"\" data-href=\"\" style=\"\"></p>', '');
INSERT INTO `post_content` VALUES (199, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">1.二食堂麻辣香锅</span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon24.png\" alt=\"\" data-href=\"\" style=\"\"/></p>', '');
INSERT INTO `post_content` VALUES (200, '3昏，小贵', NULL);
INSERT INTO `post_content` VALUES (201, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">2.二食堂益禾堂</span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon1.png\" alt=\"\" data-href=\"\" style=\"\"/></p>', '');
INSERT INTO `post_content` VALUES (202, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">4.纽约客</span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon24.png\" alt=\"\" data-href=\"\" style=\"\"/></p>', '');
INSERT INTO `post_content` VALUES (203, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">6.二食堂鸡公煲</span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon12.png\" alt=\"\" data-href=\"\" style=\"\"/></p>', '');
INSERT INTO `post_content` VALUES (204, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">7.二食堂桂林米粉</span></p>', '');
INSERT INTO `post_content` VALUES (205, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">8.二食堂肠粉</span></p>', '');
INSERT INTO `post_content` VALUES (206, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">9.二食堂烧味皇</span></p>', '');
INSERT INTO `post_content` VALUES (207, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">10.二食堂四楼马叔烧烤</span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon1.png\" alt=\"\" data-href=\"\" style=\"\"/></p>', '');
INSERT INTO `post_content` VALUES (208, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">12.水果街</span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon1.png\" alt=\"\" data-href=\"\" style=\"\"/></p>', '');
INSERT INTO `post_content` VALUES (209, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">13.二食堂三楼玉子猫</span></p>', '');
INSERT INTO `post_content` VALUES (210, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">14.辛食客麻辣烫</span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon66.png\" alt=\"\" data-href=\"\" style=\"\"/></p>', '');
INSERT INTO `post_content` VALUES (211, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">15.二食堂水果捞</span></p>', '');
INSERT INTO `post_content` VALUES (212, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">17.二食堂大口九</span></p>', '');
INSERT INTO `post_content` VALUES (213, '0昏', NULL);
INSERT INTO `post_content` VALUES (214, '水果不是很新鲜，2昏', NULL);
INSERT INTO `post_content` VALUES (215, '吃过，喷射，0昏', NULL);
INSERT INTO `post_content` VALUES (216, '0昏', NULL);
INSERT INTO `post_content` VALUES (217, '贵得一批，0昏', NULL);
INSERT INTO `post_content` VALUES (218, '还可以，4昏', NULL);
INSERT INTO `post_content` VALUES (219, '略贵一点，3昏', NULL);
INSERT INTO `post_content` VALUES (220, '一般，3昏', NULL);
INSERT INTO `post_content` VALUES (221, '一楼那个0昏', NULL);
INSERT INTO `post_content` VALUES (222, '又贵又少，0昏', NULL);
INSERT INTO `post_content` VALUES (223, '能吃，3昏', NULL);
INSERT INTO `post_content` VALUES (224, '还行，3昏', NULL);
INSERT INTO `post_content` VALUES (225, '0昏，吃了狠狠窜稀', NULL);
INSERT INTO `post_content` VALUES (226, '猪食', NULL);
INSERT INTO `post_content` VALUES (227, '猪食', NULL);
INSERT INTO `post_content` VALUES (228, '还行', NULL);
INSERT INTO `post_content` VALUES (229, '猪食', NULL);
INSERT INTO `post_content` VALUES (230, '猪食', NULL);
INSERT INTO `post_content` VALUES (231, '一般', NULL);
INSERT INTO `post_content` VALUES (232, '没吃过', NULL);
INSERT INTO `post_content` VALUES (233, '肉不新鲜，还贵，素菜还行', NULL);
INSERT INTO `post_content` VALUES (234, '贵贵贵', NULL);
INSERT INTO `post_content` VALUES (235, '凑合', NULL);
INSERT INTO `post_content` VALUES (236, '给那点肉抠得要命，味道也不行', NULL);
INSERT INTO `post_content` VALUES (237, '不行', NULL);
INSERT INTO `post_content` VALUES (238, '最多两昏', NULL);
INSERT INTO `post_content` VALUES (239, '好吃好吃', NULL);
INSERT INTO `post_content` VALUES (240, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">请问一下各位学长学姐，物流管理专升本宿舍几人间啊？上课是单独开班还是跟其他大三上？<br></span></p>', '');
INSERT INTO `post_content` VALUES (241, '<h1 style=\"text-align: left;\">我想问一下，二课学分里面的志愿公益不够0.5，能顺利毕业吗</h1>', '');
INSERT INTO `post_content` VALUES (242, '<h1 style=\"text-align: left;\">找台球搭子</h1>', '');
INSERT INTO `post_content` VALUES (243, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">机电专业升本来到这里的话怎么样，有没有必要升本过来读两年呢？<br></span></p>', '');
INSERT INTO `post_content` VALUES (244, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">请问一下各位师兄师姐们，在我的图书馆中登陆账为读者条码，读者条码是什么嘛？</span></p>', '');
INSERT INTO `post_content` VALUES (245, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">有没有学弟学妹帮忙补一份材料哒，现在毕业了不在学校过来不方便～</span></p>', '');
INSERT INTO `post_content` VALUES (246, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">现在实验做的很烂，感觉写不出论文了，如果延毕的话要延到什么时候</span></p>', '');
INSERT INTO `post_content` VALUES (247, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">经常没有网络连接</span></p><p><img src=\"http://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-05-08/11b82f24-6812-4c22-aab0-6067ffb574eeNaN\" alt=\"2024-05-08/11b82f24-6812-4c22-aab0-6067ffb574eeNaN\" data-href=\"\" style=\"\"/></p><p><br></p>', 'http://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-05-08/11b82f24-6812-4c22-aab0-6067ffb574eeNaN');
INSERT INTO `post_content` VALUES (248, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">有的时候好，有的时候有没有网络，但是wifi是一直连着的，这么不稳定</span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon6.png\" alt=\"\" data-href=\"\" style=\"\"/></p>', '');
INSERT INTO `post_content` VALUES (249, '<h1 style=\"text-align: left;\">有没有人知道学校在哪里寄信啊？</h1>', '');
INSERT INTO `post_content` VALUES (250, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">各位学长学姐你们好!我，想问一下想去这个学校读小学教育要多少分历史组呀</span></p>', '');
INSERT INTO `post_content` VALUES (251, '<h1 style=\"text-align: left;\">大家都什么时候回来啊</h1>', '');
INSERT INTO `post_content` VALUES (252, '<h1 style=\"text-align: left;\">宿舍的光纤能不能用</h1>', '');
INSERT INTO `post_content` VALUES (253, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">最近舍友买了校园网他们跟我说校园网还是快的，但是我不信，因为他们开黑一直喊卡</span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon1.png\" alt=\"\" data-href=\"\" style=\"\"/><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\"> </span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon1.png\" alt=\"\" data-href=\"\" style=\"\"/><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\"> </span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon1.png\" alt=\"\" data-href=\"\" style=\"\"/><br><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">还有就是用校园网在迅雷下载一些视频违规吗</span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon1.png\" alt=\"\" data-href=\"\" style=\"\"/><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\"> </span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon1.png\" alt=\"\" data-href=\"\" style=\"\"/></p>', '');
INSERT INTO `post_content` VALUES (254, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">有没有还没回家，或者就是崇左的同学，来个一起打台球的，手痒难耐，找个球搭子，台费我出</span></p>', '');
INSERT INTO `post_content` VALUES (255, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">笔记本电脑想换硬盘，学校里的电脑店可以换吗？</span></p>', '');
INSERT INTO `post_content` VALUES (256, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">学校啥时候更名为××大学啊？别人问我在哪里读，我说广西民族师范学院，在崇左，人家还以为我读的是广西民族大学，然后说不是在南宁吗...我简直晕倒，就是说在我毕业前（2027年）学校能改名为大学吗</span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon30.png\" alt=\"\" data-href=\"\" style=\"\"/><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\"> </span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon30.png\" alt=\"\" data-href=\"\" style=\"\"/></p>', '');
INSERT INTO `post_content` VALUES (257, '<p>有没有师兄师弟会换笔记本电脑硬盘，可以约个时间地点，30r可以请你帮忙弄一下吗？谢谢</p>', '');
INSERT INTO `post_content` VALUES (258, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">需求与需要</span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon25.png\" alt=\"\" data-href=\"\" style=\"\"/><br><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">学院贴吧出现有各种广告、交易等贴</span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon25.png\" alt=\"\" data-href=\"\" style=\"\"/><br><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">为了不影响广大吧友看帖 </span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon25.png\" alt=\"\" data-href=\"\" style=\"\"/><br><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">也为了首页不会大肆泛滥广告</span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon24.png\" alt=\"\" data-href=\"\" style=\"\"/><br><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">所有广告、交易都在此贴内进行 谢谢合作！</span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon20.png\" alt=\"\" data-href=\"\" style=\"\"/><br><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">广西民族师范学院吧吧务组 特别提醒:注意保护个人财产安全</span><img src=\"https://gsp0.baidu.com/5aAHeD3nKhI2p27j8IqW0jdnxx1xbK/tb/editor/images/client/image_emoticon28.png\" alt=\"\" data-href=\"\" style=\"\"/></p>', '');
INSERT INTO `post_content` VALUES (259, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">宿舍有几块小黑板，六块一张出</span></p>', '');
INSERT INTO `post_content` VALUES (260, '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 14px;\">基于学科核心素养的中学思想政治教学<br>出二手书两本，全新买的，基本没翻过，十块一本出，校园面提</span></p>', '');
INSERT INTO `post_content` VALUES (261, '<p>你好，书还在吗？</p>', '');
INSERT INTO `post_content` VALUES (262, '确实一般般，又贵', NULL);
INSERT INTO `post_content` VALUES (263, '还行', NULL);
INSERT INTO `post_content` VALUES (264, '不好吃把，我觉得巨难吃', NULL);
INSERT INTO `post_content` VALUES (265, '而且又贵', NULL);
INSERT INTO `post_content` VALUES (266, '那个肉不知道冻了多久了', NULL);
INSERT INTO `post_content` VALUES (267, '全是科技肉', NULL);
INSERT INTO `post_content` VALUES (268, '<p>测试<img src=\"http://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-05-13/5a16424f-baf5-43cc-9156-26544a51158dtype=images\" alt=\"2024-05-13/5a16424f-baf5-43cc-9156-26544a51158dtype=images\" data-href=\"\" style=\"\"/></p><p><br></p><p><br></p><div data-w-e-type=\"video\" data-w-e-is-void>\n<video poster=\"\" controls=\"true\" width=\"auto\" height=\"auto\"><source src=\"http://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-05-13/a692d3a0-9aff-4032-bf3b-eeb6829f63catype=video\" type=\"video/mp4\"/></video>\n</div><p><br></p>', 'http://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-05-13/a692d3a0-9aff-4032-bf3b-eeb6829f63catype=video');
INSERT INTO `post_content` VALUES (269, '<p><br></p><div data-w-e-type=\"video\" data-w-e-is-void>\n<video poster=\"\" controls=\"true\" width=\"auto\" height=\"auto\"><source src=\"http://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-05-13/f755d4c1-0b5b-4595-85e0-849e6fdd8b0atype=video\" type=\"video/mp4\"/></video>\n</div><p><br></p>', 'http://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-05-13/f755d4c1-0b5b-4595-85e0-849e6fdd8b0atype=video');
INSERT INTO `post_content` VALUES (270, '<p><br></p><div data-w-e-type=\"video\" data-w-e-is-void>\n<video poster=\"\" controls=\"true\" width=\"auto\" height=\"auto\"><source src=\"http://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-05-14/a7a5bdd4-78d0-43ec-b4d3-3a932a1f0b2ftype=video\" type=\"video/mp4\"/></video>\n</div><p><br></p>', 'http://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-05-14/a7a5bdd4-78d0-43ec-b4d3-3a932a1f0b2ftype=video');
INSERT INTO `post_content` VALUES (271, '<p>cs</p><p><img src=\"http://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-05-14/64828ccb-f3dd-4b89-9e6f-e0be593c88b5type=images\" alt=\"2024-05-14/64828ccb-f3dd-4b89-9e6f-e0be593c88b5type=images\" data-href=\"\" style=\"\"/></p><p><br></p><div data-w-e-type=\"video\" data-w-e-is-void>\n<video poster=\"\" controls=\"true\" width=\"auto\" height=\"auto\"><source src=\"http://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-05-14/e705c1fe-a294-4f8b-9c28-89a6c498f6b0type=video\" type=\"video/mp4\"/></video>\n</div><p><br></p>', 'http://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-05-14/64828ccb-f3dd-4b89-9e6f-e0be593c88b5type=images;;http://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-05-14/e705c1fe-a294-4f8b-9c28-89a6c498f6b0type=video');
INSERT INTO `post_content` VALUES (272, '呃呃呃', NULL);
INSERT INTO `post_content` VALUES (273, '<p><strong>123123</strong></p><p><img src=\"http://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-05-14/53cdc9e8-5357-4508-8b93-5477267cbbe0type=images\" alt=\"2024-05-14/53cdc9e8-5357-4508-8b93-5477267cbbe0type=images\" data-href=\"\" style=\"\"/></p>', 'http://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-05-14/53cdc9e8-5357-4508-8b93-5477267cbbe0type=images');
INSERT INTO `post_content` VALUES (274, '测试', NULL);
INSERT INTO `post_content` VALUES (275, '<p><img src=\"http://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-05-14/e212e611-b52a-4d40-b002-4fe5efa9d88dtype=images\" alt=\"2024-05-14/e212e611-b52a-4d40-b002-4fe5efa9d88dtype=images\" data-href=\"\" style=\"\"/></p>', 'http://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-05-14/e212e611-b52a-4d40-b002-4fe5efa9d88dtype=images');
INSERT INTO `post_content` VALUES (276, '<p><br></p><div data-w-e-type=\"video\" data-w-e-is-void>\n<video poster=\"\" controls=\"true\" width=\"auto\" height=\"auto\"><source src=\"http://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-05-14/1d5a7443-a1f8-48b5-9336-d0e740793a59type=video\" type=\"video/mp4\"/></video>\n</div><p><br></p>', 'http://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-05-14/1d5a7443-a1f8-48b5-9336-d0e740793a59type=video');
INSERT INTO `post_content` VALUES (277, '21312', NULL);
INSERT INTO `post_content` VALUES (278, '12321', NULL);
INSERT INTO `post_content` VALUES (279, '<p><img src=\"http://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-05-14/9fd63d52-a595-4deb-830c-4c2a79d70224type=images\" alt=\"2024-05-14/9fd63d52-a595-4deb-830c-4c2a79d70224type=images\" data-href=\"\" style=\"\"/></p><p><br></p><p><strong>213123</strong></p>', 'http://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-05-14/9fd63d52-a595-4deb-830c-4c2a79d70224type=images');
INSERT INTO `post_content` VALUES (280, '<p>23213</p>', '');

-- ----------------------------
-- Table structure for post_main
-- ----------------------------
DROP TABLE IF EXISTS `post_main`;
CREATE TABLE `post_main`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '其他' COMMENT '类型',
  `type_id` int NOT NULL DEFAULT 99 COMMENT '类型id',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标题',
  `content_id` bigint NOT NULL COMMENT '回复id',
  `reply_count` int NULL DEFAULT 0 COMMENT '总回复数',
  `likes` int NULL DEFAULT 0 COMMENT '点赞总数',
  `favorites` int NOT NULL DEFAULT 0 COMMENT '收藏数',
  `is_enable` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '状态（1:显示,0:封禁,2:审核中）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '逻辑删除（0:未删除；1:已删除）',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id_index`(`user_id`) USING BTREE,
  INDEX `create_time_index`(`create_time`) USING BTREE,
  INDEX `status_index`(`is_enable`) USING BTREE,
  INDEX `del_flag_index`(`del_flag`) USING BTREE,
  INDEX `likes_index`(`likes`) USING BTREE,
  INDEX `type_id_index`(`type_id`) USING BTREE,
  INDEX `content_id_index`(`content_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of post_main
-- ----------------------------
INSERT INTO `post_main` VALUES (57, 9, '其他', 9, '中职升本怎么考试？', 135, 0, 0, 0, '1', '1', '2024-04-24 17:15:42', '2024-05-14 01:14:22');
INSERT INTO `post_main` VALUES (58, 9, '其他', 9, '找家教', 136, 0, 1, 0, '1', '0', '2024-04-24 17:16:08', '2024-05-14 11:29:35');
INSERT INTO `post_main` VALUES (59, 9, '其他', 9, '体育课重修可以重修选课吗', 137, 3, 3, 0, '0', '0', '2024-04-24 17:16:53', '2024-05-14 14:43:28');
INSERT INTO `post_main` VALUES (60, 9, '最近实事', 1, '以画会友，寻找醉美笔触', 138, 0, 2, 2, '1', '0', '2024-04-24 17:19:19', '2024-05-14 12:42:12');
INSERT INTO `post_main` VALUES (65, 9, '最近实事', 2, '五一开心贴', 180, 3, 2, 2, '1', '0', '2024-05-07 16:19:53', '2024-05-14 11:47:00');
INSERT INTO `post_main` VALUES (66, 10, '求助', 9, '求助，专升本', 183, 2, 0, 0, '1', '0', '2024-05-07 16:32:23', '2024-05-07 17:06:07');
INSERT INTO `post_main` VALUES (67, 10, '求助', 9, '求助各位同学', 184, 1, 1, 0, '1', '0', '2024-05-07 16:32:52', '2024-05-07 17:03:44');
INSERT INTO `post_main` VALUES (68, 11, '其他', 99, '毕业六年的老鸟想回母校看看，可以进去吗', 188, 2, 1, 0, '1', '0', '2024-05-07 17:05:14', '2024-05-07 17:18:10');
INSERT INTO `post_main` VALUES (69, 12, '吐槽', 3, 'wyjj的外卖日记', 195, 2, 0, 0, '1', '0', '2024-04-02 17:20:05', '2024-05-07 17:21:01');
INSERT INTO `post_main` VALUES (70, 12, '分享', 2, '探店厚比多，真假🐭🐭说', 198, 13, 3, 3, '1', '0', '2024-05-07 17:26:25', '2024-05-14 14:42:51');
INSERT INTO `post_main` VALUES (71, 15, '求助', 9, '2024专升本', 240, 0, 0, 0, '2', '0', '2024-05-08 10:03:59', '2024-05-08 10:03:59');
INSERT INTO `post_main` VALUES (72, 15, '求助', 9, '我想问一下，二课学分里面的志愿公益不够0.5，能顺利毕业吗', 241, 0, 0, 0, '1', '0', '2024-05-08 10:04:20', '2024-05-08 10:05:01');
INSERT INTO `post_main` VALUES (73, 15, '求助', 9, '找台球搭子', 242, 0, 0, 0, '1', '0', '2024-05-08 10:04:44', '2024-05-08 10:05:01');
INSERT INTO `post_main` VALUES (74, 15, '求助', 9, '有没有专升本来到这的学长学姐', 243, 0, 0, 0, '1', '0', '2024-05-08 10:05:05', '2024-05-08 10:06:01');
INSERT INTO `post_main` VALUES (75, 15, '求助', 9, '不懂就要问嘛', 244, 0, 0, 0, '1', '0', '2024-05-08 10:05:29', '2024-05-08 10:06:01');
INSERT INTO `post_main` VALUES (76, 15, '求助', 9, '帮忙补一份材料，有偿', 245, 0, 0, 0, '1', '0', '2024-05-08 10:06:13', '2024-05-08 10:07:01');
INSERT INTO `post_main` VALUES (77, 15, '求助', 9, '请各位知道的同学回答一下', 246, 0, 0, 0, '1', '0', '2024-05-08 10:06:33', '2024-05-08 10:07:01');
INSERT INTO `post_main` VALUES (78, 15, '吐槽', 3, '这校园网咋回事啊', 247, 1, 0, 0, '2', '0', '2024-05-08 10:07:59', '2024-05-08 10:10:38');
INSERT INTO `post_main` VALUES (79, 14, '求助', 9, '有没有人知道学校在哪里寄信啊？', 249, 0, 0, 0, '1', '0', '2024-05-08 21:14:21', '2024-05-08 21:15:01');
INSERT INTO `post_main` VALUES (80, 14, '求助', 9, '多少分能去这个学校读本科', 250, 0, 0, 0, '1', '0', '2024-05-08 21:14:43', '2024-05-08 21:15:01');
INSERT INTO `post_main` VALUES (81, 14, '分享', 2, '大家都什么时候回来啊', 251, 0, 0, 0, '1', '0', '2024-05-08 21:15:22', '2024-05-08 21:16:01');
INSERT INTO `post_main` VALUES (82, 14, '求助', 9, '宿舍的光纤能不能用', 252, 0, 0, 0, '1', '0', '2024-05-08 21:15:38', '2024-05-08 21:16:01');
INSERT INTO `post_main` VALUES (83, 14, '求助', 9, '住在靠近足球场那边，校园网快不快？', 253, 0, 0, 0, '1', '0', '2024-05-08 21:18:34', '2024-05-08 21:19:00');
INSERT INTO `post_main` VALUES (84, 14, '趣事', 4, '有没有在崇左或还在学校的，蹲个一起打桌球的', 254, 0, 0, 0, '1', '0', '2024-05-08 21:19:01', '2024-05-08 21:20:01');
INSERT INTO `post_main` VALUES (85, 14, '求助', 9, '学校电脑店可以换笔记本电脑硬盘吗？', 255, 0, 0, 0, '1', '0', '2024-05-08 21:19:37', '2024-05-08 21:20:01');
INSERT INTO `post_main` VALUES (86, 14, '吐槽', 3, '想学校更名的心达到了顶点', 256, 0, 0, 0, '1', '0', '2024-05-08 21:21:05', '2024-05-08 21:22:00');
INSERT INTO `post_main` VALUES (87, 14, '求助', 9, '有师兄师弟会换笔记本电脑硬盘吗？', 257, 0, 0, 0, '1', '0', '2024-05-08 21:42:53', '2024-05-08 21:43:00');
INSERT INTO `post_main` VALUES (88, 14, '二手交易', 6, '广西民族师范学院交易帖', 258, 0, 0, 0, '1', '0', '2024-05-08 21:43:34', '2024-05-08 21:44:00');
INSERT INTO `post_main` VALUES (89, 14, '二手交易', 6, '出黑板', 259, 0, 0, 0, '1', '0', '2024-05-08 21:44:01', '2024-05-08 21:45:01');
INSERT INTO `post_main` VALUES (90, 14, '二手交易', 6, '出书', 260, 1, 1, 1, '1', '0', '2024-05-08 21:44:21', '2024-05-13 17:14:54');
INSERT INTO `post_main` VALUES (98, 9, '吐槽', 3, '测试', 276, 0, 0, 0, '1', '0', '2024-05-14 14:02:39', '2024-05-14 14:03:00');
INSERT INTO `post_main` VALUES (99, 9, '吐槽', 3, '213', 279, 0, 0, 0, '1', '0', '2024-05-14 14:44:06', '2024-05-14 14:45:00');
INSERT INTO `post_main` VALUES (100, 9, '吐槽', 3, '123', 280, 0, 0, 0, '2', '0', '2024-05-14 14:47:19', '2024-05-14 14:47:19');

-- ----------------------------
-- Table structure for post_reply
-- ----------------------------
DROP TABLE IF EXISTS `post_reply`;
CREATE TABLE `post_reply`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `parent_id` bigint NOT NULL DEFAULT 0 COMMENT '父评论id',
  `parent_user_id` bigint NOT NULL COMMENT '父评论的userid',
  `parent_user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '父评论的username',
  `content_id` bigint NOT NULL COMMENT '回复id',
  `root_id` bigint NOT NULL DEFAULT 0 COMMENT '根贴子id (0：自身为根贴)',
  `main_id` bigint NOT NULL DEFAULT 0 COMMENT '主贴子id(0：自身为主贴)',
  `reply_count` int NULL DEFAULT 0 COMMENT '总回复数',
  `floor` int NOT NULL COMMENT '楼层',
  `likes` int NULL DEFAULT 0 COMMENT '点赞总数',
  `is_enable` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '1' COMMENT '状态（1:显示,0:隐藏）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '逻辑删除（0:未删除；1:已删除）',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id_index`(`user_id`) USING BTREE,
  INDEX `root_id_index`(`root_id`) USING BTREE,
  INDEX `create_time_index`(`create_time`) USING BTREE,
  INDEX `status_index`(`is_enable`) USING BTREE,
  INDEX `del_flag_index`(`del_flag`) USING BTREE,
  INDEX `parent_id_index`(`parent_id`) USING BTREE,
  INDEX `main_id_index`(`main_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 158 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of post_reply
-- ----------------------------
INSERT INTO `post_reply` VALUES (80, 9, 59, 9, '小明', 164, 0, 59, 10, 2, 0, '1', '0', '2024-04-28 20:23:23', '2024-05-14 14:43:33');
INSERT INTO `post_reply` VALUES (81, 9, 80, 9, '小明', 165, 80, 59, 0, 3, 0, '1', '0', '2024-04-28 20:23:28', '2024-04-28 20:23:28');
INSERT INTO `post_reply` VALUES (82, 9, 80, 9, '小明', 166, 80, 59, 0, 3, 0, '1', '0', '2024-04-28 20:23:34', '2024-04-28 20:23:34');
INSERT INTO `post_reply` VALUES (83, 9, 80, 9, '小明', 167, 80, 59, 0, 3, 0, '1', '0', '2024-04-28 20:23:44', '2024-04-28 20:23:44');
INSERT INTO `post_reply` VALUES (84, 9, 80, 9, '小明', 168, 80, 59, 0, 3, 0, '1', '0', '2024-04-28 20:23:49', '2024-04-28 20:23:49');
INSERT INTO `post_reply` VALUES (85, 9, 80, 9, '小明', 169, 80, 59, 0, 3, 0, '1', '0', '2024-04-28 20:23:56', '2024-04-28 20:23:56');
INSERT INTO `post_reply` VALUES (86, 9, 80, 9, '小明', 170, 80, 59, 0, 3, 0, '1', '0', '2024-04-28 20:24:00', '2024-04-28 20:24:00');
INSERT INTO `post_reply` VALUES (87, 9, 80, 9, '小明', 171, 80, 59, 0, 3, 0, '1', '0', '2024-04-28 20:24:14', '2024-04-28 20:24:14');
INSERT INTO `post_reply` VALUES (88, 9, 59, 9, '小明', 172, 0, 59, 0, 3, 0, '1', '0', '2024-04-28 20:24:26', '2024-04-28 20:24:26');
INSERT INTO `post_reply` VALUES (90, 9, 80, 9, '小明', 174, 80, 59, 0, 5, 0, '1', '0', '2024-04-28 20:58:27', '2024-04-28 20:58:27');
INSERT INTO `post_reply` VALUES (92, 10, 65, 9, '小明', 181, 0, 65, 0, 2, 0, '1', '0', '2024-05-07 16:28:20', '2024-05-07 16:29:00');
INSERT INTO `post_reply` VALUES (94, 11, 65, 9, '小明', 185, 0, 65, 0, 3, 0, '1', '0', '2024-05-07 16:33:38', '2024-05-07 16:58:01');
INSERT INTO `post_reply` VALUES (95, 11, 67, 10, '小王', 186, 0, 67, 1, 2, 1, '1', '0', '2024-05-07 17:03:42', '2024-05-07 17:07:27');
INSERT INTO `post_reply` VALUES (96, 11, 66, 10, '小王', 187, 0, 66, 1, 2, 0, '1', '0', '2024-05-07 17:04:01', '2024-05-07 17:07:57');
INSERT INTO `post_reply` VALUES (97, 9, 66, 10, '小王', 189, 0, 66, 1, 3, 0, '1', '0', '2024-05-07 17:06:07', '2024-05-07 17:07:50');
INSERT INTO `post_reply` VALUES (98, 10, 68, 11, '小赵', 190, 0, 68, 0, 2, 0, '1', '0', '2024-05-07 17:07:18', '2024-05-07 17:08:02');
INSERT INTO `post_reply` VALUES (99, 10, 95, 11, '小王', 191, 95, 67, 0, 3, 0, '1', '0', '2024-05-07 17:07:27', '2024-05-07 17:08:02');
INSERT INTO `post_reply` VALUES (100, 10, 97, 9, '小王', 192, 97, 66, 0, 4, 0, '1', '0', '2024-05-07 17:07:51', '2024-05-07 17:08:02');
INSERT INTO `post_reply` VALUES (101, 10, 96, 11, '小王', 193, 96, 66, 0, 4, 0, '1', '0', '2024-05-07 17:07:57', '2024-05-07 17:08:02');
INSERT INTO `post_reply` VALUES (102, 12, 68, 11, '小赵', 194, 0, 68, 0, 3, 0, '1', '0', '2024-05-07 17:18:07', '2024-05-07 17:19:00');
INSERT INTO `post_reply` VALUES (103, 12, 69, 12, 'wyjj', 196, 0, 69, 0, 2, 0, '1', '0', '2024-04-25 17:20:19', '2024-05-07 17:21:01');
INSERT INTO `post_reply` VALUES (104, 12, 69, 12, 'wyjj', 197, 0, 69, 0, 3, 0, '1', '0', '2024-05-07 17:20:30', '2024-05-07 17:21:01');
INSERT INTO `post_reply` VALUES (105, 12, 70, 12, 'wyjj', 199, 0, 70, 6, 2, 1, '1', '0', '2024-05-07 17:26:53', '2024-05-13 23:12:29');
INSERT INTO `post_reply` VALUES (106, 12, 105, 12, 'wyjj', 200, 105, 70, 0, 3, 1, '1', '0', '2024-05-07 17:27:00', '2024-05-08 09:25:22');
INSERT INTO `post_reply` VALUES (107, 12, 70, 12, 'wyjj', 201, 0, 70, 3, 3, 1, '1', '0', '2024-05-07 17:27:25', '2024-05-08 09:52:29');
INSERT INTO `post_reply` VALUES (108, 12, 70, 12, 'wyjj', 202, 0, 70, 3, 4, 1, '1', '0', '2024-05-07 17:27:31', '2024-05-08 09:32:51');
INSERT INTO `post_reply` VALUES (109, 12, 70, 12, 'wyjj', 203, 0, 70, 3, 5, 0, '1', '0', '2024-05-07 17:27:37', '2024-05-08 09:31:57');
INSERT INTO `post_reply` VALUES (110, 12, 70, 12, 'wyjj', 204, 0, 70, 3, 6, 0, '1', '0', '2024-05-07 17:27:43', '2024-05-08 09:34:22');
INSERT INTO `post_reply` VALUES (111, 12, 70, 12, 'wyjj', 205, 0, 70, 1, 7, 0, '1', '0', '2024-05-07 17:27:47', '2024-05-07 17:31:12');
INSERT INTO `post_reply` VALUES (112, 12, 70, 12, 'wyjj', 206, 0, 70, 2, 8, 0, '1', '0', '2024-05-07 17:27:51', '2024-05-07 17:36:30');
INSERT INTO `post_reply` VALUES (113, 12, 70, 12, 'wyjj', 207, 0, 70, 2, 9, 1, '1', '0', '2024-05-07 17:28:02', '2024-05-07 17:38:01');
INSERT INTO `post_reply` VALUES (114, 12, 70, 12, 'wyjj', 208, 0, 70, 1, 10, 0, '1', '0', '2024-05-07 17:28:10', '2024-05-07 17:29:40');
INSERT INTO `post_reply` VALUES (115, 12, 70, 12, 'wyjj', 209, 0, 70, 2, 11, 0, '1', '0', '2024-05-07 17:28:19', '2024-05-07 17:35:59');
INSERT INTO `post_reply` VALUES (116, 12, 70, 12, 'wyjj', 210, 0, 70, 1, 12, 0, '1', '0', '2024-05-07 17:28:24', '2024-05-07 17:29:12');
INSERT INTO `post_reply` VALUES (117, 12, 70, 12, 'wyjj', 211, 0, 70, 2, 13, 0, '1', '0', '2024-05-07 17:28:28', '2024-05-07 17:37:25');
INSERT INTO `post_reply` VALUES (118, 12, 70, 12, 'wyjj', 212, 0, 70, 7, 14, 1, '1', '0', '2024-05-07 17:28:33', '2024-05-14 14:42:54');
INSERT INTO `post_reply` VALUES (119, 12, 118, 12, 'wyjj', 213, 118, 70, 0, 15, 1, '1', '0', '2024-05-07 17:28:55', '2024-05-13 17:15:34');
INSERT INTO `post_reply` VALUES (120, 12, 117, 12, 'wyjj', 214, 117, 70, 0, 15, 0, '1', '0', '2024-05-07 17:29:05', '2024-05-07 17:30:02');
INSERT INTO `post_reply` VALUES (121, 12, 116, 12, 'wyjj', 215, 116, 70, 0, 15, 0, '1', '0', '2024-05-07 17:29:13', '2024-05-07 17:29:13');
INSERT INTO `post_reply` VALUES (122, 12, 115, 12, 'wyjj', 216, 115, 70, 0, 15, 0, '1', '0', '2024-05-07 17:29:20', '2024-05-07 17:30:02');
INSERT INTO `post_reply` VALUES (123, 12, 114, 12, 'wyjj', 217, 114, 70, 0, 15, 0, '1', '0', '2024-05-07 17:29:41', '2024-05-07 17:30:02');
INSERT INTO `post_reply` VALUES (124, 12, 113, 12, 'wyjj', 218, 113, 70, 0, 15, 0, '1', '0', '2024-05-07 17:30:17', '2024-05-07 17:31:02');
INSERT INTO `post_reply` VALUES (125, 12, 112, 12, 'wyjj', 219, 112, 70, 0, 15, 0, '1', '0', '2024-05-07 17:30:40', '2024-05-07 17:31:02');
INSERT INTO `post_reply` VALUES (126, 12, 111, 12, 'wyjj', 220, 111, 70, 0, 15, 0, '1', '0', '2024-05-07 17:31:12', '2024-05-07 17:32:02');
INSERT INTO `post_reply` VALUES (127, 12, 110, 12, 'wyjj', 221, 110, 70, 0, 15, 0, '1', '0', '2024-05-07 17:31:26', '2024-05-07 17:32:02');
INSERT INTO `post_reply` VALUES (128, 12, 109, 12, 'wyjj', 222, 109, 70, 0, 15, 0, '1', '0', '2024-05-07 17:31:51', '2024-05-07 17:32:02');
INSERT INTO `post_reply` VALUES (129, 12, 108, 12, 'wyjj', 223, 108, 70, 0, 15, 0, '1', '0', '2024-05-07 17:32:14', '2024-05-07 17:33:01');
INSERT INTO `post_reply` VALUES (130, 12, 107, 12, 'wyjj', 224, 107, 70, 0, 15, 0, '1', '0', '2024-05-07 17:32:29', '2024-05-07 17:33:01');
INSERT INTO `post_reply` VALUES (131, 13, 108, 12, 'wyjj', 225, 108, 70, 0, 15, 0, '1', '0', '2024-05-07 17:35:33', '2024-05-07 17:36:01');
INSERT INTO `post_reply` VALUES (132, 13, 109, 12, 'wyjj', 226, 109, 70, 0, 15, 0, '1', '0', '2024-05-07 17:35:53', '2024-05-07 17:36:01');
INSERT INTO `post_reply` VALUES (133, 13, 115, 12, 'wyjj', 227, 115, 70, 0, 15, 0, '1', '0', '2024-05-07 17:36:00', '2024-05-07 17:36:01');
INSERT INTO `post_reply` VALUES (134, 13, 113, 12, 'wyjj', 228, 113, 70, 0, 15, 0, '1', '0', '2024-05-07 17:36:14', '2024-05-07 17:37:01');
INSERT INTO `post_reply` VALUES (135, 13, 110, 12, 'wyjj', 229, 110, 70, 0, 15, 0, '1', '0', '2024-05-07 17:36:23', '2024-05-07 17:37:01');
INSERT INTO `post_reply` VALUES (136, 13, 112, 12, 'wyjj', 230, 112, 70, 0, 15, 0, '1', '0', '2024-05-07 17:36:31', '2024-05-07 17:37:01');
INSERT INTO `post_reply` VALUES (137, 14, 118, 12, 'wyjj', 231, 118, 70, 0, 15, 1, '1', '0', '2024-05-07 17:37:18', '2024-05-13 17:15:33');
INSERT INTO `post_reply` VALUES (138, 14, 117, 12, 'wyjj', 232, 117, 70, 0, 15, 0, '1', '0', '2024-05-07 17:37:25', '2024-05-07 17:38:01');
INSERT INTO `post_reply` VALUES (139, 9, 105, 12, 'wyjj', 233, 105, 70, 0, 15, 1, '1', '0', '2024-05-08 09:25:30', '2024-05-13 23:17:18');
INSERT INTO `post_reply` VALUES (140, 9, 109, 12, 'wyjj', 234, 109, 70, 0, 15, 0, '1', '0', '2024-05-08 09:31:41', '2024-05-08 09:32:00');
INSERT INTO `post_reply` VALUES (141, 9, 108, 12, 'wyjj', 235, 108, 70, 0, 15, 0, '1', '0', '2024-05-08 09:32:51', '2024-05-08 09:33:00');
INSERT INTO `post_reply` VALUES (142, 9, 110, 12, 'wyjj', 236, 110, 70, 0, 15, 0, '1', '0', '2024-05-08 09:34:06', '2024-05-08 09:35:00');
INSERT INTO `post_reply` VALUES (143, 9, 107, 12, 'wyjj', 237, 107, 70, 0, 15, 0, '1', '0', '2024-05-08 09:51:40', '2024-05-08 09:52:00');
INSERT INTO `post_reply` VALUES (144, 9, 107, 12, 'wyjj', 238, 107, 70, 0, 15, 0, '1', '0', '2024-05-08 09:52:30', '2024-05-08 09:53:00');
INSERT INTO `post_reply` VALUES (145, 9, 105, 12, 'wyjj', 239, 105, 70, 0, 15, 0, '1', '0', '2024-05-08 09:58:29', '2024-05-08 09:59:00');
INSERT INTO `post_reply` VALUES (146, 15, 78, 15, '邓冰冰', 248, 0, 78, 0, 2, 0, '1', '0', '2024-05-08 10:10:39', '2024-05-08 10:11:01');
INSERT INTO `post_reply` VALUES (147, 13, 90, 14, '还以为故事没说完', 261, 0, 90, 0, 2, 0, '1', '0', '2024-05-13 17:14:54', '2024-05-13 17:15:01');
INSERT INTO `post_reply` VALUES (148, 13, 118, 12, 'wyjj', 262, 118, 70, 0, 15, 1, '1', '0', '2024-05-13 17:15:31', '2024-05-13 17:16:00');
INSERT INTO `post_reply` VALUES (149, 16, 105, 12, 'wyjj', 263, 105, 70, 0, 15, 0, '1', '0', '2024-05-13 23:03:10', '2024-05-13 23:04:00');
INSERT INTO `post_reply` VALUES (150, 16, 105, 12, 'wyjj', 264, 105, 70, 0, 15, 0, '1', '0', '2024-05-13 23:12:02', '2024-05-13 23:13:02');
INSERT INTO `post_reply` VALUES (151, 16, 118, 12, 'wyjj', 265, 118, 70, 0, 15, 0, '1', '0', '2024-05-13 23:12:08', '2024-05-13 23:13:02');
INSERT INTO `post_reply` VALUES (152, 16, 118, 12, 'wyjj', 266, 118, 70, 0, 15, 0, '1', '0', '2024-05-13 23:12:25', '2024-05-13 23:13:02');
INSERT INTO `post_reply` VALUES (153, 16, 105, 12, 'wyjj', 267, 105, 70, 0, 15, 0, '1', '0', '2024-05-13 23:12:30', '2024-05-13 23:13:02');
INSERT INTO `post_reply` VALUES (154, 9, 81, 9, '小明', 272, 80, 59, 0, 4, 0, '1', '0', '2024-05-14 01:03:42', '2024-05-14 01:04:00');
INSERT INTO `post_reply` VALUES (155, 9, 118, 12, 'wyjj', 274, 118, 70, 0, 15, 0, '1', '0', '2024-05-14 13:49:38', '2024-05-14 13:50:00');
INSERT INTO `post_reply` VALUES (156, 9, 118, 12, 'wyjj', 277, 118, 70, 0, 15, 0, '1', '0', '2024-05-14 14:42:54', '2024-05-14 14:43:00');
INSERT INTO `post_reply` VALUES (157, 9, 81, 9, '小明', 278, 80, 59, 0, 4, 0, '1', '0', '2024-05-14 14:43:34', '2024-05-14 14:44:01');

SET FOREIGN_KEY_CHECKS = 1;
