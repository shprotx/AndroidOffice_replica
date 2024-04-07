/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */

package kz.shprot.office.fc.hslf.record;

import kz.shprot.office.fc.util.ArrayUtil;

import java.util.ArrayList;


/**
 * Abstract class which all container records will extend. Providers
 *  helpful methods for writing child records out to disk
 *
 * @author Nick Burch
 */

public abstract class RecordContainer extends kz.shprot.office.fc.hslf.record.Record
{
	protected kz.shprot.office.fc.hslf.record.Record[] _children;
	private Boolean changingChildRecordsLock = Boolean.TRUE;

	/**
	 * Return any children
	 */
    public kz.shprot.office.fc.hslf.record.Record[] getChildRecords() { return _children; }

	/**
	 * We're not an atom
	 */
	public boolean isAnAtom() { return false; }


	/* ===============================================================
	 *                   Internal Move Helpers
	 * ===============================================================
	 */

	/**
	 * Finds the location of the given child record
	 */
	private int findChildLocation(kz.shprot.office.fc.hslf.record.Record child) {
		// Synchronized as we don't want things changing
		//  as we're doing our search
		synchronized(changingChildRecordsLock) {
			for(int i=0; i<_children.length; i++) {
				if(_children[i].equals(child)) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * Adds a child record, at the very end.
	 * @param newChild The child record to add
	 */
	private void appendChild(kz.shprot.office.fc.hslf.record.Record newChild) {
		synchronized(changingChildRecordsLock) {
			// Copy over, and pop the child in at the end
			kz.shprot.office.fc.hslf.record.Record[] nc = new kz.shprot.office.fc.hslf.record.Record[(_children.length + 1)];
			System.arraycopy(_children, 0, nc, 0, _children.length);
			// Switch the arrays
			nc[_children.length] = newChild;
			_children = nc;
		}
	}

	/**
	 * Adds the given new Child Record at the given location,
	 *  shuffling everything from there on down by one
	 * @param newChild
	 * @param position
	 */
	private void addChildAt(kz.shprot.office.fc.hslf.record.Record newChild, int position) {
		synchronized(changingChildRecordsLock) {
			// Firstly, have the child added in at the end
			appendChild(newChild);

			// Now, have them moved to the right place
			moveChildRecords( (_children.length-1), position, 1 );
		}
	}

	/**
	 * Moves <i>number</i> child records from <i>oldLoc</i>
	 *  to <i>newLoc</i>. Caller must have the changingChildRecordsLock
	 * @param oldLoc the current location of the records to move
	 * @param newLoc the new location for the records
	 * @param number the number of records to move
	 */
	private void moveChildRecords(int oldLoc, int newLoc, int number) {
		if(oldLoc == newLoc) { return; }
		if(number == 0) { return; }

		// Check that we're not asked to move too many
		if(oldLoc+number > _children.length) {
			throw new IllegalArgumentException("Asked to move more records than there are!");
		}

		// Do the move
		ArrayUtil.arrayMoveWithin(_children, oldLoc, newLoc, number);
	}


	/**
	 * Finds the first child record of the given type,
	 *  or null if none of the child records are of the
	 *  given type. Does not descend.
	 */
	public kz.shprot.office.fc.hslf.record.Record findFirstOfType(long type) {
		for(int i=0; i<_children.length; i++) {
			if(_children[i].getRecordType() == type) {
				return _children[i];
			}
		}
		return null;
	}

    /**
     * Remove a child record from this record container
     *
     * @param ch the child to remove
     * @return the removed record
     */
    public kz.shprot.office.fc.hslf.record.Record removeChild(kz.shprot.office.fc.hslf.record.Record ch) {
        kz.shprot.office.fc.hslf.record.Record rm = null;
        ArrayList<kz.shprot.office.fc.hslf.record.Record> lst = new ArrayList<kz.shprot.office.fc.hslf.record.Record>();
        for(kz.shprot.office.fc.hslf.record.Record r : _children) {
            if(r != ch) lst.add(r);
            else rm = r;
        }
        _children = lst.toArray(new kz.shprot.office.fc.hslf.record.Record[lst.size()]);
        return rm;
    }

    /* ===============================================================
	 *                   External Move Methods
	 * ===============================================================
	 */

	/**
	 * Add a new child record onto a record's list of children.
	 */
	public void appendChildRecord(kz.shprot.office.fc.hslf.record.Record newChild) {
		synchronized(changingChildRecordsLock) {
			appendChild(newChild);
		}
	}

	/**
	 * Adds the given Child Record after the supplied record
	 * @param newChild
	 * @param after
	 */
	public void addChildAfter(kz.shprot.office.fc.hslf.record.Record newChild, kz.shprot.office.fc.hslf.record.Record after) {
		synchronized(changingChildRecordsLock) {
			// Decide where we're going to put it
			int loc = findChildLocation(after);
			if(loc == -1) {
				throw new IllegalArgumentException("Asked to add a new child after another record, but that record wasn't one of our children!");
			}

			// Add one place after the supplied record
			addChildAt(newChild, loc+1);
		}
	}

	/**
	 * Adds the given Child Record before the supplied record
	 * @param newChild
	 * @param before
	 */
	public void addChildBefore(kz.shprot.office.fc.hslf.record.Record newChild, kz.shprot.office.fc.hslf.record.Record before) {
		synchronized(changingChildRecordsLock) {
			// Decide where we're going to put it
			int loc = findChildLocation(before);
			if(loc == -1) {
				throw new IllegalArgumentException("Asked to add a new child before another record, but that record wasn't one of our children!");
			}

			// Add at the place of the supplied record
			addChildAt(newChild, loc);
		}
	}

	/**
	 * Moves the given Child Record to before the supplied record
	 */
	public void moveChildBefore(kz.shprot.office.fc.hslf.record.Record child, kz.shprot.office.fc.hslf.record.Record before) {
		moveChildrenBefore(child, 1, before);
	}

	/**
	 * Moves the given Child Records to before the supplied record
	 */
	public void moveChildrenBefore(kz.shprot.office.fc.hslf.record.Record firstChild, int number, kz.shprot.office.fc.hslf.record.Record before) {
		if(number < 1) { return; }

		synchronized(changingChildRecordsLock) {
			// Decide where we're going to put them
			int newLoc = findChildLocation(before);
			if(newLoc == -1) {
				throw new IllegalArgumentException("Asked to move children before another record, but that record wasn't one of our children!");
			}

			// Figure out where they are now
			int oldLoc = findChildLocation(firstChild);
			if(oldLoc == -1) {
				throw new IllegalArgumentException("Asked to move a record that wasn't a child!");
			}

			// Actually move
			moveChildRecords(oldLoc, newLoc, number);
		}
	}

	/**
	 * Moves the given Child Records to after the supplied record
	 */
	public void moveChildrenAfter(kz.shprot.office.fc.hslf.record.Record firstChild, int number, kz.shprot.office.fc.hslf.record.Record after) {
		if(number < 1) { return; }

		synchronized(changingChildRecordsLock) {
			// Decide where we're going to put them
			int newLoc = findChildLocation(after);
			if(newLoc == -1) {
				throw new IllegalArgumentException("Asked to move children before another record, but that record wasn't one of our children!");
			}
			// We actually want after this though
			newLoc++;

			// Figure out where they are now
			int oldLoc = findChildLocation(firstChild);
			if(oldLoc == -1) {
				throw new IllegalArgumentException("Asked to move a record that wasn't a child!");
			}

			// Actually move
			moveChildRecords(oldLoc, newLoc, number);
		}
	}

    /**
     * Set child records.
     *
     * @param records   the new child records
     */
    public void setChildRecord(kz.shprot.office.fc.hslf.record.Record[] records) {
        this._children = records;
    }

	/* ===============================================================
	 *                 External Serialisation Methods
	 * ===============================================================
	 */
    /**
     * Find the records that are parent-aware, and tell them who their parent is
     */
    public static void handleParentAwareRecords(RecordContainer br) {
        // Loop over child records, looking for interesting ones
        for (kz.shprot.office.fc.hslf.record.Record record : br.getChildRecords()) {
            // Tell parent aware records of their parent
            if (record instanceof ParentAwareRecord) {
                ((ParentAwareRecord) record).setParentRecord(br);
            }
            // Walk on down for the case of container records
            if (record instanceof RecordContainer) {
                handleParentAwareRecords((RecordContainer)record);
            }
        }
    }
    
    /**
     * 
     */
    public void dispose()
    {
        if (_children != null)
        {
            for (kz.shprot.office.fc.hslf.record.Record rec : _children)
            {
                if (rec != null)
                {
                    rec.dispose();
                }
            }
            _children = null;
        }
    }


}
