package com.codedev.modernfarmer.Callbacks;

import com.codedev.modernfarmer.Entities.PlacementList;

import java.util.List;

public interface PlacementListCallback {
    void onPlacementsReceived(List<PlacementList> placementList);
}
