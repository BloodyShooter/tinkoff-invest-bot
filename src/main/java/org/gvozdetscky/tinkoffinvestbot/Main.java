package org.gvozdetscky.tinkoffinvestbot;

import lombok.extern.slf4j.Slf4j;
import org.gvozdetscky.tinkoffinvestbot.core.ApiConnector;
import org.gvozdetscky.tinkoffinvestbot.core.Parameters;
import org.gvozdetscky.tinkoffinvestbot.core.PortfolioContextProvider;

@Slf4j
public class Main {

    public static void main(String[] args) {
        Parameters params = new Parameters(args);
        try (ApiConnector conn = new ApiConnector(params)) {
            PortfolioContextProvider context = new PortfolioContextProvider(conn);
            context.getStocks();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
