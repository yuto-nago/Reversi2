package kenshu;

import kenshu2.Util;

public class GamePlay {

	//Boardクラスの参照
	private Board board;

	//Stoneクラスの参照
	private Stone stone;

	//どちらのターンか判別する。黒が2、白が1
	private int turn;

	//入力された値を保持する変数
	private int place;

	//ビット演算に使用する変数
	private int bit = 3;

	//盤面のx座標
	private int x;

	//盤面のy座標
	private int y;

	public GamePlay(int turn){
		this.turn = turn;
	}


	public void play(){

		do{
			board = new Board();

			stone = new Stone(2, 2);

			board.display();

			while(game_end() != 0){

				if(turn == 2){
					if(pass() == 1){
						System.out.println("●のターン");
						place = Util.input();

						y = place / 10;

					    x = place % 10;

					    while(check(y , x) == 0){

					    	System.out.println("挟める石がありません");

					    	board.display();

					    	System.out.println("●のターン");

					    	place = Util.input();

					    	y = place / 10;

						    x = place % 10;
					    }
					    flip(y, x);

					    board.display();

					    turn = 1;

					}else{
						System.out.println("黒はパスです");

						turn = 1;

						break;
					}
				}else if(turn == 1){
					if(pass() == 1){
						System.out.println("○のターン");

						place = Util.input();

						y = place / 10;

					    x = place % 10;

					    while(check(y , x) == 0){

					    	System.out.println("挟める石がありません");

					    	board.display();

					    	System.out.println("○のターン");

					    	place = Util.input();

					    	y = place / 10;

						    x = place % 10;
					    }
					    flip(y, x);

					    board.display();

					    turn = 2;
					}else{
						System.out.println("白はパスです");

						turn = 2;

						break;
					}
				}

			}

			judge();


		}while(Util.retry() != 0);






	}


