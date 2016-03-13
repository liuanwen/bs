package law.bs.aco;

import law.bs.Data;
import org.junit.Test;

import java.util.*;

/**
 * Created by law on 16/3/13.
 */
public class Aco {

    @Test
    public void test() {
        double[][] map = {
                {0, 1, 0.1, 1},
                {1, 0, 1, 2},
                {0.1, 1, 0, 1},
                {1, 2, 1, 0}
        };
        run(4, map);
    }

    //设置Data的最佳path，供面板调用显示。
    public void makeIt() {
        long t1 = new Date().getTime();
        double[][] cityDis = new double[Data.n][Data.n];
        for (int i = 0; i < Data.n; i++)
            for (int j = 0; j < Data.n; j++) {
                int xa = Data.pos[i][0];
                int ya = Data.pos[i][1];
                int xb = Data.pos[j][0];
                int yb = Data.pos[j][1];
                cityDis[i][j] = Math.sqrt((yb - ya) * (yb - ya) + (xb - xa) * (xb - xa));
            }
        Aco demo = new Aco();
        Data.path = demo.run(Data.n, cityDis);
        long t2 = new Date().getTime();
        System.out.println("耗时：" + (t2 - t1) + "ms");
    }

    //ACO算法逻辑
    public int[] run(int cityCount, double[][] cityDistance) {
        int antCount = 50;
        int cycle = 100;
        double alpha = 1;
        double beta = 2;
        double p = 0.1;
        double q = 1;
        double best = 99999999;
        int[] bestPath = new int[cityCount + 1];
        double[][] delta = new double[cityCount][cityCount];
        initPheromone(delta, cityCount);
        while (cycle > 0) {
            cycle--;
            //迭代m只蚂蚁，从city=0开始
            for (int i = 0; i < antCount; i++) {
                int[] curPath = new int[cityCount + 1];
                int curCity = 0;
                double pathLength = 0;
                List allowed = initAllowed(cityCount);
                while (allowed.size() > 0) {
                    int nextCity = selNextCity(curCity, allowed, delta, alpha, beta, cityDistance);
                    pathLength += cityDistance[curCity][nextCity];
                    int indexNum = cityCount - allowed.size();
                    curPath[indexNum] = nextCity;
                    allowed.remove(new Integer(nextCity));
                    curCity = nextCity;
                }
                //回到起点
                pathLength += cityDistance[curPath[cityCount - 1]][0];
                bestPath[cityCount] = 0;
                //更新最优值
                if (pathLength < best) {
                    best = pathLength;
                    copyArr(bestPath, curPath);
                }
                //留下信息素并更新
                for (int j = 0; j < cityCount; j++) {
                    int pre = curPath[j];
                    int next = curPath[j + 1];
                    delta[pre][next] = (1 - p) * delta[pre][next] + q / pathLength;
                }
            }
        }
        System.out.println("最优长度=" + best);
        return bestPath;
    }

    private int selNextCity(int city, List<Integer> allowed, double[][] delta, double alpha, double beta, double[][] map) {
        double count = 0;
        Map<Integer, Double> cMap = new HashMap<Integer, Double>();
        //计算出当前城市所有的可行边的浓度公式之和
        for (Integer i : allowed) {
            double c = Math.pow(delta[city][i], alpha) * Math.pow(1 / map[city][i], beta);
            cMap.put(i, c);
            count += c;
        }
        //产生随机数
        double random = Math.random();
        //满足随机数的即是要走的边.轮盘赌的方式
        double base = 0;
        Set entrySet = cMap.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            if (base + (Double) entry.getValue() / count > random) {
                return (Integer) entry.getKey();
            } else {
                base += (Double) entry.getValue() / count;
            }
        }
        return -1;
    }

    private void initPheromone(double[][] arr, int n) {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                arr[i][j] = 1;
    }

    private void copyArr(int[] dest, int[] orig) {
        for (int i = 0; i < dest.length; i++)
            dest[i] = orig[i];
    }

    private List initAllowed(int n) {
        List list = new ArrayList();
        for (int i = 1; i < n; i++)
            list.add(i);
        return list;
    }
}
