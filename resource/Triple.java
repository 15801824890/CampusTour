package resource;

public class Triple {
	public int row; // �кš��кš�Ԫ��ֵ��Ĭ�Ϸ���Ȩ��
	public int column;//�кţ��ߵ������ţ����кţ��յ���ţ���Ԫ��ֵ��Ȩֵ��
	public int value;

	// ���췽��������ָ���кš��кš�Ԫ��ֵ�����кš��к�Ϊ�������׳���Ч�����쳣
	public Triple(int row, int column, int value) {
		if (row >= 0 && column >= 0) {
			this.row = row;
			this.column = column;
			this.value = value;
		} else
			throw new IllegalArgumentException("�С��кŲ���Ϊ������row=" + row
					+ "��column=" + column);
	}

	public Triple(Triple tri) // �������췽��������һ����Ԫ��
	{
		this(tri.row, tri.column, tri.value);
	}

}
