package application;

//Klasa zawierająca metody do zakodowania i zdekodowania tekstu
public class RLE {
	
	//Metoda zwracająca zdekodowany tekst
	public String encode(String input) {
		String output = "";
		int count = 1;
		if (input.length() == 0)
			return "";
		char addedChar = input.charAt(0);
		for (int i = 1; i < input.length(); i++) {
			if (input.charAt(i) == addedChar)
				count += 1;
			else {
				output += "" + addedChar + count + ",";
				count = 1;
				addedChar = input.charAt(i);
			}
		}
		output +="" + addedChar + count;
		return output;
	}
	
	//Metoda zwracająca zakodowany tekst
	public String decode(String input) {
		try {
			//Sprawdzenie, czy tekst wejściowy pasuje do wzorca tekstu zakodowanego
			if(!textMatches(input))
				throw new InvalidFormatException();
			String output = "";
			//Pętla będzie się wykonywać, jeżeli są jakiekolwiek przecinki
			while(input.indexOf(',') != -1) {
				//Jeżeli pozostała część tekstu wejściowego zaczyna się od przecinka, wykonaj tą sekwencję
				if(input.indexOf(',') == 0) {
					input = input.substring(1);
					output += charNTimes(',', Integer.parseInt(input.substring(0, input.indexOf(','))));	
					input = input.substring(input.indexOf(',')+1, input.length());
				}
				//Dodaj do tekstu wyjściowego pierwszy znak tektu wejściowego n razy. Później usuń pierwszy człon tekstu wejściowego
				else {	
					output += charNTimes(input.charAt(0), Integer.parseInt(input.substring(1, input.indexOf(','))));	
					input = input.substring(input.indexOf(',')+1, input.length());
				}
			}
			//Dodaj do tekstu wyjściowego ostatni człon, potem zwróć całość
			output += charNTimes(input.charAt(0), Integer.parseInt(input.substring(1)));
			return output;
		}
		catch(InvalidFormatException e) {
			return "Nieprawidłowy format";
		}		
	}

	//Metoda, która zwróci zmienną typu String zawierającą n znaków typu inputChar
	private String charNTimes(char inputChar, int n) {
		String output = "";
		for (int i = 0; i < n; i++) {
			output += inputChar;
		}
		return output;
	}
	
	//Metoda sprawdzająca, czy tekst pasuje do wzorca tekstu wejściowego
	private boolean textMatches(String text) {
		return text.matches("(.[0-9]+,)+.[0-9]+");
	}
}
