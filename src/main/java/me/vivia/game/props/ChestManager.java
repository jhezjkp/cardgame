package me.vivia.game.props;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.vivia.game.utils.IdGenerator;
import me.vivia.game.utils.RandomUtil;
import me.vivia.game.utils.ScriptUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 宝箱管理器
 * 
 * @author jhezjkp@gmail.com
 * 
 */
public class ChestManager {

	private final Logger logger = LoggerFactory.getLogger(ChestManager.class);

	private static final ChestManager instance = new ChestManager();

	// 宝箱候选库出产量计数
	private Map<ChestKey, Integer> map = new HashMap<ChestKey, Integer>();

	private ChestManager() {
		// 初始化种宝箱的候选物品库出产量
		for (ChestT template : ScriptUtil.getAllChestTemplates()) {
			for (int[] config : template.getCandidateConfig()) {
				// [候选库编号 出产总量(全局随机时)或保底出产数(玩家随机时) 出产机率]
				map.put(new ChestKey(template.getId(), config[0]), 0);
			}
		}
	}

	public static ChestManager getInstance() {
		return instance;
	}

	/**
	 * 从物品库模板中随机获取一个物品配置项
	 * 
	 * @param template
	 * @return
	 */
	private CandidateOption getRandomCandidateOption(CandidateT template) {
		int random = RandomUtil.randomInt(template.getTotalOdds());
		int value = 0;
		for (CandidateOption option : template.getOptions()) {
			value += option.getOdds();
			if (random < value) {
				return option;
			}
		}
		logger.error("随机异常，random={}, totalOdds={}", random,
				template.getTotalOdds());
		throw new IllegalStateException("随机异常");
	}

	/**
	 * 生成指定的物品
	 * 
	 * @param id
	 *            候选物品库模板编号
	 * 
	 * @return
	 */
	private Props genSpecificProps(int id) {
		CandidateT template = ScriptUtil.findCandidateTemplate(id);
		if (template == null) {
			logger.error("xxxxx 候选物品库不存在,id={}", id);
			throw new IllegalStateException("候选物品库不存在!");
		}
		// 从候选模板库中随机一种物品出产
		CandidateOption option = getRandomCandidateOption(template);
		int propsTemplateId = option.getTemplateId();
		if (ScriptUtil.isItem(propsTemplateId)) { // 如果是道具
			return new Item(IdGenerator.getId(), propsTemplateId,
					option.getQuantity());
		}
		// TODO 不是道具的情况
		return null;
	}

	public List<Props> genProps(ChestT template) {
		List<Props> result = new ArrayList<Props>();
		// 计算机率总值，要去除不能再出产的物品库的出产机率
		int totalOdds = 0;
		// 还可以出产的候选物品库
		List<int[]> optionList = new ArrayList<int[]>();
		// 不限制出产的候选物品库
		List<int[]> unlimitOptionList = new ArrayList<int[]>();
		for (int[] config : template.getCandidateConfig()) {
			// [候选库编号 出产总量(全局随机时)或保底出产数(玩家随机时) 出产机率]
			// 去除不能再出产的物品库的出产机率
			if (config[1] == 0) {
				// 不限制出产量的候选物品库
				totalOdds += config[2];
				optionList.add(config);
				unlimitOptionList.add(config);
			} else if (map.get(new ChestKey(template.getId(), config[0])) < config[1]) {
				totalOdds += config[2];
				optionList.add(config);
			}
		}
		if (optionList.isEmpty()) {
			String msg = "模板配置错误！！！";
			logger.error("xxxxxx {}", msg);
			throw new IllegalStateException(msg);
		}
		// 是否出产多种物品
		if (template.isMultiProduce()) { // 出产多个
			// 首先做随机
			for (int[] config : optionList) {
				if (RandomUtil.isHit(config[2], 100)) {
					result.add(genSpecificProps(config[0]));
					ChestKey key = new ChestKey(template.getId(), config[0]);
					map.put(key, map.get(key) + 1);
				}
			}
			// 判断是否有产出，如果没有，则从不限制产出的候选库中随机取一个作为最终产出
			if (result.isEmpty()) {
				int[] config = unlimitOptionList.get(RandomUtil
						.randomInt(unlimitOptionList.size()));
				result.add(genSpecificProps(config[0]));
				ChestKey key = new ChestKey(template.getId(), config[0]);
				map.put(key, map.get(key) + 1);
			}
		} else { // 出产单个
			if (optionList.size() == 1) {
				int[] config = optionList.get(0);
				result.add(genSpecificProps(config[0]));
				ChestKey key = new ChestKey(template.getId(), config[0]);
				map.put(key, map.get(key) + 1);
			} else {
				int random = RandomUtil.randomInt(totalOdds);
				int value = 0;
				for (int[] config : optionList) {
					value += config[2];
					if (random < value) {
						result.add(genSpecificProps(config[0]));
						ChestKey key = new ChestKey(template.getId(), config[0]);
						map.put(key, map.get(key) + 1);
						break;
					}
				}
			}
		}
		return result;
	}

	/**
	 * 宝箱出产Key(宝箱编号+物品库编号)
	 * 
	 * @author jhezjkp@gmail.com
	 * 
	 */
	static class ChestKey {
		private int chestId;
		private int candidateId;

		public ChestKey(int chestId, int candidateId) {
			this.chestId = chestId;
			this.candidateId = candidateId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + candidateId;
			result = prime * result + chestId;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ChestKey other = (ChestKey) obj;
			if (candidateId != other.candidateId)
				return false;
			if (chestId != other.chestId)
				return false;
			return true;
		}

	}
}
