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
import javax.swing.JFileChooser;

import javax.swing.JOptionPane;

public class ChartView extends JFrame implements ActionListener {
	private static final long serialVersionUID = -8300169991685860365L;

	ItemFactory item;
	StatisticsView statisticsItem;
	
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

	public ChartView(JScrollPane scrollPanel) {
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
		createItems();
	}

	void createItems() {
		// 학생정보
		int nGridY = 0; // row
		
		item.addLabel("학교명", 0, nGridY, 1, 1, 0, 0);
		item.addText(school, 1, nGridY++, 9, 1, 0, 0);
		
		item.addLabel("학년", 0, nGridY, 1, 1, 0, 0);
		item.addText(grade, 1, nGridY++, 9, 1, 0, 0);
		
		item.addLabel("반", 0, nGridY, 1, 1, 0, 0);
		item.addText(classNum, 1, nGridY++, 9, 1, 0, 0);
		
		item.addLabel("번호", 0, nGridY, 1, 1, 0, 0);
		item.addText(studentNum, 1, nGridY++, 9, 1, 0, 0);
		
		item.addLabel("이름", 0, nGridY, 1, 1, 0, 0);
		item.addText(name, 1, nGridY++, 9, 1, 0, 0);
		
		item.addLabel("성별", 0, nGridY, 1, 1, 0, 0);
		item.addRadio(sexStr, sex, 1, nGridY++, 3, 1, 0, 0, -1);
		
		item.addLabel("생년월일", 0, nGridY, 1, 1, 0, 0);
		item.addText(birth, 1, nGridY++, 9, 1, 0, 0);
		

		// 구강검사결과 및 판정
		// 초중고 공통항목
		item.addLabel("우식치아", 0, nGridY, 1, 2, 0, 0);
		item.addRadio(existStr, dentalInfection_radio, 1, nGridY, 3, 2, 0, 0, 0);
		item.addText(updownStr, dentalInfection_text, 7, nGridY, 3, 1, 0, 0);
		nGridY+=2;
		
		item.addLabel("우식발생위험치아", 0, nGridY, 1, 2, 0, 0);
		item.addRadio(existStr, riskProducingTeeth_radio, 1, nGridY, 3, 2, 0, 0, 0);
		item.addText(updownStr, riskProducingTeeth_text, 7, nGridY, 3, 1, 0, 0);
		nGridY+=2;

		item.addLabel("결손치아(영구치에 한함)", 0, nGridY, 1, 2, 0, 0);
		item.addRadio(existStr, defectiveTeeth_radio, 1, nGridY, 3, 2, 0, 0, 0);
		item.addText(updownStr, defectiveTeeth_text, 7, nGridY, 3, 1, 0, 0);
		nGridY+=2;
		
		item.addLabel("구내염 및 연조직질환", 0, nGridY, 1, 1, 0, 0);
		item.addRadio(existStr, softTissueDisease_radio, 1, nGridY, 3, 1, 0, 0, 0);
		item.addText(softTissueDisease_text, 8, nGridY++, 2, 1, 0, 0);

		item.addLabel("부정교합", 0, nGridY, 1, 1, 0, 0);
		item.addRadio(crossbiteStr, crossbite_radio, 1, nGridY++, 3, 1, 0, 0, 0);
		
		item.addLabel("구강위생상태", 0, nGridY, 1, 1, 0, 0);
		item.addRadio(oralHygieneStr, oralHygiene_radio, 1, nGridY++, 3, 1, 0, 0, 0);
		
		item.addLabel("그밖의 치아상태", 0, nGridY, 1, 1, 0, 0);
		item.addRadio(dentalConditionStr, dentalCondition_radio, 1, nGridY++, 3, 1, 0, 0, -1);
		

		// 중고 추가항목
		item.addLabel("치주질환", 0, nGridY, 1, 4, 0, 0);
		item.addRadio(existStr, periodontalDisease_radio, 1, nGridY, 3, 4, 0, 0, 0);
		item.addCheck(periodontalDiseaseStr, periodontalDisease_check, 7, nGridY, 3, 1, 0, 0);
		nGridY+=4;
		
		item.addLabel("악관절 이상", 0, nGridY, 1, 1, 0, 0);
		item.addRadio(existStr, abnormalSymptoms_radio, 1, nGridY++, 3, 1, 0, 0, 0);
		
		// 고등 추가항목
		item.addLabel("치아마모증", 0, nGridY, 1, 1, 0, 0);
		item.addRadio(existStr, dentalWeariness_radio, 1, nGridY++, 3, 1, 0, 0, 0);
		
		item.addLabel("제3대구치(사랑니)", 0, nGridY, 1, 1, 0, 0);
		item.addRadio(existStr, wisdomeeth_radio, 1, nGridY, 3, 1, 0, 0, 0);
		item.addText(wisdomeeth_text, 8, nGridY++, 3, 1, 0, 0);
		

		// 종합소견
		item.addLabel("종합소견", 0, nGridY, 1, 2, 0, 0);
		item.addText(comprehensiveOpinion, 1, nGridY, 9, 2, 0, 0);
		nGridY += 2;
		
		item.addLabel("가정에서의 조치사항", 0, nGridY, 1, 2, 0, 0);
		item.addText(homeMeasures, 1, nGridY, 9, 2, 0, 0);
		nGridY += 2;


		saveBtn = new JButton("SAVE");
		item.addGrid(saveBtn, 5, nGridY, 1, 1, 0, 0);
		
		saveBtn.addActionListener(this);
	}

