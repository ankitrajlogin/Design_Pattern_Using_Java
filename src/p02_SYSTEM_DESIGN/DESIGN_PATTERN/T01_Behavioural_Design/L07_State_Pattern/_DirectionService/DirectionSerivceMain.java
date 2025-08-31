package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T01_Behavioural_Design.L07_State_Pattern._DirectionService;


interface TransportationMode{
    int calculateETA() ;
    String getDirection() ;
}


class Car implements TransportationMode {
    public int calculateETA() {
        System.out.println("Calculating ETA for Car");
        return 10;
    }

    public String getDirection() {
        return "Driving directions";
    }
}

class Walking implements TransportationMode {
    public int calculateETA() {
        System.out.println("Calculating ETA for Walking");
        return 20;
    }

    public String getDirection() {
        return "Walking directions";
    }
}

class Cycling implements TransportationMode {
    public int calculateETA() {
        System.out.println("Calculating ETA for Cycling");
        return 15;
    }

    public String getDirection() {
        return "Cycling directions";
    }
}

class Train implements TransportationMode {
    public int calculateETA() {
        System.out.println("Calculating ETA for Train");
        return 5;
    }

    public String getDirection() {
        return "Train directions";
    }
}

class DirectionService{
    private TransportationMode mode ;

    public DirectionService(TransportationMode mode){
        this.mode = mode ;
    }

    public void setMode(TransportationMode mode){
        this.mode = mode ;
    }

    public int getETA(){
        return mode.calculateETA() ;
    }

    public String getDirection(){
        return mode.getDirection() ;
    }
}



public class DirectionSerivceMain {
    public static void main(String[] args){
        // Start with Car
        DirectionService service = new DirectionService(new Car());
        System.out.println("ETA: " + service.getETA());
        System.out.println("Directions: " + service.getDirection());

        // Switch to Cycling
        service.setMode(new Cycling());
        System.out.println("ETA: " + service.getETA());
        System.out.println("Directions: " + service.getDirection());

        // Switch to Walking
        service.setMode(new Walking());
        System.out.println("ETA: " + service.getETA());
        System.out.println("Directions: " + service.getDirection());

    }
}
