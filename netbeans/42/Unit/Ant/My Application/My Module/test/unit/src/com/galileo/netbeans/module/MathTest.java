package com.galileo.netbeans.module;

import org.netbeans.junit.NbTestCase;
import static junit.framework.Assert.*;


public class MathTest extends NbTestCase {
   public MathTest(String name) {
      super(name);
   }

   //@Test
   public void testAdd() {
      System.out.println("testAdd");
      int a = 3;
      int b = 4;
      Math instance = new Math();
      int expResult = 7;
      int result = instance.add(a, b);
      assertEquals(expResult, result);
   }

   //@Test
   public void testSubtract() {
      System.out.println("testSubtract");
      int a = 5;
      int b = 3;
      Math instance = new Math();
      int expResult = 2;
      int result = instance.subtract(a, b);
      assertEquals(expResult, result);
   }
   
   public void testMultiply() {
      System.out.println("testMultiply");
      Math instance = new Math();
      int expResult = 6;
      int result = instance.multiply("a", "b");
      assertEquals(expResult, result);
   }
}
