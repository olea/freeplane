package org.freeplane.view.swing.map.edge;

import java.awt.Color;
import java.awt.Point;

import org.freeplane.core.ui.components.UITools;
import org.freeplane.features.edge.EdgeController;
import org.freeplane.features.map.NodeModel;
import org.freeplane.features.mode.ModeController;
import org.freeplane.features.nodelocation.LocationModel;
import org.freeplane.features.nodestyle.NodeStyleController;
import org.freeplane.features.styles.AutomaticLayoutController;
import org.freeplane.features.styles.IStyle;
import org.freeplane.features.styles.MapStyleModel;
import org.freeplane.view.swing.map.MainView;
import org.freeplane.view.swing.map.MapView;
import org.freeplane.view.swing.map.NodeView;

public class AutomaticEdgeStyle {
	private NodeModel levelStyleNode;
	private EdgeController edgeController;
	public AutomaticEdgeStyle(NodeView node){
		MapView map = node.getMap();
		ModeController modeController = map.getModeController();
		AutomaticLayoutController automaticLayoutController = modeController.getExtension(AutomaticLayoutController.class);
		modeController.getExtension(NodeStyleController.class);
		
		final NodeView rootView = map.getRoot();
		final MapStyleModel mapStyleNodes = MapStyleModel.getExtension(rootView.getModel());
		final NodeModel defaultStyleNode = mapStyleNodes.getStyleNode(MapStyleModel.DEFAULT_STYLE);
		final NodeStyleController nodeStyleController = modeController.getExtension(NodeStyleController.class);
		int nodeColumnWidth = map.getZoomed(nodeStyleController.getMaxWidth(defaultStyleNode).toBaseUnitsRounded() + LocationModel.HGAP);
		Point origin = new Point();
		final MainView rootContent = rootView.getMainView();
		UITools.convertPointToAncestor(rootContent, origin, rootView);
		Point coordinate = new Point();
		final MainView nodeContent = node.getMainView();
		UITools.convertPointToAncestor(nodeContent, coordinate, rootView);
		final int distance;
		if(origin.x < coordinate.x ){
			distance = coordinate.x + nodeContent.getWidth() - origin.x - rootContent.getWidth();
		}
		else{
			distance = origin.x - coordinate.x;
		}
		int level = (int) ((float)distance / nodeColumnWidth + 0.5);
		
		final IStyle levelStyle = automaticLayoutController.getStyle(map.getModel(), level, true);
		levelStyleNode = mapStyleNodes.getStyleNode(levelStyle);
		edgeController = modeController.getExtension(EdgeController.class);
		
		
	}
	
	public Color getColor(){
		return edgeController.getColor(levelStyleNode);
	}
}
