package com.aaron.BasicDemo.CollectionDemo

/**
 * Groovy Array 示例
 */
// Aaron: todo: output 2 blog
class ArrayDemo {
    static void main() {
        // 通过显式变量类型声明字符串数组
        String[] array1 = ['Aaron','Tom']
        assert array1.size() == 2
        assert array1 instanceof String[]
        assert !(array1 instanceof List)
        // 通过索引下标访问数组
        assert array1[0] == 'Aaron'
        // 与List类似，支持负数索引
        assert array1[-1] == 'Tom'
        // 通过索引下标修改元素
        array1[1] = "Tony"
        assert array1 == ['Aaron','Tony']

        // 通过as运算符强制类型为int数组
        def array2 = [123,44,77] as int[]
        assert array2 instanceof int[]
        assert array2.size() == 3

        // 支持Java风格的数组初始化
        def array3 = new int[] {110, 119, 120, 114}
        assert array3[2] == 120
    }
}