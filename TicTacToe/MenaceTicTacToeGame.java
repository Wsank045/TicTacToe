import java.util.Arrays;

public class MenaceTicTacToeGame extends TicTacToeGame {

	private int [] positionBeads1;
	private int choice;

	public static final int NOT_SET=0;
	public static final int WIN=1;
	public static final int LOSE=2;
	public static final int DRAW=3;
	private int gameOutcome;

	public MenaceTicTacToeGame(){
		super(3,3,3);

		gameOutcome=NOT_SET;
		choice=0;

		positionBeads1=new int [9];
		for(int i=0; i<9;i++){
			positionBeads1[i]=8;
		}	
	}
	public MenaceTicTacToeGame(MenaceTicTacToeGame base, int next){
		super(base,next);

		gameOutcome=NOT_SET;
		choice=0;

		positionBeads1=new int [9];
		if(getLevel()==0 || getLevel()==1){
			for(int i=0; i<9;i++){
				positionBeads1[i]=8;
			}	
		}
		if(getLevel()==2 || getLevel()==3){
			for(int i=0; i<9;i++){
				positionBeads1[i]=4;
			}	
		}
		if(getLevel()==4 || getLevel()==5){
			for(int i=0; i<9;i++){
				positionBeads1[i]=2;
			}	
		}
		if(getLevel()==6 || getLevel()==7){
			for(int i=0; i<9;i++){
				positionBeads1[i]=1;
			}	
		}
		if(getLevel()==8){
			for(int i=0; i<9;i++){
				positionBeads1[i]=1;
			}	
		}
		
	}


	public void setGameOutcome(int outcome){
		if(outcome==WIN){
			gameOutcome=WIN;
		}
		if(outcome==LOSE){
			gameOutcome=LOSE;
		}
		if(outcome==DRAW){
			gameOutcome=DRAW;
		}
	}

	public int getGameOutcome(){
		return gameOutcome;
	}
	
	

	public int moveChoice(){
		int [] newBoard= new int[9];
		for(int i=0; i<9; i++){
			newBoard[i]=positionBeads1[i];
		}
			Arrays.sort(newBoard);
			for(int i=8;i>=0;i--){
				for(int j=0; j<=8; j++){
					if(positionBeads1[j]==newBoard[i]){
						if(valueAt(transformedBoard[j])==CellValue.EMPTY){
							choice=j;
							break;
						}
					}
				}
			}
		return choice;
	}



	public  void setPositionbeads(){
		if(gameOutcome==DRAW){
			positionBeads1[choice]=positionBeads1[choice]+1;
			
		}
		if(gameOutcome==WIN){
			positionBeads1[choice]=positionBeads1[choice]+3;
			
		}
		if(gameOutcome==LOSE){
				if(positionBeads1[choice]>1){
					positionBeads1[choice]-=1;
				}
			
		}
		
	}

}