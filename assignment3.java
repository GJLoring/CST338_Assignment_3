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
      int suitCount=0;
      int valueCount=0;
      char[] cardValues = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
      do
      {
         playerHand.takeCard(new Card(cardValues[valueCount++], Card.Suit.values()[suitCount]));
         if(valueCount == cardValues.length){
            valueCount = 0;
            suitCount++;
         }
      }while( suitCount * cardValues.length + valueCount < playerHand.MAX_CARDS);

      // Use To String to display hand
      System.out.println(playerHand.toString());
      
      //Reset hand and inspect it
      playerHand.resetHand();
      Card cardZeroInspect;
      suitCount=0;
      valueCount=0;
      System.out.println("\nInit some cards");
      do
      {
         playerHand.takeCard(new Card(cardValues[valueCount++], Card.Suit.values()[suitCount]));
         if(valueCount == cardValues.length){
            valueCount = 0;
            suitCount++;
         }
      }while( suitCount * cardValues.length + valueCount < playerHand.MAX_CARDS);

      System.out.println("\nPlay to MAX_CARDS");
      for(int i =0; i < playerHand.MAX_CARDS;i++){
         //Inspect Valid Card
         cardZeroInspect = playerHand.inspectCard(i);
         System.out.println("Inspect Valid Card: " + cardZeroInspect.toString());   
      }   
      //Inspect Invalid Card
      cardZeroInspect = playerHand.inspectCard(playerHand.MAX_CARDS);
      System.out.println("Inspect InValid Card: " + cardZeroInspect.toString());  
      
      // Play a card   
      Card cardZeroPlay = playerHand.playCard();
      System.out.println("Play Card: " + cardZeroPlay.toString());
      
      // Phase III Test of Deck Class
      Deck playerDeck = new Deck(2); 
      
      //Deal all the cards in a loop until the deck is empty (dealt directly to the display/screen, not to any Hand objects just yet).  
      //Display each card as it comes off the deck. 
      System.out.println("\nDeal All Cards in loop");
      Card deltCard = playerDeck.dealCard();
      while(deltCard != null){
         System.out.println(deltCard.toString());
         deltCard = playerDeck.dealCard();
      };
      
      //Next, reset the deck by initializing it again (to the same two packs).  
      playerDeck.init(2);
      
      //Shuffle the deck this time, 
      playerDeck.shuffle();
      
      //and re-deal to the screen in a loop again. 
      System.out.println("\nRe Deal All Cards in loop");
      deltCard = playerDeck.dealCard();
      while(deltCard != null){
         System.out.println(deltCard.toString());
         deltCard = playerDeck.dealCard();
      };
      
      //Notice that the cards are now coming off in a random order.
      //Repeat this double deal, unshuffled, then shuffled, but this time using a single pack deck.
      Deck singlepackDeck = new Deck(1); 
      singlepackDeck.init(1);
      System.out.println("\nDeal Single pack in loop");
      deltCard = singlepackDeck.dealCard();
      while(deltCard != null){
         System.out.println(deltCard.toString());
         deltCard = singlepackDeck.dealCard();
      };
            
      //Shuffle the deck this time, 
      System.out.println("\nDeal Single pack in loop Shuffeled");
      singlepackDeck.init(1);
      singlepackDeck.shuffle();
      deltCard = singlepackDeck.dealCard();      
      while(deltCard != null){
         System.out.println(deltCard.toString());
         deltCard = singlepackDeck.dealCard();
      }; 
      
      //Phase IV
      
      //Ask the user (interactively) to select the number of players (a number from 1 to 10).
      Scanner keyboard = new Scanner(System.in);
      int numberOfPlayers = 0;
      do{
         System.out.printf("Please enter the number of players (a number from 1 to 10): ");
         numberOfPlayers = keyboard.nextInt();
      }while(numberOfPlayers<1 || numberOfPlayers > 10);
      //validated a legal value, 
      keyboard.close();
      
      Hand[] playersHands = new Hand[numberOfPlayers];
      // Init players hands
      for(int x = 0; x<playersHands.length; x++){
         playersHands[x] = new Hand();
         playersHands[x].resetHand();
      }

      //instantiate a single-pack Deck object without shuffling, 
      System.out.println("\nInstantiate a single-pack Deck object without shuffling");
      singlepackDeck.init(1);

      //deal a deck into that many Hand objects, dealing all cards until the deck is empty.  
      deltCard = singlepackDeck.dealCard();  
      int playerCount = 0;
      while(deltCard != null){
         playersHands[playerCount++].takeCard(deltCard);
         deltCard = singlepackDeck.dealCard();
         if(playerCount == numberOfPlayers )
            playerCount = 0;
      }; 
      
      //Display all the hands after the deal. 
      for(int x = 0; x<numberOfPlayers; x++){
         System.out.println("Player " + x);
         System.out.println(playersHands[x].toString());
      }
      
      // Reset hands
      System.out.println("\nShuffle and deal single-pack Deck");
      for(int x = 0; x<playersHands.length; x++)
         playersHands[x].resetHand();
   
      singlepackDeck.init(1);
      singlepackDeck.shuffle();
      //deal a deck into that many Hand objects, dealing all cards until the deck is empty.  
      deltCard = singlepackDeck.dealCard();  
      playerCount = 0;
      while(deltCard != null){
         playersHands[playerCount++].takeCard(deltCard);
         deltCard = singlepackDeck.dealCard();
         if(playerCount == numberOfPlayers )
            playerCount = 0;
      }; 

      //Display all the hands after the deal. 
      for(int x = 0; x<numberOfPlayers; x++){
         System.out.println("Player " + x);
         System.out.println(playersHands[x].toString());
      }  
   }
}      

