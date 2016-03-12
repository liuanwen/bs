package law.bs;

import law.bs.Data;

import java.util.Date;


public class Brain {

	/**
	 * 初始化参数
	 */
	public Brain(){
		
	}
	
	
	public  void makeIt(){
		long t1 = new Date().getTime();
		hs(1);
		Data.path = bestPath;
		System.out.println("最优解 : best = "+best);
		print(bestPath);
		long t2 = new Date().getTime();
		System.out.println("耗时："+(t2-t1)+"ms");
	} 
	
	/*
	 * 回溯法求解
	 */
	//顶点数
	int n = Data.n;
	//最优解的值
	double best = 99999;
	//最优解
	int[] bestPath = new int[n];
	//当前值
	double x = 0;
	//当前解
	int[] path = new int[n];
	
	public  void hs(int k){
		if(k == n){
			double dis = dis(path[n-1], 0);
			x += dis;
			if(x < best){
				best = x;
				copyArr(bestPath, path);
			}
			x -= dis;
		}
		
		//从1开始回溯，以0号顶点为起点
		for(int i=0;i<n;i++){
			if(isMove(i,k)){
				path[k] = i;
				double dis = dis(path[k-1], path[k]);
				x += dis;
				if(x < best)
					hs(k+1);
				x -= dis;
			}
		}
		
	} 
	
	/**
	 * 按照规则，判断下一个点是否可以为b
	 * @param b
	 * @param k
	 * @return
	 */
	public boolean isMove(int b, int k){
		for(int i=0;i<k;i++)
			if(path[i] == b)
				return false;
		return true;
	}
	
	/**
	 * 返回a b 的距离
	 * @param a
	 * @param b
	 * @return
	 */
	public double dis(int a, int b){
		int xa = Data.pos[a][0];
		int ya = Data.pos[a][1];
		int xb = Data.pos[b][0];
		int yb = Data.pos[b][1];
		return Math.sqrt((yb-ya)*(yb-ya)+(xb-xa)*(xb-xa));
	}
	
	/**
	 * 把s复制给t
	 * @param t
	 * @param s
	 */
	public void copyArr(int[] t,int[] s){
		for(int i=0;i<s.length;i++)
			t[i] = s[i];
	}
	
	/**
	 * 打印解
	 */
	public void print(int[] arr){
		for(int i=0;i<arr.length;i++)
			System.out.print(arr[i]+" ");
		System.out.println();
	}
}
