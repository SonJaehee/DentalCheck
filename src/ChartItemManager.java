import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class ChartItemManager extends JFrame implements ActionListener {
	
	JButton saveBtn;
	
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
	
	//public ChartItemManager() {	}
	
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
		
		createItems();
	}

	void createItems() {
		// 학생정보
		int nGridY = 0; // row
		//int nGridX = 5;	// col
		
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


		saveBtn = new JButton("SAVE");
		addGrid(saveBtn, 5, nGridY, 1, 1, 0, 0);
		
		saveBtn.addActionListener(this);
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
	

	public void save() throws IOException {
		EnterChart chart = new EnterChart();
		
		saveOnVariables(chart);
		
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try{
			
			fos = new FileOutputStream("object.dat");
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(chart);
			
			JOptionPane.showMessageDialog(null, "저장이 완료되었습니다.");
		}catch(Exception e){

			JOptionPane.showMessageDialog(null, "저장이 실패 했습니다.");
			
		}finally{
			
			if(fos != null) try{fos.close();}catch(IOException e){}
			if(oos != null) try{oos.close();}catch(IOException e){}	
		}
	}
	
	
	public void open() throws IOException, ClassNotFoundException {

		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try{
			fis = new FileInputStream("object.dat");
			ois = new ObjectInputStream(fis);

			EnterChart chart = (EnterChart)ois.readObject();
			fillOnItems(chart);
			
			JOptionPane.showMessageDialog(null, "오픈이 완료되었습니다.");

		}catch(Exception e){
			
			JOptionPane.showMessageDialog(null, "오픈이 실패했습니다.");
			
		}finally{
			
			if(fis != null) try{fis.close();}catch(IOException e){}
			if(ois != null) try{ois.close();}catch(IOException e){}
			
		}

	}
	public void saveOnVariables(EnterChart chart) {
		chart.setSchool(school);
		chart.setGrade(grade);
		chart.setClassNum(classNum);
		chart.setStudentNum(studentNum);
		chart.setName(name);
		if(sex[0].isSelected()) chart.setSex(1);
		else if(sex[1].isSelected()) chart.setSex(2);
		chart.setBirth(birth);
		
		if(dentalInfection_radio[1].isSelected()) {	// 우식치아
			chart.getDentalInfection().setCategory(2);
			if(!dentalInfection_text[0].getText().equals(""))
				chart.getDentalInfection().setTop(dentalInfection_text[0]);
			if(!dentalInfection_text[1].getText().equals(""))
				chart.getDentalInfection().setBottom(dentalInfection_text[1]);
		}
		
		if(riskProducingTeeth_radio[1].isSelected()) {	// 우식발생위험치아
			chart.getRiskProducingTeeth().setCategory(2);
			if(!riskProducingTeeth_text[0].getText().equals(""))
				chart.getRiskProducingTeeth().setTop(riskProducingTeeth_text[0]);
			if(!riskProducingTeeth_text[1].getText().equals(""))
				chart.getRiskProducingTeeth().setBottom(riskProducingTeeth_text[1]);
		}
		
		if(defectiveTeeth_radio[1].isSelected()) {	// 결손치아
			chart.getDefectiveTeeth().setCategory(2);
			if(!defectiveTeeth_text[0].getText().equals(""))
				chart.getDefectiveTeeth().setTop(defectiveTeeth_text[0]);
			if(!defectiveTeeth_text[1].getText().equals(""))
				chart.getDefectiveTeeth().setBottom(defectiveTeeth_text[1]);
		}

		if(softTissueDisease_radio[1].isSelected()) {	// 구내염 및 연조직질환
			chart.getSoftTissueDisease().setCategory(2);
			if(!softTissueDisease_text.getText().equals(""))
				chart.getSoftTissueDisease().setReason(softTissueDisease_text.getText());
		}
		
		if(crossbite_radio[1].isSelected()) {	// 부정교합
			chart.getCrossbite().setCategory(2);
		} else if(crossbite_radio[2].isSelected()) {
			chart.getCrossbite().setCategory(3);
		}
		
		if(oralHygiene_radio[1].isSelected()) {	// 구강위생 상태
			chart.getOralHygiene().setCategory(2);
		} else if(oralHygiene_radio[2].isSelected()) {
			chart.getOralHygiene().setCategory(3);
		}
		
		if(dentalCondition_radio[0].isSelected()) {	// 그밖의 치아상태
			chart.getDentalCondition().setCategory(1);
		} else if(dentalCondition_radio[1].isSelected()) {
			chart.getDentalCondition().setCategory(2);
		} else if(dentalCondition_radio[2].isSelected()) {
			chart.getDentalCondition().setCategory(3);
		}
		
		if(periodontalDisease_radio[1].isSelected()) {	// 치주질환
			chart.getPeriodontalDisease().setCategory(2);
			
			int checkedBit = 0;
			
			if(periodontalDisease_check[0].isSelected())
				checkedBit = checkedBit | 1;
			if(periodontalDisease_check[1].isSelected())
				checkedBit = checkedBit | 2;
			if(periodontalDisease_check[2].isSelected())
				checkedBit = checkedBit | 4;
			if(periodontalDisease_check[3].isSelected())
				checkedBit = checkedBit | 8;
			
			chart.getPeriodontalDisease().setTop(checkedBit);
		}
		
		if(abnormalSymptoms_radio[1].isSelected()) {	// 악관절 이상
			chart.getAbnormalSymptoms().setCategory(2);
		} 
		
		if(dentalWeariness_radio[1].isSelected()) {		// 치아마모증
			chart.getDentalWeariness().setCategory(2);
		} 
		
		if(wisdomeeth_radio[1].isSelected()) {			// 사랑니
			chart.getWisdomeeth().setCategory(2);
			if(!wisdomeeth_text.getText().equals(""))
				chart.getWisdomeeth().setTop(wisdomeeth_text);
		}
	}

	public void fillOnItems(EnterChart chart) {
		
		school.setText(chart.getSchool());
		if(chart.getGrade() > 0) {
			grade.setText(chart.getGradeToString());
		}
		if(chart.getClassNum() > 0) {
			classNum.setText(chart.getClassNumToString());
		}
		if(chart.getStudentNum() > 0) {
			studentNum.setText(chart.getStudentNumToString());
		}
		name.setText(chart.getName());
		if(chart.getSex() == 1) {
			sex[0].setSelected(true);
		} else if (chart.getSex() == 2) {
			sex[1].setSelected(true);
		}
		if(chart.getBirth() > 0) {
			birth.setText(chart.getBirthToString());
		}
		
		if (chart.getDentalInfection().getCategory() == 2) {	// 우식치아
			dentalInfection_radio[1].setSelected(true);
			if(chart.getDentalInfection().getTop() > 0) {
				dentalInfection_text[0].setText(chart.getDentalInfection().getTopToString());
			} else if(chart.getDentalInfection().getBottom() > 0) {
				dentalInfection_text[1].setText(chart.getDentalInfection().getBottomToString());
			}	
		}
		
		if (chart.getRiskProducingTeeth().getCategory() == 2) {		// 우식발생위험치아
			riskProducingTeeth_radio[1].setSelected(true);
			if(chart.getRiskProducingTeeth().getTop() > 0) {
				riskProducingTeeth_text[0].setText(chart.getRiskProducingTeeth().getTopToString());
			} else if(chart.getRiskProducingTeeth().getBottom() > 0) {
				riskProducingTeeth_text[1].setText(chart.getRiskProducingTeeth().getBottomToString());
			}	
		}
		
		if (chart.getDefectiveTeeth().getCategory() == 2) {		// 결손치아
			defectiveTeeth_radio[1].setSelected(true);
			if(chart.getDefectiveTeeth().getTop() > 0) {
				defectiveTeeth_text[0].setText(chart.getDefectiveTeeth().getTopToString());
			} else if(chart.getDefectiveTeeth().getBottom() > 0) {
				defectiveTeeth_text[1].setText(chart.getDefectiveTeeth().getBottomToString());
			}	
		}
		
		if (chart.getSoftTissueDisease().getCategory() == 2) {		// 구내염 및 연조직질환
			softTissueDisease_radio[1].setSelected(true);
			softTissueDisease_text.setText(chart.getSoftTissueDisease().getReason());
		}

		if (chart.getCrossbite().getCategory() == 2) {		// 부정교합
			crossbite_radio[1].setSelected(true);
		} else if (chart.getCrossbite().getCategory() == 3) {
			crossbite_radio[2].setSelected(true);
		}
	
		if (chart.getOralHygiene().getCategory() == 2) {		// 구강위생 상태
			oralHygiene_radio[1].setSelected(true);
		} else if (chart.getOralHygiene().getCategory() == 3) {
			oralHygiene_radio[2].setSelected(true);
		}
		
		if (chart.getDentalCondition().getCategory() == 1) {		// 그밖의 치아상태
			dentalCondition_radio[0].setSelected(true);
		} else if (chart.getDentalCondition().getCategory() == 2) {
			dentalCondition_radio[1].setSelected(true);
		} else if (chart.getDentalCondition().getCategory() == 3) {
			dentalCondition_radio[2].setSelected(true);
		}

		if (chart.getPeriodontalDisease().getCategory() == 2) {		// 치주질환
			periodontalDisease_radio[1].setSelected(true);
			
			int checkedBit = chart.getPeriodontalDisease().getTop();
			
			if(checkedBit / 8 == 1) {
				periodontalDisease_check[3].setSelected(true);
				checkedBit = checkedBit % 8;
			}
			if(checkedBit / 4 == 1) {
				periodontalDisease_check[2].setSelected(true);
				checkedBit = checkedBit % 4 ;
			}
			if(checkedBit / 2 == 1) {
				periodontalDisease_check[1].setSelected(true);
				checkedBit = checkedBit % 2;
			}
			if(checkedBit == 1) {
				periodontalDisease_check[0].setSelected(true);
			}
		}
		
		if(chart.getAbnormalSymptoms().getCategory() == 2) {	// 악관절 이상
			abnormalSymptoms_radio[1].setSelected(true);
		}
		
		if(chart.getDentalWeariness().getCategory() == 2) {	// 치아마모증
			dentalWeariness_radio[1].setSelected(true);
		}
		
		if(chart.getWisdomeeth().getCategory() == 2) {	// 사랑니
			wisdomeeth_radio[1].setSelected(true);
			if(chart.getWisdomeeth().getTop() > 0)
				wisdomeeth_text.setText(chart.getWisdomeeth().getTopToString());
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == saveBtn) {
			try {
				save();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			}
		}
	}
}


