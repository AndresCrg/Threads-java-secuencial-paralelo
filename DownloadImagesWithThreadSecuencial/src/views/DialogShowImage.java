package views;

import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import controller.Controller;

public class DialogShowImage extends JDialog{

	private static final long serialVersionUID = 1L;
	private JLabel lbImage;
	
	public DialogShowImage(Controller controller) {
		setLocationRelativeTo(null);
		setTitle("Preview Image");
		setSize(310,210);
		lbImage = new JLabel();
		add(lbImage, BorderLayout.CENTER);
	
	}
	
	public void chargeImage(String path){
		lbImage.setIcon(createImageIcon(path));
	}
	
	protected ImageIcon createImageIcon(String path) {
		ImageIcon icon = new ImageIcon(path);
		Image image = icon.getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT);
		return new ImageIcon(image);
	}
}
