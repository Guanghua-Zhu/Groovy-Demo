package com.aaron.BasicDemo

/**
 * Groovy 操作符示例
 */
class OperatorDemo {

    static void main(String[] args){
        arithmetic()
        relational()
        logical()
        bit()
        conditional()
    }

    /**
     * 算术操作符
     */
    static void arithmetic() {
        // 加
        assert 3+4 == 7
        // 加并赋值
        def foo1 = 3
        foo1 += 4
        assert foo1 == 7

        // 减
        assert 4-3 == 1
        // 减并赋值
        def foo2 = 4
        foo2 -= 3
        assert foo2 == 1

        // 乘
        assert 4*3 == 12
        // 乘并赋值
        def foo3 = 4
        foo3 *=3
        assert foo3 == 12

        // 除
        assert 12/3 == 4
        // 除并赋值
        def foo4 = 12
        foo4 /= 3
        assert foo4 ==4

        // 求余
        assert 13%3 == 1
        // 求余并赋值
        def foo5 = 13
        foo5 %= 3
        assert foo5 == 1

        // 幂
        assert 2**3 == 8
        // 幂并赋值
        def foo6 = 2
        foo6 **= 3
        assert foo6 == 8

        // 一元运算符 + 表示 正数
        assert +3 == 3
        // 一元运算符 - 表示 负数
        assert -4 == 0-4
        assert -(-11)  == 11

        // 后缀自增
        def a1 = 2
        def b1 = a1++
        assert a1 == 3
        assert b1 == 2

        // 前缀自增
        def a2 = 2
        def b2 = ++a2
        assert a2 == 3
        assert b2 == 3

        // 后缀自减
        def a3 = 2
        def b3 = a3--
        assert a3 == 1
        assert b3 == 2

        // 前缀自减
        def a4 = 2
        def b4 = --a4
        assert a4 == 1
        assert b4 == 1
    }

    /**
     * 关系运算符
     */
    static void relational() {
        // 相等
        assert (3*4) == (10+2)
        // 不相等
        assert 3 != 4
        // 小于
        assert 3 < 4
        // 小于等于
        assert 3<=4
        assert 4<=4
        // 大于
        assert 5 > 4
        // 大于等于
        assert 5>=4
        assert 5>=5
    }

    /**
     * 逻辑运算符
     */
    static void logical() {
        // 逻辑与, 支持短路求值
        assert true && true
        // 逻辑或, 支持短路求值
        assert false || true
        // 逻辑非
        assert !false
    }

    /**
     * 位运算
     */
    static void bit() {
        // 按位与
        int a = 0b1010  // a = 10
        assert a == 10
        int b = 0b0110  // b = 6
        assert b == 6
        int c = 0b0010  // c = 2
        assert c ==2
        assert (a&b) == c

        // 按位或
        int d = 0b1110  // d = 14
        assert d == 14
        assert (a|b) == d

        // 按位异或
        int e = 0b1100
        assert e == 12
        assert (a^b) == e

        // 按位取反
        byte f = 0b00001111
        assert f == 15
        byte h = 0b11110000
        assert h == -16
        assert (~f) == h

        // 左移
        byte i = 0b00000011
        assert i == 3
        byte j = 0b00001100
        assert j == 12
        assert (i<<2) == j

        // 右移
        assert (j>>2) == i
        // 右移: 左边使用原符号位进行填充, 右边超出部分直接丢弃
        byte k = 0b11111011
        assert k == -5
        byte p = 0b11111110
        assert p == -2
        assert (k>>2) == p

        // 无符号右移: 左边使用0进行填充, 右边超出部分直接丢弃
        int q = 0x8022_11ff // 数字支持使用下划线进行划分, 便于人眼查看
        assert q == -2145250817
        int r = 0x0080_2211
        assert r == 8397329
        assert (q>>>8) == r
    }

    /**
     * 条件运算符
     */
    static void conditional() {
        // 三元运算符
        def a1 = (2>1) ? 3 : 4
        assert a1 == 3
        def a2 = (2>3) ? 3 : 4
        assert a2 == 4

        // Elvis 运算符, 如果?:运算符左边的操作数判定为真，则返回左边操作数; 否则返回右边的操作数
        // 可以视为简版的三元运算符
        def b1 = "Hello World"
        //  等同于 b1 = (b1!=null || b1!="") ? b1 : "Hi"
        b1 = b1 ?: "Hi" // 非空字符串视为真
        assert b1 == "Hello World"
        // Elvis 运算符
        def b2 = ""
        b2 = b2 ?: "Hi"
        assert b2 == "Hi"

        // Elvis赋值 运算符, 其是对Elvis运算符对进一步简化, 省去了再次赋值操作
        def c1 = "Hello World"
        c1 ?= "Hi"
        assert c1 == "Hello World"

        def c2 = ""
        c2 ?= "Hi"
        assert c2 == "Hi"
    }

    /**
     * 对象操作符
     */
    static void object() {

    }

    static void special() {
        // list1a, list1b 引用地址相同
        def list1a = [1,2] as LinkedList
        def list1b = list1a
        def list2 = [1,2] as LinkedList
        // 两个对象的引用相同
        assert list1a === list1a
        // 两个对象的引用不同
        assert list1a !== list2
    }
}

