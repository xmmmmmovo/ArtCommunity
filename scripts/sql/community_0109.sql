-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- 主机： localhost
-- 生成日期： 2020-01-09 22:11:50
-- 服务器版本： 5.7.26-log
-- PHP 版本： 7.0.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `community`
--

-- --------------------------------------------------------

--
-- 表的结构 `admin`
--

CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL,
  `admin_name` varchar(30) NOT NULL,
  `admin_email` varchar(50) NOT NULL,
  `admin_password` varchar(300) NOT NULL,
  `admin_avatar` varchar(200) NOT NULL DEFAULT 'https://sqldesign-1258573901.cos.ap-beijing.myqcloud.com/pic_null.jpg',
  `register_time` bigint(20) NOT NULL,
  `token` varchar(36) DEFAULT NULL,
  `roles` char(10) NOT NULL DEFAULT 'editor'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `admin`
--

INSERT INTO `admin` (`id`, `admin_name`, `admin_email`, `admin_password`, `admin_avatar`, `register_time`, `token`, `roles`) VALUES
(20, 'daqq', 'test', '$2a$10$5XXNCNiQX2QrHPeBOWFqgOPgM65XHL07cfxsdA3fzzS7CBGXUlzWK', 'http://q3lynq058.bkt.clouddn.com/2a9b4963f6246b603c6cdecbe1f81a4c500fa245.jpg', 1578174022842, 'Jl9eTXBaRPq7-iO6p7ZZeg', 'admin'),
(24, 'wer', 'wer', '$2a$10$nqMrv4PFpeQhteJU5jhyPOsn0kxMmqAO7T07BeWL3XW.W4IKSYcy6', 'https://sqldesign-1258573901.cos.ap-beijing.myqcloud.com/pic_null.jpg', 1578174211223, 'y6akKVcdTEKJBxLhULMBHA', 'editor'),
(26, 'werss', 'wer', '$2a$10$kEbCBqyrk70C3KM5/tpZJ.Rag3WdTiURfZ9ctWT5kKqEWZge6oB7a', 'https://sqldesign-1258573901.cos.ap-beijing.myqcloud.com/pic_null.jpg', 1578174448158, 'W2vVLij7SCKNqju7-XF5BQ', 'editor'),
(28, 'dffg', 'dffg', '$2a$10$URlksnKGxnzY0UYwwei5rOs8SMJ2yEWqR2IOPKc64MoHM9UVgG74S', 'https://sqldesign-1258573901.cos.ap-beijing.myqcloud.com/pic_null.jpg', 1578174515451, 'tgbb90jNTViTwGw_mXkQKw', 'editor'),
(29, 'zxc', 'zxc', '$2a$10$VbO2HPhAfS8fugOFc5vv/.r1yokgWUb/qyXhrZXlBSTBj9xjIx.QC', 'https://sqldesign-1258573901.cos.ap-beijing.myqcloud.com/pic_null.jpg', 1578174559125, '18PKnFM2TFG7aNWaXrCDPA', 'editor'),
(30, 'dfs', 'sdf', '$2a$10$I9xrujOOiGL7iGc8xfnMQOIxt8AWqYOV7ht8bR7h439oF.NzlUakG', 'https://sqldesign-1258573901.cos.ap-beijing.myqcloud.com/pic_null.jpg', 1578174654438, 'SA4e2aXCRQyjPa2_5br1qA', 'editor'),
(31, 'asd', 'asd', '$2a$10$61DNCUaq4Zf7y9UaDDwXP.taOddsVDXCozMJzlx15y/GTzwVOLKgK', 'https://sqldesign-1258573901.cos.ap-beijing.myqcloud.com/pic_null.jpg', 1578174721048, 'af-sTeXpQkCLYhfx1b8rqw', 'editor'),
(32, 'dggvc', 'dfbvc', '$2a$10$0JH.SzAzW4aWs1WCSagBFeuge/EtY6qngWmLNziQUG8UgukhwvE1S', 'https://sqldesign-1258573901.cos.ap-beijing.myqcloud.com/pic_null.jpg', 1578174898469, 'SSSKcKikSeCp-ua0V33YWQ', 'editor'),
(33, 'asdfd', 'sdf', '$2a$10$faWJ6fVGia6tk0mtjWX14el9akPOGkIvyS3qkuWCe/CqoJjUMh3sy', 'https://sqldesign-1258573901.cos.ap-beijing.myqcloud.com/pic_null.jpg', 1578175097903, 'szzduwn6Qe2nkNP83iLF6A', 'editor'),
(35, 'asdfdsdds', 'sdf', '$2a$10$RaJ5iM72a3RKtn7GQNm5l.m8SsEwTbRa6aMkcOm0GexoiCE1DRbHm', 'https://sqldesign-1258573901.cos.ap-beijing.myqcloud.com/pic_null.jpg', 1578175115237, 'sQhGyfoBRSOKp8PgosKjYA', 'editor'),
(36, 'admin', 'admin', '$2a$10$4gpYfuHC4Mbwk76N3.l/nuT/i/iD6x15TQRT30LJgQCXIW9QFfRdW', 'http://q3lynq058.bkt.clouddn.com/1578435581361顶部2.jpg', 1578179433114, 'syq2Q8PrSAGiz2N5OY3d-Q', 'admin'),
(42, 'fgfdg', 'jhj', '$2a$10$hEBSkyOCLU9t2sisTkbZuuu5Zg4AfR.Ab6U59yFpTA.egWjHO2Jom', 'https://sqldesign-1258573901.cos.ap-beijing.myqcloud.com/pic_null.jpg', 1578183475269, 'E3hDIuWPQWewJUEDlEHVMQ', 'admin'),
(43, 'editor', 'editor', '$2a$10$W048q8kuHzADq8vIcoF3/OFxGO3E5zvUpA9/JS1dOkmZSPvXe1V.W', 'http://q3lynq058.bkt.clouddn.com/15784356865142.jpg', 1578435688161, 'l-NHxRYuQVqrJ3IFJWRv-g', 'editor');

