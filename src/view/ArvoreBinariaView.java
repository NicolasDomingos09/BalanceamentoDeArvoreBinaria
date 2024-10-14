package view;
import controller.ArvoreBinariaController;

public class ArvoreBinariaView {
	public static void main(String[] args){
		ArvoreBinariaController obj = new ArvoreBinariaController();
		
		try{
			obj.teste();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}