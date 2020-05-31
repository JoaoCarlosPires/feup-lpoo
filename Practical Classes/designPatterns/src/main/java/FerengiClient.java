public class FerengiClient extends AlienClient {
    public FerengiClient() {
        super();
    }

    protected OrderingStrategy createOrderingStrategy() {
        SmartStrategy ss = new SmartStrategy();
        return ss;
    }
}
