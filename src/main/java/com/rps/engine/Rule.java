package com.rps.engine;

import com.rps.dto.Move;

/**
 * Represents a rule for game.
 * 
 * @author mb
 *
 */
public class Rule {

	private final Move winner;
	private final Move loser;

    public Rule(final Move winner, final Move loser) {
        this.winner = winner;
        this.loser = loser;
    }

    public Move getWinner() {
        return this.winner;
    }

    public Move getLoser() {
        return this.loser;
    }

    @Override
    public String toString()
    {
         return String.format("Winner:%s Loser:%s", this.winner, this.loser);
    }
}
