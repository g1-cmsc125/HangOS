# 🖥️ MauradersXP

## 📖 About
**MauradersXP** is a retro, Windows XP-themed Hangman game built entirely in Java using Swing and AWT. Developed as a laboratory requirement for CMSC 125 (Operating Systems), this project reimagines the classic word-guessing game as a high-stakes piracy download game.

Instead of drawing a stick figure, players must accurately guess the characters from the concepts in the course. Each wrong character guessed, a virus pop up will appear. 
The project features:
* **Fully Clickable GUI:** A mouse-driven interface featuring a virtual keyboard, start menu, and draggable mini-windows.
* **Audio Integration:** Authentic Windows XP background music and system sounds (`javax.sound.sampled`) that react to your gameplay.
* **Dynamic Visuals:** A custom rounded gradient progress bar that calculates download completion based on string length, and a built-in `.gif` boot sequence.
* **Standalone Executable:** Compiled into a native `.exe` for seamless plug-and-play execution.

---

## 🎮 Instructions: How to Play
1.  **Boot Up:** Launch the game and wait for the splash screen to finish loading the desktop.
2.  **Open the App:** Click the **Start** button on the bottom left of the taskbar and select **Start** (or read the rules via **How To Play**).
3.  **Guess the Word:** You are attempting to securely download a hidden file. Use your mouse to click the letters on the virtual keyboard.
4.  **Consequences:**
    * **Correct Guess:** The letter is revealed in the blanks, and your download progress bar fills up.
    * **Wrong Guess:** Your system takes damage, and an annoying Windows error/virus popup will spawn on your desktop!
5.  **Win/Loss Conditions:**
    * You are only allowed **6 mistakes**.
    * If you reach 6 wrong guesses, your computer will experience a fatal system crash and reboot, failing the download.
    * Reveal the full word before your system crashes to complete the download and win!

---

## ⚙️ Instructions: How to Run and Compile

### Option A: Running the Standalone Executable (Recommended)
For the easiest experience, simply run the bundled executable file:
1.  Extract the submitted `.zip` file: `[CMSC 125-M] Laboratory Activity 2B_Barbosa.zip`
2.  Ensure the `resources` folder is in the same directory as the executable.
3.  Double-click `HangOS.exe` to play.

### Option B: Running the `.jar` File
If you are on a non-Windows machine (Mac/Linux) or prefer running the Java archive directly:
1.  Open your terminal/command prompt.
2.  Navigate to the directory containing `HangOS.jar`.
3.  Run the following command:
    ```bash
    java -jar HangOS.jar
    ```

### Option C: Compiling from the Command Line
*(Note: A detailed version of these steps is also available in `ReadMeFirst.txt`)*

To manually compile the source code and check for all dependencies:
1.  Open your terminal in the root directory of the project (where the `src` and `resources` folders are located).
2.  Compile the `.java` files into an `out` directory:
    * **Windows:**
        ```cmd
        dir /s /B src\*.java > sources.txt
        javac -d out @sources.txt
        del sources.txt
        ```
    * **Mac/Linux:**
        ```bash
        find src -name "*.java" > sources.txt
        javac -d out @sources.txt
        rm sources.txt
        ```
3.  **Crucial Step:** Manually copy your entire `resources` folder into the newly created `out` directory. The game will crash if it cannot find the database, fonts, sounds, or images.
4.  Execute the game with the splash screen argument:
    ```bash
    java -cp out -splash:src/resources/images/splash.gif Main
    ```

---

## 👨‍💻 The Developers
This project was developed by the Best CMSC 125 team:
* Angela Almazan
* Mac Alvarico
* Desirre Barbosa (Leader)
* Zsyvette Bugho
