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

-- 导出  表 test-helper.tb_project 结构
CREATE TABLE IF NOT EXISTS `tb_project` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `name` varchar(50) NOT NULL COMMENT '项目名称',
  `belongs_to` bigint NOT NULL COMMENT '所属用户',
  `enabled` tinyint(1) NOT NULL COMMENT '启用状态',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改人',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='项目表';

-- 正在导出表  test-helper.tb_project 的数据：~2 rows (大约)
DELETE FROM `tb_project`;
/*!40000 ALTER TABLE `tb_project` DISABLE KEYS */;
INSERT INTO `tb_project` (`id`, `name`, `belongs_to`, `enabled`, `create_by`, `create_date`, `modify_by`, `modify_date`) VALUES
	(1, '测试项目', 1, 0, 'admin', '2020-11-23 14:54:32', 'admin', '2020-11-23 14:54:35'),
	(2, '测试项目w', 1, 1, 'admin', '2020-11-23 14:54:32', 'admin', '2020-11-23 14:54:35');
/*!40000 ALTER TABLE `tb_project` ENABLE KEYS */;

-- 导出  表 test-helper.tb_project_auth 结构
CREATE TABLE IF NOT EXISTS `tb_project_auth` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `project_id` bigint NOT NULL COMMENT '项目ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='项目权限';

-- 正在导出表  test-helper.tb_project_auth 的数据：~0 rows (大约)
DELETE FROM `tb_project_auth`;
/*!40000 ALTER TABLE `tb_project_auth` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_project_auth` ENABLE KEYS */;

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- 正在导出表  test-helper.tb_user 的数据：~1 rows (大约)
DELETE FROM `tb_user`;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` (`id`, `name`, `login`, `pwd`, `mobile`, `email`, `avatar`, `role`, `create_date`, `create_by`, `modify_date`, `modify_by`) VALUES
	(1, '系统管理员', 'admin', 'YWRtaW4=', '17350952652', '', 'https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1091405991,859863778&fm=26&gp=0.jpg', '', '2020-11-03 16:36:39', 'admin', '2020-11-03 16:36:44', 'admin');
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
