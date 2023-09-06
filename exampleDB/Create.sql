# ---------------------------------------------------------------------- #
# Project name:          WineryCare                                      #
# Author:                Chiara Giangiulli                               #
# ---------------------------------------------------------------------- #

drop database if exists winerycare;

create database if not exists winerycare;

use winerycare;

create table BOTTIGLIE_DI_VINO (
	Nome char(20) not null,
	Anno numeric(4) not null,
	Capacita float(5) not null,
	Numero_di_lotto char(10) not null,
	Numero_bottiglia numeric(4) not null,
	Giacenza char not null,
	Gradazione_alcolica float(5) not null,
	Nome_tipologia char(30) not null,
	Codice_cliente char(6),
	Data_acquisto date,
	Importo_acquisto float(10),
	primary key (Numero_di_lotto, Numero_bottiglia));

create table CLIENTI (
	Codice char(6) not null,
	Tipologia char(20) not null,
	primary key (Codice));

create table CONTRATTI (
	Id_operaio char(10) not null,
	Tipologia char(20) not null,
	Data_inizio date not null,
	Data_fine date not null,
	primary key (Id_operaio, Data_inizio, Data_fine));

create table DITTE (
	Nome char(20) not null,
	Partita_IVA char(11) not null,
	primary key (Partita_IVA));

create table ESECUZIONI (
	Id_operaio char(10) not null,
	Inizio_vendemmia date not null,
	Fine_vendemmia date not null,
	primary key (Inizio_vendemmia, Fine_vendemmia, Id_operaio));

create table FASI_DI_PRODUZIONE (
	Nome char(20) not null,
	Inizio date not null,
	Fine date not null,
	primary key (Nome, Inizio, Fine));

create table FORNITORI (
	Nome char(20) not null,
	Partita_IVA char(11) not null,
	primary key (Partita_IVA));

create table MACCHINARI (
	Nome char(30) not null,
	Data_acquisto date not null,
	Prezzo float(10) not null,
	Codice char(4) not null,
	Anno_di_produzione numeric(4) not null,
	Partita_IVA_fornitore char(11) not null,
	primary key (Codice));

create table MANUTENZIONI (
	Codice_macchinario char(11) not null,
	Tipologia char(30) not null,
	Ditta char(20) not null,
	primary key (Codice_macchinario, Tipologia));

create table MOSTO (
	Anno char(4) not null,
	Peso float(10) not null,
	Gradazione_zuccherina float(6) not null,
	Codice char(6) not null,
	Nome_fase char(20) not null,
	Inizio_fase date not null,
	Fine_fase date not null,
	primary key (Codice));

create table OPERAI (
	Nome char(20) not null,
	Cognome char(20) not null,
	Data_di_nascita date not null,
	Indirizzo char(50) not null,
	Id_operaio char(5) not null,
	primary key (Id_operaio));

create table PRODOTTI_DI_LISTINO (
	Partita_IVA_fornitore char(11) not null,
	Nome char(40) not null,
	Prezzo float(6) not null,
	primary key (Partita_IVA_fornitore, Nome));

create table PRODOTTI_ENOLOGICI (
	Nome char(40) not null,
	Data_acquisto date not null,
	Prezzo float(6) not null,
	Codice char(5) not null,
	Peso float(10) not null,
	Partita_IVA_fornitore char(11) not null,
	Nome_fase char(20),
	Inizio_fase date,
	Fine_fase date,
	primary key (Codice));

create table PRODOTTI_PER_IMBALLAGGIO (
	Nome char(20) not null,
	Data_acquisto date not null,
	Prezzo float(6) not null,
	Codice char(5) not null,
	Quantita int(5) not null,
	Partita_IVA_fornitore char(11) not null,
	Nome_fase char(20),
	Inizio_fase date,
	Fine_fase date,
	primary key (Codice));

create table PRODUZIONI (
	Nome_fase char(20) not null,
	Inizio_fase date not null,
	Fine_fase date not null,
	Numero_di_lotto char(10) not null,
	Numero_bottiglia numeric(4) not null,
	primary key (Nome_fase, Inizio_fase, Fine_fase, Numero_di_lotto, Numero_bottiglia));

