http://staff.ustc.edu.cn/~lszhuang/alg/

数据有两种搞搞的方式
    处理(计算)
    存储
算法应该都是围绕这两方面搞的


#分析方法
增量法(穷举法) 暴力搜寻法
    暴力破解
    回溯法 https://zh.wikipedia.org/wiki/回溯法
        对于某些计算问题而言，回溯法是一种可以找出所有（或一部分）解的一般性算法，尤其适用于约束满足问题（在解决约束满足问题时，我们逐步构造更多的候选解，并且在确定某一部分候选解不可能补全成正确解之后放弃继续搜索这个部分候选解本身及其可以拓展出的子候选解，转而测试其他的部分候选解）。

分治法
    步骤: 分解,解决,合并
    code: 递归式
    实现: 分治是把数组按元素分成最小份,切割原有样本
    在每一层递归上都有三个步骤：
        分解：将原问题分解为若干个规模较小，相对独立，与原问题形式相同的子问题。
        解决：若子问题规模较小且易于解决时，则直接解。否则，递归地解决各子问题。
        合并：将各子问题的解合并为原问题的解。
            一般需要辅助数组,层层合并
    归并排序
    快速排序
动态规划
    https://zh.wikipedia.org/wiki/动态规划
    动态规划在查找有很多重叠子问题的情况的最优解时有效。它将问题重新组合成子问题。为了避免多次解决这些子问题，它们的结果都逐渐被计算并被保存，从简单的问题直到整个问题都被解决。因此，动态规划保存递归时的结果，因而不会在解决同样的问题时花费时间。
    `动态规划只能应用于有最优子结构的问题`。最优子结构的意思是局部最优解能决定全局最优解（对有些问题这个要求并不能完全满足，故有时需要引入一定的近似）。简单地说，问题能够分解成子问题来解决。
    `NP`
    非定常多项式（英语：non-deterministic polynomial，缩写：NP）时间复杂性类，或称非确定性多项式时间复杂性类，包含了可以在多项式时间内，对一个判定性算法问题的实例，一个给定的解是否正确的算法问题。
    `复杂性类`
    将计算问题按照在不同计算模型下所需资源的不同予以分类，从而得到一个对算法问题“难度”的类别，就是复杂性理论中复杂性类概念的来源。例如一个问题如果在确定性图灵机上所需时间不会超过一个确定的多项式（以输入的长度为多项式的不定元），那么我们称这类问题的集合为P（polynomial time Turing machine）。而将前述定义中的“确定性图灵机”改为“不确定性图灵机”，那么所得到的问题集合为NP（non-deteministic polynomial time Turing machine）。类似的，设 {\displaystyle n} n为输入的长度，那我们可以定义“在确定性图灵机上所需空间不超 {\displaystyle O(\log n)} O(\log n)的算法问题的集合”（即为 {\displaystyle L} L），“存在深度为 {\displaystyle O(\log n)} O(\log n)，输入的度（fan-in）为 {\displaystyle O(1)} O(1)的电路族（circuit family）的算法问题的集合”（即为NC1）等等复杂性类。
    NP困难问题和NP完全问题[编辑]
    要理解这几个概念，首先要明白几件事：
    对于NP问题是否存在确定的多项式时间的解，目前还不清楚（即有可能有一天可以证明NP问题=P问题，但目前还证明不出来、也不能证明NP问题≠P问题，目前的结论只是NP问题集⊇P问题集
    问题之间可以规约，即如果某个NP问题存在确定的多项式时间解，那么另一个NP问题也存在确定的多项式时间解。这个过程是可以证明的、并且已经被证明。
    NP困难问题（NP-hard problems）
    是指这样的一类问题，它们本身的复杂度是多少无所谓（但由后面的论述可知至少是NP），但是只要这个问题找到确定的多项式时间的解，那么我们可以证明出所有的NP问题都一定存在确定的多项式时间的解。（简单叙述一下就是，只要有一个NP困难问题找到P解，那么所有NP问题都是P问题）
    NP完全问题（NP-complete problems）
    如果一个问题既是NP困难问题又是NP问题，我们称之为NP完全问题。
    `NPC`
    https://zh.wikipedia.org/wiki/NP完全
    可归约在此意指对每个问题L，总有一个多项式时间多对一变换，即一个决定性的算法可以将实例l ∈ L转化成实例c ∈ C，并让c回答Yes当且仅当此答案对l也是Yes。为了证明某个NP问题A实际上是NPC问题，证明者必须找出一个已知的NPC问题可以变换成A。
        布尔可满足性问题
        N-puzzle问题（华容道问题）
        背包问题
        汉弥尔顿循环问题
        旅行推销员问题
        子图同构问题：（Subgraph isomorphism problem）
        子集合加总问题
        分团问题
        顶点涵盖问题
        独立顶点集问题：（Independent set problem）
        图着色问题
        扫雷[2]

    返回最大连续子数组和
    返回不含重复最大子字符串
        转化成一个循环内的临时最大值currSum(局部最优解),
        与所有的最大值maxSum比较问题

    寻找最小的f个数
        转化成内部f个数的排序

    返回所有和等于sum的组合
        转化成包含,不包含问题


贪心法
    贪心法是一种在每一步选择中都采取在当前状态下最好或最优（即最有利）的选择，从而希望导致结果是最好或最优的算法。
    贪心算法与动态规划的不同在于它对每个子问题的解决方案都做出选择，不能回退。动态规划则会保存以前的运算结果，并根据以前的结果对当前进行选择，有回退功能。


数据结构
    是一种存储和组织数据的方式,旨在便于访问和修改