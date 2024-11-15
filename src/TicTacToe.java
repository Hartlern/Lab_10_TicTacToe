//START GAME
//
//        REPEAT
//        INITIALIZE board to empty spaces
//        SET currentPlayer to "X"
//        SET gameOver to false
//
//        WHILE gameOver is false:
//        DISPLAY the current board
//        ASK currentPlayer to make a move (row and column)
//        IF the move is valid:
//        PLACE currentPlayer's symbol on the board at the given position
//        IF currentPlayer has won:
//        DISPLAY "currentPlayer wins"
//        SET gameOver to true
//        ELSE IF the game is a tie:
//        DISPLAY "Tie"
//        SET gameOver to true
//        ELSE:
//        SWITCH to the next player
//        ELSE:
//        DISPLAY "Invalid Move. Try again"
//
//        ASK if the player wants to play again (Y/N)
//        UNTIL player chooses not to play again
//
//        DISPLAY "Goodbye"
//        END GAME
//
//
//        FUNCTION clearBoard:
//        FOR each row and column on the board:
//        SET the cell to an empty space
//
//        FUNCTION display:
//        PRINT the board with row and column numbers
//
//        FUNCTION isValidMove(row, col):
//        RETURN true if the cell is empty, else false
//
//        FUNCTION isWin(player):
//        RETURN true if the player has won (by row, column, or diagonal)
//
//        FUNCTION isRowWin(player):
//        RETURN true if any row is completely filled by the player
//
//        FUNCTION isColWin(player):
//        RETURN true if any column is completely filled by the player
//
//        FUNCTION isDiagonalWin(player):
//        RETURN true if any diagonal is completely filled by the player
//
//        FUNCTION isTie:
//        RETURN true if all spaces are filled and there's no winner
import java.util.Scanner;

public class TicTacToe
{
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ ROW ][ COL ];
    private static String currentPlayer = "X";
    private static boolean gameOver = false;
    private static Scanner fscanner = new Scanner( System.in );

    public static void main( String[] args )
    {
        int moveCounter = 0;
        do
        {
            //clear the board for each new game
            clearBoard();
            gameOver = false;

            //while game is not over starts as false
            while( !gameOver )
            {
                display();//display that is constant;ly updated
                System.out.println( "Player " + currentPlayer + " make your move" );
                int row;
                int col;
                Scanner scan = new Scanner( System.in );
                // Use getRangedInt to ensure the player enters a valid row and column (1-3)
                row = SafeInput.getRangedInt( scan, "Enter row", 1, 3 ) - 1; //made it 0,1,2
                col = SafeInput.getRangedInt( scan, "Enter column", 1, 3 ) - 1;

                if( isValidMove( row, col ) )
                {//is valid move between ranged int-1
                    // Make the move for the current player
                    board[ row ][ col ] = currentPlayer;
                    moveCounter = moveCounter + 1;
                    System.out.println("Move: " + moveCounter);

                    // Check if player has won(couldnt figure out checking this after 5 moves)
                    if( isWin( currentPlayer ) )
                    {
                        moveCounter = 0;
                        display();
                        System.out.println( "Player " + currentPlayer + " wins!" );
                        gameOver = true;
                    }
                    // Check if the game is a tie
                    else if( isTie() )
                    {
                        display();
                        System.out.println( "It's a tie!" );
                        moveCounter = 0;
                        gameOver = true;
                    }
                    // Switch to the other player
                    else
                    {
                        if( currentPlayer.equals( "X" ) )
                        {
                            currentPlayer = "O";
                        }
                        else
                        {
                            currentPlayer = "X";
                        }
                    }
                }
                else//if not valid move
                {
                    System.out.println( "That move is invalid." );
                    System.out.println( "input valid move:" );
                }
            }

            // Ask the user if they want to play again
        } while( SafeInput.getYNConfirm( fscanner , "Do you want to play again? (Y/N)" ) );

        System.out.println( "Thanks for playing! Goodbye!" );
    }

    // Clears the board by setting all elements to a space
    private static void clearBoard()
    {
        for( int row = 0; row < ROW; row++ )
        {
            for( int col = 0; col < COL; col++ )
            {
                board[ row ][ col ] = " ";  // Set each spot to a blank space
            }
        }
    }

    // Displays the current state of the Tic-Tac-Toe board
    private static void display()
    {
        System.out.println( "    1   2   3" );
        for( int i = 0; i < 3; i++ )
        {
            System.out.print( i + 1 );  // Row index (1-3)
            for( int j = 0; j < 3; j++ )
            {
                System.out.print( " " + board[ i ][ j ] );  // Print each board element
                if( j < 2 )
                {
                    System.out.print( "  |" );  // Print column separator
                }
            }
            System.out.println();
            if( i < 2 )
            {
                System.out.println( "   -----------" );
            }
        }
    }

    private static boolean isValidMove( int row, int col )
    {
        return board[ row ][ col ].equals( " " );
    }

    // Checks if the specified player has won
    private static boolean isWin( String player )
    {
        if( isColWin( player ) || isRowWin( player ) || isDiagnalWin( player ) )
        {
            return true;
        }
        return false;
    }

    // Checks if the specified player has won by completing a row
    private static boolean isRowWin( String player )
    {
        for( int row = 0; row < ROW; row++ )
        {
            if( board[ row ][ 0 ].equals( player ) && board[ row ][ 1 ].equals( player ) && board[ row ][ 2 ].equals( player ) )
            {
                return true;
            }
        }
        return false;
    }

    // Checks if the specified player has won by completing a column
    private static boolean isColWin( String player )
    {
        for( int col = 0; col < COL; col++ )
        {
            if( board[ 0 ][ col ].equals( player ) && board[ 1 ][ col ].equals( player ) && board[ 2 ][ col ].equals( player ) )
            {
                return true;
            }
        }
        return false;
    }

    // Checks if the specified player has won by completing a diagonal
    private static boolean isDiagnalWin( String player )
    {
        // Check the main diagonal (top-left to bottom-right)
        if( board[ 0 ][ 0 ].equals( player ) && board[ 1 ][ 1 ].equals( player ) && board[ 2 ][ 2 ].equals( player ) )
        {
            return true;
        }
        // Check the anti-diagonal (top-right to bottom-left)
        if( board[ 0 ][ 2 ].equals( player ) && board[ 1 ][ 1 ].equals( player ) && board[ 2 ][ 0 ].equals( player ) )
        {
            return true;
        }
        return false;
    }

    // Checks if the game is a tie (all spaces filled and no winner)
    private static boolean isTie()
    {
        for( int i = 0; i < 3; i++ )
        {
            for( int j = 0; j < 3; j++ )
            {
                // If there's an empty space, the game is not a tie
                if( board[ i ][ j ].equals( " " ) )
                {
                    return false;
                }
            }
        }
        // If all spaces are filled and no winner, it's a tie
        return true;
    }
}