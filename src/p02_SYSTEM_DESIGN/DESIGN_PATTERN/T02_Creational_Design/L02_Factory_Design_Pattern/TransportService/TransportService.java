package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T02_Creational_Design.L02_Factory_Design_Pattern.TransportService;


/*
PROBLEM STATEMENT :
    - We want to build a transport service system where goods can be delivered using different modes of transport such as Car, Bike, or Bus.
    - Each transport has a deliver() method but their delivery mechanism differs.
    - The client (TransportService) needs to use these transports depending on the request (e.g., "car", "bike", "bus").
 */

interface Transport{
    void deliver() ;
}

class Car implements Transport{
    public void deliver(){
        System.out.println("Delivering by Car");
    }
}

class Bike implements Transport {
    public void deliver() {
        System.out.println("Delivering by Bike");
    }
}

class Bus implements Transport {
    public void deliver() {
        System.out.println("Delivering by Bus");
    }
}

class TransportFactory{
    static Transport createTransport(String type){
        switch(type.toLowerCase()){
            case "car":
                return new Car() ;
            case "bike":
                return new Bike() ;
            case "bus":
                return new Bus() ;
            default:
                throw new IllegalArgumentException("Unsupported transport type: ") ;
        }
    }
}

//👉 Key Point: Object creation is centralized here.
//We make the method static since we don’t want to create factory objects.

public class TransportService {
    public static void main(String[] args){
        Transport vehicle = TransportFactory.createTransport("bus") ;
        vehicle.deliver(); // delivery by bus

        vehicle = TransportFactory.createTransport("car");
        vehicle.deliver();  // Delivering by Car

//        vehicle = TransportFactory.createTransport("airplane") ;
//        vehicle.deliver();
    }

}
