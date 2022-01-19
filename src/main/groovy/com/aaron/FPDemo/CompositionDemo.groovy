package com.aaron.FPDemo

/**
 * Groovy 闭包组合 示例
 */
class CompositionDemo {
    static void main(String[] args) {

        // f(x) = x+2
        def fx = {x -> x+2}
        // g(x) = x*3
        def gx = {x -> x*3}

        // 闭合组合
        // h(x) = f( g(x) ) =  (x*3) + 2
        def hx = {x -> fx( gx(x) )}
        assert hx(3) == 11

        // y1(x) = g( f(x) ) =  (x+2) * 3
        def y1 = {x -> gx( fx(x) )}
        assert y1(3) == 15

        // 闭包重载了左移<<, 右移>>运算符, 将一个闭包的输出作为另一个闭包的输入
        // y2(x) = g( f(x) )
        def y2 = gx << fx
        assert y2(3) == 15

        // y3(x) = g( f(x) )
        def y3 = fx >> gx
        assert y3(3) == 15
    }
}
