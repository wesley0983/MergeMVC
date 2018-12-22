package home_class;

import java.util.Scanner;

public class Auto_Product {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str = "Insert into PRODUCT (PRO_NO,PRO_CLASSID,PRO_NAME,PRO_PIC,PRO_PIC_EXT,PRO_FORMAT,PRO_BONUS,PRO_STOCK,PRO_SAFESTOCK,PRO_DETAILS,PRO_SHELVE,PRO_ALL_ASSESS,PRO_ALL_ASSESSMAN) values ('P'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 3, '0'),?,?,?,?,?,?,?,?,?,?,?,?);";

	}

}
