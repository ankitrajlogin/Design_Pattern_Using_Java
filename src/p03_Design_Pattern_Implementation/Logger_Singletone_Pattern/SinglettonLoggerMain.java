package p03_Design_Pattern_Implementation.Logger_Singletone_Pattern;

class Logger{
    private static Logger instance ;

    private Logger(){
        System.out.println("Logger Created") ;
    }

    public static Logger getInstance(){
        if(instance == null){
            instance = new Logger() ;
        }

        return instance ;
    }

    public void log(String message){
        System.out.println(message);
    }

    public void logFull(String message){
        System.out.println("full message" + message);
    }

}

// synchronized  logger ( thread safe)
class threadSafeLogger {

    private static volatile threadSafeLogger instance;

    private threadSafeLogger() {
        System.out.println("Crated a thread Safe logger");
    }

    public static threadSafeLogger getInstance() {

        if(instance == null) {

            synchronized (threadSafeLogger.class) {

                if(instance == null) {

                    instance = new threadSafeLogger();
                }
            }
        }

        return instance;
    }
}


public class SinglettonLoggerMain {
    public static void main(String[] args) {

        Logger l1 = Logger.getInstance();

        Logger l2 = Logger.getInstance();

        Logger l3 = Logger.getInstance();

        System.out.println(l1 == l2);

        System.out.println(l2 == l3);


        threadSafeLogger l4 = threadSafeLogger.getInstance() ;
        threadSafeLogger l5 = threadSafeLogger.getInstance() ;

        System.out.println(l4 == l5);
    }
}
