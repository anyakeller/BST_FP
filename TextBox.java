import wheels.users.*;
import java.awt.Point;
import java.awt.Color;
import java.util.*;

public class TextBox extends Rectangle{
	
	private ConversationBubble _text; 
	private final int[] CHAR_WIDTHS = {0,-1,-2,-3,-4,-5,-6,-7,-8,-9,-10,-11,-12,-13,-14,-15,-16,-17,-18,-19,-20,-21,-22,-23,-24,-25,-26,-27,-28,-29,-30,-31,-32,-33,-34,-35,-36,-37,-38,-39,-40,-41,-42,-43,-44,-45,-46,-47,-48,-49,-50,-51,-52,6,-54,-55,-56,-57,-58,-59,-60,-61,-62,-63,-64,8,6,8,8,6,6,8,8,2,2,7,6,9,8,8,6,-81,-82,-83,-84,-85,-86,-87,-88,-89,-90,-91,-92,-93,-94,-95,-96,-97,-98,-99,-100,-101,-102,-103,-104,-105,-106,-107,2,-109,-110,-111,-112,-113,-114,-115,-116,-117,-118,-119,-120,-121,-122,-123,-124,-125,-126,-127};

 
    //constructor
    public TextBox(int x, int y, String text){
        super(x,y);
        this.setSize(6,30);
        this.setColor(java.awt.Color.RED);
		
		Point poop = new Point(x,y-25);
		_text = new ConversationBubble("P");   
		_text.setLocation(poop);
		_text.setBorderWidth(0);
		_text.setColor(new Color(0,0,0,0));
	}
	

}
