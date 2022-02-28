package com.aaron.AnnotationDemo

import groovy.transform.Canonical
import groovy.transform.TupleConstructor
import groovy.transform.builder.Builder

/**
 * Groovy 生成代码注解 示例
 */
class GenerateDemo {
    static void main(String[] args) {
        testConstructor()
        testOther()
        testSingleton()
    }

    static void testConstructor() {
        // 使用无参构造器
        Pc pc1 = new Pc()

        // 使用位置参数构造器
        Pc pc2 = new Pc("Dell")
        Pc pc3 = new Pc("Dell", 3)
        Pc pc4 = new Pc("Dell", 3, 1999d)

        // 使用命名参数构造器
        Pc pc5 = new Pc(brand: "HP")
        Pc pc6 = new Pc(num:5, price: 288.99d)

        // 使用链式调用的构造器
        Pc pc7 = Pc.builder()
            .price(5999)
            .brand("ThinkPad")
            .num(34)
            .build();
    }

    static void testOther() {
        Pc pc1 = new Pc("Dell",2,200)
        Pc pc2 = new Pc("Dell",2,200)

        // 对象地址不一样
        assert pc1 !== pc2
        // 对象的HashCode一样
        assert pc1.hashCode() == pc2.hashCode()
        // 二者的内容一样
        assert pc1 == pc2
        assert pc1.equals(pc2)

        assert pc1.toString() == "com.aaron.AnnotationDemo.Pc(Dell, 2, 200.0)"
    }

    static void testSingleton() {
        // 通过instance属性获取单例对象
        RedisClient redisClient1 = RedisClient.instance
        RedisClient redisClient2 = RedisClient.instance

        assert redisClient1.ip == "127.0.0.1"
        assert redisClient1.port == 6379

        assert redisClient1 === redisClient2
    }
}

//@ToString   // 生成toString方法
//@EqualsAndHashCode  // 生成equals, hashcode方法
//@TupleConstructor   // 生成构造器
@Canonical  // 组合了@ToString, @EqualsAndHashCode, @TupleConstructor 注解
@Builder    // 生成支持链式调用的构造器
class Pc {
    String brand

    Integer num;

    Double price;
}

// 单例模式, lazy属性设置是否为惰性
@Singleton(lazy = true)
class RedisClient {
    String ip = "127.0.0.1"

    Integer port = 6379
}
