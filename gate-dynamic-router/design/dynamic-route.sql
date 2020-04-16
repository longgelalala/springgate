/*
 Navicat Premium Data Transfer

 Source Server         : mysqlConnection
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : dynamic-route

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 13/02/2020 13:02:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dynamic_version
-- ----------------------------
DROP TABLE IF EXISTS `dynamic_version`;
CREATE TABLE `dynamic_version`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键、自动增长、版本号',
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dynamic_version
-- ----------------------------
INSERT INTO `dynamic_version` VALUES (1, '2020-02-12 16:46:14');
INSERT INTO `dynamic_version` VALUES (4, '2020-02-12 17:03:34');

-- ----------------------------
-- Table structure for gateway_routes
-- ----------------------------
DROP TABLE IF EXISTS `gateway_routes`;
CREATE TABLE `gateway_routes`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `route_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由id',
  `route_uri` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '转发目标uri',
  `route_order` int(11) NULL DEFAULT NULL COMMENT '路由执行顺序',
  `predicates` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '断言字符串集合，字符串结构：[{\r\n                \"name\":\"Path\",\r\n                \"args\":{\r\n                   \"pattern\" : \"/zy/**\"\r\n                }\r\n              }]',
  `filters` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '过滤器字符串集合，字符串结构：{\r\n              	\"name\":\"StripPrefix\",\r\n              	 \"args\":{\r\n              	 	\"_genkey_0\":\"1\"\r\n              	 }\r\n              }',
  `is_ebl` tinyint(1) NULL DEFAULT NULL COMMENT '是否启用',
  `is_del` tinyint(1) NULL DEFAULT NULL COMMENT '是否删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gateway_routes
-- ----------------------------
INSERT INTO `gateway_routes` VALUES (3, 'provider-client', 'http://localhost:5502/test', 1, '[\n                {\n                    \"name\": \"Path\",\n                    \"args\": {\n                        \"pattern\": \"/test\"\n                    }\n                }\n            ]', '[\n                {\n                    \"name\": \"RewritePath\"\n                \n                }\n            ]', 0, 0, '2020-02-12 17:03:26', '2020-02-12 17:03:26');

-- ----------------------------
-- Table structure for page_permissions
-- ----------------------------
DROP TABLE IF EXISTS `page_permissions`;
CREATE TABLE `page_permissions`  (
  `id` bigint(20) NOT NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `permission_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `routeId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of page_permissions
-- ----------------------------
INSERT INTO `page_permissions` VALUES (0, 'ADMIN', '{\"button\":\"true\"}', '2020-02-13 12:57:13', 'provider-client');

SET FOREIGN_KEY_CHECKS = 1;
