/*
Old broken code - adding comments for the writeup.

Adding an event handler in the following fashion caused creation of a duplicate event handler each refresh for pre-existing units
causing each click to select/unselect then select/unselect/select etc. as more handlers were added.
Changing to setOnMouseClick seems to have rectified the issue.
*/

/*
	shape.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
		public void handle(MouseEvent e) {
			if (shape.getScaleX() == 2)
			{
				client.deselectUnit(unit);
				shape.setScaleX(1);
				shape.setScaleY(1);
				client.updateDebug("UNIT DESELECTED - SHRINK");
			}
			else
			{
				boolean selected = client.selectUnit(unit);
				if (selected)
				{
					deselectAllUnits();
					shape.setScaleX(2);
					shape.setScaleY(2);
					client.updateDebug("UNIT SELECTED - ENLARGE");
				}
				else
				{
					client.updateChat("GAME: Can't select opponents units");
				}
			}
		}
	});		
*/

/*
Once a number of erroneous duplicate event handlers had been constructed a single mouse click could do this;

DEBUG: Adding a unit for land 36
GAME: It is now player 5's turn

DEBUG: Selected unit at land 23
DEBUG: UNIT SELECTED - ENLARGE
DEBUG: Deselected unit at land 23
DEBUG: UNIT DESELECTED - SHRINK
DEBUG: Selected unit at land 23
DEBUG: UNIT SELECTED - ENLARGE
DEBUG: Deselected unit at land 23
DEBUG: UNIT DESELECTED - SHRINK
DEBUG: Selected unit at land 23
DEBUG: UNIT SELECTED - ENLARGE
DEBUG: Deselected unit at land 23
DEBUG: UNIT DESELECTED - SHRINK
*/


/*
I tried creating the EventHandler as a named variable (EventHandler handler = new EventHandler<MouseEvent>)
and then using that to try and call shape.removeEventHandler; moving the eventhandler back into the unit class;
creating new instances of the unitGroup and adding / removing children from it and the parent pane in various combinations; 
creating custom implementations of 'Shape' that could provide references to unit & land objects;
but nothing successfully removed the 'moved' unit from its original position in the display and simultaneously 
kept all the event handlers from duplicating until switching to setOnMouseClick instead of addEventHandler
*/