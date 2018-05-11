package kenshu;

public class Board {

	//盤面の値を所持した二次元配列
	private int[][] banmen = new int[8][8];

	/**
	 * コンストラクタ
	 * 配列を初期化する
	 */
	public Board(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if((i == 3 && j == 3) || (i == 4 && j == 4)){
					banmen [i][j] = 1;
				}else if((i == 3 && j == 4) || (i == 4 && j == 3)){
					banmen [i][j] = 2;
				}else{
					banmen[i][j] = 0;
				}

			}
		}
	}

	/**
	 * ゲッターメソッド
	 * @return　int型二次元配列
	 */
	public int[][] getBanmen(){
		return banmen;
	}

	/**
	 * セッターメソッド
	 * @param banmen
	 */
	public void setBanmen(int[][]banmen){
		this.banmen = banmen;
	}

	/**
	 * 配列の情報をコンソールに出力するメソッド
	 */
	public void display(){

		System.out.print("   ");
		for(int i = 0; i < 8; i++){
			System.out.print(i + "  ");
		}
		System.out.println();
		for(int i = 0; i < 8; i++){
			System.out.print((i) * 10 + " ");
			if(i == 0){
				System.out.print(" ");
			}
			for(int j = 0; j < 8; j++){
				if(banmen[i][j] == 1){
					System.out.print("○ ");
				}else if(banmen[i][j] == 2){
					System.out.print("● ");
				}else{
					System.out.print("＊ ");
				}
			}
			System.out.println();
		}
	}



}
