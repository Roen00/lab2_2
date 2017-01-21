package pl.com.bottega.ecommerce.sales.domain.invoicing;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InvoiceRequestBuilder {
    private ClientData client;
    private List<RequestItem> requestItems;

    public InvoiceRequestBuilder setClient(ClientData client) {
        this.client = client;
        return this;
    }

    public InvoiceRequestBuilder setRequestItems(RequestItem ... requestItems) {
        this.requestItems = new ArrayList<>(Arrays.asList(requestItems));
        return this;
    }

    public InvoiceRequest createInvoiceRequest() {
        final InvoiceRequest invoiceRequest = new InvoiceRequest(client);
        requestItems.forEach(invoiceRequest::add);
        return invoiceRequest;
    }
}