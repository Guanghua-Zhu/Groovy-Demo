package com.aaron.BasicDemo.CollectionDemo

/**
 * Groovy Range 示例
 */
// Aaron: todo: output 2 blog
class RangeDemo {
    static void main(args) {
        // range1: [0, 5]
        def range1 = 0..5
        assert range1 instanceof Range
        // Range实现了List接口
        assert range1 instanceof List
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
}
