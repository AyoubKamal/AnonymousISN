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
	        
	        
	        
	       // - setSize(): redimensionne le composant de longueur l et hauteur h. Les valeurs de la hauteur et de largeur sont automatiquement agrandies si elle sont inférieurs à la taille minimale spécifiée par la méthode setMinimumSize.

	       // - setMinimumSize(): définit la taille minimale que la fenêtre prenne en charge. Si la taille actuelle de la fenêtre est inférieure à la taille minimale, la dimension de la fenêtre sera automatiquement élargie et adaptée à la nouvelle dimension minimale. Si les méthodes setSize() et setBounds() sont appelées par la suite par des valeurs inférieures à celle spécifiées par setMinimumSize(), la fenêtre va être automatiquement agrandie et prend les valeurs minimales.

	       // - setMaximumSize(): spécifie la taille maximale de la fenêtre avec des valeurs constante comme dans setMinimumSize().

	       // - setPreferredSize(): spécifie la taille préférée d'un composant. C'est la taille optimale du composant et le bon choix quand vous avez un layout manager.


	    }
	}

