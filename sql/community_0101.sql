-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- 主机： localhost
-- 生成日期： 2020-01-01 18:32:40
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
  `register_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `admin`
--

INSERT INTO `admin` (`id`, `admin_name`, `admin_email`, `admin_password`, `admin_avatar`, `register_time`) VALUES
(2, 'admin', 'admin', '$2a$10$VjkuIbIZMDR0QepanzfNOu6fvEqYtzdlzriaiJ0FFtnMNzwU8A2pK', NULL, '2019-12-31 08:16:53');

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
  `user_bio` varchar(200) DEFAULT '这个人什么都没写'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转储表的索引
--

--
-- 表的索引 `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `arts`
--
ALTER TABLE `arts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `arts_user_id_fk` (`art_author`);

--
-- 表的索引 `log`
--
ALTER TABLE `log`
  ADD PRIMARY KEY (`id`),
  ADD KEY `log_user_id_fk` (`operator_id`);

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
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 使用表AUTO_INCREMENT `arts`
--
ALTER TABLE `arts`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `log`
--
ALTER TABLE `log`
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
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- 限制导出的表
--

--
-- 限制表 `arts`
--
ALTER TABLE `arts`
  ADD CONSTRAINT `arts_user_id_fk` FOREIGN KEY (`art_author`) REFERENCES `user` (`id`) ON DELETE CASCADE;

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
