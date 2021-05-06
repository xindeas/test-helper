-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        8.0.22 - MySQL Community Server - GPL
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 test-helper 的数据库结构
CREATE DATABASE IF NOT EXISTS `test-helper` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `test-helper`;

-- 导出  表 test-helper.tb_defect 结构
CREATE TABLE IF NOT EXISTS `tb_defect` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `project_id` bigint NOT NULL COMMENT '项目ID',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `defect_no` varchar(50) NOT NULL COMMENT '缺陷编号',
  `remark` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '详细描述',
  `target_ver` varchar(50) NOT NULL COMMENT '目标版本',
  `module_id` bigint NOT NULL COMMENT '所属模块ID',
  `assign_to` bigint NOT NULL COMMENT '指派给',
  `find_by` bigint NOT NULL COMMENT '发现人',
  `test_by` bigint NOT NULL COMMENT '跟踪测试人',
  `status` enum('NEW','SOLVING','SOLVED','WAITE_PUB','CLOSED','REOPEN','WAITE_CONFIRM') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NEW' COMMENT '状态(NEW:新建;)',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(50) NOT NULL COMMENT '创建人',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `modify_by` varchar(50) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='缺陷';

-- 正在导出表  test-helper.tb_defect 的数据：~3 rows (大约)
DELETE FROM `tb_defect`;
/*!40000 ALTER TABLE `tb_defect` DISABLE KEYS */;
INSERT INTO `tb_defect` (`id`, `project_id`, `title`, `defect_no`, `remark`, `target_ver`, `module_id`, `assign_to`, `find_by`, `test_by`, `status`, `create_date`, `create_by`, `modify_date`, `modify_by`) VALUES
	(1, 12, '测试缺陷', '', '测试', '3.0.0', 1, 2, 1, 1, 'NEW', '2021-01-08 03:25:54', 'admin', '2021-01-08 03:25:54', 'admin'),
	(2, 12, '测试2', 'df8359eda995a445b08557271a5cfcae5f', '测试', '3.0.0', 1, 2, 1, 1, 'NEW', '2021-01-08 05:17:50', 'admin', '2021-01-08 05:17:50', 'admin'),
	(3, 12, '测试3', 'DF532542434', '测试', '3.0.0', 1, 2, 1, 1, 'NEW', '2021-01-08 05:21:01', 'admin', '2021-01-08 05:21:01', 'admin'),
	(4, 12, '测试4', 'DF7627202101081301595', '测试最大长度测试最大长度测试最大长度测试最大长度测试最大长度测试最大长度测试最大长度测试最大长度测试最大长度测试最大长度测试最大长度测试最大长度测试最大长度测试最大长度测试最大长度测试最大长度测试最大长度测试最大长度测试最大长度测试最大长度测试最大长度测试最大长度测试最大长度测试最大长度测试最大长度测试最大长度测试最大长度测试最大长度测试最大长度测试最大长度测试最大长度测试最大长度', '3.0.0', 1, 2, 1, 1, 'NEW', '2021-01-08 05:25:59', 'admin', '2021-01-08 05:25:59', 'admin');
/*!40000 ALTER TABLE `tb_defect` ENABLE KEYS */;

-- 导出  表 test-helper.tb_defect_comment 结构
CREATE TABLE IF NOT EXISTS `tb_defect_comment` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `defect_id` bigint NOT NULL COMMENT '缺陷ID',
  `user_id` bigint NOT NULL COMMENT '评论用户',
  `react_comment_id` bigint DEFAULT NULL COMMENT '回复的评论ID',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论内容',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `modify_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `defect_id` (`defect_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='缺陷评论';

-- 正在导出表  test-helper.tb_defect_comment 的数据：~2 rows (大约)
DELETE FROM `tb_defect_comment`;
/*!40000 ALTER TABLE `tb_defect_comment` DISABLE KEYS */;
INSERT INTO `tb_defect_comment` (`id`, `defect_id`, `user_id`, `react_comment_id`, `remark`, `create_date`, `create_by`, `modify_date`, `modify_by`) VALUES
	(1, 4, 1, NULL, '123', '2021-01-19 02:32:29', 'admin', '2021-01-19 02:32:29', 'admin'),
	(2, 4, 1, NULL, '测试', '2021-01-19 03:35:07', 'admin', '2021-01-19 03:35:07', 'admin'),
	(3, 4, 1, NULL, 'hhh', '2021-05-06 05:58:04', 'admin', '2021-05-06 05:58:04', 'admin');
