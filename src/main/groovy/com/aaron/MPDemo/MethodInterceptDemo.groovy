package com.aaron.MPDemo

/**
 * Groovy MP元编程示例: 方法调用拦截
 * @author Aaron Zhu
 * @date 2022-01-25
 */
// Aaron: todo: output 2 blog
class MethodInterceptDemo {

    static void main(String[] args) {
        //test1()
        //test2()
        test3()
    }

    static void test1() {
        Bird bird = new Bird(name:"Aaron")
        assert bird.calcInfo() == "Aaron is a Bird"
        // 通过GroovyObject接口的默认方法invokeMethod实现方法调用
        assert bird.invokeMethod("calcInfo", null) == "Aaron is a Bird"
    }

    static void test2() {
        RedBird redBird = new RedBird(name:"Bob")
        def result1 = redBird.calcInfo()
        // 重写GroovyObject接口的默认方法invokeMethod对方法调用的拦截
        def result2 = redBird.invokeMethod("calcColor", null)
        // 直接调用方法、通过invokeMethod调用方法 结果不一样
        assert result1 != result2
        assert result1 == "Bob is a Red Bird"
        assert result2 == "Hello~"
    }

    static void test3() {
        BlueBird blueBird = new BlueBird(name: "Aaron")

        def info1 = blueBird.calcInfo()
        def info2 = blueBird.invokeMethod("calcInfo", null)
        assert info1 == info2
        assert info1 == "Hi..."
        assert info2 == "Hi..."

        println "gg"
    }
}

class Bird {
    String name

    def calcInfo() {
        return "$name is a Bird"
    }
}

class RedBird {
    String name

    def calcInfo() {
        return "$name is a Red Bird"
    }

    def invokeMethod(String methodName, Object args) {
        return "Hello~"
    }
}

class BlueBird implements GroovyInterceptable {
    String name

    def calcInfo() {
        return "$name is a Red Bird"
    }

    def calcColor() {
        return "Blue"
    }

    def invokeMethod(String methodName, Object args) {
        println "invokeMethod: [DEBUG]: ${methodName}, ${args}"

        if( methodName == "calcInfo" ) {
            return "Hi..."
        } else if( methodName == "calcColor" ) {
            def temp = metaClass.invokeMethod(this, methodName, args)
            return "<< ${temp} >>"
        }

        return metaClass.invokeMethod(this, methodName, args)
    }
}
