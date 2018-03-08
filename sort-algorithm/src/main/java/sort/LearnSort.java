package sort;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;

/**
 * http://blog.csdn.net/qy1387/article/details/7752973
 * http://mp.weixin.qq.com/s/deUy_VPJ2m6BFbrEZp9DKg
 *
 * @author KronChan
 * @version 1.0
 * @since <pre>2017/12/25 16:17</pre>
 */
public class LearnSort {
    public final static int[] originalArray = {82, 45, 33, 78, 65, 42, 65, 23, 12, 92, 43, 66, 72, 10, 22, 11, 52};


    /**
     * 插入排序
     * <p>
     * 1. 从第一个元素开始，该元素可以认为已经被排序
     * 2. 取出下一个元素，在已经排序的元素序列中从后向前扫描
     * 3. 如果该元素（已排序）大于新元素，将该元素移到下一位置
     * 4. 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
     * 5. 将新元素插入到该位置后
     * 6. 重复步骤2~5
     *
     * @param array 待排序数组
     * @return int[]
     */
    public static int[] insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;
            for (; j >= 0 && array[j] > temp; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = temp;
        }
        return array;
    }

    /**
     * shell sort
     * <p>
     * 1. 选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；（一般初次取数组半长，之后每次再减半，直到增量为1）
     * 2. 按增量序列个数k，对序列进行k 趟排序；
     * 3. 每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。
     * 仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     *
     * @param array array
     * @return int[] 排序后的array
     */
    public static int[] shellSort(int[] array) {
        int len = array.length;
        // 分组，步长
        for (int gap = len >> 1; gap != 0; gap >>= 1) {
            for (int i = 0; i < gap; i++) {
                // 对组进行插入排序
                for (int j = i + gap; j < len; j += gap) {
                    // 组第一个元素
                    int index = j - gap;
                    // 找到当前组的例外一个元素
                    int temp = array[j];
                    // 进行比较
                    for (; index >= 0 && temp < array[index]; index -= gap) {
                        // 交换
                        array[index + gap] = array[index];
                    }
                    array[index + gap] = temp;
                }
            }
        }
        return array;
    }

    /**
     * 选择排序，
     * 直接选择排序
     * <p>
     * 1. 在要排序的一组数中，选出最小的一个数与第一个位置的数交换；
     * 2. 然后在剩下的数当中再找最小的与第二个位置的数交换
     * 3. 回到步骤1，如此循环直到倒数第二个数和最后一个数比较为止。
     *
     * @param array 需要排序的数组
     * @return int[] 排序好的数组
     */
    public static int[] selectionSort(int[] array) {
        int len = array.length;
        for (int i = 0; i < len; i++) {
            // 记下最终交换的索引
            int position = i;
            // 记下当前轮次最小值
            int temp = array[i];
            for (int j = i + 1; j < len; j++) {
                if (temp > array[j]) {
                    temp = array[j];
                    position = j;
                }
            }
            if (position > i) {
                // 交换
                array[position] = array[i];
                array[i] = temp;
            }
        }
        return array;
    }

    /**
     * 堆排序
     *
     * @param array array
     * @return int[]
     */
    public static int[] heapSort(int[] array) {
        for (int i = array.length; i > 0; i--) {
            //  每次交换后重新构造堆
            maxHeapify(i, array);
            // 将堆顶和堆的最后一个数值进行交换，
            swap(0, i - 1, array);
        }
        return array;
    }

    /**
     * 初始化大顶堆，使得子节点永远小于父节点，并且记录最大值，然后将堆顶和堆的最后一个数值进行交换，
     * 对于堆节点的访问：
     * 父节点i的左子节点在位置：(2*i+1);
     * 父节点i的右子节点在位置：(2*i+2);
     * 子节点i的父节点在位置：floor((i-1)/2);
     *
     * @param index index
     * @param array array
     */
    private static void maxHeapify(int index, int[] array) {
        int parentIdx = index >> 1;
        for (; parentIdx >= 0; parentIdx--) {
            if (parentIdx << 1 >= index) {
                continue;
            }
            //左子节点索引
            int left = parentIdx * 2;
            //右子节点索引，如果没有右节点，默认为左节点索引
            int right = (left + 1) >= index ? left : (left + 1);
            // 找到较大的子节点
            int maxChildId = array[left] >= array[right] ? left : right;
            //如果子节点中的较大值比父节点大，交换父节点与左右子节点中的最大值
            if (array[maxChildId] > array[parentIdx]) {
                swap(maxChildId, parentIdx, array);
            }
        }
    }

    /**
     * 冒泡排序
     * 1.比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * 2.对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
     * 3.针对所有的元素重复以上的步骤，除了最后一个。
     * 4.持续每次对越来越少的元素重复上面的步骤1~3，直到没有任何一对数字需要比较。
     *
     * @param array array
     * @return int[]
     */
    public static int[] bubbleSort(@NotNull int[] array) {
        int len = array.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (array[i] > array[j]) {
                    swap(i, j, array);
                }
            }
        }
        return array;
    }

    /**
     * 快速排序
     *
     * @param array array
     * @return int[]
     */
    public static int[] quickSort(@NotNull int[] array) {
        quickSort(array, 0, array.length - 1);
        return array;
    }

    /**
     * 快速排序 》递归
     * 递归满足条件：
     * low < high
     *
     * @param array array
     * @param low   最小边界
     * @param high  最大边界
     */
    private static void quickSort(@NotNull int[] array, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot = getPivot(array, low, high);
        quickSort(array, low, pivot - 1);
        quickSort(array, pivot + 1, high);
    }

    /**
     * 返回基准的索引
     * 1. i = low;L = low; R = high 将基准数挖出形成第一个坑a[i]。
     * 2. R-- 向前找到比基准a[i]小的值，找到后挖出此数填前一个坑a[i]中。
     * 3. L++ 向后找比基准大的数，找到后也挖出此数填到前一个坑a[j]中。
     * 4. 重复 2 、 3 步操作，知道 L == R ，返回基准的索引。
     *
     * @param array array
     * @param low   最小边界
     * @param high  最大边界
     * @return 索引
     */
    private static int getPivot(@NotNull int[] array, int low, int high) {
        // 定义一个基准
        int pivotValue = array[low];
        while (low < high) {
            // 找到比基准小的放在左边
            while (low < high && array[high] >= pivotValue) {
                high--;
            }
            array[low] = array[high];
            // 找到比基准大的放在右边
            while (low < high && array[low] <= pivotValue) {
                low++;
            }
            array[high] = array[low];
        }
        // 找到基准的索引位置，并且返回
        array[low] = pivotValue;
        return low;
    }

    /**
     * 归并排序
     *
     * @param array array
     * @return int[]
     */
    public static int[] mergingSort(int[] array) {
        // 拆分到最小单元的时候终止递归操作。
        if (array.length <= 1) {
            return array;
        }
        // 每次拆分的长度
        int segmentLen = array.length >> 1;
        int[] leftArr = Arrays.copyOfRange(array, 0, segmentLen);
        int[] rightArr = Arrays.copyOfRange(array, segmentLen, array.length);
        return merge(mergingSort(leftArr), mergingSort(rightArr));
    }

    /**
     * 合并左右两个数组
     *
     * @param arr1 数组1
     * @param arr2 数组2
     * @return int[]
     */
    private static int[] merge(int[] arr1, int[] arr2) {
        int i = 0, j = 0, k = 0;
        // 创建一个新数组
        int[] result = new int[arr1.length + arr2.length];
        // 将两个数据中的元素进行一一比较，较小的放入新的数组中
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }
        // 经过上一步骤后，可能有一个数组的数没有放完，继续在后面添加上
        while (i < arr1.length) {
            result[k++] = arr1[i++];
        }
        while (j < arr2.length) {
            result[k++] = arr2[j++];
        }
        return result;
    }

    /**
     * 基数排序（LSD 从低位开始）
     * <p>
     * 基数排序适用于：
     * (1)数据范围较小，建议在小于1000
     * (2)每个数值都要大于等于0
     * <p>
     * ①. 取得数组中的最大数，并取得位数；
     * ②. arr为原始数组，从最低位开始取每个位组成radix数组；
     * ③. 对radix进行计数排序（利用计数排序适用于小范围数的特点）；
     *
     * @param arr 待排序数组
     * @return int[] 排序好的数组
     */
    public static int[] radixSort(@NotNull int[] arr) {
        int len = arr.length;
        if (len <= 1) {
            return arr;
        }
        //取得数组中的最大数，并取得位数
        int max = 0;
        for (int anArr : arr) {
            if (max < anArr) {
                max = anArr;
            }
        }
        int maxDigit = 1;
        while (max / 10 > 0) {
            maxDigit++;
            max = max / 10;
        }
        System.out.println("maxDigit: " + maxDigit);
        //申请一个桶空间
        int[][] buckets = new int[10][arr.length];
        int base = 10;
        //从低位到高位，对每一位遍历，将所有元素分配到桶中
        for (int i = 0; i < maxDigit; i++) {
            //存储各个桶中存储元素的数量
            int[] bktLen = new int[10];
            //分配：将所有元素分配到桶中
            for (int j = 0; j < arr.length; j++) {
                int whichBucket = (arr[j] % base) / (base / 10);
                buckets[whichBucket][bktLen[whichBucket]] = arr[j];
                bktLen[whichBucket]++;
            }
            //收集：将不同桶里数据挨个捞出来,为下一轮高位排序做准备,由于靠近桶底的元素排名靠前,因此从桶底先捞
            int k = 0;
            for (int b = 0; b < buckets.length; b++) {
                for (int p = 0; p < bktLen[b]; p++) {
                    arr[k++] = buckets[b][p];
                }
            }
            base *= 10;
        }
        return arr;
    }

    /**
     * 交换
     *
     * @param i     索引1
     * @param j     索引2
     * @param array array
     */
    private static void swap(int i, int j, @NotNull int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
