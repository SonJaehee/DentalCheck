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


	// �ǻ�����
	public static final int licenseNumber = 9315;
	public static final String doctorName = "���ѽ�";
	public static final String hospitalName = "�ѽ�ġ��";

	// �л�����
	private String school;
	private int grade;
	private int classNum;
	private int studentNum;
	private String name;
	private int sex; // 1 : ��, 2 : ��
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

		// ���߰� �����׸�
		dentalInfection = new DentalCategory(1, 0, 0); // ���ġ��
		riskProducingTeeth = new DentalCategory(1, 0, 0); // ��Ĺ߻�����ġ��
		defectiveTeeth = new DentalCategory(1, 0, 0); // ���ġ��
		softTissueDisease = new DentalCategory(1, 0, 0); // ������ �� ��������ȯ
		crossbite = new DentalCategory(1, 0, 0); // ��������
		oralHygiene = new DentalCategory(1, 0, 0); // �������� ����
		dentalCondition = new DentalCategory(0, 0, 0); // �׹��� ġ�ƻ���

		// �߰� �߰��׸�
		periodontalDisease = new DentalCategory(1, 0, 0); // ġ����ȯ
		abnormalSymptoms = new DentalCategory(1, 0, 0); // �ǰ��� �̻�

		// �� �߰��׸�
		dentalWeariness = new DentalCategory(1, 0, 0); // ġ�Ƹ�����
		wisdomeeth = new DentalCategory(1, 0, 0); // �����

		// ���ռҰ�
		comprehensiveOpinion = 0; // ���ռҰ�
		homeMeasures = 0; // ���������� ��ġ����
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

		System.out.println("�б��� : " + chart.school);
		System.out.println("�г� : " + chart.grade);
		System.out.println("�� : " + chart.classNum);
		System.out.println("��ȣ : " + chart.studentNum);
		System.out.println("�̸� : " + chart.name);
		System.out.println("���� : " + chart.sex);
		System.out.println("���� : " + chart.birth);

		// ���߰� �����׸�
		System.out.println("���ġ�� : " + chart.dentalInfection.getCategory());
		System.out.println("������� : " + chart.riskProducingTeeth.getCategory());
		System.out.println("���ġ�� : " + chart.defectiveTeeth.getCategory());
		System.out.println("������ : " + chart.softTissueDisease.getCategory());
		System.out.println("�������� : " + chart.crossbite.getCategory());
		System.out.println("�������� : " + chart.oralHygiene.getCategory());
		System.out.println("ġ�ƻ��� : " + chart.dentalCondition.getCategory());

		// �߰� �߰��׸�
		System.out.println("ġ����ȯ : " + chart.periodontalDisease.getCategory());
		System.out.println("�ǰ����̻� : " + chart.abnormalSymptoms.getCategory());

		// �� �߰��׸�
		System.out.println("ġ�Ƹ����� : " + chart.dentalWeariness.getCategory());
		System.out.println("����� : " + chart.wisdomeeth.getCategory());
	}
	*/
}