	/**
	 * ゲームが終了かどうか調べるメソッド
	 * @return 終了なら0、終了でなければ1を返す。
	 */
	public int game_end(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(board.getBanmen()[i][j] == 0){
					return 1;
				}
			}
		}

		if(stone.getBlack() != 0 || stone.getWhite() != 0){
			return 1;
		}
		return 0;
	}

	/**
	 * 勝敗を表示するメソッド
	 */
	public void judge(){
		int black = 0 , white = 0;
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(board.getBanmen()[i][j] == 2){
					black++;
				}else if(board.getBanmen()[i][j] == 1){
					white++;
				}
			}
		}
		if(black < white){
			System.out.println(white + "枚で白の勝ち");
		}else if(black == white){
			System.out.println("引き分け");
		}else{
			System.out.println(black + "枚で黒の勝ち");
		}

	}

	/**
	 * 挟める石があるか調べるメソッド
	 * @return 挟む石が無ければ0を返す。挟む石があれば1を返す。
	 */
	public int pass(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				//配列[0][0]の時、右、下、右斜め下にしか検索しない
				if(board.getBanmen()[i][j] == 0 && i == 0 && j == 0){
					//右方向に相手の石を見つけた際にその先に自分の石があれば挟むことができるのでパスではない
					if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
						for(int a = j + 1; a < 8; a++){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}

					}

					//下方向への検索
					if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
						for(int a = i + 1; a < 8; a++){
							if(board.getBanmen()[a][j] == 0){
								break;
							}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}

					}

					//右斜め下への検索
					if(board.getBanmen()[i + 1][j + 1] == (turn ^ bit)){
						int b = j + 1;
						for(int a = i + 1; (a < 8) && (b < 8); a++, b++){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
					}

				//配列[0][7]の時、左、下、左斜め下にしか検索しない
				}else if(board.getBanmen()[i][j] == 0 && i == 0 && j == 7){

					//左方向への検索
					if(board.getBanmen()[i][j-1] == (turn ^ bit)){
						for(int a = j - 1; a >= 0; a--){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}

				    }
					//下方向への検索
					if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
				    	for(int a = i + 1; a < 8; a++){
				    		if(board.getBanmen()[a][j] == 0){
				    			break;
				    		}else if(board.getBanmen()[a][j] == 1){
								return 1;
							}
						}

				    }
					//左斜め下への検索
					if(board.getBanmen()[i + 1][j - 1] == (turn ^ bit)){
				    	int b = j - 1;
						for(int a = i + 1; (a < 8) && (b >= 0); a++, b--){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
				    }

				//配列[7][0]の時、上、右、右斜め上にしか検索しない
			    }else if(board.getBanmen()[i][j] == 0 && i == 7 && j == 0){

			    	//上方向への検索
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}

			    	}

			    	//右方向への検索
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}

			    	}

			    	//右斜め上方向への検索
			    	if(board.getBanmen()[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i - 1; (a >= 0) && (b < 8); a--, b++){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    //配列[7][7]の時、左、上、左斜め上にしか検索しない
			    }else if(board.getBanmen()[i][j] == 0 && i == 7 && j == 7){

			    	//左方向への検索
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a >= 0; a--){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}
			    	//上方向への検索
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}
			    	}
			    	//左斜め上方向への検索
			    	if(board.getBanmen()[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i - 1; (a >= 0) && (b >= 0); a--, b--){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    //配列[0][j]の時、上側を検索しない
			    }else if(board.getBanmen()[i][j] == 0 && i == 0){

			    	//左方向への検索
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a >= 0; a--){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}
			    	//左斜め下方向への検索
			    	if(board.getBanmen()[i + 1][j - 1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i + 1; (a < 8) && (b >= 0); a++, b--){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}
			    	//下方向への検索
			    	if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
			    		for(int a = i + 1; a < 8; a++){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}
			    	}

			    	//右斜め下方向への検索
			    	if(board.getBanmen()[i + 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i + 1; (a < 8) && (b < 8); a++, b++){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//右方向への検索
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    //配列[i][0]の時、左側を検索しない
			    }else if(board.getBanmen()[i][j] == 0 && j == 0){

			    	//上方向への検索
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//右斜め上方向への検索
			    	if(board.getBanmen()[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i - 1; (a >= 0) && (b < 8); a--, b++){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//右方向への検索
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//右斜め下方向への検索
			    	if(board.getBanmen()[i + 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i + 1; (a < 8) && (b < 8); a++, b++){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//下方向への検索
			    	if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
			    		for(int a = i + 1; a < 8; a++){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}
			    	}

			    //配列[i][7]の時、右側を検索しない
			    }else if(board.getBanmen()[i][j] == 0 && j == 7){

			    	//上方向への検索
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//左斜め上方向への検索
			    	if(board.getBanmen()[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i - 1; (a >= 0) && (b >= 0); a--, b--){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//左方向への検索
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a >= 0; a--){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//左斜め下方向への検索
			    	if(board.getBanmen()[i + 1][j - 1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i + 1; (a < 8) && (b >= 0); a++, b--){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//下方向への検索
			    	if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
			    		for(int a = i + 1; a < 8; a++){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}
			    	}

			    //配列[7][j]の時、下側を検索しない
			    }else if(board.getBanmen()[i][j] == 0 && i == 7){

			    	//左方向への検索
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a >= 0; a--){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//左斜め上方向への検索
			    	if(board.getBanmen()[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i - 1; (a > 0) && (b >= 0); a--, b--){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//上方向への検索
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//右斜め上方向への検索
			    	if(board.getBanmen()[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i - 1; (a >= 0) && (b < 8); a--, b++){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//右方向への検索
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    //他の座標は周りをすべて調べる
			    }else if(board.getBanmen()[i][j] == 0){

			    	//上方向への検索
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//右斜め上方向への検索
			    	if(board.getBanmen()[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i - 1; (a >= 0) && (b < 8); a--, b++){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//右方向への検索
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//右斜め下方向への検索
			    	if(board.getBanmen()[i + 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i + 1; (a < 8) && (b < 8); a++, b++){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//下方向への検索
			    	if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
			    		for(int a = i + 1; a < 8; a++){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}
			    	}

			    	//左斜め下方向への検索
			    	if(board.getBanmen()[i + 1][j - 1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i + 1; (a < 8) && (b >= 0); a++, b--){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//左方向への検索
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a >= 0; a--){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//左斜め上方向への検索
			    	if(board.getBanmen()[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i - 1; (a >= 0) && (b >= 0); a--, b--){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    }
			}
		}
		return 0;
	}


	/**
	 * 石を置けるかどうか調べるメソッド
	 * @param i　コンソールから入力された十の位の数字
	 * @param j　コンソールから入力された一の位の数字
	 * @return　置けなければ0、置けるなら1を返す。
	 */
	public int check(int i, int j){
				//配列[0][0]の時、右、下、右斜め下にしか検索しない
				if(board.getBanmen()[i][j] == 0 && i == 0 && j == 0){
					//右方向に相手の石を見つけた際にその先に自分の石があれば挟むことができるのでパスではない
					if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
						for(int a = j + 1; a < 8; a++){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}

					}

					//下方向への検索
					if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
						for(int a = i + 1; a < 8; a++){
							if(board.getBanmen()[a][j] == 0){
								break;
							}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}

					}

					//右斜め下への検索
					if(board.getBanmen()[i + 1][j + 1] == (turn ^ bit)){
						int b = j + 1;
						for(int a = i + 1; (a < 8) && (b < 8); a++, b++){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
					}

				//配列[0][7]の時、左、下、左斜め下にしか検索しない
				}else if(board.getBanmen()[i][j] == 0 && i == 0 && j == 7){

					//左方向への検索
					if(board.getBanmen()[i][j-1] == (turn ^ bit)){
						for(int a = j - 1; a >= 0; a--){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}

				    }
					//下方向への検索
					if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
				    	for(int a = i + 1; a < 8; a++){
				    		if(board.getBanmen()[a][j] == 0){
				    			break;
				    		}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}

				    }
					//左斜め下への検索
					if(board.getBanmen()[i + 1][j - 1] == (turn ^ bit)){
				    	int b = j - 1;
						for(int a = i + 1; (a < 8) && (b >= 0); a++, b--){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
				    }

				//配列[7][0]の時、上、右、右斜め上にしか検索しない
			    }else if(board.getBanmen()[i][j] == 0 && i == 7 && j == 0){

			    	//上方向への検索
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}

			    	}

			    	//右方向への検索
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}

			    	}

			    	//右斜め上方向への検索
			    	if(board.getBanmen()[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i - 1; (a >= 0) && (b < 8); a--, b++){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    //配列[7][7]の時、左、上、左斜め上にしか検索しない
			    }else if(board.getBanmen()[i][j] == 0 && i == 7 && j == 7){

			    	//左方向への検索
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a >= 0; a--){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}
			    	//上方向への検索
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}
			    	}
			    	//左斜め上方向への検索
			    	if(board.getBanmen()[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i - 1; (a >= 0) && (b >= 0); a--, b--){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    //配列[0][j]の時、上側を検索しない
			    }else if(board.getBanmen()[i][j] == 0 && i == 0){

			    	//左方向への検索
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a >= 0; a--){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}
			    	//左斜め下方向への検索
			    	if(board.getBanmen()[i + 1][j - 1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i + 1; (a < 8) && (b >= 0); a++, b--){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}
			    	//下方向への検索
			    	if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
			    		for(int a = i + 1; a < 8; a++){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}
			    	}

			    	//右斜め下方向への検索
			    	if(board.getBanmen()[i + 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i + 1; a < 8 || b < 8; a++, b++){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//右方向への検索
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    //配列[i][0]の時、左側を検索しない
			    }else if(board.getBanmen()[i][j] == 0 && j == 0){

			    	//上方向への検索
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//右斜め上方向への検索
			    	if(board.getBanmen()[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i - 1; (a >= 0) && (b < 8); a--, b++){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//右方向への検索
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//右斜め下方向への検索
			    	if(board.getBanmen()[i + 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i + 1; (a < 8) && (b < 8); a++, b++){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//下方向への検索
			    	if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
			    		for(int a = i + 1; a < 8; a++){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}
			    	}

			    //配列[i][7]の時、右側を検索しない
			    }else if(board.getBanmen()[i][j] == 0 && j == 7){

			    	//上方向への検索
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//左斜め上方向への検索
			    	if(board.getBanmen()[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i - 1; (a >= 0) && (b >= 0); a--, b--){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//左方向への検索
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a >= 0; a--){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//左斜め下方向への検索
			    	if(board.getBanmen()[i + 1][j - 1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i + 1; (a < 8) && (b >= 0); a++, b--){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//下方向への検索
			    	if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
			    		for(int a = i + 1; a < 8; a++){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}
			    	}

			    //配列[7][j]の時、下側を検索しない
			    }else if(board.getBanmen()[i][j] == 0 && i == 7){

			    	//左方向への検索
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a >= 0; a--){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//左斜め上方向への検索
			    	if(board.getBanmen()[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i - 1; (a >= 0) && (b >= 0); a--, b--){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//上方向への検索
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//右斜め上方向への検索
			    	if(board.getBanmen()[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i - 1; (a >= 0) && (b < 8); a--, b++){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//右方向への検索
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    //他の座標は周りをすべて調べる
			    }else if(board.getBanmen()[i][j] == 0){

			    	//上方向への検索
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//右斜め上方向への検索
			    	if(board.getBanmen()[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i - 1; (a >= 0) && (b < 8); a--, b++){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}

						}
			    	}

			    	//右方向への検索
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//右斜め下方向への検索
			    	if(board.getBanmen()[i + 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
						for(int a = i + 1; (a < 8) && (b < 8); a++, b++){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//下方向への検索
			    	if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
			    		for(int a = i + 1; a < 8; a++){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}
			    	}

			    	//左斜め下方向への検索
			    	if(board.getBanmen()[i + 1][j - 1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i + 1; (a < 8) && (b >= 0); a++, b--){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//左方向への検索
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a >= 0; a--){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//左斜め上方向への検索
			    	if(board.getBanmen()[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
						for(int a = i - 1; (a >= 0) && (b >= 0); a--, b--){
							if(board.getBanmen()[a][b] == 0){
								break;
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    }


		return 0;
	}

	/**
	 * 相手の石を裏返すメソッド
	 *
	 * @param i コンソールから入力された十の位の数字
	 * @param j コンソールから入力された一の位の数字
	 */
	public void flip(int i, int j){

		//配列[0][0]の時、右、下、右斜め下にしか検索しない
		if(i == 0 && j == 0){

			//右方向への処理
			if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
				for(int a = j + 1; a < 8; a++){
					if(board.getBanmen()[i][a] == 0){
						break;
					}else if(board.getBanmen()[i][a] == turn){
						for(int b = a; b >= j ; b--){
							board.getBanmen()[i][b] = turn;
						}
						break;
					}
				}
			}

			//右斜め下方向への処理
			if(board.getBanmen()[i+1][j+1] == (turn ^ bit)){
				int a; int b = j + 1;
				for(a = i + 1; (a < 8) && (b < 8); a++, b++){
					if(board.getBanmen()[a][b] == 0){
						break;
					}else if(board.getBanmen()[a][b] == turn){
						for(int c = a; c >= i; c--){
							board.getBanmen()[c][b--] = turn;
						}
						break;
					}
				}
			}

			//下方向への処理
			if(board.getBanmen()[i+1][j] == (turn ^ bit)){
				for(int a = i + 1; a < 8; a++){
					if(board.getBanmen()[a][j] == 0){
						break;
					}else if(board.getBanmen()[a][j] == turn){
						for(int b = a; b >= i; b--){
							board.getBanmen()[b][j] = turn;
						}
						break;
					}
				}
			}

		//配列[0][7]の時、左、下、左斜め下にしか検索しない
		}else if(i == 0 && j == 7){

			//左方向への処理
			if(board.getBanmen()[i][j-1] == (turn ^ bit)){
				for(int a = j - 1; a >= 0; a--){
					if(board.getBanmen()[i][a] == 0){
						break;
					}else if(board.getBanmen()[i][a] == turn){
						for(int b = a; b <= j ; b++){
							board.getBanmen()[i][b] = turn;
						}
						break;
					}
				}
			}

			//左斜め下方向への処理
			if(board.getBanmen()[i+1][j-1] == (turn ^ bit)){
				int a; int b = j - 1;
				for(a = i + 1; (a < 8) && (b >= 0); a++, b--){
					if(board.getBanmen()[a][b] == 0){
						break;
					}else if(board.getBanmen()[a][b] == turn){
						for(int c = a; c >= i; c--){
							board.getBanmen()[c][b++] = turn;
						}
						break;
					}
				}
			}

			//下方向への処理
			if(board.getBanmen()[i+1][j] == (turn ^ bit)){
				for(int a = i + 1; a < 8; a++){
					if(board.getBanmen()[a][j] == 0){
						break;
					}else if(board.getBanmen()[a][j] == turn){
						for(int b = a; b >= i; b--){
							board.getBanmen()[b][j] = turn;
						}
						break;
					}
				}
			}

		//配列[7][0]の時、上、右、右斜め上にしか検索しない
		}else if(i == 7 && j == 0){

			//上方向への処理
			if(board.getBanmen()[i-1][j] == (turn ^ bit)){
				for(int a = i - 1; a >= 0; a--){
					if(board.getBanmen()[a][j] == 0){
						break;
					}else if(board.getBanmen()[a][j] == turn){
						for(int b = a; b <= i; b++){
							board.getBanmen()[b][j] = turn;
						}
						break;
					}
				}
			}

			//右斜め上方向への処理
			if(board.getBanmen()[i-1][j+1] == (turn ^ bit)){
				int a; int b = j + 1;
				for(a = i - 1; (a >= 0) && (b < 8); a--, b++){
					if(board.getBanmen()[a][b] == 0){
						break;
					}else if(board.getBanmen()[a][b] == turn){
						for(int c = a; c <= i; c++){
							board.getBanmen()[c][b--] = turn;
						}
						break;
					}
				}
			}

			//右方向への処理
			if(board.getBanmen()[i][j+1] == (turn ^ bit)){
				for(int a = j + 1; a < 8; a++){
					if(board.getBanmen()[i][a] == 0){
						break;
					}else if(board.getBanmen()[i][a] == turn){
						for(int b = a; b >= j ; b--){
							board.getBanmen()[i][b] = turn;
						}
						break;
					}
				}
			}

		//配列[7][7]の時、左、上、左斜め上にしか
		}else if(i == 7 && j == 7){

			//上方向への処理
			if(board.getBanmen()[i-1][j] == (turn ^ bit)){
				for(int a = i - 1; a >= 0; a--){
					if(board.getBanmen()[a][j] == 0){
						break;
					}else if(board.getBanmen()[a][j] == turn){
						for(int b = a; b <= i; b++){
							board.getBanmen()[b][j] = turn;
						}
						break;
					}
				}
			}

			//左斜め上方向への処理
			if(board.getBanmen()[i-1][j-1] == (turn ^ bit)){
				int a; int b = j - 1;
				for(a = i - 1; (a >= 0) && (b >= 0); a--, b--){
					if(board.getBanmen()[a][b] == 0){
						break;
					}else if(board.getBanmen()[a][b] == turn){
						for(int c = a; c <= i; c++){
							board.getBanmen()[c][b++] = turn;
						}
						break;
					}
				}
			}

			//左方向への処理
			if(board.getBanmen()[i][j-1] == (turn ^ bit)){
				for(int a = j - 1; a >= 0; a--){
					if(board.getBanmen()[i][a] == 0){
						break;
					}else if(board.getBanmen()[i][a] == turn){
						for(int b = a; b <= j ; b++){
							board.getBanmen()[i][b] = turn;
						}
						break;
					}
				}
			}

		//配列[0][j]の時、上側を検索しない
		}else if(i == 0){

			//左方向への処理
			if(board.getBanmen()[i][j-1] == (turn ^ bit)){
				for(int a = j - 1; a >= 0; a--){
					if(board.getBanmen()[i][a] == 0){
						break;
					}else if(board.getBanmen()[i][a] == turn){
						for(int b = a; b <= j ; b++){
							board.getBanmen()[i][b] = turn;
						}
						break;
					}
				}
			}

			//左斜め下方向への処理
			if(board.getBanmen()[i+1][j-1] == (turn ^ bit)){
				int a; int b = j - 1;
				for(a = i + 1; (a < 8) && (b >= 0); a++, b--){
					if(board.getBanmen()[a][b] == 0){
						break;
					}else if(board.getBanmen()[a][b] == turn){
						for(int c = a; c >= i; c--){
							board.getBanmen()[c][b++] = turn;
						}
						break;
					}
				}
			}

			//下方向への処理
			if(board.getBanmen()[i+1][j] == (turn ^ bit)){
				for(int a = i + 1; a < 8; a++){
					if(board.getBanmen()[a][j] == 0){
						break;
					}else if(board.getBanmen()[a][j] == turn){
						for(int b = a; b >= i; b--){
							board.getBanmen()[b][j] = turn;
						}
						break;
					}
				}
			}

			//右斜め下方向への処理
			if(board.getBanmen()[i+1][j+1] == (turn ^ bit)){
				int a; int b = j + 1;
				for(a = i + 1; (a < 8) && (b < 8); a++, b++){
					if(board.getBanmen()[a][b] == 0){
						break;
					}else if(board.getBanmen()[a][b] == turn){
						for(int c = a; c >= i; c--){
							board.getBanmen()[c][b--] = turn;
						}
						break;
					}
				}
			}

			//右方向への処理
			if(board.getBanmen()[i][j+1] == (turn ^ bit)){
				for(int a = j + 1; a < 8; a++){
					if(board.getBanmen()[i][a] == 0){
						break;
					}else if(board.getBanmen()[i][a] == turn){
						for(int b = a; b >= j ; b--){
							board.getBanmen()[i][b] = turn;
						}
						break;
					}
				}
			}

		//配列[i][7]の時、右側を検索しない
		}else if(j == 7){

			//上方向への処理
			if(board.getBanmen()[i-1][j] == (turn ^ bit)){
				for(int a = i - 1; a >= 0; a--){
					if(board.getBanmen()[a][j] == 0){
						break;
					}else if(board.getBanmen()[a][j] == turn){
						for(int b = a; b <= i; b++){
							board.getBanmen()[b][j] = turn;
						}
						break;
					}
				}
			}

			//左斜め上方向への処理
			if(board.getBanmen()[i-1][j-1] == (turn ^ bit)){
				int a; int b = j - 1;
				for(a = i - 1; (a >= 0) && (b >= 0); a--, b--){
					if(board.getBanmen()[a][b] == 0){
						break;
					}else if(board.getBanmen()[a][b] == turn){
						for(int c = a; c <= i; c++){
							board.getBanmen()[c][b++] = turn;
						}
						break;
					}
				}
			}

			//左方向への処理
			if(board.getBanmen()[i][j-1] == (turn ^ bit)){
				for(int a = j - 1; a >= 0; a--){
					if(board.getBanmen()[i][a] == 0){
						break;
					}else if(board.getBanmen()[i][a] == turn){
						for(int b = a; b <= j ; b++){
							board.getBanmen()[i][b] = turn;
						}
						break;
					}
				}
			}

			//左斜め下方向への処理
			if(board.getBanmen()[i+1][j-1] == (turn ^ bit)){
				int a; int b = j - 1;
				for(a = i + 1; (a < 8) && (b >= 0); a++, b--){
					if(board.getBanmen()[a][b] == 0){
						break;
					}else if(board.getBanmen()[a][b] == turn){
						for(int c = a; c >= i; c--){
							board.getBanmen()[c][b++] = turn;
						}
						break;
					}
				}
			}

			//下方向への処理
			if(board.getBanmen()[i+1][j] == (turn ^ bit)){
				for(int a = i + 1; a < 8; a++){
					if(board.getBanmen()[a][j] == 0){
						break;
					}else if(board.getBanmen()[a][j] == turn){
						for(int b = a; b >= i; b--){
							board.getBanmen()[b][j] = turn;
						}
						break;
					}
				}
			}


		//配列[i][0]の時、左側を検索しない
		}else if(j == 0){

			//上方向への処理
			if(board.getBanmen()[i-1][j] == (turn ^ bit)){
				for(int a = i - 1; a >= 0; a--){
					if(board.getBanmen()[a][j] == 0){
						break;
					}else if(board.getBanmen()[a][j] == turn){
						for(int b = a; b <= i; b++){
							board.getBanmen()[b][j] = turn;
						}
						break;
					}
				}
			}

			//右斜め上方向への処理
			if(board.getBanmen()[i-1][j+1] == (turn ^ bit)){
				int a; int b = j + 1;
				for(a = i - 1; (a >= 0) && (b < 8); a--, b++){
					if(board.getBanmen()[a][b] == 0){
						break;
					}else if(board.getBanmen()[a][b] == turn){
						for(int c = a; c <= i; c++){
							board.getBanmen()[c][b--] = turn;
						}
						break;
					}
				}
			}

			//右方向への処理
			if(board.getBanmen()[i][j+1] == (turn ^ bit)){
				for(int a = j + 1; a < 8; a++){
					if(board.getBanmen()[i][a] == 0){
						break;
					}else if(board.getBanmen()[i][a] == turn){
						for(int b = a; b >= j ; b--){
							board.getBanmen()[i][b] = turn;
						}
						break;
					}
				}
			}

			//右斜め下方向への処理
			if(board.getBanmen()[i+1][j+1] == (turn ^ bit)){
				int a; int b = j + 1;
				for(a = i + 1; (a < 8) && (b < 8); a++, b++){
					if(board.getBanmen()[a][b] == 0){
						break;
					}else if(board.getBanmen()[a][b] == turn){
						for(int c = a; c >= i; c--){
							board.getBanmen()[c][b--] = turn;
						}
						break;
					}
				}
			}

			//下方向への処理
			if(board.getBanmen()[i+1][j] == (turn ^ bit)){
				for(int a = i + 1; a < 8; a++){
					if(board.getBanmen()[a][j] == 0){
						break;
					}else if(board.getBanmen()[a][j] == turn){
						for(int b = a; b >= i; b--){
							board.getBanmen()[b][j] = turn;
						}
						break;
					}
				}
			}


		//配列[7][j]の時、下側を検索しない
		}else if(i == 7){

			//左方向への処理
			if(board.getBanmen()[i][j-1] == (turn ^ bit)){
				for(int a = j - 1; a >= 0; a--){
					if(board.getBanmen()[i][a] == 0){
						break;
					}else if(board.getBanmen()[i][a] == turn){
						for(int b = a; b <= j ; b++){
							board.getBanmen()[i][b] = turn;
						}
						break;
					}
				}
			}

			//左斜め上方向への処理
			if(board.getBanmen()[i-1][j-1] == (turn ^ bit)){
				int a; int b = j - 1;
				for(a = i - 1; (a >= 0) && (b >= 0); a--, b--){
					if(board.getBanmen()[a][b] == 0){
						break;
					}else if(board.getBanmen()[a][b] == turn){
						for(int c = a; c <= i; c++){
							board.getBanmen()[c][b++] = turn;
						}
						break;
					}
				}
			}

			//上方向への処理
			if(board.getBanmen()[i-1][j] == (turn ^ bit)){
				for(int a = i - 1; a >= 0; a--){
					if(board.getBanmen()[a][j] == 0){
						break;
					}else if(board.getBanmen()[a][j] == turn){
						for(int b = a; b <= i; b++){
							board.getBanmen()[b][j] = turn;
						}
						break;
					}
				}
			}

			//右斜め上方向への処理
			if(board.getBanmen()[i-1][j+1] == (turn ^ bit)){
				int a; int b = j + 1;
				for(a = i - 1; (a >= 0) && (b < 8); a--, b++){
					if(board.getBanmen()[a][b] == 0){
						break;
					}else if(board.getBanmen()[a][b] == turn){
						for(int c = a; c <= i; c++){
							board.getBanmen()[c][b--] = turn;
						}
						break;
					}
				}
			}

			//右方向への処理
			if(board.getBanmen()[i][j+1] == (turn ^ bit)){
				for(int a = j + 1; a < 8; a++){
					if(board.getBanmen()[i][a] == 0){
						break;
					}else if(board.getBanmen()[i][a] == turn){
						for(int b = a; b >= j ; b--){
							board.getBanmen()[i][b] = turn;
						}
						break;
					}
				}
			}

		//他の座標は周りをすべて調べる
		}else{

			//上方向への処理
			if(board.getBanmen()[i-1][j] == (turn ^ bit)){
				for(int a = i - 1; a >= 0; a--){
					if(board.getBanmen()[a][j] == 0){
						break;
					}else if(board.getBanmen()[a][j] == turn){
						for(int b = a; b <= i; b++){
							board.getBanmen()[b][j] = turn;
						}
						break;
					}
				}
			}

			//右方向への処理
			if(board.getBanmen()[i][j+1] == (turn ^ bit)){
				for(int a = j + 1; a < 8; a++){
					if(board.getBanmen()[i][a] == 0){
						break;
					}else if(board.getBanmen()[i][a] == turn){
						for(int b = a; b >= j ; b--){
							board.getBanmen()[i][b] = turn;
						}
						break;
					}
				}
			}

			//下方向への処理
			if(board.getBanmen()[i+1][j] == (turn ^ bit)){
				for(int a = i + 1; a < 8; a++){
					if(board.getBanmen()[a][j] == 0){
						break;
					}else if(board.getBanmen()[a][j] == turn){
						for(int b = a; b >= i; b--){
							board.getBanmen()[b][j] = turn;
						}
						break;
					}
				}
			}

			//左方向への処理
			if(board.getBanmen()[i][j-1] == (turn ^ bit)){
				for(int a = j - 1; a >= 0; a--){
					if(board.getBanmen()[i][a] == 0){
						break;
					}else if(board.getBanmen()[i][a] == turn){
						for(int b = a; b <= j ; b++){
							board.getBanmen()[i][b] = turn;
						}
						break;
					}
				}
			}

			//右斜め上方向への処理
			if(board.getBanmen()[i-1][j+1] == (turn ^ bit)){
				int a; int b = j + 1;
				for(a = i - 1; (a >= 0) && (b < 8); a--, b++){
					if(board.getBanmen()[a][b] == 0){
						break;
					}else if(board.getBanmen()[a][b] == turn){
						for(int c = a; c <= i; c++){
							board.getBanmen()[c][b--] = turn;
						}
						break;
					}

				}
			}

			//右斜め下方向への処理
			if(board.getBanmen()[i+1][j+1] == (turn ^ bit)){
				int a; int b = j + 1;
				for(a = i + 1; (a < 8) && (b < 8); a++, b++){
					if(board.getBanmen()[a][b] == 0){
						break;
					}else if(board.getBanmen()[a][b] == turn){
						for(int c = a; c >= i; c--){
							board.getBanmen()[c][b--] = turn;
						}
						break;
					}
				}
			}

			//左斜め下方向への処理
			if(board.getBanmen()[i+1][j-1] == (turn ^ bit)){
				int a; int b = j - 1;
				for(a = i + 1; (a < 8) && (b >= 0); a++, b--){
					if(board.getBanmen()[a][b] == 0){
						break;
					}else if(board.getBanmen()[a][b] == turn){
						for(int c = a; c >= i; c--){
							board.getBanmen()[c][b++] = turn;
						}
						break;
					}
				}
			}

			//左斜め上方向への処理
			if(board.getBanmen()[i-1][j-1] == (turn ^ bit)){
				int a; int b = j - 1;
				for(a = i - 1; (a >= 0) && (b >= 0); a--, b--){
					if(board.getBanmen()[a][b] == 0){
						break;
					}else if(board.getBanmen()[a][b] == turn){
						for(int c = a; c <= i; c++){
							board.getBanmen()[c][b++] = turn;
						}
						break;
					}
				}
			}
		}
	}


}
