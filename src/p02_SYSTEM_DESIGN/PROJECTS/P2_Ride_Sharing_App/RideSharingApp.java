package p02_SYSTEM_DESIGN.PROJECTS.P2_Ride_Sharing_App;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * RideSharingApp
 * Single-file demo implementation of a ride-sharing system that:
 * - Matches nearest driver (proximity)
 * - Supports multiple vehicle types
 * - Uses Strategy pattern for fare calculation
 * - Uses Observer pattern for ride status notifications
 * - Uses a Mediator-like DispatchCenter for coordinating ride requests
 *
 * Note: This is a demonstration / teaching style implementation focusing on design.
 */
public class RideSharingApp {

    /* ----------------------------- Domain / Value Objects ----------------------------- */

    static class Location {
        final double x;
        final double y;

        public Location(double x, double y) { this.x = x; this.y = y; }

        public double distanceTo(Location other) {
            double dx = this.x - other.x;
            double dy = this.y - other.y;
            return Math.sqrt(dx * dx + dy * dy);
        }

        @Override public String toString() { return String.format("(%.2f, %.2f)", x, y); }
    }

    enum VehicleType {
        CAR, BIKE, LUXURY_CAR
    }

    enum RideStatus {
        PENDING, ONGOING, COMPLETED, CANCELLED
    }

    /* ----------------------------- Observer Pattern ----------------------------- */

    interface Observer {
        void update(Ride ride, RideStatus status, String message);
    }

    /* ----------------------------- Users / Entities ----------------------------- */

    static abstract class User implements Observer {
        final int id;
        final String name;

        public User(int id, String name) { this.id = id; this.name = name; }

        @Override public String toString() { return name + "#" + id; }
    }

    static class Passenger extends User {
        public Passenger(int id, String name) { super(id, name); }

        @Override
        public void update(Ride ride, RideStatus status, String message) {
            System.out.printf("[Passenger Notification] %s: Ride %s status=%s — %s%n", this, ride, status, message);
        }
    }

    static class Driver extends User {
        private boolean available = true;
        final Vehicle vehicle;
        Location location;

        public Driver(int id, String name, Vehicle vehicle, Location location) {
            super(id, name);
            this.vehicle = vehicle;
            this.location = location;
        }

        public boolean isAvailable() { return available; }
        public void setAvailable(boolean available) { this.available = available; }

        public Location getLocation() { return location; }
        public void setLocation(Location loc) { this.location = loc; }

        @Override
        public void update(Ride ride, RideStatus status, String message) {
            System.out.printf("[Driver Notification] %s: Ride %s status=%s — %s%n", this, ride, status, message);
        }

        @Override public String toString() { return "Driver:" + name + "#" + id + "{" + vehicle + "}"; }
    }

    /* ----------------------------- Vehicle ----------------------------- */

    static class Vehicle {
        final VehicleType type;
        final String licensePlate;
        // base fare per km could be part of vehicle type or strategy; keep here for demonstration
        final double baseFarePerKm;

        public Vehicle(VehicleType type, String licensePlate, double baseFarePerKm) {
            this.type = type;
            this.licensePlate = licensePlate;
            this.baseFarePerKm = baseFarePerKm;
        }

        @Override public String toString() { return type + " [" + licensePlate + "]"; }
    }

    /* ----------------------------- Strategy Pattern for Fare Calculation ----------------------------- */

    interface FareStrategy {
        /**
         * @param distanceKm distance traveled
         * @param vehicle vehicle used
         * @return calculated fare
         */

        double calculateFare(double distanceKm, Vehicle vehicle);
        String name();
    }

    static class StandardFareStrategy implements FareStrategy {
        @Override public double calculateFare(double distanceKm, Vehicle vehicle) {
            return distanceKm * vehicle.baseFarePerKm;
        }
        @Override public String name() { return "Standard"; }
    }