/* Phase IV Output, Integration test of Hand and Deck objects

Player 0
Hand = ( K of spades, 8 of spades, 3 of spades, J of hearts, 6 of hearts, A of hearts, 9 of diamonds, 4 of diamonds, Q of clubs, 7 of clubs, 2 of clubs, )

Player 1
Hand = ( Q of spades, 7 of spades, 2 of spades, T of hearts, 5 of hearts, K of diamonds, 8 of diamonds, 3 of diamonds, J of clubs, 6 of clubs, A of clubs, )

Player 2
Hand = ( J of spades, 6 of spades, A of spades, 9 of hearts, 4 of hearts, Q of diamonds, 7 of diamonds, 2 of diamonds, T of clubs, 5 of clubs, )

Player 3
Hand = ( T of spades, 5 of spades, K of hearts, 8 of hearts, 3 of hearts, J of diamonds, 6 of diamonds, A of diamonds, 9 of clubs, 4 of clubs, )

Player 4
Hand = ( 9 of spades, 4 of spades, Q of hearts, 7 of hearts, 2 of hearts, T of diamonds, 5 of diamonds, K of clubs, 8 of clubs, 3 of clubs, )


Shuffle and deal single-pack Deck
Player 0
Hand = ( A of diamonds, A of hearts, J of spades, 7 of spades, 3 of spades, 6 of clubs, 9 of diamonds, 9 of clubs, 4 of clubs, A of clubs, 7 of clubs, )

Player 1
Hand = ( K of hearts, 8 of clubs, 2 of hearts, 8 of diamonds, 3 of diamonds, J of diamonds, J of hearts, T of clubs, Q of clubs, 5 of diamonds, 6 of diamonds, )

Player 2
Hand = ( 3 of hearts, J of clubs, K of diamonds, T of spades, 8 of spades, 2 of diamonds, K of clubs, 6 of spades, 2 of spades, Q of diamonds, )

Player 3
Hand = ( 9 of hearts, 5 of spades, A of spades, 8 of hearts, K of spades, 3 of clubs, 6 of hearts, 5 of hearts, 7 of hearts, 9 of spades, )

Player 4
Hand = ( T of diamonds, 4 of spades, Q of spades, 4 of diamonds, 7 of diamonds, Q of hearts, 5 of clubs, 2 of clubs, T of hearts, 4 of hearts, )


*/

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
   public static final int MAX_CARDS = 52;
   private Card[] myCards = new Card[MAX_CARDS];
   private int numCards = 0;

   // Default constructor
   public Hand()
   {
      Deck deck = new Deck();
      myCards = new Card[MAX_CARDS];
      for (int i = 1; i <= MAX_CARDS; i++)
      {
         takeCard(deck.dealCard());
      }
   }

   // Remove all cards from the hand
   void resetHand()
   {
      myCards = new Card[MAX_CARDS];
      numCards = 0;
      return;
   }

   // Adds a card to the next available position in the myCards array
   public boolean takeCard(Card card)
   {
      if(numCards == MAX_CARDS)
         return false;
      myCards[numCards] = card;
      numCards++;
      return true;
   }

   // Returns and removes the card in the top occupied position of the myCards
   // array
   public Card playCard()
   {
      Card handTopCard = new Card();
      handTopCard = myCards[numCards - 1];
      System.out.println("Playing " + handTopCard);
      myCards[numCards - 1] = null;
      numCards--;
      return handTopCard;
   }

   // Prints out the array of cards.
   public String toString()
   {
      System.out.print("Hand = ( ");
      for (int index = 0; index <= numCards - 1; index++)
         System.out.print(myCards[index] + ", ");
      System.out.println(")");
      return "";
   }

   // Accessor for number of cards
   public int getNumCards()
   {
      return numCards;
   }

   // Inspect a single card
   Card inspectCard(int k)
   {
      if(k >= numCards)
      {
         Card x = new Card();
         x.set('F', Card.Suit.clubs);
         return x;
      }
      else
      {
         return myCards[k];
      }
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
   //Deck capacity set to six packs by 52 cards per pack
   public static final int MAX_CARDS = 6 * 52;
   
   //Private static member data
   private static Card[] masterPack;
   private static boolean isExecuted;
   
   //Private member data
   private Card[] cards;
   private int topCard;
   private int numPacks;
   
   //Constructor to initialize masterpack
   public Deck(int numPacks)
   {
      allocateMasterPack();
      this.cards = masterPack;
      init(numPacks);
   } 
   
   //if no parameters are passed 1 pack is assumed
   public Deck()
   {
      this(1);
   }
   
   // 
   public void init(int numPacks)
   {
      this.numPacks = numPacks;
      getTopCard();
      if(topCard <= MAX_CARDS && numPacks != 0)
      {
         cards = new Card[topCard];
         for(int k = 0; k < cards.length; k++)
         {
            cards[k] = new Card();
            for(int k1 =0; k1 < numPacks; k1++)
            {
               for(int i = 52 * k1, j = 0; i < 52 * k1 + 52; i++, j++)
               {
                  cards[i] = masterPack[j];
               }
            }
         }
      }
      else
      {
         return;
      }
   } 
   
   // Used to shuffle the cards[] 
   public void shuffle()
   {
      Random rand = new Random();
      for(int k = cards.length - 1; k > 0; k--)
      {
         int randomIndex = rand.nextInt(k + 1);
         Card tempCard = cards[randomIndex];
         cards[randomIndex] = cards[k];
         cards[k] = tempCard;
      }
   } 
   
   // Used to deal cards 
   public Card dealCard()
   {
      if(topCard == 0)
      {
         return null;
      }
      Card returnCard = cards[topCard -1];
      cards[topCard -1] = null;
      topCard--;
      return returnCard;
   }
   
   // Accessor returns the value of numPacks * 52 using topCard 
   public int getTopCard ()
   {
      topCard = numPacks * 52;
      return topCard;
   }
   
   // Accessor inspects card and returns them or returns illegal message
   public Card inspectCard(int k) 
   {
      if(topCard == 0 || k < 0 || k > topCard)
      {
         return new Card('F', Card.Suit.spades);
      }
      else
      {
         return cards[k];
      }
   }
   
   // Place holder 
   private static void allocateMasterPack()
   {
      if(!isExecuted)
      {
         masterPack = new Card[52];
         Card.Suit suit;
         int k;
         int j;
         char value;
         
         for( k = 0; k < masterPack.length; k++)
         {
            masterPack[k] = new Card();
         }
         for(k = 0; k < 4; k++)
         {
            switch(k)
            {
               case 0: suit = Card.Suit.clubs;
                       break;
               case 1: suit = Card.Suit.diamonds;
                       break;
               case 2: suit = Card.Suit.hearts;
                       break;
               case 3: suit = Card.Suit.spades;
                       break;
               default: suit = Card.Suit.spades;
                       break;
            }
            masterPack[13 * k].set('A', suit);
            for(value = '2', j = 1; value <= '9'; value++, j++)
            {
               masterPack[13 * k + j].set(value, suit);
               masterPack[13 * k + 9].set('T', suit);
               masterPack[13 * k + 10].set('J', suit);
               masterPack[13 * k + 11].set('Q', suit);
               masterPack[13 * k + 12].set('K', suit);
            }
         }
         isExecuted = true;
      }
      else
      {
         return;
      }
   }
}


