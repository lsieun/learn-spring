package concert;

public class DefaultEncoreable implements Encoreable {
    @Override
    public void performEncore() {
        System.out.println("-----------------");
        System.out.println("Performing Encore");
        System.out.println("-----------------");
    }
}
