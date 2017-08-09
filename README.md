#### SpringBoot微信点餐系统 --慕课网

#### 日志的使用 
一个日志方案，通常是使用slf4j作为日志框架的接口，而日志实现则有多种：
* log4j：已经被作者抛弃，觉得其太烂，无法修改，直接重写出了LogBack
* log4j2:和log4j无关，只是Apache借了个名而已，性能最优秀，但是目前bug有点多
* logBack:log4j作者重写的日志框架，十分好;

SpringBoot默认采用slf4j+logBack

* 在SpringBoot中，只需要使用Logger logger = LoggerFactory.getLogger(Xxx.class)(sif4j包)即可获得日志对象;
    * yml文件中可以实现日志文件简单的配置：
        ~~~
            logging:
              pattern:
                #控制台日志输出格式
            #    console: "%d - %msg%n"
              #日志配置文件的路径
            #  config:
              #日志文件输出路径，只是路径名
            #  path: D://log
              #日志文件输出路径，包含文件名
            #  file: D://a.log
              #最低输出日志等级
            #  level:
                #如果下写法在level中可以配置某个类的最低输出日志等级
            #    com.zx.LoggerTest: debug
        ~~~
    * 也可以直接在resources文件夹下添加logback-spring.xml文件，配置日志

#### 实体类getter/setter/toString方法的省略
该框架并非程序运行时动态添加，而是打包时添加到class文件中的,所以不会对性能产生影响
* 在pom.xml中引入lombok jar(SpringBoot中似乎很多jar都可以不写version):
    ~~~
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
            </dependency>
    ~~~
* 然后在IDEA中安装插件lombok,Eclipse去lombok官网下载jar，安装到eclipse中即可
* 然后在实体类上写上注释
~~~
        @Data   ：注解在类上；提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法
        @Setter：注解在属性上；为属性提供 setting 方法
        @Getter：注解在属性上；为属性提供 getting 方法
        @Log4j ：注解在类上；为类提供一个 属性名为log 的 log4j 日志对象
        @@Slf4j:注解在类上，为类提供一个 属性名为log的slf4j日志对象
        @NoArgsConstructor：注解在类上；为类提供一个无参的构造方法
        @AllArgsConstructor：注解在类上；为类提供一个全参的构造方法 
~~~

#### BeanUtils工具类的使用
* 可以将A实体类中所有和B实体类中属性名相同的属性的值复制到B，注意，Null值也会被复制过去
* 当含有util.Date属性时，尤其是util.Date为null时，会出错;其子类sql.util是可以被支持的

#### CollectionUtils类的使用
* 可以判断集合是否为空或者元素为0，以及是否包含某些元素等

#### timestamp格式在从数据库取出转为date后，是ms（毫秒）格式，如果需要返回的是s（秒），也就是需要除以1000
* 可以构造一个类，继承JsonSerializer<T>类，然后重写serializer()方法，在该方法中将date.getTime()/1000
* 然后在被转换的实体类的对应Date属性上，加上@JsonSerializer(using=类名.class)注解即可
* 这样的作用是SpringMVC把后台实体类转为JSON返回给前端时，对T类型的数据进行特殊的操作
* 详见Date2LongSerializer类和OrderDTO类

* 自己写了个String2TestStringSerializer类，实现了将返回的String属性末尾都加上了一串字符串

#### SpringMVC，在要返回成JSON的实体类上+ @JsonInclude(JsonInclude.Include.NON_NULL)注解，不会返回空对象
* 也可以直接在yml文件中作 spring:jackson:default-property-inclusion: non_null的全局配置，不用加注解
* 如果需要返回默认的值，可以直接在实体类中给对应属性赋初始值

#### 微信开发可以使用https://github.com/wechat-group/weixin-java-tools该项目上的依赖

#### 枚举类
如果需要枚举类拥有相同的属性，可以定义一个接口；  
例如一个A接口定义了getCode()和getMessage()方法；  
那么所有实现该接口的枚举类都需要定义code和message属性；  
然后可以写工具类，一个泛型方法，可以对实现该接口的所有枚举，通过code返回对象

#### 分布式系统：物理架构中不共享主内存，通过网络发送消息合作
* 多节点
* 消息通信
* 不共享内存
* 集群是指同一服务多节点；分布式是不同服务多节点

#### 分布式session
* 登录时生成一个token，存入redis和cookie
* 登出时删除用户的cookie即可

* 可以使用spring mvc中的aop来进行登录校验
* 如果失败可以抛出指定异常
* 然后定义SpringMVC的ExceptionHandler，在其中指定处理该异常，跳转到登录页面
* @ResponseStatus注解可以返回给前端指定的HTTP状态码
* 在被@ControllerAdvice注解的异常处理类中，也可以直接返回ModelAndView等；包括@ResponseBody等注解都可以使用
* 所以可以在其他位置抛出对应的AException，然后定义一个AException处理方法，将对应的错误码和错误信息直接返回给前端

#### Apache ab 压测工具 简单的语句即可，非常方便
