package com.courier.trackmonitor.utils.sectioned_adapter;

/**
 * Created by basselchaitani on 2019-11-22.
 */

import androidx.recyclerview.widget.GridLayoutManager;

/**
 * A SpanSizeLookup to draw section headers or footer spanning the whole width of the RecyclerView
 * when using a GridLayoutManager
 */
public class SectionedSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    protected SectionedRecyclerViewAdapter<?, ?, ?> adapter = null;
    protected GridLayoutManager layoutManager = null;

    public SectionedSpanSizeLookup(SectionedRecyclerViewAdapter<?, ?, ?> adapter, GridLayoutManager layoutManager) {
        this.adapter = adapter;
        this.layoutManager = layoutManager;
    }

    @Override
    public int getSpanSize(int position) {

        if(adapter.isSectionHeaderPosition(position) || adapter.isSectionFooterPosition(position)){
            return layoutManager.getSpanCount();
        }else{
            return 1;
        }

    }
}
