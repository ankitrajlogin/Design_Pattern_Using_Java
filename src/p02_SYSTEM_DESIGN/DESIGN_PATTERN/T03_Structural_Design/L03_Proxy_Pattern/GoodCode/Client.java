package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T03_Structural_Design.L03_Proxy_Pattern.GoodCode;


/*


🔹 Components of Proxy Pattern
    - Subject (Interface) → Declares common methods.
    - RealSubject (Real Object) → Implements actual logic.
    - Proxy (Surrogate) → Controls access to RealSubject.
    - Client → Uses Subject but doesn’t care whether it’s Proxy or Real.


🔹 Steps to Implement Proxy Pattern
    1. Identify the real object that is heavy, sensitive, or needs controlled access.
    2. Define a common interface for the real object and the proxy.
    3. Create a Proxy class that contains a reference to the real object.
    4. In the proxy, add control logic (lazy loading, access check, logging, caching).
    5. The client uses the proxy instead of the real object.

🔹 Related Terms
    - Lazy Loading → Loading resource only when needed.
    - Virtual Proxy → Handles resource-intensive objects.
    - Protection Proxy → Controls access (authorization).
    - Remote Proxy → Represents an object in a different address space (e.g., RMI).
    - Caching Proxy → Avoids duplicate expensive calls.
 */

// step 1 : Common Interface
interface Image{
    void display() ;
}

// step 2 : Real object
class RealImage implements Image{
    private String filename ;

    public RealImage(String filename){
        this.filename = filename ;
        loadFromDisk()  ;
    }

    private void loadFromDisk(){
        System.out.println("Loading image from disk: " + filename);
    }

    @Override
    public void display(){
        System.out.println("Displaying image: " + filename);
    }
}

// step 3 : Proxy Object
class ProxyImage implements Image{
    private String filename ;
    private RealImage realImage ;  // reference to real object (lazy initialized)

    public ProxyImage(String filename){
        this.filename = filename ;
    }

    @Override
    public void display(){
        if(realImage == null){
            realImage = new RealImage(filename) ;
        }
        realImage.display();
    }
}


public class Client {
    public static void main(String[] args){
        Image img1 = new ProxyImage("photo1.png");
        Image img2 = new ProxyImage("photo2.png");

        // Image not loaded until display() is called
        img1.display(); // Loads and displays
        img1.display(); // Uses cached RealImage (no reloading)

        img2.display(); // Loads and displays
    }
}

/*
🔹 Explanation of the Good Code
1. Interface (Image): Both RealImage and ProxyImage implement this, ensuring they can be used interchangeably.
2. Real Object (RealImage): The heavy object (actually loads from disk).
3. Proxy Object (ProxyImage): Controls access. Creates the real object only when required.
4. Client: Uses Image without caring whether it’s proxy or real.
 */


/*
🔹 Related Terms
    - Lazy Loading → Loading resource only when needed.
    - Virtual Proxy → Handles resource-intensive objects.
    - Protection Proxy → Controls access (authorization).
    - Remote Proxy → Represents an object in a different address space (e.g., RMI).
    - Caching Proxy → Avoids duplicate expensive calls.
 */


/*
🔹 Other Examples Where Proxy Can Be Used
    - Database Connections → Proxy delays or pools connection creation.
    - API Calls → Proxy caches responses instead of making repeated calls.
    - Virtual Proxy → In GUI frameworks (like Java Swing), images are often loaded lazily.
    - Security → Proxy checks if the user has access before allowing method execution.
    - Remote Services → In distributed systems, proxy represents an object in another server.
 */