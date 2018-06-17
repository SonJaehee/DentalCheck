import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.util.Base64;

public class EnterChart implements Serializable {
	ChartItemManager chartItems;
	
	FileOutputStream fos = null;
	ObjectOutputStream oos = null;


	// 의사정보
	public static final int licenseNumber = 9315;
	public static final String doctorName = "손한신";
	public static final String hospitalName = "한신치과";

	// 학생정보
	private String school;
	private int grade;
	private int classNum;
	private int studentNum;
	private String name;
	private int sex; // 1 : 남, 2 : 여
	private int birth;

	private DentalCategory dentalInfection;
	private DentalCategory riskProducingTeeth;
	private DentalCategory defectiveTeeth;
	private DentalCategory softTissueDisease;
	private DentalCategory crossbite;
	private DentalCategory oralHygiene;
	private DentalCategory dentalCondition;

	private DentalCategory periodontalDisease;
	private DentalCategory abnormalSymptoms;

	private DentalCategory dentalWeariness;
	private DentalCategory wisdomeeth;

	private int comprehensiveOpinion;
	private int homeMeasures;

	/*********************************************************/
	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public void setSchool(JTextField school) {
		this.school = school.getText();
	}

	public int getGrade() {
		return grade;
	}

	public String getGradeToString() {
		return Integer.toString(grade);
	}
	
	public void setGrade(int grade) {
		this.grade = grade;
	}

	public void setGrade(JTextField grade) {
		if (!grade.getText().equals("")) {
			this.grade = Integer.parseInt(grade.getText());
		}
	}

	public int getClassNum() {
		return classNum;
	}

	public String getClassNumToString() {
		return Integer.toString(classNum);
	}
	
	public void setClassNum(int classNum) {
		this.classNum = classNum;
	}

	public void setClassNum(JTextField classNum) {
		if (!classNum.getText().equals("")) {
			this.classNum = Integer.parseInt(classNum.getText());
		}
	}

	public int getStudentNum() {
		return studentNum;
	}