    static class SharedFareStrategy implements FareStrategy {
        private final double discountFactor; // e.g., 0.7 means 30% discount per passenger share
        public SharedFareStrategy(double discountFactor) { this.discountFactor = discountFactor; }

        @Override public double calculateFare(double distanceKm, Vehicle vehicle) {
            // Shared rides cheaper per passenger
            return distanceKm * vehicle.baseFarePerKm * discountFactor;
        }
        @Override public String name() { return "Shared"; }
    }

    static class LuxuryFareStrategy implements FareStrategy {
        private final double luxuryMultiplier;
        public LuxuryFareStrategy(double luxuryMultiplier) { this.luxuryMultiplier = luxuryMultiplier; }

        @Override public double calculateFare(double distanceKm, Vehicle vehicle) {
            // Luxury should apply multiplier over base fare
            return distanceKm * vehicle.baseFarePerKm * luxuryMultiplier;
        }
        @Override public String name() { return "Luxury"; }
    }

    /* ----------------------------- Ride (Subject in Observer Pattern) ----------------------------- */

    static class Ride {
        private static final AtomicInteger ID_GEN = new AtomicInteger(1);

        final int rideId;
        final Passenger passenger;
        final Driver driver;
        final Location from;
        final Location to;
        final FareStrategy fareStrategy;
        final double distanceKm;
        private RideStatus status;
        private final List<Observer> observers = new ArrayList<>();
        private double finalFare = 0.0;

        public Ride(Passenger p, Driver d, Location from, Location to, FareStrategy strategy) {
            this.rideId = ID_GEN.getAndIncrement();
            this.passenger = p;
            this.driver = d;
            this.from = from;
            this.to = to;
            this.fareStrategy = strategy;
            this.distanceKm = from.distanceTo(to);
            this.status = RideStatus.PENDING;
            subscribe(p);
            subscribe(d);
        }

        public void subscribe(Observer o) { observers.add(o); }
        public void unsubscribe(Observer o) { observers.remove(o); }

        private void notifyAllObservers(String message) {
            for (Observer o : observers) {
                o.update(this, status, message);
            }
        }

        public void startRide() {
            this.status = RideStatus.ONGOING;
            notifyAllObservers("Driver arrived and ride has started.");
        }

        public void completeRide() {
            this.status = RideStatus.COMPLETED;
            // compute final fare
            finalFare = fareStrategy.calculateFare(distanceKm, driver.vehicle);
            notifyAllObservers(String.format("Ride completed. Fare = %.2f (strategy=%s, distance=%.2f km)",
                    finalFare, fareStrategy.name(), distanceKm));
        }

        public void cancelRide(String reason) {
            this.status = RideStatus.CANCELLED;
            notifyAllObservers("Ride cancelled: " + reason);
        }

        public RideStatus getStatus() { return status; }
        public double getFinalFare() { if (status == RideStatus.COMPLETED) return finalFare; else return -1; }
        @Override public String toString() { return "Ride#" + rideId; }
    }

    /* ----------------------------- Mediator / DispatchCenter -----------------------------
       The DispatchCenter acts as a mediator to coordinate driver registration and ride assignment.
       It also encapsulates ride-matching algorithm (nearest available driver).
    ------------------------------------------------------------------------------- */

    static class DispatchCenter {
        private final List<Driver> drivers = new ArrayList<>();

        public void registerDriver(Driver driver) {
            drivers.add(driver);
        }

        public void removeDriver(Driver driver) {
            drivers.remove(driver);
        }

