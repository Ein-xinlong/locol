# locol是一个基于kotlin+mvvm 组件化开发的项目
###########环境依赖
gradle-7.5
###########目录结构描述
├── Readme.md                   // help
├── app                         // 应用
├── build                      // 配置
│   ├── default.json
│   ├── dev.json                // 开发环境
│   ├── experiment.json         // 实验
│   ├── index.js                // 配置控制
│   ├── local.json              // 本地
│   ├── production.json         // 生产环境
│   └── test.json               // 测试环境
├── common                      // 公共
│     ├── base                  // 基类
│     ├── dialog                // 公共dialog
│     ├── router                // 路由配置
│     └── status                // 底部导航栏颜色枚举
├── home                        // 主页
└── tools



```mermaid
	flowchart TD;
	     A-->B;
	     A-->C;
	     B-->D;
	     C-->D;
```




- [x]路由配置
- [ ]base开发
- [x]baseActviity
- [ ]baseFragment
- [ ]baseViewmodel
- [ ]baseModel
- [ ]网络框架
- [ ]业务逻辑
- [ ]主页开发
- [ ]功能页开发
- [ ]我的页面
