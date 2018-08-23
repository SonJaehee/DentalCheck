import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.StringTokenizer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;

public class FileExplorer extends JSplitPane implements TreeWillExpandListener {
	private static FileExplorer instance;
	private String path = FileExplorer.class.getResource("").getPath();
	
	private JPanel leftTopPanel = new JPanel();
	
	private DefaultMutableTreeNode root = new DefaultMutableTreeNode(path);
	private DefaultMutableTreeNode curNode = root;
	private JTree tree = new JTree(root);
	private JScrollPane tree_jsp = new JScrollPane(tree);
	
	private static void createFileExplorer() {
		if(instance == null) {
			instance = new FileExplorer();
		}
	}
	public JTree getTree() {
		return this.tree;
	}
	
	private FileExplorer() {
		
		setTopComponent(leftTopPanel);
		setBottomComponent(tree_jsp);
		
		File dir = new File(path); 
		File[] file = dir.listFiles(); 
		
		for (int i = 0; i < file.length; i++) {
			String extension = getFileExtension(file[i].getName());
			if(extension != "")
				continue;
			
			DefaultMutableTreeNode dmt = new DefaultMutableTreeNode(file[i]);
			dmt.add(new DefaultMutableTreeNode("EMPTY"));
			curNode.add(dmt);
		}
		
		tree.addTreeWillExpandListener(this);

	}
	

	
	public String getFileExtension(String fileName) {
		String extension = "";
		
		int i = fileName.lastIndexOf('.');
		if(i>0) {
			extension = fileName.substring(i+1);
		}
		
		return extension;
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
		if (event.getSource() == tree) {
			tree.setSelectionPath(event.getPath());
			TreePath tp = tree.getSelectionPath();
			
			//System.out.println("tp = "+tp);
			
			StringTokenizer stk = new StringTokenizer(tp.toString(), "[,]");
			//stk.nextToken();
			
			if (stk.hasMoreTokens()) {
				String filepath = stk.nextToken().trim();
				while (stk.hasMoreTokens()) {
					filepath += stk.nextToken().trim() + File.separator;
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
							String extension = getFileExtension(data[i].getName());
							if(extension.equals("class"))
								continue;
							
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
