package com.company;

import processing.core.PApplet;

// This is the Guess Which Box game
// The User is given three boxes
// One of the boxes is randomly chosen as the correct box
// The user must guess and click on the correct box to win
// Otherwise the user loses
// This is a game of chance!!!!!!! ^_^
public class MainApp extends PApplet {

    private int correct; // A number 0 or 1 or 2
    private int clicked;
    final int buttonPosX = 200;
    final int buttonPosY = 400;
    final int buttonWidth = 100;
    final int buttonHeight = 50;

    @Override
    public void setup() {
        background(255);
        fill(199,21,133); // pink!
        rect(0, 0, width/3, height/3, 15);
        fill(0, 128, 0); // green!
        rect(width/3, 0, width/3, width/3, 15);
        fill(30, 144, 255); // dodgerblue!
        rect(2*width/3, 0, width/3, width/3, 15);
        textSize(32);
        fill(0);
        text("Guess Which Box", 0 + width/5, width/3 + width/5);
        correct = chooseRandomBox();
        clicked = -1;
    }

    @Override
    public void draw() {
        if (clicked > 0 && !mousePressed) {
            fill(0);
            textSize(32);
            if (checkIfWon()) {
                text("Guessed Correctly! Yay!", 0 + width/5, width/3 + 2*width/5);
            } else {
                text("No! Better Luck Next Time!", 0, width/3 + 2*width/5);
            }
            fill(130, 130, 130);
            rect(buttonPosX, buttonPosY, buttonWidth, buttonHeight, 20);
            textSize(16);
            fill(255);
            text("Try Again?", buttonPosX+5, buttonPosY+20);
        }
        if (clicked > 0 && mousePressed) {
            if (mouseX > buttonPosX
                    && mouseX < buttonPosX+buttonWidth
                    && mouseY > buttonPosY
                    && mouseY < buttonPosY + buttonHeight) {
                System.out.println("Clicked Try Again! Let's reset!");
                setup();
                clicked = -1;
            }
        }

    }

    @Override
    public void mouseClicked() {
        if (clicked < 0) {
            if (mouseX < width / 3 && mouseY < height / 3) {
                // clicked first box
                clicked = 1;
                displayCorrectAndChosenBox();
            } else if (mouseX > width / 3 && mouseX < 2 * width / 3 && mouseY < height / 3) {
                // clicked second box
                clicked = 2;
                displayCorrectAndChosenBox();
            } else if (mouseX > 2 * width / 3 && mouseY < height / 3) {
                // clicked third box
                clicked = 3;
                displayCorrectAndChosenBox();
            }

        }
    }

    @Override
    public void settings() {
        size(500, 500);
    }

    private int chooseRandomBox() {
        return (int) (Math.random() * 3) + 1;
    }

    private boolean checkIfWon() {
        if (correct == clicked) {
            return true;
        }
        return false;
    }

    private void displayCorrectAndChosenBox() {
        System.out.println("Correct box is " + correct);
        System.out.println("Chosen box is " + clicked);
    }

    public static void main(String[] args) {
        PApplet.main("com.company.MainApp");
    }

}