/********************* OUTPUT Deck CLASS *********************
 
K of spades
Q of spades
J of spades
T of spades
9 of spades
8 of spades
7 of spades
6 of spades
5 of spades
4 of spades
3 of spades
2 of spades
A of spades
K of hearts
Q of hearts
J of hearts
T of hearts
9 of hearts
8 of hearts
7 of hearts
6 of hearts
5 of hearts
4 of hearts
3 of hearts
2 of hearts
A of hearts
K of diamonds
Q of diamonds
J of diamonds
T of diamonds
9 of diamonds
8 of diamonds
7 of diamonds
6 of diamonds
5 of diamonds
4 of diamonds
3 of diamonds
2 of diamonds
A of diamonds
K of clubs
Q of clubs
J of clubs
T of clubs
9 of clubs
8 of clubs
7 of clubs
6 of clubs
5 of clubs
4 of clubs
3 of clubs
2 of clubs
A of clubs
K of spades
Q of spades
J of spades
T of spades
9 of spades
8 of spades
7 of spades
6 of spades
5 of spades
4 of spades
3 of spades
2 of spades
A of spades
K of hearts
Q of hearts
J of hearts
T of hearts
9 of hearts
8 of hearts
7 of hearts
6 of hearts
5 of hearts
4 of hearts
3 of hearts
2 of hearts
A of hearts
K of diamonds
Q of diamonds
J of diamonds
T of diamonds
9 of diamonds
8 of diamonds
7 of diamonds
6 of diamonds
5 of diamonds
4 of diamonds
3 of diamonds
2 of diamonds
A of diamonds
K of clubs
Q of clubs
J of clubs
T of clubs
9 of clubs
8 of clubs
7 of clubs
6 of clubs
5 of clubs
4 of clubs
3 of clubs
2 of clubs
A of clubs
8 of diamonds
8 of spades
9 of hearts
5 of diamonds
8 of diamonds
5 of hearts
7 of diamonds
9 of clubs
T of clubs
5 of diamonds
3 of diamonds
5 of spades
7 of clubs
J of spades
3 of spades
Q of spades
3 of clubs
5 of clubs
6 of diamonds
4 of diamonds
K of spades
8 of clubs
T of hearts
K of hearts
K of hearts
3 of hearts
5 of spades
J of diamonds
Q of diamonds
2 of hearts
J of clubs
6 of clubs
8 of hearts
6 of spades
6 of hearts
K of diamonds
4 of spades
T of spades
J of clubs
2 of diamonds
9 of spades
6 of hearts
T of hearts
9 of diamonds
7 of hearts
8 of spades
A of hearts
T of clubs
4 of spades
Q of hearts
2 of spades
4 of hearts
A of diamonds
J of spades
A of clubs
8 of clubs
T of spades
2 of spades
J of hearts
5 of hearts
9 of spades
J of hearts
Q of spades
3 of clubs
7 of spades
6 of clubs
Q of clubs
K of clubs
7 of spades
K of clubs
2 of clubs
5 of clubs
A of spades
3 of spades
9 of hearts
T of diamonds
4 of diamonds
3 of diamonds
8 of hearts
K of diamonds
7 of hearts
Q of hearts
2 of diamonds
2 of hearts
A of diamonds
6 of spades
9 of clubs
K of spades
4 of hearts
2 of clubs
Q of diamonds
6 of diamonds
7 of diamonds
A of hearts
A of clubs
4 of clubs
7 of clubs
Q of clubs
4 of clubs
9 of diamonds
T of diamonds
A of spades
3 of hearts
J of diamonds
 
******************** END OUTPUT Deck CLASS *******************/



