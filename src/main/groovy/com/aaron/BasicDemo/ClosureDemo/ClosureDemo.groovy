package com.aaron.BasicDemo.ClosureDemo

import org.codehaus.groovy.runtime.MethodClosure

/**
 * Groovy 闭包 示例
 */
class ClosureDemo {
    static void main(String[] args) {
        define()
        call()
        returnResult()
        methodClosure()
        other()
    }

    /**
     * 闭包声明定义
     * @param args
     */
    static void define(String[] args) {
        def range1 = 1..2
        println("\n----------------- Test 1 -----------------")
        range1.each { it -> println("Test 1: $it") }

        println("\n----------------- Test 2 -----------------")
        // 如果闭包没有通过->显式声明参数列表, Groovy会默认提供了一个名为it的缺省参数
        range1.each { println("Test 2: $it") }

        println("\n----------------- Test 3 -----------------")
        // 如果期望声明一个无参数的闭包，需要显式添加->来声明空参数列表, 以避免Groovy提供默认参数it
        def closure3 = { -> println("Hello World") }
        closure3.call()

        println("\n----------------- Test 4 -----------------")
        def closure1 = { num -> println("Test 4: $num") }
        assert closure1 instanceof Closure
        range1.each( closure1 )

        println("\n----------------- Test 5 -----------------")
        Closure closure2 = { println("Test 5: $it") }
        assert closure2 instanceof Closure
        range1.each( closure2 )
    }

    /**
     * 直接调用闭包
     */
    static void call() {
        // 定义一个不需要参数的闭包
        def closure1 = { "123" }
        // 直接添加()调用该闭包
        assert closure1() == "123"
        // 通过call方法调用闭包
        assert closure1.call() == "123"

        // 定义一个需要两个参数的闭包
        def closure2 = { x, y -> x+y }
        assert closure2(1,3) == 4

        // 闭包的参数类型是可选地
        def closure3 = { String x, String y -> "${x}~~${y}" }
        assert closure3.call('2','7') == "2~~7"

        // 闭包支持带有缺省值的参数
        def closure4 = { x, y=5 -> x*y }
        assert closure4.call( 4,7 ) == 28
        // 对于未给定的参数值, 闭包会使用缺省值
        assert closure4.call( 4) == 20
    }

    /**
     * 闭包的返回结果
     */
    static void returnResult() {
        def closure1 = { ->
            def list = []
            list.add("Bob")
            list.add("Aaron")
        }
        def result1 = closure1.call()
        // 闭包中未显式使用return语句, 返回最后一个表达式的结果, 即list.add方法的返回值
        assert result1 == true

        def closure2 = { ->
            def list = []
            list.add("Bob")
            list.add("Aaron")
            return list
        }
        def result2 = closure2.call()
        // 闭包中显式使用return语句, 返回指定值
        assert result2 == ["Bob", "Aaron"]
    }

    /**
     * 方法引用作为闭包, 即方法闭包
     */
    static void methodClosure() {
        def list = ["Tony", "Amy", "civilization", "Aaron"]

        StrRule strRule1 = new StrRule(4)
        // 通过.&方法指针运算符, 获取 strRule1实例中validate方法的引用 作为闭包
        // 该方法闭包等价于: { String str -> str.size() <= 4 }
        MethodClosure closure1 = strRule1.&validate
        assert closure1 instanceof Closure
        def list1 =  list.findAll( closure1 )
        assert list1 == ["Tony", "Amy"]

        StrRule strRule2 = new StrRule(6)
        // 方法闭包 strRule2.&validate 等价于
        // { String str -> str.size() <= 6 }
        def list2 = list.findAll( strRule2.&validate )
        assert list2 == ["Tony", "Amy", "Aaron"]

        Handler handler = new Handler()
        // 方法闭包支持重载, Groovy在运行时会选择合适的方法进行执行
        def closure2 = handler.&handle
        def result1 = closure2.call("Aaron")
        assert result1 == "AARON"
        def result2 = closure2.call(12)
        assert result2 == 24
        def result3 = closure2.call(3, 7)
        assert result3 == 10
    }

    /**
     * 闭包的常用方法
     */
    static void other() {
        // 获取闭包参数的数量
        Closure closure1 = { print("Hello: $it") }
        assert closure1.getParameterTypes().size() == 1

        Closure closure2 = { print("Hello") }
        assert closure2.getParameterTypes().size() == 1

        Closure closure3 = { -> print("Hello") }
        assert closure3.getParameterTypes().size() == 0

        Closure closure4 = { x,y,z -> print("Hello") }
        assert closure4.getParameterTypes().size() == 3

        // 柯里化
        Closure closure5 = { x,y,z -> "$x--$y~~$z" }
        Closure closure6 = closure5.curry("Aaron")
        assert closure6.call("Bob", "Amy") == "Aaron--Bob~~Amy"
        Closure closure7 = closure5.curry("China", "USA")
        assert closure7.call("UK") == "China--USA~~UK"

    }
}

class StrRule{
    int limit

    StrRule(int limit) {
        this.limit = limit
    }

    boolean validate(String str) {
        return str.size() <= limit
    }
}

class Handler{
    String handle(String str) {
        return str.toUpperCase()
    }

    int handle(int num) {
        return 2 * num
    }

    int handle(int x, int y) {
        return x + y
    }
}