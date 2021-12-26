package com.aaron.BasicDemo

/**
 * Groovy 集合 示例
 */
class CollectionDemo {
    static void main(args) {
        testRange()
        testArray()
    }

    /**
     * Range 范围测试
     */
    static void testRange() {
        // range1: [0, 5]
        def range1 = 0..5
        assert range1 instanceof Range
        // contains: 检测某个元素是否存在于范围中
        assert range1.contains(0)
        assert range1.contains(3)
        assert range1.contains(5)
        assert range1.size() == 6

        def sum = 0
        // each: 对范围中每个元素执行闭包
        range1.each { e -> sum += e }
        // sum = 0+1+2+3+4+5 = 15
        assert sum == 15

        // range2: [0, 5)
        def range2 = 0..<5
        assert range2.contains(5) == false

        // 日期范围
        def today = new Date()
        def yesterday = today - 1
        assert (yesterday..today).size() == 2

        // 字符范围
        def range3 = 'a'..'c'
        assert range3.contains("b")

        // 反向范围
        def range4 = 4..<1
        assert range4.toList() == [4, 3, 2]
    }

    /**
     * Array 数组测试
     */
    static void testArray() {
        // 通过显式变量类型声明字符串数组
        String[] array1 = ['Aaron','Tom']
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

    }
}