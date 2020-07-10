-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 11, 2020 at 12:13 AM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cafe_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `bills`
--

CREATE TABLE `bills` (
  `id` int(11) NOT NULL,
  `bill_num` int(11) NOT NULL,
  `price` float NOT NULL,
  `products` text NOT NULL,
  `date_time` date NOT NULL,
  `discount` float NOT NULL,
  `service_tax` float NOT NULL,
  `bill_tax` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bills`
--

INSERT INTO `bills` (`id`, `bill_num`, `price`, `products`, `date_time`, `discount`, `service_tax`, `bill_tax`) VALUES
(1, 1, 319.5, 'sadasd x 3\nuyttuy x 2\nشاي x 1\nبسبوسه x 1\nبرجر x 5\n', '2020-04-12', 0, 0, 0),
(2, 2, 114.5, 'جاتوه x 3\nبيض مقلي x 2\nsadasd x 1\n', '2020-04-13', 0, 0, 0),
(3, 3, 0, 'برجر x 7\n', '2020-04-14', 0, 0, 0),
(4, 4, 189, 'برجر x 3\nghfg x 2\nتنلا x 4\nhjhjjhjh x 3\nارانب صاحيه x 2\nsadasd x 2\nارانب x 1\nجاتوه x 2\n', '2020-04-14', 0, 0, 0),
(5, 5, 756.5, 'بيض مقلي x 9\nشاي x 3\nuyttuy x 5\nبسبوسه x 3\nارانب x 2\nsadasd x 7\nبرجر x 4\nghfg x 1\nبيتزا x 3\n', '2020-04-16', 0, 0, 0),
(6, 6, 746, 'ارانب صاحيه x 11\nتنلا x 11\nhjhjjhjh x 16\nghfg x 7\nبيتزا x 9\nsadasd x 8\nارانب x 3\nبيض مقلي x 10\nجاتوه x 7\n', '2020-04-16', 0, 0, 0),
(9, 9, 0, 'ارانب صاحيه x 8 x\r\nتنلا x 4 x\r\nتنلا x 4 x\r\nhjhjjhjh x 63 x\r\nghfg x 4 x\r\nبيتزا x 4 x\r\n', '2020-04-14', 0, 0, 0),
(10, 10, 0, 'بيض مقلي x 5\n', '2020-04-14', 0, 0, 0),
(11, 11, 240, 'جاتوه x 4\nنعتغبلابال x 2\nارانب x 5\nارانب صاحيه x 2\nبرجر x 2\n', '2020-04-14', 0, 0, 0),
(12, 12, 40, 'فرخه مشويه x 2\n', '2020-04-14', 0, 0, 0),
(13, 13, 120, 'فرخه مشويه x 6\n', '2020-04-15', 0, 0, 0),
(14, 14, 270, 'نعتغبلابال x 2\nجاتوه x 3\nفرخه مشويه x 2\n', '2020-04-15', 0, 0, 0),
(15, 15, 413.5, 'فرخه مشويه x 8\nsadasd x 3\nارانب x 2\nghfg x 4\n', '2020-04-15', 0, 0, 0),
(16, 16, 96, 'ghfg x 15\nتنلا x 4\nبيتزا x 2\nسندوتش تفاح x 3\n', '2020-04-15', 0, 0, 0),
(17, 17, 628, 'نعتغبلابال x 3\nجاتوه x 2\nسندوتش تفاح x 4\nبلح x 3\nghfg x 2\n', '2020-04-15', 0, 0, 0),
(18, 18, 529, 'سندوتش تفاح x 4\nبلح x 2\nghfg x 2\nارانب صاحيه طازه x 4\nhjhjjhjh x 4\nبيتزا x 4\nتنلا x 4\nفرخه مشويه x 3\nsadasd x 2\nارانب x 1\n', '2020-04-15', 0, 0, 0),
(19, 19, 312, 'ارانب صاحيه طازه x 24\nتنلا x 12\nبيتزا x 10\nhjhjjhjh x 7\n', '2020-04-15', 0, 0, 0),
(21, 21, 212.5, 'جاتوه x 3\nبرجر x 5\nبطاطس x 2\n', '2020-04-17', 0, 0, 0),
(22, 22, 410.5, 'برجر x 1\nبطاطس x 1\nكبده x 1\nينلتنيتلالنيبتلانيبلالنيتبلالنيتللاينتتيبل x 3\nبازنجان x 3\nsadasd x 2\nفرخه مشويه x 1\nسندوتش تفاح x 4\n', '2020-04-17', 0, 0, 0),
(23, 23, 38.61, 'ارانب صاحيه طازه x 3\n', '2020-04-17', 0, 0, 0),
(24, 24, 65, 'ارانب صاحيه طازه x 5\n', '2020-04-17', 0, 0, 0),
(25, 25, 195.51, 'ارانب صاحيه طازه x 4\nhjhjjhjh x 5\nبيتزا x 4\nكبده x 4\nبرجر x 2\nبطاطس x 1\n', '2020-04-17', 2, 14, 25),
(26, 26, 467.4, 'ارانب صاحيه طازه x 3\nبيتزا x 2\nhjhjjhjh x 5\nتنلا x 5\nبرجر x 2\nكبده x 3\nسندوتش مخ x 2\nنعتغبلابال x 2\nجاتوه x 4\n', '2020-04-17', 0, 14, 26),
(27, 27, 108.3, 'ارانب صاحيه طازه x 5\nhjhjjhjh x 3\nسندوتش مخ x 2\n', '2020-04-17', 0, 14, 27),
(28, 28, 74.1, 'ارانب صاحيه طازه x 5\nتنلا x 3\nhjhjjhjh x 3\nبيتزا x 2\n', '2020-04-17', 0, 14, 28),
(29, 29, 0, 'تنلا x 4\nhjhjjhjh x 5\n', '2020-04-17', 0, 14, 29),
(30, 30, 430.92, 'sadasd x 4\nفرخه مشويه x 2\n', '2020-04-17', 0, 14, 30),
(31, 31, 246.24, 'سندوتش تفاح x 3\nبلح x 2\n', '2020-04-17', 0, 14, 31),
(32, 32, 88.92, 'ارانب صاحيه طازه x 6\nتنلا x 2\nhjhjjhjh x 6\n', '2020-04-17', 0, 14, 32),
(33, 33, 59.28, 'ارانب صاحيه طازه x 4\n', '2020-04-17', 0, 14, 33),
(34, 34, 59.28, 'ارانب صاحيه طازه x 4\n', '2020-04-17', 0, 14, 34),
(35, 35, 44.46, 'ارانب صاحيه طازه x 3\nتنلا x 3\n', '2020-04-17', 0, 14, 35),
(36, 36, 29.64, 'ارانب صاحيه طازه x 2\nتنلا x 3\nhjhjjhjh x 2\nبيتزا x 3\n', '2020-04-17', 0, 14, 36),
(37, 37, 44.46, 'تنلا x 3\nارانب صاحيه طازه x 3\nhjhjjhjh x 2\nبيتزا x 3\n', '2020-04-17', 0, 14, 37),
(38, 38, 44.46, 'ارانب صاحيه طازه x 3\nتنلا x 2\nhjhjjhjh x 2\nبيتزا x 3\n', '2020-04-17', 0, 14, 38),
(40, 40, 44.46, 'ارانب صاحيه طازه x 3\nتنلا x 2\nhjhjjhjh x 2\nبيتزا x 2\n', '2020-04-18', 0, 14, 40),
(41, 41, 68.4, 'تنلا x 4\nhjhjjhjh x 3\nبيتزا x 3\nارانب صاحيه طازه x 2\nبطاطس x 1\nكبده x 2\n', '2020-04-18', 0, 14, 41),
(42, 42, 14.7459, 'ارانب صاحيه طازه x 1\n', '2020-04-18', 0.5, 14, 42),
(43, 43, 44.46, 'ارانب صاحيه طازه x 3\nتنلا x 7\nhjhjjhjh x 19\n', '2020-04-18', 0, 14, 43),
(44, 44, 178.98, 'hjhjjhjh x 10\nتنلا x 10\nبيتزا x 6\nسندوتش مخ x 2\nكبده x 3\nبرجر x 2\nارانب صاحيه طازه x 2\n', '2020-04-18', 0, 14, 44),
(45, 45, 127.11, 'تنلا x 4\nhjhjjhjh x 2\nبيتزا x 3\nسندوتش مخ x 1\nكبده x 1\nبرجر x 1\nارانب صاحيه طازه x 4\n', '2020-04-18', 0, 14, 45),
(46, 46, 67.83, 'تنلا x 20\nhjhjjhjh x 8\nبيتزا x 5\nسندوتش مخ x 1\nكبده x 1\nبرجر x 1\n', '2020-04-18', 0, 14, 46),
(47, 47, 13.68, 'تنلا x 4\nhjhjjhjh x 4\nبيتزا x 7\nكبده x 1\n', '2020-04-18', 0, 14, 47),
(48, 48, 29.64, 'ارانب صاحيه طازه x 2\nتنلا x 2\n', '2020-04-18', 0, 14, 48),
(49, 49, 29.64, 'ارانب صاحيه طازه x 2\nتنلا x 2\nhjhjjhjh x 3\nبيتزا x 2\n', '2020-04-18', 0, 14, 49),
(50, 50, 0, 'تنلا x 11\nhjhjjhjh x 4\n', '2020-04-18', 0, 14, 50),
(51, 51, 0, 'تنلا x 2\nhjhjjhjh x 6\n', '2020-04-18', 0, 14, 51),
(52, 52, 14.82, 'ارانب صاحيه طازه x 1\nتنلا x 18\n', '2020-04-18', 0, 14, 52),
(53, 53, 59.28, 'ارانب صاحيه طازه x 4\nتنلا x 6\n', '2020-04-18', 0, 14, 53),
(54, 54, 0, 'تنلا x 5\nhjhjjhjh x 4\n', '2020-04-18', 0, 14, 54),
(55, 55, 44.46, 'ارانب صاحيه طازه x 3\nتنلا x 3\nhjhjjhjh x 5\n', '2020-04-18', 0, 14, 55),
(56, 56, 59.28, 'ارانب صاحيه طازه x 4\nتنلا x 4\nhjhjjhjh x 5\nبيتزا x 2\n', '2020-04-18', 0, 14, 56),
(57, 57, 59.28, 'ارانب صاحيه طازه x 4\nhjhjjhjh x 7\n', '2020-04-18', 0, 14, 57),
(58, 58, 29.64, 'ارانب صاحيه طازه x 2\nتنلا x 9\nhjhjjhjh x 4\n', '2020-04-18', 0, 14, 58),
(59, 59, 0, 'hjhjjhjh x 6\nتنلا x 4\n', '2020-04-18', 0, 14, 59),
(60, 60, 14.82, 'ارانب صاحيه طازه x 1\nتنلا x 3\nhjhjjhjh x 2\n', '2020-04-18', 0, 14, 60),
(61, 61, 336, 'ارانب صاحيه طازه x 5\nبطاطس x 2\nبرجر x 6\n', '2020-04-18', 0, 20, 61),
(62, 62, 238.26, 'جاتوه x 2\nنعتغبلابال x 2\n', '2020-04-18', 5, 14, 62),
(63, 63, 163.02, 'تنلا x 3\nhjhjjhjh x 2\nبيتزا x 3\nارانب صاحيه طازه x 3\nبرجر x 2\nكبده x 2\nسندوتش مخ x 1\n', '2020-04-18', 0, 14, 63),
(64, 64, 178.98, 'ارانب صاحيه طازه x 2\nبرجر x 2\nكبده x 3\nسندوتش مخ x 2\nبيتزا x 3\nhjhjjhjh x 3\n', '2020-04-18', 0, 14, 64),
(65, 65, 125.4, 'جاتوه x 1\nنعتغبلابال x 1\n', '2020-04-18', 0, 14, 65),
(66, 66, 96.33, 'ارانب صاحيه طازه x 1\nتنلا x 2\nبيتزا x 1\nكبده x 2\nسندوتش مخ x 1\nبرجر x 1\n', '2020-04-18', 0, 14, 66),
(67, 67, 414.39, 'ارانب صاحيه طازه x 2\nتنلا x 5\nhjhjjhjh x 5\nsadasd x 3\nفرخه مشويه x 1\nسندوتش تفاح x 2\n', '2020-04-18', 0, 14, 67),
(68, 68, 352.26, 'نعتغبلابال x 1\nsadasd x 2\nفرخه مشويه x 2\nتنلا x 3\nhjhjjhjh x 3\nبيتزا x 3\n', '2020-04-18', 0, 14, 68),
(69, 69, 394.44, 'تنلا x 2\nhjhjjhjh x 2\nبيتزا x 2\nسندوتش مخ x 1\nكبده x 1\nsadasd x 2\nفرخه مشويه x 2\nنعتغبلابال x 1\nجاتوه x 1\n', '2020-04-19', 0, 14, 69),
(70, 70, 45.6, 'بيتزا x 1\nhjhjjhjh x 1\nقهوه x 4\n', '2020-04-19', 0, 14, 70),
(71, 71, 346.56, 'بيتزا x 2\nhjhjjhjh x 3\nسندوتش تفاح x 2\nبلح x 4\n', '2020-04-19', 0, 14, 71),
(72, 72, 238.26, 'hjhjjhjh x 3\nبيتزا x 2\nفرخه مشويه x 2\nsadasd x 2\n', '2020-04-19', 0, 14, 72),
(73, 73, 238.26, 'hjhjjhjh x 3\nبيتزا x 2\nsadasd x 2\nفرخه مشويه x 2\n', '2020-04-19', 0, 14, 73),
(74, 74, 430.92, 'hjhjjhjh x 4\nبيتزا x 2\nفرخه مشويه x 2\nsadasd x 4\n', '2020-04-19', 0, 14, 74),
(75, 75, 272.46, 'hjhjjhjh x 5\nتنلا x 3\nبيتزا x 6\nفرخه مشويه x 2\nsadasd x 2\nقهوه x 3\n', '2020-04-19', 0, 14, 75),
(76, 76, 272.46, 'تنلا x 2\nhjhjjhjh x 3\nبيتزا x 2\nفرخه مشويه x 2\nsadasd x 2\nقهوه x 3\n', '2020-04-19', 0, 14, 76),
(77, 77, 159.03, 'تنلا x 4\nhjhjjhjh x 3\nبيتزا x 4\nارانب صاحيه طازه x 1\nبرجر x 1\nسندوتش تفاح x 2\nقهوه x 3\n', '2020-04-19', 0, 14, 77),
(78, 78, 0, 'تنلا x 7\n', '2020-04-19', 0, 14, 78),
(79, 79, 141.36, 'بيتزا x 4\nكبده x 2\nسندوتش مخ x 2\nبرتقال x 3\nفرخه مشويه x 2\n', '2020-04-19', 0, 14, 79),
(80, 80, 0, 'بيتزا x 2\nhjhjjhjh x 2\n', '2020-04-20', 0, 14, 80),
(81, 81, 367.137, 'بيتزا x 4\nتنلا x 1\nsadasd x 2\nفرخه مشويه x 1\nنعتغبلابال x 1\nجاتوه x 1\nقهوه x 4\n', '2020-04-20', 5, 14, 0);

-- --------------------------------------------------------

--
-- Table structure for table `bill_count`
--

CREATE TABLE `bill_count` (
  `id` int(11) NOT NULL,
  `count` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bill_count`
--

INSERT INTO `bill_count` (`id`, `count`) VALUES
(1, 81);

-- --------------------------------------------------------

--
-- Table structure for table `cafe_tables`
--

CREATE TABLE `cafe_tables` (
  `id` int(11) NOT NULL,
  `price` float NOT NULL,
  `chair_num` int(11) NOT NULL,
  `table_num` int(11) NOT NULL,
  `bill_num` int(11) NOT NULL,
  `state` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cafe_tables`
--

INSERT INTO `cafe_tables` (`id`, `price`, `chair_num`, `table_num`, `bill_num`, `state`) VALUES
(1, 200, 10, 12, 10, 0),
(4, 20.5, 14, 1, 5, 0),
(5, 212.5, 11, 1, 21, 0),
(6, 410.5, 2, 1, 22, 0),
(7, 238.26, 4, 1, 62, 0),
(9, 178.98, 5, 0, 64, 0),
(10, 100, 12, 1, 9, 0),
(11, 96.33, 1, 0, 66, 0),
(12, 414.39, 1, 0, 67, 0),
(13, 352.26, 1, 0, 68, 0),
(14, 394.44, 1, 0, 69, 0),
(15, 45.6, 1, 0, 70, 0),
(16, 346.56, 1, 0, 71, 0),
(17, 238.26, 1, 0, 72, 0),
(18, 238.26, 1, 0, 73, 0),
(19, 430.92, 1, 0, 74, 0),
(20, 272.46, 1, 0, 75, 0),
(21, 272.46, 1, 0, 76, 0),
(22, 159.03, 1, 0, 77, 0),
(23, 0, 1, 0, 80, 0),
(24, 367.137, 7, 0, 81, 0);

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `img_url` text NOT NULL,
  `printer` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`, `img_url`, `printer`) VALUES
(24, 'سندوتشات', 'file:/C:/xampp/htdocs/cafeApi/images/categories/break.png', 'Microsoft Print to PDF'),
(26, 'مشويات', 'file:/C:/xampp/htdocs/cafeApi/images/categories/caffee.png', 'Microsoft Print to PDF'),
(29, 'حلويات', 'file:/C:/xampp/htdocs/cafeApi/images/categories/black-forest-cake-thumb-500x500.jpg', 'Microsoft Print to PDF'),
(30, 'برجر', 'file:/C:/xampp/htdocs/cafeApi/images/categories/hamb.png', 'Microsoft Print to PDF'),
(31, 'مشروبات', 'file:/C:/xampp/htdocs/cafeApi/images/categories/cafee.png', 'Microsoft Print to PDF'),
(32, 'عصائر', 'file:/C:/xampp/htdocs/cafeApi/images/categories/FruitJuices_Lead.jpg', 'Microsoft Print to PDF'),
(33, 'مشروبات ساخنه', 'file:/C:/xampp/htdocs/cafeApi/images/categories/31875.jpg', 'Microsoft Print to PDF'),
(34, 'شاورما', 'file:/C:/xampp/htdocs/cafeApi/images/categories/downloaشس.jpg', 'Microsoft Print to PDF');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `img_url` text NOT NULL,
  `amount` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `name`, `img_url`, `amount`, `type`, `price`) VALUES
