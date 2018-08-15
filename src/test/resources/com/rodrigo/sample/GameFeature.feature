Feature: Game Features

  Scenario: Create games
	When Create game
		| title            		| minimalAge	| developer     |
		| Fifa 19				| 12			| EA			|
		| Crash Bandicot		| 6				| Konami		|
		| Mario Kart			| 8				| Nintendo		|
	Then Game should exists
		| title            		| minimalAge	| developer     |
		| Fifa 19				| 12			| EA			|
