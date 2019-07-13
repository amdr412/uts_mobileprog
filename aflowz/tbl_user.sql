-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Waktu pembuatan: 11. Januari 2017 jam 02:41
-- Versi Server: 5.5.16
-- Versi PHP: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `aflowz_db`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_user`
--

CREATE TABLE IF NOT EXISTS `tbl_user` (
  `id_rec` varchar(17) NOT NULL,
  `user_id` varchar(25) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `password` varchar(15) NOT NULL,
  `department` varchar(25) NOT NULL,
  `user_sex` varchar(15) NOT NULL,
  `role_admin` varchar(15) NOT NULL,
  `role_user` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tbl_user`
--

INSERT INTO `tbl_user` (`id_rec`, `user_id`, `user_name`, `password`, `department`, `user_sex`, `role_admin`, `role_user`) VALUES
('20170105135026564', 'asti', 'astimen', '32250170a0dca92', 'Finance', 'Male', 'true', 'false'),
('20170106112608339', 'andi', 'Andika Surya Bakti', 'a', 'Assurance', 'Male', 'false', 'true'),
('20170109140911089', 'ihda', 'ihda husnayain', 'as', 'Delivery', 'Female', 'false', 'true');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
