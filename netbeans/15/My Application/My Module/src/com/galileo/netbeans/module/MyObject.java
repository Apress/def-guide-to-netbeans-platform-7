package com.galileo.netbeans.module;

import java.util.Hashtable;
import javax.swing.undo.StateEditable;

public class MyObject implements StateEditable{
   
   private String prop = new String("init value");

   public void storeState(Hashtable<Object, Object> props) {
      props.put("prop", prop);
   }
   
   public void restoreState(Hashtable<?, ?> props) {
      prop = (String)props.get("prop");
   }
   
   public void setProp(String value) {
      prop = value;
   }
   
   public String getProp() {
      return(prop);
   }
}
