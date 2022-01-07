package com.aaron.OOPDemo

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.TupleConstructor
import groovy.transform.builder.Builder


//@ToString   // 生成toString方法
//@EqualsAndHashCode  // 生成equals, hashcode方法
@TupleConstructor
//@Builder
class Pc {
    String brand

    Integer num;

    /**
     * 重载加法运算符 +
     * @param other
     * @return
     */
    Pc plus(Pc other) {
        if( !other || !other.brand || !this.brand
            || this.brand!=other.brand )  {
            throw new IllegalArgumentException("参数校验失败")
        }

        int num1 = this.num ?: 0
        int num2 = other.num ?: 0
        int resultNum = num1+num2

        return new Pc()
    }

}

class TestPc{
    static void main(String[] args) {
        Pc pc1 = new Pc()
        Pc pc2 = new Pc("Dell")
        Pc pc3 = new Pc("Dell", 3)
        Pc pc4 = new Pc(brand: "HP")
        Pc pc5 = new Pc(brand: "HP", num:5)

    }
}

