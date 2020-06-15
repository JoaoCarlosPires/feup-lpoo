public class FerengiClient extends AlienClient {
    public FerengiClient() {
        super();
    }

    @Override
    protected OrderingStrategy createOrderingStrategy() {
        SmartStrategy ss = new SmartStrategy();
        return ss;
    }
}
