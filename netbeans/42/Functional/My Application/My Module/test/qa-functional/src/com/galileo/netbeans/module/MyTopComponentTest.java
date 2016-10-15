package com.galileo.netbeans.module;

import junit.framework.Test;
import org.netbeans.jellytools.JellyTestCase;
import org.netbeans.jellytools.MainWindowOperator;
import org.netbeans.jellytools.TopComponentOperator;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;
import org.netbeans.junit.NbModuleSuite;


public class MyTopComponentTest extends JellyTestCase {

   private static final String EXPECTED_RESULT = "testValue";

   public MyTopComponentTest(String name) {
      super(name);
   }

   public static Test suite() {
      return NbModuleSuite.allModules(MyTopComponentTest.class, 
              "testSetValue", "testGetValue");
   }

   public void testSetValue() {
      TopComponentOperator op =
              new TopComponentOperator("MyWindow");

      JTextFieldOperator text = new JTextFieldOperator(op, 0);
      text.setText(EXPECTED_RESULT);

      JButtonOperator button = new JButtonOperator(op, "OK");
      button.press();

      op.close();
   }

   public void testGetValue() {
      MainWindowOperator main = MainWindowOperator.getDefault();
      main.menuBar().pushMenu("Window|MyWindow");

      TopComponentOperator op =
              new TopComponentOperator("MyWindow");

      JTextFieldOperator text = new JTextFieldOperator(op, 0);
      String result = text.getText();

      assertEquals(EXPECTED_RESULT, result);
   }
}
