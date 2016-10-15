package com.hboeck.mp3manager.playlist;

import com.hboeck.mp3manager.filetype.Mp3DataNode;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.view.TreeTableView;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;

public class PlaylistView extends TreeTableView {

   public PlaylistView() {
      setRootVisible(false);
      setDropTarget();
   }

   public void setDefaultActionProcessor(final ActionListener action) {
      setDefaultActionAllowed(false);
      tree.addMouseListener(new MouseAdapter() {

         @Override
         public void mouseClicked(MouseEvent me) {
            if (me.getClickCount() == 2) {
               action.actionPerformed(null);
            }
         }
      });

      treeTable.registerKeyboardAction(action, 
              KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), 
              JComponent.WHEN_FOCUSED);
   }
   
   private void setDropTarget() {
      DropTarget dt = new DropTarget(this, new DropTargetAdapter() {

         @Override
         public void dragEnter(DropTargetDragEvent dtde) {
            if(!dtde.isDataFlavorSupported(Mp3DataNode.DATA_FLAVOR)) {
               dtde.rejectDrag();
            }
         }

         @Override
         public void drop(DropTargetDropEvent dtde) {
            try {
               Mp3DataNode n = (Mp3DataNode)dtde.getTransferable().getTransferData(Mp3DataNode.DATA_FLAVOR);
               ExplorerManager.find(getParent()).getRootContext().getChildren().add(new Node[]{n});
            } catch(Exception e) {
               Exceptions.printStackTrace(e);
               dtde.rejectDrop();
            }
         }
         
      });

      setDropTarget(dt);
   }
}
