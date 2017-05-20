/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50716
Source Host           : 127.0.0.1:3306
Source Database       : good_manage

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-05-17 23:31:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `area`
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(10) DEFAULT NULL,
  `area_name` varchar(255) DEFAULT NULL,
  `is_hot` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=346 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of area
-- ----------------------------
INSERT INTO `area` VALUES ('1', '0', '北京市', '0');
INSERT INTO `area` VALUES ('2', '0', '上海市', '0');
INSERT INTO `area` VALUES ('3', '0', '天津市', '0');
INSERT INTO `area` VALUES ('4', '0', '重庆市', '0');
INSERT INTO `area` VALUES ('5', '0', '黑龙江省', '0');
INSERT INTO `area` VALUES ('6', '0', '吉林省', '0');
INSERT INTO `area` VALUES ('7', '0', '辽宁省', '0');
INSERT INTO `area` VALUES ('8', '0', '山东省', '0');
INSERT INTO `area` VALUES ('9', '0', '山西省', '0');
INSERT INTO `area` VALUES ('10', '0', '陕西省', '0');
INSERT INTO `area` VALUES ('11', '0', '河北省', '0');
INSERT INTO `area` VALUES ('12', '0', '河南省', '0');
INSERT INTO `area` VALUES ('13', '0', '湖北省', '0');
INSERT INTO `area` VALUES ('14', '0', '湖南省', '0');
INSERT INTO `area` VALUES ('15', '0', '海南省', '0');
INSERT INTO `area` VALUES ('16', '0', '江苏省', '0');
INSERT INTO `area` VALUES ('17', '0', '江西省', '0');
INSERT INTO `area` VALUES ('18', '0', '广东省', '0');
INSERT INTO `area` VALUES ('19', '0', '广西省', '0');
INSERT INTO `area` VALUES ('20', '0', '云南省', '0');
INSERT INTO `area` VALUES ('21', '0', '贵州省', '0');
INSERT INTO `area` VALUES ('22', '0', '四川省', '0');
INSERT INTO `area` VALUES ('23', '0', '内蒙古自治区', '0');
INSERT INTO `area` VALUES ('24', '0', '宁夏回族自治区', '0');
INSERT INTO `area` VALUES ('25', '0', '甘肃省', '0');
INSERT INTO `area` VALUES ('26', '0', '青海省', '0');
INSERT INTO `area` VALUES ('27', '0', '西藏自治区', '0');
INSERT INTO `area` VALUES ('28', '0', '新疆自治区', '0');
INSERT INTO `area` VALUES ('29', '0', '安徽省', '0');
INSERT INTO `area` VALUES ('30', '0', '浙江省', '0');
INSERT INTO `area` VALUES ('31', '0', '福建省', '0');
INSERT INTO `area` VALUES ('32', '0', '台湾省', '0');
INSERT INTO `area` VALUES ('33', '0', '香港', '0');
INSERT INTO `area` VALUES ('34', '0', '澳门', '0');
INSERT INTO `area` VALUES ('35', '1', '北京市', '1');
INSERT INTO `area` VALUES ('36', '2', '上海市', '1');
INSERT INTO `area` VALUES ('37', '3', '天津市', '0');
INSERT INTO `area` VALUES ('38', '4', '重庆市', '0');
INSERT INTO `area` VALUES ('39', '11', '石家庄市', '0');
INSERT INTO `area` VALUES ('40', '11', '张家口市', '0');
INSERT INTO `area` VALUES ('41', '11', '承德市', '0');
INSERT INTO `area` VALUES ('42', '11', '唐山市', '0');
INSERT INTO `area` VALUES ('43', '11', '秦皇岛市', '0');
INSERT INTO `area` VALUES ('44', '11', '廊坊市', '0');
INSERT INTO `area` VALUES ('45', '11', '保定市', '0');
INSERT INTO `area` VALUES ('46', '11', '沧州市', '0');
INSERT INTO `area` VALUES ('47', '11', '衡水市', '0');
INSERT INTO `area` VALUES ('48', '11', '邢台市', '0');
INSERT INTO `area` VALUES ('49', '11', '邯郸市', '0');
INSERT INTO `area` VALUES ('50', '9', '太原市', '0');
INSERT INTO `area` VALUES ('51', '9', '大同市', '0');
INSERT INTO `area` VALUES ('52', '9', '朔州市', '0');
INSERT INTO `area` VALUES ('53', '9', '忻州市', '0');
INSERT INTO `area` VALUES ('54', '9', '阳泉市', '0');
INSERT INTO `area` VALUES ('55', '9', '晋中市', '0');
INSERT INTO `area` VALUES ('56', '9', '吕梁市', '0');
INSERT INTO `area` VALUES ('57', '9', '长治市', '0');
INSERT INTO `area` VALUES ('58', '9', '临汾市', '0');
INSERT INTO `area` VALUES ('59', '9', '晋城市', '0');
INSERT INTO `area` VALUES ('60', '9', '运城市', '0');
INSERT INTO `area` VALUES ('61', '23', '呼和浩特市', '0');
INSERT INTO `area` VALUES ('62', '23', '呼伦贝尔市', '0');
INSERT INTO `area` VALUES ('63', '23', '通辽市', '0');
INSERT INTO `area` VALUES ('64', '23', '赤峰市', '0');
INSERT INTO `area` VALUES ('65', '23', '巴彦淖尔市', '0');
INSERT INTO `area` VALUES ('66', '23', '乌兰察布市', '0');
INSERT INTO `area` VALUES ('67', '23', '包头市', '0');
INSERT INTO `area` VALUES ('68', '23', '鄂尔多斯市', '0');
INSERT INTO `area` VALUES ('69', '23', '乌海市', '0');
INSERT INTO `area` VALUES ('70', '5', '哈尔滨市', '1');
INSERT INTO `area` VALUES ('71', '5', '黑河市', '0');
INSERT INTO `area` VALUES ('72', '5', '伊春市', '0');
INSERT INTO `area` VALUES ('73', '5', '齐齐哈尔市', '0');
INSERT INTO `area` VALUES ('74', '5', '鹤岗市', '0');
INSERT INTO `area` VALUES ('75', '5', '佳木斯市', '0');
INSERT INTO `area` VALUES ('76', '5', '双鸭山市', '0');
INSERT INTO `area` VALUES ('77', '5', '绥化市', '0');
INSERT INTO `area` VALUES ('78', '5', '大庆市', '0');
INSERT INTO `area` VALUES ('79', '5', '七台河市', '0');
INSERT INTO `area` VALUES ('80', '5', '鸡西市', '0');
INSERT INTO `area` VALUES ('81', '5', '牡丹江市', '0');
INSERT INTO `area` VALUES ('82', '6', '长春市', '0');
INSERT INTO `area` VALUES ('83', '6', '白城市', '0');
INSERT INTO `area` VALUES ('84', '6', '松原市', '0');
INSERT INTO `area` VALUES ('85', '6', '吉林市', '0');
INSERT INTO `area` VALUES ('86', '6', '四平市', '0');
INSERT INTO `area` VALUES ('87', '6', '辽源市', '0');
INSERT INTO `area` VALUES ('88', '6', '白山市', '0');
INSERT INTO `area` VALUES ('89', '6', '通化市', '0');
INSERT INTO `area` VALUES ('90', '6', '延吉市', '0');
INSERT INTO `area` VALUES ('91', '7', '沈阳市', '0');
INSERT INTO `area` VALUES ('92', '7', '铁岭市', '0');
INSERT INTO `area` VALUES ('93', '7', '阜新市', '0');
INSERT INTO `area` VALUES ('94', '7', '抚顺市', '0');
INSERT INTO `area` VALUES ('95', '7', '朝阳市', '0');
INSERT INTO `area` VALUES ('96', '7', '本溪市', '0');
INSERT INTO `area` VALUES ('97', '7', '辽阳市', '0');
INSERT INTO `area` VALUES ('98', '7', '鞍山市', '0');
INSERT INTO `area` VALUES ('99', '7', '盘锦市', '0');
INSERT INTO `area` VALUES ('100', '7', '锦州市', '0');
INSERT INTO `area` VALUES ('101', '7', '葫芦岛市', '0');
INSERT INTO `area` VALUES ('102', '7', '营口市', '0');
INSERT INTO `area` VALUES ('103', '7', '丹东市', '0');
INSERT INTO `area` VALUES ('104', '7', '大连市', '0');
INSERT INTO `area` VALUES ('105', '16', '南京市', '1');
INSERT INTO `area` VALUES ('106', '16', '连云港市', '0');
INSERT INTO `area` VALUES ('107', '16', '徐州市', '0');
INSERT INTO `area` VALUES ('108', '16', '宿迁市', '0');
INSERT INTO `area` VALUES ('109', '16', '淮安市', '0');
INSERT INTO `area` VALUES ('110', '16', '盐城市', '0');
INSERT INTO `area` VALUES ('111', '16', '泰州市', '0');
INSERT INTO `area` VALUES ('112', '16', '扬州市', '0');
INSERT INTO `area` VALUES ('113', '16', '镇江市', '0');
INSERT INTO `area` VALUES ('114', '16', '南通市', '0');
INSERT INTO `area` VALUES ('115', '16', '常州市', '0');
INSERT INTO `area` VALUES ('116', '16', '无锡市', '0');
INSERT INTO `area` VALUES ('117', '16', '苏州市', '0');
INSERT INTO `area` VALUES ('118', '30', '杭州市', '1');
INSERT INTO `area` VALUES ('119', '30', '湖州市', '0');
INSERT INTO `area` VALUES ('120', '30', '嘉兴市', '0');
INSERT INTO `area` VALUES ('121', '30', '绍兴市', '0');
INSERT INTO `area` VALUES ('122', '30', '舟山市', '0');
INSERT INTO `area` VALUES ('123', '30', '宁波市', '0');
INSERT INTO `area` VALUES ('124', '30', '金华市', '0');
INSERT INTO `area` VALUES ('125', '30', '衢州市', '0');
INSERT INTO `area` VALUES ('126', '30', '台州市', '0');
INSERT INTO `area` VALUES ('127', '30', '丽水市', '0');
INSERT INTO `area` VALUES ('128', '30', '温州市', '0');
INSERT INTO `area` VALUES ('129', '29', '合肥市', '0');
INSERT INTO `area` VALUES ('130', '29', '淮北市', '0');
INSERT INTO `area` VALUES ('131', '29', '亳州市', '0');
INSERT INTO `area` VALUES ('132', '29', '宿州市', '0');
INSERT INTO `area` VALUES ('133', '29', '蚌埠市', '0');
INSERT INTO `area` VALUES ('134', '29', '阜阳市', '0');
INSERT INTO `area` VALUES ('135', '29', '淮南市', '0');
INSERT INTO `area` VALUES ('136', '29', '滁州市', '0');
INSERT INTO `area` VALUES ('137', '29', '六安市', '0');
INSERT INTO `area` VALUES ('138', '29', '马鞍山市', '0');
INSERT INTO `area` VALUES ('139', '29', '芜湖市', '0');
INSERT INTO `area` VALUES ('140', '29', '宣城市', '0');
INSERT INTO `area` VALUES ('141', '29', '铜陵市', '0');
INSERT INTO `area` VALUES ('142', '29', '池州市', '0');
INSERT INTO `area` VALUES ('143', '29', '安庆市', '0');
INSERT INTO `area` VALUES ('144', '29', '黄山市', '0');
INSERT INTO `area` VALUES ('145', '31', '福州市', '0');
INSERT INTO `area` VALUES ('146', '31', '宁德市', '0');
INSERT INTO `area` VALUES ('147', '31', '南平市', '0');
INSERT INTO `area` VALUES ('148', '31', '三明市', '0');
INSERT INTO `area` VALUES ('149', '31', '莆田市', '0');
INSERT INTO `area` VALUES ('150', '31', '龙岩市', '0');
INSERT INTO `area` VALUES ('151', '31', '泉州市', '0');
INSERT INTO `area` VALUES ('152', '31', '漳州市', '0');
INSERT INTO `area` VALUES ('153', '31', '厦门市', '1');
INSERT INTO `area` VALUES ('154', '17', '南昌市', '0');
INSERT INTO `area` VALUES ('155', '17', '九江市', '0');
INSERT INTO `area` VALUES ('156', '17', '景德镇市', '0');
INSERT INTO `area` VALUES ('157', '17', '上饶市', '0');
INSERT INTO `area` VALUES ('158', '17', '鹰潭市', '0');
INSERT INTO `area` VALUES ('159', '17', '抚州市', '0');
INSERT INTO `area` VALUES ('160', '17', '新余市', '0');
INSERT INTO `area` VALUES ('161', '17', '宜春市', '0');
INSERT INTO `area` VALUES ('162', '17', '萍乡市', '0');
INSERT INTO `area` VALUES ('163', '17', '吉安市', '0');
INSERT INTO `area` VALUES ('164', '17', '赣州市', '0');
INSERT INTO `area` VALUES ('165', '8', '济南市', '0');
INSERT INTO `area` VALUES ('166', '8', '德州市', '0');
INSERT INTO `area` VALUES ('167', '8', '滨州市', '0');
INSERT INTO `area` VALUES ('168', '8', '东营市', '0');
INSERT INTO `area` VALUES ('169', '8', '烟台市', '0');
INSERT INTO `area` VALUES ('170', '8', '威海市', '0');
INSERT INTO `area` VALUES ('171', '8', '淄博市', '0');
INSERT INTO `area` VALUES ('172', '8', '潍坊市', '0');
INSERT INTO `area` VALUES ('173', '8', '聊城市', '0');
INSERT INTO `area` VALUES ('174', '8', '泰安市', '0');
INSERT INTO `area` VALUES ('175', '8', '莱芜市', '0');
INSERT INTO `area` VALUES ('176', '8', '青岛市', '0');
INSERT INTO `area` VALUES ('177', '8', '日照市', '0');
INSERT INTO `area` VALUES ('178', '8', '济宁市', '0');
INSERT INTO `area` VALUES ('179', '8', '菏泽市', '0');
INSERT INTO `area` VALUES ('180', '8', '临沂市', '0');
INSERT INTO `area` VALUES ('181', '8', '枣庄市', '0');
INSERT INTO `area` VALUES ('182', '12', '郑州市', '0');
INSERT INTO `area` VALUES ('183', '12', '安阳市', '0');
INSERT INTO `area` VALUES ('184', '12', '鹤壁市', '0');
INSERT INTO `area` VALUES ('185', '12', '濮阳市', '0');
INSERT INTO `area` VALUES ('186', '12', '新乡市', '0');
INSERT INTO `area` VALUES ('187', '12', '焦作市', '0');
INSERT INTO `area` VALUES ('188', '12', '三门峡市', '0');
INSERT INTO `area` VALUES ('189', '12', '开封市', '0');
INSERT INTO `area` VALUES ('190', '12', '洛阳市', '0');
INSERT INTO `area` VALUES ('191', '12', '商丘市', '0');
INSERT INTO `area` VALUES ('192', '12', '许昌市', '0');
INSERT INTO `area` VALUES ('193', '12', '平顶山市', '0');
INSERT INTO `area` VALUES ('194', '12', '周口市', '0');
INSERT INTO `area` VALUES ('195', '12', '漯河市', '0');
INSERT INTO `area` VALUES ('196', '12', '南阳市', '0');
INSERT INTO `area` VALUES ('197', '12', '驻马店市', '0');
INSERT INTO `area` VALUES ('198', '12', '信阳市', '0');
INSERT INTO `area` VALUES ('199', '12', '济源市', '0');
INSERT INTO `area` VALUES ('200', '13', '武汉市', '1');
INSERT INTO `area` VALUES ('201', '13', '十堰市', '0');
INSERT INTO `area` VALUES ('202', '13', '襄樊市', '0');
INSERT INTO `area` VALUES ('203', '13', '随州市', '0');
INSERT INTO `area` VALUES ('204', '13', '荆门市', '0');
INSERT INTO `area` VALUES ('205', '13', '孝感市', '0');
INSERT INTO `area` VALUES ('206', '13', '宜昌市', '0');
INSERT INTO `area` VALUES ('207', '13', '黄冈市', '0');
INSERT INTO `area` VALUES ('208', '13', '鄂州市', '0');
INSERT INTO `area` VALUES ('209', '13', '荆州市', '0');
INSERT INTO `area` VALUES ('210', '13', '黄石市', '0');
INSERT INTO `area` VALUES ('211', '13', '咸宁市', '0');
INSERT INTO `area` VALUES ('212', '13', '恩施市', '0');
INSERT INTO `area` VALUES ('213', '14', '长沙市', '0');
INSERT INTO `area` VALUES ('214', '14', '岳阳市', '0');
INSERT INTO `area` VALUES ('215', '14', '张家界市', '0');
INSERT INTO `area` VALUES ('216', '14', '常德市', '0');
INSERT INTO `area` VALUES ('217', '14', '益阳市', '0');
INSERT INTO `area` VALUES ('218', '14', '湘潭市', '0');
INSERT INTO `area` VALUES ('219', '14', '株洲市', '0');
INSERT INTO `area` VALUES ('220', '14', '娄底市', '0');
INSERT INTO `area` VALUES ('221', '14', '怀化市', '0');
INSERT INTO `area` VALUES ('222', '14', '邵阳市', '0');
INSERT INTO `area` VALUES ('223', '14', '衡阳市', '0');
INSERT INTO `area` VALUES ('224', '14', '永州市', '0');
INSERT INTO `area` VALUES ('225', '14', '郴州市', '0');
INSERT INTO `area` VALUES ('226', '18', '广州市', '1');
INSERT INTO `area` VALUES ('227', '18', '韶关市', '0');
INSERT INTO `area` VALUES ('228', '18', '梅州市', '0');
INSERT INTO `area` VALUES ('229', '18', '河源市', '0');
INSERT INTO `area` VALUES ('230', '18', '清远市', '0');
INSERT INTO `area` VALUES ('231', '18', '潮州市', '0');
INSERT INTO `area` VALUES ('232', '18', '揭阳市', '0');
INSERT INTO `area` VALUES ('233', '18', '汕头市', '0');
INSERT INTO `area` VALUES ('234', '18', '肇庆市', '0');
INSERT INTO `area` VALUES ('235', '18', '惠州市', '0');
INSERT INTO `area` VALUES ('236', '18', '佛山市', '0');
INSERT INTO `area` VALUES ('237', '18', '东莞市', '0');
INSERT INTO `area` VALUES ('238', '18', '云浮市', '0');
INSERT INTO `area` VALUES ('239', '18', '汕尾市', '0');
INSERT INTO `area` VALUES ('240', '18', '江门市', '0');
INSERT INTO `area` VALUES ('241', '18', '中山市', '0');
INSERT INTO `area` VALUES ('242', '18', '深圳市', '1');
INSERT INTO `area` VALUES ('243', '18', '珠海市', '0');
INSERT INTO `area` VALUES ('244', '18', '阳江市', '0');
INSERT INTO `area` VALUES ('245', '18', '茂名市', '0');
INSERT INTO `area` VALUES ('246', '18', '湛江市', '0');
INSERT INTO `area` VALUES ('247', '19', '南宁市', '0');
INSERT INTO `area` VALUES ('248', '19', '桂林市', '0');
INSERT INTO `area` VALUES ('249', '19', '河池市', '0');
INSERT INTO `area` VALUES ('250', '19', '贺州市', '0');
INSERT INTO `area` VALUES ('251', '19', '柳州市', '0');
INSERT INTO `area` VALUES ('252', '19', '百色市', '0');
INSERT INTO `area` VALUES ('253', '19', '来宾市', '0');
INSERT INTO `area` VALUES ('254', '19', '梧州市', '0');
INSERT INTO `area` VALUES ('255', '19', '贵港市', '0');
INSERT INTO `area` VALUES ('256', '19', '玉林市', '0');
INSERT INTO `area` VALUES ('257', '19', '崇左市', '0');
INSERT INTO `area` VALUES ('258', '19', '钦州市', '0');
INSERT INTO `area` VALUES ('259', '19', '防城港市', '0');
INSERT INTO `area` VALUES ('260', '19', '北海市', '0');
INSERT INTO `area` VALUES ('261', '15', '海口市', '0');
INSERT INTO `area` VALUES ('262', '15', '三沙市', '0');
INSERT INTO `area` VALUES ('263', '15', '三亚市', '0');
INSERT INTO `area` VALUES ('264', '22', '成都市', '1');
INSERT INTO `area` VALUES ('265', '22', '广元市', '0');
INSERT INTO `area` VALUES ('266', '22', '巴中市', '0');
INSERT INTO `area` VALUES ('267', '22', '绵阳市', '0');
INSERT INTO `area` VALUES ('268', '22', '德阳市', '0');
INSERT INTO `area` VALUES ('269', '22', '达州市', '0');
INSERT INTO `area` VALUES ('270', '22', '南充市', '0');
INSERT INTO `area` VALUES ('271', '22', '遂宁市', '0');
INSERT INTO `area` VALUES ('272', '22', '广安市', '0');
INSERT INTO `area` VALUES ('273', '22', '资阳市', '0');
INSERT INTO `area` VALUES ('274', '22', '眉山市', '0');
INSERT INTO `area` VALUES ('275', '22', '雅安市', '0');
INSERT INTO `area` VALUES ('276', '22', '内江市', '0');
INSERT INTO `area` VALUES ('277', '22', '乐山市', '0');
INSERT INTO `area` VALUES ('278', '22', '自贡市', '0');
INSERT INTO `area` VALUES ('279', '22', '泸州市', '0');
INSERT INTO `area` VALUES ('280', '22', '宜宾市', '0');
INSERT INTO `area` VALUES ('281', '22', '攀枝花市', '0');
INSERT INTO `area` VALUES ('282', '21', '贵阳市', '0');
INSERT INTO `area` VALUES ('283', '21', '遵义市', '0');
INSERT INTO `area` VALUES ('284', '21', '铜仁市', '0');
INSERT INTO `area` VALUES ('285', '21', '毕节市', '0');
INSERT INTO `area` VALUES ('286', '21', '六盘水市', '0');
INSERT INTO `area` VALUES ('287', '21', '安顺市', '0');
INSERT INTO `area` VALUES ('288', '20', '昆明市', '0');
INSERT INTO `area` VALUES ('289', '20', '昭通市', '0');
INSERT INTO `area` VALUES ('290', '20', '丽江市', '0');
INSERT INTO `area` VALUES ('291', '20', '曲靖市', '0');
INSERT INTO `area` VALUES ('292', '20', '保山市', '0');
INSERT INTO `area` VALUES ('293', '20', '玉溪市', '0');
INSERT INTO `area` VALUES ('294', '20', '临沧市', '0');
INSERT INTO `area` VALUES ('295', '20', '普洱市', '0');
INSERT INTO `area` VALUES ('296', '27', '拉萨市', '0');
INSERT INTO `area` VALUES ('297', '27', '昌都市', '0');
INSERT INTO `area` VALUES ('298', '27', '日喀则市', '0');
INSERT INTO `area` VALUES ('299', '10', '西安市', '1');
INSERT INTO `area` VALUES ('300', '10', '榆林市', '0');
INSERT INTO `area` VALUES ('301', '10', '延安市', '0');
INSERT INTO `area` VALUES ('302', '10', '铜川市', '0');
INSERT INTO `area` VALUES ('303', '10', '渭南市', '0');
INSERT INTO `area` VALUES ('304', '10', '宝鸡市', '0');
INSERT INTO `area` VALUES ('305', '10', '咸阳市', '0');
INSERT INTO `area` VALUES ('306', '10', '商洛市', '0');
INSERT INTO `area` VALUES ('307', '10', '汉中市', '0');
INSERT INTO `area` VALUES ('308', '10', '安康市', '0');
INSERT INTO `area` VALUES ('309', '25', '兰州市', '0');
INSERT INTO `area` VALUES ('310', '25', '嘉峪关市', '0');
INSERT INTO `area` VALUES ('311', '25', '酒泉市', '0');
INSERT INTO `area` VALUES ('312', '25', '张掖市', '0');
INSERT INTO `area` VALUES ('313', '25', '金昌市', '0');
INSERT INTO `area` VALUES ('314', '25', '武威市', '0');
INSERT INTO `area` VALUES ('315', '25', '白银市', '0');
INSERT INTO `area` VALUES ('316', '25', '庆阳市', '0');
INSERT INTO `area` VALUES ('317', '25', '平凉市', '0');
INSERT INTO `area` VALUES ('318', '25', '定西市', '0');
INSERT INTO `area` VALUES ('319', '25', '天水市', '0');
INSERT INTO `area` VALUES ('320', '25', '陇南市', '0');
INSERT INTO `area` VALUES ('321', '26', '海东市', '0');
INSERT INTO `area` VALUES ('322', '26', '西宁市', '0');
INSERT INTO `area` VALUES ('323', '24', '银川市', '0');
INSERT INTO `area` VALUES ('324', '24', '石嘴山市', '0');
INSERT INTO `area` VALUES ('325', '24', '吴忠市', '0');
INSERT INTO `area` VALUES ('326', '24', '中卫市', '0');
INSERT INTO `area` VALUES ('327', '24', '固原市', '0');
INSERT INTO `area` VALUES ('328', '28', '乌鲁木齐市', '0');
INSERT INTO `area` VALUES ('329', '28', '克拉玛依市', '0');
INSERT INTO `area` VALUES ('330', '28', '阿克苏市', '0');
INSERT INTO `area` VALUES ('331', '28', '阿图什市', '0');
INSERT INTO `area` VALUES ('332', '28', '阿勒泰', '0');
INSERT INTO `area` VALUES ('333', '28', '阿拉尔市', '0');
INSERT INTO `area` VALUES ('334', '28', '博乐市', '0');
INSERT INTO `area` VALUES ('335', '28', '昌吉市', '0');
INSERT INTO `area` VALUES ('336', '28', '哈密市', '0');
INSERT INTO `area` VALUES ('337', '28', '和田市', '0');
INSERT INTO `area` VALUES ('338', '28', '喀什市', '0');
INSERT INTO `area` VALUES ('339', '28', '库尔勒市', '0');
INSERT INTO `area` VALUES ('340', '28', '石河子市', '0');
INSERT INTO `area` VALUES ('341', '28', '吐鲁番', '0');
INSERT INTO `area` VALUES ('342', '28', '伊宁市', '0');
INSERT INTO `area` VALUES ('343', '32', '台湾省', '0');
INSERT INTO `area` VALUES ('344', '33', '香港', '0');
INSERT INTO `area` VALUES ('345', '34', '澳门', '0');

