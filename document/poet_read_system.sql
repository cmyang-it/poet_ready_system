/*
 Navicat Premium Data Transfer

 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Schema         : poet_read_system

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for prs_author_novel
-- ----------------------------
DROP TABLE IF EXISTS `prs_author_novel`;
CREATE TABLE `prs_author_novel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `novel_id` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '默认0：有效；-1：无效',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `user_id` (`user_id`) USING BTREE,
  UNIQUE KEY `novel_id` (`novel_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for prs_chapter
-- ----------------------------
DROP TABLE IF EXISTS `prs_chapter`;
CREATE TABLE `prs_chapter` (
  `chapter_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `novel_id` bigint(20) NOT NULL COMMENT '小说id',
  `chapter_name` varchar(500) DEFAULT NULL COMMENT '章节名',
  `chapter_content` mediumtext COMMENT '章节内容',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`chapter_id`) USING BTREE,
  UNIQUE KEY `chapter_id` (`chapter_id`) USING BTREE,
  KEY `novel_id` (`novel_id`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  CONSTRAINT `novel_id` FOREIGN KEY (`novel_id`) REFERENCES `prs_novel` (`novel_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for prs_check_user
-- ----------------------------
DROP TABLE IF EXISTS `prs_check_user`;
CREATE TABLE `prs_check_user` (
  `check_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `purpose` int(11) NOT NULL DEFAULT '0' COMMENT '默认0：成为作者；1：成为管理员',
  `check_status` int(11) NOT NULL DEFAULT '0' COMMENT '默认0:待审核；1：审核通过；-1：审核不通过',
  `check_time` datetime DEFAULT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`check_id`) USING BTREE,
  UNIQUE KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for prs_comment_info
-- ----------------------------
DROP TABLE IF EXISTS `prs_comment_info`;
CREATE TABLE `prs_comment_info` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `novel_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `user_comment_info` varchar(2000) NOT NULL,
  `user_comment_time` datetime NOT NULL,
  `author_reply` varchar(2000) DEFAULT NULL,
  `author_reply_time` datetime DEFAULT NULL,
  `check_status` int(11) NOT NULL DEFAULT '1' COMMENT '默认1：审核通过；-1：审核不通过',
  `check_info` varchar(1000) DEFAULT NULL,
  `check_time` datetime DEFAULT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`comment_id`) USING BTREE,
  KEY `novel_id` (`novel_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for prs_crawl_novel
-- ----------------------------
DROP TABLE IF EXISTS `prs_crawl_novel`;
CREATE TABLE `prs_crawl_novel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `novel_name` varchar(100) DEFAULT NULL,
  `novel_author` varchar(100) DEFAULT NULL,
  `dd_url` varchar(500) NOT NULL,
  `dd_chapter_url` varchar(500) NOT NULL,
  `fl_url` varchar(500) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `id` (`id`) USING BTREE,
  KEY `status` (`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for prs_novel
-- ----------------------------
DROP TABLE IF EXISTS `prs_novel`;
CREATE TABLE `prs_novel` (
  `novel_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `novel_img` varchar(500) DEFAULT NULL COMMENT '图书封面',
  `novel_name` varchar(100) NOT NULL COMMENT '名称',
  `novel_type` int(11) NOT NULL COMMENT '小说类型',
  `novel_author` varchar(50) NOT NULL COMMENT '作者',
  `novel_info` varchar(1000) DEFAULT NULL COMMENT '简介',
  `keyword` varchar(500) DEFAULT NULL COMMENT '关键字',
  `last_chapter` varchar(100) DEFAULT NULL COMMENT '最后章节',
  `last_chapter_update` datetime DEFAULT NULL COMMENT '最后章节更新时间',
  `novel_words` int(11) NOT NULL DEFAULT '0' COMMENT '字数',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '1：连载，-1，完结',
  `check_status` int(11) NOT NULL DEFAULT '1' COMMENT '默认1，正常；-1和谐',
  `check_info` varchar(1000) DEFAULT NULL COMMENT '操作理由',
  `check_time` datetime DEFAULT NULL COMMENT '操作时间',
  `extends_info` text,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`novel_id`) USING BTREE,
  UNIQUE KEY `novel_id` (`novel_id`) USING BTREE,
  UNIQUE KEY `novel_name` (`novel_name`) USING BTREE,
  UNIQUE KEY `novel_author` (`novel_author`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for prs_novel_ext_info
-- ----------------------------
DROP TABLE IF EXISTS `prs_novel_ext_info`;
CREATE TABLE `prs_novel_ext_info` (
  `novel_ext_id` int(11) NOT NULL AUTO_INCREMENT,
  `novel_id` int(11) NOT NULL,
  `novel_recommend` int(11) NOT NULL DEFAULT '0' COMMENT '推荐',
  `novel_collect` int(11) NOT NULL DEFAULT '0' COMMENT '收藏',
  `novel_click` int(11) NOT NULL DEFAULT '0' COMMENT '点击',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`novel_ext_id`) USING BTREE,
  UNIQUE KEY `novel_id` (`novel_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for prs_novel_type
-- ----------------------------
DROP TABLE IF EXISTS `prs_novel_type`;
CREATE TABLE `prs_novel_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '默认0：有效；-1：无效',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for prs_user
-- ----------------------------
DROP TABLE IF EXISTS `prs_user`;
CREATE TABLE `prs_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `head_image_url` varchar(1000) DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `phone_number` varchar(50) NOT NULL,
  `role` int(11) NOT NULL DEFAULT '1' COMMENT '1 读者  ；10 作者 ；100 管理员',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0有效；-1；无效',
  `recommend_times` int(11) NOT NULL DEFAULT '3',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `apply_author_status` int(11) DEFAULT '0',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE KEY `phone_number` (`phone_number`) USING BTREE,
  UNIQUE KEY `account` (`account`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for prs_user_novel
-- ----------------------------
DROP TABLE IF EXISTS `prs_user_novel`;
CREATE TABLE `prs_user_novel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `novel_id` int(11) NOT NULL,
  `chapter_id` int(11) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '默认0：有效；-1：无效',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `chapter_id` (`chapter_id`) USING BTREE,
  KEY `novel_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
