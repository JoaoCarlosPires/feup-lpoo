public class RomulanClient extends AlienClient {

    @Override
    protected OrderingStrategy createOrderingStrategy() {
        ImpatientStrategy is = new ImpatientStrategy();
        return is;
    }
}
