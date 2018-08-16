import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class StatisticsView extends JFrame {
	ItemFactory item;
	
	JButton saveBtn;
	
	private JPanel panel;
	private GridBagLayout gbl;
	private GridBagConstraints gbc;
	
	int MAX_TEXTFIELD_LENGTH = 10;
	
	private JTextField period = new JTextField(MAX_TEXTFIELD_LENGTH);	// �����Ⱓ
	private JTextField grade = new JTextField(MAX_TEXTFIELD_LENGTH);	// ����г�
	private JTextField schoolName = new JTextField(MAX_TEXTFIELD_LENGTH);	// �б���
	private JTextField dentalInfection_man = new JTextField(MAX_TEXTFIELD_LENGTH);	// ġ�ƿ����(��)
	private JTextField dentalInfection_woman = new JTextField(MAX_TEXTFIELD_LENGTH);	// ġ�ƿ����(��)
	private JTextField dentalInfection_total = new JTextField(MAX_TEXTFIELD_LENGTH);	// ġ�ƿ����(�հ�)
	private JTextField periodontalDisease_man = new JTextField(MAX_TEXTFIELD_LENGTH);	// ġ����ȯ(��)
	private JTextField periodontalDisease_woman = new JTextField(MAX_TEXTFIELD_LENGTH);	// ġ����ȯ(��)
	private JTextField periodontalDisease_total = new JTextField(MAX_TEXTFIELD_LENGTH);	// ġ����ȯ(�հ�)
	private JTextField crossbite_man = new JTextField(MAX_TEXTFIELD_LENGTH);	// ��������(��)
	private JTextField crossbite_woman = new JTextField(MAX_TEXTFIELD_LENGTH);	// ��������(��)
	private JTextField crossbite_total = new JTextField(MAX_TEXTFIELD_LENGTH);	// ��������(�հ�)
	private JTextField personnel_man = new JTextField(MAX_TEXTFIELD_LENGTH);	// �˻��ο�(��)
	private JTextField personnel_woman = new JTextField(MAX_TEXTFIELD_LENGTH);	// �˻��ο�(��)
	private JTextField personnel_total = new JTextField(MAX_TEXTFIELD_LENGTH);	// �˻��ο�(�հ�)
	
	public StatisticsView(JPanel statisticsPanel) {
//		panel = new JPanel();
		statisticsPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		statisticsPanel.setBackground(Color.WHITE);
//		panel.add(statisticsPanel);
		
		gbl = new GridBagLayout();
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(1, 1, 1, 1); // top, left, bottom, right
	
		statisticsPanel.setLayout(gbl);
		item = new ItemFactory(statisticsPanel, gbc);
		createItems();
	}
	
	void createItems() {
		int nGridY = 0; // row
		
		item.addBorderLabel("�˻� �Ⱓ", 0, nGridY, 1, 1, 0, 0);
		item.addText(period, 1, nGridY++, 1, 1, 0, 0);
		gbc.insets = new Insets(1, 1, 30, 1); // top, left, bottom, right
		item.addBorderLabel("��� �г�", 0, nGridY, 1, 1, 0, 0);
		item.addText(grade, 1, nGridY++, 1, 1, 0, 0);
		
		nGridY+=2;
		gbc.insets = new Insets(1, 1, 1, 1); // top, left, bottom, right
		item.addBorderLabel("����", 0, nGridY, 2, 1, 0, 0);
		item.addBorderLabel("��", 2, nGridY, 1, 1, 0, 0);
		item.addBorderLabel("��", 3, nGridY, 1, 1, 0, 0);
		item.addBorderLabel("��", 4, nGridY++, 1, 1, 0, 0);
		
		item.addBorderLabel("����", 0, nGridY, 1, 3, 0, 0);
		
		item.addBorderLabel("ġ�ƿ����", 1, nGridY, 1, 1, 0, 0);
		item.addText(dentalInfection_man, 2, nGridY, 1, 1, 0, 0);
		item.addText(dentalInfection_woman, 3, nGridY, 1, 1, 0, 0);
		item.addText(dentalInfection_total, 4, nGridY++, 1, 1, 0, 0);
		
		item.addBorderLabel("ġ����ȯ", 1, nGridY, 1, 1, 0, 0);
		item.addText(periodontalDisease_man, 2, nGridY, 1, 1, 0, 0);
		item.addText(periodontalDisease_woman, 3, nGridY, 1, 1, 0, 0);
		item.addText(periodontalDisease_total, 4, nGridY++, 1, 1, 0, 0);
		
		item.addBorderLabel("��������", 1, nGridY, 1, 1, 0, 0);
		item.addText(crossbite_man, 2, nGridY, 1, 1, 0, 0);
		item.addText(crossbite_woman, 3, nGridY, 1, 1, 0, 0);
		item.addText(crossbite_total, 4, nGridY++, 1, 1, 0, 0);
		
		item.addBorderLabel("�˻� �ο�", 0, nGridY, 2, 1, 0, 0);
		item.addText(personnel_man, 2, nGridY, 1, 1, 0, 0);
		item.addText(personnel_woman, 3, nGridY, 1, 1, 0, 0);
		item.addText(personnel_total, 4, nGridY, 1, 1, 0, 0);
	}
	
	public void fillOnItems(StatisticsVariables statistics) {
		String totalPeriod = statistics.getStartPeriodToString() + " ~ " + statistics.getEndPeriodToString();
		period.setText(totalPeriod);
		grade.setText(statistics.getGradeToString());
		schoolName.setText(statistics.getSchoolName());
		
		dentalInfection_man.setText(statistics.getDentalInfectionManToString());
		dentalInfection_woman.setText(statistics.getDentalInfectionWomanToString());
		dentalInfection_total.setText(statistics.getDentalInfectionTotalToString());
		
		periodontalDisease_man.setText(statistics.getPeriodontalDiseaseManToString());
		periodontalDisease_woman.setText(statistics.getPeriodontalDiseaseWomanToString());
		periodontalDisease_total.setText(statistics.getPeriodontalDiseaseTotalToString());
		
		crossbite_man.setText(statistics.getCrossbiteManToString());
		crossbite_woman.setText(statistics.getCrossbiteWomanToString());
		crossbite_total.setText(statistics.getCrossbiteTotalToString());
		
		personnel_man.setText(statistics.getCrossbiteManToString());
		personnel_woman.setText(statistics.getCrossbiteWomanToString());
		personnel_total.setText(statistics.getCrossbiteTotalToString());
	}
	
	public void open() throws IOException, ClassNotFoundException {
		String path = ChartView.class.getResource("").getPath();
		JFileChooser chooser = new JFileChooser(path); //��ü ����
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		int ret = chooser.showOpenDialog(null);  //����â ����


		if (ret != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "��θ� ���������ʾҽ��ϴ�.", "���", JOptionPane.WARNING_MESSAGE);
		    return;
		}

		String filePath = chooser.getSelectedFile().getPath();  //���ϰ�θ� ������

		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try{
			fis = new FileInputStream(filePath + "/" + "statistics");
			ois = new ObjectInputStream(fis);
	
			StatisticsVariables statistics = (StatisticsVariables)ois.readObject();
			fillOnItems(statistics);
			
			//JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.");

		}catch(Exception e){
			
			JOptionPane.showMessageDialog(null, "������ �����߽��ϴ�.");
			
		}finally{
			
			if(fis != null) try{fis.close();}catch(IOException e){}
			if(ois != null) try{ois.close();}catch(IOException e){}
			
		}
	}
}
