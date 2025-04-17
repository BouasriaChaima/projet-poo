
public class Test {

	public static void main(String[] args) {
		GameFrame f = new GameFrame("UNO");
	 GameButton b = new GameButton("Play");
	 GamePanel p = new GamePanel();
	 p.add(b);
	 f.add(p);
	// GameOptionPane.showMessageDialog(null, "UNO", "TITLE", GameOptionPane.PLAIN_MESSAGE);
	}

}