-- ----------------------------
-- Table structure for `content`
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content_cat_id` bigint(20) DEFAULT NULL COMMENT '内容分类ID',
  `img` varchar(300) DEFAULT NULL COMMENT '图片地址',
  `title` varchar(200) DEFAULT NULL COMMENT '内容标题',
  `sub_title` varchar(200) DEFAULT NULL COMMENT '子标题',
  `title_desc` varchar(2000) DEFAULT NULL COMMENT '标题描述',
  `content` text COMMENT '内容',
  `url` varchar(500) DEFAULT NULL COMMENT '链接地址',
  `time` datetime DEFAULT NULL COMMENT '时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of content
-- ----------------------------

-- ----------------------------
-- Table structure for `content_cat`
-- ----------------------------
DROP TABLE IF EXISTS `content_cat`;
CREATE TABLE `content_cat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `name` varchar(168) DEFAULT NULL COMMENT '分类名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父分类ID,ID=0时，代表的是一级的类目',
  `parent_name` varchar(168) DEFAULT NULL COMMENT '父分类的名称',
  `sort` int(11) DEFAULT NULL COMMENT '排列序号,表示同级分类的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数',
  `is_parent` bit(1) DEFAULT NULL COMMENT '该分类是否为父类目，1为true，0为false',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of content_cat
