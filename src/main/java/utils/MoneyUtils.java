package utils;

import domain.CurrencyType;
import domain.MoneyModel;

public class MoneyUtils {

    public static MoneyModel convert(MoneyModel money, CurrencyType toCurrency) {
        double conversionRate = getConversionRate(money.getCurrency(), toCurrency);
        double convertedAmount = money.getAmount() * conversionRate;
        return new MoneyModel(convertedAmount, toCurrency);
    }

    public static double getConversionRate(CurrencyType fromCurrency, CurrencyType toCurrency) {
        if (fromCurrency == CurrencyType.RON && toCurrency == CurrencyType.EUR) {
            return 0.2;
        }
        if (fromCurrency == CurrencyType.EUR && toCurrency == CurrencyType.RON) {
            return 4.98;
        }
        return 1.0;
    }
}
