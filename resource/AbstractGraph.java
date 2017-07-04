package resource;

public abstract class AbstractGraph<T> // ����ͼ�࣬T��ʾ����Ԫ������
{
	protected static final int MAX_WEIGHT = 99999;

	protected SeqList<T> vertexlist; // ����˳����洢ͼ�Ķ��㼯��

	public AbstractGraph(int length) // �����ͼ��������Ϊ0��lengthָ������˳�������
	{
		this.vertexlist = new SeqList<T>(length); // ��������Ϊlength�Ŀ�˳���
	}

	public AbstractGraph() // �����ͼ��������Ϊ0
	{
		this(10); // ˳���Ĭ������Ϊ10
	}

	public int vertexCount() // ����ͼ�Ķ�����
	{
		return this.vertexlist.size();
	}

	public T getVertex(int i) // ���ض���viԪ��
	{
		return this.vertexlist.get(i);
	}

	// ����vi��vj��ĺ���ڽӶ������ ����j=-1������vi�ĵ�һ���ڽӶ�����ţ��������ں���ڽӶ��㣬����-1��
	protected abstract int next(int i, int j);

	// ͼ�����������������
	public void DFSTraverse(int i) // ����ͨͼ��һ��������������������Ӷ���vi����
	{
		boolean[] visited = new boolean[this.vertexCount()]; // ���ʱ�����飬Ԫ�س�ֵΪfalse����ʾδ������
		int j = i;
		do {
			if (!visited[j]) // ������vjδ�����ʡ���iԽ�磬Java���׳������±����Խ���쳣
			{
				System.out.print("{ ");
				this.depthfs(j, visited); // �Ӷ���vj������һ�������������
				System.out.print("} ");
			}
			j = (j + 1) % this.vertexCount(); // ��������ͨ������Ѱ��δ�����ʶ���
		} while (j != i);
		System.out.println();
	}

	// �Ӷ���vi������һ�������������������һ����ͨ������visitedָ�����ʱ�����顣�ݹ��㷨
	private void depthfs(int i, boolean[] visited) {
		System.out.print(this.getVertex(i) + " "); // ���ʶ���vi
		visited[i] = true; // ���÷��ʱ��
		for (int j = this.next(i, -1); j != -1; j = this.next(i, j))
			// j���λ��vi�������ڽӶ������
			if (!visited[j]) // ���ڽӶ���vjδ������
				depthfs(j, visited); // ��vj������������������������ݹ����
	}

}