create table TIPOLOGIE (
	Nome char(30) not null,
	Zona_origine char(20) not null,
	Prezzo_di_vendita float(6) not null,
	primary key (Nome));

create table UTILIZZI_MACCHINARI (
	Nome_fase char(20) not null,
	Inizio_fase date not null,
	Fine_fase date not null,
	Codice_macchinario char(4) not null,
	primary key (Codice_macchinario, Nome_fase, Inizio_fase, Fine_fase));

create table UVA_RACCOLTA (
	Nome_tipologia char(20) not null,
	Anno char(4) not null,
	Peso float(20) not null,
	Nome_fase char(20) not null,
	Inizio_fase date not null,
	Fine_fase date not null,
	Inizio_vendemmia date not null,
	Fine_vendemmia date not null,
	primary key (Nome_tipologia, Anno));

create table VENDEMMIE (
	Inizio date not null,
	Fine date not null,
	primary key (Inizio, Fine));


-- Constraints Section
-- ___________________

alter table BOTTIGLIE_DI_VINO add constraint FKComposizione
	foreign key (Nome_tipologia)
	references TIPOLOGIE(Nome);

-- Not implemented
-- alter table TIPOLOGIE add constraint 
-- 	check(exists(select * from BOTTIGLIE_DI_VINO
-- 	            where BOTTIGLIE_DI_VINO.Nome_tipologia = Nome));

alter table BOTTIGLIE_DI_VINO add constraint FKAcquisto
	foreign key (Codice_cliente)
	references CLIENTI(Codice);

alter table CONTRATTI add constraint FKSottoscrizione
	foreign key (Id_operaio)
	references OPERAI(Id_operaio);

alter table ESECUZIONI add constraint FKEse_VEN
	foreign key (Inizio_vendemmia, Fine_vendemmia)
	references VENDEMMIE(Inizio,Fine);

alter table ESECUZIONI add constraint FKEse_OPE
	foreign key (Id_operaio)
	references OPERAI(Id_operaio);

alter table MACCHINARI add constraint FKDistribuzione_1
	foreign key (Partita_IVA_fornitore)
	references FORNITORI(Partita_IVA);

alter table MANUTENZIONI add constraint FKAssegnazione
	foreign key (Ditta)
	references DITTE(Partita_IVA);

alter table MANUTENZIONI add constraint FKRichiesta
	foreign key (Codice_macchinario)
	references MACCHINARI(Codice);

alter table MOSTO add constraint FKUtilizzo_4
	foreign key (Nome_fase, Inizio_fase, Fine_fase)
	references FASI_DI_PRODUZIONE(Nome,Inizio,Fine);

-- Not implemented
-- alter table FASI_DI_PRODUZIONE add constraint 
-- 	check(exists(select * from MOSTO
-- 	            where MOSTO.Nome_fase = Nome and MOSTO.Inizio_fase = Inizio and MOSTO.Fine_fase = Fine));

alter table PRODOTTI_DI_LISTINO add constraint FKOfferta
	foreign key (Partita_IVA_fornitore)
	references FORNITORI(Partita_IVA);

-- Not implemented
-- alter table FORNITORI add constraint 
-- 	check(exists(select * from PRODOTTI_DI_LISTINO
-- 	            where PRODOTTI_DI_LISTINO.Partita_IVA_fornitore = Partita_IVA));

alter table PRODOTTI_ENOLOGICI add constraint FKDistribuzione_3
	foreign key (Partita_IVA_fornitore)
	references FORNITORI(Partita_IVA);

alter table PRODOTTI_ENOLOGICI add constraint FKUtilizzo_3
	foreign key (Nome_fase, Inizio_fase, Fine_fase)
	references FASI_DI_PRODUZIONE(Nome,Inizio,Fine);

-- Not implemented
-- alter table FASI_DI_PRODUZIONE add constraint 
-- 	check(exists(select * from PRODOTTI_ENOLOGICI
-- 	            where PRODOTTI_ENOLOGICI.Nome_fase = Nome and PRODOTTI_ENOLOGICI.Inizio_fase = Inizio and PRODOTTI_ENOLOGICI.Fine_fase = Fine));

