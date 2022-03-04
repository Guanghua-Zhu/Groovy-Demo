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
        // h1(x) = f( g(x) ) = (x*3) + 2 = (3*3) + 2 = 11
        def h1x = {x -> fx( gx(x) )}
        assert h1x(3) == 11

        // h2(x) = g( f(x) ) = (x+2) * 3 = (3+2) * 3 = 15
        def h2x = {x -> gx( fx(x) )}
        assert h2x(3) == 15

        // 闭包重载了左移<<, 右移>>运算符, 将一个闭包的输出作为另一个闭包的输入
        // y1(x) = g( f(x) ) = (x+2) * 3
        def y1x = gx << fx
        assert y1x(3) == 15

        // y2(x) = g( f(x) ) = (x+2) * 3
        def y2x = fx >> gx
        assert y2x(3) == 15
    }
}
