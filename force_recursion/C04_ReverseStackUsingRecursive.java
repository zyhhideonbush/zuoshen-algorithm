package force_recursion;

import java.util.Stack;

// 给你一个栈，请你逆序这个栈，不能申请额外的数据结构，只能使用递归函数。 如何实现?
public class C04_ReverseStackUsingRecursive {

	// public static void reverse(Stack<Integer> stack) {
	// if (stack.isEmpty()) {
	// return;
	// }
	// int i = getAndRemoveLastElement(stack);
	// reverse(stack);
	// stack.push(i);
	// }

	// public static int getAndRemoveLastElement(Stack<Integer> stack) {
	// int result = stack.pop();
	// if (stack.isEmpty()) {
	// return result;
	// } else {
	// int last = getAndRemoveLastElement(stack);
	// stack.push(result);
	// return last;
	// }
	// }

	public static void reverse(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			return;
		}
		Integer pop = getAndRemoveLastNode(stack);
		reverse(stack);
		stack.push(pop);
	}

	private static Integer getAndRemoveLastNode(Stack<Integer> stack) {
		if (stack.size() == 1) {
			return stack.pop();
		}
		Integer pop1 = stack.pop();
		Integer pop2 = getAndRemoveLastNode(stack);
		stack.push(pop1);
		return pop2;
	}


	public static void main(String[] args) {
		Stack<Integer> test = new Stack<Integer>();
		test.push(1);
		test.push(2);
		test.push(3);
		test.push(4);
		test.push(5);
		reverse(test);
		while (!test.isEmpty()) {
			System.out.println(test.pop());
		}

	}

}
