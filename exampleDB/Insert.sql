use winerycare;

insert into bottiglie_di_vino (Nome, Anno, Capacita, Numero_di_lotto, Numero_bottiglia, Giacenza, Gradazione_alcolica, 
 Nome_tipologia, Codice_cliente, Data_acquisto, Importo_acquisto) values
('Chroma', 2020, 0.75, '256184', 66, true, 12, 'Sangiovese', null, null, null),
('Nexus', 2021, 0.75, '848614', 101, false, 13, 'Merlot', '123456', '2022-03-13', 13.30), 
('Veloce', 2019, 0.75, '916886', 54, false, 12, 'Pinot', '321654', '2020-07-23', 16.88),
('Nimbus', 2022, 0.75, '88499', 31, true, 11, 'Lambrusco', null, null, null),
('Eidos', 2021, 0.75, '45996', 14, false, 13.5, 'Montepulciano', '789456', '2022-01-09', 14.85),
('Syntony', 2022, 0.75, '778994', 27, false, 12, 'Chianti', null, null, null),
('Flux', 2018, 1, '123336', 98, false, 13, 'Chardonnay', '987654', '2019-02-18', 26.50),
('Ether', 2021, 0.75, '669885', 33, true, 12.5, 'Pinot', null, null, null),
('Vortex', 2020, 0.75, '767555', 18, true, 13.5, 'Merlot', null, null, null),
('Quanta', 2022, 1, '889774', 111, false, 11.5, 'Chianti', '321654', '2022-12-20', 18.90);

insert into clienti (Codice, Tipologia) values
(123456, 'Enoteca'),
(321654, 'Ristorante'),
(789456, 'Privato'),
(987654, 'Enoteca'),
(112233, 'Privato'),
(415263, 'Ristorante'),
(778899, 'Enoteca');

insert into contratti (Id_operaio, Tipologia, Data_inizio, Data_fine) values
('pq999', 'cpo', '2020-08-19', '2020-10-10'),
('re445', 'determinato', '2022-09-01', '2022-10-20'),
('ll789', 'determinato', '2022-09-01', '2022-10-20'),
('mn223', 'cpo', '2021-08-20', '2021-10-15'),
('kj111', 'determinato', '2020-08-19', '2020-10-10'),
('cs456', 'cpo', '2022-09-01', '2022-10-20'),
('vb125', 'determinato', '2021-08-20', '2021-10-15'),
('as889', 'determinato', '2019-09-03', '2019-10-22'),
('fr258', 'cpo', '2019-09-04', '2019-10-19'),
('bt332', 'determinato', '2021-08-30', '2021-10-10'),
('df441', 'cpo', '2018-09-02', '2018-10-30'),
('nz778', 'determinato', '2018-09-02', '2018-10-30');

insert into ditte (Nome, Partita_IVA) values
('ExcellenceSustain', '78469821689'),
('ProTech Solutions', '95412896347'),
('EfficienzaService', '34658791254'),
('ExpertMaintain', '74589623179');

insert into esecuzioni (Id_operaio, Inizio_vendemmia, Fine_vendemmia) values
('pq999', '2020-08-19', '2020-10-10'),
('re445', '2022-09-01', '2022-10-20'),
('ll789', '2022-09-01', '2022-10-20'),
('mn223', '2021-08-20', '2021-10-15'),
('kj111', '2020-08-19', '2020-10-10'),
('cs456', '2022-09-01', '2022-10-20'),
('vb125', '2021-08-20', '2021-10-15'),
('as889', '2019-09-03', '2019-10-22'),
('fr258', '2019-09-03', '2019-10-22'),
('bt332', '2021-08-20', '2021-10-15'),
('df441', '2018-09-02', '2018-10-30'),
('nz778', '2018-09-02', '2018-10-30');

insert into fasi_di_produzione (Nome, Inizio, Fine) values
('fementazione', '2021-10-20', '2021-10-30'),
('pigiatura', '2021-10-15', '2021-10-15'),
('imbottigliamento', '2021-11-10', '2021-11-10'),
('fermentazione', '2022-10-25', '2022-11-05'),
('imbottigliamento', '2022-11-10', '2022-11-10'),
('pigiatura', '2019-10-24', '2019-10-24'),
('fermentazione', '2019-10-25', '2019-11-03'),
('fementazione', '2021-11-01', '2021-11-10'),
('fermentazione', '2020-10-12', '2020-10-20');

