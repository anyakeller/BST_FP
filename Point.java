public class Point{
    //extends object: toString(), equals()
    
    //instance variables
    private int _x,_y;   // the underscore is a conventionality which represents a private instance variable
    
    //default constructor: create a point a 0,0
    //same name as class, no reteturn type, empy parameter list
    public Point(){
        _x = _y =  0;
    }
    
    //Overload constructor  --> this reference
    public Point(int x, int y){
        //this();  //default constructor <-- already called so not nessisary
        System.out.println(this);
        move(x,y);
        //same thing
        //this._x = x;
        //this._y = y;
    }
    
    
    public Point (Point other){
        this(other.getX(), other.getY());
        //move(other.getX(), other.getY());
    }
    
    
    //accessor methods <-- read only
    
    //Big O:  O(1)
    public int getX(){
        return _x;
    }
    
    public int getY(){
        return _y;
    }
    
    // modifier methods : changes the state of an object.
    public void move(int x, int y){
	_x = x;
	_y = y;
    }

    // postcondition: use StdDraw.java to draw the point. 
    //                Use a black circle to represent the point.
    public void draw(){
       StdDraw.filledCircle(getX(),getY(),.1);
    }

    // overwritten methods: changes the behavior of the inherited methods
    public String toString(){
	return "(" + _x + ", " + _y + ")"; 
        // return "(" + getX() + ", " + getY() + ")";
    }
    
    // postconditon: return true if the points
    // have equal x and y coordinates.
    public boolean equals(Object other){
        return ((other instanceof Point) && (getX() == ((Point)other).getX()) && (getY() == ((Point)other).getY()));
    }

    //postcondition: returns a copy of the point at the same location
    public Point getLocation(){
       return new Point(this);  //this constructs a point
    }
    
    // postcondtion: translates the point by dx and dy
    //              dx is the change in x
    //              dy is the change in y
    public void translate(int dx, int dy){
       move(getX() + dx, getY() +dy);//***** your code goes here *****     
    }

    // preconditon: other != null
    // postcondition: sets the location of the point to the specified 
    //                location
    public void setLocation(Point other){
        move(other.getX(),other.getY());
    }


   
    public static void main(String [] args){
        
        //StdDraw.setXscale(-10,10); 
        //StdDraw.setYscale(-10,10);
        
        Point a = new Point();
        Point b = new Point();
        //a.draw();
        System.out.println(a.equals(b));// true
        a.move(5,4);
        System.out.println(a.equals(b));  //false
        Point c = a.getLocation();  //c at 5,4
        System.out.println(c);  //(5, 4)
        //c.draw();
        a.translate(5,6);
        System.out.println(a);    //(10, 10)
        c.setLocation(a);
        System.out.println(a);  // (10, 10)
        //a.draw();
        
        System.out.println(a.equals(a.getLocation()));
        
        /*  You can't move an object
        e ----> Point 
                ______________
                | _x = 0      |
                | _y = 0      |
                | this = self |
                |_____________|
        */
        
        //Compiler: binds methods to the reference types
        //RuntimeL the methods are bound to objects.
        // compiler doesn't look at the right side of the binding
        ////Object e = new Point();  //POLYMORPHYSM  --> multi-functionality.
        ////System.out.println(e);  // e is an object, toString, compiler is ok.  at runtime, SOP(e.toString()) is called
        //e.move(1,1);            // compiler sees Object.move(int,int), Object has no move(), error.  To fix, you have to cast e to a point: (Point)e
        ////String t = "e: " + e;   //compiler sees String = String + c.toString, compiler ok.  at runtime t gets the values
        
        // lefthandside and righthandside 
        //lhs instanceof rhs -->  truw whenever lefthandside is a rhs, false otherwise
        ////System.out.println("asfas" instanceof String);
        
    }
    /*
      ,___,
     (0, 0)
    (|^^^^|)
      "  "
      
       ,___,
       (0.0)
      /),,,)
        " "
      
    */
    
}

