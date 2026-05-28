# Design a Parking Lot System Using OOP Principles

This is one of the most important:

* Low Level Design (LLD)
* OOP Design
* SDE-2 Interview Questions

Interviewers use this question to evaluate:

* OOP fundamentals
* Class design
* SOLID principles
* Extensibility
* Scalability thinking
* Real-world backend architecture

---

# 1. Problem Statement

Design a parking lot system that supports:

* Multiple parking floors
* Different vehicle types
* Different parking spot types
* Ticket generation
* Vehicle entry and exit
* Parking fee calculation
* Payment processing

---

# 2. Functional Requirements

---

# Entry Flow

* Vehicle enters
* System allocates suitable parking spot
* Ticket generated

---

# Exit Flow

* Vehicle exits
* Parking duration calculated
* Fee calculated
* Payment completed
* Spot becomes free

---

# Vehicle Types

* Bike
* Car
* Truck

---

# Spot Types

* Bike Spot
* Car Spot
* Truck Spot

---

# 3. Non-Functional Requirements

Interviewers LOVE this discussion.

---

# Scalability

System should support:

* multiple floors
* large parking lots
* concurrent users

---

# Extensibility

Should easily support:

* EV charging
* reserved parking
* dynamic pricing

---

# Maintainability

Code should follow:

* clean architecture
* SOLID principles
* modular design

---

# 4. OOP Principles Used

| Principle     | Usage                       |
| ------------- | --------------------------- |
| Encapsulation | Hide internal state         |
| Abstraction   | Vehicle & Spot abstractions |
| Inheritance   | Car/Bike/Truck              |
| Polymorphism  | Pricing strategies          |
| Composition   | ParkingLot HAS Floors       |

---

# 5. Important Entities

| Entity       | Responsibility      |
| ------------ | ------------------- |
| Vehicle      | Vehicle information |
| ParkingSpot  | Parking spot        |
| ParkingFloor | Manage spots        |
| Ticket       | Entry details       |
| Payment      | Payment handling    |
| ParkingLot   | Main system         |

---

# 6. Project Structure

```text id="jz7z6k"
parkinglot/

│── Main.java

│
├── vehicle/
│     ├── Vehicle.java
│     ├── Car.java
│     ├── Bike.java
│     └── VehicleType.java
│
├── spot/
│     ├── ParkingSpot.java
│     ├── CarSpot.java
│     └── BikeSpot.java
│
├── parking/
│     ├── ParkingFloor.java
│     ├── ParkingLot.java
│     └── Ticket.java
│
└── payment/
      └── Payment.java
```

---

# 7. VehicleType Enum

## File: VehicleType.java

```java id="jlwmf2"
enum VehicleType {
    BIKE,
    CAR,
    TRUCK
}
```

---

# 8. Vehicle Class

## File: Vehicle.java

```java id="gkwc4y"
abstract class Vehicle {

    protected String vehicleNumber;
    protected VehicleType type;

    public Vehicle(String vehicleNumber,
                   VehicleType type) {

        this.vehicleNumber = vehicleNumber;
        this.type = type;
    }

    public VehicleType getType() {
        return type;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }
}
```

---

# Why Abstract Class?

Because:

* common fields shared
* common behavior reusable

---

# 9. Car Class

## File: Car.java

```java id="jlwm3w"
class Car extends Vehicle {

    public Car(String vehicleNumber) {

        super(vehicleNumber,
                VehicleType.CAR);
    }
}
```

---

# 10. Bike Class

## File: Bike.java

```java id="jlwmbo"
class Bike extends Vehicle {

    public Bike(String vehicleNumber) {

        super(vehicleNumber,
                VehicleType.BIKE);
    }
}
```

---

# 11. ParkingSpot Abstract Class

## File: ParkingSpot.java

```java id="jlwmgj"
abstract class ParkingSpot {

    protected int spotId;
    protected boolean occupied;
    protected Vehicle vehicle;

    public ParkingSpot(int spotId) {

        this.spotId = spotId;
    }

    abstract boolean canFitVehicle(
            Vehicle vehicle);

    public void parkVehicle(
            Vehicle vehicle) {

        this.vehicle = vehicle;
        occupied = true;
    }

    public void removeVehicle() {

        vehicle = null;
        occupied = false;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public int getSpotId() {
        return spotId;
    }
}
```

---

# Why Abstraction Here?

Different spot types:

* CarSpot
* BikeSpot
* TruckSpot

have different rules.

---

# 12. CarSpot Class

## File: CarSpot.java

