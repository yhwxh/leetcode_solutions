package algorithms.basic_alg;

import java.util.Random;

public class SortAlgorithm {
    // 排序算法中，最优的排序算法的时间复杂度是 O(nlogn) 的
    // 默认从小到大的排序

    private void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    /**
     * 冒泡排序：O(n^2)
     * 每次把当前元素跟下一个元素比较，如果比
     *
     * @param arr
     */
    public void bubbleSort(int[] arr) {
        // 外层循环可以理解为已经排了几个最大值（或者遍历了几遍数组）
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                }
            }
        }
    }

    /**
     * 选择排序：O(n^2)
     * 每次在剩余元素中选择最小的那个，跟排序好的元素中后面那个元素交换位置
     * 每次排好序的元素放在数组前面
     *
     * @param arr
     */
    public void selectSort(int[] arr) {
        // 判断每个元素要不要更换位置
        for (int i = 0; i < arr.length - 1; i++) {  // 这里遍历到数组最后一个元素
            // 在剩余元素中查找最小值
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    /**
     * 插入排序：O(n^2)
     * 每次把当前元素插入到已经排好序的元素中的合适位置
     * 依次判断当前元素和之前排好序的每个元素进行比较，
     * 如果比排好序元素小就交换位置，否则移动维护已排好序区间右侧的指针
     * <p>
     * 插入排序在比较的时候可以提前终止，所以，理论上会比选择排序好点
     *
     * @param arr
     */
    public void insertSort(int[] arr) {
        // 外层循环可看做已经排好序的个数（或者第几轮遍历）
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                } else {  // 提前终止
                    break;
                }
            }
        }
    }

    /**
     * 希尔排序：O(n^2)
     * 每次把当前元素插入到已经排好序的元素中的合适位置
     *
     * @param arr
     */
    public void shellSort(int[] arr) {

    }

    /**
     * 归并排序：O(n^2)
     * 先递归分解数组元素至一个个单独元素，然后将每层元素合并起来
     * 递归会将数组分成 logn 层，每层合并起来是O(n) 复杂度
     * 所以，整体为 O(nlogn) 的复杂度
     * <p>
     * 归并排序在每层对两部分有序子数组合并的时候，需要重新开辟一个相同大小的数组完成合并
     *
     * @param arr
     */
    public void mergeSort(int[] arr) {
        // 本质是个递归排序过程
        mergeSort(arr, 0, arr.length - 1);
    }

    private void mergeSort(int[] arr, int left, int right) {
        /**
         * 本函数对 arr[left, right] 这个范围的数据进行排序
         */
        if (left >= right) {
            return;
        }
        // 先找到数组的中间位置
        int mid = left + (right - left) / 2;
        // 递归排序
        mergeSort(arr, left, mid);  // 对左半部分递归
        mergeSort(arr, mid + 1, right);  // 对右半部分递归
        // 将两部分合并
        merge(arr, left, mid, right);
    }

    private void merge(int[] arr, int left, int mid, int right) {
        /**
         * 本函数用来将 arr[left, mid] 和 arr[mid+1, right] 两部分合并起来，合并后就对 arr[left,right] 这个区间排好了序
         */
        // 先申请一个空间,用来存储已经排好序的两个子数组
        // 这里有必要申请一个新的空间，因为虽然逻辑上是一个数组拆出两个子数组，但是物理上只是同一个数组的不同区间范围
        // 所以，如果直接遍历赋值，就会同时影响子数组，导致混乱
        int[] aux = new int[right - left + 1];
        // 用两个子数组填充新空间
        for (int i = left; i <= right; i++) {
            // 注意aux的索引和arr[left,right]索引有left的偏移
            aux[i - left] = arr[i];
        }
        // 对两个子数组合并
        // 此时将 aux 看作两个子数组，修改arr[left, right]
        int l = left, r = mid + 1;  // 分布用来记录左边子数组当前要比较的元素，和右边子数组当前要比较的元素
        for (int k = left; k <= right; k++) {  // k 是指向 arr[left, right] 区间要填充的那个索引
            // 要先保证下标在合理范围内
            if (l > mid) {  // 当左边子数组遍历完的时候
                arr[k] = aux[r - left];
                r++;
            } else if (r > right) {  // 当右边子数组遍历完的时候
                arr[k] = aux[l - left];
                l++;
            } else if (aux[l - left] <= aux[r - left]) {
                arr[k] = aux[l - left];
                l++;
            } else {
                arr[k] = aux[r - left];
                r++;
            }
        }
    }

    public void mergeSortBottomUp(int[] arr) {
        /**
         * 自底向上的归并排序：不需要递归，只需要遍历就可以
         * 从最底层开始：最底层会将数组其分成一个一个的元素
         * 然后遍历所有块，两两连续的块进行合并
         * 倒数第二代两个元素一个块
         * 然后遍历该层所有块，两两连续的块合并
         * …………
         * 依次类推，块成2倍增长，块最大不超过数组大小（即只有一个块的时候）
         */
        // 遍历对数组的所有分块情况，从最小的分块1个元素开始，两个两个的递增
        for (int sz = 1; sz <= arr.length; sz = sz * 2) {
            // 遍历层中的每个块,每次跨越两个块才能保证两两合并
            // i 是每个组合的起始位置
            for (int i = 0; i + sz < arr.length; i += 2 * sz) {
                // 对 arr[i, i+sz-1] 和 arr[i+sz, i+2*sz-1] 合并
                int boundary = i + 2 * sz - 1 < arr.length - 1 ? i + 2 * sz - 1 : arr.length - 1;   // 边界取最小值
                merge(arr, i, i + sz - 1, boundary);
            }
        }
    }

    /**
     * 快速排序：O(n^2)
     * 每次把当前元素插入到已经排好序的元素中的合适位置
     *
     * @param arr
     */
    public void quickSort(int[] arr) {

    }


    public static void main(String[] args) {
        SortAlgorithm sortAlgorithm = new SortAlgorithm();
        // function test example
        int[] test = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        // performance test example
        int[] testPerf = generateTestSamples(100000, false);

        // 测试排序
        Long begin = System.currentTimeMillis();
        sortAlgorithm.mergeSortBottomUp(testPerf);
        Long end = System.currentTimeMillis();
        System.out.println("排序用时：" + (end - begin) / 1000.0 + " s");

        System.out.print("排序功能测试：");
        sortAlgorithm.mergeSortBottomUp(test);
        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + " ");
        }
        System.out.println();

//        for (int i = 0; i < testPerf.length; i++) {
//            System.out.print(testPerf[i] + " ");
//        }
//        System.out.println();
    }

    private static int[] generateTestSamples(int n, boolean openLog) {
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = (int) (Math.random() * n);
        }
        if (openLog) {
            for (int i = 0; i < n; i++) {
                System.out.print(res[i] + ",");
            }
        }
        return res;
    }
}
