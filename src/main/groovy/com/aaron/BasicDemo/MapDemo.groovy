package com.aaron.BasicDemo

/**
 * Groovy Map 示例
 */
class MapDemo {
    static void main(String[] args) {
        basic()
    }

    static void basic() {
        def map1 = ["Aaron":25, "Bob":18, "Tony": 35]
        // Map的默认实现类为LinkedHashMap
        assert map1 instanceof LinkedHashMap
        assert map1.size() == 3
        // 通过Key作为下标访问Value
        assert map1["Bob"] == 18
        // 通过get方法访问Value
        assert map1.get("Bob") == 18
        assert map1.get("Tom", 996) == 996
        // 通过点操作符访问Value
        assert map1."Bob" == 18

        // 修改、添加操作类似
        map1["Aaron"] = 2
        map1.put("Bob", 1)
        map1."Tim" = 7
        assert map1 == ["Aaron":2, "Bob":1, "Tony":35, "Tim":7, "Tom":996]

        // Map中每一个KV对是否均满足闭包条件
        assert map1.every {entry -> entry.value>0}
        assert !map1.every {entry -> entry.value>3}
        // Map中是否存在一个KV对满足闭包条件
        assert map1.any {entry -> entry.key=="Aaron"}

        /********************** 类型指定 **********************/
        // 定义一个空Map, 通过as运算符强制类型为TreeMap
        def map2 = [:] as TreeMap
        assert map2 instanceof TreeMap

        // 直接new一个TreeMap实例
        def map3 = new TreeMap()
        assert map3 instanceof TreeMap

        // 通过显式类型声明
        TreeMap map4 = ["Aaron":25, "Bob":18, "Tony": 35]
        assert map4 instanceof TreeMap
    }

}
