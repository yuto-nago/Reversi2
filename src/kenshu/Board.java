package kenshu;

public class Board {

	//�Ֆʂ̒l�����������񎟌��z��
	private int[][] banmen = new int[8][8];

	/**
	 * �R���X�g���N�^
	 * �z�������������
	 */
	protected void init(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				//���̐΂��w��̍��W�ɒu��
				if((i == 3 && j == 3) || (i == 4 && j == 4)){
					banmen [i][j] = 1;
				//���̐΂��w��̍��W�ɒu��
				}else if((i == 3 && j == 4) || (i == 4 && j == 3)){
					banmen [i][j] = 2;
				//����ȊO��0����
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
	public void setBanmen(int[][] banmen){
		this.banmen = banmen;
	}

	/**
	 * �z��̏����R���\�[���ɏo�͂��郁�\�b�h
	 */
	public void display(){

		System.out.print("   ");

		//x���̔ԍ����o��
		for(int i = 0; i < 8; i++){
			System.out.print(i + "  ");
		}
		//���s
		System.out.println();


		for(int i = 0; i < 8; i++){
			//y���̔ԍ����o��
			System.out.print((i) * 10 + " ");
			//�����΂�ŏ��ɋ󔒂��o��
			if(i == 0){
				System.out.print(" ");
			}
			for(int j = 0; j < 8; j++){
				//�v�f�̒��g��1�Ȃ灛���o��
				if(banmen[i][j] == 1){
					System.out.print("�� ");
				//�v�f�̒��g��2�Ȃ灜���o��
				}else if(banmen[i][j] == 2){
					System.out.print("�� ");
				//����ȊO�́����o��
				}else{
					System.out.print("�� ");
				}
			}
			System.out.println();
		}
	}



}
