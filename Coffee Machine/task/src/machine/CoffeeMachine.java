package machine;

import java.util.Scanner;


// Simple virtual coffee machine project as part of jetbrains academy java basics course

public class CoffeeMachine {

    public static void main(String[] Args) {

        CoffeeEngine machine = new CoffeeEngine();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            machine.handleInput(scanner.nextLine());
        }
    }
}

class CoffeeEngine {

    enum state {
        MAINMENU,
        BUYMENU,
        FILLMENU,

    }
    private String fillItem = "none";
    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;
    private state currentState = state.MAINMENU;

    public CoffeeEngine(){
        this.water = 400;
        this.milk = 540;
        this.beans = 120;
        this.cups = 9;
        this.money = 550;
        mainMenu();

    }

    public void handleInput(String input){
        //System.out.println(input);
        switch (this.currentState) {
            case MAINMENU:
                mainMenuActions(input);
                break;
            case BUYMENU:
                buyMenuActions(input);
                break;
            case FILLMENU:
                fillMenuAction(input);
                break;
        }
    }

    public void mainMenu() {
        System.out.println("Write action (buy, fill, take, remaining, exit): ");
    }

    public void mainMenuActions(String input) {
        //System.out.println("mainmenu" + input);

        switch (input) {
            case "buy":
                buyMenu();
                break;
            case "fill":
                //System.out.println("case fill");
                fillMenu();
                break;
            case "take":
                take();
                break;
            case "remaining":
                getResources();
                break;
            case "exit":
                System.exit(0);
                break;
        }
    }

    public void buyMenu() {
        this.currentState = state.BUYMENU;
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");

        }
    public void buyMenuActions(String input) {
        switch (input) {
            case "1":
                checkingredient("espresso");
                break;
            case "2":
                checkingredient("latte");
                break;
            case "3":
                checkingredient("cappuccino");
                break;
            case "back":
                this.currentState = state.MAINMENU;
                mainMenu();
        }
    }

    public void checkingredient(String type) {

        String outOf = "";

        switch (type) {
            case "espresso":
                //System.out.println("espresso");
                if(this.water < 250) {
                    outOf = "water";
                } else if(this.beans < 16) {
                    outOf = "beans";
                }
                break;
            case "latte":
                //System.out.println("latte");
                if(this.water < 350) {
                    outOf = "water";
                } else if(this.milk < 75) {
                    outOf = "milk";
                } else if(this.beans < 20) {
                    outOf = "beans";
                }
                break;
            case "cappuccino":
                //System.out.println("Cappuccino");
                if(this.water < 200) {
                    outOf = "water";
                } else if(this.milk < 100) {
                    outOf = "milk";
                } else if(this.beans < 12) {
                    outOf = "beans";
                }
                break;
        }

        if(this.cups < 1) {
            outOf = "cups";
        }

        if(outOf.equals("")) {
            serveCoffee(type);
        } else {
            System.out.println("Not enough " + outOf);
            this.currentState = state.MAINMENU;
            mainMenu();
        }
    }

    public void serveCoffee(String type) {

        switch (type) {
            case "espresso":
                //System.out.println("espresso");
                this.water -= 250;
                this.beans -= 16;
                this.money += 4;
                break;
            case "latte":
                //System.out.println("latte");
                this.water -= 350;
                this.milk -= 75;
                this.beans -= 20;
                this.money += 7;
                break;
            case "cappuccino":
                //System.out.println("Cappuccino");
                this.water -= 200;
                this.milk -= 100;
                this.beans -= 12;
                this.money += 6;
                break;
        }
        this.cups -= 1;
        System.out.println("I have enough resources, making you a coffee!");
        this.currentState = state.MAINMENU;
        mainMenu();
    }

    public void fillMenu() {

        currentState = state.FILLMENU;

        switch (fillItem) {
            case "none":
                fillItem = "water";
                System.out.println("Write how many ml of water you want to add: ");
                break;
            case "water":
                fillItem = "milk";
                System.out.println("Write how many ml of milk you want to add: ");
                break;
            case "milk":
                fillItem = "beans";
                System.out.println("Write how many grams of coffee beans you want to add: ");
                break;
            case "beans":
                fillItem = "cups";
                System.out.println("Write how many disposable cups of coffee you want to add: ");
                break;
        }
    }

    public void fillMenuAction(String input){

        int amount = Integer.parseInt(input);
        switch (fillItem) {
            case "water":
                this.water += amount;
                break;
            case "milk":
                this.milk += amount;
                break;
            case "beans":
                this.beans += amount;
                break;
            case "cups":
                this.cups += amount;
                break;
        }
        if (this.fillItem.equals("cups")) {
            this.currentState=state.MAINMENU;
            mainMenu();
        } else {
            fillMenu();
        }
    }

    public void take() {
        System.out.println("I gave you $" + this.money);
        this.money = 0;
    }

    public void getResources() {
        System.out.println();
        System.out.println("The coffee machine has:");
        System.out.println(this.water + " ml of water");
        System.out.println(this.milk + " ml of milk");
        System.out.println(this.beans + " g of coffee beans");
        System.out.println(this.cups + " disposable cups");
        System.out.println("$" + this.money + " of money");
        System.out.println();
        this.currentState = state.MAINMENU;
        mainMenu();
    }

}