package resource;

public class Triple {
	public int row; // 行号、列号、元素值，默认访问权限
	public int column;//行号（边的起点序号），列号（终点序号）、元素值（权值）
	public int value;

	// 构造方法，参数指定行号、列号、元素值。若行号、列号为负，则抛出无效参数异常
	public Triple(int row, int column, int value) {
		if (row >= 0 && column >= 0) {
			this.row = row;
			this.column = column;
			this.value = value;
		} else
			throw new IllegalArgumentException("行、列号不能为负数：row=" + row
					+ "，column=" + column);
	}

	public Triple(Triple tri) // 拷贝构造方法，复制一个三元组
	{
		this(tri.row, tri.column, tri.value);
	}

}
