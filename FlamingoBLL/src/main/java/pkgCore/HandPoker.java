package pkgCore;

import java.util.ArrayList;
import java.util.Collections;

import pkgEnum.eCardNo;
import pkgEnum.eHandStrength;
import pkgEnum.eRank;
import pkgEnum.eSuit;

public class HandPoker extends Hand {

	private ArrayList<CardRankCount> CRC = null;

	public HandPoker() {
		this.setHS(new HandScorePoker());
	}

	protected ArrayList<CardRankCount> getCRC() {
		return CRC;
	}

	@Override
	public HandScore ScoreHand() {
		
		Collections.sort(super.getCards());
		Frequency();

		if (isRoyalFlush()) {

		} else if (isStraightFlush()) {
			
		}
		else if (isFullHouse()) {
			
		}
		else if (isFlush()) {
			
		}
		else if (isStraight()) {
			
		}
		else if (isThreeOfAKind()) {
			
		}
		else if (isTwoPair()) {
			
		}
		else if (isPair()) {
			
		}
		else if (isHighCard()) {
			
		}
		
		return null;
	}

	private void Frequency() {

		CRC = new ArrayList<CardRankCount>();

		int iCnt = 0;
		int iPos = 0;

		for (eRank eRank : eRank.values()) {
			iCnt = (CountRank(eRank));
			if (iCnt > 0) {
				iPos = FindCardRank(eRank);
				CRC.add(new CardRankCount(eRank, iCnt, iPos));
			}
		}

		Collections.sort(CRC);

		for (CardRankCount crcount : CRC) {
			System.out.print(crcount.getiCnt());
			System.out.print(" ");
			System.out.print(crcount.geteRank());
			System.out.print(" ");
			System.out.println(crcount.getiCardPosition());
		}

	}

	private int CountRank(eRank eRank) {
		int iCnt = 0;
		for (Card c : super.getCards()) {
			if (c.geteRank() == eRank) {
				iCnt++;
			}
		}
		return iCnt;
	}

