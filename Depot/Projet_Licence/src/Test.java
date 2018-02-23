public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		InterfaceConnexion  ic = new InterfaceConnexion();
		ic.setTitle("Logiciel de Formation");
		ic.setVisible(true);
		
		// TODO Instancier une JFrame
		ajouter fenetreAjout = new ajouter();
		// TODO Afficher la JFrame
		fenetreAjout.setVisible(true);
	}

}
