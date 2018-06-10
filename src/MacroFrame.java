import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MacroFrame extends JDialog implements ActionListener {
	public static final int MAX_MACRO_NUMBER = 5;
	
	private JTextField[] textFields;
	
	public static String[] mString = new String[MAX_MACRO_NUMBER];
	private JButton saveBtn;
	public MacroFrame() {
		JLabel[] macs = new JLabel[MAX_MACRO_NUMBER];
		textFields = new JTextField[MAX_MACRO_NUMBER];
		
		GridLayout grid = new GridLayout(6, 2);
		this.setLayout(grid);
		grid.setVgap(10);
		grid.setHgap(10);
		
		for(int i=0; i<MAX_MACRO_NUMBER; i++) {
			macs[i] = new JLabel("내용"+  (i+1) + "(ctrl + " + (i+1) + ") ");
			textFields[i] = new JTextField(100);
			if(mString[i] != null || mString[i] != "") {
				textFields[i].setText(mString[i]);
			}
			getContentPane().add(macs[i]);
			getContentPane().add(textFields[i]);
		}
		

		JLabel mac6 = new JLabel(" ");
		saveBtn = new JButton("Save");
		
		
		getContentPane().add(mac6);
		getContentPane().add(saveBtn);
		saveBtn.addActionListener(this);
		
		this.setSize(500,500);
		this.setLocation(350, 200);
        this.setModal(true);
        this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == saveBtn) {
			for(int i=0; i<MAX_MACRO_NUMBER; i++) {
				mString[i] = textFields[i].getText();
				System.out.println(mString[i]);
			}
			JOptionPane.showMessageDialog(null, "저장이 완료되었습니다.");
			dispose();
		}
	}
}
