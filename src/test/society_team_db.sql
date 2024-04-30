/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50527
 Source Host           : localhost:3306
 Source Schema         : society_team_db

 Target Server Type    : MySQL
 Target Server Version : 50527
 File Encoding         : 65001

 Date: 02/03/2024 00:05:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `create_time` date NULL DEFAULT NULL COMMENT '发布时间',
  `end_time` date NULL DEFAULT NULL COMMENT '结束时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `s_id` int(11) NULL DEFAULT NULL COMMENT '社团id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES (1, '开学仪式1', '迎接新学弟学妹，需要来人不止欢迎会场，帮助学弟学妹找到教室或寝室', '2024-03-12', '2024-03-13', '希望大家踊跃报名1', 1);
INSERT INTO `activity` VALUES (2, '毕业典礼', '欢送学姐学长毕业，举办毕业典礼，', '2024-03-12', '2024-03-14', '希望大家踊跃报名', 1);
INSERT INTO `activity` VALUES (3, '体育竞技', '举办两年一届的运动会，希望大家踊跃帮助同学报名参加', '2024-03-19', '2024-03-20', '希望大家踊跃报名', 1);
INSERT INTO `activity` VALUES (4, '游戏竞技', '举办以寝室为核心的游戏竞技会，希望大家踊跃帮助同学报名参加', '2024-03-25', '2024-03-27', '希望大家踊跃报名', 1);
INSERT INTO `activity` VALUES (5, '游戏竞技a ', '举办以寝室为核心的游戏竞技会，希望大家踊跃帮助同学报名参加', '2024-03-18', '2024-03-27', '希望大家踊跃报名', 1);
INSERT INTO `activity` VALUES (6, '运动会', '主要是为了新一届。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。才开的大会', '2024-03-03', '2024-03-04', '主要回去的时候给自己的垃圾带走', 5);

-- ----------------------------
-- Table structure for society_team
-- ----------------------------
DROP TABLE IF EXISTS `society_team`;
CREATE TABLE `society_team`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '社团id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '社团名称',
  `join_price` decimal(11, 0) NULL DEFAULT NULL COMMENT '入团团费',
  `slogan` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '社团口号',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '社团类型',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '社团简介',
  `create_time` date NULL DEFAULT NULL COMMENT '社团创建时期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of society_team
-- ----------------------------
INSERT INTO `society_team` VALUES (1, '文学社', 120, '文学文学，有学问就是好', '文学', '大学社团包括文学社，文学社是大学里最普遍的社团之一。在文学社里，有热衷于文学写作的很多同学，互相切磋文学创作，互相交流学习心得和经验。文学社不仅能收获写作的乐趣，还能从中学习很多。', '2024-03-01');
INSERT INTO `society_team` VALUES (3, '舞蹈社团', 124, '舞蹈社团舞蹈社团舞蹈社团', '舞蹈', '大学社团有舞蹈社团、街舞社团、集体舞社团等。舞蹈社团可以带领喜欢舞蹈的同学一起学习舞蹈，教授交谊舞、集体舞等社交舞蹈。舞蹈社团还可以教授舞蹈知识和技巧，培养学生对舞蹈的热爱和追求。在学校的舞蹈展演活动中，舞蹈社团也会展示他们的才华，吸引许多学子的参与。通过学习舞蹈，学生可以展示自己的艺术才华，丰富自己的文化底蕴。', '2024-03-01');
INSERT INTO `society_team` VALUES (4, '武术社团', 120, '武术社团武术社团武术社团', '武术', '大学社团有武术社团，这是大学生们喜欢的一个社团，可以互相切磋武功、提高技能，还有表演和比赛的机会。', '2024-03-01');
INSERT INTO `society_team` VALUES (5, '体育社', 20, '生活强身健体', '体育', '生活强身健体，只为了更好地未来和更好的身体，加入我们吧', '2024-03-01');

-- ----------------------------
-- Table structure for team_money
-- ----------------------------
DROP TABLE IF EXISTS `team_money`;
CREATE TABLE `team_money`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uid` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `sid` int(11) NULL DEFAULT NULL COMMENT '社团id',
  `price` int(11) NULL DEFAULT NULL COMMENT '团费价格',
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态（0未交，1已缴）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of team_money
-- ----------------------------
INSERT INTO `team_money` VALUES (1, 3, 1, 120, '1');
INSERT INTO `team_money` VALUES (2, 3, 1, 120, '1');
INSERT INTO `team_money` VALUES (3, 30, 1, 120, '1');
INSERT INTO `team_money` VALUES (7, 3, 5, 20, '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色(0管理员，1用户,2工作人员)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, 'admin', '123456', '123456', '0', '110', '0');
INSERT INTO `user` VALUES (3, '111', '张帅', '123456', '0', '110', '1');
INSERT INTO `user` VALUES (11, 'superAdmin', 'superAdmin', '123456', '0', '110', '0');
INSERT INTO `user` VALUES (20, '1231', '123', '123456', '0', '110', '1');
INSERT INTO `user` VALUES (22, '123123', '木木', '123456', '1', '110', '2');
INSERT INTO `user` VALUES (23, '222', '张三', '123456', '1', '112', '2');
INSERT INTO `user` VALUES (24, '123', '木木', '123456', '0', '123', '1');
INSERT INTO `user` VALUES (25, '测试', '测试', '123456', '0', '123', '0');
INSERT INTO `user` VALUES (26, '测试用', '测试测试', '123456', '0', '12', '1');
INSERT INTO `user` VALUES (28, NULL, NULL, '123456', NULL, NULL, '1');
INSERT INTO `user` VALUES (29, '1111111', '123', '123456', '0', '123', '0');
INSERT INTO `user` VALUES (30, '123123123', '123', '123456', '0', '123', '1');
INSERT INTO `user` VALUES (32, '34', '123', '123123', '0', '123', '1');

-- ----------------------------
-- Table structure for user_team
-- ----------------------------
DROP TABLE IF EXISTS `user_team`;
CREATE TABLE `user_team`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uid` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `sid` int(11) NULL DEFAULT NULL COMMENT '社团id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_team
-- ----------------------------
INSERT INTO `user_team` VALUES (1, 3, 1);
INSERT INTO `user_team` VALUES (3, 30, 1);
INSERT INTO `user_team` VALUES (4, 30, 3);
INSERT INTO `user_team` VALUES (5, 30, 4);
INSERT INTO `user_team` VALUES (6, 3, 4);
INSERT INTO `user_team` VALUES (7, 3, 5);

SET FOREIGN_KEY_CHECKS = 1;
