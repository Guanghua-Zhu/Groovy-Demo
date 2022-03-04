package com.aaron.FPDemo

/**
 * Groovy 蹦床 示例
 */
class TrampolineDemo {

    static void main(String[] args) {
        //test1()
        //test2()
        test3()
    }

    /**
     * 基于普通递归的阶乘
     */
    static void test1() {
        def fact1
        fact1 = { n ->
            if(n<2) {
                return 1
            }
            return fact1(n-1) * n
        }

        // 递归过深导致栈溢出SOF
        fact1(2000)
    }

    /**
     * 基于尾递归的阶乘
     */
    static void test2() {
        def fact2
        fact2 = { n, result=1 ->
            if(n<2) {
                return result
            }
            return fact2(n-1, result*n)
        }

        // 递归过深导致栈溢出SOF
        fact2(2000)
    }

    /**
     * 基于尾递归的阶乘, 并使用蹦床函数trampoline进行包装
     */
    static void test3() {
        def fact3
        fact3 = { n, result=1 ->
            if(n<2) {
                return result
            }
            // 递归闭包时, 通过trampoline方法传参
            // 其会返回一个TrampolineClosure实例, 用于对原闭包进行包装
            return fact3.trampoline(n-1, result*n)
        }.trampoline()  // 通过trampoline方法将闭包包装为TrampolineClosure


        fact3(2000)
        println "No Happen Stack Overflow Error"
    }

}
