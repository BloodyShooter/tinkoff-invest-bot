package org.gvozdetscky.tinkoffinvestbot.core;

import lombok.extern.slf4j.Slf4j;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrumentList;
import ru.tinkoff.invest.openapi.model.rest.UserAccount;

import java.util.List;

@Slf4j
public class PortfolioContextProvider {

    private ApiConnector apiConnector;
    private String brokerAccountId;

    public PortfolioContextProvider(ApiConnector apiConnector, String brokerAccountId) {
        this.apiConnector = apiConnector;
        this.brokerAccountId = brokerAccountId;
    }

    public PortfolioContextProvider(ApiConnector apiConnector) throws Exception {
        this.apiConnector = apiConnector;
        this.brokerAccountId = getBrokerAccountId();
    }

    public void getStocks() throws Exception {
        log.info("get Stocks");
        getOpenApi()
                .getPortfolioContext()
                .getPortfolio(brokerAccountId)
                .get()
                .getPositions()
                .forEach(System.out::println);
    }

    public String getBrokerAccountId() throws Exception {
        log.info("get Accounts");
        List<UserAccount> accounts = getOpenApi().getUserContext().getAccounts().get().getAccounts();
        accounts.forEach(System.out::println);
        return accounts.get(0).getBrokerAccountId();
    }

    public MarketInstrumentList getBonds() throws Exception {
        return getOpenApi().getMarketContext().getMarketBonds().join();
    }

    public MarketInstrumentList getEtfs() throws Exception {
        return getOpenApi().getMarketContext().getMarketEtfs().join();
    }

    public MarketInstrumentList getCurrencies() throws Exception {
        return getOpenApi().getMarketContext().getMarketCurrencies().join();
    }

    private OpenApi getOpenApi() throws Exception {
        return apiConnector.getOpenApi();
    }

}
