/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.5.20 : Database - manage_database
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`manage_database` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `manage_database`;

/*Table structure for table `persistent_logins` */

DROP TABLE IF EXISTS `persistent_logins`;

CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `persistent_logins` */

/*Table structure for table `t_account` */

DROP TABLE IF EXISTS `t_account`;

CREATE TABLE `t_account` (
  `username` char(10) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='t_account';

/*Data for the table `t_account` */

insert  into `t_account`(`username`,`password`) values ('1122334455','$2a$10$C5aakydBAEvH3y.cEn7tjOeJQcBLthjffQGlVgcRk9MO.6el/1dCq'),('2018120000','$2a$10$BvcTxxM/jyCxbhZiKgUpI.Ta0EYrt64ugo4O9ZftOrm.ptsgXw1OK'),('2018120001','$2a$10$gNBNHsSktIfDenjtUfj2serFQCcvEAlZNowTOwoDzB3JqlPYVF8YG'),('2018120002','$2a$10$oOPe6/m5qQqN4sSHTvkC2.hLdxL00YGMiJ3JvIeAkiw6bzm5/1vVG'),('2018120003','$2a$10$9tso6nk398oYfoN3Zvp1R.XCKGehLLX0I1vZLk6NJvGQ4pMjhsNUm'),('2018120004','$2a$10$AxZbVV/y04ZbwdrlYyMS2.dqEu3wEbmSrb9VtIIxaEG5e3gbqGgXG'),('2018130001','$2a$10$B19Y2BVfVKFC2HeB1b8dl.YrD6tKKlPPhGtes5L0oTeOALaK4BxAa'),('2018130002','$2a$10$V3bnF7mWECkhpcCPqT5EhebUeq0Q5XVN1oXLpZSWcJZmQpLRc4lwG'),('2018130004','$2a$10$0mJpCi8coXfPyfOBQyz5hukEkGMNvqMO7m4JPc1PNBGA//R4ChQzi'),('2018130005','$2a$10$caHE7a8.6w2GuIbPMKg55OWLzcLqfD2bS9JxxxEZQlcYM5O45Zowa'),('O123455432','$2a$10$1Ks90X.FiPHakvMI1THh7uTWjNnsbG6hpTSZ8s3Xi754cU0NHE9r6'),('O123456789','$2a$10$qPb0AieeEXn5naa8Nu3qluH6ImLgIMwVAe4MURCUCGxV30TyBnNPu'),('O135792468','$2a$10$n2laZF1f7/AT3G4uJKSF7OW0GgzCI6AHhW5RI/9/V/vpZldIUz7E6'),('O246813579','$2a$10$6vy70bIEWzAWmju6tsOpUusAZGMzCEYzqolBwLvY26LjuywETIX.2'),('O987654321','$2a$10$1tr6I4AyHlA.dPbBcdMQWe5k7pPPWOOIgyMcmGsMYMA4TF.LvmZW.');

/*Table structure for table `t_account_auth` */

DROP TABLE IF EXISTS `t_account_auth`;

CREATE TABLE `t_account_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` char(10) DEFAULT NULL,
  `auth_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_FK_T_ACCOUNT_ACCOUNT_AUTH_AUTH` (`account_id`),
  KEY `FK_FK_T_ACCOUNT_AUTH_AUTH` (`auth_id`),
  CONSTRAINT `FK_FK_T_ACCOUNT_ACCOUNT_AUTH_AUTH` FOREIGN KEY (`account_id`) REFERENCES `t_account` (`username`) ON DELETE CASCADE,
  CONSTRAINT `FK_FK_T_ACCOUNT_AUTH_AUTH` FOREIGN KEY (`auth_id`) REFERENCES `t_auth` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='t_account_auth';

/*Data for the table `t_account_auth` */

insert  into `t_account_auth`(`id`,`account_id`,`auth_id`) values (6,'O123456789',5),(8,'1122334455',1),(9,'2018120000',2),(10,'O987654321',5),(11,'O123455432',5),(12,'2018120001',2),(13,'2018120002',2),(14,'2018120003',2),(15,'2018120004',2),(16,'2018130001',2),(17,'2018130002',2),(18,'2018130004',2),(19,'2018130005',2),(20,'O135792468',5),(21,'O246813579',5);

/*Table structure for table `t_auth` */

DROP TABLE IF EXISTS `t_auth`;

CREATE TABLE `t_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(10) NOT NULL,
  `role_describe` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='t_auth';

/*Data for the table `t_auth` */

insert  into `t_auth`(`id`,`role_name`,`role_describe`) values (1,'ADMIN','系统管理员'),(2,'STUDENT','学生'),(3,'TEACHER','老师'),(4,'I_MERCHANT','内部商家'),(5,'O_MERCHANT','外部商家');

/*Table structure for table `t_bank_card` */

DROP TABLE IF EXISTS `t_bank_card`;

CREATE TABLE `t_bank_card` (
  `card_number` varchar(16) NOT NULL,
  `balance` int(11) DEFAULT NULL,
  `u_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`card_number`),
  KEY `FOREIGN_KEY_BANK_USER_ID` (`u_id`),
  CONSTRAINT `t_bank_card_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_bank_card` */

