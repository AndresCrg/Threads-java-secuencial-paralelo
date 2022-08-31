package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import controller.Controller;

public class MainWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private PanelHeader panelHeader;
	private PanelTableImages panelTableImages;
	private JProgressBar progressBar;
	private Timer timer;
	
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
		add(progressBar, BorderLayout.PAGE_END);
		
	}

	public void executeTaskUI(HashSet<String> imageList) {
		progressBar.setStringPainted(true);
		timer = new Timer(3000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int p = 0;
				progressBar.setMaximum((imageList.size() * 10) - 10);
					progressBar.setValue(p);
					chargeImages(imageList);
					p += 10;
				repaint();
			}
		});
		timer.start();
	}
	
	private void chargeImages(HashSet<String> images){
		panelTableImages.chargeImages(images);
	}
	
	public String getText() {
		return panelHeader.getTextSearch();
	}
	
	public void cleanTxt(){
		panelHeader.cleanTxt();
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
}