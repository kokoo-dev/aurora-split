package com.kokoo.aurora.config;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Map;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SplitRoutingDataSource extends AbstractRoutingDataSource {

    private String writeLookUpKey;
    private String readLookUpKey;

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
    }

    @Override
    public Object determineCurrentLookupKey() {
        return TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? readLookUpKey : writeLookUpKey;
    }
}
