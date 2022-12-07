package com.cjufi.bookmanagement.service;

import com.cjufi.bookmanagement.model.PurchaseHistory;
import com.cjufi.bookmanagement.repository.projection.PurchaseItem;

import java.util.List;

public interface PurchaseHistoryService {

    PurchaseHistory savePurchaseHistory(PurchaseHistory purchaseHistory);

    List<PurchaseItem> findPurchasedItemsOfUser(Long userId);


}
