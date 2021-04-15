-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 14 avr. 2021 à 00:00
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `ppe_gestion_des_stocks`
--

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `id` int(11) NOT NULL,
  `code_produit` varchar(255) NOT NULL,
  `client` varchar(255) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `rangement` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`id`, `code_produit`, `client`, `titre`, `rangement`) VALUES
(1, '423412', 'CYBER GROUP STUDIOS', 'TATONKA', 'SALLE TITRA A01C04E02'),
(2, '342213', 'FRANCE TELEVISIONS', 'ANGRY BIRDS', 'SALLE WARNER A10C02E02'),
(3, '115675', 'WARNER BROS ENTERTAINMEN', 'TOM AND JERRY', 'SALLE WARNER A03C04E03'),
(4, '644321', 'JBA PRODUCTION', 'BLEACH', 'SALLE TITRA A01C01E01'),
(5, '843227', 'BBCOM', 'ONE PIECE', 'SALLE NORA A07C03E04');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
