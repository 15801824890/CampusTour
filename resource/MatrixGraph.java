package resource;

public class MatrixGraph<T> extends AbstractGraph<T> {

	public static Vertex vertex[] = {
			new Vertex(0, "�����ţ�North Gate��", "ѧУ�����,1����", 215, 595),
			new Vertex(1, "����1��North Classroom 1��", "ѧУ������1", 55, 480),
			new Vertex(2, "��ͼ(North Library)", "ѧУ��ͼ���", 215, 395),
			new Vertex(3, "�ܽ�¥", "ѧУ��ý��ʵ���ң�����", 390, 490),
			new Vertex(4, "����¥(Administration Building)", "ѧУ�����ѧԺ�칫¥", 460, 530),
			new Vertex(5, "�Ͻ�1,2(South Classroom)", "ѧУ�Ͻ���1,2", 650, 460),
			new Vertex(6, "����(South Gate)", "ѧУ����ڣ�2����", 720, 595),
			new Vertex(7, "����ʵ��¥(Basic Laboratory)", "ѧУ�������ʵ��¥", 798, 520),
			new Vertex(8, "�ϲٳ�(South Playground)", "ѧУ�ϱ��˶���", 990, 400),
			new Vertex(9, "��ͼ(South Library)", "ѧУ�ϱߵ�ͼ���", 798, 360),
			new Vertex(10, "�緫�㳡(Sailing Square)", "�ŷ��ݵ���ѡ֮�أ��׳�������", 990, 180),
			//new Vertex(11, "������", "ѧУ������", , )
			};
	
	private final int MAX_WEIGHT = 99999;
	protected Matrix matrix;

	// �����ͼ��������Ϊ0������Ϊ0��lengthָ������˳����������ڽӾ�������
	public MatrixGraph(int length) {
		this.matrix = new Matrix(length); // ����length��length���󣬳�ֵΪ0
	}

	public MatrixGraph(T[] vertices) // ��vertices���㼯�Ϲ���ͼ������Ϊ0
	{
		this(vertices.length); // ����ָ�������Ŀ�ͼ
		for (int i = 0; i < vertices.length; i++)
			this.insertVertex(vertices[i]); // ����һ������
	}

	public MatrixGraph(T[] vertices, Triple[] edges) // ��vertices���㼯�Ϻ�edges�߼��Ϲ���ͼ
	{
		this(vertices); // ��vertices���㼯�Ϲ���ͼ��û�б�
		for (int j = 0; j < edges.length; j++)
			this.insertEdge(edges[j]); // ����һ����
	}

	// getȨֵ
	public int weight(int i, int j) {// ����<vi,vj>�ߵ�Ȩֵ������ͼ����С�����������·�����㷨
		return this.matrix.get(i, j);
	}

	// �����
	public void insertEdge(int i, int j, int weight) // ����ߡ�vi,vj����ȨֵΪweight
	{
		if (i != j) // ���ܱ�ʾ����
		{
			if (weight <= 0 || weight > MAX_WEIGHT) // �ߵ�Ȩֵ�ݴ���Ϊ�ޱߣ�ȡֵ��
				weight = MAX_WEIGHT;
			this.matrix.set(i, j, weight); // ���þ���Ԫ��[i,j]ֵΪweight����i��jԽ�磬�׳����Խ���쳣
		} else
			throw new IllegalArgumentException("���ܲ���������i=" + i + "��j=" + j);
	}

	public void insertEdge(Triple edge) // ����һ����
	{
		this.insertEdge(edge.row, edge.column, edge.value);
	}

	// ���붥��
	public int insertVertex(T x) // ����Ԫ��Ϊx�Ķ��㣬����x�������
	{
		int i = this.vertexlist.insert(x); // ����˳���β����x������x��ţ��Զ�����
		if (i >= this.matrix.getRows()) // ���ڽӾ������������� �������ݡ������ڽӾ���������ͬͼ�Ķ�����
			this.matrix.setRowsColumns(i + 1, i + 1);
		for (int j = 0; j < i; j++) // ��ʼ����i�С���Ԫ��ֵΪ�ޡ�i==jֵ��Ϊ0
		{
			this.matrix.set(i, j, MAX_WEIGHT);
			this.matrix.set(j, i, MAX_WEIGHT);
		}
		return i; // ���ز��붥�����
	}

	

