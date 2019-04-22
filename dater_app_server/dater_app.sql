-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 21, 2019 at 07:19 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dater_app`
--

-- --------------------------------------------------------

--
-- Table structure for table `age`
--

create TABLE `age` (
  `username` varchar(255) COLLATE utf8_bin NOT NULL,
  `birthday` date NOT NULL,
  `age` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `age`
--

insert into `age` (`username`, `birthday`, `age`) VALUES
('keseljs', '0000-00-00', 25);

-- --------------------------------------------------------

--
-- Table structure for table `chatmessage`
--

create TABLE `chatmessage` (
  `id` int(11) NOT NULL,
  `usernameOne` varchar(255) COLLATE utf8_bin NOT NULL,
  `usernameTwo` varchar(255) COLLATE utf8_bin NOT NULL,
  `senderUsername` varchar(255) COLLATE utf8_bin NOT NULL,
  `messaegeStatus` int(11) DEFAULT NULL,
  `sendDate` date NOT NULL,
  `sendTime` time NOT NULL,
  `recivedDate` date NOT NULL,
  `recivedTime` time NOT NULL,
  `messageBody` text COLLATE utf8_bin
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `gender`
--

create TABLE `gender` (
  `username` varchar(255) COLLATE utf8_bin NOT NULL,
  `gender` char(1) COLLATE utf8_bin NOT NULL,
  `prefferedGender` char(1) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `gender`
--

insert into `gender` (`username`, `gender`, `prefferedGender`) VALUES
('keseljs', '1', '1');

-- --------------------------------------------------------

--
-- Table structure for table `location`
--

create TABLE `location` (
  `username` varchar(255) COLLATE utf8_bin NOT NULL,
  `longitude` double NOT NULL,
  `latitude` double NOT NULL,
  `address` varchar(50) COLLATE utf8_bin NOT NULL,
  `prefferedDistance` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `location`
--

insert into `location` (`username`, `longitude`, `latitude`, `address`, `prefferedDistance`) VALUES
('keseljs', 20.5039, 44.8164, 'Војводе Мицка Крстића 18', 50);

-- --------------------------------------------------------

--
-- Table structure for table `matchtable`
--

create TABLE `matchtable` (
  `usernameOne` varchar(255) COLLATE utf8_bin NOT NULL,
  `usernameTwo` varchar(255) COLLATE utf8_bin NOT NULL,
  `requestUsername` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `matchStatus` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `rating`
--

create TABLE `rating` (
  `username` varchar(255) COLLATE utf8_bin NOT NULL,
  `rating` int(11) NOT NULL,
  `newStatus` tinyint(1) NOT NULL,
  `superUser` tinyint(1) NOT NULL,
  `k` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `rating`
--

insert into `rating` (`username`, `rating`, `newStatus`, `superUser`, `k`) VALUES
('keseljs', 0, 0, 0, 50);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

create TABLE `user` (
  `id` int(11) NOT NULL,
  `firstName` varchar(20) COLLATE utf8_bin NOT NULL,
  `lastName` varchar(20) COLLATE utf8_bin NOT NULL,
  `username` varchar(20) COLLATE utf8_bin NOT NULL,
  `password` varchar(20) COLLATE utf8_bin NOT NULL,
  `email` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `bio` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `user`
--

insert into `user` (`id`, `firstName`, `lastName`, `username`, `password`, `email`, `bio`) VALUES
(1, 'Strahinja', 'Keselj', 'keseljs', '123', 'keselj@keselj.com', 'penis'),
(2, 'asd', 'null', '123', 'asd', 'null', 'null'),
(3, 'asd', 'null', 'asd', 'asd', 'null', 'null'),
(5, 'jebo', 'null', 'sranje', '123', 'null', 'null'),
(6, 'asd', 'null', 'asd123', 'asd', 'null', 'null');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `age`
--
alter table `age`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `chatmessage`
--
alter table `chatmessage`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usernameOne` (`usernameOne`),
  ADD KEY `usernameTwo` (`usernameTwo`),
  ADD KEY `senderUsername` (`senderUsername`);

--
-- Indexes for table `gender`
--
alter table `gender`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `location`
--
alter table `location`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `matchtable`
--
alter table `matchtable`
  ADD PRIMARY KEY (`usernameOne`,`usernameTwo`),
  ADD KEY `usernameTwo` (`usernameTwo`),
  ADD KEY `requestUsername` (`requestUsername`);

--
-- Indexes for table `rating`
--
alter table `rating`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `user`
--
alter table `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chatmessage`
--
alter table `chatmessage`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
alter table `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `age`
--
alter table `age`
  ADD CONSTRAINT `age_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`);

--
-- Constraints for table `chatmessage`
--
alter table `chatmessage`
  ADD CONSTRAINT `chatmessage_ibfk_1` FOREIGN KEY (`usernameOne`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `chatmessage_ibfk_2` FOREIGN KEY (`usernameTwo`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `chatmessage_ibfk_3` FOREIGN KEY (`senderUsername`) REFERENCES `user` (`username`);

--
-- Constraints for table `gender`
--
alter table `gender`
  ADD CONSTRAINT `gender_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`);

--
-- Constraints for table `location`
--
alter table `location`
  ADD CONSTRAINT `location_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`);

--
-- Constraints for table `matchtable`
--
alter table `matchtable`
  ADD CONSTRAINT `matchtable_ibfk_1` FOREIGN KEY (`usernameOne`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `matchtable_ibfk_2` FOREIGN KEY (`usernameTwo`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `matchtable_ibfk_3` FOREIGN KEY (`requestUsername`) REFERENCES `user` (`username`);

--
-- Constraints for table `rating`
--
alter table `rating`
  ADD CONSTRAINT `rating_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`);
commit;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
