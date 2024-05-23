package com.ega.books.domain;

import lombok.Getter;

@Getter
public enum Genre {

	FICCION("Ficcion", 
			"Historias creadas a partir de la imaginacion del autor. Pueden ser realistas o totalmente fantasticas",
			"1984, Cien años de soledad, To Kill a Mockingbird"),
	CIENCIA_FICCION("Ciencia Ficcion",
			"Historias que exploran escenarios futuros o alternativos, a menudo centradas en avances tecnológicos o científicos",
			"Dune, Neuromancer, The Martian"),
	FANTASIA("Fantasia", 
			"Historias que incluyen elementos mágicos o sobrenaturales, a menudo ambientadas en mundos imaginarios",
			"The Lord of the Rings, Harry Potter, A Song of Iec and Fire"),
	MISTERIO("Misterio",
			"Historias centradas en la resolución de un crimen o un enigma, a menudo con un detective como protagonista",
			"The Da Vinci Code, Murder on the Orient Express, Gone Girl"),
	THRILLER("Thriller",
			"Historias llenas de suspenso y tensión, a menudo con temas de peligro y persecución",
			"The Girl with the Dragon Tattoo, The Silence of the Lamb, Gone Girl"),
	ROMANCE("Romance",
			"Historias centradas en relaciones amorosas, con un enfoque en los sentimientos y emociones de los personajes",
			"Pride and Prejudice, Outlander, The Notebook"),
	HISTORICA("Historica",
			"Historias ambientadas en el pasado, a menudo bien investigadas para reflejar con precisión la época y los eventos históricos",
			"The Book Thief, All the Light We Cannot See, War and Peace"),
	NO_FICCION("No Ficcion",
			"Libros basados en hechos reales y datos verificados, que pueden incluir biografías, ensayos, y textos académicos",
			"Sapiens, The Diary of a Young Girl, Long Walf to Freedom"),
	HORROR("Horror",
			"Historias diseñadas para asustar y crear una sensación de terror y suspenso",
			"The Shinning, Dracula, Frankenstein"),
	POESIA("Poesia",
			"Obras literarias en verso, que utilizan el ritmo y la métrica para expresar emociones y pensamientos",
			"The Waste Land, Leaves of Grass, Cien Sonetos de Amor"),
	DRAMA("Drama",
			"Obras que presentan una narrativa seria y a menudo trágica, centrándose en el conflicto y la emoción humana",
			"Hamlet, Death of a Salesman, A Streetcar Named Desire");
	
	private String name;
	private String description;
	private String examples;
	
	Genre(String name, String description, String examples) {
		this.name = name;
		this.description = description;
		this.examples = examples;
	}
}
