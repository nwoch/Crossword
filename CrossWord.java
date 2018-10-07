// CrossWord is an interactive program that displays a crossword board and clues, 
// places the users's answers in the correct spot on the board, and allows the user
// to change their answers if necessary while updating the board

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;


// Create crossword puzzle
public class CrossWord extends JComponent {

  public static final int BOARD_SIZE = 23;
  public static final int NUMBER_CLUES = 29;

  private int[][] board;
  private String[][] board_state;
  private int[][] words;
  private static int user_choice;
  private static String user_input;
  private static String direction;

  public CrossWord() {

    user_choice = 0;
    user_input = "";
    direction = "";

    words = new int[NUMBER_CLUES][2];

    // Update words array with corresponding clue numbers
    for (int i = 0; i < NUMBER_CLUES; i++) {
      for (int j = 0; j < 2; j++) {
        if (j == 0) {
          if (i < 10) { // clues 1 - 10 across
            words[i][j] = i + 1;
          }
          else if (i >= 10 && i < 14) { // clues 10 down - 13 across
            words[i][j] = i;
          }
          else if (i >= 14 && i < 22) { // clues 13 down - 20 across
            words[i][j] = i - 1;
          }
          else if (i >= 22) { // clues 20 down to 26
            words[i][j] = i - 2;
          }
        }
      }
    }

    // Create 2D board array with each box updated either with clue number or 30 for blank and 0 for black
    // Must use 30 to account for overlapping words, so only starting letter of word is labeled with its clue number
    board = new int[BOARD_SIZE][BOARD_SIZE];
    for (int row = 0; row < BOARD_SIZE; row++) {
      for (int column = 0; column < BOARD_SIZE; column++) {
        if (row < 4 && column == 13) { // clue 1 down
          if (row == 0) {
            board[row][column] = words[0][0];
          }
          else {
            board[row][column] = 30;
          }
        }
        else if (row > 0 && row < 8 && column == 7) { // clue 2 down
          if (row == 1) {
            board[row][column] = words[1][0];
          }
          else {
            board[row][column] = 30;
          }
        }
        else if (row > 0 && row < 8 && column == 10 && row != 5) { // clue 3 down
          if (row == 1) {
            board[row][column] = words[2][0];
          }
          else {
            board[row][column] = 30;
          }
        }
        else if (row == 2 && column > 3 && column < 9 && column != 5) { // clue 4 across
          if (column == 4) {
            board[row][column] = words[3][0];
          }
          else {
            board[row][column] = 30;
          }
        }
        else if (row > 1 && row < 5 && column == 5) { // clue 5 down
          if (row == 2) {
            board[row][column] = words[4][0];
          }
          else {
            board[row][column] = 30;
          }
        }
        else if (row == 3 && column > 8 && column < 15) { // clue 6 across
          if (column == 9) {
            board[row][column] = words[5][0];
          }
          else {
            board[row][column] = 30;
          }
        }
        else if (row > 4 && row < 13 && column == 4) { // clue 7 down
          if (row == 5) {
            board[row][column] = words[6][0];
          }
          else {
            board[row][column] = 30;
          }
        }
        else if (row == 5 && column > 9 && column < 16) { // clue 8 across
          if (column == 10) {
            board[row][column] = words[7][0];
          }
          else {
            board[row][column] = 30;
          }
        }
        else if (row == 6 && column > 2 && column < 11) { // clue 9 across
          if (column == 3) {
            board[row][column] = words[8][0];
          }
          else {
            board[row][column] = 30;
          }
        }
        else if (row == 7 && column > 11 && column < 17) { // clue 10 across
          if (column == 12) {
            board[row][column] = words[9][0];
          }
          else {
            board[row][column] = 30;
          }
        }
        else if (row > 6 && row < 12 && column == 12) { // clue 10 down
          if (row == 7) {
            board[row][column] = words[10][0];
          }
          else {
            board[row][column] = 30;
          }
        }
        else if (row > 7 && row < 11 && column == 2 && row != 9) { // clue 11 down
          if (row == 8) {
            board[row][column] = words[11][0];
          }
          else {
            board[row][column] = 30;
          }
        }
        else if (row == 9 && column > 1 && column < 10) { // clue 12 across
          if (column == 2) {
            board[row][column] = words[12][0];
          }
          else {
            board[row][column] = 30;
          }
        }
        else if (row == 10 && column > 9 && column < 16 && column != 15) { // clue 13 across
          if (column == 10) {
            board[row][column] = words[13][0];
          }
          else {
            board[row][column] = 30;
          }
        }
        else if (row > 9 && row < 17 && column == 10 && row != 14) { // clue 13 down
          if (row == 10) {
            board[row][column] = words[14][0];
          }
          else {
            board[row][column] = 30;
          }
        }
        else if (row > 9 && row < 14 && column == 15 && row != 12) { // clue 14 down
          if (row == 10) {
            board[row][column] = words[15][0];
          }
          else {
            board[row][column] = 30;
          }
        }
        else if (row > 9 && row < 14 && column == 18 && row != 13) { // clue 15 down
          if (row == 10) {
            board[row][column] = words[16][0];
          }
          else {
            board[row][column] = 30;
          }
        }
        else if (row == 11 && column > 2 && column < 11 && column != 7) { // clue 16 across
          if (column == 3) {
            board[row][column] = words[17][0];
          }
          else {
            board[row][column] = 30;
          }
        }
        else if (row > 10 && row < 17 && column == 7) { // clue 17 down
          if (row == 11) {
            board[row][column] = words[18][0];
          }
          else {
            board[row][column] = 30;
          }
        }
        else if (row == 12 && column > 14 && column < 19) { // clue 18 across
          if (column == 15) {
            board[row][column] = words[19][0];
          }
          else {
            board[row][column] = 30;
          }
        }
        else if (row == 13 && column > 17 && column < 21) { // clue 19 across
          if (column == 18) {
            board[row][column] = words[20][0];
          }
          else {
            board[row][column] = 30;
          }
        }
        else if (row == 14 && column > 3 && column < 9) { // clue 20 across
          if (column == 4) {
            board[row][column] = words[21][0];
          }
          else {
            board[row][column] = 30;
          }
        }
        else if (row > 13 && row < 19 && column == 4) { // clue 20 down
          if (row == 14) {
            board[row][column] = words[22][0];
          }
          else {
            board[row][column] = 30;
          }
        }
        else if (row == 14 && column > 9 && column < 15 && column != 13) { // clue 21 across
          if (column == 10) {
            board[row][column] = words[23][0];
          }
          else {
            board[row][column] = 30;
          }
        }
        else if (row > 13 && row < 23 && column == 13) { // clue 22 down
          if (row == 14) {
            board[row][column] = words[24][0];
          }
          else {
            board[row][column] = 30;
          }
        }
        else if (row > 15 && row < 20 && column == 2) { // clue 23 down
          if (row == 16) {
            board[row][column] = words[25][0];
          }
          else {
            board[row][column] = 30;
          }
        }
        else if (row == 16 && column > 8 && column < 12) { // clue 24 across
          if (column == 9) {
            board[row][column] = words[26][0];
          }
          else {
            board[row][column] = 30;
          }
        }
        else if (row == 17 && column < 5) { // clue 25 across
          if (column == 0) {
            board[row][column] = words[27][0];
          }
          else {
            board[row][column] = 30;
          }
        }
        else if (row == 20 && column > 10 && column < 14) { // clue 26 across
          if (column == 11) {
            board[row][column] = words[28][0];
          }
          else {
            board[row][column] = 30;
          }
        }
        else {
          board[row][column] = 0;
        }
      }
    }

    // Create 2D array to maintain state of words currently on the board
    board_state = new String[BOARD_SIZE][BOARD_SIZE];
    for (int row = 0; row < BOARD_SIZE; row++) {
      for (int column = 0; column < BOARD_SIZE; column++) {
        board_state[row][column] = "";
      }
    }

  }

