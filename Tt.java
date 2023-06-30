import javax.swing.*;
import java.awt.*;

public class Tt extends JFrame {
    private static final int BOARD_SIZE = 3;
    private JPanel boardPanel;
    private JButton[][] buttons;
    private String currentPlayer = "X";

    public Tt() {

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));

        buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                JButton button = new JButton();

                button.addActionListener(e -> {
                    JButton clickedButton = (JButton) e.getSource();
                    clickedButton.setText(currentPlayer);
                    clickedButton.setEnabled(false);
                    if (checkForWin(currentPlayer)) {
                        JOptionPane.showMessageDialog(Tt.this, "Player " + currentPlayer + " wins!");
                        for (JButton[] buttonRow : buttons) {
                            for (JButton b : buttonRow) {
                                b.setEnabled(false);
                            }
                        }
                    } else {
                        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
                    }
                });

                buttons[row][col] = button;
                boardPanel.add(button);
            }
        }

        getContentPane().add(boardPanel);

    }


    private boolean checkForWin(String player) {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (buttons[row][0].getText().equals(player) &&
                    buttons[row][1].getText().equals(player) &&
                    buttons[row][2].getText().equals(player)) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (buttons[0][col].getText().equals(player) &&
                    buttons[1][col].getText().equals(player) &&
                    buttons[2][col].getText().equals(player)) {
                return true;
            }
        }

        // Check diagonals
        if (buttons[0][0].getText().equals(player) &&
                buttons[1][1].getText().equals(player) &&
                buttons[2][2].getText().equals(player)) {
            return true;
        }

        if (buttons[0][2].getText().equals(player) &&
                buttons[1][1].getText().equals(player) &&
                buttons[2][0].getText().equals(player)) {
            return true;
        }

        return false;
    }
    public static void main(String[] args) {

        try {

            Tt TicTacToe = new Tt();
            TicTacToe.setTitle("Tic-Tac-Toe");
            TicTacToe.setSize(600, 600);
            TicTacToe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            TicTacToe.setVisible(true);

        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }
}
