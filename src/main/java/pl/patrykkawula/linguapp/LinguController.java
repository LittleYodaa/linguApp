package pl.patrykkawula.linguapp;

import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

@Controller
public class LinguController {

    private final static int MAX_TEST_LENGHT = 10;
    private final Scanner scanner;
    private final EntryRepository entryRepository;
    private final FileService fileService;

    private final ConsoleOutputWriter consoleOutputWriter;

    public LinguController(EntryRepository entryRepository, FileService fileService, Scanner scanner, ConsoleOutputWriter consoleOutputWriter) {
        this.entryRepository = entryRepository;
        this.fileService = fileService;
        this.scanner = scanner;
        this.consoleOutputWriter = consoleOutputWriter;
    }

    public void mainLoop() {
        Option option = null;
        do {
            printOption();
            try {
                option = chooseOption();
                executeOption(option);
            } catch (IllegalArgumentException e) {
                consoleOutputWriter.println(e.getMessage());
            }
        } while (option != Option.EXIT);
    }

    private void executeOption(Option option) {
        switch (option) {
            case ADD_ENTRY -> addEntry();
            case START_TEST -> startTest();
            case EXIT -> exit();
        }
    }

    private void startTest() {
        if (entryRepository.isEmpty()) {
            consoleOutputWriter.println("Brak słówek do testu, dodaj nowe słówka");
            mainLoop();
        }
        int score = 0;
        int maxScore = Math.min(entryRepository.getSize(), MAX_TEST_LENGHT);
        Set<Entry> testWords = entryRepository.getRandomEntries(Math.min(entryRepository.getSize(), MAX_TEST_LENGHT));
        for (Entry testWord : testWords) {
            String original = testWord.getOriginal();
            consoleOutputWriter.println(original);
            consoleOutputWriter.println("Podaj tłumaczenie");
            String translation = scanner.nextLine();
            if (translation.equals(testWord.getTranslation())) {
                score++;
                consoleOutputWriter.println(score + "/" + maxScore);
            } else {
                consoleOutputWriter.println("Nieprawidłowa odpowiedź");
            }
        }
        consoleOutputWriter.println("Twój wynik " + score + "/" + maxScore);
    }

    private void addEntry() {
        consoleOutputWriter.println("Podaj słowo");
        String original = scanner.nextLine();
        consoleOutputWriter.println("Podaj tłumaczenie");
        String translation = scanner.nextLine();
        Entry entry = new Entry(original, translation);
        entryRepository.add(entry);
    }

    private void exit() {
        try {
            fileService.saveEntryInFile(entryRepository.getAll());
            consoleOutputWriter.println("Zapisano do pliku");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        consoleOutputWriter.println("Koniec programu");
    }

    private Option chooseOption() {
        Option option;
        int optionNumber;
        do {
            optionNumber = scanner.nextInt();
            scanner.nextLine();
            option = Option.createOptionFromInt(optionNumber);
        } while (optionNumber < 0 || optionNumber > Option.values().length);
        return option;
    }

    private void printOption() {
        consoleOutputWriter.println("Wybierz opcję");
        for (Option option : Option.values()) {
            consoleOutputWriter.println(option.toString());
        }
    }

    private enum Option {
        ADD_ENTRY(1, "Dodaj słowo"),
        START_TEST(2, "Rozpocznij test"),
        EXIT(3, "Wyjście z programu");

        private int option;
        private String description;

        Option(int option, String description) {
            this.option = option;
            this.description = description;
        }

        static Option createOptionFromInt(int option) {
            if (option < 0 || option > values().length) {
                throw new IllegalArgumentException("Opcja o takim numerze nie istnieje");
            }
            return values()[option - 1];
        }

        @Override
        public String toString() {
            return option + " - " + description;
        }
    }
}
