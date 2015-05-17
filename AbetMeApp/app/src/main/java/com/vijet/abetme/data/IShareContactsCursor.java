package com.vijet.abetme.data;

import android.database.Cursor;
import android.view.ActionMode;

public interface IShareContactsCursor {
    public Cursor getCursor();
    public void setActionMode(final ActionMode actionMode);
}
