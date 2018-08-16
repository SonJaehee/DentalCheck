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
	
	private int startPeriod;	// 검진기간
	private int endPeriod;		// 검진기간
	private int[] grade = {0,0,0,0,0,0,0};		// 대상학년
	private String schoolName;	// 학교명
	private int dentalInfection_man;	// 치아우식증(남)
	private int dentalInfection_woman;	// 치아우식증(여)
	private int dentalInfection_total;	// 치아우식증(합계)
	private int periodontalDisease_man;	// 치주질환(남)
	private int periodontalDisease_woman;	// 치주질환(여)
	private int periodontalDisease_total;	// 치주질환(합계)
	private int crossbite_man;		// 부정교합(남)
	private int crossbite_woman;	// 부정교합(여)
	private int crossbite_total;	// 부정교합(합계)
	private int personnel_man;		// 검사인원(남)
	private int personnel_woman;	// 검사인원(여)
	private int personnel_total;	// 검사인원(합계)
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
		grade[idx]++;	// 학년 당 학생 수
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

	public void initVariables() {	// 통계 페이지 오픈
		startPeriod = 0;	// 검진기간
		endPeriod = 0;		// 검진기간
		schoolName = "";	// 학교명
		// grade
		dentalInfection_man = 0;	// 치아우식증(남)
		dentalInfection_woman = 0;	// 치아우식증(여)
		dentalInfection_total = 0;	// 치아우식증(합계)
		periodontalDisease_man = 0;	// 치주질환(남)
		periodontalDisease_woman = 0;	// 치주질환(여)
		periodontalDisease_total = 0;	// 치주질환(합계)
		crossbite_man = 0;		// 부정교합(남)
		crossbite_woman = 0;	// 부정교합(여)
		crossbite_total = 0;	// 부정교합(합계)
		personnel_man = 0;		// 검사인원(남)
		personnel_woman = 0;	// 검사인원(여)
		personnel_total = 0;	// 검사인원(합계)
	
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
			
			JOptionPane.showMessageDialog(null, "오픈이 실패했습니다.");
			
		}finally{
			
			if(fis != null) try{fis.close();}catch(IOException e){}
			if(ois != null) try{ois.close();}catch(IOException e){}
			
		}
		return null;
	}
	
	
	
}