	// ���·����Dijkstra�㷨��
	public String shortestPath(int start, int end) {
		if (start == end) {
			return "���ڴ���Ŀ�ĵ�";
		}
		int n = this.vertexCount(); // ͼ�Ķ�����
		int[] dist = new int[n]; // ���·������
		int[] path = new int[n]; // ���·�����յ��ǰһ������
		boolean[] vset = new boolean[n]; // ��������·���Ķ��㼯�ϣ���ֵȫΪfalse
		vset[start] = true; // ���Դ��vi�ڼ���S�С���iԽ�磬Java�׳����Խ���쳣
		for (int j = 0; j < n; j++) // ��ʼ��dist��path����
		{
			dist[j] = this.weight(start, j);
			path[j] = (j != start && dist[j] < MAX_WEIGHT) ? start : -1;
		}

		for (int j = (start + 1) % n; j != start; j = (j + 1) % n) // Ѱ�Ҵ�vi��vj�����·����vj��V-S������
		{
			int mindist = MAX_WEIGHT, min = 0; // ��·��������Сֵ�����±�
			for (int k = 0; k < n; k++)
				if (!vset[k] && dist[k] < mindist) {
					mindist = dist[k]; // ·��������Сֵ
					min = k; // ·��������Сֵ�±�
				}
			if (mindist == MAX_WEIGHT) // ��û���������·�����㷨������ �����Է���ͨͼ����
				break;
			vset[min] = true; // ȷ��һ�����·�����յ�min���뼯��S
			for (int k = 0; k < n; k++)
				// ������vi��V-S��������������·��������
				if (!vset[k] && this.weight(min, k) < MAX_WEIGHT
						&& dist[min] + this.weight(min, k) < dist[k]) {
					dist[k] = dist[min] + this.weight(min, k);// �ø���·���滻
					path[k] = min; // ���·������min����
				}
		}
		// ��ʾ���·����������
		for (int j = 0; j < n; j++) {
			if (j != start) {
				String pathString = "";
				for (int k = path[j]; k != j && k != -1; k = path[k]) {
					pathString = this.vertex[k].name + "-------" + pathString;
				}
				if (end == j) {
					return pathString + vertex[j].name;
				}
			}
		}
		return null;
	}

	public String shortestlength(int start, int end) {
		if (start == end) {
			return "0";
		}
		int n = this.vertexCount(); // ͼ�Ķ�����
		int[] dist = new int[n]; // ���·������
		int[] path = new int[n]; // ���·�����յ��ǰһ������
		boolean[] vset = new boolean[n]; // ��������·���Ķ��㼯�ϣ���ֵȫΪfalse
		vset[start] = true; // ���Դ��vi�ڼ���S�С���iԽ�磬Java�׳����Խ���쳣
		for (int j = 0; j < n; j++) // ��ʼ��dist��path����
		{
			dist[j] = this.weight(start, j);
			path[j] = (j != start && dist[j] < MAX_WEIGHT) ? start : -1;
		}

		for (int j = (start + 1) % n; j != start; j = (j + 1) % n) // Ѱ�Ҵ�vi��vj�����·����vj��V-S������
		{
			int mindist = MAX_WEIGHT, min = 0; // ��·��������Сֵ�����±�
			for (int k = 0; k < n; k++)
				if (!vset[k] && dist[k] < mindist) {
					mindist = dist[k]; // ·��������Сֵ
					min = k; // ·��������Сֵ�±�
				}
			if (mindist == MAX_WEIGHT) // ��û���������·�����㷨������ �����Է���ͨͼ����
				break;
			vset[min] = true; // ȷ��һ�����·�����յ�min���뼯��S
			for (int k = 0; k < n; k++)
				// ������vi��V-S��������������·��������
				if (!vset[k] && this.weight(min, k) < MAX_WEIGHT
						&& dist[min] + this.weight(min, k) < dist[k]) {
					dist[k] = dist[min] + this.weight(min, k);// �ø���·���滻
					path[k] = min; // ���·������min����
				}
		}
		// ��ʾ
		for (int j = 0; j < n; j++) {
			if (j != start) {
				String pathString = "";
				for (int k = path[j]; k != j && k != -1; k = path[k]) {
					pathString = this.vertex[k].name + pathString;
				}
				if (end == j) {
					return pathString = ""
							+ (dist[j] == MAX_WEIGHT ? "����" : dist[j]);
				}
			}
		}

		return null;
	}

	@Override
	protected int next(int i, int j) {
		// TODO Auto-generated method stub
		return 0;
	}




}
