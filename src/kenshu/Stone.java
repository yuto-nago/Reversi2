package kenshu;

public class Stone {

	//���̐΂̐�
	private int black;

	//���̐΂̐�
	private int white;

	/**
	 * �R���X�g���N�^�ŏ�����
	 * @param black ���̐΂̐�
	 * @param white ���̐΂̐�
	 */
	public Stone(int black, int white){
		this.black = black;
		this.white = white;
	}

	/**
	 * �Q�b�^�[���\�b�h
	 * @return ���̐΂̐�
	 */
	public int getBlack(){
		return black;
	}

	/**
	 * �Q�b�^�[���\�b�h
	 * @return ���̐΂̐�
	 */
	public int getWhite(){
		return white;
	}

	/**
	 * �Z�b�^�[���\�b�h
	 * @param black
	 */
	public void setBlack(int black){
		this.black = black;
	}

	/**
	 * �Z�b�^�[���\�b�h
	 * @param white
	 */
	public void setWhite(int white){
		this.white = white;
	}

}
