package com.aaron.ScriptDemo

/**
 * Groovy Eval 动态计算示例
 * @author Aaron Zhu
 * @date 2022-02-11
 */
class EvalDemo {
    static void main(String[] args) {
        // 2*3-1 = 5
        assert Eval.me('2*3-1') == 5
        // 2*7+1 = 15
        assert Eval.me("num", 7, '2*num+1') == 15

        // 10+3 = 13
        assert Eval.x(3, '10+x') == 13
        // 10*7+2 = 72
        assert Eval.xy(7,2, '10*x+y') == 72
        // 4*100+7*10+9= 479
        assert Eval.xyz(4,7,9, 'x*100+y*10+z') == 479
    }
}