insert  into `t_bank_card`(`card_number`,`balance`,`u_id`) values ('6225000011111110',0,11),('6225000011111111',0,12),('6225000011111112',0,13),('6225000011111113',0,14),('6225000011111115',100000,15),('6225000011111116',100000,16),('6225112233445566',420000,2),('6225112233445567',0,7),('6225112233445568',0,8),('6225112233445569',0,9),('6225112233445766',100000,4),('6225222233445566',1000,3),('6225332233445566',81000,1),('6225432233445566',0,10);

/*Table structure for table `t_comment` */

DROP TABLE IF EXISTS `t_comment`;

CREATE TABLE `t_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(500) DEFAULT NULL,
  `releases_time` datetime DEFAULT NULL,
  `u_id` int(11) DEFAULT NULL,
  `j_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FOREIGN_KEY_TO_T_USER_ID` (`u_id`),
  KEY `FOREIGN_KEY_TO_T_JOB_ID` (`j_id`),
  CONSTRAINT `t_comment_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_comment_ibfk_2` FOREIGN KEY (`j_id`) REFERENCES `t_job` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Data for the table `t_comment` */

insert  into `t_comment`(`id`,`message`,`releases_time`,`u_id`,`j_id`) values (17,'测试','2020-05-29 15:52:38',1,7),(18,'测试2','2020-05-29 15:52:42',1,7),(19,'测试3','2020-05-29 15:52:46',1,7),(20,'测试4','2020-05-29 15:52:50',1,7),(22,'测试2','2020-05-29 15:53:17',7,7),(23,'占楼','2020-06-09 09:22:29',2,9),(24,'占楼111','2020-06-09 09:22:38',2,9);

/*Table structure for table `t_credit_grade` */

DROP TABLE IF EXISTS `t_credit_grade`;

CREATE TABLE `t_credit_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `grade_score` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FOREIGN_KEY_TO_T_JOB_ID2` (`job_id`),
  KEY `FOREIGN_KEY_TO_T_USER_ID3` (`student_id`),
  CONSTRAINT `t_credit_grade_ibfk_1` FOREIGN KEY (`job_id`) REFERENCES `t_job` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_credit_grade_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_credit_grade` */

insert  into `t_credit_grade`(`id`,`job_id`,`student_id`,`grade_score`) values (1,2,1,4),(2,4,1,3),(3,11,7,3),(4,11,1,5),(5,14,14,4);

/*Table structure for table `t_job` */

DROP TABLE IF EXISTS `t_job`;

