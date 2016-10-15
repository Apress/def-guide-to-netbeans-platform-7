package com.galileo.netbeans.module.graph;

import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.anchor.Anchor;
import org.netbeans.api.visual.anchor.AnchorFactory;
import org.netbeans.api.visual.anchor.AnchorShape;
import org.netbeans.api.visual.graph.GraphPinScene;
import org.netbeans.api.visual.layout.LayoutFactory;
import org.netbeans.api.visual.router.RouterFactory;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.ImageWidget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.api.visual.widget.general.IconNodeWidget;
import org.openide.util.Utilities;

public class MyGraphPinScene extends GraphPinScene<String, String, String> {
   
   private LayerWidget mainLayer;
   private LayerWidget connectionLayer;
   
   private WidgetAction moveAction = ActionFactory.createMoveAction();
   
   public MyGraphPinScene() {
      mainLayer = new LayerWidget(this);
      addChild(mainLayer);
      connectionLayer = new LayerWidget(this);
      addChild(connectionLayer);
   }
   
   protected Widget attachNodeWidget(String node) {
      IconNodeWidget widget = new IconNodeWidget(this);
      widget.setImage(Utilities.loadImage("com/galileo/netbeans/module/node.gif"));
      widget.getLabelWidget().setLayout(LayoutFactory.createHorizontalFlowLayout(LayoutFactory.SerialAlignment.JUSTIFY, 5));
      
      WidgetAction.Chain actions = widget.getActions();
      actions.addAction(createObjectHoverAction());
      actions.addAction(createSelectAction());
      actions.addAction(moveAction);
      
      mainLayer.addChild(widget);
      return widget;
   }
   
   protected Widget attachEdgeWidget(String edge) {
      ConnectionWidget widget = new ConnectionWidget(this);
      widget.setTargetAnchorShape(AnchorShape.TRIANGLE_FILLED);
      widget.setRouter(RouterFactory.createOrthogonalSearchRouter(mainLayer, connectionLayer));
      
      WidgetAction.Chain actions = widget.getActions();
      actions.addAction(createObjectHoverAction());
      actions.addAction(createSelectAction());
      
      connectionLayer.addChild(widget);
      return widget;
   }
   
   protected Widget attachPinWidget(String node, String pin) {
      ImageWidget widget = new ImageWidget(this, Utilities.loadImage("com/galileo/netbeans/module/pin.gif"));
      IconNodeWidget n = (IconNodeWidget) findWidget(node);
      n.getLabelWidget().addChild(widget);
      return widget;
   }
   
   protected void attachEdgeSourceAnchor(String edge, String oldSourcePin, String sourcePin) {
      ConnectionWidget edgeWidget = (ConnectionWidget) findWidget(edge);
      Widget sourceNodeWidget = findWidget(sourcePin);
      Anchor sourceAnchor = AnchorFactory.createRectangularAnchor(sourceNodeWidget);
      edgeWidget.setSourceAnchor(sourceAnchor);
   }
   
   protected void attachEdgeTargetAnchor(String edge, String oldTargetPin, String targetPin) {
      ConnectionWidget edgeWidget = (ConnectionWidget) findWidget(edge);
      Widget targetNodeWidget = findWidget(targetPin);
      Anchor targetAnchor = AnchorFactory.createRectangularAnchor(targetNodeWidget);
      edgeWidget.setTargetAnchor(targetAnchor);
   }
}
