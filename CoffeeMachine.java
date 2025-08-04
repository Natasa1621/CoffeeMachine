package machine;

import java.util.Scanner;

enum CoffeeType {
	ESPRESSO(250, 0, 16, 4),
	LATTE(350, 75, 20, 7),
	CAPPUCCINO(200, 100, 12, 6);
	
	private final int water;
	private final int milk;
	private final int coffeeBeans;
	private final int money;
	
	CoffeeType(int water, int milk, int coffeeBeans, int money) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.money = money;
    }

	public int getWater() {
        return water;
    }
	public int getMilk() {
        return milk;
    }  
    public int getCoffeeBeans() {
        return coffeeBeans;
    }	    
    public int getMoney() {
        return money;
    }
}
	

public class CoffeeMachine {
    public static Scanner sc = new Scanner(System.in);
    
    private CoffeeType coffeeType;
    private int water;
    private int milk;
    private int coffeeBeans;
    private int cups;
    private int money;
    private static int countCups;
    
    public CoffeeMachine(int water, int milk, int coffeeBeans, int cups, int money) {
        super();
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.cups = cups;
        this.money = money;
    }

    public CoffeeType getCoffeeType() {
        return coffeeType;
    }
    public void setCoffeeType(CoffeeType coffeeType) {
        this.coffeeType = coffeeType;
    }
    public int getWater() {
        return water;
    }
    public void setWater(int water) {
        this.water = water;
    }
    public int getMilk() {
        return milk;
    }
    public void setMilk(int milk) {
        this.milk = milk;
    }
    public int getCoffeeBeans() {
        return coffeeBeans;
    }
    public void setCoffeeBeans(int coffeeBeans) {
        this.coffeeBeans = coffeeBeans;
    }
    public int getCups() {
        return cups;
    }
    public void setCups(int cups) {
        this.cups = cups;
    }
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }
    private void buy() {
        if (countCups >= 10) {
            System.out.println("I need cleaning!\n");
        }
        else {
            System.out.println();
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
            if (sc.hasNextInt()) {
                int coffeeCode = sc.nextInt();
                chooseCoffeType(coffeeCode);
                if (getWater() - coffeeType.getWater() >= 0 && getMilk() - coffeeType.getMilk() >= 0 && getCoffeeBeans() - coffeeType.getCoffeeBeans() >= 0 && getCups() - 1 >= 0) {
                    makeCoffee();
                }
                else {
                    String missing = "";
                    if (getWater() - coffeeType.getWater() < 0)
                        missing += " water";
                    if (getMilk() - coffeeType.getMilk() < 0)
                        missing += " milk";
                    if (getCoffeeBeans() - coffeeType.getCoffeeBeans() < 0)
                        missing += " coffee beans";
                    if (getCups() - 1 < 0)
                        missing += " cups";
                    if (!missing.equals("")) {
                        System.out.println("Sorry, not enough" + missing + "!");
                    }
                }
            }
            else if (sc.hasNext()){
                if (sc.next().equals("back"))
                    System.out.print("");
            }
            System.out.println();
        }
    }
    private void chooseCoffeType(int coffeeCode) {
        switch (coffeeCode) {
            case 1:
                setCoffeeType(CoffeeType.ESPRESSO);
                break;
            case 2:
                setCoffeeType(CoffeeType.LATTE);
                break;
            case 3:
            	setCoffeeType(CoffeeType.CAPPUCCINO);
                break;
        }
    }
    private void makeCoffee() {
        setWater(getWater() - coffeeType.getWater());
        setMilk(getMilk() - coffeeType.getMilk());
        setCoffeeBeans(getCoffeeBeans() - coffeeType.getCoffeeBeans());
        setCups(getCups() - 1);
        countCups++;
        setMoney(getMoney() + coffeeType.getMoney());
        System.out.println("I have enough resources, making you a coffee!");
    }
    private void fill() {
        System.out.println();
        System.out.println("Write how many ml of water you want to add:");
        int water = sc.nextInt();
        setWater(getWater() + water);
        System.out.println("Write how many ml of milk you want to add:");
        int milk = sc.nextInt();
        setMilk(getMilk() + milk);
        System.out.println("Write how many grams of coffee beans you want to add:");
        int coffeeBeans = sc.nextInt();
        setCoffeeBeans(getCoffeeBeans() + coffeeBeans);
        System.out.println("Write how many disposable cups you want to add:");
        int cups = sc.nextInt();
        setCups(getCups() + cups);
        System.out.println();
    }
    private void take() {
        System.out.println();
        System.out.println("I gave you $"+getMoney()+"\n");
        setMoney(0);
    }
    private void clean() {
        System.out.println("I have been cleaned!\n");
        countCups = 0;
    }
    private void remaining() {
        System.out.println();
        System.out.println(toString());
    }
    @Override
    public String toString() {
        return "The coffee machine has:\n"+water+" ml of water\n" +milk + " ml of milk\n" + coffeeBeans +
                " g of coffee beans\n" + cups + " disposable cups\n" +"$"+ money + " of money\n";
    }

    public static void main(String[] args) {
        CoffeeMachine cm = new CoffeeMachine(400, 540, 120, 9, 550);
        System.out.println("Write action (buy, fill, take, clean, remaining, exit):");
        String action = sc.nextLine();
        while (!action.equals("exit")) {
            if (action.equals("buy")) {
                cm.buy();
            }
            else if (action.equals("fill")) {
                cm.fill();
            }
            else if (action.equals("take")) {
                cm.take();
            }
            else if (action.equals("clean")) {
                cm.clean();
            }
            else if (action.equals("remaining")) {
                cm.remaining();
            }
            else if (action.equals("exit")) {
                break;
            }
            System.out.println("Write action (buy, fill, take, clean, remaining, exit):");
            action = sc.next();
        }
        sc.close();
    }
}