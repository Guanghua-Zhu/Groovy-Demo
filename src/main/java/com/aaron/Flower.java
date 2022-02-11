package com.aaron;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.FileNotFoundException;

/**
 * @author Aaron Zhu
 * @date 2022-02-10
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Flower {
    private String type;

    @Override
    public String toString() {
        return "This is a " + type +" Flower";
    }

    public String inspect() {
        return "[Info]: " + type;
    }

    public void calcException(int i) {
        try{
            if( i==1 ) {
                throw new FileNotFoundException();
            } else if( i==2 ) {
                throw new NullPointerException();
            }
        } catch (Exception e) {
            throw new RuntimeException( e );
        }
    }

}
