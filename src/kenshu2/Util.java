package kenshu2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Util {

	/**
	 * �΂�u���ꏊ����͂��郁�\�b�h
	 * @return ���͂��ꂽ�l
	 */
	public static int input(){
		int x;
		System.out.print("�΂�u���ꏊ���w�肵�Ă�������");
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try{
                String buf = br.readLine();
                x = Integer.parseInt(buf);
        }catch(Exception e){
                x = 0;
        }
        return x;
	}


	/**
	 * �I�Z���𑱂��邩�A��߂邩�I�ԃ��\�b�h
	 * @return �R���\�[��������͂��ꂽ�l
	 */
	public static int retry(){
		int x;
		System.out.print("�Q�[���𑱂��܂����H�͂�=1�A������=0");
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try{
                String buf = br.readLine();
                x = Integer.parseInt(buf);
        }catch(Exception e){
                x = 0;
        }
        return x;
	}

}
