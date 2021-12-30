package com.aaron.OOPDemo

/**
 * Groovy 类的示例: 方法
 */
class MethodDemo {
    static void main(String[] args) {
        assert Calc.getInfo1() == true
        assert Calc.getInfo2() == [1,3,2]

        Calc calc = new Calc()
        assert calc.getInfo3() == null

        assert calc.sum(2,3) == 5
        assert calc.sum(3,4,7) == 14
        // 通过命名参数的方式进行调用
        assert calc.sum("a":2, "c":7) == 9

        // Groovy是多分派语言, 其在运行期会根据方法参数的实际类型选择相应版本的方法
        Object foo1= "Hello"
        assert calc.add(foo1) == "add method: #1"
        Object foo2= 2
        assert calc.add(foo2) == "add method: #2"

        // 传统判空方法
        assert calc.getAgeByMap1("Aaron") == 23
        assert calc.getAgeByMap1("Tony") == null
        assert calc.getAgeByMap1("Amy") == null

        // 基于?.安全引用操作符的方法
        assert calc.getAgeByMap2("Bob") == 38
        assert calc.getAgeByMap2("Tony") == null
        assert calc.getAgeByMap2("Lucy") == null
    }

}

class Calc {

    Map map = [
        Aaron: [ age:23, sex:"man" ],
        Bob: [ age:38, sex:"woman" ],
        Tony: [ job:"teacher" ]
    ]

    /**
     * 静态方法。方法未使用可见性修饰符, 则默认使用public
    */
    static boolean getInfo1() {
        def list = [1,3]
        // 无return语句默认返回最后一行语句的计算结果
        list.add(2)
    }

    /**
     * 静态方法
     */
    static List getInfo2() {
        def list = [1,3]
        list.add(2)
        // 使用return语句返回指定结果
        return list
    }

    /**
     * 无返回值的方法
     */
    void getInfo3() {
        def temp = "This is getInfo Method"
    }

    def sum(int a,int b) {
        return a+b
    }

    int sum(int a, int b, int c) {
        return a+b+c
    }

    /**
     * 定义一个Map用来接收命名参数
     * @param map
     * @return
     */
    int sum(Map map) {
        List varList = ["a", "b", "c"]
        // 对于未设置值的参数使用默认值0
        varList.each { map.get(it, 0) }
        def temp = 0
        map.each { temp += it.value}
        return temp
    }

    def add(Object foo) {
        return "add method: #1"
    }

    def add(Integer foo) {
        return "add method: #2"
    }

    Integer getAgeByMap1(String name) {
        Map map1 = map.get(name)
        // 对map1进行判空, 防止出现NPE
        if( map1 ) {
            return map1.get("age")
        }
        return null
    }

    /**
     * 通过?.安全引用运算符进行方法调用可以避免NPE
     * 当?.之前的引用为null时, 将不会进行方法调用, 并直接返回null
     * @param name
     * @return
     */
    Integer getAgeByMap2(String name) {
        return map?.get(name)?.get("age")
    }
}