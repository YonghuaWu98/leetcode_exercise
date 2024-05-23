package test.javaAPItest;

import java.util.Arrays;

/**
 *  TODO java常用Api测试
 **/
public class Main {

    public static void main(String[] args) {
        /**
         *  TODO System类中的arraycopy()方法
         *  public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
         * src:源数组;
         * srcPos:源数组要复制的起始位置;
         * dest:目的数组;
         * destPos:目的数组放置的起始位置;
         * length:复制的长度, 复制的长度不能超过原来数组的长度，且复制结束后，目的数组的长度不能超过原来的长度
         */
        int[] arr = {1, 2, 3, 4, 5};
        int[] arr1 = {4, 5, 6, 7, 8};
        System.arraycopy(arr, 0, arr1, 1, 4);
        for (int i = 0; i < arr1.length; i++) {
            System.out.println(arr1[i]);
            //4 1 2 3 4
        }
        // TODO java.util.Arrays类中的copyOf()
        // Arrays.copyOf(T[] original,int newLength )：拷贝数组，其内部调用了System.arrayCopy()方法，从下标0开始，
        // 如果超过原数组长度，如果T为基本数据类型会用0进行填充，引用类型将会使用null填充。
        // copyOf()方法中先创建了一个新的长度的空数组，然后调用System.arraycopy()。
        int[] arr2 = {1, 2, 3, 4, 5};
        int[] arr3 = Arrays.copyOf(arr2, 7);
        for (int i = 0; i < arr3.length; i++) {
            System.out.println(arr3[i]);
            //1 2 3 4 5 0 0
        }
        Integer[] arr4 = {1, 2, 3, 4, 5};
        Integer[] arr5 = Arrays.copyOf(arr4, 7);
        for (int i = 0; i < arr5.length; i++) {
            System.out.println(arr5[i]);
            // 2 3 4 5 null null
        }

        /** TODO java.util.Arrays.fill():此方法将指定的数据类型值分配给指定数组的指定范围的每个元素。
         * public static void fill(int[] a, int val): 让数组a中的所有元素都等于val
         * public static void fill(int[] a, int from_Index, int to_Index, int val)：指定数组a的范围填充val，范围左闭右开
         *
         */
        int ar[] = {2, 2, 1, 8, 3, 2, 2, 4, 2};

        // To fill complete array with a particular value
        Arrays.fill(ar, 10);
        System.out.println("Array completely filled" +
                " with 10\n" + Arrays.toString(ar)); //[10, 10, 10, 10, 10, 10, 10, 10, 10]

        // Fill from index 1 to index 4.
        Arrays.fill(ar, 1, 5, 2);

        System.out.println(Arrays.toString(ar)); //[10, 2, 2, 2, 2, 10, 10, 10, 10]

        /**
         * TODO  java.util.Arrays.setAll(): 它通过计算每个元素的函数设置指定数组中的所有元素。
         * TODO java.util.parallelSetAll(): 它通过计算每个元素的函数并行设置指定数组中的所有元素。
         * public static void setAll(int[] arr, IntUnaryOperator g)
         * Parameters :
         *     arr :  Array to which the elements to be set
         *    g : It is a function that accepts index of an array
         * and returns the computed value to that index
         *
         *
         * public static void parallelSetAll(double[] arr, IntToDoubleFunction g)
         * Parameters :
         * arr :  Array to which the elements to be set
         * g : It is a function that accepts index of an array
         * and returns the computed value to that index
         *
         *
         *
         * parallelSetAll（） 与 setAll（）
         *
         * 可以看出，这两个函数产生相同的输出，但 parallelSetAll（） 被认为更快，因为它对数组执行并行更改（即一次），
         * 而 setAll（） 更新数组的每个索引（即一个接一个）。虽然 setAll（） 在较小大小的数组上运行得更快，但当数组大小较大时，
         * parallelSetAll（） 会接管 setAll（）。
         */


    }
}

