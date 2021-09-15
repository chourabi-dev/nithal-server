-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 09 juin 2021 à 13:39
-- Version du serveur :  10.4.18-MariaDB
-- Version de PHP : 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `fidelity`
--

-- --------------------------------------------------------

--
-- Structure de la table `administrateur`
--

CREATE TABLE `administrateur` (
  `etat` bit(1) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `administrateur`
--

INSERT INTO `administrateur` (`etat`, `nom`, `prenom`, `id`) VALUES
(b'0', NULL, NULL, 10);

-- --------------------------------------------------------

--
-- Structure de la table `bande_achat`
--

CREATE TABLE `bande_achat` (
  `id` bigint(20) NOT NULL,
  `code` int(11) NOT NULL,
  `date_creation` datetime DEFAULT NULL,
  `date_expiration` datetime DEFAULT NULL,
  `etat` varchar(15) DEFAULT NULL,
  `montant_bande_achat` double NOT NULL,
  `client_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `bande_achat`
--

INSERT INTO `bande_achat` (`id`, `code`, `date_creation`, `date_expiration`, `etat`, `montant_bande_achat`, `client_id`) VALUES
(8, 4556, '2021-05-23 14:45:47', '2021-06-02 14:45:47', 'UTILISEE', 150, 14),
(7, 3936, '2021-05-23 14:45:41', '2021-06-02 14:45:41', 'EXPIREE', 90, 14),
(6, 6168, '2021-05-23 14:42:53', '2021-06-02 14:42:53', 'VALIDE', 60, 14),
(9, 5122, '2021-05-24 20:48:24', '2021-06-03 20:48:24', 'VALIDE', 40, 15),
(10, 6748, '2021-05-24 20:48:45', '2021-06-03 20:48:45', 'VALIDE', 20, 15),
(11, 5500, '2021-05-25 10:32:27', '2021-06-04 10:32:27', 'VALIDE', 600, 14);

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `id` bigint(20) NOT NULL,
  `montant_attribution` int(11) NOT NULL,
  `montant_retrait` int(11) NOT NULL,
  `name` varchar(15) DEFAULT NULL,
  `nombre_point_attribution` int(11) NOT NULL,
  `nombre_point_retrait` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`id`, `montant_attribution`, `montant_retrait`, `name`, `nombre_point_attribution`, `nombre_point_retrait`) VALUES
(1, 10, 5, 'BASIC', 1, 10),
(2, 10, 2, 'SILVER', 20, 10),
(3, 10, 3, 'GOLD', 2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `date_naissance` datetime DEFAULT NULL,
  `etat` bit(1) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `nombre_points` int(11) NOT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `sexe` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `categorie_id` bigint(20) NOT NULL,
  `niveau` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`date_naissance`, `etat`, `nom`, `nombre_points`, `prenom`, `sexe`, `telephone`, `id`, `categorie_id`, `niveau`) VALUES
(NULL, b'0', 'boutheina', 1500, 'chourabiii', NULL, NULL, 14, 3, 2),
(NULL, b'0', NULL, 600, NULL, NULL, NULL, 15, 2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `client_aud`
--

CREATE TABLE `client_aud` (
  `id` bigint(20) NOT NULL,
  `rev` int(11) NOT NULL,
  `nombre_points` int(11) DEFAULT NULL,
  `categorie_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `client_aud`
--

INSERT INTO `client_aud` (`id`, `rev`, `nombre_points`, `categorie_id`) VALUES
(15, 1, 500, 2),
(15, 2, 900, 2),
(14, 3, 1780, 3),
(14, 4, 1750, 3),
(14, 5, 1700, 3),
(15, 6, 700, 2),
(15, 7, 600, 2),
(14, 8, 1500, 3),
(14, 9, 1500, 3),
(14, 10, 1500, 3),
(14, 11, 1500, 3);

-- --------------------------------------------------------

--
-- Structure de la table `detail_achat`
--

CREATE TABLE `detail_achat` (
  `id` bigint(20) NOT NULL,
  `mode_achat` varchar(15) DEFAULT NULL,
  `nbre_point` int(11) NOT NULL,
  `bande_achat_id` bigint(20) DEFAULT NULL,
  `transaction_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `detail_achat`
--

INSERT INTO `detail_achat` (`id`, `mode_achat`, `nbre_point`, `bande_achat_id`, `transaction_id`) VALUES
(1, 'ACHAT_ESPECE', 400, NULL, 9),
(2, 'ACHAT_BANDE', 0, 8, 10);

-- --------------------------------------------------------

--
-- Structure de la table `manager`
--

CREATE TABLE `manager` (
  `etat` bit(1) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `date_naissance` datetime DEFAULT NULL,
  `sexe` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `manager`
--

INSERT INTO `manager` (`etat`, `nom`, `prenom`, `id`, `date_naissance`, `sexe`, `telephone`) VALUES
(b'0', NULL, NULL, 11, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `revinfo`
--

CREATE TABLE `revinfo` (
  `rev` int(11) NOT NULL,
  `revtstmp` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `revinfo`
--

INSERT INTO `revinfo` (`rev`, `revtstmp`) VALUES
(1, 1621030526727),
(2, 1621770822418),
(3, 1621780973913),
(4, 1621781141693),
(5, 1621781147737),
(6, 1621889304832),
(7, 1621889325799),
(8, 1621938748470),
(9, 1622298568615),
(10, 1622299758190),
(11, 1623177266678);

-- --------------------------------------------------------

--
-- Structure de la table `roles`
--

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `name` varchar(15) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `roles`
--

INSERT INTO `roles` (`id`, `created_at`, `name`, `updated_at`) VALUES
(1, '2021-04-12 13:25:59', 'SUPER_ADMIN', '2021-04-12 13:25:59'),
(2, '2021-04-12 13:27:01', 'ADMIN', '2021-04-12 13:27:01'),
(3, '2021-04-12 13:27:23', 'MANAGER', '2021-04-12 13:27:23'),
(4, '2021-04-12 13:28:06', 'CLIENT', '2021-04-12 13:28:06');

-- --------------------------------------------------------

--
-- Structure de la table `roles_users`
--

CREATE TABLE `roles_users` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `roles_users`
--

INSERT INTO `roles_users` (`user_id`, `role_id`) VALUES
(1, 2),
(2, 2),
(3, 2),
(4, 2),
(5, 2),
(6, 2),
(7, 2),
(8, 2),
(9, 2),
(10, 2),
(11, 2),
(14, 4),
(15, 4);

-- --------------------------------------------------------

--
-- Structure de la table `super_administrateur`
--

CREATE TABLE `super_administrateur` (
  `etat` bit(1) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `transaction_achat`
--

CREATE TABLE `transaction_achat` (
  `id` bigint(20) NOT NULL,
  `date` datetime DEFAULT NULL,
  `montant_achat` double NOT NULL,
  `id_client` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `transaction_achat`
--

INSERT INTO `transaction_achat` (`id`, `date`, `montant_achat`, `id_client`) VALUES
(1, '2021-05-23 01:51:53', 200, 15),
(2, '2021-05-23 01:55:39', 200, 15),
(3, '2021-05-23 01:59:05', 200, 15),
(4, '2021-05-23 01:59:47', 200, 15),
(5, '2021-05-23 09:55:26', 200, 15),
(6, '2021-05-23 10:28:10', 200, 15),
(7, '2021-05-23 10:36:14', 200, 15),
(8, '2021-05-23 10:39:04', 200, 15),
(9, '2021-05-23 11:53:42', 200, 15),
(10, '2021-05-23 15:15:41', 200, 14);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `created_at`, `email`, `password`, `updated_at`) VALUES
(11, '2021-04-15 14:45:20', 'boutheinachourabi19@gmail.com', '$2a$10$lvn6wV3wLQyVwXcoSUIlB.zqcJpqcObbIBmxLsSz2qOzBqqBE1Ajq', '2021-04-15 14:45:20'),
(10, '2021-04-15 14:43:13', 'hadilsfar8@gmail.com', '$2a$10$0n6oFelPsBzXqxlxpL0vYe6eu1pBDMMI4/jY6cb65QwzBo/IDdBbe', '2021-04-15 14:43:13'),
(12, '2021-04-20 11:15:01', 'hedil@gmail.com', '$2a$10$rL2.KBzAwm0iwyWeHLwKKecNIRCajyufeDNZMCGO2XSr/q3dqfkCa', '2021-04-20 11:15:01'),
(13, '2021-04-20 11:17:36', 'hadil@gmail.com', '$2a$10$KRkk9N/3bJJalb8wFpex3.Z2KONJgU131PmdR5KuHV0XF53FIvvMy', '2021-04-20 11:17:36'),
(14, '2021-04-20 11:23:47', 'hsfr@gmail.com', '$2a$10$h281nct0Y4gucUjBFWYDU.WUZ57/KeK7ykp7WS4YfyHuveGC54eF6', '2021-06-08 18:34:26'),
(15, '2021-04-21 11:57:54', 'beth@gmail.com', '$2a$10$doOaLTXxsT2CjI.Hkng66.YTJaSQmO4QUKb4ANesODjIAdqK/zPia', '2021-05-24 20:48:45');

-- --------------------------------------------------------

--
-- Structure de la table `users_aud`
--

CREATE TABLE `users_aud` (
  `id` bigint(20) NOT NULL,
  `rev` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `users_aud`
--

INSERT INTO `users_aud` (`id`, `rev`, `revtype`, `updated_at`) VALUES
(15, 1, 1, '2021-05-14 22:15:26'),
(15, 2, 1, '2021-05-23 11:53:42'),
(14, 3, 1, '2021-05-23 14:42:53'),
(14, 4, 1, '2021-05-23 14:45:41'),
(14, 5, 1, '2021-05-23 14:45:47'),
(15, 6, 1, '2021-05-24 20:48:24'),
(15, 7, 1, '2021-05-24 20:48:45'),
(14, 8, 1, '2021-05-25 10:32:28'),
(14, 9, 1, '2021-05-29 14:29:28'),
(14, 10, 1, '2021-05-29 14:49:18'),
(14, 11, 1, '2021-06-08 18:34:26');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `administrateur`
--
ALTER TABLE `administrateur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `bande_achat`
--
ALTER TABLE `bande_achat`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKlpccu8gikuk3fysx1j7khnykf` (`client_id`);

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKa11jktosmneo7a5y2mfqicbsf` (`categorie_id`);

--
-- Index pour la table `client_aud`
--
ALTER TABLE `client_aud`
  ADD PRIMARY KEY (`id`,`rev`);

--
-- Index pour la table `detail_achat`
--
ALTER TABLE `detail_achat`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_fajv3jl5a0w9f818lgso34d94` (`bande_achat_id`),
  ADD KEY `FK7rymsjkergxxv3c25bd82t2rs` (`transaction_id`);

--
-- Index pour la table `manager`
--
ALTER TABLE `manager`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `revinfo`
--
ALTER TABLE `revinfo`
  ADD PRIMARY KEY (`rev`);

--
-- Index pour la table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `roles_users`
--
ALTER TABLE `roles_users`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKsmos02hm32191ogexm2ljik9x` (`role_id`);

--
-- Index pour la table `super_administrateur`
--
ALTER TABLE `super_administrateur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `transaction_achat`
--
ALTER TABLE `transaction_achat`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKin29ubejx9vj6tq2i51mqgavd` (`id_client`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `users_aud`
--
ALTER TABLE `users_aud`
  ADD PRIMARY KEY (`id`,`rev`),
  ADD KEY `FKc4vk4tui2la36415jpgm9leoq` (`rev`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `bande_achat`
--
ALTER TABLE `bande_achat`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `detail_achat`
--
ALTER TABLE `detail_achat`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `revinfo`
--
ALTER TABLE `revinfo`
  MODIFY `rev` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `transaction_achat`
--
ALTER TABLE `transaction_achat`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
