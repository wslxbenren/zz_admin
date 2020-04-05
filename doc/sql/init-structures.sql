/*

 File Encoding         : 65001
 Date: 05/04/2020 20:48:27
 author: dadovicn
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for biz_actual_floatpeople
-- ----------------------------
DROP TABLE IF EXISTS `biz_actual_floatpeople`;
CREATE TABLE `biz_actual_floatpeople` (
                                          `float_id` varchar(50) NOT NULL COMMENT 'ID，uuid()赋值',
                                          `identity_num` varchar(18) DEFAULT NULL COMMENT '公民身份号码:编码应符合GB/T11643',
                                          `person_name` varchar(50) DEFAULT NULL COMMENT '姓名',
                                          `used_name` varchar(50) DEFAULT NULL COMMENT '曾用名',
                                          `person_sex` varchar(1) DEFAULT NULL COMMENT '性别:编码应符合GB/T2261.1',
                                          `date_birth` date DEFAULT NULL COMMENT '出生日期:格式为“YYYYMMDD”',
                                          `nation` varchar(2) DEFAULT NULL COMMENT '民族:编码应符合GB/T3304',
                                          `native_info` varchar(6) DEFAULT NULL COMMENT '籍贯:编码应符合GB/T2260',
                                          `marriage_flag` varchar(2) DEFAULT NULL COMMENT '婚姻状况:编码应符合GB/T2261.2',
                                          `party_flag` varchar(2) DEFAULT NULL COMMENT '政治面貌:编码应符合GB/T4762',
                                          `education_bg` varchar(2) DEFAULT NULL COMMENT '学历:编码应符合GB/T4658',
                                          `faith_type` varchar(2) DEFAULT NULL COMMENT '宗教信仰:编码应符合GA214.12',
                                          `vocation_code` varchar(5) DEFAULT NULL COMMENT '职业类别:编码应符合GB/T6565',
                                          `vocation` varchar(30) DEFAULT NULL COMMENT '职业',
                                          `service_addr` varchar(100) DEFAULT NULL COMMENT '服务处所',
                                          `contact` varchar(50) DEFAULT NULL COMMENT '联系方式:手机号码或固定电话号码',
                                          `registered_place` varchar(6) DEFAULT NULL COMMENT '户籍地:编码应符合GB/T2260',
                                          `registered_addr` varchar(80) DEFAULT NULL COMMENT '户籍门（楼）详址',
                                          `residence` varchar(6) DEFAULT NULL COMMENT '现住地:编码应符合GB/T2260',
                                          `residence_addr` varchar(80) DEFAULT NULL COMMENT '现住门（楼）详址',
                                          `into_cause` varchar(2) DEFAULT NULL COMMENT '流入原因',
                                          `card_type` varchar(2) DEFAULT NULL COMMENT '办证类型',
                                          `card_no` varchar(22) DEFAULT NULL COMMENT '证件号码',
                                          `regis_date` date DEFAULT NULL COMMENT '登记日期:格式为“YYYYMMDD”',
                                          `expiry_date` date DEFAULT NULL COMMENT '证件到期日期:格式为“YYYYMMDD”',
                                          `resid_type` varchar(2) DEFAULT NULL COMMENT '住所类型',
                                          `if_import` int(1) DEFAULT NULL COMMENT '是否重点关注人员',
                                          `eff_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',
                                          `exp_date` datetime NOT NULL DEFAULT '3000-01-01 00:00:00' COMMENT '失效时间',
                                          `status` varchar(4) DEFAULT '0' COMMENT '同步状态',
                                          `status_cd` varchar(4) DEFAULT NULL COMMENT '数据状态 10是保存待提交 12是生效状态 22是失效',
                                          `oper_name` varchar(20) DEFAULT NULL COMMENT '操作人名称',
                                          `oper_date` datetime DEFAULT NULL COMMENT '操作时间',
                                          `creator` varchar(100) DEFAULT NULL COMMENT '创建人',
                                          `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                          `unit_code` varchar(100) DEFAULT NULL COMMENT '单位编码,所属单位，后续可用于权限管理',
                                          PRIMARY KEY (`float_id`) USING BTREE,
                                          UNIQUE KEY `identity_num` (`identity_num`) USING BTREE,
                                          KEY `person_name` (`person_name`,`identity_num`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流动人口基础信息表';

-- ----------------------------
-- Table structure for biz_actual_foreigners
-- ----------------------------
DROP TABLE IF EXISTS `biz_actual_foreigners`;
CREATE TABLE `biz_actual_foreigners` (
                                         `fore_id` varchar(50) NOT NULL COMMENT 'ID，uuid()赋值',
                                         `lastname` varchar(40) DEFAULT NULL COMMENT '外文姓:编码应符合GB/T11643',
                                         `firstname` varchar(40) DEFAULT NULL COMMENT '外文名',
                                         `chinesename` varchar(50) DEFAULT NULL COMMENT '中文姓名',
                                         `person_sex` varchar(1) DEFAULT NULL COMMENT '性别:编码应符合GB/T2261.1',
                                         `date_birth` date DEFAULT NULL COMMENT '出生日期:格式为“YYYYMMDD”',
                                         `country` varchar(2) DEFAULT NULL COMMENT '国籍（地区）:编码应符合GB/T2659',
                                         `faith_type` varchar(2) DEFAULT NULL COMMENT '宗教信仰:编码应符合GA214.12',
                                         `card_type` varchar(3) DEFAULT NULL COMMENT '证件代码:编码应符合GA/T517',
                                         `card_no` varchar(30) DEFAULT NULL COMMENT '证件号码',
                                         `valid_date` date DEFAULT NULL COMMENT '证件有效期:格式为“YYYYMMDD”',
                                         `contact` varchar(50) DEFAULT NULL COMMENT '联系方式:手机号码或固定电话号码',
                                         `goal_in` varchar(2) DEFAULT NULL COMMENT '来华目的',
                                         `vocation_code` varchar(5) DEFAULT NULL COMMENT '职业类别:编码应符合GB/T6565',
                                         `vocation` varchar(30) DEFAULT NULL COMMENT '职业',
                                         `service_addr` varchar(100) DEFAULT NULL COMMENT '服务处所',
                                         `residence` varchar(6) DEFAULT NULL COMMENT '现住地:编码应符合GB/T2260',
                                         `residence_addr` varchar(80) DEFAULT NULL COMMENT '现住门（楼）详址',
                                         `arrival_date` date DEFAULT NULL COMMENT '抵达日期:格式为“YYYYMMDD”',
                                         `plan_leave` date DEFAULT NULL COMMENT '预计离开日期:格式为“YYYYMMDD”',
                                         `if_import` int(1) DEFAULT NULL COMMENT '是否重点关注人员',
                                         `eff_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',
                                         `exp_date` datetime NOT NULL DEFAULT '3000-01-01 00:00:00' COMMENT '失效时间',
                                         `status` varchar(4) DEFAULT '0' COMMENT '同步状态',
                                         `status_cd` varchar(4) DEFAULT NULL COMMENT '数据状态 10是保存待提交 12是生效状态 22是失效',
                                         `oper_name` varchar(20) DEFAULT NULL COMMENT '操作人名称',
                                         `oper_date` datetime DEFAULT NULL COMMENT '操作时间',
                                         `creator` varchar(100) DEFAULT NULL COMMENT '创建人',
                                         `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                         `unit_code` varchar(100) DEFAULT NULL COMMENT '单位编码,所属单位，后续可用于权限管理',
                                         PRIMARY KEY (`fore_id`) USING BTREE,
                                         KEY `card_no` (`card_no`,`card_type`) USING BTREE,
                                         KEY `person_name` (`lastname`,`firstname`,`country`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='境外人员基础信息表';

-- ----------------------------
-- Table structure for biz_actual_leftbehind
-- ----------------------------
DROP TABLE IF EXISTS `biz_actual_leftbehind`;
CREATE TABLE `biz_actual_leftbehind` (
                                         `left_id` varchar(50) NOT NULL COMMENT 'ID，uuid()赋值',
                                         `identity_num` varchar(18) DEFAULT NULL COMMENT '公民身份号码:编码应符合GB/T11643',
                                         `person_name` varchar(50) DEFAULT NULL COMMENT '姓名',
                                         `used_name` varchar(50) DEFAULT NULL COMMENT '曾用名',
                                         `person_sex` varchar(1) DEFAULT NULL COMMENT '性别:编码应符合GB/T2261.1',
                                         `date_birth` date DEFAULT NULL COMMENT '出生日期:格式为“YYYYMMDD”',
                                         `nation` varchar(2) DEFAULT NULL COMMENT '民族:编码应符合GB/T3304',
                                         `native_info` varchar(6) DEFAULT NULL COMMENT '籍贯:编码应符合GB/T2260',
                                         `marriage_flag` varchar(2) DEFAULT NULL COMMENT '婚姻状况:编码应符合GB/T2261.2',
                                         `party_flag` varchar(2) DEFAULT NULL COMMENT '政治面貌:编码应符合GB/T4762',
                                         `education_bg` varchar(2) DEFAULT NULL COMMENT '学历:编码应符合GB/T4658',
                                         `faith_type` varchar(2) DEFAULT NULL COMMENT '宗教信仰:编码应符合GA214.12',
                                         `vocation_code` varchar(5) DEFAULT NULL COMMENT '职业类别:编码应符合GB/T6565',
                                         `vocation` varchar(30) DEFAULT NULL COMMENT '职业',
                                         `service_addr` varchar(100) DEFAULT NULL COMMENT '服务处所',
                                         `contact` varchar(50) DEFAULT NULL COMMENT '联系方式:手机号码或固定电话号码',
                                         `registered_place` varchar(6) DEFAULT NULL COMMENT '户籍地:编码应符合GB/T2260',
                                         `registered_addr` varchar(80) DEFAULT NULL COMMENT '户籍门（楼）详址',
                                         `residence` varchar(6) DEFAULT NULL COMMENT '现住地:编码应符合GB/T2260',
                                         `residence_addr` varchar(80) DEFAULT NULL COMMENT '现住门（楼）详址',
                                         `healthy` varchar(2) DEFAULT NULL COMMENT '健康状况',
                                         `annual_perincome` varchar(8) DEFAULT NULL COMMENT '个人年收入',
                                         `household_id` varchar(2) DEFAULT NULL COMMENT '人户一致标识:01：一致，02：不一致',
                                         `leftbehind_type` varchar(2) DEFAULT NULL COMMENT '留守人员类型',
                                         `mainmem_idno` varchar(18) DEFAULT NULL COMMENT '家庭主要成员身份号码',
                                         `mainmem_name` varchar(50) DEFAULT NULL COMMENT '家庭主要成员姓名',
                                         `mainmem_health` varchar(2) DEFAULT NULL COMMENT '家庭主要成员健康状况',
                                         `mainmem_rela` varchar(2) DEFAULT NULL COMMENT '与留守人员关系:编码应符合GB/T4761',
                                         `mainmem_mobile` varchar(50) DEFAULT NULL COMMENT '家庭主要成员联系方式:手机号码或固定电话号码',
                                         `mainmem_addr` varchar(200) DEFAULT NULL COMMENT '家庭主要成员工作详细地址',
                                         `annual_income` varchar(8) DEFAULT NULL COMMENT '家庭年收入',
                                         `demand` varchar(1024) DEFAULT NULL COMMENT '困难及诉求',
                                         `helpe_comment` varchar(1024) DEFAULT NULL COMMENT '帮扶情况',
                                         `eff_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',
                                         `exp_date` datetime NOT NULL DEFAULT '3000-01-01 00:00:00' COMMENT '失效时间',
                                         `status` varchar(4) DEFAULT '0' COMMENT '同步状态',
                                         `status_cd` varchar(4) DEFAULT NULL COMMENT '数据状态 10是保存待提交 12是生效状态 22是失效',
                                         `oper_name` varchar(20) DEFAULT NULL COMMENT '操作人名称',
                                         `oper_date` datetime DEFAULT NULL COMMENT '操作时间',
                                         `creator` varchar(100) DEFAULT NULL COMMENT '创建人',
                                         `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                         `unit_code` varchar(100) DEFAULT NULL COMMENT '单位编码,所属单位，后续可用于权限管理',
                                         PRIMARY KEY (`left_id`) USING BTREE,
                                         UNIQUE KEY `identity_num` (`identity_num`) USING BTREE,
                                         KEY `person_name` (`person_name`,`identity_num`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='留守人员基础信息表';

-- ----------------------------
-- Table structure for biz_actual_registpeople
-- ----------------------------
DROP TABLE IF EXISTS `biz_actual_registpeople`;
CREATE TABLE `biz_actual_registpeople` (
                                           `regis_id` varchar(50) NOT NULL COMMENT 'ID，uuid()赋值',
                                           `identity_num` varchar(18) DEFAULT NULL COMMENT '身份证号:编码应符合GB/T11643',
                                           `person_name` varchar(50) DEFAULT NULL COMMENT '姓名',
                                           `used_name` varchar(50) DEFAULT NULL COMMENT '曾用名',
                                           `person_sex` varchar(1) DEFAULT NULL COMMENT '性别:编码应符合GB/T2261.1',
                                           `date_birth` date DEFAULT NULL COMMENT '出生日期:格式为“YYYYMMDD”',
                                           `nation` varchar(2) DEFAULT NULL COMMENT '民族:编码应符合GB/T3304',
                                           `native_info` varchar(6) DEFAULT NULL COMMENT '籍贯:编码应符合GB/T2260',
                                           `marriage_flag` varchar(2) DEFAULT NULL COMMENT '婚姻状况:编码应符合GB/T2261.2',
                                           `party_flag` varchar(2) DEFAULT NULL COMMENT '政治面貌:编码应符合GB/T4762',
                                           `education_bg` varchar(2) DEFAULT NULL COMMENT '学历:编码应符合GB/T4658',
                                           `faith_type` varchar(2) DEFAULT NULL COMMENT '宗教信仰:编码应符合GA214.12',
                                           `vocation_code` varchar(5) DEFAULT NULL COMMENT '职业类别:编码应符合GB/T6565',
                                           `vocation` varchar(30) DEFAULT NULL COMMENT '职业',
                                           `service_addr` varchar(100) DEFAULT NULL COMMENT '服务处所',
                                           `contact` varchar(50) DEFAULT NULL COMMENT '联系方式:手机号码或固定电话号码',
                                           `registered_place` varchar(6) DEFAULT NULL COMMENT '户籍地:编码应符合GB/T2260',
                                           `registered_addr` varchar(80) DEFAULT NULL COMMENT '户籍门（楼）详址',
                                           `residence` varchar(6) DEFAULT NULL COMMENT '现住地:编码应符合GB/T2260',
                                           `residence_addr` varchar(80) DEFAULT NULL COMMENT '现住门（楼）详址',
                                           `household_id` varchar(2) DEFAULT NULL COMMENT '人户一致标识:01：一致,02：不一致',
                                           `door_no` varchar(9) DEFAULT NULL COMMENT '户号',
                                           `househead_idno` varchar(18) DEFAULT NULL COMMENT '户主公民身份号码:编码应符合GB/T11643',
                                           `househead_name` varchar(50) DEFAULT NULL COMMENT '户主姓名',
                                           `househead_rela` varchar(2) DEFAULT NULL COMMENT '与户主关系:编码应符合GB/T4761',
                                           `househead_mobile` varchar(50) DEFAULT NULL COMMENT '户主联系方式:手机号码或固定电话号码',
                                           `eff_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',
                                           `exp_date` datetime NOT NULL DEFAULT '3000-01-01 00:00:00' COMMENT '失效时间',
                                           `status` varchar(4) DEFAULT '0' COMMENT '同步状态',
                                           `status_cd` varchar(4) DEFAULT NULL COMMENT '数据状态 10是保存待提交 12是生效状态 22是失效',
                                           `oper_name` varchar(20) DEFAULT NULL COMMENT '操作人名称',
                                           `oper_date` datetime DEFAULT NULL COMMENT '操作时间',
                                           `creator` varchar(100) DEFAULT NULL COMMENT '创建人',
                                           `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                           `unit_code` varchar(100) DEFAULT NULL COMMENT '单位编码,所属单位，后续可用于权限管理',
                                           PRIMARY KEY (`regis_id`) USING BTREE,
                                           UNIQUE KEY `identity_num` (`identity_num`) USING BTREE,
                                           KEY `person_name` (`person_name`,`identity_num`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='户籍人口基础信息表';

-- ----------------------------
-- Table structure for biz_actual_rentalhouse
-- ----------------------------
DROP TABLE IF EXISTS `biz_actual_rentalhouse`;
CREATE TABLE `biz_actual_rentalhouse` (
                                          `rent_id` varchar(50) NOT NULL COMMENT 'ID，uuid()赋值',
                                          `house_code` varchar(50) DEFAULT NULL COMMENT '房屋编号',
                                          `house_name` varchar(200) DEFAULT NULL COMMENT '房屋名称',
                                          `house_addr` varchar(200) DEFAULT NULL COMMENT '房屋地址',
                                          `constr_purpose` varchar(2) DEFAULT NULL COMMENT '建筑用途',
                                          `constr_area` double(8,2) DEFAULT NULL COMMENT '建筑面积（平方米）',
                                          `card_type` varchar(3) DEFAULT NULL COMMENT '证件代码:编码应符合GA/T517',
                                          `card_no` varchar(30) DEFAULT NULL COMMENT '证件号码',
                                          `homeowner_name` varchar(50) DEFAULT NULL COMMENT '房主姓名',
                                          `homeowner_mobile` varchar(50) DEFAULT NULL COMMENT '房主联系方式:手机号码或固定电话号码',
                                          `homeowner_addr` varchar(200) DEFAULT NULL COMMENT '房主现居详址',
                                          `hazard_type` varchar(2) DEFAULT NULL COMMENT '隐患类型',
                                          `lessee_idno` varchar(18) DEFAULT NULL COMMENT '承租人公民身份号码:编码应符合GB11463',
                                          `lessee_name` varchar(50) DEFAULT NULL COMMENT '承租人姓名',
                                          `lessee_mobile` varchar(50) DEFAULT NULL COMMENT '承租人联系方式:手机号码或固定电话号码',
                                          `lng` double(32,8) DEFAULT NULL COMMENT '经度',
                                          `lat` double(32,8) DEFAULT NULL COMMENT '纬度',
                                          `eff_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',
                                          `exp_date` datetime NOT NULL DEFAULT '3000-01-01 00:00:00' COMMENT '失效时间',
                                          `status` varchar(4) DEFAULT '0' COMMENT '同步状态',
                                          `status_cd` varchar(4) DEFAULT NULL COMMENT '数据状态 10是保存待提交 12是生效状态 22是失效',
                                          `oper_name` varchar(20) DEFAULT NULL COMMENT '操作人名称',
                                          `oper_date` datetime DEFAULT NULL COMMENT '操作时间',
                                          `creator` varchar(100) DEFAULT NULL COMMENT '创建人',
                                          `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                          `unit_code` varchar(100) DEFAULT NULL COMMENT '单位编码,所属单位，后续可用于权限管理',
                                          PRIMARY KEY (`rent_id`) USING BTREE,
                                          KEY `card_no` (`card_no`,`card_type`) USING BTREE,
                                          KEY `house_code` (`house_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出租房屋基础信息数据结构表';

-- ----------------------------
-- Table structure for biz_org_buildhead_info
-- ----------------------------
DROP TABLE IF EXISTS `biz_org_buildhead_info`;
CREATE TABLE `biz_org_buildhead_info` (
                                          `id` varchar(50) NOT NULL COMMENT '主键',
                                          `village_code` varchar(100) DEFAULT NULL COMMENT '小区（单位）编码',
                                          `village_name` varchar(100) DEFAULT NULL COMMENT '小区（单位）名称',
                                          `build_name` varchar(100) DEFAULT NULL COMMENT '楼栋名称',
                                          `build_area` double(8,2) DEFAULT NULL COMMENT '建筑面积（平方米）',
                                          `layer_num` int(3) DEFAULT NULL COMMENT '层数',
                                          `unit_num` int(3) DEFAULT NULL COMMENT '单元数',
                                          `households_num` int(4) DEFAULT NULL COMMENT '楼栋户数',
                                          `people_num` int(5) DEFAULT NULL COMMENT '楼栋人数',
                                          `head_name` varchar(50) DEFAULT NULL COMMENT '楼栋长姓名',
                                          `sex` varchar(1) DEFAULT NULL COMMENT '性别:编码应符合GB/T2261.1',
                                          `national` varchar(2) DEFAULT NULL COMMENT '民族:编码应符合GB/T3304',
                                          `political_status` varchar(2) DEFAULT NULL COMMENT '政治面貌:编码应符合GB/T4762',
                                          `birth` date DEFAULT NULL COMMENT '出生日期:格式为“YYYYMMDD”',
                                          `education_bg` varchar(2) DEFAULT NULL COMMENT '学历:编码应符合GB/T4658',
                                          `mobile` varchar(18) DEFAULT NULL COMMENT '手机号码',
                                          `fixed_phone` varchar(18) DEFAULT NULL COMMENT '固定电话',
                                          `addr` varchar(6) DEFAULT NULL COMMENT '所在地:编码应符合GB/T2260',
                                          `addr_detail` varchar(80) DEFAULT NULL COMMENT '所在地详址',
                                          `lng` double(32,8) DEFAULT NULL COMMENT '经度',
                                          `lat` double(32,8) DEFAULT NULL COMMENT '纬度',
                                          `creator` varchar(100) DEFAULT NULL COMMENT '创建人',
                                          `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                          `modifier` varchar(100) DEFAULT NULL COMMENT '修改人',
                                          `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                          `unit_code` varchar(100) DEFAULT NULL COMMENT '所属单位',
                                          PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='楼栋长信息数据结构表';

-- ----------------------------
-- Table structure for biz_org_majorcase_info
-- ----------------------------
DROP TABLE IF EXISTS `biz_org_majorcase_info`;
CREATE TABLE `biz_org_majorcase_info` (
                                          `id` varchar(50) NOT NULL COMMENT '主键',
                                          `case_code` varchar(100) DEFAULT NULL COMMENT '案（事）件编码',
                                          `case_name` varchar(100) DEFAULT NULL COMMENT '案（事）件名称',
                                          `occur_date` datetime DEFAULT NULL COMMENT '发生日期:格式为“YYYYMMDD”',
                                          `occur_addr` varchar(6) DEFAULT NULL COMMENT '发生地:编码应符合GB/T2260',
                                          `occur_addrdetail` varchar(80) DEFAULT NULL COMMENT '发生地详址',
                                          `case_grage` varchar(2) DEFAULT NULL COMMENT '案（事）件分级:字典',
                                          `case_type` varchar(2) DEFAULT NULL COMMENT '案（事）件类型:字典',
                                          `case_info` varchar(4000) DEFAULT NULL COMMENT '案（事）件情况',
                                          `creator` varchar(100) DEFAULT NULL COMMENT '创建人',
                                          `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                          `modifier` varchar(100) DEFAULT NULL COMMENT '修改人',
                                          `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                          `unit_code` varchar(100) DEFAULT NULL COMMENT '所属单位',
                                          PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='重特大案（事）件信息数据结构表';

-- ----------------------------
-- Table structure for biz_org_managecenter_info
-- ----------------------------
DROP TABLE IF EXISTS `biz_org_managecenter_info`;
CREATE TABLE `biz_org_managecenter_info` (
                                             `id` varchar(50) NOT NULL COMMENT '主键',
                                             `center_code` varchar(100) DEFAULT NULL COMMENT '综治中心代码',
                                             `center_name` varchar(100) DEFAULT NULL COMMENT '综治中心名称',
                                             `center_mobile` varchar(50) DEFAULT NULL COMMENT '综治中心联系方式:手机号码或固定电话号码',
                                             `grage` varchar(2) DEFAULT NULL COMMENT '综治中心层级:字典',
                                             `username` varchar(50) DEFAULT NULL COMMENT '负责人姓名',
                                             `usercode` varchar(50) DEFAULT NULL COMMENT '负责人编码',
                                             `user_mobile` varchar(50) DEFAULT NULL COMMENT '负责人联系方式:手机号码或固定电话号码',
                                             `dept_id` varchar(400) DEFAULT NULL COMMENT '组成单位*:可根据工作需要由各相关综治成员单位组成',
                                             `addr` varchar(6) DEFAULT NULL COMMENT '所在地:字典',
                                             `addr_detail` varchar(80) DEFAULT NULL COMMENT '所在地详址',
                                             `lng` double(32,8) DEFAULT NULL COMMENT '经度',
                                             `lat` double(32,8) DEFAULT NULL COMMENT '纬度',
                                             `creator` varchar(100) DEFAULT NULL COMMENT '创建人',
                                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                             `modifier` varchar(100) DEFAULT NULL COMMENT '修改人',
                                             `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                             `unit_code` varchar(100) DEFAULT NULL COMMENT '所属单位',
                                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='综治中心信息数据结构表';

-- ----------------------------
-- Table structure for biz_org_manageleadrespons_info
-- ----------------------------
DROP TABLE IF EXISTS `biz_org_manageleadrespons_info`;
CREATE TABLE `biz_org_manageleadrespons_info` (
                                                  `id` varchar(50) NOT NULL COMMENT '主键',
                                                  `Impledarea_code` varchar(100) DEFAULT NULL COMMENT '被实施地区编码',
                                                  `Impledarea_name` varchar(100) DEFAULT NULL COMMENT '被实施地区',
                                                  `area_grage` varchar(2) DEFAULT NULL COMMENT '被实施地区层级:字典',
                                                  `implementer_name` varchar(100) DEFAULT NULL COMMENT '实施主体名称',
                                                  `implementer_grage` varchar(2) DEFAULT NULL COMMENT '实施主体层级:字典',
                                                  `policy_type` varchar(2) DEFAULT NULL COMMENT '政策种类:字典',
                                                  `creator` varchar(100) DEFAULT NULL COMMENT '创建人',
                                                  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                                  `modifier` varchar(100) DEFAULT NULL COMMENT '修改人',
                                                  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                                  `unit_code` varchar(100) DEFAULT NULL COMMENT '所属单位',
                                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='综治领导责任制信息管理';

-- ----------------------------
-- Table structure for biz_special_aids_person
-- ----------------------------
DROP TABLE IF EXISTS `biz_special_aids_person`;
CREATE TABLE `biz_special_aids_person` (
                                           `aids_id` varchar(50) NOT NULL COMMENT 'ID，uuid()赋值',
                                           `person_name` varchar(50) NOT NULL COMMENT '姓名',
                                           `used_name` varchar(50) NOT NULL COMMENT '曾用名',
                                           `person_sex` varchar(10) DEFAULT NULL COMMENT '性别',
                                           `date_birth` date DEFAULT NULL COMMENT '出生日期',
                                           `nation` varchar(10) NOT NULL COMMENT '民族',
                                           `identity_num` varchar(50) NOT NULL COMMENT '身份证号',
                                           `native_info` varchar(50) DEFAULT NULL COMMENT '籍贯',
                                           `marriage_flag` varchar(10) DEFAULT NULL COMMENT '婚姻状况',
                                           `party_flag` varchar(10) DEFAULT NULL COMMENT '政治面貌',
                                           `edu_level` varchar(10) DEFAULT NULL COMMENT '文化程度',
                                           `faith_type` varchar(10) DEFAULT NULL COMMENT '宗教信仰',
                                           `vocation` varchar(50) DEFAULT NULL COMMENT '职业',
                                           `service_place` varchar(200) DEFAULT NULL COMMENT '服务场所',
                                           `contact` varchar(200) DEFAULT NULL COMMENT '联系方式',
                                           `registered_place` varchar(50) DEFAULT NULL COMMENT '户籍地',
                                           `registered_address` varchar(200) DEFAULT NULL COMMENT '户籍详细地址',
                                           `routes_infection` varchar(10) DEFAULT NULL COMMENT '感染途径',
                                           `is_pedigree` varchar(10) DEFAULT NULL COMMENT '是否有犯罪史',
                                           `pedigree_comments` varchar(1024) DEFAULT NULL COMMENT '犯罪情况说明',
                                           `case_type` varchar(10) DEFAULT NULL COMMENT '案件类别',
                                           `take_type` varchar(10) DEFAULT NULL COMMENT '关注类型',
                                           `help_comments` varchar(1024) DEFAULT NULL COMMENT '帮扶情况',
                                           `helper_name` varchar(50) DEFAULT NULL COMMENT '帮扶人姓名',
                                           `helper_address` varchar(50) DEFAULT NULL COMMENT '帮扶人联系方式',
                                           `detain_type` varchar(10) DEFAULT NULL COMMENT '收治情况',
                                           `detain_union` varchar(200) DEFAULT NULL COMMENT '收治机构名称',
                                           `eff_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',
                                           `exp_date` datetime NOT NULL DEFAULT '3000-01-01 00:00:00' COMMENT '失效时间',
                                           `status` varchar(4) DEFAULT '0' COMMENT '同步状态',
                                           `status_cd` varchar(4) DEFAULT NULL COMMENT '数据状态 10是保存待提交 12是生效状态 22是失效',
                                           `oper_name` varchar(20) DEFAULT NULL COMMENT '操作人名称',
                                           `oper_date` datetime DEFAULT NULL COMMENT '操作时间',
                                           `creator` varchar(100) DEFAULT NULL COMMENT '创建人',
                                           `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                           `unit_code` varchar(100) DEFAULT NULL COMMENT '单位编码,所属单位，后续可用于权限管理',
                                           PRIMARY KEY (`aids_id`) USING BTREE,
                                           UNIQUE KEY `identity_num` (`identity_num`) USING BTREE,
                                           KEY `person_name` (`person_name`,`identity_num`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='艾滋病危险人群基础信息表';

-- ----------------------------
-- Table structure for biz_special_beg_person
-- ----------------------------
DROP TABLE IF EXISTS `biz_special_beg_person`;
CREATE TABLE `biz_special_beg_person` (
                                          `beg_id` varchar(50) NOT NULL COMMENT 'ID，uuid()赋值',
                                          `person_name` varchar(50) NOT NULL COMMENT '姓名',
                                          `used_name` varchar(50) NOT NULL COMMENT '曾用名',
                                          `person_sex` varchar(10) DEFAULT NULL COMMENT '性别',
                                          `date_birth` date DEFAULT NULL COMMENT '出生日期',
                                          `nation` varchar(10) NOT NULL COMMENT '民族',
                                          `identity_num` varchar(50) NOT NULL COMMENT '身份证号',
                                          `native_info` varchar(50) DEFAULT NULL COMMENT '籍贯',
                                          `marriage_flag` varchar(10) DEFAULT NULL COMMENT '婚姻状况',
                                          `party_flag` varchar(10) DEFAULT NULL COMMENT '政治面貌',
                                          `edu_level` varchar(10) DEFAULT NULL COMMENT '文化程度',
                                          `faith_type` varchar(10) DEFAULT NULL COMMENT '宗教信仰',
                                          `vocation` varchar(50) DEFAULT NULL COMMENT '职业',
                                          `service_place` varchar(200) DEFAULT NULL COMMENT '服务场所',
                                          `contact` varchar(200) DEFAULT NULL COMMENT '联系方式',
                                          `registered_place` varchar(50) DEFAULT NULL COMMENT '户籍地',
                                          `registered_address` varchar(200) DEFAULT NULL COMMENT '户籍详细地址',
                                          `residence` varchar(50) DEFAULT NULL COMMENT '现住地',
                                          `residence_code` varchar(50) DEFAULT NULL COMMENT '现住地编码',
                                          `residence_address` varchar(200) DEFAULT NULL COMMENT '现住地详细地址',
                                          `belong_type` varchar(10) NOT NULL COMMENT '所属类别',
                                          `leftover_child` varchar(10) NOT NULL COMMENT '是否属于留守儿童',
                                          `study_comment` varchar(200) NOT NULL COMMENT '学习状况',
                                          `unstudy_reason` varchar(10) NOT NULL COMMENT '未入学原因',
                                          `is_ensure` varchar(10) NOT NULL COMMENT '是否享受低保补助',
                                          `stopstudy_reason` varchar(10) NOT NULL COMMENT '辍学原因',
                                          `stopstudy_other` varchar(200) NOT NULL COMMENT '辍学其他原因',
                                          `is_pedigree` varchar(10) DEFAULT NULL COMMENT '是否有犯罪史',
                                          `work_comment` varchar(10) NOT NULL COMMENT '就业情况',
                                          `work_other` varchar(200) NOT NULL COMMENT '就业其他情况',
                                          `bad_behaviour` varchar(10) NOT NULL COMMENT '是否有不良行为',
                                          `behaviour_other` varchar(200) NOT NULL COMMENT '不良行为其他情况',
                                          `unwork_reason` varchar(10) NOT NULL COMMENT '未就业原因',
                                          `unwork_other` varchar(200) NOT NULL COMMENT '未就业其他原因',
                                          `guard_flag` varchar(10) NOT NULL COMMENT '监护情况',
                                          `guard_reason` varchar(10) NOT NULL COMMENT '监护情况原因',
                                          `other_comments` varchar(500) NOT NULL COMMENT '其他需要说明问题',
                                          `source_income` varchar(10) DEFAULT NULL COMMENT '家庭主要经济来源',
                                          `source_other` varchar(200) DEFAULT NULL COMMENT '其他经济来源',
                                          `eff_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',
                                          `exp_date` datetime NOT NULL DEFAULT '3000-01-01 00:00:00' COMMENT '失效时间',
                                          `status` varchar(4) DEFAULT '0' COMMENT '同步状态',
                                          `status_cd` varchar(4) DEFAULT NULL COMMENT '数据状态 10是保存待提交 12是生效状态 22是失效',
                                          `oper_name` varchar(20) DEFAULT NULL COMMENT '操作人名称',
                                          `oper_date` datetime DEFAULT NULL COMMENT '操作时间',
                                          `creator` varchar(100) DEFAULT NULL COMMENT '创建人',
                                          `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                          `unit_code` varchar(100) DEFAULT NULL COMMENT '单位编码,所属单位，后续可用于权限管理',
                                          PRIMARY KEY (`beg_id`) USING BTREE,
                                          UNIQUE KEY `identity_num` (`identity_num`) USING BTREE,
                                          KEY `person_name` (`person_name`,`identity_num`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='特殊人群闲散乞讨人员基础信息表';

-- ----------------------------
-- Table structure for biz_special_correct_person
-- ----------------------------
DROP TABLE IF EXISTS `biz_special_correct_person`;
CREATE TABLE `biz_special_correct_person` (
                                              `correct_id` varchar(50) NOT NULL COMMENT 'ID，uuid()赋值',
                                              `person_name` varchar(50) NOT NULL COMMENT '姓名',
                                              `used_name` varchar(50) NOT NULL COMMENT '曾用名',
                                              `person_sex` varchar(10) DEFAULT NULL COMMENT '性别',
                                              `date_birth` date DEFAULT NULL COMMENT '出生日期',
                                              `nation` varchar(10) NOT NULL COMMENT '民族',
                                              `identity_num` varchar(50) NOT NULL COMMENT '身份证号',
                                              `native_info` varchar(50) DEFAULT NULL COMMENT '籍贯',
                                              `marriage_flag` varchar(10) DEFAULT NULL COMMENT '婚姻状况',
                                              `party_flag` varchar(10) DEFAULT NULL COMMENT '政治面貌',
                                              `edu_level` varchar(10) DEFAULT NULL COMMENT '文化程度',
                                              `faith_type` varchar(10) DEFAULT NULL COMMENT '宗教信仰',
                                              `vocation` varchar(50) DEFAULT NULL COMMENT '职业',
                                              `service_place` varchar(200) DEFAULT NULL COMMENT '服务场所',
                                              `contact` varchar(200) DEFAULT NULL COMMENT '联系方式',
                                              `registered_place` varchar(50) DEFAULT NULL COMMENT '户籍地',
                                              `registered_address` varchar(200) DEFAULT NULL COMMENT '户籍详细地址',
                                              `residence` varchar(50) DEFAULT NULL COMMENT '现住地',
                                              `residence_code` varchar(50) DEFAULT NULL COMMENT '现住地编码',
                                              `residence_address` varchar(200) DEFAULT NULL COMMENT '现住地详细地址',
                                              `correct_code` varchar(50) NOT NULL COMMENT '社区矫正人员编号',
                                              `detain_union` varchar(200) DEFAULT NULL COMMENT '原羁押场所',
                                              `correct_type` varchar(10) NOT NULL COMMENT '矫正类别',
                                              `case_type` varchar(10) NOT NULL COMMENT '案件类别',
                                              `charge_comments` varchar(200) NOT NULL COMMENT '具体罪名',
                                              `prison_term` varchar(2) DEFAULT NULL COMMENT '原判刑期',
                                              `prison_beagindate` date DEFAULT NULL COMMENT '原判刑开始日期',
                                              `prison_enddate` date DEFAULT NULL COMMENT '原判刑结束日期',
                                              `correct_beagindate` date NOT NULL COMMENT '矫正开始日期',
                                              `correct_enddate` date NOT NULL COMMENT '矫正结束日期',
                                              `revice_flag` varchar(10) DEFAULT NULL COMMENT '接收方式',
                                              `sishi_flag` varchar(20) DEFAULT NULL COMMENT '四史情况--可多选，|号隔开',
                                              `is_recidivist` varchar(10) DEFAULT NULL COMMENT '是否是惯犯',
                                              `sanshe_flag` varchar(20) DEFAULT NULL COMMENT '三涉情况--可多选，|号隔开',
                                              `is_team` varchar(10) DEFAULT NULL COMMENT '是否建立矫正小组',
                                              `team_guys` varchar(30) DEFAULT NULL COMMENT '矫正小组人员情况--可多选，|号隔开',
                                              `correct_remove` varchar(10) DEFAULT NULL COMMENT '矫正解除类型',
                                              `is_breakmanage` varchar(10) NOT NULL COMMENT '是否脱管',
                                              `breakmanage_reason` varchar(2000) DEFAULT NULL COMMENT '脱管原因说明',
                                              `check_comments` varchar(2000) DEFAULT NULL COMMENT '检查监督情况',
                                              `breakmanage_correct` varchar(2000) DEFAULT NULL COMMENT '脱管纠正情况',
                                              `is_omit` varchar(10) NOT NULL COMMENT '是否漏管',
                                              `omit_reason` varchar(2000) NOT NULL COMMENT '漏管原因',
                                              `check_omit` varchar(2000) DEFAULT NULL COMMENT '检查漏管情况',
                                              `omit_correct` varchar(2000) DEFAULT NULL COMMENT '漏管纠正情况',
                                              `bonus_penalty` varchar(2000) DEFAULT NULL COMMENT '奖惩情况',
                                              `prison_change` varchar(2000) DEFAULT NULL COMMENT '刑罚变更执行情况',
                                              `is_again` varchar(10) NOT NULL COMMENT '是否重新犯罪',
                                              `again_charge` varchar(10) NOT NULL COMMENT '重新犯罪罪名',
                                              `eff_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',
                                              `exp_date` datetime NOT NULL DEFAULT '3000-01-01 00:00:00' COMMENT '失效时间',
                                              `status` varchar(4) DEFAULT '0' COMMENT '同步状态',
                                              `status_cd` varchar(4) DEFAULT NULL COMMENT '数据状态 10是保存待提交 12是生效状态 22是失效',
                                              `oper_name` varchar(20) DEFAULT NULL COMMENT '操作人名称',
                                              `oper_date` datetime DEFAULT NULL COMMENT '操作时间',
                                              `creator` varchar(100) DEFAULT NULL COMMENT '创建人',
                                              `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                              `unit_code` varchar(100) DEFAULT NULL COMMENT '单位编码,所属单位，后续可用于权限管理',
                                              PRIMARY KEY (`correct_id`) USING BTREE,
                                              UNIQUE KEY `identity_num` (`identity_num`) USING BTREE,
                                              KEY `person_name` (`person_name`,`identity_num`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='特殊人群社区矫正人群基础信息表';

-- ----------------------------
-- Table structure for biz_special_drug_person
-- ----------------------------
DROP TABLE IF EXISTS `biz_special_drug_person`;
CREATE TABLE `biz_special_drug_person` (
                                           `drug_id` varchar(50) NOT NULL COMMENT 'ID，uuid()赋值',
                                           `person_name` varchar(50) NOT NULL COMMENT '姓名',
                                           `used_name` varchar(50) NOT NULL COMMENT '曾用名',
                                           `person_sex` varchar(10) DEFAULT NULL COMMENT '性别',
                                           `date_birth` date DEFAULT NULL COMMENT '出生日期',
                                           `nation` varchar(10) NOT NULL COMMENT '民族',
                                           `identity_num` varchar(50) NOT NULL COMMENT '身份证号',
                                           `native_info` varchar(50) DEFAULT NULL COMMENT '籍贯',
                                           `marriage_flag` varchar(10) DEFAULT NULL COMMENT '婚姻状况',
                                           `party_flag` varchar(10) DEFAULT NULL COMMENT '政治面貌',
                                           `edu_level` varchar(10) DEFAULT NULL COMMENT '文化程度',
                                           `faith_type` varchar(10) DEFAULT NULL COMMENT '宗教信仰',
                                           `vocation` varchar(50) DEFAULT NULL COMMENT '职业',
                                           `service_place` varchar(200) DEFAULT NULL COMMENT '服务场所',
                                           `contact` varchar(200) DEFAULT NULL COMMENT '联系方式',
                                           `registered_place` varchar(50) DEFAULT NULL COMMENT '户籍地',
                                           `registered_address` varchar(200) DEFAULT NULL COMMENT '户籍详细地址',
                                           `residence` varchar(50) DEFAULT NULL COMMENT '现住地',
                                           `residence_code` varchar(50) DEFAULT NULL COMMENT '现住地编码',
                                           `residence_address` varchar(200) DEFAULT NULL COMMENT '现住地详细地址',
                                           `find_date` date NOT NULL COMMENT '初次发现日期',
                                           `manage_type` varchar(10) NOT NULL COMMENT '管控情况',
                                           `manager_name` varchar(50) NOT NULL COMMENT '管控人姓名',
                                           `manager_address` varchar(50) NOT NULL COMMENT '管控人联系方式',
                                           `helpe_comment` varchar(2000) DEFAULT NULL COMMENT '帮扶情况',
                                           `helper_name` varchar(50) NOT NULL COMMENT '帮扶人姓名',
                                           `helper_address` varchar(50) NOT NULL COMMENT '帮扶人联系方式',
                                           `is_pedigree` varchar(10) DEFAULT NULL COMMENT '是否有犯罪史',
                                           `crime_comment` varchar(2000) DEFAULT NULL COMMENT '犯罪情况',
                                           `drug_reason` varchar(10) DEFAULT NULL COMMENT '吸毒原因',
                                           `drug_result` varchar(10) DEFAULT NULL COMMENT '吸毒后果',
                                           `eff_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',
                                           `exp_date` datetime NOT NULL DEFAULT '3000-01-01 00:00:00' COMMENT '失效时间',
                                           `status` varchar(4) DEFAULT '0' COMMENT '同步状态',
                                           `status_cd` varchar(4) DEFAULT NULL COMMENT '数据状态 10是保存待提交 12是生效状态 22是失效',
                                           `oper_name` varchar(20) DEFAULT NULL COMMENT '操作人名称',
                                           `oper_date` datetime DEFAULT NULL COMMENT '操作时间',
                                           `creator` varchar(100) DEFAULT NULL COMMENT '创建人',
                                           `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                           `unit_code` varchar(100) DEFAULT NULL COMMENT '单位编码,所属单位，后续可用于权限管理',
                                           PRIMARY KEY (`drug_id`) USING BTREE,
                                           UNIQUE KEY `identity_num` (`identity_num`) USING BTREE,
                                           KEY `person_name` (`person_name`,`identity_num`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='特殊人群吸毒人员基础信息表';

-- ----------------------------
-- Table structure for biz_special_psychosis_person
-- ----------------------------
DROP TABLE IF EXISTS `biz_special_psychosis_person`;
CREATE TABLE `biz_special_psychosis_person` (
                                                `psychosis_id` varchar(50) NOT NULL COMMENT 'ID，uuid()赋值',
                                                `person_name` varchar(50) NOT NULL COMMENT '姓名',
                                                `used_name` varchar(50) NOT NULL COMMENT '曾用名',
                                                `person_sex` varchar(10) DEFAULT NULL COMMENT '性别',
                                                `date_birth` date DEFAULT NULL COMMENT '出生日期',
                                                `nation` varchar(10) NOT NULL COMMENT '民族',
                                                `identity_num` varchar(50) NOT NULL COMMENT '身份证号',
                                                `native_info` varchar(50) DEFAULT NULL COMMENT '籍贯',
                                                `marriage_flag` varchar(10) DEFAULT NULL COMMENT '婚姻状况',
                                                `party_flag` varchar(10) DEFAULT NULL COMMENT '政治面貌',
                                                `edu_level` varchar(10) DEFAULT NULL COMMENT '文化程度',
                                                `faith_type` varchar(10) DEFAULT NULL COMMENT '宗教信仰',
                                                `vocation` varchar(50) DEFAULT NULL COMMENT '职业',
                                                `service_place` varchar(200) DEFAULT NULL COMMENT '服务场所',
                                                `contact` varchar(200) DEFAULT NULL COMMENT '联系方式',
                                                `registered_place` varchar(50) DEFAULT NULL COMMENT '户籍地',
                                                `registered_address` varchar(200) DEFAULT NULL COMMENT '户籍详细地址',
                                                `residence` varchar(50) DEFAULT NULL COMMENT '现住地',
                                                `residence_code` varchar(50) DEFAULT NULL COMMENT '现住地编码',
                                                `residence_address` varchar(200) DEFAULT NULL COMMENT '现住地详细地址',
                                                `source_income` varchar(10) DEFAULT NULL COMMENT '家庭经济情况',
                                                `is_basicliving` varchar(10) NOT NULL COMMENT '是否纳入低保',
                                                `guarder_identity` varchar(50) DEFAULT NULL COMMENT '监护人身份证号',
                                                `guarder_name` varchar(50) NOT NULL COMMENT '监护人姓名',
                                                `guarder_address` varchar(50) NOT NULL COMMENT '监护人联系方式',
                                                `attack_date` date DEFAULT NULL COMMENT '初次发病日期',
                                                `diagnose_type` varchar(10) NOT NULL COMMENT '诊断类型',
                                                `is_trouble` varchar(10) NOT NULL COMMENT '有无肇祸史',
                                                `trouble_times` int(4) DEFAULT NULL COMMENT '肇祸次数',
                                                `last_trouble` varchar(50) DEFAULT NULL COMMENT '上次肇祸日期',
                                                `risk_level` varchar(10) NOT NULL COMMENT '危险性评估等级',
                                                `treat_flag` varchar(10) NOT NULL COMMENT '治疗情况',
                                                `reat_hospital` varchar(50) DEFAULT NULL COMMENT '治疗医院名称',
                                                `inhospital_reason` varchar(20) DEFAULT NULL COMMENT '实施住院治疗原因  ---多选项',
                                                `revice_union` varchar(50) DEFAULT NULL COMMENT '接受康复机构名称',
                                                `join_manager` varchar(20) DEFAULT NULL COMMENT '参与管理人员  ---多选项',
                                                `helpe_flag` varchar(20) DEFAULT NULL COMMENT '帮扶情况  ---多选项',
                                                `eff_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',
                                                `exp_date` datetime NOT NULL DEFAULT '3000-01-01 00:00:00' COMMENT '失效时间',
                                                `status` varchar(4) DEFAULT '0' COMMENT '同步状态',
                                                `status_cd` varchar(4) DEFAULT NULL COMMENT '数据状态 10是保存待提交 12是生效状态 22是失效',
                                                `oper_name` varchar(20) DEFAULT NULL COMMENT '操作人名称',
                                                `oper_date` datetime DEFAULT NULL COMMENT '操作时间',
                                                `creator` varchar(100) DEFAULT NULL COMMENT '创建人',
                                                `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                                `unit_code` varchar(100) DEFAULT NULL COMMENT '单位编码,所属单位，后续可用于权限管理',
                                                PRIMARY KEY (`psychosis_id`) USING BTREE,
                                                UNIQUE KEY `identity_num` (`identity_num`) USING BTREE,
                                                KEY `person_name` (`person_name`,`identity_num`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='特殊人群有精神病肇事人员基础信息表';

-- ----------------------------
-- Table structure for biz_special_released_person
-- ----------------------------
DROP TABLE IF EXISTS `biz_special_released_person`;
CREATE TABLE `biz_special_released_person` (
                                               `released_id` varchar(50) NOT NULL COMMENT 'ID，uuid()赋值',
                                               `person_name` varchar(50) NOT NULL COMMENT '姓名',
                                               `used_name` varchar(50) NOT NULL COMMENT '曾用名',
                                               `person_sex` varchar(10) DEFAULT NULL COMMENT '性别',
                                               `date_birth` date DEFAULT NULL COMMENT '出生日期',
                                               `nation` varchar(10) NOT NULL COMMENT '民族',
                                               `identity_num` varchar(50) NOT NULL COMMENT '身份证号',
                                               `native_info` varchar(50) DEFAULT NULL COMMENT '籍贯',
                                               `marriage_flag` varchar(10) DEFAULT NULL COMMENT '婚姻状况',
                                               `party_flag` varchar(10) DEFAULT NULL COMMENT '政治面貌',
                                               `edu_level` varchar(10) DEFAULT NULL COMMENT '文化程度',
                                               `faith_type` varchar(10) DEFAULT NULL COMMENT '宗教信仰',
                                               `vocation` varchar(50) DEFAULT NULL COMMENT '职业',
                                               `service_place` varchar(200) DEFAULT NULL COMMENT '服务场所',
                                               `contact` varchar(200) DEFAULT NULL COMMENT '联系方式',
                                               `registered_place` varchar(50) DEFAULT NULL COMMENT '户籍地',
                                               `registered_address` varchar(200) DEFAULT NULL COMMENT '户籍详细地址',
                                               `residence` varchar(50) DEFAULT NULL COMMENT '现住地',
                                               `residence_code` varchar(50) DEFAULT NULL COMMENT '现住地编码',
                                               `residence_address` varchar(200) DEFAULT NULL COMMENT '现住地详细地址',
                                               `is_pedigree` varchar(10) NOT NULL COMMENT '是否有犯罪史',
                                               `charge_comments` varchar(10) NOT NULL COMMENT '原罪名',
                                               `prison_term` varchar(4) NOT NULL COMMENT '原判刑期',
                                               `detain_union` varchar(200) NOT NULL COMMENT '服刑场所',
                                               `prison_enddate` date NOT NULL COMMENT '释放结束日期',
                                               `risk_type` varchar(10) NOT NULL COMMENT '危险性评估类型',
                                               `join_date` date NOT NULL COMMENT '衔接日期',
                                               `join_flag` varchar(10) NOT NULL COMMENT '衔接情况',
                                               `arrange_date` date NOT NULL COMMENT '安置日期',
                                               `arrange_flag` varchar(10) NOT NULL COMMENT '安置情况',
                                               `unarrange_reason` varchar(2000) DEFAULT NULL COMMENT '未安置原因',
                                               `helpe_comment` varchar(2000) DEFAULT NULL COMMENT '帮扶情况',
                                               `is_again` varchar(10) NOT NULL COMMENT '是否重新犯罪',
                                               `again_charge` varchar(200) DEFAULT NULL COMMENT '重新犯罪罪名',
                                               `eff_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',
                                               `exp_date` datetime NOT NULL DEFAULT '3000-01-01 00:00:00' COMMENT '失效时间',
                                               `status` varchar(4) DEFAULT '0' COMMENT '同步状态',
                                               `status_cd` varchar(4) DEFAULT NULL COMMENT '数据状态 10是保存待提交 12是生效状态 22是失效',
                                               `oper_name` varchar(20) DEFAULT NULL COMMENT '操作人名称',
                                               `oper_date` datetime DEFAULT NULL COMMENT '操作时间',
                                               `creator` varchar(100) DEFAULT NULL COMMENT '创建人',
                                               `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                               `unit_code` varchar(100) DEFAULT NULL COMMENT '单位编码,所属单位，后续可用于权限管理',
                                               PRIMARY KEY (`released_id`) USING BTREE,
                                               UNIQUE KEY `identity_num` (`identity_num`) USING BTREE,
                                               KEY `person_name` (`person_name`,`identity_num`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='特殊人群刑满释放人员基础信息表';

-- ----------------------------
-- Table structure for biz_teenager_baseinfo
-- ----------------------------
DROP TABLE IF EXISTS `biz_teenager_baseinfo`;
CREATE TABLE `biz_teenager_baseinfo` (
                                         `teen_id` varchar(50) NOT NULL COMMENT 'ID，uuid()赋值',
                                         `identity_num` varchar(18) DEFAULT NULL COMMENT '身份证号:编码应符合GB/T11643',
                                         `person_name` varchar(50) DEFAULT NULL COMMENT '姓名',
                                         `used_name` varchar(50) DEFAULT NULL COMMENT '曾用名',
                                         `person_sex` varchar(1) DEFAULT NULL COMMENT '性别:编码应符合GB/T2261.1',
                                         `date_birth` date DEFAULT NULL COMMENT '出生日期:格式为“YYYYMMDD”',
                                         `nation` varchar(2) DEFAULT NULL COMMENT '民族:编码应符合GB/T3304',
                                         `native_info` varchar(6) DEFAULT NULL COMMENT '籍贯:编码应符合GB/T2260',
                                         `marriage_flag` varchar(2) DEFAULT NULL COMMENT '婚姻状况:编码应符合GB/T2261.2',
                                         `party_flag` varchar(2) DEFAULT NULL COMMENT '政治面貌:编码应符合GB/T4762',
                                         `education_bg` varchar(2) DEFAULT NULL COMMENT '学历:编码应符合GB/T4658',
                                         `faith_type` varchar(2) DEFAULT NULL COMMENT '宗教信仰:编码应符合GA214.12',
                                         `vocation_code` varchar(5) DEFAULT NULL COMMENT '职业类别:编码应符合GB/T6565',
                                         `vocation` varchar(30) DEFAULT NULL COMMENT '职业',
                                         `service_addr` varchar(100) DEFAULT NULL COMMENT '服务处所',
                                         `contact` varchar(50) DEFAULT NULL COMMENT '联系方式:手机号码或固定电话号码',
                                         `registered_place` varchar(6) DEFAULT NULL COMMENT '户籍地:编码应符合GB/T2260',
                                         `registered_addr` varchar(80) DEFAULT NULL COMMENT '户籍门（楼）详址',
                                         `residence` varchar(6) DEFAULT NULL COMMENT '现住地:编码应符合GB/T2260',
                                         `residence_addr` varchar(80) DEFAULT NULL COMMENT '现住门（楼）详址',
                                         `people_type` varchar(2) DEFAULT NULL COMMENT '人员类型',
                                         `home_situ` varchar(2) DEFAULT NULL COMMENT '家庭情况',
                                         `guardian_idno` varchar(18) DEFAULT NULL COMMENT '监护人公民身份号码:编码应符合GB/T11643',
                                         `guardian_name` varchar(50) DEFAULT NULL COMMENT '监护人姓名',
                                         `guardian_rela` varchar(2) DEFAULT NULL COMMENT '与监护人关系:编码应符合GB/T4761',
                                         `guardian_mobile` varchar(50) DEFAULT NULL COMMENT '监护人联系方式:手机号码或固定电话号码',
                                         `guardian_addr` varchar(200) DEFAULT NULL COMMENT '监护人居住详址',
                                         `if_illegal` int(1) DEFAULT NULL COMMENT '是否违法犯罪:1是0否',
                                         `illegal_situ` varchar(1024) DEFAULT NULL COMMENT '违法犯罪情况',
                                         `helpe_name` varchar(50) DEFAULT NULL COMMENT '帮扶人姓名',
                                         `helpe_mobile` varchar(50) DEFAULT NULL COMMENT '帮扶人联系方式:手机号码或固定电话号码',
                                         `helpe_method` varchar(2) DEFAULT NULL COMMENT '帮扶手段',
                                         `helpe_comment` varchar(1024) DEFAULT NULL COMMENT '帮扶情况',
                                         `eff_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',
                                         `exp_date` datetime NOT NULL DEFAULT '3000-01-01 00:00:00' COMMENT '失效时间',
                                         `status` varchar(4) DEFAULT '0' COMMENT '同步状态',
                                         `status_cd` varchar(4) DEFAULT NULL COMMENT '数据状态 10是保存待提交 12是生效状态 22是失效',
                                         `oper_name` varchar(20) DEFAULT NULL COMMENT '操作人名称',
                                         `oper_date` datetime DEFAULT NULL COMMENT '操作时间',
                                         `creator` varchar(100) DEFAULT NULL COMMENT '创建人',
                                         `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                         `unit_code` varchar(100) DEFAULT NULL COMMENT '单位编码,所属单位，后续可用于权限管理',
                                         PRIMARY KEY (`teen_id`) USING BTREE,
                                         UNIQUE KEY `identity_num` (`identity_num`) USING BTREE,
                                         KEY `person_name` (`person_name`,`identity_num`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='重点青少年基本信息';

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
                        `id` varchar(255) NOT NULL COMMENT 'ID',
                        `name` varchar(255) NOT NULL COMMENT '名称',
                        `pid` varchar(255) NOT NULL COMMENT '上级部门',
                        `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `enabled` bit(1) NOT NULL COMMENT '是否启用',
                        `CODE` varchar(255) DEFAULT NULL COMMENT '部门代码_',
                        `CONTACT` varchar(255) DEFAULT NULL COMMENT '机构地址',
                        `PHONE` varchar(255) DEFAULT NULL COMMENT '机构电话',
                        `GRAGE` varchar(255) DEFAULT NULL COMMENT '层级',
                        `PARENT_ID` varchar(255) DEFAULT NULL COMMENT '父机构id，跟pid保持一致',
                        `SEQ` varchar(255) DEFAULT NULL COMMENT '排序',
                        `DEP_ABB` varchar(255) DEFAULT NULL COMMENT '部门别名',
                        `NOTE` varchar(255) DEFAULT NULL,
                        `AMOUNT` varchar(255) DEFAULT NULL COMMENT '人员数量',
                        `N_LAST_UPDATE_TIME` varchar(255) DEFAULT NULL,
                        `DELETE_FLAG` varchar(255) DEFAULT NULL COMMENT '删除标志1删除，0有效',
                        `parent_code` varchar(255) DEFAULT NULL COMMENT '父机构编码',
                        `sqe` varchar(255) DEFAULT NULL,
                        `credit_code` varchar(255) DEFAULT NULL COMMENT '统一社会信用代码',
                        `creator` varchar(100) DEFAULT NULL COMMENT '创建人',
                        `modifier` varchar(100) DEFAULT NULL COMMENT '修改人',
                        `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
                        `insti_type` varchar(2) DEFAULT NULL COMMENT '机构类型，字典',
                        `organ_type` varchar(2) DEFAULT NULL COMMENT '组织类型，字典',
                        `guide_unit` varchar(200) DEFAULT NULL COMMENT '业务指导部门',
                        `function` varchar(1024) DEFAULT NULL COMMENT '主要职能说明',
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构单位表';

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict` (
                        `id` bigint(11) NOT NULL AUTO_INCREMENT,
                        `name` varchar(255) NOT NULL COMMENT '字典名称',
                        `remark` varchar(255) DEFAULT NULL COMMENT '描述',
                        `if_valid` int(11) DEFAULT '1' COMMENT '是否有效1有效0无效',
                        `creator` varchar(100) DEFAULT NULL COMMENT '创建人',
                        `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `modifier` varchar(100) DEFAULT NULL COMMENT '修改人',
                        `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `unit_code` varchar(100) DEFAULT NULL COMMENT '单位编码',
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=263 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `dict_detail`;
CREATE TABLE `dict_detail` (
                               `id` bigint(11) NOT NULL AUTO_INCREMENT,
                               `label` varchar(255) NOT NULL COMMENT '字典标签',
                               `value` varchar(255) NOT NULL COMMENT '字典值',
                               `sort` varchar(255) DEFAULT NULL COMMENT '排序',
                               `dict_id` bigint(11) DEFAULT NULL COMMENT '字典id',
                               `pid` bigint(11) DEFAULT NULL COMMENT '父id',
                               `if_valid` int(11) DEFAULT '1' COMMENT '有效标识1有效0无效',
                               `creator` varchar(100) DEFAULT NULL COMMENT '创建人',
                               `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `modifier` varchar(100) DEFAULT NULL COMMENT '修改人',
                               `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `unit_code` varchar(100) DEFAULT NULL COMMENT '单位编码',
                               PRIMARY KEY (`id`) USING BTREE,
                               KEY `FK5tpkputc6d9nboxojdbgnpmyb` (`dict_id`) USING BTREE,
                               CONSTRAINT `FK5tpkputc6d9nboxojdbgnpmyb` FOREIGN KEY (`dict_id`) REFERENCES `dict` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5411 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for email_config
-- ----------------------------
DROP TABLE IF EXISTS `email_config`;
CREATE TABLE `email_config` (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                `from_user` varchar(255) DEFAULT NULL COMMENT '收件人',
                                `host` varchar(255) DEFAULT NULL COMMENT '邮件服务器SMTP地址',
                                `pass` varchar(255) DEFAULT NULL COMMENT '密码',
                                `port` varchar(255) DEFAULT NULL COMMENT '端口',
                                `user` varchar(255) DEFAULT NULL COMMENT '发件者用户名',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for gen_config
-- ----------------------------
DROP TABLE IF EXISTS `gen_config`;
CREATE TABLE `gen_config` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                              `author` varchar(255) DEFAULT NULL COMMENT '作者',
                              `cover` bit(1) DEFAULT NULL COMMENT '是否覆盖',
                              `module_name` varchar(255) DEFAULT NULL COMMENT '模块名称',
                              `pack` varchar(255) DEFAULT NULL COMMENT '至于哪个包下',
                              `path` varchar(255) DEFAULT NULL COMMENT '前端代码生成的路径',
                              `api_path` varchar(255) DEFAULT NULL,
                              `prefix` varchar(255) DEFAULT NULL,
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for job
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job` (
                       `id` bigint(20) NOT NULL AUTO_INCREMENT,
                       `name` varchar(255) NOT NULL,
                       `enabled` bit(1) NOT NULL,
                       `create_time` datetime DEFAULT NULL,
                       `sort` bigint(20) NOT NULL,
                       `dept_id` varchar(255) DEFAULT NULL,
                       PRIMARY KEY (`id`) USING BTREE,
                       KEY `FKmvhj0rogastlctflsxf1d6k3i` (`dept_id`) USING BTREE,
                       CONSTRAINT `FKmvhj0rogastlctflsxf1d6k3i` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
                       `id` bigint(20) NOT NULL AUTO_INCREMENT,
                       `create_time` datetime DEFAULT NULL,
                       `description` varchar(255) DEFAULT NULL,
                       `exception_detail` text,
                       `log_type` varchar(255) DEFAULT NULL,
                       `method` varchar(255) DEFAULT NULL,
                       `params` text,
                       `request_ip` varchar(255) DEFAULT NULL,
                       `time` bigint(20) DEFAULT NULL,
                       `username` varchar(255) DEFAULT NULL,
                       `address` varchar(255) DEFAULT NULL,
                       PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11092 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                        `create_time` datetime DEFAULT NULL COMMENT '创建日期',
                        `i_frame` bit(1) DEFAULT NULL COMMENT '是否外链',
                        `name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
                        `component` varchar(255) DEFAULT NULL COMMENT '组件',
                        `pid` bigint(20) NOT NULL COMMENT '上级菜单ID',
                        `sort` bigint(20) NOT NULL COMMENT '排序',
                        `icon` varchar(255) DEFAULT NULL COMMENT '图标',
                        `path` varchar(255) DEFAULT NULL COMMENT '链接地址',
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                              `alias` varchar(255) DEFAULT NULL COMMENT '别名',
                              `create_time` datetime DEFAULT NULL COMMENT '创建日期',
                              `name` varchar(255) DEFAULT NULL COMMENT '名称',
                              `pid` int(11) NOT NULL COMMENT '上级权限',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for picture
-- ----------------------------
DROP TABLE IF EXISTS `picture`;
CREATE TABLE `picture` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                           `create_time` datetime DEFAULT NULL COMMENT '上传日期',
                           `delete_url` varchar(255) DEFAULT NULL COMMENT '删除的URL',
                           `filename` varchar(255) DEFAULT NULL COMMENT '图片名称',
                           `height` varchar(255) DEFAULT NULL COMMENT '图片高度',
                           `size` varchar(255) DEFAULT NULL COMMENT '图片大小',
                           `url` varchar(255) DEFAULT NULL COMMENT '图片地址',
                           `username` varchar(255) DEFAULT NULL COMMENT '用户名称',
                           `width` varchar(255) DEFAULT NULL COMMENT '图片宽度',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `quartz_job`;
CREATE TABLE `quartz_job` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                              `bean_name` varchar(255) DEFAULT NULL COMMENT 'Spring Bean名称',
                              `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron 表达式',
                              `is_pause` bit(1) DEFAULT NULL COMMENT '状态：1暂停、0启用',
                              `job_name` varchar(255) DEFAULT NULL COMMENT '任务名称',
                              `method_name` varchar(255) DEFAULT NULL COMMENT '方法名称',
                              `params` varchar(255) DEFAULT NULL COMMENT '参数',
                              `remark` varchar(255) DEFAULT NULL COMMENT '备注',
                              `update_time` datetime DEFAULT NULL COMMENT '创建或更新日期',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for quartz_log
-- ----------------------------
DROP TABLE IF EXISTS `quartz_log`;
CREATE TABLE `quartz_log` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT,
                              `baen_name` varchar(255) DEFAULT NULL,
                              `create_time` datetime DEFAULT NULL,
                              `cron_expression` varchar(255) DEFAULT NULL,
                              `exception_detail` text,
                              `is_success` bit(1) DEFAULT NULL,
                              `job_name` varchar(255) DEFAULT NULL,
                              `method_name` varchar(255) DEFAULT NULL,
                              `params` varchar(255) DEFAULT NULL,
                              `time` bigint(20) DEFAULT NULL,
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                        `create_time` datetime DEFAULT NULL COMMENT '创建日期',
                        `name` varchar(255) NOT NULL COMMENT '名称',
                        `remark` varchar(255) DEFAULT NULL COMMENT '备注',
                        `data_scope` varchar(255) DEFAULT NULL,
                        `level` int(255) DEFAULT NULL,
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for roles_depts
-- ----------------------------
DROP TABLE IF EXISTS `roles_depts`;
CREATE TABLE `roles_depts` (
                               `role_id` bigint(20) NOT NULL,
                               `dept_id` varchar(255) NOT NULL,
                               PRIMARY KEY (`role_id`,`dept_id`) USING BTREE,
                               KEY `FK7qg6itn5ajdoa9h9o78v9ksur` (`dept_id`) USING BTREE,
                               CONSTRAINT `FK7qg6itn5ajdoa9h9o78v9ksur` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`),
                               CONSTRAINT `FKrg1ci4cxxfbja0sb0pddju7k` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for roles_menus
-- ----------------------------
DROP TABLE IF EXISTS `roles_menus`;
CREATE TABLE `roles_menus` (
                               `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
                               `role_id` bigint(20) NOT NULL COMMENT '角色ID',
                               PRIMARY KEY (`menu_id`,`role_id`) USING BTREE,
                               KEY `FKcngg2qadojhi3a651a5adkvbq` (`role_id`) USING BTREE,
                               CONSTRAINT `FKcngg2qadojhi3a651a5adkvbq` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
                               CONSTRAINT `FKq1knxf8ykt26we8k331naabjx` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for roles_permissions
-- ----------------------------
DROP TABLE IF EXISTS `roles_permissions`;
CREATE TABLE `roles_permissions` (
                                     `role_id` bigint(20) NOT NULL COMMENT '角色ID',
                                     `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
                                     PRIMARY KEY (`role_id`,`permission_id`) USING BTREE,
                                     KEY `FKboeuhl31go7wer3bpy6so7exi` (`permission_id`) USING BTREE,
                                     CONSTRAINT `FK4hrolwj4ned5i7qe8kyiaak6m` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
                                     CONSTRAINT `FKboeuhl31go7wer3bpy6so7exi` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` varchar(255) NOT NULL COMMENT 'ID',
                        `avatar` varchar(255) DEFAULT NULL COMMENT '头像地址',
                        `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
                        `enabled` bigint(20) DEFAULT NULL COMMENT '状态：1启用、0禁用',
                        `password` varchar(255) DEFAULT NULL COMMENT '密码',
                        `username` varchar(255) DEFAULT NULL COMMENT '用户名',
                        `last_password_reset_time` datetime DEFAULT NULL COMMENT '最后修改密码的日期',
                        `dept_id` varchar(255) DEFAULT NULL COMMENT '机构id',
                        `phone` varchar(255) DEFAULT NULL COMMENT '联系方式',
                        `job_id` bigint(20) DEFAULT NULL COMMENT '工作id',
                        `SEX` varchar(255) DEFAULT NULL COMMENT '性别',
                        `MOBILE` varchar(255) DEFAULT NULL COMMENT '手机号码',
                        `COMM_TYPE` varchar(255) DEFAULT NULL,
                        `SMOBILE` varchar(255) DEFAULT NULL,
                        `CODE` varchar(255) DEFAULT NULL COMMENT '人员编号',
                        `IDENTIFIER` varchar(255) DEFAULT NULL COMMENT '身份证号',
                        `DEP_CODE` varchar(255) DEFAULT NULL COMMENT '机构编码',
                        `GRADE` varchar(255) DEFAULT NULL COMMENT '人员级别，字典',
                        `POLICE` varchar(255) DEFAULT NULL COMMENT '警种',
                        `POSITION` varchar(255) DEFAULT NULL COMMENT '职务，字典',
                        `OFFICE_PHONE` varchar(255) DEFAULT NULL COMMENT '固定电话，办公电话',
                        `NAME_BRIEF_SPELL` varchar(255) DEFAULT NULL COMMENT '姓名简拼',
                        `SEQ` varchar(255) DEFAULT NULL COMMENT '排序',
                        `NOTE` varchar(255) DEFAULT NULL COMMENT '其他说明，比如职务，字典项没有的 可以备注说明',
                        `N_LAST_UPDATE_TIME` varchar(255) DEFAULT NULL,
                        `DELETE_FLAG` varchar(255) DEFAULT NULL COMMENT '删除标志1删除0有效',
                        `PERSONTYPE` varchar(255) DEFAULT NULL COMMENT '人员类型',
                        `JXFLAG` varchar(255) DEFAULT NULL,
                        `DISPLAY_FLAG` varchar(255) DEFAULT NULL COMMENT '显示标志1显示0不显示',
                        `dep_id` varchar(255) DEFAULT NULL,
                        `national` varchar(2) DEFAULT NULL COMMENT '民族，字典',
                        `political_status` varchar(2) DEFAULT NULL COMMENT '政治面貌，字典',
                        `birth` date DEFAULT NULL COMMENT '出生日期，yyyymmdd',
                        `specialty` varchar(2) DEFAULT NULL COMMENT '专业特长，字典',
                        `education_bg` varchar(2) DEFAULT NULL COMMENT '学历，字典',
                        `card_code` varchar(3) DEFAULT NULL COMMENT '证件代码，字典',
                        `card_number` varchar(30) DEFAULT NULL COMMENT '证件号码',
                        `creator` varchar(100) DEFAULT NULL COMMENT '创建人',
                        `modifier` varchar(100) DEFAULT NULL COMMENT '修改人',
                        `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
                        PRIMARY KEY (`id`) USING BTREE,
                        UNIQUE KEY `UK_kpubos9gc2cvtkb0thktkbkes` (`email`) USING BTREE,
                        UNIQUE KEY `username` (`username`) USING BTREE,
                        KEY `FK5rwmryny6jthaaxkogownknqp` (`dept_id`) USING BTREE,
                        KEY `FKfftoc2abhot8f2wu6cl9a5iky` (`job_id`) USING BTREE,
                        CONSTRAINT `FK5rwmryny6jthaaxkogownknqp` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`),
                        CONSTRAINT `FKfftoc2abhot8f2wu6cl9a5iky` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员用户表';

-- ----------------------------
-- Table structure for users_roles
-- ----------------------------
DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles` (
                               `user_id` varchar(255) NOT NULL COMMENT '用户ID',
                               `role_id` bigint(20) NOT NULL COMMENT '角色ID',
                               PRIMARY KEY (`user_id`,`role_id`) USING BTREE,
                               KEY `FKq4eq273l04bpu4efj0jd0jb98` (`role_id`) USING BTREE,
                               CONSTRAINT `users_roles_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
                               CONSTRAINT `users_roles_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for verification_code
-- ----------------------------
DROP TABLE IF EXISTS `verification_code`;
CREATE TABLE `verification_code` (
                                     `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                     `code` varchar(255) DEFAULT NULL COMMENT '验证码',
                                     `create_time` datetime DEFAULT NULL COMMENT '创建日期',
                                     `status` bit(1) DEFAULT NULL COMMENT '状态：1有效、0过期',
                                     `type` varchar(255) DEFAULT NULL COMMENT '验证码类型：email或者短信',
                                     `value` varchar(255) DEFAULT NULL COMMENT '接收邮箱或者手机号码',
                                     `scenes` varchar(255) DEFAULT NULL COMMENT '业务名称：如重置邮箱、重置密码等',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for visits
-- ----------------------------
DROP TABLE IF EXISTS `visits`;
CREATE TABLE `visits` (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT,
                          `create_time` datetime DEFAULT NULL,
                          `date` varchar(255) DEFAULT NULL,
                          `ip_counts` bigint(20) DEFAULT NULL,
                          `pv_counts` bigint(20) DEFAULT NULL,
                          `week_day` varchar(255) DEFAULT NULL,
                          PRIMARY KEY (`id`) USING BTREE,
                          UNIQUE KEY `UK_11aksgq87euk9bcyeesfs4vtp` (`date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
