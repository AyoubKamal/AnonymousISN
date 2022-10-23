#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <unistd.h>
#include <pthread.h>

// Mettez en commentaire cette ligne si vous �tes sous Windows :
//#include <termios.h>
#include <windows.h> si vous �tes sous Windows
#include <conio.h> si vous �tes sous Windows

#define TRUE 256
#define FALSE 0

// Mettez en commentaire cette ligne si vous �tes sous Windows :
//static struct termios origtc, newtc;

enum direction {HAUT,BAS,GAUCHE,DROITE};
typedef struct{
    int **plateau;
    int lignes; // Nombre de lignes de plateau
    int colonnes; // Nobre de colonnes de plateau
    int posl; // ligne actuelle de l'�toile
    int pl; // ligne pr�c�dente de l'�toile
    int posc; // colonne actuelle de l'�toile
    int pc; // colonne pr�c�dente de l'�toile
    enum direction direction; // direction actuelle de l'�toile
} star_t;

void * lire_clavier(void *arg);
void lire_plateau(char *fichier,star_t *star);
void clearScreen();
void afficher_plateau(star_t star);
void placer_star(star_t *star);
