package com.codedev.modernfarmer.Callbacks;

import com.codedev.modernfarmer.Entities.FeedStock;

import java.util.List;

public interface OnFeedStockRecevied {

    void onFeedStockReceived(List<FeedStock> feedStockList);
}
