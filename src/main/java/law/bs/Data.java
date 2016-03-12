package law.bs;

public class Data {
	//最大节点数
	public static int max = 100;
	//
	public static int[][] pos = new int[max][2];
	//
	public static int n = 0;
	//路径
	public static int[] path = new int[max];
	//初始化
	static{
		for(int i=0;i<max;i++)
			path[i] = -1;
	}
}
