package connectFour_pvp

/************************************************************
Name:         Eliana Henderson
Date:         6/6/2022
Assignment:   Connect Four
Class Number: 282
Description:  A program that plays pvp connect 4
 ************************************************************/

val screenHeight = 8
val screenWidth = 8

fun main() {

    var menuChoice = arrayOf(
        "Yes", "No"
    )
    var quit = menuChoice.size
    var userChoice = 0

    println("Would you like to play connect 4? \n")
    while (userChoice != quit) {
        var board = Array(screenHeight) { Array(screenWidth) { "." } }
        userChoice = menu(menuChoice, "\nPlease enter a selection: ")
        if (userChoice == 1) {
            var playerWin = false
            printBoard(board)
            println()
            while (!playerWin) {
                print("Choose a column between 1 and 8: ")
                var col = readLine()!!.toInt() - 1
                var row = placeChecker("X", col, board)
                printBoard(board)
                playerWin = fourInARowHorizontal("X", row, board) || fourInARowVertical(
                    "X",
                    col,
                    board
                )
                println()
                if (playerWin) {
                    println("Player 1 (X) Wins!")
                } else {
                    print("Choose a column between 1 and 8: ")
                    var col = readLine()!!.toInt() - 1
                    var row = placeChecker("O", col, board)
                    printBoard(board)
                    playerWin = fourInARowHorizontal("O", row, board) || fourInARowVertical("O", col, board)
                    println()

                    if (playerWin) {
                        print("Player 2 (O) Wins!")
                        println()
                    }
                }
            }
            println("\n Would you like to play again?")
        }
    }
    println("\n Thanks for playing!")
}

fun placeChecker(piece: String, col: Int, board: Array<Array<String>>): Int {
    var row = board.size - 1
    var found = false
    while (row >= 0 && !found) {
        if (board[row][col] == ".") {
            board[row][col] = piece
            found = true
        }
        row--
    }
    return row
}

fun printBoard(board: Array<Array<String>>) {
    println()
    println("╔═══╦═══╦═══╦═══╦═══╦═══╦═══╦═══╗ ")
    println("║ 1 ║ 2 ║ 3 ║ 4 ║ 5 ║ 6 ║ 7 ║ 8 ║ ")
    println("╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣ ")
    for (row: Int in board.indices) {
        for (col: Int in 0 until board[row].size) {
            print("║ ${board[row][col]} ")
        }
        println("║")
    }
    println("╚═══╩═══╩═══╩═══╩═══╩═══╩═══╩═══╝")
}

fun fourInARowHorizontal(ch: String, row: Int, board: Array<Array<String>>): Boolean {
    var retBool = false

    var charCounter = 0
    var colCounter = 0

    while (colCounter < board.size) {
        if (board[row + 1][colCounter] == ch) {
            charCounter++
        } else {
            charCounter = 0
        }
        colCounter++
        if (charCounter >= 4) retBool = true
    }
    return retBool
}

fun fourInARowVertical(ch: String, col: Int, board: Array<Array<String>>): Boolean {
    var retBool = false
    var rowCounter = 0

    var charCounter = 0

    while (rowCounter < board.size) {
        if (board[rowCounter][col] == ch) {
            charCounter++
        } else {
            charCounter = 0
        }
        rowCounter++
        if (charCounter >= 4) retBool = true
    }
    return retBool
}

fun menu(menuChoice: Array<String>, prompt: String): Int {
    for ((index, choice) in menuChoice.withIndex()) {
        println("${index + 1}. ${choice}")
    }
    print(prompt)

    return readLine()!!.toInt()
}


