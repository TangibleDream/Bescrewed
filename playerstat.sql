-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 23, 2012 at 10:46 PM
-- Server version: 5.5.16
-- PHP Version: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `player`
--

-- --------------------------------------------------------

--
-- Table structure for table `playerstat`
--

CREATE TABLE IF NOT EXISTS `playerstat` (
  `playernum` int(11) NOT NULL AUTO_INCREMENT,
  `playername` varchar(25) NOT NULL,
  `experience` int(11) NOT NULL,
  `gold` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  PRIMARY KEY (`playernum`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `playerstat`
--

INSERT INTO `playerstat` (`playernum`, `playername`, `experience`, `gold`, `level`) VALUES
(1, 'Dunstan', 3, 3, 1),
(2, 'Spanky', 0, 0, 1),
(3, 'Spanky', 0, 0, 1),
(4, 'Grace', 0, 0, 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
