package eg.edu.guc.yugioh.board.player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.CardDestruction;
import eg.edu.guc.yugioh.cards.spells.ChangeOfHeart;
import eg.edu.guc.yugioh.cards.spells.SpellCard;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import eg.edu.guc.yugioh.cards.spells.*;
import eg.edu.guc.yugioh.exceptions.EmptyFieldException;
import eg.edu.guc.yugioh.exceptions.MissingFieldException;
import eg.edu.guc.yugioh.exceptions.UnknownCardTypeException;
import eg.edu.guc.yugioh.exceptions.UnknownSpellCardException;

public class Deck {
	private static ArrayList <Card> monsters;
	private static ArrayList <Card> spells;
	private ArrayList <Card> deck;
	private static String monstersPath="Database-Monsters.csv";
	private static String spellsPath="Database-Spells.csv";
	private static int counter=0;
	
	public Deck() throws Exception{
		if(monsters==null && spells==null){
			counter=0;
			monsters=trials(monstersPath);
			counter=0;
		    spells= trials(spellsPath);
	}
	   buildDeck(monsters,spells);
	   }

	public static ArrayList<Card> getMonsters() {
		return monsters;
	}

	public static ArrayList<Card> getSpells() {
		return spells;
	}
	
	public ArrayList<Card> loadCardsFromFile(String path) throws IOException, UnknownSpellCardException, UnknownCardTypeException, MissingFieldException, EmptyFieldException{
		
		String [] result=null;
		int sourceLine1=0;
		ArrayList <Card> res=new ArrayList <Card> ();
		String currentLine = "";
		
		
		FileReader fileReader= new FileReader(path);
		BufferedReader br = new BufferedReader(fileReader);
		
	
			sourceLine1=0;
			while ((currentLine = br.readLine()) != null) {
				sourceLine1++;
			// Parsing the currentLine String
			result= currentLine.split(",");
			for(int i =0;i<result.length;i++){
				if(result[i].equals("")||result[i].equals(" "))
				{	br.close();
					throw new EmptyFieldException(path, sourceLine1, i+1);
			}}
			
			if(!result[0].equals("Monster") && !result[0].equals("Spell")){
				br.close();
			throw new UnknownCardTypeException(path,sourceLine1,result[0]);}
			
			if(result.length<3 || (result.length>3&&result.length<6)||(result[0].equals("Monster") &&result.length<6) || (result[0].equals("Spell") &&result.length<3)){
				br.close();
				throw new MissingFieldException(path,sourceLine1);
			}
			//if(result.length<3){
				//br.close();
				//throw new MissingFieldException(path,sourceLine1);
			//}
			
			
			
				if(result[0].equals("Monster"))
					res.add(new MonsterCard(result[1],result[2],Integer.parseInt(result[5]),Integer.parseInt(result[3]),Integer.parseInt(result[4])));
				
				else{
				
					switch(result[1]){
				case "Card Destruction":res.add(new CardDestruction(result[1],result[2]));break;
				case "Change Of Heart":res.add(new ChangeOfHeart(result[1],result[2]));break;
				case "Dark Hole":res.add(new DarkHole(result[1],result[2]));break;
				case "Graceful Dice":res.add(new GracefulDice(result[1],result[2]));break;
				case "Harpie's Feather Duster":res.add(new HarpieFeatherDuster(result[1],result[2])); break;
				case "Heavy Storm":res.add(new HeavyStorm(result[1],result[2]));break;
				case "Mage Power":res.add(new MagePower(result[1],result[2]));break;
				case "Monster Reborn":res.add(new MonsterReborn(result[1],result[2]));break;
				case "Pot of Greed":res.add(new PotOfGreed(result[1],result[2]));break;
				case "Raigeki":res.add(new Raigeki(result[1],result[2]));break;
				default:throw new UnknownSpellCardException(path,sourceLine1,result[1]);
				}
				
				}
			
			}
		
		br.close();
		return res;
		}
		
		
		//return res;
		
	
	private void buildDeck(ArrayList<Card> monsters, ArrayList<Card> spells) throws Exception{
	ArrayList <Card> res=new ArrayList<Card> ();
	//System.out.println(monsters.size());
	for(int i=0;i<15;i++){
		int rand=(int)(Math.random()*monsters.size());
		MonsterCard temp=null;
		try {
			temp = (MonsterCard) monsters.get(rand).clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.add(temp);
	}
	for(int j=0;j<5;j++){
		int rand2=(int)(Math.random()*spells.size());
		SpellCard temp=null;
		try {
			temp = (SpellCard) spells.get(rand2).clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.add(temp);
	}
	deck=res;
	shuffleDeck ();
	}
	
	private void shuffleDeck (){
		ArrayList <Card> tmp=new ArrayList <Card> ();
		int k=20;
		while(!deck.isEmpty()){
			int rand=(int)(Math.random()*k);
			tmp.add(deck.remove(rand));
			k--;
		}
		deck=tmp;
	}
	public ArrayList <Card> drawNCards(int n){
		ArrayList<Card> a=new ArrayList<Card>();
		for(int i=0;i<n;i++){
        a.add(deck.remove(0));	
		}
		return a;
	}
	public Card drawOneCard(){
		if(!deck.isEmpty())
		return deck.remove(0);
		return null;
	}
	public ArrayList <Card> getDeck() {
		return deck;
	}


	public void setDeck(ArrayList<Card> deck) {
		this.deck = deck;
	}

	public static String getSpellsPath() {
		return spellsPath;
	}


	public static void setSpellsPath(String spellsPath) {
		Deck.spellsPath = spellsPath;
	}


	public static String getMonstersPath() {
		return monstersPath;
	}


	public static void setMonstersPath(String monstersPath) {
		Deck.monstersPath = monstersPath;
	}		
	public ArrayList<Card> trials(String path) throws IOException, UnknownSpellCardException, MissingFieldException, UnknownCardTypeException, EmptyFieldException{
		counter=0;
		Scanner sc=new Scanner(System.in);
		while(true){
		try{
			return loadCardsFromFile(path);}
		catch(MissingFieldException e){
            counter++;
            if(counter<4){
			System.out.println("The file has a missing field");
			System.out.println("Please enter a correct path: ");		
			path=sc.nextLine();
			//loadCardsFromFile(path);
            }
            else {sc.close();
			throw e;}
		}
		
		catch(UnknownSpellCardException e){
            counter++;
            if(counter<4){
			System.out.println("The file has an unknown spell card");
			System.out.println("Please enter a correct path: ");		
			path=sc.nextLine();
			//loadCardsFromFile(path);
            }
            else {sc.close();
			throw e;}
		}
		
		catch(UnknownCardTypeException e){
            counter++;
            if(counter<4){
			System.out.println("The file has an unknown Card type");
			System.out.println("Please enter a correct path: ");		
			path=sc.nextLine();
			//loadCardsFromFile(path);
            }else {sc.close();
			throw e;}
		}
		
		catch(FileNotFoundException e){
            counter++;
            if(counter<4){
			System.out.println("The file was not found");
			System.out.println("Please enter a correct path: ");		
			path=sc.nextLine();
			//loadCardsFromFile(path);
            }
            else {sc.close();
			throw e;}
		}catch(EmptyFieldException e){
            counter++;
            if(counter<4){
			System.out.println("The file has an empty field");
			System.out.println("Please enter a correct path: ");		
			path=sc.nextLine();
			//loadCardsFromFile(path);
            }
            else {sc.close();
			throw e;}
		}}
		}
}