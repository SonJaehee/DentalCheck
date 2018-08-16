import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class ItemFactory {
	private JPanel panel;
	private GridBagLayout gbl = new GridBagLayout();
	private GridBagConstraints gbc = new GridBagConstraints();
	
	public ItemFactory(JPanel panel, GridBagConstraints gbc) {
		this.panel = panel;
		this.gbc = gbc;
	}
	
	void addGrid(Component c, int gridx, int gridy, int gridwidth,
			int gridheight, int weightx, int weighty) {
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = gridwidth;
		gbc.gridheight = gridheight;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		//gbc.fill = GridBagConstraints.BOTH;
		// gbl.setConstraints(c, gbc);
		panel.add(c, gbc);
	}

	
	public void addLabel(String str, int gridx, int gridy,
			int gridwidth, int gridheight, int weightx, int weighty) {
		JLabel lbl = new JLabel(str);
		addGrid(lbl, gridx, gridy, 1, gridheight, weightx, weighty);
	}
	
	public void addBorderLabel(String str, int gridx, int gridy,
			int gridwidth, int gridheight, int weightx, int weighty) {
		JLabel lbl = new JLabel(str);
		lbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl.setHorizontalAlignment(JLabel.CENTER);
		//lbl.setPreferredSize(new Dimension(116*gridwidth,22));
		gbc.fill = GridBagConstraints.BOTH;
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
}
