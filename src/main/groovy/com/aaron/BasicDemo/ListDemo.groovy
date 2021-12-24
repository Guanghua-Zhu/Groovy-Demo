package com.aaron.BasicDemo

/**
 * Groovy 列表 示例
 */
class ListDemo {
    static void main(args) {
        basic()
        likeStack()
        other()
    }

    /**
     * List 列表基本操作
     */
    static void basic() {
        def myList1 = [1,7,2]
        // List的默认实现类为ArrayList
        assert myList1 instanceof ArrayList
        assert myList1.size() == 3

        // 将Range转换为List
        def myList2 = (0..5).toList()
        assert myList2 == [0,1,2,3,4,5]
        // 通过索引下标进行访问、修改
        assert myList2[3] == 3
        myList2[5] = 996
        assert myList2[5] == 996
        // 支持使用负数索引
        // -1表示列表的最后一个元素; -2表示列表倒数第二个元素;以此类推
        assert myList2[-1] == 996
        myList2[4] = 985
        assert myList2[-2] == 985

        // 通过Range进行访问
        def myList3 = ["Aaron","Bob","C","Tony","Tom","Luca"]
        // Range作为索引下标进行访问
        assert myList3[0..2] == ["Aaron","Bob","C"]
        // 反向Range作为索引下标进行访问, 得到的结果列表也是反向的
        assert myList3[2..0] == ["C","Bob","Aaron"]

        /********************** 类型指定 **********************/
        // 通过显式类型声明
        LinkedList myLinkedList1 = [1,3,7]
        assert myLinkedList1 instanceof LinkedList

        // 直接new一个LinkedList实例
        def myLinkedList2 = new LinkedList()
        assert myLinkedList2 instanceof LinkedList

        // 通过as运算符强制类型
        def myLinkedList3 = [] as LinkedList
        assert myLinkedList3 instanceof LinkedList

        /********************** 重载运算符 **********************/
        def myList4 = [996]
        // 添加元素到列表
        myList4 += "Aaron"
        // 列表元素支持异构类型
        assert myList4 == [996, "Aaron"]
        // 添加集合到列表
        myList4 += [true, false]
        assert myList4 == [996, "Aaron", true, false]
        // 从列表中移除元素
        myList4 -= "Aaron"
        // 从列表中移除集合
        myList4 -= [true, 996]
        assert myList4 == [false]
        // 向元素尾部追加元素
        myList4 << "Bob" << 17
        assert myList4 == [false, "Bob", 17]
    }

    /**
     * 像堆栈Stack一样操作
     */
    static void likeStack() {
        def list1 = [985, "Tina"]
        // 入栈: 从列表的头部添加元素
        list1.push("Aaron")
        assert list1 == ["Aaron", 985, "Tina"]
        // 出栈: 从列表的头部移除元素
        def e = list1.pop()
        assert e == "Aaron"
        assert list1 == [985, "Tina"]
    }

    /**
     * 列表其它常用操作
     */
    static void other() {
        def list1 = [1,2,3,7,4]
        // Groovy的inject方法类似于Java Stream的reduce方法
        // 这里参数0作为闭包中第一个参数acc的初值
        def sum1 = list1.inject(0) {acc, e -> acc+e}
        // sum1 = 1+2+3+7+4 = 17
        assert sum1 == 17
        def maxNum  = list1.inject(Integer.MIN_VALUE) { result, e -> Integer.max(result,e) }
        assert maxNum == 7

        def list2 = list1.collect {e -> e*2 }
        assert list1 == [1,2,3,7,4]
        assert list2 == [2,4,6,14,8]

        // 分别获取奇数、偶数
        def oddList = list1.findAll {e -> e%2==1 }
        def evenList = list1.findAll {e -> e%2==0 }
        assert oddList == [1,3,7]
        assert evenList == [2,4]
        // 查找第一个满足闭包条件的元素
        def num = list1.find {e -> e>3}
        assert num == 7
        // 列表中每一个元素是否均满足闭包条件
        def isAllPositive = list1.every {e -> e>0 }
        assert isAllPositive
        def result = list1.every {e -> e<3 }
        assert !result
        // 列表中是否存在一个元素满足闭包条件
        def result2 = list1.any {e -> e<3 }
        assert result2

        def list3 = [1,22,13,24,15,15,3,9,22]
        // 对22进行计数
        def count1 = list3.count(22)
        assert count1 == 2
        // 列表的最大值
        def max = list3.max()
        assert max == 24
        // 列表的最小值
        def min = list3.min()
        assert min == 1

        // 通过Set实现列表去重
        def list4 = new HashSet(list3).toList()
        println( "list4 : $list4" )
        // 去重同时保留顺序
        def list5 = list3.unique()
        println( "list5 : $list5" )

        // 正向遍历
        def list6 = ["Aaron","Tina","Bob"]
        def str1 = ""
        list6.each {e -> str1 += e + "~" }
        assert str1 == "Aaron~Tina~Bob~"
        // 反向遍历
        def str2 = ""
        list6.reverseEach {e -> str2 += e+"~" }
        assert str2 == "Bob~Tina~Aaron~"
        // 更优雅地拼接
        def str3 = list6.join("~")
        assert str3 == "Aaron~Tina~Bob"
    }
}