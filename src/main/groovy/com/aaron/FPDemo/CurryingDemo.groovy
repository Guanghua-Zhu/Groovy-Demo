package com.aaron.FPDemo;

import groovy.lang.Closure;

/**
 * Groovy 闭包柯里化 示例
 * @date 2022/1/17
 */
class CurryingDemo {

    static void main(String[] args) {
        // 左柯里化
        Closure closure5 = { x,y, z -> "$x--$y~~$z" }
        Closure closure6 = closure5.curry("Aaron")
        assert closure6.call("Bob", "Amy") == "Aaron--Bob~~Amy"
        Closure closure7 = closure5.curry("China", "USA")
        assert closure7.call("UK") == "China--USA~~UK"

        // 右柯里化
        Closure closure8 = closure5.rcurry("Z1")
        assert closure8.call("X1", "Y1") == "X1--Y1~~Z1"
        Closure closure9 = closure5.rcurry("Y2", "Z2")
        assert closure9.call("X2") == "X2--Y2~~Z2"

        // 基于索引的柯里化, 参数的位置索引从0开始
        Closure c1 = { a,b,c,d -> "$a--$b~~$c>>$d" }
        // 设置闭包第二个参数为"Hello"
        Closure c2 = c1.ncurry(1,"Hello")
        assert c2.call("0", "2", "3") == "0--Hello~~2>>3"
        // 从指定索引处依次设置多个参数, 这里闭包第二、三个参数分别为"Hello"、"Aaron"
        Closure c3 = c1.ncurry(1,"Hello", "Aaron")
        assert c3.call("0", "3") == "0--Hello~~Aaron>>3"
    }

}
