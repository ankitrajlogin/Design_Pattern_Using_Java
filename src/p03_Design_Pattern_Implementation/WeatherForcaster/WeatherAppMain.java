package p03_Design_Pattern_Implementation.WeatherForcaster;


import java.util.ArrayList;
import java.util.List;

// create observer interface
interface Observer{
    void update(int temperature)  ;
}

class MobileDisplay implements Observer {

    @Override
    public void update(int temperature) {

        System.out.println(
                "Mobile Display Updated: " + temperature
        );
    }
}

class TVDisplay implements Observer {

    @Override
    public void update(int temperature) {

        System.out.println(
                "TV Display Updated: " + temperature
        );
    }
}



// subject interface
interface Subject{
    void addObserver(Observer observer) ;
    void removeObserver(Observer observer) ;
    void notifyObservers() ;
}

// concrete subject
class WeatherStation implements Subject{
    private List<Observer> observers = new ArrayList<>() ;

    private int temperature ;

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {

        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }

    public void setTemperature(int temperature) {

        this.temperature = temperature;

        notifyObservers();
    }

}



public class WeatherAppMain {
    public static void main(String[] args) {

        WeatherStation weatherStation = new WeatherStation();

        Observer mobile = new MobileDisplay();
        Observer tv = new TVDisplay();

        weatherStation.addObserver(mobile);
        weatherStation.addObserver(tv);

        weatherStation.setTemperature(30);

        weatherStation.setTemperature(40);



    }
}
