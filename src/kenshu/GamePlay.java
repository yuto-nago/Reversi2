package kenshu;

import kenshu2.Util;

public class GamePlay {

	//Board�N���X�̎Q��
	private Board board;

	//Stone�N���X�̎Q��
	private Stone stone;

	//�ǂ���̃^�[�������ʂ���B����2�A����1
	private int turn;

	//���͂��ꂽ�l��ێ�����ϐ�
	private int place;

	//�r�b�g���Z�Ɏg�p����ϐ�
	private int bit = 3;

	//�Ֆʂ�x���W
	private int x;

	//�Ֆʂ�y���W
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
						System.out.println("���̃^�[��");
						place = Util.input();

						y = place / 10;

					    x = place % 10;

					    while(check(y , x) == 0){

					    	System.out.println("���߂�΂�����܂���");

					    	board.display();

					    	System.out.println("���̃^�[��");

					    	place = Util.input();

					    	y = place / 10;

						    x = place % 10;
					    }
					    flip(y, x);

					    board.display();

					    turn = 1;

					}else{
						System.out.println("���̓p�X�ł�");

						turn = 1;

						break;
					}
				}else if(turn == 1){
					if(pass() == 1){
						System.out.println("���̃^�[��");

						place = Util.input();

						y = place / 10;

					    x = place % 10;

					    while(check(y , x) == 0){

					    	System.out.println("���߂�΂�����܂���");

					    	board.display();

					    	System.out.println("���̃^�[��");

					    	place = Util.input();

					    	y = place / 10;

						    x = place % 10;
					    }
					    flip(y, x);

					    board.display();

					    turn = 2;
					}else{
						System.out.println("���̓p�X�ł�");

						turn = 2;

						break;
					}
				}

			}

			judge();


		}while(Util.retry() != 0);






	}


	/**
	 * �Q�[�����I�����ǂ������ׂ郁�\�b�h
	 * @return �I���Ȃ�0�A�I���łȂ����1��Ԃ��B
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
	 * ���s��\�����郁�\�b�h
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
			System.out.println(white + "���Ŕ��̏���");
		}else if(black == white){
			System.out.println("��������");
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
					//�E�����ɑ���̐΂��������ۂɂ��̐�Ɏ����̐΂�����΋��ނ��Ƃ��ł���̂Ńp�X�ł͂Ȃ�
					if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
						for(int a = j + 1; a < 8; a++){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}

					}

					//�������ւ̌���
					if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
						for(int a = i + 1; a < 8; a++){
							if(board.getBanmen()[a][j] == 0){
								break;
							}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}

					}

					//�E�΂߉��ւ̌���
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

				//�z��[0][7]�̎��A���A���A���΂߉��ɂ����������Ȃ�
				}else if(board.getBanmen()[i][j] == 0 && i == 0 && j == 7){

					//�������ւ̌���
					if(board.getBanmen()[i][j-1] == (turn ^ bit)){
						for(int a = j - 1; a >= 0; a--){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}

				    }
					//�������ւ̌���
					if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
				    	for(int a = i + 1; a < 8; a++){
				    		if(board.getBanmen()[a][j] == 0){
				    			break;
				    		}else if(board.getBanmen()[a][j] == 1){
								return 1;
							}
						}

				    }
					//���΂߉��ւ̌���
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

				//�z��[7][0]�̎��A��A�E�A�E�΂ߏ�ɂ����������Ȃ�
			    }else if(board.getBanmen()[i][j] == 0 && i == 7 && j == 0){

			    	//������ւ̌���
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}

			    	}

			    	//�E�����ւ̌���
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}

			    	}

			    	//�E�΂ߏ�����ւ̌���
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

			    //�z��[7][7]�̎��A���A��A���΂ߏ�ɂ����������Ȃ�
			    }else if(board.getBanmen()[i][j] == 0 && i == 7 && j == 7){

			    	//�������ւ̌���
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a >= 0; a--){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}
			    	//������ւ̌���
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}
			    	}
			    	//���΂ߏ�����ւ̌���
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

			    //�z��[0][j]�̎��A�㑤���������Ȃ�
			    }else if(board.getBanmen()[i][j] == 0 && i == 0){

			    	//�������ւ̌���
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a >= 0; a--){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}
			    	//���΂߉������ւ̌���
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
			    	//�������ւ̌���
			    	if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
			    		for(int a = i + 1; a < 8; a++){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}
			    	}

			    	//�E�΂߉������ւ̌���
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

			    	//�E�����ւ̌���
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    //�z��[i][0]�̎��A�������������Ȃ�
			    }else if(board.getBanmen()[i][j] == 0 && j == 0){

			    	//������ւ̌���
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//�E�΂ߏ�����ւ̌���
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

			    	//�E�����ւ̌���
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//�E�΂߉������ւ̌���
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

			    	//�������ւ̌���
			    	if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
			    		for(int a = i + 1; a < 8; a++){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}
			    	}

			    //�z��[i][7]�̎��A�E�����������Ȃ�
			    }else if(board.getBanmen()[i][j] == 0 && j == 7){

			    	//������ւ̌���
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//���΂ߏ�����ւ̌���
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

			    	//�������ւ̌���
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a >= 0; a--){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//���΂߉������ւ̌���
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

			    	//�������ւ̌���
			    	if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
			    		for(int a = i + 1; a < 8; a++){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}
			    	}

			    //�z��[7][j]�̎��A�������������Ȃ�
			    }else if(board.getBanmen()[i][j] == 0 && i == 7){

			    	//�������ւ̌���
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a >= 0; a--){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//���΂ߏ�����ւ̌���
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

			    	//������ւ̌���
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//�E�΂ߏ�����ւ̌���
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

			    	//�E�����ւ̌���
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    //���̍��W�͎�������ׂĒ��ׂ�
			    }else if(board.getBanmen()[i][j] == 0){

			    	//������ւ̌���
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//�E�΂ߏ�����ւ̌���
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

			    	//�E�����ւ̌���
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//�E�΂߉������ւ̌���
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

			    	//�������ւ̌���
			    	if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
			    		for(int a = i + 1; a < 8; a++){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}
			    	}

			    	//���΂߉������ւ̌���
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

			    	//�������ւ̌���
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a >= 0; a--){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//���΂ߏ�����ւ̌���
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
	 * �΂�u���邩�ǂ������ׂ郁�\�b�h
	 * @param i�@�R���\�[��������͂��ꂽ�\�̈ʂ̐���
	 * @param j�@�R���\�[��������͂��ꂽ��̈ʂ̐���
	 * @return�@�u���Ȃ����0�A�u����Ȃ�1��Ԃ��B
	 */
	public int check(int i, int j){
				//�z��[0][0]�̎��A�E�A���A�E�΂߉��ɂ����������Ȃ�
				if(board.getBanmen()[i][j] == 0 && i == 0 && j == 0){
					//�E�����ɑ���̐΂��������ۂɂ��̐�Ɏ����̐΂�����΋��ނ��Ƃ��ł���̂Ńp�X�ł͂Ȃ�
					if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
						for(int a = j + 1; a < 8; a++){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}

					}

					//�������ւ̌���
					if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
						for(int a = i + 1; a < 8; a++){
							if(board.getBanmen()[a][j] == 0){
								break;
							}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}

					}

					//�E�΂߉��ւ̌���
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

				//�z��[0][7]�̎��A���A���A���΂߉��ɂ����������Ȃ�
				}else if(board.getBanmen()[i][j] == 0 && i == 0 && j == 7){

					//�������ւ̌���
					if(board.getBanmen()[i][j-1] == (turn ^ bit)){
						for(int a = j - 1; a >= 0; a--){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}

				    }
					//�������ւ̌���
					if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
				    	for(int a = i + 1; a < 8; a++){
				    		if(board.getBanmen()[a][j] == 0){
				    			break;
				    		}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}

				    }
					//���΂߉��ւ̌���
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

				//�z��[7][0]�̎��A��A�E�A�E�΂ߏ�ɂ����������Ȃ�
			    }else if(board.getBanmen()[i][j] == 0 && i == 7 && j == 0){

			    	//������ւ̌���
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}

			    	}

			    	//�E�����ւ̌���
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}

			    	}

			    	//�E�΂ߏ�����ւ̌���
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

			    //�z��[7][7]�̎��A���A��A���΂ߏ�ɂ����������Ȃ�
			    }else if(board.getBanmen()[i][j] == 0 && i == 7 && j == 7){

			    	//�������ւ̌���
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a >= 0; a--){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}
			    	//������ւ̌���
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}
			    	}
			    	//���΂ߏ�����ւ̌���
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

			    //�z��[0][j]�̎��A�㑤���������Ȃ�
			    }else if(board.getBanmen()[i][j] == 0 && i == 0){

			    	//�������ւ̌���
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a >= 0; a--){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}
			    	//���΂߉������ւ̌���
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
			    	//�������ւ̌���
			    	if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
			    		for(int a = i + 1; a < 8; a++){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}
			    	}

			    	//�E�΂߉������ւ̌���
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

			    	//�E�����ւ̌���
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    //�z��[i][0]�̎��A�������������Ȃ�
			    }else if(board.getBanmen()[i][j] == 0 && j == 0){

			    	//������ւ̌���
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//�E�΂ߏ�����ւ̌���
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

			    	//�E�����ւ̌���
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//�E�΂߉������ւ̌���
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

			    	//�������ւ̌���
			    	if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
			    		for(int a = i + 1; a < 8; a++){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}
			    	}

			    //�z��[i][7]�̎��A�E�����������Ȃ�
			    }else if(board.getBanmen()[i][j] == 0 && j == 7){

			    	//������ւ̌���
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//���΂ߏ�����ւ̌���
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

			    	//�������ւ̌���
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a >= 0; a--){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//���΂߉������ւ̌���
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

			    	//�������ւ̌���
			    	if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
			    		for(int a = i + 1; a < 8; a++){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}
			    	}

			    //�z��[7][j]�̎��A�������������Ȃ�
			    }else if(board.getBanmen()[i][j] == 0 && i == 7){

			    	//�������ւ̌���
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a >= 0; a--){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//���΂ߏ�����ւ̌���
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

			    	//������ւ̌���
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//�E�΂ߏ�����ւ̌���
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

			    	//�E�����ւ̌���
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    //���̍��W�͎�������ׂĒ��ׂ�
			    }else if(board.getBanmen()[i][j] == 0){

			    	//������ւ̌���
			    	if(board.getBanmen()[i - 1][j] == (turn ^ bit)){
			    		for(int a = i - 1; a >= 0; a--){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
			    				return 1;
			    			}
			    		}
			    	}

			    	//�E�΂ߏ�����ւ̌���
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

			    	//�E�����ւ̌���
			    	if(board.getBanmen()[i][j + 1] == (turn ^ bit)){
			    		for(int a = j + 1; a < 8; a++){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//�E�΂߉������ւ̌���
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

			    	//�������ւ̌���
			    	if(board.getBanmen()[i + 1][j] == (turn ^ bit)){
			    		for(int a = i + 1; a < 8; a++){
			    			if(board.getBanmen()[a][j] == 0){
			    				break;
			    			}else if(board.getBanmen()[a][j] == turn){
								return 1;
							}
						}
			    	}

			    	//���΂߉������ւ̌���
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

			    	//�������ւ̌���
			    	if(board.getBanmen()[i][j-1] == (turn ^ bit)){
			    		for(int a = j - 1; a >= 0; a--){
							if(board.getBanmen()[i][a] == 0){
								break;
							}else if(board.getBanmen()[i][a] == turn){
								return 1;
							}
						}
			    	}

			    	//���΂ߏ�����ւ̌���
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
	 * ����̐΂𗠕Ԃ����\�b�h
	 *
	 * @param i �R���\�[��������͂��ꂽ�\�̈ʂ̐���
	 * @param j �R���\�[��������͂��ꂽ��̈ʂ̐���
	 */
	public void flip(int i, int j){

		//�z��[0][0]�̎��A�E�A���A�E�΂߉��ɂ����������Ȃ�
		if(i == 0 && j == 0){

			//�E�����ւ̏���
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

			//�E�΂߉������ւ̏���
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

			//�������ւ̏���
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

		//�z��[0][7]�̎��A���A���A���΂߉��ɂ����������Ȃ�
		}else if(i == 0 && j == 7){

			//�������ւ̏���
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

			//���΂߉������ւ̏���
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

			//�������ւ̏���
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

		//�z��[7][0]�̎��A��A�E�A�E�΂ߏ�ɂ����������Ȃ�
		}else if(i == 7 && j == 0){

			//������ւ̏���
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

			//�E�΂ߏ�����ւ̏���
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

			//�E�����ւ̏���
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

		//�z��[7][7]�̎��A���A��A���΂ߏ�ɂ���
		}else if(i == 7 && j == 7){

			//������ւ̏���
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

			//���΂ߏ�����ւ̏���
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

			//�������ւ̏���
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

		//�z��[0][j]�̎��A�㑤���������Ȃ�
		}else if(i == 0){

			//�������ւ̏���
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

			//���΂߉������ւ̏���
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

			//�������ւ̏���
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

			//�E�΂߉������ւ̏���
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

			//�E�����ւ̏���
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

		//�z��[i][7]�̎��A�E�����������Ȃ�
		}else if(j == 7){

			//������ւ̏���
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

			//���΂ߏ�����ւ̏���
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

			//�������ւ̏���
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

			//���΂߉������ւ̏���
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

			//�������ւ̏���
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


		//�z��[i][0]�̎��A�������������Ȃ�
		}else if(j == 0){

			//������ւ̏���
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

			//�E�΂ߏ�����ւ̏���
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

			//�E�����ւ̏���
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

			//�E�΂߉������ւ̏���
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

			//�������ւ̏���
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


		//�z��[7][j]�̎��A�������������Ȃ�
		}else if(i == 7){

			//�������ւ̏���
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

			//���΂ߏ�����ւ̏���
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

			//������ւ̏���
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

			//�E�΂ߏ�����ւ̏���
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

			//�E�����ւ̏���
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

		//���̍��W�͎�������ׂĒ��ׂ�
		}else{

			//������ւ̏���
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

			//�E�����ւ̏���
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

			//�������ւ̏���
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

			//�������ւ̏���
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

			//�E�΂ߏ�����ւ̏���
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

			//�E�΂߉������ւ̏���
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

			//���΂߉������ւ̏���
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

			//���΂ߏ�����ւ̏���
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
