-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Mag 17, 2022 alle 15:21
-- Versione del server: 10.4.21-MariaDB
-- Versione PHP: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `parcheggio`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `occupazione`
--

CREATE TABLE `occupazione` (
  `CodOccupazione` int(11) NOT NULL,
  `OraInizio` datetime DEFAULT NULL,
  `OraFine` datetime DEFAULT NULL,
  `Targa` varchar(10) NOT NULL,
  `NumPosto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `occupazione`
--

INSERT INTO `occupazione` (`CodOccupazione`, `OraInizio`, `OraFine`, `Targa`, `NumPosto`) VALUES
(7, '2022-05-17 14:44:53', NULL, '1234', 5),
(13, '2022-05-17 15:08:43', NULL, '1234', 5);

-- --------------------------------------------------------

--
-- Struttura della tabella `piano`
--

CREATE TABLE `piano` (
  `NumPiano` int(11) NOT NULL,
  `NumPosti` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `piano`
--

INSERT INTO `piano` (`NumPiano`, `NumPosti`) VALUES
(1, 5);

-- --------------------------------------------------------

--
-- Struttura della tabella `posto`
--

CREATE TABLE `posto` (
  `NumPosto` int(11) NOT NULL,
  `NumPiano` int(11) NOT NULL,
  `StatoPosto` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `posto`
--

INSERT INTO `posto` (`NumPosto`, `NumPiano`, `StatoPosto`) VALUES
(3, 1, 0),
(4, 1, 0),
(5, 1, 0),
(6, 1, 0),
(7, 1, 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `utente`
--

CREATE TABLE `utente` (
  `CodUtente` int(11) NOT NULL,
  `Nome` varchar(30) NOT NULL,
  `Cognome` varchar(30) NOT NULL,
  `Email` varchar(60) NOT NULL,
  `Password` varchar(30) NOT NULL,
  `Privilegi` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `utente`
--

INSERT INTO `utente` (`CodUtente`, `Nome`, `Cognome`, `Email`, `Password`, `Privilegi`) VALUES
(1, 'qw', 'we', 'ciao', 'ciao03', 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `vehicolo`
--

CREATE TABLE `vehicolo` (
  `Targa` varchar(10) NOT NULL,
  `Marca` varchar(30) NOT NULL,
  `Modello` varchar(30) NOT NULL,
  `CodUtente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `vehicolo`
--

INSERT INTO `vehicolo` (`Targa`, `Marca`, `Modello`, `CodUtente`) VALUES
('1234', 'fer', 'ari', 1);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `occupazione`
--
ALTER TABLE `occupazione`
  ADD PRIMARY KEY (`CodOccupazione`),
  ADD KEY `Targa` (`Targa`),
  ADD KEY `NumPosto` (`NumPosto`);

--
-- Indici per le tabelle `piano`
--
ALTER TABLE `piano`
  ADD PRIMARY KEY (`NumPiano`);

--
-- Indici per le tabelle `posto`
--
ALTER TABLE `posto`
  ADD PRIMARY KEY (`NumPosto`),
  ADD KEY `posto_ibfk_1` (`NumPiano`);

--
-- Indici per le tabelle `utente`
--
ALTER TABLE `utente`
  ADD PRIMARY KEY (`CodUtente`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- Indici per le tabelle `vehicolo`
--
ALTER TABLE `vehicolo`
  ADD PRIMARY KEY (`Targa`),
  ADD KEY `CodUtente` (`CodUtente`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `occupazione`
--
ALTER TABLE `occupazione`
  MODIFY `CodOccupazione` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT per la tabella `posto`
--
ALTER TABLE `posto`
  MODIFY `NumPosto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT per la tabella `utente`
--
ALTER TABLE `utente`
  MODIFY `CodUtente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `occupazione`
--
ALTER TABLE `occupazione`
  ADD CONSTRAINT `occupazione_ibfk_1` FOREIGN KEY (`Targa`) REFERENCES `vehicolo` (`Targa`),
  ADD CONSTRAINT `occupazione_ibfk_2` FOREIGN KEY (`NumPosto`) REFERENCES `posto` (`NumPosto`);

--
-- Limiti per la tabella `posto`
--
ALTER TABLE `posto`
  ADD CONSTRAINT `posto_ibfk_1` FOREIGN KEY (`NumPiano`) REFERENCES `piano` (`NumPiano`) ON DELETE CASCADE;

--
-- Limiti per la tabella `vehicolo`
--
ALTER TABLE `vehicolo`
  ADD CONSTRAINT `vehicolo_ibfk_1` FOREIGN KEY (`CodUtente`) REFERENCES `utente` (`CodUtente`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
