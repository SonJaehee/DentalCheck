import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class StudentListView extends JFrame {
	private static final long serialVersionUID = -8300169991685860365L;

	ItemFactory item;
	JTable studentTable;
	
	private JPanel panel;
	private GridBagLayout gbl;
	private GridBagConstraints gbc;
	
	int MAX_TEXTFIELD_LENGTH = 10;
	
	public StudentListView(JScrollPane scrollPanel) {
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		scrollPanel.setViewportView(panel);
	
		gbl = new GridBagLayout();
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(3, 1, 3, 1); // top, left, bottom, right
	
		panel.setLayout(gbl);
		item = new ItemFactory(panel, gbc);
		//createItems();
	}
	
	void createItems() {
		// 학생정보

		StudentListVariables studentList = new StudentListVariables();
		
		String path = StudentListVariables.class.getResource("").getPath();
		JFileChooser chooser = new JFileChooser(path); //객체 생성
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		int ret = chooser.showOpenDialog(null);  //열기창 정의


		if (ret != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "경로를 선택하지않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
		    return;
		}

		String filePath = chooser.getSelectedFile().getPath();  //파일경로를 가져옴
		studentList.setStudentList(filePath);
		
		createTable(studentList);
	}
	
	void createTable(StudentListVariables studentList)
	{
		DefaultTableModel model;
		
		String header[] = {"학년","반","이름"};

		model = new DefaultTableModel(null, header);
		studentTable = new JTable(model);
		JScrollPane scrollpane = new JScrollPane(studentTable);
		
		panel.add(scrollpane);
		setVisible(true);
		
		int size = studentList.getStudentListSize();
		for(int idx=0; idx < size; idx++)
		{
			model.addRow(studentList.getStudentInfo(idx));
		}
		
	}
	
}
