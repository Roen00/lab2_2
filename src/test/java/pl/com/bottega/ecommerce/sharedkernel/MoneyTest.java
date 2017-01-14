package pl.com.bottega.ecommerce.sharedkernel;

import org.junit.Test;

import java.util.Currency;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MoneyTest {
    private final Currency usdCurrency = Currency.getInstance("USD");
    private final Currency gbpCurrency = Currency.getInstance("GBP");

    @Test
    public void sumOf5MoneyAnd3MoneyOfTheSameCurrencyShouldGive8MoneyWithTheSameCurrency() {
        //Given
        final Money money5 = new Money(5, usdCurrency);
        final Money money3 = new Money(3, usdCurrency);
        //When
        final Money result = money5.add(money3);
        //Then
        assertThat(result, is(new Money(8, usdCurrency)));
    }

    @Test
    public void sumCalledOnNonEmptyMoneyOfTwoMoniesWithDifferentCurrenciesButOneWithZeroValueShouldReturnMoneyWithNonZeroValue() {
        //Given
        final Money nonEmptyUsdMoney = new Money(5, usdCurrency);
        final Money emptyGbpMoney = new Money(0, gbpCurrency);
        //When
        final Money result = nonEmptyUsdMoney.add(emptyGbpMoney);
        //Then
        assertThat(result, is(new Money(5, usdCurrency)));
    }

    @Test
    public void sumCalledOnEmptyMoneyOfTwoMoniesWithDifferentCurrenciesButOneWithZeroValueShouldReturnMoneyWithNonZeroValue() {
        //Given
        final Money nonEmptyUsdMoney = new Money(5, usdCurrency);
        final Money emptyGbpMoney = new Money(0, gbpCurrency);
        //When
        final Money result = emptyGbpMoney.add(nonEmptyUsdMoney);
        //Then
        assertThat(result, is(new Money(5, usdCurrency)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void sumOfTwoNonZeroMoniesWithDifferentCurrenciesShouldThrowAnIllegalArgumentException() {
        //Given
        final Money nonEmptyUsdMoney = new Money(5, usdCurrency);
        final Money nonEmptyGbpMoney = new Money(5, gbpCurrency);
        //When
        nonEmptyUsdMoney.add(nonEmptyGbpMoney);
    }

    @Test
    public void subtractOf5MoneyAnd3MoneyOfTheSameCurrencyShouldGive2MoneyWithTheSameCurrency() {
        //Given
        final Money money5 = new Money(5, usdCurrency);
        final Money money3 = new Money(3, usdCurrency);
        //When
        final Money result = money5.subtract(money3);
        //Then
        assertThat(result, is(new Money(2, usdCurrency)));
    }

    @Test
    public void subtractCalledOnNonEmptyMoneyOfTwoMoniesWithDifferentCurrenciesButOneWithZeroValueShouldReturnMoneyWithNonZeroValue() {
        //Given
        final Money nonEmptyUsdMoney = new Money(5, usdCurrency);
        final Money emptyGbpMoney = new Money(0, gbpCurrency);
        //When
        final Money result = nonEmptyUsdMoney.subtract(emptyGbpMoney);
        //Then
        assertThat(result, is(new Money(5, usdCurrency)));
    }

    @Test
    public void subtractCalledOnEmptyMoneyOfTwoMoniesWithDifferentCurrenciesButOneWithZeroValueShouldReturnMoneyWithMinusNonZeroValue() {
        //Given
        final Money nonEmptyUsdMoney = new Money(5, usdCurrency);
        final Money emptyGbpMoney = new Money(0, gbpCurrency);
        //When
        final Money result = emptyGbpMoney.subtract(nonEmptyUsdMoney);
        //Then
        assertThat(result, is(new Money(-5, usdCurrency)));
    }

    @Test
    public void multiplyOf5MoneyBy3Give15MoneyWithTheSameCurrency() {
        //Given
        final Money money5 = new Money(5, usdCurrency);
        final double multiplier = 3;
        //When
        final Money result = money5.multiplyBy(multiplier);
        //Then
        assertThat(result, is(new Money(15, usdCurrency)));
    }

    @Test
    public void multiplyOf5MoneyByMinus1GiveNegative5MoneyWithTheSameCurrency() {
        //Given
        final Money money5 = new Money(5, usdCurrency);
        final double minus1Multiplier = -1;
        //When
        final Money result = money5.multiplyBy(minus1Multiplier);
        //Then
        assertThat(result, is(new Money(-5, usdCurrency)));
    }

    @Test
    public void multiplyOf5MoneyBy0Give0MoneyWithTheSameCurrency() {
        //Given
        final Money money5 = new Money(5, usdCurrency);
        final double minus1Multiplier = 0;
        //When
        final Money result = money5.multiplyBy(minus1Multiplier);
        //Then
        assertThat(result, is(new Money(0, usdCurrency)));
    }

    @Test
    public void greaterThanReturnTrueWhenComparing5MoneyWith4MoneyWithTheSameCurrency() {
        //Given
        final Money money5 = new Money(5, usdCurrency);
        final Money money4 = new Money(4, usdCurrency);
        //When
        final boolean result = money5.greaterThan(money4);
        //Then
        assertTrue(result);
    }
}