(1, 'ارانب جبلي', 'file:/C:/xampp/htdocs/cafeApi/images/products/break.png', 1, 26, 0),
(2, 'ارانب', 'file:/C:/xampp/htdocs/cafeApi/images/categories/Hoodwinked.jpg', 2, 24, 13),
(4, 'تنلا', 'file:/C:/xampp/htdocs/cafeApi/images/products/353dfbd37019af4689b117ecf608fd72_w750_h750.jpg', 431, 24, 0),
(5, 'hjhjjhjh', 'file:/C:/xampp/htdocs/cafeApi/images/products/BLT_sandwich_on_toast.jpg', 5486, 24, 0),
(6, 'بسبوسه', 'file:/C:/xampp/htdocs/cafeApi/images/categories/Boingo_evil.jpg', 43, 25, 0),
(7, 'شاي', 'file:/C:/xampp/htdocs/cafeApi/images/products/caffee.png', 30, 25, 0),
(8, 'بيتزا', 'file:/C:/xampp/htdocs/cafeApi/images/products/break.png', 335, 24, 0),
(9, 'بطاطس', 'file:/C:/xampp/htdocs/cafeApi/images/products/potato.png', 0, 24, 10),
(10, 'برجر', 'file:/C:/xampp/htdocs/cafeApi/images/products/hamb.png', 4, 24, 32.5),
(11, 'بيض مقلي', 'file:/C:/xampp/htdocs/cafeApi/images/products/break.png', 42, 27, 0),
(12, 'sadasd', 'file:/C:/xampp/htdocs/cafeApi/images/products/cafee.png', 5, 26, 84.5),
(13, 'uyttuy', 'file:/C:/xampp/htdocs/cafeApi/images/products/potato.png', 836, 25, 33),
(14, 'جاتوه', 'file:/C:/xampp/htdocs/cafeApi/images/products/triple-chocolate-cake-4.jpg', 0, 29, 10),
(15, 'نعتغبلابال', 'file:/C:/xampp/htdocs/cafeApi/images/products/Summer-Sangria-Cake-4.jpg', 0, 29, 100),
(16, 'بلح', 'file:/C:/xampp/htdocs/cafeApi/images/products/', 1, 30, 60),
(17, 'سندوتش تفاح', 'file:/C:/xampp/htdocs/cafeApi/images/products/break.png', 10, 30, 32),
(18, 'فرخه مشويه', 'file:/C:/xampp/htdocs/cafeApi/images/products/chicken-grill-png-1.png', 0, 26, 20),
(19, 'كبده', 'file:/C:/xampp/htdocs/cafeApi/images/products/353dfbd37019af4689b117ecf608fd72_w750_h750.jpg', 24, 24, 12),
(20, 'سندوتش مخ', 'file:/C:/xampp/htdocs/cafeApi/images/products/BLT_sandwich_on_toast.jpg', 5, 24, 15),
(21, 'بازنجان', 'file:/C:/xampp/htdocs/cafeApi/images/products/downloaشس.jpg', 7, 24, 5),
(22, 'سندوتش كبير', 'file:/C:/xampp/htdocs/cafeApi/images/products/1498851838-shot-2-195-875x323.jpg', 12, 24, 8),
(23, 'قهوه', 'file:/C:/xampp/htdocs/cafeApi/images/products/caffee.png', 13, 31, 10),
(24, 'برتقال', 'file:/C:/xampp/htdocs/cafeApi/images/products/31875.jpg', 47, 32, 10),
(25, 'شاورما دجاج', 'file:/C:/xampp/htdocs/cafeApi/images/products/chicken-grill-png-1.png', 30, 34, 15);

-- --------------------------------------------------------

--
-- Table structure for table `settings`
--

CREATE TABLE `settings` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `img_url` text NOT NULL,
  `cashier_printer` text NOT NULL,
  `chef_printer` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `settings`
--

INSERT INTO `settings` (`id`, `name`, `img_url`, `cashier_printer`, `chef_printer`) VALUES
(1, 'Cafe Zone 2', 'file:/C:/xampp/htdocs/cafeApi/images/cafee.png', 'default', 'default');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `user` text NOT NULL,
  `pass` text NOT NULL,
  `type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `user`, `pass`, `type`) VALUES
(1, 'admin', '12345', 0),
(4, 'koko', '12345', 1),
(5, 'Ahmed', '12345', 1),
(6, 'ama', '12345', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bills`
--
ALTER TABLE `bills`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bill_count`
--
ALTER TABLE `bill_count`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cafe_tables`
--
ALTER TABLE `cafe_tables`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `settings`
--
ALTER TABLE `settings`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bills`
--
ALTER TABLE `bills`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=82;

--
-- AUTO_INCREMENT for table `bill_count`
--
ALTER TABLE `bill_count`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `cafe_tables`
--
ALTER TABLE `cafe_tables`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `settings`
--
ALTER TABLE `settings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