insert into fornitori (Nome, Partita_IVA) values
('VinotasteX', 26589745123),
('VinnoTech', 65874892130),
('EnsoFerm', 06574894120),
('VitiQube', 30214780259),
('VinVanguard', 65402589713),
('FermentiXpert', 33014799420),
('FermaFlavor', 85422069871);

insert into macchinari (Nome, Data_acquisto, Prezzo, Codice, Anno_di_produzione, Partita_IVA_fornitore) values
('VasoFerm', '2018-03-25', 6000, '111e', 2015, 65874894120),
('Pigiavino', '2020-07-11', 2000, '222r', 2017, 30214780259),
('FermaPress', '2019-04-26', 7500, '333h', 2016, 30214780259),
('VinoGrip', '2021-02-28', 2500, '444c', 2020, 65402589713);

insert into manutenzioni (Codice_macchinario, Tipologia, Ditta) values
('222r', 'sostituzione pezzi', '95412896347'),
('111e', 'pulizia', '34658791254'),
('444c', 'controllo', '74589623179'),
('333h', 'lubrificazione ingranaggi', '74589623179'),
('111e', 'verniciatura', '78469821689');

insert into mosto (Anno, Peso, Gradazione_zuccherina, Codice, Nome_fase, Inizio_fase, Fine_fase) values
(2021, 2000, 19, '2n2j2j', 'fementazione', '2021-10-20', '2021-10-30'),
(2020, 2100, 18, '45cy4o', 'fermentazione', '2020-10-12', '2020-10-20'),
(2022, 1900, 16.5, '3l4gjk', 'fermentazione', '2022-10-25', '2022-11-05'),
(2019, 2000, 17, '6vi744', 'fermentazione', '2019-10-25', '2019-11-03'),
(2021, 2200, 18.5, 'y7r447', 'fementazione', '2021-11-01', '2021-11-10');

insert into operai (Nome, Cognome, Data_di_nascita, Indirizzo, Id_operaio) values
('Giuseppe', 'Rossi', '1979-04-22', 'Via Roma, 93', 'pq999'),
('Maria', 'Bianchi', '1991-01-11', 'Viale dei Pini, 45', 're445'),
('Francesco', 'Romano', '1988-12-20', 'Piazza del Duomo, 1', 'll789'),
('Carlo', 'Ferrari', '1976-10-03', 'Corso Umberto I, 555', 'mn223'),
('Laura', 'Marino', '1982-06-07', 'Via dei Fiori, 8', 'kj111'),
('Giovanni', 'Conti', '1977-05-09', 'Via Nazionale, 4567', 'cs456'),
('Andrea', 'Costa', '1989-11-15', 'Via dei Colli, 18', 'vb125'),
('Alessandro', 'Martini', '1969-03-24', 'Via Aurelia, 7890', 'as889'),
('Federica', 'Russo', '1987-08-21', 'Via Verdi, 15', 'fr258'),
('Marco', 'Greco', '1994-02-18', 'Piazza San Marco, 7', 'bt332'),
('Davide', 'Lombardi', '1986-04-13', 'Lungomare Adriatico, 123', 'df441'),
('Luca', 'Rizzo', '1993-06-19', 'Corso Italia, 320', 'nz778');

