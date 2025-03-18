/*
Navicat MySQL Data Transfer

Source Server         : 112.44.102.75
Source Server Version : 80027
Source Host           : 112.44.102.75:3306
Source Database       : alg_git

Target Server Type    : MYSQL
Target Server Version : 80027
File Encoding         : 65001

Date: 2025-03-18 16:59:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for alarm_data
-- ----------------------------
DROP TABLE IF EXISTS `alarm_data`;
CREATE TABLE `alarm_data` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `alarm_type` bigint DEFAULT NULL,
  `alarm_image` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `alarm_time` bigint DEFAULT NULL,
  `task_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `model_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `algorithmModel_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `customer_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `customer_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of alarm_data
-- ----------------------------

-- ----------------------------
-- Table structure for algorithm_model
-- ----------------------------
DROP TABLE IF EXISTS `algorithm_model`;
CREATE TABLE `algorithm_model` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `model_key` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `model_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `algorithm_type` bigint DEFAULT NULL,
  `core_tech` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `shell_key` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `latestTraining_time` bigint DEFAULT NULL,
  `online_time` bigint DEFAULT NULL,
  `conf_threshold` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `nms_threshold` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `label_list` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `oos_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `order_num` int DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of algorithm_model
-- ----------------------------
INSERT INTO `algorithm_model` VALUES ('1', '吸烟检测', 'smoke_detec', 'w3bvCceu', '112', 'cnn', 'yolov8_detect_tensorrt', '1701014400000', '1701100800000', '0.5', '0.5', '[\"吸烟\",\"未吸烟\"]', '/algorithmmodel/bbea322ad6464615986e029745c9.engine', '6', '0', '1700644344986');
INSERT INTO `algorithm_model` VALUES ('2', '安全帽检测', 'yolov8_safety_hat', 'p3dCKdc5', '111', 'knn', 'yolov8_detect_tensorrt', '1700616808000', '1700668800000', '0.5', '0.5', '[\"helmet\",\"no-helmet\"]', 'http://192.168.2.241:9001/default/example.engine', '3', '1', '1700646457621');
INSERT INTO `algorithm_model` VALUES ('3', '人车检测', 'yolov8n', 'sj9hds2b', '101', 'cnn', 'yolov8_detect_tensorrt', '1701187200000', '1701273600000', '0.5', '0.5', '[\"person\",\"bicycle\",\"car\",\"motorcycle\",\"airplane\",\"bus\",\"train\",\"truck\",\"boat\",\"trafficlight\",\"firehydrant\",\"stopsign\",\"parkingmeter\",\"bench\",\"bird\",\"cat\",\"dog\",\"horse\",\"sheep\",\"cow\",\"elephant\",\"bear\",\"zebra\",\"giraffe\",\"backpack\",\"umbrella\",\"handbag\",\"tie\",\"suitcase\",\"frisbee\",\"skis\",\"snowboard\",\"sportsball\",\"kite\",\"baseballbat\",\"baseballglove\",\"skateboard\",\"surfboard\",\"tennisracket\",\"bottle\",\"wineglass\",\"cup\",\"fork\",\"knife\",\"spoon\",\"bowl\",\"banana\",\"apple\",\"sandwich\",\"orange\",\"broccoli\",\"carrot\",\"hotdog\",\"pizza\",\"donut\",\"cake\",\"chair\",\"couch\",\"pottedplant\",\"bed\",\"diningtable\",\"toilet\",\"tv\",\"laptop\",\"mouse\",\"remote\",\"keyboard\",\"cellphone\",\"microwave\",\"oven\",\"toaster\",\"sink\",\"refrigerator\",\"book\",\"clock\",\"vase\",\"scissors\",\"teddybear\",\"hairdrier\",\"toothbrush\"]', '/algorithmmodel/fbde0e3709ef4dc9bb81ecc53bb4.engine', '1', '1', '1701163417481');
INSERT INTO `algorithm_model` VALUES ('4', '烟雾检测', 'yolov8_sl_fh', 'dXgAaKGI', '104', 'CNN', 'yolov8_detect_tensorrt', '1701014400000', '1701014400000', '0.5', '0.5', '[\"smoke\",\"no-smoke\"]', '/algorithmmodel/809de03622bd442299c99e3df9d0.engine', '2', '1', '1718340228067');
INSERT INTO `algorithm_model` VALUES ('5', '电动车头盔', 'ebike', 'Ha06qSwC', '113', 'cnn', 'yolov8_detect_tensorrt', '1720713600000', '1720713600000', '0.5', '0.5', '[\"Helmet\",\"NO-Helmet\",\"Rider\"]', '/algorithmmodel/4326d5ef50524ba7aa923e63dd11.engine', '1', '1', '1720779050065');
INSERT INTO `algorithm_model` VALUES ('6', '道路墙体裂缝检测', 'crack', 'vA3rnst5', '115', 'CNN', 'yolov8_detect_tensorrt', '1721059200000', '1721059200000', '0.5', '0.5', '[\"crack\"]', '/algorithmmodel/350b6cea2eef4821bf4ef153345e.engine', '1', '1', '1721270722801');
INSERT INTO `algorithm_model` VALUES ('7', '车牌识别', 'car_plate_rec_yolov8s', 'JXWMzmLb', '116', 'CNN', 'yolov8_detect_tensorrt', '1720627200000', '1720627200000', '0.5', '0.5', '[\"CAR-PLATE\"]', '/algorithmmodel/d254559d79ea461bb82654c54ac6.engine', '1', '1', '1721276663260');

-- ----------------------------
-- Table structure for algorithm_task
-- ----------------------------
DROP TABLE IF EXISTS `algorithm_task`;
CREATE TABLE `algorithm_task` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `task_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `task_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `model_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `algorithmModel_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `customer_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `customer_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `videoBase_info` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `videoPlay_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `pushVideoPlay_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `computingVideoPlay_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `streamServer_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `skip_frame` int DEFAULT NULL,
  `push_frequency` int DEFAULT NULL,
  `conf_threshold` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `nms_threshold` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `work_dir` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `shell_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `firstExec_time` bigint DEFAULT NULL,
  `latestExec_time` bigint DEFAULT NULL,
  `alarm_amount` int DEFAULT NULL,
  `latestAlarm_time` bigint DEFAULT NULL,
  `order_num` int DEFAULT NULL,
  `task_status` tinyint DEFAULT NULL,
  `pid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `pidStart_time` bigint DEFAULT NULL,
  `pidStop_time` bigint DEFAULT NULL,
  `restart_count` int DEFAULT NULL,
  `restart_msg` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=328 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of algorithm_task
-- ----------------------------
INSERT INTO `algorithm_task` VALUES ('1', '烟火检测', 'XpKA773K', '6fz4FErL', '烟火检测', 'hymv8g1b', '测试客户1', '', '/data/app/yolo/data/sl-fh/4_699pic_01cgxr_spxy.mp4', 'ori_XpKA773K.live.flv', 'dec_XpKA773K.live.flv', 'dec_XpKA773K?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '0.5', '0.65', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1720070739468', '1721288673816', null, null, '4', '0', null, null, null, '0', null, '1720070711777');
INSERT INTO `algorithm_task` VALUES ('2', '电动车头盔', 'UDjCk34T', 'Ha06qSwC', '电动车头盔', 'bnck6b2d', '测试客户2', '', '/data/app/yolo/data/sl-fh/4_699pic_01cgxr_spxy.mp4', 'ori_UDjCk34T.live.flv', 'dec_UDjCk34T.live.flv', 'dec_UDjCk34T?sign=41db35390ddad33f83944f44b8b75ded', '3', '60', '0.5', '0.65', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1720779482918', '1722411276856', null, null, null, '0', null, null, null, '0', null, '1720779470602');
INSERT INTO `algorithm_task` VALUES ('3', '吸烟检测', 'VToib7pc', 'w3bvCceu', '吸烟检测', 'hymv8g1b', '测试客户1', null, '/data/app/yolo/data/sl-fh/4_699pic_01cgxr_spxy.mp4', 'ori_VToib7pc.live.flv', 'dec_VToib7pc.live.flv', 'dec_VToib7pc?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '0.5', '0.65', '/data/app/yolo/', 'yolov8', '1721025752076', '1728434917153', null, null, null, '0', null, null, null, '0', null, '1721025750314');
INSERT INTO `algorithm_task` VALUES ('4', '安全帽检测', 'oqHfS8K0', 'p3dCKdc5', '安全帽检测', 'bnck6b2d', '测试客户1', null, '/data/app/yolo/data/sl-fh/4_699pic_01cgxr_spxy.mp4', 'ori_oqHfS8K0.live.flv', 'dec_oqHfS8K0.live.flv', 'dec_oqHfS8K0?sign=41db35390ddad33f83944f44b8b75ded', '1', '5', '0.5', '0.65', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721209303529', '1742260023308', null, null, null, '0', null, null, null, '0', null, '1721027414104');
INSERT INTO `algorithm_task` VALUES ('5', '人车检测', 'mrn3R5lM', 'sj9hds2b', '人车检测', 'hymv8g1b', '测试客户2', null, '/data/app/yolo/data/sl-fh/4_699pic_01cgxr_spxy.mp4', 'ori_mrn3R5lM.live.flv', 'dec_mrn3R5lM.live.flv', 'dec_mrn3R5lM?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '0.5', '0.65', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721263082748', '1725021076379', null, null, null, '0', null, null, null, '0', null, '1721263081488');
INSERT INTO `algorithm_task` VALUES ('6', '烟雾检测', 'ngsREBsi', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '测试客户2', null, '/data/app/yolo/data/sl-fh/4_699pic_01cgxr_spxy.mp4', 'ori_ngsREBsi.live.flv', 'dec_ngsREBsi.live.flv', 'dec_ngsREBsi?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '0.5', '0.65', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721263358556', '1721263358556', null, null, null, '0', null, null, null, '0', null, '1721263356594');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `mobile_num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `customer_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `access_key` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `httpReq_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `httpReq_header` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `login_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `login_time` bigint DEFAULT NULL,
  `taskAmount_limit` int DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('2', '内部测试', '13552011202', 'bnck6b2d', 'lmvt5j9v', 'http://172.38.25.200:8080/api/sys/httpPushLog/receiveHttpData', '{\"zt\":\"666\"}', null, null, '10', '0', '1700474382435');

-- ----------------------------
-- Table structure for data_dict
-- ----------------------------
DROP TABLE IF EXISTS `data_dict`;
CREATE TABLE `data_dict` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `parent_id` bigint DEFAULT NULL,
  `multiple` bit(1) DEFAULT NULL,
  `mutex` int DEFAULT NULL,
  `order_num` int DEFAULT NULL,
  `selected` bit(1) DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2094 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of data_dict
-- ----------------------------
INSERT INTO `data_dict` VALUES ('5', '用户', 'USER', '0', '', '1', '2', '\0', '1', '1612346348896');
INSERT INTO `data_dict` VALUES ('6', '注册类型', 'RegType', '5', '', '1', '3', '\0', '1', '1612346348898');
INSERT INTO `data_dict` VALUES ('7', '申请中', 'Apply', '6', '', '1', '4', '\0', '1', '1612346348900');
INSERT INTO `data_dict` VALUES ('8', '正式用户', 'Office', '6', '', '1', '5', '\0', '1', '1612346348902');
INSERT INTO `data_dict` VALUES ('9', '未通过', 'NotPass', '6', '', '1', '6', '\0', '1', '1612346348903');
INSERT INTO `data_dict` VALUES ('10', '权限', 'PERMISSION', '0', '', '1', '7', '\0', '1', '1612346348905');
INSERT INTO `data_dict` VALUES ('11', '权限类型', 'PermsType', '10', '', '1', '8', '\0', '1', '1612346348907');
INSERT INTO `data_dict` VALUES ('12', '使用类型', 'UseType', '10', '', '1', '9', '\0', '1', '1612346348910');
INSERT INTO `data_dict` VALUES ('13', '链接类型', 'LinkType ', '10', '', '1', '10', '\0', '1', '1612346348913');
INSERT INTO `data_dict` VALUES ('14', '目录', 'Catalog', '11', '', '1', '11', '\0', '1', '1612346348917');
INSERT INTO `data_dict` VALUES ('15', '菜单', 'Menu', '11', '', '1', '12', '\0', '1', '1612346348919');
INSERT INTO `data_dict` VALUES ('16', '按钮', 'Button', '11', '', '1', '13', '\0', '1', '1612346348921');
INSERT INTO `data_dict` VALUES ('17', '电脑端', 'PC', '12', '', '1', '14', '\0', '1', '1612346348922');
INSERT INTO `data_dict` VALUES ('18', '手机端', 'APP', '12', '', '1', '15', '\0', '1', '1612346348924');
INSERT INTO `data_dict` VALUES ('19', '内部链接', 'Inner', '13', '', '1', '16', '\0', '1', '1612346348925');
INSERT INTO `data_dict` VALUES ('20', '外部链接', 'Out', '13', '', '1', '17', '\0', '1', '1612346348928');
INSERT INTO `data_dict` VALUES ('100', '算法类型', 'AGMTYPE', '0', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('101', '人车检测', 'AGPERSONCAR', '100', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('102', '人行检测', 'AGPERSON', '100', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('103', '汽车', 'AGCAR', '100', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('104', '烟雾', 'AGSMOKE', '100', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('105', '火焰', 'AGFIRE', '100', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('106', '烟火', 'AGSMOKEFIRE', '100', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('107', '河道漂浮物', 'AGRIVERFLOATER', '100', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('108', '城市垃圾', 'AGCITYRUBBISH', '100', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('109', '城市裸土', 'AGCITYBARELAND', '100', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('110', '城市流动摊贩', 'AGCITYPEDDLER', '100', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('111', '安全帽检测', 'AGSAFETYHELMET\n', '100', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('112', '吸烟', 'AGFUMARE', '100', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('113', '电瓶车头盔', 'DPCTK', '100', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('114', '人车计数', 'PERSONCARCOUNTER', '100', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('115', '道路墙体裂缝检测', 'DLQTLFJC', '100', '', '1', '1', '\0', '0', '1612346747808');
INSERT INTO `data_dict` VALUES ('116', '车牌识别', 'CARPLATEREC', '100', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('117', '罂粟花识别', 'YINGSHUHUA', '100', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('118', '秸秆燃烧', 'JIEGANRANSHAO', '100', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('119', '城市违章建筑', 'CITYWZJZ', '100', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('120', '砂石识别', 'SHASHI', '100', '\0', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('121', '工程渣土车', 'GCZTC', '100', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('401', '区域信息', 'REGION', '0', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('402', '区域类型', 'REGIONTYPE', '401', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('403', '国家', 'NATION', '402', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('404', '省', 'PROVINCE', '402', '', '1', '2', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('405', '市', 'CITY', '402', '', '1', '3', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('406', '区市县', 'COUNTY', '402', '', '1', '4', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('601', '日志', 'LOG', '0', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('602', '操作日志', 'OperateLog', '601', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('603', '新增', 'INSERT', '602', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('604', '更新', 'UPDATE', '602', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('605', '删除', 'DELETE', '602', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('606', '授权', 'GRANT', '602', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('607', '导入', 'IMPORT', '602', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('608', '导出', 'EXPORT', '602', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('609', '强制停止', 'FORCE', '602', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('610', '生成代码', 'GENCODE', '602', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('611', '清除', 'CLEAN', '602', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('612', '重载', 'RELOAD', '602', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('615', '付款', 'PAYMENT', '602', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('616', '退款', 'RRFUND', '602', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('617', '其他', 'OTHER', '602', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('618', '推送', 'PUSH', '602', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('620', '登录日志', 'LoginLog', '601', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('621', '注册', 'REGISTER', '620', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('622', '获取验证码', 'GETCODE', '620', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('900', '推送服务', 'HttpPushService', '0', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('901', '推送类型', 'HttpPushType', '900', '', '1', '1', '\0', '1', '1612346747808');
INSERT INTO `data_dict` VALUES ('911', '推送模型推理结果', 'HttpPushInferecenResult', '901', '', '1', '1', '\0', '1', '1612346747808');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `perms` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `router_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `component` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `icon` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `parent_id` bigint DEFAULT NULL,
  `parent_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `order_num` int DEFAULT NULL,
  `perms_type` bigint DEFAULT NULL,
  `use_type` bigint DEFAULT NULL,
  `link_type` bigint DEFAULT NULL,
  `path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `query` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3841 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '系统设置', '', 'System', 'Layout', 'Layout', 'system (1)', '0', null, '3', '14', '17', '19', '/system', null, '1', '1621839879460');
INSERT INTO `permission` VALUES ('2', '系统监控', '', 'Monitor', 'Layout', 'Layout', 'log', '0', null, '4', '14', '17', '19', 'monitor', null, '1', '1624072833191');
INSERT INTO `permission` VALUES ('3', '系统工具', '', 'Tool', 'Layout', 'Layout', 'build', '0', null, '5', '14', '17', '19', 'tool', null, '1', '1624073051113');
INSERT INTO `permission` VALUES ('101', '用户管理', 'sys:user:list', 'User', 'system/user/index', '/sys/user/index', 'user', '1', null, '4', '15', '17', '19', 'user', null, '1', '1621847013621');
INSERT INTO `permission` VALUES ('102', '角色管理', 'sys:role:list', 'Role', 'system/role/index', '/sys/role/index', 'peoples', '1', null, '3', '15', '17', '19', 'role', null, '1', '1621847013621');
INSERT INTO `permission` VALUES ('103', '菜单管理', 'sys:permisson:list', 'Menu', 'system/menu/index', '/sys/permisson/index', 'tree-table', '1', null, '5', '15', '17', '19', 'menu', null, '1', '1621845584634');
INSERT INTO `permission` VALUES ('104', '园区管理', 'sys:park:list', 'Park', 'system/park/index', '/sys/park/index', 'nested', '1', null, '2', '15', '17', '19', 'park', null, '1', '1621845584634');
INSERT INTO `permission` VALUES ('105', '部门管理', 'sys:dept:list', 'Dept', 'system/dept/index', '/sys/dept/index', 'tree', '1', null, '6', '15', '17', '19', 'dept', null, '1', '1621845584634');
INSERT INTO `permission` VALUES ('106', '职位管理', 'sys:post:list', 'Post', 'system/post/index', '/sys/post/index', 'post', '1', null, '7', '15', '17', '19', 'post', null, '1', '1621845584634');
INSERT INTO `permission` VALUES ('107', '字典管理', 'sys:dataDict:list', 'Dict', 'system/dict/index', '/sys/dataDict/index', 'dict', '1', null, '8', '15', '17', '19', 'dict', null, '1', '1621845584634');
INSERT INTO `permission` VALUES ('108', '系统设置', 'sys:param:list', 'Config', 'system/config/index', '/sys/param/index', 'bug', '1', null, '9', '15', '17', '19', 'config', null, '1', '1621845584634');
INSERT INTO `permission` VALUES ('109', '通知公告', 'sys:notice:list', 'Notice', 'system/notice/index', '/sys/notice/index', 'bug', '1', null, '10', '15', '17', '19', 'notice', null, '1', '1621845584634');
INSERT INTO `permission` VALUES ('151', '在线用户', 'monitor:online:list', 'Online', '/monitor/online/index', '/monitor/online/index', null, '2', null, '1', '15', '17', '19', 'online', null, '1', '1624072852297');
INSERT INTO `permission` VALUES ('152', '定时任务', 'monitor:job:list', 'Job', '/monitor/job/index', '/monitor/job/index', null, '2', null, '1', '15', '17', '19', 'job', null, '1', '1624072904325');
INSERT INTO `permission` VALUES ('153', '数据监控', 'monitor:druid:list', 'Druid', '/monitor/druid/index', '/monitor/druid/index', null, '2', null, '1', '15', '17', '19', 'druid', null, '1', '1624072932307');
INSERT INTO `permission` VALUES ('154', '服务监控', 'monitor:server:list', 'Server', '/monitor/server/index', '/monitor/server/index', null, '2', null, '1', '15', '17', '19', 'server', null, '1', '1624072986595');
INSERT INTO `permission` VALUES ('155', '缓存监控', 'monitor:cache:list', 'Cache', '/monitor/cache/index', '/monitor/cache/index', null, '2', null, '1', '15', '17', '19', 'cache', null, '1', '1624073017972');
INSERT INTO `permission` VALUES ('181', '表单构建', 'tool:build:list', 'Build', '/tool/build/index', '/tool/build/index', null, '3', null, '1', '15', '17', '19', 'build', null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('182', '代码生成', 'tool:gen:list', 'Gen', '/tool/gen/index', '/tool/gen/index', null, '3', null, '1', '15', '17', '19', 'gen', null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('183', '系统接口', 'tool:swagger:list', 'Swagger', '/tool/swagger/index', '/tool/swagger/index', null, '3', null, '1', '15', '17', '19', 'swagger', null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('184', '大屏设计', 'tool:screen:list', 'Screen', '/tool/screen/index', '/tool/screen/index', '/static/images/community/break_the_news@2x.png', '3', null, '1', '15', '17', '19', 'screen', null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1001', '用户新增', 'sys:user:add', null, '/sys/user/addUser', '/sys/user/addUser', '../../static/images/guide@2x.png', '101', null, '3', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1002', '用户删除', 'sys:user:delete', null, '/sys/user/deleteUserById', '/sys/user/deleteUserById', '/static/images/community/deliberation@2x.png', '101', null, '4', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1003', '用户修改', 'sys:user:update', null, '/sys/user/updateUser', '/sys/user/updateUser', '/static/images/park@2x.png', '101', null, '5', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1004', '用户查询', 'sys:user:query', null, '/sys/user/getUserById', '/sys/user/getUserById', '../../static/images/medical_insurance@2x.png', '101', null, '6', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1005', '用户导入', 'sys:user:import', null, '/sys/user/import', '/sys/user/import', '../../static/images/fawu@2x.png', '101', null, '7', '16', '17', '19', null, null, '0', '1624073077795');
INSERT INTO `permission` VALUES ('1006', '用户导出', 'sys:user:export', null, '/sys/user/export', '/sys/user/export', '../../static/images/yimiao@2x.png', '101', null, '8', '16', '17', '19', null, null, '0', '1624073077795');
INSERT INTO `permission` VALUES ('1007', '角色新增', 'sys:role:add', null, null, null, null, '102', null, '1', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1008', '角色删除', 'sys:role:delete', null, null, null, null, '102', null, '2', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1009', '角色修改', 'sys:role:update', null, '/sys/role', '/sys/role', null, '102', null, '2', '16', '17', '19', null, null, '1', '1621845584634');
INSERT INTO `permission` VALUES ('1010', '角色查询', 'sys:role:query', null, '/sys/role', '/sys/role', null, '102', null, '2', '16', '17', '19', null, null, '1', '1621845584634');
INSERT INTO `permission` VALUES ('1011', '角色导出', 'sys:user:export', null, '/sys/role', '/sys/role', null, '102', null, '2', '16', '17', '19', null, null, '1', '1621845584634');
INSERT INTO `permission` VALUES ('1013', '菜单新增', 'sys:menu:add', null, null, null, '../../static/images/tianfu@2x.png', '103', null, '1', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1014', '菜单删除', 'sys:menu:delete', null, null, null, '../../static/images/rong@2x.png', '103', null, '2', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1015', '菜单修改', 'sys:menu:update', null, null, null, null, '103', null, '3', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1016', '菜单查询', 'sys:menu:query', null, null, null, null, '103', null, '3', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1019', '园区新增', 'sys:park:add', null, null, null, null, '104', null, '4', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1020', '园区删除', 'sys:park:delete', null, '#', '#', null, '104', null, '2', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1021', '园区修改', 'sys:park:update', null, null, null, null, '104', null, '1', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1022', '园区查询', 'sys:park:query', null, null, null, '/static/images/livingPayment@2x.png', '104', null, '1', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1023', '园区导出', 'sys:user:export', null, '/sys/role', '/sys/role', null, '104', null, '2', '16', '17', '19', null, null, '1', '1621845584634');
INSERT INTO `permission` VALUES ('1026', '部门新增', 'sys:dept:add', null, null, null, '/static/images/Global_purchase@2x.png', '105', null, '2', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1027', '部门删除', 'sys:dept:delete', null, null, null, '/static/images/yinli.png', '105', null, '3', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1028', '部门修改', 'sys:dept:update', null, null, null, '/static/images/park@2x.png', '105', null, '4', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1029', '部门查询', 'sys:dept:query', null, null, null, '/static/images/pay@2x.png', '105', null, '5', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1032', '职位新增', 'sys:post:add', null, null, null, '/static/images/binan@2x.png', '106', null, '6', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1033', '职位删除', 'sys:post:delete', null, null, null, '/static/images/yizhan@2x.png', '106', null, '7', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1034', '职位修改', 'sys:post:update', null, null, null, '/static/images/older@2x.png', '106', null, '8', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1035', '职位查询', 'sys:post:query', null, null, null, null, '106', null, '2', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1036', '职位导出', 'sys:post:export', null, '/sys/role', '/sys/role', null, '106', null, '2', '16', '17', '19', null, null, '1', '1621845584634');
INSERT INTO `permission` VALUES ('1038', '字典新增', 'sys:dict:add', null, 'https://zhzw.qbj.gov.cn/qbjzwdt/H5/wechat.m7.zwfw.qingbaijiang/pages/appoint/appoint_list.html?taskGuid=', 'https://zhzw.qbj.gov.cn/qbjzwdt/H5/wechat.m7.zwfw.qingbaijiang/pages/appoint/appoint_list.html?taskGuid=', '../../static/images/home/appointment_line@2x.png', '107', null, '1', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1039', '字典删除', 'sys:dict:delete', null, 'https://zhzw.qbj.gov.cn/qbjzwdt/H5/wechat.m7.zwfw.qingbaijiang/pages/appoint/appoint_search.html?taskGuid=', 'https://zhzw.qbj.gov.cn/qbjzwdt/H5/wechat.m7.zwfw.qingbaijiang/pages/appoint/appoint_search.html?taskGuid=', '../../static/images/home/appointment@2x.png', '107', null, '2', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1040', '字典修改', 'sys:dict:update', null, 'https://zhzw.qbj.gov.cn/qbjzwdt/H5/wechat.m7.zwfw.qingbaijiang/pages/service/service_common.html', 'https://zhzw.qbj.gov.cn/qbjzwdt/H5/wechat.m7.zwfw.qingbaijiang/pages/service/service_common.html', '../../static/images/guide@2x.png', '107', null, '3', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1041', '字典查询', null, null, 'https://zhzw.qbj.gov.cn/qbjzwdt/H5/wechat.m7.zwfw.qingbaijiang/pages/policy/enjoypolicy_list.html', 'https://zhzw.qbj.gov.cn/qbjzwdt/H5/wechat.m7.zwfw.qingbaijiang/pages/policy/enjoypolicy_list.html', '../../static/images/home/discount@2x.png', '107', null, '4', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1042', '字典导出', 'sys:dict:export', null, '/sys/role', '/sys/role', null, '107', null, '2', '16', '17', '19', null, null, '1', '1621845584634');
INSERT INTO `permission` VALUES ('1045', '参数新增', 'system:config:add', null, 'https://zhzw.qbj.gov.cn/qbjzwdt/H5/wechat.m7.zwfw.qingbaijiang/pages/policywiki/policywiki_index.html', 'https://zhzw.qbj.gov.cn/qbjzwdt/H5/wechat.m7.zwfw.qingbaijiang/pages/policywiki/policywiki_index.html', '../../static/images/home/encyclopedias@2x.png', '108', null, '5', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1046', '参数删除', 'system:config:remove', null, 'https://zhzw.qbj.gov.cn/qbjzwdt/H5/wechat.m7.zwfw.qingbaijiang/pages/onlineservice/onlineservice_tasklist.html', 'https://zhzw.qbj.gov.cn/qbjzwdt/H5/wechat.m7.zwfw.qingbaijiang/pages/onlineservice/onlineservice_tasklist.html', '../../static/images/home/online_Introduction@2x.png', '108', null, '6', '16', '17', '19', 'system', null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1047', '参数修改', 'system:config:edit', null, 'https://zhzw.qbj.gov.cn/qbjzwdt/epointzwmhwz/pages/carveout/carve', 'https://zhzw.qbj.gov.cn/qbjzwdt/epointzwmhwz/pages/carveout/carve', '../../static/images/home/theme@2x.png', '108', null, '7', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1048', '参数查询', null, null, 'https://zhzw.qbj.gov.cn/qbjzwdt/H5/wechat.m7.zwfw.qingbaijiang/pages/governmentmap/governmentmap_index.html', 'https://zhzw.qbj.gov.cn/qbjzwdt/H5/wechat.m7.zwfw.qingbaijiang/pages/governmentmap/governmentmap_index.html', '../../static/images/home/map@2x.png', '108', null, '8', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1049', '参数导出', 'system:config:export', null, '/sys/role', '/sys/role', null, '108', null, '2', '16', '17', '19', null, null, '1', '1621845584634');
INSERT INTO `permission` VALUES ('1051', '公告新增', null, null, 'wx65813e92ccd10f2f', 'wx65813e92ccd10f2f', '../../static/images/tianfu@2x.png', '109', null, '9', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1052', '公告删除', null, null, 'wx4b18951ae5c405cf', 'wx4b18951ae5c405cf', '../../static/images/rong@2x.png', '109', null, '10', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1053', '公告修改', null, null, null, null, null, '109', null, '1', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1054', '公告查询', null, null, null, null, '../../static/images/medical_insurance@2x.png', '109', null, '1', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('1055', '公告导出', null, null, null, null, '../../static/images/yuan@2x.png', '109', null, '1', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('2001', '在线用户管理', null, null, null, null, '../../static/images/gang@2x.png', '2', null, '2', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('2002', '在线用户单条强退', 'menu', null, '/sys/role', '/sys/role', null, '151', null, '2', '16', '17', '19', null, null, '1', '1621845584634');
INSERT INTO `permission` VALUES ('2003', '在线用户批量强退', 'menu', null, '/sys/role', '/sys/role', null, '151', null, '2', '16', '17', '19', null, null, '1', '1621845584634');
INSERT INTO `permission` VALUES ('2006', '定时任务新增', null, null, null, null, '../../static/images/yixiang@2x.png', '152', null, '3', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('2007', '定时任务删除', null, null, null, null, '../../static/images/peixun@2x.png', '152', null, '4', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('2008', '定时任务修改', null, null, null, null, null, '152', null, '1', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('2009', '定时任务查询', null, null, null, null, '../../static/images/qi@2x.png', '152', null, '1', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('2010', '代码生成查询', null, null, null, null, '../../static/images/zhuan@2x.png', '182', null, '2', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('2011', '代码生成修改', null, null, null, null, '../../static/images/qipei@2x.png', '182', null, '3', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('2012', '代码生成删除', null, null, null, null, '.../../static/images/shuangchuang@2x.png', '182', null, '4', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('2013', '导入代码', null, null, null, null, null, '182', null, '1', '16', '17', '19', null, null, '1', '1624073077795');
INSERT INTO `permission` VALUES ('3679', '区域管理', null, 'Address', 'system/address/index', null, 'clipboard', '1', null, '1', '15', '17', '19', 'address', null, '1', '1647325486144');
INSERT INTO `permission` VALUES ('3683', '设备管理', 'sys:dev:list', 'DeviceList', 'systemOut/device/index', null, 'documentation', '3687', null, '12', '15', '17', '19', 'device', null, '1', '1647328591901');
INSERT INTO `permission` VALUES ('3684', '设备增加', 'sys:video:add', null, null, null, null, '3683', null, '1', '16', '17', '19', null, null, '1', '1647328622683');
INSERT INTO `permission` VALUES ('3685', '设备删除', 'sys:video:delete', null, null, null, null, '3683', null, '2', '16', '17', '19', null, null, '1', '1647328654610');
INSERT INTO `permission` VALUES ('3686', '设备更新', 'sys:video:update', null, null, null, null, '3683', null, '3', '16', '17', '19', null, null, '1', '1647328683185');
INSERT INTO `permission` VALUES ('3687', '系统管理', null, 'SystemOut', 'Layout', null, 'cascader', '0', null, '2', '14', '17', '19', '/systemOut', null, '1', '1647339317328');
INSERT INTO `permission` VALUES ('3689', '视频播放', 'sys:video:list', 'VideoPlay', 'systemOut/video/play', null, 'documentation', '3687', null, '2', '15', '17', '19', 'video/play', null, '1', '1647339667245');
INSERT INTO `permission` VALUES ('3690', '告警中心', 'alarm:center:list', 'AlarmCenert', 'systemOut/alarm/index', null, 'component', '3687', null, '4', '15', '17', '19', 'alarm', null, '1', '1647398896995');
INSERT INTO `permission` VALUES ('3692', '商家管理', 'bus:center:list', 'Business', 'systemOut/business/index', null, 'documentation', '3687', null, '6', '15', '17', '19', 'business', null, '1', '1647481400739');
INSERT INTO `permission` VALUES ('3694', '建筑管理', 'assets:building:list', 'Property', 'systemOut/property/index', null, 'clipboard', '3687', null, '8', '15', '17', '19', 'property', null, '1', '1647481686505');
INSERT INTO `permission` VALUES ('3696', '人车管理', null, 'ManCar', 'systemOut/manCar/index', null, 'druid', '3687', null, '9', '15', '17', '19', 'manCar', null, '1', '1647481817357');
INSERT INTO `permission` VALUES ('3698', '人车布控', null, 'manCarControl', 'systemOut/manCarControl/index', null, 'documentation', '3687', null, '10', '15', '17', '19', 'manCarControl', null, '1', '1647482074682');
INSERT INTO `permission` VALUES ('3700', '出入记录', null, 'AccessRecord', 'systemOut/accessRecord/index', null, 'edit', '3687', null, '11', '15', '17', '19', 'accessRecord', null, '1', '1647482192743');
INSERT INTO `permission` VALUES ('3702', '三防数据', 'defense:data:list', 'CivilAirDefense', 'systemOut/civilAirDefense/index', null, 'monitor', '3687', null, '12', '15', '17', '19', 'civilAirDefense', null, '1', '1647482317768');
INSERT INTO `permission` VALUES ('3704', '能源管理', null, 'Pay', 'systemOut/pay/index', null, 'chart', '3687', null, '14', '15', '17', '20', 'http://ygl.czkxwl.cn/gy/', null, '1', '1647482442252');
INSERT INTO `permission` VALUES ('3708', '区域新增', 'sys:area:add', null, null, null, null, '3679', null, '1', '16', '17', '19', null, null, '1', '1647484205875');
INSERT INTO `permission` VALUES ('3709', '区域修改', 'sys:area:update', null, null, null, null, '3679', null, '2', '16', '17', '19', null, null, '1', '1647484249607');
INSERT INTO `permission` VALUES ('3710', '区域删除', 'sys:area:delete', null, null, null, null, '3679', null, '3', '16', '17', '19', null, null, '1', '1647484305791');
INSERT INTO `permission` VALUES ('3711', '区域查询', 'sys:area:query', null, null, null, null, '3679', null, '4', '16', '17', '19', null, null, '1', '1647484351358');
INSERT INTO `permission` VALUES ('3713', '密码重置', 'sys:user:reset', null, null, null, null, '101', null, '1', '16', '17', '19', null, null, '1', '1647499504433');
INSERT INTO `permission` VALUES ('3714', '楼栋添加', 'assets:building:add', null, null, null, null, '3694', null, '1', '16', '17', '19', null, null, '1', '1647915202651');
INSERT INTO `permission` VALUES ('3715', '楼栋删除', 'assets:building:delete', null, null, null, null, '3694', null, '2', '16', '17', '19', null, null, '1', '1647915330692');
INSERT INTO `permission` VALUES ('3716', '楼栋更新', 'assets:building:update', null, null, null, null, '3694', null, '3', '16', '17', '19', null, null, '1', '1647915401183');
INSERT INTO `permission` VALUES ('3717', '空间添加', 'assets:space:add', null, null, null, null, '3694', null, '4', '16', '17', '19', null, null, '1', '1647916026862');
INSERT INTO `permission` VALUES ('3718', '空间删除', 'assets:space:delete', null, null, null, null, '3694', null, '5', '16', '17', '19', null, null, '1', '1647916603632');
INSERT INTO `permission` VALUES ('3719', '空间更新', 'assets:space:update', null, null, null, null, '3694', null, '6', '16', '17', '19', null, null, '1', '1647916646990');
INSERT INTO `permission` VALUES ('3720', '添加商家', 'bus:center:add', null, null, null, null, '3692', null, '1', '16', '17', '19', null, null, '1', '1648031226479');
INSERT INTO `permission` VALUES ('3721', '删除商家', 'bus:center:delete', null, null, null, null, '3692', null, '2', '16', '17', '19', null, null, '1', '1648031389319');
INSERT INTO `permission` VALUES ('3722', '更新商家', 'bus:center:update', null, null, null, null, '3692', null, '3', '16', '17', '19', null, null, '1', '1648031425367');
INSERT INTO `permission` VALUES ('3723', '资产管理', 'means:manage:list', 'Means', 'systemOut/means/index', null, 'property safety', '3687', null, '3', '15', '17', '19', 'means', null, '1', '1650339084379');
INSERT INTO `permission` VALUES ('3724', '添加资产', 'means:manage:add', null, null, null, null, '3723', null, '1', '16', '17', '19', null, null, '1', '1650359920169');
INSERT INTO `permission` VALUES ('3725', '删除资产', 'means:manage:delete', null, null, null, null, '3723', null, '2', '16', '17', '19', null, null, '1', '1650360034043');
INSERT INTO `permission` VALUES ('3726', '更新资产', 'means:manage:update', null, null, null, null, '3723', null, '3', '16', '17', '19', null, null, '1', '1650360074594');
INSERT INTO `permission` VALUES ('3727', '人防添加', 'defense:person:add', null, null, null, null, '3702', null, '1', '16', '17', '19', null, null, '1', '1650446535535');
INSERT INTO `permission` VALUES ('3728', '人防删除', 'defense:person:delete', null, null, null, null, '3702', null, '2', '16', '17', '19', null, null, '1', '1650446584887');
INSERT INTO `permission` VALUES ('3729', '人防更新', 'defense:person:update', null, null, null, null, '3702', null, '3', '16', '17', '19', null, null, '1', '1650446726228');
INSERT INTO `permission` VALUES ('3730', '物防添加', 'defense:physical:add', null, null, null, null, '3702', null, '4', '16', '17', '19', null, null, '1', '1650447797547');
INSERT INTO `permission` VALUES ('3731', '物防删除', 'defense:physical:delete', null, null, null, null, '3702', null, '5', '16', '17', '19', null, null, '1', '1650447845343');
INSERT INTO `permission` VALUES ('3732', '物防更新', 'defense:physical:update', null, null, null, null, '3702', null, '6', '16', '17', '19', null, null, '1', '1650447905336');
INSERT INTO `permission` VALUES ('3733', '技防添加', 'defense:technical:add', null, null, null, null, '3702', null, '7', '16', '17', '19', null, null, '1', '1650448521442');
INSERT INTO `permission` VALUES ('3734', '技防删除', 'defense:technical:delete', null, null, null, null, '3702', null, '8', '16', '17', '19', null, null, '1', '1650448548827');
INSERT INTO `permission` VALUES ('3735', '技防更新', 'defense:technical:update', null, null, null, null, '3702', null, '9', '16', '17', '19', null, null, '1', '1650448574255');
INSERT INTO `permission` VALUES ('3736', '访客管理', null, 'Visitor', 'systemOut/visitor/list', null, 'cascader', '3687', null, '10', '15', '17', '19', 'visitor', null, '1', '1650769409143');
INSERT INTO `permission` VALUES ('3737', '模板管理', 'template:manage:list', 'Template', 'systemOut/template/index', null, 'tree', '1', null, '7', '15', '17', '19', 'template', null, '1', '1651025743250');
INSERT INTO `permission` VALUES ('3738', '模板添加', 'template:manage:add', null, null, null, null, '3737', null, '1', '16', '17', '19', null, null, '1', '1651029703415');
INSERT INTO `permission` VALUES ('3739', '模板更新', 'template:manage:update', null, null, null, null, '3737', null, '2', '16', '17', '19', null, null, '1', '1651029742440');
INSERT INTO `permission` VALUES ('3740', '模板删除', 'template:manage:delete', null, null, null, null, '3737', null, '3', '16', '17', '19', null, null, '1', '1651029790339');
INSERT INTO `permission` VALUES ('3741', '模板启用', 'template:manage:enable', null, null, null, null, '3737', null, '4', '16', '17', '19', null, null, '1', '1651030262224');
INSERT INTO `permission` VALUES ('3742', '主题设置', null, 'themeSetting', 'systemOut/themeSetting', null, 'dashboard', '1', null, '13', '15', '17', '19', 'themeSetting', null, '1', '1658479613549');
INSERT INTO `permission` VALUES ('3745', '账号审核', 'sys:user:examine', null, null, null, null, '101', null, '9', '16', '17', '19', null, null, '1', '1658997903740');
INSERT INTO `permission` VALUES ('3749', '审核资产', 'means:manage:examine', null, null, null, null, '3723', null, '4', '16', '17', '19', null, null, '1', '1660210155107');
INSERT INTO `permission` VALUES ('3750', '导入资产', 'means:manage:import', null, null, null, null, '3723', null, '5', '16', '17', '19', null, null, '1', '1660210233624');
INSERT INTO `permission` VALUES ('3751', '导出资产', 'means:manage:export', null, null, null, null, '3723', null, '6', '16', '17', '19', null, null, '1', '1660210264530');
INSERT INTO `permission` VALUES ('3752', '下载资产模板', 'means:manage:down', null, null, null, null, '3723', null, '7', '16', '17', '19', null, null, '1', '1660210408383');
INSERT INTO `permission` VALUES ('3753', '添加操作人员', 'specialOperator:manage:add', null, null, null, null, '3723', null, '8', '16', '17', '19', null, null, '1', '1660210518320');
INSERT INTO `permission` VALUES ('3754', '更新操作人员', 'specialOperator:manage:update', null, null, null, null, '3723', null, '9', '16', '17', '19', null, null, '1', '1660210578630');
INSERT INTO `permission` VALUES ('3755', '删除操作人员', 'specialOperator:manage:delete', null, null, null, null, '3723', null, '10', '16', '17', '19', null, null, '1', '1660210618742');
INSERT INTO `permission` VALUES ('3756', '审核操作人员', 'specialOperator:manage:examine', null, null, null, null, '3723', null, '11', '16', '17', '19', null, null, '1', '1660210657752');
INSERT INTO `permission` VALUES ('3757', '导入操作人员', 'specialOperator:manage:import', null, null, null, null, '3723', null, '12', '16', '17', '19', null, null, '1', '1660210685376');
INSERT INTO `permission` VALUES ('3758', '导出操作人员', 'specialOperator:manage:export', null, null, null, null, '3723', null, '13', '16', '17', '19', null, null, '1', '1660210707835');
INSERT INTO `permission` VALUES ('3759', '下载操作人员模板', 'specialOperator:manage:down', null, null, null, null, '3723', null, '14', '16', '17', '19', null, null, '1', '1660210745502');
INSERT INTO `permission` VALUES ('3760', '更新特种设备', 'specialEquipment:manage:update', null, null, null, null, '3723', null, '15', '16', '17', '19', null, null, '1', '1660210775672');
INSERT INTO `permission` VALUES ('3761', '添加特种设备', 'specialEquipment:manage:add', null, null, null, null, '3723', null, '16', '16', '17', '19', null, null, '1', '1660210812454');
INSERT INTO `permission` VALUES ('3762', '删除特种设备', 'specialEquipment:manage:delete', null, null, null, null, '3723', null, '17', '16', '17', '19', null, null, '1', '1660210845543');
INSERT INTO `permission` VALUES ('3763', '审核特种设备', 'specialEquipment:manage:examine', null, null, null, null, '3723', null, '18', '16', '17', '19', null, null, '1', '1660210886844');
INSERT INTO `permission` VALUES ('3764', '导入特种设备', 'specialEquipment:manage:import', null, null, null, null, '3723', null, '19', '16', '17', '19', null, null, '1', '1660210919738');
INSERT INTO `permission` VALUES ('3765', '导出特种设备', 'specialEquipment:manage:export', null, null, null, null, '3723', null, '20', '16', '17', '19', null, null, '1', '1660210943078');
INSERT INTO `permission` VALUES ('3766', '下载特种设备模板', 'specialEquipment:manage:down', null, null, null, null, '3723', null, '21', '16', '17', '19', null, null, '1', '1660210996751');
INSERT INTO `permission` VALUES ('3767', '事件管理', null, 'Event', 'systemOut/eventPage/index', null, 'checkbox', '3687', null, '15', '15', '17', '19', 'eventPage', null, '1', '1660718858090');
INSERT INTO `permission` VALUES ('3768', '人车新增', 'sys:personCar:add', null, null, null, null, '3696', null, '1', '16', '17', '19', null, null, '1', '1669354329695');
INSERT INTO `permission` VALUES ('3769', '人车删除', 'sys:personCar:delete', null, null, null, null, '3696', null, '2', '16', '17', '19', null, null, '1', '1669354382679');
INSERT INTO `permission` VALUES ('3770', '人车修改', 'sys:personCar:update', null, null, null, null, '3696', null, '3', '16', '17', '19', null, null, '1', '1669354408443');
INSERT INTO `permission` VALUES ('3771', '系统文件', null, 'Doc', 'system/doc/index', null, 'tab', '1', null, '12', '14', '17', '19', 'doc', null, '1', '1676619736415');
INSERT INTO `permission` VALUES ('3772', '日志管理', null, 'Log', 'system/log/index', null, 'bug', '1', null, '11', '14', '17', '19', 'log', null, '1', '1676872152930');
INSERT INTO `permission` VALUES ('3773', '操作日志', null, 'OperLog', 'system/log/operlog/index', null, 'bug', '3772', null, '1', '15', '17', '19', 'operlog', null, '1', '1676872241390');
INSERT INTO `permission` VALUES ('3774', '登录日志', null, 'loginLog', 'system/log/logininfor/index', null, 'cascader', '3772', null, '2', '15', '17', '19', 'login', null, '1', '1676872719531');
INSERT INTO `permission` VALUES ('3775', '系统配置', null, 'Setting', 'system/setting/index', null, 'clipboard', '1', null, '14', '15', '17', '19', 'setting', null, '0', '1677029191680');
INSERT INTO `permission` VALUES ('3778', '视频播放(云)', 'sys:video:list', 'VideoPlayY', 'systemOut/video/yglPlay', null, 'documentation', '3687', null, '2', '15', '17', '19', 'video/yglPlay', null, '1', '1684994701303');
INSERT INTO `permission` VALUES ('3779', '园区设置', 'sys:park:set', null, null, null, null, '104', null, '5', '16', '17', '19', null, null, '1', '1685001443751');
INSERT INTO `permission` VALUES ('3780', '云钢联', 'means:manage:ygl', null, null, null, null, '3723', null, '2', '16', '17', '19', null, null, '1', '1685522201861');
INSERT INTO `permission` VALUES ('3781', '园区资产管理', 'specialOperator:manage:park', null, null, null, null, '3723', null, '22', '16', '17', '19', null, null, '1', '1685606325540');
INSERT INTO `permission` VALUES ('3782', '特种设备管理', 'specialOperator:manage:special', null, null, null, null, '3723', null, '23', '16', '17', '19', null, null, '1', '1685606357537');
INSERT INTO `permission` VALUES ('3783', '操作人员管理', 'specialOperator:manage:operator', null, null, null, null, '3723', null, '24', '16', '17', '19', null, null, '1', '1685606385828');
INSERT INTO `permission` VALUES ('3784', '建筑分区', 'assets:building:partition', null, null, null, null, '3694', null, '7', '16', '17', '19', null, null, '1', '1686116663638');
INSERT INTO `permission` VALUES ('3785', '楼栋类型', null, 'BuildingClassify', 'system/buildingClassify/index', null, 'button', '1', null, '15', '15', '17', '19', 'buildingClassify', null, '1', '1687144509376');
INSERT INTO `permission` VALUES ('3786', '楼栋类型新增', 'sys:buildClassify:add', null, null, null, null, '3785', null, '1', '16', '17', '19', null, null, '1', '1687146471788');
INSERT INTO `permission` VALUES ('3787', '更新', 'sys:buildClassify:update', null, null, null, null, '3785', null, '2', '16', '17', '19', null, null, '1', '1687146526218');
INSERT INTO `permission` VALUES ('3788', '删除', 'sys:buildClassify:delete', null, null, null, null, '3785', null, '3', '16', '17', '19', null, null, '1', '1687146548557');
INSERT INTO `permission` VALUES ('3789', '云钢联能源商户', 'space:ygl:energyMerchant', null, null, null, null, '3694', null, '8', '16', '17', '19', null, null, '1', '1687771722157');
INSERT INTO `permission` VALUES ('3790', '云钢联能源商户', 'space:ygl:energyMerchant', null, null, null, null, '3692', null, '4', '16', '17', '19', null, null, '1', '1687771853471');
INSERT INTO `permission` VALUES ('3801', '能源管理', null, 'energyManager', 'Layout', null, 'chart', '0', null, '2', '14', '17', '19', '/energyManager', null, '1', '1688520935491');
INSERT INTO `permission` VALUES ('3802', '电能参数设置', null, 'eleManager', 'energyManager/eleManager/index', null, 'ele', '3801', null, '1', '14', '17', '19', 'eleManager', null, '1', '1688521253007');
INSERT INTO `permission` VALUES ('3803', '网关状态', null, 'gatewayState', 'energyManager/eleManager/gatewayState/index', null, '', '3802', null, '1', '15', '17', '19', 'gatewayState', null, '1', '1688521871755');
INSERT INTO `permission` VALUES ('3806', '阶梯电价设置', null, 'stepEle', 'energyManager/eleManager/stepEle/index', null, '', '3802', null, '4', '15', '17', '19', 'stepEle', null, '1', '1688522308429');
INSERT INTO `permission` VALUES ('3807', '费用不足预警', null, 'costOut', 'energyManager/eleManager/costOut/index', null, '', '3802', null, '5', '15', '17', '19', 'costOut', null, '1', '1688522346002');
INSERT INTO `permission` VALUES ('3808', '水表参数设置', null, 'waterManager', 'energyManager/waterManager/index', null, 'water', '3801', null, '2', '14', '17', '19', 'waterManager', null, '1', '1688696963310');
INSERT INTO `permission` VALUES ('3809', '网关状态', null, 'gatewayState', 'energyManager/waterManager/gatewayState/index', null, '', '3808', null, '1', '15', '17', '19', 'gatewayState', null, '1', '1688696991874');
INSERT INTO `permission` VALUES ('3812', '阶梯水价设置', null, 'stepEle', 'energyManager/waterManager/stepEle/index', null, '', '3808', null, '4', '15', '17', '19', 'stepEle', null, '1', '1688697080574');
INSERT INTO `permission` VALUES ('3813', '费用不足预警', null, 'costOut', 'energyManager/waterManager/costOut/index', null, '', '3808', null, '5', '15', '17', '19', 'costOut', null, '1', '1688697105310');
INSERT INTO `permission` VALUES ('3814', '能源设备管理', null, 'energyDevice', 'energyManager/energyDevice/index', null, 'energy', '3801', null, '3', '14', '17', '19', 'energyDevice', null, '1', '1689155954486');
INSERT INTO `permission` VALUES ('3815', '水表设备', null, 'waterDevice', 'energyManager/energyDevice/waterDevice', null, 'water', '3814', null, '1', '15', '17', '19', 'waterDevice', null, '1', '1689218955761');
INSERT INTO `permission` VALUES ('3816', '电表设备', null, 'electricityDeive', 'energyManager/energyDevice/electricityDeive', null, 'ele', '3814', null, '2', '15', '17', '19', 'electricityDeive', null, '1', '1689219046668');
INSERT INTO `permission` VALUES ('3817', '网关设备', null, 'gatewayDevice', 'energyManager/energyDevice/gatewayDevice', null, 'gateway', '3814', null, '3', '15', '17', '19', 'gatewayDevice', null, '1', '1689219101297');
INSERT INTO `permission` VALUES ('3820', '缴费管理', null, 'payManager', 'energyManager/payManager/index', null, 'money', '3801', null, '4', '14', '17', '19', 'payManager', null, '1', '1689219340898');
INSERT INTO `permission` VALUES ('3821', '缴费', null, 'pay', 'energyManager/payManager/pay', null, null, '3820', null, '1', '15', '17', '19', 'pay', null, '1', '1689219393397');
INSERT INTO `permission` VALUES ('3822', '缴费记录', null, 'payList', 'energyManager/payManager/payList', null, null, '3820', null, '2', '15', '17', '19', 'payList', null, '1', '1689219519517');
INSERT INTO `permission` VALUES ('3823', '能源统计', null, 'statistics', 'energyManager/statistics', null, 'server', '3801', null, '6', '15', '17', '19', 'statistics', null, '1', '1689219589176');
INSERT INTO `permission` VALUES ('3824', '建筑模型', null, 'model', 'energyManager/model', null, 'tab', '3801', null, '5', '15', '17', '19', 'model', null, '1', '1689234265467');
INSERT INTO `permission` VALUES ('3825', '水表新增', 'energy:water:add', null, null, null, null, '3815', null, '1', '16', '17', '19', null, null, '1', '1690869827716');
INSERT INTO `permission` VALUES ('3826', '水表更新', 'energy:water:update', null, null, null, null, '3815', null, '2', '16', '17', '19', null, null, '1', '1690869855676');
INSERT INTO `permission` VALUES ('3827', '水表删除', 'energy:water:delete', null, null, null, null, '3815', null, '3', '16', '17', '19', null, null, '1', '1690869877178');
INSERT INTO `permission` VALUES ('3828', '电表新增', 'energy:electricity:add', null, null, null, null, '3816', null, '1', '16', '17', '19', null, null, '1', '1690869926105');
INSERT INTO `permission` VALUES ('3829', '电表更新', 'energy:electricity:update', null, null, null, null, '3816', null, '2', '16', '17', '19', null, null, '1', '1690869951376');
INSERT INTO `permission` VALUES ('3830', '电表删除', 'energy:electricity:delete', null, null, null, null, '3816', null, '3', '16', '17', '19', null, null, '1', '1690869972835');
INSERT INTO `permission` VALUES ('3831', '网关新增', 'energy:gateway:add', null, null, null, null, '3817', null, '1', '16', '17', '19', null, null, '1', '1690870044057');
INSERT INTO `permission` VALUES ('3832', '缴费记录添加', 'pay:list:add', null, null, null, null, '3821', null, '1', '16', '17', '19', null, null, '1', '1690871309300');
INSERT INTO `permission` VALUES ('3833', '缴费查询', 'pay:list:query', null, null, null, null, '3821', null, '2', '16', '17', '19', null, null, '1', '1690871528973');
INSERT INTO `permission` VALUES ('3834', '模型新增', 'model:list:add', null, null, null, null, '3824', null, '1', '16', '17', '19', null, null, '1', '1690871602957');
INSERT INTO `permission` VALUES ('3835', '模型修改', 'model:list:update', null, null, null, null, '3824', null, '2', '16', '17', '19', null, null, '1', '1690871625244');
INSERT INTO `permission` VALUES ('3836', '模型删除', 'model:list:delete', null, null, null, null, '3824', null, '3', '16', '17', '19', null, null, '1', '1690871673390');
INSERT INTO `permission` VALUES ('3837', '网关更新', 'energy:gateway:update', null, null, null, null, '3817', null, '2', '16', '17', '19', null, null, '1', '1690873058547');
INSERT INTO `permission` VALUES ('3838', '网关删除', 'energy:gateway:delete', null, null, null, null, '3817', null, '3', '16', '17', '19', null, null, '1', '1690873085311');
INSERT INTO `permission` VALUES ('3839', '告警记录', null, 'eleAlarm', 'energyManager/eleManager/alarm/index', null, null, '3802', null, '6', '15', '17', '19', 'alarm', null, '1', '1691377438999');
INSERT INTO `permission` VALUES ('3840', '告警记录', null, 'waterAlarm', 'energyManager/waterManager/alarm/index', null, null, '3808', null, '6', '15', '17', '19', 'alarm', null, '1', '1691377489501');

-- ----------------------------
-- Table structure for rocket_mq_fail_msg
-- ----------------------------
DROP TABLE IF EXISTS `rocket_mq_fail_msg`;
CREATE TABLE `rocket_mq_fail_msg` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` bigint DEFAULT NULL,
  `obj_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `reconsume_times` int DEFAULT NULL,
  `msg_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `msg_body` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `queue_id` int DEFAULT NULL,
  `queue_offset` bigint DEFAULT NULL,
  `commitLog_offset` bigint DEFAULT NULL,
  `broker_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bornHost_string` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1086197 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of rocket_mq_fail_msg
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `role_key` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `order_num` int DEFAULT NULL,
  `use_type` bigint DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `del_flag` tinyint DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  `park_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1686276112903884801 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1501859873070829561', '超级管理员', 'SuperAdmin', '1', '17', '1', '0', null, null);
INSERT INTO `role` VALUES ('1501859873070829562', '刚注册用户', 'Apply_User', '2', '17', '1', '0', null, null);
INSERT INTO `role` VALUES ('1501859873070829568', '测试', 'Admin', '3', '17', '1', '0', '1646906271425', '1501815782786256896');

-- ----------------------------
-- Table structure for role_model
-- ----------------------------
DROP TABLE IF EXISTS `role_model`;
CREATE TABLE `role_model` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint DEFAULT NULL,
  `model_id` bigint DEFAULT NULL,
  `model_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `model_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of role_model
-- ----------------------------
INSERT INTO `role_model` VALUES ('1', '1501859873070829568', '6', 'p3dCKdc5', '安全帽检测');
INSERT INTO `role_model` VALUES ('2', '1501859873070829568', '35', 'jm5HngvF', '无人机-违章建筑');
INSERT INTO `role_model` VALUES ('3', '1501859873070829568', '29', 'X0DmtfsO', 'cpp人车检测');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint DEFAULT NULL,
  `permission_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6008 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1734', '1501859873070829562', '104');
INSERT INTO `role_permission` VALUES ('1735', '1501859873070829562', '1015');
INSERT INTO `role_permission` VALUES ('1736', '1501859873070829562', '1016');
INSERT INTO `role_permission` VALUES ('1737', '1501859873070829562', '1019');
INSERT INTO `role_permission` VALUES ('1738', '1501859873070829562', '1020');
INSERT INTO `role_permission` VALUES ('1739', '1501859873070829562', '1021');
INSERT INTO `role_permission` VALUES ('1740', '1501859873070829562', '1022');
INSERT INTO `role_permission` VALUES ('1741', '1501859873070829562', '1');
INSERT INTO `role_permission` VALUES ('2811', '1558031718048112640', '3687');
INSERT INTO `role_permission` VALUES ('2812', '1558031718048112640', '3689');
INSERT INTO `role_permission` VALUES ('2813', '1558031718048112640', '3723');
INSERT INTO `role_permission` VALUES ('2814', '1558031718048112640', '3724');
INSERT INTO `role_permission` VALUES ('2815', '1558031718048112640', '3725');
INSERT INTO `role_permission` VALUES ('2816', '1558031718048112640', '3726');
INSERT INTO `role_permission` VALUES ('2817', '1558031718048112640', '3749');
INSERT INTO `role_permission` VALUES ('2818', '1558031718048112640', '3750');
INSERT INTO `role_permission` VALUES ('2819', '1558031718048112640', '3751');
INSERT INTO `role_permission` VALUES ('2820', '1558031718048112640', '3752');
INSERT INTO `role_permission` VALUES ('2821', '1558031718048112640', '3753');
INSERT INTO `role_permission` VALUES ('2822', '1558031718048112640', '3754');
INSERT INTO `role_permission` VALUES ('2823', '1558031718048112640', '3755');
INSERT INTO `role_permission` VALUES ('2824', '1558031718048112640', '3756');
INSERT INTO `role_permission` VALUES ('2825', '1558031718048112640', '3757');
INSERT INTO `role_permission` VALUES ('2826', '1558031718048112640', '3758');
INSERT INTO `role_permission` VALUES ('2827', '1558031718048112640', '3759');
INSERT INTO `role_permission` VALUES ('2828', '1558031718048112640', '3760');
INSERT INTO `role_permission` VALUES ('2829', '1558031718048112640', '3761');
INSERT INTO `role_permission` VALUES ('2830', '1558031718048112640', '3762');
INSERT INTO `role_permission` VALUES ('2831', '1558031718048112640', '3763');
INSERT INTO `role_permission` VALUES ('2832', '1558031718048112640', '3764');
INSERT INTO `role_permission` VALUES ('2833', '1558031718048112640', '3765');
INSERT INTO `role_permission` VALUES ('2834', '1558031718048112640', '3766');
INSERT INTO `role_permission` VALUES ('2835', '1558031718048112640', '3690');
INSERT INTO `role_permission` VALUES ('2836', '1558031718048112640', '3694');
INSERT INTO `role_permission` VALUES ('2837', '1558031718048112640', '3714');
INSERT INTO `role_permission` VALUES ('2838', '1558031718048112640', '3715');
INSERT INTO `role_permission` VALUES ('2839', '1558031718048112640', '3716');
INSERT INTO `role_permission` VALUES ('2840', '1558031718048112640', '3717');
INSERT INTO `role_permission` VALUES ('2841', '1558031718048112640', '3718');
INSERT INTO `role_permission` VALUES ('2842', '1558031718048112640', '3719');
INSERT INTO `role_permission` VALUES ('2843', '1558031718048112640', '3683');
INSERT INTO `role_permission` VALUES ('2844', '1558031718048112640', '3684');
INSERT INTO `role_permission` VALUES ('2845', '1558031718048112640', '3685');
INSERT INTO `role_permission` VALUES ('2846', '1558031718048112640', '3686');
INSERT INTO `role_permission` VALUES ('3472', '1547845254751744000', '3687');
INSERT INTO `role_permission` VALUES ('3473', '1547845254751744000', '3723');
INSERT INTO `role_permission` VALUES ('3474', '1547845254751744000', '3749');
INSERT INTO `role_permission` VALUES ('3475', '1547845254751744000', '3756');
INSERT INTO `role_permission` VALUES ('3476', '1547845254751744000', '3763');
INSERT INTO `role_permission` VALUES ('3477', '1547845254751744000', '3689');
INSERT INTO `role_permission` VALUES ('3478', '1547845254751744000', '3694');
INSERT INTO `role_permission` VALUES ('3479', '1547845254751744000', '3714');
INSERT INTO `role_permission` VALUES ('3480', '1547845254751744000', '3715');
INSERT INTO `role_permission` VALUES ('3481', '1547845254751744000', '3716');
INSERT INTO `role_permission` VALUES ('3482', '1547845254751744000', '3717');
INSERT INTO `role_permission` VALUES ('3483', '1547845254751744000', '3718');
INSERT INTO `role_permission` VALUES ('3484', '1547845254751744000', '3719');
INSERT INTO `role_permission` VALUES ('3485', '1547845254751744000', '3683');
INSERT INTO `role_permission` VALUES ('3486', '1547845254751744000', '3684');
INSERT INTO `role_permission` VALUES ('3487', '1547845254751744000', '3685');
INSERT INTO `role_permission` VALUES ('3488', '1547845254751744000', '3686');
INSERT INTO `role_permission` VALUES ('3489', '1547845254751744000', '1');
INSERT INTO `role_permission` VALUES ('3490', '1547845254751744000', '104');
INSERT INTO `role_permission` VALUES ('3491', '1547845254751744000', '1021');
INSERT INTO `role_permission` VALUES ('3492', '1547845254751744000', '1022');
INSERT INTO `role_permission` VALUES ('3493', '1547845254751744000', '1020');
INSERT INTO `role_permission` VALUES ('3494', '1547845254751744000', '1023');
INSERT INTO `role_permission` VALUES ('3495', '1547845254751744000', '1019');
INSERT INTO `role_permission` VALUES ('3496', '1547845254751744000', '101');
INSERT INTO `role_permission` VALUES ('3497', '1547845254751744000', '3713');
INSERT INTO `role_permission` VALUES ('3498', '1547845254751744000', '1001');
INSERT INTO `role_permission` VALUES ('3499', '1547845254751744000', '1002');
INSERT INTO `role_permission` VALUES ('3500', '1547845254751744000', '1003');
INSERT INTO `role_permission` VALUES ('3501', '1547845254751744000', '1004');
INSERT INTO `role_permission` VALUES ('3502', '1547845254751744000', '3745');
INSERT INTO `role_permission` VALUES ('3503', '1547845254751744000', '102');
INSERT INTO `role_permission` VALUES ('3504', '1547845254751744000', '1007');
INSERT INTO `role_permission` VALUES ('3505', '1547845254751744000', '1008');
INSERT INTO `role_permission` VALUES ('3506', '1547845254751744000', '1009');
INSERT INTO `role_permission` VALUES ('3507', '1547845254751744000', '1010');
INSERT INTO `role_permission` VALUES ('3508', '1547845254751744000', '1011');
INSERT INTO `role_permission` VALUES ('3509', '1547845254751744000', '105');
INSERT INTO `role_permission` VALUES ('3510', '1547845254751744000', '1026');
INSERT INTO `role_permission` VALUES ('3511', '1547845254751744000', '1027');
INSERT INTO `role_permission` VALUES ('3512', '1547845254751744000', '1028');
INSERT INTO `role_permission` VALUES ('3513', '1547845254751744000', '1029');
INSERT INTO `role_permission` VALUES ('3514', '1547845254751744000', '3742');
INSERT INTO `role_permission` VALUES ('3672', '1559784873417048064', '3687');
INSERT INTO `role_permission` VALUES ('3673', '1559784873417048064', '1');
INSERT INTO `role_permission` VALUES ('3674', '1559784873417048064', '3723');
INSERT INTO `role_permission` VALUES ('3675', '1559784873417048064', '3694');
INSERT INTO `role_permission` VALUES ('3676', '1559784873417048064', '3714');
INSERT INTO `role_permission` VALUES ('3677', '1559784873417048064', '3715');
INSERT INTO `role_permission` VALUES ('3678', '1559784873417048064', '3716');
INSERT INTO `role_permission` VALUES ('3679', '1559784873417048064', '3717');
INSERT INTO `role_permission` VALUES ('3680', '1559784873417048064', '3718');
INSERT INTO `role_permission` VALUES ('3681', '1559784873417048064', '3719');
INSERT INTO `role_permission` VALUES ('3682', '1559784873417048064', '3683');
INSERT INTO `role_permission` VALUES ('3683', '1559784873417048064', '3684');
INSERT INTO `role_permission` VALUES ('3684', '1559784873417048064', '3685');
INSERT INTO `role_permission` VALUES ('3685', '1559784873417048064', '3686');
INSERT INTO `role_permission` VALUES ('3686', '1559784873417048064', '101');
INSERT INTO `role_permission` VALUES ('3687', '1559784873417048064', '3713');
INSERT INTO `role_permission` VALUES ('3688', '1559784873417048064', '1001');
INSERT INTO `role_permission` VALUES ('3689', '1559784873417048064', '1002');
INSERT INTO `role_permission` VALUES ('3690', '1559784873417048064', '1003');
INSERT INTO `role_permission` VALUES ('3691', '1559784873417048064', '1004');
INSERT INTO `role_permission` VALUES ('3692', '1559784873417048064', '3745');
INSERT INTO `role_permission` VALUES ('3693', '1559784873417048064', '102');
INSERT INTO `role_permission` VALUES ('3694', '1559784873417048064', '1007');
INSERT INTO `role_permission` VALUES ('3695', '1559784873417048064', '1008');
INSERT INTO `role_permission` VALUES ('3696', '1559784873417048064', '1009');
INSERT INTO `role_permission` VALUES ('3697', '1559784873417048064', '1010');
INSERT INTO `role_permission` VALUES ('3698', '1559784873417048064', '1011');
INSERT INTO `role_permission` VALUES ('3699', '1559784873417048064', '105');
INSERT INTO `role_permission` VALUES ('3700', '1559784873417048064', '1026');
INSERT INTO `role_permission` VALUES ('3701', '1559784873417048064', '1027');
INSERT INTO `role_permission` VALUES ('3702', '1559784873417048064', '1028');
INSERT INTO `role_permission` VALUES ('3703', '1559784873417048064', '1029');
INSERT INTO `role_permission` VALUES ('3704', '1559784873417048064', '3742');
INSERT INTO `role_permission` VALUES ('4322', '1552934632142462976', '3687');
INSERT INTO `role_permission` VALUES ('4323', '1552934632142462976', '3723');
INSERT INTO `role_permission` VALUES ('4324', '1552934632142462976', '1');
INSERT INTO `role_permission` VALUES ('4325', '1552934632142462976', '104');
INSERT INTO `role_permission` VALUES ('4326', '1552934632142462976', '3689');
INSERT INTO `role_permission` VALUES ('4327', '1552934632142462976', '3724');
INSERT INTO `role_permission` VALUES ('4328', '1552934632142462976', '3725');
INSERT INTO `role_permission` VALUES ('4329', '1552934632142462976', '3780');
INSERT INTO `role_permission` VALUES ('4330', '1552934632142462976', '3726');
INSERT INTO `role_permission` VALUES ('4331', '1552934632142462976', '3690');
INSERT INTO `role_permission` VALUES ('4332', '1552934632142462976', '3692');
INSERT INTO `role_permission` VALUES ('4333', '1552934632142462976', '3720');
INSERT INTO `role_permission` VALUES ('4334', '1552934632142462976', '3721');
INSERT INTO `role_permission` VALUES ('4335', '1552934632142462976', '3722');
INSERT INTO `role_permission` VALUES ('4336', '1552934632142462976', '3694');
INSERT INTO `role_permission` VALUES ('4337', '1552934632142462976', '3714');
INSERT INTO `role_permission` VALUES ('4338', '1552934632142462976', '3715');
INSERT INTO `role_permission` VALUES ('4339', '1552934632142462976', '3716');
INSERT INTO `role_permission` VALUES ('4340', '1552934632142462976', '3717');
INSERT INTO `role_permission` VALUES ('4341', '1552934632142462976', '3718');
INSERT INTO `role_permission` VALUES ('4342', '1552934632142462976', '3719');
INSERT INTO `role_permission` VALUES ('4343', '1552934632142462976', '3696');
INSERT INTO `role_permission` VALUES ('4344', '1552934632142462976', '3698');
INSERT INTO `role_permission` VALUES ('4345', '1552934632142462976', '3736');
INSERT INTO `role_permission` VALUES ('4346', '1552934632142462976', '3700');
INSERT INTO `role_permission` VALUES ('4347', '1552934632142462976', '3683');
INSERT INTO `role_permission` VALUES ('4348', '1552934632142462976', '3684');
INSERT INTO `role_permission` VALUES ('4349', '1552934632142462976', '3685');
INSERT INTO `role_permission` VALUES ('4350', '1552934632142462976', '3686');
INSERT INTO `role_permission` VALUES ('4351', '1552934632142462976', '3702');
INSERT INTO `role_permission` VALUES ('4352', '1552934632142462976', '3727');
INSERT INTO `role_permission` VALUES ('4353', '1552934632142462976', '3728');
INSERT INTO `role_permission` VALUES ('4354', '1552934632142462976', '3729');
INSERT INTO `role_permission` VALUES ('4355', '1552934632142462976', '3730');
INSERT INTO `role_permission` VALUES ('4356', '1552934632142462976', '3731');
INSERT INTO `role_permission` VALUES ('4357', '1552934632142462976', '3732');
INSERT INTO `role_permission` VALUES ('4358', '1552934632142462976', '3733');
INSERT INTO `role_permission` VALUES ('4359', '1552934632142462976', '3734');
INSERT INTO `role_permission` VALUES ('4360', '1552934632142462976', '3735');
INSERT INTO `role_permission` VALUES ('4361', '1552934632142462976', '3704');
INSERT INTO `role_permission` VALUES ('4362', '1552934632142462976', '1021');
INSERT INTO `role_permission` VALUES ('4363', '1552934632142462976', '1022');
INSERT INTO `role_permission` VALUES ('4364', '1552934632142462976', '1020');
INSERT INTO `role_permission` VALUES ('4365', '1552934632142462976', '1023');
INSERT INTO `role_permission` VALUES ('4366', '1552934632142462976', '1019');
INSERT INTO `role_permission` VALUES ('4367', '1552934632142462976', '3679');
INSERT INTO `role_permission` VALUES ('4368', '1552934632142462976', '3708');
INSERT INTO `role_permission` VALUES ('4369', '1552934632142462976', '3709');
INSERT INTO `role_permission` VALUES ('4370', '1552934632142462976', '3710');
INSERT INTO `role_permission` VALUES ('4371', '1552934632142462976', '3711');
INSERT INTO `role_permission` VALUES ('4372', '1552934632142462976', '101');
INSERT INTO `role_permission` VALUES ('4373', '1552934632142462976', '3713');
INSERT INTO `role_permission` VALUES ('4374', '1552934632142462976', '1001');
INSERT INTO `role_permission` VALUES ('4375', '1552934632142462976', '1002');
INSERT INTO `role_permission` VALUES ('4376', '1552934632142462976', '1003');
INSERT INTO `role_permission` VALUES ('4377', '1552934632142462976', '1004');
INSERT INTO `role_permission` VALUES ('4378', '1552934632142462976', '3745');
INSERT INTO `role_permission` VALUES ('4379', '1552934632142462976', '102');
INSERT INTO `role_permission` VALUES ('4380', '1552934632142462976', '1007');
INSERT INTO `role_permission` VALUES ('4381', '1552934632142462976', '1008');
INSERT INTO `role_permission` VALUES ('4382', '1552934632142462976', '1009');
INSERT INTO `role_permission` VALUES ('4383', '1552934632142462976', '1010');
INSERT INTO `role_permission` VALUES ('4384', '1552934632142462976', '1011');
INSERT INTO `role_permission` VALUES ('4385', '1552934632142462976', '103');
INSERT INTO `role_permission` VALUES ('4386', '1552934632142462976', '1013');
INSERT INTO `role_permission` VALUES ('4387', '1552934632142462976', '1014');
INSERT INTO `role_permission` VALUES ('4388', '1552934632142462976', '1015');
INSERT INTO `role_permission` VALUES ('4389', '1552934632142462976', '1016');
INSERT INTO `role_permission` VALUES ('4390', '1552934632142462976', '105');
INSERT INTO `role_permission` VALUES ('4391', '1552934632142462976', '1026');
INSERT INTO `role_permission` VALUES ('4392', '1552934632142462976', '1027');
INSERT INTO `role_permission` VALUES ('4393', '1552934632142462976', '1028');
INSERT INTO `role_permission` VALUES ('4394', '1552934632142462976', '1029');
INSERT INTO `role_permission` VALUES ('4395', '1552934632142462976', '106');
INSERT INTO `role_permission` VALUES ('4396', '1552934632142462976', '1035');
INSERT INTO `role_permission` VALUES ('4397', '1552934632142462976', '1036');
INSERT INTO `role_permission` VALUES ('4398', '1552934632142462976', '1032');
INSERT INTO `role_permission` VALUES ('4399', '1552934632142462976', '1033');
INSERT INTO `role_permission` VALUES ('4400', '1552934632142462976', '1034');
INSERT INTO `role_permission` VALUES ('4401', '1552934632142462976', '3737');
INSERT INTO `role_permission` VALUES ('4402', '1552934632142462976', '3738');
INSERT INTO `role_permission` VALUES ('4403', '1552934632142462976', '3739');
INSERT INTO `role_permission` VALUES ('4404', '1552934632142462976', '3740');
INSERT INTO `role_permission` VALUES ('4405', '1552934632142462976', '3741');
INSERT INTO `role_permission` VALUES ('4406', '1552934632142462976', '107');
INSERT INTO `role_permission` VALUES ('4407', '1552934632142462976', '1038');
INSERT INTO `role_permission` VALUES ('4408', '1552934632142462976', '1039');
INSERT INTO `role_permission` VALUES ('4409', '1552934632142462976', '1042');
INSERT INTO `role_permission` VALUES ('4410', '1552934632142462976', '1040');
INSERT INTO `role_permission` VALUES ('4411', '1552934632142462976', '1041');
INSERT INTO `role_permission` VALUES ('4412', '1552934632142462976', '108');
INSERT INTO `role_permission` VALUES ('4413', '1552934632142462976', '1049');
INSERT INTO `role_permission` VALUES ('4414', '1552934632142462976', '1045');
INSERT INTO `role_permission` VALUES ('4415', '1552934632142462976', '1046');
INSERT INTO `role_permission` VALUES ('4416', '1552934632142462976', '1047');
INSERT INTO `role_permission` VALUES ('4417', '1552934632142462976', '1048');
INSERT INTO `role_permission` VALUES ('4418', '1552934632142462976', '109');
INSERT INTO `role_permission` VALUES ('4419', '1552934632142462976', '1053');
INSERT INTO `role_permission` VALUES ('4420', '1552934632142462976', '1054');
INSERT INTO `role_permission` VALUES ('4421', '1552934632142462976', '1055');
INSERT INTO `role_permission` VALUES ('4422', '1552934632142462976', '1051');
INSERT INTO `role_permission` VALUES ('4423', '1552934632142462976', '1052');
INSERT INTO `role_permission` VALUES ('4424', '1552934632142462976', '3742');
INSERT INTO `role_permission` VALUES ('4425', '1552934632142462976', '2');
INSERT INTO `role_permission` VALUES ('4426', '1552934632142462976', '151');
INSERT INTO `role_permission` VALUES ('4427', '1552934632142462976', '2002');
INSERT INTO `role_permission` VALUES ('4428', '1552934632142462976', '2003');
INSERT INTO `role_permission` VALUES ('4429', '1552934632142462976', '152');
INSERT INTO `role_permission` VALUES ('4430', '1552934632142462976', '2008');
INSERT INTO `role_permission` VALUES ('4431', '1552934632142462976', '2009');
INSERT INTO `role_permission` VALUES ('4432', '1552934632142462976', '2006');
INSERT INTO `role_permission` VALUES ('4433', '1552934632142462976', '2007');
INSERT INTO `role_permission` VALUES ('4434', '1552934632142462976', '153');
INSERT INTO `role_permission` VALUES ('4435', '1552934632142462976', '154');
INSERT INTO `role_permission` VALUES ('4436', '1552934632142462976', '155');
INSERT INTO `role_permission` VALUES ('4437', '1552934632142462976', '2001');
INSERT INTO `role_permission` VALUES ('4438', '1552934632142462976', '3');
INSERT INTO `role_permission` VALUES ('4439', '1552934632142462976', '181');
INSERT INTO `role_permission` VALUES ('4440', '1552934632142462976', '182');
INSERT INTO `role_permission` VALUES ('4441', '1552934632142462976', '2013');
INSERT INTO `role_permission` VALUES ('4442', '1552934632142462976', '2010');
INSERT INTO `role_permission` VALUES ('4443', '1552934632142462976', '2011');
INSERT INTO `role_permission` VALUES ('4444', '1552934632142462976', '2012');
INSERT INTO `role_permission` VALUES ('4445', '1552934632142462976', '183');
INSERT INTO `role_permission` VALUES ('4446', '1552934632142462976', '184');
INSERT INTO `role_permission` VALUES ('5613', '1501859873070829568', '3687');
INSERT INTO `role_permission` VALUES ('5614', '1501859873070829568', '3723');
INSERT INTO `role_permission` VALUES ('5615', '1501859873070829568', '3694');
INSERT INTO `role_permission` VALUES ('5616', '1501859873070829568', '1');
INSERT INTO `role_permission` VALUES ('5617', '1501859873070829568', '104');
INSERT INTO `role_permission` VALUES ('5618', '1501859873070829568', '101');
INSERT INTO `role_permission` VALUES ('5619', '1501859873070829568', '3689');
INSERT INTO `role_permission` VALUES ('5620', '1501859873070829568', '3724');
INSERT INTO `role_permission` VALUES ('5621', '1501859873070829568', '3725');
INSERT INTO `role_permission` VALUES ('5622', '1501859873070829568', '3726');
INSERT INTO `role_permission` VALUES ('5623', '1501859873070829568', '3781');
INSERT INTO `role_permission` VALUES ('5624', '1501859873070829568', '3690');
INSERT INTO `role_permission` VALUES ('5625', '1501859873070829568', '3692');
INSERT INTO `role_permission` VALUES ('5626', '1501859873070829568', '3720');
INSERT INTO `role_permission` VALUES ('5627', '1501859873070829568', '3721');
INSERT INTO `role_permission` VALUES ('5628', '1501859873070829568', '3722');
INSERT INTO `role_permission` VALUES ('5629', '1501859873070829568', '3714');
INSERT INTO `role_permission` VALUES ('5630', '1501859873070829568', '3715');
INSERT INTO `role_permission` VALUES ('5631', '1501859873070829568', '3716');
INSERT INTO `role_permission` VALUES ('5632', '1501859873070829568', '3717');
INSERT INTO `role_permission` VALUES ('5633', '1501859873070829568', '3718');
INSERT INTO `role_permission` VALUES ('5634', '1501859873070829568', '3719');
INSERT INTO `role_permission` VALUES ('5635', '1501859873070829568', '3696');
INSERT INTO `role_permission` VALUES ('5636', '1501859873070829568', '3700');
INSERT INTO `role_permission` VALUES ('5637', '1501859873070829568', '3683');
INSERT INTO `role_permission` VALUES ('5638', '1501859873070829568', '3684');
INSERT INTO `role_permission` VALUES ('5639', '1501859873070829568', '3685');
INSERT INTO `role_permission` VALUES ('5640', '1501859873070829568', '3686');
INSERT INTO `role_permission` VALUES ('5641', '1501859873070829568', '3702');
INSERT INTO `role_permission` VALUES ('5642', '1501859873070829568', '3727');
INSERT INTO `role_permission` VALUES ('5643', '1501859873070829568', '3728');
INSERT INTO `role_permission` VALUES ('5644', '1501859873070829568', '3729');
INSERT INTO `role_permission` VALUES ('5645', '1501859873070829568', '3730');
INSERT INTO `role_permission` VALUES ('5646', '1501859873070829568', '3731');
INSERT INTO `role_permission` VALUES ('5647', '1501859873070829568', '3732');
INSERT INTO `role_permission` VALUES ('5648', '1501859873070829568', '3733');
INSERT INTO `role_permission` VALUES ('5649', '1501859873070829568', '3734');
INSERT INTO `role_permission` VALUES ('5650', '1501859873070829568', '3735');
INSERT INTO `role_permission` VALUES ('5651', '1501859873070829568', '3704');
INSERT INTO `role_permission` VALUES ('5652', '1501859873070829568', '1021');
INSERT INTO `role_permission` VALUES ('5653', '1501859873070829568', '1022');
INSERT INTO `role_permission` VALUES ('5654', '1501859873070829568', '1020');
INSERT INTO `role_permission` VALUES ('5655', '1501859873070829568', '1019');
INSERT INTO `role_permission` VALUES ('5656', '1501859873070829568', '102');
INSERT INTO `role_permission` VALUES ('5657', '1501859873070829568', '1007');
INSERT INTO `role_permission` VALUES ('5658', '1501859873070829568', '1008');
INSERT INTO `role_permission` VALUES ('5659', '1501859873070829568', '1009');
INSERT INTO `role_permission` VALUES ('5660', '1501859873070829568', '1010');
INSERT INTO `role_permission` VALUES ('5661', '1501859873070829568', '1011');
INSERT INTO `role_permission` VALUES ('5662', '1501859873070829568', '1001');
INSERT INTO `role_permission` VALUES ('5663', '1501859873070829568', '1002');
INSERT INTO `role_permission` VALUES ('5664', '1501859873070829568', '1003');
INSERT INTO `role_permission` VALUES ('5665', '1501859873070829568', '1004');
INSERT INTO `role_permission` VALUES ('5666', '1501859873070829568', '105');
INSERT INTO `role_permission` VALUES ('5667', '1501859873070829568', '1026');
INSERT INTO `role_permission` VALUES ('5668', '1501859873070829568', '1027');
INSERT INTO `role_permission` VALUES ('5669', '1501859873070829568', '1028');
INSERT INTO `role_permission` VALUES ('5670', '1501859873070829568', '1029');
INSERT INTO `role_permission` VALUES ('5671', '1501859873070829568', '106');
INSERT INTO `role_permission` VALUES ('5672', '1501859873070829568', '1035');
INSERT INTO `role_permission` VALUES ('5673', '1501859873070829568', '1036');
INSERT INTO `role_permission` VALUES ('5674', '1501859873070829568', '1032');
INSERT INTO `role_permission` VALUES ('5675', '1501859873070829568', '1033');
INSERT INTO `role_permission` VALUES ('5676', '1501859873070829568', '1034');
INSERT INTO `role_permission` VALUES ('5677', '1501859873070829568', '108');
INSERT INTO `role_permission` VALUES ('5678', '1501859873070829568', '1049');
INSERT INTO `role_permission` VALUES ('5679', '1501859873070829568', '1045');
INSERT INTO `role_permission` VALUES ('5680', '1501859873070829568', '1046');
INSERT INTO `role_permission` VALUES ('5681', '1501859873070829568', '1047');
INSERT INTO `role_permission` VALUES ('5682', '1501859873070829568', '1048');
INSERT INTO `role_permission` VALUES ('5683', '1504354687226007552', '3687');
INSERT INTO `role_permission` VALUES ('5684', '1504354687226007552', '1');
INSERT INTO `role_permission` VALUES ('5685', '1504354687226007552', '101');
INSERT INTO `role_permission` VALUES ('5686', '1504354687226007552', '3689');
INSERT INTO `role_permission` VALUES ('5687', '1504354687226007552', '3690');
INSERT INTO `role_permission` VALUES ('5688', '1504354687226007552', '3692');
INSERT INTO `role_permission` VALUES ('5689', '1504354687226007552', '3694');
INSERT INTO `role_permission` VALUES ('5690', '1504354687226007552', '3696');
INSERT INTO `role_permission` VALUES ('5691', '1504354687226007552', '3700');
INSERT INTO `role_permission` VALUES ('5692', '1504354687226007552', '3683');
INSERT INTO `role_permission` VALUES ('5693', '1504354687226007552', '3684');
INSERT INTO `role_permission` VALUES ('5694', '1504354687226007552', '3685');
INSERT INTO `role_permission` VALUES ('5695', '1504354687226007552', '3686');
INSERT INTO `role_permission` VALUES ('5696', '1504354687226007552', '3702');
INSERT INTO `role_permission` VALUES ('5697', '1504354687226007552', '3704');
INSERT INTO `role_permission` VALUES ('5698', '1504354687226007552', '102');
INSERT INTO `role_permission` VALUES ('5699', '1504354687226007552', '1007');
INSERT INTO `role_permission` VALUES ('5700', '1504354687226007552', '1008');
INSERT INTO `role_permission` VALUES ('5701', '1504354687226007552', '1009');
INSERT INTO `role_permission` VALUES ('5702', '1504354687226007552', '1010');
INSERT INTO `role_permission` VALUES ('5703', '1504354687226007552', '1011');
INSERT INTO `role_permission` VALUES ('5704', '1504354687226007552', '1001');
INSERT INTO `role_permission` VALUES ('5705', '1504354687226007552', '1002');
INSERT INTO `role_permission` VALUES ('5706', '1504354687226007552', '1003');
INSERT INTO `role_permission` VALUES ('5707', '1504354687226007552', '1004');
INSERT INTO `role_permission` VALUES ('5708', '1504354687226007552', '105');
INSERT INTO `role_permission` VALUES ('5709', '1504354687226007552', '1026');
INSERT INTO `role_permission` VALUES ('5710', '1504354687226007552', '1027');
INSERT INTO `role_permission` VALUES ('5711', '1504354687226007552', '1028');
INSERT INTO `role_permission` VALUES ('5712', '1504354687226007552', '1029');
INSERT INTO `role_permission` VALUES ('5713', '1504354687226007552', '106');
INSERT INTO `role_permission` VALUES ('5714', '1504354687226007552', '1035');
INSERT INTO `role_permission` VALUES ('5715', '1504354687226007552', '1036');
INSERT INTO `role_permission` VALUES ('5716', '1504354687226007552', '1032');
INSERT INTO `role_permission` VALUES ('5717', '1504354687226007552', '1033');
INSERT INTO `role_permission` VALUES ('5718', '1504354687226007552', '1034');
INSERT INTO `role_permission` VALUES ('5719', '1503682070533906432', '3687');
INSERT INTO `role_permission` VALUES ('5720', '1503682070533906432', '3723');
INSERT INTO `role_permission` VALUES ('5721', '1503682070533906432', '3694');
INSERT INTO `role_permission` VALUES ('5722', '1503682070533906432', '1');
INSERT INTO `role_permission` VALUES ('5723', '1503682070533906432', '104');
INSERT INTO `role_permission` VALUES ('5724', '1503682070533906432', '101');
INSERT INTO `role_permission` VALUES ('5725', '1503682070533906432', '3689');
INSERT INTO `role_permission` VALUES ('5726', '1503682070533906432', '3724');
INSERT INTO `role_permission` VALUES ('5727', '1503682070533906432', '3725');
INSERT INTO `role_permission` VALUES ('5728', '1503682070533906432', '3726');
INSERT INTO `role_permission` VALUES ('5729', '1503682070533906432', '3781');
INSERT INTO `role_permission` VALUES ('5730', '1503682070533906432', '3690');
INSERT INTO `role_permission` VALUES ('5731', '1503682070533906432', '3692');
INSERT INTO `role_permission` VALUES ('5732', '1503682070533906432', '3720');
INSERT INTO `role_permission` VALUES ('5733', '1503682070533906432', '3721');
INSERT INTO `role_permission` VALUES ('5734', '1503682070533906432', '3722');
INSERT INTO `role_permission` VALUES ('5735', '1503682070533906432', '3790');
INSERT INTO `role_permission` VALUES ('5736', '1503682070533906432', '3714');
INSERT INTO `role_permission` VALUES ('5737', '1503682070533906432', '3715');
INSERT INTO `role_permission` VALUES ('5738', '1503682070533906432', '3716');
INSERT INTO `role_permission` VALUES ('5739', '1503682070533906432', '3717');
INSERT INTO `role_permission` VALUES ('5740', '1503682070533906432', '3718');
INSERT INTO `role_permission` VALUES ('5741', '1503682070533906432', '3719');
INSERT INTO `role_permission` VALUES ('5742', '1503682070533906432', '3789');
INSERT INTO `role_permission` VALUES ('5743', '1503682070533906432', '3696');
INSERT INTO `role_permission` VALUES ('5744', '1503682070533906432', '3700');
INSERT INTO `role_permission` VALUES ('5745', '1503682070533906432', '3683');
INSERT INTO `role_permission` VALUES ('5746', '1503682070533906432', '3684');
INSERT INTO `role_permission` VALUES ('5747', '1503682070533906432', '3685');
INSERT INTO `role_permission` VALUES ('5748', '1503682070533906432', '3686');
INSERT INTO `role_permission` VALUES ('5749', '1503682070533906432', '3702');
INSERT INTO `role_permission` VALUES ('5750', '1503682070533906432', '3727');
INSERT INTO `role_permission` VALUES ('5751', '1503682070533906432', '3728');
INSERT INTO `role_permission` VALUES ('5752', '1503682070533906432', '3729');
INSERT INTO `role_permission` VALUES ('5753', '1503682070533906432', '3730');
INSERT INTO `role_permission` VALUES ('5754', '1503682070533906432', '3731');
INSERT INTO `role_permission` VALUES ('5755', '1503682070533906432', '3732');
INSERT INTO `role_permission` VALUES ('5756', '1503682070533906432', '3733');
INSERT INTO `role_permission` VALUES ('5757', '1503682070533906432', '3734');
INSERT INTO `role_permission` VALUES ('5758', '1503682070533906432', '3735');
INSERT INTO `role_permission` VALUES ('5759', '1503682070533906432', '3704');
INSERT INTO `role_permission` VALUES ('5760', '1503682070533906432', '1021');
INSERT INTO `role_permission` VALUES ('5761', '1503682070533906432', '1022');
INSERT INTO `role_permission` VALUES ('5762', '1503682070533906432', '1020');
INSERT INTO `role_permission` VALUES ('5763', '1503682070533906432', '1019');
INSERT INTO `role_permission` VALUES ('5764', '1503682070533906432', '3779');
INSERT INTO `role_permission` VALUES ('5765', '1503682070533906432', '102');
INSERT INTO `role_permission` VALUES ('5766', '1503682070533906432', '1007');
INSERT INTO `role_permission` VALUES ('5767', '1503682070533906432', '1008');
INSERT INTO `role_permission` VALUES ('5768', '1503682070533906432', '1009');
INSERT INTO `role_permission` VALUES ('5769', '1503682070533906432', '1010');
INSERT INTO `role_permission` VALUES ('5770', '1503682070533906432', '1011');
INSERT INTO `role_permission` VALUES ('5771', '1503682070533906432', '1001');
INSERT INTO `role_permission` VALUES ('5772', '1503682070533906432', '1002');
INSERT INTO `role_permission` VALUES ('5773', '1503682070533906432', '1003');
INSERT INTO `role_permission` VALUES ('5774', '1503682070533906432', '1004');
INSERT INTO `role_permission` VALUES ('5775', '1503682070533906432', '105');
INSERT INTO `role_permission` VALUES ('5776', '1503682070533906432', '1026');
INSERT INTO `role_permission` VALUES ('5777', '1503682070533906432', '1027');
INSERT INTO `role_permission` VALUES ('5778', '1503682070533906432', '1028');
INSERT INTO `role_permission` VALUES ('5779', '1503682070533906432', '1029');
INSERT INTO `role_permission` VALUES ('5780', '1503682070533906432', '106');
INSERT INTO `role_permission` VALUES ('5781', '1503682070533906432', '1035');
INSERT INTO `role_permission` VALUES ('5782', '1503682070533906432', '1036');
INSERT INTO `role_permission` VALUES ('5783', '1503682070533906432', '1032');
INSERT INTO `role_permission` VALUES ('5784', '1503682070533906432', '1033');
INSERT INTO `role_permission` VALUES ('5785', '1503682070533906432', '1034');
INSERT INTO `role_permission` VALUES ('5786', '1503682070533906432', '108');
INSERT INTO `role_permission` VALUES ('5787', '1503682070533906432', '1049');
INSERT INTO `role_permission` VALUES ('5788', '1503682070533906432', '1045');
INSERT INTO `role_permission` VALUES ('5789', '1503682070533906432', '1046');
INSERT INTO `role_permission` VALUES ('5790', '1503682070533906432', '1047');
INSERT INTO `role_permission` VALUES ('5791', '1503682070533906432', '1048');
INSERT INTO `role_permission` VALUES ('5890', '1686276112903884800', '3687');
INSERT INTO `role_permission` VALUES ('5891', '1686276112903884800', '1');
INSERT INTO `role_permission` VALUES ('5892', '1686276112903884800', '104');
INSERT INTO `role_permission` VALUES ('5893', '1686276112903884800', '102');
INSERT INTO `role_permission` VALUES ('5894', '1686276112903884800', '3689');
INSERT INTO `role_permission` VALUES ('5895', '1686276112903884800', '3723');
INSERT INTO `role_permission` VALUES ('5896', '1686276112903884800', '3724');
INSERT INTO `role_permission` VALUES ('5897', '1686276112903884800', '3725');
INSERT INTO `role_permission` VALUES ('5898', '1686276112903884800', '3780');
INSERT INTO `role_permission` VALUES ('5899', '1686276112903884800', '3726');
INSERT INTO `role_permission` VALUES ('5900', '1686276112903884800', '3749');
INSERT INTO `role_permission` VALUES ('5901', '1686276112903884800', '3750');
INSERT INTO `role_permission` VALUES ('5902', '1686276112903884800', '3751');
INSERT INTO `role_permission` VALUES ('5903', '1686276112903884800', '3752');
INSERT INTO `role_permission` VALUES ('5904', '1686276112903884800', '3753');
INSERT INTO `role_permission` VALUES ('5905', '1686276112903884800', '3754');
INSERT INTO `role_permission` VALUES ('5906', '1686276112903884800', '3755');
INSERT INTO `role_permission` VALUES ('5907', '1686276112903884800', '3756');
INSERT INTO `role_permission` VALUES ('5908', '1686276112903884800', '3757');
INSERT INTO `role_permission` VALUES ('5909', '1686276112903884800', '3758');
INSERT INTO `role_permission` VALUES ('5910', '1686276112903884800', '3759');
INSERT INTO `role_permission` VALUES ('5911', '1686276112903884800', '3760');
INSERT INTO `role_permission` VALUES ('5912', '1686276112903884800', '3761');
INSERT INTO `role_permission` VALUES ('5913', '1686276112903884800', '3762');
INSERT INTO `role_permission` VALUES ('5914', '1686276112903884800', '3763');
INSERT INTO `role_permission` VALUES ('5915', '1686276112903884800', '3764');
INSERT INTO `role_permission` VALUES ('5916', '1686276112903884800', '3765');
INSERT INTO `role_permission` VALUES ('5917', '1686276112903884800', '3766');
INSERT INTO `role_permission` VALUES ('5918', '1686276112903884800', '3781');
INSERT INTO `role_permission` VALUES ('5919', '1686276112903884800', '3782');
INSERT INTO `role_permission` VALUES ('5920', '1686276112903884800', '3783');
INSERT INTO `role_permission` VALUES ('5921', '1686276112903884800', '3690');
INSERT INTO `role_permission` VALUES ('5922', '1686276112903884800', '3692');
INSERT INTO `role_permission` VALUES ('5923', '1686276112903884800', '3720');
INSERT INTO `role_permission` VALUES ('5924', '1686276112903884800', '3721');
INSERT INTO `role_permission` VALUES ('5925', '1686276112903884800', '3722');
INSERT INTO `role_permission` VALUES ('5926', '1686276112903884800', '3790');
INSERT INTO `role_permission` VALUES ('5927', '1686276112903884800', '3694');
INSERT INTO `role_permission` VALUES ('5928', '1686276112903884800', '3714');
INSERT INTO `role_permission` VALUES ('5929', '1686276112903884800', '3715');
INSERT INTO `role_permission` VALUES ('5930', '1686276112903884800', '3716');
INSERT INTO `role_permission` VALUES ('5931', '1686276112903884800', '3717');
INSERT INTO `role_permission` VALUES ('5932', '1686276112903884800', '3718');
INSERT INTO `role_permission` VALUES ('5933', '1686276112903884800', '3719');
INSERT INTO `role_permission` VALUES ('5934', '1686276112903884800', '3784');
INSERT INTO `role_permission` VALUES ('5935', '1686276112903884800', '3789');
INSERT INTO `role_permission` VALUES ('5936', '1686276112903884800', '3696');
INSERT INTO `role_permission` VALUES ('5937', '1686276112903884800', '3768');
INSERT INTO `role_permission` VALUES ('5938', '1686276112903884800', '3769');
INSERT INTO `role_permission` VALUES ('5939', '1686276112903884800', '3770');
INSERT INTO `role_permission` VALUES ('5940', '1686276112903884800', '3698');
INSERT INTO `role_permission` VALUES ('5941', '1686276112903884800', '3736');
INSERT INTO `role_permission` VALUES ('5942', '1686276112903884800', '3700');
INSERT INTO `role_permission` VALUES ('5943', '1686276112903884800', '3683');
INSERT INTO `role_permission` VALUES ('5944', '1686276112903884800', '3684');
INSERT INTO `role_permission` VALUES ('5945', '1686276112903884800', '3685');
INSERT INTO `role_permission` VALUES ('5946', '1686276112903884800', '3686');
INSERT INTO `role_permission` VALUES ('5947', '1686276112903884800', '3702');
INSERT INTO `role_permission` VALUES ('5948', '1686276112903884800', '3727');
INSERT INTO `role_permission` VALUES ('5949', '1686276112903884800', '3728');
INSERT INTO `role_permission` VALUES ('5950', '1686276112903884800', '3729');
INSERT INTO `role_permission` VALUES ('5951', '1686276112903884800', '3730');
INSERT INTO `role_permission` VALUES ('5952', '1686276112903884800', '3731');
INSERT INTO `role_permission` VALUES ('5953', '1686276112903884800', '3732');
INSERT INTO `role_permission` VALUES ('5954', '1686276112903884800', '3733');
INSERT INTO `role_permission` VALUES ('5955', '1686276112903884800', '3734');
INSERT INTO `role_permission` VALUES ('5956', '1686276112903884800', '3735');
INSERT INTO `role_permission` VALUES ('5957', '1686276112903884800', '3801');
INSERT INTO `role_permission` VALUES ('5958', '1686276112903884800', '3802');
INSERT INTO `role_permission` VALUES ('5959', '1686276112903884800', '3803');
INSERT INTO `role_permission` VALUES ('5961', '1686276112903884800', '3806');
INSERT INTO `role_permission` VALUES ('5962', '1686276112903884800', '3807');
INSERT INTO `role_permission` VALUES ('5963', '1686276112903884800', '3808');
INSERT INTO `role_permission` VALUES ('5964', '1686276112903884800', '3809');
INSERT INTO `role_permission` VALUES ('5966', '1686276112903884800', '3812');
INSERT INTO `role_permission` VALUES ('5967', '1686276112903884800', '3813');
INSERT INTO `role_permission` VALUES ('5968', '1686276112903884800', '3814');
INSERT INTO `role_permission` VALUES ('5969', '1686276112903884800', '3815');
INSERT INTO `role_permission` VALUES ('5970', '1686276112903884800', '3825');
INSERT INTO `role_permission` VALUES ('5971', '1686276112903884800', '3826');
INSERT INTO `role_permission` VALUES ('5972', '1686276112903884800', '3827');
INSERT INTO `role_permission` VALUES ('5973', '1686276112903884800', '3816');
INSERT INTO `role_permission` VALUES ('5974', '1686276112903884800', '3828');
INSERT INTO `role_permission` VALUES ('5975', '1686276112903884800', '3829');
INSERT INTO `role_permission` VALUES ('5976', '1686276112903884800', '3830');
INSERT INTO `role_permission` VALUES ('5977', '1686276112903884800', '3817');
INSERT INTO `role_permission` VALUES ('5978', '1686276112903884800', '3831');
INSERT INTO `role_permission` VALUES ('5979', '1686276112903884800', '3837');
INSERT INTO `role_permission` VALUES ('5980', '1686276112903884800', '3838');
INSERT INTO `role_permission` VALUES ('5981', '1686276112903884800', '3820');
INSERT INTO `role_permission` VALUES ('5982', '1686276112903884800', '3821');
INSERT INTO `role_permission` VALUES ('5983', '1686276112903884800', '3832');
INSERT INTO `role_permission` VALUES ('5984', '1686276112903884800', '3833');
INSERT INTO `role_permission` VALUES ('5985', '1686276112903884800', '3822');
INSERT INTO `role_permission` VALUES ('5986', '1686276112903884800', '3824');
INSERT INTO `role_permission` VALUES ('5987', '1686276112903884800', '3834');
INSERT INTO `role_permission` VALUES ('5988', '1686276112903884800', '3835');
INSERT INTO `role_permission` VALUES ('5989', '1686276112903884800', '3836');
INSERT INTO `role_permission` VALUES ('5990', '1686276112903884800', '3823');
INSERT INTO `role_permission` VALUES ('5991', '1686276112903884800', '1021');
INSERT INTO `role_permission` VALUES ('5992', '1686276112903884800', '1022');
INSERT INTO `role_permission` VALUES ('5993', '1686276112903884800', '1020');
INSERT INTO `role_permission` VALUES ('5994', '1686276112903884800', '1023');
INSERT INTO `role_permission` VALUES ('5995', '1686276112903884800', '1019');
INSERT INTO `role_permission` VALUES ('5996', '1686276112903884800', '1007');
INSERT INTO `role_permission` VALUES ('5997', '1686276112903884800', '1008');
INSERT INTO `role_permission` VALUES ('5998', '1686276112903884800', '1009');
INSERT INTO `role_permission` VALUES ('5999', '1686276112903884800', '1010');
INSERT INTO `role_permission` VALUES ('6000', '1686276112903884800', '101');
INSERT INTO `role_permission` VALUES ('6001', '1686276112903884800', '3713');
INSERT INTO `role_permission` VALUES ('6002', '1686276112903884800', '1001');
INSERT INTO `role_permission` VALUES ('6003', '1686276112903884800', '1002');
INSERT INTO `role_permission` VALUES ('6004', '1686276112903884800', '1003');
INSERT INTO `role_permission` VALUES ('6005', '1686276112903884800', '1004');
INSERT INTO `role_permission` VALUES ('6006', '1686276112903884800', '3745');
INSERT INTO `role_permission` VALUES ('6007', '1686276112903884800', '3742');

-- ----------------------------
-- Table structure for role_task
-- ----------------------------
DROP TABLE IF EXISTS `role_task`;
CREATE TABLE `role_task` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint DEFAULT NULL,
  `task_id` bigint DEFAULT NULL,
  `task_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `task_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of role_task
-- ----------------------------
INSERT INTO `role_task` VALUES ('1', '1501859873070829568', '33', 'oqHfS8K0', '安全帽检测');
INSERT INTO `role_task` VALUES ('2', '1501859873070829568', '326', 'iwM9KJum', '人车检测');
INSERT INTO `role_task` VALUES ('3', '1501859873070829568', '327', 'jm5HngvF', '无人机-违章建筑');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名',
  `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `sex` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性别',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'zhangsan', '张三', '123', '男', '888@qq.com', '呼伦贝尔');
INSERT INTO `sys_user` VALUES ('2', 'lisi', '李四', '123', '男', '777@qq.com', '北京市');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `pwd` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_sex` tinyint DEFAULT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `mobile_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `pic_url` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `role_key` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  `reg_type` bigint DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `del_flag` tinyint DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  `login_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `login_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=416107238 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '超级管理员', 'admin', 'd9a3b7d666d633d3a3d91bd8bbe4e1f74d8aff6180e883978cf7e4ac9c87ba6d', '123456', '1', null, '15208492742', null, 'SuperAdmin', '1501859873070829561', '8', '1', '0', '1645431809000', '192.168.1.124', '1645431809000');
INSERT INTO `user` VALUES ('2', '张三', 'test', 'b85e1aaa8cf67df916c343028cbd663e47f55b7a6f9405848178ce4d5d842a13', 'test123', '1', null, '15208492741', null, 'Admin', '1501859873070829562', '7', '1', '0', '1645431809000', '192.168.1.124', '1645431809000');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1501859873070829561', null);
INSERT INTO `user_role` VALUES ('2', '2', '1501859873070829562', null);
INSERT INTO `user_role` VALUES ('3', '3', '1501859873070829568', null);
