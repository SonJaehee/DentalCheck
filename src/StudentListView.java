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
		// �л�����

		StudentListVariables studentList = new StudentListVariables();
		
		String path = StudentListVariables.class.getResource("").getPath();
		JFileChooser chooser = new JFileChooser(path); //��ü ����
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		int ret = chooser.showOpenDialog(null);  //����â ����


		if (ret != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "��θ� ���������ʾҽ��ϴ�.", "���", JOptionPane.WARNING_MESSAGE);
		    return;
		}

		String filePath = chooser.getSelectedFile().getPath();  //���ϰ�θ� ������
		studentList.setStudentList(filePath);
		
		createTable(studentList);
	}
	
	void createTable(StudentListVariables studentList)
	{
		DefaultTableModel model;
		
		String header[] = {"�г�","��","�̸�"};

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
