import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileProc extends Errors{

    protected static final String FILE_EXTENSION = ".txt";

    public String filePath;
    private final List<String> lines;

    protected static List<String> strings = new ArrayList<>();
    protected static List<String> integers = new ArrayList<>();
    protected static List<String> floats = new ArrayList<>();

    public FileProc(String filePath) {
        this.filePath = filePath;
        this.lines = new ArrayList<>();
    }

    protected static ArrayList<Integer> sizeList() {
        ArrayList<Integer> list_count = new ArrayList<>();

        list_count.add(integers.size());
        list_count.add(floats.size());
        list_count.add(strings.size());

        return list_count;
    }

    protected void readFile() throws FileReadException {
        try
        {
            File file = new File(filePath);

            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }

            if (lines.isEmpty()) {
                System.out.println("Файл " + filePath + " пустой");
            } else {
                System.out.println("Данные успешно записаны из файла: " + filePath);
            }

            sc.close();
        } catch (FileNotFoundException e) {
            throw new FileReadException("Файл не найден: " + filePath);
        }
    }

    protected static void writeFile(String filePath, String dataType, String beginningFile, boolean flagA, boolean flagO, boolean flagP) throws FileWriteException {

        List<String> data = switch (dataType) {
            case "integers" -> integers;
            case "floats" -> floats;
            case "strings" -> strings;
            default -> throw new IllegalArgumentException("Неверный тип данных: " + dataType);
        };

        String fullName;

        if (flagP) {
            fullName = beginningFile + dataType + FILE_EXTENSION;
        } else {
            fullName = dataType + FILE_EXTENSION;
        }

        try {
            Path jarDir = Paths.get(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();

            Path file;

            if (!flagO) {
                file = jarDir.resolve(fullName).normalize();
            } else if (filePath.startsWith("../") || filePath.startsWith("..\\")) {
                file = jarDir.resolve(filePath).resolve(fullName).normalize();
            } else if (filePath.matches("^[a-zA-Z]:[/\\\\].*")) {
                file = Paths.get(filePath).resolve(fullName);
            } else {
                throw new IllegalArgumentException("Неверный формат пути: " + filePath);
            }

            Path parentDir = file.getParent();
            if (parentDir != null && !Files.exists(parentDir)) {
                try {
                    Files.createDirectories(parentDir);
                } catch (IOException e) {
                    throw new FileWriteException("Не удалось создать директорию: " + parentDir.toAbsolutePath());
                }
            }

            try (BufferedWriter writer = Files.newBufferedWriter(file, StandardOpenOption.CREATE,
                    flagA ? StandardOpenOption.APPEND :
                            StandardOpenOption.TRUNCATE_EXISTING)) {
                for (String line : data) {
                    writer.write(line);
                    writer.newLine();
                }

                if (flagA) {
                    System.out.println("Данные успешно добавлены в файл: " + file.toAbsolutePath());
                } else {
                    System.out.println("Данные успешно перезаписаны в файл: " + file.toAbsolutePath());
                }
            }
        } catch (Exception e) {
            throw new FileWriteException("Ошибка при обработке пути или записи в файл: " + e.getMessage());
        }
    }

    protected void dataDistribution() {
        for (String line : lines) {
            if (StringCheck.tryInteger(line)) {
                integers.add(line);
            } else {
                if (StringCheck.tryFloat(line)) {
                    floats.add(line);
                } else {
                    strings.add(line);
                }
            }
        }
    }
}
