package pl.com.bottega.ecommerce.sales.domain.invoicing;

import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
import pl.com.bottega.ecommerce.sharedkernel.Money;

public class TaxPolicyStub implements TaxPolicy{
    @Override
    public Tax calculateTax(ProductType productType, Money net) {
        return new Tax(net, "tax_description");
    }
}
