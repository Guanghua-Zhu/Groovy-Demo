package com.aaron.Basic

/**
 * 语句结构
 */
class StatementDemo {
    static void main(args) {
        testIf()
        testWhile()
        testFor()

    }

    /**
     * If 语句测试
     */
    static void testIf() {
        def x
        if (true) {
            x = 1
        } else {
            x = 2
        }
        assert x == 1

        if (false) {
            x = 1
        } else {
            x = 2
        }
        assert x == 2

        // 条件中 null 被处理为 false
        if (null) {
            x = 1
        } else {
            x = 2
        }
        assert x == 2

        // 条件中 not null 被处理为 true
        if ("Hello") {
            x = 1
        } else {
            x = 2
        }
        assert x == 1

        // 支持 if - else if 语句
        def foo = 99
        def bar
        if (foo==1) {
            bar = 1
        } else if (foo==2) {
            bar = 2
        } else {
            bar = 3
        }
        assert bar == 3
    }

    /**
     * while 语句测试
     */
    static void testWhile() {
        def bar = 0
        while ( bar < 10 ) {
            bar++
        }
        assert bar == 10

        // 支持 do - while 语句
        def count = 4
        def fact = 1
        do{
            fact *= count
            count--
        }while (count>1)
        assert fact == 24
    }

    /**
     * switch 语句测试
     */
    static testSwitch() {
        // Aaron todo
    }

    static testFor() {
        /************* 经典 for 循环 *************/
        def msg = ""
        for(int i=1; i<=5; i++) {
            msg += i
        }
        assert msg == "12345"

        // for语句 支持多赋值
        def str = ""
        for( def (x, y) = ["Hello", 42]; y<45; x++, y++) {
            str += "$x $y, "
        }
        assert str == "Hello 42, Hellp 43, Hellq 44, "

        /************* for in 迭代容器 *************/
        // for in 迭代 List
        def list = [1,3,5]
        def x = 0
        for( e in list ) {
            x += e
        }
        assert x == 9

        // for in 迭代 Array
        def array = [2, 6, 4] as int[]
        def y = 0
        for( e in array ) {
            y += e
        }
        assert y == 12

        // for in 迭代 Map
        def map = ["Aaron":1, "Bob":3, "Tina":2]
        def names = ""
        def sum = 0
        for( e in map ) {
            names += e.key + ","
            sum += e.value
        }
        assert names == "Aaron,Bob,Tina,"
        assert sum == 6

        def sum1 = 0
        for ( e in map.values()) {
            sum1 += e
        }
        assert sum1 == 6

        // for in 迭代 字符串中的字符
        def text = "Hello"
        def list2 = []
        for ( c in text ) {
            list2.add( c )
        }
        assert list2 == ["H", "e", "l", "l", "o"]

        // for in 迭代 range
        def range = 1..3
        def result2 = 0
        for( e in range ) {
            result2 += e
        }
        assert result2 == 6

        /************* 经典 for each 循环 *************/
        // for each 迭代 List
        def list3 = [1,3,5]
        def result = 0
        // 使用foe each循环需要指定e变量的类型,
        // 这里可以使用int, 也可以直接使用def
        for(def e : list3) {
            result += e
        }
        assert result == 9

    }






}
