#游戏物品系统设计
---


## 说明

游戏中的物品系统主要包括道具、装备两种，以下用卡牌游戏进行说明。

拟设计以下道具：

1. 体力丹：使用后增加体力
2. 培养丹：玩家进行属性培养时需要消耗培养丹
3. VIP月卡
4. 新手礼包：固定出产若干种物品
5. VIP礼包：固定或随机出产若干种物品
6. 卡牌经验符：可以分不同的品质，用来给指定的某张卡牌加经验，不同品质的经验符加的经验不同
7. 银两：银两作为一种道具不会出现在玩家背包中，但它可能在礼包中出现，在打开礼包的同时就生效--即给玩家加上对应的银两
	
## 道具设计

首先分析一下道具的种类，从使用角度来看，道具主要分为以下几种：
	
* 消耗类道具：本身没有业务逻辑，只是做为其他功能/操作的消耗品，如强化材料、培养丹等
* 丹药类道具：如恢复体力、恢复魔法的药剂
* 其他道具：如VIP月卡，使用后在一定时间范围内享受VIP待遇
		
从程序员的角度来看，道具又分为以下几个层次：

* 展现层：如作为商品展现，作为背包中的物品展现，主要用来给客户端提供展现数据，如名称、图标等
* 生成层：如固定宝箱(生成的物品是确定的)、随机宝箱(生成的物品是随机的)等可以生成具体的某一种或某几种道具，该层仅在服务端生效，对客户端透明
* 使用逻辑层：具体执行道具的业务逻辑的层，使用效果需要向客户端反馈，比如，用了体力恢复药剂后，客户端将收到体力恢复的通知反馈

在设计一种道具时需要从以上两种角度来分析，以确保最终的设计方案灵活有效。

### 道具模板的一般属性

* 编号
* 名称
* 描述
* 类型
* 最大堆叠数
* 使用对象
* 图标编号

### 道具的一般属性

* 实例编号
* 模板编号
* 数量

	
	
## 装备设计
一般而言，装备一般分为以下几种：

* 武器(加攻)
* 防具(加防)
* 饰品(加血)


	
