package kenshu;

import kenshu2.Util;

public class GamePlay {

	//Board�N���X�̎Q��
	private Board board;

	//Stone�N���X�̎Q��
	private Stone stone;

	//�ǂ���̃^�[�������ʂ���B����2�A����1
	public int turn = 2;

	//���͂��ꂽ�l��ێ�����ϐ�
	private int place;

	//�r�b�g���Z�Ɏg�p����ϐ�
	public final int bit = 3;

	//�Ֆʂ�x���W
	private int x;

	//�Ֆʂ�y���W
	private int y;


	/**
	 * �I�Z���Q�[�����J�n����
	 * �I���܂ł̈�A�̏��������郁�\�b�h
	 */
	public void play(){

		do{
			//Board�N���X�̃C���X�^���X���쐬
			board = new Board();

			//Stone�N���X�̃C���X�^���X���쐬
			stone = new Stone(2, 2);

			//�Ֆʂ̏�����
			board.init();

			//�Ֆʂ̒��g���R���\�[���ɏo��
			board.display();

			//�I�Z�����I������܂ŌJ��Ԃ�
			while(game_end() != 0){

				//���̃^�[���̏���
				if(turn == 2){
					//�p�X�łȂ��ꍇ
					if(pass() == 1){
						System.out.println("���̃^�[��");

						//�R���\�[�����琔�������
						place = Util.input();

						//���͂����l��10�̈ʂ�y�ɑ��
						y = place / 10;

						//���͂����l��1�̈ʂ�x�ɑ��
					    x = place % 10;

					  //���͂����l���z��̒l�𒴂��Ă���Ƃ�����x���͂��Ă��炤
						if((y < 0 || y >= 8) || (x < 0 || x >= 8)){
							System.out.println("0����7�̊Ԃœ��͂��Ă�������");
							continue;
						}

					    //�u���Ȃ���΍ŏ��ɖ߂�
					    if(put_check(y, x) == 0){

					    	System.out.println("�����ɂ͒u���܂���");
					    	continue;

					    }

					    //���Ԃ��̏��������s
					    check(y, x);

					    //�z��̒��g���R���\�[���ɕ\��
					    board.display();

					    //�^�[���̓���ւ�
					    turn = 1;

					//�p�X�̍ۂ̏���
					}else{
						System.out.println("���̓p�X�ł�");

						//�^�[���̓���ւ�
						turn = 1;

						break;
					}
				//���̃^�[���̏���
				}else if(turn == 1){
					//�p�X�łȂ��ꍇ
					if(pass() == 1){
						System.out.println("���̃^�[��");

						//�R���\�[�����琔�������
						place = Util.input();

						//���͂����l��10�̈ʂ�y�ɑ��
						y = place / 10;

						//���͂����l��1�̈ʂ�x�ɑ��
					    x = place % 10;

						//���͂����l���z��̒l�𒴂��Ă���Ƃ�����x���͂��Ă��炤
						if((y < 0 || y >= 8) || (x < 0 || x >= 8)){
							System.out.println("00����77�̊Ԃœ��͂��Ă�������");
							continue;
						}


					    //�u���Ȃ���΍ŏ��ɖ߂�
					    if(put_check(y, x) == 0){

					    	System.out.println("�����ɂ͒u���܂���");
					    	continue;

					    }

					    //���Ԃ��̏��������s
					    check(y, x);

					    //�z��̒��g���R���\�[���ɕ\��
					    board.display();

					    //�^�[���̓���ւ�
					    turn = 2;

					//�p�X�̍ۂ̏���
					}else{
						System.out.println("���̓p�X�ł�");

						//�^�[���̓���ւ�
						turn = 2;

						break;
					}
				}

			}
			//���s��\��
			judge();

		//�I�Z���Q�[���𑱂��邩�I��
		}while(Util.retry() != 0);






	}