/*!40000 ALTER TABLE `tb_defect_comment` ENABLE KEYS */;

-- 导出  表 test-helper.tb_defect_comment_at 结构
CREATE TABLE IF NOT EXISTS `tb_defect_comment_at` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `comment_id` bigint NOT NULL COMMENT '评论ID',
  `user_id` bigint NOT NULL COMMENT '评论用户ID',
  `refer_user_id` bigint NOT NULL COMMENT '被@的用户ID',
  `read_flag` tinyint NOT NULL DEFAULT '0' COMMENT '是否已读',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论@';

-- 正在导出表  test-helper.tb_defect_comment_at 的数据：~0 rows (大约)
DELETE FROM `tb_defect_comment_at`;
/*!40000 ALTER TABLE `tb_defect_comment_at` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_defect_comment_at` ENABLE KEYS */;

-- 导出  表 test-helper.tb_log 结构
CREATE TABLE IF NOT EXISTS `tb_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `target_tb` varchar(50) NOT NULL COMMENT '操作表',
  `target_id` bigint NOT NULL COMMENT '操作表ID',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '操作描述',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作人',
  `create_date` datetime NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`),
  KEY `target_id` (`target_tb`,`target_id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='操作记录';

-- 正在导出表  test-helper.tb_log 的数据：~48 rows (大约)
DELETE FROM `tb_log`;
/*!40000 ALTER TABLE `tb_log` DISABLE KEYS */;
INSERT INTO `tb_log` (`id`, `target_tb`, `target_id`, `remark`, `create_by`, `create_date`) VALUES
	(1, 'tb_project', 3, '修改了项目名称：从123修改为测试名称；', 'admin', '2020-12-16 05:47:14'),
	(2, 'tb_project', 3, '修改了项目名称：从【测试名称】修改为【123】；', 'admin', '2020-12-16 05:54:19'),
	(3, 'tb_project', 2, '修改了项目名称：从【测试项目w】修改为【测试项目ww】；', 'admin', '2020-12-16 05:59:53'),
	(4, 'tb_project', 2, '修改了项目名称：从【dasd】修改为空；修改了所属用户：从【1】修改为空；修改了启用状态：从【true】修改为【false】；', 'admin', '2020-12-16 07:17:39'),
	(5, 'tb_project', 2, '修改了启用状态：从【true】修改为【false】；', 'admin', '2020-12-16 07:21:08'),
	(6, 'tb_project', 1, '修改了启用状态：从【true】修改为【false】；', 'admin', '2020-12-16 07:21:30'),
	(7, 'tb_project', 2, '修改了启用状态：从【启用】修改为【禁用】；', 'admin', '2020-12-16 07:37:19'),
	(8, 'tb_project', 2, '/n/n/n修改了启用状态：从【禁用】修改为【启用】；/n', 'admin', '2020-12-16 07:40:59'),
	(9, 'tb_project', 2, '修改了启用状态：从【启用】修改为【禁用】；', 'admin', '2020-12-16 08:16:36'),
	(10, 'tb_project', 2, '修改了启用状态：从【禁用】修改为【启用】；', 'admin', '2020-12-16 08:53:07'),
	(11, 'tb_project', 4, '创建一条新纪录', 'admin', '2020-12-22 01:35:08'),
	(12, 'tb_project', 5, '创建一条新纪录', 'admin', '2020-12-22 01:35:34'),
	(13, 'tb_project', 4, '修改了启用状态：从【启用】修改为【禁用】；', 'admin', '2020-12-22 01:35:54'),
	(14, 'tb_project', 5, '修改了启用状态：从【启用】修改为【禁用】；', 'admin', '2020-12-22 01:35:54'),
	(15, 'tb_project', 6, '创建一条新纪录', 'admin', '2020-12-22 02:04:01'),
	(16, 'tb_project', 7, '创建一条新纪录', 'admin', '2020-12-22 02:04:40'),
	(17, 'tb_project', 8, '创建一条新纪录', 'admin', '2020-12-22 02:04:53'),
	(18, 'tb_project', 9, '创建一条新纪录', 'admin', '2020-12-22 02:05:32'),
	(19, 'tb_project', 10, '创建一条新纪录', 'admin', '2020-12-22 02:11:18'),
	(20, 'tb_project', 11, '创建一条新纪录', 'admin', '2020-12-22 02:14:00'),
	(21, 'tb_project', 11, '添加新版本0.0.0', 'admin', '2020-12-22 02:14:00'),
	(22, 'tb_project', 12, '创建一条新纪录', 'admin', '2020-12-22 02:15:59'),
	(23, 'tb_project', 12, '添加新版本0.0.0', 'admin', '2020-12-22 02:16:00'),
	(24, 'tb_project', 13, '创建一条新纪录', 'admin', '2020-12-22 03:32:54'),
	(25, 'tb_project', 13, '添加新版本0.0.0', 'admin', '2020-12-22 03:32:54'),
	(26, 'tb_project', 13, '添加新版本1.0.0', 'admin', '2020-12-28 07:01:01'),
	(27, 'tb_project', 13, '修改了当前版本：从【0.0.0】修改为【1.0.0】；', 'admin', '2020-12-28 07:22:28'),
	(28, 'tb_project', 13, '添加新版本2.0.0', 'admin', '2020-12-28 07:32:21'),
	(29, 'tb_project', 13, '添加新版本3.0.0', 'admin', '2020-12-28 07:34:14'),
	(30, 'tb_project', 13, '添加新版本4.0.0', 'admin', '2020-12-28 07:34:37'),
	(31, 'tb_project', 13, '修改了当前版本：从【1.0.0】修改为【4.0.0】；', 'admin', '2020-12-28 07:34:43'),
	(32, 'tb_project', 13, '添加新版本5.0.0', 'admin', '2020-12-28 07:42:43'),
	(33, 'tb_project', 13, '修改了当前版本：从【4.0.0】修改为【5.0.0】；', 'admin', '2020-12-28 07:42:48'),
	(34, 'tb_project', 13, '添加新版本6.0.0', 'admin', '2020-12-28 08:15:47'),
	(35, 'tb_project', 13, '修改了当前版本：从【5.0.0】修改为【6.0.0】；', 'admin', '2020-12-28 08:15:56'),
	(36, 'tb_project', 13, '添加新版本7.0.0', 'admin', '2020-12-28 08:47:30'),
	(37, 'tb_project', 13, '修改了当前版本：从【6.0.0】修改为【7.0.0】；', 'admin', '2020-12-28 08:47:44'),
	(38, 'tb_project', 13, '修改了版本说明：从【第七版】修改为【第七版233】；', 'admin', '2020-12-29 01:22:48'),
	(39, 'tb_project', 13, '修改了版本说明：从【第七版233】修改为【第七版】；', 'admin', '2020-12-29 01:23:42'),
	(40, 'tb_project', 13, '修改了版本号：从【7.0.0】修改为【7.0.1】；', 'admin', '2020-12-29 03:12:14'),
	(41, 'tb_project', 13, '修改了版本号：从【7.0.1】修改为【7.0.0】；', 'admin', '2020-12-29 03:12:40'),
	(42, 'tb_project', 13, '修改了版本号：从【7.0.0】修改为【7.0.1】；', 'admin', '2020-12-29 03:12:46'),
	(43, 'tb_project', 13, '修改了版本号：从【7.0.1】修改为【7.0.0】；', 'admin', '2020-12-29 03:12:55'),
	(44, 'tb_project', 13, '修改了版本号：从【7.0.0】修改为【7.0.1】；', 'admin', '2020-12-29 03:13:39'),
	(45, 'tb_project', 13, '修改了版本号：从【7.0.1】修改为【7.0.2】；', 'admin', '2020-12-29 03:15:21'),
	(46, 'tb_project', 13, '修改了当前版本：从【7.0.2】修改为【5.0.0】；', 'admin', '2020-12-29 07:58:50'),
	(47, 'tb_project', 13, '修改了版本说明：从【第七版】修改为【第七版233】；', 'admin', '2020-12-29 08:06:44'),
	(48, 'tb_project', 13, '修改了版本说明：从【第七版233】修改为【第七版】；', 'admin', '2020-12-29 08:10:11'),
	(49, 'tb_project', 13, '修改了版本说明：从【第七版】修改为【第七版233】；', 'admin', '2020-12-29 08:10:41'),
	(50, 'tb_project', 13, '修改7.0.2版本;修改了版本说明：从【第七版233】修改为【第七版】；', 'admin', '2020-12-29 08:12:03'),
	(51, 'tb_project', 13, '修改了当前版本：从【5.0.0】修改为【7.0.2】；', 'admin', '2020-12-29 08:12:05'),
	(52, 'tb_project', 13, '修改了启用状态：从【启用】修改为【禁用】；', 'admin', '2020-12-29 08:43:12'),
	(53, 'tb_project', 13, '修改了启用状态：从【禁用】修改为【启用】；', 'admin', '2020-12-29 08:50:06'),
	(54, 'tb_project', 13, '添加新版本7.0.1', 'admin', '2020-12-30 01:35:57'),
	(55, 'tb_project', 12, '修改了当前版本：从【0.0.0】修改为【2.0.0】；', 'admin', '2020-12-31 07:05:33'),
	(56, 'tb_project_module', 1, '创建一条新纪录', 'admin', '2021-01-08 02:49:59'),
	(57, 'tb_defect', 1, '创建一条新纪录', 'admin', '2021-01-08 03:25:54'),
	(58, 'tb_defect', 2, '创建一条新纪录', 'admin', '2021-01-08 05:17:50'),
	(59, 'tb_defect', 3, '创建一条新纪录', 'admin', '2021-01-08 05:21:01'),
	(60, 'tb_defect', 4, '创建一条新纪录', 'admin', '2021-01-08 05:25:59'),
	(61, 'tb_defect', 4, '系统管理员评论说：123', 'admin', '2021-01-19 02:32:29'),
	(62, 'tb_defect', 4, '系统管理员评论说：测试', 'admin', '2021-01-19 03:35:07'),
	(63, 'tb_project', 13, '修改了当前版本：从【7.0.2】修改为【7.0.1】；', 'admin', '2021-05-06 05:24:22'),
	(64, 'tb_project', 13, '修改了当前版本：从【7.0.1】修改为【7.0.2】；', 'admin', '2021-05-06 05:24:27'),
	(65, 'tb_defect', 4, '系统管理员评论说：hhh', 'admin', '2021-05-06 05:58:04');
/*!40000 ALTER TABLE `tb_log` ENABLE KEYS */;

-- 导出  表 test-helper.tb_project 结构
CREATE TABLE IF NOT EXISTS `tb_project` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `name` varchar(50) NOT NULL COMMENT '项目名称',
  `belongs_to` bigint NOT NULL COMMENT '所属用户',
  `version_no` varchar(50) NOT NULL COMMENT '当前版本',
  `enabled` tinyint(1) NOT NULL COMMENT '启用状态',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改人',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `belongs_to` (`belongs_to`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='项目表';

-- 正在导出表  test-helper.tb_project 的数据：~1 rows (大约)
DELETE FROM `tb_project`;
/*!40000 ALTER TABLE `tb_project` DISABLE KEYS */;
INSERT INTO `tb_project` (`id`, `name`, `belongs_to`, `version_no`, `enabled`, `create_by`, `create_date`, `modify_by`, `modify_date`) VALUES
	(12, '测试2', 1, '2.0.0', 1, 'admin', '2020-12-22 02:15:59', 'admin', '2020-12-31 07:05:33'),
	(13, '测试23', 1, '7.0.2', 1, 'admin', '2020-12-22 03:32:54', 'admin', '2021-05-06 05:24:27');
/*!40000 ALTER TABLE `tb_project` ENABLE KEYS */;

-- 导出  表 test-helper.tb_project_auth 结构
CREATE TABLE IF NOT EXISTS `tb_project_auth` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `project_id` bigint NOT NULL COMMENT '项目ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='项目权限';

-- 正在导出表  test-helper.tb_project_auth 的数据：~0 rows (大约)
DELETE FROM `tb_project_auth`;
/*!40000 ALTER TABLE `tb_project_auth` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_project_auth` ENABLE KEYS */;

-- 导出  表 test-helper.tb_project_module 结构
CREATE TABLE IF NOT EXISTS `tb_project_module` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `project_id` bigint NOT NULL COMMENT '项目ID',
  `module_name` varchar(50) NOT NULL COMMENT '模块名',
  `create_by` varchar(50) NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_by` varchar(50) NOT NULL COMMENT '修改人',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='模块';

-- 正在导出表  test-helper.tb_project_module 的数据：~0 rows (大约)
DELETE FROM `tb_project_module`;
/*!40000 ALTER TABLE `tb_project_module` DISABLE KEYS */;
INSERT INTO `tb_project_module` (`id`, `project_id`, `module_name`, `create_by`, `create_date`, `modify_by`, `modify_date`) VALUES
	(1, 12, '测试模块1', 'admin', '2021-01-08 02:49:59', 'admin', '2021-01-08 02:49:59');
/*!40000 ALTER TABLE `tb_project_module` ENABLE KEYS */;

-- 导出  表 test-helper.tb_project_version 结构
CREATE TABLE IF NOT EXISTS `tb_project_version` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `project_id` bigint NOT NULL COMMENT '项目ID',
  `version_no` varchar(50) NOT NULL COMMENT '版本号',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '版本说明',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(50) NOT NULL COMMENT '创建人',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `modify_by` varchar(50) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `project_id` (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='版本记录';

-- 正在导出表  test-helper.tb_project_version 的数据：~12 rows (大约)
DELETE FROM `tb_project_version`;
/*!40000 ALTER TABLE `tb_project_version` DISABLE KEYS */;
INSERT INTO `tb_project_version` (`id`, `project_id`, `version_no`, `remark`, `create_date`, `create_by`, `modify_date`, `modify_by`) VALUES
	(3, 12, '0.0.0', '初版', '2020-12-22 02:16:00', 'admin', '2020-12-22 02:16:00', 'admin'),
	(4, 12, '1.0.0', '初版', '2020-12-22 02:16:00', 'admin', '2020-12-22 02:16:00', 'admin'),
	(5, 12, '2.0.0', '初版', '2020-12-22 02:16:00', 'admin', '2020-12-22 02:16:00', 'admin'),
	(6, 12, '3.0.0', '初版', '2020-12-22 02:16:00', 'admin', '2020-12-22 02:16:00', 'admin'),
	(10, 13, '0.0.0', '初版', '2020-12-22 03:32:54', 'admin', '2020-12-22 03:32:54', 'admin'),
	(11, 13, '1.0.0', '第一版', '2020-12-28 07:01:01', 'admin', '2020-12-28 07:01:01', 'admin'),
	(12, 13, '2.0.0', '第二版', '2020-12-28 07:32:21', 'admin', '2020-12-28 07:32:21', 'admin'),
	(13, 13, '3.0.0', '第3版', '2020-12-28 07:34:14', 'admin', '2020-12-28 07:34:14', 'admin'),
	(14, 13, '4.0.0', '第4版', '2020-12-28 07:34:37', 'admin', '2020-12-28 07:34:37', 'admin'),
	(15, 13, '5.0.0', '第五版', '2020-12-28 07:42:43', 'admin', '2020-12-28 07:42:43', 'admin'),
	(17, 13, '7.0.2', '第七版', '2020-12-28 08:47:30', 'admin', '2020-12-29 08:12:03', 'admin'),
	(18, 13, '7.0.1', '第七版', '2020-12-30 01:35:57', 'admin', '2020-12-30 01:35:57', 'admin');
/*!40000 ALTER TABLE `tb_project_version` ENABLE KEYS */;

-- 导出  表 test-helper.tb_user 结构
CREATE TABLE IF NOT EXISTS `tb_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `login` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录账号',
  `pwd` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码(base64加密)',
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '手机',
  `email` varchar(50) NOT NULL DEFAULT '' COMMENT '邮箱',
  `avatar` varchar(250) NOT NULL DEFAULT '' COMMENT '头像地址',
  `role` varchar(20) NOT NULL COMMENT '角色，可选值（SYS_ADMIN系统管理员，PRJ_ADMIN项目管理员，MEMBER普通用户）',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `modify_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- 正在导出表  test-helper.tb_user 的数据：~1 rows (大约)
DELETE FROM `tb_user`;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` (`id`, `name`, `login`, `pwd`, `mobile`, `email`, `avatar`, `role`, `create_date`, `create_by`, `modify_date`, `modify_by`) VALUES
	(1, '系统管理员', 'admin', 'YWRtaW4=', '17350952652', '', 'http://cdn.duitang.com/uploads/item/201509/29/20150929195250_5VZzL.gif', 'SYS_ADMIN', '2020-11-03 16:36:39', 'admin', '2020-11-03 16:36:44', 'admin'),
	(2, '测试1', 'test', 'YWRtaW4=', '17350952652', '', 'https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1091405991,859863778&fm=26&gp=0.jpg', 'SYS_ADMIN', '2020-12-17 10:55:44', 'admin', '2020-12-17 10:55:45', 'admin');
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
