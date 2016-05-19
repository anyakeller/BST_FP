import wheels.users.*;
import java.awt.geom.Point2D.*;

public class TextBox extends Rectangle{
	
	private ConversationBubble _text; 
    
    //constructor
    public TextBox(int x, int y){
        super(x,y);
        this.setSize(200,300);
        this.setColor(java.awt.Color.BLUE);
		Point poop = new Point(x,y);
		_text = new ConversationBubble("ASDF");   
		_text.setLocation(poop);
	}
}