	/**
	 * �Q�[�����I�����ǂ������ׂ郁�\�b�h
	 * @return �I���Ȃ�0�A�I���łȂ����1��Ԃ��B
	 */
	public int game_end(){

		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){

				//�z��̒��g��0�����邩���ׂ�
				if(board.getBanmen()[i][j] == 0){
					return 1;
				}
			}
		}

		//���Ɣ��̐΁A�ǂ��炩0�łȂ������ׂ�
		if(stone.getBlack() != 0 || stone.getWhite() != 0){
			return 1;
		}
		return 0;
	}

	/**
	 * ���s��\�����郁�\�b�h
	 */
	public void judge(){
		int black = 0 , white = 0;
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				//���̐΂𐔂���
				if(board.getBanmen()[i][j] == 2){
					black++;
				//���̐΂𐔂���
				}else if(board.getBanmen()[i][j] == 1){
					white++;
				}
			}
		}
		//��������������
		if(black < white){
			System.out.println(white + "���Ŕ��̏���");
		//�����������̍�
		}else if(black == white){
			System.out.println("��������");
		//��������������
		}else{
			System.out.println(black + "���ō��̏���");
		}

	}

	/**
	 * ���߂�΂����邩���ׂ郁�\�b�h
	 * @return ���ސ΂��������0��Ԃ��B���ސ΂������1��Ԃ��B
	 */
	public int pass(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				//�z��[0][0]�̎��A�E�A���A�E�΂߉��ɂ����������Ȃ�
				if(board.getBanmen()[i][j] == 0 && i == 0 && j == 0){
					//�E�����ɑ���̐΂���������
					if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
						//�E�����ɐi��ł���
						for(int a = j + 1; a < 8; a++){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}

					}

					//�������ɑ���̐΂���������
					if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
						//�������ɐi��ł���
						for(int a = i + 1; a < 8; a++){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][j] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}

					}

					//�E�΂߉������ɑ���̐΂���������
					if(board.getBanmen()[i + 1][j + 1] == (turn ^ bit)){
						int b = j + 1;
						//�E�������ɐi��ł���
						for(int a = i + 1; (a < 8) && (b < 8); a++, b++){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
					}

				//�z��[0][7]�̎��A���A���A���΂߉��ɂ����������Ȃ�
				}else if(board.getBanmen()[i][j] == 0 && i == 0 && j == 7){

					//�������ɑ���̐΂���������
					if(board.getBanmen()[i][j-1] == (turn ^ bit)){
						//�������ɐi��ł���
						for(int a = j - 1; a >= 0; a--){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}

				    }
					//�������ɑ���̐΂���������
					if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
						//�������֐i��ł���
				    	for(int a = i + 1; a < 8; a++){
				    		//�΂��u���ĂȂ��ꏊ����������
				    		if(board.getBanmen()[a][j] == 0){
				    			break;
				    		//�����̐΂���������
				    		}else if(board.getBanmen()[a][j] == 1){
								return 1;
							}
						}

				    }
					//���΂߉������ɑ���̐΂���������
					if(board.getBanmen()[i + 1][j - 1] == (turn ^ bit)){
				    	int b = j - 1;
				    	//�����֐i��ł���
						for(int a = i + 1; (a < 8) && (b >= 0); a++, b--){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
				    }

				//�z��[7][0]�̎��A��A�E�A�E�΂ߏ�ɂ����������Ȃ�
			    }else if(board.getBanmen()[i][j] == 0 && i == 7 && j == 0){

			    	//������ɑ���̐΂���������
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		//������֐i��ł���
			    		for(int a = i - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			//�����̐΂���������
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}

			    	}

			    	//�E�����ɑ���̐΂���������
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		//�E�����֐i��ł���
			    		for(int a = j + 1; a < 8; a++){
			    			//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}

			    	}

			    	//�E�΂ߏ�����ɑ���̐΂���������
			    	if(board.getBanmen()[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
			    		//�E������֐i��ł���
						for(int a = i - 1; (a >= 0) && (b < 8); a--, b++){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    //�z��[7][7]�̎��A���A��A���΂ߏ�ɂ����������Ȃ�
			    }else if(board.getBanmen()[i][j] == 0 && i == 7 && j == 7){

			    	//�������ɑ���̐΂���������
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		//�������֐i��ł���
			    		for(int a = j - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}
			    	//������ɑ���̐΂���������
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		//������֐i��ł���
			    		for(int a = i - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			//�����̐΂���������
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}
			    	}
			    	//���΂ߏ�����ɑ���̐΂���������
			    	if(board.getBanmen()[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
			    		//��������֐i��ł���
						for(int a = i - 1; (a >= 0) && (b >= 0); a--, b--){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    //�z��[0][j]�̎��A�㑤���������Ȃ�
			    }else if(board.getBanmen()[i][j] == 0 && i == 0){

			    	//�������ɑ���̐΂���������
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		//�������֐i��ł���
			    		for(int a = j - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}
			    	//���΂߉������ւ̌���
			    	if(board.getBanmen()[i + 1][j - 1] == (turn ^ bit)){
			    		int b = j - 1;
			    		//���������֐i��ł���
						for(int a = i + 1; (a < 8) && (b >= 0); a++, b--){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}
			    	//�������ɑ���̐΂���������
			    	if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
			    		//�������֐i��ł���
			    		for(int a = i + 1; a < 8; a++){
			    			//�΂��u���ĂȂ��ꏊ����������
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			//�����̐΂���������
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}
			    	}

			    	//�E�΂߉������ɑ���̐΂���������
			    	if(board.getBanmen()[i + 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
			    		//�E�������֐i��ł���
						for(int a = i + 1; (a < 8) && (b < 8); a++, b++){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//�E�����ɑ���̐΂���������
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		//�E�����֐i��ł���
			    		for(int a = j + 1; a < 8; a++){
			    			//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    //�z��[i][0]�̎��A�������������Ȃ�
			    }else if(board.getBanmen()[i][j] == 0 && j == 0){

			    	//������ɑ���̐΂���������
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		//������֐i��ł���
			    		for(int a = i - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			//�����̐΂���������
			    			}else if(board.getBanmen()[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//�E�΂ߏ�����ɑ���̐΂���������
			    	if(board.getBanmen()[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
			    		//�E������֐i��ł���
						for(int a = i - 1; (a >= 0) && (b < 8); a--, b++){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//�E�����ɑ���̐΂���������
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		//�E�����֐i��ł���
			    		for(int a = j + 1; a < 8; a++){
			    			//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//�E�΂߉������ɑ���̐΂���������
			    	if(board.getBanmen()[i + 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
			    		//�E�������֐i��ł���
						for(int a = i + 1; (a < 8) && (b < 8); a++, b++){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//�������ɑ���̐΂���������
			    	if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
			    		//�������֐i��ł���
			    		for(int a = i + 1; a < 8; a++){
			    			//�΂��u���ĂȂ��ꏊ����������
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			//�����̐΂���������
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}
			    	}

			    //�z��[i][7]�̎��A�E�����������Ȃ�
			    }else if(board.getBanmen()[i][j] == 0 && j == 7){

			    	//������ɑ���̐΂���������
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		//������֐i��ł���
			    		for(int a = i - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			//�����̐΂���������
			    			}else if(board.getBanmen()[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//���΂ߏ�����ɑ���̐΂���������
			    	if(board.getBanmen()[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
			    		//��������֐i��ł���
						for(int a = i - 1; (a >= 0) && (b >= 0); a--, b--){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//�������ɑ���̐΂���������
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		//�������֐i��ł���
			    		for(int a = j - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//���΂߉������ɑ���̐΂���������
			    	if(board.getBanmen()[i + 1][j - 1] == (turn ^ bit)){
			    		int b = j - 1;
			    		//���������֐i��ł���
						for(int a = i + 1; (a < 8) && (b >= 0); a++, b--){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//�������ɑ���̐΂���������
			    	if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
			    		//�������֐i��ł���
			    		for(int a = i + 1; a < 8; a++){
			    			//�΂��u���ĂȂ��ꏊ����������
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			//�����̐΂���������
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}
			    	}

			    //�z��[7][j]�̎��A�������������Ȃ�
			    }else if(board.getBanmen()[i][j] == 0 && i == 7){

			    	//�������ɑ���̐΂���������
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		//�������֐i��ł���
			    		for(int a = j - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//���΂ߏ�����ɑ���̐΂���������
			    	if(board.getBanmen()[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
			    		//��������֐i��ł���
						for(int a = i - 1; (a > 0) && (b >= 0); a--, b--){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//������ɑ���̐΂���������
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		//������֐i��ł���
			    		for(int a = i - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			//�����̐΂���������
			    			}else if(board.getBanmen()[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//�E�΂ߏ�����ɑ���̐΂���������
			    	if(board.getBanmen()[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
			    		//�E������֐i��ł���
						for(int a = i - 1; (a >= 0) && (b < 8); a--, b++){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//�E�����ɑ���̐΂���������
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		//�E�����֐i��ł���
			    		for(int a = j + 1; a < 8; a++){
			    			//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    //���̍��W�͎�������ׂĒ��ׂ�
			    }else if(board.getBanmen()[i][j] == 0){

			    	//������ɑ���̐΂���������
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		//������֐i��ł���
			    		for(int a = i - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			//�����̐΂���������
			    			}else if(board.getBanmen()[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//�E�΂ߏ�����ɑ���̐΂���������
			    	if(board.getBanmen()[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
			    		//�E������֐i��ł���
						for(int a = i - 1; (a >= 0) && (b < 8); a--, b++){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//�E�����ɑ���̐΂���������
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		//�E�����֐i��ł���
			    		for(int a = j + 1; a < 8; a++){
			    			//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//�E�΂߉������ɑ���̐΂���������
			    	if(board.getBanmen()[i + 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
			    		//�E�������֐i��ł���
						for(int a = i + 1; (a < 8) && (b < 8); a++, b++){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//�������ɑ���̐΂���������
			    	if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
			    		//�������֐i��ł���
			    		for(int a = i + 1; a < 8; a++){
			    			//�΂��u���ĂȂ��ꏊ����������
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			//�����̐΂���������
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}
			    	}

			    	//���΂߉������ɑ���̐΂���������
			    	if(board.getBanmen()[i + 1][j - 1] == (turn ^ bit)){
			    		int b = j - 1;
			    		//���������֐i��ł���
						for(int a = i + 1; (a < 8) && (b >= 0); a++, b--){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//�������ɑ���̐΂���������
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		//�������֐i��ł���
			    		for(int a = j - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//���΂ߏ�����ɑ���̐΂���������
			    	if(board.getBanmen()[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
			    		//��������֐i��ł���
						for(int a = i - 1; (a >= 0) && (b >= 0); a--, b--){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
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
	 * �΂�u���邩�ǂ������ׂ郁�\�b�h
	 * @param i�@�R���\�[��������͂��ꂽ�\�̈ʂ̐���
	 * @param j�@�R���\�[��������͂��ꂽ��̈ʂ̐���
	 */
	public void check(int i, int j){
				//�z��[0][0]�̎��A�E�A���A�E�΂߉��ɂ����������Ȃ�
				if(board.getBanmen()[i][j] == 0 && i == 0 && j == 0){
					//�E�����ɑ���̐΂��������ۂ�
					if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
						//�E�����֐i��ł���
						for(int a = j + 1; a < 8; a++){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								//���Ԃ�����
								flip_E(i, j);
							}
						}

					}

					//�������ɑ���̐΂���������
					if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
						//�������֐i��ł���
						for(int a = i + 1; a < 8; a++){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][j] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][j] == turn){
								//���Ԃ�����
								flip_S(i, j);
							}
						}

					}

					//�E�΂߉��ɑ���̐΂���������
					if(board.getBanmen()[i + 1][j + 1] == (turn ^ bit)){
						int b = j + 1;
						//�E�������֐i��ł���
						for(int a = i + 1; (a < 8) && (b < 8); a++, b++){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								//���Ԃ�����
								flip_SE(i, j);
							}
						}
					}


				//�z��[0][7]�̎��A���A���A���΂߉��ɂ����������Ȃ�
				}else if(board.getBanmen()[i][j] == 0 && i == 0 && j == 7){

					//�������ɑ���̐΂���������
					if(board.getBanmen()[i][j-1] == (turn ^ bit)){
						//�������֐i��ł���
						for(int a = j - 1; a >= 0; a--){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								//���Ԃ�����
								flip_W(i, j);
							}
						}

				    }
					//�������ɑ���̐΂���������
					if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
						//�������֐i��ł���
				    	for(int a = i + 1; a < 8; a++){
				    		//�΂��u���ĂȂ��ꏊ����������
				    		if(board.getBanmen()[a][j] == 0){
				    			break;
				    		//�����̐΂���������
				    		}else if(board.getBanmen()[a][j] == turn){
				    			//���Ԃ�����
								flip_S(i, j);
							}
						}

				    }
					//���΂߉��ɑ���̐΂���������
					if(board.getBanmen()[i + 1][j - 1] == (turn ^ bit)){
				    	int b = j - 1;
				    	//���������֐i��ł���
						for(int a = i + 1; (a < 8) && (b >= 0); a++, b--){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								//���Ԃ�����
								flip_SW(i, j);
							}
						}
				    }

				//�z��[7][0]�̎��A��A�E�A�E�΂ߏ�ɂ����������Ȃ�
			    }else if(board.getBanmen()[i][j] == 0 && i == 7 && j == 0){

			    	//������ɑ���̐΂���������
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		//������ɐi��ł���
			    		for(int a = i - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			//�����̐΂���������
			    			}else if(board.getBanmen()[a][j] == turn){
			    				//���Ԃ�����
								flip_N(i, j);
							}
						}

			    	}

			    	//�E�����ɑ���̐΂���������
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		//�E�����֐i��ł���
			    		for(int a = j + 1; a < 8; a++){
			    			//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								//���Ԃ�����
								flip_E(i, j);
							}
						}

			    	}

			    	//�E�΂ߏ�����ɑ���̐΂���������
			    	if(board.getBanmen()[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
			    		//�E������֐i��ł���
						for(int a = i - 1; (a >= 0) && (b < 8); a--, b++){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								//���Ԃ�����
								flip_NE(i, j);
							}
						}
			    	}

			    //�z��[7][7]�̎��A���A��A���΂ߏ�ɂ����������Ȃ�
			    }else if(board.getBanmen()[i][j] == 0 && i == 7 && j == 7){

			    	//�������ɑ���̐΂���������
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		//�������֐i��ł���
			    		for(int a = j - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								//���Ԃ�����
								flip_W(i, j);
							}
						}
			    	}
			    	//������ɑ���̐΂���������
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		//������֐i��ł���
			    		for(int a = i - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			//�����̐΂���������
			    			}else if(board.getBanmen()[a][j] == turn){
			    				//���Ԃ�����
								flip_N(i, j);
							}
						}
			    	}
			    	//���΂ߏ�����ɑ���̐΂���������
			    	if(board.getBanmen()[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
			    		//��������֐i��ł���
						for(int a = i - 1; (a >= 0) && (b >= 0); a--, b--){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								//���Ԃ�����
								flip_NW(i, j);
							}
						}
			    	}

			    //�z��[0][j]�̎��A�㑤���������Ȃ�
			    }else if(board.getBanmen()[i][j] == 0 && i == 0){

			    	//�������ɑ���̐΂���������
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		//�������ɐi��ł���
			    		for(int a = j - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								//���Ԃ�����
								flip_W(i, j);
							}
						}
			    	}
			    	//���΂߉������ɑ���̐΂���������
			    	if(board.getBanmen()[i + 1][j - 1] == (turn ^ bit)){
			    		int b = j - 1;
			    		//���������֐i��ł���
						for(int a = i + 1; (a < 8) && (b >= 0); a++, b--){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								//���Ԃ�����
								flip_SW(i, j);
							}
						}
			    	}
			    	//�������ɑ���̐΂���������
			    	if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
			    		//�������֐i��ł���
			    		for(int a = i + 1; a < 8; a++){
			    			//�΂��u���ĂȂ��ꏊ����������
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			//�����̐΂���������
			    			}else if(board.getBanmen()[a][j] == turn){
			    				//���Ԃ�����
								flip_S(i, j);
							}
						}
			    	}

			    	//�E�΂߉������ɑ���̐΂���������
			    	if(board.getBanmen()[i + 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
			    		//�E�������֐i��ł���
						for(int a = i + 1; (a < 8) && (b < 8); a++, b++){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								//���Ԃ�����
								flip_SE(i, j);
							}
						}
			    	}

			    	//�E�����ɑ���̐΂���������
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		//�E�����֐i��ł���
			    		for(int a = j + 1; a < 8; a++){
			    			//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								//���Ԃ�����
								flip_E(i, j);
							}
						}
			    	}

			    //�z��[i][0]�̎��A�������������Ȃ�
			    }else if(board.getBanmen()[i][j] == 0 && j == 0){

			    	//������ɑ���̐΂���������
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		//������֐i��ł���
			    		for(int a = i - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			//�����̐΂���������
			    			}else if(board.getBanmen()[a][j] == turn){
			    				//���Ԃ�����
			    				flip_N(i, j);
			    			}
			    		}
			    	}

			    	//�E�΂ߏ�����ɑ���̐΂���������
			    	if(board.getBanmen()[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
			    		//�E������֐i��ł���
						for(int a = i - 1; (a >= 0) && (b < 8); a--, b++){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								//���Ԃ�����
								flip_NE(i, j);
							}
						}
			    	}

			    	//�E�����ɑ���̐΂���������
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		//�E�����֐i��ł���
			    		for(int a = j + 1; a < 8; a++){
			    			//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								//���Ԃ�����
								flip_E(i, j);
							}
						}
			    	}

			    	//�E�΂߉������ɑ���̐΂���������
			    	if(board.getBanmen()[i + 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
			    		//�E�������֐i��ł���
						for(int a = i + 1; (a < 8) && (b < 8); a++, b++){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								//���Ԃ�����
								flip_SE(i, j);
							}
						}
			    	}

			    	//�������ɑ���̐΂���������
			    	if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
			    		//�������֐i��ł���
			    		for(int a = i + 1; a < 8; a++){
			    			//�΂��u���ĂȂ��ꏊ����������
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			//�����̐΂���������
			    			}else if(board.getBanmen()[a][j] == turn){
			    				//���Ԃ�����
								flip_S(i, j);
							}
						}
			    	}

			    //�z��[i][7]�̎��A�E�����������Ȃ�
			    }else if(board.getBanmen()[i][j] == 0 && j == 7){

			    	//������ɑ���̐΂���������
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		//������֐i��ł���
			    		for(int a = i - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			//�����̐΂���������
			    			}else if(board.getBanmen()[a][j] == turn){
			    				//���Ԃ�����
			    				flip_N(i, j);
			    			}
			    		}
			    	}

			    	//���΂ߏ�����ɑ���̐΂���������
			    	if(board.getBanmen()[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
			    		//��������֐i��ł���
						for(int a = i - 1; (a >= 0) && (b >= 0); a--, b--){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								//���Ԃ�����
								flip_NW(i, j);
							}
						}
			    	}

			    	//�������ɑ���̐΂���������
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		//�������֐i��ł���
			    		for(int a = j - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								//���Ԃ�����
								flip_W(i, j);
							}
						}
			    	}

			    	//���΂߉������ɑ���̐΂���������
			    	if(board.getBanmen()[i + 1][j - 1] == (turn ^ bit)){
			    		int b = j - 1;
			    		//���������֐i��ł���
						for(int a = i + 1; (a < 8) && (b >= 0); a++, b--){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								//���Ԃ�����
								flip_SW(i, j);
							}
						}
			    	}

			    	//�������ɑ���̐΂���������
			    	if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
			    		//�������֐i��ł���
			    		for(int a = i + 1; a < 8; a++){
			    			//�΂��u���ĂȂ��ꏊ����������
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			//�����̐΂���������
			    			}else if(board.getBanmen()[a][j] == turn){
			    				//���Ԃ�����
								flip_S(i, j);
							}
						}
			    	}

			    //�z��[7][j]�̎��A�������������Ȃ�
			    }else if(board.getBanmen()[i][j] == 0 && i == 7){

			    	//�������ɑ���̐΂���������
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		//�������֐i��ł���
			    		for(int a = j - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								//���Ԃ�����
								flip_W(i, j);
							}
						}
			    	}

			    	//���΂ߏ�����ɑ���̐΂���������
			    	if(board.getBanmen()[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
			    		//��������֐i��ł���
						for(int a = i - 1; (a >= 0) && (b >= 0); a--, b--){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								//���Ԃ�����
								flip_NW(i, j);
							}
						}
			    	}

			    	//������ɑ���̐΂���������
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		//������֐i��ł���
			    		for(int a = i - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			//�����̐΂���������
			    			}else if(board.getBanmen()[a][j] == turn){
			    				//���Ԃ�����
			    				flip_N(i, j);
			    			}
			    		}
			    	}

			    	//�E�΂ߏ�����ɑ���̐΂���������
			    	if(board.getBanmen()[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
			    		//�E������֐i��ł���
						for(int a = i - 1; (a >= 0) && (b < 8); a--, b++){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								//���Ԃ�����
								flip_NE(i, j);
							}
						}
			    	}

			    	//�E�����ɑ���̐΂���������
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		//�E�����֐i��ł���
			    		for(int a = j + 1; a < 8; a++){
			    			//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								//���Ԃ�����
								flip_E(i, j);
							}
						}
			    	}

			    //���̍��W�͎�������ׂĒ��ׂ�
			    }else if(board.getBanmen()[i][j] == 0){

			    	//������ɑ���̐΂���������
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		//������֐i��ł���
			    		for(int a = i - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			//�����̐΂���������
			    			}else if(board.getBanmen()[a][j] == turn){
			    				//���Ԃ�����
			    				flip_N(i, j);
			    			}
			    		}
			    	}

			    	//�E�΂ߏ�����ɑ���̐΂���������
			    	if(board.getBanmen()[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
			    		//�E������֐i��ł���
						for(int a = i - 1; (a >= 0) && (b < 8); a--, b++){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								//���Ԃ�����
								flip_NE(i, j);
							}

						}
			    	}

			    	//�E�����ɑ���̐΂���������
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		//�E�����֐i��ł���
			    		for(int a = j + 1; a < 8; a++){
			    			//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								//���Ԃ�����
								flip_E(i, j);
							}
						}
			    	}

			    	//�E�΂߉������ɑ���̐΂���������
			    	if(board.getBanmen()[i + 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
			    		//�E�������֐i��ł���
						for(int a = i + 1; (a < 8) && (b < 8); a++, b++){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								//���Ԃ�����
								flip_SE(i, j);
							}
						}
			    	}

			    	//�������ɑ���̐΂���������
			    	if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
			    		//�������֐i��ł���
			    		for(int a = i + 1; a < 8; a++){
			    			//�΂��u���ĂȂ��ꏊ����������
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			//�����̐΂���������
			    			}else if(board.getBanmen()[a][j] == turn){
			    				//���Ԃ�����
								flip_S(i, j);
							}
						}
			    	}

			    	//���΂߉������ɑ���̐΂���������
			    	if(board.getBanmen()[i + 1][j - 1] == (turn ^ bit)){
			    		int b = j - 1;
			    		//���������֐i��ł���
						for(int a = i + 1; (a < 8) && (b >= 0); a++, b--){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								//���Ԃ�����
								flip_SW(i, j);
							}
						}
			    	}

			    	//�������ɑ���̐΂���������
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		//�������֐i��ł���
			    		for(int a = j - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								//���Ԃ�����
								flip_W(i, j);
							}
						}
			    	}

			    	//���΂ߏ�����ɑ���̐΂���������
			    	if(board.getBanmen()[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
			    		//��������֐i��ł���
						for(int a = i - 1; (a >= 0) && (b >= 0); a--, b--){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								//���Ԃ�����
								flip_NW(i, j);
							}
						}
			    	}

			    }



	}


	//������𗠕Ԃ�����
	private void flip_N(int i, int j){
		//�Ֆʂ̃R�s�[
		int[][] banmen_copy;

		//������ɑ���̐΂���������
		if(board.getBanmen()[i-1][j] == (turn ^ bit)){
			//������֐i��ł���
			for(int a = i - 1; a >= 0; a--){
				//�΂��u���ĂȂ��ꏊ����������
				if(board.getBanmen()[a][j] == 0){
					break;
				//�����̐΂���������
				}else if(board.getBanmen()[a][j] == turn){
					//�Ֆʂ̃R�s�[���쐬
					banmen_copy = board.getBanmen();
					//�߂�Ȃ��玩���̐΂ɕς��Ă���
					for(int b = a; b <= i; b++){
						banmen_copy[b][j] = turn;
					}
					//�R�s�[�̓��e���Z�b�g����
					board.setBanmen(banmen_copy);
					break;
				}
			}
		}


	}
	//�������𗠕Ԃ�����
	private void flip_S(int i, int j){

		//�Ֆʂ̃R�s�[
		int[][] banmen_copy;

		//�������ɑ���̐΂���������
		if(board.getBanmen()[i+1][j] == (turn ^ bit)){
			//�������֐i��ł���
			for(int a = i + 1; a < 8; a++){
				//�΂��u���ĂȂ��ꏊ����������
				if(board.getBanmen()[a][j] == 0){
					break;
				//�����̐΂���������
				}else if(board.getBanmen()[a][j] == turn){
					//�Ֆʂ̃R�s�[���쐬
					banmen_copy = board.getBanmen();
					//�߂�Ȃ��玩���̐΂ɕς��Ă���
					for(int b = a; b >= i; b--){
						banmen_copy[b][j] = turn;
					}
					//�R�s�[�̓��e���Z�b�g����
					board.setBanmen(banmen_copy);
					break;
				}
			}
		}


}

	//�E�����𗠕Ԃ�����
	private void flip_E(int i, int j){

		//�Ֆʂ̃R�s�[
		int[][] banmen_copy;

	//�E�����ɑ���̐΂���������
		if(board.getBanmen()[i][j+1] == (turn ^ bit)){
			//�E�����֐i��ł���
			for(int a = j + 1; a < 8; a++){
				//�΂��u���ĂȂ��ꏊ����������
				if(board.getBanmen()[i][a] == 0){
					break;
				//�����̐΂���������
				}else if(board.getBanmen()[i][a] == turn){
					//�Ֆʂ̃R�s�[���쐬
					banmen_copy = board.getBanmen();
					//�߂�Ȃ��玩���̐΂ɕς��Ă���
					for(int b = a; b >= j ; b--){
					banmen_copy[i][b] = turn;
					}
					//�R�s�[�̓��e���Z�b�g
					board.setBanmen(banmen_copy);
					break;
				}
			}
		}

	}
	//�������𗠕Ԃ�����
	private void flip_W(int i, int j){

		//�Ֆʂ̃R�s�[
		int[][] banmen_copy;

		//�������ɑ���̐΂���������
		if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			//�������֐i��ł���
			for(int a = j - 1; a >= 0; a--){
				//�΂��u���ĂȂ��ꏊ����������
				if(board.getBanmen()[i][a] == 0){
					break;
				//�����̐΂���������
				}else if(board.getBanmen()[i][a] == turn){
					//�Ֆʂ̃R�s�[���쐬
					banmen_copy = board.getBanmen();
					//�߂�Ȃ��玩���̐΂ɕς��Ă���
					for(int b = a; b <= j ; b++){
						banmen_copy[i][b] = turn;
					}
					//�R�s�[�̓��e���Z�b�g
					board.setBanmen(banmen_copy);
					break;
				}
			}
		}

	}
	//�E������𗠕Ԃ�����
	private void flip_NE(int i, int j){

		//�Ֆʂ̃R�s�[
		int[][] banmen_copy;

		//�E�΂ߏ�����ɑ���̐΂���������
		if(board.getBanmen()[i-1][j+1] == (turn ^ bit)){
			int a; int b = j + 1;
			//�E������֐i��ł���
			for(a = i - 1; (a >= 0) && (b < 8); a--, b++){
				//�΂��u���ĂȂ��ꏊ����������
				if(board.getBanmen()[a][b] == 0){
					break;
				//�����̐΂���������
				}else if(board.getBanmen()[a][b] == turn){
					//�Ֆʂ̃R�s�[���쐬
					banmen_copy = board.getBanmen();
					//�߂�Ȃ��玩���̐΂ɕς��Ă���
					for(int c = a; c <= i; c++){
						banmen_copy[c][b--] = turn;
					}
					//�R�s�[�̓��e���Z�b�g
					board.setBanmen(banmen_copy);
					break;
				}

			}
		}

	}
	//�E�������𗠕Ԃ�����
	private void flip_SE(int i, int j){

		//�Ֆʂ̃R�s�[
		int[][] banmen_copy;

		//�E�΂߉������ɑ���̐΂���������
		if(board.getBanmen()[i+1][j+1] == (turn ^ bit)){
			int a; int b = j + 1;
			//�E�������֐i��ł���
			for(a = i + 1; (a < 8) && (b < 8); a++, b++){
				//�΂��u���ĂȂ��ꏊ����������
				if(board.getBanmen()[a][b] == 0){
					break;
				//�����̐΂���������
				}else if(board.getBanmen()[a][b] == turn){
					//�Ֆʂ̃R�s�[���쐬
					banmen_copy = board.getBanmen();
					//�߂�Ȃ��玩���̐΂ɕς��Ă���
					for(int c = a; c >= i; c--){
						banmen_copy[c][b--] = turn;
					}
					//�R�s�[�̓��e���Z�b�g
					board.setBanmen(banmen_copy);
					break;
				}
			}
		}

	}
	//���������𗠕Ԃ�����
	private void flip_SW(int i, int j){

		//�Ֆʂ̃R�s�[
		int[][] banmen_copy;

		//���΂߉������ɑ���̐΂���������
		if(board.getBanmen()[i+1][j-1] == (turn ^ bit)){
			int a; int b = j - 1;
			//���������֐i��ł���
			for(a = i + 1; (a < 8) && (b >= 0); a++, b--){
				//�΂��u���ĂȂ��ꏊ����������
				if(board.getBanmen()[a][b] == 0){
					break;
				//�����̐΂���������
				}else if(board.getBanmen()[a][b] == turn){
					//�Ֆʂ̃R�s�[���쐬
					banmen_copy = board.getBanmen();
					//�߂�Ȃ��玩���̐΂ɕς��Ă���
					for(int c = a; c >= i; c--){
						banmen_copy[c][b++] = turn;
					}
					//�R�s�[�̓��e���Z�b�g
					board.setBanmen(banmen_copy);
					break;
				}
			}
		}

	}
	//��������𗠕Ԃ�����
	private void flip_NW(int i, int j){

		//�Ֆʂ̃R�s�[
		int[][] banmen_copy;

		//���΂ߏ�����ɑ���̐΂���������
		if(board.getBanmen()[i-1][j-1] == (turn ^ bit)){
			int a; int b = j - 1;
			//��������֐i��ł���
			for(a = i - 1; (a >= 0) && (b >= 0); a--, b--){
				//�΂��u���ĂȂ��ꏊ����������
				if(board.getBanmen()[a][b] == 0){
					break;
				//�����̐΂���������
				}else if(board.getBanmen()[a][b] == turn){
					//�Ֆʂ̃R�s�[���쐬
					banmen_copy = board.getBanmen();
					//�߂�Ȃ��玩���̐΂ɕς��Ă���
					for(int c = a; c <= i; c++){
						banmen_copy[c][b++] = turn;
					}
					//�R�s�[�̓��e���Z�b�g
					board.setBanmen(banmen_copy);
					break;
				}
			}
		}

	}


	/**
	 * ���߂�΂����邩���ׂ郁�\�b�h
	 * @return ���ސ΂��������0��Ԃ��B���ސ΂������1��Ԃ��B
	 */
	public int put_check(int i, int j){
				//�z��[0][0]�̎��A�E�A���A�E�΂߉��ɂ����������Ȃ�
				if(board.getBanmen()[i][j] == 0 && i == 0 && j == 0){
					//�E�����ɑ���̐΂���������
					if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
						//�E�����ɐi��ł���
						for(int a = j + 1; a < 8; a++){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}

					}

					//�������ɑ���̐΂���������
					if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
						//�������ɐi��ł���
						for(int a = i + 1; a < 8; a++){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][j] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}

					}

					//�E�΂߉������ɑ���̐΂���������
					if(board.getBanmen()[i + 1][j + 1] == (turn ^ bit)){
						int b = j + 1;
						//�E�������ɐi��ł���
						for(int a = i + 1; (a < 8) && (b < 8); a++, b++){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
					}

				//�z��[0][7]�̎��A���A���A���΂߉��ɂ����������Ȃ�
				}else if(board.getBanmen()[i][j] == 0 && i == 0 && j == 7){

					//�������ɑ���̐΂���������
					if(board.getBanmen()[i][j-1] == (turn ^ bit)){
						//�������ɐi��ł���
						for(int a = j - 1; a >= 0; a--){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}

				    }
					//�������ɑ���̐΂���������
					if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
						//�������֐i��ł���
				    	for(int a = i + 1; a < 8; a++){
				    		//�΂��u���ĂȂ��ꏊ����������
				    		if(board.getBanmen()[a][j] == 0){
				    			break;
				    		//�����̐΂���������
				    		}else if(board.getBanmen()[a][j] == 1){
								return 1;
							}
						}

				    }
					//���΂߉������ɑ���̐΂���������
					if(board.getBanmen()[i + 1][j - 1] == (turn ^ bit)){
				    	int b = j - 1;
				    	//�����֐i��ł���
						for(int a = i + 1; (a < 8) && (b >= 0); a++, b--){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
				    }

				//�z��[7][0]�̎��A��A�E�A�E�΂ߏ�ɂ����������Ȃ�
			    }else if(board.getBanmen()[i][j] == 0 && i == 7 && j == 0){

			    	//������ɑ���̐΂���������
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		//������֐i��ł���
			    		for(int a = i - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			//�����̐΂���������
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}

			    	}

			    	//�E�����ɑ���̐΂���������
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		//�E�����֐i��ł���
			    		for(int a = j + 1; a < 8; a++){
			    			//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}

			    	}

			    	//�E�΂ߏ�����ɑ���̐΂���������
			    	if(board.getBanmen()[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
			    		//�E������֐i��ł���
						for(int a = i - 1; (a >= 0) && (b < 8); a--, b++){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    //�z��[7][7]�̎��A���A��A���΂ߏ�ɂ����������Ȃ�
			    }else if(board.getBanmen()[i][j] == 0 && i == 7 && j == 7){

			    	//�������ɑ���̐΂���������
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		//�������֐i��ł���
			    		for(int a = j - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}
			    	//������ɑ���̐΂���������
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		//������֐i��ł���
			    		for(int a = i - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			//�����̐΂���������
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}
			    	}
			    	//���΂ߏ�����ɑ���̐΂���������
			    	if(board.getBanmen()[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
			    		//��������֐i��ł���
						for(int a = i - 1; (a >= 0) && (b >= 0); a--, b--){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    //�z��[0][j]�̎��A�㑤���������Ȃ�
			    }else if(board.getBanmen()[i][j] == 0 && i == 0){

			    	//�������ɑ���̐΂���������
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		//�������֐i��ł���
			    		for(int a = j - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}
			    	//���΂߉������ւ̌���
			    	if(board.getBanmen()[i + 1][j - 1] == (turn ^ bit)){
			    		int b = j - 1;
			    		//���������֐i��ł���
						for(int a = i + 1; (a < 8) && (b >= 0); a++, b--){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}
			    	//�������ɑ���̐΂���������
			    	if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
			    		//�������֐i��ł���
			    		for(int a = i + 1; a < 8; a++){
			    			//�΂��u���ĂȂ��ꏊ����������
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			//�����̐΂���������
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}
			    	}

			    	//�E�΂߉������ɑ���̐΂���������
			    	if(board.getBanmen()[i + 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
			    		//�E�������֐i��ł���
						for(int a = i + 1; (a < 8) && (b < 8); a++, b++){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//�E�����ɑ���̐΂���������
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		//�E�����֐i��ł���
			    		for(int a = j + 1; a < 8; a++){
			    			//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    //�z��[i][0]�̎��A�������������Ȃ�
			    }else if(board.getBanmen()[i][j] == 0 && j == 0){

			    	//������ɑ���̐΂���������
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		//������֐i��ł���
			    		for(int a = i - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			//�����̐΂���������
			    			}else if(board.getBanmen()[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//�E�΂ߏ�����ɑ���̐΂���������
			    	if(board.getBanmen()[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
			    		//�E������֐i��ł���
						for(int a = i - 1; (a >= 0) && (b < 8); a--, b++){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//�E�����ɑ���̐΂���������
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		//�E�����֐i��ł���
			    		for(int a = j + 1; a < 8; a++){
			    			//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//�E�΂߉������ɑ���̐΂���������
			    	if(board.getBanmen()[i + 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
			    		//�E�������֐i��ł���
						for(int a = i + 1; (a < 8) && (b < 8); a++, b++){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//�������ɑ���̐΂���������
			    	if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
			    		//�������֐i��ł���
			    		for(int a = i + 1; a < 8; a++){
			    			//�΂��u���ĂȂ��ꏊ����������
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			//�����̐΂���������
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}
			    	}

			    //�z��[i][7]�̎��A�E�����������Ȃ�
			    }else if(board.getBanmen()[i][j] == 0 && j == 7){

			    	//������ɑ���̐΂���������
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		//������֐i��ł���
			    		for(int a = i - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			//�����̐΂���������
			    			}else if(board.getBanmen()[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//���΂ߏ�����ɑ���̐΂���������
			    	if(board.getBanmen()[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
			    		//��������֐i��ł���
						for(int a = i - 1; (a >= 0) && (b >= 0); a--, b--){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//�������ɑ���̐΂���������
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		//�������֐i��ł���
			    		for(int a = j - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//���΂߉������ɑ���̐΂���������
			    	if(board.getBanmen()[i + 1][j - 1] == (turn ^ bit)){
			    		int b = j - 1;
			    		//���������֐i��ł���
						for(int a = i + 1; (a < 8) && (b >= 0); a++, b--){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//�������ɑ���̐΂���������
			    	if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
			    		//�������֐i��ł���
			    		for(int a = i + 1; a < 8; a++){
			    			//�΂��u���ĂȂ��ꏊ����������
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			//�����̐΂���������
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}
			    	}

			    //�z��[7][j]�̎��A�������������Ȃ�
			    }else if(board.getBanmen()[i][j] == 0 && i == 7){

			    	//�������ɑ���̐΂���������
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		//�������֐i��ł���
			    		for(int a = j - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//���΂ߏ�����ɑ���̐΂���������
			    	if(board.getBanmen()[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
			    		//��������֐i��ł���
						for(int a = i - 1; (a > 0) && (b >= 0); a--, b--){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//������ɑ���̐΂���������
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		//������֐i��ł���
			    		for(int a = i - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			//�����̐΂���������
			    			}else if(board.getBanmen()[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//�E�΂ߏ�����ɑ���̐΂���������
			    	if(board.getBanmen()[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
			    		//�E������֐i��ł���
						for(int a = i - 1; (a >= 0) && (b < 8); a--, b++){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//�E�����ɑ���̐΂���������
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		//�E�����֐i��ł���
			    		for(int a = j + 1; a < 8; a++){
			    			//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    //���̍��W�͎�������ׂĒ��ׂ�
			    }else if(board.getBanmen()[i][j] == 0){

			    	//������ɑ���̐΂���������
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		//������֐i��ł���
			    		for(int a = i - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			//�����̐΂���������
			    			}else if(board.getBanmen()[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//�E�΂ߏ�����ɑ���̐΂���������
			    	if(board.getBanmen()[i - 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
			    		//�E������֐i��ł���
						for(int a = i - 1; (a >= 0) && (b < 8); a--, b++){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//�E�����ɑ���̐΂���������
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		//�E�����֐i��ł���
			    		for(int a = j + 1; a < 8; a++){
			    			//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//�E�΂߉������ɑ���̐΂���������
			    	if(board.getBanmen()[i + 1][j + 1] == (turn ^ bit)){
			    		int b = j + 1;
			    		//�E�������֐i��ł���
						for(int a = i + 1; (a < 8) && (b < 8); a++, b++){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//�������ɑ���̐΂���������
			    	if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
			    		//�������֐i��ł���
			    		for(int a = i + 1; a < 8; a++){
			    			//�΂��u���ĂȂ��ꏊ����������
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			//�����̐΂���������
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}
			    	}

			    	//���΂߉������ɑ���̐΂���������
			    	if(board.getBanmen()[i + 1][j - 1] == (turn ^ bit)){
			    		int b = j - 1;
			    		//���������֐i��ł���
						for(int a = i + 1; (a < 8) && (b >= 0); a++, b--){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    	//�������ɑ���̐΂���������
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		//�������֐i��ł���
			    		for(int a = j - 1; a >= 0; a--){
			    			//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[i][a] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//���΂ߏ�����ɑ���̐΂���������
			    	if(board.getBanmen()[i-1][j-1] == (turn ^ bit)){
			    		int b = j - 1;
			    		//��������֐i��ł���
						for(int a = i - 1; (a >= 0) && (b >= 0); a--, b--){
							//�΂��u���ĂȂ��ꏊ����������
							if(board.getBanmen()[a][b] == 0){
								break;
							//�����̐΂���������
							}else if(board.getBanmen()[a][b] == turn){
								return 1;
							}
						}
			    	}

			    }
		return 0;
	}


}
