/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Heiko
 */
public class MathTest {
   
   public MathTest() {
   }

   @BeforeClass
   public static void setUpClass() throws Exception {
   }

   @AfterClass
   public static void tearDownClass() throws Exception {
   }
   
   @Before
   public void setUp() {
   }
   
   @After
   public void tearDown() {
   }

   /**
    * Test of add method, of class Math.
    */
   @Test
   public void testAdd() {
      System.out.println("add");
      int a = 2;
      int b = 3;
      Math instance = new Math();
      int expResult = 5;
      int result = instance.add(a, b);
      assertEquals(expResult, result);
   }
   /*
   @Test
   public void testMultiply() {
      System.out.println("mulitply");
      Math instance = new Math();
      int res = instance.multiply("a", "b");
      assertEquals(6, res);
   }
    * */
}
