package com.galileo.netbeans.module;

import junit.framework.Test;
import org.netbeans.junit.NbTestCase;
import org.netbeans.junit.NbModuleSuite;

public class CalculatorTest extends NbTestCase {

   public CalculatorTest(String name) {
      super(name);
   }
/*
   @BeforeClass
   public static void setUpClass() throws Exception {
      MockServices.setServices(CalculatorMockInt.class);
   }
*/
   public static Test suite() {
      NbModuleSuite.Configuration config = 
         NbModuleSuite.createConfiguration(CalculatorTest.class).enableModules(".*").clusters(".*").addTest(MathTest.class).gui(false);
      //config.addTest(MathTest.class);
     return NbModuleSuite.create(config);
   }

   public void testAdd() {
      System.out.println("add");
      Calculator calc = new Calculator();
      assertEquals(calc.add(3, 5), 8);
   }

   public static final class CalculatorMockInt implements CalculatorService {

      @Override
      public int add(int a, int b) {
         return a + b;
      }
      
   }
}
