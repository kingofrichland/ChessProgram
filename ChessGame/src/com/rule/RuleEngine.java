package com.rule;

/*
 * RuleEngine
 * RuleEngine should divided into 2 parts.
 * Part1, is a set of function that is rich of information, and is easily be used by BoardGameFramework
 * Part2, is a set of function that is not so rich of info, but uses integer, array, char etc to speed up the calculation process, 
 * and is used by players to calc their best move.
 * Some of the functions may be duplicate in 2 parts. However, as their purpose are different, they are target to be exist together
 * Both set of functions are stateless, which means caller should pass all the required information to the functions, and those functions 
 * will base on those parameter to return corresponding value. They will not "remember" board state 
 * */
abstract public class RuleEngine {

	public RuleEngine() {
	}

}
