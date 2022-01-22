package com.aaron.MPDemo

/**
 * Groovy MP元编程示例: 属性缺失
 * @author Aaron Zhu
 * @date 2022-01-22
 */
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
            println("Happen Missing Property Exception")
        }
    }

    static void testLion() {
        Lion lion = new Lion(name: "Tom", type: "LION")

        assert lion.name == "Tom"
        assert lion.type == "LION"

        // 访问不存在的属性
        def result = lion.age
        assert result == "Not age Property!"
    }

}

class Tiger{
    String name
    String type
}

class Lion{
    String name
    String type

    def propertyMissing(String propertyMissing) {
        return  "Not ${propertyMissing} Property!"
    }
}

