package com.aaron.FPDemo

/**
 * Groovy 闭包记忆化 示例
 */
class MemoizationDemo {
    static void main(String[] args) {
        test1()
        test2()
    }

    static void test1() {
        def count = 0
        // 递归调用闭包时, 必须先定义一个变量作为闭包的名字, 然后才能在闭包内部递归调用
        def fib1
        fib1 = { long n ->
            count++     // 计数
            if( n==0 ) {
                return 0
            } else if( n==1 ) {
                return 1
            } else {
                return fib1(n-1) + fib1(n-2)
            }
        }
        assert fib1(6) == 8
        assert count == 25

        count = 0   // 清空计数器
        def fib3
        fib3 = { long n ->
            count++     // 计数
            if( n==0 ) {
                return 0
            } else if( n==1 ) {
                return 1
            } else {
                return fib3(n-1) + fib3(n-2)
            }
        }.memoize()
        assert fib3(6) == 8
        assert count == 7
    }


    static void test2() {
        def f1
        // 采用基于LRU的淘汰策略, 最多保留3个缓存数据
        f1 = { n ->
            if(n==1) {
                return 1
            }
            return f1(n-1) + n
        }.memoizeAtMost(3);

        def f2
        // 采用基于LRU的淘汰策略, 发生GC时至少保留3个缓存数据
        f2 = { n ->
            if(n==1) {
                return 1
            }
            return f2(n-1) + n
        }.memoizeAtLeast(3);

        def f3
        // 采用基于LRU的淘汰策略
        // 最多保留5个缓存数据, 发生GC时至少保留3个缓存数据
        f3 = { n ->
            if(n==1) {
                return 1
            }
            return f3(n-1) + n
        }.memoizeBetween(3,5);
    }

}
