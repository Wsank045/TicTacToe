import java.util.LinkedList;



public class ComputerMenacePlayer extends Player {
	private LinkedList<LinkedList<MenaceTicTacToeGame>> allGames;
	private LinkedList<MenaceTicTacToeGame> menaceTicTacToeGamesList;
	
	public ComputerMenacePlayer(){
		super();
		allGames= new LinkedList<LinkedList<MenaceTicTacToeGame>> ();
		menaceTicTacToeGamesList= new LinkedList<>();
		
		// First game for level 0
		allGames.add(new LinkedList<MenaceTicTacToeGame>());
		allGames.get(0).add(new MenaceTicTacToeGame());

		for(int i=1; i<= 9; i++) {
			LinkedList<MenaceTicTacToeGame> newList;
			newList = new LinkedList<MenaceTicTacToeGame>();
			allGames.add(newList);
			for(MenaceTicTacToeGame game: allGames.get(i-1)){
				if(game.getGameState() == GameState.PLAYING) {
					for(int j = 0;
						j < 9;
						j++) {
						if(game.valueAt(j) == CellValue.EMPTY) {
							MenaceTicTacToeGame newGame = new MenaceTicTacToeGame(game,j);
							//checking that this game was not already found
							boolean isNew = true;
							for(MenaceTicTacToeGame existingGame: allGames.get(i)){
								if(newGame.equalsWithSymmetry(existingGame)){
									isNew = false;
									break;
								}
							}
							if(isNew) {
								newList.add(newGame);
							}					
						}
					}
				}

			}
		}
	}
	public void addMenaceGames(MenaceTicTacToeGame gamePlayed){
		menaceTicTacToeGamesList.add(gamePlayed);
	}

	public void setMenaceOutcomes(TicTacToeGame game){

		for(MenaceTicTacToeGame menace: allGames.get(game.getLevel())){
			if(menace.equalsWithSymmetry(game)){
				if(menace.getGameState()== GameState.XWIN ||menace.getGameState()== GameState.OWIN){
					for(MenaceTicTacToeGame gamePlayed: menaceTicTacToeGamesList){
						gamePlayed.setGameOutcome(MenaceTicTacToeGame.WIN);
						gamePlayed.setPositionbeads();
					}
				}
				if(menace.getGameState()== GameState.DRAW){
					for(MenaceTicTacToeGame gamePlayed: menaceTicTacToeGamesList){
						gamePlayed.setGameOutcome(MenaceTicTacToeGame.DRAW);
						gamePlayed.setPositionbeads();
					}
				}
			}
		}

	}
	

	public void nextGameOutcome( MenaceTicTacToeGame menaceGame){
		for(int i=0; i<9;i++){
			if(menaceGame.valueAt(i)==CellValue.EMPTY){
				MenaceTicTacToeGame newMenaceGame= new MenaceTicTacToeGame(menaceGame, i);
				for(MenaceTicTacToeGame existingMenaceGame: allGames.get(newMenaceGame.getLevel())){
					if(existingMenaceGame.equalsWithSymmetry(newMenaceGame)){
						if(existingMenaceGame.getGameState()==GameState.XWIN || existingMenaceGame.getGameState()==GameState.OWIN){
							for(MenaceTicTacToeGame games: menaceTicTacToeGamesList){
								games.setGameOutcome(MenaceTicTacToeGame.DRAW);
								games.setPositionbeads();
							}
						}
						if(existingMenaceGame.getGameState()==GameState.DRAW){
							for(MenaceTicTacToeGame games: menaceTicTacToeGamesList){
								games.setGameOutcome(MenaceTicTacToeGame.DRAW);
								games.setPositionbeads();
							}
						}
						break;
					}
				}
			}
		}
	}

	public void play(TicTacToeGame game){

		if(game.getLevel()==game.lines*game.columns){
			throw new IllegalArgumentException("Game is already finished");
		}

		for(MenaceTicTacToeGame menaceGame: allGames.get(game.getLevel())){
			if(menaceGame.equalsWithSymmetry(game)){
				game.play(menaceGame.moveChoice());
				addMenaceGames(menaceGame);
				setMenaceOutcomes(menaceGame);
				for(MenaceTicTacToeGame newMenaceGame: allGames.get(game.getLevel())){
					if(newMenaceGame.equalsWithSymmetry(game)){
						nextGameOutcome(newMenaceGame);
						break;
					}
				}
				break;
			}
		}
			
	}
	
}