import javax.swing.JFrame;

public class test {

	    public static void main(String[] args) {
	        new test();
	    }

	    public test(){
	        JFrame f = new JFrame();
	        f.setTitle("Maze Game"); //titre
	        //f.add(new board());
	        f.setLocationRelativeTo(null); // centrer la fenetre
	        f.setVisible(true);  //visible
	        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE); //boutton pour quitter
	        f.setResizable(false);    //pour ne pas redefinir la dimension
	        
	        
	        
	       // - setSize(): redimensionne le composant de longueur l et hauteur h. Les valeurs de la hauteur et de largeur sont automatiquement agrandies si elle sont inf�rieurs � la taille minimale sp�cifi�e par la m�thode setMinimumSize.

	       // - setMinimumSize(): d�finit la taille minimale que la fen�tre prenne en charge. Si la taille actuelle de la fen�tre est inf�rieure � la taille minimale, la dimension de la fen�tre sera automatiquement �largie et adapt�e � la nouvelle dimension minimale. Si les m�thodes setSize() et setBounds() sont appel�es par la suite par des valeurs inf�rieures � celle sp�cifi�es par setMinimumSize(), la fen�tre va �tre automatiquement agrandie et prend les valeurs minimales.

	       // - setMaximumSize(): sp�cifie la taille maximale de la fen�tre avec des valeurs constante comme dans setMinimumSize().

	       // - setPreferredSize(): sp�cifie la taille pr�f�r�e d'un composant. C'est la taille optimale du composant et le bon choix quand vous avez un layout manager.


	    }
	}

