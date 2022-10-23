#include "jeu.h"
pthread_mutex_t dmutex = PTHREAD_MUTEX_INITIALIZER;
// Version de lire_clavier sous Linux
//https://stackoverflow.com/questions/41928673/implementing-a-keypress-event-in-c-with-multiple-threads
/*void * lire_clavier(void *arg) {
    star_t * star = (star_t *)arg;
    char c;
    tcgetattr(0, &origtc);
    newtc = origtc;
    newtc.c_lflag &= ~ICANON;
    newtc.c_lflag &= ~ECHO;
    while(1) {
        tcsetattr(0, TCSANOW, &newtc);
        c = getchar();
        tcsetattr(0, TCSANOW, &origtc);
        pthread_mutex_lock(&dmutex);
        if (c=='z') {
            star->direction = HAUT;
        }else if (c == 's') {
                star->direction = BAS;
        }else if (c == 'd') {
                star->direction = DROITE;
        }else if (c == 'q') {
                star->direction = GAUCHE;
        }
        pthread_mutex_unlock(&dmutex);
        sleep(1);
    }
}*/
/*
//Sous windows utiliser cette vevrsion de lire_clavier. Mettez la fonction lire_clavier
// ci-dessus en commentaire
//Pour windows https://docs.microsoft.com/en-us/cpp/c-runtime-library/reference/kbhit?view=vs-2019*/
 void *lire_clavier(void *arg){
    star_t * star = (star_t *)arg;
    char c;
    while(1) {
        if (_kbhit()) {
            c = _getch();
            pthread_mutex_lock(&dmutex);
            if (c=='z') {
                star->direction = HAUT;
            }else if (c == 's') {
                star->direction = BAS;
            }else if (c == 'd') {
                star->direction = DROITE;
            }else if (c == 'q') {
                star->direction = GAUCHE;
            }
            pthread_mutex_unlock(&dmutex);
        }
        Sleep(1000);
    }
 }

void calculer_direction(star_t *star) {
    if (star->direction==HAUT){
        if (star->posl+1<star->lignes-1 && star->plateau[star->posl+1][star->posc]==0){
                star->direction=HAUT;
                	star->pc=star->posc;
                    star->pl=star->posl;
                star->pl=star->posl++;
                }
        else if (star->posc+1<star->colonnes-1 &&star->plateau[star->posl][star->posc+1]==0){
                	star->pc=star->posc;
                star->pl=star->posl;
                star->direction=DROITE;
                star->pc=star->posc++;
                }
        else if(star->posc-1>0 &&star->plateau[star->posl][star->posc-1]==0){
               star->direction=DROITE;
               	star->pc=star->posc;
	star->pl=star->posl;
                star->pc=star->posc--;}
    }

        else if (star->direction==BAS){
            if (star->posl-1>0 && star->plateau[star->posl-1][star->posc]==0){
                star->direction=BAS;
                	star->pc=star->posc;
	star->pl=star->posl;
                star->pl=star->posl--;
            }
            else if (star->posc+1<star->colonnes-1 &&star->plateau[star->posl][star->posc+1]==0){
                    star->direction=DROITE;
                    	star->pc=star->posc;
	star->pl=star->posl;
                    star->pc=star->posc++;
                    }
            else if(star->posc-1>0 &&star->plateau[star->posl][star->posc-1]==0){
                    star->direction=GAUCHE;
                    	star->pc=star->posc;
	star->pl=star->posl;
                    star->pc=star->posc--;}
        }



        else if (star->direction==GAUCHE){

        if (star->posc-1>0 && star->plateau[star->posl][star->posc-1]==0){
                star->direction=GAUCHE;
                	star->pc=star->posc;
	star->pl=star->posl;
                star->pc=star->posc--;}
        else if (star->posl+1<star->colonnes-1 &&star->plateau[star->posl+1][star->posc]==0){
                star->direction=HAUT;
                	star->pc=star->posc;
	star->pl=star->posl;
                star->pl=star->posl++;
                }
        else if(star->posl-1>0 &&star->plateau[star->posl-1][star->posc]==0){
                star->direction=BAS;
                	star->pc=star->posc;
	star->pl=star->posl;
                star->pl=star->posl--;}

        }

        else if (star->direction==DROITE){

            if (star->posc+1>star->lignes-1 && star->plateau[star->posl][star->posc+1]==0){
                star->direction=DROITE;
                	star->pc=star->posc;
	star->pl=star->posl;
                star->pc=star->posc++;}
        else if (star->posl+1<star->lignes-1 &&star->plateau[star->posl+1][star->posc]==0){
                star->direction=HAUT;
                	star->pc=star->posc;
	star->pl=star->posl;
                star->pl=star->posl++;
                }
       else if(star->posl-1>0 &&star->plateau[star->posl-1][star->posc]==0){
                star->direction=BAS;
                	star->pc=star->posc;
                star->pl=star->posl;
               star->pl=star->posl--;}
        }

    }
    // A compléter pour calculer la direction suivante de l'étoile : star->direction
    // L'étoile doit prendre une nouvelle direction lorsqu'elle atteint un obstacle
    // Elle doit aussi éviter de refaire la direction précédente.



void *deplacer_star(void *arg) {
    star_t * star = (star_t *)arg;


    while(1) {
        pthread_mutex_lock(&dmutex);
        calculer_direction(star);
        // A compléter pour mettre à 0 la case précédente de l'étoile
        star->plateau[star->pl][star->pc]=0;
        // et 1 sa nouvelle position avant d'afficher le plateau
        star->plateau[star->posl][star->posc]=2;
        afficher_plateau(*star);
        pthread_mutex_unlock(&dmutex);
        // Sous windows utiliser //
        Sleep(1000);
        }
}

int main() {
    star_t star;
    star.direction = GAUCHE;
    pthread_t anim,clavier;
    srand(time(0));
	lire_plateau("plateau.txt",&star);
    placer_star(&star);
    afficher_plateau(star);
    pthread_create(&anim,NULL,deplacer_star,&star);
    pthread_create(&clavier,NULL,lire_clavier,&star);
    pthread_join(anim,NULL);
	return 0;
}
