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
     * 归并排序：O(nlogn)
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
        if (arr[mid+1] < arr[mid])  // 这里是对归并的优化，
            // 当右半部分第一个元素大于左半部分最后一个元素的时候不进行归并动作，因为此时就是有序的
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
            // i 是每对两两组合的起始位置
            for (int i = 0; i + sz < arr.length; i += 2 * sz) {
                // 对 arr[i, i+sz-1] 和 arr[i+sz, i+2*sz-1] 合并
                int boundary = i + 2 * sz - 1 < arr.length - 1 ? i + 2 * sz - 1 : arr.length - 1;   // 边界取最小值
                merge(arr, i, i + sz - 1, boundary);
            }
        }
    }

    /**
     * 快速排序：O(nlogn) ： 20世纪，对世界影响最大的算法之一
     * 先选定一个基准点 pivot 然后将这个基准点放到合适的位置，使得该基准点左侧的所有数据都比他小，右侧所有数据都比他大
     * 然后对左右两部分递归完成这个过程
     *
     * 所以，该方法的关键是如何将 pivot 放到合适的位置：这个子过程叫做 partition
     * 1、通常我们使用数组的第一个元素作为基准点 pivot, 它初始时指向当前数组区间的第一个元素
     * 2、我们的目的是将基准点以后的数据整理成一部分小于 pivot ，一部分大于 pivot 的两个区间
     * 3、定义一个指向两个区间分界点的指针 sep
     * 4、小于 pivot 的区间为 arr[pivot+1,sep] , 大于 pivot 的区间为 arr[sep+1,i-1] (i 为遍历数组的指针当前指的索引)
     * 5、遍历的时候不断保持这个性质就好了
     * 6、当数组遍历完后，会有三个区间，一个区间只有 pivot 这个元素，一个是小于 pivot 的元素，一个是大于 pivot 的元素
     * 7、最后，将 pivot 和索引为 sep 的元素交换位置，就找到了 pivot 的合适位置
     *
     *
     * 快排在这几种情况下会退化成 O(n^2) 的复杂度
     * 1、导致问题的本质是使得元素的划分不平衡
     * 2、当元素近乎有序的时候（解决方案是 随机选取 povit，而不是永远都指定为第一个元素）
     * 3、当元素存在大量重复元素的时候（解决方案是 双路快排、三路快排）
     *
     * @param arr
     */
    public void quickSort(int[] arr) {
        // 这里都是闭区间
        quickSort(arr, 0, arr.length-1);
    }
    private void quickSort(int[] arr, int start, int end){
        /**
         * 对arr[start,end] 区间进行排序
         */
        if (start >= end){
            return;
        }
        // 先进行一次 partition 操作，返回 pivot 的合适索引位置
//        int pivot = partition(arr, start, end);
        int pivot = partitionTwoWays(arr, start, end);
        // 递归小于pivot的区间（这里右端索引不能是pivot，我们不需要把它包括在内）
        quickSort(arr, start, pivot-1);  // 左侧区间的头一个元素的索引不是永远都是0（这里犯了个错误，写成了0）
        quickSort(arr, pivot+1, end);
    }
    private int partition(int[] arr, int left, int right){
        // 优化：随机选取基准点pivot，这样在数据近乎有序的时候会避免分割数据有偏
        int randomIndex = ((int)Math.random())*(right-left+1)+left;
        // 还要将随机元素换到第一个位置
        swap(arr, left, randomIndex);
        int pivot = left;
        int sep = left;   // 两个区间的分界点
        // 从第二个元素开始遍历，第一个被pivot占用了
        // [left+1,sep]<pivot  [sep,i)>=pivot
        for (int i = left + 1; i <= right; i++) {
            if (arr[i] < arr[pivot]){
                swap(arr, i, sep+1);
                sep++;
            }
        }
        // 最后记得把 pivot 放到合适的位置（一定要跟<pivot的区间的最后一个元素交换）
        swap(arr, pivot, sep);
        return sep;
    }

    private int partitionTwoWays(int[] arr, int left, int right) {
        int randomIndex = (int)Math.random()*(right-left+1)+left;
        swap(arr, left, randomIndex);
        int pivot = left;

        // arr[left+1,i) <= pivot ; arr(j,right] >= pivot
        int i = left+1;
        int j = right;
        while (i <= j){
            if (arr[i] < arr[pivot]){
                i++;
            } else if (arr[j] > arr[pivot]){
                j--;
            } else {
                swap(arr, i, j);
                i++;
                j--;
            }
            // 循环结束后，必定是 i 指向 >=pivot 第一个元素的位置，j指向 <=pivot的最后一个元素的位置
        }
        // 这里必须是和j指向的元素交换，因为只能把小于pivot的变量换到pivot的位置，否则性质就不保了
        swap(arr, pivot, j);
        return j;
    }


    public static void main(String[] args) {
        SortAlgorithm sortAlgorithm = new SortAlgorithm();
        // function test example
        int[] test = {5, 3, 4, 7, 1, 2, 2, 7, 3, 6};
        // performance test example
        int[] testPerf = generateTestSamples(5000000, false);

        // 测试排序
        Long begin = System.currentTimeMillis();
        sortAlgorithm.quickSort(testPerf);
        Long end = System.currentTimeMillis();
        System.out.println("排序用时：" + (end - begin) / 1000.0 + " s");

        System.out.print("排序功能测试：");
        sortAlgorithm.quickSort(test);
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