insert into prodotti_di_listino (Partita_IVA_fornitore, Nome, Prezzo) values
('26589745123', 'Vasca di fermentazione da 3000L', 2000.00),
('26589745123', 'Barrique da 220L', 700.00),
('26589745123', 'Bottiglie', 0.50),
('26589745123', 'Pigiatrice', 1680.00),
('26589745123', 'Tappi', 0.10),
('26589745123', 'Lieviti', 2.50),
('26589745123', 'Chiarificanti', 10.80),
('26589745123', 'Attivanti', 3.80),
('65874892130', 'Pigiatrice', 1700.00),
('65874892130', 'Bottiglie', 1.00),
('65874892130', 'Vasca di fermentazione da 3000L', 1800.00),
('65874892130', 'Barrique da 220L', 650.00),
('65874892130', 'Tappi', 0.09),
('65874892130', 'Lieviti', 27.10),
('65874892130', 'Chiarificanti', 12.50),
('65874892130', 'Attivanti', 4.30),
('06574894120', 'Tappi', 0.20),
('06574894120', 'Vasca di fermentazione da 3000L', 2200.00),
('06574894120', 'Barrique da 220L', 580.00),
('06574894120', 'Bottiglie', 0.75),
('06574894120', 'Pigiatrice', 1720.00),
('06574894120', 'Lieviti', 25.70),
('06574894120', 'Chiarificanti', 20.50),
('06574894120', 'Attivanti', 2.75),
('30214780259', 'Bottiglie', 0.60),
('30214780259', 'Vasca di fermentazione da 3000L', 1880.00),
('30214780259', 'Barrique da 220L', 600.00),
('30214780259', 'Pigiatrice', 1630.50),
('30214780259', 'Tappi', 0.08),
('30214780259', 'Lieviti', 19.90),
('30214780259', 'Chiarificanti', 18.90),
('30214780259', 'Attivanti', 6.0),
('65402589713', 'Lieviti', 20.50),
('65402589713', 'Fermentatori in acciaio inox da 500L', 600.00),
('65402589713', 'Vasca di fermentazione da 3000L', 1999.90),
('65402589713', 'Barrique da 220L', 560.00),
('65402589713', 'Bottiglie', 0.90),
('65402589713', 'Pigiatrice', 1700.00),
('65402589713', 'Tappi', 0.15),
('65402589713', 'Chiarificanti', 10.50),
('65402589713', 'Attivanti', 5.20),
('33014799420', 'Chiarificanti', 10.50),
('33014799420', 'Attivanti', 5.70),
('33014799420', 'Vasca di fermentazione da 3000L', 2057.50),
('33014799420', 'Barrique da 220L', 700.00),
('33014799420', 'Bottiglie', 0.85),
('33014799420', 'Pigiatrice', 1590.00),
('33014799420', 'Tappi', 0.16),
('33014799420', 'Lieviti', 23.70),
('85422069871', 'Vasca di fermentazione da 3000L', 1890.99),
('85422069871', 'Barrique da 220L', 720.00),
('85422069871', 'Bottiglie', 0.65),
('85422069871', 'Pigiatrice', 1650.00),
('85422069871', 'Tappi', 0.12),
('85422069871', 'Lieviti', 21.99),
('85422069871', 'Chiarificanti', 10.50),
('85422069871', 'Attivanti', 5.70);

insert into prodotti_enologici (Nome, Data_acquisto, Prezzo, Codice, Peso, Partita_IVA_fornitore, 
Nome_fase, Inizio_fase, Fine_fase) values
('Attivanti', '2021-08-15', 114, 'AT001', 30, '26589745123', 'fermentazione', '2021-10-20', '2021-10-30'),
('Lieviti', '2020-08-20', 542, 'LV007', 20, '65874892130', 'fermentazione', '2020-10-12', '2020-10-20'),
('Chiarificanti', '2022-04-17', 205, 'CH013', 10, '06574894120', 'fermentazione', '2022-10-25', '2022-11-05'),
('Attivanti', '2019-08-20', 162, 'AT019', 27, '30214780259', 'fermentazione', '2019-10-25', '2019-11-03'),
('Lieviti', '2021-07-30', 389.5, 'LV025', 19, '65402589713', 'fermentazione', '2021-11-01', '2021-11-10'),
('Chiarificanti', '2019-06-10', 137.5, 'CH008', 11, '65874892130', 'fermentazione', '2019-10-25', '2019-11-03'),
('Lieviti', '2019-09-01', 487.8, 'LV010', 18, '65874892130', 'fermentazione', '2019-10-25', '2019-11-03'),
('Lieviti', '2022-08-18', 430.5, 'LV021', 21, '65402589713', 'fermentazione', '2022-10-25', '2022-11-05'),
('Chiarificanti', '2022-05-25', 226.8, 'CH018', 12, '30214780259', null, null, null),
('Attivanti', '2023-08-10', 183, 'AT016', 30.5, '30214780259', null, null, null),
('Lieviti', '2023-09-03', 567.97, 'LV012', 22.1, '06574894120', null, null, null);

