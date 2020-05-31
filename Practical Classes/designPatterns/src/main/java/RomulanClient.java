public class RomulanClient extends AlienClient {
    public RomulanClient() {
        super();
    }

    protected OrderingStrategy createOrderingStrategy() {
        ImpatientStrategy is = new ImpatientStrategy();
        return is;
    }
}
