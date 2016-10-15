/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module;

import org.openide.util.Lookup;

public class Calculator {
   public int add(int a, int b) {
      CalculatorService calc = Lookup.getDefault().lookup(CalculatorService.class);
      return calc.add(a, b);
   }
}
