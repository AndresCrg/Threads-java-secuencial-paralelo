package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashSet;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import controller.Controller;

public class MainWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private PanelHeader panelHeader;
	private PanelTableImages panelTableImages;
	private JProgressBar progressBar;
	
	public MainWindow(Controller controller) {
		setTitle("Dowload Images");
		setIconImage(createImageIcon("/img/iconAppImage.png").getImage());
		setExtendedState(MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.BLACK);
		setLayout(new BorderLayout());
		initComponents(controller);
		
		setVisible(true);
	}
	
	private void initComponents(Controller controller) {
		panelHeader = new PanelHeader(this, controller);
		add(panelHeader, BorderLayout.PAGE_START);
		
		panelTableImages = new PanelTableImages(controller);
		add(panelTableImages, BorderLayout.CENTER);
		
		progressBar = new JProgressBar(0,100);
		progressBar.setStringPainted(true);
		add(progressBar, BorderLayout.PAGE_END);
	}
	
	public void chargeImages(HashSet<String> images){
		panelTableImages.chargeImages(images);
	}
	
	public String getText() {
		return panelHeader.getTextSearch();
	}
	
	public void cleanTxt(){
		panelHeader.cleanTxt();
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	protected ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
	public static void main(String[] args) {
		new MainWindow(null);
	}
}