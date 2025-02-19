package org.example;

import org.example.Item;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public class Room {
    private String name;
    private String description;
    private List<Item> items;
    private HashMap<String, Room> connections;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.items = new ArrayList<>();
        this.connections = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void addConnection(String direction, Room room) {
        connections.put(direction, room);
    }

    public Room getConnection(String direction) {
        return connections.get(direction);
    }

    public String describe() {
        StringBuilder sb = new StringBuilder();
        sb.append("Room: ").append(name).append("\n");
        sb.append("Description: ").append(description).append("\n");
        sb.append("Items here: ");
        for (Item item : items) {
            sb.append(item.getName()).append(" ");
        }
        sb.append("\n");
        return sb.toString();
    }
}