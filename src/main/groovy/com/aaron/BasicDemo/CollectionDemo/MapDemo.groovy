package com.aaron.BasicDemo.CollectionDemo

/**
 * Groovy Map 示例
 */
class MapDemo {
    static void main(String[] args) {
        //basic()
        testType()
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
        assert map1.Bob == 18

        // 修改、添加操作类似
        map1["Aaron"] = 2
        map1.put("Bob", 1)
        map1."Tim" = 7
        assert map1 == ["Aaron":2, "Bob":1, "Tony":35, "Tim":7, "Tom":996]

        // 迭代Map中的条目按闭包进行计算，并将结果保存到列表中
        def list1 = map1.collect{ entry -> entry.value+1000 }
        assert list1 instanceof List
        assert list1 == [1002, 1001, 1035, 1996, 1007]

        def list2 = []
        // 将结果保存到给定的List中
        map1.collect(list2) { entry -> entry.value+2000}
        assert list2 instanceof List
        assert list2 == [2002, 2001, 2035, 2996, 2007]

        // Map中每一个KV对是否均满足闭包条件
        assert map1.every {entry -> entry.value>0}
        assert !map1.every {entry -> entry.value>3}
        // Map中是否存在一个KV对满足闭包条件
        assert map1.any {entry -> entry.key=="Aaron"}

        def map5 = ["Bob":3, "Aaron":18, "Tom": 23]
        // 查找任一一个满足闭包条件的条目
        def entry1 =  map5.find { entry -> entry.value>5 }
        assert entry1.toString() == "Aaron=18"

        // 查找所有满足闭包条件的条目
        def resutMap =  map5.findAll { entry -> entry.value>5 }
        assert resutMap == [Aaron:18, Tom:23]
    }

    static void testType() {
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
