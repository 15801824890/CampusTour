package resource;

public class MatrixGraph<T> extends AbstractGraph<T> {

	public static Vertex vertex[] = {
			new Vertex(0, "北大门（North Gate）", "学校北入口,1号门", 215, 595),
			new Vertex(1, "北教1（North Classroom 1）", "学校北教室1", 55, 480),
			new Vertex(2, "北图(North Library)", "学校北图书馆", 215, 395),
			new Vertex(3, "奋进楼", "学校多媒体实验室，机房", 390, 490),
			new Vertex(4, "行政楼(Administration Building)", "学校计算机学院办公楼", 460, 530),
			new Vertex(5, "南教1,2(South Classroom)", "学校南教室1,2", 650, 460),
			new Vertex(6, "南门(South Gate)", "学校南入口，2号门", 720, 595),
			new Vertex(7, "基础实验楼(Basic Laboratory)", "学校物理基础实验楼", 798, 520),
			new Vertex(8, "南操场(South Playground)", "学校南边运动场", 990, 400),
			new Vertex(9, "南图(South Library)", "学校南边的图书馆", 798, 360),
			new Vertex(10, "风帆广场(Sailing Square)", "放风筝的首选之地，俗称情人坡", 990, 180),
			//new Vertex(11, "大礼堂", "学校大礼堂", , )
			};
	
	private final int MAX_WEIGHT = 99999;
	protected Matrix matrix;

	// 构造空图，顶点数为0，边数为0；length指定顶点顺序表容量和邻接矩阵容量
	public MatrixGraph(int length) {
		this.matrix = new Matrix(length); // 构造length×length矩阵，初值为0
	}

	public MatrixGraph(T[] vertices) // 以vertices顶点集合构造图，边数为0
	{
		this(vertices.length); // 构造指定容量的空图
		for (int i = 0; i < vertices.length; i++)
			this.insertVertex(vertices[i]); // 插入一个顶点
	}

	public MatrixGraph(T[] vertices, Triple[] edges) // 以vertices顶点集合和edges边集合构造图
	{
		this(vertices); // 以vertices顶点集合构造图，没有边
		for (int j = 0; j < edges.length; j++)
			this.insertEdge(edges[j]); // 插入一条边
	}

	// get权值
	public int weight(int i, int j) {// 返回<vi,vj>边的权值。用于图的最小生成树、最短路径等算法
		return this.matrix.get(i, j);
	}

	// 插入边
	public void insertEdge(int i, int j, int weight) // 插入边〈vi,vj〉，权值为weight
	{
		if (i != j) // 不能表示自身环
		{
			if (weight <= 0 || weight > MAX_WEIGHT) // 边的权值容错，视为无边，取值∞
				weight = MAX_WEIGHT;
			this.matrix.set(i, j, weight); // 设置矩阵元素[i,j]值为weight。若i、j越界，抛出序号越界异常
		} else
			throw new IllegalArgumentException("不能插入自身环，i=" + i + "，j=" + j);
	}

	public void insertEdge(Triple edge) // 插入一条边
	{
		this.insertEdge(edge.row, edge.column, edge.value);
	}

	// 插入顶点
	public int insertVertex(T x) // 插入元素为x的顶点，返回x顶点序号
	{
		int i = this.vertexlist.insert(x); // 顶点顺序表尾插入x，返回x序号，自动扩容
		if (i >= this.matrix.getRows()) // 若邻接矩阵容量不够， 矩阵扩容。保持邻接矩阵行列数同图的顶点数
			this.matrix.setRowsColumns(i + 1, i + 1);
		for (int j = 0; j < i; j++) // 初始化第i行、列元素值为∞。i==j值已为0
		{
			this.matrix.set(i, j, MAX_WEIGHT);
			this.matrix.set(j, i, MAX_WEIGHT);
		}
		return i; // 返回插入顶点序号
	}

	

