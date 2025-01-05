package fr.tpjava;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameInventory {
    public JFrame frame;
    public DefaultListModel<String> listModel = new DefaultListModel<>();
    public JList<String> inventory = new JList<>(listModel);

    public GameInventory() {
        frame = new JFrame("Inventory");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        JTextField itemField = new JTextField();
        JTextField quantityField = new JTextField();
        inputPanel.add(new JLabel("Item :"));
        inputPanel.add(itemField);
        inputPanel.add(new JLabel("Quantité :"));
        inputPanel.add(quantityField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton addButton = new JButton("Ajouter");
        JButton removeButton = new JButton("Supprimer");
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(new JLabel("Inventaire :"), BorderLayout.NORTH);
        listPanel.add(new JScrollPane(inventory), BorderLayout.CENTER);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(listPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

        addButton.addActionListener(addButtonEvent -> {
            String itemName = itemField.getText();
            if (itemName.isEmpty() || quantityField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Veuillez remplir tous les champs.");
                return;
            }
            try {
                int quantity = Integer.parseInt(quantityField.getText());
                Inventory.addItem(itemName, quantity);
                updateDisplay();
                itemField.setText("");
                quantityField.setText("");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Quantité invalide !");
            }
        });

        removeButton.addActionListener(removeButtonEvent -> {
            String itemName = itemField.getText();
            if (itemName.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Veuillez entrer un nom d'objet.");
                return;
            }
            Inventory.removeItem(itemName);
            updateDisplay();
            itemField.setText("");
        });
    }

    public void updateDisplay() {
        listModel.clear();
        List<String> items = Inventory.displayItems();
        for (String item : items) {
            listModel.addElement(item);
        }
    }

    public static void main(String[] args) {
        new GameInventory();
    }
}
