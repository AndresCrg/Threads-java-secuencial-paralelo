package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.util.EventObject;
import java.util.HashSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import controller.Action;
import controller.Controller;

public class PanelTableImages extends JPanel{

	private static final long serialVersionUID = 1L;
	private DefaultTableModel model;
	private JTable tableImages;
	private int id = 0;
	private TableCellEditor tableCellEditor;
	private TableCellRenderer tableCellRenderer;
	private Controller controller;
	
	public PanelTableImages(Controller controller) {
		this.controller = controller;
		setLayout(new BorderLayout());
		model = new DefaultTableModel();
		model.setColumnIdentifiers(new String []{"ID", "PATH", "STATUS", "VIEW", "B & W"});
		tableImages = new JTable(model);
		tableImages.setRowHeight(30);
		tableImages.getTableHeader().setBackground(Color.BLACK);
		tableImages.getTableHeader().setForeground(Color.WHITE);
		tableImages.setFont(new Font("Calibri", Font.BOLD, 15));
		tableCellRenderer = new TableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				return (JButton) value;
			}
		};

		tableImages.setDefaultEditor(Object.class, null);

		tableCellEditor = new TableCellEditor() {

			public boolean stopCellEditing() {
				return true;
			}

			public boolean shouldSelectCell(EventObject anEvent) {
				return false;
			}

			public void removeCellEditorListener(CellEditorListener l) {
			}

			public boolean isCellEditable(EventObject anEvent) {
				return true;
			}

			public Object getCellEditorValue() {
				return null;
			}

			public void cancelCellEditing() {
			}

			public void addCellEditorListener(CellEditorListener l) {
			}

			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
				return (JButton)value;
			}
		};
		
		add(new JScrollPane(tableImages), BorderLayout.CENTER);
	}
	
	public Object[] createRowImage(String path){
		return new Object[]{id,path, btnIconCheck(id), btnView(path), btnViewBW(path)};
	}
	
	public void chargeImages(HashSet<String> images){
		model.setRowCount(0);
		for (String imagePath : images) {
			model.addRow(createRowImage(imagePath));
			id++;
		}
		model.fireTableStructureChanged();
		dateTable();
		revalidate();
	}
	
	private void dateTable(){
		tableImages.getColumn("STATUS").setCellEditor(tableCellEditor);
		tableImages.getColumn("STATUS").setCellRenderer(tableCellRenderer);
		tableImages.getColumn("VIEW").setCellEditor(tableCellEditor);
		tableImages.getColumn("VIEW").setCellRenderer(tableCellRenderer);
		tableImages.getColumn("B & W").setCellEditor(tableCellEditor);
		tableImages.getColumn("B & W").setCellRenderer(tableCellRenderer);
	}
	
	private JButton btnView(String path){
		JButton btnView = new JButton(new ImageIcon(createImageIcon("/img/show.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
		btnView.setActionCommand(Action.SHOW_IMAGE.toString());
		btnView.setBackground(Color.WHITE);
		btnView.setForeground(Color.WHITE);
		btnView.addActionListener(controller);
		btnView.setName(path);
		return btnView;
	}
	private JButton btnIconCheck(int id){
		JButton btnView = new JButton(new ImageIcon(createImageIcon("/img/checkIn.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
		btnView.setBackground(Color.WHITE);
		btnView.setForeground(Color.WHITE);
		btnView.setName(""+id);
		return btnView;
	}
	
	private JButton btnViewBW(String path){
		JButton btnView = new JButton(new ImageIcon(createImageIcon("/img/show.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
		btnView.setActionCommand(Action.SHOW_IMAGE_COLOR_BW.toString());
		btnView.setBackground(Color.WHITE);
		btnView.setForeground(Color.WHITE);
		btnView.addActionListener(controller);
		btnView.setName(path);
		return btnView;
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
