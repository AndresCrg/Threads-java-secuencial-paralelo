package views;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.Action;
import controller.Controller;

public class PanelHeader extends JPanel{

	private static final long serialVersionUID = 1L;
	private JButton btnSearch;
	private JTextArea txSearch;
	
	public PanelHeader(MainWindow mainWindow, Controller controller) {

		JPanel panelSearch = new JPanel();
		txSearch = new JTextArea(1,25);
		btnSearch = new JButton(createImageIcon("/img/lupe.png"));
		btnSearch.setActionCommand(Action.SEARCH.toString());
		btnSearch.addActionListener(controller);
		btnSearch.setBorder(null);
		btnSearch.setBorderPainted(true);
		btnSearch.setBackground(Color.WHITE);
		panelSearch.add(txSearch);
		panelSearch.add(btnSearch);
		panelSearch.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelSearch.setBackground(Color.WHITE);
		add(panelSearch);
	}
	
	public String getTextSearch() {
		return txSearch.getText();
	}
	
    public void cleanTxt(){
		txSearch.setText("");
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
