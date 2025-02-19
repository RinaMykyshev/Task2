package org.example;

import org.example.Item;
import org.example.MUDController;
import org.example.Player;
import org.example.Room;

public class Main {
    public static void main(String[] args) {
        // Create rooms
        Room startingRoom = new Room("Starting Room", "A small stone chamber.");
        Room nextRoom = new Room("Next Room", "A dark, eerie hallway.");

        startingRoom.addConnection("forward", nextRoom);
        nextRoom.addConnection("back", startingRoom);


        startingRoom.addItem(new Item("sword"));
        startingRoom.addItem(new Item("shield"));

        Player player = new Player(startingRoom);

        MUDController controller = new MUDController(player);
        controller.runGameLoop();
    }
}