	void reset() {
		// 학생정보
		school.setText("");
		grade.setText("");
		classNum.setText("");
		studentNum.setText("");
		name.setText("");
		if(sex[0].isSelected())
			sex[0].setSelected(false);
		if(sex[1].isSelected())
			sex[1].setSelected(false);
		birth.setText("");

		// 구강검사결과 및 판정
		// 초중고 공통항목
		if(!dentalInfection_radio[0].isSelected())	//우식치아
		{
			dentalInfection_radio[0].setSelected(true);
			dentalInfection_radio[1].setSelected(false);
			dentalInfection_text[0].setText("");
			dentalInfection_text[1].setText("");
		}
		
		if(!riskProducingTeeth_radio[0].isSelected())	//우식발생위험치아
		{
			riskProducingTeeth_radio[0].setSelected(true);
			riskProducingTeeth_radio[1].setSelected(false);
			riskProducingTeeth_text[0].setText("");
			riskProducingTeeth_text[1].setText("");
		}
	
		if(!defectiveTeeth_radio[0].isSelected())	//결손치아(영구치에 한함)
		{
			defectiveTeeth_radio[0].setSelected(true);
			defectiveTeeth_radio[1].setSelected(false);
			defectiveTeeth_text[0].setText("");
			defectiveTeeth_text[1].setText("");
		}

		if(!softTissueDisease_radio[0].isSelected())	//구내염 및 연조직질환
		{
			softTissueDisease_radio[0].setSelected(true);
			softTissueDisease_radio[1].setSelected(false);
			softTissueDisease_text.setText("");
		}
		
		if(!crossbite_radio[0].isSelected())	//부정교합
		{
			crossbite_radio[0].setSelected(true);
			crossbite_radio[1].setSelected(false);
			crossbite_radio[2].setSelected(false);
		}
		
		if(!oralHygiene_radio[0].isSelected())	//구강위생상태
		{
			oralHygiene_radio[0].setSelected(true);
			oralHygiene_radio[1].setSelected(false);
			oralHygiene_radio[2].setSelected(false);
		}
		
		if(!dentalCondition_radio[0].isSelected())	//그밖의 치아상태
		{
			dentalCondition_radio[0].setSelected(true);
			dentalCondition_radio[1].setSelected(false);
			dentalCondition_radio[2].setSelected(false);
		}
		
		// 중고 추가항목
		if(!periodontalDisease_radio[0].isSelected())	//치주질환
		{
			periodontalDisease_radio[0].setSelected(true);
			periodontalDisease_radio[1].setSelected(false);
			if(periodontalDisease_check[0].isSelected())
				periodontalDisease_check[0].setSelected(false);
			if(periodontalDisease_check[1].isSelected())
				periodontalDisease_check[1].setSelected(false);
			if(periodontalDisease_check[2].isSelected())
				periodontalDisease_check[2].setSelected(false);
			if(periodontalDisease_check[3].isSelected())
				periodontalDisease_check[3].setSelected(false);
		}
		
		if(!abnormalSymptoms_radio[0].isSelected())	//악관절 이상
		{
			abnormalSymptoms_radio[0].setSelected(true);
			abnormalSymptoms_radio[1].setSelected(false);
		}

		if(!dentalWeariness_radio[0].isSelected())	//치아마모증
		{
			dentalWeariness_radio[0].setSelected(true);
			dentalWeariness_radio[1].setSelected(false);
		}
		
		if(!wisdomeeth_radio[0].isSelected())	//제3대구치(사랑니)
		{
			wisdomeeth_radio[0].setSelected(true);
			wisdomeeth_radio[1].setSelected(false);
			wisdomeeth_text.setText("");
		}
		
		comprehensiveOpinion.setText("");	//종합소견
		homeMeasures.setText("");			//가정에서의 조치사항
	}
	
