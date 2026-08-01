package lesson9.hw;

public class Main {
    public static void main(String[] args) {

        SportCar lamborghini = new SportCar(4, "Red", "Carbon", 600, "Robot");
        System.out.println("\nLamborghini = " + lamborghini);
        lamborghini.move();
        lamborghini.startWithTwoPedals();
        lamborghini.stopOnParking();

        RemoteControl remoteControl = new RemoteControl("Cable", 15);
        ChildCar miniBMW = new ChildCar(4, "Blue", "Plastic", remoteControl);
        System.out.println("\nminiBMW = " + miniBMW);
        miniBMW.move();
        miniBMW.playKidsMusic();
        miniBMW.stopOnParking();

        RemovalBucket removalBucket = new RemovalBucket(3, "Steel");
        SnowRemovalCar snowBobCat = new SnowRemovalCar(4, "White", "Steel", removalBucket);
        System.out.println("\nsnowBobCat = " + snowBobCat);
        snowBobCat.move();
        snowBobCat.startSnowRemoval();
        snowBobCat.stopOnParking();

    }
}
