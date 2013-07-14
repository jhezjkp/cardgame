package me.vivia.game.props;

/**
 * 随机出产候选组
 * 
 * @author jhezjkp@gmail.com
 * 
 */
public class CandidateOption {

	/** 模板编号 */
	private int templateId;
	/** 数量 */
	private int quantity;
	/** 出产机率 */
	private int odds;

	public CandidateOption(int templateId, int quantity, int odds) {
		super();
		this.templateId = templateId;
		this.quantity = quantity;
		this.odds = odds;
	}

	public int getTemplateId() {
		return templateId;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getOdds() {
		return odds;
	}

}
