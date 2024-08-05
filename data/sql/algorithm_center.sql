/*
Navicat MySQL Data Transfer

Source Server         : 192.168.2.241
Source Server Version : 80027
Source Host           : 192.168.2.241:3306
Source Database       : algorithm_center

Target Server Type    : MYSQL
Target Server Version : 80027
File Encoding         : 65001

Date: 2024-07-31 09:17:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for alarm_data
-- ----------------------------
DROP TABLE IF EXISTS `alarm_data`;
CREATE TABLE `alarm_data` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `alarm_type` bigint DEFAULT NULL,
  `alarm_image` varchar(20) DEFAULT NULL,
  `alarm_time` bigint DEFAULT NULL,
  `task_no` varchar(20) DEFAULT NULL,
  `model_no` varchar(20) DEFAULT NULL,
  `algorithmModel_name` varchar(20) DEFAULT NULL,
  `customer_no` varchar(20) DEFAULT NULL,
  `customer_name` varchar(20) DEFAULT NULL,
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
  `core_tech` varchar(20) DEFAULT NULL,
  `shell_key` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `latestTraining_time` bigint DEFAULT NULL,
  `online_time` bigint DEFAULT NULL,
  `conf_threshold` varchar(20) DEFAULT NULL,
  `nms_threshold` varchar(20) DEFAULT NULL,
  `label_list` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `oos_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `order_num` int DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of algorithm_model
-- ----------------------------
INSERT INTO `algorithm_model` VALUES ('1', '吸烟检测', 'smoke_detec', 'w3bvCceu', '112', 'cnn', 'yolov8_detect_tensorrt', '1701014400000', '1701100800000', '0.5', '0.65', '吸烟,未吸烟', '/algorithmmodel/bbea322ad6464615986e029745c9.engine', '6', '0', '1700644344986');
INSERT INTO `algorithm_model` VALUES ('6', '安全帽检测', 'yolov8_safety_hat', 'p3dCKdc5', '111', 'knn', 'yolov8_detect_tensorrt', '1700616808000', '1700668800000', '0.5', '0.65', 'helmet,no-helmet', 'http://192.168.2.241:9001/default/example.engine', '3', '1', '1700646457621');
INSERT INTO `algorithm_model` VALUES ('8', '人车检测', 'yolov8n', 'sj9hds2b', '101', 'cnn', 'yolov8_detect_tensorrt', '1701187200000', '1701273600000', '0.5', '0.65', '\"person\",\"bicycle\",\"car\",\"motorcycle\",\"airplane\",\"bus\",\"train\",\"truck\",\"boat\",\"trafficlight\",\"firehydrant\",\"stopsign\",\"parkingmeter\",\"bench\",\"bird\",\"cat\",\"dog\",\"horse\",\"sheep\",\"cow\",\"elephant\",\"bear\",\"zebra\",\"giraffe\",\"backpack\",\"umbrella\",\"handbag\",\"tie\",\"suitcase\",\"frisbee\",\"skis\",\"snowboard\",\"sportsball\",\"kite\",\"baseballbat\",\"baseballglove\",\"skateboard\",\"surfboard\",\"tennisracket\",\"bottle\",\"wineglass\",\"cup\",\"fork\",\"knife\",\"spoon\",\"bowl\",\"banana\",\"apple\",\"sandwich\",\"orange\",\"broccoli\",\"carrot\",\"hotdog\",\"pizza\",\"donut\",\"cake\",\"chair\",\"couch\",\"pottedplant\",\"bed\",\"diningtable\",\"toilet\",\"tv\",\"laptop\",\"mouse\",\"remote\",\"keyboard\",\"cellphone\",\"microwave\",\"oven\",\"toaster\",\"sink\",\"refrigerator\",\"book\",\"clock\",\"vase\",\"scissors\",\"teddybear\",\"hairdrier\",\"toothbrush\"', '/algorithmmodel/fbde0e3709ef4dc9bb81ecc53bb4.engine', '1', '1', '1701163417481');
INSERT INTO `algorithm_model` VALUES ('12', '烟火检测', 'yolov8s-fs', '6fz4FErL', '101', 'cnn', 'yolov8_detect_tensorrt', '1701187200000', '1701273600000', '0.5', '0.65', '\"person\",\"bicycle\",\"car\",\"motorcycle\",\"airplane\",\"bus\",\"train\",\"truck\",\"boat\",\"trafficlight\",\"firehydrant\",\"stopsign\",\"parkingmeter\",\"bench\",\"bird\",\"cat\",\"dog\",\"horse\",\"sheep\",\"cow\",\"elephant\",\"bear\",\"zebra\",\"giraffe\",\"backpack\",\"umbrella\",\"handbag\",\"tie\",\"suitcase\",\"frisbee\",\"skis\",\"snowboard\",\"sportsball\",\"kite\",\"baseballbat\",\"baseballglove\",\"skateboard\",\"surfboard\",\"tennisracket\",\"bottle\",\"wineglass\",\"cup\",\"fork\",\"knife\",\"spoon\",\"bowl\",\"banana\",\"apple\",\"sandwich\",\"orange\",\"broccoli\",\"carrot\",\"hotdog\",\"pizza\",\"donut\",\"cake\",\"chair\",\"couch\",\"pottedplant\",\"bed\",\"diningtable\",\"toilet\",\"tv\",\"laptop\",\"mouse\",\"remote\",\"keyboard\",\"cellphone\",\"microwave\",\"oven\",\"toaster\",\"sink\",\"refrigerator\",\"book\",\"clock\",\"vase\",\"scissors\",\"teddybear\",\"hairdrier\",\"toothbrush\"', '/algorithmmodel/518c0c3b6afd402f895716e69220.engine', '6', '1', '1701163417481');
INSERT INTO `algorithm_model` VALUES ('13', '烟雾检测', 'yolov8_sl_fh', 'dXgAaKGI', '104', 'CNN', 'yolov8_detect_tensorrt', '1701014400000', '1701014400000', '0.5', '0.65', 'smoke,no-smoke', '/algorithmmodel/809de03622bd442299c99e3df9d0.engine', '2', '1', '1718340228067');
INSERT INTO `algorithm_model` VALUES ('15', '电动车头盔', 'ebike', 'Ha06qSwC', '113', 'cnn', 'yolov8_detect_tensorrt', '1720713600000', '1720713600000', '0.5', '0.65', '{\"Helmet\",\"NO-Helmet\",\"Rider\"}', '/algorithmmodel/4326d5ef50524ba7aa923e63dd11.engine', '1', '1', '1720779050065');
INSERT INTO `algorithm_model` VALUES ('17', '道路墙体裂缝检测', 'crack', 'vA3rnst5', '115', 'CNN', 'yolov8_detect_tensorrt', '1721059200000', '1721059200000', '0.5', '0.65', '{\"crack\"}', '/algorithmmodel/350b6cea2eef4821bf4ef153345e.engine', '1', '1', '1721270722801');
INSERT INTO `algorithm_model` VALUES ('18', '车牌识别', 'car_plate_rec_yolov8s', 'JXWMzmLb', '116', 'CNN', 'yolov8_detect_tensorrt', '1720627200000', '1720627200000', '0.5', '0.65', '{\"CAR-PLATE\"}', '/algorithmmodel/d254559d79ea461bb82654c54ac6.engine', '1', '1', '1721276663260');
INSERT INTO `algorithm_model` VALUES ('19', '裸土识别', 'bare_soil', 'P4uGD0Ab', '109', 'cnn', 'yolov8_detect_tensorrt', '1721664000000', '1721664000000', '0.5', '0.65', '{\"bare_soil\"}', '/algorithmmodel/9b3e8449b07c4b6c873df26377d4.engine', '1', '1', '1721722296436');
INSERT INTO `algorithm_model` VALUES ('20', '河道漂浮物', 'river', 'fM6NO7M4', '107', 'cnn', 'yolov8_detect_tensorrt', '1721664000000', '1721664000000', '0.5', '0.65', '{\"floating_obj\"}', '/algorithmmodel/4abbb60f9d0044e39a3aac2f19e6.engine', '1', '1', '1721728974935');

-- ----------------------------
-- Table structure for algorithm_task
-- ----------------------------
DROP TABLE IF EXISTS `algorithm_task`;
CREATE TABLE `algorithm_task` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `task_name` varchar(50) DEFAULT NULL,
  `task_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `model_no` varchar(20) DEFAULT NULL,
  `algorithmModel_name` varchar(20) DEFAULT NULL,
  `customer_no` varchar(20) DEFAULT NULL,
  `customer_name` varchar(20) DEFAULT NULL,
  `videoBase_info` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `videoPlay_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `pushVideoPlay_url` varchar(500) DEFAULT NULL,
  `computingVideoPlay_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `streamServer_url` varchar(500) DEFAULT NULL,
  `skip_frame` int DEFAULT NULL,
  `push_frequency` int DEFAULT NULL,
  `work_dir` varchar(500) DEFAULT NULL,
  `shell_key` varchar(100) DEFAULT NULL,
  `firstExec_time` bigint DEFAULT NULL,
  `latestExec_time` bigint DEFAULT NULL,
  `alarm_amount` int DEFAULT NULL,
  `latestAlarm_time` bigint DEFAULT NULL,
  `order_num` int DEFAULT NULL,
  `task_status` tinyint DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=168 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of algorithm_task
-- ----------------------------
INSERT INTO `algorithm_task` VALUES ('7', '烟火检测1', 'XpKA773K', '6fz4FErL', '烟火检测', 'hymv8g1b', '金石科技', '', '/data/app/yolo/data/sl-fh/4_699pic_01cgxr_spxy.mp4', 'ori_XpKA773K.live.flv', 'dec_XpKA773K.live.flv', 'dec_XpKA773K?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1720070739468', '1721288673816', null, null, '4', '0', '1720070711777');
INSERT INTO `algorithm_task` VALUES ('10', '人车检测1', 'Qi85rGoy', 'sj9hds2b', '人车检测', 'hymv8g1b', '金石科技', null, 'wss://face.jszhcs.cn:9686/ws_flv/live/51011303001327000002_51011399701327100054_0113990054.flv', 'ori_Qi85rGoy.live.flv', 'dec_Qi85rGoy.live.flv', 'dec_Qi85rGoy?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1720664437957', '1721806271022', null, null, null, '0', '1720664230778');
INSERT INTO `algorithm_task` VALUES ('13', '烟雾检测', 'n1Logpna', 'dXgAaKGI', '烟雾检测', 'bnck6b2d', '测试客户2', '', '/data/app/yolo/data/sl-fh/1.699pic_01crz4_spxy.mp4', 'ori_n1Logpna.live.flv', 'dec_n1Logpna.live.flv', 'dec_n1Logpna?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1720754190714', '1721806888289', null, null, null, '0', '1720754180239');
INSERT INTO `algorithm_task` VALUES ('18', '电动车头盔', 'UDjCk34T', 'Ha06qSwC', '电动车头盔', 'bnck6b2d', '测试客户2', '', 'https://face.jszhcs.cn:9686/flv/live/51011303001327000073_51011399701327100145_0113990145.flv', 'ori_UDjCk34T.live.flv', 'dec_UDjCk34T.live.flv', 'dec_UDjCk34T?sign=41db35390ddad33f83944f44b8b75ded', '3', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1720779482918', '1721807031167', null, null, null, '0', '1720779470602');
INSERT INTO `algorithm_task` VALUES ('28', '吸烟检测', 'VToib7pc', 'w3bvCceu', '吸烟检测', 'hymv8g1b', '测试客户1', null, 'wss://face.jszhcs.cn:9686/ws_flv/live/51011303001327000400_51011399701327100526_0113990526.flv', 'ori_VToib7pc.live.flv', 'dec_VToib7pc.live.flv', 'dec_VToib7pc?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8', '1721025752076', '1721025752076', null, null, null, '0', '1721025750314');
INSERT INTO `algorithm_task` VALUES ('33', '安全帽检测', 'oqHfS8K0', 'p3dCKdc5', '安全帽检测', 'hymv8g1b', '金石科技', null, '/data/app/yolo/data/safety_hat/1_sa.699pic_01ekb5_spxy.mp4', 'ori_oqHfS8K0.live.flv', 'dec_oqHfS8K0.live.flv', 'dec_oqHfS8K0?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721209303529', '1721806995512', null, null, null, '0', '1721027414104');
INSERT INTO `algorithm_task` VALUES ('55', '人车检测', 'QHGbrfKB', 'sj9hds2b', '人车检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/51011303001327000077_51011399701327100149_0113990149.flv', 'ori_QHGbrfKB.live.flv', 'dec_QHGbrfKB.live.flv', 'dec_QHGbrfKB?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721208426164', '1721208864133', null, null, null, '0', '1721208424392');
INSERT INTO `algorithm_task` VALUES ('56', '电动车头盔', 'tkVqdDin', 'Ha06qSwC', '电动车头盔', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/51011303001327000077_51011399701327100149_0113990149.flv', 'ori_tkVqdDin.live.flv', 'dec_tkVqdDin.live.flv', 'dec_tkVqdDin?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721208964876', '1721807069922', null, null, null, '0', '1721208963154');
INSERT INTO `algorithm_task` VALUES ('57', '电动车头盔', 'wad6Zlnk', 'Ha06qSwC', '电动车头盔', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/51011303001327000077_51011399701327100149_0113990149.flv', 'ori_wad6Zlnk.live.flv', 'dec_wad6Zlnk.live.flv', 'dec_wad6Zlnk?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721209590576', '1721353047513', null, null, null, '0', '1721209588865');
INSERT INTO `algorithm_task` VALUES ('58', '电动车头盔', 'VflqaVqr', 'Ha06qSwC', '电动车头盔', 'hymv8g1b', '金石科技', null, 'https://face.jszhcs.cn:9686/flv/live/51011303001327000077_51011399701327100149_0113990149.flv', 'ori_VflqaVqr.live.flv', 'dec_VflqaVqr.live.flv', 'dec_VflqaVqr?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721209941281', '1721807091501', null, null, null, '0', '1721209939672');
INSERT INTO `algorithm_task` VALUES ('59', '人车检测', 'irPOuJKK', 'sj9hds2b', '人车检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/51011303001327000077_51011399701327100149_0113990149.flv', 'ori_irPOuJKK.live.flv', 'dec_irPOuJKK.live.flv', 'dec_irPOuJKK?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721210315703', '1721210315703', null, null, null, '0', '1721210314167');
INSERT INTO `algorithm_task` VALUES ('60', '人车检测', 'mrn3R5lM', 'sj9hds2b', '人车检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/51011303001327000077_51011399701327100149_0113990149.flv', 'ori_mrn3R5lM.live.flv', 'dec_mrn3R5lM.live.flv', 'dec_mrn3R5lM?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721263082748', '1721263082748', null, null, null, '0', '1721263081488');
INSERT INTO `algorithm_task` VALUES ('61', '烟雾检测', 'ngsREBsi', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/51011318001327000018_51011399701327100001_0113990001.flv', 'ori_ngsREBsi.live.flv', 'dec_ngsREBsi.live.flv', 'dec_ngsREBsi?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721263358556', '1721263358556', null, null, null, '0', '1721263356594');
INSERT INTO `algorithm_task` VALUES ('62', '大同无人机烟雾', 'Stc2682A', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, '/data/app/yolo/data/sl-fh/072315.mp4', 'ori_Stc2682A.live.flv', 'dec_Stc2682A.live.flv', 'dec_Stc2682A?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721263890980', '1721699975327', null, null, null, '0', '1721263889620');
INSERT INTO `algorithm_task` VALUES ('63', '烟雾检测', 'y6EeAmZI', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://scjskj.tpddns.cn:19001/algcenter-video/1.699pic_01crz4_spxy.mp4', 'ori_y6EeAmZI.live.flv', 'dec_y6EeAmZI.live.flv', 'dec_y6EeAmZI?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721264977738', '1721264977738', null, null, null, '0', '1721264976386');
INSERT INTO `algorithm_task` VALUES ('64', '烟雾检测', 'OUpuYo3P', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://scjskj.tpddns.cn:19001/algcenter-video/1.699pic_01crz4_spxy.mp4', 'ori_OUpuYo3P.live.flv', 'dec_OUpuYo3P.live.flv', 'dec_OUpuYo3P?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721265231712', '1721265231712', null, null, null, '0', '1721265230422');
INSERT INTO `algorithm_task` VALUES ('65', '烟雾检测', 'RkDsVHWR', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://scjskj.tpddns.cn:19001/algcenter-video/1.699pic_01crz4_spxy.mp4', 'ori_RkDsVHWR.live.flv', 'dec_RkDsVHWR.live.flv', 'dec_RkDsVHWR?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721265265801', '1721265265801', null, null, null, '0', '1721265264501');
INSERT INTO `algorithm_task` VALUES ('66', '烟雾检测555', 'PE8dCykG', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', '', '/data/app/yolo/data/sl-fh/1.699pic_01crz4_spxy.mp4', 'ori_PE8dCykG.live.flv', 'dec_PE8dCykG.live.flv', 'dec_PE8dCykG?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721265453956', '1721265453956', null, null, null, '0', '1721265428070');
INSERT INTO `algorithm_task` VALUES ('67', '烟雾检测', 'hwn4rsuT', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://scjskj.tpddns.cn:19001/algcenter-video/1.699pic_01crz4_spxy.mp4', 'ori_hwn4rsuT.live.flv', 'dec_hwn4rsuT.live.flv', 'dec_hwn4rsuT?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721265582509', '1721265582509', null, null, null, '0', '1721265581012');
INSERT INTO `algorithm_task` VALUES ('68', '烟雾检测', 'zmzkevqE', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://scjskj.tpddns.cn:19001/algcenter-video/1.699pic_01crz4_spxy.mp4', 'ori_zmzkevqE.live.flv', 'dec_zmzkevqE.live.flv', 'dec_zmzkevqE?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721266031060', '1721297072153', null, null, null, '0', '1721266029758');
INSERT INTO `algorithm_task` VALUES ('69', '烟雾检测', 'm3RuqpKp', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://scjskj.tpddns.cn:19001/algcenter-video/1.699pic_01crz4_spxy.mp4', 'ori_m3RuqpKp.live.flv', 'dec_m3RuqpKp.live.flv', 'dec_m3RuqpKp?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721266633002', '1721266633002', null, null, null, '0', '1721266631453');
INSERT INTO `algorithm_task` VALUES ('70', '烟雾检测', '7a3z533a', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://scjskj.tpddns.cn:19001/algcenter-video/1.699pic_01crz4_spxy.mp4', 'ori_7a3z533a.live.flv', 'dec_7a3z533a.live.flv', 'dec_7a3z533a?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721267964193', '1721267964193', null, null, null, '0', '1721267962534');
INSERT INTO `algorithm_task` VALUES ('71', '道路墙体裂缝检测', 'DM2dqP9o', 'vA3rnst5', '道路墙体裂缝检测', 'hymv8g1b', '金石科技', '', '/data/app/yolo/data/crack/001_output1.mp4', 'ori_DM2dqP9o.live.flv', 'dec_DM2dqP9o.live.flv', 'dec_DM2dqP9o?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721270761508', '1721806967584', null, null, null, '0', '1721270758699');
INSERT INTO `algorithm_task` VALUES ('72', '清泉烟雾检测', '1vu0WImT', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_1vu0WImT.live.flv', 'dec_1vu0WImT.live.flv', 'dec_1vu0WImT?sign=41db35390ddad33f83944f44b8b75ded', '1', '10', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721275419552', '1722308235056', null, null, null, '0', '1721275417842');
INSERT INTO `algorithm_task` VALUES ('73', '车牌识别', 'SF2x1kcn', 'JXWMzmLb', '车牌识别', 'bnck6b2d', '测试客户2', '', '/data/app/yolo/data/car/car5.mp4', 'ori_SF2x1kcn.live.flv', 'dec_SF2x1kcn.live.flv', 'dec_SF2x1kcn?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721276720393', '1721723719528', null, null, null, '0', '1721276714205');
INSERT INTO `algorithm_task` VALUES ('74', '烟雾检测', 'spSYeqCN', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://scjskj.tpddns.cn:19001/algcenter-video/1.699pic_01crz4_spxy.mp4', 'ori_spSYeqCN.live.flv', 'dec_spSYeqCN.live.flv', 'dec_spSYeqCN?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721279565125', '1721279565125', null, null, null, '0', '1721279563561');
INSERT INTO `algorithm_task` VALUES ('75', '烟雾检测', 'ePPO5By8', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://scjskj.tpddns.cn:19001/algcenter-video/1.699pic_01crz4_spxy.mp4', 'ori_ePPO5By8.live.flv', 'dec_ePPO5By8.live.flv', 'dec_ePPO5By8?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721283169385', '1721283169385', null, null, null, '0', '1721283167947');
INSERT INTO `algorithm_task` VALUES ('76', '安全帽检测', 'N9HFWPrG', 'p3dCKdc5', '安全帽检测', 'hymv8g1b', '金石科技', null, 'http://scjskj.tpddns.cn:19001/algcenter-video/1.699pic_01crz4_spxy.mp4', 'ori_N9HFWPrG.live.flv', 'dec_N9HFWPrG.live.flv', 'dec_N9HFWPrG?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721288919500');
INSERT INTO `algorithm_task` VALUES ('77', '烟雾检测', 'IDcVlxVh', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'ws://221.237.108.76:9685/ws_flv/live/stream_387.flv', 'ori_IDcVlxVh.live.flv', 'dec_IDcVlxVh.live.flv', 'dec_IDcVlxVh?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721291099529', '1721291099529', null, null, null, '0', '1721291097059');
INSERT INTO `algorithm_task` VALUES ('78', '烟雾检测', 'pEymR609', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_pEymR609.live.flv', 'dec_pEymR609.live.flv', 'dec_pEymR609?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721291378292', '1721291378292', null, null, null, '0', '1721291376513');
INSERT INTO `algorithm_task` VALUES ('79', '烟雾检测', 'yMJxAWti', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_yMJxAWti.live.flv', 'dec_yMJxAWti.live.flv', 'dec_yMJxAWti?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721291898758', '1721291898758', null, null, null, '0', '1721291897323');
INSERT INTO `algorithm_task` VALUES ('80', '烟雾检测', 'uJ9DGqbH', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_uJ9DGqbH.live.flv', 'dec_uJ9DGqbH.live.flv', 'dec_uJ9DGqbH?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721293718532', '1721293718532', null, null, null, '0', '1721293717129');
INSERT INTO `algorithm_task` VALUES ('81', '烟雾检测', 'WrdsIJxF', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_WrdsIJxF.live.flv', 'dec_WrdsIJxF.live.flv', 'dec_WrdsIJxF?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721295389553', '1721295389553', null, null, null, '0', '1721295388035');
INSERT INTO `algorithm_task` VALUES ('82', '烟雾检测', 'xdpyuAwD', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_xdpyuAwD.live.flv', 'dec_xdpyuAwD.live.flv', 'dec_xdpyuAwD?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721296616924', '1721296616924', null, null, null, '0', '1721296615613');
INSERT INTO `algorithm_task` VALUES ('83', '烟雾检测', 'gN2hNxKz', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/51011303001327000002_51011399701327100054_0113990054.flv', 'ori_gN2hNxKz.live.flv', 'dec_gN2hNxKz.live.flv', 'dec_gN2hNxKz?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721296700144', '1721296700144', null, null, null, '0', '1721296698803');
INSERT INTO `algorithm_task` VALUES ('84', '烟雾检测', '5Nhn5cmm', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://scjskj.tpddns.cn:19001/algcenter-video/1.699pic_01crz4_spxy.mp4', 'ori_5Nhn5cmm.live.flv', 'dec_5Nhn5cmm.live.flv', 'dec_5Nhn5cmm?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721296923024', '1721296923024', null, null, null, '0', '1721296921921');
INSERT INTO `algorithm_task` VALUES ('85', '烟雾检测', 'nzIvBeD4', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://scjskj.tpddns.cn:19001/algcenter-video/1.699pic_01crz4_spxy.mp4', 'ori_nzIvBeD4.live.flv', 'dec_nzIvBeD4.live.flv', 'dec_nzIvBeD4?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721297204994', '1721297204994', null, null, null, '0', '1721297201500');
INSERT INTO `algorithm_task` VALUES ('86', '烟雾检测', 'd5UkZsnB', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_d5UkZsnB.live.flv', 'dec_d5UkZsnB.live.flv', 'dec_d5UkZsnB?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721297629789', '1721297629789', null, null, null, '0', '1721297628631');
INSERT INTO `algorithm_task` VALUES ('87', '人车检测', 'wN69wNJV', 'sj9hds2b', '人车检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/51011303001327000077_51011399701327100149_0113990149.flv', 'ori_wN69wNJV.live.flv', 'dec_wN69wNJV.live.flv', 'dec_wN69wNJV?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721350131406', '1721806317689', null, null, null, '0', '1721350129948');
INSERT INTO `algorithm_task` VALUES ('88', '烟雾检测', 'UiRA1eee', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_UiRA1eee.live.flv', 'dec_UiRA1eee.live.flv', 'dec_UiRA1eee?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721355866747', '1721355866747', null, null, null, '0', '1721355865345');
INSERT INTO `algorithm_task` VALUES ('89', '烟雾检测', 'uREfzHod', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_uREfzHod.live.flv', 'dec_uREfzHod.live.flv', 'dec_uREfzHod?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721362406082', '1721362406082', null, null, null, '0', '1721362403668');
INSERT INTO `algorithm_task` VALUES ('90', '烟雾检测111', '5tazM9yn', 'dXgAaKGI', '烟雾检测', 'bnck6b2d', '测试客户2', '', 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_5tazM9yn.live.flv', 'dec_5tazM9yn.live.flv', 'dec_5tazM9yn?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721363119162', '1721363440347', null, null, null, '0', '1721363042827');
INSERT INTO `algorithm_task` VALUES ('91', '烟雾检测', 'TFtXc8S8', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_TFtXc8S8.live.flv', 'dec_TFtXc8S8.live.flv', 'dec_TFtXc8S8?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721363562418', '1721363562418', null, null, null, '0', '1721363561288');
INSERT INTO `algorithm_task` VALUES ('92', '烟雾检测', 'HFxdLNwg', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_HFxdLNwg.live.flv', 'dec_HFxdLNwg.live.flv', 'dec_HFxdLNwg?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721364466716', '1721364466716', null, null, null, '0', '1721364465279');
INSERT INTO `algorithm_task` VALUES ('93', '烟雾检测', 'jYNyWRSv', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_jYNyWRSv.live.flv', 'dec_jYNyWRSv.live.flv', 'dec_jYNyWRSv?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721366412533', '1721366412533', null, null, null, '0', '1721366410955');
INSERT INTO `algorithm_task` VALUES ('94', '烟雾检测', 'XwRO4w4n', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_XwRO4w4n.live.flv', 'dec_XwRO4w4n.live.flv', 'dec_XwRO4w4n?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721370404824', '1721370404824', null, null, null, '0', '1721370403175');
INSERT INTO `algorithm_task` VALUES ('95', '烟雾检测', 'UfjMiBlm', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_UfjMiBlm.live.flv', 'dec_UfjMiBlm.live.flv', 'dec_UfjMiBlm?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721371257034', '1721371257034', null, null, null, '0', '1721371255640');
INSERT INTO `algorithm_task` VALUES ('96', '烟雾检测', '0VNLN3N0', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_0VNLN3N0.live.flv', 'dec_0VNLN3N0.live.flv', 'dec_0VNLN3N0?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721372481950', '1721372481950', null, null, null, '0', '1721372480710');
INSERT INTO `algorithm_task` VALUES ('97', '人车检测', '1IqqFUOK', 'sj9hds2b', '人车检测', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_1IqqFUOK.live.flv', 'dec_1IqqFUOK.live.flv', 'dec_1IqqFUOK?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721609558647');
INSERT INTO `algorithm_task` VALUES ('98', '烟雾检测', 'gfldEAJ6', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_gfldEAJ6.live.flv', 'dec_gfldEAJ6.live.flv', 'dec_gfldEAJ6?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721609558646');
INSERT INTO `algorithm_task` VALUES ('99', '电动车头盔', 'MI9hHBan', 'Ha06qSwC', '电动车头盔', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_MI9hHBan.live.flv', 'dec_MI9hHBan.live.flv', 'dec_MI9hHBan?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721609558651');
INSERT INTO `algorithm_task` VALUES ('100', '烟雾检测', 'gj1Dv5uB', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_gj1Dv5uB.live.flv', 'dec_gj1Dv5uB.live.flv', 'dec_gj1Dv5uB?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721610481447');
INSERT INTO `algorithm_task` VALUES ('101', '电动车头盔', 'UrhOFUZI', 'Ha06qSwC', '电动车头盔', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_UrhOFUZI.live.flv', 'dec_UrhOFUZI.live.flv', 'dec_UrhOFUZI?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721610481449');
INSERT INTO `algorithm_task` VALUES ('102', '人车检测', '67oPPlRx', 'sj9hds2b', '人车检测', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_67oPPlRx.live.flv', 'dec_67oPPlRx.live.flv', 'dec_67oPPlRx?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721610481452');
INSERT INTO `algorithm_task` VALUES ('103', '烟雾检测', 'pc0kyieT', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_pc0kyieT.live.flv', 'dec_pc0kyieT.live.flv', 'dec_pc0kyieT?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721610631655');
INSERT INTO `algorithm_task` VALUES ('104', '电动车头盔', 'zXJiSX4z', 'Ha06qSwC', '电动车头盔', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_zXJiSX4z.live.flv', 'dec_zXJiSX4z.live.flv', 'dec_zXJiSX4z?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721610631652');
INSERT INTO `algorithm_task` VALUES ('105', '人车检测', '46flW4bs', 'sj9hds2b', '人车检测', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_46flW4bs.live.flv', 'dec_46flW4bs.live.flv', 'dec_46flW4bs?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721610631657');
INSERT INTO `algorithm_task` VALUES ('106', '人车检测', 'SAec6Nwk', 'sj9hds2b', '人车检测', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_SAec6Nwk.live.flv', 'dec_SAec6Nwk.live.flv', 'dec_SAec6Nwk?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721610656910');
INSERT INTO `algorithm_task` VALUES ('107', '烟雾检测', 'CCrMvsRN', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_CCrMvsRN.live.flv', 'dec_CCrMvsRN.live.flv', 'dec_CCrMvsRN?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721610657225');
INSERT INTO `algorithm_task` VALUES ('108', '电动车头盔', '0H5GaxJl', 'Ha06qSwC', '电动车头盔', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_0H5GaxJl.live.flv', 'dec_0H5GaxJl.live.flv', 'dec_0H5GaxJl?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721610657230');
INSERT INTO `algorithm_task` VALUES ('109', '电动车头盔', 'ZJ7UgXra', 'Ha06qSwC', '电动车头盔', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_ZJ7UgXra.live.flv', 'dec_ZJ7UgXra.live.flv', 'dec_ZJ7UgXra?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721610734296');
INSERT INTO `algorithm_task` VALUES ('110', '人车检测', 'eEOKnOi4', 'sj9hds2b', '人车检测', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_eEOKnOi4.live.flv', 'dec_eEOKnOi4.live.flv', 'dec_eEOKnOi4?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721610734293');
INSERT INTO `algorithm_task` VALUES ('111', '烟雾检测', 'eiyzIEyx', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_eiyzIEyx.live.flv', 'dec_eiyzIEyx.live.flv', 'dec_eiyzIEyx?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721610734298');
INSERT INTO `algorithm_task` VALUES ('112', '车牌识别', 'tmRruqqp', 'JXWMzmLb', '车牌识别', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_tmRruqqp.live.flv', 'dec_tmRruqqp.live.flv', 'dec_tmRruqqp?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721611147359');
INSERT INTO `algorithm_task` VALUES ('113', '电动车头盔', 'ukDDaIRj', 'Ha06qSwC', '电动车头盔', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_ukDDaIRj.live.flv', 'dec_ukDDaIRj.live.flv', 'dec_ukDDaIRj?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721611147363');
INSERT INTO `algorithm_task` VALUES ('114', '电动车头盔', 'OjyGI8gv', 'Ha06qSwC', '电动车头盔', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_OjyGI8gv.live.flv', 'dec_OjyGI8gv.live.flv', 'dec_OjyGI8gv?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721611216899');
INSERT INTO `algorithm_task` VALUES ('115', '车牌识别', 'QM8H3BgY', 'JXWMzmLb', '车牌识别', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_QM8H3BgY.live.flv', 'dec_QM8H3BgY.live.flv', 'dec_QM8H3BgY?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721611216899');
INSERT INTO `algorithm_task` VALUES ('116', '电动车头盔', 'HAguXgtS', 'Ha06qSwC', '电动车头盔', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_HAguXgtS.live.flv', 'dec_HAguXgtS.live.flv', 'dec_HAguXgtS?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721611640147');
INSERT INTO `algorithm_task` VALUES ('117', '车牌识别', 'eHV0qtis', 'JXWMzmLb', '车牌识别', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_eHV0qtis.live.flv', 'dec_eHV0qtis.live.flv', 'dec_eHV0qtis?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721611640150');
INSERT INTO `algorithm_task` VALUES ('118', '电动车头盔', 'XdGGIjmP', 'Ha06qSwC', '电动车头盔', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_XdGGIjmP.live.flv', 'dec_XdGGIjmP.live.flv', 'dec_XdGGIjmP?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721611642695');
INSERT INTO `algorithm_task` VALUES ('119', '车牌识别', 'RkWIrqnG', 'JXWMzmLb', '车牌识别', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_RkWIrqnG.live.flv', 'dec_RkWIrqnG.live.flv', 'dec_RkWIrqnG?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721611642697');
INSERT INTO `algorithm_task` VALUES ('120', '车牌识别', 'Tv2WWC9x', 'JXWMzmLb', '车牌识别', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_Tv2WWC9x.live.flv', 'dec_Tv2WWC9x.live.flv', 'dec_Tv2WWC9x?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721716634368', '1721716634368', null, null, null, '0', '1721611653129');
INSERT INTO `algorithm_task` VALUES ('121', '电动车头盔', 'uMp1dBgI', 'Ha06qSwC', '电动车头盔', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_uMp1dBgI.live.flv', 'dec_uMp1dBgI.live.flv', 'dec_uMp1dBgI?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721611653131');
INSERT INTO `algorithm_task` VALUES ('122', '人车检测', '68o5s1gJ', 'sj9hds2b', '人车检测', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_68o5s1gJ.live.flv', 'dec_68o5s1gJ.live.flv', 'dec_68o5s1gJ?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721612051506');
INSERT INTO `algorithm_task` VALUES ('123', '人车检测', '6ltDNsmQ', 'sj9hds2b', '人车检测', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_6ltDNsmQ.live.flv', 'dec_6ltDNsmQ.live.flv', 'dec_6ltDNsmQ?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721613161690');
INSERT INTO `algorithm_task` VALUES ('124', '人车检测', 'JqRJZSl7', 'sj9hds2b', '人车检测', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_JqRJZSl7.live.flv', 'dec_JqRJZSl7.live.flv', 'dec_JqRJZSl7?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721635014296');
INSERT INTO `algorithm_task` VALUES ('125', '人车检测', 'IYZGlWP8', 'sj9hds2b', '人车检测', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_IYZGlWP8.live.flv', 'dec_IYZGlWP8.live.flv', 'dec_IYZGlWP8?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721635034162');
INSERT INTO `algorithm_task` VALUES ('126', '人车检测', 'u0LmXfle', 'sj9hds2b', '人车检测', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_u0LmXfle.live.flv', 'dec_u0LmXfle.live.flv', 'dec_u0LmXfle?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721635206628');
INSERT INTO `algorithm_task` VALUES ('127', '人车检测', 'FWA0a1pa', 'sj9hds2b', '人车检测', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_FWA0a1pa.live.flv', 'dec_FWA0a1pa.live.flv', 'dec_FWA0a1pa?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721635334550');
INSERT INTO `algorithm_task` VALUES ('128', '人车检测', 'bIO1WGr3', 'sj9hds2b', '人车检测', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_bIO1WGr3.live.flv', 'dec_bIO1WGr3.live.flv', 'dec_bIO1WGr3?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721635530139');
INSERT INTO `algorithm_task` VALUES ('129', '电动车头盔', 'AU7JCs6d', 'Ha06qSwC', '电动车头盔', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_AU7JCs6d.live.flv', 'dec_AU7JCs6d.live.flv', 'dec_AU7JCs6d?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721701645946');
INSERT INTO `algorithm_task` VALUES ('130', '人车检测', 'Q086UEkJ', 'sj9hds2b', '人车检测', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_Q086UEkJ.live.flv', 'dec_Q086UEkJ.live.flv', 'dec_Q086UEkJ?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721724210228', '1721724210228', null, null, null, '0', '1721703420441');
INSERT INTO `algorithm_task` VALUES ('131', '电动车头盔', 'DSFwnTCr', 'Ha06qSwC', '电动车头盔', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_DSFwnTCr.live.flv', 'dec_DSFwnTCr.live.flv', 'dec_DSFwnTCr?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721703649573');
INSERT INTO `algorithm_task` VALUES ('132', '烟雾检测', 'MFS35WjI', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_MFS35WjI.live.flv', 'dec_MFS35WjI.live.flv', 'dec_MFS35WjI?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721724210470', '1721724210470', null, null, null, '0', '1721705546051');
INSERT INTO `algorithm_task` VALUES ('133', '人车检测', 'lNusnVTb', 'sj9hds2b', '人车检测', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_lNusnVTb.live.flv', 'dec_lNusnVTb.live.flv', 'dec_lNusnVTb?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721706322615');
INSERT INTO `algorithm_task` VALUES ('134', '电动车头盔', 'rqI680jG', 'Ha06qSwC', '电动车头盔', 'hymv8g1b', '金石科技', null, 'rtmp://221.237.108.76:19350/rlive/stream_387?sign=qeckRtfD', 'ori_rqI680jG.live.flv', 'dec_rqI680jG.live.flv', 'dec_rqI680jG?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1721706322613');
INSERT INTO `algorithm_task` VALUES ('135', '裸土识别', '3XtTD7OU', 'P4uGD0Ab', '裸土识别', 'bnck6b2d', '测试客户2', '', '/data/app/yolo/data/bare_soil/dtjd_1.mp4', 'ori_3XtTD7OU.live.flv', 'dec_3XtTD7OU.live.flv', 'dec_3XtTD7OU?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721722562226', '1721978334245', null, null, null, '0', '1721722543349');
INSERT INTO `algorithm_task` VALUES ('136', '裸土识别', 'logNh6Dp', 'P4uGD0Ab', '裸土识别', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_logNh6Dp.live.flv', 'dec_logNh6Dp.live.flv', 'dec_logNh6Dp?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721724437858', '1721977818582', null, null, null, '0', '1721724436583');
INSERT INTO `algorithm_task` VALUES ('137', '易涉毒場所巡查-桔丰', 'N0ZqUU5f', 'sj9hds2b', '人车检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_N0ZqUU5f.live.flv', 'dec_N0ZqUU5f.live.flv', 'dec_N0ZqUU5f?sign=41db35390ddad33f83944f44b8b75ded', '1', '30', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721727651169', '1722295610229', null, null, null, '0', '1721727084898');
INSERT INTO `algorithm_task` VALUES ('138', '易涉毒場所巡查-桔丰', 'yIq10jbj', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_yIq10jbj.live.flv', 'dec_yIq10jbj.live.flv', 'dec_yIq10jbj?sign=41db35390ddad33f83944f44b8b75ded', '1', '30', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721812628961', '1722302941785', null, null, null, '0', '1721727212902');
INSERT INTO `algorithm_task` VALUES ('139', '河道漂浮物', 'iFxybcTH', 'fM6NO7M4', '河道漂浮物', 'bnck6b2d', '测试客户2', '', 'http://scjskj.tpddns.cn:19001/dj-cloud-bucket/wayline/5ed5a7f1-ae35-43be-a825-daace5235848/DJI_202403061047_006_5ed5a7f1-ae35-43be-a825-daace5235848/DJI_20240306105426_0002_Z.mp4', 'ori_iFxybcTH.live.flv', 'dec_iFxybcTH.live.flv', 'dec_iFxybcTH?sign=41db35390ddad33f83944f44b8b75ded', '1', '5', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721729021429', '1721811744036', null, null, null, '0', '1721729013153');
INSERT INTO `algorithm_task` VALUES ('140', '河道漂浮物', 'eWjNLA3Q', 'fM6NO7M4', '河道漂浮物', 'hymv8g1b', '金石科技', null, 'http://scjskj.tpddns.cn:19001/dj-cloud-bucket/wayline/5ed5a7f1-ae35-43be-a825-daace5235848/DJI_202403061047_006_5ed5a7f1-ae35-43be-a825-daace5235848/DJI_20240306105426_0002_Z.mp4', 'ori_eWjNLA3Q.live.flv', 'dec_eWjNLA3Q.live.flv', 'dec_eWjNLA3Q?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721785469097', '1721785469097', null, null, null, '0', '1721785467731');
INSERT INTO `algorithm_task` VALUES ('141', '河道漂浮物', 'dMwjA383', 'fM6NO7M4', '河道漂浮物', 'hymv8g1b', '金石科技', null, 'http://scjskj.tpddns.cn:19001/dj-cloud-bucket/wayline/5ed5a7f1-ae35-43be-a825-daace5235848/DJI_202403061047_006_5ed5a7f1-ae35-43be-a825-daace5235848/DJI_20240306105426_0002_Z.mp4', 'ori_dMwjA383.live.flv', 'dec_dMwjA383.live.flv', 'dec_dMwjA383?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721785569104', '1721785569104', null, null, null, '0', '1721785566422');
INSERT INTO `algorithm_task` VALUES ('142', '河道漂浮物', 'cZLWaNe8', 'fM6NO7M4', '河道漂浮物', 'hymv8g1b', '金石科技', null, 'http://scjskj.tpddns.cn:19001/dj-cloud-bucket/wayline/5ed5a7f1-ae35-43be-a825-daace5235848/DJI_202403061047_006_5ed5a7f1-ae35-43be-a825-daace5235848/DJI_20240306105426_0002_Z.mp4', 'ori_cZLWaNe8.live.flv', 'dec_cZLWaNe8.live.flv', 'dec_cZLWaNe8?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721785745767', '1721785745767', null, null, null, '0', '1721785744420');
INSERT INTO `algorithm_task` VALUES ('143', '河道漂浮物', 'LGcgp8VN', 'fM6NO7M4', '河道漂浮物', 'hymv8g1b', '金石科技', null, 'http://scjskj.tpddns.cn:19001/dj-cloud-bucket/wayline/5ed5a7f1-ae35-43be-a825-daace5235848/DJI_202403061047_006_5ed5a7f1-ae35-43be-a825-daace5235848/DJI_20240306105426_0002_Z.mp4', 'ori_LGcgp8VN.live.flv', 'dec_LGcgp8VN.live.flv', 'dec_LGcgp8VN?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721785987900', '1721808582989', null, null, null, '0', '1721785986583');
INSERT INTO `algorithm_task` VALUES ('145', '烟雾检测', 'EmpDASd0', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_EmpDASd0.live.flv', 'dec_EmpDASd0.live.flv', 'dec_EmpDASd0?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721802713174', '1721802713174', null, null, null, '0', '1721802711785');
INSERT INTO `algorithm_task` VALUES ('146', '大同裸土识别', 'LaUPqzzj', 'P4uGD0Ab', '裸土识别', 'bnck6b2d', '测试客户2', '', '/data/app/yolo/data/bare_soil/dtjd_1.mp4', 'ori_LaUPqzzj.live.flv', 'dec_LaUPqzzj.live.flv', 'dec_LaUPqzzj?sign=41db35390ddad33f83944f44b8b75ded', '1', '10', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1721981054529', '1722326683139', null, null, null, '0', '1721981044883');
INSERT INTO `algorithm_task` VALUES ('148', '人车检测', 'RUvOnsAK', 'sj9hds2b', '人车检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_RUvOnsAK.live.flv', 'dec_RUvOnsAK.live.flv', 'dec_RUvOnsAK?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1722293875628', '1722293875628', null, null, null, '0', '1722293874169');
INSERT INTO `algorithm_task` VALUES ('149', '烟雾检测', 'rYTq6MRC', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_rYTq6MRC.live.flv', 'dec_rYTq6MRC.live.flv', 'dec_rYTq6MRC?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1722297094720', '1722297094720', null, null, null, '0', '1722297091680');
INSERT INTO `algorithm_task` VALUES ('150', '人车检测', 'nhumRN0u', 'sj9hds2b', '人车检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_nhumRN0u.live.flv', 'dec_nhumRN0u.live.flv', 'dec_nhumRN0u?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1722297303312', '1722297303312', null, null, null, '0', '1722297301654');
INSERT INTO `algorithm_task` VALUES ('151', '烟雾检测', 'bEFUqkGd', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_bEFUqkGd.live.flv', 'dec_bEFUqkGd.live.flv', 'dec_bEFUqkGd?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1722297372473', '1722297372473', null, null, null, '0', '1722297370874');
INSERT INTO `algorithm_task` VALUES ('152', '烟雾检测', 'DJT5vQE3', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', '', 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_DJT5vQE3.live.flv', 'dec_DJT5vQE3.live.flv', 'dec_DJT5vQE3?sign=41db35390ddad33f83944f44b8b75ded', '1', '10', '/data/app/yolo/', 'yolov8_detect_tensorrt', null, null, null, null, null, '0', '1722298015504');
INSERT INTO `algorithm_task` VALUES ('153', '人车检测', 'FlJZ2eoF', 'sj9hds2b', '人车检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_FlJZ2eoF.live.flv', 'dec_FlJZ2eoF.live.flv', 'dec_FlJZ2eoF?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1722300600809', '1722300600809', null, null, null, '0', '1722300599396');
INSERT INTO `algorithm_task` VALUES ('154', '烟雾检测', '4y18TnZI', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_4y18TnZI.live.flv', 'dec_4y18TnZI.live.flv', 'dec_4y18TnZI?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1722317177592', '1722317177592', null, null, null, '0', '1722317176335');
INSERT INTO `algorithm_task` VALUES ('155', '烟雾检测', '7XS6Oc5O', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_7XS6Oc5O.live.flv', 'dec_7XS6Oc5O.live.flv', 'dec_7XS6Oc5O?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1722320688012', '1722320688012', null, null, null, '0', '1722320686598');
INSERT INTO `algorithm_task` VALUES ('156', '烟雾检测', '5EKz7F2X', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_5EKz7F2X.live.flv', 'dec_5EKz7F2X.live.flv', 'dec_5EKz7F2X?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1722320954892', '1722320954892', null, null, null, '0', '1722320952048');
INSERT INTO `algorithm_task` VALUES ('157', '烟雾检测', '0hS0ODA4', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_0hS0ODA4.live.flv', 'dec_0hS0ODA4.live.flv', 'dec_0hS0ODA4?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1722320995072', '1722320995072', null, null, null, '0', '1722320993862');
INSERT INTO `algorithm_task` VALUES ('158', '烟雾检测', 'Ox7cryjD', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_Ox7cryjD.live.flv', 'dec_Ox7cryjD.live.flv', 'dec_Ox7cryjD?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1722321882301', '1722321882301', null, null, null, '0', '1722321881054');
INSERT INTO `algorithm_task` VALUES ('159', '烟雾检测', 'W0UXL6Fm', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_W0UXL6Fm.live.flv', 'dec_W0UXL6Fm.live.flv', 'dec_W0UXL6Fm?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1722321951310', '1722321951310', null, null, null, '0', '1722321949664');
INSERT INTO `algorithm_task` VALUES ('160', '烟雾检测', 'kZZJBUCd', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_kZZJBUCd.live.flv', 'dec_kZZJBUCd.live.flv', 'dec_kZZJBUCd?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1722322502892', '1722322502892', null, null, null, '0', '1722322501324');
INSERT INTO `algorithm_task` VALUES ('161', '烟雾检测', '6GPG5mcf', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_6GPG5mcf.live.flv', 'dec_6GPG5mcf.live.flv', 'dec_6GPG5mcf?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1722326648945', '1722326648945', null, null, null, '0', '1722326647260');
INSERT INTO `algorithm_task` VALUES ('162', '烟雾检测', 'VHWWGgbE', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_VHWWGgbE.live.flv', 'dec_VHWWGgbE.live.flv', 'dec_VHWWGgbE?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1722327695685', '1722327695685', null, null, null, '0', '1722327694128');
INSERT INTO `algorithm_task` VALUES ('163', '烟雾检测', 'f7xKCQBK', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_f7xKCQBK.live.flv', 'dec_f7xKCQBK.live.flv', 'dec_f7xKCQBK?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1722330229586', '1722330229586', null, null, null, '0', '1722330228224');
INSERT INTO `algorithm_task` VALUES ('164', '烟雾检测', 'RH9gtNkX', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_RH9gtNkX.live.flv', 'dec_RH9gtNkX.live.flv', 'dec_RH9gtNkX?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1722330578363', '1722330578363', null, null, null, '0', '1722330577167');
INSERT INTO `algorithm_task` VALUES ('165', '烟雾检测', '6zndNCOY', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_6zndNCOY.live.flv', 'dec_6zndNCOY.live.flv', 'dec_6zndNCOY?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1722331599317', '1722331599317', null, null, null, '0', '1722331597654');
INSERT INTO `algorithm_task` VALUES ('166', '烟雾检测', '7CoU6Zse', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_7CoU6Zse.live.flv', 'dec_7CoU6Zse.live.flv', 'dec_7CoU6Zse?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1722333371588', '1722333371588', null, null, null, '0', '1722333370151');
INSERT INTO `algorithm_task` VALUES ('167', '烟雾检测', '2U0EArQ0', 'dXgAaKGI', '烟雾检测', 'hymv8g1b', '金石科技', null, 'http://221.237.108.76:9685/flv/live/stream_387.flv', 'ori_2U0EArQ0.live.flv', 'dec_2U0EArQ0.live.flv', 'dec_2U0EArQ0?sign=41db35390ddad33f83944f44b8b75ded', '1', '60', '/data/app/yolo/', 'yolov8_detect_tensorrt', '1722334058388', '1722334058388', null, null, null, '0', '1722334056874');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `mobile_num` varchar(20) DEFAULT NULL,
  `customer_no` varchar(20) DEFAULT NULL,
  `access_key` varchar(20) DEFAULT NULL,
  `httpReq_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `httpReq_header` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `login_ip` varchar(20) DEFAULT NULL,
  `login_time` bigint DEFAULT NULL,
  `taskAmount_limit` int DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', '金石科技', '15208492745', 'hymv8g1b', 'kdjn5961', 'https://qbj.jszhcs.cn:9586/prod-api/accessAlarm/algorithmCenter/receiveHttpPushLog', '{\"zt\":\"123\"}', '', null, '10', '1', '1700474382435');
INSERT INTO `customer` VALUES ('2', '测试客户2', '13552011202', 'bnck6b2d', 'lmvt5j9v', 'http://192.168.2.6:8080/api/sys/httpPushLog/receiveHttpData', '{\"zt\":\"666\"}', null, null, '10', '1', '1700474382435');

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
) ENGINE=InnoDB AUTO_INCREMENT=2095 DEFAULT CHARSET=utf8mb3;

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
) ENGINE=InnoDB AUTO_INCREMENT=488 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of rocket_mq_fail_msg
-- ----------------------------
INSERT INTO `rocket_mq_fail_msg` VALUES ('233', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863104EB9C0000', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:26:45.076892.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.54}\", \"alarm_time\": \"2024-07-10 12:26:45\"}', '0', '222', '1444809', 'broker-a', '172.18.0.1', '2024-07-10 13:54:04', '1720590844560');
INSERT INTO `rocket_mq_fail_msg` VALUES ('234', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863112A978000F', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:41:45.769728.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.564}\", \"alarm_time\": \"2024-07-10 12:41:45\"}', '0', '223', '1446458', 'broker-a', '172.18.0.1', '2024-07-10 13:54:14', '1720590854471');
INSERT INTO `rocket_mq_fail_msg` VALUES ('235', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86310D2A240009', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:35:45.788487.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.565}\", \"alarm_time\": \"2024-07-10 12:35:45\"}', '0', '224', '1446980', 'broker-a', '172.18.0.1', '2024-07-10 13:54:14', '1720590854482');
INSERT INTO `rocket_mq_fail_msg` VALUES ('236', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86310B548C0007', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:33:45.244461.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.563}\", \"alarm_time\": \"2024-07-10 12:33:45\"}', '0', '227', '1448545', 'broker-a', '172.18.0.1', '2024-07-10 13:54:14', '1720590854488');
INSERT INTO `rocket_mq_fail_msg` VALUES ('237', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86310FEACC000C', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:38:46.086784.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.519}\", \"alarm_time\": \"2024-07-10 12:38:46\"}', '0', '233', '1451675', 'broker-a', '172.18.0.1', '2024-07-10 13:54:14', '1720590854490');
INSERT INTO `rocket_mq_fail_msg` VALUES ('238', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86311488C80011', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:43:48.718157.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.503}\", \"alarm_time\": \"2024-07-10 12:43:48\"}', '0', '225', '1447502', 'broker-a', '172.18.0.1', '2024-07-10 13:54:14', '1720590854487');
INSERT INTO `rocket_mq_fail_msg` VALUES ('239', '2650', 'InferenceResult', '3', 'AC11000307D016C66E8631165E580013', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:45:49.163311.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.507}\", \"alarm_time\": \"2024-07-10 12:45:49\"}', '0', '238', '1456509', 'broker-a', '172.18.0.1', '2024-07-10 13:54:14', '1720590854521');
INSERT INTO `rocket_mq_fail_msg` VALUES ('240', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86311397700010', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:42:45.795795.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.57}\", \"alarm_time\": \"2024-07-10 12:42:45\"}', '0', '228', '1449067', 'broker-a', '172.18.0.1', '2024-07-10 13:54:14', '1720590854488');
INSERT INTO `rocket_mq_fail_msg` VALUES ('241', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863111C140000E', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:40:46.212653.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.538}\", \"alarm_time\": \"2024-07-10 12:40:46\"}', '0', '235', '1452718', 'broker-a', '172.18.0.1', '2024-07-10 13:54:14', '1720590854491');
INSERT INTO `rocket_mq_fail_msg` VALUES ('242', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863105D60C0001', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:27:44.890844.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.61}\", \"alarm_time\": \"2024-07-10 12:27:44\"}', '0', '239', '1457031', 'broker-a', '172.18.0.1', '2024-07-10 13:54:14', '1720590854532');
INSERT INTO `rocket_mq_fail_msg` VALUES ('243', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86310C45800008', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:34:46.909168.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.502}\", \"alarm_time\": \"2024-07-10 12:34:46\"}', '0', '230', '1450110', 'broker-a', '172.18.0.1', '2024-07-10 13:54:14', '1720590854489');
INSERT INTO `rocket_mq_fail_msg` VALUES ('244', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863108D7640004', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:31:01.671148.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.538}\", \"alarm_time\": \"2024-07-10 12:31:01\"}', '0', '229', '1449588', 'broker-a', '172.18.0.1', '2024-07-10 13:54:14', '1720590854489');
INSERT INTO `rocket_mq_fail_msg` VALUES ('245', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86310F006C000B', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:37:45.964462.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.605}\", \"alarm_time\": \"2024-07-10 12:37:45\"}', '0', '231', '1450632', 'broker-a', '172.18.0.1', '2024-07-10 13:54:14', '1720590854490');
INSERT INTO `rocket_mq_fail_msg` VALUES ('246', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86310E1268000A', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:36:45.409796.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.82}\", \"alarm_time\": \"2024-07-10 12:36:45\"}', '0', '232', '1451154', 'broker-a', '172.18.0.1', '2024-07-10 13:54:14', '1720590854490');
INSERT INTO `rocket_mq_fail_msg` VALUES ('247', '2650', 'InferenceResult', '3', 'AC11000307D016C66E8631097DDC0005', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:31:45.166850.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.504}\", \"alarm_time\": \"2024-07-10 12:31:45\"}', '0', '241', '1458073', 'broker-a', '172.18.0.1', '2024-07-10 13:54:14', '1720590854532');
INSERT INTO `rocket_mq_fail_msg` VALUES ('248', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86310A6C550006', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:32:46.085442.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.63}\", \"alarm_time\": \"2024-07-10 12:32:46\"}', '0', '234', '1452197', 'broker-a', '172.18.0.1', '2024-07-10 13:54:14', '1720590854491');
INSERT INTO `rocket_mq_fail_msg` VALUES ('249', '2650', 'InferenceResult', '3', 'AC11000307D016C66E8631156D440012', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:44:47.302137.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.54}\", \"alarm_time\": \"2024-07-10 12:44:47\"}', '0', '226', '1448024', 'broker-a', '172.18.0.1', '2024-07-10 13:54:14', '1720590854488');
INSERT INTO `rocket_mq_fail_msg` VALUES ('250', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863110D2D5000D', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:39:45.651836.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.595}\", \"alarm_time\": \"2024-07-10 12:39:45\"}', '0', '236', '1453240', 'broker-a', '172.18.0.1', '2024-07-10 13:54:14', '1720590854491');
INSERT INTO `rocket_mq_fail_msg` VALUES ('251', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863106F6ED0002', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:28:59.403767.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.52}\", \"alarm_time\": \"2024-07-10 12:28:59\"}', '0', '240', '1457552', 'broker-a', '172.18.0.1', '2024-07-10 13:54:14', '1720590854531');
INSERT INTO `rocket_mq_fail_msg` VALUES ('252', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863107AB1C0003', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:29:45.636502.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.616}\", \"alarm_time\": \"2024-07-10 12:29:45\"}', '0', '237', '1455987', 'broker-a', '172.18.0.1', '2024-07-10 13:54:14', '1720590854520');
INSERT INTO `rocket_mq_fail_msg` VALUES ('253', '2650', 'InferenceResult', '3', 'AC11000307D016C66E8631173E6D0014', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:46:46.051053.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.704}\", \"alarm_time\": \"2024-07-10 12:46:46\"}', '0', '242', '1458595', 'broker-a', '172.18.0.1', '2024-07-10 13:54:14', '1720590854532');
INSERT INTO `rocket_mq_fail_msg` VALUES ('254', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86311922040016', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:48:50.079873.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.587}\", \"alarm_time\": \"2024-07-10 12:48:50\"}', '0', '243', '1459117', 'broker-a', '172.18.0.1', '2024-07-10 13:54:18', '1720590858390');
INSERT INTO `rocket_mq_fail_msg` VALUES ('255', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863119FED00017', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:49:46.656125.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.614}\", \"alarm_time\": \"2024-07-10 12:49:46\"}', '0', '245', '1460161', 'broker-a', '172.18.0.1', '2024-07-10 13:54:18', '1720590858548');
INSERT INTO `rocket_mq_fail_msg` VALUES ('256', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86311DA750001B', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:53:46.516719.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.51}\", \"alarm_time\": \"2024-07-10 12:53:46\"}', '0', '248', '1462776', 'broker-a', '172.18.0.1', '2024-07-10 13:54:18', '1720590858637');
INSERT INTO `rocket_mq_fail_msg` VALUES ('257', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86311F7BF4001D', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:55:46.594043.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.713}\", \"alarm_time\": \"2024-07-10 12:55:46\"}', '0', '244', '1459639', 'broker-a', '172.18.0.1', '2024-07-10 13:54:18', '1720590858531');
INSERT INTO `rocket_mq_fail_msg` VALUES ('258', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86311BE5C40019', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:51:49.211582.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.538}\", \"alarm_time\": \"2024-07-10 12:51:49\"}', '0', '253', '1465385', 'broker-a', '172.18.0.1', '2024-07-10 13:54:18', '1720590858711');
INSERT INTO `rocket_mq_fail_msg` VALUES ('259', '2650', 'InferenceResult', '3', 'AC11000307D016C66E8631223C8C0020', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:58:46.775145.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.728}\", \"alarm_time\": \"2024-07-10 12:58:46\"}', '0', '251', '1464341', 'broker-a', '172.18.0.1', '2024-07-10 13:54:18', '1720590858702');
INSERT INTO `rocket_mq_fail_msg` VALUES ('260', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86312412FC0022', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:00:46.886004.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.52}\", \"alarm_time\": \"2024-07-10 13:00:46\"}', '0', '254', '1465907', 'broker-a', '172.18.0.1', '2024-07-10 13:54:18', '1720590858771');
INSERT INTO `rocket_mq_fail_msg` VALUES ('261', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86312067FC001E', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:56:46.686983.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.786}\", \"alarm_time\": \"2024-07-10 12:56:46\"}', '0', '252', '1464863', 'broker-a', '172.18.0.1', '2024-07-10 13:54:18', '1720590858709');
INSERT INTO `rocket_mq_fail_msg` VALUES ('262', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86311B06320018', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:50:54.125140.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.632}\", \"alarm_time\": \"2024-07-10 12:50:54\"}', '0', '249', '1463297', 'broker-a', '172.18.0.1', '2024-07-10 13:54:18', '1720590858652');
INSERT INTO `rocket_mq_fail_msg` VALUES ('263', '2650', 'InferenceResult', '3', 'AC11000307D016C66E8631215204001F', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:57:46.735138.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.517}\", \"alarm_time\": \"2024-07-10 12:57:46\"}', '0', '247', '1462254', 'broker-a', '172.18.0.1', '2024-07-10 13:54:18', '1720590858629');
INSERT INTO `rocket_mq_fail_msg` VALUES ('264', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863124FE940023', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:01:47.519708.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.534}\", \"alarm_time\": \"2024-07-10 13:01:47\"}', '0', '259', '1468515', 'broker-a', '172.18.0.1', '2024-07-10 13:54:18', '1720590858869');
INSERT INTO `rocket_mq_fail_msg` VALUES ('265', '2650', 'InferenceResult', '3', 'AC11000307D016C66E8631183AFC0015', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:47:49.252465.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.514}\", \"alarm_time\": \"2024-07-10 12:47:49\"}', '0', '250', '1463819', 'broker-a', '172.18.0.1', '2024-07-10 13:54:18', '1720590858654');
INSERT INTO `rocket_mq_fail_msg` VALUES ('266', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86312629F60024', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:03:04.256348.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.527}\", \"alarm_time\": \"2024-07-10 13:03:04\"}', '0', '256', '1466949', 'broker-a', '172.18.0.1', '2024-07-10 13:54:18', '1720590858806');
INSERT INTO `rocket_mq_fail_msg` VALUES ('267', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863127D8130026', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:04:54.435501.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.51}\", \"alarm_time\": \"2024-07-10 13:04:54\"}', '0', '255', '1466428', 'broker-a', '172.18.0.1', '2024-07-10 13:54:18', '1720590858793');
INSERT INTO `rocket_mq_fail_msg` VALUES ('268', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86311E9479001C', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:54:47.253873.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.583}\", \"alarm_time\": \"2024-07-10 12:54:47\"}', '0', '246', '1461732', 'broker-a', '172.18.0.1', '2024-07-10 13:54:18', '1720590858609');
INSERT INTO `rocket_mq_fail_msg` VALUES ('269', '2650', 'InferenceResult', '3', 'AC11000307D016C66E8631233EAC0021', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:59:52.605847.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.522}\", \"alarm_time\": \"2024-07-10 12:59:52\"}', '0', '257', '1467471', 'broker-a', '172.18.0.1', '2024-07-10 13:54:18', '1720590858811');
INSERT INTO `rocket_mq_fail_msg` VALUES ('270', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86311CC65A001A', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 12:52:48.865931.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.514}\", \"alarm_time\": \"2024-07-10 12:52:48\"}', '0', '261', '1469558', 'broker-a', '172.18.0.1', '2024-07-10 13:54:18', '1720590858941');
INSERT INTO `rocket_mq_fail_msg` VALUES ('271', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863126E4400025', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:03:51.803592.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.548}\", \"alarm_time\": \"2024-07-10 13:03:51\"}', '0', '258', '1467993', 'broker-a', '172.18.0.1', '2024-07-10 13:54:18', '1720590858860');
INSERT INTO `rocket_mq_fail_msg` VALUES ('272', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86312994F80028', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:06:48.056185.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.51}\", \"alarm_time\": \"2024-07-10 13:06:48\"}', '0', '262', '1470080', 'broker-a', '172.18.0.1', '2024-07-10 13:54:18', '1720590858962');
INSERT INTO `rocket_mq_fail_msg` VALUES ('273', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863128B4340027', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:05:50.354625.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.54}\", \"alarm_time\": \"2024-07-10 13:05:50\"}', '0', '260', '1469037', 'broker-a', '172.18.0.1', '2024-07-10 13:54:18', '1720590858919');
INSERT INTO `rocket_mq_fail_msg` VALUES ('274', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86312B68D4002A', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:08:48.128465.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.616}\", \"alarm_time\": \"2024-07-10 13:08:48\"}', '0', '263', '1470601', 'broker-a', '172.18.0.1', '2024-07-10 13:54:20', '1720590860935');
INSERT INTO `rocket_mq_fail_msg` VALUES ('275', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86312C94C5002B', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:10:04.250836.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.538}\", \"alarm_time\": \"2024-07-10 13:10:04\"}', '0', '264', '1471123', 'broker-a', '172.18.0.1', '2024-07-10 13:54:21', '1720590861026');
INSERT INTO `rocket_mq_fail_msg` VALUES ('276', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86312AAC080029', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:07:59.469155.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.52}\", \"alarm_time\": \"2024-07-10 13:07:59\"}', '0', '265', '1471645', 'broker-a', '172.18.0.1', '2024-07-10 13:54:21', '1720590861035');
INSERT INTO `rocket_mq_fail_msg` VALUES ('277', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86312F15EC002E', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:12:48.823455.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.527}\", \"alarm_time\": \"2024-07-10 13:12:48\"}', '0', '270', '1474253', 'broker-a', '172.18.0.1', '2024-07-10 13:54:21', '1720590861163');
INSERT INTO `rocket_mq_fail_msg` VALUES ('278', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863136668C0036', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:20:48.376161.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.636}\", \"alarm_time\": \"2024-07-10 13:20:48\"}', '0', '275', '1476863', 'broker-a', '172.18.0.1', '2024-07-10 13:54:21', '1720590861311');
INSERT INTO `rocket_mq_fail_msg` VALUES ('279', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86312D3CD4002C', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:10:47.865720.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.515}\", \"alarm_time\": \"2024-07-10 13:10:47\"}', '0', '266', '1472166', 'broker-a', '172.18.0.1', '2024-07-10 13:54:21', '1720590861040');
INSERT INTO `rocket_mq_fail_msg` VALUES ('280', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863130F1C60030', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:14:50.642893.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.565}\", \"alarm_time\": \"2024-07-10 13:14:50\"}', '0', '269', '1473731', 'broker-a', '172.18.0.1', '2024-07-10 13:54:21', '1720590861157');
INSERT INTO `rocket_mq_fail_msg` VALUES ('281', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86313BE7A4003C', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:26:48.655865.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.514}\", \"alarm_time\": \"2024-07-10 13:26:48\"}', '0', '273', '1475819', 'broker-a', '172.18.0.1', '2024-07-10 13:54:21', '1720590861256');
INSERT INTO `rocket_mq_fail_msg` VALUES ('282', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86312FFC4D002F', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:13:47.704464.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.553}\", \"alarm_time\": \"2024-07-10 13:13:47\"}', '0', '268', '1473209', 'broker-a', '172.18.0.1', '2024-07-10 13:54:21', '1720590861153');
INSERT INTO `rocket_mq_fail_msg` VALUES ('283', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863137562C0037', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:21:49.688408.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.517}\", \"alarm_time\": \"2024-07-10 13:21:49\"}', '0', '271', '1474775', 'broker-a', '172.18.0.1', '2024-07-10 13:54:21', '1720590861208');
INSERT INTO `rocket_mq_fail_msg` VALUES ('284', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86312E36F0002D', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:11:51.810562.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.63}\", \"alarm_time\": \"2024-07-10 13:11:51\"}', '0', '267', '1472688', 'broker-a', '172.18.0.1', '2024-07-10 13:54:21', '1720590861117');
INSERT INTO `rocket_mq_fail_msg` VALUES ('285', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86314081280041', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:31:50.330089.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.514}\", \"alarm_time\": \"2024-07-10 13:31:50\"}', '0', '278', '1478429', 'broker-a', '172.18.0.1', '2024-07-10 13:54:21', '1720590861337');
INSERT INTO `rocket_mq_fail_msg` VALUES ('286', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86313492DC0034', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:18:48.517721.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.598}\", \"alarm_time\": \"2024-07-10 13:18:48\"}', '0', '274', '1476341', 'broker-a', '172.18.0.1', '2024-07-10 13:54:21', '1720590861256');
INSERT INTO `rocket_mq_fail_msg` VALUES ('287', '2650', 'InferenceResult', '3', 'AC11000307D016C66E8631357DDC0035', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:19:48.553682.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.507}\", \"alarm_time\": \"2024-07-10 13:19:48\"}', '0', '272', '1475297', 'broker-a', '172.18.0.1', '2024-07-10 13:54:21', '1720590861255');
INSERT INTO `rocket_mq_fail_msg` VALUES ('288', '2650', 'InferenceResult', '3', 'AC11000307D016C66E8631383E710038', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:22:49.087962.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.503}\", \"alarm_time\": \"2024-07-10 13:22:49\"}', '0', '280', '1479473', 'broker-a', '172.18.0.1', '2024-07-10 13:54:21', '1720590861389');
INSERT INTO `rocket_mq_fail_msg` VALUES ('289', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863131D2C00031', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:15:48.160257.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.797}\", \"alarm_time\": \"2024-07-10 13:15:48\"}', '0', '279', '1478951', 'broker-a', '172.18.0.1', '2024-07-10 13:54:21', '1720590861360');
INSERT INTO `rocket_mq_fail_msg` VALUES ('290', '2650', 'InferenceResult', '3', 'AC11000307D016C66E8631394AA40039', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:23:57.841262.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.528}\", \"alarm_time\": \"2024-07-10 13:23:57\"}', '0', '277', '1477907', 'broker-a', '172.18.0.1', '2024-07-10 13:54:21', '1720590861337');
INSERT INTO `rocket_mq_fail_msg` VALUES ('291', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86314169640042', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:32:49.194154.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.521}\", \"alarm_time\": \"2024-07-10 13:32:49\"}', '0', '276', '1477385', 'broker-a', '172.18.0.1', '2024-07-10 13:54:21', '1720590861327');
INSERT INTO `rocket_mq_fail_msg` VALUES ('292', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863142517C0043', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:33:49.079463.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.59}\", \"alarm_time\": \"2024-07-10 13:33:49\"}', '0', '281', '1479995', 'broker-a', '172.18.0.1', '2024-07-10 13:54:21', '1720590861470');
INSERT INTO `rocket_mq_fail_msg` VALUES ('293', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86313CD13C003D', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:27:48.790733.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.587}\", \"alarm_time\": \"2024-07-10 13:27:48\"}', '0', '282', '1480516', 'broker-a', '172.18.0.1', '2024-07-10 13:54:21', '1720590861626');
INSERT INTO `rocket_mq_fail_msg` VALUES ('294', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86313EBFF0003F', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:29:55.424465.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.632}\", \"alarm_time\": \"2024-07-10 13:29:55\"}', '0', '283', '1481038', 'broker-a', '172.18.0.1', '2024-07-10 13:54:23', '1720590863394');
INSERT INTO `rocket_mq_fail_msg` VALUES ('295', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86314427E40045', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:35:49.107636.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.777}\", \"alarm_time\": \"2024-07-10 13:35:49\"}', '0', '284', '1481560', 'broker-a', '172.18.0.1', '2024-07-10 13:54:23', '1720590863468');
INSERT INTO `rocket_mq_fail_msg` VALUES ('296', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86313AFBC6003B', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:25:48.470845.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.735}\", \"alarm_time\": \"2024-07-10 13:25:48\"}', '0', '285', '1482082', 'broker-a', '172.18.0.1', '2024-07-10 13:54:23', '1720590863527');
INSERT INTO `rocket_mq_fail_msg` VALUES ('297', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863146EB340048', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:38:50.642394.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.577}\", \"alarm_time\": \"2024-07-10 13:38:50\"}', '0', '287', '1483126', 'broker-a', '172.18.0.1', '2024-07-10 13:54:23', '1720590863645');
INSERT INTO `rocket_mq_fail_msg` VALUES ('298', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86314607680047', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:37:52.509854.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.616}\", \"alarm_time\": \"2024-07-10 13:37:52\"}', '0', '286', '1482604', 'broker-a', '172.18.0.1', '2024-07-10 13:54:23', '1720590863604');
INSERT INTO `rocket_mq_fail_msg` VALUES ('299', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86314512380046', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:36:49.573870.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.506}\", \"alarm_time\": \"2024-07-10 13:36:49\"}', '0', '288', '1483648', 'broker-a', '172.18.0.1', '2024-07-10 13:54:23', '1720590863652');
INSERT INTO `rocket_mq_fail_msg` VALUES ('300', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863147D2F40049', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:39:49.725276.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.57}\", \"alarm_time\": \"2024-07-10 13:39:49\"}', '0', '289', '1484170', 'broker-a', '172.18.0.1', '2024-07-10 13:54:23', '1720590863670');
INSERT INTO `rocket_mq_fail_msg` VALUES ('301', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86314C68F8004E', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:44:49.652650.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.554}\", \"alarm_time\": \"2024-07-10 13:44:49\"}', '0', '294', '1486778', 'broker-a', '172.18.0.1', '2024-07-10 13:54:23', '1720590863773');
INSERT INTO `rocket_mq_fail_msg` VALUES ('302', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863149B3FC004B', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:41:52.985200.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.527}\", \"alarm_time\": \"2024-07-10 13:41:52\"}', '0', '293', '1486256', 'broker-a', '172.18.0.1', '2024-07-10 13:54:23', '1720590863767');
INSERT INTO `rocket_mq_fail_msg` VALUES ('303', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86314B95F4004D', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:43:56.658810.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.565}\", \"alarm_time\": \"2024-07-10 13:43:56\"}', '0', '292', '1485734', 'broker-a', '172.18.0.1', '2024-07-10 13:54:23', '1720590863766');
INSERT INTO `rocket_mq_fail_msg` VALUES ('304', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863148D134004A', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:40:55.190562.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.63}\", \"alarm_time\": \"2024-07-10 13:40:55\"}', '0', '291', '1485213', 'broker-a', '172.18.0.1', '2024-07-10 13:54:23', '1720590863728');
INSERT INTO `rocket_mq_fail_msg` VALUES ('305', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86314A9264004C', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:42:50.221516.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.501}\", \"alarm_time\": \"2024-07-10 13:42:50\"}', '0', '290', '1484691', 'broker-a', '172.18.0.1', '2024-07-10 13:54:23', '1720590863692');
INSERT INTO `rocket_mq_fail_msg` VALUES ('306', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86314F27100051', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:47:49.845218.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.581}\", \"alarm_time\": \"2024-07-10 13:47:49\"}', '0', '298', '1488865', 'broker-a', '172.18.0.1', '2024-07-10 13:54:23', '1720590863900');
INSERT INTO `rocket_mq_fail_msg` VALUES ('307', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86314E43600050', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:46:52.057621.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.548}\", \"alarm_time\": \"2024-07-10 13:46:52\"}', '0', '295', '1487300', 'broker-a', '172.18.0.1', '2024-07-10 13:54:23', '1720590863810');
INSERT INTO `rocket_mq_fail_msg` VALUES ('308', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86315011440052', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:48:50.086369.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.538}\", \"alarm_time\": \"2024-07-10 13:48:50\"}', '0', '296', '1487822', 'broker-a', '172.18.0.1', '2024-07-10 13:54:23', '1720590863819');
INSERT INTO `rocket_mq_fail_msg` VALUES ('309', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863150FA280053', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:49:50.069284.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.554}\", \"alarm_time\": \"2024-07-10 13:49:50\"}', '0', '300', '1489909', 'broker-a', '172.18.0.1', '2024-07-10 13:54:23', '1720590863921');
INSERT INTO `rocket_mq_fail_msg` VALUES ('310', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863152CF340055', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:51:50.069138.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.56}\", \"alarm_time\": \"2024-07-10 13:51:50\"}', '0', '297', '1488344', 'broker-a', '172.18.0.1', '2024-07-10 13:54:23', '1720590863900');
INSERT INTO `rocket_mq_fail_msg` VALUES ('311', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863151E6680054', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:50:50.381244.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.517}\", \"alarm_time\": \"2024-07-10 13:50:50\"}', '0', '299', '1489387', 'broker-a', '172.18.0.1', '2024-07-10 13:54:23', '1720590863901');
INSERT INTO `rocket_mq_fail_msg` VALUES ('312', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863153DBA00056', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:52:58.696038.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.528}\", \"alarm_time\": \"2024-07-10 13:52:58\"}', '0', '301', '1490431', 'broker-a', '172.18.0.1', '2024-07-10 13:54:24', '1720590864008');
INSERT INTO `rocket_mq_fail_msg` VALUES ('313', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86313A1A24003A', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:24:50.348470.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.507}\", \"alarm_time\": \"2024-07-10 13:24:50\"}', '0', '302', '1490953', 'broker-a', '172.18.0.1', '2024-07-10 13:54:24', '1720590864197');
INSERT INTO `rocket_mq_fail_msg` VALUES ('314', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86313F92220040', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:30:49.330447.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.538}\", \"alarm_time\": \"2024-07-10 13:30:49\"}', '0', '303', '1491475', 'broker-a', '172.18.0.1', '2024-07-10 13:54:27', '1720590867107');
INSERT INTO `rocket_mq_fail_msg` VALUES ('315', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863132C4C00032', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:16:48.812713.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.543}\", \"alarm_time\": \"2024-07-10 13:16:48\"}', '0', '304', '1491997', 'broker-a', '172.18.0.1', '2024-07-10 13:54:27', '1720590867313');
INSERT INTO `rocket_mq_fail_msg` VALUES ('316', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863133B1500033', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:17:50.412463.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.548}\", \"alarm_time\": \"2024-07-10 13:17:50\"}', '0', '306', '1493041', 'broker-a', '172.18.0.1', '2024-07-10 13:54:27', '1720590867435');
INSERT INTO `rocket_mq_fail_msg` VALUES ('317', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86314D4F8B004F', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:45:49.712463.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.634}\", \"alarm_time\": \"2024-07-10 13:45:49\"}', '0', '307', '1493563', 'broker-a', '172.18.0.1', '2024-07-10 13:54:27', '1720590867451');
INSERT INTO `rocket_mq_fail_msg` VALUES ('318', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86313DBD24003E', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:28:49.278482.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.642}\", \"alarm_time\": \"2024-07-10 13:28:49\"}', '0', '308', '1494085', 'broker-a', '172.18.0.1', '2024-07-10 13:54:27', '1720590867492');
INSERT INTO `rocket_mq_fail_msg` VALUES ('319', '2650', 'InferenceResult', '3', 'AC11000307D016C66E8631433FF80044', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:34:50.349320.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.532}\", \"alarm_time\": \"2024-07-10 13:34:50\"}', '0', '305', '1492519', 'broker-a', '172.18.0.1', '2024-07-10 13:54:27', '1720590867416');
INSERT INTO `rocket_mq_fail_msg` VALUES ('320', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863154A5610057', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:53:50.313632.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.507}\", \"alarm_time\": \"2024-07-10 13:53:50\"}', '0', '310', '1570451', 'broker-a', '172.18.0.1', '2024-07-10 13:55:01', '1720590901756');
INSERT INTO `rocket_mq_fail_msg` VALUES ('321', '2650', 'InferenceResult', '3', 'AC11000307D016C66E8631558FD00058', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:54:50.298071.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.725}\", \"alarm_time\": \"2024-07-10 13:54:50\"}', '0', '316', '1581862', 'broker-a', '172.18.0.1', '2024-07-10 13:56:07', '1720590967752');
INSERT INTO `rocket_mq_fail_msg` VALUES ('322', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86315687EA0059', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:55:54.041416.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.539}\", \"alarm_time\": \"2024-07-10 13:55:54\"}', '0', '415', '1717816', 'broker-a', '172.18.0.1', '2024-07-10 13:57:07', '1720591027479');
INSERT INTO `rocket_mq_fail_msg` VALUES ('323', '2650', 'InferenceResult', '3', 'AC11000307D016C66E8631578DEE005A', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:56:59.186487.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.548}\", \"alarm_time\": \"2024-07-10 13:56:59\"}', '0', '420', '1728709', 'broker-a', '172.18.0.1', '2024-07-10 13:58:26', '1720591106142');
INSERT INTO `rocket_mq_fail_msg` VALUES ('324', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86315851FC005B', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:57:51.140486.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.56}\", \"alarm_time\": \"2024-07-10 13:57:51\"}', '0', '422', '1733528', 'broker-a', '172.18.0.1', '2024-07-10 13:59:02', '1720591142585');
INSERT INTO `rocket_mq_fail_msg` VALUES ('325', '2650', 'InferenceResult', '3', 'AC11000307D016C66E8631594D64005C', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:58:55.517302.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.632}\", \"alarm_time\": \"2024-07-10 13:58:55\"}', '0', '515', '1868157', 'broker-a', '172.18.0.1', '2024-07-10 14:00:06', '1720591206919');
INSERT INTO `rocket_mq_fail_msg` VALUES ('326', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86315A2A09005D', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 13:59:50.596479.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.511}\", \"alarm_time\": \"2024-07-10 13:59:50\"}', '0', '521', '1879574', 'broker-a', '172.18.0.1', '2024-07-10 14:01:03', '1720591263429');
INSERT INTO `rocket_mq_fail_msg` VALUES ('327', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86315B0EF8005E', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:00:50.757453.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.696}\", \"alarm_time\": \"2024-07-10 14:00:50\"}', '0', '534', '1899518', 'broker-a', '172.18.0.1', '2024-07-10 14:02:02', '1720591322025');
INSERT INTO `rocket_mq_fail_msg` VALUES ('328', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86315BF9AC005F', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:01:50.767530.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.615}\", \"alarm_time\": \"2024-07-10 14:01:50\"}', '0', '539', '1909120', 'broker-a', '172.18.0.1', '2024-07-10 14:03:02', '1720591382063');
INSERT INTO `rocket_mq_fail_msg` VALUES ('329', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86315CE4AC0060', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:02:50.768459.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.662}\", \"alarm_time\": \"2024-07-10 14:02:50\"}', '0', '631', '2042644', 'broker-a', '172.18.0.1', '2024-07-10 14:04:02', '1720591442210');
INSERT INTO `rocket_mq_fail_msg` VALUES ('330', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86315DD6D40061', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:03:53.076501.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.532}\", \"alarm_time\": \"2024-07-10 14:03:53\"}', '0', '639', '2057356', 'broker-a', '172.18.0.1', '2024-07-10 14:05:04', '1720591504231');
INSERT INTO `rocket_mq_fail_msg` VALUES ('331', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86315EC41D0062', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:04:50.911587.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.679}\", \"alarm_time\": \"2024-07-10 14:04:50\"}', '0', '647', '2072075', 'broker-a', '172.18.0.1', '2024-07-10 14:06:04', '1720591564982');
INSERT INTO `rocket_mq_fail_msg` VALUES ('332', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86315FA5630063', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:05:51.357995.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.519}\", \"alarm_time\": \"2024-07-10 14:05:51\"}', '0', '651', '2080194', 'broker-a', '172.18.0.1', '2024-07-10 14:07:02', '1720591622651');
INSERT INTO `rocket_mq_fail_msg` VALUES ('333', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863160A4040064', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:06:56.620637.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.616}\", \"alarm_time\": \"2024-07-10 14:06:56\"}', '0', '671', '2110889', 'broker-a', '172.18.0.1', '2024-07-10 14:08:07', '1720591687823');
INSERT INTO `rocket_mq_fail_msg` VALUES ('334', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86316179E80065', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:07:51.100481.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.692}\", \"alarm_time\": \"2024-07-10 14:07:51\"}', '0', '758', '2212459', 'broker-a', '172.18.0.1', '2024-07-10 14:09:35', '1720591775154');
INSERT INTO `rocket_mq_fail_msg` VALUES ('335', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86316352670067', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:09:51.872329.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.513}\", \"alarm_time\": \"2024-07-10 14:09:51\"}', '0', '785', '2268246', 'broker-a', '172.18.0.1', '2024-07-10 14:13:17', '1720591997892');
INSERT INTO `rocket_mq_fail_msg` VALUES ('336', '2650', 'InferenceResult', '3', 'AC11000307D016C66E8631627CF10066', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:08:57.453576.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.57}\", \"alarm_time\": \"2024-07-10 14:08:57\"}', '0', '784', '2267725', 'broker-a', '172.18.0.1', '2024-07-10 14:13:17', '1720591997865');
INSERT INTO `rocket_mq_fail_msg` VALUES ('337', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86316462400068', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:11:01.813995.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.527}\", \"alarm_time\": \"2024-07-10 14:11:01\"}', '0', '794', '2287104', 'broker-a', '172.18.0.1', '2024-07-10 14:14:17', '1720592057923');
INSERT INTO `rocket_mq_fail_msg` VALUES ('338', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86316528280069', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:11:52.478936.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.513}\", \"alarm_time\": \"2024-07-10 14:11:52\"}', '0', '793', '2286582', 'broker-a', '172.18.0.1', '2024-07-10 14:14:17', '1720592057923');
INSERT INTO `rocket_mq_fail_msg` VALUES ('339', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86316615C4006A', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:12:53.309645.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.51}\", \"alarm_time\": \"2024-07-10 14:12:53\"}', '0', '868', '2393910', 'broker-a', '172.18.0.1', '2024-07-10 14:14:46', '1720592086989');
INSERT INTO `rocket_mq_fail_msg` VALUES ('340', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863166FA40006B', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:13:51.446844.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.61}\", \"alarm_time\": \"2024-07-10 14:13:51\"}', '0', '871', '2398660', 'broker-a', '172.18.0.1', '2024-07-10 14:15:03', '1720592103602');
INSERT INTO `rocket_mq_fail_msg` VALUES ('341', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863167E388006C', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:14:51.511242.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.539}\", \"alarm_time\": \"2024-07-10 14:14:51\"}', '0', '904', '2445966', 'broker-a', '172.18.0.1', '2024-07-10 14:16:02', '1720592162894');
INSERT INTO `rocket_mq_fail_msg` VALUES ('342', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863168D098006D', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:15:52.167566.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.535}\", \"alarm_time\": \"2024-07-10 14:15:52\"}', '0', '913', '2462002', 'broker-a', '172.18.0.1', '2024-07-10 14:17:03', '1720592223691');
INSERT INTO `rocket_mq_fail_msg` VALUES ('343', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863169B885006E', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:16:51.657810.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.646}\", \"alarm_time\": \"2024-07-10 14:16:51\"}', '0', '920', '2474673', 'broker-a', '172.18.0.1', '2024-07-10 14:18:02', '1720592282940');
INSERT INTO `rocket_mq_fail_msg` VALUES ('344', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86316AAE78006F', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:17:54.628459.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.538}\", \"alarm_time\": \"2024-07-10 14:17:54\"}', '0', '932', '2495256', 'broker-a', '172.18.0.1', '2024-07-10 14:19:06', '1720592346122');
INSERT INTO `rocket_mq_fail_msg` VALUES ('345', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86316B8F180070', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:18:51.738443.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.654}\", \"alarm_time\": \"2024-07-10 14:18:51\"}', '0', '941', '2511285', 'broker-a', '172.18.0.1', '2024-07-10 14:20:03', '1720592403581');
INSERT INTO `rocket_mq_fail_msg` VALUES ('346', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86316C796C0071', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:19:51.972006.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.57}\", \"alarm_time\": \"2024-07-10 14:19:51\"}', '0', '946', '2521055', 'broker-a', '172.18.0.1', '2024-07-10 14:21:03', '1720592463548');
INSERT INTO `rocket_mq_fail_msg` VALUES ('347', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86316D71290072', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:20:55.313603.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.503}\", \"alarm_time\": \"2024-07-10 14:20:55\"}', '0', '1016', '2622839', 'broker-a', '172.18.0.1', '2024-07-10 14:22:06', '1720592526869');
INSERT INTO `rocket_mq_fail_msg` VALUES ('348', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86316E53000073', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:21:53.371439.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.54}\", \"alarm_time\": \"2024-07-10 14:21:53\"}', '0', '1063', '2690677', 'broker-a', '172.18.0.1', '2024-07-10 14:23:04', '1720592584754');
INSERT INTO `rocket_mq_fail_msg` VALUES ('349', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86316F460C0074', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:22:55.217394.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.507}\", \"alarm_time\": \"2024-07-10 14:22:55\"}', '0', '1073', '2708159', 'broker-a', '172.18.0.1', '2024-07-10 14:24:06', '1720592646821');
INSERT INTO `rocket_mq_fail_msg` VALUES ('350', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863170253C0075', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:23:52.058189.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.736}\", \"alarm_time\": \"2024-07-10 14:23:52\"}', '0', '1080', '2721232', 'broker-a', '172.18.0.1', '2024-07-10 14:25:04', '1720592704017');
INSERT INTO `rocket_mq_fail_msg` VALUES ('351', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86317112A00076', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:24:53.370425.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.514}\", \"alarm_time\": \"2024-07-10 14:24:53\"}', '0', '1088', '2735951', 'broker-a', '172.18.0.1', '2024-07-10 14:26:04', '1720592764797');
INSERT INTO `rocket_mq_fail_msg` VALUES ('352', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86317204E90077', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:25:55.073471.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.587}\", \"alarm_time\": \"2024-07-10 14:25:55\"}', '0', '1099', '2755619', 'broker-a', '172.18.0.1', '2024-07-10 14:27:06', '1720592826759');
INSERT INTO `rocket_mq_fail_msg` VALUES ('353', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863172E2C80078', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:26:52.404339.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.614}\", \"alarm_time\": \"2024-07-10 14:26:52\"}', '0', '1105', '2766706', 'broker-a', '172.18.0.1', '2024-07-10 14:28:03', '1720592883563');
INSERT INTO `rocket_mq_fail_msg` VALUES ('354', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863173F39A0079', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:28:01.923156.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.632}\", \"alarm_time\": \"2024-07-10 14:28:01\"}', '0', '1123', '2797262', 'broker-a', '172.18.0.1', '2024-07-10 14:29:13', '1720592953437');
INSERT INTO `rocket_mq_fail_msg` VALUES ('355', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863174B96D007A', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:28:52.691974.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.583}\", \"alarm_time\": \"2024-07-10 14:28:52\"}', '0', '1190', '2895542', 'broker-a', '172.18.0.1', '2024-07-10 14:30:04', '1720593004085');
INSERT INTO `rocket_mq_fail_msg` VALUES ('356', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863175B44E007B', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:29:56.864470.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.514}\", \"alarm_time\": \"2024-07-10 14:29:56\"}', '0', '1226', '2949792', 'broker-a', '172.18.0.1', '2024-07-10 14:31:08', '1720593068271');
INSERT INTO `rocket_mq_fail_msg` VALUES ('357', '2650', 'InferenceResult', '3', 'AC11000307D016C66E8631768DF4007C', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:30:52.573454.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.539}\", \"alarm_time\": \"2024-07-10 14:30:52\"}', '0', '1242', '2975310', 'broker-a', '172.18.0.1', '2024-07-10 14:32:03', '1720593123996');
INSERT INTO `rocket_mq_fail_msg` VALUES ('358', '2650', 'InferenceResult', '3', 'AC11000307D016C66E8631778054007D', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:31:54.581645.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.583}\", \"alarm_time\": \"2024-07-10 14:31:54\"}', '0', '1252', '2993132', 'broker-a', '172.18.0.1', '2024-07-10 14:33:06', '1720593186062');
INSERT INTO `rocket_mq_fail_msg` VALUES ('359', '2650', 'InferenceResult', '3', 'AC11000307D016C66E8631786232007E', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:32:52.617673.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.651}\", \"alarm_time\": \"2024-07-10 14:32:52\"}', '0', '1261', '3009171', 'broker-a', '172.18.0.1', '2024-07-10 14:34:03', '1720593243852');
INSERT INTO `rocket_mq_fail_msg` VALUES ('360', '2650', 'InferenceResult', '3', 'AC11000307D016C66E8631794DC5007F', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:33:52.675004.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.74}\", \"alarm_time\": \"2024-07-10 14:33:52\"}', '0', '1273', '3029992', 'broker-a', '172.18.0.1', '2024-07-10 14:35:04', '1720593304238');
INSERT INTO `rocket_mq_fail_msg` VALUES ('361', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86317A41F40080', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:34:55.424488.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.519}\", \"alarm_time\": \"2024-07-10 14:34:55\"}', '0', '1287', '3054248', 'broker-a', '172.18.0.1', '2024-07-10 14:36:06', '1720593366674');
INSERT INTO `rocket_mq_fail_msg` VALUES ('362', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86317B24B50081', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:35:53.358578.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.514}\", \"alarm_time\": \"2024-07-10 14:35:53\"}', '0', '1297', '3069652', 'broker-a', '172.18.0.1', '2024-07-10 14:37:04', '1720593424870');
INSERT INTO `rocket_mq_fail_msg` VALUES ('363', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86317C11010082', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:36:53.766089.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.522}\", \"alarm_time\": \"2024-07-10 14:36:53\"}', '0', '1306', '3086087', 'broker-a', '172.18.0.1', '2024-07-10 14:38:05', '1720593485360');
INSERT INTO `rocket_mq_fail_msg` VALUES ('364', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86317D07540083', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:37:57.031942.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.57}\", \"alarm_time\": \"2024-07-10 14:37:57\"}', '0', '1399', '3222190', 'broker-a', '172.18.0.1', '2024-07-10 14:39:33', '1720593573083');
INSERT INTO `rocket_mq_fail_msg` VALUES ('365', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86317DFD640084', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:39:00.028441.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.63}\", \"alarm_time\": \"2024-07-10 14:39:00\"}', '0', '1415', '3247410', 'broker-a', '172.18.0.1', '2024-07-10 14:40:11', '1720593611286');
INSERT INTO `rocket_mq_fail_msg` VALUES ('366', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86317EE1E40085', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:39:58.216408.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.527}\", \"alarm_time\": \"2024-07-10 14:39:58\"}', '0', '1426', '3266752', 'broker-a', '172.18.0.1', '2024-07-10 14:41:09', '1720593669900');
INSERT INTO `rocket_mq_fail_msg` VALUES ('367', '2650', 'InferenceResult', '3', 'AC11000307D016C66E86317FB7700086', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:40:53.112375.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.668}\", \"alarm_time\": \"2024-07-10 14:40:53\"}', '0', '1445', '3283576', 'broker-a', '172.18.0.1', '2024-07-10 14:44:49', '1720593889753');
INSERT INTO `rocket_mq_fail_msg` VALUES ('368', '2650', 'InferenceResult', '3', 'AC11000307D016C66E863180A3510087', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:41:53.409855.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.526}\", \"alarm_time\": \"2024-07-10 14:41:53\"}', '0', '1477', '3344554', 'broker-a', '172.18.0.1', '2024-07-10 14:46:12', '1720593972569');
INSERT INTO `rocket_mq_fail_msg` VALUES ('369', '2650', 'InferenceResult', '3', 'AC11000307D016C66E8631818BFD0088', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:42:53.236486.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.54}\", \"alarm_time\": \"2024-07-10 14:42:53\"}', '0', '1479', '3347527', 'broker-a', '172.18.0.1', '2024-07-10 14:46:31', '1720593991514');
INSERT INTO `rocket_mq_fail_msg` VALUES ('370', '2650', 'InferenceResult', '3', 'AC11000307D016C66E8631827D3C0089', '{\"task_no\": \"AN9LZYvJ\", \"model_name\": \"yolov8_sl_fh.engine\", \"img_file_name\": \"AN9LZYvJ_2024-07-10 14:43:54.875321.jpg\", \"bucket_name\": \"algcenter-yolov8-sl-fh\", \"cls_score\": \"{\'smoke\': 0.508}\", \"alarm_time\": \"2024-07-10 14:43:54\"}', '0', '1480', '3348048', 'broker-a', '172.18.0.1', '2024-07-10 14:46:31', '1720593991589');
INSERT INTO `rocket_mq_fail_msg` VALUES ('371', '2650', 'InferenceResult', '3', 'AC110003013E75EFCF0B324364640000', '{\"task_no\": \"djnOPKgW\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"djnOPKgW_2024-07-10 18:14:37.178766.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'person\': 0.833}\", \"alarm_time\": \"2024-07-10 18:14:37\"}', '0', '2231', '4504223', 'broker-a', '172.18.0.1', '2024-07-10 18:16:17', '1720606577921');
INSERT INTO `rocket_mq_fail_msg` VALUES ('372', '2650', 'InferenceResult', '3', 'AC110003013E75EFCF0B32444F390001', '{\"task_no\": \"djnOPKgW\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"djnOPKgW_2024-07-10 18:15:37.248263.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'person\': 0.874}\", \"alarm_time\": \"2024-07-10 18:15:37\"}', '0', '2232', '4505855', 'broker-a', '172.18.0.1', '2024-07-10 18:17:17', '1720606637782');
INSERT INTO `rocket_mq_fail_msg` VALUES ('373', '2650', 'InferenceResult', '3', 'AC11000306E6773CAEEE356E46F40000', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:00:19.454290.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.871}\", \"alarm_time\": \"2024-07-11 09:00:19\"}', '0', '2412', '4856548', 'broker-a', '172.18.0.1', '2024-07-11 09:02:00', '1720659720997');
INSERT INTO `rocket_mq_fail_msg` VALUES ('374', '2650', 'InferenceResult', '3', 'AC11000306E6773CAEEE356F31F00001', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:01:19.535030.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.333}\", \"alarm_time\": \"2024-07-11 09:01:19\"}', '0', '2415', '4862288', 'broker-a', '172.18.0.1', '2024-07-11 09:02:59', '1720659779994');
INSERT INTO `rocket_mq_fail_msg` VALUES ('375', '2650', 'InferenceResult', '3', 'AC11000306E6773CAEEE35701C950002', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:02:19.573603.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.302, \'person\': 0.894, \'handbag\': 0.26}\", \"alarm_time\": \"2024-07-11 09:02:19\"}', '0', '2418', '4868528', 'broker-a', '172.18.0.1', '2024-07-11 09:04:00', '1720659840043');
INSERT INTO `rocket_mq_fail_msg` VALUES ('376', '2650', 'InferenceResult', '3', 'AC11000306E6773CAEEE3571069A0003', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:03:19.613443.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.261}\", \"alarm_time\": \"2024-07-11 09:03:19\"}', '0', '2423', '4878631', 'broker-a', '172.18.0.1', '2024-07-11 09:05:02', '1720659902372');
INSERT INTO `rocket_mq_fail_msg` VALUES ('377', '2650', 'InferenceResult', '3', 'AC11000306E6773CAEEE3571F2840004', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:04:20.007392.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.459, \'person\': 0.308}\", \"alarm_time\": \"2024-07-11 09:04:20\"}', '0', '2426', '4885004', 'broker-a', '172.18.0.1', '2024-07-11 09:06:00', '1720659960608');
INSERT INTO `rocket_mq_fail_msg` VALUES ('378', '2650', 'InferenceResult', '3', 'AC11000306E6773CAEEE3572DD180005', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:05:20.050239.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.331}\", \"alarm_time\": \"2024-07-11 09:05:20\"}', '0', '2428', '4889858', 'broker-a', '172.18.0.1', '2024-07-11 09:07:00', '1720660020415');
INSERT INTO `rocket_mq_fail_msg` VALUES ('379', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC8357455580000', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:06:56.016339.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'clock\': 0.807}\", \"alarm_time\": \"2024-07-11 09:06:56\"}', '0', '2437', '4906121', 'broker-a', '172.18.0.1', '2024-07-11 09:08:38', '1720660118652');
INSERT INTO `rocket_mq_fail_msg` VALUES ('380', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC8357540380001', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:07:56.073561.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'bus\': 0.676, \'train\': 0.43}\", \"alarm_time\": \"2024-07-11 09:07:56\"}', '0', '2442', '4915944', 'broker-a', '172.18.0.1', '2024-07-11 09:09:38', '1720660178125');
INSERT INTO `rocket_mq_fail_msg` VALUES ('381', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC835762AF00002', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:08:56.129315.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'motorcycle\': 0.717, \'car\': 0.413}\", \"alarm_time\": \"2024-07-11 09:08:56\"}', '0', '2446', '4923973', 'broker-a', '172.18.0.1', '2024-07-11 09:10:37', '1720660237786');
INSERT INTO `rocket_mq_fail_msg` VALUES ('382', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC83577147C0003', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:09:56.184492.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.709}\", \"alarm_time\": \"2024-07-11 09:09:56\"}', '0', '2451', '4933679', 'broker-a', '172.18.0.1', '2024-07-11 09:11:36', '1720660296828');
INSERT INTO `rocket_mq_fail_msg` VALUES ('383', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC83578000F0004', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:10:56.260475.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.707}\", \"alarm_time\": \"2024-07-11 09:10:56\"}', '0', '2459', '4948192', 'broker-a', '172.18.0.1', '2024-07-11 09:12:38', '1720660358705');
INSERT INTO `rocket_mq_fail_msg` VALUES ('384', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC83578EA380005', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:11:56.330588.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.707, \'person\': 0.581}\", \"alarm_time\": \"2024-07-11 09:11:56\"}', '0', '2464', '4957719', 'broker-a', '172.18.0.1', '2024-07-11 09:13:38', '1720660418088');
INSERT INTO `rocket_mq_fail_msg` VALUES ('385', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC83579D4B00006', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:12:56.370702.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.752}\", \"alarm_time\": \"2024-07-11 09:12:56\"}', '0', '2469', '4966579', 'broker-a', '172.18.0.1', '2024-07-11 09:14:37', '1720660477048');
INSERT INTO `rocket_mq_fail_msg` VALUES ('386', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC8357ABF880007', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:13:56.460464.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.683}\", \"alarm_time\": \"2024-07-11 09:13:56\"}', '0', '2475', '4978633', 'broker-a', '172.18.0.1', '2024-07-11 09:15:37', '1720660537860');
INSERT INTO `rocket_mq_fail_msg` VALUES ('387', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC8357BAAA40008', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:14:56.511151.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.922, \'person\': 0.382}\", \"alarm_time\": \"2024-07-11 09:14:56\"}', '0', '2483', '4991608', 'broker-a', '172.18.0.1', '2024-07-11 09:16:37', '1720660597444');
INSERT INTO `rocket_mq_fail_msg` VALUES ('388', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC8357C941C0009', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:15:56.574260.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.862}\", \"alarm_time\": \"2024-07-11 09:15:56\"}', '0', '2489', '5004129', 'broker-a', '172.18.0.1', '2024-07-11 09:17:38', '1720660658148');
INSERT INTO `rocket_mq_fail_msg` VALUES ('389', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC8357D7EBC000A', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:16:56.616230.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.834, \'cell phone\': 0.372}\", \"alarm_time\": \"2024-07-11 09:16:56\"}', '0', '2494', '5013784', 'broker-a', '172.18.0.1', '2024-07-11 09:18:37', '1720660717237');
INSERT INTO `rocket_mq_fail_msg` VALUES ('390', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC8357E6A3C000B', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:17:56.674504.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'person\': 0.916, \'car\': 0.626, \'tie\': 0.264}\", \"alarm_time\": \"2024-07-11 09:17:56\"}', '0', '2502', '5028491', 'broker-a', '172.18.0.1', '2024-07-11 09:19:37', '1720660777632');
INSERT INTO `rocket_mq_fail_msg` VALUES ('391', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC8357F54C4000C', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:18:56.756111.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.455}\", \"alarm_time\": \"2024-07-11 09:18:56\"}', '0', '2510', '5042501', 'broker-a', '172.18.0.1', '2024-07-11 09:20:37', '1720660837537');
INSERT INTO `rocket_mq_fail_msg` VALUES ('392', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC835803E94000D', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:19:56.814054.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.66}\", \"alarm_time\": \"2024-07-11 09:19:56\"}', '0', '2516', '5052950', 'broker-a', '172.18.0.1', '2024-07-11 09:21:37', '1720660897433');
INSERT INTO `rocket_mq_fail_msg` VALUES ('393', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC835812982000E', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:20:56.860490.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.415}\", \"alarm_time\": \"2024-07-11 09:20:56\"}', '0', '2526', '5071244', 'broker-a', '172.18.0.1', '2024-07-11 09:22:38', '1720660958522');
INSERT INTO `rocket_mq_fail_msg` VALUES ('394', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC835821430000F', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:21:56.912486.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'toilet\': 0.484, \'car\': 0.299}\", \"alarm_time\": \"2024-07-11 09:21:56\"}', '0', '2532', '5080918', 'broker-a', '172.18.0.1', '2024-07-11 09:23:37', '1720661017615');
INSERT INTO `rocket_mq_fail_msg` VALUES ('395', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC83582FE240010', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:22:56.993027.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.748}\", \"alarm_time\": \"2024-07-11 09:22:56\"}', '0', '2540', '5097111', 'broker-a', '172.18.0.1', '2024-07-11 09:24:38', '1720661078196');
INSERT INTO `rocket_mq_fail_msg` VALUES ('396', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC83583E96A0011', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:23:57.066181.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.576, \'truck\': 0.291}\", \"alarm_time\": \"2024-07-11 09:23:57\"}', '0', '2548', '5110034', 'broker-a', '172.18.0.1', '2024-07-11 09:25:37', '1720661137804');
INSERT INTO `rocket_mq_fail_msg` VALUES ('397', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC83584D3900012', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:24:57.113613.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.725, \'person\': 0.518}\", \"alarm_time\": \"2024-07-11 09:24:57\"}', '0', '2555', '5123535', 'broker-a', '172.18.0.1', '2024-07-11 09:26:38', '1720661198112');
INSERT INTO `rocket_mq_fail_msg` VALUES ('398', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC83585BE5C0013', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:25:57.174010.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.883}\", \"alarm_time\": \"2024-07-11 09:25:57\"}', '0', '2566', '5141334', 'broker-a', '172.18.0.1', '2024-07-11 09:27:57', '1720661277625');
INSERT INTO `rocket_mq_fail_msg` VALUES ('399', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC83586A9940014', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:26:57.225094.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.255}\", \"alarm_time\": \"2024-07-11 09:26:57\"}', '0', '2577', '5163915', 'broker-a', '172.18.0.1', '2024-07-11 09:29:37', '1720661377814');
INSERT INTO `rocket_mq_fail_msg` VALUES ('400', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC8358793480015', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:27:57.314345.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.922, \'person\': 0.379}\", \"alarm_time\": \"2024-07-11 09:27:57\"}', '0', '2585', '5175843', 'broker-a', '172.18.0.1', '2024-07-11 09:30:37', '1720661437636');
INSERT INTO `rocket_mq_fail_msg` VALUES ('401', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC835887DF80016', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:28:57.347980.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.862}\", \"alarm_time\": \"2024-07-11 09:28:57\"}', '0', '2591', '5187965', 'broker-a', '172.18.0.1', '2024-07-11 09:31:37', '1720661497692');
INSERT INTO `rocket_mq_fail_msg` VALUES ('402', '2650', 'InferenceResult', '3', 'AC11000309D156F0458E358ADBF80000', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:31:32.486347.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.591}\", \"alarm_time\": \"2024-07-11 09:31:32\"}', '0', '2605', '5209316', 'broker-a', '172.18.0.1', '2024-07-11 09:33:32', '1720661612788');
INSERT INTO `rocket_mq_fail_msg` VALUES ('403', '2650', 'InferenceResult', '3', 'AC11000300BB7AF8FA0B369BF5C00000', '{\"task_no\": \"bnhy5jmG\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"bnhy5jmG_2024-07-11 14:29:50.197839.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'person\': 0.48, \'car\': 0.302}\", \"alarm_time\": \"2024-07-11 14:29:50\"}', '0', '2890', '5631556', 'broker-a', '172.18.0.1', '2024-07-11 14:31:31', '1720679491284');
INSERT INTO `rocket_mq_fail_msg` VALUES ('404', '2650', 'InferenceResult', '3', 'AC1100030172008908D636A0F5780000', '{\"task_no\": \"otRJPgYz\", \"model_name\": \"yolov8_safety_hat.engine\", \"img_file_name\": \"otRJPgYz_2024-07-11 14:35:18.090351.jpg\", \"bucket_name\": \"algcenter-yolov8-safety-hat\", \"cls_score\": \"{}\", \"alarm_time\": \"2024-07-11 14:35:18\"}', '0', '2896', '5647659', 'broker-a', '172.18.0.1', '2024-07-11 14:36:58', '1720679818374');
INSERT INTO `rocket_mq_fail_msg` VALUES ('405', '2650', 'InferenceResult', '3', 'AC1100030172008908D636A1E0180001', '{\"task_no\": \"otRJPgYz\", \"model_name\": \"yolov8_safety_hat.engine\", \"img_file_name\": \"otRJPgYz_2024-07-11 14:36:18.150944.jpg\", \"bucket_name\": \"algcenter-yolov8-safety-hat\", \"cls_score\": \"{\'Safety Hat\': 0.668}\", \"alarm_time\": \"2024-07-11 14:36:18\"}', '0', '2900', '5655722', 'broker-a', '172.18.0.1', '2024-07-11 14:37:58', '1720679878456');
INSERT INTO `rocket_mq_fail_msg` VALUES ('406', '2650', 'InferenceResult', '3', 'AC1100030172008908D636A2CA480002', '{\"task_no\": \"otRJPgYz\", \"model_name\": \"yolov8_safety_hat.engine\", \"img_file_name\": \"otRJPgYz_2024-07-11 14:37:18.211624.jpg\", \"bucket_name\": \"algcenter-yolov8-safety-hat\", \"cls_score\": \"{\'Safety Hat\': 0.891}\", \"alarm_time\": \"2024-07-11 14:37:18\"}', '0', '2902', '5733392', 'broker-a', '172.18.0.1', '2024-07-11 14:38:58', '1720679938400');
INSERT INTO `rocket_mq_fail_msg` VALUES ('407', '2650', 'InferenceResult', '3', 'AC1100030172008908D636A3B4EE0003', '{\"task_no\": \"otRJPgYz\", \"model_name\": \"yolov8_safety_hat.engine\", \"img_file_name\": \"otRJPgYz_2024-07-11 14:38:18.265408.jpg\", \"bucket_name\": \"algcenter-yolov8-safety-hat\", \"cls_score\": \"{\'Safety Hat\': 0.764}\", \"alarm_time\": \"2024-07-11 14:38:18\"}', '0', '2906', '5777403', 'broker-a', '172.18.0.1', '2024-07-11 14:39:58', '1720679998436');
INSERT INTO `rocket_mq_fail_msg` VALUES ('408', '2650', 'InferenceResult', '3', 'AC11000306E6773CAEEE3571069A0003', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:03:19.613443.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.261}\", \"alarm_time\": \"2024-07-11 09:03:19\"}', '0', '66', '5783299', 'broker-a', '172.18.0.1', '2024-07-11 14:39:33', '1720679973666');
INSERT INTO `rocket_mq_fail_msg` VALUES ('409', '2650', 'InferenceResult', '3', 'AC11000306E6773CAEEE356F31F00001', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:01:19.535030.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.333}\", \"alarm_time\": \"2024-07-11 09:01:19\"}', '0', '64', '5782276', 'broker-a', '172.18.0.1', '2024-07-11 14:39:33', '1720679973664');
INSERT INTO `rocket_mq_fail_msg` VALUES ('410', '2650', 'InferenceResult', '3', 'AC11000306E6773CAEEE3572DD180005', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:05:20.050239.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.331}\", \"alarm_time\": \"2024-07-11 09:05:20\"}', '0', '69', '5784866', 'broker-a', '172.18.0.1', '2024-07-11 14:39:33', '1720679973674');
INSERT INTO `rocket_mq_fail_msg` VALUES ('411', '2650', 'InferenceResult', '3', 'AC11000306E6773CAEEE356E46F40000', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:00:19.454290.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.871}\", \"alarm_time\": \"2024-07-11 09:00:19\"}', '0', '71', '5785903', 'broker-a', '172.18.0.1', '2024-07-11 14:39:33', '1720679973676');
INSERT INTO `rocket_mq_fail_msg` VALUES ('412', '2650', 'InferenceResult', '3', 'AC11000306E6773CAEEE3571F2840004', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:04:20.007392.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.459, \'person\': 0.308}\", \"alarm_time\": \"2024-07-11 09:04:20\"}', '0', '70', '5785376', 'broker-a', '172.18.0.1', '2024-07-11 14:39:33', '1720679973675');
INSERT INTO `rocket_mq_fail_msg` VALUES ('413', '2650', 'InferenceResult', '3', 'AC110003013E75EFCF0B324364640000', '{\"task_no\": \"djnOPKgW\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"djnOPKgW_2024-07-10 18:14:37.178766.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'person\': 0.833}\", \"alarm_time\": \"2024-07-10 18:14:37\"}', '0', '68', '5784353', 'broker-a', '172.18.0.1', '2024-07-11 14:39:33', '1720679973672');
INSERT INTO `rocket_mq_fail_msg` VALUES ('414', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC8357455580000', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:06:56.016339.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'clock\': 0.807}\", \"alarm_time\": \"2024-07-11 09:06:56\"}', '0', '72', '5786413', 'broker-a', '172.18.0.1', '2024-07-11 14:39:33', '1720679973677');
INSERT INTO `rocket_mq_fail_msg` VALUES ('415', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC8357ABF880007', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:13:56.460464.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.683}\", \"alarm_time\": \"2024-07-11 09:13:56\"}', '0', '76', '5790254', 'broker-a', '172.18.0.1', '2024-07-11 14:39:33', '1720679973729');
INSERT INTO `rocket_mq_fail_msg` VALUES ('416', '2650', 'InferenceResult', '3', 'AC11000306E6773CAEEE35701C950002', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:02:19.573603.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.302, \'person\': 0.894, \'handbag\': 0.26}\", \"alarm_time\": \"2024-07-11 09:02:19\"}', '0', '67', '5783809', 'broker-a', '172.18.0.1', '2024-07-11 14:39:33', '1720679973672');
INSERT INTO `rocket_mq_fail_msg` VALUES ('417', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC83578000F0004', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:10:56.260475.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.707}\", \"alarm_time\": \"2024-07-11 09:10:56\"}', '0', '74', '5787450', 'broker-a', '172.18.0.1', '2024-07-11 14:39:33', '1720679973679');
INSERT INTO `rocket_mq_fail_msg` VALUES ('418', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC83577147C0003', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:09:56.184492.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.709}\", \"alarm_time\": \"2024-07-11 09:09:56\"}', '0', '78', '5791291', 'broker-a', '172.18.0.1', '2024-07-11 14:39:33', '1720679973730');
INSERT INTO `rocket_mq_fail_msg` VALUES ('419', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC8357540380001', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:07:56.073561.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'bus\': 0.676, \'train\': 0.43}\", \"alarm_time\": \"2024-07-11 09:07:56\"}', '0', '73', '5786925', 'broker-a', '172.18.0.1', '2024-07-11 14:39:33', '1720679973678');
INSERT INTO `rocket_mq_fail_msg` VALUES ('420', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC835803E94000D', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:19:56.814054.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.66}\", \"alarm_time\": \"2024-07-11 09:19:56\"}', '0', '80', '5792332', 'broker-a', '172.18.0.1', '2024-07-11 14:39:33', '1720679973736');
INSERT INTO `rocket_mq_fail_msg` VALUES ('421', '2650', 'InferenceResult', '3', 'AC110003013E75EFCF0B32444F390001', '{\"task_no\": \"djnOPKgW\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"djnOPKgW_2024-07-10 18:15:37.248263.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'person\': 0.874}\", \"alarm_time\": \"2024-07-10 18:15:37\"}', '0', '65', '5782786', 'broker-a', '172.18.0.1', '2024-07-11 14:39:33', '1720679973666');
INSERT INTO `rocket_mq_fail_msg` VALUES ('422', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC83579D4B00006', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:12:56.370702.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.752}\", \"alarm_time\": \"2024-07-11 09:12:56\"}', '0', '75', '5789744', 'broker-a', '172.18.0.1', '2024-07-11 14:39:33', '1720679973726');
INSERT INTO `rocket_mq_fail_msg` VALUES ('423', '2650', 'InferenceResult', '3', 'AC11000309D156F0458E358ADBF80000', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:31:32.486347.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.591}\", \"alarm_time\": \"2024-07-11 09:31:32\"}', '0', '83', '5793894', 'broker-a', '172.18.0.1', '2024-07-11 14:39:33', '1720679973745');
INSERT INTO `rocket_mq_fail_msg` VALUES ('424', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC83583E96A0011', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:23:57.066181.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.576, \'truck\': 0.291}\", \"alarm_time\": \"2024-07-11 09:23:57\"}', '0', '82', '5793368', 'broker-a', '172.18.0.1', '2024-07-11 14:39:33', '1720679973744');
INSERT INTO `rocket_mq_fail_msg` VALUES ('425', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC835821430000F', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:21:56.912486.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'toilet\': 0.484, \'car\': 0.299}\", \"alarm_time\": \"2024-07-11 09:21:56\"}', '0', '81', '5792841', 'broker-a', '172.18.0.1', '2024-07-11 14:39:33', '1720679973744');
INSERT INTO `rocket_mq_fail_msg` VALUES ('426', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC83578EA380005', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:11:56.330588.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.707, \'person\': 0.581}\", \"alarm_time\": \"2024-07-11 09:11:56\"}', '0', '77', '5790764', 'broker-a', '172.18.0.1', '2024-07-11 14:39:33', '1720679973729');
INSERT INTO `rocket_mq_fail_msg` VALUES ('427', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC835762AF00002', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:08:56.129315.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'motorcycle\': 0.717, \'car\': 0.413}\", \"alarm_time\": \"2024-07-11 09:08:56\"}', '0', '79', '5791801', 'broker-a', '172.18.0.1', '2024-07-11 14:39:33', '1720679973730');
INSERT INTO `rocket_mq_fail_msg` VALUES ('428', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC8358793480015', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:27:57.314345.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.922, \'person\': 0.379}\", \"alarm_time\": \"2024-07-11 09:27:57\"}', '0', '86', '5795441', 'broker-a', '172.18.0.1', '2024-07-11 14:39:36', '1720679976448');
INSERT INTO `rocket_mq_fail_msg` VALUES ('429', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC83585BE5C0013', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:25:57.174010.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.883}\", \"alarm_time\": \"2024-07-11 09:25:57\"}', '0', '87', '5795968', 'broker-a', '172.18.0.1', '2024-07-11 14:39:36', '1720679976492');
INSERT INTO `rocket_mq_fail_msg` VALUES ('430', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC83584D3900012', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:24:57.113613.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.725, \'person\': 0.518}\", \"alarm_time\": \"2024-07-11 09:24:57\"}', '0', '84', '5794404', 'broker-a', '172.18.0.1', '2024-07-11 14:39:36', '1720679976410');
INSERT INTO `rocket_mq_fail_msg` VALUES ('431', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC83582FE240010', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:22:56.993027.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.748}\", \"alarm_time\": \"2024-07-11 09:22:56\"}', '0', '85', '5794931', 'broker-a', '172.18.0.1', '2024-07-11 14:39:36', '1720679976411');
INSERT INTO `rocket_mq_fail_msg` VALUES ('432', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC8357E6A3C000B', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:17:56.674504.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'person\': 0.916, \'car\': 0.626, \'tie\': 0.264}\", \"alarm_time\": \"2024-07-11 09:17:56\"}', '0', '89', '5796988', 'broker-a', '172.18.0.1', '2024-07-11 14:39:36', '1720679976507');
INSERT INTO `rocket_mq_fail_msg` VALUES ('433', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC835812982000E', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:20:56.860490.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.415}\", \"alarm_time\": \"2024-07-11 09:20:56\"}', '0', '88', '5796478', 'broker-a', '172.18.0.1', '2024-07-11 14:39:36', '1720679976500');
INSERT INTO `rocket_mq_fail_msg` VALUES ('434', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC8357BAAA40008', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:14:56.511151.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.922, \'person\': 0.382}\", \"alarm_time\": \"2024-07-11 09:14:56\"}', '0', '91', '5798039', 'broker-a', '172.18.0.1', '2024-07-11 14:39:36', '1720679976546');
INSERT INTO `rocket_mq_fail_msg` VALUES ('435', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC83586A9940014', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:26:57.225094.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.255}\", \"alarm_time\": \"2024-07-11 09:26:57\"}', '0', '90', '5797529', 'broker-a', '172.18.0.1', '2024-07-11 14:39:36', '1720679976510');
INSERT INTO `rocket_mq_fail_msg` VALUES ('436', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC835887DF80016', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:28:57.347980.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.862}\", \"alarm_time\": \"2024-07-11 09:28:57\"}', '0', '94', '5799607', 'broker-a', '172.18.0.1', '2024-07-11 14:39:36', '1720679976571');
INSERT INTO `rocket_mq_fail_msg` VALUES ('437', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC8357C941C0009', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:15:56.574260.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.862}\", \"alarm_time\": \"2024-07-11 09:15:56\"}', '0', '93', '5799097', 'broker-a', '172.18.0.1', '2024-07-11 14:39:36', '1720679976571');
INSERT INTO `rocket_mq_fail_msg` VALUES ('438', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC8357F54C4000C', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:18:56.756111.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.455}\", \"alarm_time\": \"2024-07-11 09:18:56\"}', '0', '95', '5801607', 'broker-a', '172.18.0.1', '2024-07-11 14:39:36', '1720679976609');
INSERT INTO `rocket_mq_fail_msg` VALUES ('439', '2650', 'InferenceResult', '3', 'AC11000307B75B2B9BC8357D7EBC000A', '{\"task_no\": \"vvQ9B1dd\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"vvQ9B1dd_2024-07-11 09:16:56.616230.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'car\': 0.834, \'cell phone\': 0.372}\", \"alarm_time\": \"2024-07-11 09:16:56\"}', '0', '92', '5798566', 'broker-a', '172.18.0.1', '2024-07-11 14:39:36', '1720679976546');
INSERT INTO `rocket_mq_fail_msg` VALUES ('440', '2650', 'InferenceResult', '3', 'AC1100030172008908D636A49F920004', '{\"task_no\": \"otRJPgYz\", \"model_name\": \"yolov8_safety_hat.engine\", \"img_file_name\": \"otRJPgYz_2024-07-11 14:39:18.328304.jpg\", \"bucket_name\": \"algcenter-yolov8-safety-hat\", \"cls_score\": \"{\'Safety Hat\': 0.876}\", \"alarm_time\": \"2024-07-11 14:39:18\"}', '0', '2912', '5834190', 'broker-a', '172.18.0.1', '2024-07-11 14:40:58', '1720680058502');
INSERT INTO `rocket_mq_fail_msg` VALUES ('441', '2650', 'InferenceResult', '3', 'AC1100030172008908D636A58A690005', '{\"task_no\": \"otRJPgYz\", \"model_name\": \"yolov8_safety_hat.engine\", \"img_file_name\": \"otRJPgYz_2024-07-11 14:40:18.383014.jpg\", \"bucket_name\": \"algcenter-yolov8-safety-hat\", \"cls_score\": \"{\'Safety Hat\': 0.873}\", \"alarm_time\": \"2024-07-11 14:40:18\"}', '0', '2918', '5844470', 'broker-a', '172.18.0.1', '2024-07-11 14:41:58', '1720680118606');
INSERT INTO `rocket_mq_fail_msg` VALUES ('442', '2650', 'InferenceResult', '3', 'AC1100030172008908D636A674C80006', '{\"task_no\": \"otRJPgYz\", \"model_name\": \"yolov8_safety_hat.engine\", \"img_file_name\": \"otRJPgYz_2024-07-11 14:41:18.443847.jpg\", \"bucket_name\": \"algcenter-yolov8-safety-hat\", \"cls_score\": \"{\'Safety Hat\': 0.444}\", \"alarm_time\": \"2024-07-11 14:41:18\"}', '0', '2922', '5870081', 'broker-a', '172.18.0.1', '2024-07-11 14:42:58', '1720680178641');
INSERT INTO `rocket_mq_fail_msg` VALUES ('443', '2650', 'InferenceResult', '3', 'AC1100030B5713A2F2DF4A076CB10000', '{\"task_no\": \"tensor01\", \"model_name\": \"ebike.engine\", \"img_file_name\": \"tensor01_2024-07-15_08:59:59_138464.jpg\", \"bucket_name\": \"algcenter-ebike\", \"cls_score\": \"{\'person\': 0.258}\", \"alarm_time\": \"2024-07-15 08:59:59\"}', '0', '518', '6628098', 'broker-a', '172.18.0.1', '2024-07-15 09:01:40', '1721005300695');
INSERT INTO `rocket_mq_fail_msg` VALUES ('444', '2650', 'InferenceResult', '3', 'AC1100030B5713A2F2DF4A076CB10000', '{\"task_no\": \"tensor01\", \"model_name\": \"ebike.engine\", \"img_file_name\": \"tensor01_2024-07-15_08:59:59_138464.jpg\", \"bucket_name\": \"algcenter-ebike\", \"cls_score\": \"{\'person\': 0.258}\", \"alarm_time\": \"2024-07-15 08:59:59\"}', '0', '518', '6628098', 'broker-a', '172.18.0.1', '2024-07-15 09:01:40', '1721005300707');
INSERT INTO `rocket_mq_fail_msg` VALUES ('445', '2650', 'InferenceResult', '3', 'AC1100030B5713A2F2DF4A076CB10000', '{\"task_no\": \"tensor01\", \"model_name\": \"ebike.engine\", \"img_file_name\": \"tensor01_2024-07-15_08:59:59_138464.jpg\", \"bucket_name\": \"algcenter-ebike\", \"cls_score\": \"{\'person\': 0.258}\", \"alarm_time\": \"2024-07-15 08:59:59\"}', '0', '519', '6628607', 'broker-a', '172.18.0.1', '2024-07-15 09:01:40', '1721005300709');
INSERT INTO `rocket_mq_fail_msg` VALUES ('446', '2650', 'InferenceResult', '3', 'AC1100030B5713A2F2DF4A076CB10000', '{\"task_no\": \"tensor01\", \"model_name\": \"ebike.engine\", \"img_file_name\": \"tensor01_2024-07-15_08:59:59_138464.jpg\", \"bucket_name\": \"algcenter-ebike\", \"cls_score\": \"{\'person\': 0.258}\", \"alarm_time\": \"2024-07-15 08:59:59\"}', '0', '520', '6630058', 'broker-a', '172.18.0.1', '2024-07-15 09:01:40', '1721005300722');
INSERT INTO `rocket_mq_fail_msg` VALUES ('447', '2650', 'InferenceResult', '3', 'AC1100030B5713A2F2DF4A076CB10000', '{\"task_no\": \"tensor01\", \"model_name\": \"ebike.engine\", \"img_file_name\": \"tensor01_2024-07-15_08:59:59_138464.jpg\", \"bucket_name\": \"algcenter-ebike\", \"cls_score\": \"{\'person\': 0.258}\", \"alarm_time\": \"2024-07-15 08:59:59\"}', '0', '521', '6630881', 'broker-a', '172.18.0.1', '2024-07-15 09:01:40', '1721005300736');
INSERT INTO `rocket_mq_fail_msg` VALUES ('448', '2650', 'InferenceResult', '3', 'AC1100030B5713A2F2DF4A076CB10000', '{\"task_no\": \"tensor01\", \"model_name\": \"ebike.engine\", \"img_file_name\": \"tensor01_2024-07-15_08:59:59_138464.jpg\", \"bucket_name\": \"algcenter-ebike\", \"cls_score\": \"{\'person\': 0.258}\", \"alarm_time\": \"2024-07-15 08:59:59\"}', '0', '520', '6630058', 'broker-a', '172.18.0.1', '2024-07-15 09:01:40', '1721005300741');
INSERT INTO `rocket_mq_fail_msg` VALUES ('449', '2650', 'InferenceResult', '3', 'AC1100030B5713A2F2DF4A076CB10000', '{\"task_no\": \"tensor01\", \"model_name\": \"ebike.engine\", \"img_file_name\": \"tensor01_2024-07-15_08:59:59_138464.jpg\", \"bucket_name\": \"algcenter-ebike\", \"cls_score\": \"{\'person\': 0.258}\", \"alarm_time\": \"2024-07-15 08:59:59\"}', '0', '519', '6628607', 'broker-a', '172.18.0.1', '2024-07-15 09:01:40', '1721005300724');
INSERT INTO `rocket_mq_fail_msg` VALUES ('450', '2650', 'InferenceResult', '3', 'AC1100030B5713A2F2DF4A076CB10000', '{\"task_no\": \"tensor01\", \"model_name\": \"ebike.engine\", \"img_file_name\": \"tensor01_2024-07-15_08:59:59_138464.jpg\", \"bucket_name\": \"algcenter-ebike\", \"cls_score\": \"{\'person\': 0.258}\", \"alarm_time\": \"2024-07-15 08:59:59\"}', '0', '521', '6630881', 'broker-a', '172.18.0.1', '2024-07-15 09:01:40', '1721005300751');
INSERT INTO `rocket_mq_fail_msg` VALUES ('451', '2650', 'InferenceResult', '3', 'AC1100030B5713A2F2DF4A076CB10000', '{\"task_no\": \"tensor01\", \"model_name\": \"ebike.engine\", \"img_file_name\": \"tensor01_2024-07-15_08:59:59_138464.jpg\", \"bucket_name\": \"algcenter-ebike\", \"cls_score\": \"{\'person\': 0.258}\", \"alarm_time\": \"2024-07-15 08:59:59\"}', '0', '522', '6632132', 'broker-a', '172.18.0.1', '2024-07-15 09:01:40', '1721005300758');
INSERT INTO `rocket_mq_fail_msg` VALUES ('452', '2650', 'InferenceResult', '3', 'AC1100030B5713A2F2DF4A076CB10000', '{\"task_no\": \"tensor01\", \"model_name\": \"ebike.engine\", \"img_file_name\": \"tensor01_2024-07-15_08:59:59_138464.jpg\", \"bucket_name\": \"algcenter-ebike\", \"cls_score\": \"{\'person\': 0.258}\", \"alarm_time\": \"2024-07-15 08:59:59\"}', '0', '522', '6632132', 'broker-a', '172.18.0.1', '2024-07-15 09:01:40', '1721005300756');
INSERT INTO `rocket_mq_fail_msg` VALUES ('453', '2650', 'InferenceResult', '3', 'AC1100030B5713A2F2DF4A076CB10000', '{\"task_no\": \"tensor01\", \"model_name\": \"ebike.engine\", \"img_file_name\": \"tensor01_2024-07-15_08:59:59_138464.jpg\", \"bucket_name\": \"algcenter-ebike\", \"cls_score\": \"{\'person\': 0.258}\", \"alarm_time\": \"2024-07-15 08:59:59\"}', '0', '523', '6633269', 'broker-a', '172.18.0.1', '2024-07-15 09:01:40', '1721005300770');
INSERT INTO `rocket_mq_fail_msg` VALUES ('454', '2650', 'InferenceResult', '3', 'AC1100030B5713A2F2DF4A076CB10000', '{\"task_no\": \"tensor01\", \"model_name\": \"ebike.engine\", \"img_file_name\": \"tensor01_2024-07-15_08:59:59_138464.jpg\", \"bucket_name\": \"algcenter-ebike\", \"cls_score\": \"{\'person\': 0.258}\", \"alarm_time\": \"2024-07-15 08:59:59\"}', '0', '524', '6633778', 'broker-a', '172.18.0.1', '2024-07-15 09:01:40', '1721005300770');
INSERT INTO `rocket_mq_fail_msg` VALUES ('455', '2650', 'InferenceResult', '3', 'AC1100030B5713A2F2DF4A076CB10000', '{\"task_no\": \"tensor01\", \"model_name\": \"ebike.engine\", \"img_file_name\": \"tensor01_2024-07-15_08:59:59_138464.jpg\", \"bucket_name\": \"algcenter-ebike\", \"cls_score\": \"{\'person\': 0.258}\", \"alarm_time\": \"2024-07-15 08:59:59\"}', '0', '525', '6634601', 'broker-a', '172.18.0.1', '2024-07-15 09:01:40', '1721005300788');
INSERT INTO `rocket_mq_fail_msg` VALUES ('456', '2650', 'InferenceResult', '3', 'AC1100030B5713A2F2DF4A076CB10000', '{\"task_no\": \"tensor01\", \"model_name\": \"ebike.engine\", \"img_file_name\": \"tensor01_2024-07-15_08:59:59_138464.jpg\", \"bucket_name\": \"algcenter-ebike\", \"cls_score\": \"{\'person\': 0.258}\", \"alarm_time\": \"2024-07-15 08:59:59\"}', '0', '523', '6633269', 'broker-a', '172.18.0.1', '2024-07-15 09:01:40', '1721005300784');
INSERT INTO `rocket_mq_fail_msg` VALUES ('457', '2650', 'InferenceResult', '3', 'AC1100030B5713A2F2DF4A076CB10000', '{\"task_no\": \"tensor01\", \"model_name\": \"ebike.engine\", \"img_file_name\": \"tensor01_2024-07-15_08:59:59_138464.jpg\", \"bucket_name\": \"algcenter-ebike\", \"cls_score\": \"{\'person\': 0.258}\", \"alarm_time\": \"2024-07-15 08:59:59\"}', '0', '524', '6633778', 'broker-a', '172.18.0.1', '2024-07-15 09:01:40', '1721005300811');
INSERT INTO `rocket_mq_fail_msg` VALUES ('458', '2650', 'InferenceResult', '3', 'AC1100030B5713A2F2DF4A076CB10000', '{\"task_no\": \"tensor01\", \"model_name\": \"ebike.engine\", \"img_file_name\": \"tensor01_2024-07-15_08:59:59_138464.jpg\", \"bucket_name\": \"algcenter-ebike\", \"cls_score\": \"{\'person\': 0.258}\", \"alarm_time\": \"2024-07-15 08:59:59\"}', '0', '525', '6634601', 'broker-a', '172.18.0.1', '2024-07-15 09:01:40', '1721005300816');
INSERT INTO `rocket_mq_fail_msg` VALUES ('459', '2650', 'InferenceResult', '3', 'AC11000304B81245F87D4BC38D640003', '{\"task_no\": \"rgpWdWPG\", \"model_name\": \"ebike.engine\", \"img_file_name\": \"rgpWdWPG_2024-07-15_17:05:06_664942.jpg\", \"bucket_name\": \"algcenter-ebike\", \"cls_score\": \"{\'Helmet\': 0.566}\", \"alarm_time\": \"2024-07-15 17:05:06\"}', '0', '98805', '145788408', 'broker-a', '172.18.0.1', '2024-07-15 17:07:47', '1721034467021');
INSERT INTO `rocket_mq_fail_msg` VALUES ('460', '2650', 'InferenceResult', '3', 'AC11000304B81245F87D4BC38D640003', '{\"task_no\": \"rgpWdWPG\", \"model_name\": \"ebike.engine\", \"img_file_name\": \"rgpWdWPG_2024-07-15_17:05:06_664942.jpg\", \"bucket_name\": \"algcenter-ebike\", \"cls_score\": \"{\'Helmet\': 0.566}\", \"alarm_time\": \"2024-07-15 17:05:06\"}', '0', '98805', '145788408', 'broker-a', '172.18.0.1', '2024-07-15 17:07:47', '1721034467048');
INSERT INTO `rocket_mq_fail_msg` VALUES ('461', '2650', 'InferenceResult', '3', 'AC11000304B81245F87D4BC475A80004', '{\"task_no\": \"rgpWdWPG\", \"model_name\": \"ebike.engine\", \"img_file_name\": \"rgpWdWPG_2024-07-15_17:06:06_083839.jpg\", \"bucket_name\": \"algcenter-ebike\", \"cls_score\": \"{\'Helmet\': 0.297}\", \"alarm_time\": \"2024-07-15 17:06:06\"}', '0', '98808', '145795852', 'broker-a', '172.18.0.1', '2024-07-15 17:08:46', '1721034526458');
INSERT INTO `rocket_mq_fail_msg` VALUES ('462', '2650', 'InferenceResult', '3', 'AC11000304B81245F87D4BC475A80004', '{\"task_no\": \"rgpWdWPG\", \"model_name\": \"ebike.engine\", \"img_file_name\": \"rgpWdWPG_2024-07-15_17:06:06_083839.jpg\", \"bucket_name\": \"algcenter-ebike\", \"cls_score\": \"{\'Helmet\': 0.297}\", \"alarm_time\": \"2024-07-15 17:06:06\"}', '0', '98808', '145795852', 'broker-a', '172.18.0.1', '2024-07-15 17:08:46', '1721034526489');
INSERT INTO `rocket_mq_fail_msg` VALUES ('463', '2650', 'InferenceResult', '3', 'AC11000304B81245F87D4BC562340005', '{\"task_no\": \"rgpWdWPG\", \"model_name\": \"ebike.engine\", \"img_file_name\": \"rgpWdWPG_2024-07-15_17:07:06_726372.jpg\", \"bucket_name\": \"algcenter-ebike\", \"cls_score\": \"{\'NO-Helmet\': 0.6}\", \"alarm_time\": \"2024-07-15 17:07:06\"}', '0', '98811', '145804904', 'broker-a', '172.18.0.1', '2024-07-15 17:09:47', '1721034587021');
INSERT INTO `rocket_mq_fail_msg` VALUES ('464', '2650', 'InferenceResult', '3', 'AC11000304B81245F87D4BC562340005', '{\"task_no\": \"rgpWdWPG\", \"model_name\": \"ebike.engine\", \"img_file_name\": \"rgpWdWPG_2024-07-15_17:07:06_726372.jpg\", \"bucket_name\": \"algcenter-ebike\", \"cls_score\": \"{\'NO-Helmet\': 0.6}\", \"alarm_time\": \"2024-07-15 17:07:06\"}', '0', '98811', '145804904', 'broker-a', '172.18.0.1', '2024-07-15 17:09:47', '1721034587041');
INSERT INTO `rocket_mq_fail_msg` VALUES ('465', '2650', 'InferenceResult', '3', 'AC11000304B81245F87D4BC64B150006', '{\"task_no\": \"rgpWdWPG\", \"model_name\": \"ebike.engine\", \"img_file_name\": \"rgpWdWPG_2024-07-15_17:08:06_195701.jpg\", \"bucket_name\": \"algcenter-ebike\", \"cls_score\": \"{\'NO-Helmet\': 0.847, \'Rider\': 0.641}\", \"alarm_time\": \"2024-07-15 17:08:06\"}', '0', '98814', '145811197', 'broker-a', '172.18.0.1', '2024-07-15 17:10:46', '1721034646618');
INSERT INTO `rocket_mq_fail_msg` VALUES ('466', '2650', 'InferenceResult', '3', 'AC11000304B81245F87D4BC64B150006', '{\"task_no\": \"rgpWdWPG\", \"model_name\": \"ebike.engine\", \"img_file_name\": \"rgpWdWPG_2024-07-15_17:08:06_195701.jpg\", \"bucket_name\": \"algcenter-ebike\", \"cls_score\": \"{\'NO-Helmet\': 0.847, \'Rider\': 0.641}\", \"alarm_time\": \"2024-07-15 17:08:06\"}', '0', '98814', '145811197', 'broker-a', '172.18.0.1', '2024-07-15 17:10:46', '1721034646638');
INSERT INTO `rocket_mq_fail_msg` VALUES ('467', '2650', 'InferenceResult', '3', 'AC11000304B81245F87D4BC735BC0007', '{\"task_no\": \"rgpWdWPG\", \"model_name\": \"ebike.engine\", \"img_file_name\": \"rgpWdWPG_2024-07-15_17:09:06_243230.jpg\", \"bucket_name\": \"algcenter-ebike\", \"cls_score\": \"{\'Helmet\': 0.278}\", \"alarm_time\": \"2024-07-15 17:09:06\"}', '0', '98817', '145819538', 'broker-a', '172.18.0.1', '2024-07-15 17:11:46', '1721034706705');
INSERT INTO `rocket_mq_fail_msg` VALUES ('468', '2650', 'InferenceResult', '3', 'AC11000304B81245F87D4BC735BC0007', '{\"task_no\": \"rgpWdWPG\", \"model_name\": \"ebike.engine\", \"img_file_name\": \"rgpWdWPG_2024-07-15_17:09:06_243230.jpg\", \"bucket_name\": \"algcenter-ebike\", \"cls_score\": \"{\'Helmet\': 0.278}\", \"alarm_time\": \"2024-07-15 17:09:06\"}', '0', '98817', '145819538', 'broker-a', '172.18.0.1', '2024-07-15 17:11:46', '1721034706732');
INSERT INTO `rocket_mq_fail_msg` VALUES ('469', '2650', 'InferenceResult', '3', 'AC110003069F66DA72054BC8EA8C0000', '{\"task_no\": \"LCWuFtNJ\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"LCWuFtNJ_2024-07-15_17:10:58_123746.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'person\': 0.7, \'motorcycle\': 0.52, \'chair\': 0.287}\", \"alarm_time\": \"2024-07-15 17:10:58\"}', '0', '98824', '145836668', 'broker-a', '172.18.0.1', '2024-07-15 17:13:38', '1721034818530');
INSERT INTO `rocket_mq_fail_msg` VALUES ('470', '2650', 'InferenceResult', '3', 'AC110003069F66DA72054BC8EA8C0000', '{\"task_no\": \"LCWuFtNJ\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"LCWuFtNJ_2024-07-15_17:10:58_123746.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'person\': 0.7, \'motorcycle\': 0.52, \'chair\': 0.287}\", \"alarm_time\": \"2024-07-15 17:10:58\"}', '0', '98824', '145836668', 'broker-a', '172.18.0.1', '2024-07-15 17:13:38', '1721034818514');
INSERT INTO `rocket_mq_fail_msg` VALUES ('471', '2650', 'InferenceResult', '3', 'AC1100030172008908D636A75F600007', '{\"task_no\": \"otRJPgYz\", \"model_name\": \"yolov8_safety_hat.engine\", \"img_file_name\": \"otRJPgYz_2024-07-11 14:42:18.512225.jpg\", \"bucket_name\": \"algcenter-yolov8-safety-hat\", \"cls_score\": \"{\'Safety Hat\': 0.491}\", \"alarm_time\": \"2024-07-11 14:42:18\"}', '0', '2927', '5877409', 'broker-a', '172.18.0.1', '2024-07-15 17:16:22', '1721034982977');
INSERT INTO `rocket_mq_fail_msg` VALUES ('472', '2650', 'InferenceResult', '3', 'AC1100030AE31000D9EF4CE3E3D00000', '{\"task_no\": \"tensor01\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"tensor01_2024-07-15_23:20:03_017838.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'traffic light\': 0.292, \'person\': 0.407, \'bus\': 0.372, \'truck\': 0.258}\", \"alarm_time\": \"2024-07-15 23:20:03\"}', '0', '2941', '145932723', 'broker-a', '192.168.2.245', '2024-07-16 11:21:43', '1721100103667');
INSERT INTO `rocket_mq_fail_msg` VALUES ('473', '2650', 'InferenceResult', '3', 'AC1100030AE31000D9EF4CE3E3D00000', '{\"task_no\": \"tensor01\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"tensor01_2024-07-15_23:20:03_017838.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'traffic light\': 0.292, \'person\': 0.407, \'bus\': 0.372, \'truck\': 0.258}\", \"alarm_time\": \"2024-07-15 23:20:03\"}', '0', '2942', '145933290', 'broker-a', '192.168.2.245', '2024-07-16 11:21:43', '1721100103673');
INSERT INTO `rocket_mq_fail_msg` VALUES ('474', '2650', 'InferenceResult', '3', 'AC1100030AE31000D9EF4CE3E3D00000', '{\"task_no\": \"tensor01\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"tensor01_2024-07-15_23:20:03_017838.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'traffic light\': 0.292, \'person\': 0.407, \'bus\': 0.372, \'truck\': 0.258}\", \"alarm_time\": \"2024-07-15 23:20:03\"}', '0', '2944', '145935166', 'broker-a', '192.168.2.245', '2024-07-16 11:21:43', '1721100103688');
INSERT INTO `rocket_mq_fail_msg` VALUES ('475', '2650', 'InferenceResult', '3', 'AC1100030AE31000D9EF4CE3E3D00000', '{\"task_no\": \"tensor01\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"tensor01_2024-07-15_23:20:03_017838.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'traffic light\': 0.292, \'person\': 0.407, \'bus\': 0.372, \'truck\': 0.258}\", \"alarm_time\": \"2024-07-15 23:20:03\"}', '0', '2943', '145933857', 'broker-a', '192.168.2.245', '2024-07-16 11:21:43', '1721100103674');
INSERT INTO `rocket_mq_fail_msg` VALUES ('476', '2650', 'InferenceResult', '3', 'AC1100030AE31000D9EF4CE3E3D00000', '{\"task_no\": \"tensor01\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"tensor01_2024-07-15_23:20:03_017838.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'traffic light\': 0.292, \'person\': 0.407, \'bus\': 0.372, \'truck\': 0.258}\", \"alarm_time\": \"2024-07-15 23:20:03\"}', '0', '2945', '145939083', 'broker-a', '192.168.2.245', '2024-07-16 11:21:43', '1721100103773');
INSERT INTO `rocket_mq_fail_msg` VALUES ('477', '2650', 'InferenceResult', '3', 'AC1100030AE31000D9EF4CE3E3D00000', '{\"task_no\": \"tensor01\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"tensor01_2024-07-15_23:20:03_017838.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'traffic light\': 0.292, \'person\': 0.407, \'bus\': 0.372, \'truck\': 0.258}\", \"alarm_time\": \"2024-07-15 23:20:03\"}', '0', '2946', '145939964', 'broker-a', '192.168.2.245', '2024-07-16 11:21:43', '1721100103785');
INSERT INTO `rocket_mq_fail_msg` VALUES ('478', '2650', 'InferenceResult', '3', 'AC1100030AE31000D9EF4CE3E3D00000', '{\"task_no\": \"tensor01\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"tensor01_2024-07-15_23:20:03_017838.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'traffic light\': 0.292, \'person\': 0.407, \'bus\': 0.372, \'truck\': 0.258}\", \"alarm_time\": \"2024-07-15 23:20:03\"}', '0', '2947', '145941099', 'broker-a', '192.168.2.245', '2024-07-16 11:21:43', '1721100103794');
INSERT INTO `rocket_mq_fail_msg` VALUES ('479', '2650', 'InferenceResult', '3', 'AC1100030AE31000D9EF4CE3E3D00000', '{\"task_no\": \"tensor01\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"tensor01_2024-07-15_23:20:03_017838.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'traffic light\': 0.292, \'person\': 0.407, \'bus\': 0.372, \'truck\': 0.258}\", \"alarm_time\": \"2024-07-15 23:20:03\"}', '0', '2948', '145942385', 'broker-a', '192.168.2.245', '2024-07-16 11:21:43', '1721100103837');
INSERT INTO `rocket_mq_fail_msg` VALUES ('480', '2650', 'InferenceResult', '3', 'AC1100030AE31000D9EF4CE3E3D00000', '{\"task_no\": \"tensor01\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"tensor01_2024-07-15_23:20:03_017838.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'traffic light\': 0.292, \'person\': 0.407, \'bus\': 0.372, \'truck\': 0.258}\", \"alarm_time\": \"2024-07-15 23:20:03\"}', '0', '2943', '145933857', 'broker-a', '192.168.2.245', '2024-07-16 11:21:43', '1721100103790');
INSERT INTO `rocket_mq_fail_msg` VALUES ('481', '2650', 'InferenceResult', '3', 'AC1100030AE31000D9EF4CE3E3D00000', '{\"task_no\": \"tensor01\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"tensor01_2024-07-15_23:20:03_017838.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'traffic light\': 0.292, \'person\': 0.407, \'bus\': 0.372, \'truck\': 0.258}\", \"alarm_time\": \"2024-07-15 23:20:03\"}', '0', '2941', '145932723', 'broker-a', '192.168.2.245', '2024-07-16 11:21:43', '1721100103728');
INSERT INTO `rocket_mq_fail_msg` VALUES ('482', '2650', 'InferenceResult', '3', 'AC1100030AE31000D9EF4CE3E3D00000', '{\"task_no\": \"tensor01\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"tensor01_2024-07-15_23:20:03_017838.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'traffic light\': 0.292, \'person\': 0.407, \'bus\': 0.372, \'truck\': 0.258}\", \"alarm_time\": \"2024-07-15 23:20:03\"}', '0', '2945', '145939083', 'broker-a', '192.168.2.245', '2024-07-16 11:21:43', '1721100103890');
INSERT INTO `rocket_mq_fail_msg` VALUES ('483', '2650', 'InferenceResult', '3', 'AC1100030AE31000D9EF4CE3E3D00000', '{\"task_no\": \"tensor01\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"tensor01_2024-07-15_23:20:03_017838.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'traffic light\': 0.292, \'person\': 0.407, \'bus\': 0.372, \'truck\': 0.258}\", \"alarm_time\": \"2024-07-15 23:20:03\"}', '0', '2942', '145933290', 'broker-a', '192.168.2.245', '2024-07-16 11:21:43', '1721100103736');
INSERT INTO `rocket_mq_fail_msg` VALUES ('484', '2650', 'InferenceResult', '3', 'AC1100030AE31000D9EF4CE3E3D00000', '{\"task_no\": \"tensor01\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"tensor01_2024-07-15_23:20:03_017838.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'traffic light\': 0.292, \'person\': 0.407, \'bus\': 0.372, \'truck\': 0.258}\", \"alarm_time\": \"2024-07-15 23:20:03\"}', '0', '2948', '145942385', 'broker-a', '192.168.2.245', '2024-07-16 11:21:43', '1721100103914');
INSERT INTO `rocket_mq_fail_msg` VALUES ('485', '2650', 'InferenceResult', '3', 'AC1100030AE31000D9EF4CE3E3D00000', '{\"task_no\": \"tensor01\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"tensor01_2024-07-15_23:20:03_017838.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'traffic light\': 0.292, \'person\': 0.407, \'bus\': 0.372, \'truck\': 0.258}\", \"alarm_time\": \"2024-07-15 23:20:03\"}', '0', '2944', '145935166', 'broker-a', '192.168.2.245', '2024-07-16 11:21:43', '1721100103812');
INSERT INTO `rocket_mq_fail_msg` VALUES ('486', '2650', 'InferenceResult', '3', 'AC1100030AE31000D9EF4CE3E3D00000', '{\"task_no\": \"tensor01\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"tensor01_2024-07-15_23:20:03_017838.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'traffic light\': 0.292, \'person\': 0.407, \'bus\': 0.372, \'truck\': 0.258}\", \"alarm_time\": \"2024-07-15 23:20:03\"}', '0', '2946', '145939964', 'broker-a', '192.168.2.245', '2024-07-16 11:21:43', '1721100103897');
INSERT INTO `rocket_mq_fail_msg` VALUES ('487', '2650', 'InferenceResult', '3', 'AC1100030AE31000D9EF4CE3E3D00000', '{\"task_no\": \"tensor01\", \"model_name\": \"yolov8n.engine\", \"img_file_name\": \"tensor01_2024-07-15_23:20:03_017838.jpg\", \"bucket_name\": \"algcenter-yolov8n\", \"cls_score\": \"{\'traffic light\': 0.292, \'person\': 0.407, \'bus\': 0.372, \'truck\': 0.258}\", \"alarm_time\": \"2024-07-15 23:20:03\"}', '0', '2947', '145941099', 'broker-a', '192.168.2.245', '2024-07-16 11:21:43', '1721100103900');

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
INSERT INTO `role` VALUES ('1501859873070829568', '一期管理员', 'Admin', '3', '17', '1', '0', '1646906271425', '1501815782786256896');
INSERT INTO `role` VALUES ('1503682070533906432', '园区超级管理员', 'Admin', '1', '17', '1', '0', '1647385301000', '1501815782786256895');
INSERT INTO `role` VALUES ('1504354687226007552', '二期管理员', 'Admin', '5', '17', '1', '0', '1647501081461', '1501815782786256897');
INSERT INTO `role` VALUES ('1547845254751744000', '国贸物流', 'Admin', '1', '17', '1', '0', '1657870040944', '1500354392018309001');
INSERT INTO `role` VALUES ('1552934632142462976', 'xuanzhong', 'xuanzhong', '1', '17', '1', '0', '1659083442821', '1552564427771924480');
INSERT INTO `role` VALUES ('1558031718048112640', '普通管理员', 'ordinary', '1', '17', '1', '0', '1660298682917', '1500354392018309001');
INSERT INTO `role` VALUES ('1559784873417048064', '园区', 'Admin', '1', '17', '1', '0', '1660716667729', '1500354392018309001');
INSERT INTO `role` VALUES ('1686276112903884800', '欧洲产业城管理员', 'ozcyc', '1', '17', '1', '0', '1690874528737', '1560215296790056666');

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'zhangsan', '张三', '123', '男', '888@qq.com', '呼伦贝尔');
INSERT INTO `sys_user` VALUES ('2', 'lisi', '李四', '123', '男', '777@qq.com', '北京市');
INSERT INTO `sys_user` VALUES ('3', 'lisi1', '李四1', '123', '男', '777@qq.com', '北京市');
INSERT INTO `sys_user` VALUES ('5', 'lisi3', '李四', '123', '男', '777@qq.com', '北京市');
INSERT INTO `sys_user` VALUES ('6', 'lisi4', '李四', '123', '男', '777@qq.com', '北京市');
INSERT INTO `sys_user` VALUES ('7', 'lisi5', '李四', '123', '男', '777@qq.com', '北京市');
INSERT INTO `sys_user` VALUES ('8', 'lisi6', '李四', '123', '男', '777@qq.com', '北京市');
INSERT INTO `sys_user` VALUES ('9', 'lisi7', '李四', '123', '男', '777@qq.com', '北京市');
INSERT INTO `sys_user` VALUES ('10', 'lisi8', '李四', '123', '男', '777@qq.com', '北京市');
INSERT INTO `sys_user` VALUES ('11', 'lisi9', '李四', '123', '男', '777@qq.com', '北京市');
INSERT INTO `sys_user` VALUES ('17', 'zhangsan1', '张三1', '123', '男', '888@qq.com', '呼伦贝尔');
INSERT INTO `sys_user` VALUES ('18', 'zhangsan2', '张三2', '123', '男', '888@qq.com', '呼伦贝尔');
INSERT INTO `sys_user` VALUES ('19', 'zhangsan3', '张三3', '123', '男', '888@qq.com', '呼伦贝尔');
INSERT INTO `sys_user` VALUES ('20', 'zhangsan4', '张三4', '123', '男', '888@qq.com', '呼伦贝尔');
INSERT INTO `sys_user` VALUES ('21', 'zhangsan5', '张三5', '123', '男', '888@qq.com', '呼伦贝尔');
INSERT INTO `sys_user` VALUES ('22', 'zhangsan6', '张三6', '123', '男', '888@qq.com', '呼伦贝尔');
INSERT INTO `sys_user` VALUES ('23', 'zhangsan7', '张三7', '123', '男', '888@qq.com', '呼伦贝尔');
INSERT INTO `sys_user` VALUES ('24', 'zhangsan8', '张三8', '123', '男', '888@qq.com', '呼伦贝尔');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=1560215298107067110 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '超级管理员', 'admin', 'd9a3b7d666d633d3a3d91bd8bbe4e1f74d8aff6180e883978cf7e4ac9c87ba6d', '1', null, '15208492742', null, 'SuperAdmin', '1501859873070829561', '8', '1', '0', '1645431809000', '192.168.1.124', '1645431809000');
INSERT INTO `user` VALUES ('2', '张三', 'zhangsan', 'a6d5497af78e701e9e8e7c81fc3647f7c58c7a417ae6a7fd6dca466172366c59', '1', null, '15208492741', null, 'Apply_User', '1501859873070829562', '7', '1', '0', '1645431809000', '192.168.1.124', '1645431809000');
INSERT INTO `user` VALUES ('3', '王五', 'wangwu', 'bf3d164d5baaa1c241babba322863a2074dd28c4036282ba976869180f5393de', '1', null, '15208492743', null, 'Admin', '1501859873070829568', '8', '1', '0', '1645431809000', '192.168.1.124', '1645431809000');

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
INSERT INTO `user_role` VALUES ('4', '4', '1501859873070829568', null);
INSERT INTO `user_role` VALUES ('6', '1502534704665731072', '1501859873070829562', '1647067163994');
INSERT INTO `user_role` VALUES ('11', '1503253905768468480', '1503682070533906432', '1647340767850');
INSERT INTO `user_role` VALUES ('13', '1504357149622194176', '1504354687226007552', '1647501668731');
INSERT INTO `user_role` VALUES ('14', '1503192297125998592', '1503682070533906432', '1648030402350');
INSERT INTO `user_role` VALUES ('16', '1541975861128663040', '1503682070533906432', '1656470668476');
INSERT INTO `user_role` VALUES ('20', '1549294191769587712', '1503682070533906432', '1658215494469');
INSERT INTO `user_role` VALUES ('22', '1551754677547225088', '1503682070533906432', '1658802119992');
INSERT INTO `user_role` VALUES ('28', '1500603377809204001', '1547845254751744000', '1659693613567');
INSERT INTO `user_role` VALUES ('31', '1555492843320791040', '1559784873417048064', '1660716685914');
INSERT INTO `user_role` VALUES ('32', '1547845743287496102', '1559784873417048064', '1660716692002');
INSERT INTO `user_role` VALUES ('34', '1559808688394141696', '1559784873417048064', '1660722472074');
INSERT INTO `user_role` VALUES ('36', '1560208610566160384', '1559784873417048064', '1660817778332');
INSERT INTO `user_role` VALUES ('38', '1560211743447007232', '1559784873417048064', '1660818474520');
INSERT INTO `user_role` VALUES ('42', '1560215298107064320', '1559784873417048064', '1660819322918');
INSERT INTO `user_role` VALUES ('43', '1560214852789420032', '1559784873417048064', '1660819327968');
INSERT INTO `user_role` VALUES ('44', '1560213590639460352', '1559784873417048064', '1660819333860');
INSERT INTO `user_role` VALUES ('45', '1552564430003294208', '1559784873417048064', '1660821021189');
INSERT INTO `user_role` VALUES ('46', '1555497622502944768', '1559784873417048064', '1660822443740');
INSERT INTO `user_role` VALUES ('49', '1560215298107064388', '1559784873417048064', '1660819322918');
INSERT INTO `user_role` VALUES ('55', '1560215298107066901', '1547845254751744000', '1660819322918');
INSERT INTO `user_role` VALUES ('56', '1560215298107067109', '1547845254751744000', '1660819322918');
INSERT INTO `user_role` VALUES ('58', '1503253905768468481', '1501859873070829568', '1686014952717');
INSERT INTO `user_role` VALUES ('60', '1503253905768468482', '1504354687226007552', '1686018638980');
INSERT INTO `user_role` VALUES ('61', '1560215298107061111', '1686276112903884800', '1690874710949');