	private int FindCardRank(eRank eRank) {
		int iPos = 0;

		for (iPos = 0; iPos < super.getCards().size(); iPos++) {
			if (super.getCards().get(iPos).geteRank() == eRank) {
				break;
			}
		}
		return iPos;
	}
//ROYAL FLUSH DONE
	public boolean isRoyalFlush() {
		boolean bIsRoyalFlush = false;
		HandScorePoker HS = (HandScorePoker)super.getHS();
		
		if (isStraightFlush() && super.getCards().get(eCardNo.FIRST.getiCardNo()).geteRank() == eRank.TEN) {
			
			bIsRoyalFlush = true;
			HS.seteHandStrength(eHandStrength.RoyalFlush);
			
			
		}

		return bIsRoyalFlush;
	}
//Straight FLUSH DONE
	public boolean isStraightFlush() {
		boolean bisStraightFlush = false;
		HandScorePoker HS = (HandScorePoker)super.getHS();
		
		if (isStraight() && isFlush()) {
			bisStraightFlush = true;
			HS.seteHandStrength(eHandStrength.StraightFlush);
		}
		
		return bisStraightFlush;
	}
//FOUR KIND DONE
	public boolean isFourOfAKind() {
		boolean bisFourOfAKind = false;
		HandScorePoker HS = (HandScorePoker) super.getHS();

		if (super.getCards().get(eCardNo.FIRST.getiCardNo()).geteRank() == super.getCards()
				.get(eCardNo.FOURTH.getiCardNo()).geteRank()) {

			HS.seteHandStrength(eHandStrength.FourOfAKind);
			HS.setHiCard(super.getCards().get(eCardNo.FIRST.getiCardNo()));
			HS.setLoCard(null);
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(super.getCards().get(eCardNo.FIFTH.getiCardNo()));
			HS.setKickers(kickers);
			bisFourOfAKind = true;

		} else if (super.getCards().get(eCardNo.SECOND.getiCardNo()).geteRank() == super.getCards()
				.get(eCardNo.FIFTH.getiCardNo()).geteRank()) {
			HS.seteHandStrength(eHandStrength.FourOfAKind);
			HS.setHiCard(super.getCards().get(eCardNo.SECOND.getiCardNo()));
			HS.setLoCard(null);
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(super.getCards().get(eCardNo.FIRST.getiCardNo()));
			HS.setKickers(kickers);
			bisFourOfAKind = true;
		}

		return bisFourOfAKind;
	}
//Full House Done
	public boolean isFullHouse() {
		boolean bisFullHouse = false;
		

		if (this.CRC.size() == 2) {
			if ((CRC.get(0).getiCnt() == 3) && (CRC.get(1).getiCnt() == 2)) {
				bisFullHouse = true;
				HandScorePoker HS = (HandScorePoker)super.getHS();
				HS.seteHandStrength(eHandStrength.FullHouse);
				HS.setHiCard(this.getCards().get(CRC.get(0).getiCardPosition()));
				HS.setLoCard(this.getCards().get(CRC.get(1).getiCardPosition()));
				ArrayList<Card> kickers = new ArrayList<Card>();
				HS.setKickers(kickers);
				this.setHS(HS);
			}
		}
		return bisFullHouse;

	}
//Flush was already done
	public boolean isFlush() {
		boolean bisFlush = false;

		int iCardCnt = super.getCards().size();
		int iSuitCnt = 0;

		for (eSuit eSuit : eSuit.values()) {
			for (Card c : super.getCards()) {
				if (eSuit == c.geteSuit()) {
					iSuitCnt++;
				}
			}
			if (iSuitCnt > 0)
				break;
		}

		if (iSuitCnt == iCardCnt) {
			bisFlush = true;
			HandScorePoker HS = (HandScorePoker)super.getHS();
			HS.seteHandStrength(eHandStrength.Flush);
	}
		else {
			bisFlush = false;
	}
		return bisFlush;
	}
//Straight Done
	public boolean isStraight() {
		boolean bisStraight = false;
		HandScorePoker HS = (HandScorePoker)super.getHS();
		
		if (super.getCards().get(eCardNo.FIRST.getiCardNo()).geteRank().getiRankNbr() ==
				1 + super.getCards().get(eCardNo.SECOND.getiCardNo()).geteRank().getiRankNbr()) {
			if (super.getCards().get(eCardNo.SECOND.getiCardNo()).geteRank().getiRankNbr() ==
					1 + super.getCards().get(eCardNo.THIRD.getiCardNo()).geteRank().getiRankNbr()) {
				if (super.getCards().get(eCardNo.THIRD.getiCardNo()).geteRank().getiRankNbr() ==
						1 + super.getCards().get(eCardNo.FOURTH.getiCardNo()).geteRank().getiRankNbr()) {
					if (super.getCards().get(eCardNo.FOURTH.getiCardNo()).geteRank().getiRankNbr() ==
							1 + super.getCards().get(eCardNo.FIFTH.getiCardNo()).geteRank().getiRankNbr()) {
						bisStraight = true;
						HS.seteHandStrength(eHandStrength.Straight);
						return bisStraight;
					}
				}
			}
		}
		else if (super.getCards().get(eCardNo.FIRST.getiCardNo()).geteRank().getiRankNbr() == 14) {
			if (super.getCards().get(eCardNo.SECOND.getiCardNo()).geteRank().getiRankNbr() ==
					1 + super.getCards().get(eCardNo.THIRD.getiCardNo()).geteRank().getiRankNbr()) {
				if (super.getCards().get(eCardNo.THIRD.getiCardNo()).geteRank().getiRankNbr() ==
						1 + super.getCards().get(eCardNo.FOURTH.getiCardNo()).geteRank().getiRankNbr()) {
					if (super.getCards().get(eCardNo.FOURTH.getiCardNo()).geteRank().getiRankNbr() ==
							1 + super.getCards().get(eCardNo.FIFTH.getiCardNo()).geteRank().getiRankNbr()) {
						if (super.getCards().get(eCardNo.FOURTH.getiCardNo()).geteRank().getiRankNbr() == 2) {
							bisStraight = true;
							HS.seteHandStrength(eHandStrength.Straight);
							return bisStraight;
						}
					}
				}
			}
		}
		
		else
			bisStraight = false;
		
		// TODO : Implement this method
		return bisStraight;
	}
//THREE KIND DONE
	public boolean isThreeOfAKind() {
		boolean bisThreeOfAKind = false;

		if (super.getCards().get(eCardNo.FIRST.getiCardNo()).geteRank() ==
				super.getCards().get(eCardNo.THIRD.getiCardNo()).geteRank()) {
			HandScorePoker HS = (HandScorePoker) super.getHS();
			HS.seteHandStrength(eHandStrength.ThreeOfAKind);
			HS.setHiCard(super.getCards().get(eCardNo.FIRST.getiCardNo()));
			HS.setLoCard(null);
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(super.getCards().get(eCardNo.FOURTH.getiCardNo()));
			kickers.add(super.getCards().get(eCardNo.FIFTH.getiCardNo()));
			HS.setKickers(kickers);
			bisThreeOfAKind = true;
				
		}
		else if (super.getCards().get(eCardNo.SECOND.getiCardNo()).geteRank() ==
				super.getCards().get(eCardNo.FOURTH.getiCardNo()).geteRank()) {
			HandScorePoker HS = (HandScorePoker) super.getHS();
			HS.seteHandStrength(eHandStrength.ThreeOfAKind);
			HS.setHiCard(super.getCards().get(eCardNo.SECOND.getiCardNo()));
			HS.setLoCard(null);
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(super.getCards().get(eCardNo.FIRST.getiCardNo()));
			kickers.add(super.getCards().get(eCardNo.FIFTH.getiCardNo()));
			HS.setKickers(kickers);
			bisThreeOfAKind = true;
		}
		else if (super.getCards().get(eCardNo.THIRD.getiCardNo()).geteRank() ==
				super.getCards().get(eCardNo.FIFTH.getiCardNo()).geteRank()) {
			HandScorePoker HS = (HandScorePoker) super.getHS();
			HS.seteHandStrength(eHandStrength.ThreeOfAKind);
			HS.setHiCard(super.getCards().get(eCardNo.THIRD.getiCardNo()));
			HS.setLoCard(null);
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(super.getCards().get(eCardNo.FIRST.getiCardNo()));
			kickers.add(super.getCards().get(eCardNo.SECOND.getiCardNo()));
			HS.setKickers(kickers);
			bisThreeOfAKind = true;
		}


		return bisThreeOfAKind;
	}
//Two Pair Done
	public boolean isTwoPair() {
		boolean bisTwoPair = false;
		HandScorePoker HS = (HandScorePoker)super.getHS();
		if (super.getCards().get(eCardNo.FIRST.getiCardNo()).geteRank() ==
				super.getCards().get(eCardNo.SECOND.getiCardNo()).geteRank() &&
				super.getCards().get(eCardNo.THIRD.getiCardNo()).geteRank() ==
				super.getCards().get(eCardNo.FOURTH.getiCardNo()).geteRank()) {
			HS.seteHandStrength(eHandStrength.TwoPair);
			HS.setHiCard(super.getCards().get(eCardNo.FIRST.getiCardNo()));
			HS.setLoCard(super.getCards().get(eCardNo.THIRD.getiCardNo()));
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(super.getCards().get(eCardNo.FIFTH.getiCardNo()));
			HS.setKickers(kickers);
			bisTwoPair = true;
		}
		else if (super.getCards().get(eCardNo.FIRST.getiCardNo()).geteRank() == 
				super.getCards().get(eCardNo.SECOND.getiCardNo()).geteRank() &&
				super.getCards().get(eCardNo.FOURTH.getiCardNo()).geteRank() ==
				super.getCards().get(eCardNo.FIFTH.getiCardNo()).geteRank()) {
			HS.setHiCard(super.getCards().get(eCardNo.FIRST.getiCardNo()));
			HS.setLoCard(super.getCards().get(eCardNo.FOURTH.getiCardNo()));
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(super.getCards().get(eCardNo.THIRD.getiCardNo()));
			HS.setKickers(kickers);
			bisTwoPair = true;
		}
		else if (super.getCards().get(eCardNo.SECOND.getiCardNo()).geteRank() == 
				super.getCards().get(eCardNo.THIRD.getiCardNo()).geteRank() &&
				super.getCards().get(eCardNo.FOURTH.getiCardNo()).geteRank() ==
				super.getCards().get(eCardNo.FIFTH.getiCardNo()).geteRank()) {
			HS.setHiCard(super.getCards().get(eCardNo.SECOND.getiCardNo()));
			HS.setLoCard(super.getCards().get(eCardNo.FOURTH.getiCardNo()));
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(super.getCards().get(eCardNo.FIRST.getiCardNo()));
			HS.setKickers(kickers);
			bisTwoPair = true;
		}
				

		return bisTwoPair;
	}
	//Pair Done
	public boolean isPair() {
		boolean bisPair = false;
		HandScorePoker HS = (HandScorePoker)super.getHS();
		
		if (super.getCards().get(eCardNo.FIRST.getiCardNo()).geteRank() ==
				super.getCards().get(eCardNo.SECOND.getiCardNo()).geteRank()) {
			HS.seteHandStrength(eHandStrength.Pair);
			HS.setHiCard(super.getCards().get(eCardNo.FIRST.getiCardNo()));
			HS.setLoCard(null);
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(super.getCards().get(eCardNo.THIRD.getiCardNo()));
			kickers.add(super.getCards().get(eCardNo.FOURTH.getiCardNo()));
			kickers.add(super.getCards().get(eCardNo.FIFTH.getiCardNo()));
			HS.setKickers(kickers);
			bisPair = true;
		}
		else if (super.getCards().get(eCardNo.SECOND.getiCardNo()).geteRank() ==
				super.getCards().get(eCardNo.THIRD.getiCardNo()).geteRank()) {
			HS.seteHandStrength(eHandStrength.Pair);
			HS.setHiCard(super.getCards().get(eCardNo.SECOND.getiCardNo()));
			HS.setLoCard(null);
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(super.getCards().get(eCardNo.FIRST.getiCardNo()));
			kickers.add(super.getCards().get(eCardNo.FOURTH.getiCardNo()));
			kickers.add(super.getCards().get(eCardNo.FIFTH.getiCardNo()));
			HS.setKickers(kickers);
			bisPair = true;
		}
		else if (super.getCards().get(eCardNo.THIRD.getiCardNo()).geteRank() ==
				super.getCards().get(eCardNo.FOURTH.getiCardNo()).geteRank()) {
			HS.seteHandStrength(eHandStrength.Pair);
			HS.setHiCard(super.getCards().get(eCardNo.THIRD.getiCardNo()));
			HS.setLoCard(null);
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(super.getCards().get(eCardNo.FIRST.getiCardNo()));
			kickers.add(super.getCards().get(eCardNo.SECOND.getiCardNo()));
			kickers.add(super.getCards().get(eCardNo.FIFTH.getiCardNo()));
			HS.setKickers(kickers);
			bisPair = true;
		}
		else if (super.getCards().get(eCardNo.FOURTH.getiCardNo()).geteRank() ==
				super.getCards().get(eCardNo.FIFTH.getiCardNo()).geteRank()) {
			HS.seteHandStrength(eHandStrength.Pair);
			HS.setHiCard(super.getCards().get(eCardNo.FOURTH.getiCardNo()));
			HS.setLoCard(null);
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(super.getCards().get(eCardNo.FIRST.getiCardNo()));
			kickers.add(super.getCards().get(eCardNo.SECOND.getiCardNo()));
			kickers.add(super.getCards().get(eCardNo.THIRD.getiCardNo()));
			HS.setKickers(kickers);
			bisPair = true;
		}

		return bisPair;
	}
	//High Card Done
	public boolean isHighCard() {
		boolean bisHighCard = false;
		HandScorePoker HS = (HandScorePoker)super.getHS();
		
		HS.seteHandStrength(eHandStrength.HighCard);
		HS.setHiCard(super.getCards().get(eCardNo.FIRST.getiCardNo()));
		
		ArrayList<Card> kickers = new ArrayList<Card>();
		kickers.add(super.getCards().get(eCardNo.SECOND.getiCardNo()));
		kickers.add(super.getCards().get(eCardNo.THIRD.getiCardNo()));
		kickers.add(super.getCards().get(eCardNo.FOURTH.getiCardNo()));
		kickers.add(super.getCards().get(eCardNo.FIFTH.getiCardNo()));
		bisHighCard = true;
		
		
		

		return bisHighCard;
	}

}
