package pl.com.bottega.ecommerce.sharedkernel;

import org.junit.Test;

import java.util.Currency;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MoneyTest {

    @Test
    public void sumOf5MoneyAnd3MoneyOfTheSameCurrencyShouldGive8MoneyWithTheSameCurrency(){
        //Given
        final Currency currency = Currency.getInstance("USD");
        final Money money5 = new Money(5, currency);
        final Money money3 = new Money(3, currency);
        //When
        final Money result = money5.add(money3);
        //Then
        assertThat(result, is(new Money(8, currency)));
    }

}