package cmb2017;

import java.util.LinkedHashSet;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @program: CMBExam
 * @descripition https://www.nowcoder.com/questionTerminal/cbd6ed4d1bf5475288b54ce5abe7ea66
 * @author: wutianxiong
 * @create: 2018-04-01 10:26
 **/

public class CreditCardRecommendation {

    public static void main(String[] args) {
        //用来存储客户推荐关系，行表示推荐人，列表示被推荐人，推荐人->被推荐人关系存在即为true
        boolean[][] userAdj = new boolean[26][26];
        //存储客户，当n=0时，起作用
        Set<Character> userSet = new LinkedHashSet<>();
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        for (int i = 0; i < m; i++) {
            char first = sc.next().charAt(0);
            char two = sc.next().charAt(0);
            char three = sc.next().charAt(0);
            userSet.add(first);
            userSet.add(two);
            userAdj[first - 'A'][two - 'A'] = true;
            if (three != '*') {
                userAdj[first - 'A'][three - 'A'] = true;
                userSet.add(three);
            }
        }
        //客户列表为空时，输出None
        if (m == 0) {
            System.out.println("None");
            return;
        }
        //存储每个客户推荐的客户数目
        int[] count = new int[26];
        //n=0时输出全部
        if (n == 0) {
            userSet.forEach(p -> System.out.print(p + " "));
            return;
        } else {
            for (int i = 0; i < userAdj.length; i++) {
                Queue<Integer> queue = new LinkedBlockingDeque<>();
                queue.add(i);
                //存储所推荐客户是否已经访问
                boolean[] visit = new boolean[26];
                //按照层序遍历的思路统计i的推荐客户数
                while (!queue.isEmpty()) {
                    int start = queue.remove();
                    for (int j = 0; j < userAdj[i].length; j++) {
                        if (userAdj[start][j] && !visit[j]) {
                            count[i]++;
                            visit[j] = true;
                            queue.add(j);
                        }
                    }
                }
            }
        }
        //存储是否有满足条件的客户
        boolean flag = false;
        for (int i = 0; i < count.length; i++) {
            if (count[i] >= n) {
                flag = true;
                System.out.printf("%c ", i + 'A');
            }
        }
        //没有满足条件的
        if (!flag) {
            System.out.println("None");
        }
    }
}
