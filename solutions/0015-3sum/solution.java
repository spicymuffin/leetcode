class Solution {
    // array is of non zero length

    private static void swap(int idx1, int idx2, int[] arr) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

    private static void quicksort(int l, int r, int[] arr) {
        if (l >= r)
            return;

        int pivot = arr[l];

        int bigptr = l;
        for (int scanptr = l + 1; scanptr <= r; scanptr++) {
            if (arr[scanptr] < pivot) {
                bigptr++;
                swap(scanptr, bigptr, arr);
            }
        }

        swap(l, bigptr, arr);
        quicksort(l, bigptr - 1, arr);
        quicksort(bigptr + 1, r, arr);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        quicksort(0, nums.length - 1, nums);
        System.out.println(Arrays.toString(nums));

        List<List<Integer>> answer = new ArrayList<>();

        // HashSet<Integer> checked = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            int l = i + 1;
            int r = nums.length - 1;

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // if (checked.contains(nums[i]))
            //     continue;

            // checked.add(nums[i]);

            while (l < r) {
                if (nums[i] + nums[l] + nums[r] > 0) {
                    r--;
                } else if (nums[i] + nums[l] + nums[r] < 0) {
                    l++;
                } else {
                    List<Integer> triplet = new ArrayList<>();
                    triplet.add(nums[i]);
                    triplet.add(nums[l]);
                    triplet.add(nums[r]);
                    answer.add(triplet);
                    while (l < r - 1 && nums[r] == nums[r - 1])
                        r--;
                    while (l + 1 < r && nums[l] == nums[l + 1])
                        l++;
                    r--;
                    l++;
                }
            }
        }

        return answer;
    }
}
