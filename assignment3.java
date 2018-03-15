/*
 * Assignment 3 Deck of Cards
 * Program to set up classes that can be used in future programs involving playing card games with a human,
 * or simulating card games entirely by a computer
 * Authors: Gabriel Loring, Christian Guerrero, Jose Garcia, Grace Alvarez
 * Last Changed: March 14th, 2018
 * 
 */

import java.util.*;

public class assignment3
{  
   public static void main(String[] args)
   {
 
      System.out.println("Card Class Tests\n");
      
      //Instantiate three cards, two legally, and one illegally
      Card card1 = new Card('A', Card.Suit.spades);
      Card card2 = new Card('Z', Card.Suit.diamonds);
      Card card3 = new Card('J', Card.Suit.clubs);
      
      //Print out all three cards to confirm
      System.out.println(card1.toString());
      System.out.println(card2.toString());
      System.out.println(card3.toString());
      
      //Make good card bad by set() with an illegal value,
      //and change the initial illegal card to a legal one by setting a legal value
      card1.set('X', Card.Suit.spades);
      card2.set('K', Card.Suit.clubs);
      
      //Print out all three cards to confirm
      System.out.println();
      System.out.println(card1.toString());
      System.out.println(card2.toString());
      System.out.println(card3.toString());

   }
}      

/*------------------------------------------------------------------------------------ 
 * PHASE I: The Card Class
 * Card: The Card class has two obvious members:  value (a char)  and suit (an enum).
 * But we add a new boolean, errorFlag, which can inform a client that a card is in an
 * illegal state. We'll want the usual constructors, mutators, accessors and toString()
 * methods for the class.  We only allow standard cards, like ('A', clubs), ('9', hearts)
 * and ('T', diamonds), no jokers or other special cards.
 *------------------------------------------------------------------------------------*/
class Card
{
   //A Public enum Type with added members
   public enum Suit{clubs, diamonds, hearts, spades};
   
   //card values, T for ten
   public static final char[] cardValue = {'A', '2', '3', '4', '5', '6', '7', 
      '8', '9', 'T', 'J', 'Q', 'K'};
   
   //private member data
   private char value;
   private Suit suit;
   private boolean errorFlag;
   
   //Constructor with parameters value and suit
   public Card(char value, Suit suit)
   {
      set(value, suit);
   }
   
   //Default constructor sets value to "A" and suit to "spades" when value and suit not supplied
   public Card()
   {
      set('A', Suit.spades);
   }
   
   /*
    * Stringizer that client can use prior to displaying card
    * provides clean representation of the card
    */
   public String toString()
   {
      if (errorFlag == true)
      {
         return("** illegal **");
      }
      return String.valueOf(value) + " of " + suit;
   }
   
   /*
    * Mutator that accepts legal values.
    * When bad values are passed, errorFlag is set to true
    * When good values are passed they are stored and errorFlag is set to false 
   */
   public boolean set(char value, Suit suit)
   {
      if (isValid(value, suit))
      {
         this.value = value;
         this.suit = suit;
         errorFlag = false;
         return true;
      }
      else
      {
         errorFlag = true;
         return false;
      }
   }
   
   

   //Accessor that returns a cards value
   public char getValue()
   {
      return value;
   }
   
    //Accessor that returns a cards suit
   public Suit getSuit()
   {
      return suit;
   }
   
   //Accessor that returns errorFlag
   public boolean getErrorFlag()
   {
      return errorFlag;
   }
   
   //Returns true if all the fields(members) are identical and false, otherwise
   public boolean equals(Card card)
   {
      if (suit.equals(card.getSuit()) && value == card.getValue())
      {
         return true;
      }
      return false;
   }
   
   /*
    * Private method that returns true or false, depending on the legality of the parameters.
    * Note that, although it may be impossible for suit to be illegal (due to its enum-ness),
    * we pass it, anyway, in anticipation of possible changes to the type from enum to, say,
    * char or int, someday.  We only need to test value, at this time.
    * */
   

   private boolean isValid(char value, Suit suit)
   {
      for (int i = 0; i < cardValue.length; i++)
      {
         if (value == cardValue[i])
            return true;
      }
      return false;
   }
}

/********************* OUTPUT CARD CLASS *********************
 
Card Class Tests

A of spades
** illegal **
J of clubs

** illegal **
K of clubs
J of clubs
 
 
 ******************** END OUTPUT CARD CLASS *******************/
