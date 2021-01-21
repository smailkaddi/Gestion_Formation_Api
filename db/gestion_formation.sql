-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 21, 2021 at 10:40 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gestion_formation`
--

-- --------------------------------------------------------

--
-- Table structure for table `employé`
--

CREATE TABLE `employé` (
  `id_employee` int(200) NOT NULL,
  `matricule` varchar(200) NOT NULL,
  `nom` varchar(200) NOT NULL,
  `prénom` varchar(200) NOT NULL,
  `login` varchar(200) NOT NULL,
  `mot_de_passe` varchar(200) NOT NULL,
  `ville` varchar(200) NOT NULL,
  `type` varchar(200) NOT NULL,
  `Request` varchar(200) NOT NULL,
  `code_formation` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employé`
--

INSERT INTO `employé` (`id_employee`, `matricule`, `nom`, `prénom`, `login`, `mot_de_passe`, `ville`, `type`, `Request`, `code_formation`) VALUES
(37, 'MAR123', 'Smail', 'Kaddi', 'Admin', 'admin', 'Safi', 'admin', 'Incomplate', ''),
(38, 'a', 'a', 'a', 'a', 'a', 'a', 'Employee', 'InComplate', 'ad1221');

-- --------------------------------------------------------

--
-- Table structure for table `formation`
--

CREATE TABLE `formation` (
  `id_formation` int(200) NOT NULL,
  `code` varchar(200) NOT NULL,
  `libellé` varchar(200) NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `formation`
--

INSERT INTO `formation` (`id_formation`, `code`, `libellé`, `description`) VALUES
(17, 'ad1221', 'SMA', 'DEVELOPEUR WEB');

-- --------------------------------------------------------

--
-- Table structure for table `session`
--

CREATE TABLE `session` (
  `id_session` int(200) NOT NULL,
  `id_employee` int(200) NOT NULL,
  `code` varchar(200) NOT NULL,
  `libellé` varchar(200) NOT NULL,
  `formation` varchar(200) NOT NULL,
  `Mois` varchar(200) NOT NULL,
  `annee` int(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `session`
--

INSERT INTO `session` (`id_session`, `id_employee`, `code`, `libellé`, `formation`, `Mois`, `annee`) VALUES
(38, 38, 'adaa', 'aaa', 'SMA', '03', 2021);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employé`
--
ALTER TABLE `employé`
  ADD PRIMARY KEY (`id_employee`);

--
-- Indexes for table `formation`
--
ALTER TABLE `formation`
  ADD PRIMARY KEY (`id_formation`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employé`
--
ALTER TABLE `employé`
  MODIFY `id_employee` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `formation`
--
ALTER TABLE `formation`
  MODIFY `id_formation` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
