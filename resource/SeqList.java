package resource;

import java.awt.Label;

public class SeqList<T> {
	protected Object[] element; // 对象数组，保护成员
	protected int n; // 顺序表元素个数（长度）

	public SeqList(int length) // 构造容量为length的空表
	{
		this.element = new Object[length]; // 申请数组的存储空间，元素为null。
		this.n = 0;
	}

	public SeqList() // 创建默认容量的空表，构造方法重载
	{
		this(64); // 调用本类已声明的指定参数列表的构造方法
	}

	public SeqList(T[] values) // 构造顺序表，由values数组提供元素，忽略其中空对象
	{
		this(values.length); // 创建容量为values.length的空表
		// 若values==null，用空对象调用方法，Java抛出空对象异常NullPointerException
		for (int i = 0; i < values.length; i++)
			// 复制数组元素，O(n)
			this.element[i] = values[i]; // 对象引用赋值
		this.n = element.length;

	}

	public boolean isEmpty() // 判断顺序表是否空，若空返回true，O(1)
	{
		return this.n == 0;
	}

	public int size() // 返回顺序表元素个数，O(1)
	{
		return this.n;
	}

	// 存取操作
	public T get(int i) // 返回第i个元素，0≤i<n。若i越界，返回null。O(1)
	{
		if (i >= 0 && i < this.n)
			return (T) this.element[i]; // 返回数组元素引用的对象，传递对象引用
		return null;
	}

	// 设置第i个元素为x，0≤i<n。若i越界，抛出序号越界异常；若x==null，抛出空对象异常。O(1)
	public void set(int i, T x) {
		if (x == null)
			throw new NullPointerException("x==null"); // 抛出空对象异常
		if (i >= 0 && i < this.n)
			this.element[i] = x;
		else
			throw new java.lang.IndexOutOfBoundsException(i + "");// 抛出序号越界异常
	}

	public String toPreviousString() {
		return "";
	}

	// 顺序表的插入操作
	// 插入x作为第i个元素，x!=null，返回x序号。若x==null，抛出空对象异常。O(n)
	// 对序号i采取容错措施，若i<0，插入x在最前；若i>n，插入x在最后
	public int insert(int i, T x) {
		if (x == null)
			throw new NullPointerException("x==null"); // 抛出空对象异常
		if (i < 0)
			i = 0; // 插入位置i容错，插入在最前
		if (i > this.n)
			i = this.n; // 插入在最后
		Object[] source = this.element; // 数组变量引用赋值，source也引用element数组
		if (this.n == element.length) // 若数组满，则扩充顺序表的数组容量
		{
			this.element = new Object[source.length * 2]; // 重新申请一个容量更大的数组
			for (int j = 0; j < i; j++)
				// 复制当前数组前i-1个元素
				this.element[j] = source[j];
		}
		for (int j = this.n - 1; j >= i; j--)
			// 从i开始至表尾的元素向后移动，次序从后向前
			this.element[j + 1] = source[j];
		this.element[i] = x;
		this.n++;
		return i; // 返回x序号
	}

	public int insert(T x) // 顺序表尾插入x元素，返回x序号。成员方法重载
	{
		return this.insert(this.n, x); // 插入操作中，this.n加1
	}

}
