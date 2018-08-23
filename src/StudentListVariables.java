import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javax.swing.JFrame;

public class StudentListVariables extends JFrame {
	private static final long serialVersionUID = 1L;

	StudentListView StudentListItems;
	
	Vector<String[]> studentInfoList = new Vector<>();
	
	FileOutputStream fos = null;
	ObjectOutputStream oos = null;
	
	int level = -1;
	String strGrade;
	String strClass;
	String strName;
	String strFilePath;
	
	public void setStudentList(String source){
		level++;
		File dir = new File(source); 
		File[] fileList = dir.listFiles(); 
		try {
			for(int i = 0 ; i < fileList.length ; i++){
				File file = fileList[i]; 
				if(file.isFile() && level > 0){
					// ������ �ִٸ� ���� �̸� ���
					strName = file.getName();
					int fileIdx = strName.lastIndexOf(".");	// Ȯ���� ���ֱ�
					if(fileIdx > -1)
						strName = strName.substring(0, fileIdx);
					strFilePath = strGrade + "," + strClass + "," + strName;
					
					addToList(strGrade, strClass, strName);
					
					//System.out.println(strFilePath);
				}else if(file.isDirectory()){
					strFilePath = null;
					switch(level)
					{
					case 0:
						strGrade = file.getName();
						break;
					case 1:
						strClass = file.getName();
						break;
					}

					// ������丮�� �����ϸ� ����� ������� �ٽ� Ž��
					setStudentList(file.getCanonicalPath().toString()); 
					level--;
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	void addToList(String strGrade, String strClass, String strName)
	{
		String[] studentInfo = new String[3];
		
		studentInfo[0] = strGrade;
		studentInfo[1] = strClass;
		studentInfo[2] = strName;
		studentInfoList.addElement(studentInfo);
	}
	
	 public String[] getStudentInfo(int idx)
	{
		 String[] str = (String[])studentInfoList.get(idx);
		 
		 System.out.print(str[0]+",");
		 System.out.print(str[1]+",");
		 System.out.println(str[2]);
		 
		return studentInfoList.get(idx);
	}
	 
	 public int getStudentListSize()
	 {
		 return studentInfoList.size();
	 }
}
