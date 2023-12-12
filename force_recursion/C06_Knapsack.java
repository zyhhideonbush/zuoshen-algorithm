package force_recursion;

// 给定两个长度都为N的数组weights和values，weights[i]和values[i]分别代表i号物品的重量和价值。
// 给定一个正数bag，表示一个载重bag的袋子，你装的物品不能超过这个重量。返回你能装下最多的价值是多少?
public class C06_Knapsack {

	// public static int maxValue1(int[] weights, int[] values, int bag) {
	// return process1(weights, values, 0, 0, bag);
	// }

	// public static int process1(int[] weights, int[] values, int i, int alreadyWeight, int bag) {
	// 	if (alreadyWeight > bag) {
	// 		return 0;
	// 	}
	// 	// 有bug：
	// 	// if (i == weights.length) {
	// 	// return 0;
	// 	// }
	// 	// return Math.max(
	// 	// process1(weights, values, i + 1, alreadyWeight, bag),
	// 	// values[i] + process1(weights, values, i + 1, alreadyWeight + weights[i],
	// 	// bag));

	// 	// 正确写法：
	// 	return Math.max(process1(weights, values, i + 1, alreadyWeight, bag),
	// 			alreadyWeight + weights[i] <= bag
	// 					? values[i] + process1(weights, values, i + 1, alreadyWeight + weights[i], bag)
	// 					: 0);
	// }

	// TODO:动态规划?
	// public static int maxValue2(int[] c, int[] p, int bag) {
	// int[][] dp = new int[c.length + 1][bag + 1];
	// for (int i = c.length - 1; i >= 0; i--) {
	// for (int j = bag; j >= 0; j--) {
	// dp[i][j] = dp[i + 1][j];
	// if (j + c[i] <= bag) {
	// dp[i][j] = Math.max(dp[i][j], p[i] + dp[i + 1][j + c[i]]);
	// }
	// }
	// }
	// return dp[0][0];
	// }

	// 法一
	public static int maxValue1(int[] weights, int[] values, int bag) {
		return process1(weights, values, bag, 0, 0);
	}

	private static int process1(int[] weights, int[] values, int bag, int alreadyWeight, int index) {
		//
		if (alreadyWeight > bag) {
			return 0;
		}
		if (index >= weights.length) {
			return 0;
		}
		return Math.max(
				alreadyWeight + weights[index] + process1(weights, values, bag, alreadyWeight + weights[index], index + 1),
				alreadyWeight + process1(weights, values, bag, alreadyWeight, index + 1));
	}

	// 法二 TODO:未完成
	public static int maxValue2(int[] weights, int[] values, int bag) {
		return 0;
	}

	public static void main(String[] args) {
		int[] weights = { 3, 2, 4, 7 };
		int[] values = { 5, 6, 3, 19 };
		int bag = 11;
		System.out.println(maxValue1(weights, values, bag));
		System.out.println(maxValue2(weights, values, bag));
	}

}
