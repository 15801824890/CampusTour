package resource;

public abstract class AbstractGraph<T> // 抽象图类，T表示顶点元素类型
{
	protected static final int MAX_WEIGHT = 99999;

	protected SeqList<T> vertexlist; // 顶点顺序表，存储图的顶点集合

	public AbstractGraph(int length) // 构造空图，顶点数为0，length指定顶点顺序表容量
	{
		this.vertexlist = new SeqList<T>(length); // 构造容量为length的空顺序表。
	}

	public AbstractGraph() // 构造空图，顶点数为0
	{
		this(10); // 顺序表默认容量为10
	}

	public int vertexCount() // 返回图的顶点数
	{
		return this.vertexlist.size();
	}

	public T getVertex(int i) // 返回顶点vi元素
	{
		return this.vertexlist.get(i);
	}

	// 返回vi在vj后的后继邻接顶点序号 ；若j=-1，返回vi的第一个邻接顶点序号；若不存在后继邻接顶点，返回-1。
	protected abstract int next(int i, int j);

	// 图的深度优先搜索遍历
	public void DFSTraverse(int i) // 非连通图的一次深度优先搜索遍历，从顶点vi出发
	{
		boolean[] visited = new boolean[this.vertexCount()]; // 访问标记数组，元素初值为false，表示未被访问
		int j = i;
		do {
			if (!visited[j]) // 若顶点vj未被访问。若i越界，Java将抛出数组下标序号越界异常
			{
				System.out.print("{ ");
				this.depthfs(j, visited); // 从顶点vj出发的一次深度优先搜索
				System.out.print("} ");
			}
			j = (j + 1) % this.vertexCount(); // 在其他连通分量中寻找未被访问顶点
		} while (j != i);
		System.out.println();
	}

	// 从顶点vi出发的一次深度优先搜索，遍历一个连通分量；visited指定访问标记数组。递归算法
	private void depthfs(int i, boolean[] visited) {
		System.out.print(this.getVertex(i) + " "); // 访问顶点vi
		visited[i] = true; // 设置访问标记
		for (int j = this.next(i, -1); j != -1; j = this.next(i, j))
			// j依次获得vi的所有邻接顶点序号
			if (!visited[j]) // 若邻接顶点vj未被访问
				depthfs(j, visited); // 从vj出发的深度优先搜索遍历，递归调用
	}

}