package com.aaron.BasicDemo

/**
 * Groovy 操作符重载 示例
 */
// Aaron: todo: output 2 blog
class OperatorOverLoad {
    static void main(String[] args){
        testPlus()
        testNext()
        testCall()
    }

    static void testNext() {
        Food food1 = new Food("西瓜", 14)
        food1++
        assert food1.toString() == "Food { type=西瓜, num=15 }"

        def food2 = new Food(type: "哈蜜瓜" )
        food2++
        assert food2.toString() == "Food { type=哈蜜瓜, num=1 }"
    }

    static void testPlus() {
        Food food1 = new Food("橘子", 1)
        Food food2 = new Food("橘子", 2)
        Food food3 = new Food("type": "橘子")
        Food food4 = new Food("type": "苹果")

        assert (food1 + food2).toString() == "Food { type=橘子, num=3 }"
        assert (food2 + food3).toString() == "Food { type=橘子, num=2 }"
        assert (food1 + food4) == null
    }

    static void testCall() {
        Food food1 = new Food("柠檬", 21)
        def str = food1()
        // 显式调用call方法
        assert food1.call() == "Food { type=柠檬, num=21 }"
        // 通过 调用运算符() 调用 call方法
        assert food1() == "Food { type=柠檬, num=21 }"
    }

}

class Food {
    String type
    Integer num

    Food(String type, Integer num) {
        this.type = type
        this.num = num
    }

    Food() {
    }

    /**
     * 重载加法运算符+
     * @param other
     * @return
     */
    Food plus(Food other) {
        if( !this.type || this.type != other?.type ) {
            return null
        }

        Integer num1 = this.num ?: 0
        Integer num2 = other.num ?: 0
        Integer result = num1 + num2
        return new Food("type":type, "num": result)
    }

    /**
     * 重载自增运算符++
     * @return
     */
    Food next() {
        this.num ?= 0
        this.num++
        return this
    }

    /**
     * 重载调用运算符()
     * @return
     */
    String call() {
        return toString()
    }

    @Override
    String toString() {
        return "Food { type=$type, num=$num }"
    }

}
