package com.galileo.netbeans.dynlookup;

import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

public class DynamicLookup extends AbstractLookup {

    private static DynamicLookup lookup = new DynamicLookup();
    private InstanceContent content = new InstanceContent();

    private DynamicLookup() {
    }

    public void add(Object instance) {
        content.add(instance);
    }

    public void remove(Object instance) {
        content.remove(instance);
    }

    public static DynamicLookup getDefault(){
        return lookup;
    }
}
