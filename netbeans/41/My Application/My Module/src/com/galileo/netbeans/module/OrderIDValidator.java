package com.galileo.netbeans.module;

import org.jdesktop.beansbinding.Validator;
import org.jdesktop.beansbinding.Validator.Result;


public class OrderIDValidator extends Validator<String> {

   @Override
   public Result validate(String value) {
      if (value.length() > 10) {
         return new Result(null, "Max length of Order ID is 10");
      }
      
      return null;
   }
}
