import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class GameFrame extends JFrame {
	private GamePanel gpanel ;
	private JMenuItem exititem ;

 GameFrame(String g){
	 super(g);
	 gpanel = new GamePanel();
	  this.setSize(600,600);
	  this.setLocationRelativeTo(null);
	  this.setResizable(true);
	  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	gpanel.setLayout(new BorderLayout());
	this.add(gpanel);
	 initializeMenu();
    this.setVisible(true);
	
}
 private void initializeMenu() {
	 JMenuBar menu = new JMenuBar();
	 JMenu ExitMenu = new JMenu("Exit");
	 exititem = new JMenuItem("Exit game");
	 ExitMenu.add(exititem);
	 exititem.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == exititem) {
				System.exit(0);
			}
		}
	 });
	 menu.add(ExitMenu);
	 this.setJMenuBar(menu);
	 
 }

}