/* Cumulative output of entire program


Card Class Tests

A of spades
** illegal **
J of clubs

** illegal **
K of clubs
J of clubs
Hand = ( K of spades, Q of spades, J of spades, T of spades, 9 of spades, 8 of spades, 7 of spades, 6 of spades, 5 of spades, 4 of spades, 3 of spades, 2 of spades, A of spades, K of hearts, Q of hearts, J of hearts, T of hearts, 9 of hearts, 8 of hearts, 7 of hearts, 6 of hearts, 5 of hearts, 4 of hearts, 3 of hearts, 2 of hearts, A of hearts, K of diamonds, Q of diamonds, J of diamonds, T of diamonds, 9 of diamonds, 8 of diamonds, 7 of diamonds, 6 of diamonds, 5 of diamonds, 4 of diamonds, 3 of diamonds, 2 of diamonds, A of diamonds, K of clubs, Q of clubs, J of clubs, T of clubs, 9 of clubs, 8 of clubs, 7 of clubs, 6 of clubs, 5 of clubs, 4 of clubs, 3 of clubs, 2 of clubs, A of clubs, )


Init some cards

Play to MAX_CARDS
Inspect Valid Card: A of clubs
Inspect Valid Card: 2 of clubs
Inspect Valid Card: 3 of clubs
Inspect Valid Card: 4 of clubs
Inspect Valid Card: 5 of clubs
Inspect Valid Card: 6 of clubs
Inspect Valid Card: 7 of clubs
Inspect Valid Card: 8 of clubs
Inspect Valid Card: 9 of clubs
Inspect Valid Card: T of clubs
Inspect Valid Card: J of clubs
Inspect Valid Card: Q of clubs
Inspect Valid Card: K of clubs
Inspect Valid Card: A of diamonds
Inspect Valid Card: 2 of diamonds
Inspect Valid Card: 3 of diamonds
Inspect Valid Card: 4 of diamonds
Inspect Valid Card: 5 of diamonds
Inspect Valid Card: 6 of diamonds
Inspect Valid Card: 7 of diamonds
Inspect Valid Card: 8 of diamonds
Inspect Valid Card: 9 of diamonds
Inspect Valid Card: T of diamonds
Inspect Valid Card: J of diamonds
Inspect Valid Card: Q of diamonds
Inspect Valid Card: K of diamonds
Inspect Valid Card: A of hearts
Inspect Valid Card: 2 of hearts
Inspect Valid Card: 3 of hearts
Inspect Valid Card: 4 of hearts
Inspect Valid Card: 5 of hearts
Inspect Valid Card: 6 of hearts
Inspect Valid Card: 7 of hearts
Inspect Valid Card: 8 of hearts
Inspect Valid Card: 9 of hearts
Inspect Valid Card: T of hearts
Inspect Valid Card: J of hearts
Inspect Valid Card: Q of hearts
Inspect Valid Card: K of hearts
Inspect Valid Card: A of spades
Inspect Valid Card: 2 of spades
Inspect Valid Card: 3 of spades
Inspect Valid Card: 4 of spades
Inspect Valid Card: 5 of spades
Inspect Valid Card: 6 of spades
Inspect Valid Card: 7 of spades
Inspect Valid Card: 8 of spades
Inspect Valid Card: 9 of spades
Inspect Valid Card: T of spades
Inspect Valid Card: J of spades
Inspect Valid Card: Q of spades
Inspect Valid Card: K of spades
Inspect InValid Card: ** illegal **
Playing K of spades
Play Card: K of spades

Deal All Cards in loop
K of spades
Q of spades
J of spades
T of spades
9 of spades
8 of spades
7 of spades
6 of spades
5 of spades
4 of spades
3 of spades
2 of spades
A of spades
K of hearts
Q of hearts
J of hearts
T of hearts
9 of hearts
8 of hearts
7 of hearts
6 of hearts
5 of hearts
4 of hearts
3 of hearts
2 of hearts
A of hearts
K of diamonds
Q of diamonds
J of diamonds
T of diamonds
9 of diamonds
8 of diamonds
7 of diamonds
6 of diamonds
5 of diamonds
4 of diamonds
3 of diamonds
2 of diamonds
A of diamonds
K of clubs
Q of clubs
J of clubs
T of clubs
9 of clubs
8 of clubs
7 of clubs
6 of clubs
5 of clubs
4 of clubs
3 of clubs
2 of clubs
A of clubs
K of spades
Q of spades
J of spades
T of spades
9 of spades
8 of spades
7 of spades
6 of spades
5 of spades
4 of spades
3 of spades
2 of spades
A of spades
K of hearts
Q of hearts
J of hearts
T of hearts
9 of hearts
8 of hearts
7 of hearts
6 of hearts
5 of hearts
4 of hearts
3 of hearts
2 of hearts
A of hearts
K of diamonds
Q of diamonds
J of diamonds
T of diamonds
9 of diamonds
8 of diamonds
7 of diamonds
6 of diamonds
5 of diamonds
4 of diamonds
3 of diamonds
2 of diamonds
A of diamonds
K of clubs
Q of clubs
J of clubs
T of clubs
9 of clubs
8 of clubs
7 of clubs
6 of clubs
5 of clubs
4 of clubs
3 of clubs
2 of clubs
A of clubs

Re Deal All Cards in loop
5 of spades
A of diamonds
5 of clubs
A of hearts
5 of spades
K of hearts
9 of spades
2 of diamonds
9 of hearts
6 of clubs
4 of spades
K of diamonds
8 of diamonds
A of hearts
6 of diamonds
2 of diamonds
6 of spades
T of diamonds
A of clubs
K of spades
3 of clubs
5 of diamonds
9 of clubs
7 of spades
7 of hearts
J of diamonds
9 of diamonds
5 of hearts
4 of hearts
4 of diamonds
7 of diamonds
J of clubs
6 of hearts
6 of spades
J of hearts
5 of diamonds
T of spades
6 of clubs
J of diamonds
4 of hearts
4 of diamonds
T of diamonds
T of clubs
9 of clubs
9 of spades
A of spades
A of spades
9 of hearts
5 of clubs
Q of hearts
8 of clubs
J of hearts
K of diamonds
5 of hearts
2 of spades
2 of hearts
Q of diamonds
K of clubs
T of clubs
3 of hearts
Q of clubs
6 of diamonds
9 of diamonds
7 of hearts
K of spades
3 of clubs
8 of hearts
3 of spades
J of clubs
6 of hearts
J of spades
T of hearts
3 of diamonds
3 of spades
8 of spades
A of diamonds
4 of spades
Q of spades
7 of clubs
7 of diamonds
3 of diamonds
2 of spades
8 of spades
3 of hearts
2 of clubs
Q of spades
8 of clubs
T of spades
8 of hearts
K of clubs
4 of clubs
Q of clubs
K of hearts
2 of hearts
Q of hearts
7 of spades
7 of clubs
T of hearts
J of spades
2 of clubs
4 of clubs
A of clubs
Q of diamonds
8 of diamonds

Deal Single pack in loop
K of spades
Q of spades
J of spades
T of spades
9 of spades
8 of spades
7 of spades
6 of spades
5 of spades
4 of spades
3 of spades
2 of spades
A of spades
K of hearts
Q of hearts
J of hearts
T of hearts
9 of hearts
8 of hearts
7 of hearts
6 of hearts
5 of hearts
4 of hearts
3 of hearts
2 of hearts
A of hearts
K of diamonds
Q of diamonds
J of diamonds
T of diamonds
9 of diamonds
8 of diamonds
7 of diamonds
6 of diamonds
5 of diamonds
4 of diamonds
3 of diamonds
2 of diamonds
A of diamonds
K of clubs
Q of clubs
J of clubs
T of clubs
9 of clubs
8 of clubs
7 of clubs
6 of clubs
5 of clubs
4 of clubs
3 of clubs
2 of clubs
A of clubs

Deal Single pack in loop Shuffeled
T of clubs
4 of clubs
J of hearts
K of hearts
8 of spades
Q of diamonds
A of clubs
3 of diamonds
6 of diamonds
2 of clubs
T of spades
2 of spades
8 of diamonds
7 of hearts
T of hearts
9 of spades
T of diamonds
5 of diamonds
9 of clubs
3 of hearts
Q of hearts
K of diamonds
7 of diamonds
2 of hearts
5 of clubs
9 of diamonds
Q of spades
J of spades
3 of spades
5 of spades
K of clubs
3 of clubs
Q of clubs
6 of spades
A of hearts
7 of spades
5 of hearts
A of diamonds
2 of diamonds
J of clubs
8 of hearts
6 of hearts
4 of hearts
4 of diamonds
J of diamonds
8 of clubs
7 of clubs
6 of clubs
A of spades
K of spades
9 of hearts
4 of spades
Please enter the number of players (a number from 1 to 10): 5

Instantiate a single-pack Deck object without shuffling
Player 0
Hand = ( K of spades, 8 of spades, 3 of spades, J of hearts, 6 of hearts, A of hearts, 9 of diamonds, 4 of diamonds, Q of clubs, 7 of clubs, 2 of clubs, )

Player 1
Hand = ( Q of spades, 7 of spades, 2 of spades, T of hearts, 5 of hearts, K of diamonds, 8 of diamonds, 3 of diamonds, J of clubs, 6 of clubs, A of clubs, )

Player 2
Hand = ( J of spades, 6 of spades, A of spades, 9 of hearts, 4 of hearts, Q of diamonds, 7 of diamonds, 2 of diamonds, T of clubs, 5 of clubs, )

Player 3
Hand = ( T of spades, 5 of spades, K of hearts, 8 of hearts, 3 of hearts, J of diamonds, 6 of diamonds, A of diamonds, 9 of clubs, 4 of clubs, )

Player 4
Hand = ( 9 of spades, 4 of spades, Q of hearts, 7 of hearts, 2 of hearts, T of diamonds, 5 of diamonds, K of clubs, 8 of clubs, 3 of clubs, )


Shuffle and deal single-pack Deck
Player 0
Hand = ( 8 of diamonds, 9 of diamonds, 7 of spades, 6 of diamonds, A of spades, Q of spades, 6 of hearts, 8 of hearts, K of clubs, 5 of spades, J of hearts, )

Player 1
Hand = ( Q of hearts, Q of diamonds, 9 of clubs, J of clubs, J of diamonds, 9 of hearts, 5 of hearts, 9 of spades, A of clubs, 2 of hearts, 2 of spades, )

Player 2
Hand = ( 4 of clubs, T of hearts, K of spades, 5 of diamonds, 5 of clubs, 3 of clubs, 8 of spades, T of spades, 4 of spades, 2 of clubs, )

Player 3
Hand = ( T of clubs, T of diamonds, A of hearts, K of diamonds, 8 of clubs, J of spades, 3 of spades, 3 of hearts, Q of clubs, 7 of clubs, )

Player 4
Hand = ( 7 of diamonds, 4 of hearts, K of hearts, 6 of spades, 7 of hearts, A of diamonds, 2 of diamonds, 3 of diamonds, 6 of clubs, 4 of diamonds, )
*/
