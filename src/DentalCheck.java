import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class DentalCheck extends JFrame implements ActionListener {
	private Container con;
	private JSplitPane mainPanel = new JSplitPane();
	private JSplitPane leftPanel = FileExplorer.getInstance();
//	private JSplitPane leftPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
//	private JPanel leftTopPanel = new JPanel();

	private JScrollPane rightScrollPanel = new JScrollPane();

//	private DefaultMutableTreeNode root = new DefaultMutableTreeNode("My 탐색기");
//	private JTree tree = new JTree(root);
//	private JScrollPane tree_jsp = new JScrollPane(tree);

	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnEdit;
	private JMenuItem mntmOpen;
	private JMenuItem mntmSave;
	private JMenuItem mntmMacro;

	private MacroFrame macroFrame;
	private JMenuItem[] macros;
	private Dimension screen;

	EnterChart chart;
	ChartItemManager chartImtes;
	
	public DentalCheck(String str) {
		super(str);

		screen = Toolkit.getDefaultToolkit().getScreenSize();

		this.init();
		this.start();

		// getContentPane().setBackground(Color.WHITE);

		/*********************** 메뉴 ***********************/
		// JMenuBar menuBar = new JMenuBar();
		// setJMenuBar(menuBar);
		//
		// JMenu mnFile = new JMenu("File");
		// menuBar.add(mnFile);
		//
		// JMenuItem mntmOpen = new JMenuItem("Open");
		// mnFile.add(mntmOpen);
		//
		// JMenuItem mntmSave = new JMenuItem("Save");
		// mnFile.add(mntmSave);
		// getContentPane().setLayout(null);

		/********** 왼쪽 **********/
		// JScrollPane leftScrollPane = new JScrollPane();
		// leftScrollPane.setBounds(10, 10, 200, 600);
		// getContentPane().add(leftScrollPane);
		//
		// JPanel leftPanel = new JPanel();
		// leftPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		// leftPanel.setBackground(Color.WHITE);
		// leftScrollPane.setViewportView(leftPanel);
		//
		// GridBagLayout left_gbl = new GridBagLayout();
		// GridBagConstraints left_gbc = new GridBagConstraints();
		// left_gbc.fill = GridBagConstraints.BOTH;
		//
		// leftPanel.setLayout(left_gbl);
		// subDirList("C:\\test");

		/*** Do something withd tempPath and temp FileName ^^; ***/

		// 아이콘 배치

		// /********** 오른쪽 **********/
		// JScrollPane rightScrollPane = new JScrollPane();
		// rightScrollPane.setBounds(220, 10, 400, 600);
		// getContentPane().add(rightScrollPane);
		//
		// JPanel rightPanel = new JPanel();
		// rightPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		// rightPanel.setBackground(Color.WHITE);
		// rightScrollPane.setViewportView(rightPanel);
		//
		// GridBagLayout right_gbl = new GridBagLayout();
		// GridBagConstraints right_gbc = new GridBagConstraints();
		// right_gbc.fill = GridBagConstraints.BOTH;
		//
		// rightPanel.setLayout(right_gbl);
		//
		// // 아이콘 배치
		//
		// pack();
		this.setSize((int) screen.width, (int) screen.height);
		this.setLocation(0, 0);
		this.setVisible(true);

	}

	public void init() {
		con = this.getContentPane();
		con.setBackground(Color.WHITE);

		createMenubar();
		
		UIManager.put("TabbedPane.selected", Color.WHITE);
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JComponent panel1 = makeTextPanel();
		tabbedPane.addTab("검색 및 입력", panel1);

		JComponent panel2 = makeTextPanel();
		tabbedPane.addTab("통계", panel2);

		JComponent panel3 = makeTextPanel();
		tabbedPane.addTab("리스트", panel3);

		con.add(tabbedPane);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		panel1.add("Center", mainPanel);
		leftPanel.setPreferredSize(new Dimension((int) screen.width / 3, (int) screen.height));
		
		mainPanel.setLeftComponent(leftPanel);
//		leftPanel.setTopComponent(leftTopPanel);
//		leftPanel.setBottomComponent(tree_jsp);

		mainPanel.setRightComponent(rightScrollPanel);

		mainPanel.setRightComponent(rightScrollPanel);
		
		// set Items
		chart = new EnterChart(rightScrollPanel);
	}

	protected JComponent makeTextPanel() {
		JPanel panel = new JPanel(false);
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(1, 1));
		// panel.setMaximumSize(screen);
		return panel;
	}

	private void createMenubar() {
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

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

		// getContentPane().setLayout(null);
	}