-- ----------------------------
INSERT INTO `content_cat` VALUES ('1', '内容管理', '0', null, '1', '', '2017-04-20 09:17:49', '2017-04-20 09:17:51');
INSERT INTO `content_cat` VALUES ('2', '新闻中心', '1', '内容管理', '1', '', '2017-04-20 09:19:51', '2017-04-20 09:19:53');
INSERT INTO `content_cat` VALUES ('3', '最新资讯', '2', '新闻中心', '1', '', '2017-04-20 09:20:58', '2017-04-20 09:21:01');
INSERT INTO `content_cat` VALUES ('4', '媒体报道', '2', '新闻中心', '2', '', '2017-04-20 09:21:22', '2017-04-20 09:21:24');
INSERT INTO `content_cat` VALUES ('5', '新闻专题', '2', '新闻中心', '3', '', '2017-04-20 09:21:43', '2017-04-20 09:21:45');

-- ----------------------------
-- Table structure for `log`
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `app_name` char(36) NOT NULL COMMENT '所属应用',
  `log_type` int(11) DEFAULT NULL COMMENT '日志类型，0为操作日志，1为异常日志',
  `username` varchar(100) DEFAULT NULL COMMENT '访问者/请求者',
  `operation` varchar(100) DEFAULT NULL COMMENT '方法描述',
  `method_name` varchar(100) DEFAULT NULL COMMENT '请求方法名称(全路径)',
  `request_method` varchar(20) DEFAULT NULL COMMENT '请求方式(GET,POST,DELETE,PUT)',
  `request_params` varchar(500) DEFAULT NULL COMMENT '请求参数',
  `request_ip` varchar(50) DEFAULT NULL COMMENT '访问者IP',
  `request_uri` varchar(200) DEFAULT NULL COMMENT '请求URI',
  `exception_code` varchar(100) DEFAULT NULL COMMENT '异常码',
  `exception_detail` varchar(2000) DEFAULT NULL COMMENT '异常描述',
  `time_consuming` bigint(20) DEFAULT NULL COMMENT '请求耗时',
  `user_agent` varchar(500) DEFAULT NULL COMMENT '客户端信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=229 DEFAULT CHARSET=utf8 COMMENT='系统日志表';

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES ('227', '', '0', 'admin', '添加调度', 'com.jk.controller.admin.JobController.saveJob()', 'POST', 'jobName=测试1&jobGroup=测试组1&cron=0/10 * * * * ?&isLocal=1&beanClass=com.jk.task.TaskTest&methodName=run&remoteUrl=http://www.baidu.com&params=&isSync=1&remarks=1', '0:0:0:0:0:0:0:1', '/admin/job', null, null, '343', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36', '2017-05-17 21:26:01', '2017-05-17 21:26:01');
INSERT INTO `log` VALUES ('228', '', '0', 'admin', '删除调度', 'com.jk.controller.admin.JobController.delete()', 'DELETE', '', '0:0:0:0:0:0:0:1', '/admin/job/10', null, null, '60', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36', '2017-05-17 23:02:51', '2017-05-17 23:02:51');

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '资源名称',
  `type` varchar(32) DEFAULT NULL COMMENT '资源类型：0,1,2(目录,菜单or按钮)',
  `url` varchar(255) DEFAULT NULL COMMENT '访问url地址',
  `perms` varchar(128) DEFAULT NULL COMMENT '权限代码字符串',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父节点id',
  `parent_name` varchar(168) DEFAULT NULL COMMENT '父节点名称',
  `parent_ids` varchar(128) DEFAULT NULL COMMENT '父节点id列表串，用/分割',
  `icon` varchar(168) DEFAULT NULL COMMENT '图标',
  `sort` bigint(20) DEFAULT NULL COMMENT '排序号',
  `is_lock` bit(1) DEFAULT NULL COMMENT '账号是否锁定，1：锁定，0未锁定',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '系统管理', '0', null, '', '0', null, '0/', '&#xe62d;', '1', '', '2017-02-09 10:34:50', '2017-05-16 23:10:48');
INSERT INTO `permission` VALUES ('3', '管理员管理', '1', '/admin/manager/user', 'user:list', '1', '系统管理', '1/', null, '1', '', '2017-02-13 12:08:50', '2017-02-15 15:23:54');
INSERT INTO `permission` VALUES ('9', '角色管理', '1', '/admin/role', 'role:list', '1', '系统管理', null, '', '2', '', '2017-02-15 14:07:21', '2017-05-16 23:10:17');
INSERT INTO `permission` VALUES ('10', '权限管理', '1', '/admin/permission', 'permission:list', '1', '系统管理', null, '', '3', '', '2017-02-15 14:08:24', '2017-05-16 23:10:23');
INSERT INTO `permission` VALUES ('11', '新增', '2', null, 'user:create', '3', '管理员管理', null, '', '1', '', '2017-02-15 14:25:22', '2017-05-16 23:11:10');
INSERT INTO `permission` VALUES ('12', '修改', '2', null, 'user:update', '3', '管理员管理', null, '', '2', '', '2017-02-15 14:26:03', '2017-05-16 23:11:19');
INSERT INTO `permission` VALUES ('13', '删除', '2', null, 'user:delete', '3', '管理员管理', null, '', '3', '', '2017-02-15 14:27:29', '2017-05-16 23:11:25');
INSERT INTO `permission` VALUES ('14', '查看', '2', null, 'user:view', '3', '管理员管理', null, '', '4', '', '2017-02-15 15:22:47', '2017-05-16 23:11:30');
INSERT INTO `permission` VALUES ('15', '新增', '2', null, 'role:create', '9', '角色管理', null, '', '1', '', '2017-02-15 15:23:21', '2017-05-16 23:11:46');
INSERT INTO `permission` VALUES ('17', '删除', '2', null, 'role:delete', '9', '角色管理', null, '', '3', '', '2017-02-15 15:25:48', '2017-05-16 23:12:09');
INSERT INTO `permission` VALUES ('18', '查看', '2', null, 'role:view', '9', '角色管理', null, '', '4', '', '2017-02-15 15:26:24', '2017-05-16 23:12:16');
INSERT INTO `permission` VALUES ('19', '新增', '2', null, 'permission:create', '10', '权限管理', null, '', '1', '', '2017-02-15 15:27:53', '2017-05-16 23:12:28');
INSERT INTO `permission` VALUES ('20', '修改', '2', null, 'permission:update', '10', '权限管理', null, '', '2', '', '2017-02-15 15:28:24', '2017-05-16 23:12:32');
INSERT INTO `permission` VALUES ('21', '删除', '2', null, 'permission:delete', '10', '权限管理', null, '', '3', '', '2017-02-15 15:28:51', '2017-05-16 23:12:37');
INSERT INTO `permission` VALUES ('22', '查看', '2', null, 'permission:view', '10', '权限管理', null, '', '4', '', '2017-02-15 15:29:10', '2017-05-16 23:12:42');
INSERT INTO `permission` VALUES ('31', '修改', '2', null, 'role:update', '9', '角色管理', null, '', '2', '', '2017-02-17 13:39:20', '2017-05-16 23:12:01');
INSERT INTO `permission` VALUES ('32', '禁用|启用', '2', null, 'user:status', '3', '管理员管理', null, '', '5', '', '2017-02-17 15:57:15', '2017-05-16 23:13:00');
INSERT INTO `permission` VALUES ('51', '内容管理', '0', null, null, '0', null, null, '&#xe616;', '3', '', '2017-04-19 11:16:42', '2017-05-16 23:13:30');
INSERT INTO `permission` VALUES ('52', '内容分类', '1', '/admin/content/cat', 'content-cat:list', '51', '内容管理', null, '', '1', '', '2017-04-19 11:17:50', '2017-05-16 23:16:17');
INSERT INTO `permission` VALUES ('53', '内容列表', '1', '/admin/content', 'content:list', '51', '内容管理', null, '', '2', '', '2017-04-19 11:21:28', '2017-05-16 23:16:33');
INSERT INTO `permission` VALUES ('54', '删除', '2', null, 'content:delete', '53', '内容列表', null, '', '3', '', '2017-04-20 17:14:31', '2017-05-16 23:17:51');
INSERT INTO `permission` VALUES ('55', '添加', '2', null, 'content:create', '53', '内容列表', null, '', '1', '', '2017-04-20 17:14:52', '2017-05-16 23:17:12');
INSERT INTO `permission` VALUES ('56', '修改', '2', null, 'content:update', '53', '内容列表', null, '', '2', '', '2017-04-20 17:15:18', '2017-05-16 23:18:02');
INSERT INTO `permission` VALUES ('57', '添加', '2', null, 'content-cat:create', '52', '内容分类', null, null, '1', '', '2017-04-24 11:04:12', '2017-04-24 11:04:12');
INSERT INTO `permission` VALUES ('58', '修改', '2', null, 'content-cat:update', '52', '内容分类', null, null, '2', '', '2017-04-24 11:04:40', '2017-04-24 11:04:40');
INSERT INTO `permission` VALUES ('59', '删除', '2', null, 'content-cat:delete', '52', '内容分类', null, null, '3', '', '2017-04-24 11:05:01', '2017-04-24 11:05:01');
INSERT INTO `permission` VALUES ('64', 'SQL监控', '1', '/druid/sql.html', 'sql:list', '1', '系统管理', null, '', '5', '', '2017-05-05 15:31:41', '2017-05-16 23:19:19');
INSERT INTO `permission` VALUES ('66', '调度管理', '0', null, null, '0', null, null, '&#xe637;', '2', '', '2017-05-09 15:22:11', '2017-05-16 23:13:42');
INSERT INTO `permission` VALUES ('67', '调度列表', '1', '/admin/job', 'job:list', '66', '调度管理', null, '', '1', '', '2017-05-09 15:22:37', '2017-05-16 23:19:40');
INSERT INTO `permission` VALUES ('68', '添加', '2', null, 'job:create', '67', '调度列表', null, '', '1', '', '2017-05-09 15:23:22', '2017-05-16 23:19:43');
INSERT INTO `permission` VALUES ('69', '日志管理', '0', null, null, '0', null, null, '&#xe623;', '4', '', '2017-05-16 23:22:15', '2017-05-16 23:26:13');
INSERT INTO `permission` VALUES ('70', '操作日志', '1', '/admin/log', 'log:list', '69', '日志管理', null, '', '1', '', '2017-05-16 23:23:57', '2017-05-16 23:23:57');
INSERT INTO `permission` VALUES ('71', '删除', '2', null, 'log:delete', '70', '操作日志', null, '', '1', '', '2017-05-16 23:24:26', '2017-05-16 23:24:26');
INSERT INTO `permission` VALUES ('72', '查看', '2', null, 'log:view', '70', '操作日志', null, '', '2', '', '2017-05-16 23:24:39', '2017-05-16 23:24:39');
INSERT INTO `permission` VALUES ('73', '修改', '2', null, 'job:update', '67', '调度列表', null, '', '2', '', '2017-05-17 15:40:42', '2017-05-17 15:40:42');
INSERT INTO `permission` VALUES ('74', '删除', '2', null, 'job:delete', '67', '调度列表', null, '', '3', '', '2017-05-17 16:04:48', '2017-05-17 16:04:48');
INSERT INTO `permission` VALUES ('75', '暂停', '2', null, 'job:pause', '67', '调度列表', null, '', '4', '', '2017-05-19 12:43:12', '2017-05-19 12:43:12');
INSERT INTO `permission` VALUES ('76', '恢复', '2', null, 'job:resume', '67', '调度列表', null, '', '5', '', '2017-05-19 12:46:22', '2017-05-19 12:46:22');
INSERT INTO `permission` VALUES ('77', '运行一次', '2', null, 'job:run', '67', '调度列表', null, '', '6', '', '2017-05-19 12:48:10', '2017-05-19 12:48:10');

-- ----------------------------
-- Table structure for `qrtz_blob_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_calendars`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_cron_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_fired_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(140) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(19) NOT NULL,
  `SCHED_TIME` bigint(19) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` tinyint(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_job_details`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` tinyint(1) NOT NULL,
  `IS_NONCONCURRENT` tinyint(1) NOT NULL,
  `IS_UPDATE_DATA` tinyint(1) NOT NULL,
  `REQUESTS_RECOVERY` tinyint(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_locks`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('GoodClusterScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('GoodClusterScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for `qrtz_paused_trigger_grps`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_scheduler_state`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(19) NOT NULL,
  `CHECKIN_INTERVAL` bigint(19) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('GoodClusterScheduler', 'cuip20171495033252118', '1495035075442', '15000');

-- ----------------------------
-- Table structure for `qrtz_simple_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_simprop_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` tinyint(1) DEFAULT NULL,
  `BOOL_PROP_2` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(19) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(19) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(19) NOT NULL,
  `END_TIME` bigint(19) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '注解',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `perms` varchar(168) DEFAULT NULL COMMENT '角色标识',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '超级管理员', '我是超级管理员', 'ROEL_ADMIN', '2017-02-08 16:03:57', '2017-05-16 23:24:48');
INSERT INTO `role` VALUES ('3', '管理员', '我是管理员', 'ROLE_GLY', '2017-02-08 17:13:50', '2017-05-05 15:26:19');
INSERT INTO `role` VALUES ('8', '普通用户', '我是普通用户', 'ROLE_SIMPLE', '2017-02-17 10:39:35', '2017-02-21 12:08:11');

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `permission_id` bigint(20) NOT NULL COMMENT '权限id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=956 DEFAULT CHARSET=utf8 COMMENT='角色和权限中间表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('400', '8', '1', '2017-02-21 12:08:11', '2017-02-21 12:08:11');
INSERT INTO `role_permission` VALUES ('401', '8', '3', '2017-02-21 12:08:11', '2017-02-21 12:08:11');
INSERT INTO `role_permission` VALUES ('402', '8', '11', '2017-02-21 12:08:11', '2017-02-21 12:08:11');
INSERT INTO `role_permission` VALUES ('403', '8', '14', '2017-02-21 12:08:11', '2017-02-21 12:08:11');
INSERT INTO `role_permission` VALUES ('956', '1', '1', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('957', '1', '3', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('958', '1', '11', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('959', '1', '12', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('960', '1', '13', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('961', '1', '14', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('962', '1', '32', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('963', '1', '9', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('964', '1', '15', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('965', '1', '31', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('966', '1', '17', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('967', '1', '18', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('968', '1', '10', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('969', '1', '19', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('970', '1', '20', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('971', '1', '21', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('972', '1', '22', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('973', '1', '64', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('974', '1', '66', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('975', '1', '67', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('976', '1', '68', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('977', '1', '73', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('978', '1', '74', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('979', '1', '75', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('980', '1', '76', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('981', '1', '77', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('982', '1', '51', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('983', '1', '52', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('984', '1', '57', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('985', '1', '58', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('986', '1', '59', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('987', '1', '53', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('988', '1', '55', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('989', '1', '56', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('990', '1', '54', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('991', '1', '69', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('992', '1', '70', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('993', '1', '71', '2017-05-19 12:48:21', '2017-05-19 12:48:21');
INSERT INTO `role_permission` VALUES ('994', '1', '72', '2017-05-19 12:48:21', '2017-05-19 12:48:21');

-- ----------------------------
-- Table structure for `schedule_job`
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `job_name` varchar(200) DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(200) DEFAULT NULL COMMENT '任务分组',
  `cron` varchar(200) DEFAULT NULL COMMENT 'cron表达式',
  `is_local` bit(1) DEFAULT NULL COMMENT '是否本地执行',
  `remote_url` varchar(200) DEFAULT NULL COMMENT '远程执行url',
  `bean_class` varchar(500) DEFAULT NULL COMMENT '执行类名称 包名+类名',
  `method_name` varchar(200) DEFAULT NULL COMMENT '执行方法名称',
  `params` varchar(500) DEFAULT NULL COMMENT '参数',
  `is_sync` bit(1) DEFAULT NULL COMMENT '是否异步  0否 1是',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_by` bigint(20) DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `remarks` varchar(200) DEFAULT NULL COMMENT '描述',
  `status` int(11) DEFAULT NULL COMMENT '任务状态 0禁用 1启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='任务调度';

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
INSERT INTO `schedule_job` VALUES ('14', '测试组', '测试无参数的本地调度任务', '0/8 * * * * ?', '', null, 'com.jk.task.TaskTest', 'run1', null, '', '83', '2017-05-19 14:28:27', '83', '2017-05-20 16:08:23', '测试无参数的本地调度任务', '1');
INSERT INTO `schedule_job` VALUES ('15', '测试组', '测试带参数的本地调度任务', '0/8 * * * * ?', '', null, 'com.jk.task.TaskTest', 'run2', '100,200', '', '83', '2017-05-20 13:28:35', '83', '2017-05-20 16:08:14', '测试带参数的本地调度任务', '1');
INSERT INTO `schedule_job` VALUES ('16', '测试组', '测试同步异步有效性的本地调度任务', '0/8 * * * * ?', '', null, 'com.jk.task.TaskTest', 'run3', null, '', '83', '2017-05-20 16:07:43', null, '2017-05-20 16:07:43', '测试同步异步有效性的本地调度任务', '1');


-- ----------------------------
-- Table structure for `schedule_job_log`
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `job_id` bigint(20) DEFAULT NULL COMMENT '任务ID',
  `job_name` varchar(200) DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(200) DEFAULT NULL COMMENT '任务分组',
  `cron` varchar(200) DEFAULT NULL COMMENT 'cron表达式',
  `is_local` bit(1) DEFAULT NULL COMMENT '是否本地执行',
  `remote_url` varchar(200) DEFAULT NULL COMMENT '远程执行url',
  `bean_class` varchar(500) DEFAULT NULL COMMENT '执行类名称 包名+类名',
  `method_name` varchar(200) DEFAULT NULL COMMENT '执行方法名称',
  `job_trigger` varchar(255) DEFAULT NULL COMMENT '触发器',
  `params` varchar(500) DEFAULT NULL COMMENT '参数',
  `is_sync` bit(1) DEFAULT NULL COMMENT '是否异步  0否 1是',
  `job_create_by` bigint(20) DEFAULT NULL COMMENT '任务创建者',
  `job_create_time` datetime DEFAULT NULL COMMENT '任务创建时间',
  `remarks` varchar(200) DEFAULT NULL COMMENT '描述',
  `status` int(11) DEFAULT NULL COMMENT '任务状态 0失败 1成功',
  `error` varchar(500) DEFAULT NULL COMMENT '失败信息',
  `times` bigint(20) DEFAULT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='任务调度执行日志';

-- ----------------------------
-- Records of schedule_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) DEFAULT NULL COMMENT '账号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `salt` varchar(64) DEFAULT NULL COMMENT '盐',
  `real_name` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `sex` bit(1) DEFAULT NULL COMMENT '性别  1 男  0 女',
  `mobile_phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `is_lock` bit(1) DEFAULT NULL COMMENT '账号是否锁定，1：锁定，0未锁定',
  `is_del` bit(1) DEFAULT NULL COMMENT '账号是否删除，1：删除，0未删除',
  `is_admin` bit(1) DEFAULT NULL COMMENT '是否是超级管理员',
  `login_time` datetime DEFAULT NULL COMMENT '最近一次登录时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('83', 'admin', '202cb962ac59075b964b07152d234b70', '202cb962ac59075b964b07152d234b70', '崔朋', '', '18568820939', '228727120@qq.com', '', '', '', null, '2017-02-07 11:45:57', '2017-02-20 16:45:07');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户和角色中间表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '83', '1', '2017-02-13 11:02:06', '2017-02-13 11:02:08');
INSERT INTO `user_role` VALUES ('2', '84', '8', '2017-05-16 23:06:46', '2017-05-16 23:06:46');
