package com.example.demo.resBody.PurchaseHistoryResBody;
import java.util.List;

import com.example.demo.model.PurchaseHistory;

public class GetPurchaseHistoryListResBody {
    private List<PurchaseHistory> purchaseHistoryList;

    public GetPurchaseHistoryListResBody(List<PurchaseHistory> purchaseHistoryList){
        this.purchaseHistoryList = purchaseHistoryList;
    }
    public List<PurchaseHistory> getPurchaseHistoryList() {
        return purchaseHistoryList;
    }
    public void setPurchaseHistoryList(List<PurchaseHistory> purchaseHistoryList) {
        this.purchaseHistoryList = purchaseHistoryList;
    }
}
