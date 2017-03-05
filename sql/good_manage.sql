/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.238
Source Server Version : 50166
Source Host           : 192.168.1.238:3306
Source Database       : good_manage

Target Server Type    : MYSQL
Target Server Version : 50166
File Encoding         : 65001

Date: 2017-03-03 17:23:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_area`
-- ----------------------------
DROP TABLE IF EXISTS `t_area`;
CREATE TABLE `t_area` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(10) DEFAULT NULL,
  `area_name` varchar(255) DEFAULT NULL,
  `is_hot` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=418 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_area
-- ----------------------------
INSERT INTO `t_area` VALUES ('1', '0', '北京市', '0');
INSERT INTO `t_area` VALUES ('2', '0', '上海市', '0');
INSERT INTO `t_area` VALUES ('3', '0', '天津市', '0');
INSERT INTO `t_area` VALUES ('4', '0', '重庆市', '0');
INSERT INTO `t_area` VALUES ('5', '0', '黑龙江省', '0');
INSERT INTO `t_area` VALUES ('6', '0', '吉林省', '0');
INSERT INTO `t_area` VALUES ('7', '0', '辽宁省', '0');
INSERT INTO `t_area` VALUES ('8', '0', '山东省', '0');
INSERT INTO `t_area` VALUES ('9', '0', '山西省', '0');
INSERT INTO `t_area` VALUES ('10', '0', '陕西省', '0');
INSERT INTO `t_area` VALUES ('11', '0', '河北省', '0');
INSERT INTO `t_area` VALUES ('12', '0', '河南省', '0');
INSERT INTO `t_area` VALUES ('13', '0', '湖北省', '0');
INSERT INTO `t_area` VALUES ('14', '0', '湖南省', '0');
INSERT INTO `t_area` VALUES ('15', '0', '海南省', '0');
INSERT INTO `t_area` VALUES ('16', '0', '江苏省', '0');
INSERT INTO `t_area` VALUES ('17', '0', '江西省', '0');
INSERT INTO `t_area` VALUES ('18', '0', '广东省', '0');
INSERT INTO `t_area` VALUES ('19', '0', '广西省', '0');
INSERT INTO `t_area` VALUES ('20', '0', '云南省', '0');
INSERT INTO `t_area` VALUES ('21', '0', '贵州省', '0');
INSERT INTO `t_area` VALUES ('22', '0', '四川省', '0');
INSERT INTO `t_area` VALUES ('23', '0', '内蒙古自治区', '0');
INSERT INTO `t_area` VALUES ('24', '0', '宁夏回族自治区', '0');
INSERT INTO `t_area` VALUES ('25', '0', '甘肃省', '0');
INSERT INTO `t_area` VALUES ('26', '0', '青海省', '0');
INSERT INTO `t_area` VALUES ('27', '0', '西藏自治区', '0');
INSERT INTO `t_area` VALUES ('28', '0', '新疆自治区', '0');
INSERT INTO `t_area` VALUES ('29', '0', '安徽省', '0');
INSERT INTO `t_area` VALUES ('30', '0', '浙江省', '0');
INSERT INTO `t_area` VALUES ('31', '0', '福建省', '0');
INSERT INTO `t_area` VALUES ('32', '0', '台湾省', '0');
INSERT INTO `t_area` VALUES ('33', '0', '香港', '0');
INSERT INTO `t_area` VALUES ('34', '0', '澳门', '0');
INSERT INTO `t_area` VALUES ('35', '1', '北京市', '1');
INSERT INTO `t_area` VALUES ('36', '2', '上海市', '1');
INSERT INTO `t_area` VALUES ('37', '3', '天津市', '0');
INSERT INTO `t_area` VALUES ('38', '4', '重庆市', '0');
INSERT INTO `t_area` VALUES ('39', '11', '石家庄市', '0');
INSERT INTO `t_area` VALUES ('40', '11', '张家口市', '0');
INSERT INTO `t_area` VALUES ('41', '11', '承德市', '0');
INSERT INTO `t_area` VALUES ('42', '11', '唐山市', '0');
INSERT INTO `t_area` VALUES ('43', '11', '秦皇岛市', '0');
INSERT INTO `t_area` VALUES ('44', '11', '廊坊市', '0');
INSERT INTO `t_area` VALUES ('45', '11', '保定市', '0');
INSERT INTO `t_area` VALUES ('46', '11', '沧州市', '0');
INSERT INTO `t_area` VALUES ('47', '11', '衡水市', '0');
INSERT INTO `t_area` VALUES ('48', '11', '邢台市', '0');
INSERT INTO `t_area` VALUES ('49', '11', '邯郸市', '0');
INSERT INTO `t_area` VALUES ('50', '9', '太原市', '0');
INSERT INTO `t_area` VALUES ('51', '9', '大同市', '0');
INSERT INTO `t_area` VALUES ('52', '9', '朔州市', '0');
INSERT INTO `t_area` VALUES ('53', '9', '忻州市', '0');
INSERT INTO `t_area` VALUES ('54', '9', '阳泉市', '0');
INSERT INTO `t_area` VALUES ('55', '9', '晋中市', '0');
INSERT INTO `t_area` VALUES ('56', '9', '吕梁市', '0');
INSERT INTO `t_area` VALUES ('57', '9', '长治市', '0');
INSERT INTO `t_area` VALUES ('58', '9', '临汾市', '0');
INSERT INTO `t_area` VALUES ('59', '9', '晋城市', '0');
INSERT INTO `t_area` VALUES ('60', '9', '运城市', '0');
INSERT INTO `t_area` VALUES ('61', '23', '呼和浩特市', '0');
INSERT INTO `t_area` VALUES ('62', '23', '呼伦贝尔市', '0');
INSERT INTO `t_area` VALUES ('63', '23', '通辽市', '0');
INSERT INTO `t_area` VALUES ('64', '23', '赤峰市', '0');
INSERT INTO `t_area` VALUES ('65', '23', '巴彦淖尔市', '0');
INSERT INTO `t_area` VALUES ('66', '23', '乌兰察布市', '0');
INSERT INTO `t_area` VALUES ('67', '23', '包头市', '0');
INSERT INTO `t_area` VALUES ('68', '23', '鄂尔多斯市', '0');
INSERT INTO `t_area` VALUES ('69', '23', '乌海市', '0');
INSERT INTO `t_area` VALUES ('70', '5', '哈尔滨市', '1');
INSERT INTO `t_area` VALUES ('71', '5', '黑河市', '0');
INSERT INTO `t_area` VALUES ('72', '5', '伊春市', '0');
INSERT INTO `t_area` VALUES ('73', '5', '齐齐哈尔市', '0');
INSERT INTO `t_area` VALUES ('74', '5', '鹤岗市', '0');
INSERT INTO `t_area` VALUES ('75', '5', '佳木斯市', '0');
INSERT INTO `t_area` VALUES ('76', '5', '双鸭山市', '0');
INSERT INTO `t_area` VALUES ('77', '5', '绥化市', '0');
INSERT INTO `t_area` VALUES ('78', '5', '大庆市', '0');
INSERT INTO `t_area` VALUES ('79', '5', '七台河市', '0');
INSERT INTO `t_area` VALUES ('80', '5', '鸡西市', '0');
INSERT INTO `t_area` VALUES ('81', '5', '牡丹江市', '0');
INSERT INTO `t_area` VALUES ('82', '6', '长春市', '0');
INSERT INTO `t_area` VALUES ('83', '6', '白城市', '0');
INSERT INTO `t_area` VALUES ('84', '6', '松原市', '0');
INSERT INTO `t_area` VALUES ('85', '6', '吉林市', '0');
INSERT INTO `t_area` VALUES ('86', '6', '四平市', '0');
INSERT INTO `t_area` VALUES ('87', '6', '辽源市', '0');
INSERT INTO `t_area` VALUES ('88', '6', '白山市', '0');
INSERT INTO `t_area` VALUES ('89', '6', '通化市', '0');
INSERT INTO `t_area` VALUES ('90', '6', '延吉市', '0');
INSERT INTO `t_area` VALUES ('91', '7', '沈阳市', '0');
INSERT INTO `t_area` VALUES ('92', '7', '铁岭市', '0');
INSERT INTO `t_area` VALUES ('93', '7', '阜新市', '0');
INSERT INTO `t_area` VALUES ('94', '7', '抚顺市', '0');
INSERT INTO `t_area` VALUES ('95', '7', '朝阳市', '0');
INSERT INTO `t_area` VALUES ('96', '7', '本溪市', '0');
INSERT INTO `t_area` VALUES ('97', '7', '辽阳市', '0');
INSERT INTO `t_area` VALUES ('98', '7', '鞍山市', '0');
INSERT INTO `t_area` VALUES ('99', '7', '盘锦市', '0');
INSERT INTO `t_area` VALUES ('100', '7', '锦州市', '0');
INSERT INTO `t_area` VALUES ('101', '7', '葫芦岛市', '0');
INSERT INTO `t_area` VALUES ('102', '7', '营口市', '0');
INSERT INTO `t_area` VALUES ('103', '7', '丹东市', '0');
INSERT INTO `t_area` VALUES ('104', '7', '大连市', '0');
INSERT INTO `t_area` VALUES ('105', '16', '南京市', '1');
INSERT INTO `t_area` VALUES ('106', '16', '连云港市', '0');
INSERT INTO `t_area` VALUES ('107', '16', '徐州市', '0');
INSERT INTO `t_area` VALUES ('108', '16', '宿迁市', '0');
INSERT INTO `t_area` VALUES ('109', '16', '淮安市', '0');
INSERT INTO `t_area` VALUES ('110', '16', '盐城市', '0');
INSERT INTO `t_area` VALUES ('111', '16', '泰州市', '0');
INSERT INTO `t_area` VALUES ('112', '16', '扬州市', '0');
INSERT INTO `t_area` VALUES ('113', '16', '镇江市', '0');
INSERT INTO `t_area` VALUES ('114', '16', '南通市', '0');
INSERT INTO `t_area` VALUES ('115', '16', '常州市', '0');
INSERT INTO `t_area` VALUES ('116', '16', '无锡市', '0');
INSERT INTO `t_area` VALUES ('117', '16', '苏州市', '0');
INSERT INTO `t_area` VALUES ('118', '30', '杭州市', '1');
INSERT INTO `t_area` VALUES ('119', '30', '湖州市', '0');
INSERT INTO `t_area` VALUES ('120', '30', '嘉兴市', '0');
INSERT INTO `t_area` VALUES ('121', '30', '绍兴市', '0');
INSERT INTO `t_area` VALUES ('122', '30', '舟山市', '0');
INSERT INTO `t_area` VALUES ('123', '30', '宁波市', '0');
INSERT INTO `t_area` VALUES ('124', '30', '金华市', '0');
INSERT INTO `t_area` VALUES ('125', '30', '衢州市', '0');
INSERT INTO `t_area` VALUES ('126', '30', '台州市', '0');
INSERT INTO `t_area` VALUES ('127', '30', '丽水市', '0');
INSERT INTO `t_area` VALUES ('128', '30', '温州市', '0');
INSERT INTO `t_area` VALUES ('129', '29', '合肥市', '0');
INSERT INTO `t_area` VALUES ('130', '29', '淮北市', '0');
INSERT INTO `t_area` VALUES ('131', '29', '亳州市', '0');
INSERT INTO `t_area` VALUES ('132', '29', '宿州市', '0');
INSERT INTO `t_area` VALUES ('133', '29', '蚌埠市', '0');
INSERT INTO `t_area` VALUES ('134', '29', '阜阳市', '0');
INSERT INTO `t_area` VALUES ('135', '29', '淮南市', '0');
INSERT INTO `t_area` VALUES ('136', '29', '滁州市', '0');
INSERT INTO `t_area` VALUES ('137', '29', '六安市', '0');
INSERT INTO `t_area` VALUES ('138', '29', '马鞍山市', '0');
INSERT INTO `t_area` VALUES ('139', '29', '芜湖市', '0');
INSERT INTO `t_area` VALUES ('140', '29', '宣城市', '0');
INSERT INTO `t_area` VALUES ('141', '29', '铜陵市', '0');
INSERT INTO `t_area` VALUES ('142', '29', '池州市', '0');
INSERT INTO `t_area` VALUES ('143', '29', '安庆市', '0');
INSERT INTO `t_area` VALUES ('144', '29', '黄山市', '0');
INSERT INTO `t_area` VALUES ('145', '31', '福州市', '0');
INSERT INTO `t_area` VALUES ('146', '31', '宁德市', '0');
INSERT INTO `t_area` VALUES ('147', '31', '南平市', '0');
INSERT INTO `t_area` VALUES ('148', '31', '三明市', '0');
INSERT INTO `t_area` VALUES ('149', '31', '莆田市', '0');
INSERT INTO `t_area` VALUES ('150', '31', '龙岩市', '0');
INSERT INTO `t_area` VALUES ('151', '31', '泉州市', '0');
INSERT INTO `t_area` VALUES ('152', '31', '漳州市', '0');
INSERT INTO `t_area` VALUES ('153', '31', '厦门市', '1');
INSERT INTO `t_area` VALUES ('154', '17', '南昌市', '0');
INSERT INTO `t_area` VALUES ('155', '17', '九江市', '0');
INSERT INTO `t_area` VALUES ('156', '17', '景德镇市', '0');
INSERT INTO `t_area` VALUES ('157', '17', '上饶市', '0');
INSERT INTO `t_area` VALUES ('158', '17', '鹰潭市', '0');
INSERT INTO `t_area` VALUES ('159', '17', '抚州市', '0');
INSERT INTO `t_area` VALUES ('160', '17', '新余市', '0');
INSERT INTO `t_area` VALUES ('161', '17', '宜春市', '0');
INSERT INTO `t_area` VALUES ('162', '17', '萍乡市', '0');
INSERT INTO `t_area` VALUES ('163', '17', '吉安市', '0');
INSERT INTO `t_area` VALUES ('164', '17', '赣州市', '0');
INSERT INTO `t_area` VALUES ('165', '8', '济南市', '0');
INSERT INTO `t_area` VALUES ('166', '8', '德州市', '0');
INSERT INTO `t_area` VALUES ('167', '8', '滨州市', '0');
INSERT INTO `t_area` VALUES ('168', '8', '东营市', '0');
INSERT INTO `t_area` VALUES ('169', '8', '烟台市', '0');
INSERT INTO `t_area` VALUES ('170', '8', '威海市', '0');
INSERT INTO `t_area` VALUES ('171', '8', '淄博市', '0');
INSERT INTO `t_area` VALUES ('172', '8', '潍坊市', '0');
INSERT INTO `t_area` VALUES ('173', '8', '聊城市', '0');
INSERT INTO `t_area` VALUES ('174', '8', '泰安市', '0');
INSERT INTO `t_area` VALUES ('175', '8', '莱芜市', '0');
INSERT INTO `t_area` VALUES ('176', '8', '青岛市', '0');
INSERT INTO `t_area` VALUES ('177', '8', '日照市', '0');
INSERT INTO `t_area` VALUES ('178', '8', '济宁市', '0');
INSERT INTO `t_area` VALUES ('179', '8', '菏泽市', '0');
INSERT INTO `t_area` VALUES ('180', '8', '临沂市', '0');
INSERT INTO `t_area` VALUES ('181', '8', '枣庄市', '0');
INSERT INTO `t_area` VALUES ('182', '12', '郑州市', '0');
INSERT INTO `t_area` VALUES ('183', '12', '安阳市', '0');
INSERT INTO `t_area` VALUES ('184', '12', '鹤壁市', '0');
INSERT INTO `t_area` VALUES ('185', '12', '濮阳市', '0');
INSERT INTO `t_area` VALUES ('186', '12', '新乡市', '0');
INSERT INTO `t_area` VALUES ('187', '12', '焦作市', '0');
INSERT INTO `t_area` VALUES ('188', '12', '三门峡市', '0');
INSERT INTO `t_area` VALUES ('189', '12', '开封市', '0');
INSERT INTO `t_area` VALUES ('190', '12', '洛阳市', '0');
INSERT INTO `t_area` VALUES ('191', '12', '商丘市', '0');
INSERT INTO `t_area` VALUES ('192', '12', '许昌市', '0');
INSERT INTO `t_area` VALUES ('193', '12', '平顶山市', '0');
INSERT INTO `t_area` VALUES ('194', '12', '周口市', '0');
INSERT INTO `t_area` VALUES ('195', '12', '漯河市', '0');
INSERT INTO `t_area` VALUES ('196', '12', '南阳市', '0');
INSERT INTO `t_area` VALUES ('197', '12', '驻马店市', '0');
INSERT INTO `t_area` VALUES ('198', '12', '信阳市', '0');
INSERT INTO `t_area` VALUES ('199', '12', '济源市', '0');
INSERT INTO `t_area` VALUES ('200', '13', '武汉市', '1');
INSERT INTO `t_area` VALUES ('201', '13', '十堰市', '0');
INSERT INTO `t_area` VALUES ('202', '13', '襄樊市', '0');
INSERT INTO `t_area` VALUES ('203', '13', '随州市', '0');
INSERT INTO `t_area` VALUES ('204', '13', '荆门市', '0');
INSERT INTO `t_area` VALUES ('205', '13', '孝感市', '0');
INSERT INTO `t_area` VALUES ('206', '13', '宜昌市', '0');
INSERT INTO `t_area` VALUES ('207', '13', '黄冈市', '0');
INSERT INTO `t_area` VALUES ('208', '13', '鄂州市', '0');
INSERT INTO `t_area` VALUES ('209', '13', '荆州市', '0');
INSERT INTO `t_area` VALUES ('210', '13', '黄石市', '0');
INSERT INTO `t_area` VALUES ('211', '13', '咸宁市', '0');
INSERT INTO `t_area` VALUES ('212', '13', '恩施市', '0');
INSERT INTO `t_area` VALUES ('213', '14', '长沙市', '0');
INSERT INTO `t_area` VALUES ('214', '14', '岳阳市', '0');
INSERT INTO `t_area` VALUES ('215', '14', '张家界市', '0');
INSERT INTO `t_area` VALUES ('216', '14', '常德市', '0');
INSERT INTO `t_area` VALUES ('217', '14', '益阳市', '0');
INSERT INTO `t_area` VALUES ('218', '14', '湘潭市', '0');
INSERT INTO `t_area` VALUES ('219', '14', '株洲市', '0');
INSERT INTO `t_area` VALUES ('220', '14', '娄底市', '0');
INSERT INTO `t_area` VALUES ('221', '14', '怀化市', '0');
INSERT INTO `t_area` VALUES ('222', '14', '邵阳市', '0');
INSERT INTO `t_area` VALUES ('223', '14', '衡阳市', '0');
INSERT INTO `t_area` VALUES ('224', '14', '永州市', '0');
INSERT INTO `t_area` VALUES ('225', '14', '郴州市', '0');
INSERT INTO `t_area` VALUES ('226', '18', '广州市', '1');
INSERT INTO `t_area` VALUES ('227', '18', '韶关市', '0');
INSERT INTO `t_area` VALUES ('228', '18', '梅州市', '0');
INSERT INTO `t_area` VALUES ('229', '18', '河源市', '0');
INSERT INTO `t_area` VALUES ('230', '18', '清远市', '0');
INSERT INTO `t_area` VALUES ('231', '18', '潮州市', '0');
INSERT INTO `t_area` VALUES ('232', '18', '揭阳市', '0');
INSERT INTO `t_area` VALUES ('233', '18', '汕头市', '0');
INSERT INTO `t_area` VALUES ('234', '18', '肇庆市', '0');
INSERT INTO `t_area` VALUES ('235', '18', '惠州市', '0');
INSERT INTO `t_area` VALUES ('236', '18', '佛山市', '0');
INSERT INTO `t_area` VALUES ('237', '18', '东莞市', '0');
INSERT INTO `t_area` VALUES ('238', '18', '云浮市', '0');
INSERT INTO `t_area` VALUES ('239', '18', '汕尾市', '0');
INSERT INTO `t_area` VALUES ('240', '18', '江门市', '0');
INSERT INTO `t_area` VALUES ('241', '18', '中山市', '0');
INSERT INTO `t_area` VALUES ('242', '18', '深圳市', '1');
INSERT INTO `t_area` VALUES ('243', '18', '珠海市', '0');
INSERT INTO `t_area` VALUES ('244', '18', '阳江市', '0');
INSERT INTO `t_area` VALUES ('245', '18', '茂名市', '0');
INSERT INTO `t_area` VALUES ('246', '18', '湛江市', '0');
INSERT INTO `t_area` VALUES ('247', '19', '南宁市', '0');
INSERT INTO `t_area` VALUES ('248', '19', '桂林市', '0');
INSERT INTO `t_area` VALUES ('249', '19', '河池市', '0');
INSERT INTO `t_area` VALUES ('250', '19', '贺州市', '0');
INSERT INTO `t_area` VALUES ('251', '19', '柳州市', '0');
INSERT INTO `t_area` VALUES ('252', '19', '百色市', '0');
INSERT INTO `t_area` VALUES ('253', '19', '来宾市', '0');
INSERT INTO `t_area` VALUES ('254', '19', '梧州市', '0');
INSERT INTO `t_area` VALUES ('255', '19', '贵港市', '0');
INSERT INTO `t_area` VALUES ('256', '19', '玉林市', '0');
INSERT INTO `t_area` VALUES ('257', '19', '崇左市', '0');
INSERT INTO `t_area` VALUES ('258', '19', '钦州市', '0');
INSERT INTO `t_area` VALUES ('259', '19', '防城港市', '0');
INSERT INTO `t_area` VALUES ('260', '19', '北海市', '0');
INSERT INTO `t_area` VALUES ('261', '15', '海口市', '0');
INSERT INTO `t_area` VALUES ('262', '15', '三沙市', '0');
INSERT INTO `t_area` VALUES ('263', '15', '三亚市', '0');
INSERT INTO `t_area` VALUES ('264', '22', '成都市', '1');
INSERT INTO `t_area` VALUES ('265', '22', '广元市', '0');
INSERT INTO `t_area` VALUES ('266', '22', '巴中市', '0');
INSERT INTO `t_area` VALUES ('267', '22', '绵阳市', '0');
INSERT INTO `t_area` VALUES ('268', '22', '德阳市', '0');
INSERT INTO `t_area` VALUES ('269', '22', '达州市', '0');
INSERT INTO `t_area` VALUES ('270', '22', '南充市', '0');
INSERT INTO `t_area` VALUES ('271', '22', '遂宁市', '0');
INSERT INTO `t_area` VALUES ('272', '22', '广安市', '0');
INSERT INTO `t_area` VALUES ('273', '22', '资阳市', '0');
INSERT INTO `t_area` VALUES ('274', '22', '眉山市', '0');
INSERT INTO `t_area` VALUES ('275', '22', '雅安市', '0');
INSERT INTO `t_area` VALUES ('276', '22', '内江市', '0');
INSERT INTO `t_area` VALUES ('277', '22', '乐山市', '0');
INSERT INTO `t_area` VALUES ('278', '22', '自贡市', '0');
INSERT INTO `t_area` VALUES ('279', '22', '泸州市', '0');
INSERT INTO `t_area` VALUES ('280', '22', '宜宾市', '0');
INSERT INTO `t_area` VALUES ('281', '22', '攀枝花市', '0');
INSERT INTO `t_area` VALUES ('282', '21', '贵阳市', '0');
INSERT INTO `t_area` VALUES ('283', '21', '遵义市', '0');
INSERT INTO `t_area` VALUES ('284', '21', '铜仁市', '0');
INSERT INTO `t_area` VALUES ('285', '21', '毕节市', '0');
INSERT INTO `t_area` VALUES ('286', '21', '六盘水市', '0');
INSERT INTO `t_area` VALUES ('287', '21', '安顺市', '0');
INSERT INTO `t_area` VALUES ('288', '20', '昆明市', '0');
INSERT INTO `t_area` VALUES ('289', '20', '昭通市', '0');
INSERT INTO `t_area` VALUES ('290', '20', '丽江市', '0');
INSERT INTO `t_area` VALUES ('291', '20', '曲靖市', '0');
INSERT INTO `t_area` VALUES ('292', '20', '保山市', '0');
INSERT INTO `t_area` VALUES ('293', '20', '玉溪市', '0');
INSERT INTO `t_area` VALUES ('294', '20', '临沧市', '0');
INSERT INTO `t_area` VALUES ('295', '20', '普洱市', '0');
INSERT INTO `t_area` VALUES ('296', '27', '拉萨市', '0');
INSERT INTO `t_area` VALUES ('297', '27', '昌都市', '0');
INSERT INTO `t_area` VALUES ('298', '27', '日喀则市', '0');
INSERT INTO `t_area` VALUES ('299', '10', '西安市', '1');
INSERT INTO `t_area` VALUES ('300', '10', '榆林市', '0');
INSERT INTO `t_area` VALUES ('301', '10', '延安市', '0');
INSERT INTO `t_area` VALUES ('302', '10', '铜川市', '0');
INSERT INTO `t_area` VALUES ('303', '10', '渭南市', '0');
INSERT INTO `t_area` VALUES ('304', '10', '宝鸡市', '0');
INSERT INTO `t_area` VALUES ('305', '10', '咸阳市', '0');
INSERT INTO `t_area` VALUES ('306', '10', '商洛市', '0');
INSERT INTO `t_area` VALUES ('307', '10', '汉中市', '0');
INSERT INTO `t_area` VALUES ('308', '10', '安康市', '0');
INSERT INTO `t_area` VALUES ('309', '25', '兰州市', '0');
INSERT INTO `t_area` VALUES ('310', '25', '嘉峪关市', '0');
INSERT INTO `t_area` VALUES ('311', '25', '酒泉市', '0');
INSERT INTO `t_area` VALUES ('312', '25', '张掖市', '0');
INSERT INTO `t_area` VALUES ('313', '25', '金昌市', '0');
INSERT INTO `t_area` VALUES ('314', '25', '武威市', '0');
INSERT INTO `t_area` VALUES ('315', '25', '白银市', '0');
INSERT INTO `t_area` VALUES ('316', '25', '庆阳市', '0');
INSERT INTO `t_area` VALUES ('317', '25', '平凉市', '0');
INSERT INTO `t_area` VALUES ('318', '25', '定西市', '0');
INSERT INTO `t_area` VALUES ('319', '25', '天水市', '0');
INSERT INTO `t_area` VALUES ('320', '25', '陇南市', '0');
INSERT INTO `t_area` VALUES ('321', '26', '海东市', '0');
INSERT INTO `t_area` VALUES ('322', '26', '西宁市', '0');
INSERT INTO `t_area` VALUES ('323', '24', '银川市', '0');
INSERT INTO `t_area` VALUES ('324', '24', '石嘴山市', '0');
INSERT INTO `t_area` VALUES ('325', '24', '吴忠市', '0');
INSERT INTO `t_area` VALUES ('326', '24', '中卫市', '0');
INSERT INTO `t_area` VALUES ('327', '24', '固原市', '0');
INSERT INTO `t_area` VALUES ('328', '28', '乌鲁木齐市', '0');
INSERT INTO `t_area` VALUES ('329', '28', '克拉玛依市', '0');
INSERT INTO `t_area` VALUES ('330', '28', '阿克苏市', '0');
INSERT INTO `t_area` VALUES ('331', '28', '阿图什市', '0');
INSERT INTO `t_area` VALUES ('332', '28', '阿勒泰', '0');
INSERT INTO `t_area` VALUES ('333', '28', '阿拉尔市', '0');
INSERT INTO `t_area` VALUES ('334', '28', '博乐市', '0');
INSERT INTO `t_area` VALUES ('335', '28', '昌吉市', '0');
INSERT INTO `t_area` VALUES ('336', '28', '哈密市', '0');
INSERT INTO `t_area` VALUES ('337', '28', '和田市', '0');
INSERT INTO `t_area` VALUES ('338', '28', '喀什市', '0');
INSERT INTO `t_area` VALUES ('339', '28', '库尔勒市', '0');
INSERT INTO `t_area` VALUES ('340', '28', '石河子市', '0');
INSERT INTO `t_area` VALUES ('341', '28', '吐鲁番', '0');
INSERT INTO `t_area` VALUES ('342', '28', '伊宁市', '0');
INSERT INTO `t_area` VALUES ('343', '32', '台湾省', '0');
INSERT INTO `t_area` VALUES ('344', '33', '香港', '0');
INSERT INTO `t_area` VALUES ('345', '34', '澳门', '0');

-- ----------------------------
-- Table structure for `t_article`
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号id',
  `time` timestamp NULL DEFAULT NULL COMMENT '添加时间',
  `type_id` int(11) DEFAULT NULL COMMENT '类别',
  `title` varchar(250) DEFAULT NULL COMMENT '标题',
  `title2` varchar(250) DEFAULT NULL,
  `author` varchar(20) DEFAULT NULL COMMENT '作者',
  `content` longtext COMMENT '内容',
  `keywords` varchar(200) DEFAULT NULL COMMENT '关键字',
  `read_count` int(11) DEFAULT '0' COMMENT '阅读次数',
  `image_filename` varchar(200) DEFAULT NULL COMMENT '图片路径',
  `location_pc` tinyint(4) DEFAULT NULL COMMENT '0 不推荐 1 推荐至首页\r\n2 荐跑马灯效果区\r\n3 推荐首页头条',
  `start_show_time` datetime DEFAULT NULL COMMENT '开始显示时间',
  `summary` varchar(1000) DEFAULT NULL COMMENT '摘要',
  `is_use` int(1) DEFAULT '0' COMMENT '是否显示',
  `order_number` int(11) NOT NULL DEFAULT '0' COMMENT '编号',
  `name` varchar(255) DEFAULT NULL,
  `view_count` int(15) DEFAULT '100',
  `agree_count` int(15) DEFAULT '100',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_news_id` (`id`) USING BTREE,
  KEY `index_news_type_id` (`type_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1345 DEFAULT CHARSET=utf8 COMMENT='资讯活动表';

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES ('1313', '2017-02-20 09:49:30', '83', '222', 'http://www.baidu.com', null, null, null, null, '/upload/images?filename=d088f8bf-0590-4df3-ac3f-ed8c9437bb1c.jpg', '0', null, null, '1', '1', null, '100', '100', '2017-02-20 09:49:30', '2017-02-24 17:58:47', null);
INSERT INTO `t_article` VALUES ('1318', '2017-02-20 15:19:14', '83', '111', null, null, null, null, null, '/upload/images?filename=1e1d72f3-46b9-4bcd-8626-5cb2f2b3d868.png', '0', null, null, '1', '0', null, '100', '100', '2017-02-20 15:19:14', '2017-02-24 17:15:50', null);
INSERT INTO `t_article` VALUES ('1321', '2017-02-24 17:17:45', '83', '轮播3', 'http://www.sina.com.cn', null, null, null, null, '/upload/images?filename=23a6ea78-e249-45df-ab09-7839b75fa525.png', '0', null, null, '1', '2', null, '100', '100', '2017-02-24 17:17:46', '2017-02-24 17:59:02', null);
INSERT INTO `t_article` VALUES ('1322', '2017-02-27 15:19:03', '118', '张霞：公益故事的转播需要激发公众的参与1公益故事的', null, null, '<p>\n	张小姐~~2017\n</p>\n<p>\n	&nbsp;\n</p>\n<div class=\"font pl\" style=\"margin:0px;padding:19px 0px 0px 25px;font-size:13px;color:#777777;\">\n	<p style=\"font-style:inherit;font-size:inherit;font-family:inherit;vertical-align:baseline;color:inherit;text-indent:2em;\">\n		记得上高中的时候，学校号召全校师生为灾区捐衣捐物，那是我第一次经历公益捐赠活动。同学们都把捐赠的东西送上讲台，收集了以班级为单位再交给学校。班级同学们捐赠的东西寥寥无几，而只有我，除了身上穿的，把自己新的旧的一年四季的外穿衣物记..\n	</p>\n</div>\n<p>\n	<a href=\"\"></a><img src=\"/upload/images?filename=cac7ffaf-4864-4926-8078-a6c1fa3e8063.png\" alt=\"\" /><br />\n<a href=\"\">\n	<div class=\"font pl\" style=\"margin:0px;padding:19px 0px 0px 25px;font-size:13px;color:#777777;\">\n		<h3 style=\"font-style:inherit;font-size:18px;font-family:inherit;vertical-align:baseline;color:#333333;font-weight:normal;\">\n			霞：公益故事的转播需要激发公众的参与1公益故事的...\n		</h3>\n		<div class=\"author_info clearfix\" style=\"margin:0px 0px 23px;padding:0px;\">\n			<span class=\"pl\" style=\"font-style:inherit;font-size:inherit;font-family:inherit;vertical-align:baseline;line-height:inherit;color:inherit;\">张小姐</span><span class=\"pl\" style=\"font-style:inherit;font-size:inherit;font-family:inherit;vertical-align:baseline;line-height:inherit;color:inherit;\">2017年1月5日</span>\n		</div>\n		<p style=\"font-style:inherit;font-size:inherit;font-family:inherit;vertical-align:baseline;color:inherit;text-indent:2em;\">\n			记得上高中的时候，学校号召全校师生为灾区捐衣捐物，那是我第一次经历公益捐赠活动。同学们都把捐赠的东西送上讲台，收集了以班级为单位再交给学校。班级同学们捐赠的东西寥寥无几，而只有我，除了身上穿的，把自己新的旧的一年四季的外穿衣物记..\n		</p>\n	</div>\n</a>\n</p>', null, null, '/upload/images?filename=4d27dd4c-933f-4e7c-aad9-0aae8da31842.png', '0', null, '张小姐2017年1月5日\n\n记得上高中的时候，学校号召全 我，除了身上穿的，把自己新的旧的一年四季的外穿衣物记..', '1', '10', null, '102', '110', '2017-02-27 15:19:03', '2017-03-03 16:50:04', null);
INSERT INTO `t_article` VALUES ('1323', '2017-02-27 15:19:42', '118', '张小姐2', null, null, 'dsfasdf', null, null, '/upload/images?filename=896722f1-ffb5-4614-b5ee-5dbcb6508b8c.jpg', '0', null, '张小姐2', '1', '12', null, '100', '102', '2017-02-27 15:19:42', '2017-03-03 14:23:09', null);
INSERT INTO `t_article` VALUES ('1324', '2017-02-27 15:19:57', '118', '3333', null, null, '33', null, null, '/upload/images?filename=e33e43a1-b9fa-4764-bb20-20cac45b7d56.jpg', '0', null, '33', '1', '33', null, '100', '100', '2017-02-27 15:19:57', '2017-02-27 15:19:57', null);
INSERT INTO `t_article` VALUES ('1325', '2017-02-27 15:20:12', '118', '444', null, null, '44', null, null, '/upload/images?filename=c3d04e2e-b0e1-4604-a12c-d32a980251bf.jpg', '0', null, '44', '1', '44', null, '100', '100', '2017-02-27 15:20:12', '2017-02-27 15:20:12', null);
INSERT INTO `t_article` VALUES ('1326', '2017-02-27 15:20:24', '118', '55', null, null, '55', null, null, '/upload/images?filename=a898606f-fad6-4c0f-b3c0-347d5d667d61.jpg', '0', null, '55', '1', '55', null, '100', '100', '2017-02-27 15:20:24', '2017-02-27 15:20:24', null);
INSERT INTO `t_article` VALUES ('1327', '2017-02-27 15:20:36', '118', '66', null, null, '66', null, null, '/upload/images?filename=aff71886-dcc3-4efc-af3e-8f93c85b8438.jpg', '0', null, '666', '1', '6', null, '100', '100', '2017-02-27 15:20:36', '2017-02-27 15:20:36', null);
INSERT INTO `t_article` VALUES ('1328', '2017-02-27 15:20:47', '118', '77', null, null, '77', null, null, '/upload/images?filename=b2c704f8-0643-48d0-903e-b420af67ab87.jpg', '0', null, '77', '1', '77', null, '100', '105', '2017-02-27 15:20:47', '2017-03-03 14:23:20', null);
INSERT INTO `t_article` VALUES ('1329', '2017-02-27 15:20:57', '118', '88', null, null, '88', null, null, '/upload/images?filename=3a178afa-9108-478f-b8c1-2958359386da.jpg', '0', null, '88', '1', '88', null, '100', '100', '2017-02-27 15:20:57', '2017-02-27 15:20:57', null);
INSERT INTO `t_article` VALUES ('1330', '2017-02-27 15:21:11', '118', '99', null, null, '99', null, null, '/upload/images?filename=6d3b53bb-45c0-4f53-b288-fbce83fdf694.jpg', '0', null, '999', '1', '99', null, '100', '100', '2017-02-27 15:21:11', '2017-02-27 15:21:11', null);
INSERT INTO `t_article` VALUES ('1331', '2017-02-27 15:21:25', '118', '100', null, null, '100', null, null, '/upload/images?filename=c2a466c5-ee8d-4e1a-bd5f-3e834eb4a75a.jpg', '0', null, '100', '1', '100', null, '100', '100', '2017-02-27 15:21:25', '2017-02-27 15:21:25', null);
INSERT INTO `t_article` VALUES ('1332', '2017-02-27 15:21:42', '118', '101', null, null, '101', null, null, '/upload/images?filename=53fe9dd4-cc8d-4ddd-a427-f7a383d53cab.jpg', '0', null, '11', '1', '101', null, '100', '101', '2017-02-27 15:21:42', '2017-03-03 14:15:53', null);
INSERT INTO `t_article` VALUES ('1333', '2017-02-27 15:21:58', '118', '102', null, null, '102', null, null, '/upload/images?filename=02a1342f-88ad-44db-9887-1b996d020253.jpg', '0', null, '102', '1', '102', null, '100', '100', '2017-02-27 15:21:58', '2017-02-27 15:21:58', null);
INSERT INTO `t_article` VALUES ('1334', '2017-02-27 15:22:15', '119', '111', null, null, '111', null, null, '/upload/images?filename=1afd72d4-bee2-43b8-8cd7-ba144dc9dd0e.jpg', '0', null, '111', '1', '111', null, '100', '103', '2017-02-27 15:22:15', '2017-03-03 15:39:13', null);
INSERT INTO `t_article` VALUES ('1335', '2017-02-27 15:22:27', '119', '222', null, null, '22', null, null, '/upload/images?filename=9b85cd69-5dee-4f38-a761-ff93662312eb.png', '0', null, '22', '1', '22', null, '100', '100', '2017-02-27 15:22:27', '2017-02-27 15:22:27', null);
INSERT INTO `t_article` VALUES ('1336', '2017-02-27 15:22:39', '119', '33', null, null, '33', null, null, '/upload/images?filename=39a45734-d00a-4cad-b550-44979240f152.png', '0', null, '33', '1', '333', null, '100', '106', '2017-02-27 15:22:39', '2017-03-03 14:15:16', null);
INSERT INTO `t_article` VALUES ('1337', '2017-02-27 15:22:52', '120', '444', null, null, '44', null, null, '/upload/images?filename=26d5e97d-769a-4e31-910a-acc5514324e9.png', '0', null, '44', '1', '444', null, '100', '105', '2017-02-27 15:22:52', '2017-03-03 14:14:50', null);
INSERT INTO `t_article` VALUES ('1338', '2017-02-27 15:23:05', '120', '566', null, null, '55', null, null, '/upload/images?filename=27b863e8-281e-41f9-84e9-e5272df0872e.png', '0', null, '55', '1', '555', null, '101', '216768', '2017-02-27 15:23:05', '2017-03-03 15:44:11', null);
INSERT INTO `t_article` VALUES ('1339', '2017-02-27 17:32:55', '122', '轮播图1', 'http://www.4fangguan.cn', null, null, null, null, '/upload/images?filename=770e724a-6a64-4a7d-8157-329a6ac3b551.png', '0', null, null, '1', '1', null, '100', '100', '2017-02-27 17:32:55', '2017-03-03 11:05:55', null);
INSERT INTO `t_article` VALUES ('1340', '2017-02-27 17:35:53', '122', '轮播图2', 'http://www.q-jade.com.cn', null, null, null, null, '/upload/images?filename=922fd919-768c-4b59-9e6a-4a969acc94cb.png', '0', null, null, '1', '0', null, '100', '100', '2017-02-27 17:35:53', '2017-02-27 17:35:53', null);
INSERT INTO `t_article` VALUES ('1343', '2017-03-03 16:35:38', '123', null, 'http://localhost:90/front/find/finddetail/1322', null, null, null, null, '/upload/images?filename=a2a439d5-cc0e-4b47-a2d7-fa052c5b751c.png', '0', null, null, '1', '22', null, null, null, '2017-03-03 16:35:38', '2017-03-03 16:49:55', null);
INSERT INTO `t_article` VALUES ('1344', '2017-03-03 16:36:02', '123', null, 'http://www.sina.com.cn', null, null, null, null, '/upload/images?filename=0a19fab4-7c93-4fec-b5c4-dc1c73eec0e0.jpg', '0', null, null, '1', '2', null, null, null, '2017-03-03 16:36:02', '2017-03-03 16:36:02', null);

-- ----------------------------
-- Table structure for `t_article_types`
-- ----------------------------
DROP TABLE IF EXISTS `t_article_types`;
CREATE TABLE `t_article_types` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `order_number` int(11) NOT NULL,
  `status` int(1) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_article_types
-- ----------------------------
INSERT INTO `t_article_types` VALUES ('77', '-1', '发现', '发现', '20', '1', '2017-02-20 09:34:28', '2017-02-20 09:34:28');
INSERT INTO `t_article_types` VALUES ('80', '-1', '消息', '消息', '30', '1', '2017-02-20 09:35:03', '2017-02-20 09:35:03');
INSERT INTO `t_article_types` VALUES ('81', '80', '消息', '消息', '1', '1', '2017-02-20 09:35:12', '2017-02-20 09:35:12');
INSERT INTO `t_article_types` VALUES ('82', '-1', '首页轮播图', '首页轮播图', '1', '1', '2017-02-20 09:35:50', '2017-02-20 09:35:50');
INSERT INTO `t_article_types` VALUES ('83', '82', '首页轮播图', '首页轮播图', '1', '1', '2017-02-20 09:35:54', '2017-02-20 09:35:54');
INSERT INTO `t_article_types` VALUES ('118', '77', '故事', '故事', '1', '1', '2017-02-27 10:36:15', '2017-02-27 10:36:15');
INSERT INTO `t_article_types` VALUES ('119', '77', '新闻', '新闻', '10', '1', '2017-02-27 10:36:31', '2017-02-27 10:36:31');
INSERT INTO `t_article_types` VALUES ('120', '77', '活动', '活动', '20', '1', '2017-02-27 10:36:46', '2017-02-27 10:36:46');
INSERT INTO `t_article_types` VALUES ('121', '-1', '子页轮播图', '子页轮播图', '10', '1', '2017-02-27 17:30:40', '2017-02-27 17:30:40');
INSERT INTO `t_article_types` VALUES ('122', '121', '发现页面轮播图', '发现页面轮播图', '0', '1', '2017-02-27 17:31:08', '2017-02-27 17:31:08');
INSERT INTO `t_article_types` VALUES ('123', '77', '最近活动', '最近活动', '30', '1', '2017-03-03 15:49:11', '2017-03-03 15:49:11');

-- ----------------------------
-- Table structure for `t_authentication_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_authentication_info`;
CREATE TABLE `t_authentication_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `good_user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `auth_user_name` varchar(168) DEFAULT NULL COMMENT '个人姓名',
  `id_card` varchar(168) DEFAULT NULL COMMENT '身份证号',
  `id_card_face` varchar(255) DEFAULT NULL COMMENT '身份证正面',
  `id_card_back` varchar(255) DEFAULT NULL COMMENT '身份证背面',
  `type` int(11) DEFAULT NULL COMMENT '用户认证类型 1 个人 2 机构',
  `auth_legal_user_name` varchar(168) DEFAULT NULL COMMENT '法人代表姓名',
  `business_licence` varchar(255) DEFAULT NULL COMMENT '三证合一的证件',
  `company_name` varchar(168) DEFAULT NULL COMMENT '公司姓名',
  `register_time` datetime DEFAULT NULL COMMENT '注册时间',
  `register_address` varchar(255) DEFAULT NULL COMMENT '注册登记地址',
  `company_introduce` varchar(255) DEFAULT NULL COMMENT '公司简介',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  `check_time` datetime DEFAULT NULL COMMENT '审核时间',
  `check_status` int(11) DEFAULT NULL COMMENT '审核状态 0.待审核 1.通过 2.拒绝',
  `remark` varchar(255) DEFAULT NULL COMMENT '审核理由',
  `admin_id` bigint(20) DEFAULT NULL COMMENT '审核人ID',
  `admin_name` varchar(168) DEFAULT NULL COMMENT '审核人姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='用户认证表';

-- ----------------------------
-- Records of t_authentication_info
-- ----------------------------
INSERT INTO `t_authentication_info` VALUES ('1', '1', '好项目基金会', '1212121212121', '1', '1', '1', '1', '1', '弘毅基金会', '2017-02-22 14:20:43', '金高路2355号', null, '2017-02-22 14:20:56', '2017-02-23 18:14:58', '2017-02-22 14:21:00', '2', '1', null, null);
INSERT INTO `t_authentication_info` VALUES ('2', '2', '好项目基金会', '3323232', '1', '1', '1', '1', '1', '弘毅基金会', '2017-02-22 14:29:17', '金高路2355号', null, '2017-02-22 14:29:27', '2017-02-22 14:29:30', '2017-02-22 14:29:32', '1', '12121', null, null);
INSERT INTO `t_authentication_info` VALUES ('3', '3', '好项目基金会', '1', '1', '121', '1', '1', '1', '弘毅基金会', '2017-02-22 14:30:04', '金高路2355号', null, '2017-02-22 14:29:51', '2017-02-22 14:29:49', '2017-02-22 14:29:46', '1', '1212', null, null);
INSERT INTO `t_authentication_info` VALUES ('4', '4', '好项目基金会', '32', '1', '1', '1', '1', '1', '弘毅基金会', '2017-02-22 14:30:49', '金高路2355号', null, '2017-02-22 14:31:03', '2017-02-22 14:31:06', '2017-02-22 14:31:08', '1', '1212', null, null);
INSERT INTO `t_authentication_info` VALUES ('5', '1', '', '121', '1', '1', '2', '1', '1', '弘毅基金会', '2017-02-22 14:34:09', '金高路2355号', null, '2017-02-22 14:34:32', '2017-02-22 14:34:34', '2017-02-22 14:34:37', '1', '121', null, null);

-- ----------------------------
-- Table structure for `t_expert_comment`
-- ----------------------------
DROP TABLE IF EXISTS `t_expert_comment`;
CREATE TABLE `t_expert_comment` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `project_id` bigint(10) DEFAULT NULL COMMENT '项目id',
  `project_number` varchar(25) DEFAULT NULL COMMENT '项目编号',
  `img_code` varchar(255) DEFAULT NULL COMMENT '上传的头像',
  `contents` varchar(255) DEFAULT NULL COMMENT '评论内容',
  `expert_name` varchar(25) DEFAULT NULL COMMENT '专家称呼',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `expert_title` varchar(25) DEFAULT NULL COMMENT '专家职称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_expert_comment
-- ----------------------------
INSERT INTO `t_expert_comment` VALUES ('1', '1', '111111111', '/upload/images?filename=6eb71057-1caf-4364-b373-02d98cf28c97.jpg', '11221', 'jim', '5689', null, '2017-02-23 16:31:24', '职称1');
INSERT INTO `t_expert_comment` VALUES ('3', '1', '111111111', null, '1232143', '21', '5689', null, null, '职称1');
INSERT INTO `t_expert_comment` VALUES ('21', '4', '111111114', '/upload/images?filename=ad7d5fca-b5bd-4799-a6ba-587f626b64fa.png', '11', '1', '项目4', '2017-02-20 18:44:10', '2017-02-21 14:25:36', '职称1');
INSERT INTO `t_expert_comment` VALUES ('22', '1', '111111111', '/upload/images?filename=f02a5a6d-1649-49de-8904-635de42d9879.jpg', '4444', '4444', '5689', '2017-02-21 10:33:06', '2017-02-21 10:33:06', '职称1');
INSERT INTO `t_expert_comment` VALUES ('23', '1', '111111111', '/upload/images?filename=bf757d31-2dcb-495b-940b-3a58c30e809f.png', '55555', '5555555', '5689', '2017-02-21 10:33:51', '2017-02-21 10:33:51', '职称1');
INSERT INTO `t_expert_comment` VALUES ('24', '1', '111111111', '/upload/images?filename=61949c63-f83f-45b0-aa3c-a33026fc819f.gif', 'y', 'y', '5689', '2017-02-21 10:34:25', '2017-02-21 10:34:25', '职称1');
INSERT INTO `t_expert_comment` VALUES ('25', '1', '111111111', '/upload/images?filename=2c43a785-47b1-48d1-99ba-7ef7a6b59bd2.gif', '123123', '123123', '5689', '2017-02-21 10:45:08', '2017-02-21 10:45:08', '职称1');
INSERT INTO `t_expert_comment` VALUES ('26', '1', '111111111', '/upload/images?filename=d8f0b8ce-d7d3-4358-b039-e41cbb6690ba.jpg', '0000', '000', '5689', '2017-02-21 10:51:09', '2017-02-21 10:51:09', '职称1');
INSERT INTO `t_expert_comment` VALUES ('29', '1', '111111111', '/upload/images?filename=5eb5737f-390b-4dd7-b92a-b213d65e263e.jpg', '1', '1', '5689', '2017-02-21 14:45:46', '2017-02-23 10:21:24', '职称1');
INSERT INTO `t_expert_comment` VALUES ('30', '1', '111111111', '/upload/images?filename=9e65da05-0246-4fb0-a480-ac9a115aeab8.jpg', '21212', '222', '5689', '2017-02-22 17:22:02', '2017-02-23 16:25:56', '21212');
INSERT INTO `t_expert_comment` VALUES ('31', '2', '111111112', '/upload/images?filename=53998968-94a2-42dc-8621-ecc0375cd541.jpg', '1', '1', '项目22', '2017-02-23 16:27:43', '2017-02-23 16:27:52', '9');
INSERT INTO `t_expert_comment` VALUES ('32', '1', '111111111', '/upload/images?filename=b09e65e6-1f36-42ed-9874-620b21cc61f0.jpg', '1', 'jim', '5689', '2017-02-23 16:30:47', '2017-02-23 16:30:47', '大学教授');
INSERT INTO `t_expert_comment` VALUES ('33', '1', '111111111', '/upload/images?filename=46938759-575c-4c97-91d8-fba1968f0366.jpg', '22222222', '1222222222222', '5689', '2017-02-23 16:31:05', '2017-02-23 16:31:05', '222222222222222222');
INSERT INTO `t_expert_comment` VALUES ('34', '3', '111111113', '/upload/images?filename=0828d6df-6c41-4ef3-931a-126943fba01b.gif', '3', '3', '项目312', '2017-03-02 15:17:41', '2017-03-02 15:17:41', '3');

-- ----------------------------
-- Table structure for `t_good_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_good_user`;
CREATE TABLE `t_good_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(168) DEFAULT NULL COMMENT '个人/机构名',
  `password` varchar(168) DEFAULT NULL COMMENT '密码',
  `salt` varchar(168) DEFAULT NULL COMMENT '加密密码的盐',
  `mobile_phone` varchar(168) DEFAULT NULL COMMENT '手机号',
  `email` varchar(168) DEFAULT NULL COMMENT '邮箱',
  `is_accept_email` bit(1) DEFAULT NULL COMMENT '是否接受系统邮件',
  `start_number` int(11) DEFAULT NULL COMMENT '发起的项目数量',
  `keep_number` int(11) DEFAULT NULL COMMENT '收藏的项目数量',
  `support_number` int(11) DEFAULT NULL COMMENT '支持的项目数量',
  `get_price` bigint(20) DEFAULT NULL COMMENT '募集的金额  单位为：分，避免四舍五入换算精度问题',
  `pay_price` bigint(20) DEFAULT NULL COMMENT '支持的金额  单位为：分，避免四舍五入换算精度问题',
  `is_person_authed` bit(1) DEFAULT NULL COMMENT '个人权益是否认证通过',
  `is_team_authed` bit(1) DEFAULT NULL COMMENT '机构权益是否认证通过',
  `is_lock` bit(1) DEFAULT NULL COMMENT '是否禁用  true禁用  false 启用',
  `is_del` bit(1) DEFAULT NULL COMMENT '是否删除 true 删除 false 未删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_email_authed` bit(1) DEFAULT NULL COMMENT '邮箱是否认证通过 默认false',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='前台用户表';

-- ----------------------------
-- Records of t_good_user
-- ----------------------------
INSERT INTO `t_good_user` VALUES ('1', 'cuipeng1', '202cb962ac59075b964b07152d234b70', null, '15001338253', '22@qq.com', null, '11', '1', '10', '100', '0', '', '', '', '', '2017-02-21 18:18:47', '2017-02-23 18:16:28', null);
INSERT INTO `t_good_user` VALUES ('2', 'cuipeng2', '121', null, '12121', '221@qq.com', null, '11', '212', '2121', '212', '2121', '', '', '', '', '2017-02-22 14:27:04', '2017-02-22 14:27:07', null);
INSERT INTO `t_good_user` VALUES ('3', 'cuipeng3', '321', null, '434343', '222@qq.com', null, '32', '121', '54', '53', '21', '', '', '', '', '2017-02-22 14:27:44', '2017-02-22 14:27:47', null);
INSERT INTO `t_good_user` VALUES ('4', 'cuipeng4', '44', null, '12121', '223@qq.com', null, '44', '43', '45', '424', '42543', '', '', '', '', '2017-02-22 14:28:28', '2017-02-22 14:28:31', null);

-- ----------------------------
-- Table structure for `t_industry`
-- ----------------------------
DROP TABLE IF EXISTS `t_industry`;
CREATE TABLE `t_industry` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `industry_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_industry
-- ----------------------------
INSERT INTO `t_industry` VALUES ('1', '环境');
INSERT INTO `t_industry` VALUES ('2', '动物保护');
INSERT INTO `t_industry` VALUES ('3', '社区发展');
INSERT INTO `t_industry` VALUES ('4', '三农');
INSERT INTO `t_industry` VALUES ('5', '文化');
INSERT INTO `t_industry` VALUES ('6', '青少年');
INSERT INTO `t_industry` VALUES ('7', '扶贫助困');
INSERT INTO `t_industry` VALUES ('8', '心理健康');
INSERT INTO `t_industry` VALUES ('9', '教育');
INSERT INTO `t_industry` VALUES ('10', '儿童');
INSERT INTO `t_industry` VALUES ('11', '老年人');
INSERT INTO `t_industry` VALUES ('12', '安全救灾');
INSERT INTO `t_industry` VALUES ('13', '见义勇为');
INSERT INTO `t_industry` VALUES ('14', '公益事业发展');

-- ----------------------------
-- Table structure for `t_pay_stream`
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_stream`;
CREATE TABLE `t_pay_stream` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `project_id` bigint(10) DEFAULT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  `user_id` bigint(10) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `pay_amount` bigint(10) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_pay_stream
-- ----------------------------
INSERT INTO `t_pay_stream` VALUES ('1', '4', '项目1', '6', '丁发展', '50', '2017-02-21 11:30:27', '2017-02-21 11:30:27');
INSERT INTO `t_pay_stream` VALUES ('2', '5', '项目2', '7', '杨帆', '1111', '2017-02-21 11:30:27', '2017-02-21 11:30:27');
INSERT INTO `t_pay_stream` VALUES ('3', '6', '金恪1', '8', '崔朋', '3333', '2017-02-21 11:30:27', '2017-02-21 11:30:27');
INSERT INTO `t_pay_stream` VALUES ('4', '7', '项目1', '9', '丁发展', '20333', '2017-02-21 11:30:27', '2017-02-21 11:30:27');
INSERT INTO `t_pay_stream` VALUES ('5', '8', '项目2', '10', '杨帆', '25344', '2017-02-22 11:30:27', '2017-02-22 11:30:27');
INSERT INTO `t_pay_stream` VALUES ('6', '9', '金恪1', '11', '崔朋', '30566', '2017-02-11 11:30:27', '2017-02-21 11:30:28');
INSERT INTO `t_pay_stream` VALUES ('7', '10', '项目1', '12', '丁发展', '354', '2017-02-21 11:30:27', '2017-02-21 11:30:27');
INSERT INTO `t_pay_stream` VALUES ('8', '11', '项目2', '13', '杨帆', '40', '2017-02-21 11:30:27', '2017-02-21 11:30:27');
INSERT INTO `t_pay_stream` VALUES ('9', '12', '金恪1', '14', '崔朋', '454', '2017-02-21 11:30:27', '2017-02-21 11:30:27');
INSERT INTO `t_pay_stream` VALUES ('10', '13', '项目1', '15', '丁发展', '505', '2017-02-21 11:30:27', '2017-02-21 11:30:27');
INSERT INTO `t_pay_stream` VALUES ('11', '14', '项目2', '16', '杨帆', '555', '2017-02-21 11:30:27', '2017-02-21 11:30:27');
INSERT INTO `t_pay_stream` VALUES ('12', '15', '金恪1', '17', '崔朋', '605', '2017-02-21 11:30:27', '2017-02-21 11:30:27');
INSERT INTO `t_pay_stream` VALUES ('13', '16', '项目1', '18', '丁发展', '65', '2017-02-21 11:30:27', '2017-02-21 11:30:27');
INSERT INTO `t_pay_stream` VALUES ('14', '17', '项目2', '19', '杨帆', '706', '2017-02-21 11:30:27', '2017-02-21 11:30:27');
INSERT INTO `t_pay_stream` VALUES ('15', '18', '金恪1', '20', '崔朋', '756', '2017-02-21 11:30:27', '2017-02-21 11:30:27');
INSERT INTO `t_pay_stream` VALUES ('16', '19', '项目1', '21', '丁发展', '80', '2017-02-21 11:30:27', '2017-02-21 11:30:27');
INSERT INTO `t_pay_stream` VALUES ('17', '20', '项目2', '22', '杨帆', '85', '2017-02-21 11:30:27', '2017-02-21 11:30:27');
INSERT INTO `t_pay_stream` VALUES ('18', '21', '金恪1', '23', '崔朋', '90', '2017-02-21 11:30:27', '2017-02-21 11:30:27');
INSERT INTO `t_pay_stream` VALUES ('19', '22', '项目2', '24', '崔朋', '95', '2017-02-21 11:30:27', '2017-02-21 11:30:27');
INSERT INTO `t_pay_stream` VALUES ('20', '23', '项目2', '25', '崔朋', '100', '2017-02-21 11:30:27', '2017-02-21 11:30:27');
INSERT INTO `t_pay_stream` VALUES ('21', '23', '项目3', '26', '崔朋', '105', '2017-02-21 11:30:27', '2017-02-21 11:30:27');
INSERT INTO `t_pay_stream` VALUES ('22', '23', '项目4', '27', '崔朋', '110', '2017-02-21 11:30:27', '2017-02-21 11:30:27');
INSERT INTO `t_pay_stream` VALUES ('23', '23', '项目5', '28', '崔朋', '115', '2017-02-21 11:30:27', '2017-02-21 11:30:27');
INSERT INTO `t_pay_stream` VALUES ('24', '23', '项目6', '29', '崔朋', '120', '2017-02-21 11:30:27', '2017-02-21 11:30:27');

-- ----------------------------
-- Table structure for `t_pei`
-- ----------------------------
DROP TABLE IF EXISTS `t_pei`;
CREATE TABLE `t_pei` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `project_id` bigint(10) DEFAULT NULL COMMENT '项目id',
  `number` varchar(5) DEFAULT '' COMMENT '分项',
  `code` varchar(5) DEFAULT '' COMMENT '分数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=305 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_pei
-- ----------------------------
INSERT INTO `t_pei` VALUES ('225', '2', '1', '2', '2017-02-17 17:16:28', null);
INSERT INTO `t_pei` VALUES ('226', '2', '2', '0', '2017-02-17 17:16:28', null);
INSERT INTO `t_pei` VALUES ('227', '2', '3', '0', '2017-02-17 17:16:28', null);
INSERT INTO `t_pei` VALUES ('228', '2', '4', '0', '2017-02-17 17:16:28', null);
INSERT INTO `t_pei` VALUES ('229', '2', '5', '0', '2017-02-17 17:16:28', null);
INSERT INTO `t_pei` VALUES ('230', '2', '6', '0', '2017-02-17 17:16:28', null);
INSERT INTO `t_pei` VALUES ('231', '2', '7', '0', '2017-02-17 17:16:28', null);
INSERT INTO `t_pei` VALUES ('232', '2', '8', '0', '2017-02-17 17:16:28', null);
INSERT INTO `t_pei` VALUES ('233', '2', '9', '0', '2017-02-17 17:16:28', null);
INSERT INTO `t_pei` VALUES ('234', '2', '10', '0', '2017-02-17 17:16:28', null);
INSERT INTO `t_pei` VALUES ('235', '2', '11', '0', '2017-02-17 17:16:28', null);
INSERT INTO `t_pei` VALUES ('236', '2', '12', '0', '2017-02-17 17:16:28', null);
INSERT INTO `t_pei` VALUES ('237', '2', '13', '0', '2017-02-17 17:16:28', null);
INSERT INTO `t_pei` VALUES ('238', '2', '14', '0', '2017-02-17 17:16:28', null);
INSERT INTO `t_pei` VALUES ('239', '2', '15', '0', '2017-02-17 17:16:28', null);
INSERT INTO `t_pei` VALUES ('240', '2', '16', '0', '2017-02-17 17:16:28', null);
INSERT INTO `t_pei` VALUES ('241', '3', '1', '0', '2017-02-20 10:36:50', null);
INSERT INTO `t_pei` VALUES ('242', '3', '2', '0', '2017-02-20 10:36:50', null);
INSERT INTO `t_pei` VALUES ('243', '3', '3', '0', '2017-02-20 10:36:50', null);
INSERT INTO `t_pei` VALUES ('244', '3', '4', '0', '2017-02-20 10:36:50', null);
INSERT INTO `t_pei` VALUES ('245', '3', '5', '0', '2017-02-20 10:36:50', null);
INSERT INTO `t_pei` VALUES ('246', '3', '6', '0', '2017-02-20 10:36:50', null);
INSERT INTO `t_pei` VALUES ('247', '3', '7', '0', '2017-02-20 10:36:50', null);
INSERT INTO `t_pei` VALUES ('248', '3', '8', '0', '2017-02-20 10:36:50', null);
INSERT INTO `t_pei` VALUES ('249', '3', '9', '0', '2017-02-20 10:36:50', null);
INSERT INTO `t_pei` VALUES ('250', '3', '10', '0', '2017-02-20 10:36:50', null);
INSERT INTO `t_pei` VALUES ('251', '3', '11', '0', '2017-02-20 10:36:50', null);
INSERT INTO `t_pei` VALUES ('252', '3', '12', '5', '2017-02-20 10:36:50', null);
INSERT INTO `t_pei` VALUES ('253', '3', '13', '0', '2017-02-20 10:36:50', null);
INSERT INTO `t_pei` VALUES ('254', '3', '14', '0', '2017-02-20 10:36:50', null);
INSERT INTO `t_pei` VALUES ('255', '3', '15', '0', '2017-02-20 10:36:50', null);
INSERT INTO `t_pei` VALUES ('256', '3', '16', '0', '2017-02-20 10:36:50', null);
INSERT INTO `t_pei` VALUES ('273', '1', '1', '0', '2017-02-23 16:39:36', null);
INSERT INTO `t_pei` VALUES ('274', '1', '2', '1', '2017-02-23 16:39:36', null);
INSERT INTO `t_pei` VALUES ('275', '1', '3', '2', '2017-02-23 16:39:36', null);
INSERT INTO `t_pei` VALUES ('276', '1', '4', '3', '2017-02-23 16:39:36', null);
INSERT INTO `t_pei` VALUES ('277', '1', '5', '4', '2017-02-23 16:39:36', null);
INSERT INTO `t_pei` VALUES ('278', '1', '6', '0', '2017-02-23 16:39:36', null);
INSERT INTO `t_pei` VALUES ('279', '1', '7', '1', '2017-02-23 16:39:36', null);
INSERT INTO `t_pei` VALUES ('280', '1', '8', '2', '2017-02-23 16:39:36', null);
INSERT INTO `t_pei` VALUES ('281', '1', '9', '3', '2017-02-23 16:39:36', null);
INSERT INTO `t_pei` VALUES ('282', '1', '10', '0', '2017-02-23 16:39:36', null);
INSERT INTO `t_pei` VALUES ('283', '1', '11', '1', '2017-02-23 16:39:36', null);
INSERT INTO `t_pei` VALUES ('284', '1', '12', '2', '2017-02-23 16:39:36', null);
INSERT INTO `t_pei` VALUES ('285', '1', '13', '3', '2017-02-23 16:39:36', null);
INSERT INTO `t_pei` VALUES ('286', '1', '14', '0', '2017-02-23 16:39:36', null);
INSERT INTO `t_pei` VALUES ('287', '1', '15', '1', '2017-02-23 16:39:36', null);
INSERT INTO `t_pei` VALUES ('288', '1', '16', '2', '2017-02-23 16:39:36', null);
INSERT INTO `t_pei` VALUES ('289', '4', '1', '2', '2017-02-23 16:39:47', null);
INSERT INTO `t_pei` VALUES ('290', '4', '2', '0', '2017-02-23 16:39:47', null);
INSERT INTO `t_pei` VALUES ('291', '4', '3', '0', '2017-02-23 16:39:47', null);
INSERT INTO `t_pei` VALUES ('292', '4', '4', '0', '2017-02-23 16:39:47', null);
INSERT INTO `t_pei` VALUES ('293', '4', '5', '0', '2017-02-23 16:39:47', null);
INSERT INTO `t_pei` VALUES ('294', '4', '6', '0', '2017-02-23 16:39:47', null);
INSERT INTO `t_pei` VALUES ('295', '4', '7', '0', '2017-02-23 16:39:47', null);
INSERT INTO `t_pei` VALUES ('296', '4', '8', '0', '2017-02-23 16:39:47', null);
INSERT INTO `t_pei` VALUES ('297', '4', '9', '0', '2017-02-23 16:39:47', null);
INSERT INTO `t_pei` VALUES ('298', '4', '10', '0', '2017-02-23 16:39:47', null);
INSERT INTO `t_pei` VALUES ('299', '4', '11', '0', '2017-02-23 16:39:47', null);
INSERT INTO `t_pei` VALUES ('300', '4', '12', '0', '2017-02-23 16:39:47', null);
INSERT INTO `t_pei` VALUES ('301', '4', '13', '0', '2017-02-23 16:39:47', null);
INSERT INTO `t_pei` VALUES ('302', '4', '14', '0', '2017-02-23 16:39:47', null);
INSERT INTO `t_pei` VALUES ('303', '4', '15', '0', '2017-02-23 16:39:47', null);
INSERT INTO `t_pei` VALUES ('304', '4', '16', '0', '2017-02-23 16:39:47', null);

-- ----------------------------
-- Table structure for `t_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
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
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('1', '系统管理', '0', null, '', '0', null, '0/', '&#xe62d;', '0', '', '2017-02-09 10:34:50', '2017-02-20 14:31:49');
INSERT INTO `t_permission` VALUES ('3', '管理员管理', '1', '/admin/manager/user', 'user:list', '1', '系统管理', '1/', null, '1', '', '2017-02-13 12:08:50', '2017-02-15 15:23:54');
INSERT INTO `t_permission` VALUES ('9', '角色管理', '1', '/admin/role', 'role:list', '1', '系统管理', null, null, '0', '', '2017-02-15 14:07:21', '2017-02-15 15:24:03');
INSERT INTO `t_permission` VALUES ('10', '权限管理', '1', '/admin/permission', 'permission:list', '1', '系统管理', null, null, '0', '', '2017-02-15 14:08:24', '2017-02-15 15:23:44');
INSERT INTO `t_permission` VALUES ('11', '管理员管理--新增', '2', null, 'user:create', '3', '管理员管理', null, null, '0', '', '2017-02-15 14:25:22', '2017-02-15 15:24:10');
INSERT INTO `t_permission` VALUES ('12', '管理员管理--修改', '2', null, 'user:update', '3', '管理员管理', null, null, '0', '', '2017-02-15 14:26:03', '2017-02-15 15:24:20');
INSERT INTO `t_permission` VALUES ('13', '管理员管理--删除', '2', null, 'user:delete', '3', '管理员管理', null, null, '0', '', '2017-02-15 14:27:29', '2017-02-15 15:24:26');
INSERT INTO `t_permission` VALUES ('14', '管理员管理--查看', '2', null, 'user:view', '3', '管理员管理', null, null, '0', '', '2017-02-15 15:22:47', '2017-02-15 15:24:32');
INSERT INTO `t_permission` VALUES ('15', '角色管理--新增', '2', null, 'role:create', '9', '角色管理', null, null, '0', '', '2017-02-15 15:23:21', '2017-02-15 15:24:38');
INSERT INTO `t_permission` VALUES ('17', '角色管理--删除', '2', null, 'role:delete', '9', '角色管理', null, null, '0', '', '2017-02-15 15:25:48', '2017-02-15 15:25:48');
INSERT INTO `t_permission` VALUES ('18', '角色管理--查看', '2', null, 'role:view', '9', '角色管理', null, null, '0', '', '2017-02-15 15:26:24', '2017-02-15 15:26:24');
INSERT INTO `t_permission` VALUES ('19', '权限管理--新增', '2', null, 'permission:create', '10', '权限管理', null, null, '0', '', '2017-02-15 15:27:53', '2017-02-15 15:27:53');
INSERT INTO `t_permission` VALUES ('20', '权限管理--修改', '2', null, 'permission:update', '10', '权限管理', null, null, '0', '', '2017-02-15 15:28:24', '2017-02-15 15:28:24');
INSERT INTO `t_permission` VALUES ('21', '权限管理--删除', '2', null, 'permission:delete', '10', '权限管理', null, null, '0', '', '2017-02-15 15:28:51', '2017-02-15 15:28:51');
INSERT INTO `t_permission` VALUES ('22', '权限管理--查看', '2', null, 'permission:view', '10', '权限管理', null, null, '0', '', '2017-02-15 15:29:10', '2017-02-15 15:29:10');
INSERT INTO `t_permission` VALUES ('31', '角色管理--修改', '2', null, 'role:update', '9', '角色管理', null, null, '0', '', '2017-02-17 13:39:20', '2017-02-17 13:39:20');
INSERT INTO `t_permission` VALUES ('32', '管理员管理--禁用|启用', '2', null, 'user:status', '3', '管理员管理', null, null, '0', '', '2017-02-17 15:57:15', '2017-02-17 15:57:15');
INSERT INTO `t_permission` VALUES ('34', '资讯管理', '0', null, null, '0', null, null, null, '6', '', '2017-02-21 11:35:04', '2017-02-21 11:35:04');
INSERT INTO `t_permission` VALUES ('35', '资讯管理列表', '1', '/admin/article/list', 'article:list', '34', '资讯管理', null, null, '1', '', '2017-02-21 11:37:17', '2017-02-21 11:37:17');
INSERT INTO `t_permission` VALUES ('36', '资讯类别列表', '1', '/admin/articletype/list', 'articletype:list', '34', '资讯管理', null, null, '8', '', '2017-02-21 11:40:15', '2017-02-21 11:40:15');
INSERT INTO `t_permission` VALUES ('37', '推荐管理', '0', null, null, '0', null, null, null, '12', '', '2017-02-21 11:42:02', '2017-02-21 11:42:02');
INSERT INTO `t_permission` VALUES ('38', '推荐位列表', '1', '/admin/recommend/list', 'recommend:list', '37', '推荐管理', null, null, '0', '', '2017-02-21 11:42:40', '2017-02-21 11:42:40');
INSERT INTO `t_permission` VALUES ('39', '结算管理', '0', null, null, '0', null, null, null, '12', '', '2017-02-21 11:43:02', '2017-02-23 17:00:37');
INSERT INTO `t_permission` VALUES ('40', '项目管理', '0', null, null, '0', null, null, '&#xe620;', '1', '', '2017-02-21 12:05:07', '2017-02-21 12:05:07');
INSERT INTO `t_permission` VALUES ('41', '项目列表', '1', '/admin/project/projectList', 'project:list', '40', '项目管理', null, null, '1', '', '2017-02-21 12:05:52', '2017-02-21 12:05:52');
INSERT INTO `t_permission` VALUES ('42', '项目待打分列表', '1', '/admin/project/projectCheckScoreList', 'projectCheckScoreList:wait', '40', '项目管理', null, null, '2', '', '2017-02-21 12:06:36', '2017-02-21 12:06:36');
INSERT INTO `t_permission` VALUES ('43', '项目待打分列表', '1', '/admin/project/projectCheckScoreList?flag=1', 'projectCheckScoreList:ok', '40', '项目管理', null, null, '3', '', '2017-02-21 12:07:21', '2017-02-21 12:07:21');
INSERT INTO `t_permission` VALUES ('44', '捐款列表', '1', '/admin/paystream/list', 'pay:list', '39', '支付管理', null, null, '2', '', '2017-02-21 14:06:45', '2017-02-23 17:00:52');
INSERT INTO `t_permission` VALUES ('45', '用户管理', '0', null, null, '0', null, null, '&#xe60d;', '1', '', '2017-02-21 17:23:45', '2017-02-21 17:23:45');
INSERT INTO `t_permission` VALUES ('46', '用户列表', '1', '/admin/good', 'good:list', '45', '用户管理', null, null, '0', '', '2017-02-21 17:26:54', '2017-02-21 17:45:35');
INSERT INTO `t_permission` VALUES ('47', '提现列表', '1', '/admin/withdraw/list', 'withdraw:list', '39', '支付管理', null, null, '2', '', '2017-02-22 10:09:21', '2017-02-22 10:09:21');
INSERT INTO `t_permission` VALUES ('48', '认证管理', '1', '/admin/authentication-info', 'authentication-info:list', '45', '用户管理', null, null, '3', '', '2017-02-22 11:38:58', '2017-02-22 15:53:48');
INSERT INTO `t_permission` VALUES ('50', '敏感词管理', '1', '/admin/sensitiveword/list', 'sensitiveword:list', '1', '系统管理', null, null, '100', '', '2017-02-22 14:24:54', '2017-02-22 14:24:54');
INSERT INTO `t_permission` VALUES ('52', 'ooo', '0', null, null, '0', null, null, null, '0', '', '2017-02-24 11:07:24', '2017-02-24 11:07:24');

-- ----------------------------
-- Table structure for `t_project`
-- ----------------------------
DROP TABLE IF EXISTS `t_project`;
CREATE TABLE `t_project` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '项目名称',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `project_picture` varchar(255) DEFAULT NULL COMMENT '项目图片',
  `project_lead` varchar(255) DEFAULT NULL COMMENT '项目导语',
  `project_partner` varchar(255) DEFAULT NULL COMMENT '合作伙伴  最多3个，用;隔开',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `status` int(10) DEFAULT NULL COMMENT '   0:未审核，1：审核失败（驳回） 2：审核成功(募款中), 3:终止项目(前台用户控制)',
  `project_number` varchar(18) DEFAULT NULL,
  `admin_status` int(10) DEFAULT '0' COMMENT '0:启用中  1：禁用中',
  `industry_id` bigint(10) DEFAULT NULL,
  `score` double(20,0) DEFAULT NULL COMMENT '分数',
  `sort` int(10) DEFAULT NULL COMMENT '排序，默认999',
  `project_capital` bigint(20) DEFAULT '0' COMMENT '项目需求资金',
  `raised_funds` bigint(20) DEFAULT '0' COMMENT '已筹资金',
  `cash_withdrawal` bigint(20) DEFAULT '0' COMMENT '已提现资金',
  `fail_reasion` varchar(255) DEFAULT NULL COMMENT '审核失败原因',
  `earnings` bigint(10) DEFAULT NULL COMMENT '预计收益人数',
  `begin_time` datetime DEFAULT NULL COMMENT '项目周期:开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '项目周期:结束日期',
  `income_group_description` varchar(255) DEFAULT NULL COMMENT '收益群体描述',
  `project_requiredment_analysis` varchar(500) DEFAULT NULL COMMENT '项目需求分析',
  `full_time_number` bigint(10) DEFAULT NULL COMMENT '全职人数',
  `part_time_number` bigint(10) DEFAULT NULL COMMENT '兼职人数',
  `volunteer_number` bigint(10) DEFAULT NULL COMMENT '志愿者人数',
  `project_objectives` varchar(500) DEFAULT NULL COMMENT '项目目标',
  `project_design_road_one` varchar(500) DEFAULT NULL COMMENT '项目设计路径1',
  `project_design_road_two` varchar(500) DEFAULT NULL COMMENT '项目设计路径2',
  `project_design_road_three` varchar(500) DEFAULT NULL COMMENT '项目设计路径3',
  `expected_results_one` varchar(500) DEFAULT NULL,
  `expected_results_two` varchar(500) DEFAULT NULL COMMENT '预期成果2',
  `expected_results_three` varchar(500) DEFAULT NULL,
  `project_type` int(10) DEFAULT NULL COMMENT '1：个人项目  2：企业项目',
  `is_score` int(10) DEFAULT '0' COMMENT '0：未打分  1：已打分',
  `good_user_id` bigint(10) DEFAULT NULL COMMENT '用户id',
  `city_id` bigint(10) DEFAULT NULL COMMENT '关联城市表',
  `user_name` varchar(50) DEFAULT NULL COMMENT '账户名',
  `authentication_name` varchar(50) DEFAULT NULL COMMENT '机构/个人名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_project
-- ----------------------------
INSERT INTO `t_project` VALUES ('1', '56893333', '/upload/images?filename=16d6e8cf-c9dc-4b14-ab82-6652e18e4f3c.jpg', '导语1', '伙伴1', '2017-02-08 00:00:00', '2017-02-24 09:28:41', '0', '111111111', '0', '1', '25', '999', '100', '1', '1', '1', '122', '2017-02-01 00:00:00', '2017-02-28 00:00:00', '123', '2', '333', '121', '12', '213213', '12', 'xzcxzc', 'as', 'sadsasa', 'wdsax', 'qws', '1', '1', '1', '36', '100', '名称1');
INSERT INTO `t_project` VALUES ('2', '项目22', '图片2', '导语22', '伙伴2', '2017-02-16 00:00:00', '2017-02-14 16:31:05', '0', '111111112', '0', '3', '2', '999', '555555', '0', '0', '2121', '12213213', '2017-02-02 00:00:00', '2017-02-10 00:00:00', '11111111111111111111111111111111111111111111111111111111111111111111111111111111', '222222222222222222222', '121', '12121', '121', '213213', '21321', 'xzcxz12', 'sazx21的2', 'das23饿1额2', 'wsax额2饿1', 'tgb213we', '1', '1', '2', '36', 'ding199309@qq.com', '名称2');
INSERT INTO `t_project` VALUES ('3', '项目312', '/upload/images?filename=f52944a9-f515-4c69-ab57-fa62a5cdd019.jpg', '导语312', '伙伴3', '2017-02-07 00:00:00', '2017-02-21 15:50:58', '0', '111111113', '0', '1', '5', '999', '3', '0', '0', null, '12321321', '2017-02-01 00:00:00', '2017-02-10 00:00:00', '1', '2', '2113', '12', '21', '123213123', '3213', 'xzcx', 'zx', 'asddc', 'wsax', 'ghhnhm', '1', '1', '3', '35', 'ding199309@qq.com', '名称3');
INSERT INTO `t_project` VALUES ('4', '项目4', '图片4', '导语4', '伙伴4', '2017-02-08 00:00:00', '2017-02-14 15:58:27', '0', '111111114', '0', '1', '2', '999', '4', '0', '0', 'yyy', '111111', '2017-02-10 00:00:00', '2017-02-08 00:00:00', '1', '22222222222222222222222222222222', '331', '111', '12', '12321', '21321', 'vcxv', 'zx', 'wdscx', 'wsax', 'ggvb', '1', '1', '4', '36', 'ding199309@qq.com', '名称4');
INSERT INTO `t_project` VALUES ('5', '项目5', '/upload/images?filename=ea7cf662-1669-4812-ba61-7fb959e9d77a.png', '导语5', '伙伴5', '2017-02-08 00:00:00', '2017-02-23 19:15:28', '0', '111111115', '0', '1', '0', '999', '500', '0', '0', '通过', '45647', '2017-02-14 00:00:00', '2017-02-23 00:00:00', '1', '2', '11', '111', '12', '321321', '321', 'cxqw', 'g', 'erfg', 'wsaxz', 'vbdf', '1', '0', '0', '35', 'ding199309@qq.com', '名称5');
INSERT INTO `t_project` VALUES ('6', '项目6', '/upload/images?filename=4af773d2-ca6e-4335-9a2d-71e4912bdab8.gif', '导语6', '伙伴6', '2017-02-03 00:00:00', '2017-02-21 16:30:52', '0', '111111116', '0', '2', '0', '999', '6', '0', '0', null, '12321312', '2017-02-22 00:00:00', '2017-02-24 00:00:00', '1', '2', '1221', '21234', '12', '321321', '321ewd', 'sadas', 'xz', 'cv', 'wsax', 'vbnm', '2', '0', null, '75', 'ding199309@qq.com', '名称6');
INSERT INTO `t_project` VALUES ('7', '项目7', '图片7', '导语7', '伙伴7', '2017-02-06 00:00:00', '2017-02-22 11:05:34', '0', '111111117', '0', '2', '0', '999', '7', '0', '0', '7777', '312312321', '2017-02-08 00:00:00', '2017-02-24 00:00:00', '1', '2', '3123', '45', '12', '321321', 'scxczxcd', 'sxz', 'xz', 'thgbn', 'rfgvgc', 'wedscx', '2', '0', '0', '22', 'ding199309@qq.com', '名称7');
INSERT INTO `t_project` VALUES ('8', '项目28', '图片8', '导语8', '伙伴8', '2017-02-04 00:00:00', '2017-02-14 16:19:58', '0', '111111118', '0', '2', '0', '999', '8', '0', '0', null, '3313425', '2017-02-08 00:00:00', '2017-02-24 00:00:00', '1', '2', '1212', '565', '21', '12312', 'das', 'qasd', 'xzz', 'yhjn', 'rgfd', 'yhjhuj', '2', '0', null, '55', 'ding199309@qq.com', '名称8');
INSERT INTO `t_project` VALUES ('9', '项目9', '图片9', '导语9', '伙伴9', '2017-02-13 00:00:00', '2017-02-14 16:20:05', '0', '111111119', '0', '2', '0', '999', '9', '0', '0', null, '13432512', '2017-02-07 00:00:00', '2017-02-03 00:00:00', '1', '2', '1212', '7687', '12', '21321', 'asdsa', 'asdxzc', 'uu', 'ukj', 'tyghb', 'rfdv', '2', '0', null, '44', 'ding199309@qq.com', '名称9');
INSERT INTO `t_project` VALUES ('12', '项目12', '图片12', '导语12', '伙伴11', '2017-02-08 00:00:00', '2017-02-08 00:00:00', '0', '111111122', '0', '11', '0', '999', '12', '0', '0', null, '536578', null, null, '1', '2', '1212', '343', '1', '123213', 'asdsa', 'asxz', 'g', 'ol.', 'ergf', 'rrefdv', '2', '0', null, '96', 'ding199309@qq.com', '名称10');

-- ----------------------------
-- Table structure for `t_project_milepost`
-- ----------------------------
DROP TABLE IF EXISTS `t_project_milepost`;
CREATE TABLE `t_project_milepost` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `project_id` bigint(10) DEFAULT NULL COMMENT '项目编号',
  `project_number` varchar(20) DEFAULT NULL,
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `img_codes` varchar(500) DEFAULT NULL COMMENT '图片组',
  `contents` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_project_milepost
-- ----------------------------
INSERT INTO `t_project_milepost` VALUES ('1', '1', '111111111', '5689', '/upload/images?filename=5eb5737f-390b-4dd7-b92a-b213d65e263e.jpg;/upload/images?filename=5eb5737f-390b-4dd7-b92a-b213d65e263e.jpg;/upload/images?filename=5eb5737f-390b-4dd7-b92a-b213d65e263e.jpg', '1217-02-22 16:29:46.373 [http-nio-90-exec-8] INFO  c.j.c.admin.ProjectController -查询所有行业信息成功！ industryList = [com.jk.model.Industry@570f7ac9, com.jk.model.Industry@3f487c09, com.jk.model.Industry@17532a11, com.jk.model.Industry@79c57a7c, com.jk.model.Indu', '2017-02-07 16:15:12', '2017-02-22 16:15:15', 'title1');
INSERT INTO `t_project_milepost` VALUES ('3', '1', '111111111', '5689', '/upload/images?filename=051eb73d-e424-4a2b-b33f-a846716f8bcd.jpg', '1221', '2017-02-01 16:17:22', null, 'title2');
INSERT INTO `t_project_milepost` VALUES ('4', '1', '111111111', '5689', '/upload/images?filename=051eb73d-e424-4a2b-b33f-a846716f8bcd.jpg', '213213', '2017-02-02 16:17:26', null, 'title3');
INSERT INTO `t_project_milepost` VALUES ('5', '1', '111111111', '5689', '/upload/images?filename=f4f8a687-902f-4209-a34a-ae1459b4d4e6.png', '12321', '2017-02-03 16:17:29', null, 'title4');
INSERT INTO `t_project_milepost` VALUES ('7', '1', '111111111', '5689', '/upload/images?filename=f4f8a687-902f-4209-a34a-ae1459b4d4e6.png', 'sadxz', '2017-02-03 16:17:36', null, 'title5');
INSERT INTO `t_project_milepost` VALUES ('8', '1', '111111111', '5689', '/upload/images?filename=f4f8a687-902f-4209-a34a-ae1459b4d4e6.png', 'zxz', '2017-02-03 16:17:39', null, 'title6');
INSERT INTO `t_project_milepost` VALUES ('9', '1', '111111111', '5689', '/upload/images?filename=f4f8a687-902f-4209-a34a-ae1459b4d4e6.png', 'xsc', '2017-02-03 16:17:41', null, 'title7');
INSERT INTO `t_project_milepost` VALUES ('10', '1', '111111111', '5689', '/upload/images?filename=f4f8a687-902f-4209-a34a-ae1459b4d4e6.png', 'xc', '2017-02-03 16:17:46', null, 'title8');
INSERT INTO `t_project_milepost` VALUES ('11', '1', '111111111', '5689', '/upload/images?filename=f4f8a687-902f-4209-a34a-ae1459b4d4e6.png', 'as', '2017-02-03 16:17:49', null, 'title9');
INSERT INTO `t_project_milepost` VALUES ('14', '1', '111111111', '5689', null, 'as', '2017-02-03 16:17:58', null, 'title10');
INSERT INTO `t_project_milepost` VALUES ('16', '1', '111111111', '5689', null, 'xzc', '2017-02-03 16:18:07', null, 'title11');
INSERT INTO `t_project_milepost` VALUES ('17', '1', '111111111', '5689', null, 'xzc', '2017-02-03 16:18:12', null, 'title12');
INSERT INTO `t_project_milepost` VALUES ('18', '1', '111111111', '5689', null, 'zxc', '2017-02-03 16:18:15', null, 'title13');
INSERT INTO `t_project_milepost` VALUES ('19', '1', '111111111', '5689', null, 'a', '2017-02-03 16:18:18', null, 'title14');
INSERT INTO `t_project_milepost` VALUES ('20', '1', '111111111', '5689', null, 'zxca', '2017-02-03 16:18:21', null, 'title15');
INSERT INTO `t_project_milepost` VALUES ('21', '1', '111111111', '5689', null, 'zxc', '2017-02-03 16:18:24', null, 'title16');
INSERT INTO `t_project_milepost` VALUES ('22', '1', '111111111', '5689', null, 'zxc', '2017-01-31 16:18:31', null, 'title17');
INSERT INTO `t_project_milepost` VALUES ('23', '1', '111111111', '5689', null, 'zxc', '2017-02-01 16:18:35', null, 'title18');
INSERT INTO `t_project_milepost` VALUES ('26', '1', '111111111', '5689', null, 'zc', '2017-02-02 16:18:49', null, 'title19');

-- ----------------------------
-- Table structure for `t_recommend`
-- ----------------------------
DROP TABLE IF EXISTS `t_recommend`;
CREATE TABLE `t_recommend` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL COMMENT '0：首页-项目推荐位，1：子页-项目推荐位，2：榜单-企业排行',
  `recommend_id` bigint(10) DEFAULT NULL,
  `image_filename` varchar(255) DEFAULT NULL,
  `order_number` int(10) DEFAULT NULL,
  `company_logo` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `company_p_number` int(5) DEFAULT NULL,
  `company_p_amount` bigint(15) DEFAULT NULL,
  `is_use` int(10) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_recommend
-- ----------------------------
INSERT INTO `t_recommend` VALUES ('13', '0', '5', '/upload/images?filename=f07e9d9d-da12-4275-a538-3dc3e421cf68.png', '5', null, null, null, null, '1', '2017-02-23 16:33:05', '2017-02-23 16:58:25');
INSERT INTO `t_recommend` VALUES ('14', '1', '5', null, '6', null, null, null, null, '1', '2017-02-23 16:40:22', '2017-02-23 16:58:45');
INSERT INTO `t_recommend` VALUES ('17', '2', null, null, '0', null, null, null, null, '1', '2017-02-23 16:45:11', '2017-02-23 16:45:11');
INSERT INTO `t_recommend` VALUES ('18', '2', null, null, '6', '/upload/images?filename=b913040d-26a2-4eed-9acc-c819b531b022.jpg', '666', '666', '666', '0', '2017-02-23 16:47:07', '2017-02-23 16:59:52');

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '注解',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `perms` varchar(168) DEFAULT NULL COMMENT '角色标识',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '超级管理员', '我是超级管理员', 'ROEL_ADMIN', '2017-02-08 16:03:57', '2017-02-23 18:51:44');
INSERT INTO `t_role` VALUES ('3', '管理员', '我是管理员', null, '2017-02-08 17:13:50', '2017-02-09 10:10:01');
INSERT INTO `t_role` VALUES ('8', '普通用户', '我是普通用户', 'ROLE_SIMPLE', '2017-02-17 10:39:35', '2017-02-21 12:08:11');

-- ----------------------------
-- Table structure for `t_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `permission_id` bigint(20) NOT NULL COMMENT '权限id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=623 DEFAULT CHARSET=utf8 COMMENT='角色和权限中间表';

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES ('287', '9', '1', '2017-02-20 18:08:21', '2017-02-20 18:08:21');
INSERT INTO `t_role_permission` VALUES ('288', '9', '3', '2017-02-20 18:08:21', '2017-02-20 18:08:21');
INSERT INTO `t_role_permission` VALUES ('289', '9', '11', '2017-02-20 18:08:21', '2017-02-20 18:08:21');
INSERT INTO `t_role_permission` VALUES ('290', '9', '12', '2017-02-20 18:08:21', '2017-02-20 18:08:21');
INSERT INTO `t_role_permission` VALUES ('291', '9', '13', '2017-02-20 18:08:21', '2017-02-20 18:08:21');
INSERT INTO `t_role_permission` VALUES ('292', '9', '14', '2017-02-20 18:08:21', '2017-02-20 18:08:21');
INSERT INTO `t_role_permission` VALUES ('293', '9', '32', '2017-02-20 18:08:21', '2017-02-20 18:08:21');
INSERT INTO `t_role_permission` VALUES ('400', '8', '1', '2017-02-21 12:08:11', '2017-02-21 12:08:11');
INSERT INTO `t_role_permission` VALUES ('401', '8', '3', '2017-02-21 12:08:11', '2017-02-21 12:08:11');
INSERT INTO `t_role_permission` VALUES ('402', '8', '11', '2017-02-21 12:08:11', '2017-02-21 12:08:11');
INSERT INTO `t_role_permission` VALUES ('403', '8', '14', '2017-02-21 12:08:11', '2017-02-21 12:08:11');
INSERT INTO `t_role_permission` VALUES ('589', '1', '1', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('590', '1', '3', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('591', '1', '11', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('592', '1', '12', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('593', '1', '13', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('594', '1', '14', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('595', '1', '32', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('596', '1', '9', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('597', '1', '15', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('598', '1', '17', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('599', '1', '18', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('600', '1', '31', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('601', '1', '10', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('602', '1', '19', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('603', '1', '20', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('604', '1', '21', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('605', '1', '22', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('606', '1', '50', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('607', '1', '34', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('608', '1', '35', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('609', '1', '36', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('610', '1', '37', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('611', '1', '38', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('612', '1', '39', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('613', '1', '44', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('614', '1', '47', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('615', '1', '40', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('616', '1', '41', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('617', '1', '42', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('618', '1', '43', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('619', '1', '45', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('620', '1', '46', '2017-02-23 18:51:44', '2017-02-23 18:51:44');
INSERT INTO `t_role_permission` VALUES ('621', '1', '48', '2017-02-23 18:51:44', '2017-02-23 18:51:44');

-- ----------------------------
-- Table structure for `t_sensitive_word`
-- ----------------------------
DROP TABLE IF EXISTS `t_sensitive_word`;
CREATE TABLE `t_sensitive_word` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `word` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sensitive_word
-- ----------------------------
INSERT INTO `t_sensitive_word` VALUES ('19', '2016-10-20 15:01:56', '2017-02-22 16:49:55', '2', '擦尼玛');
INSERT INTO `t_sensitive_word` VALUES ('20', '2016-10-20 15:02:03', '2016-10-20 15:02:03', '0', '滚蛋');
INSERT INTO `t_sensitive_word` VALUES ('22', '2016-10-20 15:02:20', '2016-10-20 15:02:20', '0', '妈的');
INSERT INTO `t_sensitive_word` VALUES ('25', '2016-10-20 15:02:43', '2016-10-20 15:02:43', '0', '坑爹');
INSERT INTO `t_sensitive_word` VALUES ('26', '2016-10-20 15:02:49', '2016-10-20 15:02:49', '0', '孙子');
INSERT INTO `t_sensitive_word` VALUES ('27', '2016-10-20 15:02:56', '2016-10-20 15:02:56', '0', '呵呵');
INSERT INTO `t_sensitive_word` VALUES ('28', '2016-10-20 15:03:02', '2016-10-20 15:03:02', '0', '嗯嗯');
INSERT INTO `t_sensitive_word` VALUES ('29', '2016-10-20 15:03:09', '2016-10-20 15:03:09', '0', '哦哦');
INSERT INTO `t_sensitive_word` VALUES ('30', '2016-10-20 15:06:35', '2016-10-20 15:06:35', '0', '国民党');
INSERT INTO `t_sensitive_word` VALUES ('31', '2016-10-20 15:56:54', '2017-02-22 16:50:28', '6', '习近平');
INSERT INTO `t_sensitive_word` VALUES ('32', '2016-10-26 17:14:41', '2016-10-26 17:14:41', '0', '江泽民');
INSERT INTO `t_sensitive_word` VALUES ('37', '2017-02-22 16:43:34', '2017-02-22 16:43:34', '0', '鸟人');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
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
) ENGINE=InnoDB AUTO_INCREMENT=211 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('83', 'admin', '202cb962ac59075b964b07152d234b70', '202cb962ac59075b964b07152d234b70', '崔朋', '', '18568820939', '228727120@qq.com', '', '', '', null, '2017-02-07 11:45:57', '2017-02-20 16:45:07');
INSERT INTO `t_user` VALUES ('144', 'cuiuiuiui', '202cb962ac59075b964b07152d234b70', null, null, '', '8484887878787', '228@qq.com', '', '', '', '2017-02-22 18:23:48', '2017-02-13 14:47:27', '2017-02-22 18:23:48');
INSERT INTO `t_user` VALUES ('209', 'ssss', '202cb962ac59075b964b07152d234b70', null, null, '', '15001338253', '228727120@qq.com', '', '', '', null, '2017-02-27 10:31:27', '2017-02-27 10:31:27');
INSERT INTO `t_user` VALUES ('210', '小三', 'd9b1d7db4cd6e70935368a1efb10e377', null, null, null, '15001338253', '228727120@qq.com', '', '', '', null, '2017-03-03 11:55:29', '2017-03-03 11:55:29');

-- ----------------------------
-- Table structure for `t_user_comment`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_comment`;
CREATE TABLE `t_user_comment` (
  `id` bigint(15) NOT NULL AUTO_INCREMENT,
  `project_id` bigint(15) DEFAULT NULL COMMENT '项目id',
  `user_id` bigint(15) DEFAULT NULL COMMENT '会员id',
  `project_number` varchar(255) DEFAULT NULL COMMENT '项目编号',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `user_name` varchar(255) DEFAULT NULL COMMENT '会员称呼',
  `contents` varchar(500) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_comment
-- ----------------------------
INSERT INTO `t_user_comment` VALUES ('1', '1', '83', '111111112', '5689', 'admin', '112', null, null);

-- ----------------------------
-- Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='用户和角色中间表';

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '83', '1', '2017-02-13 11:02:06', '2017-02-13 11:02:08');
INSERT INTO `t_user_role` VALUES ('2', '144', '8', '2017-02-13 14:47:27', '2017-02-17 13:47:35');
INSERT INTO `t_user_role` VALUES ('5', '196', '1', '2017-02-14 16:22:16', '2017-02-14 16:22:16');
INSERT INTO `t_user_role` VALUES ('6', '197', '1', '2017-02-14 16:48:06', '2017-02-14 16:48:06');
INSERT INTO `t_user_role` VALUES ('7', '198', '8', '2017-02-20 10:27:58', '2017-02-20 10:27:58');
INSERT INTO `t_user_role` VALUES ('8', '199', '8', '2017-02-20 10:45:07', '2017-02-20 10:45:07');
INSERT INTO `t_user_role` VALUES ('9', '200', '8', '2017-02-20 17:26:26', '2017-02-20 17:26:26');
INSERT INTO `t_user_role` VALUES ('10', '201', '8', '2017-02-20 17:28:22', '2017-02-20 17:28:22');
INSERT INTO `t_user_role` VALUES ('11', '202', '8', '2017-02-20 17:56:10', '2017-02-20 17:56:10');
INSERT INTO `t_user_role` VALUES ('12', '203', '1', '2017-02-22 18:08:12', '2017-02-22 18:08:12');
INSERT INTO `t_user_role` VALUES ('13', '204', '1', '2017-02-22 18:09:24', '2017-02-22 18:09:24');
INSERT INTO `t_user_role` VALUES ('14', '205', '1', '2017-02-22 18:11:45', '2017-02-22 18:11:45');
INSERT INTO `t_user_role` VALUES ('15', '206', '8', '2017-02-22 18:14:21', '2017-02-24 11:10:03');
INSERT INTO `t_user_role` VALUES ('16', '207', '1', '2017-02-22 18:21:42', '2017-02-22 18:21:42');
INSERT INTO `t_user_role` VALUES ('17', '208', '1', '2017-02-22 18:23:48', '2017-02-22 18:23:48');
INSERT INTO `t_user_role` VALUES ('18', '209', '8', '2017-02-27 10:31:27', '2017-02-27 10:31:27');
INSERT INTO `t_user_role` VALUES ('19', '210', '1', '2017-03-03 11:55:29', '2017-03-03 11:55:29');

-- ----------------------------
-- Table structure for `t_withdrawcash`
-- ----------------------------
DROP TABLE IF EXISTS `t_withdrawcash`;
CREATE TABLE `t_withdrawcash` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `project_id` bigint(10) DEFAULT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  `user_id` bigint(10) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `withdraw_amount` bigint(10) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '1:申请提现 2:正在提现 3:提现成功 4:提现拒绝 5:提现取消',
  `refuse_reason` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_withdrawcash
-- ----------------------------
INSERT INTO `t_withdrawcash` VALUES ('1', '4', '项目1', '6', '丁发展', '5', '3', null, '2017-02-22 11:20:54', '2017-02-22 11:21:26');
INSERT INTO `t_withdrawcash` VALUES ('2', '5', '项目2', '7', '杨帆', '10', '4', null, '2017-02-22 11:20:54', '2017-02-22 11:23:47');
INSERT INTO `t_withdrawcash` VALUES ('3', '6', '金恪1', '8', '崔朋', '15', '3', null, '2017-02-22 11:20:54', '2017-02-22 11:27:39');
INSERT INTO `t_withdrawcash` VALUES ('4', '7', '项目1', '9', '丁发展', '20', '3', null, '2017-02-22 11:20:55', '2017-02-22 11:32:42');
INSERT INTO `t_withdrawcash` VALUES ('5', '8', '项目2', '10', '杨帆', '25', '3', null, '2017-02-22 11:20:55', '2017-02-22 11:34:05');
INSERT INTO `t_withdrawcash` VALUES ('6', '9', '金恪1', '11', '崔朋', '30', '3', null, '2017-02-22 11:20:55', '2017-02-22 11:34:31');
INSERT INTO `t_withdrawcash` VALUES ('7', '10', '项目1', '12', '丁发展', '35', '3', null, '2017-02-22 11:20:55', '2017-02-22 11:35:03');
INSERT INTO `t_withdrawcash` VALUES ('8', '11', '项目2', '13', '杨帆', '40', '3', null, '2017-02-22 11:20:55', '2017-02-22 11:35:41');
INSERT INTO `t_withdrawcash` VALUES ('9', '12', '金恪1', '14', '崔朋', '45', '3', null, '2017-02-22 11:20:55', '2017-02-22 11:36:15');
INSERT INTO `t_withdrawcash` VALUES ('10', '13', '项目1', '15', '丁发展', '50', '3', null, '2017-02-22 11:20:55', '2017-02-22 11:37:04');
INSERT INTO `t_withdrawcash` VALUES ('11', '14', '项目2', '16', '杨帆', '55', '4', null, '2017-02-22 11:20:55', '2017-02-22 11:37:09');
INSERT INTO `t_withdrawcash` VALUES ('12', '15', '金恪1', '17', '崔朋', '60', '3', null, '2017-02-22 11:20:55', '2017-02-22 11:37:13');
INSERT INTO `t_withdrawcash` VALUES ('13', '16', '项目1', '18', '丁发展', '65', '4', null, '2017-02-22 11:20:55', '2017-02-22 11:37:16');
INSERT INTO `t_withdrawcash` VALUES ('14', '17', '项目2', '19', '杨帆', '70', '3', null, '2017-02-22 11:20:55', '2017-02-22 17:19:41');
INSERT INTO `t_withdrawcash` VALUES ('15', '18', '金恪1', '20', '崔朋', '75', '4', null, '2017-02-22 11:20:55', '2017-02-22 17:19:50');
INSERT INTO `t_withdrawcash` VALUES ('16', '19', '项目1', '21', '丁发展', '80', '3', null, '2017-02-22 11:20:55', '2017-02-22 17:19:53');
INSERT INTO `t_withdrawcash` VALUES ('17', '20', '项目2', '22', '杨帆', '85', '4', '不行', '2017-02-22 11:20:55', '2017-02-23 17:31:54');
INSERT INTO `t_withdrawcash` VALUES ('18', '21', '金恪1', '23', '崔朋', '90', '3', null, '2017-02-22 11:20:55', '2017-02-23 17:42:56');
INSERT INTO `t_withdrawcash` VALUES ('19', '22', '项目2', '24', '崔朋', '95', '4', '傻逼', '2017-02-22 11:20:55', '2017-02-23 17:43:15');
INSERT INTO `t_withdrawcash` VALUES ('20', '23', '项目2', '25', '崔朋', '100', '4', '内容不符222', '2017-02-22 11:20:55', '2017-02-23 17:44:24');
INSERT INTO `t_withdrawcash` VALUES ('21', '23', '项目3', '26', '崔朋', '105', '4', '陈22', '2017-02-22 11:20:55', '2017-02-23 17:44:51');
INSERT INTO `t_withdrawcash` VALUES ('22', '23', '项目4', '27', '崔朋', '110', '1', null, '2017-02-22 11:20:55', '2017-02-22 11:20:55');
INSERT INTO `t_withdrawcash` VALUES ('23', '23', '项目5', '28', '崔朋', '115', '1', null, '2017-02-22 11:20:55', '2017-02-22 11:20:55');
INSERT INTO `t_withdrawcash` VALUES ('24', '23', '项目6', '29', '崔朋', '120', '1', null, '2017-02-22 11:20:55', '2017-02-22 11:20:55');
