package trivia;

import static java.lang.System.out;

// REFACTOR ME
public class GameBetter implements IGame {

   PlayerFactory playerFactory = new PlayerFactory();

   Question popQuestions = new PopQuestion();
   Question scienceQuestions = new ScienceQuestion();
   Question sportsQuestions = new SportsQuestion();
   Question rockQuestions = new RockQuestion();

   int currentPlayer = 0;

   public GameBetter() {
      for (int i = 0; i < 50; i++) {
         popQuestions.add("Pop Question " + i);
         scienceQuestions.add(("Science Question " + i));
         sportsQuestions.add(("Sports Question " + i));
         rockQuestions.add("Rock Question " + i);
      }
   }

   public boolean add(String playerName) {
      Player player = new Player(playerName);
      playerFactory.with(player);

      out.println(playerName + " was added");
      out.println("They are player number " + playerFactory.totalPlayers());
      return true;
   }

   public void roll(int roll) {
      Player player = playerFactory.extract();
      out.println(player.getName() + " is the current player");
      out.println("They have rolled a " + roll);

      if (player.isPenalized()) {
         if (roll % 2 != 0) {
            player.getOutOfPenaltyBox(true);

            out.println(player.getName() + " is getting out of the penalty box");
            determinePlayerPlace(roll);
         } else {
            out.println(player.getName() + " is not getting out of the penalty box");
            player.getOutOfPenaltyBox(false);
         }

      } else {

         determinePlayerPlace(roll);
      }

   }

   private void determinePlayerPlace(int roll) {
      Player player = playerFactory.extract();
      player.getNewPlace(roll);

      out.println(player.getName()
              + "'s new location is "
              + player.getPlace());
      out.println("The category is " + currentCategory().getLabel());
      askQuestion();
   }

   private void askQuestion() {
      Category currentCategory = currentCategory();
      switch (currentCategory) {
         case POP -> out.println(popQuestions.remove(0));
         case SCIENCE -> out.println(scienceQuestions.remove(0));
         case SPORTS -> out.println(sportsQuestions.remove(0));
         case ROCK -> out.println(rockQuestions.remove(0));
      }
   }


   private Category currentCategory() {
      Player player = playerFactory.extract();
      return switch (player.getPlace()) {
         case 0, 4, 8 -> Category.POP;
         case 1, 5, 9 -> Category.SCIENCE;
         case 2, 6, 10 -> Category.SPORTS;
         default -> Category.ROCK;
      };
   }

   public boolean wasCorrectlyAnswered() {
      Player player = playerFactory.extract();
      if (player.isPenalized()) {
         if (player.isGettingOutOfPenaltyBox()) {
            out.println("Answer was correct!!!!");
            return isWinner();
         } else {
            playerFactory.next();
            return true;
         }


      } else {

         out.println("Answer was corrent!!!!");
         return isWinner();
      }
   }

   private boolean isWinner() {
      Player player = playerFactory.extract();
      player.winPurse();
      out.println(player.getName()
              + " now has "
              + player.getPurse()
              + " Gold Coins.");

      boolean winner = didPlayerWin();
      playerFactory.next();

      return winner;
   }

   public boolean wrongAnswer() {
      Player player = playerFactory.extract();
      out.println("Question was incorrectly answered");
      out.println(player.getName() + " was sent to the penalty box");
      player.penalize();

      playerFactory.next();
      return true;
   }


   private boolean didPlayerWin() {
      Player player = playerFactory.extract();
      return player.getPurse() != 6;
   }
}
