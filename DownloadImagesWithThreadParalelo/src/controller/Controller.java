package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import models.WebManager;
import views.DialogShowImage;
import views.MainWindow;

public class Controller implements ActionListener{
	
	private WebManager fileManager;
	private MainWindow mainWindow;
	private DialogShowImage dialogShowImage;
	
	public Controller() {
		fileManager = new WebManager();
		mainWindow = new MainWindow(this);
		dialogShowImage = new DialogShowImage(this);
		init();
	}

	private void init() {
//		mainWindow.chargeImages(fileManager.chargueImageBase());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Action.valueOf(e.getActionCommand())) {
		case SEARCH:
			searchWallpapers();
			break;
		case SHOW_IMAGE_COLOR_BW:
			showImageColorBW(((JButton)(e.getSource())).getName());
			break;
		case SHOW_IMAGE:
			showImage(((JButton)(e.getSource())).getName());
			break;
		default:
			break;
		}
	}

	private void searchWallpapers() {
		try {
			fileManager.read(mainWindow.getText());
			fileManager.chargeImageParallel();
			fileManager.startThread();
			mainWindow.executeTaskUI(fileManager.getImagesList());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	private void processDownload() {
//		SwingWorker<Void, Void> thread = new SwingWorker<Void, Void>() {
//			@Override
//			protected Void doInBackground() throws Exception {
//				int p = 0;
//				mainWindow.getProgressBar().setMaximum((fileManager.getImagesList().size() * 10) - 10);
//					mainWindow.getProgressBar().setValue(p);
//					p += 10;
//					Thread.sleep(4000);
//					mainWindow.chargeImages(fileManager.getImagesList());
//				return null;
//			}
//		};
//		thread.execute();
//	}
	
	private void showImage(String path) {
		dialogShowImage.chargeImage(path);
		dialogShowImage.setVisible(true);
	}
	
	private void showImageColorBW(String path) {
		dialogShowImage.chargeImage(path);
		dialogShowImage.setVisible(true);
	}
}