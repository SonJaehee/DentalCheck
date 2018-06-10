import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class ChartItemManager extends JFrame {
	private JPanel panel;
	private GridBagLayout gbl;
	private GridBagConstraints gbc;
	
	int MAX_TEXTFIELD_LENGTH = 10;
	
	private String[] sexStr = {"��","��"};
	private String[] existStr = {"����","����"};
	private String[] updownStr = {"��","��"};
	private String[] crossbiteStr = {"����","�䱳��","������"};
	private String[] oralHygieneStr = {"���","����","�������"};
	private String[] dentalConditionStr = {"����ġ","��ġ����","�׹��� ġ�ƻ���"};
	private String[] periodontalDiseaseStr = {"ġ������/���","ġ������","ġ�ֳ�����","�׹��� ����"};
	
	private JTextField school = new JTextField(MAX_TEXTFIELD_LENGTH);
	private JTextField grade = new JTextField(MAX_TEXTFIELD_LENGTH);
	private JTextField classNum = new JTextField(MAX_TEXTFIELD_LENGTH);
	private JTextField studentNum = new JTextField(MAX_TEXTFIELD_LENGTH);
	private JTextField name = new JTextField(MAX_TEXTFIELD_LENGTH);
	private JRadioButton[] sex = new JRadioButton[2];
	private JTextField birth = new JTextField(MAX_TEXTFIELD_LENGTH);
	
	// ���߰� �����׸�
	private JRadioButton dentalInfection_radio[] = new JRadioButton[2];		// ���ġ��
	private JTextField dentalInfection_text[] = new JTextField[2];
	
	private JRadioButton riskProducingTeeth_radio[] = new JRadioButton[2];	// ��Ĺ߻�����ġ��
	private JTextField riskProducingTeeth_text[] = new JTextField[2];
	
	private JRadioButton defectiveTeeth_radio[] = new JRadioButton[2];		// ���ġ��
	private JTextField defectiveTeeth_text[] = new JTextField[2];
	
	private JRadioButton softTissueDisease_radio[] = new JRadioButton[2];	// ������ �� ��������ȯ
	private JTextField softTissueDisease_text = new JTextField(MAX_TEXTFIELD_LENGTH);
	
	private JRadioButton crossbite_radio[] = new JRadioButton[3];			// ��������
	
	private JRadioButton oralHygiene_radio[] = new JRadioButton[3];			// �������� ����
	
	private JRadioButton dentalCondition_radio[] = new JRadioButton[3];		// �׹��� ġ�ƻ���
	
	// �߰� �߰��׸�
	private JRadioButton periodontalDisease_radio[] = new JRadioButton[2];	// ġ����ȯ
	private JCheckBox periodontalDisease_check[] = new JCheckBox[4];
	
	private JRadioButton abnormalSymptoms_radio[] = new JRadioButton[2];	// �ǰ��� �̻�
			
	// �� �߰��׸�
	private JRadioButton dentalWeariness_radio[] = new JRadioButton[2];		// ġ�Ƹ�����
	
	private JRadioButton wisdomeeth_radio[] = new JRadioButton[2];			// �����
	private JTextField wisdomeeth_text = new JTextField(MAX_TEXTFIELD_LENGTH);
	
	// ���ռҰ�
	private JTextField comprehensiveOpinion = new JTextField(MAX_TEXTFIELD_LENGTH);		// ���ռҰ�
	private JTextField homeMeasures = new JTextField(MAX_TEXTFIELD_LENGTH);				// ���������� ��ġ����
	
	public ChartItemManager(JScrollPane scrollPanel) {
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		scrollPanel.setViewportView(panel);
	
		gbl = new GridBagLayout();
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(3, 1, 3, 1); // top, left, bottom, right
	
		panel.setLayout(gbl);
	}
	
	void createItems() {
		// �л�����
		int nGridY = 0; // row
		int nGridX = 5;	// col
		
		addLabel("�б���", 0, nGridY, 1, 1, 0, 0);
		addText(school, 1, nGridY++, 9, 1, 0, 0);
		
		addLabel("�г�", 0, nGridY, 1, 1, 0, 0);
		addText(grade, 1, nGridY++, 9, 1, 0, 0);
		
		addLabel("��", 0, nGridY, 1, 1, 0, 0);
		addText(classNum, 1, nGridY++, 9, 1, 0, 0);
		
		addLabel("��ȣ", 0, nGridY, 1, 1, 0, 0);
		addText(studentNum, 1, nGridY++, 9, 1, 0, 0);
		
		addLabel("�̸�", 0, nGridY, 1, 1, 0, 0);
		addText(name, 1, nGridY++, 9, 1, 0, 0);
		
		addLabel("����", 0, nGridY, 1, 1, 0, 0);
		addRadio(sexStr, sex, 1, nGridY++, 3, 1, 0, 0, -1);
		
		addLabel("�������", 0, nGridY, 1, 1, 0, 0);
		addText(birth, 1, nGridY++, 9, 1, 0, 0);
		

		// �����˻��� �� ����
		// ���߰� �����׸�
		addLabel("���ġ��", 0, nGridY, 1, 2, 0, 0);
		addRadio(existStr, dentalInfection_radio, 1, nGridY, 3, 2, 0, 0, 0);
		addText(updownStr, dentalInfection_text, 7, nGridY, 3, 1, 0, 0);
		nGridY+=2;
		
		addLabel("��Ĺ߻�����ġ��", 0, nGridY, 1, 2, 0, 0);
		addRadio(existStr, riskProducingTeeth_radio, 1, nGridY, 3, 2, 0, 0, 0);
		addText(updownStr, riskProducingTeeth_text, 7, nGridY, 3, 1, 0, 0);
		nGridY+=2;

		addLabel("���ġ��(����ġ�� ����)", 0, nGridY, 1, 2, 0, 0);
		addRadio(existStr, defectiveTeeth_radio, 1, nGridY, 3, 2, 0, 0, 0);
		addText(updownStr, defectiveTeeth_text, 7, nGridY, 3, 1, 0, 0);
		nGridY+=2;
		
		addLabel("������ �� ��������ȯ", 0, nGridY, 1, 1, 0, 0);
		addRadio(existStr, softTissueDisease_radio, 1, nGridY, 3, 1, 0, 0, 0);
		addText(softTissueDisease_text, 8, nGridY++, 2, 1, 0, 0);

		addLabel("��������", 0, nGridY, 1, 1, 0, 0);
		addRadio(crossbiteStr, crossbite_radio, 1, nGridY++, 3, 1, 0, 0, 0);
		
		addLabel("������������", 0, nGridY, 1, 1, 0, 0);
		addRadio(oralHygieneStr, oralHygiene_radio, 1, nGridY++, 3, 1, 0, 0, 0);
		
		addLabel("�׹��� ġ�ƻ���", 0, nGridY, 1, 1, 0, 0);
		addRadio(dentalConditionStr, dentalCondition_radio, 1, nGridY++, 3, 1, 0, 0, -1);
		

		// �߰� �߰��׸�
		addLabel("ġ����ȯ", 0, nGridY, 1, 4, 0, 0);
		addRadio(existStr, periodontalDisease_radio, 1, nGridY, 3, 4, 0, 0, 0);
		addCheck(periodontalDiseaseStr, periodontalDisease_check, 7, nGridY, 3, 1, 0, 0);
		nGridY+=4;
		
		addLabel("�ǰ��� �̻�", 0, nGridY, 1, 1, 0, 0);
		addRadio(existStr, abnormalSymptoms_radio, 1, nGridY++, 3, 1, 0, 0, 0);
		
		// ��� �߰��׸�
		addLabel("ġ�Ƹ�����", 0, nGridY, 1, 1, 0, 0);
		addRadio(existStr, dentalWeariness_radio, 1, nGridY++, 3, 1, 0, 0, 0);
		
		addLabel("��3�뱸ġ(�����)", 0, nGridY, 1, 1, 0, 0);
		addRadio(existStr, wisdomeeth_radio, 1, nGridY, 3, 1, 0, 0, 0);
		addText(wisdomeeth_text, 8, nGridY++, 3, 1, 0, 0);
		

		// ���ռҰ�
		addLabel("���ռҰ�", 0, nGridY, 1, 2, 0, 0);
		addText(comprehensiveOpinion, 1, nGridY, 9, 2, 0, 0);
		nGridY += 2;
		
		addLabel("���������� ��ġ����", 0, nGridY, 1, 2, 0, 0);
		addText(homeMeasures, 1, nGridY, 9, 2, 0, 0);
		nGridY += 2;


		JButton saveBtn = new JButton("SAVE");
		addGrid(saveBtn, 5, nGridY, 1, 1, 0, 0);
		
	}
	

	private void addGrid(Component c, int gridx, int gridy, int gridwidth,
			int gridheight, int weightx, int weighty) {
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = gridwidth;
		gbc.gridheight = gridheight;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		// gbl.setConstraints(c, gbc);
		panel.add(c, gbc);
	}
/*
	public void addLabel2Radio2Text(String lblStr, String radioStr1,
			String radioStr2, String textStr1, String textStr2, int gridx, int gridy, int gridwidth, int gridheight,
			int weightx, int weighty) {
		JLabel lbl = new JLabel(lblStr);
		addGrid(lbl, 0, gridy, 1, 2, weightx, weighty);

		JRadioButton radioButton1 = new JRadioButton(radioStr1);
		//radioButton1.addItemListener(new SelectItemListener());
		addGrid(radioButton1, 1, gridy, 1, 2, weightx, weighty);

		JRadioButton radioButton2 = new JRadioButton(radioStr2);
		addGrid(radioButton2, 2, gridy, 1, 2, weightx, weighty);
		
		ButtonGroup bG = new ButtonGroup();
		bG.add(radioButton1);
		bG.add(radioButton2);
		
		JLabel textLbl1 = new JLabel(textStr1);
		textLbl1.setHorizontalAlignment(0);
		addGrid(textLbl1, 3, gridy, 1, 1, weightx, weighty);
		JTextField textField1 = new JTextField();
		textField1.setColumns(10);
		addGrid(textField1, 4, gridy, 3, 1, weightx, weighty);

		JLabel textLbl2 = new JLabel(textStr2);
		textLbl2.setHorizontalAlignment(0);
		addGrid(textLbl2, 3, gridy + 1, 1, 1, weightx, weighty);
		JTextField textField2 = new JTextField();
		textField2.setColumns(10);
		addGrid(textField2, 4, gridy + 1, 3, 1, weightx, weighty);
	}

	public void addLabel2Radio1Text(String lblStr, String radioStr1,
			String radioStr2, boolean addText, int gridx, int gridy, int gridwidth, int gridheight, int weightx,
			int weighty) {
		JLabel lbl = new JLabel(lblStr);
		addGrid(lbl, 0, gridy, 1, 1, weightx, weighty);

		ButtonGroup bG = new ButtonGroup();

		JRadioButton radioButton1 = new JRadioButton(radioStr1);
		bG.add(radioButton1);
		addGrid(radioButton1, 1, gridy, 1, 1, weightx, weighty);

		JRadioButton radioButton2 = new JRadioButton(radioStr2);
		bG.add(radioButton2);
		addGrid(radioButton2, 2, gridy, 1, 1, weightx, weighty);

		if (addText) {
			JTextField textField1 = new JTextField();
			textField1.setColumns(10);
			addGrid(textField1, 3, gridy, 4, 1, weightx, weighty);
		}
	}

	public void addLabel3Radio1Text(String lblStr, String radioStr1,
			String radioStr2, String radioStr3, Boolean addText, int gridx, int gridy, int gridwidth, int gridheight,
			int weightx, int weighty) {
		JLabel lbl = new JLabel(lblStr);
		addGrid(lbl, 0, gridy, 1, 1, weightx, weighty);

		ButtonGroup bG = new ButtonGroup();

		JRadioButton radioButton1 = new JRadioButton(radioStr1);
		bG.add(radioButton1);
		addGrid(radioButton1, 1, gridy, 1, 1, weightx, weighty);

		JRadioButton radioButton2 = new JRadioButton(radioStr2);
		bG.add(radioButton2);
		addGrid(radioButton2, 2, gridy, 1, 1, weightx, weighty);

		JRadioButton radioButton3 = new JRadioButton(radioStr3);
		bG.add(radioButton3);
		if (addText) {
			addGrid(radioButton3, 3, gridy, 1, 1, weightx, weighty);
		} else {
			addGrid(radioButton3, 3, gridy, 3, 1, weightx, weighty);
		}

		if (addText) {
			JTextField textField1 = new JTextField();
			textField1.setColumns(10);
			addGrid(textField1, 4, gridy, 2, 1, weightx, weighty);
		}
	}

	public void addLabel2Radio4Radio(String lblStr, String radioStr1,
			String radioStr2, String textStr1, String textStr2, String textStr3, String textStr4, int gridx, int gridy,
			int gridwidth, int gridheight, int weightx, int weighty) {
		JLabel lbl = new JLabel(lblStr);
		addGrid(lbl, 0, gridy, 1, 4, weightx, weighty);

		ButtonGroup bG = new ButtonGroup();

		JRadioButton radioButton1 = new JRadioButton(radioStr1);
		bG.add(radioButton1);
		addGrid(radioButton1, 1, gridy, 1, 4, weightx, weighty);

		JRadioButton radioButton2 = new JRadioButton(radioStr2);
		bG.add(radioButton2);
		addGrid(radioButton2, 2, gridy, 1, 4, weightx, weighty);

		JRadioButton sub_radioButton1 = new JRadioButton(textStr1);
		addGrid(sub_radioButton1, 3, gridy, 3, 1, weightx, weighty);

		JRadioButton sub_radioButton2 = new JRadioButton(textStr2);
		addGrid(sub_radioButton2, 3, gridy + 1, 3, 1, weightx, weighty);

		JRadioButton sub_radioButton3 = new JRadioButton(textStr3);
		addGrid(sub_radioButton3, 3, gridy + 2, 3, 1, weightx, weighty);

		JRadioButton sub_radioButton4 = new JRadioButton(textStr4);
		addGrid(sub_radioButton4, 3, gridy + 3, 3, 1, weightx, weighty);

	}
*/
	
	public void addLabel(String str, int gridx, int gridy,
			int gridwidth, int gridheight, int weightx, int weighty) {
		JLabel lbl = new JLabel(str);
		addGrid(lbl, gridx, gridy, 1, gridheight, weightx, weighty);
	}

	public void addText(JTextField text, int gridx, int gridy,
			int gridwidth, int gridheight, int weightx, int weighty) {
		addGrid(text, gridx, gridy, gridwidth, gridheight, weightx, weighty);
	}

	public void addText(String[] str, JTextField[] text, int gridx, int gridy,
			int gridwidth, int gridheight, int weightx, int weighty) {
		for(int i=0;i<text.length;i++) {
			addLabel(str[i], gridx, gridy+i, 1, gridheight, weightx, weighty);
			text[i] = new JTextField(10);
			addGrid(text[i], gridx+1, gridy+i, gridwidth-1, gridheight, weightx, weighty);
		}
	}
	
	public void addRadio(String[] str, JRadioButton[] c, int gridx, int gridy,
			int gridwidth, int gridheight, int weightx, int weighty, int default_selected_index) {
		ButtonGroup bG = new ButtonGroup();
		for(int i=0;i<c.length;i++) {
			c[i] = new JRadioButton(str[i], default_selected_index==i);
			addGrid(c[i], gridx+i*gridwidth, gridy, gridwidth, gridheight, weightx, weighty);
			bG.add(c[i]);
		}
	}
	
	public void addCheck(String[] str, JCheckBox[] c, int gridx, int gridy,
			int gridwidth, int gridheight, int weightx, int weighty) {
		for(int i=0;i<c.length;i++) {
			c[i] = new JCheckBox(str[i]);
			addGrid(c[i], gridx, gridy+i, gridwidth, gridheight, weightx, weighty);
		}
	}
	
	public void SelectItemListener(ActionEvent e) {


	}
}


