package UI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class mainFrm extends JFrame implements ActionListener {
	JButton jb1, jb2, jb3, jb4;
	JMenu jMenu = new JMenu("�˵�");
	JMenuBar menuBar = new JMenuBar();
	JMenuItem jItem1 = new JMenuItem("������̾���");
	//JMenuItem jItem2 = new JMenuItem("���Ҿ�����Ϣ");
	JMenuItem jItem3 = new JMenuItem("����У԰�����ͼ");

	public mainFrm() {
		menuBar.add(jMenu);
		jMenu.add(jItem1);
		//jMenu.add(jItem2);
		jMenu.add(jItem3);
		// JPanel jPanel = new JPanel();
		jItem1.addActionListener(this);
		//jItem2.addActionListener(this);
		jItem3.addActionListener(this);

		this.setJMenuBar(menuBar);
		this.setTitle("У԰������ѯ");
		this.setVisible(true);
		this.setBounds(120, 120, 796, 430);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		// �������
		this.setResizable(false);
		this.setLocation(700, 200);

		String path = "D:/myelicpse2014/workspaces/CampusTour/src/1.jpg";
		ImageIcon background = new ImageIcon(path);
		JLabel label = new JLabel(background);
		this.add(label, BorderLayout.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jItem1) {
			new searchFrm();
		}
		/*else if (e.getSource()==jItem2) {
			new allFrm();
		}*/
		else if (e.getSource()==jItem3) {
			new drawFrm();
		}
	}
	public static void main(String[] args) {
		new mainFrm();
	}
}
