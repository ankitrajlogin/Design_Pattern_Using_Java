package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T03_Structural_Design.L03_Proxy_Pattern.BadCode;


/*
🔹 What is the Proxy Pattern?
- The Proxy Pattern is a Structural Design Pattern that provides a substitute (proxy) or placeholder for another object to control access to it.
- In simple terms:
    - A Proxy acts as a middleman between the client and the real object.
    - It controls how and when the real object is accessed (for example, adding logging, caching, security, or lazy loading).


🔹 Problem Statement

Imagine you’re building an image viewer application.
    - Each image file is very large (say high-resolution images), and loading it takes a lot of time.
    - If we create an Image object every time directly, our application will be slow even if the image is never viewed.
 */


// Real Image class
class RealImage {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk(); // Heavy initialization
    }

    private void loadFromDisk() {
        System.out.println("Loading image from disk: " + filename);
    }

    public void display() {
        System.out.println("Displaying image: " + filename);
    }
}

// Client code
public class Client {
    public static void main(String[] args) {
        RealImage img1 = new RealImage("photo1.png"); // loads immediately
        RealImage img2 = new RealImage("photo2.png"); // loads immediately

        // Even if I don’t display img2, it is still loaded (wasted resources!)
        img1.display();
    }
}

/*
❌ Problems in Bad Code:
1. Unnecessary resource usage – Images are loaded at creation time, even if not displayed.
2. Slow performance – Opening the app loads all images upfront.
3. No access control – Anyone can directly access the real object.
 */