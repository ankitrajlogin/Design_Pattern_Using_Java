package p02_SYSTEM_DESIGN.L02_DESIGN_PATTERN.T03_Structural_Design.L06_FlyWeight_Pattern.GoodCode;

/*
/*
Steps to Implement Flyweight
    - Identify shared intrinsic data (common across many objects).
    - Create Flyweight class to store this intrinsic state.
    - Create Factory to manage and reuse Flyweight objects.
    - Store extrinsic state outside and pass it at runtime.
    - Client code uses factory to get shared flyweights.

Components
    - Flyweight (TreeType) → shared intrinsic data.
    - ConcreteFlyweight → specific implementation of Flyweight.
    - FlyweightFactory → manages Flyweight objects, ensures sharing.
    - Context (Tree) → holds extrinsic data.
    - Client → requests objects from factory.
 */


/*
We separate:
    - Intrinsic state (type, color, texture → shared, stored in Flyweight object)
    - Extrinsic state (x, y → provided at runtime)
 */


import java.util.HashMap;
import java.util.Map;

// Flyweight Class: stores shared (intrinsic) state
// The assumption here is: TreeType is immutable (its fields don’t change).
class TreeType{
    private String name ;
    private String color ;
    private String texture ;

    public TreeType(String name , String color , String texture){
        this.name = name ;
        this.color = color ;
        this.texture = texture ;
    }

    public void draw(int x , int y){
        System.out.println("Drawing " + name + " tree in color " + color +
                " with texture " + texture + " at (" + x + ", " + y + ")");

    }
}


// Flyweight Factory: reuses existing objects
class TreeFactory{
    private static Map<String , TreeType> treeTypes = new HashMap<>() ;

    public static TreeType getTreeType(String name , String color , String texture){
        String key = name + "-" + color + "-" + texture ;

        if(!treeTypes.containsKey(key)){
            treeTypes.put(key , new TreeType(name , color , texture)) ;
        }
        return treeTypes.get(key) ;
    }
}

// Context class : Stores extrinsic state
class Tree{
    private int x ;
    private int y ;
    private TreeType type ;

    public Tree(int x , int y , TreeType type){
        this.x = x ;
        this.y = y ;
        this.type = type ;
    }

    public void draw(){
        type.draw(x , y) ;  // Extrinsic add here
    }
}


public class ForestGame {
    public static void main(String[] args) {
        TreeType oakType = TreeFactory.getTreeType("Oak", "Green", "Rough");

        // Multiple trees share same flyweight object
        Tree tree1 = new Tree(10, 20, oakType);
        Tree tree2 = new Tree(15, 25, oakType);
        Tree tree3 = new Tree(50, 60, oakType);

        tree1.draw();
        tree2.draw();
        tree3.draw();
    }
}



/*

Benefits
    - 🚀 Massive memory savings (less object duplication).
    - ⚡ Better performance when many similar objects exist.
    - 🔗 Encourages separation of intrinsic & extrinsic state.

Other Examples
    - Text editor → Each character (A, B, C) is a flyweight, position is extrinsic.
    - Game with bullets/enemies → only one shared type per enemy, positions are extrinsic.
    - Map applications (Google Maps) → Trees, buildings, cars use shared flyweights.
 */
