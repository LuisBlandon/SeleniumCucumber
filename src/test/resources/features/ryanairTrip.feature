@Test 
Feature: Search for trip

Scenario Outline: User can search trip
	Given User have the following information to create a new trip
		| Departure   | Destination   |
		| <Departure> | <Destination> |
	When User selects the trip
	And  User confirms trip
	Then User go to the search results page
	Examples:
		| Departure | Destination |
		| Portugal  | France      |