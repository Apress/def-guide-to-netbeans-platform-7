/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module2;

import com.galileo.netbeans.module.CalculatorService;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Heiko
 */
@ServiceProvider(service=CalculatorService.class)
public class CalculatorImpl implements CalculatorService {

   @Override
   public int add(int a, int b) {
      return a + b;
   }

}
