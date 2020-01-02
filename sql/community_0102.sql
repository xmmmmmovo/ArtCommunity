-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- 主机： localhost
-- 生成日期： 2020-01-02 17:59:50
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
  `admin_avatar` varchar(100) DEFAULT NULL,
  `register_time` datetime NOT NULL,
  `token` varchar(36) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `admin`
--

INSERT INTO `admin` (`id`, `admin_name`, `admin_email`, `admin_password`, `admin_avatar`, `register_time`, `token`) VALUES
(3, 'zha', 'zha', '$2a$10$g.Nos0uXTXk8eZ/RAQe5KOWgIwpxtDapr2SMszgioFaoYnMs17T8u', NULL, '2020-01-02 03:12:35', '_Y6XAt6qTaeiAmm6f97wCA'),
(5, 'zhaa', 'zha', '$2a$10$VHaK.5w8yHWIGBRRCuQWnuxeyGA.EubbEqRBsVJkbLAT1hIvIfy6q', NULL, '2020-01-02 03:35:11', 'K9IDEMY1S9GJ8J4awRdIaA');

-- --------------------------------------------------------

--
-- 表的结构 `arts`
--

CREATE TABLE `arts` (
  `id` bigint(20) NOT NULL,
  `art_name` varchar(50) NOT NULL,
  `art_author` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `modified_time` datetime NOT NULL,
  `art_size` bigint(20) DEFAULT NULL,
  `art_tag` int(11) DEFAULT NULL,
  `art_like_num` bigint(20) NOT NULL DEFAULT '0',
  `art_commit_num` bigint(20) NOT NULL DEFAULT '0',
  `art_content` varchar(400) NOT NULL DEFAULT '什么也没说',
  `art_pic_url` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `comment`
--

CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL,
  `comment_by` bigint(20) NOT NULL,
  `comment_content` varchar(300) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `comment_time` datetime NOT NULL,
  `comment_like_count` bigint(20) NOT NULL DEFAULT '0',
  `comment_count` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `log`
--

CREATE TABLE `log` (
  `id` bigint(20) NOT NULL,
  `operator_id` bigint(20) NOT NULL,
  `operator_identity` char(10) NOT NULL,
  `operate_content` varchar(100) NOT NULL,
  `operate_type` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `size`
--

CREATE TABLE `size` (
  `id` bigint(20) NOT NULL,
  `length` int(11) NOT NULL,
  `height` int(11) NOT NULL,
  `create_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `tag`
--

CREATE TABLE `tag` (
  `id` bigint(20) NOT NULL,
  `tag_name` varchar(20) NOT NULL,
  `register_time` datetime NOT NULL,
  `usage_count` bigint(20) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `register_time` datetime NOT NULL,
  `user_name` varchar(30) NOT NULL,
  `user_email` varchar(100) NOT NULL,
  `user_password` varchar(300) NOT NULL,
  `user_bio` varchar(200) DEFAULT '这个人什么都没写',
  `token` varchar(36) NOT NULL,
  `user_avatar` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`id`, `register_time`, `user_name`, `user_email`, `user_password`, `user_bio`, `token`, `user_avatar`) VALUES
(1, '2020-01-02 02:12:09', '11', '1@1.com', '$2a$10$PbT9MIkITTHhIce8Ou7t5eMKslZzO19PAfPxyZ2yGyIF6t1ZvRX62', '这个人什么都没写', 'lOIUo2qxTVaFvP-r-vSFwQ', NULL),
(3, '2020-01-02 02:20:27', '11', '11@1.com', '$2a$10$0ha3oNieMoK7XZ9EqGyXsO0Py/uVT1UhnmDrqExLb63lNYDJFhLZK', '这个人什么都没写', '4yjVrFpYTp6vrSPPs3G4Tg', NULL),
(5, '2020-01-02 02:28:49', '11', '111@1.com', '$2a$10$8hrCfM3aguBWnWy9vLTVLeuGfBnX0uQiUS1C9t0jIsUcVKIIQAB3u', '这个人什么都没写', 'M94PUFz3S6O80rRVKmUUnA', NULL),
(6, '2020-01-02 03:10:35', '11', '1111@1.com', '$2a$10$Tq4DKXNkiLT.FwFQ2BeKk..lT7tFi0N7CV0vClmzOq/88vJVJW6eC', '这个人什么都没写', 'WrdaHdVbRSqpoQkCkFeccQ', NULL);

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
-- 表的索引 `arts`
--
ALTER TABLE `arts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `arts_user_id_fk` (`art_author`);

--
-- 表的索引 `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `comment_user_id_fk` (`comment_by`),
  ADD KEY `comment_user_id_fk_2` (`parent_id`);

--
-- 表的索引 `log`
--
ALTER TABLE `log`
  ADD PRIMARY KEY (`id`),
  ADD KEY `log_user_id_fk` (`operator_id`);

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
  ADD UNIQUE KEY `user_user_email_uindex` (`user_email`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `admin`
--
ALTER TABLE `admin`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- 使用表AUTO_INCREMENT `arts`
--
ALTER TABLE `arts`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `comment`
--
ALTER TABLE `comment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `log`
--
ALTER TABLE `log`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `size`
--
ALTER TABLE `size`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `tag`
--
ALTER TABLE `tag`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- 限制导出的表
--

--
-- 限制表 `arts`
--
ALTER TABLE `arts`
  ADD CONSTRAINT `arts_user_id_fk` FOREIGN KEY (`art_author`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- 限制表 `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_user_id_fk` FOREIGN KEY (`comment_by`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `comment_user_id_fk_2` FOREIGN KEY (`parent_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- 限制表 `log`
--
ALTER TABLE `log`
  ADD CONSTRAINT `log_admin_id_fk` FOREIGN KEY (`operator_id`) REFERENCES `admin` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `log_user_id_fk` FOREIGN KEY (`operator_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
