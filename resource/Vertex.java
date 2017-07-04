package resource;

public class Vertex<T> {//¥Ê¥¢æ∞µ„–≈œ¢
	int num;
	String name;
	String introduction;
	int dx;
	int dy;

	public Vertex(int num, String name, String introduction, int dx, int dy) {
		this.num = num;
		this.name = name;
		this.dx = dx;
		this.dy = dy;
		this.introduction = introduction;
	}

	public String toString() {
		return "(" + num + "," + name + "," + introduction + ")";
	}

	public int tonum() {
		return num;
	}

	public int todx() {
		return dx;
	}

	public int tody() {
		return dy;
	}

	public String toname() {
		return name;
	}

	public String tointroduction() {
		return introduction;
	}
}
