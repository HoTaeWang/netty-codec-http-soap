package npetzall.example.priceservice;

import npetzall.search.*;
import npetzall.sec.ClientType;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.math.BigDecimal;

@WebService(endpointInterface = "npetzall.search.PriceSearchEndpoint")
public class PriceSearchServer implements PriceSearchEndpoint {

    private final ObjectFactory searchFactory = new ObjectFactory();

    @Override
    public ResultType search(@WebParam(name = "Search", targetNamespace = "http://npetzall/search", partName = "body") SearchType body, @WebParam(name = "Client", targetNamespace = "http://npetzall/sec", header = true, partName = "client") ClientType client) {
        ResultType resultType = searchFactory.createResultType();
        ItemType item = searchFactory.createItemType();
        item.setName("Ball");
        item.setCategory("Sports");
        item.setPrice(new BigDecimal(250));
        resultType.getItem().add(item);
        return resultType;
    }

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9999/pricesearch", new PriceSearchServer());
    }
}
