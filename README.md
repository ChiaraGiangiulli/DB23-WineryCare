# WineryCare
Il progetto vuole realizzare un database di supporto per la gestione di una cantina privata per permettere di gestire le fasi di produzione del vino, acquisto e manutenzione di prodotti utili e vendita del prodotto finito ai clienti. 
Le funzionalità realizzate sono:
1.	Registrare nuovi clienti e fornitori
2.	Registrare l’acquisto di un nuovo prodotto da un dato fornitore
3.	Registrare la vendita di un prodotto finito ad un cliente
4.	Registrare nuove ditte manutentrici
5.	Aggiornamento prezzi di vendita
6.	Aggiunta tipologia di uva e vino venduta
7.	Visualizzare giacenze in magazzino
8.	Visualizzare la lista di operai che hanno un contratto in un dato periodo
9.	Visualizzare il prezzo di una data tipologia di vino
10.	Ottenere il fornitore più vantaggioso per un prodotto
11.	Ottenere la quantità di vino venduto in un anno
12.	Ottenere la quantità di uva raccolta in un anno
13.	Ottenere la tipologia di vino più venduta in un anno
14.	Controllare il tempo di fermentazione in una vasca

# Come utilizzare l'applicazione
Avviata l'applicazione nella classe WineryCare (it.unibo.winerycare), viene richiesto l'accesso tramite le credenziali mysql. Il database deve, quindi, prima essere generato utilizzando i files Create.sql e Insert.sql (nella cartella ExampleDB) per poi essere manipolato attraverso l'applicazione. Il nome del database è "winerycare", è possibile modificarlo tramite la costante DB_NAME nella classe StartView (it.unibo.winerycare.view). Eseguito il login viene aperta una finestra attraverso la quale è possibile scegliere ed eseguire le operazioni desiderate.
