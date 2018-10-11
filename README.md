# weixin-fapiao
微信小程序 -- 发票重复校验

背景：由于电子发票可以重复打印进行，以至于财务人员在进行报销的发票时，无法区分此发票是否已经报销过了，所以开始此软件通过自动扫码和手动录入方式记录发票信息，以防止电子发票重复报销。

在开发小程序之前开发过一个Android APP 鉴于部分手机是苹果手机，难不成需要购买开发者账号，作为后端开发人员，放弃了，最终选择了微信小程序，方便快捷，不用考虑适配问题


功能

    1. 新增
        1.1 手动录入
        1.2 自动扫二维码录入
![image](https://github.com/wangjie201701/weixin-fapiao/blob/master/images/add.jpg)
    
    2. 历史发票记录
        2.1 查看已录入发票信息
        2.2 删除已录入发票
![image](https://github.com/wangjie201701/weixin-fapiao/blob/master/images/history.jpg)    
    3. 查询
       3.1 手动查询
       3.2 自动扫二维码查询
![image](https://github.com/wangjie201701/weixin-fapiao/blob/master/images/query.jpg)
    4. 统计
       默认按照月统计
       4.1 按照 今天、昨天、本周、本月、本年
![image](https://github.com/wangjie201701/weixin-fapiao/blob/master/images/tongji.jpg) 
