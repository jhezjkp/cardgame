package me.vivia.game.utils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import me.vivia.game.props.CandidateT;
import me.vivia.game.props.ChestT;
import me.vivia.game.props.ItemT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import clojure.lang.RT;

/**
 * 脚本工具类
 * 
 * @author jhezjkp@gmail.com
 * 
 */
public class ScriptUtil {

	private static final Logger logger = LoggerFactory
			.getLogger(ScriptUtil.class);

	private static final String ns = "me.vivia.game.props";

	static {
		try {
			RT.loadResourceScript("PropsDef.clj");
			RT.loadResourceScript("ItemDef.clj");
			RT.loadResourceScript("ItemTemplates.clj");
			RT.loadResourceScript("CandidateTemplates.clj");
			RT.loadResourceScript("ItemInit.clj");
			RT.loadResourceScript("ChestTemplates.clj");
			RT.loadResourceScript("ChestInit.clj");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("xxxxxxxx脚本加载失败xxxxxxxxx");
		}
	}

	/**
	 * 获取所有的道具模板
	 * 
	 * @return
	 */
	public static List<ItemT> getAllItemTemplates() {
		try {
			return (List<ItemT>) RT.var(ns, "item-templates-cache").deref();
		} catch (Exception e) {
			logger.error("获取所有道具模板时发生异常", e);
			return Collections.EMPTY_LIST;
		}
	}

	/**
	 * 根据编号查找道具模板
	 * 
	 * @param templateId
	 * @return
	 */
	public static ItemT findItemTemplate(int templateId) {
		try {
			return (ItemT) RT.var(ns, "find-item-template").invoke(templateId);
		} catch (Exception e) {
			logger.error("查找道具模板时发生异常", e);
			return null;
		}
	}

	/**
	 * 根据编号查找物品候选库
	 * 
	 * @param templateId
	 * @return
	 */
	public static CandidateT findCandidateTemplate(int templateId) {
		try {
			return (CandidateT) RT.var(ns, "find-candidate-template").invoke(
					templateId);
		} catch (Exception e) {
			logger.error("查找物品候选库时发生异常", e);
			return null;
		}
	}

	/**
	 * 根据模板编号判断指定物品是否是道具
	 * 
	 * @param templateId
	 * @return
	 */
	public static boolean isItem(int templateId) {
		try {
			return (Boolean) RT.var(ns, "is-item-template").invoke(templateId);
		} catch (Exception e) {
			logger.error("根据模板编号判断是否道具时发生异常", e);
			return false;
		}
	}

	/**
	 * 获取所有的宝箱模板
	 * 
	 * @return
	 */
	public static List<ChestT> getAllChestTemplates() {
		try {
			return (List<ChestT>) RT.var(ns, "chest-templates-cache").deref();
		} catch (Exception e) {
			logger.error("获取所有宝箱模板时发生异常", e);
			return Collections.EMPTY_LIST;
		}
	}

}
