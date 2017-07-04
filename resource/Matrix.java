package resource;

public class Matrix {
	protected int rows, columns; // 矩阵行数、列数
	protected int[][] element; // 二维数组，存储矩阵元素

	public Matrix(int m, int n) // 构造m×n零矩阵
	{
		this.element = new int[m][n]; 
		this.rows = m;
		this.columns = n;
	}

	public Matrix() // 默认构造方法，构造10×10空矩阵
	{
		this(10);
	}

	public Matrix(int n) // 构造n×n零方阵
	{
		this(n, n);
	}

	public Matrix(int m, int n, int[][] value) // 构造m×n矩阵，由value[][]提供元素
	{
		this(m, n);
		for (int i = 0; i < value.length && i < m; i++)
			for (int j = 0; j < value[i].length && j < n; j++)
				this.element[i][j] = value[i][j];
	}

	public int getRows() // 返回矩阵行数
	{
		return this.rows;
	}

	public int getColumns() // 返回矩阵列数
	{
		return this.columns;
	}

	public int get(int i, int j) // 返回矩阵第i行第j列元素。若i、j越界，抛出序号越界异常
	{
		if (i >= 0 && i < this.rows && j >= 0 && j < this.columns)
			return this.element[i][j];
		throw new IndexOutOfBoundsException("i=" + i + "，j=" + j);
	}

	public void set(int i, int j, int x) // 设置矩阵第i行第j列元素为x。若i、j越界，抛出序号越界异常
	{
		if (i >= 0 && i < this.rows && j >= 0 && j < this.columns)
			this.element[i][j] = x;
		else
			throw new IndexOutOfBoundsException("i=" + i + "，j=" + j);
	}


	//设置矩阵为m行n列。若参数指定行列数较大，则将矩阵扩容，并复制原矩阵元素。
	public void setRowsColumns(int m, int n) {
		// TODO Auto-generated method stub
		if (m > 0 && n > 0) {
			if (m > this.element.length || n > this.element[0].length) { // 参数指定的行数或列数较大时，扩充二维数组容量
				int[][] source = this.element;
				this.element = new int[m * 2][n * 2]; // 重新申请二维数组空间，元素初值为0
				for (int i = 0; i < this.rows; i++)
					// 复制原二维数组元素
					for (int j = 0; j < this.columns; j++)
						this.element[i][j] = source[i][j];
			}
			this.rows = m;
			this.columns = n;
		} else
			throw new IllegalArgumentException("矩阵行列数不能≤0，m=" + m + "，n=" + n);
	}

}
