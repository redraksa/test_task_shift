public class Errors {
    public static class NoArgumentsException extends Exception {
        public NoArgumentsException() {
            super("Не переданы аргументы командной строки. Необходимо ввести данные");
        }
    }

    public static class RepeatFlagException extends Exception {
        public RepeatFlagException(String flag) {
            super("Ошибка: флаг " + flag + " повторяется.");
        }
    }

    public static class EmptyParameterException extends Exception {
        public EmptyParameterException(String flag, String parameter) {
            super("Ошибка: после флага " + flag + parameter);
        }
    }

    public static class RepeatStatisticsException extends Exception {
        public RepeatStatisticsException() {
            super("Ошибка: нельзя выбрать сразу два вида статистики.");
        }
    }

     public static class WrongFlagException extends Exception {
     public WrongFlagException(String flag) {
           super("Ошибка: неверный флаг " + flag);
         }
     }

     public static class InvalidNameException extends Exception {
         public InvalidNameException(String name) {
            super("Ошибка: неверное название. " + name);
         }
     }

    public static class FileReadException extends Exception {
        public FileReadException(String message) {
            super(message);
        }
    }

    public static class FileWriteException extends Exception {
        public FileWriteException(String message) {
            super(message);
        }
    }

}
