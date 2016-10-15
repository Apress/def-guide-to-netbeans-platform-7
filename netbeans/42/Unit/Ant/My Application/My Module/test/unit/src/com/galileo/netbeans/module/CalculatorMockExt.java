package com.galileo.netbeans.module;

import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = CalculatorService.class)
public class CalculatorMockExt implements CalculatorService {

   @Override
   public int add(int a, int b) {
      return a + b;
   }
   
}
