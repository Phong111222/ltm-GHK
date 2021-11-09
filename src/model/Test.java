/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author PC
 */
public class Test {
    public static void main(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
                        Date currentTime = new Date();
                        String dayString = df.format(currentTime);
                        System.out.println(dayString);
    }
}
