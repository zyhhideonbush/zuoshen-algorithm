package hash_function;

import java.util.HashMap;

// 设计RandomPool结构
// 【题目】
// 设计一种结构，在该结构中有如下三个功能: 
// insert(key):将某个key加入到该结构，做到不重复加入 
// delete(key):将原本在结构中的某个key移除 
// getRandom(): 等概率随机返回结构中的任何一个key。
// 【要求】 Insert、delete和getRandom方法的时间复杂度都是O(1)
public class C02_RandomPool {

	// public static class Pool<K> {
	// private HashMap<K, Integer> keyIndexMap;
	// private HashMap<Integer, K> indexKeyMap;
	// private int size;

	// public Pool() {
	// this.keyIndexMap = new HashMap<K, Integer>();
	// this.indexKeyMap = new HashMap<Integer, K>();
	// this.size = 0;
	// }

	// public void insert(K key) {
	// if (!this.keyIndexMap.containsKey(key)) {
	// this.keyIndexMap.put(key, this.size);
	// this.indexKeyMap.put(this.size++, key);
	// }
	// }

	// public void delete(K key) {
	// if (this.keyIndexMap.containsKey(key)) {
	// int deleteIndex = this.keyIndexMap.get(key);
	// int lastIndex = --this.size;
	// K lastKey = this.indexKeyMap.get(lastIndex);
	// this.keyIndexMap.put(lastKey, deleteIndex);
	// this.indexKeyMap.put(deleteIndex, lastKey);
	// this.keyIndexMap.remove(key);
	// this.indexKeyMap.remove(lastIndex);
	// }
	// }

	// public K getRandom() {
	// if (this.size == 0) {
	// return null;
	// }
	// int randomIndex = (int) (Math.random() * this.size); // 0 ~ size -1
	// return this.indexKeyMap.get(randomIndex);
	// }

	// }

	public static class Pool<T> {
		private HashMap<T, Integer> valueMap = new HashMap<>();
		private HashMap<Integer, T> indexMap = new HashMap<>();

		public void insert(T t) {
			if (t != null && !valueMap.containsKey(t)) {
				valueMap.put(t, valueMap.size());
				indexMap.put(indexMap.size(), t);
			}
		}

		public void del(T t) {
			if (t == null || !valueMap.containsKey(t)) {
				return;
			}
			T t1 = indexMap.get(indexMap.size() - 1);
			Integer index = valueMap.get(t);
			indexMap.put(index, t1);
			indexMap.remove(indexMap.size() - 1);
			valueMap.put(t1, index);
			valueMap.remove(t);
		}

		public T getRandom() {
			return indexMap.get((int)(Math.random() * indexMap.size()));
		}
	}

	public static void main(String[] args) {
		Pool<String> pool = new Pool<String>();
		pool.insert("zuo");
		pool.insert("cheng");
		pool.insert("yun");
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());

	}

}
