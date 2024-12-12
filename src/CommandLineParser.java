import java.util.ArrayList;
import java.util.List;

public class CommandLineParser extends Errors{

    private boolean flagO;
    private boolean flagP;
    private boolean flagA;
    private boolean flagS;
    private boolean flagF;

    private String stringPath;
    private String stringPrefix;

    private final List<String> inputFiles;

    public CommandLineParser() {
        this.flagO = false;
        this.flagP = false;
        this.flagA = false;
        this.flagS = false;
        this.flagF = false;

        this.stringPath = "";
        this.stringPrefix = "";

        this.inputFiles = new ArrayList<>();

    }

    public boolean isFlagA() {
        return flagA;
    }

    public boolean isFlagS() {
        return flagS;
    }

    public boolean isFlagF() {
        return flagF;
    }

    public boolean isFlagO() {
        return flagO;
    }

    public boolean isFlagP() {
        return flagP;
    }

    public String getStringPath() {
        return stringPath;
    }

    public String getStringPrefix() {
        return stringPrefix;
    }

    public List<String> getInputFiles() {
        return inputFiles;
    }

    public void parseCommandLine(String[] args) throws NoArgumentsException,
            RepeatFlagException,
            EmptyParameterException,
            InvalidNameException,
            RepeatStatisticsException,
            WrongFlagException {
        if (args.length == 0) {
            throw new NoArgumentsException();
        }

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-o":
                    if (flagO || args[i + 1].equals("-o")) {
                        throw new RepeatFlagException(args[i]);
                    } else {
                        if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                            flagO = true;
                            stringPath = args[++i];
                        } else {
                            throw new EmptyParameterException(args[i], " необходимо указать путь к конечным файлам.");
                        }
                    }
                    break;
                case "-p":
                    if (flagP || args[i + 1].equals("-p")) {
                        throw new RepeatFlagException(args[i]);
                    } else {
                        if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                            if (args[i + 1].contains(".")) {
                                throw new InvalidNameException("Префикс не может содержать точки.");
                            } else {
                                flagP = true;
                                stringPrefix = args[++i];
                            }
                        } else {
                            throw new EmptyParameterException(args[i], " необходимо указать префикс конечных файлов.");
                        }
                    }
                    break;
                case "-a":
                    if (flagA) {
                        throw new RepeatFlagException(args[i]);
                    } else {
                        flagA = true;
                    }
                    break;
                case "-s":
                    if (flagS) {
                        throw new RepeatFlagException(args[i]);
                    } else {
                        if (flagF) {
                            throw new RepeatStatisticsException();
                        } else {
                            flagS = true;
                        }
                    }
                    break;
                case "-f":
                    if (flagF) {
                        throw new RepeatFlagException(args[i]);
                    } else {
                        if (flagS) {
                            throw new RepeatStatisticsException();
                        } else {
                            flagF = true;
                        }
                    }
                    break;
                default:
                    if (args[i].charAt(0) == '-') {
                        throw new WrongFlagException(args[i]);
                    } else {
                        inputFiles.add(args[i]);
                    }
                    break;
            }
        }
    }
}
