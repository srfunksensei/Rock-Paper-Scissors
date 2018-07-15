package com.rps.engine;

import com.rps.dto.Move;

/**
 * Represents a rule for game.
 * 
 * @author mb
 *
 */
public class Rule {
	public final Move winner;
	public final Move loser;

    public Rule(final Move winner, final Move loser)
    {
        this.winner = winner;
        this.loser = loser;
    }

    @Override
    public String toString()
    {
         return String.format("Winner:%s Loser:%s", this.winner, this.loser);
    }
}
