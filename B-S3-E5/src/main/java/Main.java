import entities.*;
import enumeration.Periodicita;


import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Archivio archivio = new Archivio();

        System.out.println("Benvenuto nel catalogo bibliotecario");

        while (true) {
            System.out.println("\nCosa vuoi fare?");
            System.out.println("1 - Aggiungi Libro");
            System.out.println("2 - Aggiungi Rivista");
            System.out.println("3 - Aggiungi Nuovo Utente");
            System.out.println("4 - Registra Nuovo Prestito");
            System.out.println("5 - Ricerca per ISBN");
            System.out.println("6 - Rimuovi elemento tramite codice ISBN");
            System.out.println("7 - Ricerca elemento per anno pubblicazione");
            System.out.println("8 - Ricerca libro per autore");
            System.out.println("9 - Ricerca per titolo o parte di esso");
            System.out.println("10 - Ricerca elemento attualmente in prestito dato un numero di tessera utente");
            System.out.println("11- Ricerca prestiti scaduti e non ancora restituiti");
            System.out.println("0 - Esci");
            System.out.print("Scelta: ");
            String scelta = scanner.nextLine();

            try {
                switch (scelta) {
                    case "1" -> {
                        System.out.print("ISBN: ");
                        String isbn = scanner.nextLine();

                        System.out.print("Titolo: ");
                        String titolo = scanner.nextLine();

                        System.out.print("Anno pubblicazione: ");
                        int anno = Integer.parseInt(scanner.nextLine());

                        System.out.print("Numero pagine: ");
                        int pagine = Integer.parseInt(scanner.nextLine());

                        System.out.print("Autore: ");
                        String autore = scanner.nextLine();

                        System.out.print("Genere: ");
                        String genere = scanner.nextLine();

                        Libri libro = new Libri(isbn, titolo, anno, pagine, autore, genere);
                        archivio.aggiungiElementoCatalogo(libro);
                        System.out.println("Libro aggiunto con successo!");
                    }

                    case "2" -> {
                        System.out.print("ISBN: ");
                        String isbn = scanner.nextLine();

                        System.out.print("Titolo: ");
                        String titolo = scanner.nextLine();

                        System.out.print("Anno pubblicazione: ");
                        int anno = Integer.parseInt(scanner.nextLine());

                        System.out.print("Numero pagine: ");
                        int pagine = Integer.parseInt(scanner.nextLine());

                        System.out.print("Periodicità (SETTIMANALE, MENSILE, SEMESTRALE): ");
                        Periodicita periodicita = Periodicita.valueOf(scanner.nextLine().toUpperCase());

                        Riviste rivista = new Riviste(isbn, titolo, anno, pagine, periodicita);
                        archivio.aggiungiElementoCatalogo(rivista);
                        System.out.println("Rivista aggiunta con successo!");
                    }

                    case "3" -> {
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();

                        System.out.print("Cognome: ");
                        String cognome = scanner.nextLine();

                        System.out.print("Data di nascita (formato: yyyy-MM-dd): ");
                        String dataNascita = scanner.nextLine();
                        LocalDate dataDiNascita = LocalDate.parse(dataNascita);

                        System.out.print("Numero tessera: ");
                        int numeroTessera = Integer.parseInt(scanner.nextLine());

                        Utente utenti = new Utente(nome,cognome,dataDiNascita,numeroTessera);
                        archivio.aggiungiNuovoUtente(utenti);
                        System.out.println("Utente aggiunto con successo!");
                    }

                    case "4" -> {
                        System.out.print("Utente (numero tessera): ");
                        int numeroTessera = Integer.parseInt(scanner.nextLine());

                        System.out.print("Elemento prestato (ISBN): ");
                        String isbn = scanner.nextLine();

                        System.out.print("Data inizio prestito (yyyy-MM-dd): ");
                        LocalDate dataInizio = LocalDate.parse(scanner.nextLine());

                        System.out.print("Data restituzione effettiva (formato: yyyy-MM-dd, lascia vuoto se non disponibile): ");
                        String dataRestituzione = scanner.nextLine();

                        LocalDate dataRestituzioneEffettiva = null;
                        if (!dataRestituzione.isBlank()) {
                            dataRestituzioneEffettiva = LocalDate.parse(dataRestituzione);
                        }

                        // Recupera oggetti da database
                        Utente utente = archivio.cercaPerNumeroTessera(numeroTessera);
                        Catalogo catalogo = archivio.cercaPerISBN(isbn);

                        if (utente == null || catalogo == null) {
                            System.out.println("Utente o Catalogo non trovati.");
                        } else {
                            Prestito prestito = new Prestito(dataInizio, dataInizio.plusDays(30), dataRestituzioneEffettiva);
                            prestito.setUtente(utente);
                            prestito.setCatalogo(catalogo);

                            archivio.aggiungiNuovoPrestito(prestito);
                            System.out.println("Prestito aggiunto con successo!");
                        }

                    }

                    case "5" -> {
                        System.out.print("Inserisci ISBN da cercare: ");
                        String isbn = scanner.nextLine();
                        Catalogo trovato = archivio.cercaPerISBN(isbn);
                        System.out.println("Elemento trovato:\n" + trovato);
                    }

                    case "6" -> {
                        System.out.print("Inserisci ISBN dell'elemento da rimuovere: ");
                        String isbn = scanner.nextLine();
                        archivio.rimuoviElemento(isbn);
                        System.out.println("Elemento rimosso con successo.");
                    }

                    case "7" -> {
                        System.out.print("Inserisci anno pubblicazione: ");
                        int anno = Integer.parseInt(scanner.nextLine());
                        List<Catalogo> perAnno = archivio.cercaPerAnno(anno);
                        if (perAnno.isEmpty()) {
                            System.out.println("Nessun elemento trovato per l'anno " + anno);
                        } else {
                            perAnno.forEach(System.out::println);
                        }
                    }

                    case "8" -> {
                        System.out.print("Inserisci autore: ");
                        String autore = scanner.nextLine();
                        List<Libri> libriAutore = archivio.cercaLibriPerAutore(autore);
                        if (libriAutore.isEmpty()) {
                            System.out.println("Nessun libro trovato per l'autore " + autore);
                        } else {
                            libriAutore.forEach(System.out::println);
                        }
                    }

                    case "9" -> {
                        System.out.print("Inserisci parte del titolo da cercare: ");
                        String titolo = scanner.nextLine();
                        List<Catalogo> risultati = archivio.cercaPerTitolo(titolo);
                        if (risultati.isEmpty()) {
                            System.out.println("Nessun elemento trovato.");
                        } else {
                            risultati.forEach(System.out::println);
                        }
                    }

                    case "10" ->{
                        System.out.print("Inserisci il numero di tessera dell'utente: ");
                        int numeroTessera = Integer.parseInt(scanner.nextLine());

                        List<Catalogo> prestitiAttivi = archivio.elementiInPrestitoPerUtente(numeroTessera);

                        if (prestitiAttivi.isEmpty()) {
                            System.out.println("Nessun elemento attualmente in prestito per questo utente.");
                        } else {
                            System.out.println("Elementi attualmente in prestito:");
                            for (Catalogo c : prestitiAttivi) {
                                System.out.println(c);
                            }
                        }
                    }

                    case "11" ->{
                        List<Prestito> prestitiScaduti = archivio.getPrestitiScaduti();

                        if (prestitiScaduti.isEmpty()) {
                            System.out.println("Non ci sono prestiti scaduti.");
                        } else {
                            System.out.println("Prestiti scaduti non restituiti:");
                            for (Prestito p : prestitiScaduti) {
                                System.out.println("Utente: " + p.getUtente().getNome() + " " + p.getUtente().getCognome());
                                System.out.println("Elemento: " + p.getCatalogo().getTitolo());
                                System.out.println("Data restituzione prevista: " + p.getDataRestituzionePrevista());
                                System.out.println("-------------");
                            }
                        }
                    }

                    case "0" -> {
                        System.out.println("Chiusura programma...");
                        scanner.close();
                        return;
                    }

                    default -> System.out.println("Scelta non valida.");
                }

            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }
}

