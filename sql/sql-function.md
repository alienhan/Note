---
title: sql 方法
date:
categories:
- sql方法
tags:
- sql方法
---


### 合并多个列   mysql
```sql
SELECT
    uniMark,operateTime,rcsCode,rcsCodeName,quota,
    GROUP_CONCAT(type, ":", quota) AS index_quota
FROM
    t_quota_cashier_pay_channel_code
GROUP BY
    `rcsCode`;
```
group_concat(param1,param2,param3)  
param1:分组字段  
param2:分割符  
param3:被分组字段  

根据分组显示合并的字符串  
eg: type1:quota1,type2:quota2  
