package sokoban;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class temporary {
	
	java.net.URL blankUrl = getClass().getResource("blank.png");
	ImageIcon blankicon = new ImageIcon(blankUrl);
	JLabel blanklabel = new JLabel(blankicon);

	java.net.URL blankmarkedUrl = getClass().getResource("blankmarked.png");
	ImageIcon blankmarkedicon = new ImageIcon(blankmarkedUrl);
	JLabel blankmarkedlabel = new JLabel(blankmarkedicon);

	java.net.URL crateUrl = getClass().getResource("crate.png");
	ImageIcon crateicon = new ImageIcon(crateUrl);
	JLabel cratelabel = new JLabel(crateicon);
	
	java.net.URL cratemarkedUrl = getClass().getResource("cratemarked.png");
	ImageIcon cratemarkedicon = new ImageIcon(cratemarkedUrl);
	JLabel cratemarkedlabel = new JLabel(cratemarkedicon);
	
	
	java.net.URL playerUrl = getClass().getResource("player.png");
	ImageIcon playericon = new ImageIcon(playerUrl);
	JLabel playerlabel = new JLabel(playericon);
	

	java.net.URL wallUrl = getClass().getResource("wall.png");
	ImageIcon wallicon = new ImageIcon(wallUrl);
	JLabel walllabel = new JLabel(wallicon);
	
	
}