CREATE TABLE `t_job` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_theme` varchar(30) DEFAULT NULL,
  `job_tempt` varchar(30) DEFAULT NULL,
  `job_address` varchar(70) DEFAULT NULL,
  `promulgator` varchar(10) DEFAULT NULL,
  `salary` int(11) DEFAULT NULL,
  `recruit_count` tinyint(4) DEFAULT NULL,
  `job_description` text,
  `job_requirement` text,
  `info_supplement` text,
  `current_status` tinyint(4) DEFAULT '0',
  `release_time` datetime DEFAULT NULL,
  `username` char(10) DEFAULT NULL,
  `working_time` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FOREIGN_KEY_ACCOUNT_JOB` (`username`),
  CONSTRAINT `t_job_ibfk_1` FOREIGN KEY (`username`) REFERENCES `t_account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `t_job` */

insert  into `t_job`(`id`,`job_theme`,`job_tempt`,`job_address`,`promulgator`,`salary`,`recruit_count`,`job_description`,`job_requirement`,`info_supplement`,`current_status`,`release_time`,`username`,`working_time`) values (2,'Java开发工程师','牛人团队,带薪年假,16薪考核,快速成长','北京市海淀区中关村软件园二期','张三',20000,0,'1.资金方对接开发、联调；\n2.支付路由通道接入，路由策略设计、实现；\n3.按照阿里规约进行代码优化；\n4.通过设计模式、算法重构现有系统逻辑；\n5.系统整体补偿逻辑实现；\n6.新技术调研、分享，并应用于现有系统；\n7.线上问题跟进、解决。','1.计算机等相关专业统招本科及以上学历，3年以上大型互联网公司经验；\n2.熟悉多线程及网络编程；熟悉大容量、高并发服务器的设计及优化技术；\n3.精通Java语言，了解Java主流工具及高并发高性能开源工具（netty、disruptor、logback、log4j2、guava、Apache Commons Pool等），并熟悉至少一种其它开发语言；\n4.熟悉主流开源框架，如Spring、SpringMVC、MyBatis、Activity等；熟悉Spring原理；对开源框架dubbo,zookeeper有实际应用经验；\n5.熟悉缓存技术（如Redis、memcache），在生产环境中使用过至少一种noSql产品；\n6.熟悉MySQL的使用，有SQL优化经验；\n7.熟悉MQ原理和使用（RabbitMQ、ActiveMQ、Kafka等）；\n8.自学能力强，有责任心，上进心。','无',-2,'2020-03-23 13:22:06','O123456789','早上9点到下午6点'),(3,'Java开发工程师','15薪 职业发展 领导nice','成都高新区天府大道北段966号天府国际金融中心4号楼13','张三',15000,2,'工作职责:\n1、根据产品需求,完成产品功能的模块设计,开发,测试及维护;\n2、解决重要项目中的关键架构问题和技术难题,负责项目中关键技术难点的攻关;\n3、整理业务方的需求并与产品一起完善需求.\n4、能够与架构师,部门负责人一起解,将战略转化成目标和重要事件,并制定相应的计划。','1、计算机相关专业,5年及以上Java语言为主的软件开发经验, JAVA基础扎实,深入理解JVM原理,对性能调优有丰富的经验;\n2、熟悉IO,NIO,多线程,集合等基础框架;\n3、对设计模式有深入的理解,熟练使用AOP,MVC等框架,如Spring MVC,SpringBoot,SpringCloud等;\n4、熟悉Netty、dubbo等RPC框架;\n5、熟悉数据库设计和优化,了解常用的数据库如MYSQL;有NoSQL数据库如Mongodb、Elasicsearch使用经验者优先;\n6、熟悉LINUX操作命令及SHELL脚本编写;\n7、有工控网络分析系统经验优先','暂无',-1,'2020-03-24 00:10:00','O123456789','早上9点到下午7点'),(4,'全栈工程师','大平台， 团队牛，领导好','北京朝阳区望京北京朝阳望京商业中心F座','李四',35000,2,'支持Cobo核心业务、创新业务的开发工作\n负责项目架构设计与优化\n负责前后端业务逻辑开发, 保障系统稳定\n与产品、运营等团队紧密配合，确定产品的技术解决方案和开发时间评估\n跟踪区块链领域相关技术变化，并应用于实际研发产品中','- 计算机及相关专业本科及以上学历，2年以上软件开发经验\n\n- 熟悉 Linux 操作系统开发环境\n熟练掌握 HTML、CSS、JavaScript 相关前端技术\n掌握 React 或 Vue ，有实际的前端项目开发经验\n精通python/golang/java或者其他任何一门编程语言，有相关服务端项目开发经验\n熟悉Mysql、Memcache、Redis，了解其工作原理\n基础扎实，思路清晰，具备良好的沟通能力和团队协作精神','暂时无',1,'2020-03-31 11:23:12','O987654321','早上9点到晚上10点'),(5,'全栈工程师','大平台， 团队牛，领导好','北京朝阳区望京北京朝阳望京商业中心F座','王五',20000,2,'支持Cobo核心业务、创新业务的开发工作\n负责项目架构设计与优化\n负责前后端业务逻辑开发, 保障系统稳定\n与产品、运营等团队紧密配合，确定产品的技术解决方案和开发时间评估\n跟踪区块链领域相关技术变化，并应用于实际研发产品中','- 计算机及相关专业本科及以上学历，2年以上软件开发经验\n\n- 熟悉 Linux 操作系统开发环境\n熟练掌握 HTML、CSS、JavaScript 相关前端技术\n掌握 React 或 Vue ，有实际的前端项目开发经验\n精通python/golang/java或者其他任何一门编程语言，有相关服务端项目开发经验\n熟悉Mysql、Memcache、Redis，了解其工作原理\n基础扎实，思路清晰，具备良好的沟通能力和团队协作精神','暂无',1,'2020-03-31 11:50:01','O123455432','早上9点到晚上9点'),(6,'高级java开发工程师 ','发展前景大、福利待遇好','杭州 - 萧山区 - 宁围 - 鸿宁路2268号浙江农信科技大楼','张三',20000,2,'1、负责银行各类系统（包括核心业务、信贷业务、账务及会计业务、中间业务、财富管理、支付清算等各类业务系统，数据分析系统、平台管理系统）的需求分析和方案设计；\n2、负责银行各类系统的开发实施和上线投产；\n3、负责银行各类系统的日常运维支持。','1、年龄35周岁以下；\n2、全日制本科及以上学历并取得相关学位，计算机科学与技术、软件工程等相关专业优先；\n3、4年以上Java开发工作经验，2年及以上项目管理或研发团队管理经验，具备主导银行项目开发经验者优先；\n4、具有扎实的软件开发基础知识，能熟练使用至少一种以上主流编程语言，能熟练使用开源主流框架，对程序并发执行效率有经验和把控能力；熟悉主流操作系统，深入理解设计模式；了解前端相关技术；熟悉主流数据库产品；熟练使用版本控制和构建发布工具；具有良好的编码习惯和较强的文档编写能力；\n5、具备良好的团队合作能力、沟通能力和综合分析能力，学习能力强，承压能力强，工作责任心强。','无',1,'2020-04-03 08:39:39','O123456789','早上9点到晚上9点'),(7,'Java开发工程师','周末双休，五险一金','南京 - 雨花台区 - 大周路88号软件谷科创城B1栋','张三',10000,2,'1、完成Java软件系统代码的实现，编写代码注释和开发文档；\n2、辅助进行系统的功能定义,程序设计；\n3、根据设计文档或需求说明完成代码编写，调试，测试和维护；\n4、分析并解决软件开发过程中的问题；\n5、协助测试工程师制定测试计划，定位发现的问题；\n6、配合项目经理完成相关任务目标。','1、计算机相关专业，本科及以上学历，2年以上软件开发经验；\n2、熟悉面向对象思想，精通编程，调试和相关技术；\n3、熟悉J2EE、Spring、hibernate/ibatis等主流框架；\n4、具备需求分析和系统设计能力，、以及较强的逻辑分析和独立解决问题能力；\n5、能熟练阅读中文、英文技术文档；富有团队精神,责任感和沟通能力。','暂无',1,'2020-04-26 13:57:50','O123456789','早上9点到晚上9点'),(8,'算法工程师','周末双休，五险一金','北京 - 海淀区 - 西二旗 - 西二旗','张三',30000,2,'1、负责快手AI算法的服务端平台和工具的架构设计、开发、维护和优化；\n2、参与设计和开发AI算法实验平台、质量平台、监控系统、通用工具平台；\n3、跟踪国内外相关领域的最新技术和架构。','1、计算机等相关专业211本科及以上学历，扎实的数据结构和算法基础，熟悉设计模式；\n2、服务端3-5年的开发经验，至少熟悉C++/JAVA/Python技术栈中的一种；\n3、3年以上B/S结构系统分析及设计经验，有构建可伸缩、可扩展、高可用系统经验；\n4、熟悉常用数据库（MySQL，HBase，Redis，MongoDB)原理，掌握其使用场景以及优化。\n5、有丰富的微服务架构实践经验，熟悉常用RPC框架(gRPC/Dubbo等)，理解服务高可用性与可扩展性；\n6、熟悉Kubernates、Docker容器等主流云原生平台和技术。','暂无',0,'2020-04-26 14:00:37','O123456789','早上9点到晚上9点'),(9,'深度学习/机器学习算法工程师','期权，餐饮补贴，五险一金，带薪年假','上海 - 浦东新区 - 上海市浦东嘉里城4F Wework','张三',30000,2,'1. 负责图像识别、目标检测、OCR等方向深度学习前沿算法的调研和改进，并应用到实际项目中\n2. 根据公司项目和产品需求，设计和训练深度学习网络结构和测试平台','1. 计算机相关专业硕士及以上学历\n2. 熟悉图像处理基本原理\n3. 熟悉tensorflow、pytorch等深度学习框架\n4. 熟悉CNN、LSTM、attention等相关网络结构\n5. 熟悉模型量化和Automl等优化手段\n6. 熟练使用opencv，numpy，scipy等工具','暂无',1,'2020-04-26 14:01:58','O123456789','早上9点到晚上9点'),(10,'web前端开发工程师 ','大平台 人工智能','北京 - 海淀区 - 西二旗 - 奎科大厦','王五',10000,1,'负责百度云计算事业部产品的Web前端研发工作，\n与产品、运营、UE、PM团队和其他支持部门紧密合作，提供产品的技术支持和产品体验','计算机及相关专业本科及以上学历，具备足够扎实的前端基础\n精通HTML、CSS、JavaScript、Ajax等，熟悉W3C标准，对交互体验/Web语义化/浏览器兼容问题等有深刻理解\n熟悉vue/react/angularjs，有相关项目开发经验\n熟悉Linux系统，对算法、数据库、数据结构以及后台开发(C/C++/PHP/Java等)有一定了解\n良好的沟通能力和团队协作精神，抗压，有严谨的工作态度与高质量意识、强烈的进取心','无',0,'2020-04-26 14:05:19','O123455432','早上9点到下午6点'),(11,'WEB前端开发工程师','周末双休，五险一金','南京 - 雨花台区 - 大周路88号软件谷科创城B1栋','王五',10000,2,'1、负责公司核心产品的前端页面开发；\n2、协同后台开发人员定义数据接口与逻辑交互；\n3、根据项目开发要求高质高效完成任务，并兼顾用户体验、性能、可维护性。','1、学历本科及以上，有2年以上前端设计开发相关经验\n2、熟悉HTML5和CSS3、Javascript，能熟练使用多种Web调试工具，开发符合标准、兼容各浏览器的页面；熟悉收集浏览器HTML5兼容性;\n3、熟练使用vue.js、 Angular、React中的一个或多个\n4、逻辑分析能力强，善于沟通，具备较强的学习能力\n5、有nodejs、小程序开发经验者优先','无',1,'2020-04-26 14:06:22','O123455432','早上9点到下午6点'),(12,'网络安全工程师','五险一金,下午茶,绩效','深圳 - 福田区 - 商报路7号天健创业大厦8楼（立创商城）','王五',20000,2,'1、负责公司内部网络终端的安全管理维护，对网络攻击和安全事件做应急响应\n2、负责公司网络安全体系建设、系统安全评估与加固\n3、保障公司终端、系统、网络与信息的安全性、完整性和可用性，消除安全隐患，避免问题发生\n4、基于动态的安全运营和监控，安全日志审计\n5、从网络安全角度评估从研发流程，实施，运营，使用等方面的现状并提出整改建议和推动落地\n6、网络信息安全的方案制定、讲解、培训','1、2年及以上相关工作经验，大专及以上学历，计算机网络相关专业\n2、精通TCP/IP协议，熟悉windows、linux系统\n3、熟悉防**、IPS、防火墙、漏洞扫描、WAF等安全产品\n4、熟悉常见的安全漏洞原理、防范方法和审计方法，包括DDos攻击、SQL注入、XSS、CSRF等OWASP TOP 10安全风险\n5、具有事件分析与解析还原能力，具有网络安全攻防知识和经验\n6、具有CCIE\\CISSP\\CISAW\\CISP等证书者优先\n7、具有处理过木马、**、入侵、网络攻击等突发安全事件经验者优先\n8、具有良好的团队协作精神，思维清晰敏捷，逻辑分析能力强','无',1,'2020-04-26 14:07:55','O123455432','早上9点到下午6点'),(13,'网络安全工程师','五险一金、餐补','深圳 - 福田区 - - 荣超大厦13楼＿少年宫地铁站','王五',40000,2,'1、负责银行应用系统的安全评审、业务安全设计、安全测试和安全事件应急响应等；\n2、负责银行应用系统安全开发管理过程的安全审核，提供安全解决方案，参与优化公司SDLC最佳实践；\n3、跟进业内创新技术，为银行引入新技术提供安全解决方案；\n4、负责银行SDLC相关配套工具IAST，SAST等工具平台的建设与优化；','1、熟悉常见WEB安全漏洞原理与防范，可提供代码层的漏洞修复方案；\n2、了解Java、php、Python等的一种或几种程序语言；\n3、了解业界安全攻防动态，追踪最新安全漏洞；\n4、具备良好沟通、协调能力、分析和解决问题的能力；\n5、计算机，软件工程，网络安全等全日制本科及以上学历；\n6、3年以上经验','无',0,'2020-04-26 14:09:02','O123455432','早上9点到晚上9点'),(14,'Java高级研发','班车 餐补 健身房','南京 - 秦淮区 - 白下科技园天安数码城','张三',15000,2,'1. 负责Java应用项目的系统分析以及架构设计工作\n2. 负责系统核心代码的编写工作\n3. 指导初级工程师实施详细设计和编码\n4. 负责所研发项目后续迭代优化、BUG处理等工作\n5. 参与相关项目的设计Review和代码Review工作','1、精通JAVA开发语言；\n2、五年以上（Java研发组长七年以上）项目开发经验，相关基础知识扎实\n3、熟练主流JavaWeb应用开发技术， 熟悉JavaScript、Ajax、JQuery 、Webservice等技术；\n4、熟练使用Eclipse、Maven、SVN等开发和构建工具；\n5、熟悉linux系统的相关命令及基础shell编程；\n6、良好的沟通和语言表达能力；\n7、为人诚恳，团队意识强；\n8、较强的学习、自我总结能力；\n9、有高并发、大流量经验优先。','无',1,'2020-06-13 17:30:21','O123456789','早上9点到晚上10点'),(15,'java开发工程师','正式行员编制，六险二金，发展前景大','佛山 - 南海区 - 桂城 - 桂城街道海六路29号广发金融中心','张三三3',15000,5,'此岗位初级到高级皆有招聘，具体薪酬视面试后级别而定。','1、本科及以上学历，计算机相关专业，35周岁以下。具有2年以上软件开发经验。具备以下一项或多项技术技能优先。\n2、有银行业务系统开发经验者优先考虑，包含传统业务系统、互联网金融系统、财务类系统等。\n3、熟练使用JAVA、脚本语言（SHELL,PYTHON）等编程语言，熟练掌握常见数据结构与算法。熟悉主流开源框架如SpringBoot的使用。\n4、熟练掌握常用中间件Tomcat、WebSphere和常用开源组件Activitiy、MyBatis、Dubbo。\n5、熟练使用DB2/MySQL/Oracle数据库；\n6、熟悉分布式架构相关内容，熟悉微服务原理，对服务调用、消息中间件、应用监控、分布式缓存等其中一项或多项有深入理解，有实战经验者优先考虑。\n7、熟悉Nginx、Redis、RocketMQ、Kafka等开源软件的使用\n8、逻辑思维严密，勇于创新，沟通良好，具备团队合作意识，以及较强的工作责任心','无',1,'2020-06-22 08:18:23','O135792468','早上8点到晚上6点');

/*Table structure for table `t_job_status` */

DROP TABLE IF EXISTS `t_job_status`;

CREATE TABLE `t_job_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_id` int(11) DEFAULT NULL,
  `is_pass` tinyint(4) DEFAULT NULL,
  `handle_time` datetime DEFAULT NULL,
  `explain_content` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FOREIGN_KEY_STATUS_JOB_ID` (`job_id`),
  CONSTRAINT `t_job_status_ibfk_1` FOREIGN KEY (`job_id`) REFERENCES `t_job` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `t_job_status` */

