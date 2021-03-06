# java-performance

#### 介绍
书配套例子,**Java系统性能优化实战** ，程序员的优化宝典。购买地址可以从[京东购买](https://item.jd.com/12742086.html)。书中代码包含了关键注释和结论，也可以直接浏览代码获得知识

> 如果对SpringBoot技术有兴趣，可以购买[<SpringBoot2.0精髓>](https://item.jd.com/12214143.html)或者电子版[看云广场购买](https://www.kancloud.cn/xiandafu/springboot2-in-practice/)

#### 大佬评书
看了李兄的第4章“代码性能优化”，里面提到了很多代码优化技巧，非常实用，很多技巧也是t-io经常在用的，比如预编译、预编码、批处理、压缩等。

高性能的程序，都没有容易二字，都是一个细节一个细节抠出来的，希望李兄的这本书可以帮到更多Java工程师和架构师，也祝本书的读者以后Bug少少、性能高高。

——谭耀武（知名网络框架t-io创始人，目前任牛吧云播CTO）



有幸阅读此书，这是一本大道至简的Java性能优化指南，使得我们的开源项目的代码质量和性能有了飞跃，更能够指导每个Java工程师写出优雅、易阅读、高质量、易于扩展的代码。

——肖宇（开源组织dromara.org创始人，分布式事务框架hmily、

API网关soul等开源框架作者）



读了家智的第8章“JIT优化”、第10章“ASM运行时增强”、第11章“JSR269编译时增强”，颇感惊艳。很少有文章或书籍能够将JIT、ASM、JSR269用近乎白话的方式进行较为系统的阐述。家智使用很少的文字把这三个比较深的知识说清楚、带入门，实属难得。

应用性能管理（APM）在高性能的应用系统中相当重要，字节码和运行增强技术点也非常深。本书不仅可以作为Java应用程序性能优化的指导，也是很好的Java APM领域入门工具书。

——高驰涛（SeasLog及多项开源项目作者、SeasX开源团队创始人，APM专家，

目前任云智慧Technical VP）





此书很好地介绍了Java在代码层面的一些优化技巧，同时透过这些技巧很好地解释了JVM在解析代码时的工作方式。其实这种小技巧在熟悉原理后完全可以封装成现成的工具避免重复劳动。Hutool工具也是优化代码后封装的结果，而本书几乎涵盖了这些技巧。不只是性能优化，在代码规范上本书也有很好的诠释。好的规范可以规避大部分的“坑”，因此如果你想少“踩坑”，本书也不容错过。

——路小磊（知名开源Java工具包Hutool作者）





指数级增长面临的技术挑战是系统性能。性能优化是时间和空间的艺术，而运行时优化又是性能优化技术体系的难点所在，一直是软件工程师们的苦处。家智兄的《Java系统性能优化实战》一书有庖丁解牛的味道，从run-time的视角指出了Java代码系统性优化的一些方向和实战技法，可以作为Java程序员追求性能的参考指南甚至案头手册。能先睹为快，真一幸事！

——曹洪伟（百度DuerOS首席布道师）





本书对日常Java开发工作中的性能优化方法做了详尽的阐述，可以很好地指导我们让自己的代码更健壮的同时更高效。书中还为我们提供了高性能工具的使用和建议，非常值得一读。



——王新栋（京东资深架构师，《架构修炼之道》作者）



随着需求功能不断的迭代，系统瓶颈越发明显，本书对工作中遇到的系统性能优化做了详尽的讲解，并提供了一套切实可行的实践指南。如何打破系统的坏味道？如何提高系统的性能？如何设计出高性能的技术架构？阅读本书对读者必有裨益。

——梁桂钊（《高可用可伸缩微服务架构：基于Dubbo、Spring Cloud和Service Mesh》

联合作者）



编程的本质是在时间和空间上组织逻辑结构并管理系统复杂性，这让编程成为非常有趣的工作，同时极具挑战性。大赋在本书中采用短小而贴切的例子就Java编程的几乎所有方面讲述各种技巧与陷阱。全书组织清晰、文笔简明，对于Java新手是必读的cookbook，对于像我这样的Java老人也是一本隽秀的散文集。



——罗格林（ACTFramework作者）

### 目录

第1章　Java代码优化

1.1　可优化的代码

1.2　性能监控

1.3　JMH

1.3.1　使用JMH

1.3.2　JMH常用设置

1.3.3　注意事项

1.3.4　单元测试

第2章　字符串和数字操作

2.1　构造字符串

2.2　字符串拼接

2.3　字符串格式化

2.4　字符串查找

2.5　替换

2.6　intern方法

2.7　StringUtils类

2.8　前缀树过滤

2.9　数字装箱

2.10　BigDecimal

第3章　并发编程和异步编程

3.1　不安全的代码

3.2　Java并发编程

3.2.1　volatile

3.2.2　synchronized

3.2.3　Lock

3.2.4　Condition

3.2.5　读写锁

3.2.6　Semaphore

3.2.7　栅栏

3.3　Java并发工具

3.3.1　原子变量

3.3.2　Queue

3.3.3　Future

3.3.4　ThreadLocal

3.4　Java线程池

3.5　异步编程

3.5.1　创建异步任务

3.5.2　完成后回调

3.5.3　串行执行

3.5.4　并行执行

3.5.5　接收任务处理结果

第4章　代码性能优化

4.1　int转String

4.2　使用Native方法

4.3　日期格式化

4.4　switch优化

4.5　优先使用局部变量

4.6　预处理

4.7　预分配

4.8　预编译

4.9　预先编码

4.10　谨慎使用Exception

4.11　批处理

4.12　展开循环

4.13　静态方法调用

4.14　高速Map存取

4.15　位运算

4.16　反射

4.17　压缩

4.18　可变数组

4.19　System.nanoTime()

4.20　ThreadLocalRandom

4.21　错误优化策略

4.21.1　final无法帮助内联

4.21.2　subString内存泄漏

4.21.3　循环优化

4.21.4　循环中捕捉异常

第5章　高性能工具

5.1　高速缓存Caffeine

5.1.1　安装Caffeine

5.1.2　Caffeine的基本使用方法

5.1.3　淘汰策略

5.1.4　statistics功能

5.1.5　Caffeine高命中率

5.1.6　卓越的性能

5.2　映射工具Selma

5.3　JSON工具Jackson

5.3.1　Jackson的三种使用方式

5.3.2　Jackson树遍历

5.3.3　对象绑定

5.3.4　流式操作

5.3.5　自定义JsonSerializer

5.3.6　集合的反序列化

5.3.7　性能提升和优化

5.4　HikariCP

5.4.1　安装HikariCP

5.4.2　HikariCP性能测试

5.4.3　性能优化说明

5.5　文本处理工具Beetl

5.5.1　安装和配置

5.5.2　脚本引擎

5.5.3　Beetl的特点

5.5.4　性能优化

5.6　MessagePack

5.7　ReflectASM

第6章　Java注释规范

6.1　Javadoc

6.2　Tag

6.2.1　{@link}

6.2.2　@deprecated

6.2.3　{@literal}

6.2.4　{@code}

6.2.5　{@value}

6.2.6　@author

6.2.7　@param和@return

6.2.8　@throws

6.2.9　@see

6.2.10　自动复制

6.3　Package-Info

6.4　HTML的生成

6.5　Markdown-doclet

第7章　可读性代码

7.1　精简注释

7.2　变量

7.2.1　变量命名

7.2.2　变量的位置

7.2.3　中间变量

7.3　方法

7.3.1　方法签名

7.3.2　短方法

7.3.3　单一职责

7.4　分支

7.4.1　if else

7.4.2　switch case

7.5　发现对象

7.5.1　不要使用String

7.5.2　不要用数组、Map

7.6　checked异常（可控异常）

7.7　其他事项

7.7.1　避免自动格式化

7.7.2　关于Null

第8章　JIT优化

8.1　解释和编译

8.2　C1和C2

8.3　代码缓存

8.4　JITWatch

8.5　内联

8.6　虚方法调用

第9章　代码审查

9.1　ConcurrentHashMap陷阱

9.2　字符串搜索

9.3　I/O输出

9.4　字符串拼接

9.5　方法的入参和出参

9.6　RPC调用定义的返回值

9.7　Integer的使用

9.8　排序

9.9　判断特殊的ID

9.10　优化if结构

9.11　文件复制

9.12　switch优化

9.13　Encoder

9.14　一个JMH例子

9.15　注释

9.16　完善注释

9.17　方法抽取

9.18　遍历Map

9.19　日期格式化

9.20　日志框架设计的问题

9.21　持久化到数据库

9.22　某个RPC框架

9.23　循环调用

9.24　lock的使用

9.25　字符集

9.26　处理枚举值

9.27　任务执行

9.28　开关判断

9.29　JDBC操作

9.30　Controller代码

9.31　停止任务

第10章　ASM运行时增强

10.1　Java字节码

10.1.1　基础知识

10.1.2　.class文件的格式

10.2　Java方法的执行

10.2.1　方法在内存中的表示

10.2.2　方法在.class文件中的表示

10.2.3　指令的分类

10.2.4　操作数栈的变化分析

10.3　Bytecode Outline插件

10.4　ASM入门

10.4.1　生成类名和构造函数

10.4.2　生成main方法

10.4.3　调用生成的代码

10.5　ASM增强代码

10.5.1　使用反射实现

10.5.2　使用ASM生成辅助类

10.5.3　switch语句的分类

10.5.4　获取Bean中的property

10.5.5　switch语句的实现

10.5.6　性能对比

第11章　JSR269编译时增强

11.1　Java编译的过程

11.2　注解处理器入门

11.3　相关概念介绍

11.3.1　AbstractProcessor

11.3.2　Element与TypeMirror

11.4　注解处理器进阶

11.4.1　JsonWriter注解

11.4.2　处理器与生成辅助类

11.4.3　使用生成的Mapper类

11.4.4　注解处理器的使用

11.5　调试注解处理器

11.5.1　在Eclipse中调试注解处理器

11.5.2　在IDEA中调试注解处理

附录A　使用OQL分析虚拟机内存**