package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ActionMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import resource.MatrixGraph;
import resource.Triple;
import resource.Vertex;

public class allFrm extends JFrame {
	JLabel label = new JLabel("所有的景点:");
	JLabel label1 = new JLabel("该景点的信息:");
	JTextArea area = new JTextArea();
	JButton ok = new JButton("确定");
	JButton back = new JButton("返回");
	final JList jList;
	
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
	
	MatrixGraph myGraph = new MatrixGraph(vertex,edge);
	
	public allFrm() {
		String[] contents = new String[myGraph.vertexCount()];
		for (int i = 0; i < myGraph.vertexCount(); i++) {
			contents[i] = myGraph.vertex[i].toname();
		}
		jList = new JList(contents);
		JScrollPane jsp = new JScrollPane(jList);

		label.setBounds(10, 10, 100, 25);
		label1.setBounds(250, 15, 100, 20);
		jsp.setBounds(10, 40, 200, 300);
		ok.setBounds(10, 345, 100, 25);
		area.setBounds(250, 50, 160, 200);
		back.setBounds(300, 345, 80, 25);
		area.setLineWrap(true);// 自动换行

		this.setLayout(null);
		this.add(label);
		this.add(label1);
		this.add(jsp);
		this.add(ok);
		this.add(area);
		this.add(back);
		this.setTitle("校园景点信息");
		this.setVisible(true);
		this.setBounds(120, 120, 500, 420);
		this.setLocation(700, 200);
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setResizable(false);

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int a = jList.getSelectedIndex();
				area.setText("您选择的景点是：\n景点" + (a + 1) + " " + "\n"
						+ myGraph.vertex[a].toname() + "\n" + "简介：\n"
						+ myGraph.vertex[a].tointroduction());
			}
		});
	}
}
