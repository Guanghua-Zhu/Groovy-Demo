package com.aaron.MPDemo

/**
 * Groovy MP元编程示例: 属性缺失
 * @author Aaron Zhu
 * @date 2022-01-22
 */
// Aaron: todo: output 2 blog
class MissPropertyDemo {
    static void main(String[] args){
        testTiger()
        testLion()
    }

    static void testTiger() {
        Tiger tiger = new Tiger(name: "Tony", type: "TIGER")

        assert tiger.name == "Tony"
        assert tiger.type == "TIGER"

        try {
            // 访问不存在的属性
            def age = tiger.age
        } catch (Exception e) {
            assert e instanceof MissingPropertyException
            println("Happen Missing Property Exception #1")
        }

        try {
            // 修改不存在的属性
            tiger.age = 234
        } catch (Exception e) {
            assert e instanceof MissingPropertyException
            println("Happen Missing Property Exception #2")
        }

        try {
            // 访问不存在的静态属性
            def remark = Tiger.remark
        } catch (Exception e) {
            assert e instanceof MissingPropertyException
            println("Happen Missing Property Exception #3")
        }

        try {
            // 修改不存在的静态属性
            Tiger.remark = 234
        } catch (Exception e) {
            assert e instanceof MissingPropertyException
            println("Happen Missing Property Exception #4")
        }
    }

    static void testLion() {
        Lion lion = new Lion(name: "Tom", type: "LION")

        assert lion.name == "Tom"
        assert lion.type == "LION"

        // 访问不存在的属性
        def result = lion.age
        assert result == "Not age Property For Get!"
        // 修改不存在的属性
        lion.age = 22

        // 访问不存在的静态属性
        result = Lion.remark
        assert result == "Not remark Static Property For Get!"
        // 修改不存在的静态属性
        Lion.remark = "危险动物"
    }
}

class Tiger{
    String name
    String type
}

class Lion{
    String name
    String type

    /**
     * 实现访问不存在的属性的钩子函数
     * @param propertyName
     * @return
     */
    def propertyMissing(String propertyName) {
        return  "Not ${propertyName} Property For Get!"
    }

    /**
     * 实现修改不存在的属性的钩子函数
     * @param propertyName
     * @param args
     */
    void propertyMissing(String propertyName, Object args) {
        println "Not ${propertyName} Property! For Set, args: ${args}"
    }

    /**
     * 实现访问不存在的静态属性的钩子函数
     * @param propertyName
     * @return
     */
    static def $static_propertyMissing(String propertyName) {
        return "Not ${propertyName} Static Property For Get!"
    }

    /**
     * 实现修改不存在的静态属性的钩子函数
     * @param propertyName
     * @param args
     */
    static void $static_propertyMissing(String propertyName, Object args) {
        println "Not ${propertyName} Static Property For Set, args: ${args}"
    }
}