-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 17, 2024 at 09:00 AM
-- Server version: 10.4.20-MariaDB
-- PHP Version: 7.3.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `melisa_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `accidentreport`
--

CREATE TABLE `accidentreport` (
  `id` int(11) NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `accidentreport`
--

INSERT INTO `accidentreport` (`id`, `description`) VALUES
(1, '0');

-- --------------------------------------------------------

--
-- Table structure for table `falsealarm`
--

CREATE TABLE `falsealarm` (
  `id` int(11) NOT NULL,
  `falseState` text NOT NULL,
  `textState` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `falsealarm`
--

INSERT INTO `falsealarm` (`id`, `falseState`, `textState`) VALUES
(1, '0', '1');

-- --------------------------------------------------------

--
-- Table structure for table `readings`
--

CREATE TABLE `readings` (
  `id` int(11) NOT NULL,
  `lattitude` text NOT NULL,
  `longitude` text NOT NULL,
  `vibration` text NOT NULL,
  `accelerometer` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `readings`
--

INSERT INTO `readings` (`id`, `lattitude`, `longitude`, `vibration`, `accelerometer`) VALUES
(1, '-17.842920303345', '31.010366439819', '0', '2');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accidentreport`
--
ALTER TABLE `accidentreport`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `falsealarm`
--
ALTER TABLE `falsealarm`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `readings`
--
ALTER TABLE `readings`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accidentreport`
--
ALTER TABLE `accidentreport`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `falsealarm`
--
ALTER TABLE `falsealarm`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `readings`
--
ALTER TABLE `readings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
