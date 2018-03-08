package sort;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * LearnSort Tester.
 *
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>01/09/2018</pre>
 */
public class LearnSortTest {
    public final static int[] originalArray = {82, 45, 33, 78, 65, 42, 65, 23, 12, 92, 43, 66, 72, 10, 22, 11, 52};
    public final static int[] resultArray = {10, 11, 12, 22, 23, 33, 42, 43, 45, 52, 65, 65, 66, 72, 78, 82, 92};

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: insertSort(int[] array)
     */
    @Test
    public void testInsertSort() throws Exception {
        Assert.assertThat(resultArray, Matchers.equalTo(LearnSort.insertSort(originalArray)));
    }

    /**
     * Method: shellSort(int[] array)
     */
    @Test
    public void testShellSort() throws Exception {
        Assert.assertThat(resultArray, Matchers.equalTo(LearnSort.shellSort(originalArray)));
    }

    /**
     * Method: selectionSort(int[] array)
     */
    @Test
    public void testSelectionSort() throws Exception {
        Assert.assertThat(resultArray, Matchers.equalTo(LearnSort.selectionSort(originalArray)));
    }

    /**
     * Method: heapSort(int[] array)
     */
    @Test
    public void testHeapSort() throws Exception {
        Assert.assertThat(resultArray, Matchers.equalTo(LearnSort.heapSort(originalArray)));
    }

    /**
     * Method: bubbleSort(@NotNull int[] array)
     */
    @Test
    public void testBubbleSort() throws Exception {
        Assert.assertThat(resultArray, Matchers.equalTo(LearnSort.bubbleSort(originalArray)));
    }

    /**
     * Method: quickSort(@NotNull int[] array)
     */
    @Test
    public void testQuickSort() throws Exception {
        Assert.assertThat(resultArray, Matchers.equalTo(LearnSort.quickSort(originalArray)));
    }

    /**
     * Method: mergingSort(int[] array)
     */
    @Test
    public void testMergingSort() throws Exception {
        Assert.assertThat(resultArray, Matchers.equalTo(LearnSort.mergingSort(originalArray)));
    }

    /**
     * Method: radixSort(int[] arr)
     */
    @Test
    public void testRadixSort() throws Exception {
        Assert.assertThat(resultArray, Matchers.equalTo(LearnSort.radixSort(originalArray)));
    }


    /**
     * Method: maxHeapify(int index, @NotNull int[] array)
     */
    @Test
    public void testMaxHeapify() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = LearnSort.getClass().getMethod("maxHeapify", int.class, @NotNull.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: quickSort(@NotNull int[] array, int low, int high)
     */
    @Test
    public void testQuickSort1() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = LearnSort.getClass().getMethod("quickSort", @NotNull.class, int.class, int.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: getPivot(@NotNull int[] array, int low, int high)
     */
    @Test
    public void testGetPivot() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = LearnSort.getClass().getMethod("getPivot", @NotNull.class, int.class, int.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: merge(int[] arr1, int[] arr2)
     */
    @Test
    public void testMerge() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = LearnSort.getClass().getMethod("merge", int[].class, int[].class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: swap(int i, int j, @NotNull int[] array)
     */
    @Test
    public void testSwap() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = LearnSort.getClass().getMethod("swap", int.class, int.class, @NotNull.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

} 
