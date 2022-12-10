package com.cjufi.bookmanagement.service.impl;

import com.cjufi.bookmanagement.model.PurchaseHistory;
import com.cjufi.bookmanagement.repository.PurchaseHistoryRepo;
import com.cjufi.bookmanagement.repository.projection.PurchaseItem;
import com.cjufi.bookmanagement.service.PurchaseHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class PurchaseHistoryServiceImplementation implements PurchaseHistoryService {

    private PurchaseHistoryRepo purchaseHistoryRepo;

    @Override
    public PurchaseHistory savePurchaseHistory(PurchaseHistory purchaseHistory) {
        purchaseHistory.setPurchaseTime(LocalDateTime.now());
        return purchaseHistoryRepo.save(purchaseHistory);
    }

    @Override
    public List<PurchaseItem> findPurchasedItemsOfUser(Long userId) {
        return purchaseHistoryRepo.findAllPurchasesOfUser(userId);
    }
}