  // Add letters of user_input word to board_state array in corresponding position (based on clue number/user_choice)
  // First check to see if word is correct length based on the length stored in the corresponding position in the word array
  public void updateBoardState() {
    for (int row = 0; row < BOARD_SIZE; row++) {
      for (int column = 0; column < BOARD_SIZE; column++) {
        if (user_choice == board[row][column]) {
          if (user_input.length() != words[user_choice - 1][1] && words[user_choice][0] < 11) { //  user_choice: 1 - 10
            JOptionPane.showMessageDialog(null, "That word does not fit.");
          }
          else if (user_input.length() != words[user_choice][1] && words[user_choice][0] > 10 && words[user_choice][0] < 13) { // user_choice: 11 - 12
            JOptionPane.showMessageDialog(null, "That word does not fit.");
          }
          else if (user_choice == 13 && direction.equals("across") && user_input.length() != words[user_choice][1]) {
            JOptionPane.showMessageDialog(null, "That word does not fit.");
          }
          else if (user_choice == 13 && direction.equals("down") && user_input.length() != words[user_choice + 1][1]) {
            JOptionPane.showMessageDialog(null, "That word does not fit.");
          }
          else if (user_input.length() != words[user_choice + 1][1] && words[user_choice + 1][0] > 13 && words[user_choice + 1][0] < 21) { // user_choice: 14 - 20
            JOptionPane.showMessageDialog(null, "That word does not fit.");
          }
          else if (user_input.length() != words[user_choice + 2][1] && words[user_choice + 2][0] > 20 && words[user_choice + 2][0] < 27) { // user_choice: 21 - 26
            JOptionPane.showMessageDialog(null, "That word does not fit.");
          }
          // If word is correct length, store in board_state array with "0" or "1" in front to indicate if across or down
          // If word is of a clue number that is both across and down, also add a "0" or "1" at the end of word to indicate this
          else {
            if (direction.equals("across")) {
              if (user_choice == 10 || user_choice == 13 || user_choice == 20) {
                board_state[row][column + 1] = "0" + user_input + "0";
              }
              else {
                board_state[row][column] = "0" + user_input;
              }
            }
            else if (direction.equals("down")) {
              if (user_choice == 10 || user_choice == 13 || user_choice == 20) {
                board_state[row + 1][column] = "1" + user_input + "1";
              }
              else {
                board_state[row][column] = "1" + user_input;
              }
            }
          }
        }
      }
    }
  }

