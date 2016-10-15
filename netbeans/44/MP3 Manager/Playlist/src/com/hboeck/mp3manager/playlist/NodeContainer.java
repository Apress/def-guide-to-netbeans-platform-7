package com.hboeck.mp3manager.playlist;

import com.hboeck.mp3manager.filetype.Mp3DataObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;
import org.apache.derby.Database;
import org.openide.nodes.Index;
import org.openide.nodes.Node;

public final class NodeContainer extends Index.ArrayChildren {

   private static final Logger LOG = Logger.getLogger(NodeContainer.class.getName());
   private List<Node> list = new ArrayList<Node>();

   @Override
   protected List<Node> initCollection() {
      return list;
   }

   public ListIterator<Mp3DataObject> getRemaining(Node current) {
      List<Mp3DataObject> v = new ArrayList<Mp3DataObject>();
      for (Node n : list.subList(indexOf(current), list.size())) {
         v.add(n.getLookup().lookup(Mp3DataObject.class));
      }
      return v.listIterator();
   }
   
   public void add(Node n) {
      add(new Node[]{n});
   }

   public void load(String id) {
      try {
         String sql = "SELECT filename FROM playlist WHERE id = ?";
         PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
         stmt.setString(1, id);
         ResultSet rs = stmt.executeQuery();
         while (rs.next()) {
            try {
               add(Mp3DataObject.find(rs.getString(1)).getNodeDelegate());
            } catch(Exception e) {}
         }
         rs.close();
         stmt.close();
      } catch(SQLException e) {
         LOG.severe(e.toString());
      }
   }
   
   public void update(String id) {
      try {
         String sql = "DELETE FROM playlist WHERE id = ?";
         PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
         stmt.setString(1, id);
         stmt.execute();
         stmt.close();

         sql = "INSERT INTO playlist (id, filename) VALUES (?, ?)";
         stmt = Database.getConnection().prepareStatement(sql);
         for(Node n : getNodes()) {
            stmt.setString(1, id);
            stmt.setString(2, n.getLookup().lookup(Mp3DataObject.class).getPrimaryFile().getPath());
            stmt.execute();
         }
         stmt.close();
      } catch(Exception e) {
         LOG.severe(e.toString());
      }
   }
}