	public String getStudentNumToString() {
		return Integer.toString(studentNum);
	}
	
	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}

	public void setStudentNum(JTextField studentNum) {
		if (!studentNum.getText().equals("")) {
			this.studentNum = Integer.parseInt(studentNum.getText());
		}
	}

	public String getName() {
		return name;
	}

	public void setName(JTextField name) {
		this.name = name.getText();
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getBirth() {
		return birth;
	}

	public String getBirthToString() {
		return Integer.toString(birth);
	}
	
	public void setBirth(int birth) {
		this.birth = birth;
	}

	public void setBirth(JTextField birth) {
		if (!birth.getText().equals("")) {
			this.birth = Integer.parseInt(birth.getText());
		}
	}

	public DentalCategory getDentalInfection() {
		return dentalInfection;
	}

	public void setDentalInfection(DentalCategory dentalInfection) {
		this.dentalInfection = dentalInfection;
	}

	public DentalCategory getRiskProducingTeeth() {
		return riskProducingTeeth;
	}

	public void setRiskProducingTeeth(DentalCategory riskProducingTeeth) {
		this.riskProducingTeeth = riskProducingTeeth;
	}

	public DentalCategory getDefectiveTeeth() {
		return defectiveTeeth;
	}

	public void setDefectiveTeeth(DentalCategory defectiveTeeth) {
		this.defectiveTeeth = defectiveTeeth;
	}

	public DentalCategory getSoftTissueDisease() {
		return softTissueDisease;
	}

	public void setSoftTissueDisease(DentalCategory softTissueDisease) {
		this.softTissueDisease = softTissueDisease;
	}

	public DentalCategory getCrossbite() {
		return crossbite;
	}

	public void setCrossbite(DentalCategory crossbite) {
		this.crossbite = crossbite;
	}

	public DentalCategory getOralHygiene() {
		return oralHygiene;
	}

	public void setOralHygiene(DentalCategory oralHygiene) {
		this.oralHygiene = oralHygiene;
	}

	public DentalCategory getDentalCondition() {
		return dentalCondition;
	}

	public void setDentalCondition(DentalCategory dentalCondition) {
		this.dentalCondition = dentalCondition;
	}

	public DentalCategory getPeriodontalDisease() {
		return periodontalDisease;
	}

	public void setPeriodontalDisease(DentalCategory periodontalDisease) {
		this.periodontalDisease = periodontalDisease;
	}

	public DentalCategory getAbnormalSymptoms() {
		return abnormalSymptoms;
	}

	public void setAbnormalSymptoms(DentalCategory abnormalSymptoms) {
		this.abnormalSymptoms = abnormalSymptoms;
	}

	public DentalCategory getDentalWeariness() {
		return dentalWeariness;
	}

	public void setDentalWeariness(DentalCategory dentalWeariness) {
		this.dentalWeariness = dentalWeariness;
	}

	public DentalCategory getWisdomeeth() {
		return wisdomeeth;
	}

	public void setWisdomeeth(DentalCategory wisdomeeth) {
		this.wisdomeeth = wisdomeeth;
	}

	public int getComprehensiveOpinion() {
		return comprehensiveOpinion;
	}

	public void setComprehensiveOpinion(int comprehensiveOpinion) {
		this.comprehensiveOpinion = comprehensiveOpinion;
	}

	public int getHomeMeasures() {
		return homeMeasures;
	}

	public void setHomeMeasures(int homeMeasures) {
		this.homeMeasures = homeMeasures;
	}

	/**********************************************************/

	public EnterChart(JScrollPane scrollPanel) {
		initVariables();
		chartItems = new ChartItemManager(scrollPanel, this);
		chartItems.createItems();
	}

	public EnterChart() {

	}

	public void initVariables() {
		school = "";
		grade = 0;
		classNum = 0;
		studentNum = 0;
		name = "";
		sex = 0;
		birth = 0;

		// 초중고 공통항목
		dentalInfection = new DentalCategory(1, 0, 0); // 우식치아
		riskProducingTeeth = new DentalCategory(1, 0, 0); // 우식발생위험치아
		defectiveTeeth = new DentalCategory(1, 0, 0); // 결손치아
		softTissueDisease = new DentalCategory(1, 0, 0); // 구내염 및 연조직질환
		crossbite = new DentalCategory(1, 0, 0); // 부정교합
		oralHygiene = new DentalCategory(1, 0, 0); // 구강위생 상태
		dentalCondition = new DentalCategory(0, 0, 0); // 그밖의 치아상태

		// 중고 추가항목
		periodontalDisease = new DentalCategory(1, 0, 0); // 치주질환
		abnormalSymptoms = new DentalCategory(1, 0, 0); // 악관절 이상

		// 고 추가항목
		dentalWeariness = new DentalCategory(1, 0, 0); // 치아마모증
		wisdomeeth = new DentalCategory(1, 0, 0); // 사랑니

		// 종합소견
		comprehensiveOpinion = 0; // 종합소견
		homeMeasures = 0; // 가정에서의 조치사항
	}
	
	public void save() throws IOException {
		chartItems.saveOnVariables();

		FileOutputStream fileStream = new FileOutputStream("student.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fileStream);
		oos.writeObject(this);
		oos.close();
	}
	
	public void open() throws IOException, ClassNotFoundException{
		FileInputStream fileStream = new FileInputStream("student.txt");
		ObjectInputStream ois = new ObjectInputStream(fileStream);

		Object obj = ois.readObject();
		
		//chartItems = new ChartItemManager();
		EnterChart chart = new EnterChart();
		chart = (EnterChart)obj;
		//printAllVariables(chart);
		chartItems.fillOnItems(chart);
		
		ois.close();
	}
	/*
	public void printAllVariables(EnterChart chart) {

		System.out.println("학교명 : " + chart.school);
		System.out.println("학년 : " + chart.grade);
		System.out.println("반 : " + chart.classNum);
		System.out.println("번호 : " + chart.studentNum);
		System.out.println("이름 : " + chart.name);
		System.out.println("성별 : " + chart.sex);
		System.out.println("생일 : " + chart.birth);

		// 초중고 공통항목
		System.out.println("우식치아 : " + chart.dentalInfection.getCategory());
		System.out.println("우식위험 : " + chart.riskProducingTeeth.getCategory());
		System.out.println("결손치아 : " + chart.defectiveTeeth.getCategory());
		System.out.println("구내염 : " + chart.softTissueDisease.getCategory());
		System.out.println("부정교합 : " + chart.crossbite.getCategory());
		System.out.println("위생상태 : " + chart.oralHygiene.getCategory());
		System.out.println("치아상태 : " + chart.dentalCondition.getCategory());

		// 중고 추가항목
		System.out.println("치주질환 : " + chart.periodontalDisease.getCategory());
		System.out.println("악관절이상 : " + chart.abnormalSymptoms.getCategory());

		// 고 추가항목
		System.out.println("치아마모증 : " + chart.dentalWeariness.getCategory());
		System.out.println("사랑니 : " + chart.wisdomeeth.getCategory());
	}
	*/
}
