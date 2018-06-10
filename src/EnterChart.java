
public class EnterChart {
	// 의사정보
	public static final int licenseNumber = 9315;
	public static final String doctorName = "손한신";
	public static final String hospitalName = "한신치과";
	
	// 학생정보
	String school;
	int grade;
	int classNum;
	int studentNum;
	String name;
	int sex;	// 1 : 남, 2 : 여
	int birth;
	int comprehensiveOpinion;
	int homeMeasures;

	
	
	public void initVariables() {
		school = "";
		grade = 0;
		classNum = 0;
		studentNum = 0;
		name = "";
		sex = 0;
		birth = 0;
		
		// 초중고 공통항목
		DentalCategory dentalInfection = new DentalCategory(1, 0, 0);		// 우식치아
		DentalCategory riskProducingTeeth = new DentalCategory(1, 0, 0);	// 우식발생위험치아
		DentalCategory defectiveTeeth = new DentalCategory(1, 0, 0);		// 결손치아
		DentalCategory softTissueDisease = new DentalCategory(1, 0, 0);		// 구내염 및 연조직질환
		DentalCategory crossbite = new DentalCategory(1, 0, 0);				// 부정교합
		DentalCategory oralHygiene = new DentalCategory(1, 0, 0);			// 구강위생 상태
		DentalCategory dentalCondition = new DentalCategory(0, 0, 0);		// 그밖의 치아상태
		
		// 중고 추가항목
		DentalCategory periodontalDisease = new DentalCategory(1, 0, 0);	// 치주질환
		DentalCategory abnormalSymptoms = new DentalCategory(1, 0, 0);		// 악관절 이상
		
		// 고 추가항목
		DentalCategory dentalWeariness = new DentalCategory(1, 0, 0);		// 치아마모증
		DentalCategory wisdomeeth = new DentalCategory(1, 0, 0);			// 사랑니

		// 종합소견
		comprehensiveOpinion = 0;		// 종합소견
		homeMeasures = 0;				// 가정에서의 조치사항
	}
	
	public void main(String args[]){
		initVariables();
	}
	
	
}
