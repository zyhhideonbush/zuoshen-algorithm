package hash_function;

import java.util.Date;
import java.util.Random;

// Manacher算法解决的问题: 字符串str中，最长回文子串的长度如何求解? 如何做到时间复杂度O(N)完成?
public class C06_Manacher {

	// public static char[] manacherString(String str) {
	// char[] charArr = str.toCharArray();
	// char[] res = new char[str.length() * 2 + 1];
	// int index = 0;
	// for (int i = 0; i != res.length; i++) {
	// res[i] = (i & 1) == 0 ? '#' : charArr[index++];
	// }
	// return res;
	// }

	// public static int maxLcpsLength(String str) {
	// if (str == null || str.length() == 0) {
	// return 0;
	// }
	// char[] charArr = mapString(str);
	// int[] pArr = new int[charArr.length];
	// int C = -1;
	// int R = -1;
	// int max = Integer.MIN_VALUE;
	// for (int i = 0; i != charArr.length; i++) {
	// pArr[i] = R > i ? Math.min(pArr[2 * C - i/* c - (i - c) */], R - i) : 1;
	// while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
	// if (charArr[i + pArr[i]] == charArr[i - pArr[i]])
	// pArr[i]++;
	// else {
	// break;
	// }
	// }
	// if (i + pArr[i] > R) {
	// R = i + pArr[i];
	// C = i;
	// }
	// max = Math.max(max, pArr[i]);
	// }
	// return max - 1;
	// }

	// 法一：暴力解法
	public static int maxLcpsLength1(String str) {
		char[] arr = mapString(str);
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			int r = 1;
			while (i - r >= 0 && i + r < arr.length && arr[i - r] == arr[i + r]) {
				r++;
			}
			count = Math.max(count, 2 * r - 1);
		}
		return count / 2;
	}

	// 法二：Manacher
	public static int maxLcpsLength2(String str) {
		char[] charArr = mapString(str);
		int[] diameterArr = new int[charArr.length];

		int borderRight = -1;
		int borderCenter = -1;

		for (int i = 0; i < diameterArr.length; i++) {
			if (borderRight <= i) { // 暴力扩充
				int r = 1;
				while (i - r >= 0 && i + r < charArr.length && charArr[i - r] == charArr[i + r]) {
					r++;
				}
				diameterArr[i] = r * 2 - 1;
				borderRight = Math.max(i + r, borderRight);
				borderCenter = Math.max(i, borderCenter);
			} else {
				int borderLeft = 2 * borderCenter - borderRight;
				int left = 2 * borderCenter - i;

				if (left - diameterArr[left] > borderLeft) {
					diameterArr[i] = diameterArr[left];
				} else if (left - diameterArr[left] == borderLeft) { // 有优化的暴力扩充
					int r = borderRight - i + 1;
					while (i - r >= 0 && i + r < charArr.length && charArr[i - r] == charArr[i + r]) {
						r++;
					}
					diameterArr[i] = r * 2 - 1;
					borderRight = Math.max(i + r, borderRight);
					borderCenter = Math.max(i, borderCenter);
				} else {
					diameterArr[i] = 2 * (left - borderLeft + 1) - 1;
				}
			}

		}
		int maxDiameter = diameterArr[0];
		for (int i = 1; i < diameterArr.length; i++) {
			maxDiameter = Math.max(maxDiameter, diameterArr[i]);
		}
		return maxDiameter / 2;
	}

	private static char[] mapString(String str) {
		char[] arr = str.toCharArray();
		char[] tem = new char[arr.length * 2 + 1];
		for (int i = 0; i < tem.length; i++) {
			tem[i] = i % 2 == 1 ? arr[i / 2] : '#';
		}
		return tem;
	}

	private static int maxLcpsLength3(char[] arr, int left, int right) {
		if (arr == null || arr.length == 0 || left > right) {
			return 0;
		}
		if (left == right) {
			return 1;
		}
		int res = Math.max(maxLcpsLength3(arr, left + 1, right), maxLcpsLength3(arr, left, right - 1));
		int mid = maxLcpsLength3(arr, left + 1, right - 1);
		return res;
	}


	private static int maxLcpsLength4 (String str) {
		if (str == null || str.isEmpty()) {
			return 0;
		}
		char[] arr = mapString2(str);
		int[] help = new int[arr.length];
		int res = Integer.MIN_VALUE;

		int C = -1;
		int R = -1;
		for (int i = 1; i < arr.length; i++) {
			help[i] = i < R ? Math.min(R - i, help[2 * C - i]) : 1;

			while (i - help[i] > -1 && i + help[i] < arr.length) {
				if (arr[i - help[i]] == arr[i + help[i]]) {
					help[i]++;
				} else {
					break;
				}
			}

			if (i > R) {
				R = i;
				C = i - help[i];
			}
			res = Math.max(help[i], res);
		}
		return res - 1;
	}

	private static char[] mapString2(String str) {
		char[] strChar = str.toCharArray();
		char[] res = new char[str.length() * 2 + 1];
		for (int i = 0; i < res.length; i++) {
			res[i] = i % 2 == 1 ? strChar[i / 2] : '#';
		}
		return res;
	}


	public static void main(String[] args) {
//		String str1 = "abc1234321ab";
//		System.out.println(maxLcpsLength1(str1));
//		System.out.println(maxLcpsLength2(str1));
//		System.out.println(maxLcpsLength4(str1));
//		System.out.println(maxLcpsLength3(str1.toCharArray(), 0, str1.length() - 1));
		String randomString = getRandomString(60000);

		String randomString1 = getRandomString(10000);
		String s1 = "234e234fdgfdsfsdf";
		String exam = randomString + s1 + randomString1;
		long l1 = System.currentTimeMillis();
		System.out.println(exam.contains(s1));
		long l2 = System.currentTimeMillis();
		System.out.println(l2 - l1);

	}

	public static String getRandomString(int length) {
		//定义一个字符串（A-Z，a-z，0-9）；
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		//由Random生成随机数
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; ++i) {
			int number = random.nextInt(str.length());
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}

}
