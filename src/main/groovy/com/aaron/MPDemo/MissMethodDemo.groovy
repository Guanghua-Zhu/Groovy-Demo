package com.aaron.MPDemo

/**
 * Groovy MP元编程示例: 调用方法缺失
 * @author zhuguanghua
 * @date 2022/1/21
 */
class MissMethodDemo {
    static void main(String[] args) {
        testCat()
        testDog()
    }

    static void testCat() {
        Dog cat = new Dog(name:"Tom", type:"CAT")

        try {
            // 调用实例不存在的方法
            cat.fly()
        } catch (Exception e) {
            assert e instanceof MissingMethodException
            println("Happen Missing Method Exception")
        }
    }

    static void testDog() {
        Dog dog = new Dog(name:"Aaron", type:"DOG")

        // 调用实例不存在的方法
        String msg = dog.fly("5", "km")
        assert msg == "[DOG] ==>> methodName: fly, args: [5, km]"

    }

}

class Cat {
    String name
    String type
}

class Dog {
    String name
    String type

    /**
     * 实现方法缺失的钩子函数
     * @param methodName
     * @param args
     * @return
     */
    def methodMissing(String methodName, Object args) {
        return "[DOG] ==>> methodName: ${methodName}, args: ${args}"
    }
}

