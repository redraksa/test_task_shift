import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String[] TYPE_DATA = {"integers", "floats", "strings"};
    private static List<FileProc> files;
    private static CommandLineParser clp;

    private static void _processingConsole(String[] args) throws Errors.EmptyParameterException, Errors.RepeatStatisticsException, Errors.WrongFlagException, Errors.InvalidNameException, Errors.RepeatFlagException, Errors.NoArgumentsException {
        clp = new CommandLineParser();
        clp.parseCommandLine(args);

        files = new ArrayList<>();
        for (String filename: clp.getInputFiles()) {
            files.add(new FileProc(filename));
        }
    }

    private static void _readFile() throws Errors.FileReadException {
        for (FileProc file : files) {
            file.readFile();
            file.dataDistribution();
        }

        System.out.println();
    }

    private static void _writeFile() throws Errors.FileWriteException {
        ArrayList<Integer> sizes = FileProc.sizeList();

        int i = 0;
        for (String type : TYPE_DATA) {
            if (sizes.get(i++) != 0) FileProc.writeFile(clp.getStringPath(), type, clp.getStringPrefix(), clp.isFlagA(), clp.isFlagO(), clp.isFlagP());
        }
    }

    private static void _processingStatistics() {
        if (clp.isFlagS()) {
            System.out.println();
            Statistics.shortStatistic(clp.getStringPrefix());
        } else {
            if (clp.isFlagF()) {
                System.out.println();
                Statistics.fullStatistics(clp.getStringPrefix());
            }
        }
    }

    public static void main(String[] args) throws Errors.EmptyParameterException, Errors.RepeatStatisticsException,
            Errors.WrongFlagException, Errors.InvalidNameException, Errors.RepeatFlagException,
            Errors.NoArgumentsException, Errors.FileReadException, Errors.FileWriteException {
        _processingConsole(args);
        _readFile();
        _writeFile();
        _processingStatistics();
    }
}