        /**
         * Assign nearest available driver to the passenger's 'from' location and create a Ride.
         * Returns an Optional<Ride> - empty if no driver available
         */
        public Optional<Ride> requestRide(Passenger passenger, Location from, Location to, VehicleType requestedType, FareStrategy strategy) {
            Driver nearest = null;
            double bestDist = Double.MAX_VALUE;
            for (Driver d : drivers) {
                if (!d.isAvailable()) continue;
                if (d.vehicle.type != requestedType) continue;
                double dist = d.getLocation().distanceTo(from);
                if (dist < bestDist) {
                    bestDist = dist;
                    nearest = d;
                }
            }

            if (nearest == null) return Optional.empty();

            // Reserve the driver
            nearest.setAvailable(false);

            Ride ride = new Ride(passenger, nearest, from, to, strategy);
            // Notify both passenger & driver that ride is pending/assigned
            ride.notifyAllObservers(String.format("Driver %s assigned. Distance to passenger: %.2f km", nearest, bestDist));
            return Optional.of(ride);
        }

        // After completion, free driver and optionally update driver location to destination
        public void finishRide(Ride ride) {
            Driver d = ride.driver;
            d.setLocation(ride.to); // driver ends at destination
            d.setAvailable(true);
        }
    }

    /* ----------------------------- TESTS / Demo ----------------------------- */

    public static void main(String[] args) {
        System.out.println("=== RideSharingApp Demo ===");

        // Create dispatch center (mediator)
        DispatchCenter dispatch = new DispatchCenter();

        // Base fares per vehicle type
        Map<VehicleType, Double> baseFare = new EnumMap<>(VehicleType.class);
        baseFare.put(VehicleType.CAR, 10.0);        // ₹10 per km
        baseFare.put(VehicleType.BIKE, 5.0);        // ₹5 per km
        baseFare.put(VehicleType.LUXURY_CAR, 25.0); // ₹25 per km

        // Create drivers
        Driver d1 = new Driver(1, "Ravi", new Vehicle(VehicleType.CAR, "MH01AB1111", baseFare.get(VehicleType.CAR)), new Location(0, 0));
        Driver d2 = new Driver(2, "Sonia", new Vehicle(VehicleType.BIKE, "MH01BB2222", baseFare.get(VehicleType.BIKE)), new Location(5, 1));
        Driver d3 = new Driver(3, "Priya", new Vehicle(VehicleType.LUXURY_CAR, "MH01CC3333", baseFare.get(VehicleType.LUXURY_CAR)), new Location(0.5, 0.4));

        dispatch.registerDriver(d1);
        dispatch.registerDriver(d2);
        dispatch.registerDriver(d3);

        // Create passenger
        Passenger p = new Passenger(101, "Ankit");

        // Fare strategies
        FareStrategy standard = new StandardFareStrategy();
        FareStrategy shared = new SharedFareStrategy(0.7); // 30% discount
        FareStrategy luxury = new LuxuryFareStrategy(1.8);  // 80% premium

        // TEST 1: Request standard car ride — should pick nearest CAR (d1)
        System.out.println("\n--- TEST 1: Standard Car Ride ---");
        Location from = new Location(1, 1);
        Location to = new Location(10, 10);
        Optional<Ride> r1 = dispatch.requestRide(p, from, to, VehicleType.CAR, standard);
        if (r1.isPresent()) {
            Ride ride = r1.get();
            // Start and complete ride
            ride.startRide();
            ride.completeRide();
            dispatch.finishRide(ride);
            System.out.printf("Final fare reported: %.2f%n", ride.getFinalFare());
        } else {
            System.out.println("No driver available for TEST 1.");
        }

        // TEST 2: Shared bike ride — should pick nearest BIKE (d2)
        System.out.println("\n--- TEST 2: Shared Bike Ride ---");
        Location from2 = new Location(4.7, 0.9);
        Location to2 = new Location(8, 2);
        Optional<Ride> r2 = dispatch.requestRide(p, from2, to2, VehicleType.BIKE, shared);
        if (r2.isPresent()) {
            Ride ride = r2.get();
            ride.startRide();
            ride.completeRide();
            dispatch.finishRide(ride);
            System.out.printf("Final fare reported: %.2f%n", ride.getFinalFare());
        } else {
            System.out.println("No driver available for TEST 2.");
        }

        // TEST 3: Luxury ride — should pick nearest luxury (d3)
        System.out.println("\n--- TEST 3: Luxury Ride ---");
        Location from3 = new Location(0.4, 0.3);
        Location to3 = new Location(2, 2);
        Optional<Ride> r3 = dispatch.requestRide(p, from3, to3, VehicleType.LUXURY_CAR, luxury);
        if (r3.isPresent()) {
            Ride ride = r3.get();
            ride.startRide();
            ride.completeRide();
            dispatch.finishRide(ride);
            System.out.printf("Final fare reported: %.2f%n", ride.getFinalFare());
        } else {
            System.out.println("No driver available for TEST 3.");
        }

        // TEST 4: No driver available for requested type
        System.out.println("\n--- TEST 4: Request unavailable type (e.g., LUXURY when none available) ---");
        // Temporarily mark all luxury drivers unavailable
        d3.setAvailable(false);
        Optional<Ride> r4 = dispatch.requestRide(p, new Location(0,0), new Location(1,1), VehicleType.LUXURY_CAR, standard);
        System.out.println("Ride created? " + r4.isPresent());
        // make d3 available again for future tests
        d3.setAvailable(true);

        // Final prints
        System.out.println("\n=== Demo Completed ===");
    }
}


