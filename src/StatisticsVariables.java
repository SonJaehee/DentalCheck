import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class StatisticsVariables implements Serializable {
	private static final long serialVersionUID = 1L;

	FileOutputStream fos = null;
	ObjectOutputStream oos = null;
	
	private int startPeriod;	// �����Ⱓ
	private int endPeriod;		// �����Ⱓ
	private int[] grade = {0,0,0,0,0,0,0};		// ����г�
	private String schoolName;	// �б���
	private int dentalInfection_man;	// ġ�ƿ����(��)
	private int dentalInfection_woman;	// ġ�ƿ����(��)
	private int dentalInfection_total;	// ġ�ƿ����(�հ�)
	private int periodontalDisease_man;	// ġ����ȯ(��)
	private int periodontalDisease_woman;	// ġ����ȯ(��)
	private int periodontalDisease_total;	// ġ����ȯ(�հ�)
	private int crossbite_man;		// ��������(��)
	private int crossbite_woman;	// ��������(��)
	private int crossbite_total;	// ��������(�հ�)
	private int personnel_man;		// �˻��ο�(��)
	private int personnel_woman;	// �˻��ο�(��)
	private int personnel_total;	// �˻��ο�(�հ�)
	/************************************/
	public int getStartPeriod() {
		return startPeriod;
	}
	public String getStartPeriodToString() {
		return Integer.toString(startPeriod);
	}
	public void setStartPeriod(int startPeriod) {
		this.startPeriod = startPeriod;
	}
	public int getEndPeriod() {
		return endPeriod;
	}
	public String getEndPeriodToString() {
		return Integer.toString(endPeriod);
	}
	public void setEndPeriod(int endPeriod) {
		this.endPeriod = endPeriod;
	}
	public int getGrade(int idx) {
		return grade[idx];
	}
	public String getGradeToString() {
		String strGrade = "";
		for(int i=1;i<7;i++)
		{
			if(grade[i] > 0)
			{
				if(strGrade != "")
					strGrade += ", ";
				strGrade += i;
			}
		}
		return strGrade;
	}
	public void setGrade(int idx) {
		grade[idx]++;	// �г� �� �л� ��
	}
//	public void editGrade(int beforeIdx, int afterIdx) {
//		this.grade[beforeIdx]--;
//		this.grade[afterIdx]++;
//	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public int getDentalInfection_man() {
		return dentalInfection_man;
	}
	public String getDentalInfectionManToString() {
		return Integer.toString(dentalInfection_man);
	}
	public void setDentalInfection_man(int dentalInfection_man) {
		this.dentalInfection_man = dentalInfection_man;
	}
	public int getDentalInfection_woman() {
		return dentalInfection_woman;
	}
	public String getDentalInfectionWomanToString() {
		return Integer.toString(dentalInfection_woman);
	}
	public void setDentalInfection_woman(int dentalInfection_woman) {
		this.dentalInfection_woman = dentalInfection_woman;
	}
	public int getDentalInfection_total() {
		return dentalInfection_total;
	}
	public String getDentalInfectionTotalToString() {
		return Integer.toString(dentalInfection_total);
	}
	public void setDentalInfection_total(int dentalInfection_total) {
		this.dentalInfection_total = dentalInfection_total;
	}
	public int getPeriodontalDisease_man() {
		return periodontalDisease_man;
	}
	public String getPeriodontalDiseaseManToString() {
		return Integer.toString(periodontalDisease_man);
	}
	public void setPeriodontalDisease_man(int periodontalDisease_man) {
		this.periodontalDisease_man = periodontalDisease_man;
	}
	public int getPeriodontalDisease_woman() {
		return periodontalDisease_woman;
	}
	public String getPeriodontalDiseaseWomanToString() {
		return Integer.toString(periodontalDisease_woman);
	}
	public void setPeriodontalDisease_woman(int periodontalDisease_woman) {
		this.periodontalDisease_woman = periodontalDisease_woman;
	}
	public int getPeriodontalDisease_total() {
		return periodontalDisease_total;
	}
	public String getPeriodontalDiseaseTotalToString() {
		return Integer.toString(periodontalDisease_total);
	}
	public void setPeriodontalDisease_total(int periodontalDisease_total) {
		this.periodontalDisease_total = periodontalDisease_total;
	}
	public int getCrossbite_man() {
		return crossbite_man;
	}
	public String getCrossbiteManToString() {
		return Integer.toString(crossbite_man);
	}
	public void setCrossbite_man(int crossbite_man) {
		this.crossbite_man = crossbite_man;
	}
	public int getCrossbite_woman() {
		return crossbite_woman;
	}
	public String getCrossbiteWomanToString() {
		return Integer.toString(crossbite_woman);
	}
	public void setCrossbite_woman(int crossbite_woman) {
		this.crossbite_woman = crossbite_woman;
	}
	public int getCrossbite_total() {
		return crossbite_total;
	}
	public String getCrossbiteTotalToString() {
		return Integer.toString(crossbite_total);
	}
	public void setCrossbite_total(int crossbite_total) {
		this.crossbite_total = crossbite_total;
	}
	public int getPersonnel_man() {
		return personnel_man;
	}
	public String getPersonnelManToString() {
		return Integer.toString(personnel_man);
	}
	public void setPersonnel_man(int personnel_man) {
		this.personnel_man = personnel_man;
	}
	public int getPersonnel_woman() {
		return personnel_woman;
	}
	public String getPersonnelWomanToString() {
		return Integer.toString(personnel_woman);
	}
	public void setPersonnel_woman(int personnel_woman) {
		this.personnel_woman = personnel_woman;
	}
	public int getPersonnel_total() {
		return personnel_total;
	}
	public String getPersonnelTotalToString() {
		return Integer.toString(personnel_total);
	}
	public void setPersonnel_total(int personnel_total) {
		this.personnel_total = personnel_total;
	}
	/************************************/
	
	public StatisticsVariables() {
		initVariables();
	}

	public void initVariables() {	// ��� ������ ����
		startPeriod = 0;	// �����Ⱓ
		endPeriod = 0;		// �����Ⱓ
		schoolName = "";	// �б���
		// grade
		dentalInfection_man = 0;	// ġ�ƿ����(��)
		dentalInfection_woman = 0;	// ġ�ƿ����(��)
		dentalInfection_total = 0;	// ġ�ƿ����(�հ�)
		periodontalDisease_man = 0;	// ġ����ȯ(��)
		periodontalDisease_woman = 0;	// ġ����ȯ(��)
		periodontalDisease_total = 0;	// ġ����ȯ(�հ�)
		crossbite_man = 0;		// ��������(��)
		crossbite_woman = 0;	// ��������(��)
		crossbite_total = 0;	// ��������(�հ�)
		personnel_man = 0;		// �˻��ο�(��)
		personnel_woman = 0;	// �˻��ο�(��)
		personnel_total = 0;	// �˻��ο�(�հ�)
	
	}
	public StatisticsVariables open(String statisticsFilePath, StatisticsVariables statistics) throws IOException, ClassNotFoundException {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try{
			fis = new FileInputStream(statisticsFilePath);
			ois = new ObjectInputStream(fis);
	
			statistics = (StatisticsVariables)ois.readObject();
			return statistics;
		}catch(Exception e){
			
			JOptionPane.showMessageDialog(null, "������ �����߽��ϴ�.");
			
		}finally{
			
			if(fis != null) try{fis.close();}catch(IOException e){}
			if(ois != null) try{ois.close();}catch(IOException e){}
			
		}
		return null;
	}
	
	
	
}
