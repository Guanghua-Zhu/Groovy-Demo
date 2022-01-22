package com.aaron.MPDemo

/**
 * Groovy MP元编程示例: 调用方法缺失
 * @author Aaron
 * @date 2022/1/21
 */
class MissMethodDemo {
    static void main(String[] args) {
        testCat()
        testDog()
        testPig()
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

    static void testPig() {
        Pig pig = new Pig(name:"Bob", type:"PIG")

        // 调用实例不存在的方法
        def result1 = pig.swim()
        assert result1 == "Yes, Bob can Swim..."

        // 调用实例不存在的方法
        def result2 = pig.fly()
        assert result2 == "Yes, Bob also can Fly~"

        // 调用实例不存在的方法
        def result3 = pig.eat()
        assert result3 == null
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

class Pig {
    String name
    String type

    String swimmable() {
        return "Yes, ${name} can Swim..."
    }

    String flyable() {
        return "Yes, ${name} also can Fly~"
    }

    /**
     * 实现方法缺失的钩子函数
     * @param methodName
     * @param args
     * @return
     */
    def methodMissing(String methodName, Object args) {
        // 新方法名
        String newMethodName
        switch (methodName) {
            case "swim" : {
                newMethodName = methodName + "mable"
                break
            }
            case "fly" : {
                newMethodName = methodName + "able"
                break
            }
            default : newMethodName = null
        }

        if(newMethodName) {
            def result = this.invokeMethod( newMethodName, null )
            return result
        }
        return null
    }
}

