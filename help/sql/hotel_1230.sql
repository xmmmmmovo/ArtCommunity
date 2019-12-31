-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- 主机： localhost
-- 生成日期： 2019-12-30 22:30:42
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
-- 数据库： `hotel`
--

-- --------------------------------------------------------

--
-- 表的结构 `CHECKIN`
--

CREATE TABLE `CHECKIN` (
  `CIRno` int(11) NOT NULL,
  `CIname` varchar(30) DEFAULT NULL,
  `CIid` varchar(30) DEFAULT NULL,
  `CItime` varchar(30) DEFAULT NULL,
  `CImon` varchar(30) DEFAULT NULL,
  `CIdays` varchar(30) DEFAULT NULL,
  `CIMname` varchar(30) DEFAULT NULL,
  `CIRstate` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `CHECKIN`
--

INSERT INTO `CHECKIN` (`CIRno`, `CIname`, `CIid`, `CItime`, `CImon`, `CIdays`, `CIMname`, `CIRstate`) VALUES
(1, 'a', '11', 'null', '0', '0', 'JOSH', '已住');

-- --------------------------------------------------------

--
-- 表的结构 `history`
--

CREATE TABLE `history` (
  `Hno` int(11) DEFAULT NULL,
  `HCname` varchar(30) DEFAULT NULL,
  `HCid` varchar(30) DEFAULT NULL,
  `Hatime` varchar(30) DEFAULT NULL,
  `Hltime` varchar(30) DEFAULT NULL,
  `Hmon` varchar(30) DEFAULT NULL,
  `HMname` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `Manager`
--

CREATE TABLE `Manager` (
  `Mno` varchar(20) NOT NULL,
  `Mpassword` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `Manager`
--

INSERT INTO `Manager` (`Mno`, `Mpassword`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- 表的结构 `Room`
--

CREATE TABLE `Room` (
  `Rno` int(11) NOT NULL,
  `Rtype` varchar(50) DEFAULT NULL,
  `Rstate` char(10) DEFAULT NULL,
  `ROstate` char(10) DEFAULT NULL,
  `Rprice` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `Room`
--

INSERT INTO `Room` (`Rno`, `Rtype`, `Rstate`, `ROstate`, `Rprice`) VALUES
(1, '大床房', '空', '否', 100);

--
-- 转储表的索引
--

--
-- 表的索引 `CHECKIN`
--
ALTER TABLE `CHECKIN`
  ADD PRIMARY KEY (`CIRno`);

--
-- 表的索引 `Manager`
--
ALTER TABLE `Manager`
  ADD PRIMARY KEY (`Mno`);

--
-- 表的索引 `Room`
--
ALTER TABLE `Room`
  ADD PRIMARY KEY (`Rno`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
