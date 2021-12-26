package com.aaron.BasicDemo

/**
 * Groovy 闭包 示例
 */
class ClosureDemo {
    static void main(String[] args) {
        define()
        call()
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
        def closure3 = { String x, String y -> "${x}==${y}" }
        assert closure3.call('2','7') == "2==7"
    }
}