```java id="jlwmq9"
class CarSpot extends ParkingSpot {

    public CarSpot(int spotId) {

        super(spotId);
    }

    @Override
    boolean canFitVehicle(
            Vehicle vehicle) {

        return vehicle.getType() ==
                VehicleType.CAR;
    }
}
```

---

# 13. BikeSpot Class

## File: BikeSpot.java

```java id="jlwmha"
class BikeSpot extends ParkingSpot {

    public BikeSpot(int spotId) {

        super(spotId);
    }

    @Override
    boolean canFitVehicle(
            Vehicle vehicle) {

        return vehicle.getType() ==
                VehicleType.BIKE;
    }
}
```

---

# 14. ParkingFloor Class

## File: ParkingFloor.java

```java id="jlwm3j"
import java.util.List;

class ParkingFloor {

    private List<ParkingSpot> spots;

    public ParkingFloor(
            List<ParkingSpot> spots) {

        this.spots = spots;
    }

    public ParkingSpot
    findAvailableSpot(
            Vehicle vehicle) {

        for(ParkingSpot spot : spots) {

            if(!spot.isOccupied() &&
                    spot.canFitVehicle(vehicle)) {

                return spot;
            }
        }

        return null;
    }
}
```

---

# Responsibilities

ParkingFloor:

* manages spots
* finds free spot

---

# 15. Ticket Class

## File: Ticket.java

```java id="jlwmiz"
class Ticket {

    private String ticketId;
    private Vehicle vehicle;
    private ParkingSpot spot;
    private long entryTime;

    public Ticket(
            String ticketId,
            Vehicle vehicle,
            ParkingSpot spot) {

        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.spot = spot;

        this.entryTime =
                System.currentTimeMillis();
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public void printTicket() {

        System.out.println(
                "\n------ TICKET ------"
        );

        System.out.println(
                "Ticket ID : " + ticketId
        );

        System.out.println(
                "Vehicle No : " +
                        vehicle.getVehicleNumber()
        );

        System.out.println(
                "Spot ID : " +
                        spot.getSpotId()
        );

        System.out.println(
                "---------------------"
        );
    }
}
```

---

# Why Ticket Important?

Ticket stores:

* entry time
* vehicle
* allocated spot

used during exit flow.

---

# 16. Payment Class

## File: Payment.java

```java id="jlwmg0"
class Payment {

    public void makePayment(
            double amount) {

        System.out.println(
                "Payment of Rs " +
                        amount +
                        " successful"
        );
    }
}
```

---

# Future Extension

Can support:

* UPI
* Card
* Wallet
* Net Banking

using:

* Strategy Pattern

---

# 17. ParkingLot Class

## File: ParkingLot.java

```java id="jlwm3z"
import java.util.List;
import java.util.UUID;

class ParkingLot {

    private List<ParkingFloor> floors;

    public ParkingLot(
            List<ParkingFloor> floors) {

        this.floors = floors;
    }

    public Ticket parkVehicle(
            Vehicle vehicle) {

        for(ParkingFloor floor : floors) {

            ParkingSpot spot =
                    floor.findAvailableSpot(
                            vehicle);

            if(spot != null) {

                spot.parkVehicle(vehicle);

                Ticket ticket =
                        new Ticket(
                                UUID.randomUUID()
                                        .toString(),
                                vehicle,
                                spot
                        );

                return ticket;
            }
        }

        return null;
    }

    public void exitVehicle(
            Ticket ticket) {

        ParkingSpot spot =
                ticket.getSpot();

        long exitTime =
                System.currentTimeMillis();

        long duration =
                (exitTime -
                 ticket.getEntryTime())
                        / 1000;

        double amount =
                calculateFee(duration);

        System.out.println(
                "\nVehicle Returning..."
        );

        System.out.println(
                "Vehicle Number : "
                + ticket.getVehicle()
                        .getVehicleNumber()
        );

        System.out.println(
                "Parked Spot : "
                + spot.getSpotId()
        );

        System.out.println(
                "Parking Fee : Rs "
                + amount
        );

        Payment payment =
                new Payment();

        payment.makePayment(amount);

        spot.removeVehicle();

        System.out.println(
                "Spot is now free"
        );
    }

    private double calculateFee(
            long seconds) {

        return seconds * 2;
    }
}
```

---

# Responsibilities

ParkingLot:

* allocate spot
* generate ticket
* calculate fee
* free parking spot

---

# 18. Main Class

## File: Main.java