alter table PRODOTTI_PER_IMBALLAGGIO add constraint FKDistribuzione_2
	foreign key (Partita_IVA_fornitore)
	references FORNITORI(Partita_IVA);

alter table PRODOTTI_PER_IMBALLAGGIO add constraint FKUtilizzo_2
	foreign key (Nome_fase, Inizio_fase, Fine_fase)
	references FASI_DI_PRODUZIONE(Nome,Inizio,Fine);

-- Not implemented
-- alter table FASI_DI_PRODUZIONE add constraint 
-- 	check(exists(select * from PRODOTTI_PER_IMBALLAGGIO
-- 	            where PRODOTTI_PER_IMBALLAGGIO.Nome_fase = Nome and PRODOTTI_PER_IMBALLAGGIO.Inizio_fase = Inizio and PRODOTTI_PER_IMBALLAGGIO.Fine_fase = Fine));

alter table PRODUZIONI add constraint FKPro_BOT
	foreign key (Numero_di_lotto, Numero_bottiglia)
	references BOTTIGLIE_DI_VINO(Numero_di_lotto, Numero_bottiglia);

-- Not implemented
-- alter table BOTTIGLIE_DI_VINO add constraint 
-- 	check(exists(select * from PRODUZIONI
-- 	            where PRODUZIONI.Numero_di_lotto = Numero_di_lotto and PRODUZIONI.Numero_bottiglia = Numero_bottiglia));

alter table PRODUZIONI add constraint FKPro_FAS
	foreign key (Nome_fase, Inizio_fase, Fine_fase)
	references FASI_DI_PRODUZIONE(Nome,Inizio,Fine);

-- Not implemented
-- alter table FASI_DI_PRODUZIONE add constraint 
-- 	check(exists(select * from PRODUZIONI
-- 	            where PRODUZIONI.Nome_fase = Nome and PRODUZIONI.Inizio_fase = Inizio and PRODUZIONI.Fine_fase = Fine));

alter table UTILIZZI_MACCHINARI add constraint FKUti_MAC
	foreign key (Codice_macchinario)
	references MACCHINARI(Codice);

-- Not implemented
-- alter table MACCHINARI add constraint 
-- 	check(exists(select * from UTILIZZI_MACCHINARI
-- 	            where UTILIZZI_MACCHINARI.Codice_macchinario = Codice));

alter table UTILIZZI_MACCHINARI add constraint FKUti_FAS
	foreign key (Nome_fase, Inizio_fase, Fine_fase)
	references FASI_DI_PRODUZIONE(Nome,Inizio,Fine);

-- Not implemented
-- alter table FASI_DI_PRODUZIONE add constraint 
-- 	check(exists(select * from UTILIZZI_MACCHINARI
-- 	            where UTILIZZI_MACCHINARI.Nome_fase = Nome and UTILIZZI_MACCHINARI.Inizio_fase = Inizio and UTILIZZI_MACCHINARI.Fine_fase = Fine));

alter table UVA_RACCOLTA add constraint FKRiferimento
	foreign key (Nome_tipologia)
	references TIPOLOGIE(Nome);

-- Not implemented
-- alter table TIPOLOGIE add constraint 
-- 	check(exists(select * from UVA_RACCOLTA
-- 	            where UVA_RACCOLTA.Nome_tipologia = Nome));

alter table UVA_RACCOLTA add constraint FKUtilizzo_5
	foreign key (Nome_fase, Inizio_fase, Fine_fase)
	references FASI_DI_PRODUZIONE(Nome,Inizio,Fine);

-- Not implemented
-- alter table FASI_DI_PRODUZIONE add constraint 
-- 	check(exists(select * from UVA_RACCOLTA
-- 	            where UVA_RACCOLTA.Nome_fase = Nome and UVA_RACCOLTA.Inizio_fase = Inizio and UVA_RACCOLTA.Fine_fase = Fine));

alter table UVA_RACCOLTA add constraint FKProvenienza
	foreign key (Inizio_vendemmia, Fine_vendemmia)
	references VENDEMMIE(Inizio,Fine);