/*
Explanation — design, SOLID and patterns
High-level architecture
    - DispatchCenter acts as a mediator/dispatcher that knows registered drivers and matches the nearest available driver to a passenger request.
    - Ride is the subject in the Observer pattern and holds references to the Passenger and Driver (which act as observers).
    - FareStrategy interface and concrete classes (StandardFareStrategy, SharedFareStrategy, LuxuryFareStrategy) implement the Strategy pattern to compute fares.
    - Vehicle and VehicleType encapsulate vehicle-specific data like base fare per km and type.
    - Passenger and Driver are Observers that receive notifications when ride status changes.

Why these patterns?
    - Strategy: Fare calculation varies independently from rides themselves. Strategy allows adding new fare rules (e.g., peak-hour) without changing ride or dispatcher logic.
    - Observer: Both passengers and drivers must be notified when the ride changes status. Observer cleanly models many listeners to ride state changes.
    - Mediator (DispatchCenter): Matching logic and driver availability management are centralized so participants (drivers, passengers) don't directly manage coordination logic, improving separation of concerns and simplifying testing.

How SOLID principles are applied
    - Single Responsibility: Classes have focused responsibilities — DispatchCenter matches drivers, Ride handles lifecycle & notifications, FareStrategy computes fares.
    - Open/Closed: You can add new FareStrategy or new VehicleType without modifying existing matching logic. For example, add PeakHourFareStrategy.
    - Liskov Substitution: FareStrategy implementations can be swapped wherever FareStrategy is expected.
    - Interface Segregation: Observers and strategies expose minimal interfaces tailored to their roles (Observer only has update, FareStrategy only has calculateFare).
    - Dependency Inversion: High-level Ride logic depends on abstraction FareStrategy rather than concrete implementations.

Extensibility
    - Add more VehicleType values and base fares — drivers with that vehicle type will be considered.
    - Add new FareStrategy implementations (e.g., SurgePricingStrategy) and pass them to requestRide.
    - Add richer matching: filter drivers by rating, capacity, or custom rules inside DispatchCenter without changing Ride.
    - Test cases (what the main demonstrates)
    - The main method shows four test scenarios:
    - Standard Car Ride — finds nearest CAR driver, starts & completes ride, prints calculated fare.
    - Shared Bike Ride — finds nearest BIKE driver, uses SharedFareStrategy (discount).
    - Luxury Ride — finds nearest LUXURY_CAR driver using LuxuryFareStrategy.
    - No driver available — demonstrates behavior when no available driver of requested type exists.
    - Each test prints notifications (Passenger & Driver) during state changes and prints final fare. You can convert those printed checks to real assertions in JUnit if desired.
 */