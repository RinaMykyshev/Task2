package com.example.mud.controller;

import java.util.List;
import java.util.Scanner;
import com.example.mud.player.Player;
import com.example.mud.room.Room;
import com.example.mud.item.Item;

public class MUDController {

    private final Player player;
    private boolean running;

    public MUDController(Player player) {
        this.player = player;
        this.running = true;
    }

    public void runGameLoop() {
        Scanner in = new Scanner(System.in);
        System.out.println("Добро пожаловать в текстовую игру! Введите 'помощь' для списка команд.");

        while (running) {
            System.out.print("> ");
            String input = in.nextLine().trim();
            handleInput(input);
        }

        in.close();
        System.out.println("Игра завершена. Спасибо за игру!");
    }

    public void handleInput(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        String argument = parts.length > 1 ? parts[1] : "";

        switch (command) {
            case "осмотреться":
                lookAround();
                break;
            case "идти":
                move(argument);
                break;
            case "взять":
                if (argument.startsWith("предмет ")) {
                    pickUp(argument.substring(8));
                } else {
                    System.out.println("Неизвестная команда.");
                }
                break;
            case "инвентарь":
                checkInventory();
                break;
            case "помощь":
                showHelp();
                break;
            case "выход":
                running = false;
                break;
            default:
                System.out.println("Неизвестная команда. Введите 'помощь' для списка команд.");
        }
    }

    private void lookAround() {
        Room currentRoom = player.getCurrentRoom();
        System.out.println(currentRoom.describe());
    }

    private void move(String direction) {
        Room currentRoom = player.getCurrentRoom();
        Room nextRoom = currentRoom.getConnection(direction);

        if (nextRoom != null) {
            player.setCurrentRoom(nextRoom);
            System.out.println("Вы пошли " + direction + ".");
            lookAround();
        } else {
            System.out.println("Вы не можете пойти в этом направлении!");
        }
    }

    private void pickUp(String itemName) {
        Room currentRoom = player.getCurrentRoom();
        Item itemToPick = null;

        for (Item item : currentRoom.getItems()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                itemToPick = item;
                break;
            }
        }

        if (itemToPick != null) {
            currentRoom.removeItem(itemToPick);
            player.addItemToInventory(itemToPick);
            System.out.println("Вы взяли " + itemName + ".");
        } else {
            System.out.println("Предмет '" + itemName + "' не найден!");
        }
    }

    private void checkInventory() {
        List<Item> inventory = player.getInventory();
        if (inventory.isEmpty()) {
            System.out.println("Ваш инвентарь пуст.");
        } else {
            System.out.println("Ваш инвентарь:");
            for (Item item : inventory) {
                System.out.println("- " + item.getName());
            }
        }
    }

    private void showHelp() {
        System.out.println("Доступные команды:");
        System.out.println("осмотреться - Описать текущую комнату.");
        System.out.println("идти <направление> - Двигаться в указанном направлении (например, вперед, назад).");
        System.out.println("взять предмет <название> - Взять предмет.");
        System.out.println("инвентарь - Показать предметы в инвентаре.");
        System.out.println("помощь - Показать этот список команд.");
        System.out.println("выход - Выйти из игры.");
    }
}
