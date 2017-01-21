package pl.com.bottega.ecommerce.sales.domain.invoicing;

import org.junit.Test;
import org.powermock.api.support.membermodification.MemberModifier;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductData;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductDataBuilder;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
import pl.com.bottega.ecommerce.sharedkernel.Money;

import java.util.Currency;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BookKeeperTest {

    @Test
    public void bookKeeperHappyPath() throws IllegalAccessException {
        //Given
        final BookKeeper bookKeeper = new BookKeeper();
        setupInvoiceFactoryInBookKeeper(bookKeeper);

        final Currency currency = Currency.getInstance("USD");
        final int quantity = 2;
        final Money productPrice = new Money(5, currency);

        final ProductData productData = new ProductDataBuilder()
                .setProductId(new Id("productId"))
                .setName("productName")
                .setPrice(productPrice)
                .setSnapshotDate(new Date())
                .setType(ProductType.STANDARD).createProductData();

        final Money totalCost = productPrice.multiplyBy(quantity);

        final RequestItem requestItem = new RequestItemBuilder()
                .setProductData(productData)
                .setQuantity(quantity)
                .setTotalCost(totalCost)
                .createRequestItem();

        final ClientData client = new ClientData(new Id("clientId"), "clientName");

        final InvoiceRequest invoiceRequest = new InvoiceRequestBuilder()
                .setClient(client)
                .setRequestItems(requestItem)
                .createInvoiceRequest();

        final Money taxAmount = new Money(9, currency);
        final TaxPolicyStub taxPolicy = new TaxPolicyStub(taxAmount);

        //When
        final Invoice issuance = bookKeeper.issuance(invoiceRequest, taxPolicy);


        //Then
        assertThat(issuance.getClient().getAggregateId(), is(equalTo(new Id("clientId"))));
        assertThat(issuance.getClient().getName(), is(equalTo("clientName")));

        final Money expectedNet = productPrice.multiplyBy(quantity);
        assertThat(issuance.getNet(), is(equalTo(expectedNet)));

        final Money expectedGross = expectedNet.add(taxAmount);
        assertThat(issuance.getGros(), is(equalTo(expectedGross)));
    }

    private void setupInvoiceFactoryInBookKeeper(BookKeeper bookKeeper) throws IllegalAccessException {
        MemberModifier.field(BookKeeper.class, "invoiceFactory").set(bookKeeper, new InvoiceFactory());
    }
}