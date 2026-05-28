package p02_SYSTEM_DESIGN.L02_DESIGN_PATTERN.T03_Structural_Design.L03_Proxy_Pattern.NetworkProxy;

import java.util.*;

// NetworkService interface
interface NetworkService {
    String fetchData(String input);
}

// Real Network Service (actual service)
class RealNetworkService implements NetworkService {

    private String data;

    @Override
    public String fetchData(String input) {
        System.out.println("Fetching data from remote server...");
        try {
            Thread.sleep(2000); // Simulating network delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        data = "Data fetched from remote server for input: " + input;
        return data;
    }
}

// Proxy class
class NetworkServiceProxy implements NetworkService {

    private RealNetworkService realNetworkService;
    private Map<String, String> cache;

    public NetworkServiceProxy() {
        // ✅ Initialize the cache to store fetched data.
        cache = new HashMap<>();
    }

    @Override
    public String fetchData(String input) {

        if (cache.containsKey(input)) {
            System.out.println("Fetching data from cache");
            // ✅ Return the cached data for the given input.
            return cache.get(input);
        }

        if (realNetworkService == null) {
            // ✅ Initialize the RealNetworkService if it has not been created yet.
            realNetworkService = new RealNetworkService();
        }

        // ✅ Fetch data from the real network service using the provided input.
        String fetchedData = realNetworkService.fetchData(input);

        // ✅ Cache the fetched data with the input as the key for future access.
        cache.put(input, fetchedData);

        // ✅ Return the fetched data to the client.
        return fetchedData;
    }
}

// Client Class
public class Exercise {

    // Do not modify the run method. It demonstrates the usage of the Proxy Design Pattern to manage access to a network service.
    public void run() {

        NetworkService networkService = new NetworkServiceProxy();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your input:");
        String userInput = sc.nextLine();

        // ✅ Fetch data using the networkService and print the result
        String firstFetch = networkService.fetchData(userInput);
        System.out.println("Client: " + firstFetch);

        // ✅ Fetch data again using the networkService (should retrieve from cache) and print the result
        String secondFetch = networkService.fetchData(userInput);
        System.out.println("Client: " + secondFetch);

        sc.close();
    }

    public static void main(String[] args) {
        new Exercise().run();
    }
}

/*
🔎 How this works:
    - First call → Proxy checks cache, doesn’t find input, calls RealNetworkService, stores result.

    - Second call → Same input found in cache, returns instantly (skipping expensive remote call).
 */
