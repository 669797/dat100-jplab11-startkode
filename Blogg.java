package no.hvl.dat100.jplab11.oppgave3;

import no.hvl.dat100.jplab11.common.TODO;
import no.hvl.dat100.jplab11.oppgave1.*;

public class Blogg {

	// TODO: objektvariable
	private Innlegg[] innleggtabell;
	private int nesteledig;

	public Blogg() {
		innleggtabell = new Innlegg[20];
		nesteledig = 0;
	}

	public Blogg(int lengde) {
		innleggtabell = new Innlegg[lengde];
		nesteledig = 0;
	}

	public int getAntall() {
		return nesteledig;
	}

	public Innlegg[] getSamling() {
		return innleggtabell;
	}

	public void setSamling(Innlegg[] tabell) {
		innleggtabell = tabell;
	}

	public int finnInnlegg(Innlegg innlegg) {
		int pos = 0;
		boolean funnet = false;

		while (!funnet && pos < nesteledig) {
			if (innleggtabell[pos].erLik(innlegg)) {
				funnet = true;
			} else {
				pos++;
			}
		}

		if (funnet) {
			return pos;
		} else {
			return -1;
		}
	}

	public boolean finnes(Innlegg innlegg) {
//		boolean finnes = false;
//		int i = 0;
//
//		while (!finnes && i < nesteledig) {
//			if (innleggtabell[i].getId() == innlegg.getId()) {
//				finnes = true;
//			}
//			i++;
//		}
//		return finnes;
		if (finnInnlegg(innlegg) >= 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean ledigPlass() {
		return nesteledig < innleggtabell.length;
	}

	public boolean leggTil(Innlegg innlegg) {
		boolean leggtil = finnInnlegg(innlegg) == -1;

		if (leggtil && nesteledig < innleggtabell.length) {
			innleggtabell[nesteledig] = innlegg;
			nesteledig++;
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		String string = "";

		for (int i = 0; i < nesteledig; i++) {
			string += innleggtabell[i].toString();
		}
		string = nesteledig + "\n" + string;
		return string;
	}

	// valgfrie oppgaver nedenfor

	public void utvid() {
		Blogg nyblogg = new Blogg(2 * innleggtabell.length);
		for (int i = 0; i < innleggtabell.length; i++) {
			nyblogg.getSamling()[i] = getSamling()[i];
		}
		setSamling(nyblogg.getSamling());
	}

	public boolean leggTilUtvid(Innlegg innlegg) {
		boolean lagttil = true;
		if (finnes(innlegg)) {
			lagttil = false;
		} else {
			leggTil(innlegg);
			if (!leggTil(innlegg)) {
				utvid();
				leggTil(innlegg);
			}
		}
		return lagttil;
	}

	public boolean slett(Innlegg innlegg) {
		int pos = finnInnlegg(innlegg);
		if (pos >= 0) {
			nesteledig--;
			innleggtabell[pos] = null;
			return true;
		} else {
			return false;
		}
	}

	public int[] search(String keyword) {

		int[] idtab = new int[innleggtabell.length];
		for (int i = 0; i < innleggtabell.length; i++) {
			if (toString().contains(keyword)) {
				idtab[i] = innleggtabell[i].getId();
			}
		}
		return idtab;
	}
}