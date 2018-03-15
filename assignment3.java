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
      // Test Of Card Class
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


      // Phase II Test of Hand Class
      Hand playerHand = new Hand();
      
      // Loop to max allowable cards playerHand.MAX_CARDS
      int s=0;
      int v=0;
      char[] cardValues = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
      do
      {
         playerHand.takeCard(new Card(cardValues[v++], Card.Suit.values()[s]));
         if(v == cardValues.length-1){
            v = 0;
            s = s + 1;
         }
      }while( s * cardValues.length + v < playerHand.MAX_CARDS);

      // Use To String to display hand
      System.out.println(playerHand.toString());
      
      playerHand.resetHand();
      Card cardZeroInspect = playerHand.inspectCard(0);      
      Card cardZeroPlay = playerHand.playCard();
      
      // Phase III Test of Deck Class
      Deck playerDeck = new Deck(2); 
      
      //Deal all the cards in a loop until the deck is empty (dealt directly to the display/screen, not to any Hand objects just yet).  
      //Display each card as it comes off the deck.  
      
      //Next, reset the deck by initializing it again (to the same two packs).  
      
      //Shuffle the deck this time, 
      
      //and re-deal to the screen in a loop again. 
      //Notice that the cards are now coming off in a random order.

      //Repeat this double deal, unshuffled, then shuffled, but this time using a single pack deck.
      
      // Integration Demonstration

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


/*------------------------------------------------------------------------------------ 
 * PHASE II: The Hand Class
 * 
 *------------------------------------------------------------------------------------*/
class Hand
{
   public Integer MAX_CARDS = 52;
   
   // Place holder
   public Hand()
   {
      System.out.println("TODO, Hand class Hand");
      return;
   }
   
   // Place holder
   public boolean takeCard(Card card)
   {
      System.out.println("TODO, Hand class takeCard");
      return true;
   }
   
   // Place holder
   public String toString()
   {
      System.out.println("TODO, Hand class toString ");
      return "";
   }

   // Place holder
   void resetHand(){
      System.out.println("TODO, Hand class resetHand ");
      return;
   }
   
   // Place holder 
   Card playCard() 
   {
      Card placeHolderTODODelMe = new Card();
      System.out.println("TODO, Hand class playCard ");
      return placeHolderTODODelMe;
   }
      
   // Place holder 
   Card inspectCard(int k)
   {
      Card placeHolderTODODelMe = new Card();
      System.out.println("TODO, Hand class inspectCard ");
      return placeHolderTODODelMe;
   }

}
/********************* OUTPUT HAND CLASS *********************
 
Hand Class Tests

 
******************** END OUTPUT HAND CLASS *******************/

/*------------------------------------------------------------------------------------ 
 * PHASE III: The Deck Class
 * 
 *------------------------------------------------------------------------------------*/
class Deck
{
   
   // Place holder 
   Deck(int numPacks)
   {
      System.out.println("TODO, Deck class numPacks");
      return;
   } 
   
   // Place holder 
   public void init(int numPacks)
   {
      System.out.println("TODO, Deck class init");
      return;
      
   } 
   
   // Place holder 
   public void shuffle()
   {
      System.out.println("TODO, Deck class shuffle");
      return;
   } 
   
   // Place holder 
   public Card dealCard()
   {
      Card placeHolderTODODelMe = new Card();
      System.out.println("TODO, Deck class dealCard ");
      return placeHolderTODODelMe;
   }
   
   // Place holder 
   public int topCard ()
   {
      System.out.println("TODO, Deck class topCard ");
      return 1;
   }
   
   // Place holder 
   public Card inspectCard(int k) 
   {
      Card placeHolderTODODelMe = new Card();
      System.out.println("TODO, Deck class inspectCard ");
      return placeHolderTODODelMe;
   }
   
   // Place holder 
   private static void allocateMasterPack()
   {
      System.out.println("TODO, Deck class allocateMasterPack ");
      return;
   }
}


/********************* OUTPUT Deck CLASS *********************
 
Deck Class Tests

 
******************** END OUTPUT Deck CLASS *******************/
