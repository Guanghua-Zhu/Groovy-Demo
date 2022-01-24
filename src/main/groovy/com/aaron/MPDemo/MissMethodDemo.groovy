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
        testChicken()
    }

    /**
     * 调用不存在的方法, 抛出异常
     */
    static void testCat() {
        Cat cat = new Cat(name:"Tom", type:"CAT")

        try {
            // 调用实例不存在的方法
            cat.fly()
        } catch (Exception e) {
            assert e instanceof MissingMethodException
            println("Happen Missing Method Exception #1")
        }

        try {
            // 调用不存在的静态方法
            Cat.run()
        } catch (Exception e) {
            assert e instanceof MissingMethodException
            println("Happen Missing Method Exception #2")
        }
    }

    /**
     * 调用不存在的方法, 返回默认值
     */
    static void testDog() {
        Dog dog = new Dog(name:"Aaron", type:"DOG")
        // 调用实例不存在的方法
        String msg = dog.fly("5", "km")
        assert msg == "[DOG] ==>> methodName: fly, args: [5, km]"
    }

    /**
     * 调用不存在的方法, 动态处理
     */
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

    /**
     * 调用不存在的静态方法
     */
    static void testChicken() {
        assert Chicken.getInfo() == "I'm a CHICKEN"
        assert Chicken.calcPrice() == "Missing Static Method: calcPrice"
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

class Chicken {
    static String type = "CHICKEN"

    static String getInfo() {
        return "I'm a ${type}"
    }

    /**
     * 实现静态方法缺失的钩子函数$static_methodMissing, 以避免抛出MissingMethodException异常
     * @param methodName
     * @param args
     * @return
     */
    static def $static_methodMissing(String methodName, Object args) {
        return "Missing Static Method: $methodName"
    }
}
