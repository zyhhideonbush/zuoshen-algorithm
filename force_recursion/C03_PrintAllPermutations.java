package force_recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

// 打印一个字符串的全部排列，要求不要出现重复的排列
public class C03_PrintAllPermutations {

	// public static ArrayList<String> Permutation(String str) {
	// ArrayList<String> res = new ArrayList<>();
	// if (str == null || str.length() == 0) {
	// return res;
	// }
	// char[] chs = str.toCharArray();
	// process(chs, 0, res);
	// res.sort(null);
	// return res;
	// }

	// public static void process(char[] chs, int i, ArrayList<String> res) {
	// if (i == chs.length) {
	// res.add(String.valueOf(chs));
	// }
	// boolean[] visit = new boolean[26];
	// for (int j = i; j < chs.length; j++) {
	// if (!visit[chs[j] - 'a']) {
	// visit[chs[j] - 'a'] = true;
	// swap(chs, i, j);
	// process(chs, i + 1, res);
	// swap(chs, i, j);
	// }
	// }
	// }

	// public static void swap(char[] chs, int i, int j) {
	// char tmp = chs[i];
	// chs[i] = chs[j];
	// chs[j] = tmp;
	// }

	public static ArrayList<String> Permutation(String str) {
		ArrayList<String> res = new ArrayList<>();
		if (str == null) {
			return res;
		}
		process(str.toCharArray(), res, 0);
		return res;
	}

	private static void process(char[] chars, ArrayList<String> res, int index) {
		if (index == chars.length) {
			res.add(String.valueOf(chars));
			return;
		}
		Set<Character> set = new HashSet<>();
		for (int j = index; j < chars.length; j++) {
			if (!set.contains(chars[j])) {
				set.add(chars[j]);
				swap(chars, j, index);
				process(chars, res, index+1);
				swap(chars, j, index);
			}
		}
	}

	private static void swap(char[] arr, int i, int j) {
		char tem = arr[i];
		arr[i] = arr[j];
		arr[j] = tem;
	}

	public static void main(String[] args) {
		ArrayList<String> permutation = Permutation("12345");
		for (String s : permutation) {
			System.out.println(s);
		}
	}

}
