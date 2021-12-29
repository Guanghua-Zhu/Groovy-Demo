package com.aaron.BasicDemo

/**
 * Groovy 判定表达式真/假 示例
 */
class TruthDemo {
    static void main(String[] args){
        /************ 布尔表达式 ************/
        // 布尔值true 表示 真
        assert true
        // 布尔值false 表示 假
        assert !false

        // 布尔表达式直接计算布尔值结果 作为 真/假
        assert 2>1
        assert !(2<1)

        /************ 集合、数组 ************/
        def list1 = ["a", 23]
        def list2 = []
        // 非空集合 表示 真
        assert list1
        // 空集合 表示 假
        assert !list2

        // 非空集合 表示 真
        assert ["Ok":200, "Error": 404]
        // 空集合 表示 假
        def map1 = [:]
        assert !map1

        def array1 = [211,985] as int[]
        def array2 = [] as int[]
        // 非空数组 表示 真
        assert array1
        // 空数组 表示 假
        assert !array2

        /************ 字符、字符串 ************/
        // 非空字符 表示 真
        assert 'A'
        // 空字符 表示 假
        assert !''

        def str1 = "ABC"
        def str2 = ""
        // 非空字符串 表示 真
        assert str1
        // 空字符 表示 假
        assert !str2

        def str3 = "$str1"
        def str4 = "$str2"
        // 非空字符串 表示 真
        assert str3
        // 空字符 表示 假
        assert !str4

        /***************** 数字 *************/
        // 非零 表示 真
        assert 985
        assert 3.14f
        assert 3.14g
        // 零 表示 假
        assert !0
        assert !0g

        /***************** 引用 *************/
        def obj = new Object()
        // 引用非null表示真
        assert obj

        def map2 = null
        // 空指针null 表示 假
        assert !map2

        def map3 = new HashMap()
        // 虽然引用map3不为null, 但其类型为集合
        // 根据集合的真假判定规则, 空集合同样为假
        assert !map3

        /***************** 匹配 *************/
        // 正则匹配成功 表示 真
        assert "Aaron" =~ /Aar/
        // 正则匹配失败 表示 真
        assert !("Aaron" =~ /Bob/)
    }
}
