import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;

public class DentalCheck extends JFrame implements ActionListener, TreeSelectionListener {
	private Container con;
	private JSplitPane mainPanel = new JSplitPane();
	private JSplitPane leftPanel = FileExplorer.getInstance();
	private JPanel rightPanel = new JPanel(new BorderLayout());
	private JTabbedPane tabbedPane = new JTabbedPane();
	
	private JScrollPane chartScrollPanel = new JScrollPane(); // tab1 panel
	private JPanel statisticsPanel = new JPanel(); // tab2 panel
	private JScrollPane listScrollPanel = new JScrollPane(); // tab3 panel
	
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnEdit;
	private JMenuItem mntmNew;
	private JMenuItem mntmOpen;
	private JMenuItem mntmSave;
	private JMenuItem mntmMacro;

	private MacroFrame macroFrame;
	private JMenuItem[] macros;
	private Dimension screen;

	ChartView chartItems;
	StatisticsView statisticItems;
	StudentListView studentListItems;
	JTree tree = FileExplorer.getInstance().getTree();
	
	public DentalCheck(String str) {
		super(str);

		screen = Toolkit.getDefaultToolkit().getScreenSize();

		this.init();
		this.start();

		this.setSize((int) screen.width, (int) screen.height);
		this.setLocation(0, 0);
		this.setVisible(true);

	}

	public void init() {
		con = this.getContentPane();
		con.setBackground(Color.WHITE);

		createMenubar();

		leftPanel.setPreferredSize(new Dimension((int) screen.width / 3, (int) screen.height));
		con.add(mainPanel);
		mainPanel.setLeftComponent(leftPanel);
		mainPanel.setRightComponent(rightPanel);

		UIManager.put("TabbedPane.selected", Color.WHITE);

		JComponent tab1 = makeTextPanel();
		tabbedPane.addTab("검진 차트", tab1);

		JComponent tab2 = makeTextPanel();
		tabbedPane.addTab("통계", tab2);

		JComponent tab3 = makeTextPanel();
		tabbedPane.addTab("리스트", tab3);

		rightPanel.add(tabbedPane);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		// set Items
		tab1.add("Center", chartScrollPanel);
		chartItems = new ChartView(chartScrollPanel);

		tab2.add("Center", statisticsPanel);
		statisticItems = new StatisticsView(statisticsPanel);
		
		tab3.add("Center", listScrollPanel);
		studentListItems = new StudentListView(listScrollPanel);
		
		tabbedPane.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	        	int selectedTabIndex = tabbedPane.getSelectedIndex();
	            switch(selectedTabIndex)
	            {
	            case 1:
	            	try {
						statisticItems.open();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "클래스 없음.");
					} catch (IOException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "오픈 실패.");
					}
	            	break;
	            case 2:
	            	studentListItems.createItems();
	            	break;
	            }
	        }
	    });
		tree.addTreeSelectionListener(this);
	}

	
	protected JComponent makeTextPanel() {
		JPanel panel = new JPanel(false);
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(1, 1));
		return panel;
	}
	
	private void createMenubar() {
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		mntmNew = new JMenuItem("New");
		mnFile.add(mntmNew);
		
		mntmOpen = new JMenuItem("Open");
		mntmOpen.setAccelerator(KeyStroke.getKeyStroke('O', Event.CTRL_MASK));
		mnFile.add(mntmOpen);

		mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);

		mntmMacro = new JMenuItem("Macro 설정");
		mnEdit.add(mntmMacro);

		macros = new JMenuItem[MacroFrame.MAX_MACRO_NUMBER];
		for (int i = 0; i < MacroFrame.MAX_MACRO_NUMBER; i++) {
			macros[i] = new JMenuItem("macro" + (i + 1));
			macros[i].setAccelerator(KeyStroke.getKeyStroke(i + 49, Event.CTRL_MASK));
			macros[i].addActionListener(this);
			mnEdit.add(macros[i]);
		}
	}

	private void start() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mntmNew.addActionListener(this);
		mntmOpen.addActionListener(this);
		mntmSave.addActionListener(this);
		mntmMacro.addActionListener(this);

	}

	public static void main(String[] args) {
		DentalCheck checkUp = new DentalCheck("Dental Check Up");

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmNew) {
			chartItems.reset();
		} else if (e.getSource() == mntmOpen) {
			try {
				int selectedTabIndex = tabbedPane.getSelectedIndex();
				switch(selectedTabIndex)
	            {
				case 0:
					chartItems.open();
					break;
	            case 1:
	            	statisticItems.open();
	            	break;
	            case 2:
	            	studentListItems.createItems();
	            	break;
	            }
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "클래스 없음.");
			} catch (IOException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "오픈 실패.");
			}
		} else if (e.getSource() == mntmSave) {
			try {
				chartItems.save();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		} else if (e.getSource() == mntmMacro) {
			macroFrame = new MacroFrame();
		} else if (e.getSource() == macros[0]) {
			System.out.println(macroFrame.mString[0]);

		} else if (e.getSource() == macros[1]) {
			System.out.println(macroFrame.mString[1]);
		} else if (e.getSource() == macros[2]) {
			System.out.println(macroFrame.mString[2]);
		} else if (e.getSource() == macros[3]) {
			System.out.println(macroFrame.mString[3]);
		} else if (e.getSource() == macros[4]) {
			System.out.println(macroFrame.mString[4]);
		}
	}

	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		TreePath tp = arg0.getNewLeadSelectionPath();
        if (tp != null)
        {
        	StringTokenizer stk = new StringTokenizer(tp.toString(), "[,]");
			//stk.nextToken();
			
			if (stk.hasMoreTokens()) {
				String filePath = stk.nextToken().trim();
				while (stk.hasMoreTokens()) {

					String extension = "";
					String curPathFileName = stk.nextToken().trim();
					int i = curPathFileName.lastIndexOf('.');
					if(i>0) {
						extension = curPathFileName.substring(i+1);
					}
					
					filePath += curPathFileName;
					if(extension.equals(""))
						filePath += File.separator;
				}
				System.out.println(filePath);
				
				File file = new File(filePath);
				if(file.isFile())
					try {
						tabbedPane.setSelectedIndex(0);
						chartItems.open(filePath);//.getAbsolutePath());
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "클래스 없음.");
					} catch (IOException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "오픈 실패.");
					}
			}
        }
        //   System.out.println("Selected:  "+tp.getLastPathComponent());
	}
}