	public void save() throws IOException {
		ChartVariables chart = new ChartVariables();
		StatisticsVariables statistics = new StatisticsVariables();
		
		//생성할 파일경로 지정
		String schoolPath = ChartView.class.getResource("").getPath() + school.getText();
		
        //파일 객체 생성
        File file = new File(schoolPath);
        //!표를 붙여주어 파일이 존재하지 않는 경우의 조건을 걸어줌
        if(!file.exists()){
            //디렉토리 생성 메서드
            file.mkdirs();
            System.out.println("created directory successfully!");
        }

		String chart_fileName = schoolPath + "/" + grade.getText() + "/" + classNum.getText();	// 'C://.../학교명/학년/반' 디렉토리
		String statistics_fileName = "statistics";

		File pathWithDir = new File(schoolPath, statistics_fileName);
		if(pathWithDir.exists())
		{
			try {
				statistics = statistics.open(schoolPath + "/" + statistics_fileName,statistics);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		saveOnChartVariables(chart,statistics);
		saveOnStatisticsVariables(statistics);
		
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		// 검진차트 저장
		try{
			String studentPath = chart_fileName + "/" + name.getText();
			fos = new FileOutputStream(studentPath);	// 'C://.../학교명/학년/반/학생이름' 파일
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(chart);
			
			JOptionPane.showMessageDialog(null, "차트 저장이 완료되었습니다.");
		}catch(Exception e){

			JOptionPane.showMessageDialog(null, "차트 저장이 실패 했습니다.");
			
		}finally{
			
			if(fos != null) try{fos.close();}catch(IOException e){}
			if(oos != null) try{oos.close();}catch(IOException e){}	
		}
		
		// 통계표 저장
		try{
			
			fos = new FileOutputStream(schoolPath+ "/" + statistics_fileName);
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(statistics);
			
			JOptionPane.showMessageDialog(null, "통계표 저장이 완료되었습니다.");
		}catch(Exception e){

			JOptionPane.showMessageDialog(null, "통계표 저장이 실패 했습니다.");
			
		}finally{
			
			if(fos != null) try{fos.close();}catch(IOException e){}
			if(oos != null) try{oos.close();}catch(IOException e){}	
		}
	}
	
	
	public void open() throws IOException, ClassNotFoundException {
		String path = ChartView.class.getResource("").getPath();
		JFileChooser chooser = new JFileChooser(path); //객체 생성
		
		int ret = chooser.showOpenDialog(null);  //열기창 정의


		if (ret != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "경로를 선택하지않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
		    return;
		}

		String filePath = chooser.getSelectedFile().getPath();  //파일경로를 가져옴
		
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		// 검진 차트 오픈
		try{
			fis = new FileInputStream(filePath);
			ois = new ObjectInputStream(fis);
	
			ChartVariables chart = (ChartVariables)ois.readObject();
			fillOnChartItems(chart);
				
			//JOptionPane.showMessageDialog(null, "차트 오픈이 완료되었습니다.");

		}catch(Exception e){
				
			JOptionPane.showMessageDialog(null, "차트 오픈이 실패했습니다.");
				
		}finally{
				
			if(fis != null) try{fis.close();}catch(IOException e){}
			if(ois != null) try{ois.close();}catch(IOException e){}
				
		}
	}
	
	public void saveOnChartVariables(ChartVariables chart, StatisticsVariables statistics) {
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

	public void saveOnStatisticsVariables(StatisticsVariables statistics) {
		statistics.setGrade(Integer.parseInt(grade.getText()));
		
		if(dentalInfection_radio[1].isSelected())
		{
			if(sex[0].isSelected())
				statistics.setDentalInfection_man(statistics.getDentalInfection_man()+1);
			else if(sex[1].isSelected())
				statistics.setDentalInfection_woman(statistics.getDentalInfection_woman()+1);
			statistics.setDentalInfection_total(statistics.getDentalInfection_total()+1);
		}
		if(periodontalDisease_radio[1].isSelected())
		{
			if(sex[0].isSelected())
				statistics.setPeriodontalDisease_man(statistics.getPeriodontalDisease_man()+1);
			else if(sex[1].isSelected())
				statistics.setPeriodontalDisease_woman(statistics.getPeriodontalDisease_woman()+1);
			statistics.setPeriodontalDisease_total(statistics.getPeriodontalDisease_total()+1);
		}
		if(crossbite_radio[1].isSelected())
		{
			if(sex[0].isSelected())
				statistics.setCrossbite_man(statistics.getCrossbite_man()+1);
			else if(sex[1].isSelected())
				statistics.setCrossbite_woman(statistics.getCrossbite_woman()+1);
			statistics.setCrossbite_total(statistics.getCrossbite_total()+1);
		}

		if(dentalInfection_radio[1].isSelected() || periodontalDisease_radio[1].isSelected() || crossbite_radio[1].isSelected())
		{
			if(sex[0].isSelected())
				statistics.setPersonnel_man(statistics.getPersonnel_man()+1);
			else if(sex[1].isSelected())
				statistics.setPersonnel_woman(statistics.getPersonnel_woman()+1);
			statistics.setPersonnel_total(statistics.getPersonnel_total()+1);
		}
	}
	
	public void fillOnChartItems(ChartVariables chart) {
		
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
		if (e.getSource() == saveBtn) {
			try {
				save();
				
			} catch (IOException e1) {
				e1.printStackTrace();
				
			}
		}
	}
}


