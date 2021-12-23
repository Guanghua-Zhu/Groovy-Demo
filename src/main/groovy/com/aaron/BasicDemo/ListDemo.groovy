package com.aaron.BasicDemo

/**
 * Groovy 列表 示例
 */
class ListDemo {
    static void main(args) {
        basic()
        operaAsStack()
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
        LinkedList myLinkedList1 = new LinkedList()
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
    static void operaAsStack() {
        def list1 = [985, "Tina"]
        // 入栈: 从列表的头部添加元素
        list1.push("Aaron")
        assert list1 == ["Aaron", 985, "Tina"]
        // 出栈: 从列表的头部移除元素
        def e = list1.pop()
        assert e == "Aaron"
        assert list1 == [985, "Tina"]
    }
}