package pkgCore;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgEnum.eRank;
import pkgEnum.eSuit;

public class HandPokerTest {
	
	 @Test
	 public void RoyalFlush() {
		 
	 HandPoker hp = new HandPoker();
	 hp.AddCard(new Card(eSuit.CLUBS,eRank.TEN));
	 hp.AddCard(new Card(eSuit.CLUBS,eRank.JACK));
	 hp.AddCard(new Card(eSuit.CLUBS,eRank.QUEEN));
	 hp.AddCard(new Card(eSuit.CLUBS,eRank.KING));
	 hp.AddCard(new Card(eSuit.CLUBS,eRank.ACE));
	
	 HandScorePoker HS = (HandScorePoker) hp.ScoreHand();
	 assertEquals("RoyalFlush", HS.geteHandStrength().toString());
	}


	@Test
	 public void StraightFlush() {
		
	 HandPoker hp = new HandPoker();
	 hp.AddCard(new Card(eSuit.CLUBS,eRank.SIX));
	 hp.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
	 hp.AddCard(new Card(eSuit.CLUBS,eRank.FIVE));
	 hp.AddCard(new Card(eSuit.CLUBS,eRank.FOUR));
	 hp.AddCard(new Card(eSuit.CLUBS,eRank.THREE));
	
	 HandScorePoker HS = (HandScorePoker) hp.ScoreHand();
	 assertEquals("Straight Flush",HS.geteHandStrength().toString());
	 }
	 