insert into prodotti_per_imballaggio (Nome, Data_acquisto, Prezzo, Codice, Quantita, Partita_IVA_fornitore, 
Nome_fase, Inizio_fase, Fine_fase) values
('Tappi', '2021-02-18', 800, 'TP001', 4000, '06574894120', 'imbottigliamento', '2021-11-10', '2021-11-10'),
('Bottiglie', '2022-09-15', 3600, 'BT004', 4000, '65402589713', 'imbottigliamento', '2022-11-10', '2022-11-10'),
('Tappi', '2022-06-05', 270, 'TP007', 3000, '65874892130', 'imbottigliamento', '2022-11-10', '2022-11-10'),
('Bottiglie', '2021-08-28', 2250, 'BT008', 3000, '06574894120', 'imbottigliamento', '2022-11-10', '2022-11-10'),
('Tappi', '2021-04-02', 360, 'TP009', 3000, '85422069871', 'imbottigliamento', '2021-11-10', '2021-11-10'),
('Bottiglie', '2020-11-20', 3600, 'BT010', 4000, '65402589713', 'imbottigliamento', '2021-11-10', '2021-11-10'),
('Bottiglie', '2021-05-08', 2000, 'BT012', 4000, '26589745123', 'imbottigliamento', '2022-11-10', '2022-11-10');

insert into produzioni (Nome_fase, Inizio_fase, Fine_fase, Numero_di_lotto, Numero_bottiglia) values
('fementazione', '2021-10-20', '2021-10-30', 848614, 101),
('pigiatura', '2021-10-15', '2021-10-15', 848614, 101),
('imbottigliamento', '2021-11-10', '2021-11-10', 848614, 101),
('fermentazione', '2022-10-25', '2022-11-05', 778994, 27),
('imbottigliamento', '2022-11-10', '2022-11-10', 778994, 27),
('pigiatura', '2019-10-24', '2019-10-24', 916886, 54),
('fermentazione', '2019-10-25', '2019-11-03', 916886, 54),
('fementazione', '2021-11-01', '2021-11-10', 45996, 14),
('fermentazione', '2020-10-12', '2020-10-20', 256184, 66);

insert into tipologie (Nome, Zona_origine, Prezzo_di_vendita) values
('Chardonnay', 'Borgogna', 26.50),
('Chianti', 'Toscana', 18.90),
('Lambrusco', 'Emilia Romagna', 23.50), 
('Merlot', 'Gironde', 17.70),
('Montepulciano', 'Abruzzo', 19.80),
('Pinot', 'Borgogna', 22.50),
('Sangiovese', 'Toscana', 18.30);

insert into utilizzi_macchinari (Nome_fase, Inizio_fase, Fine_fase, Codice_macchinario) values
('fementazione', '2021-10-20', '2021-10-30', '111e'),
('pigiatura', '2021-10-15', '2021-10-15', '222r'),
('imbottigliamento', '2021-11-10', '2021-11-10', '444c'),
('fermentazione', '2022-10-25', '2022-11-05', '111e'),
('imbottigliamento', '2022-11-10', '2022-11-10', '444c'),
('pigiatura', '2019-10-24', '2019-10-24', '222r'),
('fermentazione', '2019-10-25', '2019-11-03', '111e'),
('fementazione', '2021-11-01', '2021-11-10', '111e'),
('fermentazione', '2020-10-12', '2020-10-20', '111e');

insert into uva_raccolta (Nome_tipologia, Anno, Peso, Nome_fase, Inizio_fase, Fine_fase, Inizio_vendemmia, Fine_vendemmia) values
('Pinot', 2019, 2000, 'pigiatura','2019-10-24', '2019-10-24', '2019-09-03', '2019-10-22'),
('Merlot', 2021, 4990, 'pigiatura','2021-10-15', '2021-10-15', '2021-08-20', '2021-10-15');

insert into vendemmie (Inizio, Fine) values
('2019-09-03', '2019-10-22'),
('2021-08-20', '2021-10-15'),
('2020-08-19', '2020-10-10'),
('2018-09-02', '2018-10-30'),
('2022-09-01', '2022-10-20');