  public void paintComponent(Graphics g) {

    int spot_height = getHeight() / BOARD_SIZE; // box width
    int spot_width = getWidth() / BOARD_SIZE; // box height

    for (int row = 0; row < BOARD_SIZE; row++) {
      for (int column = 0; column < BOARD_SIZE; column++) {

        // Draw blank board
        if (board[row][column] == 0) {
          g.setColor(Color.BLACK);
          g.fillRect(spot_width * column, spot_height * row, spot_width, spot_height);
        }
        else {
          g.drawRect(spot_width * column, spot_height * row, spot_width, spot_height);
          // Draw clue number in upper left corner of corresponding box
          if (board[row][column] != 30) {
            g.setFont(new Font("default", Font.PLAIN, 12));
            g.drawString(Integer.toString(board[row][column]), spot_width * column + (spot_width / 15), (spot_height * row) + (spot_height / (5 / 2)));
          }
        }

        // Draw letters on board in corresponding clue boxes based on word's position in board_state array
        // Account for exceptions for words of clue numbers that are both across and down
        if (board_state[row][column].length() > 0) {
          String word = board_state[row][column].substring(1);
          for (int i = 0; i < word.length(); i++) {
            g.setFont(new Font("default", Font.BOLD, 16));
            if (board_state[row][column].substring(0, 1).equals("0")) {
              if (board_state[row][column].substring(word.length()).equals("0")) {
                g.drawString(Character.toString(word.charAt(i)), (spot_width * (column - 1)) + (spot_width / 2) + (i * spot_width), (spot_height * row) + (spot_height / 2));
              }
              else {
                g.drawString(Character.toString(word.charAt(i)), (spot_width * column) + (spot_width / 2) + (i * spot_width), (spot_height * row) + (spot_height / 2));
              }
            }
            else {
              if (board_state[row][column].substring(word.length()).equals("1")) {
                g.drawString(Character.toString(word.charAt(i)), (spot_width * column) + (spot_width / 2), (spot_height * (row - 1)) + (spot_height / 2) + (i * spot_height));
              }
              else {
                g.drawString(Character.toString(word.charAt(i)), (spot_width * column) + (spot_width / 2), (spot_height * row) + (spot_height / 2) + (i * spot_height));
              }
            }
          }
        }

      }
    }

  }

