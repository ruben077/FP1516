#include <string.h>
#include <stdio.h>
#include "cadena.h"
#include "logico.h"

void testQuitaSaltoLinea(Cadena);
void mostrarCaracteresCadena(const Cadena);

int main (void){
	Cadena c1 ="Hola, Mariola!!", c2 = "Adi�s, Mariola!!\n";
	printf("PRUEBA 1: Cadena SIN salto de l�nea\n");
	testQuitaSaltoLinea(c1);

	printf("PRUEBA 2: Cadena CON salto de l�nea\n");
	testQuitaSaltoLinea(c2);
	return 0;
}

void testQuitaSaltoLinea(Cadena c){
	printf("Cadena ANTES de quitar salto de l�nea: ");
	mostrarCaracteresCadena(c);

	quitaSaltoDeLinea(c);

	printf("Cadena DESPUES de quitar salto de l�nea: ");
	mostrarCaracteresCadena(c);
}

void mostrarCaracteresCadena(const Cadena c){
	int i;
	int lon = strlen(c);
	printf("( %d )", lon);
	for (i=0; i<lon; i++){
		printf("-%c-", c[i]);
	}
	printf("\n");
}