	// 最短路径（Dijkstra算法）
	public String shortestPath(int start, int end) {
		if (start == end) {
			return "现在处在目的地";
		}
		int n = this.vertexCount(); // 图的顶点数
		int[] dist = new int[n]; // 最短路径长度
		int[] path = new int[n]; // 最短路径的终点的前一个顶点
		boolean[] vset = new boolean[n]; // 已求出最短路径的顶点集合，初值全为false
		vset[start] = true; // 标记源点vi在集合S中。若i越界，Java抛出序号越界异常
		for (int j = 0; j < n; j++) // 初始化dist和path数组
		{
			dist[j] = this.weight(start, j);
			path[j] = (j != start && dist[j] < MAX_WEIGHT) ? start : -1;
		}

		for (int j = (start + 1) % n; j != start; j = (j + 1) % n) // 寻找从vi到vj的最短路径，vj在V-S集合中
		{
			int mindist = MAX_WEIGHT, min = 0; // 求路径长度最小值及其下标
			for (int k = 0; k < n; k++)
				if (!vset[k] && dist[k] < mindist) {
					mindist = dist[k]; // 路径长度最小值
					min = k; // 路径长度最小值下标
				}
			if (mindist == MAX_WEIGHT) // 若没有其他最短路径则算法结束； 此语句对非连通图必需
				break;
			vset[min] = true; // 确定一条最短路径的终点min并入集合S
			for (int k = 0; k < n; k++)
				// 调整从vi到V-S中其他顶点的最短路径及长度
				if (!vset[k] && this.weight(min, k) < MAX_WEIGHT
						&& dist[min] + this.weight(min, k) < dist[k]) {
					dist[k] = dist[min] + this.weight(min, k);// 用更短路径替换
					path[k] = min; // 最短路径经过min顶点
				}
		}
		// 显示最短路径经过顶点
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
		int n = this.vertexCount(); // 图的顶点数
		int[] dist = new int[n]; // 最短路径长度
		int[] path = new int[n]; // 最短路径的终点的前一个顶点
		boolean[] vset = new boolean[n]; // 已求出最短路径的顶点集合，初值全为false
		vset[start] = true; // 标记源点vi在集合S中。若i越界，Java抛出序号越界异常
		for (int j = 0; j < n; j++) // 初始化dist和path数组
		{
			dist[j] = this.weight(start, j);
			path[j] = (j != start && dist[j] < MAX_WEIGHT) ? start : -1;
		}

		for (int j = (start + 1) % n; j != start; j = (j + 1) % n) // 寻找从vi到vj的最短路径，vj在V-S集合中
		{
			int mindist = MAX_WEIGHT, min = 0; // 求路径长度最小值及其下标
			for (int k = 0; k < n; k++)
				if (!vset[k] && dist[k] < mindist) {
					mindist = dist[k]; // 路径长度最小值
					min = k; // 路径长度最小值下标
				}
			if (mindist == MAX_WEIGHT) // 若没有其他最短路径则算法结束； 此语句对非连通图必需
				break;
			vset[min] = true; // 确定一条最短路径的终点min并入集合S
			for (int k = 0; k < n; k++)
				// 调整从vi到V-S中其他顶点的最短路径及长度
				if (!vset[k] && this.weight(min, k) < MAX_WEIGHT
						&& dist[min] + this.weight(min, k) < dist[k]) {
					dist[k] = dist[min] + this.weight(min, k);// 用更短路径替换
					path[k] = min; // 最短路径经过min顶点
				}
		}
		// 显示
		for (int j = 0; j < n; j++) {
			if (j != start) {
				String pathString = "";
				for (int k = path[j]; k != j && k != -1; k = path[k]) {
					pathString = this.vertex[k].name + pathString;
				}
				if (end == j) {
					return pathString = ""
							+ (dist[j] == MAX_WEIGHT ? "无穷" : dist[j]);
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
