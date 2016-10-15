package com.galileo.netbeans.module;

import org.jdesktop.beansbinding.Converter;

public class ProductCategoryConverter extends Converter<Integer, String> {

   @Override
   public String convertForward(Integer arg) {
      String value = null;
      switch (arg) {
         case 1:
            value = "Category 1";
            break;
         case 2:
            value = "Category 2";
            break;
      }
      return value;
   }

   @Override
   public Integer convertReverse(String arg) {
      int value = 0;
      if ("Category 1".equals(arg)) {
         value = 1;
      } else if ("Category 2".equals(arg)) {
         value = 2;
      }
      return value;
   }
}