```java id="jlwmij"
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args)
            throws InterruptedException {

        // Create Spots
        List<ParkingSpot> spots =
                new ArrayList<>();

        spots.add(new CarSpot(1));
        spots.add(new CarSpot(2));
        spots.add(new BikeSpot(3));

        // Create Floor
        ParkingFloor floor =
                new ParkingFloor(spots);

        // Create Floors
        List<ParkingFloor> floors =
                new ArrayList<>();

        floors.add(floor);

        // Create Parking Lot
        ParkingLot parkingLot =
                new ParkingLot(floors);

        // Create Vehicle
        Vehicle car =
                new Car("KA01AB1234");

        // Park Vehicle
        Ticket ticket =
                parkingLot.parkVehicle(car);

        if(ticket != null) {

            System.out.println(
                    "Vehicle Parked Successfully"
            );

            ticket.printTicket();

        } else {

            System.out.println(
                    "Parking Full"
            );
        }

        // Simulate parking duration
        Thread.sleep(5000);

        // Exit Vehicle
        parkingLot.exitVehicle(ticket);
    }
}
```

---

# 19. Sample Output

```text id="jlwmw3"
Vehicle Parked Successfully

------ TICKET ------
Ticket ID : 12345
Vehicle No : KA01AB1234
Spot ID : 1
---------------------

Vehicle Returning...
Vehicle Number : KA01AB1234
Parked Spot : 1
Parking Fee : Rs 10.0
Payment of Rs 10.0 successful
Spot is now free
```

---

# 20. Important Design Patterns Used

| Pattern           | Usage            |
| ----------------- | ---------------- |
| Strategy Pattern  | Payment/Pricing  |
| Factory Pattern   | Vehicle creation |
| Singleton Pattern | ParkingLot       |
| Observer Pattern  | Notifications    |

---

# 21. Important OOP Relationships

| Relationship | Example               |
| ------------ | --------------------- |
| IS-A         | Car IS-A Vehicle      |
| HAS-A        | Floor HAS Spots       |
| Composition  | ParkingLot HAS Floors |
| Polymorphism | Pricing strategies    |

---

# 22. Important Interview Discussion

This section is EXTREMELY IMPORTANT.

SDE-2 interviewers care more about:

* design thinking
* tradeoffs
  than code syntax.

---

# Q1. Why Use Composition Over Inheritance?

Good answer:

```text id="jlwmu5"
ParkingLot HAS floors
Floor HAS spots
```

Composition gives:

* loose coupling
* flexibility
* maintainability

---

# Q2. Current Complexity?

Spot search:

```text id="jlwm95"
O(total_spots)
```

---

# Optimization

Use:

```java id="jlwm3y"
Map<VehicleType,
    Queue<ParkingSpot>>
```

to achieve:

```text id="jlwmj2"
O(1)
```

spot allocation.

---

# Q3. Concurrency Problem?

Two cars may get same spot.

---

# Solution

Use:

* synchronized
* ReentrantLock
* Atomic operations

---

# Q4. How to Scale System?

Possible answers:

* Redis cache
* DB persistence
* Kafka events
* Microservices
* Distributed locking

---

# Q5. How to Extend System?

Can add:

* EV charging
* online booking
* reserved parking
* dynamic pricing
* QR-based entry

without major code changes.

---

# 23. SOLID Principles Used

| Principle | Example                      |
| --------- | ---------------------------- |
| SRP       | Payment handles payment only |
| OCP       | Add new spot type easily     |
| LSP       | Car/Bike behave as Vehicle   |
| ISP       | Separate abstractions        |
| DIP       | Depend on abstractions       |

---

# 24. Common Interview Mistakes

---

# Mistake 1

Starting coding immediately.

Always discuss:

* requirements
* entities
* tradeoffs

first.

---

# Mistake 2

Using inheritance everywhere.

---

# Mistake 3

Ignoring scalability.

---

# Mistake 4

No extensibility discussion.

---

# Mistake 5

No concurrency handling.

---

# 25. What Interviewers Actually Evaluate

| Area          | Expectation |
| ------------- | ----------- |
| OOP           | Strong      |
| Design        | Clean       |
| Scalability   | Discussed   |
| Extensibility | Good        |
| Tradeoffs     | Mature      |
| Communication | Clear       |

---

# 26. SDE-2 Perspective

At SDE-2 level:
coding alone is NOT enough.

Interviewers expect:

* clean architecture
* scalable thinking
* extensibility
* SOLID principles
* concurrency awareness
* real backend discussion

---

# Final Interview Tip

In LLD interviews:

> Communication is as important as coding.

Always explain:

* WHY this design?
* WHY this pattern?
* WHY this abstraction?
* HOW will it scale?
* WHAT future features can be added?
