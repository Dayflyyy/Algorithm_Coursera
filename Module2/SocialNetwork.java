import java.io.File;
import java.util.Scanner;

public class SocialNetwork {

    // 记录该网络的连通度，初始等于0，最高等于size
    int connectdegree;

    // 网络的规模
    int size;

    // 使用并查集存储该网络,初始化使用-1作为没加入网络的标志
    int net[];
    // 用以存储大小,初始化为 1
    int netsz[];


    public int root(int p) {
        while (net[p] != p) {
            net[p] = net[net[p]];
            p = net[p];
        }
        return p;
    }

    public void union(int p, int q) {
        if (root(p) == root(q)) return;
        else {
            if (net[q] == -1) connectdegree++;
            if (netsz[p] >= netsz[q]) {
                net[q] = root(p);
                netsz[p] += netsz[q];
            } else {
                net[p] = root(q);
                netsz[q] += netsz[p];
            }

        }
    }


    public void update(File file) {
        // 从.txt文件中读入网络更新时间戳，每个时间戳一个新产生的社交对，返回连通度==size的时间戳
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                int p = scanner.nextInt();
                int q = scanner.nextInt();
                union(p, q);
                if (connectdegree == size) System.out.println("All members connected at time " + p + " " + q);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
