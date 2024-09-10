DROP TABLE IF EXISTS `t_finance_manage`;
DROP TABLE IF EXISTS `t_leave_approval`;
DROP TABLE IF EXISTS `t_meeting_attendance`;
DROP TABLE IF EXISTS `t_project_attendance`;
DROP TABLE IF EXISTS `t_user_manage`;

DROP TABLE IF EXISTS `t_note`;
DROP TABLE IF EXISTS `t_leave`;
DROP TABLE IF EXISTS `t_attendance`;
DROP TABLE IF EXISTS `t_finance`;
DROP TABLE IF EXISTS `t_project`;
DROP TABLE IF EXISTS `t_meeting`;
DROP TABLE IF EXISTS `t_user`;
-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------

CREATE TABLE `t_user` (
  `user_id` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `user_name` varchar(10) NOT NULL,
  `user_account` varchar(20) NOT NULL,
  `user_password` varchar(35) NOT NULL,
  `user_email` varchar(20) NOT NULL,
  `user_authority` varchar(6) NOT NULL,
  `user_salt` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY (`user_account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `t_user` VALUES ('00000001', '1号管理员', 'admi1', '5d80bd52e3c1bcf897045785da24b522', '123456789@qq.com','管理员','Km`s');-- 111
INSERT INTO `t_user` VALUES ('00000002', '2号管理员', 'admi2', '463289bd6838c1ed0afcfa5439a8664e','123456789@qq.com', '管理员','}02d');-- 222
INSERT INTO `t_user` VALUES ('00000003', '3号管理员', 'admi3', '8b78791caa3837b1bedef3126bb3be36','123456789@qq.com', '管理员','_Tjd');-- 333
INSERT INTO `t_user` VALUES ('00000004', '赵一', 'user1', '109889f941630d269546335f728f3558', '123456789@qq.com','员工','123');-- MD5加密 真实密码12345
INSERT INTO `t_user` VALUES ('00000005', '李二', 'user2', 'bfda7bb176421340cba445f8465de887','123456789@qq.com', '员工','123');--  123456123
INSERT INTO `t_user` VALUES ('00000006', '安三', 'user3', '3ef0160214bc057fea68b3635beaac54','123456789@qq.com', '员工','123');-- 123457123

-- ----------------------------
-- Table structure for `t_attendance`
-- ----------------------------

CREATE TABLE `t_attendance` (
  `attendance_id` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `user_id` int(8) unsigned zerofill NOT NULL,
  `attendance_state` varchar(10) NOT NULL DEFAULT '未签到',
  `attendance_time` datetime DEFAULT NULL,
  `date` date NOT NULL,
  `deadline` varchar(5) NOT NULL,
  PRIMARY KEY (`attendance_id`),
  FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `t_attendance` VALUES ('00000001', '00000001', '已签到', '2024-09-08 08:30:15', '2024-09-04', '否');
INSERT INTO `t_attendance` VALUES ('00000002', '00000002', '未签到', null, '2024-09-04', '否');
INSERT INTO `t_attendance` VALUES ('00000003', '00000003', '请假', null, '2024-09-04', '否');

-- ----------------------------
-- Table structure for `t_project`
-- ----------------------------

CREATE TABLE `t_project` (
  `project_id` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `project_name` varchar(30) NOT NULL,
  `project_content` varchar(200) NOT NULL,
  `project_state` varchar(10) NOT NULL,
  `project_total` double(10,2) DEFAULT '0.00',
  `user_id` int(8) unsigned zerofill NOT NULL,
  PRIMARY KEY (`project_id`),
  FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `t_project` VALUES ('00000001', '品牌重塑与增长营销活动', '分析目标市场趋势、竞争对手策略及目标客群偏好，制定差异化营销策略。', '进行中','200000.00', '00000004');
INSERT INTO `t_project` VALUES ('00000002', '企业智能财务管理系统升级', '与企业财务部门紧密合作，收集并整理现有系统的不足、用户痛点及新需求，自动化报表生成、预算控制、成本分析等。', '未开始','0.00', '00000005');
INSERT INTO `t_project` VALUES ('00000003', '全方位客户服务体验升级计划', '通过客户反馈、服务记录、满意度调查等方式，全面评估当前客户服务流程、效率及客户满意度水平，识别痛点与不足。', '已结束','0.00', '00000006');

-- ----------------------------
-- Table structure for `t_finance`
-- ----------------------------

CREATE TABLE `t_finance` (
  `finance_id` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `finance_type` varchar(10) NOT NULL,
  `amount` double(10,2) DEFAULT '0.00',
  `finance_description` varchar(60) NOT NULL,
  `finance_record_time` datetime NOT NULL,
  `user_id` int(8) unsigned zerofill DEFAULT NULL,
  `project_id` int(8) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`finance_id`),
  FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`project_id`) REFERENCES `t_project` (`project_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `t_finance` VALUES ('00000001', '项目流水', '200000.00', '品牌重塑与增长营销活动启动成本', '2024-09-03 10:30:15', null, '00000001');
INSERT INTO `t_finance` VALUES ('00000002', '绩效奖金', '10000.00', '年度优秀员工', '2023-12-20 14:15:00', '00000001', null);
INSERT INTO `t_finance` VALUES ('00000003', '补贴', '2000.00', '住房补贴', '2023-06-15 15:14:00', '00000003', null);
-- ----------------------------
-- Table structure for `t_finance_manage`
-- ----------------------------

CREATE TABLE `t_finance_manage` (
  `finance_manage_id` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `finance_id` int(8) unsigned zerofill NOT NULL,
  `user_id` int(8) unsigned zerofill NOT NULL,
  `finance_manage_time` datetime NOT NULL,
  `finance_manage_description` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`finance_manage_id`),
  FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `t_finance_manage` VALUES ('00000001', '00000001', '00000005', '2024-09-03 10:30:15', '核实流水');
INSERT INTO `t_finance_manage` VALUES ('00000002', '00000002', '00000004', '2023-12-15 14:15:00', '登记奖金');
INSERT INTO `t_finance_manage` VALUES ('00000003', '00000003', '00000006', '2024-09-03 10:30:15', '该员工仍需补贴');
-- ----------------------------
-- Table structure for `t_leave`
-- ----------------------------

CREATE TABLE `t_leave` (
  `leave_id` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `reason` varchar(100) NOT NULL,
  `state` varchar(10) NOT NULL,
  `user_id` int(8) unsigned zerofill NOT NULL,
  PRIMARY KEY (`leave_id`),
  FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `t_leave` VALUES ('00000001', '2024-03-09', '2024-03-11', '病假', '已批准', '00000003');
INSERT INTO `t_leave` VALUES ('00000002', '2024-06-15', '2024-06-17', '外出旅游', '未批准', '00000002');
INSERT INTO `t_leave` VALUES ('00000003', '2024-09-06', '2024-09-11', '年假', '未审批', '00000001');

-- ----------------------------
-- Table structure for `t_leave_approval`
-- ----------------------------

CREATE TABLE `t_leave_approval` (
  `approval_id` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `leave_id` int(8) unsigned zerofill NOT NULL,
  `user_id` int(8) unsigned zerofill NOT NULL,
  PRIMARY KEY (`approval_id`),
  FOREIGN KEY (`leave_id`) REFERENCES `t_leave` (`leave_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `t_leave_approval` VALUES ('00000001', '00000001', '00000006');
INSERT INTO `t_leave_approval` VALUES ('00000002', '00000002', '00000005');
INSERT INTO `t_leave_approval` VALUES ('00000003', '00000003', '00000004');

-- ----------------------------
-- Table structure for `t_meeting`
-- ----------------------------

CREATE TABLE `t_meeting` (
  `meeting_id` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `meeting_num` int(3) unsigned zerofill NOT NULL ,
  `meeting_name` varchar(40) NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `meeting_state` varchar(10) NOT NULL,
  `user_id` int(8) unsigned zerofill NOT NULL,
  PRIMARY KEY (`meeting_id`),
  FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  UNIQUE KEY (`meeting_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `t_meeting` VALUES ('00000001', '001', '未来趋势与创新策略研讨会', '2024-06-05 08:30:00', '2024-06-05 11:30:00', '已结束', '00000005');
INSERT INTO `t_meeting` VALUES ('00000002', '002', '健康与安全合作会议', '2024-09-04 15:30:00', '2024-09-04 17:00:00', '进行中', '00000004');
INSERT INTO `t_meeting` VALUES ('00000003', '003', '可持续发展与企业社会责任峰会', '2024-10-15 13:00:00', '2024-10-15 15:00:54', '未开始', '00000006');
-- ----------------------------
-- Table structure for `t_meeting_attendance`
-- ----------------------------

CREATE TABLE `t_meeting_attendance` (
  `meeting_attendance_id` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `meeting_id` int(8) unsigned zerofill NOT NULL,
  `user_id` int(8) unsigned zerofill NOT NULL,
  PRIMARY KEY (`meeting_attendance_id`),
  FOREIGN KEY (`meeting_id`) REFERENCES `t_meeting` (`meeting_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `t_meeting_attendance` VALUES ('00000001', '00000001', '00000001');
INSERT INTO `t_meeting_attendance` VALUES ('00000002', '00000001', '00000002');
INSERT INTO `t_meeting_attendance` VALUES ('00000003', '00000001', '00000003');
INSERT INTO `t_meeting_attendance` VALUES ('00000004', '00000002', '00000003');
INSERT INTO `t_meeting_attendance` VALUES ('00000005', '00000003', '00000002');
INSERT INTO `t_meeting_attendance` VALUES ('00000006', '00000003', '00000001');
-- ----------------------------
-- Table structure for `t_note`
-- ----------------------------

CREATE TABLE `t_note` (
  `note_id` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `note_name` varchar(20) NOT NULL,
  `note_body` varchar(500) DEFAULT NULL,
  `note_date` datetime NOT NULL,
  `remind_time` datetime DEFAULT NULL,
  `user_id` int(8) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`note_id`),
  FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `t_note` VALUES ('00000001', '会议提醒', '记得带上资料', '2024-09-13 13:02:00', '2024-09-15 08:30:00', '00000001');
INSERT INTO `t_note` VALUES ('00000002', '会议提醒', '提前半个小时到', '2024-09-20 11:03:32', '2024-09-24 11:03:39', '00000002');
INSERT INTO `t_note` VALUES ('00000003', '提交提醒', '资料提交时间', '2024-09-26 11:04:33', '2024-10-23 11:04:39', '00000003');
INSERT INTO `t_note` VALUES ('00000004', '面试', '面试应聘者', '2024-09-12 11:05:32', '2024-10-21 11:05:38', '00000004');

-- ----------------------------
-- Table structure for `t_project_attendance`
-- ----------------------------

CREATE TABLE `t_project_attendance` (
  `join_id` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `project_id` int(8) unsigned zerofill NOT NULL,
  `user_id` int(8) unsigned zerofill NOT NULL,
  PRIMARY KEY (`join_id`),
  FOREIGN KEY (`project_id`) REFERENCES `t_project` (`project_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `t_project_attendance` VALUES ('00000001', '00000001', '00000002');
INSERT INTO `t_project_attendance` VALUES ('00000002', '00000001', '00000003');
INSERT INTO `t_project_attendance` VALUES ('00000003', '00000002', '00000001');
INSERT INTO `t_project_attendance` VALUES ('00000004', '00000003', '00000003');

