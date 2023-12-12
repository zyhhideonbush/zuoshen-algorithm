package hash_function;

// KMP算法解决的问题 
// 字符串str1和str2，str1是否包含str2，如果包含返回str2在str1中开始的位置。 如何做到时间复杂度O(N)完成?
public class C05_KMP {

	public static int getIndexOf(String s, String m) {
		if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
			return -1;
		}
		char[] str1 = s.toCharArray();
		char[] str2 = m.toCharArray();
		int i1 = 0;
		int i2 = 0;
		int[] next = getNextArray(str2);
		while (i1 < str1.length && i2 < str2.length) {
			if (str1[i1] == str2[i2]) {
				i1++;
				i2++;
			} else if (next[i2] == -1) {
				i1++;
			} else {
				i2 = next[i2];
			}
		}
		return i2 == str2.length ? i1 - i2 : -1;
	}

	// public static int[] getNextArray(char[] ms) {
	// if (ms.length == 1) {
	// return new int[] { -1 };
	// }
	// int[] next = new int[ms.length];
	// next[0] = -1;
	// next[1] = 0;
	// int i = 2;
	// int cn = 0;
	// while (i < next.length) {
	// if (ms[i - 1] == ms[cn]) {
	// next[i++] = ++cn;
	// } else if (cn > 0) {
	// cn = next[cn];
	// } else {
	// next[i++] = 0;
	// }
	// }
	// return next;
	// }

	public static int KMP(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() < s2.length()) {
			return -1;
		}
		char[] chars2 = s2.toCharArray();
		char[] chars1 = s1.toCharArray();

		int[] nexts = getNextArray(chars2);

		int index1 = 0;
		int index2 = 0;

		while (index1 < s1.length() && index2 < s2.length()) {
			if (chars1[index1] == chars2[index2]) {
				index1++;
				index2++;
			} else if (nexts[index2] != -1) {
				index2 = nexts[index2];
			} else {
				index1++;
			}
		}
		return index2 == s2.length() ? index1 - index2 : -1;
	}

	private static int[] getNextArray(char[] arr) {
		int[] nexts = new int[arr.length];
		nexts[0] = -1;
		if (arr.length == 1) {
			return nexts;
		}
		nexts[1] = 0;
		int cn = 0;
		int i = 2;
		while (i < arr.length) {
			if (arr[i - 1] == arr[cn]) {
				nexts[i++] = ++cn;
			} else if (cn > 0) {
				cn = nexts[cn];
			} else {
				nexts[i++] = 0;
			}
		}
		return nexts;
	}

	public static void main(String[] args) {
		String str = "abcabcaababaccc";
		String match = "ababa";
		System.out.println(getIndexOf(str, match));
		System.out.println("-----------------");
		System.out.println(KMP(str, match));
	}

}
