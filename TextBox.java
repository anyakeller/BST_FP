import wheels.users.*;
import java.awt.Point;
import java.awt.Color;
import java.util.*;



/*
char c = '+';
int i = (int)c;
System.out.println("i: " + i + " ch: " + Character.getNumericValue(c));
*/

public class TextBox extends Rectangle{
	
	private ConversationBubble _text; 
	private final int[] CHAR_WIDTHS = {0,-1,-2,-3,-4,-5,-6,-7,-8,-9,-10,-11,-12,-13,-14,-15,-16,-17,-18,-19,-20,-21,-22,-23,-24,-25,-26,-27,-28,-29,-30,-31, 1,-33,-34,-35,-36,-37,-38,-39,-40,-41,-42,-43,-44,-45,-46,-47,-48,-49,-50,-51,-52,6,-54,-55,-56,-57,-58,-59,-60,-61,-62,-63, -64, 8, 6, 8, 9, 6, 6, 8, 8, 3, 3, 8, 6, 9, 9, 9, 6, 10, 7, 6, 8, 7, 7, 10, 7, 7, 7, -91, -92, -93, -94, -95, -96, 6, 7, 6, 7, 6, 5, 6, 6, 3, 3, 6, 3, 10, 7, 7, 7, 7, 5, 6, 5, 7, 5, 10, 7, 7, 7,-123,-124,-125,-126,-127};
	private final String[] LETTERS = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

	// retuns char number from string of one letter
	public static int charNum(String s){
		char c = s.charAt(0);
		return (int)c;
	}

    //constructor
    public TextBox(int x, int y, String text){
        super(x,y);

		int width = 0;
	
		for (int i = 0; i < text.length(); i++){
			width += CHAR_WIDTHS[text.charAt(i)] + 1;
		}

		/*
		String tester = "";
		for (int i = 97; i < 122; i++){
			//int i = 65;
			tester += ((char)i);
			width += 1 + CHAR_WIDTHS[i];
		}
		*/
		//System.out.println(tester);
		//System.out.println(width);
		

		this.setSize(width,30);
        this.setColor(java.awt.Color.RED);
		
		Point poop = new Point(x,y-25);

		_text = new ConversationBubble(text);   
		_text.setLocation(poop);
		_text.setBorderWidth(0);
		_text.setColor(new Color(0,0,0,0));
	}
	

	public static void main(String[] args){	
	}

}
