
public class EnterChart {
	// �ǻ�����
	public static final int licenseNumber = 9315;
	public static final String doctorName = "���ѽ�";
	public static final String hospitalName = "�ѽ�ġ��";
	
	// �л�����
	String school;
	int grade;
	int classNum;
	int studentNum;
	String name;
	int sex;	// 1 : ��, 2 : ��
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
		
		// ���߰� �����׸�
		DentalCategory dentalInfection = new DentalCategory(1, 0, 0);		// ���ġ��
		DentalCategory riskProducingTeeth = new DentalCategory(1, 0, 0);	// ��Ĺ߻�����ġ��
		DentalCategory defectiveTeeth = new DentalCategory(1, 0, 0);		// ���ġ��
		DentalCategory softTissueDisease = new DentalCategory(1, 0, 0);		// ������ �� ��������ȯ
		DentalCategory crossbite = new DentalCategory(1, 0, 0);				// ��������
		DentalCategory oralHygiene = new DentalCategory(1, 0, 0);			// �������� ����
		DentalCategory dentalCondition = new DentalCategory(0, 0, 0);		// �׹��� ġ�ƻ���
		
		// �߰� �߰��׸�
		DentalCategory periodontalDisease = new DentalCategory(1, 0, 0);	// ġ����ȯ
		DentalCategory abnormalSymptoms = new DentalCategory(1, 0, 0);		// �ǰ��� �̻�
		
		// �� �߰��׸�
		DentalCategory dentalWeariness = new DentalCategory(1, 0, 0);		// ġ�Ƹ�����
		DentalCategory wisdomeeth = new DentalCategory(1, 0, 0);			// �����

		// ���ռҰ�
		comprehensiveOpinion = 0;		// ���ռҰ�
		homeMeasures = 0;				// ���������� ��ġ����
	}
	
	public void main(String args[]){
		initVariables();
	}
	
	
}
