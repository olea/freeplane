/*
 *  Freeplane - mind map editor
 *  Copyright (C) 2018 dimitry
 *
 *  This file author is dimitry
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.freeplane.plugin.collaboration.client.event.content;

import org.freeplane.features.map.NodeModel;
import org.freeplane.plugin.collaboration.client.event.batch.Updates;

/**
 * @author Dimitry Polivaev
 * Jan 2, 2018
 */
public class CoreUpdateGenerator {

	private final Updates updates;
	private final ContentUpdateEventFactory factory;

	public CoreUpdateGenerator(Updates updates, ContentUpdateEventFactory factory) {
		this.updates = updates;
		this.factory = factory;
	}

	public void onCoreUpdate(NodeModel node) {
			updates.addUpdateEvent(node.createID(), () -> factory.createCoreUpdatedEvent(node));
	}
}
