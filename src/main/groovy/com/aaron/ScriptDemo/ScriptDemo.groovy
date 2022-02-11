package com.aaron.ScriptDemo

// Groovy Script 示例

println("Hello, This is a Groovy Script")

assert calc("mul", 3,7) == 21
assert calc("plus", 3,7) == 10
assert calc("other", 3,7) == -4

// 脚本内直接定义方法
def calc(String type, def num1, def num2) {
    if(type=="plus") {
        return num1 + num2
    } else if(type=='mul') {
        return num1 * num2
    } else {
        return num1 - num2
    }
}