//	private void createLeftView() {
//		File[] file = File.listRoots();
//		for (int i = 0; i < file.length; i++) {
//			DefaultMutableTreeNode dmt = new DefaultMutableTreeNode(file[i]);
//			dmt.add(new DefaultMutableTreeNode("EMPTY"));
//			root.add(dmt);
//		}
//	}

//	private void createRightView() {
//		
//		
//		
//	}
	private void start() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		tree.addTreeWillExpandListener(this);

		mntmOpen.addActionListener(this);
		mntmSave.addActionListener(this);
		mntmMacro.addActionListener(this);

	}

	// public void subDirList(String source) {
	// File dir = new File(source);
	// File[] fileList = dir.listFiles();
	// if (fileList != null && fileList.length != 0) {
	// try {
	// for (int i = 0; i < fileList.length; i++) {
	// File file = fileList[i];
	// if (file.isFile()) {
	// // 파일이 있다면 파일 이름 출력
	// System.out.println("\t 파일 이름 = " + file.getName());
	// } else if (file.isDirectory()) {
	// System.out.println("디렉토리 이름 = " + file.getName());
	// // 서브디렉토리가 존재하면 재귀적 방법으로 다시 탐색
	// subDirList(file.getCanonicalPath().toString());
	// }
	// }
	// } catch (IOException e) {
	//
	// }
	// }
	// }



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// new DentalCheck("Dental Check Up");
		DentalCheck checkUp = new DentalCheck("Dental Check Up");

	}

//	@Override
//	public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
//		// TODO Auto-generated method stub
//		if (event.getSource() == tree) {
//			tree.setSelectionPath(event.getPath());
//			TreePath tp = tree.getSelectionPath();
//			// System.out.println("tp = "+tp);
//			StringTokenizer stk = new StringTokenizer(tp.toString(), "[,]");
//			stk.nextToken();
//			if (stk.hasMoreTokens()) {
//				String filepath = stk.nextToken().trim();
//				while (stk.hasMoreTokens()) {
//					filepath += stk.nextToken().trim() + "/";
//				}
//				// System.out.println("file = "+filepath);
//				File dir = new File(filepath);
//				File[] data = dir.listFiles();
//				if (data == null)
//					return;
//				DefaultMutableTreeNode imsi = (DefaultMutableTreeNode) event.getPath().getLastPathComponent();
//				imsi.removeAllChildren();
//				// view_vc.clear();
//
//				if (data.length == 0)
//					imsi.add(new DefaultMutableTreeNode("EMPTY"));
//				else {
//					int count = -1;
//					for (int i = 0; i < data.length; i++) {
//						if (data[i].isDirectory()) {
//							DefaultMutableTreeNode dtm = new DefaultMutableTreeNode(data[i].getName());
//							dtm.add(new DefaultMutableTreeNode("EMPTY"));
//							imsi.add(dtm);
//							count++;
//						} else {
//							DefaultMutableTreeNode dtm = new DefaultMutableTreeNode(data[i].getName());
//							imsi.add(dtm);
//							count++;
//						}
//					}
//					if (count == -1)
//						imsi.add(new DefaultMutableTreeNode("EMPTY"));
//				}
//				// view_li.setListData(view_vc);
//			}
//		}
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == mntmOpen) {
			try {
				chart = new EnterChart(rightScrollPanel);
				chart.open();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == mntmSave) {
			try {
				chart.save();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
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
}