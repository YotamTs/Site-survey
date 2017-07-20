-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 19, 2017 at 10:01 PM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ex3`
--

-- --------------------------------------------------------

--
-- Table structure for table `results`
--

CREATE TABLE `results` (
  `id` int(11) NOT NULL,
  `pollname` varchar(64) NOT NULL,
  `choice` varchar(64) NOT NULL,
  `userid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `results`
--

INSERT INTO `results` (`id`, `pollname`, `choice`, `userid`) VALUES
(1, 'programming_language', 'C++', 22222),
(2, 'color', 'Blue', 22222),
(3, 'color', 'White', 7777),
(4, 'programming_language', 'C++', 7777),
(5, 'programming_language', 'Python', 4444),
(6, 'programming_language', 'C', 123),
(7, 'color', 'Blue', 123),
(8, 'programming_language', 'Python', 1234),
(9, 'color', 'Blue', 777),
(10, 'color', 'Blue', 666),
(11, 'programming_language', 'Python', 666),
(12, 'color', 'Yellow', 1234),
(13, 'programming_language', 'Python', 88),
(14, 'color', 'Blue', 22),
(15, 'color', 'Blue', 18),
(16, 'programming_language', 'C++', 11),
(17, 'color', 'Blue', 11);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `lastname` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `lastname`) VALUES
(11, 'mimi'),
(18, 'liron'),
(22, 'mama'),
(33, 'shimi\r\n'),
(88, 'yolo'),
(123, 'baba'),
(666, 'shaked'),
(777, 'shuli'),
(1234, 'babi'),
(4444, 'vichman'),
(7777, 'amenou'),
(11111, 'cohen'),
(22222, 'tsorfi'),
(33333, 'patrick');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `results`
--
ALTER TABLE `results`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `results`
--
ALTER TABLE `results`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33334;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
