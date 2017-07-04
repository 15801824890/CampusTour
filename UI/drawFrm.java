package UI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import resource.MatrixGraph;
import resource.Triple;
import resource.Vertex;

public class drawFrm extends JFrame {
	JButton[] button;
	JButton allButton=new JButton("查询所有景点");
	JButton reback = new JButton("退出");
	JButton inquiry = new JButton("查询最短路径");
	JTextArea area = new JTextArea();
	JScrollPane jsp = new JScrollPane(area);
	JLabel label3 = new JLabel("景点信息：");
	String[] s;
	int[] x;
	int[] y;

	Vertex vertex[] = {
			new Vertex(0, "北大门（North Gate）", "学校北入口,1号门", 215, 595),
			new Vertex(1, "北教1（North Classroom 1）", "学校北教室1", 55, 480),
			new Vertex(2, "北图(North Library)", "学校北图书馆", 215, 395),
			new Vertex(3, "奋进楼", "学校多媒体实验室，机房", 390, 490),
			new Vertex(4, "行政楼(Administration Building)", "学校武装部，计算机学院办公室", 460,
					530),
			new Vertex(5, "南教1,2(South Classroom)", "学校南教室1,2", 650, 490),
			new Vertex(6, "南门(South Gate)", "学校南入口，2号门", 720, 595),
			new Vertex(7, "基础实验楼(Basic Laboratory)", "学校物理基础实验楼", 798, 520),
			new Vertex(8, "南操场(South Playground)", "学校南边运动场", 990, 400),
			new Vertex(9, "南图(South Library)", "学校南图书馆", 798, 360),
			new Vertex(10, "风帆广场(Sailing Square)", "放风筝的首选之地，俗称情人坡", 990, 180), };

	Triple edge[] = {  new Triple(0, 1, 15), new Triple(1, 0, 15),
			new Triple(0, 2, 30), new Triple(2, 0, 30), new Triple(2, 3, 25),
			new Triple(3, 2, 25), new Triple(1, 2, 20), new Triple(2, 1, 20),
			new Triple(3, 4, 5), new Triple(4, 3, 5), new Triple(2, 4, 28),
			new Triple(4, 2, 28), new Triple(2, 9, 70), new Triple(9, 2, 70),
			new Triple(2, 10, 110), new Triple(10, 2, 110),
			new Triple(2, 5, 40), new Triple(5, 2, 40), new Triple(4, 5, 15),
			new Triple(5, 4, 15), new Triple(4, 9, 50), new Triple(9, 4, 50),
			new Triple(5, 9, 30), new Triple(9, 5, 30), new Triple(5, 6, 15),
			new Triple(6, 5, 15), new Triple(5, 7, 20), new Triple(7, 5, 20),
			new Triple(6, 9, 30), new Triple(9, 6, 30), new Triple(6, 7, 10),
			new Triple(7, 6, 10), new Triple(7, 9, 10), new Triple(9, 7, 10),
			new Triple(7, 8, 20), new Triple(8, 7, 20), new Triple(8, 10, 20),
			new Triple(10, 8, 20), new Triple(9, 8, 15), new Triple(8, 9, 15),
			new Triple(9, 10, 30), new Triple(10, 9, 30),  };

	MatrixGraph myGraph = new MatrixGraph(vertex, edge);

	public drawFrm() {
		this.setLayout(null);
		allButton.setBounds(550, 770, 120, 30);
		reback.setBounds(850, 770, 80, 30);
		inquiry.setBounds(700, 770, 120, 30);
		label3.setBounds(80, 670, 80, 20);
		jsp.setBounds(80, 700, 250, 100);
		area.setLineWrap(true);

		String[] contents = new String[myGraph.vertexCount()];
		for (int i = 0; i < myGraph.vertexCount(); i++) {
			contents[i] = myGraph.vertex[i].toname();
		}

		s = new String[myGraph.vertexCount()];
		x = new int[myGraph.vertexCount()];
		y = new int[myGraph.vertexCount()];
		button = new JButton[myGraph.vertexCount()];
		for (int i = 0; i < myGraph.vertexCount(); i++) {
			s[i] = myGraph.vertex[i].toname();
			x[i] = myGraph.vertex[i].todx();
			y[i] = myGraph.vertex[i].tody();
			button[i] = new JButton("景点" + (i + 1));
			button[i].setBounds(x[i], y[i], 75, 30);
			this.add(button[i]);
		}

		// 背景
		String path = "D:/myelicpse2014/workspaces/CampusTour/src/1.jpg";
		ImageIcon background = new ImageIcon(path);
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, 1163, 640); // 位置大小
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		((JPanel) this.getContentPane()).setOpaque(false);
		// getLayeredPane()获取的是分层面板，将背景图片放置最底层，就可以作为背景图片了。
		// 在JFrame中还有一个ContentPane容器，如此，放置最底层就会被ContentPane所遮挡，将此设置为透明。注意，需要对其进行强制转换。

