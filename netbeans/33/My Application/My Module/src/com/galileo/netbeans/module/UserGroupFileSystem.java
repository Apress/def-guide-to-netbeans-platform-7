package com.galileo.netbeans.module;

import java.util.List;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.MultiFileSystem;
import org.openide.filesystems.XMLFileSystem;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = FileSystem.class)
public class UserGroupFileSystem extends MultiFileSystem {

   public UserGroupFileSystem() {
      setPropagateMasks(true);
   }

   public static UserGroupFileSystem getDefault() {
      return Lookup.getDefault().lookup(UserGroupFileSystem.class);
   }

   public void setUserGroups(List<UserGroup> groups) 
           throws Exception {
      FileSystem[] fileSystems = new FileSystem[groups.size()];
      for (int idx = 0; idx < fileSystems.length; idx++) {
         fileSystems[idx] = 
                 new XMLFileSystem(groups.get(idx).getConfig());
      }
      setDelegates(fileSystems);
   }
}
