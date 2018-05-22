
常用的编译app sencha命令：
sencha package build 打包命令
sencha ant clean //清除编译制品
sencha app build development //构建为开发环境项目
sencha app build production //构建为生成环境项目
sencha app refresh //可在添加类、删除类或更改类名后运行。它会通过Ext.Loader来更新bootstrap文件，方便开发模式的调试
sencha ant sass //该命令会根据Sass源代码重新生成CSS文件。它可在任何scss文件改变后运行。

```json
{
 ...
   "env": "${build.environment}", 
   "serviceUrl":"${app.url}", //定义Ext下的全局变量，可直接引用workspace中添加的属性配置
   "theme": "theme-test", //可在此处指定app所需的主题
   "requires":[
         "font-awesome", //字体图标
         "commonWidget", //上方创建的公共包
         "ext-locale", //国际化
         ...
     ]
 ...
 }
```



