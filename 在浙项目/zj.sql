-- MySQL dump 10.13  Distrib 5.7.22, for macos10.13 (x86_64)
--
-- Host: 127.0.0.1    Database: zj_app
-- ------------------------------------------------------
-- Server version	5.7.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_article`
--

DROP TABLE IF EXISTS `t_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_article` (
  `a_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章ID(自增)',
  `a_title` varchar(50) DEFAULT NULL COMMENT '50字以内的文章标题',
  `a_city_id` int(11) NOT NULL COMMENT '市ID',
  `a_cover_img` varchar(500) DEFAULT NULL COMMENT '封面图片',
  `a_author` varchar(30) DEFAULT NULL COMMENT '作者用户名',
  `a_content` text COMMENT '文章内容',
  `a_status` tinyint(4) DEFAULT NULL COMMENT '审核状态',
  `deleted` int(1) DEFAULT NULL COMMENT '逻辑删除',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`a_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='文章表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_article`
--

LOCK TABLES `t_article` WRITE;
/*!40000 ALTER TABLE `t_article` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_character`
--

DROP TABLE IF EXISTS `t_character`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_character` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '红色任务ID(自增)',
  `c_city_id` int(11) DEFAULT NULL COMMENT '城市ID',
  `c_deeds_synopsis` varchar(500) DEFAULT NULL COMMENT '事迹简介',
  `c_deeds_detail` text COMMENT '事迹详情',
  `c_img` varchar(500) DEFAULT NULL COMMENT '人物形象图',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='红色人物表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_character`
--

LOCK TABLES `t_character` WRITE;
/*!40000 ALTER TABLE `t_character` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_character` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_city`
--

DROP TABLE IF EXISTS `t_city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_city` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '市ID(自增)',
  `c_name` varchar(20) DEFAULT NULL COMMENT '市名称',
  `c_header_introduce` text COMMENT '市的百年发展头部信息介绍',
  `c_introduce_detail` text COMMENT '市的详情介绍',
  `c_shape_img` varchar(500) DEFAULT NULL COMMENT '市形状图',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='市级表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_city`
--

LOCK TABLES `t_city` WRITE;
/*!40000 ALTER TABLE `t_city` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_leave_message`
--

DROP TABLE IF EXISTS `t_leave_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_leave_message` (
  `m_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '留言id',
  `m_user_id` bigint(20) DEFAULT NULL COMMENT '留言人ID',
  `m_content` varchar(200) DEFAULT NULL COMMENT '200字内留言',
  `m_article_id` int(11) DEFAULT NULL COMMENT '文章ID',
  `deleted` int(1) DEFAULT '0' COMMENT '逻辑删除',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='留言表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_leave_message`
--

LOCK TABLES `t_leave_message` WRITE;
/*!40000 ALTER TABLE `t_leave_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_leave_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_permission`
--

DROP TABLE IF EXISTS `t_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_permission` (
  `p_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限ID(自增)',
  `p_name` varchar(20) DEFAULT NULL COMMENT '权限名称',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_permission`
--

LOCK TABLES `t_permission` WRITE;
/*!40000 ALTER TABLE `t_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_to_permission`
--

DROP TABLE IF EXISTS `t_role_to_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role_to_permission` (
  `rp_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色权限ID(自增)',
  `r_id` int(11) NOT NULL COMMENT '角色ID',
  `p_id` int(11) DEFAULT NULL COMMENT '权限ID',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`rp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='用户权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_to_permission`
--

LOCK TABLES `t_role_to_permission` WRITE;
/*!40000 ALTER TABLE `t_role_to_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_role_to_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_roles`
--

DROP TABLE IF EXISTS `t_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_roles` (
  `r_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID(自增)',
  `r_name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_roles`
--

LOCK TABLES `t_roles` WRITE;
/*!40000 ALTER TABLE `t_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_users`
--

DROP TABLE IF EXISTS `t_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_users` (
  `u_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID(使用雪花算法)',
  `u_username` varchar(30) DEFAULT NULL COMMENT '用户名',
  `u_email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `u_password` varchar(500) DEFAULT NULL COMMENT '密码',
  `u_sex` tinyint(4) DEFAULT NULL COMMENT '性别',
  `u_age` int(11) DEFAULT NULL COMMENT '年龄',
  `u_telephone` varchar(11) DEFAULT NULL COMMENT '电话号码',
  `u_role_id` int(2) DEFAULT NULL COMMENT '角色ID',
  `deleted` int(1) DEFAULT '0' COMMENT '逻辑删除',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_users`
--

LOCK TABLES `t_users` WRITE;
/*!40000 ALTER TABLE `t_users` DISABLE KEYS */;
INSERT INTO `t_users` VALUES (1,'admin','admin@email.com','admin',0,100,'11111111111',1,0,NULL,NULL,0);
/*!40000 ALTER TABLE `t_users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-14 11:59:53
