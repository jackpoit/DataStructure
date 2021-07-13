package segmenttree;

public class NumArray2 {
	private int[] sum;     //sum[i]	存前i个元素的和  sum[0]=0
	//sum[i]存的是[0...i-1]的和

	public NumArray2(int[] nums) {
		if (nums.length > 0) {
			sum = new int[nums.length + 1];
			sum[0] = 0;
			for (int i = 1; i < sum.length; i++) {
				sum[i] = sum[i - 1] + nums[i - 1];
			}

		}
	}

	public int sumRange(int left, int right) {

		return sum[right + 1] - sum[left];
	}
}
