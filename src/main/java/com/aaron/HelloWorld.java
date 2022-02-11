package com.aaron;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Aaron Zhu
 * @date 2022-02-09
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HelloWorld {
    private String name;

    public String hello(String otherName) {
        StringBuilder sb = new StringBuilder();
        sb.append("I'm " + name + ", ");
        sb.append( "Hi " + otherName + "~" );
        return sb.toString();
    }
}
