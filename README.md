# Echo
手机软件大作业

4-28  项目初始化。

4-29  由于空目录没有被上传，因此在空目录中创建了占位的文件以保证其能同步到GitHub。

4-29 16:30  大概地做出了启动页面和主导航页面的activity

5-03 23:23  进一步做出了主导航页面-首页和排行榜页的基本布局.

            主页:添加了搜索条,首页轮转图的控件.

            排行榜页:暂时复制主页.

5-11 23:32实现了主导航页面中的首页和排行榜页的视图细节.暂无设计

点击tabitem或导航的item,都会使当前页面滚动到顶部.

    首页:
    
        轮转头条:添加了切换动画,标题.
        
        添加了简略版的排行榜:热门app\新品app\开发者榜
        
            每个排行榜都显示12项(viewpager4页 × recyclerview3项)
            
    排行榜页:
    
        添加了tablayout + viewpager来显示三个排行榜,fragment复用首页的简略版,但列表item个数不限于3个,同样更换了切换动画.
