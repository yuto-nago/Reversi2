package kenshu;

public class Board {

	//�Ֆʂ̒l�����������񎟌��z��
	private int[][] banmen = new int[8][8];

	/**
	 * �R���X�g���N�^
	 * �z�������������
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
	 * �Q�b�^�[���\�b�h
	 * @return�@int�^�񎟌��z��
	 */
	public int[][] getBanmen(){
		return banmen;
	}

	/**
	 * �Z�b�^�[���\�b�h
	 * @param banmen
	 */
	public void setBanmen(int[][]banmen){
		this.banmen = banmen;
	}

	/**
	 * �z��̏����R���\�[���ɏo�͂��郁�\�b�h
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
					System.out.print("�� ");
				}else if(banmen[i][j] == 2){
					System.out.print("�� ");
				}else{
					System.out.print("�� ");
				}
			}
			System.out.println();
		}
	}



}