  // Update 2D array words and with word lengths of words in text file for each corresponding clue
  public void updateWordArray() {
    try {
      Scanner new_scanner = new Scanner(new File("words.txt"));
      int count = 0;
      while (new_scanner.hasNext()) {
        String word = new_scanner.next();
        int sum_letters = 0;
        for (int i = 0; i < word.length(); i++) {
          sum_letters++;
        }
        words[count][1] = sum_letters;
        count++;
      }
    }
    catch (FileNotFoundException e) {
      System.out.println("Error: " + e);
    }
  }

  // Get a string of clue numbers and clues from text file
  public static String getClues(String filename) {
    String clues_across = "Across\n";
    String clues_down = "\nDown\n";
    int value = 0;
    try {
      Scanner new_scanner = new Scanner(new File(filename));
      while (new_scanner.hasNextLine()) {
        String line = new_scanner.nextLine();
        if (line.substring(0, 2).equals("1:")) {
          value = 1;
        }
        if (value == 1) {
          clues_down += line + "\n";
        }
        else {
          clues_across += line + "\n";
        }
      }
    }
    catch (FileNotFoundException e) {
      System.out.println("Error: " + e);
    }
    return clues_across + clues_down;
  }

  // Get clue number choices from text file for user selection
  public static String[] getClueChoices(int number_clues, String filename) {
    String[] choices = new String[number_clues];
    try {
      Scanner new_scanner = new Scanner(new File(filename));
      int i = 0;
      while (new_scanner.hasNextLine()) {
        choices[i] = new_scanner.nextLine();
        i++;
      }
    }
    catch (FileNotFoundException e) {
      System.out.println("Error: " + e);
    }
    return choices;
  }


  public static void main(String[] args) {

    // Create frame containing board
    JFrame board_frame = new JFrame();

    board_frame.setSize(800, 800);
    board_frame.setTitle("CrossWord");
    board_frame.setVisible(true);
    board_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    CrossWord new_crossword = new CrossWord();
    board_frame.add(new_crossword);

    new_crossword.updateWordArray();

    // Ask for user input as long as user wants to keep playing, then exit
    // Update board_state after every input
    // Repaint board after each word entry based on current board_state array
    while (!user_input.equals("exit")) {
      new_crossword.repaint();
      String user_choice_initial = (String) JOptionPane.showInputDialog(null, getClues("clues.txt") + "\n\n" + "Choose a clue: ", "Clue Selection", JOptionPane.QUESTION_MESSAGE, null, getClueChoices(29, "cluechoices.txt"), getClueChoices(29, "cluechoices.txt")[0]);
      user_input = JOptionPane.showInputDialog("Enter a word: \n(Enter 'exit' to quit.)");

      if (user_input == null || user_choice_initial == null) {
        System.exit(0);
      }

      // Assigns direction to user_input word based on user_choice clue number
      if (user_choice_initial.equals("4") || user_choice_initial.equals("6") || user_choice_initial.equals("8") || user_choice_initial.equals("9") || user_choice_initial.equals("10 across") || user_choice_initial.equals("12") || user_choice_initial.equals("13 across") || user_choice_initial.equals("16") || user_choice_initial.equals("18") || user_choice_initial.equals("19") || user_choice_initial.equals("20 across") || user_choice_initial.equals("21") || user_choice_initial.equals("24") || user_choice_initial.equals("25") || user_choice_initial.equals("26")) {
        direction = "across";
      }
      else {
        direction = "down";
      }

      // Converts string clue number to integer
      if (user_choice_initial.equals("10 across") || user_choice_initial.equals("13 across") || user_choice_initial.equals("20 across") || user_choice_initial.equals("10 down") || user_choice_initial.equals("13 down") || user_choice_initial.equals("20 down")) {
        user_choice = Integer.parseInt(user_choice_initial.substring(0, 2));
      }
      else {
        user_choice = Integer.parseInt(user_choice_initial);
      }
      new_crossword.updateBoardState();
    }
    System.exit(0);
  }

}
