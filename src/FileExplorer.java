import java.awt.Container;
import java.io.File;
import java.util.StringTokenizer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;

public class FileExplorer extends JSplitPane implements TreeWillExpandListener {
	private static FileExplorer instance;
	
//	private JSplitPane leftPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	private JPanel leftTopPanel = new JPanel();
//	leftPanel.setTopComponent(leftTopPanel);
//	leftPanel.setBottomComponent(tree_jsp);
	private DefaultMutableTreeNode root = new DefaultMutableTreeNode("My Å½»ö±â");
	private JTree tree = new JTree(root);
	private JScrollPane tree_jsp = new JScrollPane(tree);
	
	private static void createFileExplorer() {
		if(instance==null) {
			instance = new FileExplorer();
		}
	}
	
	private FileExplorer() {
		
		setTopComponent(leftTopPanel);
		setBottomComponent(tree_jsp);
		
		File[] file = File.listRoots();
		for (int i = 0; i < file.length; i++) {
			DefaultMutableTreeNode dmt = new DefaultMutableTreeNode(file[i]);
			dmt.add(new DefaultMutableTreeNode("EMPTY"));
			root.add(dmt);
		}
		
		tree.addTreeWillExpandListener(this);
	}
	
	public static FileExplorer getInstance() {
		if(instance==null) {
			createFileExplorer();
		}
		
		return instance;
	}
	
	
	@Override
	public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
		// TODO Auto-generated method stub
		if (event.getSource() == tree) {
			tree.setSelectionPath(event.getPath());
			TreePath tp = tree.getSelectionPath();
			// System.out.println("tp = "+tp);
			StringTokenizer stk = new StringTokenizer(tp.toString(), "[,]");
			stk.nextToken();
			if (stk.hasMoreTokens()) {
				String filepath = stk.nextToken().trim();
				while (stk.hasMoreTokens()) {
					filepath += stk.nextToken().trim() + "/";
				}
				// System.out.println("file = "+filepath);
				File dir = new File(filepath);
				File[] data = dir.listFiles();
				if (data == null)
					return;
				DefaultMutableTreeNode imsi = (DefaultMutableTreeNode) event.getPath().getLastPathComponent();
				imsi.removeAllChildren();
				// view_vc.clear();

				if (data.length == 0)
					imsi.add(new DefaultMutableTreeNode("EMPTY"));
				else {
					int count = -1;
					for (int i = 0; i < data.length; i++) {
						if (data[i].isDirectory()) {
							DefaultMutableTreeNode dtm = new DefaultMutableTreeNode(data[i].getName());
							dtm.add(new DefaultMutableTreeNode("EMPTY"));
							imsi.add(dtm);
							count++;
						} else {
							DefaultMutableTreeNode dtm = new DefaultMutableTreeNode(data[i].getName());
							imsi.add(dtm);
							count++;
						}
					}
					if (count == -1)
						imsi.add(new DefaultMutableTreeNode("EMPTY"));
				}
				// view_li.setListData(view_vc);
			}
		}
	}
	
}