		@Test
		public void FourOfAKind() {
		
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS, eRank.TWO));
		hp.AddCard(new Card(eSuit.HEARTS, eRank.THREE));
		hp.AddCard(new Card(eSuit.CLUBS, eRank.THREE));
		hp.AddCard(new Card(eSuit.SPADES, eRank.THREE));
		hp.AddCard(new Card(eSuit.CLUBS, eRank.THREE));

		HandScorePoker HS = (HandScorePoker) hp.ScoreHand();

		assertEquals("Four of a Kind", HS.geteHandStrength().toString());
		assertEquals(eRank.THREE, HS.getHiCard().geteRank());

		assertEquals(new Card(eSuit.CLUBS, eRank.TWO).geteRank(), HS.getKickers().get(4).geteRank());
	}

		@Test
		public void FourOfAKind2() {
			
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS, eRank.JACK));
		hp.AddCard(new Card(eSuit.SPADES, eRank.JACK));
		hp.AddCard(new Card(eSuit.DIAMONDS, eRank.JACK));
		hp.AddCard(new Card(eSuit.CLUBS, eRank.JACK));
		hp.AddCard(new Card(eSuit.CLUBS, eRank.THREE));

		HandScorePoker HS = (HandScorePoker) hp.ScoreHand();

		assertEquals("Four of a Kind", HS.geteHandStrength().toString());
		assertEquals(eRank.JACK, HS.getHiCard().geteRank());

		assertEquals(new Card(eSuit.CLUBS, eRank.THREE).geteRank(), HS.getKickers().get(0).geteRank());
	}
		
		@Test
		public void Pair() {

		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS, eRank.JACK));
		hp.AddCard(new Card(eSuit.CLUBS, eRank.EIGHT));
		hp.AddCard(new Card(eSuit.DIAMONDS, eRank.TWO));
		hp.AddCard(new Card(eSuit.CLUBS, eRank.SEVEN));
		hp.AddCard(new Card(eSuit.HEARTS, eRank.TWO));

		HandScorePoker HS = (HandScorePoker) hp.ScoreHand();
		assertEquals("Pair", HS.geteHandStrength().toString());

		assertEquals(eRank.TWO, HS.getHiCard().geteRank());

		assertEquals(new Card(eSuit.CLUBS, eRank.JACK).geteRank(), HS.getKickers().get(0).geteRank());
		
		assertEquals(new Card(eSuit.CLUBS, eRank.EIGHT).geteRank(), HS.getKickers().get(1).geteRank());
		
		assertEquals(new Card(eSuit.CLUBS, eRank.SEVEN).geteRank(), HS.getKickers().get(2).geteRank());
		
		}
		
		@Test
		public void Pair2() {

		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS, eRank.FIVE));
		hp.AddCard(new Card(eSuit.CLUBS, eRank.THREE));
		hp.AddCard(new Card(eSuit.SPADES, eRank.ACE));
		hp.AddCard(new Card(eSuit.CLUBS, eRank.SIX));
		hp.AddCard(new Card(eSuit.HEARTS, eRank.SIX));
		

		HandScorePoker HS = (HandScorePoker) hp.ScoreHand();


		assertEquals("Pair", HS.geteHandStrength().toString());

		assertEquals(eRank.SIX, HS.getHiCard().geteRank());

		assertEquals(new Card(eSuit.CLUBS, eRank.FIVE).geteRank(), HS.getKickers().get(1).geteRank());
			
		assertEquals(new Card(eSuit.CLUBS, eRank.THREE).geteRank(), HS.getKickers().get(2).geteRank());
			
		assertEquals(new Card(eSuit.SPADES, eRank.ACE).geteRank(), HS.getKickers().get(0).geteRank());
		
	}
		
		@Test
		public void TwoPair() {
		
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS, eRank.TWO));
		hp.AddCard(new Card(eSuit.SPADES, eRank.THREE));
		hp.AddCard(new Card(eSuit.DIAMONDS, eRank.TWO));
		hp.AddCard(new Card(eSuit.SPADES, eRank.THREE));
		hp.AddCard(new Card(eSuit.CLUBS, eRank.KING));

		HandScorePoker HS = (HandScorePoker) hp.ScoreHand();

		assertEquals("Two Pair", HS.geteHandStrength().toString());
		assertEquals(eRank.THREE, HS.getHiCard().geteRank());
		assertEquals(eRank.TWO, HS.getLoCard().geteRank());
	
		assertEquals(new Card(eSuit.CLUBS, eRank.KING).geteRank(), HS.getKickers().get(0).geteRank());
		}
		

		@Test
		public void TwoPair2() {
		
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.SPADES, eRank.SEVEN));
		hp.AddCard(new Card(eSuit.SPADES, eRank.SEVEN));
		hp.AddCard(new Card(eSuit.CLUBS, eRank.TWO));
		hp.AddCard(new Card(eSuit.CLUBS, eRank.TWO));
		hp.AddCard(new Card(eSuit.CLUBS, eRank.FIVE));

		HandScorePoker HS = (HandScorePoker) hp.ScoreHand();

		assertEquals("Two Pairs", HS.geteHandStrength().toString());
		assertEquals(eRank.SEVEN, HS.getHiCard().geteRank());
		assertEquals(eRank.TWO, HS.getLoCard().geteRank());
		assertEquals(new Card(eSuit.SPADES, eRank.FIVE).geteRank(), HS.getKickers().get(0).geteRank());

	}

		@Test
		public void ThreeKind() {

		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS, eRank.THREE));
		hp.AddCard(new Card(eSuit.CLUBS, eRank.THREE));
		hp.AddCard(new Card(eSuit.SPADES, eRank.SEVEN));
		hp.AddCard(new Card(eSuit.CLUBS, eRank.KING));
		hp.AddCard(new Card(eSuit.CLUBS, eRank.THREE));

		HandScorePoker HS = (HandScorePoker) hp.ScoreHand();

		assertEquals("Three of a Kind", HS.geteHandStrength().toString());
		assertEquals(eRank.TWO, HS.getHiCard().geteRank());

		assertEquals(new Card(eSuit.CLUBS, eRank.KING).geteRank(), HS.getKickers().get(0).geteRank());
		assertEquals(new Card(eSuit.SPADES, eRank.SEVEN).geteRank(), HS.getKickers().get(1).geteRank());
	}
		
		@Test
		public void ThreeKind2() {

		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.SPADES, eRank.QUEEN));
		hp.AddCard(new Card(eSuit.SPADES, eRank.QUEEN));
		hp.AddCard(new Card(eSuit.DIAMONDS, eRank.FIVE));
		hp.AddCard(new Card(eSuit.CLUBS, eRank.SEVEN));
		hp.AddCard(new Card(eSuit.SPADES, eRank.QUEEN));
		HandScorePoker HS = (HandScorePoker) hp.ScoreHand();

		assertEquals("Three of a Kind", HS.geteHandStrength().toString());
		assertEquals(eRank.KING, HS.getHiCard().geteRank());

		assertEquals(new Card(eSuit.DIAMONDS, eRank.FIVE).geteRank(), HS.getKickers().get(1).geteRank());
		assertEquals(new Card(eSuit.CLUBS, eRank.SEVEN).geteRank(), HS.getKickers().get(0).geteRank());
		
		}
		
		@Test
		public void FullHouse() {
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.HEARTS, eRank.TWO));
		hp.AddCard(new Card(eSuit.SPADES, eRank.THREE));
		hp.AddCard(new Card(eSuit.SPADES, eRank.TWO));
		hp.AddCard(new Card(eSuit.DIAMONDS, eRank.TWO));
		hp.AddCard(new Card(eSuit.CLUBS, eRank.THREE));

		HandScorePoker HS = (HandScorePoker) hp.ScoreHand();

		assertEquals("Full House", HS.geteHandStrength().toString());
		assertEquals(eRank.TWO, HS.getHiCard().geteRank());
		assertEquals(eRank.THREE, HS.getLoCard().geteRank());
	
		}
		
		@Test
		public void FullHosue2() {
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.DIAMONDS, eRank.SEVEN));
		hp.AddCard(new Card(eSuit.DIAMONDS, eRank.SEVEN));
		hp.AddCard(new Card(eSuit.HEARTS, eRank.FOUR));
		hp.AddCard(new Card(eSuit.HEARTS, eRank.FOUR));
		hp.AddCard(new Card(eSuit.DIAMONDS, eRank.SEVEN));

		HandScorePoker HS = (HandScorePoker) hp.ScoreHand();

		assertEquals("Full House", HS.geteHandStrength().toString());
		assertEquals(eRank.SEVEN, HS.getHiCard().geteRank());
		assertEquals(eRank.FOUR, HS.getLoCard().geteRank());
	}

		@Test
		public void Flush() {
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS, eRank.FOUR));
		hp.AddCard(new Card(eSuit.CLUBS, eRank.TWO));
		hp.AddCard(new Card(eSuit.CLUBS, eRank.KING));
		hp.AddCard(new Card(eSuit.CLUBS, eRank.TWO));
		hp.AddCard(new Card(eSuit.CLUBS, eRank.THREE));

		HandScorePoker HS = (HandScorePoker) hp.ScoreHand();

		assertEquals("Flush", HS.geteHandStrength().toString());
			
	}
		
		@Test
		public void Straight() {
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.DIAMONDS, eRank.THREE));
		hp.AddCard(new Card(eSuit.CLUBS, eRank.FOUR));
		hp.AddCard(new Card(eSuit.SPADES, eRank.FIVE));
		hp.AddCard(new Card(eSuit.HEARTS, eRank.SIX));
		hp.AddCard(new Card(eSuit.DIAMONDS, eRank.SEVEN));
			
		HandScorePoker HS = (HandScorePoker) hp.ScoreHand();

		assertEquals("Straight", HS.geteHandStrength().toString());
	}
		
		@Test
		public void HiCard() {
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.HEARTS, eRank.FOUR));
		hp.AddCard(new Card(eSuit.CLUBS, eRank.TWO));
		hp.AddCard(new Card(eSuit.SPADES, eRank.QUEEN));
		hp.AddCard(new Card(eSuit.SPADES, eRank.SIX));
		hp.AddCard(new Card(eSuit.DIAMONDS, eRank.NINE));

		HandScorePoker HS = (HandScorePoker) hp.ScoreHand();

		assertEquals("Hi Card", HS.geteHandStrength().toString());
			
	}

}