		this.add(label3);
		this.add(jsp);
		this.add(allButton);
		this.add(inquiry);
		this.add(reback);

		this.setTitle("校园景点信息");
		this.setResizable(false);
		this.setBounds(50, 50, 1163, 900);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

		button[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				area.setText("您选择的景点是： \n " + "第"
						+ (myGraph.vertex[0].tonum() + 1) + "号景点   ：   "
						+ (myGraph.vertex[0].toname()) + "\n\n" + "简介："
						+ myGraph.vertex[0].tointroduction());
			}
		});
		button[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				area.setText("您选择的景点是： \n " + "第"
						+ (myGraph.vertex[1].tonum() + 1) + "号景点   ：   "
						+ (myGraph.vertex[1].toname()) + "\n\n" + "简介："
						+ myGraph.vertex[1].tointroduction());
			}
		});
		button[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				area.setText("您选择的景点是： \n " + "第"
						+ (myGraph.vertex[2].tonum() + 1) + "号景点   ：   "
						+ (myGraph.vertex[2].toname()) + "\n\n" + "简介："
						+ myGraph.vertex[2].tointroduction());
			}
		});
		button[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				area.setText("您选择的景点是： \n " + "第"
						+ (myGraph.vertex[3].tonum() + 1) + "号景点   ：   "
						+ (myGraph.vertex[3].toname()) + "\n\n" + "简介："
						+ myGraph.vertex[3].tointroduction());
			}
		});
		button[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				area.setText("您选择的景点是： \n " + "第"
						+ (myGraph.vertex[4].tonum() + 1) + "号景点   ：   "
						+ (myGraph.vertex[4].toname()) + "\n\n" + "简介："
						+ myGraph.vertex[4].tointroduction());
			}
		});
		button[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				area.setText("您选择的景点是： \n " + "第"
						+ (myGraph.vertex[5].tonum() + 1) + "号景点   ：   "
						+ (myGraph.vertex[5].toname()) + "\n\n" + "简介："
						+ myGraph.vertex[5].tointroduction());
			}
		});
		button[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				area.setText("您选择的景点是： \n " + "第"
						+ (myGraph.vertex[6].tonum() + 1) + "号景点   ：   "
						+ (myGraph.vertex[6].toname()) + "\n\n" + "简介："
						+ myGraph.vertex[6].tointroduction());
			}
		});
		button[7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				area.setText("您选择的景点是： \n " + "第"
						+ (myGraph.vertex[7].tonum() + 1) + "号景点   ：   "
						+ (myGraph.vertex[7].toname()) + "\n\n" + "简介："
						+ myGraph.vertex[7].tointroduction());
			}
		});
		button[8].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				area.setText("您选择的景点是： \n " + "第"
						+ (myGraph.vertex[8].tonum() + 1) + "号景点   ：   "
						+ (myGraph.vertex[8].toname()) + "\n\n" + "简介："
						+ myGraph.vertex[8].tointroduction());
			}
		});
		button[9].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				area.setText("您选择的景点是： \n " + "第"
						+ (myGraph.vertex[9].tonum() + 1) + "号景点   ：   "
						+ (myGraph.vertex[9].toname()) + "\n\n" + "简介："
						+ myGraph.vertex[9].tointroduction());
			}
		});
		button[10].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				area.setText("您选择的景点是： \n " + "第"
						+ (myGraph.vertex[10].tonum() + 1) + "号景点   ：   "
						+ (myGraph.vertex[10].toname()) + "\n\n" + "简介："
						+ myGraph.vertex[10].tointroduction());
			}
		});
		inquiry.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new searchFrm();
			}
		});
		reback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		allButton.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new allFrm();
			}
		});
	}

	public static void main(String[] args) {
		new drawFrm();
	}

}
