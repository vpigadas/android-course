-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Sep 07, 2020 at 10:30 PM
-- Server version: 10.2.33-MariaDB
-- PHP Version: 7.3.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dimitris619033_channels_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `channels`
--

CREATE TABLE `channels` (
  `id` int(11) NOT NULL,
  `title` varchar(64) NOT NULL,
  `description` text DEFAULT NULL,
  `icon` varchar(128) NOT NULL,
  `website` varchar(256) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `channels`
--

INSERT INTO `channels` (`id`, `title`, `description`, `icon`, `website`, `enabled`) VALUES
(1, 'ERT1', 'ERT1 (Greek: ΕΡΤ1),[1] is the flagship television network of state-owned broadcaster ERT (Greek: Ελληνική Ραδιοφωνία Τηλεόραση). It was launched in 1966 as a television service of the National Radio and Television Foundation.\r\n\r\nEIRT became ERT, then ERT1, ENA, and ET1 before being closed by the Greek government in 2013, when it was mainly an entertainment and cultural channel but also broadcast documentaries, news, sports and children\'s shows. On 11 June 2015 ERT was reopened and the ERT1 name was restored, technically replacing NERIT1 which had been launched by the previous government in 2014 as part of the New Hellenic Radio, Internet and Television (Greek: Νέα Ελληνική Ραδιοφωνία, Ίντερνετ και Τηλεόραση, abbrev. NΕΡIΤ or NERIT). ERT1\'s programming is focused on that of a generalistic TV network, being composed of talk shows, movies, popular foreign TV series, and some cultural shows.', 'ERT1.png', 'https://webtv.ert.gr/ert1-live/', 1),
(2, 'ERT2', 'ERT2 (Greek: ΕΡΤ2; short for Ellinikí Radiophonia Tileórasi 2; Greek: Ελληνική Ραδιοφωνία Τηλεόραση 2; Greek for \'Hellenic Radio and Television 2\'), formerly NET (short for Néa Ellinikí Tileórasi; Greek: Νέα Ελληνική Τηλεόραση; Greek for \'New Hellenic Television\'), is the second television network of the Hellenic Broadcasting Corporation (Greek: Ελληνική Ραδιοφωνία Τηλεόραση), the public broadcaster of Greece. It broadcasts documentaries, talkshows, current affairs programs, sporting events and children\'s shows.\r\n\r\nIt was originally referred to as ERT2 and ET2 (short for Ellinikí Tileórasi 2; Greek: Ελληνική Τηλεόραση 2; Greek for \'Hellenic Television 2\') but the name was later changed to NET, before changing back to ERT2 by technically replacing NERIT Plus. Amid protests of the government\'s decision to close the public broadcaster, ERT staff continued to operate NET via satellite and internet.[1] until November 7, 2013, when riot police stormed into ERT headquarters and took the internet programming of NET off the air.', 'ERT2.png', 'https://webtv.ert.gr/ert2-live/', 1),
(3, 'ERT3', 'ERT3 (Greek: ΕΡΤ3) is a Greek free-to-air television channel, owned by the Hellenic Broadcasting Corporation, the public broadcaster of Greece. It is an entertainment channel: although it broadcasts nationwide, most of the content on ERT3 is centred towards Northern Greece: consequently, ERT3 mainly broadcasts from Thessaloniki instead of the Broadcasting House in Athens, with regional studios in various cities in the north, including Florina, Komotini, Alexandroupoli and on the islands of Paros, Mytilene and Samos.', 'ert3.png', 'https://webtv.ert.gr/ert3-live/', 1),
(4, 'ERT Sports HD', 'ERT Sports HD (Greek: ΕΡΤ Sports HD) is a Greek free-to-air television channel, owned by the Hellenic Broadcasting Corporation, the state broadcaster of Greece. It is the first high-definition television channel in the country and started broadcasting on 27 April 2011 in several large cities such as Athens, Thessaloniki and Alexandroupoli.[1] ERT HD broadcast such important international events as the Olympic Games, the UEFA European Championship and the Eurovision Song Contest. It broadcasts at 1920x1080i50 lines, encoded at Advanced Video Coding on the DVB-T standard.', 'ert-sports.jpg', 'https://webtv.ert.gr/ert-sports-live/', 1),
(5, 'Alpha TV', 'Alpha TV is a Greek free-to-air channel. With Antenna TV it is one of the two biggest stations in Greece, after the collapse of the Mega channel, due to financial problems. The station features a mix of Greek and foreign shows with an emphasis on information. The studios are located near Athens. Alpha TV is owned by Alpha Satellite Television S.A. 100% shareholder of which is the Alpha Media Group Ltd. In Cyprus, private broadcaster Sigma TV used to broadcast a number of Alpha TV\'s programs. In the past, public service broadcaster CyBC used to broadcast Alpha TV programs. In 2015, Alpha TV Cyprus was founded and it broadcast the Alpha TV Programs and others of its own. Alpha Cyprus now is one of the highest-rated Cypriot channels.', 'alpha.png', 'https://www.alphatv.gr/', 1),
(6, 'ANT1', 'Antenna, better known as ANT1, is a television network airing in Greece and Cyprus. The alternate spelling is wordplay in Greek; ena (ένα) is the Greek number 1 (one), thus ANT1 is pronounced the same as Antenna (Αντέννα).\r\n\r\nIt launched on 31 December 1989, the same year as rival Mega Channel, and is owned by ANT1 Group. ANT1 had been the most popular network in Greece for years with its line up of hit series including popular dramas Lampsi and Kalimera Zoi. Programming consists of comedies, dramas, news, current affairs programs, game shows, and entertainment shows.\r\n\r\nThe first broadcast was on 31 December 1989. One of the concept executive producers has been Nico Mastorakis who brought with him several original (for the Greek viewers) ideas after his stay in the United States. Programs like Wheel of Fortune (Greek version), and Oi Men Kai Oi Den were immediately among the top-ten shows in Greece. Cartoons used to be offered in the morning; most were shown in the Greek language.\r\n\r\nAntenna also runs Easy 97.2FM and Rythmos 94.9 (a local pop-music radio station in Athens), as well as international networks ANT1 Satellite (from North America), ANT1 Pacific (Australia) and ANT1 Europe (from Europe and outside the Balkans) which broadcast the best of ANT1 programming to audiences abroad. ANT1 has also launched a digital channel, Antenna Gold, that airs programming from Antenna\'s programming library including many number-one hit shows. It airs in North America via Dish Network. It also owns a stake in Makedonia TV, a TV station with national coverage in Greece, and Easy 97.5FM based in Thessaloniki.\r\n\r\nPrograms that were once shown on ANT1 include CBS Evening News from the U.S. ANT1 Satellite News was also shown until the late 1990s. Antenna TV studios are located in the Athens suburb of Marousi. Its main Athens transmission tower is on Mount Hymettus. The station broadcasts terrestrially via a network of repeaters throughout all of Greece and Cyprus.', 'ANT1BLACK.png', 'https://www.antenna.gr/', 1),
(7, 'Makedonia TV', 'Makedonia TV (Greek: Μακεδονία TV) is a Greek free-to-air television channel broadcasting from Thessaloniki, the capital of Macedonia region in Greece. The network is licensed for national coverage. It was founded in 1991 by a group of journalists from the newspaper of the same name.\r\nThe foreign program includes a big variety of series: comedy, drama, and action as well as big heat series and feature films from the big production studios that broadcast daily; Sex and the City, Amores Verdaderos, Just for Laughs, Rookie Blue, Bold And Beautiful, La Tempestad, Mayday, In Treatment, Spartacus and many more.\r\n\r\nFrom 3 September 2018, the network changed its programming which includes only foreign series and telenovelas.', 'makedonia.png', 'https://www.maktv.gr/', 1),
(8, 'Mega Channel', 'MEGA Channel, also known as MEGA TV or just MEGA, is a television network in Greece, that broadcasts a mix of foreign and Greek programming. Teletipos S.A. was founded in 1989 under the name Teletypos S.A. of Television Programmes and the trade name MEGA CHANNEL. It is the first private television station in Greece, launched on air on November 20, 1989.\r\n\r\nMEGA\'s programming consisted mainly of Greek programmes such as comedies, dramas, news, current affairs and entertainment shows. Some of its hit series included popular comedies such as Sto Para Pente, Savatogenimmenes and Maria, i Aschimi as a comedy-drama series. This channel has also made some drama serials which are very famous in Greek television history.\r\n\r\nStudios are located on Andrea Syngrou Avenue. It was previously located in Paiania. As of 29 May 2017, Teletipos S.A. is listed on the Athens Exchange (Athex: TELET); from March 2016, it had been in the under suspension market segment of the stock exchange.[1] It had been listed on this stock exchange since 1994.[1]\r\n\r\nSince 2012 MEGA had been with financial problems from banks due to its borrowing practices. The channel made a decision to cease all news and live segments and focused its programming exclusively on pre-broadcast content. The terrestrial over-the-air broadcast of MEGA Channel was discontinued by Digea at 02:08:36 in the morning on October 28, 2018. It stopped broadcasting on pay-TV platforms on November 20, 2018. It continues to broadcast through online streaming from its webpage.\r\n\r\nOn 17 February 2020, Mega relaunched under the ownership of Alter Ego Mass Media S.A.', 'mega.png', 'https://www.megatv.com/', 1),
(9, 'Open TV', 'Open TV, formerly Epsilontv[3], is a Greek free-to-air television station, based in Paiania, East Attica.\r\nIt belonged to Radio-television S.A. which was sold from Communist Party of Greece to the Cypriot offshore company A-Orizon Media Ltd. under contract came on July 31, 2013, and replaced the 902 channel. The operation began on September 11, 2013, at 00:10 with broadcast Entertainment Gossip News with presenter Maria Louisa Vourou.\r\n\r\nThe first station\'s newscast sent out the same day at 19:30 pm, with main presenter journalist George Karameros. Since October 2013 the channel has a full schedule, including Press News, information, entertainment, cultural and sports programs. George Tragas for a few months showed the program State of Siege and subsequently Restricted Area. Also presented program Without Anesthetic and Sniper. Since September 2014, the main news program has been presented by Lina Klitou. In 2017, the channel was sold to Ivan Savvidis.[2][4] Filippos Vrionis was the former owner.', 'open-tv.jpg', 'https://www.tvopen.gr/', 1),
(10, 'Skai TV', 'Skai TV is a Greek free-to-air television network based in Piraeus. It is part of the Skai Group, one of the largest media groups in the country.[1] It was relaunched in its present form on 1st of April 2006 in the Athens metropolitan area, and gradually spread its coverage nationwide. Besides digital terrestrial transmission, it is available on the subscription-based encrypted services of Nova and Cosmote TV.\r\n\r\nSkai TV is also a member of Digea, a consortium of private television networks introducing digital terrestrial transmission in Greece. At launch, Skai TV opted for dubbing all foreign language content into Greek, instead of using subtitles. This is very uncommon in Greece for anything except documentaries (using voiceover dubbing) and children\'s programs (using lip-synced dubbing), so after intense criticism, the station switched to using subtitles for almost all foreign shows.', 'skai-new.jpg', 'https://www.skaitv.gr/', 1),
(11, 'Star Channel', 'Star Channel is a Greek free-to-air television channel that broadcasts a mix of foreign and Greek shows. It was launched in December 1993 and is owned by New Television S.A.\r\n\r\nStar Channel is known in Greece mostly for its programming style, both in terms of live shows and news content, with an increased focus on lifestyle, showbiz, gossip, and fashion news, and on \"comedic\" presentation.[not verified in body]\r\n\r\nIn 2010, Star Channel staff was escorted out of the Eurovision Song Contest by police after accusations were made that it was transmitting footage from the dress rehearsal illegally.[not verified in body]\r\n\r\nIn 2013, Star started combining information with entertainment, resulting in the channel\'s shift to a more serious tone. Star Channel generated €64 million in net profit in 2014 which represents a 32.2% increase from the previous year.', 'star-new.jpg', 'https://www.star.gr/tv/', 1),
(12, 'Vouli TV', 'Voulí Tileórasi (Greek: Βουλή Τηλεόραση, Parliament TV) is a Greek network dedicated to airing non-stop coverage of government proceedings and public affairs programming. The name comes from Greek Βουλή Voulí, meaning \'assembly\', \'council\', or \'parliament\'; and Tileórasi, meaning television.\r\n\r\nThe primary aim of the channel is to give each citizen direct access to the inner workings of the Hellenic Parliament. It broadcasts live all sessions of parliament and the meetings of the department of parliamentary recess. Also broadcast recorded not live, the work of the various permanent parliamentary committees. Voulí TV broadcasts a daily parliamentary newscast that gives briefings on the day-to-day business of parliament, as well as information on democratic institutions and the parliamentary history of Greece.\r\n\r\nIt also features updates on the European Parliament with special emphasis on the Greek members of parliament. The network also features non-political type programming, a block of cultural programming airs daily from 6pm-Midnight, with documentaries (covering various topics such as the arts, society, nature, history and science), films, theatre, dance, opera and classical music.\r\n\r\nVoulí TV is the only channel of its kind in Europe that broadcasts terrestrially, FTA without the need for any special equipment or subscription fees. The signal is transmitted from 19 broadcast centres on the country which enable it to reach over 50% of the population. Efforts are underway to increase transmission so that the entire country can receive the signal. Voulí is also available on satellite, the signal transmits on Hotbird 3 and HellasSat.', 'voulitv.png', 'https://www.hellenicparliament.gr/Enimerosi/Vouli-Tileorasi', 1),
(13, 'Nickelodeon', 'Nickelodeon is a Greek free-to-air television channel that was launched on 3 September 2010.[1] It was available free-to-air in the Athens area on 35 analog UHF signal broadcast from Hymettus,[1] before the analog switch off on 20 July 2012, but it is available through the Digea DVB-T2 digital consortium on 54 UHF signal broadcast from Aegina, Hymettus and Parnitha. It also broadcasts in Thessaloniki and its surrounding areas through Nickelodeon Plus, a version of the channel for the Northern Greece area.[2] Nickelodeon HD started officially broadcasting on 17 October 2011 via Cosmote TV. As with MTV, Restis Enterprises was involved in launching Nickelodeon in Greece. Nickelodeon was originally a block on Channel 9 from 2003 to 2008.', 'nickelodeon.png', 'http://www.nickelodeon.gr/', 1),
(14, 'New Epsilon TV', 'E Channel started operating on Wednesday, September 11, 2013, replacing 902 TV, bought by Vrionis one month earlier from the Communist Party of Greece. In 2017, the channel was sold to Ivan Savvidis and replaced by Open TV.\r\n\r\nThe channel was initially known as Shop TV, and was owned by Telemarketing. In December 15, 2011, it was replaced with a Greek version of Body In Balance. One year later, it was bought by Kiopra Commercial Ltd.,[1] Philippe Vriones and journalist Makis Triantafyllopoulos, as programming from the newly established programming block, GR, would take up most of BIB\'s schedule. The channel, owned by Star Hellenic Radio Television SA and later by Lavrentis Lavrentiades,[2] was renamed to Zoom TV in March 19, 2013,[3] and was the main one to host the programming block, also managed by Triantafyllopoulos, which would be also hosted by local stations throughout Greece: Delta TV (Evros), Star TV (Drama), ENA TV (Lamia), New Television (Serres), Diktyo 1 (Kastoria), XTV now Atlas TV (Chalkidiki, Imathia and Pella), Dion TV (Thessaloniki and Pieria), Astra TV (Volos, Larissa and Karditsa), TV 10 (Trikala), TV Kosmos (Rhodes and Dodecanese), FLASH TV (Kozani), Channel 4U now Notos TV (Heraklion), Sitia TV (Crete), Super B (Patras and Western Greece), ΑRT (Tripoli), Mesogeios TV (Messenia), Archipelagos TV (Lesbos, Chios and Samos), Zeus TV (Naxos), Max TV (Nafplio) and ORT (Pyrgos).[4][3] His signature talk shows Zougla and Kitrinos Typos would also be featured in the block.[5] The main news bulletin was first hosted by Giorgos Karameros, and later, by Giorgos Noulas.[6] The channel was renamed to AB Channel in summer 2014. In June 4, 2018, the channel was replaced with Extra Channel, as New Epsilon TV would start airing from its former frequency.[7] Zoom TV was formerly included on Cosmote TV, on channel 664.[8] The Greek version of Body in Balance is currently available on Cosmote TV.', 'neo-epsilon.jpg', 'https://www.youtube.com/channel/UCCzsKdpmNy-6I6mmU8zP-FA', 1);

-- --------------------------------------------------------

--
-- Table structure for table `channel_programme`
--

CREATE TABLE `channel_programme` (
  `id` int(11) NOT NULL,
  `title` varchar(128) NOT NULL,
  `description` text DEFAULT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `channel_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `channel_programme`
--

INSERT INTO `channel_programme` (`id`, `title`, `description`, `start_time`, `end_time`, `channel_id`) VALUES
(1, 'ΘΥΡΙΔΑ ΤΗΛΕΠΩΛΗΣΗΣ', NULL, '2020-09-07 06:00:00', '2020-09-07 06:30:00', 5),
(2, 'ΒΕΛΟΥΔΟ ΑΠΟ ΜΕΤΑΞΙ (E)', NULL, '2020-09-07 06:30:00', '2020-09-07 07:00:00', 5),
(3, 'ΜΗ ΜΑΣΑΣ (E)', NULL, '2020-09-07 07:00:00', '2020-09-07 08:00:00', 5),
(4, 'HAPPY DAY ΣΤΟΝ ALPHA', NULL, '2020-09-07 08:00:00', '2020-09-07 11:00:00', 5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `channels`
--
ALTER TABLE `channels`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `channel_programme`
--
ALTER TABLE `channel_programme`
  ADD PRIMARY KEY (`id`),
  ADD KEY `programme_fk` (`channel_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `channels`
--
ALTER TABLE `channels`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `channel_programme`
--
ALTER TABLE `channel_programme`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `channel_programme`
--
ALTER TABLE `channel_programme`
  ADD CONSTRAINT `programme_fk` FOREIGN KEY (`channel_id`) REFERENCES `channels` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