-- --------------------------------------------------------

--
-- 表的结构 `art`
--

CREATE TABLE `art` (
  `id` bigint(20) NOT NULL,
  `art_name` varchar(50) NOT NULL,
  `art_author` bigint(20) NOT NULL,
  `create_time` bigint(20) NOT NULL,
  `modified_time` bigint(20) NOT NULL,
  `art_size` bigint(20) DEFAULT NULL,
  `art_tag` bigint(20) NOT NULL,
  `art_like_num` bigint(20) NOT NULL DEFAULT '0',
  `art_comment_num` bigint(20) NOT NULL DEFAULT '0',
  `art_content` varchar(400) NOT NULL DEFAULT '什么也没说',
  `art_pic_url` varchar(200) NOT NULL DEFAULT 'https://sqldesign-1258573901.cos.ap-beijing.myqcloud.com/pic_null.jpg'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `art`
--

INSERT INTO `art` (`id`, `art_name`, `art_author`, `create_time`, `modified_time`, `art_size`, `art_tag`, `art_like_num`, `art_comment_num`, `art_content`, `art_pic_url`) VALUES
(123, '1', 1206, 1578474350672, 1578479081000, 5, 1, 2, 0, 'sfasfsadfsdfasf', 'http://q3lynq058.bkt.clouddn.com/1578474347730巴黎圣母院.jpg'),
(124, 'asdfdas', 1207, 1578474484852, 1578474484852, 1, 2, 3, 0, '真香', 'http://q3lynq058.bkt.clouddn.com/1578474481053art1.jpg'),
(125, 'asd', 1206, 1578476422315, 1578476422315, 5, 5, 0, 0, 'asdfdas', 'http://q3lynq058.bkt.clouddn.com/1578476420944巴黎圣母院.jpg'),
(126, '啊啊啊', 1207, 1578578746427, 1578578746427, 1, 2, 0, 0, 'dasasdfas', 'http://q3lynq058.bkt.clouddn.com/15785787352432c0b8b014c086e067c09f5a908087bf408d1cbc6.jpg'),
(127, 'asdf', 1207, 1578578767329, 1578578767329, 5, 2, 0, 0, 'asdfasdfasdfas', 'http://q3lynq058.bkt.clouddn.com/15785787638482d80eafcc3cec3fd0b6e87cadc88d43f8694274b.jpg');

-- --------------------------------------------------------

--
-- 表的结构 `article`
--

CREATE TABLE `article` (
  `id` bigint(20) NOT NULL,
  `article_content` text NOT NULL,
  `article_front` varchar(200) NOT NULL DEFAULT 'https://sqldesign-1258573901.cos.ap-beijing.myqcloud.com/pic_null.jpg',
  `article_title` varchar(100) NOT NULL,
  `create_time` bigint(20) NOT NULL,
  `article_author` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `article`
--

INSERT INTO `article` (`id`, `article_content`, `article_front`, `article_title`, `create_time`, `article_author`) VALUES
(57, '今日是赫鲁晓夫的胜利呢', 'http://q3lynq058.bkt.clouddn.com/1.jpg', '赛尔号攻', 1578393061978, 1206),
(58, '确实很难弄好吧Zzxcvzcxv', 'http://q3lynq058.bkt.clouddn.com/15783938635292.jpg', '物联网好难啊啊啊啊aa', 1578393868991, 1206),
(59, '困了', 'http://q3lynq058.bkt.clouddn.com/15783938795083.jpg', '还有没有那么难的啊啊啊', 1578393882792, 1206),
(60, 'asdfsdaf', 'http://q3lynq058.bkt.clouddn.com/15784283390874.jpg', 'asd', 1578428341648, 1206),
(61, '好像吃饭啊 啊啊昂昂啊啊啊啊昂昂啊啊啊啊啊啊', 'http://q3lynq058.bkt.clouddn.com/15784541121353.jpg', '能', 1578454117367, 1206),
(62, '闪闪闪', 'http://q3lynq058.bkt.clouddn.com/15784657753626.jpg', '114514', 1578465777871, 1206);

-- --------------------------------------------------------

--
-- 表的结构 `comment`
--

CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL,
  `comment_by` bigint(20) NOT NULL,
  `comment_content` varchar(300) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `comment_time` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `like_list`
--

CREATE TABLE `like_list` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `art_id` bigint(20) NOT NULL,
  `like_time` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `like_list`
--

INSERT INTO `like_list` (`id`, `user_id`, `art_id`, `like_time`) VALUES
(16, 1206, 124, 1578479182085),
(17, 1206, 124, 1578479185388);

-- --------------------------------------------------------

--
-- 表的结构 `size`
--

CREATE TABLE `size` (
  `id` bigint(20) NOT NULL,
  `length` int(11) NOT NULL,
  `height` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `size`
--

INSERT INTO `size` (`id`, `length`, `height`) VALUES
(1, 1920, 1080),
(5, 640, 480),
(6, 324, 23432);

-- --------------------------------------------------------

--
-- 表的结构 `tag`
--

CREATE TABLE `tag` (
  `id` bigint(20) NOT NULL,
  `tag_name` varchar(20) NOT NULL,
  `usage_count` bigint(20) DEFAULT '0',
  `tag_describe` varchar(1000) NOT NULL DEFAULT 'null'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `tag`
--

INSERT INTO `tag` (`id`, `tag_name`, `usage_count`, `tag_describe`) VALUES
(1, '当代', 0, '从1970年到今天，当代艺术史代表着艺术领域中最为多样化和广泛收集的时代。当\r\n\r\n代艺术家从当下汲取灵感，挑战观众从另一个角度思考现实。'),
(2, '印象派', 0, '印象派人士从根本上挑战艺术创作的传统，拒绝既定的学术风格，转而使用宽松的笔触来捕捉大气景观。从立体主义到超现实主义，随后的现代艺术运动延续了这种前卫的敏感性，引入了激进的新表现形式，例如抽象绘画和概念雕塑。'),
(4, '摄影', 0, 'Photography helps people to see.自从1839年这项技术问世以来，摄影已成为一种必不可少的艺术媒介，它使艺术家能够捕捉街头短暂的瞬间，构建虚构的世界来迷惑观众并呈现新的抽象形式。'),
(5, '战后', 0, '战后的艺术史时期大约在1945年至1970年之间，包括一些20世纪最具标志性和最受欢迎的艺术活动，包括抽象表现主义、波普艺术和极简主义。'),
(6, '20世纪之前', 0, '莱昂纳多·达·芬奇曾经写道：“最崇高的荣幸就是理解喜悦。”从古代雕塑到文艺复兴时期的素描，具有数百年历史的艺术品常常促使人们研究艺术史，并邀请参观者更多地了解使这些物品栩栩如生的背景。');

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `register_time` bigint(20) NOT NULL,
  `user_name` varchar(30) NOT NULL,
  `user_email` varchar(100) NOT NULL,
  `user_password` varchar(300) NOT NULL,
  `user_bio` varchar(200) DEFAULT '这个人什么都没写',
  `token` varchar(36) NOT NULL,
  `user_avatar` varchar(200) NOT NULL DEFAULT 'https://sqldesign-1258573901.cos.ap-beijing.myqcloud.com/pic_null.jpg',
  `user_sex` char(10) NOT NULL DEFAULT '男'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`id`, `register_time`, `user_name`, `user_email`, `user_password`, `user_bio`, `token`, `user_avatar`, `user_sex`) VALUES
(1206, 1578393021278, 'zhazha', '1@1.com', '$2a$10$0uMtAjaKgtMeyLxwVeHqne3AKrTuLnYfw53pfEhUNhPsX.BW762km', '这个人什么都没写', 'g4fK4audR5qdoN5OAwjzkw', 'http://q3lynq058.bkt.clouddn.com/15784162161576.jpg', '男'),
(1207, 1578466204792, '111111', '111@111.com', '$2a$10$u9te717XC0nMQwj0RuGgNO62x6yLjaqtJ3FaBa5htZ4VDbxuIOe2.', '这个人什么都没写', 'cSYjxndoQIOyikI4zx9dsQ', 'http://q3lynq058.bkt.clouddn.com/15784690437732.png', '男'),
(1210, 1578466254701, '12', '12@12.com', '$2a$10$BniGDyx3q8It6xOg9/E9EeVBn2do7KKaroieBEjmRCRf5DxNm.WAO', '这个人什么都没写', 'YBUPTqVhQkGBGppdnJgx_A', 'https://sqldesign-1258573901.cos.ap-beijing.myqcloud.com/pic_null.jpg', '男'),
(1212, 1578578793882, 'asdfa', 'asdfsa', '$2a$10$x9LLvFUxiEKTd3uQ0WCG7emPdh6kEmPSIVpYVUOHUyU.PGsebAIzu', '这个人什么都没写', 'ht-AzL9PSp6JIUma0_QO5A', 'http://q3lynq058.bkt.clouddn.com/15785787904323c41df1f4134970a609481eb99cad1c8a5865da5.jpg', '女');

--
-- 转储表的索引
--

--
-- 表的索引 `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `admin_admin_name_uindex` (`admin_name`);

--
-- 表的索引 `art`
--
ALTER TABLE `art`
  ADD PRIMARY KEY (`id`),
  ADD KEY `arts_user_id_fk` (`art_author`),
  ADD KEY `art_size_id_fk` (`art_size`),
  ADD KEY `art_tag_id_fk` (`art_tag`);

--
-- 表的索引 `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`id`),
  ADD KEY `article_user_id_fk` (`article_author`);

--
-- 表的索引 `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `comment_user_id_fk` (`comment_by`),
  ADD KEY `comment_art_id_fk` (`parent_id`);

--
-- 表的索引 `like_list`
--
ALTER TABLE `like_list`
  ADD PRIMARY KEY (`id`),
  ADD KEY `like_list_art_id_fk` (`art_id`),
  ADD KEY `like_list_user_id_fk` (`user_id`);

--
-- 表的索引 `size`
--
ALTER TABLE `size`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `tag`
--
ALTER TABLE `tag`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user_user_email_uindex` (`user_email`),
  ADD UNIQUE KEY `user_user_name_uindex` (`user_name`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `admin`
--
ALTER TABLE `admin`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- 使用表AUTO_INCREMENT `art`
--
ALTER TABLE `art`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=128;

--
-- 使用表AUTO_INCREMENT `article`
--
ALTER TABLE `article`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- 使用表AUTO_INCREMENT `comment`
--
ALTER TABLE `comment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- 使用表AUTO_INCREMENT `like_list`
--
ALTER TABLE `like_list`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- 使用表AUTO_INCREMENT `size`
--
ALTER TABLE `size`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- 使用表AUTO_INCREMENT `tag`
--
ALTER TABLE `tag`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- 使用表AUTO_INCREMENT `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1213;

--
-- 限制导出的表
--

--
-- 限制表 `art`
--
ALTER TABLE `art`
  ADD CONSTRAINT `art_size_id_fk` FOREIGN KEY (`art_size`) REFERENCES `size` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `art_tag_id_fk` FOREIGN KEY (`art_tag`) REFERENCES `tag` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `arts_user_id_fk` FOREIGN KEY (`art_author`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- 限制表 `article`
--
ALTER TABLE `article`
  ADD CONSTRAINT `article_user_id_fk` FOREIGN KEY (`article_author`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_art_id_fk` FOREIGN KEY (`parent_id`) REFERENCES `art` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `comment_user_id_fk` FOREIGN KEY (`comment_by`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- 限制表 `like_list`
--
ALTER TABLE `like_list`
  ADD CONSTRAINT `like_list_art_id_fk` FOREIGN KEY (`art_id`) REFERENCES `art` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `like_list_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
