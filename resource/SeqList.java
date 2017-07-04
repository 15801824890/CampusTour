package resource;

import java.awt.Label;

public class SeqList<T> {
	protected Object[] element; // �������飬������Ա
	protected int n; // ˳���Ԫ�ظ��������ȣ�

	public SeqList(int length) // ��������Ϊlength�Ŀձ�
	{
		this.element = new Object[length]; // ��������Ĵ洢�ռ䣬Ԫ��Ϊnull��
		this.n = 0;
	}

	public SeqList() // ����Ĭ�������Ŀձ����췽������
	{
		this(64); // ���ñ�����������ָ�������б�Ĺ��췽��
	}

	public SeqList(T[] values) // ����˳�����values�����ṩԪ�أ��������пն���
	{
		this(values.length); // ��������Ϊvalues.length�Ŀձ�
		// ��values==null���ÿն�����÷�����Java�׳��ն����쳣NullPointerException
		for (int i = 0; i < values.length; i++)
			// ��������Ԫ�أ�O(n)
			this.element[i] = values[i]; // �������ø�ֵ
		this.n = element.length;

	}

	public boolean isEmpty() // �ж�˳����Ƿ�գ����շ���true��O(1)
	{
		return this.n == 0;
	}

	public int size() // ����˳���Ԫ�ظ�����O(1)
	{
		return this.n;
	}

	// ��ȡ����
	public T get(int i) // ���ص�i��Ԫ�أ�0��i<n����iԽ�磬����null��O(1)
	{
		if (i >= 0 && i < this.n)
			return (T) this.element[i]; // ��������Ԫ�����õĶ��󣬴��ݶ�������
		return null;
	}

	// ���õ�i��Ԫ��Ϊx��0��i<n����iԽ�磬�׳����Խ���쳣����x==null���׳��ն����쳣��O(1)
	public void set(int i, T x) {
		if (x == null)
			throw new NullPointerException("x==null"); // �׳��ն����쳣
		if (i >= 0 && i < this.n)
			this.element[i] = x;
		else
			throw new java.lang.IndexOutOfBoundsException(i + "");// �׳����Խ���쳣
	}

	public String toPreviousString() {
		return "";
	}

	// ˳���Ĳ������
	// ����x��Ϊ��i��Ԫ�أ�x!=null������x��š���x==null���׳��ն����쳣��O(n)
	// �����i��ȡ�ݴ��ʩ����i<0������x����ǰ����i>n������x�����
	public int insert(int i, T x) {
		if (x == null)
			throw new NullPointerException("x==null"); // �׳��ն����쳣
		if (i < 0)
			i = 0; // ����λ��i�ݴ���������ǰ
		if (i > this.n)
			i = this.n; // ���������
		Object[] source = this.element; // ����������ø�ֵ��sourceҲ����element����
		if (this.n == element.length) // ����������������˳������������
		{
			this.element = new Object[source.length * 2]; // ��������һ���������������
			for (int j = 0; j < i; j++)
				// ���Ƶ�ǰ����ǰi-1��Ԫ��
				this.element[j] = source[j];
		}
		for (int j = this.n - 1; j >= i; j--)
			// ��i��ʼ����β��Ԫ������ƶ�������Ӻ���ǰ
			this.element[j + 1] = source[j];
		this.element[i] = x;
		this.n++;
		return i; // ����x���
	}

	public int insert(T x) // ˳���β����xԪ�أ�����x��š���Ա��������
	{
		return this.insert(this.n, x); // ��������У�this.n��1
	}

}
