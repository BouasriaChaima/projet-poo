import java.awt.Color;
import java.awt.Font;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

public class GameButton extends JButton{
 GameButton(String g){
	 super(g);
	initializebutton();
 }

private void initializebutton() {
	 this.setFocusable(false);
		this.setHorizontalTextPosition(JButton.CENTER);
		this.setVerticalTextPosition(JButton.BOTTOM);
		this.setForeground(Color.BLACK);
		this.setBackground(new Color(119,178,84));
		this.setFont(new Font("Garamond", Font.BOLD, 25));
		Border colorb = BorderFactory.createLineBorder(new Color(39,57,28), 3);
	    this.setBorder(colorb);	
	
}
}