insert  into `t_job_status`(`id`,`job_id`,`is_pass`,`handle_time`,`explain_content`) values (1,2,1,'2020-03-24 06:45:54','该审核通过'),(2,3,0,'2020-03-24 07:00:14','证书或者证明材料不充足'),(3,4,1,'2020-03-31 11:24:45','该审核通过'),(4,5,1,'2020-03-31 11:52:06','该审核通过'),(5,6,1,'2020-04-03 08:41:27','该审核通过'),(6,9,1,'2020-05-18 19:36:40','该审核通过'),(7,11,1,'2020-05-18 19:36:58','该审核通过'),(8,12,1,'2020-05-18 19:37:07','该审核通过'),(9,7,1,'2020-05-22 11:05:52','该审核通过'),(10,14,1,'2020-06-13 17:33:34','该审核通过'),(11,15,1,'2020-06-22 08:22:09','该审核通过');

/*Table structure for table `t_resume` */

DROP TABLE IF EXISTS `t_resume`;

CREATE TABLE `t_resume` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `s_id` int(11) DEFAULT NULL,
  `j_id` int(11) DEFAULT NULL,
  `file_path` varchar(100) DEFAULT NULL,
  `apply_time` datetime DEFAULT NULL,
  `check_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FOREIGN_KEY_STUDENT_ID` (`s_id`),
  KEY `FOREIGN_KEY_MERCHANT_ID` (`j_id`),
  CONSTRAINT `t_resume_ibfk_1` FOREIGN KEY (`s_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_resume_ibfk_2` FOREIGN KEY (`j_id`) REFERENCES `t_job` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `t_resume` */

insert  into `t_resume`(`id`,`s_id`,`j_id`,`file_path`,`apply_time`,`check_status`) values (1,1,2,'F:/files/08e74af7-b4b1-4e4c-ba14-316558ca413d_2018软件2班冉堃赤.docx','2020-03-26 09:14:24',1),(2,1,4,'F:/files/63ed8ebd-5f38-49cf-9d9d-6e6b16008c08_3组冉堃赤.docx','2020-03-31 11:25:30',-1),(3,1,5,'F:/files/2d854388-feea-44e5-81f9-0567476c8d7e_3组冉堃赤.docx','2020-03-31 11:53:17',-1),(4,1,6,'F:/files/ff4a875c-de4d-4ede-92d0-95d0892eec12_3组冉堃赤.docx','2020-04-04 21:57:02',0),(5,8,2,'F:/files/87714a61-7ee4-490e-b78c-6d163e8399dc_3组冉堃赤swing.docx','2020-05-21 09:22:38',1),(6,9,2,'F:/files/241cfedc-5f95-47f1-bc35-9646a028d58c_简历.txt','2020-05-21 09:57:13',-1),(7,11,2,'F:/files/ec2133a0-cbfd-4412-bd70-60a952c73f5e_简历.txt','2020-05-21 11:24:43',1),(8,12,2,'F:/files/2747f063-b256-4b0e-a100-ac2f37ad29d2_简历.txt','2020-05-21 11:25:03',-1),(9,13,2,'F:/files/b9b15e15-4ae8-425e-9af5-b12cc3cf94ce_简历.txt','2020-05-21 11:26:26',1),(10,14,14,'F:/files/6a3ea20a-976f-4375-9371-ecf31bda5e9f_简历.txt','2020-06-13 17:38:47',1);

/*Table structure for table `t_trading_certificate` */

DROP TABLE IF EXISTS `t_trading_certificate`;

CREATE TABLE `t_trading_certificate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `file_path` varchar(100) DEFAULT NULL,
  `upload_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FOREIGN_KEY_TRADING_CERTIFICATE` (`uid`),
  CONSTRAINT `t_trading_certificate_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_trading_certificate` */

insert  into `t_trading_certificate`(`id`,`uid`,`file_path`,`upload_time`) values (3,2,'F:/files/f04b3826-df53-4700-af00-a57619448bfa_商家证书.txt','2020-04-06 21:45:15');

/*Table structure for table `t_transfer_record` */

DROP TABLE IF EXISTS `t_transfer_record`;

CREATE TABLE `t_transfer_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_from` varchar(16) DEFAULT NULL,
  `transfer_count` int(11) DEFAULT NULL,
  `card_to` varchar(16) DEFAULT NULL,
  `transfer_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FOREIGN_KEY_CARD_FROM` (`card_from`),
  KEY `FOREIGN_KEY_CARD_TO` (`card_to`),
  CONSTRAINT `t_transfer_record_ibfk_1` FOREIGN KEY (`card_from`) REFERENCES `t_bank_card` (`card_number`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_transfer_record_ibfk_2` FOREIGN KEY (`card_to`) REFERENCES `t_bank_card` (`card_number`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_transfer_record` */

insert  into `t_transfer_record`(`id`,`card_from`,`transfer_count`,`card_to`,`transfer_time`) values (1,'6225112233445566',20000,'6225332233445566','2020-03-31 01:06:34'),(2,'6225112233445566',20000,'6225332233445566','2020-03-31 09:10:33'),(3,'6225112233445566',20000,'6225332233445566','2020-04-25 17:59:24');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `identification` char(18) DEFAULT NULL,
  `gender` char(2) DEFAULT NULL,
  `nation` varchar(10) DEFAULT NULL,
  `t_account` char(10) DEFAULT NULL,
  `bind_mail` varchar(20) DEFAULT NULL,
  `current_address` varchar(50) DEFAULT NULL,
  `subsidiary_organ` varchar(50) DEFAULT NULL,
  `phone` char(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_FK_T_STUDENT_ACCOUNT` (`t_account`),
  CONSTRAINT `FK_FK_T_STUDENT_ACCOUNT` FOREIGN KEY (`t_account`) REFERENCES `t_account` (`username`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='t_student';

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`name`,`identification`,`gender`,`nation`,`t_account`,`bind_mail`,`current_address`,`subsidiary_organ`,`phone`) values (1,'冉堃赤','50023319990123171X','男','汉族','2018120000','2243756824@qq.com','四川省德阳市旌阳区四川工程职业技术学院','四川工程职业技术学院','17602382977'),(2,'张三','500233198901231715','男','汉族','O123456789','2243756824@qq.com','成都','成都科技有限公司','13112345678'),(3,'冉堃赤','50023319990123171X','男','汉族','1122334455','2243756824@qq.com','四川省德阳市旌阳区四川工程职业技术学院','四川工程职业技术学院','17602382999'),(4,'李四','500233199501231222','女','汉族','O987654321','2243756824@qq.com','重庆','重庆网络有限公司','17602382966'),(5,'王五','500233199901231231','男','汉族','O123455432','2243756824@qq.com','成都','成都科技有限公司','17602382966'),(7,'张三三','500233200001231234','女','汉族','2018120001','2243756824@qq.com','重庆','四川工程职业技术学院','17600000000'),(8,'李四四','500233200001231234','女','汉族','2018120002','2243756824@qq.com','重庆','四川工程职业技术学院','13112345678'),(9,'赵柳','500233200001231222','女','汉族','2018120003','2243756824@qq.com','重庆市忠县乌杨镇','四川工程职业技术学院','13112345678'),(10,'张四','500233200001232222','男','汉族','2018120004','2243756824@qq.com','重庆市忠县乌杨镇','四川工程职业技术学院','13112345678'),(11,'张三三三','500233200001232222','男','汉族','2018130001','2243756824@qq.com','重庆市忠县乌杨镇','四川工程职业技术学院','17602382966'),(12,'张三三四','500233200011232223','女','汉族','2018130002','2243756824@qq.com','重庆市忠县乌杨镇','四川工程职业技术学院','17602382966'),(13,'张三三五','500233200011232225','女','汉族','2018130004','2243756824@qq.com','重庆市忠县乌杨镇','四川工程职业技术学院','17602382966'),(14,'张三三六','500233200011232228','女','汉族','2018130005','2243756824@qq.com','重庆市忠县乌杨镇','四川工程职业技术学院','17602382966'),(15,'张三三3','510233198901231721','女','汉','O135792468','2243756824@qq.com','成都','成都科技有限公司','17620382966'),(16,'李四四4','520233198901231721','女','汉','O246813579','2243756824@qq.com','重庆','重庆科技有限公司','17632382966');

/* Trigger structure for table `t_job` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `auto_update` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `auto_update` BEFORE UPDATE ON `t_job` FOR EACH ROW BEGIN
	IF old.recruit_count != new.recruit_count 
		THEN
			IF new.recruit_count < 1 
			THEN SET new.current_status = -2;
			END IF;
	END IF;
END */$$


DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
