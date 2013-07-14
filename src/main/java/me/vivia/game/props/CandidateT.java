package me.vivia.game.props;

import java.util.List;

/**
 * 候选库模板
 * 
 * @author jhezjkp@gmail.com
 * 
 */
public class CandidateT {

	/** 修行库编号 */
	private int id;
	/** 隶属于该库的候选出产项 */
	private CandidateOption[] options;
	/** 该库下所有候选出产项的机率总和 */
	private int totalOdds;

	public CandidateT(int id, CandidateOption[] options) {
		super();
		this.id = id;
		this.options = options;
		for (CandidateOption option : options) {
			totalOdds += option.getOdds();
		}
	}

	public int getId() {
		return id;
	}

	public CandidateOption[] getOptions() {
		return options;
	}

	public int getTotalOdds() {
		return totalOdds;
	}
}
