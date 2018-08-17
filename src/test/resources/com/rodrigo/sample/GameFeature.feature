Feature: Game Features

  Scenario: Create games
	Given I bought these games
		| title            		| minimalAge	| developer     |
		| Fifa 19				| 12			| EA			|
		| Crash Bandicot		| 6				| Konami		|
		| Mario Kart			| 8				| Nintendo		|
		| Resident Evil			| 18			| Bioharzard	|
	When I check the game list
	Then I should have 4 games
	
  Scenario: Games still available
	When I sold "Fifa 19"
	Then Game "Mario Kart" should be available
	
  Scenario: Games still available
	When I sold "Mario Kart" 
	And I check the game list
	Then I should have 2 games
