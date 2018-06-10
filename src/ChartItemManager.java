import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class ChartItemManager extends JFrame {
	private JPanel panel;
	private GridBagLayout gbl;
	private GridBagConstraints gbc;
	
	int MAX_TEXTFIELD_LENGTH = 10;
	
	private String[] sexStr = {"남","여"};
	private String[] existStr = {"없음","있음"};
	private String[] updownStr = {"상","하"};
	private String[] crossbiteStr = {"없음","요교정","교정중"};
	private String[] oralHygieneStr = {"우수","보통","개선요망"};
	private String[] dentalConditionStr = {"과잉치","유치잔존","그밖의 치아상태"};
	private String[] periodontalDiseaseStr = {"치은출혈/비대","치석형성","치주낭형성","그밖의 증상"};
	
	private JTextField school = new JTextField(MAX_TEXTFIELD_LENGTH);
	private JTextField grade = new JTextField(MAX_TEXTFIELD_LENGTH);
	private JTextField classNum = new JTextField(MAX_TEXTFIELD_LENGTH);
	private JTextField studentNum = new JTextField(MAX_TEXTFIELD_LENGTH);
	private JTextField name = new JTextField(MAX_TEXTFIELD_LENGTH);
	private JRadioButton[] sex = new JRadioButton[2];
	private JTextField birth = new JTextField(MAX_TEXTFIELD_LENGTH);
	
	// 초중고 공통항목
	private JRadioButton dentalInfection_radio[] = new JRadioButton[2];		// 우식치아
	private JTextField dentalInfection_text[] = new JTextField[2];
	
	private JRadioButton riskProducingTeeth_radio[] = new JRadioButton[2];	// 우식발생위험치아
	private JTextField riskProducingTeeth_text[] = new JTextField[2];
	
	private JRadioButton defectiveTeeth_radio[] = new JRadioButton[2];		// 결손치아
	private JTextField defectiveTeeth_text[] = new JTextField[2];
	
	private JRadioButton softTissueDisease_radio[] = new JRadioButton[2];	// 구내염 및 연조직질환
	private JTextField softTissueDisease_text = new JTextField(MAX_TEXTFIELD_LENGTH);
	
	private JRadioButton crossbite_radio[] = new JRadioButton[3];			// 부정교합
	
	private JRadioButton oralHygiene_radio[] = new JRadioButton[3];			// 구강위생 상태
	
	private JRadioButton dentalCondition_radio[] = new JRadioButton[3];		// 그밖의 치아상태
	
	// 중고 추가항목
	private JRadioButton periodontalDisease_radio[] = new JRadioButton[2];	// 치주질환
	private JCheckBox periodontalDisease_check[] = new JCheckBox[4];
	
	private JRadioButton abnormalSymptoms_radio[] = new JRadioButton[2];	// 악관절 이상
			
	// 고 추가항목
	private JRadioButton dentalWeariness_radio[] = new JRadioButton[2];		// 치아마모증
	
	private JRadioButton wisdomeeth_radio[] = new JRadioButton[2];			// 사랑니
	private JTextField wisdomeeth_text = new JTextField(MAX_TEXTFIELD_LENGTH);
	
	// 종합소견
	private JTextField comprehensiveOpinion = new JTextField(MAX_TEXTFIELD_LENGTH);		// 종합소견
	private JTextField homeMeasures = new JTextField(MAX_TEXTFIELD_LENGTH);				// 가정에서의 조치사항
	
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
		// 학생정보
		int nGridY = 0; // row
		int nGridX = 5;	// col
		
		addLabel("학교명", 0, nGridY, 1, 1, 0, 0);
		addText(school, 1, nGridY++, 9, 1, 0, 0);
		
		addLabel("학년", 0, nGridY, 1, 1, 0, 0);
		addText(grade, 1, nGridY++, 9, 1, 0, 0);
		
		addLabel("반", 0, nGridY, 1, 1, 0, 0);
		addText(classNum, 1, nGridY++, 9, 1, 0, 0);
		
		addLabel("번호", 0, nGridY, 1, 1, 0, 0);
		addText(studentNum, 1, nGridY++, 9, 1, 0, 0);
		
		addLabel("이름", 0, nGridY, 1, 1, 0, 0);
		addText(name, 1, nGridY++, 9, 1, 0, 0);
		
		addLabel("성별", 0, nGridY, 1, 1, 0, 0);
		addRadio(sexStr, sex, 1, nGridY++, 3, 1, 0, 0, -1);
		
		addLabel("생년월일", 0, nGridY, 1, 1, 0, 0);
		addText(birth, 1, nGridY++, 9, 1, 0, 0);
		

		// 구강검사결과 및 판정
		// 초중고 공통항목
		addLabel("우식치아", 0, nGridY, 1, 2, 0, 0);
		addRadio(existStr, dentalInfection_radio, 1, nGridY, 3, 2, 0, 0, 0);
		addText(updownStr, dentalInfection_text, 7, nGridY, 3, 1, 0, 0);
		nGridY+=2;
		
		addLabel("우식발생위험치아", 0, nGridY, 1, 2, 0, 0);
		addRadio(existStr, riskProducingTeeth_radio, 1, nGridY, 3, 2, 0, 0, 0);
		addText(updownStr, riskProducingTeeth_text, 7, nGridY, 3, 1, 0, 0);
		nGridY+=2;

		addLabel("결손치아(영구치에 한함)", 0, nGridY, 1, 2, 0, 0);
		addRadio(existStr, defectiveTeeth_radio, 1, nGridY, 3, 2, 0, 0, 0);
		addText(updownStr, defectiveTeeth_text, 7, nGridY, 3, 1, 0, 0);
		nGridY+=2;
		
		addLabel("구내염 및 연조직질환", 0, nGridY, 1, 1, 0, 0);
		addRadio(existStr, softTissueDisease_radio, 1, nGridY, 3, 1, 0, 0, 0);
		addText(softTissueDisease_text, 8, nGridY++, 2, 1, 0, 0);

		addLabel("부정교합", 0, nGridY, 1, 1, 0, 0);
		addRadio(crossbiteStr, crossbite_radio, 1, nGridY++, 3, 1, 0, 0, 0);
		
		addLabel("구강위생상태", 0, nGridY, 1, 1, 0, 0);
		addRadio(oralHygieneStr, oralHygiene_radio, 1, nGridY++, 3, 1, 0, 0, 0);
		
		addLabel("그밖의 치아상태", 0, nGridY, 1, 1, 0, 0);
		addRadio(dentalConditionStr, dentalCondition_radio, 1, nGridY++, 3, 1, 0, 0, -1);
		

		// 중고 추가항목
		addLabel("치주질환", 0, nGridY, 1, 4, 0, 0);
		addRadio(existStr, periodontalDisease_radio, 1, nGridY, 3, 4, 0, 0, 0);
		addCheck(periodontalDiseaseStr, periodontalDisease_check, 7, nGridY, 3, 1, 0, 0);
		nGridY+=4;
		
		addLabel("악관절 이상", 0, nGridY, 1, 1, 0, 0);
		addRadio(existStr, abnormalSymptoms_radio, 1, nGridY++, 3, 1, 0, 0, 0);
		
		// 고등 추가항목
		addLabel("치아마모증", 0, nGridY, 1, 1, 0, 0);
		addRadio(existStr, dentalWeariness_radio, 1, nGridY++, 3, 1, 0, 0, 0);
		
		addLabel("제3대구치(사랑니)", 0, nGridY, 1, 1, 0, 0);
		addRadio(existStr, wisdomeeth_radio, 1, nGridY, 3, 1, 0, 0, 0);
		addText(wisdomeeth_text, 8, nGridY++, 3, 1, 0, 0);
		

		// 종합소견
		addLabel("종합소견", 0, nGridY, 1, 2, 0, 0);
		addText(comprehensiveOpinion, 1, nGridY, 9, 2, 0, 0);
		nGridY += 2;
		
		addLabel("가정에서의 조치사항", 0, nGridY, 1, 2, 0, 0);
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


