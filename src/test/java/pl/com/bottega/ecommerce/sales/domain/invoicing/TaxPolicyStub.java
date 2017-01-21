package pl.com.bottega.ecommerce.sales.domain.invoicing;

import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
import pl.com.bottega.ecommerce.sharedkernel.Money;

public class TaxPolicyStub implements TaxPolicy{
    private final Money taxAmount;

    public TaxPolicyStub(Money taxAmount) {
        this.taxAmount = taxAmount;
    }

    @Override
    public Tax calculateTax(ProductType productType, Money net) {
        return new Tax(taxAmount, "tax_description");
    }
}
