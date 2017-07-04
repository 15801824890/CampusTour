package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import resource.MatrixGraph;
import resource.Triple;
import resource.Vertex;

public class searchFrm extends JFrame {
	JLabel label1 = new JLabel("������ ��");
	JLabel label2 = new JLabel("Ŀ�ĵ� ��");
	JLabel label3 = new JLabel("���·��Ϊ��");
	JLabel label4 = new JLabel("��������·��Ϊ��");
	JComboBox jb1, jb2;
	JButton btn1 = new JButton("���·��");
	JButton btn2 = new JButton("����");
	JTextArea area = new JTextArea();
	JTextField text = new JTextField();
	JScrollPane jsp = new JScrollPane(area);

	Vertex vertex[] = {
			new Vertex(0, "�����ţ�North Gate��", "ѧУ�����,1����", 215, 595),
			new Vertex(1, "����1��North Classroom 1��", "ѧУ������1", 55, 480),
			new Vertex(2, "��ͼ(North Library)", "ѧУ��ͼ���", 215, 395),
			new Vertex(3, "�ܽ�¥", "ѧУ��ý��ʵ���ң�����", 390, 490),
			new Vertex(4, "����¥(Administration Building)", "ѧУ��װ���������ѧԺ�칫��",
					460, 530),
			new Vertex(5, "�Ͻ�1,2(South Classroom)", "ѧУ�Ͻ���1,2", 650, 490),
			new Vertex(6, "����(South Gate)", "ѧУ����ڣ�2����", 720, 595),
			new Vertex(7, "����ʵ��¥(Basic Laboratory)", "ѧУ�������ʵ��¥", 798, 520),
			new Vertex(8, "�ϲٳ�(South Playground)", "ѧУ�ϱ��˶���", 990, 400),
			new Vertex(9, "��ͼ(South Library)", "ѧУ��ͼ���", 798, 360),
			new Vertex(10, "�緫�㳡(Sailing Square)", "�ŷ��ݵ���ѡ֮�أ��׳�������", 990, 180), };

	Triple edge[] = { new Triple(0, 1, 15), new Triple(1, 0, 15),
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
			new Triple(9, 10, 30), new Triple(10, 9, 30), };

	MatrixGraph myGraph = new MatrixGraph(vertex, edge);

	public searchFrm() {
		String[] contents = new String[myGraph.vertexCount()];
		for (int i = 0; i < myGraph.vertexCount(); i++) {
			contents[i] = myGraph.vertex[i].toname();
		}
		jb1 = new JComboBox(contents);
		jb2 = new JComboBox(contents);

		this.setLayout(null);
		this.setTitle("��ѯ���·��");
		this.setResizable(false);

		label1.setBounds(5, 5, 150, 20);
		jb1.setBounds(5, 30, 150, 20);
		label2.setBounds(180, 5, 150, 20);
		jb2.setBounds(180, 30, 150, 20);
		label4.setBounds(5, 60, 140, 20);
		label3.setBounds(5, 250, 120, 20);
		jsp.setBounds(5, 90, 290, 150);
		area.setLineWrap(true);
		text.setBounds(5, 275, 140, 25);
		btn1.setBounds(5, 310, 140, 25);
		btn2.setBounds(150, 310, 140, 25);

		this.add(text);
		this.add(label3);
		this.add(label4);
		this.add(label1);
		this.add(label2);
		this.add(jb1);
		this.add(jb2);
		this.add(jsp);
		this.add(btn1);
		this.add(btn2);

		this.setBounds(700, 200, 370, 420);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int c = jb1.getSelectedIndex();
				int b = jb2.getSelectedIndex();
				String s1 = myGraph.shortestPath(c, b);
				area.setText(s1);
				String s2 = myGraph.shortestlength(c, b);
				text.setText(s2);
			}
		});
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});

	}

	public static void main(String[] args) {
		new searchFrm();
	}

}
